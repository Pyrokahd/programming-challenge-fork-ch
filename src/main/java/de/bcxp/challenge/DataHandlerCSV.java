package de.bcxp.challenge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Class to hold the csv data in a more convenient format.
 * Every column of data is an ArrayList.
 * All columns are saved in a HashMap with the columnNames as Keys.
 */
public class DataHandlerCSV {

    // Keys are column names and values are ArrayLists<String> with the column values
    private HashMap<String, ArrayList<String>> columnsMap;
    public HashMap<String, ArrayList<String>> getColumnsMap() {
        return columnsMap;
    }


    DataHandlerCSV(String[] columnNameslist, String[][] valueLists){
        saveColumnNamesAndDataLists(columnNameslist, valueLists);
    }

    /***
     * Takes an Array of Column names and a 2D Array of values (both type string)
     * and saves them in a HashMap with the ColumnName as Key and the column values
     * as ArrayList of strings.
     * @param columnNamesArray
     * @param valueLists
     */
    private void saveColumnNamesAndDataLists(String[] columnNamesArray, String[][] valueLists){
        this.columnsMap = new HashMap<>();
        for (int i = 0; i < valueLists.length; i++){
            for (int n = 0; n < valueLists[i].length; n++){
                String key = columnNamesArray[n];
                String value = valueLists[i][n];
                // check if the key exists and if it has a value other than null
                // if not, create a new empty ArrayList as value to that key
                // else take the value of that key (Here an arraylist) and add a value to it
                try{
                    this.columnsMap.computeIfAbsent(key, v -> new ArrayList<>()).add(value);
                } catch(ClassCastException e){
                    System.out.println("Cannot convert to string: "+e);
                }
            }
        }
    }

    /***
     * Takes the column names for the day, the min value and the max value
     * returns the day in which the delta is minimum.
     * @param day_col
     * @param deltamin_col
     * @param deltamax_col
     * @return
     */
    public String getLowestDeltaDay(String day_col, String deltamin_col, String deltamax_col) throws Exception {
        // if column name does not exist
        if (!(columnsMap.containsKey(day_col) && columnsMap.containsKey(deltamin_col) &&
                columnsMap.containsKey(deltamax_col))){
            throw new Exception("Column name not found!");
        }

        // Calculate the temperature delta
        int rowCount = columnsMap.get(day_col).size();
        float[] tempDifference = new float[rowCount];
        for (int i = 0; i < rowCount; i++) {
            float diff = Float.valueOf(columnsMap.get(deltamax_col).get(i)) - Float.valueOf(columnsMap.get(deltamin_col).get(i));
            tempDifference[i] = diff;
        }
        // Get day with the lowest delta
        int index = 0;
        float min = tempDifference[index];
        for (int i = 0; i < tempDifference.length; i++) {
            if (tempDifference[i] < min) {
                index = i;
            }
        }
        String returnDay = columnsMap.get(day_col).get(index);
        return returnDay;
    }


    public String getHighestCountryPopDensity(String country_col, String area_col, String pop_col) throws Exception {
        // if column name does not exist
        if (!(columnsMap.containsKey(country_col) && columnsMap.containsKey(area_col) &&
                columnsMap.containsKey(pop_col))){
            throw new Exception("Column name not found!");
        }

        // population / area
        int rowCount = columnsMap.get(country_col).size();
        float[] densityArray = new float[rowCount];
        for (int i = 0; i < rowCount; i++) {
            float a = Float.valueOf(columnsMap.get(pop_col).get(i));
            var b = Float.valueOf(columnsMap.get(area_col).get(i));
            float density = a / b;
            densityArray[i] = density;
        }
        // Get country with the highest density
        int index = 0;
        float max = densityArray[index];
        for (int i = 0; i < densityArray.length; i++) {
            if (densityArray[i] > max) {
                index = i;
            }
        }
        String returnCountry = columnsMap.get(country_col).get(index);
        return returnCountry;
    }


}
