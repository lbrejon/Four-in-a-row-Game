package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;

public abstract class Player {

  private final String name;
  private final String type;
  private final int numeroPlayer;
  WriteInLog write;

  public Player(String name, int numeroPlayer, String type) {
    this.name = name;
    this.numeroPlayer = numeroPlayer;
    this.type = type;
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getType(){
    return this.type;
  }

  public int getNumeroPlayer() {
    return numeroPlayer;
  }

  // Méthodes
  public abstract int choice(int[][] grid, int width, int height, int tokens);
}
