package com.example.pdp_meal.service.dailyMenu;

import com.example.pdp_meal.dto.dailyMenu.DailyMenuCreateDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuUpdateDto;
import com.example.pdp_meal.entity.DailyMenu;
import com.example.pdp_meal.mapper.DailyMenuMapper;
import com.example.pdp_meal.repository.DailyMenuRepository;
import com.example.pdp_meal.service.AbstractService;
import com.example.pdp_meal.service.GenericCrudService;
import com.example.pdp_meal.validator.dailyMenu.DailyMenuValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyMenuService extends AbstractService<DailyMenuRepository, DailyMenuMapper, DailyMenuValidator>
        implements GenericCrudService<DailyMenu, DailyMenuDto, DailyMenuCreateDto, DailyMenuUpdateDto, Integer> {

    protected DailyMenuService(DailyMenuRepository repository, DailyMenuMapper mapper,
                               DailyMenuValidator validator) {
        super(repository, mapper, validator);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public Integer create(DailyMenuCreateDto createDto) {
        DailyMenu dailyMenu = mapper.fromCreateDto(createDto);
        repository.save(dailyMenu);
        return dailyMenu.getId();
    }

    public void createDailyMenu(List<DailyMenuCreateDto> dailyMenus) {
        clearDailyMenu();
        for (DailyMenuCreateDto dailyMenu : dailyMenus) {
            create(dailyMenu);
        }
    }

    private void clearDailyMenu() {
        repository.clearDailyMenu();
    }

    @Override
    public Void delete(Integer id) {
        repository.deleteById(id);
        return null;
    }

    @Override
    public Void update(DailyMenuUpdateDto updateDto) {
        DailyMenu dailyMenu = mapper.fromUpdateDto(updateDto);
        repository.save(dailyMenu);
        return null;
    }

    @Override
    public List<DailyMenuDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public DailyMenuDto get(Integer id) {
        return mapper.toDto(repository.findById(id).get());
    }
}
