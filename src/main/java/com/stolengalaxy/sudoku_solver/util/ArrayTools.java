package com.stolengalaxy.sudoku_solver.util;

import java.util.ArrayList;

public class ArrayTools {
    public static int countIntegerOccurrences(ArrayList<Integer> array, int targetInteger){
        int count = 0;
        for(int value:array){
            if(value == targetInteger){
                count++;
            }
        }
        return count;
    }
}
