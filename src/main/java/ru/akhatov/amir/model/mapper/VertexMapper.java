package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.VertexDto;
import ru.akhatov.amir.model.entity.Vertex;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VertexMapper {

    VertexDto toDto(Vertex vertex);

    Vertex fromDto(VertexDto vertexDto);

    List<VertexDto> toDtoList(List<Vertex> vertexes);
}
