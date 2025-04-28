package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Module.Area;
import com.example.demo.Module.City;
import com.example.demo.Repository.AreaRepository;
import com.example.demo.Repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CityRepository cityRepository; // ✅ Correctly inject CityRepository

    @Override
    @Transactional
    public Area addArea(Area area) {
        if (areaRepository.existsByAreaNameAndAreaPin(area.getAreaName(), area.getAreaPin())) {
            throw new RuntimeException("Area with this name and pin already exists.");
        }

        // ✅ Ensure the city exists before assigning it to Area
        if (area.getCity() != null && area.getCity().getId() != null) {
            City existingCity = cityRepository.findById(area.getCity().getId()) // ✅ Corrected the ID reference
                    .orElseThrow(() -> new RuntimeException("City not found with ID: " + area.getCity().getId()));
            area.setCity(existingCity);
        } else {
            throw new RuntimeException("City must be provided and must exist in the database.");
        }

        return areaRepository.save(area);
    }

    @Override
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @Override
    public Area getAreaById(Long id) {
        return areaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteArea(Long id) {
        if (areaRepository.existsById(id)) {
            areaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Area updateArea(Long id, Area area) {
        Optional<Area> existingArea = areaRepository.findById(id);
        if (existingArea.isPresent()) {
            Area updatedArea = existingArea.get();
            updatedArea.setAreaName(area.getAreaName());
            updatedArea.setAreaPin(area.getAreaPin());

            // ✅ Ensure the city exists before updating
            if (area.getCity() != null && area.getCity().getId() != null) {
                City existingCity = cityRepository.findById(area.getCity().getId())
                        .orElseThrow(() -> new RuntimeException("City not found with ID: " + area.getCity().getId()));
                updatedArea.setCity(existingCity);
            }

            return areaRepository.save(updatedArea);
        }
        return null;
    }
}
