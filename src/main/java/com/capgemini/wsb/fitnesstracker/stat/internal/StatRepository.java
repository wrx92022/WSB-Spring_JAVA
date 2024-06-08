package com.capgemini.wsb.fitnesstracker.stat.internal;

import com.capgemini.wsb.fitnesstracker.stat.api.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findStatsByUserId(Long userId);
    }