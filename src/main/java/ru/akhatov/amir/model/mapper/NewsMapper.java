package ru.akhatov.amir.model.mapper;

import org.mapstruct.Mapper;
import ru.akhatov.amir.model.dto.NewsDto;
import ru.akhatov.amir.model.entity.News;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto toDto(News node);

    News fromDto(NewsDto nodeDto);

    List<NewsDto> toDtoList(List<News> nodes);

    Set<NewsDto> toDtoSet(Set<News> nodes);
}
