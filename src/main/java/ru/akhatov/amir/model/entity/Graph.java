package ru.akhatov.amir.model.entity;

import java.util.Set;


public interface Graph {

    Node getNode(Long id);

    Set<Node> getConnections(Node node);

    NodeType getNodeTypeForGraph();
}
