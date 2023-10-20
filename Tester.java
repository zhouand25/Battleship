import java.util.Scanner;
public class Main {
public static void main(String[] args) {
     Board myBoard = new Board();
     Board guessBoard = new Board();
     myBoard.manualSet();

     int numGuesses=0;
     int shipsRemaining;

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
         if(myBoard.getValue(x,y)==1) {
           System.out.println("Hit");
           guessBoard.setValue(x, y, 1);
         }
        }
          
     }

}
}
