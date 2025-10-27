package com.example.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.feedback.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByCourseIgnoreCase(String course);
    List<Feedback> findByCourseContainingIgnoreCase(String course);
    List<Feedback> findByStudentNameContainingIgnoreCase(String name);
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.course = ?1")
    Double averageRatingByCourse(String course);
}


