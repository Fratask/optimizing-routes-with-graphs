package ru.akhatov.amir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.akhatov.amir.model.entity.Connection;
import ru.akhatov.amir.model.entity.NodeType;

import java.util.List;
import java.util.Set;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {


    @Query("select conn.nodeTo.id from Connection  conn where conn.nodeFrom.id = :nodeFromId ")
    Set<Long> findConnectedNodesIdFromNodeId(@Param("nodeFromId") Long nodeFromId);

    @Query("select conn from Connection  conn where conn.nodeFrom.id = :nodeId or conn.nodeTo.id = :nodeId")
    List<Connection> findAllConnectionsForNodeId(@Param("nodeId") Long nodeId);

    @Query("select connection from Connection connection where connection.nodeFrom.nodeType = :nodeType")
    Set<Connection> findAllByNodeType(@Param("nodeType") NodeType nodeType);
}
