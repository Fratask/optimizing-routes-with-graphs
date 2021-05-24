package ru.akhatov.amir.service.news;

import ru.akhatov.amir.model.dto.NewsDto;

import java.util.List;

public interface INewsService {

    List<NewsDto> getNewsByKeyWords(List<String> keyWords);
}
