package com.stolengalaxy.sudoku_solver.grid;

import com.stolengalaxy.sudoku_solver.cell.Cell;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Importer {
    private static String pickFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Select a CSV or Text File", "csv", "txt"));
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return chooser.getSelectedFile().getAbsolutePath();
        } else{
            throw new RuntimeException("Failed to select a file.");
        }
    }
    public static Grid fromCSV(){
        File file = new File(pickFile());
        try {
            Scanner scanner = new Scanner(file);

            ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
            for(int rowIndex = 0; scanner.hasNextLine(); rowIndex++){
                String line = scanner.nextLine();
                String[] unformattedValues = line.split(",");
                ArrayList<Cell> row = new ArrayList<>();

                for(int columnIndex = 0; columnIndex < unformattedValues.length; columnIndex++){
                    String formattedValue = unformattedValues[columnIndex].strip();

                    Cell cell = new Cell(Integer.parseInt(formattedValue));
                    cell.row = rowIndex;
                    cell.column = columnIndex;

                    row.add(cell);
                }
                rows.add(row);
            }
            return new Grid(rows);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
