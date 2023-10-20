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

  private int[] random(int x, int y) {
    int orientI = (numCols-x+1) * (numRows-y+1);
    int orientII = (numCols-y+1) * (numRows-x+10);
    int numPositions = orientI + orientII;

    //randomizes whether the orientation is to the right or down
    int rand = (int)(Math.random()*numPositions) + 1;

    if(rand>orientI) {
      //Orient down
      return randPos(y,x);
    } else {
      //Orient right
      return randPos(x,y);
    }
  }

  private int[] randPos(int len, int width) {
      int seedX = (int)(Math.random()*(numCols-len+1));
      int seedY = (int)(Math.random()*(numRows-width+1));

      if(verify(seedX, seedY, len, width)) {
       int[] array1 = {seedX, seedY, seedX+len, seedY+width};  
        return array1;
      } else {
        int[] array2 =  {-1,-1,-1,-1};
        return array2;
      }
  }

  private boolean verify(int x, int y, int l, int w) {

    for(int i=x; i<x+l; ++i) {
      for(int j=y; j<y+w; ++j) {
        if(gameBoard[j][i]==1) {
          return false;
        }
      }
    }
  return true;
  }
  
  private void setValue(int x1, int y1, int x2, int y2) {
    for(int i=x1; i<x2; ++i) {
      for(int j=y1; j<y2; ++j) {
        gameBoard[j][i] = 1;
      }
    }
  }

 public void manualSet() {
 int valid = 0;
  for(int i=0; i<numShips; ++i) {
    while (valid!=1) {
      Scanner coordinateX = new Scanner(System.in);
      System.out.print("X Coordinate of ship: ");
      int x = coordinateX.nextInt();

      Scanner coordinateY = new Scanner(System.in);
      System.out.print("Y Coordinate of ship: ");
      int y = coordinateY.nextInt();

      Scanner direc = new Scanner(System.in);
      System.out.print("0 for down, 1 for right: ");
      int direction = direc.nextInt();
      valid = secondary(x, y, direction, i);

    } 
    printBoard();
    valid = 0;
  }
}  
}
