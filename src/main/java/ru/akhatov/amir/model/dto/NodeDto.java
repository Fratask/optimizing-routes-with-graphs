package ru.akhatov.amir.model.dto;

import ru.akhatov.amir.model.entity.NodeType;

public class NodeDto {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private NodeType nodeType;
    private Long groupNum;
    private String color;
    private double pointX;
    private double pointY;
    private double textX;
    private double textY;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Long getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Long groupNum) {
        this.groupNum = groupNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getTextX() {
        return textX;
    }

    public void setTextX(double textX) {
        this.textX = textX;
    }

    public double getTextY() {
        return textY;
    }

    public void setTextY(double textY) {
        this.textY = textY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }
}
