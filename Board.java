public class Board {
    
    int numRows = 10;
    int numCols = 10;
    int[] ships = new int[5]; //{2,3,3,4,5}
    final int numShips = ships.length;
    //

    private int[][] gameBoard = new int[numRows][numCols];

    public void printBoard() {
        for(int r=0; r<numRows; ++r) {
            for(int c=0; c<numCols; ++c) {
                System.out.print(gameBoard[r][c]+" ");
            }
            System.out.println("");
        }
    }

    public void setValue(int r, int c, int value) {
        gameBoard[r][c] = value;
    }

    public int getValue(int r, int c) {
        return gameBoard[r][c];
    }

    public void generate() {
        for(int move=1; move<numShips+1; ++move) {
            ships[move-1] = 

        }
    }

    private int[] iterate(int size, int direc) {
        if(direc==1 || direc == 2) {

            
        }
    }

    /* random stuff start
    randPos(m1, m2);
    randPos(m2, m1);
    function randPos(int x, int y) {
        int seedX = Math.floor(Math.random()*(cols-x+1));
        int seedY = Math.floor(Math.random()*(rows-y+1));board[i][j]
        
        for(int i=seedX; i<seedX+x; ++i) {board[i][j]
            for(int j=seedY; j<seedY+y; ++j) {board[i][j]
                board[i][j]
            }

        }
    }
    */
}
