package com.fitness.challlenge.api.config;

import com.fitness.challlenge.api.model.*;
import com.fitness.challlenge.api.repository.AchievementRepository;
import com.fitness.challlenge.api.repository.ChallengeRepository;
import com.fitness.challlenge.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public void run(String... args) {
        // Initialize Challenges
        Challenge stepChallenge = new Challenge();
        stepChallenge.setName("30-Day Step Challenge");
        stepChallenge.setDescription("Walk 10,000 steps every day for 30 days");
        stepChallenge.setStartDate(LocalDateTime.now());
        stepChallenge.setEndDate(LocalDateTime.now().plusDays(30));
        stepChallenge.setType(Challenge.ChallengeType.STEPS);
        stepChallenge.setTargetValue(10000.0);
        stepChallenge.setUnit("steps");

        Challenge distanceChallenge = new Challenge();
        distanceChallenge.setName("Summer Distance Challenge");
        distanceChallenge.setDescription("Run or walk 100km in 2 months");
        distanceChallenge.setStartDate(LocalDateTime.now());
        distanceChallenge.setEndDate(LocalDateTime.now().plusMonths(2));
        distanceChallenge.setType(Challenge.ChallengeType.DISTANCE);
        distanceChallenge.setTargetValue(100.0);
        distanceChallenge.setUnit("km");

        challengeRepository.saveAll(Arrays.asList(stepChallenge, distanceChallenge));

        // Initialize Achievements
        Achievement firstWorkout = new Achievement();
        firstWorkout.setName("First Workout");
        firstWorkout.setDescription("Complete your first workout");
        firstWorkout.setIconUrl("/icons/first-workout.png");
        firstWorkout.setPoints(10);

        Achievement consistentWeek = new Achievement();
        consistentWeek.setName("Consistent Week");
        consistentWeek.setDescription("Work out for 7 consecutive days");
        consistentWeek.setIconUrl("/icons/consistent-week.png");
        consistentWeek.setPoints(50);

        Achievement stepMaster = new Achievement();
        stepMaster.setName("Step Master");
        stepMaster.setDescription("Reach 10,000 steps in a day");
        stepMaster.setIconUrl("/icons/step-master.png");
        stepMaster.setPoints(30);

        achievementRepository.saveAll(Arrays.asList(firstWorkout, consistentWeek, stepMaster));

        // Initialize Workouts
        Workout beginnerCardio = new Workout();
        beginnerCardio.setName("Beginner Cardio");
        beginnerCardio.setDescription("30-minute beginner-friendly cardio workout");
        beginnerCardio.setDurationMinutes(30);
        beginnerCardio.setCaloriesBurned(200.0);

        Workout fullBodyStrength = new Workout();
        fullBodyStrength.setName("Full Body Strength");
        fullBodyStrength.setDescription("45-minute full body strength training");
        fullBodyStrength.setDurationMinutes(45);
        fullBodyStrength.setCaloriesBurned(300.0);

        Workout yogaFlow = new Workout();
        yogaFlow.setName("Yoga Flow");
        yogaFlow.setDescription("60-minute yoga session for flexibility and strength");
        yogaFlow.setDurationMinutes(60);
        yogaFlow.setCaloriesBurned(250.0);

        workoutRepository.saveAll(Arrays.asList(beginnerCardio, fullBodyStrength, yogaFlow));
    }
} 