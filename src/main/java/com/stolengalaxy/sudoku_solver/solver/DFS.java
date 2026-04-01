package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

import java.util.ArrayList;

public class DFS {
    private static ArrayList<Integer> getEmptyCellIndexes(Grid grid){
        ArrayList<Integer> indexes= new ArrayList<>();

        for(int i = 0; i < grid.cells().size(); i++){
            if(grid.cells().get(i).value == 0){
                indexes.add(i);
            }
        }

        return indexes;
    }

    public static Grid complete(Grid grid){
        ArrayList<Integer> emptyCellIndexes = getEmptyCellIndexes(grid);
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
