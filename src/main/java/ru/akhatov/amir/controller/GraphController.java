package ru.akhatov.amir.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhatov.amir.model.dto.GraphDto;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.service.GraphService;

@Controller
@RequestMapping(GraphController.BASE_MAPPING)
public class GraphController {

    private Logger LOG = LoggerFactory.getLogger(GraphController.class);

    protected static final String BASE_MAPPING = "/graph/v1";
    private final String GET_MAPPING = "/get";

    @Autowired
    private GraphService graphService;

    @GetMapping(GET_MAPPING)
    public ResponseEntity<?> getGraph(@RequestParam String type) {
        LOG.info("GetGraph START");
        GraphDto graph = graphService.getGraph(NodeType.valueOf(type));
        LOG.info("GetGraph FINISH");
        return ResponseEntity.ok(graph);
    }
}
