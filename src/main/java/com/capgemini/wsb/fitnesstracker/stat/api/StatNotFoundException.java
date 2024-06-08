package com.capgemini.wsb.fitnesstracker.stat.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Stat} was not found.
 */
@SuppressWarnings("squid:S110")
public class StatNotFoundException extends NotFoundException {

    private StatNotFoundException(String message) {
        super(message);
    }

    public StatNotFoundException(Long id) {
        this("Stats with ID=%s was not found".formatted(id));
    }

}
