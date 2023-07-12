package de.bcxp.challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void create_output_data(String filepath)  {
        Scanner csv_reader = null;
        // Create csv_reader as Scanner
        try {
            csv_reader = new Scanner(new File(filepath));

            // Todo check expected format of same number of columns and same number of values in every row
            // Read column names
            this.columnArray = csv_reader.nextLine().split(this.delimiter);

            System.out.println("\n");
            System.out.println(Arrays.toString(columnArray));
            // Read Values
            ArrayList<String[]> dataLists = new ArrayList<>();
            while (csv_reader.hasNext()){
                // clean special cases where we have german numbers 1.000,00
                // maybe only call if delimiter is ";" ?
                String[] rowArray = csv_reader.nextLine().split(this.delimiter);
                rowArray = cleanFloatStrings(rowArray);
                dataLists.add(rowArray);
            }
            // Convert ArrayList into 2D Array (of appropriate size), the inner part is already of Type Array
            this.dataArray2D = dataLists.toArray(String[][]::new);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.printf(e.toString());
        }

    }

    /**
     * For strings (that represent a decimal) that contain a dot and a comma like the german format "1.000.000,00"
     * remove the dots and replace comma with a dot to convert to english decimal.
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
