package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

import java.util.ArrayList;

public class DFS {
    public static Grid complete(Grid grid){
        ArrayList<Integer> emptyCellIndexes = grid.getEmptyCellIndexes();
        Grid modifiedGrid = grid;

        int cellToModifyIndex = CellTools.getFirstEmptyCellIndex(modifiedGrid, emptyCellIndexes);

        while(!Validation.isGridComplete(grid)){
            // increment the first dynamic cell before the first empty cell by 1
            Cell cell = grid.cells().get(cellToModifyIndex);

            if(cell.value < grid.size){
                modifiedGrid = modifiedGrid.setCell(cell, cell.value + 1);
            } else{
                modifiedGrid = modifiedGrid.setCell(cell, 0);
                cellToModifyIndex--;

                Cell previousCell = grid.cells().get(cellToModifyIndex);
                modifiedGrid = modifiedGrid.setCell(previousCell, previousCell.value + 1);
            }

            if(Validation.isGridValid(grid)){
                cellToModifyIndex++;
            }
        }
        return modifiedGrid;
    }
}
