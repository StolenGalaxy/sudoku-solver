package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Validation {
    public static boolean isSetValid(ArrayList<Cell> cells, ArrayList<Integer> validValues){
        boolean stillValid = true;

        for(Cell cell:cells){
            if(cell.value == 0){
                break;
            }
            if(!validValues.contains(cell.value)){
                stillValid = false;
                break;
            }
            ArrayList<Integer> values = CellTools.toIntegerRow(cells);
            if (ArrayTools.countIntegerOccurrences(values, cell.value) > 1){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }

    public static boolean isGridValid(Grid grid){
        boolean stillValid = true;

        for(ArrayList<Cell> row:grid.rows()){
            if(!isSetValid(row, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Cell> column:grid.columns()){
            if(!isSetValid(column, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        for(ArrayList<Cell> block:grid.blocks()){
            if(!isSetValid(block, grid.validValues())){
                stillValid = false;
                break;
            }
        }
        return stillValid;
    }

    public static boolean isGridComplete(Grid grid){
        ArrayList<Integer> values = CellTools.toIntegerRow(grid.cells());
        return !values.contains(0) && isGridValid(grid);
    }
}
