import java.util.Scanner;
/**
 * Board.java
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 * The Board class contains not only the game board, which is the data structure storing the ship configuration and the guessing map in the game, but also other crucial methods revolving around the game board such as generate(), the configuration randomizer for 'Fast Game', and manualSet()-
 * which allows for the user placement of ships on the board in the 'Regular Game'. 
 */

//Board Class - contains not only the gameBoard but also crucial methods needed in the program such as (Generate) and (Manual Set)
public class Board {

    
  int numRows;
  int numCols;
  final int numCoords = 4; 
  int[][] ships;
  int numShips;
  //Stores coordinates of ships
  int[][] shipCoords;
  //The 2d Matrix which is basically the Game Board
  private int[][] gameBoard; 

  //Constructor of Class (Allows for adjust of dimensions based on the mode)
  public Board(int m) {
    if(m==1) {
      numRows = 10;
      numCols = 10;
      //X skewed (aka. length by width)
      ships = new int[][] {{2,1}, {3,1}, {3,1}, {4,1}, {5,1}}; numShips = 5;
    }
    if(m==0 || m==-1) {
      numRows = 8;
      numCols = 8;
      //X skewed (aka. length by width)
      ships = new int[][] {{2,1}, {3,1}, {5,1}}; numShips = 3;
    }
    shipCoords = new int[numShips][numCoords];
    gameBoard = new int[numRows][numCols];
  }
  //Overload constructor for the guess object (The screen allowing the Player to guess and display hits and misses) 
  public Board(int a, int b) {
    gameBoard = new int[a][b];
    numRows = a;
    numCols = b;
  }
  //Default Constructor of Class
  public Board() {
    
  }


  //Prints the 2d game board matrix (with indexes to help with coordinate target)
  public void printBoard() {
    String output="++ ";
    for(int i=0; i<numCols; ++i) {
      output+=i+" ";
    }
    output+="\n"+"----------------------------"+"\n";
  
      for(int r=0; r<numRows; ++r) {
        output+=r+"| ";
          for(int c=0; c<numCols; ++c) {
              output+=gameBoard[r][c]+" ";
          }
          output+="\n";
      }
    System.out.println(output);
  }

  //setter method (2d game board)
  public void setValue(int col, int row, int value) {
    gameBoard[row][col] = value;
  }
  //getter method (2d game board)
  public int getValue(int col, int row) {
    return gameBoard[row][col];
  }
  //returns the entire 2d game board matrix
  public int[][] getBoard() {
    return gameBoard;
  }

  //Random configuration of ships on grid during FAST MODE
  public void generate() {
    //Loops through each ship and individually randomizes them one at a time
      for(int move=0; move<numShips; ++move) {
        int[] check = new int[numCoords];
        boolean valid = false;
        //While Loop continues until a valid ship position is found
        while(valid==false) {
          //Check is the array that contains the eventual positions of the randomized ships
          check = random(ships[move][0], ships[move][1]);
          if(check[0]!=-1) {

            for(int i=0; i<numCoords; ++i) {
              shipCoords[move][i]=check[i];
            }
            //Fills the entire block of the game board so no overlap occurs
            fill(check[0], check[1], check[2], check[3]);
            valid = true;
          }
        }

      }
  }

