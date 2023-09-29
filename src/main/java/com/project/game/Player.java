package com.project.game;

import com.project.search.TicTacToeState;

public abstract class Player {
    
    private char letter;

    public Player(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public abstract TicTacToeState getGameMove(TicTacToeState state);
}
