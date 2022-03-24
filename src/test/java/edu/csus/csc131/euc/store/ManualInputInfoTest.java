package edu.csus.csc131.euc.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ManualInputInfoTest {

    @Test
    void add() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YY hh a");
        LocalDateTime start = LocalDateTime.of(2020, 3, 1, 1, 0);
        LocalDateTime end = LocalDateTime.of(2020, 3, 1, 2, 0);
        double elecRate = 0.1;
        String strStartTime = start.format(formatter);
        String strEndTime = end.format(formatter);
        ManualInputInfo euc = new ManualInputInfo(start, start.plusHours(1), 0.1);
        String testString = "Starting Interval: " + strStartTime + "\nEnding Interval: " + strEndTime + "\nElectrical Usage: " + elecRate + " KWH";
        String finalString = euc.toString();
        assertTrue(finalString.equals(testString));

    }
}