package gamePackage.interfacePackage;

import gamePackage.Game;
import gamePackage.Grid;

public class Display {


  // Display the grid
  public static void displayGrid(Grid grid) {
    int width = grid.getWidth();
    int height = grid.getHeight();

    String lineNumero = new String("1");

    for (int k = 2; k < width + 1; k++)
      lineNumero = lineNumero + " " + k;

    System.out.println(lineNumero);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++)
        System.out.print(Color.setColor(grid.getSymbols(grid.getValues()[i][j]), grid.getValues()[i][j], 0));
      System.out.println("");
    }
    System.out.println("");
  }

  // Display of the game parameters
  public static void gameParameters(Game game) {
    System.out.println(Color.setColor("\n[GAME]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Victory: The one with the most wins on the " + game.getRounds() + " rounds");
    System.out.println("Round : " + game.getTokens() + " tokens to align to win 1 round");
    System.out.println(game.writeScore());

    System.out.println(Color.setColor("\n[GRID]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Height : " + game.getHeight() + " rows");
    System.out.println("Width : " + game.getWidth() + " columns");

    System.out.println(Color.setColor("\n[PLAYERS]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Number of players : " + game.getNumberPlayers());
    for (int i = 1; i<=game.getNumberPlayers(); i++)
      System.out.println("Player "+i+" is "+game.getPlayer(i).getName()+" of type "+game.getPlayer(i).getType()+" (symbol : "+Color.setColor(game.getGrid().getSymbols(i),i,0)+")");
    System.out.println("");
  }
}
