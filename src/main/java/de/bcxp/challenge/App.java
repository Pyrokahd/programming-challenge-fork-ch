package de.bcxp.challenge;


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

        String filePathWeather = "src/main/resources/de/bcxp/challenge/weather.csv";
        String filePathCountries = "src/main/resources/de/bcxp/challenge/countries.csv";

        // TASK1

        CSVDataReader myCSVDataReader = new CSVDataReader();
        try{
            myCSVDataReader.createOutputData(filePathWeather);
        } catch (Exception e){
            e.printStackTrace(System.out);
            System.out.printf(e.toString());
        }

        DataHandlerCSV myDataHandlerCSVWeather = new DataHandlerCSV(myCSVDataReader.getColumnArray(), myCSVDataReader.getDataArray2D());

        String dayWithSmallestTempSpread = null;

        dayWithSmallestTempSpread = myDataHandlerCSVWeather.getLowestDeltaDay("Day", "MnT", "MxT"); // Your day analysis function call …

        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        // TASK2

        myCSVDataReader.setDelimiter(";");
        try {
            myCSVDataReader.createOutputData(filePathCountries);
        }catch (Exception e){
            e.printStackTrace(System.out);
            System.out.printf(e.toString());
        }


        DataHandlerCSV myDataHandlerCSVCountries = new DataHandlerCSV(myCSVDataReader.getColumnArray(), myCSVDataReader.getDataArray2D());

        String countryWithHighestPopulationDensity = null;

        countryWithHighestPopulationDensity = myDataHandlerCSVCountries.getHighestCountryPopDensity("Name", "Area (km²)", "Population"); // Your population density analysis function call …


        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);

    }
}
