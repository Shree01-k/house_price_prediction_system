package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Module.Property;
import com.example.demo.Service.PropertyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React frontend
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Get all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Get property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable int id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

    // Update an existing property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable int id, @RequestBody Property updatedProperty) {
        Optional<Property> existingProperty = propertyService.getPropertyById(id);
        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setPropName(updatedProperty.getPropName());
            property.setPropAddress(updatedProperty.getPropAddress());
            property.setPropPrice(updatedProperty.getPropPrice());
            property.setPropSqft(updatedProperty.getPropSqft());
            property.setPriceWithAmenity(updatedProperty.getPriceWithAmenity());
            property.setArea(updatedProperty.getArea());
            
            Property savedProperty = propertyService.saveProperty(property);
            return ResponseEntity.ok(savedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable int id) {
        if (propertyService.getPropertyById(id).isPresent()) {
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}