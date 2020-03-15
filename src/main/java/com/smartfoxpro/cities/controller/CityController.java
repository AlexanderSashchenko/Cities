package com.smartfoxpro.cities.controller;

import com.smartfoxpro.cities.model.City;
import com.smartfoxpro.cities.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private static String currentCityName;
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/begin")
    public String startGame() {
        cityService.updateAllCitiesIfPlayedToFalse();
        City city = cityService.findFirst();
        cityService.saveOrUpdate(city.getName());
        currentCityName = city.getName();
        return city.getName();
    }

    @GetMapping("/next")
    public String reply(@RequestParam("word") String cityName) {
        if (currentCityName.charAt(currentCityName.length() - 1)
                != Character.toLowerCase(cityName.charAt(0))) {
            return "Entered city name first letter is not equal to previous city name last letter!"
                    + " Previous city name was " + currentCityName;
        }
        if (cityService.findByNameAndIfPlayedIsTrue(cityName).isPresent()) {
            return "That city name has already been played! Please enter another one!"
                    + " Previous city name was " + currentCityName;
        }
        cityService.saveOrUpdate(cityName);
        currentCityName = cityName;
        return cityService.findByFirstLetter(cityName)
                .map(this::mapCityNameAndUpdateCurrentCityName)
                .orElse("You won! I don't know any more city names starting with '"
                + Character.toUpperCase(cityName.charAt(cityName.length() - 1)) + "' letter");
    }

    @PostMapping("/end")
    public String endGame() {
        return "Thank you for playing!";
    }

    private String mapCityNameAndUpdateCurrentCityName(City city) {
        currentCityName = city.getName();
        return city.getName();
    }
}
