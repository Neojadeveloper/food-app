package com.example.pdp_meal.controller.meal;

import com.example.pdp_meal.controller.base.AbstractController;
import com.example.pdp_meal.dto.meal.MealCreateDto;
import com.example.pdp_meal.dto.meal.MealDto;
import com.example.pdp_meal.dto.meal.MealUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.service.meal.MealService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bekpulatov Shoxruh, Fri 3:24 PM. 3/4/2022
 */
@Controller
@RequestMapping("/api/meal")
public class MealController extends AbstractController<MealService> {

    public MealController(MealService service) {
        super(service);
    }

    @GetMapping
    public HttpEntity<?> getMeals() {
        List<MealDto> meals = service.getAll();
        return ResponseEntity.ok(meals);

    }


    @GetMapping("/{id}")
    public HttpEntity<?> getMeal(@PathVariable Integer id) {
        try {
            MealDto mealDto = service.get(id);
            return ResponseEntity.ok(mealDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Integer> addMeal(@RequestBody MealCreateDto createDto) {
        Integer id = service.create(createDto);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeal(@PathVariable(name = "id") Integer id, @RequestBody MealUpdateDto mealUpdateDto) {

        return new ResponseEntity<>(service.update(mealUpdateDto), HttpStatus.OK);


    }
}
