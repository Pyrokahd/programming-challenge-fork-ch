package de.bcxp.challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class DataHandlerCSVTest {


    @Test
    void testDataHandlerCSVDataCreationOnConstructionSuccess(){
        String[] mockColumns = {"col1","col2"};
        String[][] mockRowValues = {{"1","a"},{"2","b"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        HashMap<String, ArrayList<String>> actualData = myDataHandler.getColumnsMap();

        HashMap<String, ArrayList<String>> expectedData = new HashMap<>();
        expectedData.put("col1", new ArrayList<String>(){{add("1");add("2");}});
        expectedData.put("col2", new ArrayList<String>(){{add("a");add("b");}});

        assertEquals(expectedData,actualData);
    }


    @Test
    void calculateLowestDeltaTempDaySuccess(){
        String[] mockColumns = {"Day","minT","maxT"};
        String[][] mockRowValues = {{"1","10","-7"},{"2","20","-1"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        String actualDay = null;
        try{
            actualDay = myDataHandler.getLowestDeltaDay("Day", "minT", "maxT");
        } catch(Exception e){
        }
        String expectedDay = "2";
        assertEquals(expectedDay, actualDay);
    }

    @Test
    void calculateLowestDeltaTempDayWrongColumnFailure(){
        String[] mockColumns = {"Day","minT","maxT"};
        String[][] mockRowValues = {{"1","10","-7"},{"2","20","-1"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);

        Exception exception = assertThrows(Exception.class, () -> {
            myDataHandler.getLowestDeltaDay("DayZ", "minT", "maxT");
        });
        String expectedMessage = "Column name not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculateLowestDeltaTempDayNonFloatConvertibleStringFailure(){
        String[] mockColumns = {"Day","minT","maxT"};
        String[][] mockRowValues = {{"1","10","-7"},{"2","20","notafloat"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);

        Exception exception = assertThrows(java.lang.NumberFormatException.class, () -> {
            myDataHandler.getLowestDeltaDay("Day", "minT", "maxT");
        });
        String expectedMessage = "Can not convert one of those strings to float";
        String actualMessage = exception.getMessage();
        System.out.println(exception.getMessage());
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculateHighestCountryPopDensitySuccess()
    {
        String[] mockColumns = {"Country","Area","Population"};
        String[][] mockRowValues = {{"c1","30000","600000"},{"c2","450000","1000000"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        String actualCountry = null;
        try{
            actualCountry = myDataHandler.getHighestCountryPopDensity("Country", "Area", "Population");
        } catch(Exception e){
        }
        String expectedCountry = "c1";
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void calculateHighestCountryPopDensityWrongColumnFailure()
    {
        String[] mockColumns = {"Country","Area","Population"};
        String[][] mockRowValues = {{"c1","30000","600000"},{"c2","450000","1000000"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        Exception exception = assertThrows(Exception.class, () -> {
            myDataHandler.getHighestCountryPopDensity("Country", "Area51", "Population");
        });
        String expectedMessage = "Column name not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculateHighestCountryPopDensityDivisionByZeroFailure()
    {
        String[] mockColumns = {"Country","Area","Population"};
        String[][] mockRowValues = {{"c1","30000","600000"},{"c2","450000","0"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            myDataHandler.getHighestCountryPopDensity("Country", "Area", "Population");
        });
        String expectedMessage = "one of the values is less or equal than zero!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculateHighestCountryPopDensityNegativeValueFailure()
    {
        String[] mockColumns = {"Country","Area","Population"};
        String[][] mockRowValues = {{"c1","30000","-600000"},{"c2","450000","1000000"}};
        DataHandlerCSV myDataHandler = new DataHandlerCSV(mockColumns, mockRowValues);
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            myDataHandler.getHighestCountryPopDensity("Country", "Area", "Population");
        });
        String expectedMessage = "one of the values is less or equal than zero!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
