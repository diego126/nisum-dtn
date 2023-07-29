package com.nisum.dtn.model.abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(updatable = false)
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;
}
