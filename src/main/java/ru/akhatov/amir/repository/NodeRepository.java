package ru.akhatov.amir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akhatov.amir.model.entity.Node;
import ru.akhatov.amir.model.entity.NodeType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

    Set<Node> findAllByNodeType(NodeType nodeType);

    Optional<Node> findByName(String name);

    Optional<Node> findByNameAndGroupNum(String name, Long groupNum);

    List<Node> findByGroupNum(Long groupNum);
}
