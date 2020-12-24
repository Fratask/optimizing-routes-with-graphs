package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.EdgeDto;
import ru.akhatov.amir.model.entity.Edge;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EdgeMapper {

    EdgeDto toDto(Edge edge);

    Edge fromDto(EdgeDto edgeDto);

    List<EdgeDto> toListDto(List<Edge> edges);
}
