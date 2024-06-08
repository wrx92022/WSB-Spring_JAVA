package com.capgemini.wsb.fitnesstracker.stat.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stats")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Setter
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "numberoftrainings", nullable = false)
    private int numberOfTrainings;

    @Column(name = "avgdistance", nullable = false)
    private int avgDistance;

    @Column(name = "difficulty", nullable = false)
    private int difficultyOfTrainings;

    public Stat(
            final User user,
            final int numberOfTrainings,
            final int avgDistance,
            final int difficultyOfTrainings) {

        this.user = user;
        this.numberOfTrainings = numberOfTrainings;
        this.avgDistance = avgDistance;
        this.difficultyOfTrainings = difficultyOfTrainings;
    }

}