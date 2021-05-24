package ru.akhatov.amir.model.dto;

import java.util.List;

public class GraphDto {

    private List<NodeDto> nodes;
    private List<LinkDto> links;

    public List<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDto> nodes) {
        this.nodes = nodes;
    }

    public List<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDto> links) {
        this.links = links;
    }
}
