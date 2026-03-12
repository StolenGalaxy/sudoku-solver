package com.stolengalaxy.sudoku_solver.models;

import java.util.ArrayList;

public class Grid {
    ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

    public Grid(ArrayList<ArrayList<Integer>> rows){
        this.rows = rows;
    }
}
