package ru.akhatov.amir.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "edges")
public class Edge {

    @Id
    @SequenceGenerator(name = "edge_id_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edge_id_generator")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vertex1_id", nullable = false)
    private Vertex vertex1;

    @ManyToOne
    @JoinColumn(name = "vertex2_id", nullable = false)
    private Vertex vertex2;

    @Column(name = "cost", nullable = false)
    private Double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
