package ru.akhatov.amir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akhatov.amir.model.entity.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {
}
