package com.example.pdp_meal.service;


import com.example.pdp_meal.dto.GenericDto;

import java.io.Serializable;
import java.util.List;


/**
 * @param <D> -> Dto
 * @param <K> -> class that defines the primary key for your pojo class
 */
public interface GenericService<
        D extends GenericDto,
        K extends Serializable> extends BaseService {

    List<D> getAll();

    D get(K id);

}
