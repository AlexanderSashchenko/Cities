package com.smartfoxpro.cities.service;

import com.smartfoxpro.cities.model.City;
import java.util.List;
import java.util.Optional;

public interface CityService {

    City findFirst();

    Optional<City> findByFirstLetter(String cityName);

    Optional<City> findByNameAndIfPlayedIsTrue(String cityName);

    City saveOrUpdate(String cityName);

    List<City> updateAllCitiesIfPlayedToFalse();
}
