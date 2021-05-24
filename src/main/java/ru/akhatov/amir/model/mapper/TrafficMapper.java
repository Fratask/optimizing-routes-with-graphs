package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.TrafficDto;
import ru.akhatov.amir.model.entity.Traffic;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TrafficMapper {

    TrafficDto toDto(Traffic node);

    Traffic fromDto(TrafficDto nodeDto);

    List<TrafficDto> toDtoList(List<Traffic> nodes);

    Set<TrafficDto> toDtoSet(Set<Traffic> nodes);
}
