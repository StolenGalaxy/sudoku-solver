package com.stolengalaxy.sudoku_solver.cell;

import com.stolengalaxy.sudoku_solver.grid.Grid;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CellTools {
    public static int getCellValue(Grid grid, DynamicCell cell){
        return grid.rows().get(cell.row).get(cell.column);
    }

    public static int getFirstEmptyCellIndex(Grid grid, ArrayList<DynamicCell> cellArray){
        for(int i=0; i<cellArray.size(); i++){
            if(getCellValue(grid, cellArray.get(i)) == 0){
                return i;
            }
        }
        return -1;
    }

    private static ArrayList<Integer> getCellRow(Grid grid, DynamicCell cell){
        return grid.rows().get(cell.row);
    }
    private static ArrayList<Integer> getCellColumn(Grid grid, DynamicCell cell){
        return grid.columns().get(cell.column);
    }
    private static ArrayList<Integer> getCellBlock(Grid grid, DynamicCell cell){

    }
}
