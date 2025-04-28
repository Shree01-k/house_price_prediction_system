package com.example.demo.Service;

import com.example.demo.Module.District;
import com.example.demo.Repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public Optional<District> getDistrictById(Long id) {
        return districtRepository.findById(id);
    }

    public District saveDistrict(District district) {
        System.out.println("Saving District: " + district.getName() + " with State ID: " + (district.getState() != null ? district.getState().getId() : "null"));
        return districtRepository.save(district);
    }

    public District updateDistrict(Long id, District updatedDistrict) {
        return districtRepository.findById(id).map(district -> {
            district.setName(updatedDistrict.getName());
            district.setState(updatedDistrict.getState());
            return districtRepository.save(district);
        }).orElseThrow(() -> new RuntimeException("District not found with ID: " + id));
    }

    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }
}
