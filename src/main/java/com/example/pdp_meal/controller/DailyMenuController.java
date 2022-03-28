package com.example.pdp_meal.controller;

import com.example.pdp_meal.controller.base.AbstractController;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuCreateDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.service.dailyMenu.DailyMenuService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily")
public class DailyMenuController extends AbstractController<DailyMenuService> {

    public DailyMenuController(DailyMenuService service) {
        super(service);
    }

    @GetMapping
    public HttpEntity<?> getDailyMenus() {
        List<DailyMenuDto> dailyMenus = service.getAll();
        return ResponseEntity.ok( dailyMenus );

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDailyMenu(@PathVariable Integer id) {
        try {
            DailyMenuDto dailyMenuDto = service.get( id );
            return ResponseEntity.ok( dailyMenuDto );
        } catch (NotFoundException e) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
        }
    }
    @PostMapping
    public ResponseEntity<Integer> addDailyMenu(@RequestBody DailyMenuCreateDto createDto){
        Integer id = service.create( createDto );
        return new ResponseEntity<>( id,HttpStatus.OK );

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDailyMenu(@PathVariable Integer id){
        service.delete( id );
        return new ResponseEntity<>( null,HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateDailyMenu(@PathVariable(name = "id") Integer id,@RequestBody DailyMenuUpdateDto dailyMenuUpdateDto){
        return new ResponseEntity<>(service.update(dailyMenuUpdateDto), HttpStatus.OK);
    }


}
