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



        // first we want to split the grid into 3 (or more depending on size) "block columns" (each 3 (or more) cell wide)
        //--------------------------------------------------------------------
        ArrayList<ArrayList<Integer>> blockColumns = new ArrayList<>();

        ArrayList<Integer> newBlockColumn = new ArrayList<>();
        columns().forEach(column -> {
            if(newBlockColumn.size() < size * Math.pow(size, 0.5)){
                newBlockColumn.addAll(column);
            } else{
                blockColumns.add(new ArrayList<>(newBlockColumn));
                newBlockColumn.clear();
                newBlockColumn.addAll(column);
            }
        });
        blockColumns.add(newBlockColumn);
        //--------------------------------------------------------------------
        
        while(blocks.size() < size){
            // initialise blocks subset
            ArrayList<ArrayList<Integer>> blocksSubset = new ArrayList<>();

            for(int i = 1; i <= Math.pow(size, 0.5); i++){
                blocksSubset.add(new ArrayList<>());
            }
            //while the length of the final block in the sub-blocks is less than 9 (or greater if non-standard)
            while(blocksSubset.get((int) Math.pow(size, 0.5) - 1).size() < size){
                if(blockColumns.isEmpty()){
                    break;
                }

                int nextBlockToAddTo = ArrayTools.nextSmallestArrayIndex(blocksSubset);

                // add the next 3 (or more if non-standard) cells to the next lowest size block them remove them from blockColumns
                ArrayList<Integer> block = blocksSubset.get(nextBlockToAddTo);
                for(int i = 0; i < Math.pow(size, 0.5); i++){
                    if(blockColumns.getFirst().isEmpty()){
                        blockColumns.removeFirst();
                        break;
                    }
                    block.add(blockColumns.getFirst().getFirst());
                    blockColumns.getFirst().removeFirst();

                    blocksSubset.set(nextBlockToAddTo, block);
                }
            }
            blocks.addAll(blocksSubset);
            blocksSubset.clear();
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
