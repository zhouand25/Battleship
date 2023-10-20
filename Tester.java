import java.util.Scanner;
public class Main {
public static void main(String[] args) {
     Board myBoard = new Board();
     Board guessBoard = new Board();
     myBoard.manualSet();

     int numGuesses=0;
     int shipsRemaining = myBoard.ships.length;

     boolean gameEnd=false;
     while(!gameEnd) {
        Scanner xCoordinate = new Scanner(System.in);
        System.out.print("X Coordinate of Guess: ");
        int x = xCoordinate.nextInt();

        Scanner yCoordinate = new Scanner(System.in);
        System.out.print("Y Coordinate of Guess: ");
        int y = yCoordinate.nextInt();
       
        //First verify that the guess is unique
        if(guessBoard.getValue(x,y)==0) {
          ++numGuesses;
          if(myBoard.getValue(x,y)==1) {
            System.out.println("Hit");
            guessBoard.setValue(x, y, 1);
            int rem = myBoard.numRemaining(guessBoard);

            if(rem == shipsRemaining-1) {
              System.out.println("You destroyed a ship");
              shipsRemaining = rem;
            }
            if(shipsRemaining==0) {
              gameEnd=true;
            }
          } else {
            System.out.println("Miss");
            guessBoard.setValue(x, y, 2);
          }
          guessBoard.printBoard();
          System.println("Number of guesses: "+numGuesses);
          System.println("Ships remaining: "+shipsRemaining);
        } else {
          System.out.println("Already Guessed there");
        }
          
     }

}
}

