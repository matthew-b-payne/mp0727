package com.homedepot.toolrental.api;

import com.homedepot.toolrental.model.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @PostMapping
    public Tool addTool(@RequestBody Tool tool) {
        return toolService.addTool(tool);
    }

    @PutMapping
    public Tool updateTool(@RequestBody Tool tool) {
        return toolService.updateTool(tool);
    }

    @DeleteMapping("/{id}")
    public void deleteTool(@PathVariable String id) {
        toolService.deleteTool(id);
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    public Tool getToolById(@PathVariable String id) {
        return toolService.getToolById(id).orElse(null);
    }
}
