package com.stolengalaxy.sudoku_solver.solver;

import com.stolengalaxy.sudoku_solver.grid.Grid;

import java.util.ArrayList;

public class DFS {
    private static ArrayList<EmptyCell> getEmptyCells(Grid grid){
        ArrayList<EmptyCell> emptyCells = new ArrayList<>();
        ArrayList<ArrayList<Integer>> rows = grid.rows();
        for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++){
            ArrayList<Integer> row = rows.get(rowIndex);
            for(int columnIndex = 0; columnIndex < rows.size(); columnIndex++){
                int value = row.get(columnIndex);
                if(value == 0){
                    emptyCells.add(new EmptyCell(rowIndex, columnIndex));
                }
            }
        }
        return emptyCells;
    }

    public static Grid complete(Grid grid){
        ArrayList<EmptyCell> emptyCells = getEmptyCells(grid);

        emptyCells.forEach(emptyCell -> {
            System.out.println("Empty cell at (" + emptyCell.row + ", " + emptyCell.column + ")");
        });

        return new Grid(new ArrayList<>());
    }
}
