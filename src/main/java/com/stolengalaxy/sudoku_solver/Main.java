package com.stolengalaxy.sudoku_solver;

import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Importer;
import com.stolengalaxy.sudoku_solver.solver.DFS;
import com.stolengalaxy.sudoku_solver.solver.Heuristics;

public class Main {
    static void main() {
        Grid grid = Importer.fromCSV();
        System.out.println("Initial grid:\n" + grid + "\n");

        Grid modifiedGrid = Heuristics.completeHeuristics(grid);
        System.out.println("Heuristics applied:\n" + modifiedGrid + "\n");

        modifiedGrid = DFS.complete(modifiedGrid);
        System.out.println("DFS applied:\n" + modifiedGrid);

    }
}
