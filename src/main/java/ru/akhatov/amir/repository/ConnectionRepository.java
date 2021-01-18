package ru.akhatov.amir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.akhatov.amir.model.entity.Connection;

import java.util.Set;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {


    @Query("select conn.nodeTo.id from Connection  conn where conn.nodeFrom.id = :nodeFromId ")
    Set<Long> findConnectedNodesIdFromNodeId(@Param("nodeFromId") Long nodeFromId);
}
