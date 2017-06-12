package com.aconex.constants;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void shouldNotChangeConstantsAccidentally() throws Exception{
        Time time = new Time();

        assertEquals(24,time.HOURS_IN_A_DAY);
        assertEquals(60,time.MINUTES_IN_A_HOUR);
        assertEquals(60,time.SECONDS_IN_A_MINUTE);
        assertEquals(1000,time.MILLISECONDS_IN_A_SECOND);
        assertEquals(86400000,time.MAX_MILLISECONDS_IN_A_DAY);
    }

}