  //Randomly determines the orientation of the ship (longest side downward, or rightward)
  private int[] random(int x, int y) {
    int orientI = (numCols-x+1) * (numRows-y+1);
    int orientII = (numCols-y+1) * (numRows-x+1);
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

  //Actually randomizes the coordinates creates the 'seed' x and y coordinates 
  private int[] randPos(int len, int width) {
      int seedX = (int)(Math.random()*(numCols-len+1));
      int seedY = (int)(Math.random()*(numRows-width+1));

      if(verify(seedX, seedY, len, width)) {
        //This branch shows that the randomization is successful
       int[] array1 = {seedX, seedY, seedX+len, seedY+width};  
        return array1;
      } else {
        //This branch returns that the randomization has failed/has a collision
        int[] array2 =  {-1,-1,-1,-1};
        return array2;
      }
  }

  //Verifies if a configuration of seedX, seedY with the proper dimensions does not have any overlap with any other ships on the game board
  //(Keep in mind: The randomizaiton forces the ship to stay on the board, so that type of error can be neglected)
  private boolean verify(int x, int y, int l, int w) {
  //Returns true if a ship in these coordinates and dimensions can land validly in the grid
    for(int i=x; i<x+l; ++i) {
      for(int j=y; j<y+w; ++j) {
        if(gameBoard[j][i]==1) {
          return false;
        }
      }
    }
  return true;
  }
  //Fills an entire rectangular section of the game board full of 1s 
  private void fill(int x1, int y1, int x2, int y2) {
    for(int i=x1; i<x2; ++i) {
      for(int j=y1; j<y2; ++j) {
        gameBoard[j][i] = 1;
      }
    }
  }

 //User placement of ships (Player 1)
 public void manualSet() {
 int valid = 0;
  //Loops through ships and allows user to place ships one at a time
  for(int i=0; i<numShips; ++i) {
    while (valid!=1) {
      //Try-Catch to eliminate errors in input
      try {
		//Placement input
        System.out.println("Placement of Ship: "+ships[i][0]+" x "+ships[i][1]);
        Scanner coordinateX = new Scanner(System.in);
        System.out.print("Place X-Coordinate of Ship at: ");
        int x = coordinateX.nextInt();

        Scanner coordinateY = new Scanner(System.in);
        System.out.print("Place Y-Coordinate of ship at: ");
        int y = coordinateY.nextInt();

        Scanner direc = new Scanner(System.in);
        System.out.print("0 for down, 1 for right: ");
        int direction = direc.nextInt();
        valid = secondary(x, y, direction, i);
      } catch (Exception e) {
        System.out.println("\nInvalid Input: make sure X and Y are positive integers and within the grid and that all input is in the correct format.");
      }
      System.out.println("-----------------------------------------------------");
    } 
    printBoard();
    valid = 0;
  }
}  
//Follow up method to manualSet
private int secondary(int x, int y, int direction, int row) {
  boolean result;

  int finalX;
  int finalY;

  //Changing finalX and finalY due to ship orientation
  if(direction==1) {
    finalX = x+ships[row][0];
    finalY = y+ships[row][1];
  } else {
      if(direction==0) {
        finalX = x+ships[row][1];
        finalY = y+ships[row][0];
      } else {
        System.out.println("Invalid Input");
        return 0;
      }
  }
  
  //finalX and finalY are actually never reached (More are upperBounds)
    if((finalX > numCols) || (finalY > numRows)) {
     //Error handling in case placement is out of bounds
      System.out.println("Out of Bounds: Make Sure that given the starting X and Y and the direction, the ship does not go off the screen");
      return 0;
    }

    //Use of the verification method
    result = verify(x, y, finalX-x, finalY-y);

    if(result) {
      //Inputting the ship coordinates into the matrix
       shipCoords[row][0] = x;
       shipCoords[row][1] = y;
       shipCoords[row][2] = finalX;
       shipCoords[row][3] = finalY;

       fill(x, y, finalX, finalY);
       return 1;
    }
  //If verification fails
  System.out.println("Collision with other ship: Remember ships can not overlap");
  return 0;
}

//Built in method showing number of remaining ships (not destroyed by player)
public int numRemaining(int[][] guess) {
  int numDestroyed=0;
  for(int i=0; i<numShips; ++i) {
    
    boolean clean = true;
    //iterate to see if a ship given its coordinates completely matches with the guesses of the player
    for(int j=shipCoords[i][0]; j<shipCoords[i][2]; ++j) {
      for(int k=shipCoords[i][1]; k<shipCoords[i][3]; ++k) {
        if(guess[k][j]==0) {
          clean = false;
        }
      }
    }
    //if guess matrix matches with config matrix
    if(clean) {
      ++numDestroyed;
    }
    
  }
  return numShips-numDestroyed;
}

}
