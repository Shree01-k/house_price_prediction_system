package com.example.demo.Module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

  
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore  // Prevents infinite recursion
    private District district;

    
    // Constructors
    public City() {}

    public City(String name, District district) {
        this.name = name;
        this.district = district;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public District getDistrict() { return district; }
    public void setDistrict(District district) { this.district = district; }
}
