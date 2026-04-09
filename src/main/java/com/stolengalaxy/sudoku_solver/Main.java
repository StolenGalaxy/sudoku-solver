package com.stolengalaxy.sudoku_solver;

import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.grid.Importer;
import com.stolengalaxy.sudoku_solver.solver.DFS;
import com.stolengalaxy.sudoku_solver.solver.Heuristics;

public class Main {
    static void main() {
        Grid grid = Importer.fromCSV();
        System.out.println("Starting grid:\n" + grid + "\n");

        grid = Heuristics.applyHeuristics(grid);
        System.out.println("Applied heuristics:\n" + grid + "\n");

        grid = DFS.complete(grid);
        System.out.println("Applied DFS:\n" + grid);
    }
}
