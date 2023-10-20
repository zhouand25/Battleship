import java.util.Scanner;
public class Board {

  int numRows = 10;
  int numCols = 10;
  int numCoords = 4;
  //X skewed (aka. length by width)
  int[][] ships = {{2,1}, {3,1}, {3,1}, {4,1}, {5,1}};
  final int numShips = ships.length;
  int[][] shipCoords = new int[numShips][numCoords];

  private int[][] gameBoard = new int[numRows][numCols];

  public void printBoard() {
      for(int r=0; r<numRows; ++r) {
          for(int c=0; c<numCols; ++c) {
              System.out.print(gameBoard[r][c]+" ");
          }
          System.out.println("");
      }
  }

  public void generate() {
      for(int move=0; move<numShips; ++move) {
        int[] check = new int[numCoords];
        boolean valid = false;

        while(valid==false) {
          check = random(ships[move][0], ships[move][1]);
          if(check[0]!=-1) {

            for(int i=0; i<numCoords; ++i) {
              shipCoords[move][i]=check[i];
            }

            setValue(check[0], check[1], check[2], check[3]);
            valid = true;
          }
        }

      }
  }
