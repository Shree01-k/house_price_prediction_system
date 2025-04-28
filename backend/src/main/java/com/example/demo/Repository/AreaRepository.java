package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Module.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByAreaNameAndAreaPin(String areaName, int areaPin);
}
