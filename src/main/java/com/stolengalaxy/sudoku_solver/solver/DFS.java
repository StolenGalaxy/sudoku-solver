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

        int cellToModifyIndex = CellTools.getFirstEmptyCellIndex(modifiedGrid, dynamicCells);
        while(!Validation.isGridComplete(grid)){
            // increment the first dynamic cell before the first empty cell by 1
            DynamicCell cell = dynamicCells.get(cellToModifyIndex);

            int currentValue = CellTools.getCellValue(grid, cell);
            if(currentValue < grid.size){
                modifiedGrid = modifiedGrid.setCell(cell, currentValue + 1);
            } else{
                cellToModifyIndex--;
                modifiedGrid = modifiedGrid.setCell(cell, 0);

                DynamicCell previousCell = dynamicCells.get(cellToModifyIndex);
                modifiedGrid = modifiedGrid.setCell(previousCell, CellTools.getCellValue(modifiedGrid, previousCell) + 1);
            }

            if(Validation.isGridValid(grid)){
                cellToModifyIndex++;
            }
        }

        return modifiedGrid;
    }
}
