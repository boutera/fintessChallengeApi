package com.fitness.challlenge.api.service;

import com.fitness.challlenge.api.model.User;
import com.fitness.challlenge.api.model.Workout;
import com.fitness.challlenge.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public List<Workout> getWorkoutsByUser(User user) {
        return workoutRepository.findByUser(user);
    }

    public List<Workout> getWorkoutsByUserAndDateRange(User user, LocalDateTime startDate, LocalDateTime endDate) {
        return workoutRepository.findByUserAndWorkoutDateBetween(user, startDate, endDate);
    }

    public List<Workout> getWorkoutsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return workoutRepository.findByWorkoutDateBetween(startDate, endDate);
    }

    public Workout updateWorkout(Workout workout) {
        if (!workoutRepository.existsById(workout.getId())) {
            throw new RuntimeException("Workout not found");
        }
        return workoutRepository.save(workout);
    }

    public void deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException("Workout not found");
        }
        workoutRepository.deleteById(id);
    }
} 