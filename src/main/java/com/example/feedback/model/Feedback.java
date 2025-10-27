package com.example.feedback.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String course;
    private int rating;
    private String comments;
}
