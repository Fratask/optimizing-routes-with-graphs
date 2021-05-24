package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.model.entity.Node;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface NodeMapper {

    NodeDto toDto(Node node);

    Node fromDto(NodeDto nodeDto);

    List<NodeDto> toDtoList(List<Node> nodes);

    Set<NodeDto> toDtoSet(Set<Node> nodes);
}
