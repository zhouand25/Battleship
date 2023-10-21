import java.util.Scanner;
/**
 * Tester.java
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 * This class is the main body of the program, displaying the menu for the user, printing the instructions of the different game modes, and housing the fundmental guessing mechanic of the Battleship Game.
 */

public class Tester {
//Pretty much the main body of the program, runs the menu and guessing mechanic
  
public static void main(String[] args) {
  String m = "\n\n\n\n\n\n";
  int mode;
    while (true) {
      //Error handling
    try {
      Scanner start = new Scanner(System.in);
      System.out.println("Welcome to Battleship!\n");
      System.out.println("Play a fast game if you are short on time or playing by yourself, otherwise play a regular game with a friend! \n");
      System.out.println("Type 0 for fast mode (-1 to also skip instructions) or Type 1 to pick regular mode");
      System.out.println("INSTRUCTIONS for each game mode will be shown after mode selection :)");

      mode = start.nextInt();
      break;
    } catch (Exception e) {
      System.out.println("Invalid Input: Please set mode equal to -1, 0, or, 1");
      System.out.println(m);  
    }
    }

  //Using constructors to initialize
    Board opBoard = new Board(mode);
    Board guessBoard = new Board(); 

  //FAST MODE
    if(mode<=0) {
      if(mode==0) {
        System.out.println(m);
        //Instructions for fast mode
        System.out.println("BATTLESHIP: FAST GAME INSTRUCTIONS: \n*Keep in mind, this is a variant of battleship and not exactly the original battleship game\n");  
        System.out.println("-Three ships composed of the dimensions (2 x 1), (3 x 1), (5 x 1) are randomly placed on an 8x8 board.\n");
        System.out.println("-These ships completely lie on the board as contiguous blocks, are not diagonally placed, and do not share a point with one another.\n");
         System.out.println("-The games ends in your victory if you destroy all of the generated ships by guessing their locations.");
        System.out.println("-Guess one point of the grid (determined by X and Y) and input the x-coordinate and y-coordinate of your guess, and whether part of the enemy ship lies on that coordinate will be returned to you as feedback.\n");
        System.out.println("-A 'hit' will be returned (with a 1 displayed on your grid) will show the presence of the enemy ship at that point, while a 'miss' will be returned (with a 2 on the grid) if the enemy ship is not present.\n");
        System.out.println("-A ship is destroyed when every one of the points it occupies on the grid are destroyed by “hits” / the user’s guesses.\n");
        System.out.println("Strive to minimize the number of your guesses, the lower the number, the greater the performance!\n");
      }

      System.out.println(m);
      guessBoard = new Board(8, 8);
      opBoard.generate();
      System.out.println("PLAYER 2: Guess the Ships\n\n");
    }
  
  //REGULAR MODE  
  if(mode==1) {
    System.out.println(m);
      //Instructions for Regular Game Mode
      System.out.println("BATTLESHIP: REGULAR GAME INSTRUCTIONS \n\n-This game is a two player game, with one player setting up the puzzle/game, while the other person guesses.\n");
    System.out.println("Player 1 (The creator):\n");
    System.out.println("-Player 1 configures the placement of 5 ships with the corresponding dimensions: (2 x 1), (3 x 1), (3 x 1), (4 x 1), and (5 x 1) on a 10x10 grid.\n");
    System.out.println("-A configuration of ships is only valid when no ships occupy the same coordinates, lie completely on the grid, and lie perpendicular to the edges of the board (i.e lie straight, not diagonally).\n");
    System.out.println("-Player 1 will be prompted to input the coordinate of a point on the 10x10 grid and then choose whether to face the ship down or to the right.\n");
    System.out.println("-(Facing a ship a certain direction will ensure that the longer side points there, i.e the direction “right” for a ship (5x1) means that the ship will span 4 additional units right (for a total of 5 units) while staying in the same row to preserve its dimensional width of 1 unit)\n");
    System.out.println("-Outwit and stall the guesser with hard-to-guess configurations and arrangements\n");
    System.out.println("Player 2 (The guesser):\n");
    System.out.println("-The games ends in your victory if you destroy all of the ships set by Player 1 through guessing their locations.\n");
    System.out.println("-Input the x-coordinate and y-coordinate of your guess for the location of an enemy ship, whether a part of the enemy ship lies on that coordinate will be returned to you as feedback. \n");
    System.out.println("-A 'hit' will be returned (with a 1 displayed on your grid) will show the presence of the enemy ship at that point, while a “miss” will be returned (with a 2 on the grid) if the enemy ship is not present.\n");
    System.out.println("-A ship is destroyed when every one of the points it occupies on the grid are destroyed by 'hits' / the user’s guesses.\n");
    System.out.println("-Strive to minimize the number of your guesses, the lower the number, the greater the performance!\n");
    System.out.println(m);
    //
      
      guessBoard = new Board(10, 10);
      opBoard.printBoard();
      System.out.println("PLAYER 1: Place your Ships\n");
      opBoard.manualSet();
      System.out.println(m);
      System.out.println(m);
      System.out.println(m);
      System.out.println("CONFIGURATION HAS BEEN SET");
      System.out.println("CONFIGURATION HAS BEEN SET");
      System.out.println("CONFIGURATION HAS BEEN SET\n");
      System.out.println("PLAYER 2: Please do not scroll up.");
      System.out.println(m);
      System.out.println("PLAYER 2: Guess");
    }

  
    
     //Start of Player guessing
    int numGuesses=0;
    int shipsRemaining = opBoard.numShips;
    boolean gameEnd=false;
  //Guessing loop until the game Ends
     while(!gameEnd) {
       System.out.println("-----------------------------------------------------");

       int x;
       int y;
       //Error handling
        try {
          Scanner xCoordinate = new Scanner(System.in);
          System.out.print("X Coordinate of Guess: ");
          x = xCoordinate.nextInt();

          Scanner yCoordinate = new Scanner(System.in);
          System.out.print("Y Coordinate of Guess: ");
          y = yCoordinate.nextInt();
        } catch (Exception e) {
          System.out.println("\nInvalid Input: Make sure X and Y are positive integers");
          continue;
        }      
       
        //More Error Handling
       try{
         //Checks if the guess is redundant
          if(guessBoard.getValue(x,y)==0) {
            ++numGuesses;
          
            if(opBoard.getValue(x,y)==1) {
              System.out.println("Hit!");
              guessBoard.setValue(x, y, 1);
              int rem = opBoard.numRemaining(guessBoard.getBoard());

            //If one less ship after the guess exists, the guess must have destroyed a ship
            if(rem == shipsRemaining-1) {
              System.out.println("You destroyed a ship!");
              shipsRemaining = rem;
            }
            //If all ships are gone than terminate game
            if(shipsRemaining==0) {
              System.out.println("----------------------------------");
              System.out.println("\nYou Won! In " +numGuesses+" guesses!" );
              gameEnd=true;
              continue;
            }
          } else {
              //Handles misses
            System.out.println("\nMiss!\n");
            guessBoard.setValue(x, y, 2);
          }
          guessBoard.printBoard();
          System.out.println("Number of guesses: "+numGuesses);
          System.out.println("Ships remaining: "+shipsRemaining);
        } else {
          System.out.println("Already Guessed there");
        }
       } catch (Exception e) {
         System.out.println("Invalid Input: Make sure your X and Y are within the grid and that all input is in the correct format.");
       }     
     }

}
}
