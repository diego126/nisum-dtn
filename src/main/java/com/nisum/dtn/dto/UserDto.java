package com.nisum.dtn.dto;

import com.nisum.dtn.dto.abstracts.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDto extends BaseDto {
    private String name;
    private String email;
    private String password;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private List<PhoneDto> phones;
}
