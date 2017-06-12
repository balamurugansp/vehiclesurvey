package com.aconex.model;

import com.aconex.constants.App;
import com.aconex.constants.Time;
import com.aconex.helpers.TimeParser;

import java.util.Date;

public class VehicleEntry {
    private final int frontAxleTime;
    private final int rearAxleTime;
    private final Direction direction;
    private int day;

    public Direction getDirection() {
        return direction;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public VehicleEntry(int frontAxleTime, int rearAxleTime, Direction direction, int day) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
        this.day = day;
    }

    public double speedInKMPH(){
        if (!isValid())
            return 0;
        double timeTakenToPassCounter = TimeParser.convertToHour(rearAxleTime - frontAxleTime);
        return App.LENGTH_OF_VEHICLE / timeTakenToPassCounter;
    }

    public Date entryTime(){
        return new Date((frontAxleTime + rearAxleTime)/2);
    }

    public boolean isValid(){
        return frontAxleTime >= 0
                && frontAxleTime < Time.MAX_MILLISECONDS_IN_A_DAY
                && rearAxleTime >= 0
                && rearAxleTime < Time.MAX_MILLISECONDS_IN_A_DAY
                && frontAxleTime < rearAxleTime;
    }

    @Override
    public String toString() {
        return "VehicleEntry{" +
                "frontAxleTime=" + frontAxleTime +
                ", rearAxleTime=" + rearAxleTime +
                ", direction=" + direction +
                '}';
    }
}
