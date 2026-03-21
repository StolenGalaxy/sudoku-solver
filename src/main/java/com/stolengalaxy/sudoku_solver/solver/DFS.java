package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.DynamicCell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

import java.util.ArrayList;

public class DFS {
    private static ArrayList<DynamicCell> getEmptyCells(Grid grid){
        ArrayList<DynamicCell> dynamicCells = new ArrayList<>();
        ArrayList<ArrayList<Integer>> rows = grid.rows();
        for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++){
            ArrayList<Integer> row = rows.get(rowIndex);
            for(int columnIndex = 0; columnIndex < rows.size(); columnIndex++){
                int value = row.get(columnIndex);
                if(value == 0){
                    dynamicCells.add(new DynamicCell(rowIndex, columnIndex));
                }
            }
        }
        return dynamicCells;
    }

    public static Grid complete(Grid grid){
        ArrayList<DynamicCell> dynamicCells = getEmptyCells(grid);

        return new Grid(new ArrayList<>());
    }
}
