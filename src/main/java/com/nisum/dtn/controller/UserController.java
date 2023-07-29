package com.nisum.dtn.controller;

import com.nisum.dtn.dto.ErrorResponse;
import com.nisum.dtn.dto.UserDto;
import com.nisum.dtn.dto.UserRequest;
import com.nisum.dtn.exception.ConflictEmailException;
import com.nisum.dtn.exception.NotFoundUserException;
import com.nisum.dtn.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/users")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<UserDto>> getUsers(Pageable page){
        return new ResponseEntity<>(userService.getUsers(page), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getUser(@PathVariable UUID id){
        try{
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        }catch (NotFoundUserException e){
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@Valid @RequestBody UserRequest request){
        try{
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            return  new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
        }catch (ConflictEmailException e){
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(e.getMessage()));
        }
    }
}
