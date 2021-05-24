package ru.akhatov.amir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.service.route.NodeService;

@Controller
@RequestMapping(NodeController.BASE_MAPPING)
public class NodeController {

    protected static final String BASE_MAPPING = "/node/v1";
    private final String GET_MAPPING = "/get/{id}";
    private final String GET_BY_NAME_MAPPING = "/get/name";
    private final String ADD_MAPPING = "/add";
    private final String GET_ALL_MAPPING = "/all";
    private final String GET_ALL_BY_TYPE_MAPPING = "/all/{type}";
    private final String REFORMAT_POINTS_MAPPING = "/reformat/points";

    @Autowired
    private NodeService nodeService;

    @GetMapping(GET_MAPPING)
    public ResponseEntity<?> getNode(@PathVariable("id") Long id) {
        return ResponseEntity.ok(nodeService.getNode(id));
    }

    @GetMapping(GET_BY_NAME_MAPPING)
    public ResponseEntity<?> getNodeByName(@RequestParam String name, @RequestParam Long groupNum) {
        return ResponseEntity.ok(nodeService.getNodeByNameAndGroupNum(name, groupNum));
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

    @GetMapping(REFORMAT_POINTS_MAPPING)
    public ResponseEntity<?> reformatPoints() {
        nodeService.reformatPoints();
        return ResponseEntity.ok().build();
    }
}
