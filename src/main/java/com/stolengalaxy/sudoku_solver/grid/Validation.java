package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Validation {
    public static boolean isSetValid(ArrayList<Integer> values){
        boolean stillValid = true;

        if(ArrayTools.countIntegerOccurrences(values, 0) > 0){
            stillValid = false;
        } else{
            for(int i=1; i <=9; i++){
                if (ArrayTools.countIntegerOccurrences(values, i) > 1){
                    stillValid = false;
                    break;
                }
            }
        }


        return stillValid;
    }

    public static boolean isGridValid(Grid grid){
        boolean stillValid = true;

        for(ArrayList<Integer> row:grid.rows()){
            if(!isSetValid(row)){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Integer> column:grid.columns()){
            if(!isSetValid(column)){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Integer> block:grid.blocks()){
            if(!isSetValid(block)){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }
}
