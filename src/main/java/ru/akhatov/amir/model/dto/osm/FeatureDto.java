package ru.akhatov.amir.model.dto.osm;

public class FeatureDto {
    private PropertiesDto properties;
    private GeometryDto geometry;
    private String id;

    public PropertiesDto getProperties() {
        return properties;
    }

    public void setProperties(PropertiesDto properties) {
        this.properties = properties;
    }

    public GeometryDto getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryDto geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FeatureDto{" +
                "properties=" + properties +
                ", geometry=" + geometry +
                ", id='" + id + '\'' +
                '}';
    }
}
