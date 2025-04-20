package com.fitness.challlenge.api.repository;

import com.fitness.challlenge.api.model.Achievement;
import com.fitness.challlenge.api.model.User;
import com.fitness.challlenge.api.model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    List<UserAchievement> findByUser(User user);
    List<UserAchievement> findByAchievement(Achievement achievement);
    boolean existsByUserAndAchievement(User user, Achievement achievement);
} 