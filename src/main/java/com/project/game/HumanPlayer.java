package com.project.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TicTacToeState playerState = null;
        int row = 0, column = 0;

        while (!validSquare) {

            try {
                System.out.println(getLetter()+"'s turn.\nInput move (0-2 to row)");
                row = Integer.parseInt(reader.readLine());
                System.out.println("Input move (0-2 to column)");
                column = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
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
