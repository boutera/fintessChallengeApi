package com.fitness.challenge.api.controller;

import com.fitness.challenge.api.model.Challenge;
import com.fitness.challenge.api.model.ChallengeProgress;
import com.fitness.challenge.api.service.ChallengeService;
import com.fitness.challenge.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;
    private final UserService userService;

    @Autowired
    public ChallengeController(ChallengeService challengeService, UserService userService) {
        this.challengeService = challengeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        Challenge createdChallenge = challengeService.createChallenge(challenge);
        return ResponseEntity.ok(createdChallenge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Challenge>> getActiveChallenges() {
        return ResponseEntity.ok(challengeService.getActiveChallenges());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Challenge>> getChallengesByType(@PathVariable Challenge.ChallengeType type) {
        return ResponseEntity.ok(challengeService.getChallengesByType(type));
    }

    @GetMapping("/creator/{userId}")
    public ResponseEntity<List<Challenge>> getChallengesByCreator(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(challengeService::getChallengesByCreator)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody Challenge challenge) {
        challenge.setId(id);
        Challenge updatedChallenge = challengeService.updateChallenge(challenge);
        return ResponseEntity.ok(updatedChallenge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{challengeId}/join/{userId}")
    public ResponseEntity<ChallengeProgress> joinChallenge(@PathVariable Long challengeId, @PathVariable Long userId) {
        return challengeService.getChallengeById(challengeId)
                .flatMap(challenge -> userService.getUserById(userId)
                        .map(user -> challengeService.joinChallenge(user, challenge)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/progress/{id}")
    public ResponseEntity<ChallengeProgress> updateChallengeProgress(@PathVariable Long id, @RequestBody ChallengeProgress progress) {
        progress.setId(id);
        ChallengeProgress updatedProgress = challengeService.updateChallengeProgress(progress);
        return ResponseEntity.ok(updatedProgress);
    }
} 