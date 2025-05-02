package com.fitness.challenge.api.repository;

import com.fitness.challenge.api.model.Challenge;
import com.fitness.challenge.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByCreatedBy(User user);
    List<Challenge> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);
    List<Challenge> findByType(Challenge.ChallengeType type);
} 