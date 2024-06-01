package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    Optional<User> getTraining(Long trainingId);

    void deleteUserTrainingByUserId(Long userId);

    public List<Training> findAllTrainings();

    public List<Training> findAllTrainingsForUser(Long userId);

    public List<Training> findAllFinishedTrainingsAfter(LocalDateTime afterTime);

    public List<Training> findAllTrainingsByActivityType(ActivityType activityType);

    public Training createTraining(TrainingDto trainingDTO);

    public Training updateTraining(Long trainingId, Training updatedTraining);
}