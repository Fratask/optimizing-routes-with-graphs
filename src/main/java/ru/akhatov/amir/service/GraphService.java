package ru.akhatov.amir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhatov.amir.model.dto.ConnectionDto;
import ru.akhatov.amir.model.dto.GraphDto;
import ru.akhatov.amir.model.dto.LinkDto;
import ru.akhatov.amir.model.dto.NodeDto;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.model.mapper.ConnectionMapper;
import ru.akhatov.amir.model.mapper.NodeMapper;
import ru.akhatov.amir.repository.ConnectionRepository;
import ru.akhatov.amir.repository.NodeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GraphService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private ConnectionMapper connectionMapper;

    public GraphDto getGraph(NodeType nodeType) {
        GraphDto dto = new GraphDto();
        List<NodeDto> nodes = new ArrayList<>(nodeMapper.toDtoSet(nodeRepository.findAllByNodeType(nodeType)));

        Set<ConnectionDto> connections = connectionMapper.toDtoSet(connectionRepository.findAllByNodeType(nodeType));
        List<LinkDto> links;
        links = connections.stream().map(connection -> {
            LinkDto linkDto = new LinkDto();
            Long sourceIndex;
            Long sourceId = connection.getNodeFrom().getId();
            sourceIndex = (long) IntStream
                    .range(0, nodes.size())
                    .map(index -> {
                        if (nodes.get(index).getId().equals(sourceId)) {
                            return index;
                        }
                        return -1;
                    }).filter(index -> index != -1).findFirst().getAsInt();
            linkDto.setSource(sourceIndex);

            Long targetIndex;
            Long targetId = connection.getNodeTo().getId();
            targetIndex = (long) IntStream
                    .range(0, nodes.size())
                    .map(index -> {
                        if (nodes.get(index).getId().equals(targetId)) {
                            return index;
                        }
                        return -1;
                    }).filter(index -> index != -1).findFirst().getAsInt();
            linkDto.setTarget(targetIndex);

            String color = "#CECECE";
            NodeDto source = nodes.get(Math.toIntExact(sourceIndex));
            NodeDto target = nodes.get(Math.toIntExact(targetIndex));
            if (source.getGroupNum().equals(target.getGroupNum())) {
                color = source.getColor();
            }
            linkDto.setColor(color);
            return linkDto;
        }).collect(Collectors.toList());

        dto.setNodes(nodes);
        dto.setLinks(links);
        return dto;
    }
}
