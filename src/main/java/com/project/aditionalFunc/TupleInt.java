package com.project.aditionalFunc;

public class TupleInt {

    private int fst;
    private int snd;

    public TupleInt(int fst, int snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public int getFst() {
        return fst;
    }

    public int getSnd() {
        return snd;
    }

    public void setFst(int fst) {
        this.fst = fst;
    }

    public void setSnd(int snd) {
        this.snd = snd;
    }

    public String toString() {
        return "("+fst+","+snd+")";
    }
}
