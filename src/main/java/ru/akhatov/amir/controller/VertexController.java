package ru.akhatov.amir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhatov.amir.model.dto.VertexDto;
import ru.akhatov.amir.service.VertexService;

@Controller
@RequestMapping(VertexController.BASE_MAPPING)
public class VertexController {

    protected final static String BASE_MAPPING = "/vertex/v1";
    private final static String ADD_MAPPING = "/add";
    private final static String GET_ALL_MAPPING = "/all";
    private final static String UPDATE_MAPPING = "/update";
    private final static String DELETE_MAPPING = "/delete/{id}";

    @Autowired
    private VertexService vertexService;

    @PostMapping(ADD_MAPPING)
    public ResponseEntity<?> addVertex(@RequestBody VertexDto dto) {
        return ResponseEntity.ok(vertexService.addVertex(dto));
    }

    @GetMapping(GET_ALL_MAPPING)
    public ResponseEntity<?> getAllVertexes() {
        return ResponseEntity.ok(vertexService.getAll());
    }

    @PutMapping(UPDATE_MAPPING)
    public ResponseEntity<?> updateVertex(@RequestBody VertexDto dto) {
        return ResponseEntity.ok(vertexService.updateVertex(dto));
    }

    @DeleteMapping(DELETE_MAPPING)
    public ResponseEntity<?> deleteVertex(@PathVariable Long id) {
        return ResponseEntity.ok(vertexService.deleteVertex(id));
    }
}
