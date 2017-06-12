package com.aconex.constants;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    private static final double DELTA = 1e-15;

    @Test
    public void shouldNotChangeConstantsAccidentally() throws Exception{
        App appConstants = new App();

        assertEquals("^[AaBb][0-9]+$",appConstants.INPUT_VALIDATION_REGEX);
        assertEquals("A",appConstants.SENSOR1_PREFIX);
        assertEquals("B",appConstants.SENSOR2_PREFIX);
        assertEquals(0.0025,appConstants.LENGTH_OF_VEHICLE,DELTA);
        assertEquals(5,appConstants.NUMBER_OF_DAYS);
        assertEquals(16.6667,appConstants.AVERAGE_SPEED,DELTA);
        assertEquals("data.txt",appConstants.DEFAULT_INPUT_FILE_PATH);
    }

}
