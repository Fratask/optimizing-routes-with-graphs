package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.ConnectionDto;
import ru.akhatov.amir.model.entity.Connection;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ConnectionMapper {

    ConnectionDto toDto(Connection entity);

    List<ConnectionDto> toDtoSet(List<Connection> entity);

    Set<ConnectionDto> toDtoSet(Set<Connection> entity);

    Connection fromDto(ConnectionDto dto);
}
