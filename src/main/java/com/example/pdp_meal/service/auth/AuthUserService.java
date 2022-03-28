package com.example.pdp_meal.service.auth;

import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.dto.auth.AuthUserDto;
import com.example.pdp_meal.dto.auth.AuthUserUpdateDto;
import com.example.pdp_meal.entity.AuthUser;
import com.example.pdp_meal.mapper.auth.AuthUserMapper;
import com.example.pdp_meal.repository.AuthUserRepository;
import com.example.pdp_meal.service.AbstractService;
import com.example.pdp_meal.service.GenericCrudService;
import com.example.pdp_meal.validator.auth.AuthUserValidator;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthUserService extends AbstractService<AuthUserRepository, AuthUserMapper, AuthUserValidator> implements GenericCrudService<AuthUser, AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto, Integer>, UserDetailsService {
    protected AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Integer create(AuthUserCreateDto createDto) throws RuntimeException {
        try {
            validator.validOnCreate(createDto);
            AuthUser authUser = mapper.fromCreateDto(createDto);
            return repository.save(authUser).getId();
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Void delete(Integer id) {
        AuthUser authUser = repository.getById(id);
        repository.delete(authUser);
        return null;
    }

    @Override
    public Void update(AuthUserUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);
        AuthUser authUser = mapper.fromUpdateDto(updateDto);
        repository.save(authUser);
        return null;
    }

    @Override
    public List<AuthUserDto> getAll() throws RuntimeException {
        List<AuthUser> authUsers = repository.findAll();
        return mapper.toDto(authUsers);
    }

    @Override
    public AuthUserDto get(Integer id) throws RuntimeException {
        AuthUser authUser = repository.getById(id);
        return mapper.toDto(authUser);
    }

    public AuthUserDto getByChatId(String id) throws RuntimeException {
        AuthUser authUser = repository.findByChatId(id);
        return mapper.toDto(authUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole()))
                .build();
    }
}
