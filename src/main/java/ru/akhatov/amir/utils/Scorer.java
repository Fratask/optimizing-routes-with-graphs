package ru.akhatov.amir.utils;

import ru.akhatov.amir.model.entity.Node;

public interface Scorer {
    double computeCost(Node from, Node to);
}
