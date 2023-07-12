package de.bcxp.challenge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Class to hold the csv data in a more convenient format.
 * Every column of data is an ArrayList.
 * All columns are saved in a HashMap with the columnNames as Keys.
 */
public class DataHandlerCSV {

    // Keys are column names and values are ArrayLists with the column values
    // Note: with this solution, only Float values are possible in the rows
    // Maybe use multiple ArrayLists instead of different Type per Column
    private HashMap<String, ArrayList<Float>> columnsMap;
    ArrayList<java.lang.String> oldList;
    public HashMap<String, ArrayList<Float>> getColumnsMap() {
        return columnsMap;
    }


    DataHandlerCSV(String[] columnNameslist, String[][] valueLists){
        this.columnsMap = new HashMap<>();
        for (int i = 0; i < valueLists.length; i++){
            for (int n = 0; n < valueLists[i].length; n++){
                String key = columnNameslist[n];
                String value = valueLists[i][n];
                // check if the key exists and if it has a value other than null
                // if not, create a new empty ArrayList as value to that key
                // else take the value of that key (Here an arraylist) and add a value to it
                try{
                    this.columnsMap.computeIfAbsent(key, v -> new ArrayList<>()).add(Float.valueOf(value));
                } catch(ClassCastException e){
                    // Cannot Convert string to float
                }

            }
        }
    }

}
