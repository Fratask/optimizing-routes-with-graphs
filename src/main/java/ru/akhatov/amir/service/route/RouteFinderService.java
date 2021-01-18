package ru.akhatov.amir.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhatov.amir.exception.DiplomException;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.model.entity.Graph;
import ru.akhatov.amir.model.entity.Node;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.repository.NodeRepository;
import ru.akhatov.amir.utils.HaversineScorer;
import ru.akhatov.amir.utils.RouteNode;
import ru.akhatov.amir.utils.Scorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.akhatov.amir.exception.DiplomResponseCode.DIFFERENT_NODE_TYPES;
import static ru.akhatov.amir.exception.DiplomResponseCode.NODE_NOT_FOUND;
import static ru.akhatov.amir.exception.DiplomResponseCode.ROUTE_NOT_FOUND;

@Service
public class RouteFinderService {

    @Autowired
    private NodeRepository nodeRepository;

    private final Map<NodeType, Graph> graphMap;

    public RouteFinderService(List<Graph> graphs) {
        graphMap = graphs.stream().collect(Collectors.toMap(Graph::getNodeTypeForGraph, Function.identity()));
    }

    public List<Node> findRoute(NodeDto fromDto, NodeDto toDto) {
        if (!fromDto.getNodeType().equals(toDto.getNodeType())) {
            throw new DiplomException(DIFFERENT_NODE_TYPES);
        }
        Node from = nodeRepository.findById(fromDto.getId()).orElseThrow(() -> new DiplomException(NODE_NOT_FOUND));
        Node to = nodeRepository.findById(toDto.getId()).orElseThrow(() -> new DiplomException(NODE_NOT_FOUND));

        Scorer nextNodeScorer = new HaversineScorer();
        Scorer targetScorer = new HaversineScorer();
        Graph graph = graphMap.get(fromDto.getNodeType());
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<Node, RouteNode> allNodes = new HashMap<>();

        RouteNode start = new RouteNode(from, null, 0d, targetScorer.computeCost(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        while (!openSet.isEmpty()) {
//            System.out.println("Open Set contains: " + openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            RouteNode next = openSet.poll();
//            System.out.println("Looking at node: " + next);
            if (next.getCurrent().equals(to)) {
//                System.out.println("Found our destination!");

                List<Node> route = new ArrayList<>();
                RouteNode current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

//                System.out.println("Route: " + route);
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                double newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
                RouteNode nextNode = allNodes.getOrDefault(connection, new RouteNode(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
//                    System.out.println("Found a better route to node: " + nextNode);
                }
            });
        }

        throw new DiplomException(ROUTE_NOT_FOUND);
    }
}
