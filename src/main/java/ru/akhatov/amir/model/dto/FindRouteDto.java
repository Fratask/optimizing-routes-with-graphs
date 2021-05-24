package ru.akhatov.amir.model.dto;

public class FindRouteDto {

    private NodeDto from;
    private NodeDto to;

    public NodeDto getFrom() {
        return from;
    }

    public void setFrom(NodeDto from) {
        this.from = from;
    }

    public NodeDto getTo() {
        return to;
    }

    public void setTo(NodeDto to) {
        this.to = to;
    }
}
