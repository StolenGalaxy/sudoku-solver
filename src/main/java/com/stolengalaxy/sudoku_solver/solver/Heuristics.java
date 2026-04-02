package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Heuristics {
    public static Grid fillFullHouses(Grid grid){
        Grid modifiedGrid = grid;

        ArrayList<ArrayList<ArrayList<Cell>>> rowsColumnsAndBlocks = new ArrayList<>();

        rowsColumnsAndBlocks.add(grid.rows());
        rowsColumnsAndBlocks.add(grid.columns());
        rowsColumnsAndBlocks.add(grid.blocks());

        for(ArrayList<ArrayList<Cell>> type:rowsColumnsAndBlocks){
            for(ArrayList<Cell> set:type){
                ArrayList<Integer> rowAsIntegers = CellTools.toIntegerRow(set);
                ArrayList<Integer> missingValues = ArrayTools.findMissingValues(rowAsIntegers, grid.size);

                if(missingValues.size() == 1){
                    // in this case a full house is present
                    Cell emptyCell = CellTools.getEmptyCells(set).getFirst();
                    modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
                }
            }
        }
        return modifiedGrid;
    }
}
