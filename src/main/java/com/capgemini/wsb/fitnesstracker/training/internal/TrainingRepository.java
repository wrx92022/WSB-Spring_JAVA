package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findTrainingsByUserId(Long userId);
    List<Training> findTrainingsByEndTimeAfter(LocalDateTime afterTime);
    List<Training> findTrainingsByActivityType(ActivityType activityType);
}