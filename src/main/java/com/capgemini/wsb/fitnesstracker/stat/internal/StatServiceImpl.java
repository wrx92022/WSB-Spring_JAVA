package com.capgemini.wsb.fitnesstracker.stat.internal;

import com.capgemini.wsb.fitnesstracker.stat.api.Stat;
import com.capgemini.wsb.fitnesstracker.stat.api.StatProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class StatServiceImpl implements StatProvider {

    @Autowired
    private final StatRepository statRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getStat(Long statId) {
        return Optional.empty();
    }

    @Override
    public void deleteUserStatByUserId(Long userId) {
        Optional<Stat> first = statRepository.findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .findFirst();
        first.ifPresent(statRepository::delete);
    }

    @Override
    public List<Stat> findAllStats() {
        return statRepository.findAll();
    }

    @Override
    public List<Stat> findAllStatsForUser(Long userId) {
        return statRepository.findStatsByUserId(userId);
    }







}