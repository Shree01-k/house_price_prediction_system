package com.example.demo.Module;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "area_master")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaId;

    @Column(nullable = false, length = 100)
    private String areaName;

    @Column(nullable = false, unique = true)
    private int areaPin;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true) // Allow null values if city is optional
    @JsonIgnore  // 🚀 Prevents infinite recursion
    private City city;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // 🚀 Prevents infinite recursion
    private List<Property> properties;

    // Getters and Setters
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaPin() {
        return areaPin;
    }

    public void setAreaPin(int areaPin) {
        this.areaPin = areaPin;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
