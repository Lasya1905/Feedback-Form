package com.example.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.feedback.model.Feedback;
import com.example.feedback.repository.FeedbackRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository repo;

    @PostMapping
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return repo.save(feedback);
    }

    @GetMapping
    public List<Feedback> getAll() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Feedback update(@PathVariable Long id, @RequestBody Feedback feedback) {
        return repo.findById(id).map(f -> {
            f.setStudentName(feedback.getStudentName());
            f.setCourse(feedback.getCourse());
            f.setRating(feedback.getRating());
            f.setComments(feedback.getComments());
            return repo.save(f);
        }).orElse(null);
    }


    @GetMapping("/search")
    public List<Feedback> search(@RequestParam String course) {
        return repo.findByCourseContainingIgnoreCase(course);
    }

    @GetMapping("/search/name")
    public List<Feedback> searchByName(@RequestParam String name) {
        return repo.findByStudentNameContainingIgnoreCase(name);
    }

    @GetMapping("/sort")
    public List<Feedback> sort(@RequestParam String field) {
        return repo.findAll(org.springframework.data.domain.Sort.by(field));
    }

    @GetMapping("/page")
    public List<Feedback> getPage(@RequestParam int page, @RequestParam int size) {
        return repo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }


}
