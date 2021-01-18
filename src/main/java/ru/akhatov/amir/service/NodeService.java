package ru.akhatov.amir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhatov.amir.exception.DiplomException;
import ru.akhatov.amir.exception.DiplomResponseCode;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.model.entity.Node;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.model.mapper.NodeMapper;
import ru.akhatov.amir.repository.NodeRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.akhatov.amir.exception.DiplomResponseCode.NODE_ALREADY_EXISTS;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private NodeMapper nodeMapper;

    public NodeDto getNode(Long id) {
        Node node = nodeRepository.findById(id)
                .orElseThrow(() -> new DiplomException(DiplomResponseCode.NODE_NOT_FOUND));
        return nodeMapper.toDto(node);
    }

    public NodeDto addNode(NodeDto dto) {
        nodeRepository.findByName(dto.getName()).ifPresent((n) -> {
            throw new DiplomException(NODE_ALREADY_EXISTS);
        });

        Node node = new Node();
        node.setName(dto.getName());
        node.setNodeType(dto.getNodeType());
        node.setLatitude(dto.getLatitude());
        node.setLongitude(dto.getLongitude());
        return nodeMapper.toDto(nodeRepository.save(node));
    }

    public List<NodeDto> getAllNodes() {
        return nodeMapper.toDtoList(nodeRepository.findAll());
    }

    public List<NodeDto> getAllNodesByType(String type) {
        NodeType nodeType;
        try {
            nodeType = NodeType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new DiplomException(DiplomResponseCode.NODE_TYPE_NOT_FOUND);
        }
        return nodeMapper.toDtoList(new ArrayList<>(nodeRepository.findAllByNodeType(nodeType)));
    }
}
