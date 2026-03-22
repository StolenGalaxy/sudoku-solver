package com.stolengalaxy.sudoku_solver.util;

import java.util.ArrayList;
import java.util.Random;

public class ArrayTools {
    public static int countIntegerOccurrences(ArrayList<Integer> array, int targetInteger){
        int count = 0;
        for(int value:array){
            if(value == targetInteger){
                count++;
            }
        }
        return count;
    }

    private static ArrayList<Integer> blankArray(int length){
        ArrayList<Integer> newArray = new ArrayList<>();
        for(int i = 1; i <= length; i++){
            newArray.add(0);
        }
        return newArray;
    }

    public static ArrayList<Integer> orderedIntegerArray(int length){
        ArrayList<Integer> newArray = new ArrayList<>();
        for(int i=1; i <= length; i++){
            newArray.add(i);
        }
        return newArray;
    }

    public static ArrayList<Integer> unorderedIntegerArray(int length){

        ArrayList<Integer> array = blankArray(length);

        ArrayList<Integer> unusedValues = orderedIntegerArray(length);

        Random random = new Random();
        for(int i = 0; i < length; i++){

            int randomListIndex = random.nextInt(unusedValues.size());
            int randomUnusedValue = unusedValues.get(randomListIndex);
            unusedValues.remove(randomListIndex);
            array.set(i, randomUnusedValue);
        }
        return array;
    }

    public static int nextSmallestArrayIndex (ArrayList<ArrayList<Integer>> arrays){
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
}
