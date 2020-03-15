package com.smartfoxpro.cities.service.impl;

import com.smartfoxpro.cities.model.City;
import com.smartfoxpro.cities.repository.CityRepository;
import com.smartfoxpro.cities.service.CityService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private static final Long FIRST_CITY_ID = 1L;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findFirst() {
        return cityRepository.getOne(FIRST_CITY_ID);
    }

    @Override
    public Optional<City> findByFirstLetter(String cityName) {
        char firstLetter = Character.toUpperCase(cityName.charAt(cityName.length() - 1));
        Optional<City> cityOptional = cityRepository
                .findTopByNameIsStartingWithAndIfPlayedFalse(Character.toString(firstLetter));
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setIfPlayed(true);
            cityRepository.save(city);
        }
        return cityOptional;
    }

    @Override
    public Optional<City> findByNameAndIfPlayedIsTrue(String cityName) {
        return cityRepository.findByNameAndIfPlayedIsTrue(cityName);
    }

    @Override
    public City saveOrUpdate(String cityName) {
        Optional<City> cityOptional = cityRepository.findByNameAndIfPlayedIsFalse(cityName);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setIfPlayed(true);
            return cityRepository.save(city);
        }
        City city = new City();
        city.setName(cityName);
        city.setIfPlayed(true);
        return cityRepository.save(city);
    }

    @Override
    public List<City> updateAllCitiesIfPlayedToFalse() {
        return cityRepository.saveAll(cityRepository.findAllByIfPlayedIsTrue()
                .stream()
                .map(this::setIfPlayedFalse)
                .collect(Collectors.toList()));
    }

    private City setIfPlayedFalse(City city) {
        city.setIfPlayed(false);
        return city;
    }
}
