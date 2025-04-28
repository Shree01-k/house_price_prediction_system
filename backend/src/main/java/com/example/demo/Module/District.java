package com.example.demo.Module;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<City> cities;

    // Constructors
    public District() {}

    public District(String name, State state) {
        this.name = name;
        this.state = state;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public List<City> getCities() { return cities; }
    public void setCities(List<City> cities) { this.cities = cities; }
}
