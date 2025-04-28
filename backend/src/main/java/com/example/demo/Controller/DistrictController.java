package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Module.District;
import com.example.demo.Service.DistrictService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/districts")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("/{id}")
    public Optional<District> getDistrictById(@PathVariable Long id) {
        return districtService.getDistrictById(id);
    }

    @PostMapping
    public District createDistrict(@RequestBody District district) {
        System.out.println("Received District: " + district.getName() + ", State ID: " + (district.getState() != null ? district.getState().getId() : "null"));
        return districtService.saveDistrict(district);
    }

    @PutMapping("/{id}")
    public District updateDistrict(@PathVariable Long id, @RequestBody District updatedDistrict) {
        return districtService.updateDistrict(id, updatedDistrict);
    }

    @DeleteMapping("/{id}")
    public String deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return "District with ID " + id + " deleted successfully!";
    }
}
