package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Heuristics {
    public static Grid fillNakedSingles(Grid grid){
        Grid modifiedGrid = grid;

        // fill naked singles in rows
        for(ArrayList<Cell> row:grid.rows()){
            ArrayList<Integer> rowAsIntegers = CellTools.toIntegerRow(row);
            ArrayList<Integer> missingValues = ArrayTools.findMissingValues(rowAsIntegers, grid.size);

            if(missingValues.size() == 1){
                // in this case a naked single is present
                Cell emptyCell = CellTools.getEmptyCells(row).getFirst();
                modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
            }
        }
        return modifiedGrid;
    }
}
