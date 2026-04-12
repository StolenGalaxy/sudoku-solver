package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.util.IntegerArrayTools;

import java.util.ArrayList;

public class Heuristics {
    private static ArrayList<Integer> getCellCandidates(Grid grid, Cell cell){

        if (cell.value == 0){
            ArrayList<ArrayList<Integer>> rowColumnAndBlock = CellTools.getRowColumnAndBlockAsIntegers(grid, cell);
            ArrayList<Integer> union = IntegerArrayTools.union(rowColumnAndBlock);
            return IntegerArrayTools.complement(union, grid.size);
        } else{
            return new ArrayList<>();
        }
    }

    public static Cell getMRVCell(Grid grid){
        ArrayList<Cell> cells = grid.cells();

        // not just using values from the first cell as it may not be empty
        Cell MRVCell = new Cell(0);
        int minimumRemainingValues = (int) Math.pow(grid.size, 2) + 1;

        for(Cell cell:cells){
            if(cell.value != 0){
                continue;
            }
            int remainingValues = getCellCandidates(grid, cell).size();

            if(remainingValues < minimumRemainingValues){
                minimumRemainingValues = remainingValues;
                MRVCell = cell;
            }
        }
        return MRVCell;
    }

    private static Grid fillFullHouses(Grid grid){
        Grid modifiedGrid = grid;

        ArrayList<ArrayList<Cell>> allRowsColumnsAndBlocks = CellTools.getAllRowsColumnsAndBlocks(grid);

        for(ArrayList<Cell> set:allRowsColumnsAndBlocks){
            ArrayList<Integer> rowAsIntegers = CellTools.toIntegerRow(set);
            ArrayList<Integer> missingValues = IntegerArrayTools.complement(rowAsIntegers, grid.size);

            if(missingValues.size() == 1){
                // in this case a full house is present
                Cell emptyCell = CellTools.getEmptyCells(set).getFirst();

                if(!getCellCandidates(grid, emptyCell).contains(missingValues.getFirst())){
                    throw new RuntimeException("An error occurred while finding Full Houses. Are you sure the given grid was solvable?");
                }

                modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
            }
        }
        return modifiedGrid;
    }

    private static Grid fillNakedSingles(Grid grid){
        Grid modifiedGrid = grid;

        for(Cell emptyCell: grid.emptyCells()){
            ArrayList<ArrayList<Integer>> rowColumnAndBlock = CellTools.getRowColumnAndBlockAsIntegers(grid, emptyCell);
            ArrayList<Integer> union = IntegerArrayTools.union(rowColumnAndBlock);

            ArrayList<Integer> missingValues = IntegerArrayTools.complement(union, grid.size);
            if(missingValues.size() == 1){
                modifiedGrid = modifiedGrid.setCell(emptyCell, missingValues.getFirst());
            }
        }
        return modifiedGrid;
    }

    private static Grid fillHiddenSingles(Grid grid){
        Grid modifiedGrid = grid;
        ArrayList<ArrayList<Cell>> allRowsColumnsAndBlocks = CellTools.getAllRowsColumnsAndBlocks(grid);

        for(ArrayList<Cell> set:allRowsColumnsAndBlocks){

            // what values are missing?
            ArrayList<Integer> missingValues = IntegerArrayTools.complement(CellTools.toIntegerRow(set), grid.size);

            ArrayList<ArrayList<Integer>> setCellCandidates = new ArrayList<>();
            for(Cell cell:set){
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
                        Cell cell = set.get(cellIndexInSet);
                        if(getCellCandidates(grid, cell).contains(value)){
                            // this cell contains the hidden single, set it to that value
                            modifiedGrid = modifiedGrid.setCell(cell, value);
                        }
                    }
                }
            }
        }
        return modifiedGrid;
    }

    private static Grid applyHeuristics(Grid grid){
        Grid modifiedGrid = fillHiddenSingles(grid);
        modifiedGrid = fillNakedSingles(modifiedGrid);
        modifiedGrid = fillFullHouses(modifiedGrid);

        return modifiedGrid;
    }

    public static Grid completeHeuristics(Grid grid){
        Grid oldGrid = grid;
        Grid modifiedGrid = applyHeuristics(oldGrid);

        while(!modifiedGrid.equals(oldGrid)){
            oldGrid = modifiedGrid;
            modifiedGrid = applyHeuristics(oldGrid);
        }
        return modifiedGrid;
    }
}
