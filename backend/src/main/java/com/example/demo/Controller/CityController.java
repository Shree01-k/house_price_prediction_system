package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Module.City;
import com.example.demo.Service.CityService;
import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React frontend
public class CityController {

    @Autowired
    private CityService cityService;
    
    @PostMapping
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id, updatedCity);
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }
}
