import java.util.Scanner;
public class Tester {
public static void main(String[] args) {

    Scanner start = new Scanner(System.in);
     System.out.print("Type 0 for fast mode (-1 to also skip instructions) or Type 1 to pick regular mode");
     int mode = start.nextInt();

    Board opBoard = new Board(mode);
    Board guessBoard = new Board();    
  
    if(mode<=0) {
      if(mode==0) {
        //Instructions for fast mode
        System.out.println("");
      }
      opBoard.generate();
      opBoard.printBoard();
    }
    if(mode==1) {
      System.out.println("Board Set-Up");
      System.out.println(" ");
      opBoard.printBoard();
      opBoard.manualSet();
    }

  
    
     //Start of Player guessing
    int numGuesses=0;
    int shipsRemaining = opBoard.numShips;
    boolean gameEnd=false;
     while(!gameEnd) {
       System.out.println("-----------------------------------------------------");
        Scanner xCoordinate = new Scanner(System.in);
        System.out.print("X Coordinate of Guess: ");
        int x = xCoordinate.nextInt();

        Scanner yCoordinate = new Scanner(System.in);
        System.out.print("Y Coordinate of Guess: ");
        int y = yCoordinate.nextInt();
       
        //First verify that the guess is unique
        if(guessBoard.getValue(x,y)==0) {
          ++numGuesses;
          if(opBoard.getValue(x,y)==1) {
            System.out.println("Hit");
            guessBoard.setValue(x, y, 1);
            int rem = opBoard.numRemaining(guessBoard.getBoard());

            if(rem == shipsRemaining-1) {
              System.out.println("You destroyed a ship");
              shipsRemaining = rem;
            }
            if(shipsRemaining==0) {
              System.out.println("----------------------------------");
              System.out.println("You Won! In " +numGuesses+" guesses!" );
              gameEnd=true;
            }
          } else {
            System.out.println("Miss");
            guessBoard.setValue(x, y, 2);
          }
          guessBoard.printBoard();
          System.out.println("Number of guesses: "+numGuesses);
          System.out.println("Ships remaining: "+shipsRemaining);
        } else {
          System.out.println("Already Guessed there");
        }
          
     }

}
}
