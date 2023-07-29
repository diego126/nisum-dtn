package com.nisum.dtn.dto;

import com.nisum.dtn.validator.CustomRegex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "The name is mandatory and must not be empty")
    private String name;
    @NotBlank(message = "The email is mandatory and must not be empty")
    @Email(message = "The email is not in a valid format")
    private String email;
    @NotBlank(message = "The password is mandatory and must not be empty")
    @CustomRegex
    private String password;
    private List<PhoneRequest> phones;
}
