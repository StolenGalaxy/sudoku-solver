package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Validation {
    public static boolean isSetValid(ArrayList<Integer> values){
        boolean stillValid = true;
        for(int i=1; i <=9; i++){
            if (ArrayTools.countIntegerOccurrences(values, i) > 1){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }
}
