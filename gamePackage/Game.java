package gamePackage;

import gamePackage.interfacePackage.Display;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.interfacePackage.WriteInLog;
import gamePackage.playerPackage.*;

import java.util.Scanner;
import java.util.ArrayList; // for dynamic array of Player

public class Game{

    // Attributes
    private ArrayList<Player> players;
    private Grid grid;
    private int[] score;             
    private int rounds;              
    private final int tokens;       
    private final int numberPlayers; 

    private final static Display disp = new Display();
    private final static WriteInLog write = new WriteInLog();
    private final static CheckInput check = new CheckInput();

    // Constructor
    public Game(String[] inputPlayers, int numberPlayers, int width, int height, int[] score, int rounds, int tokens, String[] symbols){
      int name;
      int type;
      int numeroPlayer;
      this.players = new ArrayList<Player>(); 

      for (int i = 0; i<2*numberPlayers; i+=2) {
          type = i;
          name = i + 1;
          numeroPlayer = (i / 2) + 1;

          if (inputPlayers[type].equals("human"))
              this.players.add(new Human(inputPlayers[name], numeroPlayer, inputPlayers[type]));
          else if (inputPlayers[type].equals("ia:monkey"))
              this.players.add(new Monkey(inputPlayers[name], numeroPlayer, inputPlayers[type]));
          else if (inputPlayers[type].equals("ia:pro"))
              this.players.add(new Pro(inputPlayers[name], numeroPlayer, inputPlayers[type]));
          else if (inputPlayers[type].equals("ia:random"))
              this.players.add(new Ia(inputPlayers[name], numeroPlayer, inputPlayers[type]));
          else // ia:random
              this.players.add(new Ia(inputPlayers[name], numeroPlayer, inputPlayers[type]));

      }
      this.grid = new Grid(width, height, symbols);
      this.score = score;
      this.rounds = rounds;
      this.tokens = tokens;
      this.numberPlayers = numberPlayers;
    }

    // Getteurs
    public Grid getGrid(){
        return grid;
    }

    public Player getPlayer(int who){
       return players.get(who-1);
    }

    public int getScore(int i){
        return score[i];
    }

    public int getRounds(){
      return rounds;
    }

    public int getNumberPlayers(){
      return numberPlayers;
    }

    public int getTokens(){
      return tokens;
    }

    public int getHeight(){
        return grid.getHeight();
    }

    public int getWidth(){
        return grid.getWidth();
    }

    // Setters
    private void setScore(int i){
        score[i] += 1;
    }

    // Check if player has win recquired rounds
    private int checkRounds(){
      int res = 0;
      for (int i = 0; i<this.numberPlayers; i++){
        if (this.score[i] == this.rounds)
          res = 1;
      }
      return res;
    }

    // Return 1 (True) if the total number of rounds played = the number of rounds to be played
    public int checkRoundsBestOf(){
        int res = 0;
        int sum = 0;
        for (int i = 0; i<this.numberPlayers; i++){ 
            sum += this.score[i]; // Each player adds his victory number to the sum
        }
        if (sum == this.rounds) // If the sum is equal to the number of rounds then the game ends
            res = 1;
        return res;
    }

    // Write score into a string
    public String writeScore(){
      String res;
      res = "Score "+this.score[0];

      for (int i = 1; i<this.numberPlayers; i++){
        res = res+" - "+this.score[i];
      }
      return res;
    }

    // Return the current run number
    private int getRoundNumber(){
        int sum = 1; // rounds start at round 1
        for (int i = 0; i<numberPlayers; i++){
            sum += score[i];
        }
        return sum;
    }

    // Return the number of the player who will play this round
    private int whichPlayer(int tour, int roundNumber){
      int res = tour%this.numberPlayers; // 1st tour = 1, 2nd = 0, then 1 ect (for 2 players)

      int roundParity = (roundNumber-1)%this.numberPlayers; // 1st round = 0, 2nd round = 1, then 0 ect (still for two players)

      res = res + roundParity; 

      if (res == 0) // If both are at zero, it's the last player's turn to play
          res = this.numberPlayers;

      if (res > this.numberPlayers) // If the total number of players is exceeded, a new modulo must be performed
          res = res%this.numberPlayers;

      return res;
    }

    // Return True if array isnot nul
    private int nullTab(int[] tab, int len){
      int bool = 0;
      for (int i=0; i<len; i++){
        if (tab[i] == 1) // If a player has won then 1 is returned
            bool = 1;
      }
      return bool;
    }

    // Actions during the game
    public void play(){
      int tour = 1; // i = tour number (player 1 starts playing) 
      int who; // player number
      int position = -3; // -3 corresponds to an invalid move (full column)
      int[] wins = new int[this.numberPlayers];
      int equality = 0;

      disp.displayGrid(grid);

      // As long as no player has won the round and there is no tie, the game continues
      while(nullTab(wins, this.numberPlayers) == 0 && equality == 0){
        who = whichPlayer(tour, getRoundNumber()); // For 2 players, who = 1 or 2
        position = -3; // Initialization to the value -3 which corresponds to an invalid position

        while(position == -3){
            position = this.getPlayer(who).choice(grid.getValues(), grid.getWidth(), grid.getHeight(), this.tokens);

            if (position == -1) // command "quit"
                System.exit(0);

            if (position == -2) {
                disp.gameParameters(this);
                position = -3; // We put the "player's choice" back on hold
            }
            else if (position == -1) { // command "quit"
                System.exit(0);
            }
            else{
                if (getPlayer(who).getType().equals("human")) 
                  System.out.println("");

                position = grid.updateGrid(position, who, write);
            }
        }

        if (position == -1) // command "quit"
          System.exit(0);

        disp.displayGrid(grid);
        
        if(grid.victoryCheck(this.tokens,position-1) == 1){ 
          write.writeBuffer("Player "+who+" won");
          System.out.println("Player "+who+" won");
          setScore(who-1); 
          wins[who-1] = 1; 
        }

        else if(grid.equalityCheck() == 1){
          equality = 1;
          write.writeBuffer("Tie");
          System.out.println("Tie");
        }

      tour++; 
      }
      grid.resetGrid(); 
    }

}