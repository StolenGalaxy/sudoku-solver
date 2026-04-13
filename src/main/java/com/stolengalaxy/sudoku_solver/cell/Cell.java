package com.stolengalaxy.sudoku_solver.cell;

import java.util.ArrayList;

public class Cell {
    public int value;

    public int row;
    public int block;
    public int column;

    public ArrayList<Integer> cellCandidates;

    public Cell(int value){
        this.value = value;
    }

    public Cell(int value, int row, int column, int block){
        this.value = value;
        this.row = row;
        this.column = column;
        this.block = block;
    }

    @Override
    public String toString(){
        String formattable = "(Row: %d, Column: %d, Block: %d, Value: %d, Candidates: " + cellCandidates + ")";
        return String.format(formattable, row, column, column, column);
    }
}
