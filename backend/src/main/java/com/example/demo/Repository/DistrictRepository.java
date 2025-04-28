package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Module.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
}
