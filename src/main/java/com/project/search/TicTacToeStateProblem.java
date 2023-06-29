package com.project.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.project.aditionalFunc.Tuple;

/**
 * Class which defines the basic elements necessary for characterize 
 * a tic-tac-toe problem as a search problem.
 */
public class TicTacToeStateProblem {
    
    private TicTacToeState initial;

    public TicTacToeStateProblem() {
        initial = new TicTacToeState(true);
    }

    /**
     * Returns the initial state (empty board) corresponding to the problem.
     * @return the initial state for tic-tac-toe problem.
     */
    public TicTacToeState initialState() {
        return initial;
    }

    public void getInitialState() {
        initial.printState();
    }

    /**
     * Returns the list of successor states for a given state.
     * @param s is the state for which its successors are being computed.
     * @return the list of successor states of s.
     */
    public List<TicTacToeState> getSuccessors(TicTacToeState s) {
        if (s == null) throw new IllegalArgumentException("illegal state");
        
        if (s.end()) {
            return new LinkedList<>();
        }

        List<TicTacToeState> result = new LinkedList<>();
        ArrayList<Tuple> list = s.freePositions();
        Tuple elem;

        if (s.isMax()) {
            for (int i = 0; i < list.size(); i++) {
                elem = list.get(i);
                char[][] boardClone = new char[s.getBoard().length][s.getBoard().length];
                cloneBoard(s.getBoard(), boardClone);
                result.add(new TicTacToeState(boardClone, elem.getFst(), elem.getSnd(), false, s));
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                elem = list.get(i);
                char[][] boardClone = new char[s.getBoard().length][s.getBoard().length];
                cloneBoard(s.getBoard(), boardClone);
                result.add(new TicTacToeState(boardClone, elem.getFst(), elem.getSnd(), true, s));
            }
        }
        return result;
    }

    /**
     * Method to clone a board on other board.
     * @param board original board.
     * @param boardClone to store the original board.
     */
    private void cloneBoard(char[][] board, char[][] boardClone) {
        for (int i = 0; i < boardClone.length; i++) {
            for (int j = 0; j < boardClone[i].length; j++) {
                boardClone[i][j] = board[i][j];
            }
        }
    }
}
