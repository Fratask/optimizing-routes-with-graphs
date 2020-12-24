package ru.akhatov.amir.model.entity;

import javax.persistence.*;

@Table(name = "edges")
public class Edge {

    @Id
    @SequenceGenerator(name = "edge_id_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edge_id_generator")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    //TODO: Annotate
    private Vertex vertex1;

    private Vertex vertex2;

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
}
