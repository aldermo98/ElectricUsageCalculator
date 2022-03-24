package edu.csus.csc131.euc.store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManualInputInfo implements UsageInfo {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double elecUsage;

    public ManualInputInfo(LocalDateTime startTime, LocalDateTime endTime, double elecUsage) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.elecUsage = elecUsage;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getElecUsage() {
        return elecUsage;
    }

    public void setElecUsage(double elecUsage) {
        this.elecUsage = elecUsage;
    }

    public String toString() {
        //Get current date time
    	//System.out.println(startTime.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YY hh a");

        String strStartTime = startTime.format(formatter);
        String strEndTime = endTime.format(formatter);
        //System.out.println(startTime.toString());
        return "Starting Interval: " + strStartTime + "\nEnding Interval: " + strEndTime + "\nElectrical Usage: " + elecUsage + " KWH";
    }
}
