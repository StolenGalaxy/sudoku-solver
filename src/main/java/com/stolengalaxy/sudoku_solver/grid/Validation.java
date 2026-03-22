package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Validation {
    public static boolean isSetValid(ArrayList<Integer> values, ArrayList<Integer> validValues){
        boolean stillValid = true;

        for(int value:values){
            if(value == 0){
                break;
            }
            if(!validValues.contains(value)){
                stillValid = false;
                break;
            }
            if (ArrayTools.countIntegerOccurrences(values, value) > 1){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }

    public static boolean isGridValid(Grid grid){
        boolean stillValid = true;

        for(ArrayList<Integer> row:grid.rows()){
            if(!isSetValid(row, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Integer> column:grid.columns()){
            if(!isSetValid(column, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Integer> block:grid.blocks()){
            if(!isSetValid(block, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }

    public static boolean isGridComplete(Grid grid){
        return !grid.values().contains(0) && isGridValid(grid);
    }
}
