package com.capgemini.wsb.fitnesstracker.stat.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StatProvider {

    Optional<User> getStat(Long statId);

    void deleteUserStatByUserId(Long userId);

    public List<Stat> findAllStats();

    public List<Stat> findAllStatsForUser(Long userId);
}
