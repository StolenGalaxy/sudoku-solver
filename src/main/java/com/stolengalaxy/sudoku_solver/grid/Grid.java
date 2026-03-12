package com.stolengalaxy.sudoku_solver.grid;

import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
    int size;

    public Grid(ArrayList<ArrayList<Integer>> rows){
        this.rows = rows;
        this.size = rows.size();
    }

    public ArrayList<ArrayList<Integer>> columns(){
        ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < size; columnIndex++){
            ArrayList<Integer> column = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < size; rowIndex++){
                int value = rows.get(rowIndex).get(columnIndex);
                column.add(value);
            }
            columns.add(column);
        }

        return columns;
    }

    public ArrayList<ArrayList<Integer>> rows(){
        return rows;
    }
}
