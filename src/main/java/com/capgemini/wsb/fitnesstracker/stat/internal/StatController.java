package com.capgemini.wsb.fitnesstracker.stat.internal;

import com.capgemini.wsb.fitnesstracker.stat.api.Stat;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/stats")
@RequiredArgsConstructor
@Slf4j
public class StatController {

    @Autowired
    private StatServiceImpl statService;

    @GetMapping
    public ResponseEntity<List<Stat>> findAllStats() {
        return ResponseEntity.ok(statService.findAllStats());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Stat>> findAllStatsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(statService.findAllStatsForUser(userId));
    }
}