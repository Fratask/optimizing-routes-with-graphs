package ru.akhatov.amir.model.dto;

import java.time.LocalDateTime;

public class NewsDto {

    private Long id;
    private String title;
    private String body;
    private LocalDateTime publishedAt;
    private NodeDto nodeFrom;
    private NodeDto nodeTo;
    private String comment;
    private TrafficDto trafficDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TrafficDto getTrafficDto() {
        return trafficDto;
    }

    public void setTrafficDto(TrafficDto trafficDto) {
        this.trafficDto = trafficDto;
    }
}
