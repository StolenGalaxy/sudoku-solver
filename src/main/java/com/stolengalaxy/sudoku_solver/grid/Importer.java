package com.stolengalaxy.sudoku_solver.grid;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
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
            while(scanner.hasNextLine()){
                String row = scanner.nextLine();
                String[] values = row.split(",");
                for(String value:values){
                    // for now just print the value
                    System.out.println(value.strip());
                }
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        return Generator.emptyGrid(9);
    }
}
