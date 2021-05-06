package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;
import java.util.Random;

public class Ia extends Player{

  public Ia(String name, int numeroPlayer, String type){
    super(name,numeroPlayer,type);
    this.write = new WriteInLog();
  }

  // Retourne un nombre aléatoire
  private static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    
    return nb.nextInt((max-min)+1)+min;
  }

  // Action du joueur
  public int choice(int[][] grid, int width, int height, int tokens){
    int position;
    int min = 1;

    position = getRandomNumber(min,width);

    return position;
  }


}
