package com.project.game;

import java.util.List;

import com.project.engine.AlphaBetaPruning;
import com.project.search.TicTacToeState;
import com.project.search.TicTacToeStateProblem;

/*
 * This class represents a computer player. 
 * Game moves are made through AlphaBeta pruning algorithm. 
 */
public class SmartPlayer extends Player {

    private AlphaBetaPruning alphaBetaPruning;

    public SmartPlayer(char letter, TicTacToeStateProblem sp) {
        super(letter);
        alphaBetaPruning = new AlphaBetaPruning(sp, 5);
    }

    public TicTacToeState getFirstGameMove(TicTacToeStateProblem stateProblem) {
        List<TicTacToeState> successors = stateProblem.getSuccessors(stateProblem.initialState());
        int min = 0, max = successors.size() - 1;
        int randomSquare = (int)Math.floor(Math.random()*(max-min+1)+min);
        return successors.get(randomSquare);
    }

    @Override
    public TicTacToeState getGameMove(TicTacToeState state) {
        return alphaBetaPruning.computeSuccessor(state);
    }

}
