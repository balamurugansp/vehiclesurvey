package com.aconex.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeParserTest {
    private static final double DELTA = 1e-15;

    @Test
    public void shouldReturnMillisecondForValidInput() throws Exception {
        int output = TimeParser.parseMilliSecondFromInput("A2134");
        assertEquals(2134,output);
    }

    @Test
    public void shouldReturnMinusOneForInValidInput() throws Exception {
        int output = TimeParser.parseMilliSecondFromInput("A21A4");
        assertEquals(-1,output);
    }

    @Test
    public void shouldReturnMinusOneForNegativeInput() throws Exception {
        int output = TimeParser.parseMilliSecondFromInput("A-214");
        assertEquals(-1,output);
    }

    @Test
    public void shouldReturnMinusOneForLargeInput() throws Exception {
        int output = TimeParser.parseMilliSecondFromInput("A123456789123456789123456789");
        assertEquals(-1,output);
    }

    @Test
    public void shouldReturnMinusOneForNonIntegerInput() throws Exception {
        int output = TimeParser.parseMilliSecondFromInput("A0.13");
        assertEquals(-1,output);
    }

    @Test
    public void shouldConvertMillisecondsToHours() throws Exception {
        double output = TimeParser.convertToHour(3600000);
        assertEquals(1, output, DELTA);
    }

    @Test
    public void shouldReturnMinusOneForNegativeInputs() throws Exception {
        double output = new TimeParser().convertToHour(-1);
        assertEquals(-1, output, DELTA);
    }
}
