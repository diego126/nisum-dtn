package com.nisum.dtn.service;

import com.nisum.dtn.dto.UserDto;
import com.nisum.dtn.dto.UserRequest;
import com.nisum.dtn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserDto getById(UUID id);
    User getByUsername(String username);
    Page<UserDto> getUsers(Pageable page);
    UserDto register(UserRequest request);
}
