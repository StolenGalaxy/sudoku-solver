package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.DynamicCell;
import com.stolengalaxy.sudoku_solver.util.ArrayTools;

import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
    public int size;
    int blockSize;

    public Grid(ArrayList<ArrayList<Integer>> rows){
        this.rows = rows;
        this.size = rows.size();
        double lengthRoot = Math.sqrt(this.size);
        if(lengthRoot % 1 != 0){
            throw new RuntimeException("Grid size is invalid");
        } else {
            this.blockSize = (int) lengthRoot;
        }
    }

    public ArrayList<ArrayList<Integer>> columns(){
        ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < size; columnIndex++){
            ArrayList<Integer> column = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < size; rowIndex++){
                int value = rows.get(rowIndex).get(columnIndex);
                column.add(value);
            }
            columns.add(column);
        }

        return columns;
    }

    public ArrayList<ArrayList<Integer>> rows(){
        return rows;
    }

    public ArrayList<ArrayList<Integer>> blocks(){
        ArrayList<ArrayList<Integer>> blocks = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            blocks.add(new ArrayList<>());
        }

        int rowCount = 0;
        for(ArrayList<Integer> row:rows){
            rowCount++;
            int fromColumn = 0;

            // split the row into 3 (if a standard 9x9 sudoku) sections
            for(int horizontalBlockIndex = 1; horizontalBlockIndex <= blockSize; horizontalBlockIndex++){
                for(int i = 1; i <= blockSize; i++){
                    int targetBlockIndex;

                    //TODO: currently only works for 9x9 sudoku
                    if(rowCount <= blockSize){
                        targetBlockIndex = horizontalBlockIndex - 1;
                    } else if (rowCount <= 2 * blockSize) {
                        targetBlockIndex = horizontalBlockIndex + blockSize - 1;
                    } else if (rowCount <= 3 * blockSize) {
                        targetBlockIndex = horizontalBlockIndex + 2 * blockSize - 1;
                    } else{
                        throw new RuntimeException("Non-standard sudoku not supported.");
                    }
                    ArrayList<Integer> currentBlock = blocks.get(targetBlockIndex);

                    currentBlock.add(row.get(fromColumn));
                    blocks.set(targetBlockIndex, currentBlock);
                    fromColumn++;

                }

            }
        }
        return blocks;
    }

    public Grid setCell(DynamicCell cell, int newValue){
        Grid modifiedGrid = this;
        modifiedGrid.rows.get(cell.row).set(cell.column, newValue);
        return modifiedGrid;
    }

    public String toString(){
        StringBuilder printableGrid = new StringBuilder();
        for(ArrayList<Integer> row:this.rows){
            StringBuilder printableRow = new StringBuilder();
            for(Integer value:row){
                printableRow.append(value).append("  ");
            }
            printableGrid.append(printableRow).append("\n");
        }
        return printableGrid.toString().strip();
    }

    public ArrayList<Integer> values(){
        ArrayList<Integer> allValues = new ArrayList<>();
        this.rows.forEach(allValues::addAll);
        return allValues;
    }

    public ArrayList<Integer> validValues(){
        return ArrayTools.orderedIntegerArray(this.size);
    }
}
