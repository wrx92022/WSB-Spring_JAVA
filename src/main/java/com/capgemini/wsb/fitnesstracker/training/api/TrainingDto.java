package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.util.Date;

public record TrainingDto(Long userId, Date startTime, Date endTime,
                          ActivityType activityType, double distance,
                          double averageSpeed) {

}