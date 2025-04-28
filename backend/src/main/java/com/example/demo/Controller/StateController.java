package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Module.State;
import com.example.demo.Service.StateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React frontend
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping("/{id}")
    public Optional<State> getStateById(@PathVariable Long id) {
        return stateService.getStateById(id);
    }

    @PostMapping
    public State createState(@RequestBody State state) {
        return stateService.saveState(state);
    }

    @PutMapping("/{id}")
    public State updateState(@PathVariable Long id, @RequestBody State updatedState) {
        return stateService.updateState(id, updatedState);
    }

    @DeleteMapping("/{id}")
    public String deleteState(@PathVariable Long id) {
        boolean deleted = stateService.deleteState(id);
        if (deleted) {
            return "State deleted successfully.";
        } else {
            return "State not found.";
        }
    }
}
