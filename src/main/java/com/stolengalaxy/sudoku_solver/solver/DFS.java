package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.CellTools;
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
        Grid modifiedGrid = grid;

        while(!Validation.isGridComplete(grid)){
            while(!Validation.isGridValid(grid)){
                System.out.println("grid no longer valid-------\n");
                System.out.println(grid);
            }
            int firstEmptyIndex = CellTools.getFirstEmptyCellIndex(grid, dynamicCells);

            // increment the first dynamic cell before the first empty cell by 1

            DynamicCell cell;
            if(firstEmptyIndex == 0){
                cell = dynamicCells.get(firstEmptyIndex);
            } else{
                cell = dynamicCells.get(firstEmptyIndex - 1);
            }


            int currentValue = CellTools.getCellValue(grid, cell);
            modifiedGrid = modifiedGrid.setCell(cell, currentValue + 1);

            break;
        }

        return new Grid(new ArrayList<>());
    }
}
