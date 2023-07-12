package de.bcxp.challenge;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {


    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        String filePathWeather = "src\\main\\resources\\de\\bcxp\\challenge\\weather.csv";

        Scanner csv_reader = null;
        // Create csv_reader as Scanner
        try {
            csv_reader = new Scanner(new File(filePathWeather));
        } catch (Exception e) {
            System.out.printf(e.toString());
        }

        // read the reader content
        // TODO error handling, exceptions ?
        System.out.print("\n");
        // get first line as columnHeaders
        // Todo make into function and add deliminator parameter
        // Todo check expected format of same number of columns and same number of values in every row
        String[] columnHeaderArray = csv_reader.nextLine().split(",");

        ArrayList<String[]> dataLists = new ArrayList<>();
        while (csv_reader.hasNext()){
            dataLists.add(csv_reader.nextLine().split(","));
        }
        // Convert ArrayList into 2D Array (of appropriate size), the inner part is already of Type Array
        String[][] dataArrays = dataLists.toArray(String[][]::new);

        System.out.print(columnHeaderArray);
        DataHandlerCSV myDataHandlerCSV = new DataHandlerCSV(columnHeaderArray, dataArrays);
        // Get they Hashmap with the values
        HashMap<String, ArrayList<Float>> columnsMap = myDataHandlerCSV.getColumnsMap();


        for (String key : columnsMap.keySet()){
            String value = columnsMap.get(key).toString();
            System.out.println(key + " " + value);
        }


        int rowCount = columnsMap.get("Day").size();
        float[] tempDifference = new float[rowCount];
        for (int i = 0; i < rowCount; i++) {
            float diff = columnsMap.get("MxT").get(i) - columnsMap.get("MnT").get(i);
            tempDifference[i] = diff;
        }
        // Todo handle null array?
        int index = 0;
        float min = tempDifference[index];
        for (int i = 0; i < tempDifference.length; i++){
            if (tempDifference[i] < min){
                index = i;
            }
        }

        Float returnDay = columnsMap.get("Day").get(index);
        System.out.println(returnDay);


        System.out.print("\n");
        String dayWithSmallestTempSpread = returnDay.toString();     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        String countryWithHighestPopulationDensity = "Some country"; // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
