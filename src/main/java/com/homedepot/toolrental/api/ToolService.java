package com.homedepot.toolrental.api;

import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {
    @Autowired
    private ToolRepository toolRepository;

    public Tool addTool(Tool tool) {
        return toolRepository.save(tool);
    }

    public Tool updateTool(Tool tool) {
        return toolRepository.save(tool);
    }

    public void deleteTool(String id) {
        toolRepository.deleteById(id);
    }

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public Optional<Tool> getToolById(String id) {
        return toolRepository.findById(id);
    }
}

// Similar services for UserService and RentalService
