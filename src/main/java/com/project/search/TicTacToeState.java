package com.project.search;

import java.util.ArrayList;

import com.project.aditionalFunc.TupleInt;

/**
 * Class which defines the basic requirements on tic tac toe state. 
 * Search strategies can be used this class
 */
public class TicTacToeState {
    
    static char xs = 'X';

    static char os = 'O';

    final int N = 3;

    //Attribute which defines the state parent of this state
    private TicTacToeState parent;

    //State of board
    private char[][] board = new char[N][N];

    //Attribute which indicates if this state is max or not
    private boolean isMax;

    public TicTacToeState() {
    }
    
    public TicTacToeState(boolean isMax) {
        boardInitialization();
        this.isMax = isMax;
    }

    public TicTacToeState(TicTacToeState parent, char[][] board, boolean isMax) {
        this.parent = parent;
        this.board = board;
        this.isMax = isMax;
    }

    public TicTacToeState(char[][] board, int row, int column, boolean max, TicTacToeState parent) {
        this.isMax = max;
        this.parent = parent;

        if (!repOk(board, row, column)) {  
            throw new IllegalArgumentException("invalid values");
        }

        if (isAValidMovement(board, row, column)) {
            this.board = board;
            if (this.parent.isMax()) {
                this.board[row][column] = xs;
            } else {
                this.board[row][column] = os;
            }
        } else {
            throw new IllegalArgumentException("invalid position");
        }
    }

    /**
     * Returns the parent of the current state.
     * @return the parent of the current state or null if does not exists parent.
     */
    public TicTacToeState getParent() {
        return parent;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isMax() {
        return isMax;
    }

    /**
     * Verifies whether this board and position are valids.
     * @param board .
     * @param a is the row position.
     * @param b is the column position.
     * @return true iff the position (a,b) is valid to do a movement, 
     * otherwise it returns false
     */
    public boolean repOk(char[][] board, int a, int b) {
        return (a >= 0 && a <= board.length-1) && (b >= 0 && b <= board.length-1);
    }
    
    /** 
     * Method to initialize a board of an initial state
    */
    private void boardInitialization() {
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board.length; column++) {
                this.board[row][column] = '_';
            }
        }
    }

    /**
     * Print state of board
     */
    public void printState() {
        for (int row = 0; row < board.length; row++) {
            System.out.print("[ ");
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column]+" ");
            }
            System.out.println("]");
        }
    }
    
    /**
     * Indicates whether this state is a successful state.
     * @return true iff this is a winner board, could be to os or xs
     */
    public boolean isSuccess() {
        int winner = isAWinnerBoard(board);
        return winner == 1 || winner == -1;
    }

    /**
     * Indicates whether this state is a final state.
     * @return true iff this is an end state, a state with no successors(a leaf).
     */
    public boolean end() {
        return isSuccess() || isTie();
    }

    /**
     * Computes the value of this state. If this state is a leaf,
     * then this method return an exact value for this state.
     * Otherwise return an approximate value.
     * @return a value, representing the value of the state.
     */
    public int value() {
        if (end()) {
            return isAWinnerBoard(board);
        } else {
            return approximateValue();
        }
    }
    
    /**
     * Indicates if this state contains a board with tie
     * @return true if is a board with tie otherwise false  
     */
    public boolean isTie() {
        return !areThereFreeMovements(board) && !isSuccess();
    }

    /**
     * This method returns a integer value, this value is a approximate value
     * that use to calculate the minimax decisions for those no final states.
     * @return 
     */
    private int approximateValue() {
        if (isMax()) {
            if (closeToWinning(xs)) {
                return 1;
            }
        } else {
            if (closeToWinning(os)) {
                return -1;
            }
        }
        return 0;
    } 

    /**
     * 
     */
    private boolean closeToWinning(char letter) {

        boolean isEmpty = true;

        //Control for empty board
        for (int row = 0; row < board.length && isEmpty; row++) {
            for(int column = 0; column < board[row].length && isEmpty; column++) {
                if (board[row][column] != '_') {
                    isEmpty = false;
                } 
            }
        }

        if (isEmpty) return false;

        //Rows
        for (int row = 0; row < board.length; row++) {
            if ((board[row][0] == board[row][1]) && board[row][2] == '_') {
                if (board[row][0] == letter) {
                    return true;
                }
            } else if ((board[row][1] == board[row][2]) && board[row][0] == '_') {
                if (board[row][1] == letter) {
                    return true;
                }
            } else if ((board[row][0] == board[row][2]) && board[row][1] == '_') {
                if (board[row][0] == letter) {
                    return true;
                }
            }
        }

        //Columns
        for (int column = 0; column < board.length; column++) {
            if ((board[0][column] == board[1][column]) && board[2][column] == '_') {
                if (board[0][column] == letter) {
                    return true;
                }
            } else if ((board[1][column] == board[2][column]) && board[0][column] == '_') {
                if (board[1][column] == letter) {
                    return true;
                }
            } else if ((board[0][column] == board[2][column]) && board[1][column] == '_') {
                if (board[0][column] == letter) {
                    return true;
                }
            }
        }

        //Diagonals
        //Left down corner to right top corner
        if ((board[2][0] == board[1][1]) && board[0][2] == '_' ) {
            if (board[2][0] == letter) {
                return true;
            }
        } else if ((board[0][2] == board[1][1]) && board[2][0] == '_') {
            if (board[0][2] == letter) {
                return true;
            }
        } else if ((board[2][0] == board[0][2]) && board[1][1] == '_') {
            if (board[2][0] == letter) {
                return true;
            }
        }

        //Left top corner to right down corner
        if ((board[0][0] == board[1][1]) && board[2][2] == '_') {
            if (board[0][0] == letter) {
                return true;
            }
        } else if ((board[1][1] == board[2][2]) && board[0][0] == '_') {
            if (board[1][1] == letter) {
                return true;
            }
        } else if ((board[0][0] == board[2][2]) && board[1][1] == '_') {
            if (board[0][0] == letter) {
                return true;
            }
        }

        return false;
    }


    private int isAWinnerBoard(char[][] board){
        
        //Control for rows
        for (int row = 0; row < board.length; row++) {
            if ((board[row][0] == board[row][1] && board[row][1] == board[row][2]) && board[row][0] != '_'){
                if (board[row][0] == xs) {
                    return 1;
                }else {
                    return -1;
                }
            }
        }

        //Control for columns
        for (int column = 0; column < board.length; column++) {
            if ((board[0][column] == board[1][column] && board[1][column] == board[2][column]) && board[0][column] != '_' ){
                if (board[0][column] == xs) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        //Control for diagonals
        //Left top corner to right down corner
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != '_') {
            if (board[0][0] == xs) {
                return 1;
            } else {
                return -1;
            }
        }

        //Left down corner to right top corner
        if ((board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[2][0] != '_'){
            if (board[2][0] == xs) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }


    private boolean areThereFreeMovements(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAValidMovement(char[][] board, int a, int b) {
        return board[a][b] == '_';
    }

    public ArrayList<TupleInt> freePositions() { 
        ArrayList<TupleInt> list = new ArrayList<>();
        for (int row = 0; row < board.length; row++) { 
            for (int column = 0; column < board.length; column++) {
                if (isAValidMovement(board, row, column)) {
                    list.add(new TupleInt(row, column));
                }
            }
        }
        return list;
    }
}
