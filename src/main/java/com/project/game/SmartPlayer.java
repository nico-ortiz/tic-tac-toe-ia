package com.project.game;

import java.util.List;

import com.project.search.TicTacToeState;
import com.project.search.TicTacToeStateProblem;

public class SmartPlayer extends Player {

    public SmartPlayer(char letter) {
        super(letter);

    }

    public TicTacToeState getFirstGameMove(TicTacToeStateProblem stateProblem) {
        List<TicTacToeState> successors = stateProblem.getSuccessors(stateProblem.initialState());
        int min = 0, max = successors.size() - 1;
        int randomSquare = (int)Math.floor(Math.random()*(max-min+1)+min);
        return successors.get(randomSquare);
    }

    @Override
    public TicTacToeState getGameMove(TicTacToeState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameMove'");
    }

}
