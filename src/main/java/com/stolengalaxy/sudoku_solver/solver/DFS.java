package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Validation;

public class DFS {
    public static Grid complete(Grid grid){
        grid = HeuristicTools.logAllCellCandidates(grid);

        if(Validation.isGridFull(grid)){
            return grid;
        }

        Cell MRVCell = HeuristicTools.getMRVCell(grid);

        for(Integer candidate:MRVCell.cellCandidates){
            Grid modifiedGrid = grid.setCellValue(MRVCell, candidate);

            if(Validation.isGridValid(modifiedGrid)){
                Grid nextGrid = complete(modifiedGrid);
                if(nextGrid != null){
                    return nextGrid;
                }
            }
        }
        return null;
    }
}
