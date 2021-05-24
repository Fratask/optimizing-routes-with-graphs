package ru.akhatov.amir.controller.osm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.akhatov.amir.model.dto.osm.FeatureDto;
import ru.akhatov.amir.model.dto.osm.GeometryDto;
import ru.akhatov.amir.model.dto.osm.OsmLocationsDto;
import ru.akhatov.amir.model.dto.osm.PropertiesDto;
import ru.akhatov.amir.model.entity.Connection;
import ru.akhatov.amir.model.entity.Node;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.repository.ConnectionRepository;
import ru.akhatov.amir.repository.NodeRepository;
import ru.akhatov.amir.utils.JacksonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/osm/v1")
public class OsmMapperController {

    private Logger LOG = LoggerFactory.getLogger(OsmMapperController.class);

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private JacksonUtils jacksonUtils;

    @PostMapping("/load/file")
    @Transactional
    public ResponseEntity<?> loadFile(@RequestBody MultipartFile file) throws IOException {
        OsmLocationsDto dto = jacksonUtils.getObjectMapper().readValue(file.getBytes(), OsmLocationsDto.class);
        return parseOsmToNodeAndSaveIt(dto);
    }

    @PostMapping("/load")
    @Transactional
    public ResponseEntity<?> load(@RequestBody OsmLocationsDto dto) {
        return parseOsmToNodeAndSaveIt(dto);
    }

    private ResponseEntity<List<FeatureDto>> parseOsmToNodeAndSaveIt(OsmLocationsDto dto) {
        int o = 0;
        for (FeatureDto feature : dto.getFeatures()) {
            if (feature.getId().toLowerCase().contains("way")) {
                if (feature.getProperties().getName() == null) {
                    continue;
                }
                List<NodeDto> nodesDto = createNodes(feature);
                if (nodesDto.size() < 2 || nodesDto.size() > 7) {
                    continue;
                }
                List<Node> nodes = new ArrayList<>();
//                NodeDto nodeDto = nodesDto.get(0);
//                Node node1 = new Node();
//                node1.setName(nodeDto.getName()+"_1");
//                node1.setLatitude(nodeDto.getLatitude());
//                node1.setLongitude(nodeDto.getLongitude());
//                node1.setGroupNum(nodeDto.getWayId());
//                node1.setNodeType(NodeType.CROSSROAD);
//                node1.setColor("#757575");
//                nodeDto = nodesDto.get(nodesDto.size()-1);
//                Node node2 = new Node();
//                node2.setName(nodeDto.getName()+"_2");
//                node2.setLatitude(nodeDto.getLatitude());
//                node2.setLongitude(nodeDto.getLongitude());
//                node2.setGroupNum(nodeDto.getWayId());
//                node2.setNodeType(NodeType.CROSSROAD);
//                node2.setColor("#757575");
//
//                if (Math.abs(node1.getLongitude() - node2.getLongitude()) * 100000 < 100 ||
//                        Math.abs(node1.getLatitude() - node2.getLatitude()) * 100000 < 100
//                ) {
//                    continue;
//                }
//
//                node1 = nodeRepository.save(node1);
//                node2 = nodeRepository.save(node2);
//                nodes.add(node1);
//                nodes.add(node2);

                o++;
                int k = 0;
                for (NodeDto nodeDto : nodesDto) {
                    k++;
                    Node node = new Node();
                    node.setName(o + "." + k + ". " + nodeDto.getName());
                    node.setLatitude(nodeDto.getLatitude());
                    node.setLongitude(nodeDto.getLongitude());
                    node.setGroupNum(nodeDto.getWayId());
                    node.setNodeType(NodeType.CROSSROAD);
                    node.setColor("#757575");
                    node = nodeRepository.save(node);
                    nodes.add(node);
                }
                for (int i = 1; i < nodes.size(); i++) {
                    Node node1 = nodes.get(i - 1);
                    Node node2 = nodes.get(i);
                    if (Math.abs(node1.getLongitude() - node2.getLongitude()) * 100000 == 0 ||
                            Math.abs(node1.getLatitude() - node2.getLatitude()) * 100000 == 0 ||
                            Math.abs(node1.getLongitude() - node2.getLongitude()) * 100000 > 100000 ||
                            Math.abs(node1.getLatitude() - node2.getLatitude()) * 100000 > 100000
                    ) {
                        for (int j = 0; j < i; j++) {
                            Node n = nodes.get(j);
                            List<Connection> deletingConnections = connectionRepository.findAllConnectionsForNodeId(n.getId());
                            connectionRepository.deleteAll(deletingConnections);
                            nodeRepository.delete(n);
                        }
                        continue;
                    }
                    List<Node> nodeList = nodeRepository.findByGroupNum(node1.getGroupNum());
                    if (nodeList.isEmpty() || nodeList.size() < 2) {
                        for (int j = 0; j < i; j++) {
                            Node n = nodes.get(j);
                            List<Connection> deletingConnections = connectionRepository.findAllConnectionsForNodeId(n.getId());
                            connectionRepository.deleteAll(deletingConnections);
                            nodeRepository.delete(n);
                        }
                        continue;
                    }
                    Connection connection1 = new Connection();
                    connection1.setNodeFrom(node1);
                    connection1.setNodeTo(node2);
                    connectionRepository.save(connection1);

                    Connection connection2 = new Connection();
                    connection2.setNodeFrom(node2);
                    connection2.setNodeTo(node1);
                    connectionRepository.save(connection2);
                }
            }
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/stub")
    @Transactional
    public ResponseEntity<?> stub() {
        OsmLocationsDto osm = new OsmLocationsDto();
        List<FeatureDto> features = new ArrayList<>();
        FeatureDto featureDto1 = new FeatureDto();
        GeometryDto geometryDto1 = new GeometryDto();
        geometryDto1.setType("type1");
        Double[][] coordinates1 = {{1d, 2d}, {3d, 4d}};
        geometryDto1.setCoordinates(coordinates1);
        featureDto1.setGeometry(geometryDto1);
        PropertiesDto propertiesDto1 = new PropertiesDto();
        propertiesDto1.setName("name1");
        featureDto1.setProperties(propertiesDto1);
        features.add(featureDto1);

        FeatureDto featureDto2 = new FeatureDto();
        GeometryDto geometryDto2 = new GeometryDto();
        geometryDto2.setType("type2");
        Double[][] coordinates2 = {{1d, 2d}, {3d, 4d}};
        geometryDto2.setCoordinates(coordinates2);
        featureDto2.setGeometry(geometryDto2);
        PropertiesDto propertiesDto2 = new PropertiesDto();
        propertiesDto2.setName("name2");
        featureDto2.setProperties(propertiesDto2);
        features.add(featureDto2);
        osm.setFeatures(features);
        return ResponseEntity.ok(osm);
    }

    private List<NodeDto> createNodes(FeatureDto feature) {
        List<NodeDto> result = new ArrayList<>();
        for (int i = 0; i < feature.getGeometry().getCoordinates().length; i++) {
            NodeDto dto = new NodeDto();
            dto.setName(feature.getProperties().getName());
            dto.setWayId(Long.valueOf(feature.getId().replace("way/", "")));
            dto.setLongitude(feature.getGeometry().getCoordinates()[i][0]);
            dto.setLatitude(feature.getGeometry().getCoordinates()[i][1]);
            result.add(dto);
        }
        return result;
    }
}
