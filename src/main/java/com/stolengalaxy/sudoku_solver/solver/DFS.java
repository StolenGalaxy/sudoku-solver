package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

import java.util.ArrayList;

public class DFS {
    private static ArrayList<Cell> getEmptyCells(Grid grid){
        ArrayList<Cell> dynamicCells = new ArrayList<>();
        ArrayList<ArrayList<Cell>> rows = grid.rows();
        for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++){
            ArrayList<Cell> row = rows.get(rowIndex);
            for(int columnIndex = 0; columnIndex < rows.size(); columnIndex++){
                Cell cell = row.get(columnIndex);
                if(cell.value == 0){
                    dynamicCells.add(cell);
                }
            }
        }
        return dynamicCells;
    }

    public static Grid complete(Grid grid){
        ArrayList<Cell> dynamicCells = getEmptyCells(grid);
        Grid modifiedGrid = grid;

        int cellToModifyIndex = CellTools.getFirstEmptyCellIndex(dynamicCells);
        int count = 0;
        while(!Validation.isGridComplete(grid)){
            // increment the first dynamic cell before the first empty cell by 1
            Cell cell = dynamicCells.get(cellToModifyIndex);


            if(cell.value < grid.size){
                modifiedGrid = modifiedGrid.setCell(cell, cell.value + 1);

                count++;
            } else{
                cellToModifyIndex--;
                modifiedGrid = modifiedGrid.setCell(cell, 0);

                Cell previousCell = dynamicCells.get(cellToModifyIndex);
                modifiedGrid = modifiedGrid.setCell(previousCell, previousCell.value + 1);
            }

            if(Validation.isGridValid(grid)){
                cellToModifyIndex++;
            }
        }

        return modifiedGrid;
    }
}
