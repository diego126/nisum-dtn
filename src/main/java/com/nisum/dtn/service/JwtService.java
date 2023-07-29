package com.nisum.dtn.service;

import com.nisum.dtn.model.User;

public interface JwtService {

    String getUsername(String token);
    String generateToken(User user);
}
