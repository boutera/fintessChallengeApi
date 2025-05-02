package com.fitness.challenge.api.config;

import com.fitness.challenge.api.model.*;
import com.fitness.challenge.api.repository.AchievementRepository;
import com.fitness.challenge.api.repository.ChallengeRepository;
import com.fitness.challenge.api.repository.UserRepository;
import com.fitness.challenge.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (workoutRepository.count() == 0) {
            // Initialize Users
            User john = userRepository.findByUsername("johnsmith")
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("johnsmith");
                    newUser.setEmail("john.smith@example.com");
                    newUser.setPassword(passwordEncoder.encode("password123"));
                    newUser.setFirstName("John");
                    newUser.setLastName("Smith");
                    newUser.setRole(User.UserRole.USER);
                    return userRepository.save(newUser);
                });

            User sarah = userRepository.findByUsername("sarahjohnson")
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("sarahjohnson");
                    newUser.setEmail("sarah.johnson@example.com");
                    newUser.setPassword(passwordEncoder.encode("password123"));
                    newUser.setFirstName("Sarah");
                    newUser.setLastName("Johnson");
                    newUser.setRole(User.UserRole.USER);
                    return userRepository.save(newUser);
                });

            User emma = userRepository.findByUsername("emmadavis")
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("emmadavis");
                    newUser.setEmail("emma.davis@example.com");
                    newUser.setPassword(passwordEncoder.encode("password123"));
                    newUser.setFirstName("Emma");
                    newUser.setLastName("Davis");
                    newUser.setRole(User.UserRole.USER);
                    return userRepository.save(newUser);
                });

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
            Workout fullBodyWorkout = new Workout();
            fullBodyWorkout.setTitle("Full Body Workout");
            fullBodyWorkout.setTrainer("John Smith");
            fullBodyWorkout.setDuration("45 min");
            fullBodyWorkout.setDifficulty("Intermediate");
            fullBodyWorkout.setCalories(350);
            fullBodyWorkout.setDescription("A comprehensive full-body workout targeting all major muscle groups");
            fullBodyWorkout.setEquipment(Arrays.asList("Dumbbells", "Yoga mat"));
            fullBodyWorkout.setImage("full-body-workout.jpg");
            fullBodyWorkout.setWorkoutDate(LocalDateTime.now());
            fullBodyWorkout.setUser(john);
            workoutRepository.save(fullBodyWorkout);

            Workout hiitCardio = new Workout();
            hiitCardio.setTitle("HIIT Cardio");
            hiitCardio.setTrainer("Sarah Johnson");
            hiitCardio.setDuration("30 min");
            hiitCardio.setDifficulty("Advanced");
            hiitCardio.setCalories(450);
            hiitCardio.setDescription("High-intensity interval training to boost cardio and burn calories");
            hiitCardio.setEquipment(Arrays.asList("None"));
            hiitCardio.setImage("hiit-cardio.jpg");
            hiitCardio.setWorkoutDate(LocalDateTime.now());
            hiitCardio.setUser(sarah);
            workoutRepository.save(hiitCardio);

            Workout yogaFlow = new Workout();
            yogaFlow.setTitle("Yoga Flow");
            yogaFlow.setTrainer("Emma Davis");
            yogaFlow.setDuration("60 min");
            yogaFlow.setDifficulty("Beginner");
            yogaFlow.setCalories(175);
            yogaFlow.setDescription("Relaxing yoga flow focusing on flexibility and mindfulness");
            yogaFlow.setEquipment(Arrays.asList("Yoga mat"));
            yogaFlow.setImage("yoga-flow.jpg");
            yogaFlow.setWorkoutDate(LocalDateTime.now());
            yogaFlow.setUser(emma);
            workoutRepository.save(yogaFlow);
        }
    }
} 