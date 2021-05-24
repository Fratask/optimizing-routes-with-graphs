package ru.akhatov.amir.model.dto.osm;

import java.util.Arrays;

public class GeometryDto {
    private String type;
    private Double[][] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "GeometryDto{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}