package com.nisum.dtn.service;

import com.nisum.dtn.dto.UserDto;
import com.nisum.dtn.dto.UserRequest;
import com.nisum.dtn.exception.ConflictEmailException;
import com.nisum.dtn.exception.NotFoundUserException;
import com.nisum.dtn.mapper.UserMapper;
import com.nisum.dtn.model.User;
import com.nisum.dtn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private static final UserMapper mapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Page<UserDto> getUsers(Pageable page) {
        return userRepository.findAll(page)
                .map(mapper::toDto);
    }

    @Override
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException("Username not found: " + id));
        return mapper.toDto(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findFirstByEmail(username)
                .orElseThrow(() -> new NotFoundUserException("Username not found: " + username));
    }

    @Override
    public UserDto register(UserRequest request){
        Optional<User> savedUser = userRepository.findFirstByEmail(request.getEmail());
        if(savedUser.isPresent()){
            throw new ConflictEmailException("User already registered");
        }

        User model = mapper.toModel(request);
        model.setLastLogin(model.getCreated());
        model.setActive(true);
        model.setToken(jwtService.generateToken(model));

        return mapper.toDto(userRepository.save(model));
    }
}
