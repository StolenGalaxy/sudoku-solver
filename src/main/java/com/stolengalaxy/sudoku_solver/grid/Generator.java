package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Generator {
    public static Grid randomGrid(int size){
        ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
        for(int i = 0; i < size; i++){
            ArrayList<Integer> row = ArrayTools.unorderedIntegerArray(size);
            ArrayList<Cell> cellRow = CellTools.toCellRow(row);

            for(Cell cell:cellRow){
                cell.row = i;
            }
            rows.add(cellRow);
        }
        return new Grid(rows);
    }

    public static Grid emptyGrid(int size){
        ArrayList<ArrayList<Cell>> rows = new ArrayList<>();

        for(int i = 0; i < size; i++){
            ArrayList<Cell> row = new ArrayList<>();
            for(int j = 0; j < size; j++){
                Cell cell = new Cell(0);
                cell.row = i;
                cell.column = j;
                row.add(cell);
            }
            rows.add(row);
        }
        return new Grid(rows);
    }
}
