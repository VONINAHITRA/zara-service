package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.FeedbackDTO;
import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.createFeedback(feedbackDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FeedbackDTO>>getFeedbackById(@PathVariable Long id){
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @GetMapping("/consultantID/{id}")
    public ResponseEntity<List<FeedbackDTO>>getAllFeedbackByConsultantId(@PathVariable Long id){
        return ResponseEntity.ok(feedbackService.getAllFeedbackByConsultantId(id));
    }

    @GetMapping("/clientID/{id}")
    public ResponseEntity<List<FeedbackDTO>>getAllFeedbackByClientId(@PathVariable Long id){
        return ResponseEntity.ok(feedbackService.getAllFeedbackByClientId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO>updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO){
        return ResponseEntity.ok(feedbackService.updateFeedback(id, feedbackDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable Long id){
        return ResponseEntity.ok(feedbackService.deleteFeedback(id));
    }
}
