package com.project.aditionalFunc;

import com.project.search.TicTacToeState;

public class Tuple<S extends TicTacToeState> {
    
    private S fst;
    private int snd;

    public Tuple(S fst, int snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public S getFst() {
        return fst;
    }

    public int getSnd() {
        return snd;
    }

    public void setFst(S fst) {
        this.fst = fst;
    }

    public void setSnd(int snd) {
        this.snd = snd;
    }

    public String toString() {
        return "("+fst+","+snd+")";
    }
}
