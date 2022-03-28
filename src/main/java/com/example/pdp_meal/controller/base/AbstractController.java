package com.example.pdp_meal.controller.base;
import com.example.pdp_meal.service.BaseService;


public abstract class AbstractController<S extends BaseService> {
    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }
}
