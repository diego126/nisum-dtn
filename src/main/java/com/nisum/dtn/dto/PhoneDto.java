package com.nisum.dtn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class PhoneDto {
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;
}
