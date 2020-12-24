package ru.akhatov.amir.model.entity;

import javax.persistence.*;

@Table(name = "vertexes")
public class Vertex {

    @Id
    @SequenceGenerator(name = "vertex_id_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vertex_id_generator")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
