package de.bcxp.challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVDataReader implements DataReader {

    private String[][] dataArray2D;
    private String[] columnArray;
    private String delimiter = ",";

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String[][] getDataArray2D() {
        return dataArray2D;
    }

    public String[] getColumnArray() {
        return columnArray;
    }

    CSVDataReader(){
    }

    /***
     * Creates the output data in formate of a 2D array for the every row and every column,
     * As well as an Array for the column names.
     * Sets the private fields dataArray2D and ColumnArray.
     * @param filepath Path to the CSV file
     */
    @Override
    public void createOutputData(String filepath) throws Exception {
        Scanner csv_reader = null;
        // Create csv_reader as Scanner
        try {
            csv_reader = new Scanner(new File(filepath));

            // TODO handle missing values here or in another function, by filling the in? Currently they are empty strings

            // Read column names
            // split is overloaded to leave trailing empty strings in, for cases where the last value of a row is empty
            this.columnArray = csv_reader.nextLine().split(this.delimiter, -1);
            // Read Values
            ArrayList<String[]> dataLists = new ArrayList<>();
            while (csv_reader.hasNext()){
                // clean special cases where we have german numbers 1.000,00
                // maybe only call if delimiter is ";" ? Or a way to specify the csv language
                String[] rowArray = csv_reader.nextLine().split(this.delimiter, -1);
                rowArray = cleanFloatStrings(rowArray);
                dataLists.add(rowArray);
            }
            // Convert ArrayList into 2D Array (of appropriate size), the inner part is already of Type Array
            this.dataArray2D = dataLists.toArray(String[][]::new);

        } catch (Exception e) {
            throw new Exception("Could not read the data, wrong file path or type");
        }

    }

    /**
     * For strings (that represent a decimal) that contain a dot and a comma like the german format "1.000.000,00"
     * remove the dots and replace comma with a dot to convert to english decimal.
     * Does not consider cases like "50,00" in which only a comma is present within a number!
     * @param splitList
     * @return
     */
    private String[] cleanFloatStrings(String[] splitList){
        String[] newArray = new String[splitList.length];

        for (int i = 0; i < splitList.length; i++){
            String currentVal = splitList[i];
            if (currentVal.contains(",") && currentVal.contains(".")){
                currentVal = currentVal.replace(".", "");
                currentVal = currentVal.replace(",", ".");
            }
            newArray[i] = currentVal;
        }
        return newArray;
    }

}
