package ru.akhatov.amir.controller.hh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhatov.amir.model.dto.hh.LineDto;
import ru.akhatov.amir.model.dto.hh.MetroDto;
import ru.akhatov.amir.model.dto.hh.StationDto;
import ru.akhatov.amir.model.entity.Connection;
import ru.akhatov.amir.model.entity.Node;
import ru.akhatov.amir.model.entity.NodeType;
import ru.akhatov.amir.repository.ConnectionRepository;
import ru.akhatov.amir.repository.NodeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hh/v1/load")
public class MapperController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> load(@RequestBody MetroDto dto) {

        for (LineDto line : dto.getLines()) {
            ArrayList<Node> nodes = new ArrayList<>();
            for (StationDto station : line.getStations()) {
                Node node = new Node();
                node.setName(station.getName());
                node.setGroupNum(line.getId());
                node.setColor("#" + line.getHex_color());
                node.setLatitude(station.getLat());
                node.setLongitude(station.getLng());
                double RADIUS = 6378137.0;
//                double pointX = Math.toRadians(node.getLongitude()) * RADIUS;
//                double pointY = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(node.getLatitude()) / 2)) * RADIUS;
                double pointX = (node.getLongitude() - 37) * 10000;
                double pointY = (node.getLatitude() - 55) * 10000 * (-1) + 10000;
                node.setPointX(pointX);
                node.setPointY(pointY);
                node.setTextX(pointX);
                node.setTextY(pointY);
                node.setNodeType(NodeType.UNDERGROUND);
                nodes.add(node);
                nodeRepository.save(node);
            }

            for (int i = 1; i < nodes.size(); i++) {
                Node prev = nodes.get(i - 1);
                Node curr = nodes.get(i);
                Connection connection = new Connection();
                connection.setNodeFrom(prev);
                connection.setNodeTo(curr);
                connectionRepository.save(connection);
                connection.setNodeFrom(curr);
                connection.setNodeTo(prev);
                connectionRepository.save(connection);
            }
        }
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<?> adjustConnections() {
        List<Connection> connections = connectionRepository.findAll().stream().filter(connection -> connection.getId() <= 315).collect(Collectors.toList());
        for (Connection conn : connections) {
            Connection newConn = new Connection();
            newConn.setNodeFrom(conn.getNodeTo());
            newConn.setNodeTo(conn.getNodeFrom());
            connectionRepository.save(newConn);
        }

        return ResponseEntity.ok().build();
    }
}
