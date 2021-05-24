package ru.akhatov.amir.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhatov.amir.repository.ConnectionRepository;
import ru.akhatov.amir.repository.NodeRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ru.akhatov.amir.model.entity.NodeType.CROSSROAD;

@Component
public class CrossroadGraph implements Graph {

    private Logger logger = Logger.getLogger(UndergroundGraph.class.getName());

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    private Set<Node> nodes;
    private Map<Long, Set<Long>> connections;

    @PostConstruct
    public void initialize() {
        logger.info("Initialize CrossroadGraph START");
        nodes = nodeRepository.findAllByNodeType(CROSSROAD);
        connections = new HashMap<>();
        connections = nodes.stream()
                .collect(Collectors.toMap(
                        Node::getId,
                        (node -> connectionRepository.findConnectedNodesIdFromNodeId(node.getId()))
                ));
        logger.info("Initialize CrossroadGraph FINISH");
    }

    public Node getNode(Long id) {
        return nodes.stream()
                .filter(node -> node.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No node found with id"));
    }

    public Set<Node> getConnections(Node node) {
        return connections.get(node.getId()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }

    @Override
    public NodeType getNodeTypeForGraph() {
        return CROSSROAD;
    }
}
