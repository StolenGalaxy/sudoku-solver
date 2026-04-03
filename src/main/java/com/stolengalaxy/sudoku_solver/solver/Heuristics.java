package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.util.IntegerArrayTools;

import java.util.ArrayList;

public class Heuristics {
    private static ArrayList<Integer> getCellCandidates(Grid grid, Cell cell){
        ArrayList<ArrayList<Integer>> rowColumnAndBlock = CellTools.getRowColumnAndBlockAsIntegers(grid, cell);
        ArrayList<Integer> union = IntegerArrayTools.union(rowColumnAndBlock);

        return IntegerArrayTools.complement(union, grid.size);
    }

    public static Grid fillFullHouses(Grid grid){
        Grid modifiedGrid = grid;

        ArrayList<ArrayList<Cell>> allRowsColumnsAndBlocks = CellTools.getAllRowsColumnsAndBlocks(grid);

        for(ArrayList<Cell> set:allRowsColumnsAndBlocks){
            ArrayList<Integer> rowAsIntegers = CellTools.toIntegerRow(set);
            ArrayList<Integer> missingValues = IntegerArrayTools.complement(rowAsIntegers, grid.size);

            if(missingValues.size() == 1){
                // in this case a full house is present
                Cell emptyCell = CellTools.getEmptyCells(set).getFirst();
                modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
            }
        }
        return modifiedGrid;
    }

    public static Grid fillNakedSingles(Grid grid){
        Grid modifiedGrid = grid;
        for(ArrayList<Cell> row:grid.rows()){
            for(Cell cell:row){
                ArrayList<ArrayList<Integer>> rowColumnAndBlock = CellTools.getRowColumnAndBlockAsIntegers(grid, cell);
                ArrayList<Integer> union = IntegerArrayTools.union(rowColumnAndBlock);

                ArrayList<Integer> missingValues = IntegerArrayTools.complement(union, grid.size);
                if(missingValues.size() == 1){
                    modifiedGrid = modifiedGrid.setCell(cell, missingValues.getFirst());
                }
            }
        }
        return modifiedGrid;
    }

    public static Grid fillHiddenSingles(Grid grid){
        Grid modifiedGrid = grid;
        ArrayList<ArrayList<Cell>> allRowsColumnsAndBlocks = CellTools.getAllRowsColumnsAndBlocks(grid);

        for(int setIndex = 0; setIndex < allRowsColumnsAndBlocks.size(); setIndex++){
        }
        return modifiedGrid;
    }
}
