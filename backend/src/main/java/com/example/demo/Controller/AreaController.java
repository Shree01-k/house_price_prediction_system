package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Module.Area;
import com.example.demo.Service.AreaService;

import java.util.List;

@RestController
@RequestMapping("/areas")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React frontend
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        try {
            return ResponseEntity.ok(areaService.addArea(area));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAllAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable Long id) {
        Area area = areaService.getAreaById(id);
        return area != null ? ResponseEntity.ok(area) : ResponseEntity.badRequest().body("Area not found!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArea(@PathVariable Long id) {
        return areaService.deleteArea(id) ?
                ResponseEntity.ok("Deleted successfully") :
                ResponseEntity.badRequest().body("Area not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArea(@PathVariable Long id, @RequestBody Area area) {
        try {
            Area updatedArea = areaService.updateArea(id, area);
            return updatedArea != null ? ResponseEntity.ok(updatedArea) : ResponseEntity.badRequest().body("Area not found!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
