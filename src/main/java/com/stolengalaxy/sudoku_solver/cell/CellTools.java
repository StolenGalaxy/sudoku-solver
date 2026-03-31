package com.stolengalaxy.sudoku_solver.cell;

import com.stolengalaxy.sudoku_solver.grid.Grid;

import java.util.ArrayList;

public class CellTools {
    public static int getFirstEmptyCellIndex(ArrayList<Cell> cellArray){
        for(int i=0; i<cellArray.size(); i++){
            if(cellArray.get(i).value == 0){
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Cell> toCellRow(ArrayList<Integer> integerArray){
        ArrayList<Cell> cellRow = new ArrayList<>();

        for(int i = 0; i < integerArray.size(); i++){
            Cell newCell = new Cell(i, integerArray.get(i));
            cellRow.add(newCell);
        }
        return cellRow;
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


    private static ArrayList<Integer> getCellRow(Grid grid, Cell cell){
        return grid.rows().get(cell.row);
    }
    private static ArrayList<Integer> getCellColumn(Grid grid, Cell cell){
        return grid.columns().get(cell.column);
    }
    private static ArrayList<Integer> getCellBlock(Grid grid, Cell cell){
        //TODO: IMPLEMENT
        return new ArrayList<>();
    }
}
