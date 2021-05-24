package ru.akhatov.amir.model.dto;

public class ConnectionDto {

    private Long id;
    private NodeDto nodeFrom;
    private NodeDto nodeTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NodeDto getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(NodeDto nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public NodeDto getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(NodeDto nodeTo) {
        this.nodeTo = nodeTo;
    }
}
