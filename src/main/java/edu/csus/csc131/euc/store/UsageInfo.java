package edu.csus.csc131.euc.store;

import java.time.LocalDateTime;

public interface UsageInfo {
    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    double getElecUsage();

    String toString();
}
