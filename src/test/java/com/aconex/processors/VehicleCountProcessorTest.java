package com.aconex.processors;

import com.aconex.model.Direction;
import com.aconex.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleCountProcessorTest {
    private final List<VehicleEntry> entries = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        entries.add(new VehicleEntry(8771,8899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(28771,28899, Direction.NORTH,0));
        entries.add(new VehicleEntry(328771,328899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(6328771,6328899, Direction.NORTH,0));
        entries.add(new VehicleEntry(86328771,86328899, Direction.SOUTH,0));

        entries.add(new VehicleEntry(8632,8732, Direction.NORTH,1));
        entries.add(new VehicleEntry(86328,86400, Direction.NORTH,1));
        entries.add(new VehicleEntry(863287,863328, Direction.SOUTH,1));
        entries.add(new VehicleEntry(8632877,8632889, Direction.NORTH,1));
        entries.add(new VehicleEntry(86328771,86328899, Direction.SOUTH,1));
    }

    @Test
    public void shouldGiveSessionviceOutputForHourlyCount() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(360);
        String output = processor.process(entries);

        String expectedOutput = "Session 00:00:00 to 06:00:00 | Day 0 Count South = 2 North = 2 | Day 1 Count South = 1 North = 3 | Day 2 Count South = 0 North = 0 | Day 3 Count South = 0 North = 0 | Day 4 Count South = 0 North = 0\n" +
                "Session 06:00:00 to 12:00:00 | Day 0 Count South = 0 North = 0 | Day 1 Count South = 0 North = 0 | Day 2 Count South = 0 North = 0 | Day 3 Count South = 0 North = 0 | Day 4 Count South = 0 North = 0\n" +
                "Session 12:00:00 to 18:00:00 | Day 0 Count South = 0 North = 0 | Day 1 Count South = 0 North = 0 | Day 2 Count South = 0 North = 0 | Day 3 Count South = 0 North = 0 | Day 4 Count South = 0 North = 0\n" +
                "Session 18:00:00 to 00:00:00 | Day 0 Count South = 1 North = 0 | Day 1 Count South = 1 North = 0 | Day 2 Count South = 0 North = 0 | Day 3 Count South = 0 North = 0 | Day 4 Count South = 0 North = 0\n" +
                "\nSession 00:00:00 to 06:00:00 is peak session with a vehicle count of 8 across 5 days";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void shouldGivePeakSessionInformationInOutput() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(360);
        String output = processor.process(entries);

        assertTrue(output.contains("Session 00:00:00 to 06:00:00 is peak session with a vehicle count of 8 across 5 days"));
    }

    @Test
    public void shouldHaveSeparateCountForBothDirections() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(360);
        String output = processor.process(entries);

        assertTrue(output.contains("Day 0 Count South = 2 North = 2"));
    }

    @Test
    public void shouldNotGiveOutputIfSessionIntervalIsNotEvenlyDistributed() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(25);
        String output = processor.process(entries);

        assertEquals("", output);
    }
}