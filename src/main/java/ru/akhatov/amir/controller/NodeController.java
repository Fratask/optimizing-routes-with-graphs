package ru.akhatov.amir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.service.NodeService;

@Controller
@RequestMapping(NodeController.BASE_MAPPING)
public class NodeController {

    protected static final String BASE_MAPPING = "/node/v1";
    private final String GET_MAPPING = "/get/{id}";
    private final String ADD_MAPPING = "/add";
    private final String GET_ALL_MAPPING = "/all";
    private final String GET_ALL_BY_TYPE_MAPPING = "/all/{type}";

    @Autowired
    private NodeService nodeService;

    @GetMapping(GET_MAPPING)
    public ResponseEntity<?> getNode(@PathVariable("id") Long id) {
        return ResponseEntity.ok(nodeService.getNode(id));
    }

    @PostMapping(ADD_MAPPING)
    public ResponseEntity<?> addNode(@RequestBody NodeDto dto) {
        return ResponseEntity.ok(nodeService.addNode(dto));
    }

    @GetMapping(GET_ALL_MAPPING)
    public ResponseEntity<?> getAllNodes() {
        return ResponseEntity.ok(nodeService.getAllNodes());
    }

    @GetMapping(GET_ALL_BY_TYPE_MAPPING)
    public ResponseEntity<?> getAllNodesByType(@PathVariable String type) {
        return ResponseEntity.ok(nodeService.getAllNodesByType(type));
    }
}
