package com.fitness.challlenge.api.repository;

import com.fitness.challlenge.api.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByPointsGreaterThanEqual(int points);
} 