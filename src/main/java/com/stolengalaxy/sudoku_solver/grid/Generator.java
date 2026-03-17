package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Generator {
    private static int size = 9;
    public static Grid randomGrid(){
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            rows.add(ArrayTools.unorderedIntegerArray(size));
        }
        return new Grid(rows);
    }
}
