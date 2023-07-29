package com.nisum.dtn.service;

import com.nisum.dtn.dto.PhoneRequest;
import com.nisum.dtn.dto.UserDto;
import com.nisum.dtn.dto.UserRequest;
import com.nisum.dtn.exception.ConflictEmailException;
import com.nisum.dtn.mapper.UserMapper;
import com.nisum.dtn.model.User;
import com.nisum.dtn.repository.UserRepository;
import jakarta.annotation.security.RunAs;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final UserMapper mapper = UserMapper.INSTANCE;
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void getUsers() {
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(userRepository.findAll(pageable))
                .thenReturn(Page.empty());

        Page<UserDto> response = service.getUsers(pageable);

        Assertions.assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
    }

    @Test
    void register() {
        var request = UserRequest.builder()
                .name("Diego Tarazona")
                .email("diego@nisum.cl")
                .password("abc12")
                .phones(
                        List.of(
                            PhoneRequest.builder()
                                    .number("940144890")
                                    .cityCode("1")
                                    .countryCode("+51")
                                    .build()
                        )
                )
                .build();

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(
                        mapper.toModel(request)
                );

        Mockito.when(jwtService.generateToken(ArgumentMatchers.any()))
                .thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWVnb0Bkb21pbmlvLmNsIiwiaWF0IjoxNjkwNTY5NDI4LCJleHAiOjE2OTA1NzA4Njh9.AZ6cOpKyXSfCjUQNUm8cznCcRIs0ECKYV3MntfhCEUI");

        UserDto response = service.register(request);

        Assertions.assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("diego@nisum.cl", response.getEmail());
    }

    @Test
    void shouldReturnAConflictEmailException(){
        var request = UserRequest.builder()
                .name("Diego Tarazona")
                .email("diego@nisum.cl")
                .password("abc12")
                .phones(
                        List.of(
                                PhoneRequest.builder()
                                        .number("940144890")
                                        .cityCode("1")
                                        .countryCode("+51")
                                        .build()
                        )
                )
                .build();

        Mockito.when(userRepository.findFirstByEmail(ArgumentMatchers.any()))
                .thenReturn(
                        Optional.of(mapper.toModel(request))
                );

        ConflictEmailException thrown = Assertions.assertThrows(ConflictEmailException.class, () -> {
            service.register(request);
        });

        Assertions.assertEquals("User already registered", thrown.getMessage());
    }
}