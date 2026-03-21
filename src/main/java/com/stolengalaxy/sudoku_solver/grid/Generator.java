package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Generator {
    public static Grid randomGrid(int size){
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            rows.add(ArrayTools.unorderedIntegerArray(size));
        }
        return new Grid(rows);
    }

    public static Grid emptyGrid(int size){
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

        ArrayList<Integer> emptyRow = new ArrayList<>();

        for(int i = 0; i < size; i++){
            emptyRow.add(0);
        }
        for(int i = 0; i < size; i++){
            rows.add(emptyRow);
        }

        return new Grid(rows);
    }
}
