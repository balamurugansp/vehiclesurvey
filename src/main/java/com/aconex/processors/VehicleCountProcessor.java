package com.aconex.processors;

import com.aconex.constants.App;
import com.aconex.model.Direction;
import com.aconex.model.SessionOfTheDay;
import com.aconex.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleCountProcessor extends AbstractBaseProcessor {
    private final int interval; //in minutes

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        SessionOfTheDay peakSession = null;
        int peakVehicleCount = 0;
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(interval);
        if (sessions.isEmpty())
            return "";
        for(SessionOfTheDay session : sessions){
            int totalCountAcrossDays = 0;
            List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
            sb.append(session.toString());
            for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                int southCount = countEntriesForTheDayForDirection(sessionEntries, day, Direction.SOUTH);
                int northCount = countEntriesForTheDayForDirection(sessionEntries, day, Direction.NORTH);
                sb.append(" | Day ").append(day).append(" Count South = ").append(southCount).append(" North = ").append(northCount);
                totalCountAcrossDays += southCount + northCount;
            }
            sb.append('\n');
            if (totalCountAcrossDays > peakVehicleCount){
                peakVehicleCount = totalCountAcrossDays;
                peakSession = session;
            }
        }
        sb.append('\n').append(peakSession).append(" is peak session with a vehicle count of ").append(peakVehicleCount).append(" across ").append(App.NUMBER_OF_DAYS).append(" days");
        return sb.toString();
    }

    private int countEntriesForTheDayForDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().filter(entry -> entry.getDay() == day && entry.getDirection() == direction).collect(Collectors.toList()).size();
    }
}
