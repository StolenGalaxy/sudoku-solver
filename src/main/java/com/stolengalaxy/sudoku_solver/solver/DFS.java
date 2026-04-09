package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

import java.util.ArrayList;

public class DFS {
    public static Grid complete(Grid grid){
        int cellToModifyIndex = 0;
        Grid modifiedGrid = grid;
        ArrayList<Cell> emptyCells = grid.emptyCells();

        while(!Validation.isGridFull(modifiedGrid)){
            // increment the first dynamic cell before the first empty cell by 1
            Cell cell = emptyCells.get(cellToModifyIndex);

            if(cell.value < grid.size){
                modifiedGrid = modifiedGrid.setCell(cell, cell.value + 1);
            } else{
                modifiedGrid = modifiedGrid.setCell(cell, 0);
                cellToModifyIndex--;
                Cell previousCell = emptyCells.get(cellToModifyIndex);

                modifiedGrid = modifiedGrid.setCell(previousCell, previousCell.value + 1);
            }

            if(Validation.isGridValid(modifiedGrid)){
                cellToModifyIndex++;
            }

            // update emptyCell values
            for(int i = 0; i < emptyCells.size(); i++){
                Cell emptyCell = emptyCells.get(i);
                emptyCells.get(i).value = modifiedGrid.rows().get(emptyCell.row).get(emptyCell.column).value;
            }
        }
        return modifiedGrid;
    }
}
