package com.fitness.challenge.api.repository;

import com.fitness.challenge.api.model.Challenge;
import com.fitness.challenge.api.model.ChallengeProgress;
import com.fitness.challenge.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeProgressRepository extends JpaRepository<ChallengeProgress, Long> {
    List<ChallengeProgress> findByUser(User user);
    List<ChallengeProgress> findByChallenge(Challenge challenge);
    Optional<ChallengeProgress> findByUserAndChallenge(User user, Challenge challenge);
    List<ChallengeProgress> findByIsCompleted(boolean isCompleted);
    boolean existsByUserAndChallenge(User user, Challenge challenge);
} 