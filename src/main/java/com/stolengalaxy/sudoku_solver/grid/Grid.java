package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.Cell;
import com.stolengalaxy.sudoku_solver.cell.CellTools;
import com.stolengalaxy.sudoku_solver.util.IntegerArrayTools;

import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
    public int size;
    public int blockSize;

    public Grid(ArrayList<ArrayList<Cell>> rows){
        this.rows = rows;
        this.size = rows.size();
        double lengthRoot = Math.sqrt(this.size);
        if(lengthRoot % 1 != 0){
            throw new RuntimeException("Grid size is invalid");
        } else {
            this.blockSize = (int) lengthRoot;
        }
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
        return rows;
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
        Grid modifiedGrid = this;

        Cell newCell = new Cell(newValue, cell.row, cell.column, cell.block);
        modifiedGrid.rows.get(cell.row).set(cell.column, newCell);

        return modifiedGrid;
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

    public ArrayList<Integer> getEmptyCellIndexes(){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < cells().size(); i++){
            if(cells().get(i).value == 0){
                indexes.add(i);
            }
        }
        return indexes;
    }
}
