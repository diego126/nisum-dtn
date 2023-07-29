package com.nisum.dtn.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneRequest {
    private String number;
    private String cityCode;
    private String countryCode;
}
