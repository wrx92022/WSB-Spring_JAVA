package com.capgemini.wsb.fitnesstracker.stat.api;

import jakarta.annotation.Nullable;

public record StatDto(@Nullable Long Id, int numberOfTrainings, int avgDistance, int difficultyOfTrainings){

}