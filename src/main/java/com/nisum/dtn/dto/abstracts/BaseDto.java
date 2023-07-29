package com.nisum.dtn.dto.abstracts;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseDto {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
}
