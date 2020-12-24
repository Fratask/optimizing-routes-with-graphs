package ru.akhatov.amir.model.dto;

public class EdgeDto {

    private Long id;
    private VertexDto vertexDto1;
    private VertexDto vertexDto2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VertexDto getVertexDto1() {
        return vertexDto1;
    }

    public void setVertexDto1(VertexDto vertexDto1) {
        this.vertexDto1 = vertexDto1;
    }

    public VertexDto getVertexDto2() {
        return vertexDto2;
    }

    public void setVertexDto2(VertexDto vertexDto2) {
        this.vertexDto2 = vertexDto2;
    }
}
