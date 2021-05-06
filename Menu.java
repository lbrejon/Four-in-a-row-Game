import gamePackage.interfacePackage.WriteInLog;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.interfacePackage.BonusMenu;
import gamePackage.Game;
import java.util.Scanner;

public class Menu{

  // Using methods from WriteInLog, CheckInput and BonusMenu
  private static WriteInLog write; 
  private static CheckInput check; 
  private static BonusMenu menu;  

  public static void main(String[] args){
    int numberPlayers = 2; 
    int width = 7;  
    int height = 6; 
    int rounds = 3; 
    int tokens = 4; 
    int[] score;    
    String[] inputPlayers;
    String[] symbols = {". ", "X ", "O ", "V ", "T ", "Y ", "@ "}; // Players symbols (index 0 = nobody)

    // Menu display
    int[] parameters = new int[5];
    String[] parametersString = new String[parameters.length+symbols.length];

    parametersString = menu.parametersMenu(numberPlayers, width, height, rounds, tokens, symbols);
    parameters = menu.convertStringToInt(menu.getStringFromTo(parametersString,0,4));
    numberPlayers = parameters[0];
    width = parameters[1];
    height = parameters[2];
    rounds = parameters[3];
    tokens = parameters[4];
    symbols = menu.getStringFromTo(parametersString,5,parametersString.length-1);

    score = new int[numberPlayers];

    // Choose pseudo + type
    inputPlayers = selectPlayers(numberPlayers);
    if (inputPlayers[0].equals("exit")){
      System.exit(0);
    }

    // Game initialization
    Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds, tokens, symbols);
    System.out.println("");

    // Beginning of the game
    while(game.checkRoundsBestOf() == 0){
      write.writeBuffer("Beginning of the round");
      game.play();
      write.writeBuffer(game.writeScore());
    }

    System.out.println("End of the game");
    write.writeBuffer("End of the game");
  }



  // Creation of the log.txt file
  private static String[] selectPlayers(int numberPlayers){
    Scanner scanner = new Scanner(System.in);
    String s = new String("Player ");
    String res[] = new String[2*numberPlayers];
    int cntPlayer;

    write.createLog();

    for (int i = 0; i<2*numberPlayers; i +=2){
      String buf[] = new String[2];
      String name = new String();
      String type = new String();

      cntPlayer = (i/2)+1;

      System.out.println(s+cntPlayer+"'s pseudo ?"); // Joueur i?

      buf = check.checkNameType(scanner.nextLine(), cntPlayer);
      if (buf[0].equals("exit")){
        return buf;
      }
      type = buf[0];
      name = buf[1];

      write.writeBuffer("Player "+cntPlayer+" is "+type+" "+name);
      res[i] = type;
      res[i+1] = name;
    }

    return res; // type1 name1 type2 name2 ..
  }
    
}
