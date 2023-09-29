package com.project.game;

import java.util.Scanner;

import com.project.search.TicTacToeState;

/*
 * This class represents a human player. 
 */
public class HumanPlayer extends Player{
    
    public HumanPlayer(char letter) {
        super(letter);
    }

    @Override
    public TicTacToeState getGameMove(TicTacToeState state) {
        boolean validSquare = false;
        Scanner scanner = new Scanner(System.in);
        TicTacToeState playerState = null;

        while (!validSquare) {

            System.out.println(getLetter()+"'s turn.\nInput move (0-2 to row)");
            int row = scanner.nextInt();
            System.out.println("Input move (0-2 to column)");
            int column = scanner.nextInt();
            
            try {
                playerState = new TicTacToeState(state.getBoard(), row, column, state.isMax() ? false : true, state);
                validSquare = true;
            } catch (Exception e) {
                System.out.println("Invalid square. Try again.");
            }
        }
        return playerState;
    }
    
}
