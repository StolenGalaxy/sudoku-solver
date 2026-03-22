package com.stolengalaxy.sudoku_solver;

import com.stolengalaxy.sudoku_solver.grid.Generator;
import com.stolengalaxy.sudoku_solver.grid.Grid;
import com.stolengalaxy.sudoku_solver.solver.DFS;

public class Main {
    static void main() {
        Grid emptyGrid = Generator.emptyGrid(16);
        System.out.println(DFS.complete(emptyGrid));
    }
}
