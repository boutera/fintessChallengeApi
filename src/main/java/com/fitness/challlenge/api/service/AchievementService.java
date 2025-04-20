package com.fitness.challlenge.api.service;

import com.fitness.challlenge.api.model.Achievement;
import com.fitness.challlenge.api.model.User;
import com.fitness.challlenge.api.model.UserAchievement;
import com.fitness.challlenge.api.repository.AchievementRepository;
import com.fitness.challlenge.api.repository.UserAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository, UserAchievementRepository userAchievementRepository) {
        this.achievementRepository = achievementRepository;
        this.userAchievementRepository = userAchievementRepository;
    }

    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getAchievementsByPoints(int points) {
        return achievementRepository.findByPointsGreaterThanEqual(points);
    }

    public Achievement updateAchievement(Achievement achievement) {
        if (!achievementRepository.existsById(achievement.getId())) {
            throw new RuntimeException("Achievement not found");
        }
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        if (!achievementRepository.existsById(id)) {
            throw new RuntimeException("Achievement not found");
        }
        achievementRepository.deleteById(id);
    }

    public UserAchievement awardAchievement(User user, Achievement achievement) {
        if (userAchievementRepository.existsByUserAndAchievement(user, achievement)) {
            throw new RuntimeException("User already has this achievement");
        }

        UserAchievement userAchievement = new UserAchievement();
        userAchievement.setUser(user);
        userAchievement.setAchievement(achievement);

        return userAchievementRepository.save(userAchievement);
    }

    public List<UserAchievement> getUserAchievements(User user) {
        return userAchievementRepository.findByUser(user);
    }

    public List<UserAchievement> getAchievementUsers(Achievement achievement) {
        return userAchievementRepository.findByAchievement(achievement);
    }
} 