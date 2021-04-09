package ru.akhatov.amir.model.dto.hh;

import java.util.List;

public class LineDto {

    private Long id;
    private String hex_color;
    private String name;
    private List<StationDto> stations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHex_color() {
        return hex_color;
    }

    public void setHex_color(String hex_color) {
        this.hex_color = hex_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }
}
