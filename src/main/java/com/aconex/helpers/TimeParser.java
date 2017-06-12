package com.aconex.helpers;

import com.aconex.constants.Time;

public class TimeParser {

    public static int parseMilliSecondFromInput(String input){
        try {
            int milliSeconds = Integer.parseInt(input.substring(1));
            if (milliSeconds < 0 )
                return -1;
            return milliSeconds;
        }catch (NumberFormatException e){
            System.out.println("Error parsing time from : " + input);
        }
        return -1;
    }

    public static double convertToHour(int milliseconds){
        if (milliseconds <0 )
            return -1;
        double minutesInHour = Time.MINUTES_IN_A_HOUR;
        double secondsInMinute = Time.SECONDS_IN_A_MINUTE;
        double milliSecondsInSecond = Time.MILLISECONDS_IN_A_SECOND;
        return ((double)milliseconds)/(milliSecondsInSecond * secondsInMinute * minutesInHour);
    }
}
