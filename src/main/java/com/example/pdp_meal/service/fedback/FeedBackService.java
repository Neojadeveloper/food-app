package com.example.pdp_meal.service.fedback;

import com.example.pdp_meal.dto.feedback.FeedBackCreateDto;
import com.example.pdp_meal.dto.feedback.FeedBackDto;
import com.example.pdp_meal.dto.feedback.FeedBackUpdateDto;
import com.example.pdp_meal.entity.FeedBack;
import com.example.pdp_meal.enums.FeedBackType;
import com.example.pdp_meal.mapper.FeedBackMapper;
import com.example.pdp_meal.repository.FeedBackRepository;
import com.example.pdp_meal.service.AbstractService;
import com.example.pdp_meal.service.GenericCrudService;
import com.example.pdp_meal.validator.feedback.FeedBackValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panjiyev Javohir, чт 14:47. 03.03.2022
 */
@Service
public class FeedBackService extends AbstractService<FeedBackRepository, FeedBackMapper, FeedBackValidator>
        implements GenericCrudService<FeedBack, FeedBackDto, FeedBackCreateDto, FeedBackUpdateDto, Integer> {


    protected FeedBackService(FeedBackRepository repository,
                              FeedBackMapper mapper,
                              FeedBackValidator validator) {
        super(repository, mapper, validator);

    }


    @Override
    public Integer create(FeedBackCreateDto createDto) {
        validator.validOnCreate(createDto);
        FeedBack feedBack = mapper.fromCreateDto(createDto);
        repository.save(feedBack);
        return feedBack.getId();
    }

    @Override
    public Void delete(Integer id) {
        repository.deleteById(id);
        return null;
    }

    @Override
    public Void update(FeedBackUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);
        FeedBack feedBack = mapper.fromUpdateDto(updateDto);
        repository.save(feedBack);
        return null;
    }

    @Override
    public List<FeedBackDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public FeedBackDto get(Integer id) {
        return mapper.toDto(repository.getById(id));
    }
}
