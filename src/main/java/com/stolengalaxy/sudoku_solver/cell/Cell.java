package com.stolengalaxy.sudoku_solver.cell;

public class Cell {
    public int value;

    public int row;
    public int block;
    public int column;

    public Cell(int value){
        this.value = value;
    }

    public Cell(int value, int row, int column, int block){
        this.value = value;
        this.row = row;
        this.column = column;
        this.block = block;
    }
}
