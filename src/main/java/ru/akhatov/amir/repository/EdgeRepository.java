package ru.akhatov.amir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akhatov.amir.model.entity.Edge;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
}
