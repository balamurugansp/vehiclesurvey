package com.aconex.processors;

import com.aconex.constants.App;
import com.aconex.constants.Time;
import com.aconex.model.Direction;
import com.aconex.model.SessionOfTheDay;
import com.aconex.model.VehicleEntry;

import java.util.List;

public class AverageDistanceProcessor extends AbstractBaseProcessor {
    private final int sessionInterval;

    public AverageDistanceProcessor(int sessionInterval) {
        this.sessionInterval = sessionInterval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(sessionInterval);
        for (SessionOfTheDay session : sessions){
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            List<VehicleEntry> entriesInSouthDirection = getEntriesInTheDirection(entriesInTheSession, Direction.SOUTH);
            List<VehicleEntry> entriesInNorthDirection = getEntriesInTheDirection(entriesInTheSession, Direction.NORTH);
            double distanceInSouthDirection = getAverageDistance(entriesInSouthDirection);
            double distanceInNorthDirection = getAverageDistance(entriesInNorthDirection);
            sb.append(session).append(" | Average distance between cars in south direction = ").append(distanceInSouthDirection).append(" meters, in north direction = ").append(distanceInNorthDirection).append(" meters\n");
        }
        return sb.toString();
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.isEmpty())
            return 0;
            long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).entryTime().getTime();
            long timeOfFirstVehicle = entriesInTheSession.get(0).entryTime().getTime();
            double averageTimeGapBetweenVehicles = ((double) timeOfLastVehicle - (double) timeOfFirstVehicle) / (double)entriesInTheSession.size();
            return averageTimeGapBetweenVehicles * App.AVERAGE_SPEED/ Time.MILLISECONDS_IN_A_SECOND;
    }
}
