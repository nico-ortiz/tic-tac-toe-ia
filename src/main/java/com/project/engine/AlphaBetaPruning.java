package com.project.engine;

import java.util.List;

import com.project.aditionalFunc.Tuple;
import com.project.search.TicTacToeState;
import com.project.search.TicTacToeStateProblem;

public class AlphaBetaPruning {
    
    private TicTacToeStateProblem p;
    private int maxDepth;

    public AlphaBetaPruning(TicTacToeStateProblem p, int maxDepth) {
        this.p = p;
        if (maxDepth == 0) throw new IllegalArgumentException("maximum depth must be more biggest than 1");
        this.maxDepth = maxDepth;
    }
    
    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        if (maxDepth <= 0) throw new IllegalArgumentException("maximum depth must be more biggest or equal than 1");
        this.maxDepth = maxDepth;
    }

    public TicTacToeStateProblem getProblem() {
        return p;
    }

    public void setProblem(TicTacToeStateProblem p) {
        if (p == null) throw new IllegalArgumentException("invalid value");
        this.p = p;
    }

    //Hacer el AlphaBetaPruning fuera de este metodo
    public int computeValue(TicTacToeState state) {
        if (state == null) 
            throw new IllegalArgumentException("invalid state");
        return alphaBetaPruning(state, this.maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE).getSnd();
    }

    public TicTacToeState computeSuccessor(TicTacToeState state) {
        if (state == null)
            throw new IllegalArgumentException("invalid state");
        return alphaBetaPruning(state, this.maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE).getFst();
    }

    /**
     * 
     * @param state
     * @return Return a tuple, first component is the most prommising succesor for state.
     * The second component is the value of the most prommisin succesor.
     */
    private Tuple<TicTacToeState> alphaBetaPruning(TicTacToeState state, int maxDepth, int alpha, int beta) {
        if (state.end() || maxDepth == 0) {
            return new Tuple<TicTacToeState>(state, state.value());
        } else {
            int minTemp = Integer.MAX_VALUE;
            int maxTemp = Integer.MIN_VALUE;
            TicTacToeState maxState = null, minState = null;

            maxDepth--;
            if (alpha < beta) {
                List<TicTacToeState> succs = p.getSuccessors(state);
                for (TicTacToeState succ: succs) {
                    if (state.isMax()) {
                        alpha = Math.max(alpha, alphaBetaPruning(succ, maxDepth, alpha, beta).getSnd());
                        if (maxTemp < alpha) {
                            maxState = succ;
                            maxTemp  = alpha;
                        }
                    } else {
                        beta = Math.min(beta, alphaBetaPruning(succ, maxDepth, alpha, beta).getSnd());
                        if (minTemp > beta) {
                            minState = succ;
                            minTemp  = beta;
                        }
                    }
                }
            }   

            if (state.isMax()) {
                return new Tuple<TicTacToeState>(maxState, alpha);
            } else {
                return new Tuple<TicTacToeState>(minState, beta);
            }
        }
    }

}
