package com.stolengalaxy.sudoku_solver.cell;

public class Cell {
    public int row;
    public int value;

    public int block;
    public int column;

    public Cell(int row, int value){
        this.row = row;
        this.value = value;
    }
}
