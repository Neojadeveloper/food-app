package com.example.pdp_meal.service.meal;

import com.example.pdp_meal.dto.meal.MealCreateDto;
import com.example.pdp_meal.dto.meal.MealDto;
import com.example.pdp_meal.dto.meal.MealUpdateDto;
import com.example.pdp_meal.entity.Meal;
import com.example.pdp_meal.mapper.MealMapper;
import com.example.pdp_meal.repository.MealRepository;
import com.example.pdp_meal.service.AbstractService;
import com.example.pdp_meal.service.GenericCrudService;
import com.example.pdp_meal.service.file.FileStorageService;
import com.example.pdp_meal.validator.meal.MealValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.pdp_meal.FileStorageUtils.UPLOAD_DIRECTORY;

/**
 * @author Bekpulatov Shoxruh, Wed 11:15 PM. 3/2/2022
 */

@Service
public class MealService extends AbstractService<MealRepository, MealMapper, MealValidator>
        implements GenericCrudService<Meal, MealDto, MealCreateDto, MealUpdateDto, Integer> {

    private final FileStorageService fileStorageService;

    protected MealService(
            MealRepository repository,
            MealMapper mapper,
            MealValidator validator, FileStorageService fileStorageService) {
        super(repository, mapper, validator);
        this.fileStorageService = fileStorageService;
    }


//    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public Integer create(MealCreateDto createDto) {
//        MultipartFile file = createDto.getPathPhoto();
        Meal meal = mapper.fromCreateDto(createDto);
//        String store = fileStorageService.store(file);
//        meal.setPathPhoto(UPLOAD_DIRECTORY + store);
        return repository.save(meal).getId();
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public Void delete(Integer id) {
        repository.deleteById(id);
        return null;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public Void update(MealUpdateDto updateDto) {
//        MultipartFile file = updateDto.getPathPhoto();
        Meal meal = mapper.fromUpdateDto(updateDto);
//        String store = fileStorageService.store(file);
//        meal.setPathPhoto(UPLOAD_DIRECTORY + store);
        repository.save(meal);
        return null;
    }

    @Override
    public List<MealDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public MealDto get(Integer id) {
        return mapper.toDto(repository.findById(id).get());
    }


}
