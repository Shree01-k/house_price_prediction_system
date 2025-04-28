package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Module.City;
import com.example.demo.Module.District;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

  
    public City saveCity(City city) {
        if (city.getName() == null || city.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty.");
        }
        
        if (city.getDistrict() == null || city.getDistrict().getId() == null) {
            throw new IllegalArgumentException("City must have a valid district assigned.");
        }

        // Fetch district from DB
        District district = districtRepository.findById(city.getDistrict().getId())
                .orElseThrow(() -> new RuntimeException("District not found with ID: " + city.getDistrict().getId()));

        city.setDistrict(district);
        
        return cityRepository.save(city);
    }


    public City updateCity(Long id, City updatedCity) {
        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        existingCity.setName(updatedCity.getName());

        if (updatedCity.getDistrict() != null) {
            District district = districtRepository.findById(updatedCity.getDistrict().getId())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            existingCity.setDistrict(district);
        }

        return cityRepository.save(existingCity);
    }

    public String deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return "City deleted successfully";
        }
        return "City not found";
    }
}
