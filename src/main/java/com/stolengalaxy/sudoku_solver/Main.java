package com.stolengalaxy.sudoku_solver;

import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Importer;
import com.stolengalaxy.sudoku_solver.solver.DFS;
import com.stolengalaxy.sudoku_solver.solver.HeuristicTools;

public class Main {
    static void main() {
        Grid grid = Importer.fromCSV();
        int initialEmptyCells = grid.emptyCells().size();
        System.out.println("Initial grid contains " + initialEmptyCells + " empty cells:\n" + grid);

        Grid modifiedGrid = HeuristicTools.completeHeuristics(grid);
        int postHeuristicEmptyCells = modifiedGrid.emptyCells().size();
        System.out.println("\nHeuristics applied, successfully solving " + (initialEmptyCells - postHeuristicEmptyCells) + " empty cells:\n" + modifiedGrid);

        if(postHeuristicEmptyCells > 0){
            System.out.println("\nStarting MRV Recursion. There are " + postHeuristicEmptyCells + " empty cells remaining.");
            modifiedGrid = DFS.complete(modifiedGrid);

            if(modifiedGrid == null){
                throw new RuntimeException("MRV Recursion failed. Are you sure the initial grid was solvable?");
            }

            System.out.println("\nMRV Recursion applied, successfully solving " + (postHeuristicEmptyCells) + " empty cells:\n" + modifiedGrid);
        }
        System.out.println("Solving complete!");
    }
}
