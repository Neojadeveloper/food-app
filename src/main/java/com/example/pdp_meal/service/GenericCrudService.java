package com.example.pdp_meal.service;


import com.example.pdp_meal.dto.BaseDto;
import com.example.pdp_meal.dto.GenericDto;
import com.example.pdp_meal.entity.BaseEntity;

import java.io.Serializable;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto,
        K extends Serializable> extends GenericService<D, K> {

    K create(CD createDto);

    Void delete(K id);

    Void update(UD updateDto);

}
