package ru.akhatov.amir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhatov.amir.exception.DiplomException;
import ru.akhatov.amir.model.dto.VertexDto;
import ru.akhatov.amir.model.entity.Vertex;
import ru.akhatov.amir.model.mapper.VertexMapper;
import ru.akhatov.amir.repository.VertexRepository;

import java.util.List;

import static ru.akhatov.amir.exception.DiplomResponseCode.ID_MUST_NOT_BE_NULL;
import static ru.akhatov.amir.exception.DiplomResponseCode.VERTEX_ALREADY_EXISTS;
import static ru.akhatov.amir.exception.DiplomResponseCode.VERTEX_NOT_FOUND;

@Service
public class VertexService {

    @Autowired
    private VertexRepository vertexRepository;

    @Autowired
    private VertexMapper vertexMapper;

    public VertexDto addVertex(VertexDto dto) {
        vertexRepository.findByName(dto.getName()).ifPresent((v) -> {
            throw new DiplomException(VERTEX_ALREADY_EXISTS);
        });
        Vertex vertex = vertexMapper.fromDto(dto);
        return vertexMapper.toDto(vertexRepository.save(vertex));
    }

    public List<VertexDto> getAll() {
        return vertexMapper.toDtoList(vertexRepository.findAll());
    }

    public VertexDto updateVertex(VertexDto dto) {
        if (dto.getId() == null) {
            throw new DiplomException(ID_MUST_NOT_BE_NULL);
        }
        Vertex vertex = vertexRepository.findById(dto.getId())
                .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND));

        vertex.setName(dto.getName());
        return vertexMapper.toDto(vertexRepository.save(vertex));
    }

    public VertexDto deleteVertex(Long id) {
        if (id == null) {
            throw new DiplomException(ID_MUST_NOT_BE_NULL);
        }
        Vertex vertex = vertexRepository.findById(id)
                .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND));
        vertexRepository.delete(vertex);
        return vertexMapper.toDto(vertex);
    }
}
