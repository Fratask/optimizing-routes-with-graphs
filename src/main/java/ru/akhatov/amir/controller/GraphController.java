package ru.akhatov.amir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.service.GraphService;

@Controller
@RequestMapping(GraphController.BASE_MAPPING)
public class GraphController {

    protected static final String BASE_MAPPING = "/graph/v1";
    private final String GET_MAPPING = "/get";

    @Autowired
    private GraphService graphService;

    @GetMapping(GET_MAPPING)
    public ResponseEntity<?> getGraph(@RequestParam String type) {
        return ResponseEntity.ok(graphService.getGraph(NodeType.valueOf(type)));
    }
}
