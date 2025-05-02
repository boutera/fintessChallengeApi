package com.fitness.challenge.api.controller;

import com.fitness.challenge.api.model.Achievement;
import com.fitness.challenge.api.model.UserAchievement;
import com.fitness.challenge.api.service.AchievementService;
import com.fitness.challenge.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {
    private final AchievementService achievementService;
    private final UserService userService;

    @Autowired
    public AchievementController(AchievementService achievementService, UserService userService) {
        this.achievementService = achievementService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement) {
        Achievement createdAchievement = achievementService.createAchievement(achievement);
        return ResponseEntity.ok(createdAchievement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable Long id) {
        return achievementService.getAchievementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @GetMapping("/points/{points}")
    public ResponseEntity<List<Achievement>> getAchievementsByPoints(@PathVariable int points) {
        return ResponseEntity.ok(achievementService.getAchievementsByPoints(points));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable Long id, @RequestBody Achievement achievement) {
        achievement.setId(id);
        Achievement updatedAchievement = achievementService.updateAchievement(achievement);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/award/{achievementId}/user/{userId}")
    public ResponseEntity<UserAchievement> awardAchievement(@PathVariable Long achievementId, @PathVariable Long userId) {
        return achievementService.getAchievementById(achievementId)
                .flatMap(achievement -> userService.getUserById(userId)
                        .map(user -> achievementService.awardAchievement(user, achievement)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAchievement>> getUserAchievements(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(achievementService::getUserAchievements)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{achievementId}/users")
    public ResponseEntity<List<UserAchievement>> getAchievementUsers(@PathVariable Long achievementId) {
        return achievementService.getAchievementById(achievementId)
                .map(achievementService::getAchievementUsers)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 