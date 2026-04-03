package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.util.IntegerArrayTools;

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
                ArrayList<Integer> missingValues = IntegerArrayTools.findMissingValues(rowAsIntegers, grid.size);

                if(missingValues.size() == 1){
                    // in this case a full house is present
                    Cell emptyCell = CellTools.getEmptyCells(set).getFirst();
                    modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
                }
            }
        }
        return modifiedGrid;
    }

    public static Grid fillNakedSingles(Grid grid){
        for(ArrayList<Cell> row:grid.rows()){
            for(Cell cell:row){
                ArrayList<Cell> cellRow = CellTools.getCellRow(grid, cell);
                ArrayList<Cell> cellColumn = CellTools.getCellColumn(grid, cell);
                ArrayList<Cell> cellBlock = CellTools.getCellBlock(grid, cell);

                ArrayList<ArrayList<Integer>> rowsColumnsAndBlocks = new ArrayList<>();

                rowsColumnsAndBlocks.add(CellTools.toIntegerRow(cellRow));
                rowsColumnsAndBlocks.add(CellTools.toIntegerRow(cellColumn));
                rowsColumnsAndBlocks.add(CellTools.toIntegerRow(cellBlock));

                ArrayList<Integer> union = IntegerArrayTools.union(rowsColumnsAndBlocks);

                ArrayList<Integer> missingValues = IntegerArrayTools.findMissingValues(union, grid.size);
                if(missingValues.size() == 1){
                    // this cell is a naked single
                    System.out.println(cell.row + ", " + cell.column);
                }
            }
        }
        return grid;
    }
}
