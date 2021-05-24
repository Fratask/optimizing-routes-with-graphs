package ru.akhatov.amir.model.dto.osm;

public class PropertiesDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertiesDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
