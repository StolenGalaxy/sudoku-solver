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
            // what values are missing?
            ArrayList<Cell> currentSet = allRowsColumnsAndBlocks.get(setIndex);
            ArrayList<Integer> missingValues = IntegerArrayTools.complement(CellTools.toIntegerRow(currentSet), grid.size);

            ArrayList<ArrayList<Integer>> setCellCandidates = new ArrayList<>();
            for(Cell cell:currentSet){
                ArrayList<Integer> cellCandidates = getCellCandidates(grid, cell);
                setCellCandidates.add(cellCandidates);
            }

            for(int value:missingValues){
                int candidateOccurrences = 0;

                for(ArrayList<Integer> cellCandidates:setCellCandidates){
                    if(cellCandidates.contains(value)){
                        candidateOccurrences++;
                    }
                }
                // does a hidden single exist in this set?
                if(candidateOccurrences == 1){
                    // which cell has the candidate?
                    for(int cellIndexInSet = 0; cellIndexInSet < grid.size; cellIndexInSet++){
                        Cell cell = currentSet.get(cellIndexInSet);
                        if(getCellCandidates(grid, cell).contains(value)){
                            // this cell contains the hidden single, set it to that value
                            modifiedGrid = modifiedGrid.setCell(cell, value);
                            //System.out.println("hidden single (" + value + ") at (" + cell.row + ", " + cell.column + ")");
                        }
                    }
                }
            }
        }
        return modifiedGrid;
    }
}
