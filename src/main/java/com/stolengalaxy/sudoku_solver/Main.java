package com.stolengalaxy.sudoku_solver;

import com.stolengalaxy.sudoku_solver.grid.Generator;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.solver.DFS;
import com.stolengalaxy.sudoku_solver.solver.Heuristics;

public class Main {
    static void main() {
        //TODO: input sample grids from a file to solve
        Grid grid = Generator.emptyGrid(9);
        System.out.println("Starting grid:\n" +  grid + "\n");

        // note this won't currently do anything for the given empty grid
        grid = Heuristics.applyHeuristics(grid);
        System.out.println("Heuristic-applied grid:\n" +  grid + "\n");

        grid = DFS.complete(grid);
        System.out.println("DFS-applied grid:\n" +  grid);
    }
}
