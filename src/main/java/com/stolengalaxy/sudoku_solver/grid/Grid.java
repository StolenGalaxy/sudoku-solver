package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.util.IntegerArrayTools;

import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
    public int size;
    public int blockSize;

    public Grid(ArrayList<ArrayList<Cell>> localRows){
        this.rows = localRows;
        this.size = localRows.size();
        double lengthRoot = Math.sqrt(this.size);
        if(lengthRoot % 1 != 0){
            throw new RuntimeException("Grid size is invalid");
        } else {
            this.blockSize = (int) lengthRoot;
        }
    }

    public Grid(ArrayList<Cell> cells, boolean passCellsNotRows){
        double sizeRoot = Math.sqrt(cells.size());
        double lengthRoot = Math.sqrt(sizeRoot);

        if(lengthRoot % 1 != 0){
            throw new RuntimeException("Grid size is invalid");
        } else {
            this.size = (int) sizeRoot;
            this.blockSize = (int) lengthRoot;
        }

        ArrayList<ArrayList<Cell>> localRows = new ArrayList<>();

        while (localRows.size() < this.size){
            ArrayList<Cell> row = new ArrayList<>();
            while(row.size() < this.size){
                row.add(cells.getFirst());
                cells.removeFirst();
            }
            localRows.add(row);
        }
        this.rows = localRows;
    }

    public ArrayList<ArrayList<Cell>> columns(){
        ArrayList<ArrayList<Cell>> columns = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < size; columnIndex++){
            ArrayList<Cell> column = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < size; rowIndex++){
                column.add(this.rows.get(rowIndex).get(columnIndex));
            }
            columns.add(column);
        }
        return columns;
    }

    public ArrayList<ArrayList<Cell>> rows(){
        return new ArrayList<>(this.rows);
    }

    public ArrayList<ArrayList<Cell>> blocks(){
        // It should be possible to make this more efficient using some modulo calculations to decide on which block to
        // add a cell to, but this is all I've been able to get working so far

        ArrayList<ArrayList<Cell>> blocks = new ArrayList<>();


        // first we want to split the grid into 3 (or more depending on size - sqrt(size)) "block columns" (each 3 (or more) cell wide)
        //--------------------------------------------------------------------
        ArrayList<ArrayList<Cell>> blockColumns = new ArrayList<>();

        ArrayList<Cell> newBlockColumn = new ArrayList<>();
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
            ArrayList<ArrayList<Cell>> blocksSubset = new ArrayList<>();

            for(int i = 1; i <= Math.pow(size, 0.5); i++){
                blocksSubset.add(new ArrayList<>());
            }
            //while the length of the final block in the sub-blocks is less than 9 (or greater if non-standard)
            while(blocksSubset.get((int) Math.pow(size, 0.5) - 1).size() < size){
                if(blockColumns.isEmpty()){
                    break;
                }
                int nextBlockToAddTo = CellTools.nextSmallestArrayIndex(blocksSubset);

                // add the next 3 (or more if non-standard) cells to the next lowest size block then remove them from blockColumns
                ArrayList<Cell> block = blocksSubset.get(nextBlockToAddTo);
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

            for(ArrayList<Cell> block:blocksSubset){
                int blockIndex = blocks.size();

                for(Cell cell:block){
                    cell.block = blockIndex;
                }
                blocks.add(block);
            }
            blocksSubset.clear();
        }
        return blocks;
    }

    public Grid setCell(Cell cell, int newValue){
        ArrayList<ArrayList<Cell>> oldRows = rows();
        ArrayList<Cell> oldRow = oldRows.get(cell.row);
        ArrayList<Integer> values = CellTools.toIntegerRow(oldRow);

        // creating the new row
        ArrayList<Cell> newRow = new ArrayList<>();
        for(int columnIndex = 0; columnIndex < values.size(); columnIndex++){
            Cell newCell = new Cell(values.get(columnIndex));

            newCell.column = columnIndex;
            newCell.row = cell.row;

            if(columnIndex != cell.column){
                newCell.value = oldRow.get(columnIndex).value;
            } else{
                newCell.value = newValue;
            }
            newRow.add(newCell);
        }
        // adding all rows back
        ArrayList<ArrayList<Cell>> newRows = new ArrayList<>();
        for(int rowIndex = 0; rowIndex < oldRows.size(); rowIndex++){
            if(rowIndex != cell.row){
                newRows.add(new ArrayList<>(oldRows.get(rowIndex)));
            } else{
                newRows.add(newRow);
            }
        }
        return new Grid(newRows);
    }

    @Override
    public String toString(){
        StringBuilder printableGrid = new StringBuilder();
        for(ArrayList<Cell> row:this.rows){
            StringBuilder printableRow = new StringBuilder();
            for(Cell cell:row){
                printableRow.append(cell.value).append("  ");
            }
            printableGrid.append(printableRow).append("\n");
        }
        return printableGrid.toString().strip();
    }

    public ArrayList<Cell> cells(){
        ArrayList<Cell> allCells = new ArrayList<>();
        this.rows.forEach(allCells::addAll);
        return allCells;
    }

    public ArrayList<Integer> validValues(){
        return IntegerArrayTools.orderedIntegerArray(this.size);
    }

    public ArrayList<Cell> emptyCells(){
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for(Cell cell:cells()){
            if(cell.value == 0){
                emptyCells.add(cell);
            }
        }
        return emptyCells;
    }
}
