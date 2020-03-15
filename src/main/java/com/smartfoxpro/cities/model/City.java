package com.smartfoxpro.cities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name;
    Boolean ifPlayed;
}
