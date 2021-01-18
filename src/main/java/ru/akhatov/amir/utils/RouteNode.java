package ru.akhatov.amir.utils;

import ru.akhatov.amir.model.entity.Node;

import java.util.StringJoiner;

public class RouteNode implements Comparable<RouteNode> {

    private final Node current;
    private Node previous;
    private double routeScore;
    private double estimatedScore;

    public RouteNode(Node current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public RouteNode(Node current, Node previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteNode other) {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }

    public Node getCurrent() {
        return current;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RouteNode.class.getSimpleName() + "[", "]").add("current=" + current)
                .add("previous=" + previous).add("routeScore=" + routeScore).add("estimatedScore=" + estimatedScore)
                .toString();
    }
}
