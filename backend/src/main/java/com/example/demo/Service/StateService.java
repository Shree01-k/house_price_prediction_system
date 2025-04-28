package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Module.State;
import com.example.demo.Repository.StateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    public Optional<State> getStateById(Long id) {
        return stateRepository.findById(id);
    }

    public State saveState(State state) {
        return stateRepository.save(state);
    }

    public State updateState(Long id, State updatedState) {
        return stateRepository.findById(id).map(state -> {
            state.setName(updatedState.getName());
            return stateRepository.save(state);
        }).orElse(null);
    }

    public boolean deleteState(Long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
