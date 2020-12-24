package ru.akhatov.amir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhatov.amir.exception.DiplomException;
import ru.akhatov.amir.model.dto.EdgeDto;
import ru.akhatov.amir.model.entity.Edge;
import ru.akhatov.amir.model.entity.Vertex;
import ru.akhatov.amir.model.mapper.EdgeMapper;
import ru.akhatov.amir.repository.EdgeRepository;
import ru.akhatov.amir.repository.VertexRepository;

import java.util.List;

import static ru.akhatov.amir.exception.DiplomResponseCode.COST_MUST_NOT_BE_NULL;
import static ru.akhatov.amir.exception.DiplomResponseCode.EDGE_NOT_FOUND;
import static ru.akhatov.amir.exception.DiplomResponseCode.ID_MUST_NOT_BE_NULL;
import static ru.akhatov.amir.exception.DiplomResponseCode.VERTEX_NOT_FOUND;

@Service
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    @Autowired
    private VertexRepository vertexRepository;

    @Autowired
    private EdgeMapper edgeMapper;

    public EdgeDto addEdge(EdgeDto dto) {
        Edge edge = new Edge();

        if (dto.getVertexDto1() != null && dto.getVertexDto1().getId() != null) {
            Vertex vertex1 = vertexRepository.findById(dto.getVertexDto1().getId())
                    .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND, "id", String.valueOf(dto.getVertexDto1().getId())));
            edge.setVertex1(vertex1);
        } else {
            throw new DiplomException(VERTEX_NOT_FOUND, "vertexDto1");
        }

        if (dto.getVertexDto2() != null && dto.getVertexDto2().getId() != null) {
            Vertex vertex2 = vertexRepository.findById(dto.getVertexDto2().getId())
                    .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND, "id", String.valueOf(dto.getVertexDto2().getId())));
            edge.setVertex2(vertex2);
        } else {
            throw new DiplomException(VERTEX_NOT_FOUND, "vertexDto2");
        }

        if (dto.getCost() != null) {
            edge.setCost(dto.getCost());
        } else {
            throw new DiplomException(COST_MUST_NOT_BE_NULL);
        }

        return edgeMapper.toDto(edgeRepository.save(edge));
    }

    public List<EdgeDto> getAllEdges() {
        return edgeMapper.toListDto(edgeRepository.findAll());
    }

    public EdgeDto updateEdge(EdgeDto dto) {
        if (dto.getId() == null) {
            throw new DiplomException(ID_MUST_NOT_BE_NULL);
        }

        Edge edge = edgeRepository.findById(dto.getId())
                .orElseThrow(() -> new DiplomException(EDGE_NOT_FOUND));

        if (dto.getVertexDto1() != null && dto.getVertexDto1().getId() != null &&
                !dto.getVertexDto1().getId().equals(edge.getVertex1().getId())) {
            Vertex vertex1 = vertexRepository.findById(dto.getVertexDto1().getId())
                    .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND, "id", String.valueOf(dto.getVertexDto1().getId())));
            edge.setVertex1(vertex1);
        }

        if (dto.getVertexDto2() != null && dto.getVertexDto2().getId() != null &&
                !dto.getVertexDto2().getId().equals(edge.getVertex2().getId())) {
            Vertex vertex2 = vertexRepository.findById(dto.getVertexDto2().getId())
                    .orElseThrow(() -> new DiplomException(VERTEX_NOT_FOUND, "id", String.valueOf(dto.getVertexDto2().getId())));
            edge.setVertex2(vertex2);
        }

        if (dto.getCost() != null && !dto.getCost().equals(edge.getCost())) {
            edge.setCost(dto.getCost());
        }

        edgeRepository.save(edge);
        return edgeMapper.toDto(edge);
    }

    public EdgeDto deleteEdge(Long id) {
        if (id == null) {
            throw new DiplomException(ID_MUST_NOT_BE_NULL);
        }
        Edge edge = edgeRepository.findById(id)
                .orElseThrow(() -> new DiplomException(EDGE_NOT_FOUND));
        edgeRepository.delete(edge);
        return edgeMapper.toDto(edge);
    }


}
