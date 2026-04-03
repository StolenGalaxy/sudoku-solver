package com.stolengalaxy.sudoku_solver.cell;

import com.stolengalaxy.sudoku_solver.grid.Grid;

import java.util.ArrayList;

public class CellTools {
    public static int getFirstEmptyCellIndex(Grid grid, ArrayList<Integer> indexes){
        for(int i=0; i<indexes.size(); i++){
            if(grid.cells().get(i).value  == 0){
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Cell> toCellRow(ArrayList<Integer> integerArray){
        ArrayList<Cell> cellRow = new ArrayList<>();

        for(int i = 0; i < integerArray.size(); i++){
            Cell newCell = new Cell(integerArray.get(i));

            newCell.column = i;
            cellRow.add(newCell);
        }
        return cellRow;
    }

    public static ArrayList<Integer> toIntegerRow(ArrayList<Cell> cellArray){
        ArrayList<Integer> values = new ArrayList<>();

        cellArray.forEach(cell -> {
            values.add(cell.value);
        });

        return values;
    }

    public static int nextSmallestArrayIndex (ArrayList<ArrayList<Cell>> arrays){
        // returns the index of the next smallest array in a list of arrays

        int index = 0;
        int lowestSize = arrays.getFirst().size();

        for(int i = 0; i<arrays.size(); i++){
            if(arrays.get(i).size() < lowestSize){
                lowestSize = arrays.get(i).size();
                index = i;
            }
        }
        return index;
    }

    public static ArrayList<Cell> getEmptyCells(ArrayList<Cell> set){
        ArrayList<Cell> emptyCells = new ArrayList<>();

        for(Cell cell:set){
            if(cell.value == 0){
                emptyCells.add(cell);
            }
        }
        return emptyCells;
    }

    public static ArrayList<Cell> getCellRow(Grid grid, Cell cell){
        return grid.rows().get(cell.row);
    }
    public static ArrayList<Cell> getCellColumn(Grid grid, Cell cell){
        return grid.columns().get(cell.column);
    }
    public static ArrayList<Cell> getCellBlock(Grid grid, Cell cell){
        return grid.blocks().get(cell.block);
    }
}
