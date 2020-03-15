package com.smartfoxpro.cities.repository;

import com.smartfoxpro.cities.model.City;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findTopByNameIsStartingWithAndIfPlayedFalse(String firstLetter);

    Optional<City> findByNameAndIfPlayedIsTrue(String cityName);

    Optional<City> findByNameAndIfPlayedIsFalse(String cityName);

    List<City> findAllByIfPlayedIsTrue();
}
