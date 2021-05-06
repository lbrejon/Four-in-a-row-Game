package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;
import java.util.Random;

public class Pro extends Player{

  public Pro(String name,int numeroPlayer, String type){
      super(name,numeroPlayer,type);
      this.write = new WriteInLog();
  }

  // This method is the same as Monkey.java, see Monkey.java for comments
  private static int possibleWin(int[][] grid, int width, int height){
    for (int j=0;j<height;j++) {
      for (int i=0;i<width;i++) {
          if (j>2){
            if ((grid[j][i]==grid[j-1][i])&&(grid[j][i]==grid[j-2][i])&&(grid[j][i]!=0)) {
              if (grid[j-2][i]!=0) {
                if (grid[j-3][i]==0) {
                  return i;
                }
              }
            }
          }
          if (i>3){
            if ((grid[j][i]==grid[j][i-1])&&(grid[j][i]==grid[j][i-2])&&(grid[j][i]!=0)){
              if((j==height-1)&&(grid[j][i-3]==0)){
                return i-3;
              }
              if(j<height-1){
                if ((grid[j][i-3]==0)&&(grid[j+1][i-3]!=0)){
                  return i-3;
                }
              }
          }
        }

          if (i<width-3){
            if(((grid[j][i]==grid[j][i+1])&&(grid[j][i]==grid[j][i+2]))&&(grid[j][i]!=0)){
              if(j==height-1){
                if(grid[j][i+3]==0){
                  return i+3;
                }
              }
              if(j<height-1){
                if ((grid[j][i+3]==0)&&(grid[j+1][i+3]!=0)){
                  return i+3;
                }
              }
            }
          }
          if ((j>3)&&(i>3)){
            if ((grid[j][i]==grid[j-1][i-1])&&(grid[j][i]==grid[j-2][i-2])&&(grid[j][i]!=0)){
              if ((grid[j-3][i-3]==0)&&(grid[j-2][i-3]!=0)){
                return i-3;
              }
            }
          }
          if((j>3)&&(i<width-3)){
            if ((grid[j][i]==grid[j-1][i+1])&&(grid[j][i]==grid[j-2][i+2])&&(grid[j][i]!=0)){
              if ((grid[j-3][i+3]==0)&&(grid[j-2][i+3]!=0)){
                return i+3;
              }
            }
          }
          if ((j<height-3)&&(i<width-3)){
            if ((grid[j][i]==grid[j+1][i+1])&&(grid[j][i]==grid[j+2][i+2])&&(grid[j][i]!=0)){
              if ((grid[j+3][i+3]==0)&&(j+3==height-1)){
                return i+3;
              }if(j+3<height-1){
                if ((grid[j+3][i+3]==0)&&(grid[j+4][i+3]!=0)) {
                  return i+3;
                }
              }
            }
          }
          if ((j<height-3)&&(i>3)) {
            if ((grid[j][i]==grid[j+1][i-1])&&(grid[j][i]==grid[j+2][i-2])&&(grid[j][i]!=0)){
              if ((grid[j+3][i-3]==0)&&(j+3==height-1)){
                return i-3;
              }if(j+3<height-1){
                if ((grid[j+3][i-3]==0)&&(grid[j+4][i-3]!=0)) {
                  return i-3;
                }
              }
            }
          }
      }
    }
    return -1;
  }


  // Return a random number
  private static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    return nb.nextInt((max-min)+1)+min;
  }


  public int choice(int[][] grid, int width, int height, int tokens){
    int position;
    position=possibleWin(grid, width, height);
    if (position>-1) {
      while(grid[0][position]!=0) {
          position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
      }
    }
    if(position==-1){
      // if the AI does not find an alignment of three tokens, it plays randomly according to a Gaussian centered in the middle of the board
      position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
      while(grid[0][position]!=0) {
        position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
      }
    }
    return position+1;
  }


}
