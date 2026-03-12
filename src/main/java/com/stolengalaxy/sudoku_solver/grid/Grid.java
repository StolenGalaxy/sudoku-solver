package com.stolengalaxy.sudoku_solver.grid;

import java.util.ArrayList;

public class Grid {
    ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

    public Grid(ArrayList<ArrayList<Integer>> rows){
        this.rows = rows;
    }

    public ArrayList<ArrayList<Integer>> columns(){
        ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

        for (int columnIndex = 1; columnIndex <= 9; columnIndex++){
            ArrayList<Integer> column = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= 9; rowIndex++){
                int value = rows.get(rowIndex).get(columnIndex);
                column.add(value);
            }
            columns.add(column);
        }

        return columns;
    }
}
