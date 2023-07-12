package de.bcxp.challenge;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVDataReaderTest {

    @Test
    void testGetDelimiterSuccess() {
        CSVDataReader mydude = new CSVDataReader();
        mydude.setDelimiter("k");
        assertEquals(mydude.getDelimiter(), "k");
    }

    //String csv = "Col1,Col2\nVal1,Val2";
}

/*
Test Small Pieces of Code in Isolation.
Follow Arrange, Act, Assert.
Keep Tests Short.
Make Them Simple.
Cover Happy Path First.
Test Edge Cases.
Write Tests Before Fixing Bugs.
Make Them Performant
Keep Them Stateless
Write Deterministic Tests
Use Descriptive Names
Test One Requirement at a Time
Favor Precise Assertions
Run Tests Automatically
Conclusion
 */