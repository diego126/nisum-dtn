package com.nisum.dtn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    private UUID id = UUID.randomUUID();
    private String number;
    private String cityCode;
    private String countryCode;
}
