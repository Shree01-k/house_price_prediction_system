package com.example.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Module.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
