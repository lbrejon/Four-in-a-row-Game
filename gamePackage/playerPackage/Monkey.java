package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;
import java.util.Random;

public class Monkey extends Player{

  public Monkey(String name,int numeroPlayer, String type){
      super(name,numeroPlayer,type);
      this.write = new WriteInLog();
  }

  private static int isPlayable(int [][] grid,int x,int y){
    if (grid[x][y]!=0) {
      return 1;
    }else{
      return 0;
    }
  }

  // Find the alignments of three tokens
  private static int possibleWin(int[][] grid, int width, int height, int tokens){
    for (int j=0;j<height;j++) {
      for (int i=0;i<width;i++) {
        // upward alignment
          if (j>2){
            if ((grid[j][i]==grid[j-1][i])&&(grid[j][i]==grid[j-2][i])&&(grid[j][i]!=0)) {
              if (grid[j-2][i]!=0) {
                if (grid[j-3][i]==0) {
                  return i;
                }
              }
            }
          }
        // left alignment
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

        // right alignment
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
        // up and left alignment
          if ((j>3)&&(i>3)){
            if ((grid[j][i]==grid[j-1][i-1])&&(grid[j][i]==grid[j-2][i-2])&&(grid[j][i]!=0)){
              if ((grid[j-3][i-3]==0)&&(grid[j-2][i-3]!=0)){
                return i-3;
              }
            }
          }
        // up and right alignment
          if((j>3)&&(i<width-3)){
            if ((grid[j][i]==grid[j-1][i+1])&&(grid[j][i]==grid[j-2][i+2])&&(grid[j][i]!=0)){
              if ((grid[j-3][i+3]==0)&&(grid[j-2][i+3]!=0)){
                return i+3;
              }
            }
          }
          // down and right alignment
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
        // down and left alignment
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
    position = possibleWin(grid, width, height, tokens);
    if (position>-1) {
      // if the calculated position is impossible to play, then the choice becomes random until it is playable
      while(grid[0][position]!=0) {
          position = getRandomNumber(0,width);
      }
    }
    if(position==-1){
      // if the AI does not find a row of three tokens, it plays randomly in a uniform manner
      position = getRandomNumber(0,width-1);
        while(grid[0][position]!=0) {
        position = getRandomNumber(0,width-1);
        }
      }
    return position+1;
  }


}
