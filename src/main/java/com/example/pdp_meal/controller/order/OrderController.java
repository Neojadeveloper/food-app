package com.example.pdp_meal.controller.order;


import com.example.pdp_meal.controller.base.AbstractController;
import com.example.pdp_meal.dto.order.OrderCreateDto;
import com.example.pdp_meal.dto.order.OrderDto;
import com.example.pdp_meal.dto.order.OrderUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.service.order.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController<OrderService> {


    public OrderController(OrderService service) {
        super( service );
    }

    @GetMapping
    public HttpEntity<?> getOrders() {
        List<OrderDto> orders = service.getAll();
        return ResponseEntity.ok( orders );

    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOrder(@PathVariable Integer id) {
        try {
            OrderDto orderDto = service.get( id );
            return ResponseEntity.ok( orderDto );
        } catch (NotFoundException e) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
        }
    }


    @PreAuthorize("hasAuthority('super_admin')")
    @PostMapping("/add")
    public ResponseEntity<Integer> addOrder(@RequestBody OrderCreateDto createDto){
        Integer id = service.create( createDto );
        return new ResponseEntity<>( id,HttpStatus.OK );

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id){
        service.delete( id );
        return new ResponseEntity<>( null,HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateOrder(@PathVariable(name = "id") Integer id,@RequestBody OrderUpdateDto orderUpdateDto){

        return new ResponseEntity<>(service.update(orderUpdateDto), HttpStatus.OK);


    }


}
