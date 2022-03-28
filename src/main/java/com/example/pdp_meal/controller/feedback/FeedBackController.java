package com.example.pdp_meal.controller.feedback;

import com.example.pdp_meal.controller.base.AbstractController;
import com.example.pdp_meal.dto.feedback.FeedBackCreateDto;
import com.example.pdp_meal.dto.feedback.FeedBackDto;
import com.example.pdp_meal.dto.feedback.FeedBackUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.service.fedback.FeedBackService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Panjiyev Javohir, чт 15:46. 03.03.2022
 */

@RestController
@RequestMapping("/api/feedback/*")

public class FeedBackController extends AbstractController<FeedBackService> {
    public FeedBackController(FeedBackService service) {
        super(service);
    }

    @PostMapping("create")
    public ResponseEntity<Integer> create(@RequestBody FeedBackCreateDto dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @GetMapping
    public HttpEntity<?> getFeedBacks() {
        List<FeedBackDto> feedBacks = service.getAll();
        return ResponseEntity.ok(feedBacks);

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFeedBack(@PathVariable Integer id) {
        try {
            FeedBackDto feedbackDto = service.get(id);
            return ResponseEntity.ok(feedbackDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedBack(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedBack(@PathVariable(name = "id") Integer id, @RequestBody FeedBackUpdateDto feedBackUpdateDto) {

        return new ResponseEntity<>(service.update(feedBackUpdateDto), HttpStatus.OK);


    }
}
