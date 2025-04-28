package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Module.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
