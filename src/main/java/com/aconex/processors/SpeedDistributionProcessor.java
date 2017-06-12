package com.aconex.processors;

import com.aconex.model.SessionOfTheDay;
import com.aconex.model.VehicleEntry;

import java.util.List;

public class SpeedDistributionProcessor extends AbstractBaseProcessor {
    private final int sessionInterval;

    public SpeedDistributionProcessor(int sessionInterval) {
        this.sessionInterval = sessionInterval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(sessionInterval);
        for (SessionOfTheDay session : sessions){
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            double totalSpeed = entriesInTheSession.stream().mapToDouble(VehicleEntry::speedInKMPH).sum();
            double averageSpeed = 0;
            if (!entriesInTheSession.isEmpty())
                averageSpeed = totalSpeed/entriesInTheSession.size();
            sb.append(session).append(" | Average speed = ").append(averageSpeed).append('\n');
        }
        return sb.toString();
    }
}
