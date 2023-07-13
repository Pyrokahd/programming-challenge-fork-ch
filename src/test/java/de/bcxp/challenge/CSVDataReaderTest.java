package de.bcxp.challenge;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CSVDataReaderTest {

    @Test
    void testGetDelimiterSuccess() {
        CSVDataReader myReader = new CSVDataReader();
        myReader.setDelimiter("k");
        assertEquals(myReader.getDelimiter(), "k");
    }

    @Test
    void testCreateCSVDataFromReadingFileSuccess(){
        CSVDataReader myReader = new CSVDataReader();
        String path = "src/main/resources/de/bcxp/challenge/unitTest.csv";
        try{
            myReader.createOutputData(path);
        } catch(Exception e) {
        }
        String[] expectedColumns = {"col1", "col2", "col3"};
        String[][] expectedRows = {{"1","0.1","a"},{"2","0.2","b"},{"3","0.3","c"}};
        assertArrayEquals(expectedColumns, myReader.getColumnArray());
        assertArrayEquals(expectedRows, myReader.getDataArray2D());
    }

    @Test
    void testCreateCSVDataWrongInputFailure(){
        CSVDataReader myReader = new CSVDataReader();
        String path = "src/main/resources/de/bcxp/challenge/WrongPath.csv";
        Exception exception = assertThrows(java.io.FileNotFoundException.class, () -> {
            myReader.createOutputData(path);
        });
        String expectedMessage = "Could not read the data, wrong file path or type";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCleanFloatStringsSuccess(){
        CSVDataReader myReader = new CSVDataReader();
        myReader.setDelimiter(";");
        String path = "src/main/resources/de/bcxp/challenge/unitTest2.csv";
        try{
            myReader.createOutputData(path);
        } catch(Exception e) {
        }
        String[] expectedColumns = {"col1", "col2", "col3"};
        String[][] expectedRows = {{"1","1000.00","a"},{"2","0.2","b"},{"3","5444.33","c"}};
        assertArrayEquals(expectedColumns, myReader.getColumnArray());
        assertArrayEquals(expectedRows, myReader.getDataArray2D());
    }

}
