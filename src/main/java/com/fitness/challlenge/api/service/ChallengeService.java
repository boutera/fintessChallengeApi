package com.fitness.challlenge.api.service;

import com.fitness.challlenge.api.model.Challenge;
import com.fitness.challlenge.api.model.ChallengeProgress;
import com.fitness.challlenge.api.model.User;
import com.fitness.challlenge.api.repository.ChallengeRepository;
import com.fitness.challlenge.api.repository.ChallengeProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeProgressRepository challengeProgressRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository, ChallengeProgressRepository challengeProgressRepository) {
        this.challengeRepository = challengeRepository;
        this.challengeProgressRepository = challengeProgressRepository;
    }

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Optional<Challenge> getChallengeById(Long id) {
        return challengeRepository.findById(id);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public List<Challenge> getActiveChallenges() {
        LocalDateTime now = LocalDateTime.now();
        return challengeRepository.findByStartDateBeforeAndEndDateAfter(now, now);
    }

    public List<Challenge> getChallengesByType(Challenge.ChallengeType type) {
        return challengeRepository.findByType(type);
    }

    public List<Challenge> getChallengesByCreator(User user) {
        return challengeRepository.findByCreatedBy(user);
    }

    public Challenge updateChallenge(Challenge challenge) {
        if (!challengeRepository.existsById(challenge.getId())) {
            throw new RuntimeException("Challenge not found");
        }
        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long id) {
        if (!challengeRepository.existsById(id)) {
            throw new RuntimeException("Challenge not found");
        }
        challengeRepository.deleteById(id);
    }

    public ChallengeProgress joinChallenge(User user, Challenge challenge) {
        if (challengeProgressRepository.existsByUserAndChallenge(user, challenge)) {
            throw new RuntimeException("User already joined this challenge");
        }

        ChallengeProgress progress = new ChallengeProgress();
        progress.setUser(user);
        progress.setChallenge(challenge);
        progress.setCurrentValue(0.0);
        progress.setCompleted(false);

        return challengeProgressRepository.save(progress);
    }

    public ChallengeProgress updateChallengeProgress(ChallengeProgress progress) {
        if (!challengeProgressRepository.existsById(progress.getId())) {
            throw new RuntimeException("Challenge progress not found");
        }

        Challenge challenge = progress.getChallenge();
        if (progress.getCurrentValue() >= challenge.getTargetValue()) {
            progress.setCompleted(true);
        }

        return challengeProgressRepository.save(progress);
    }
} 