package ru.akhatov.amir.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class Connection {

    @Id
    @SequenceGenerator(name = "connection_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connection_id_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "node_from_id", referencedColumnName = "id")
    private Node nodeFrom;

    @ManyToOne
    @JoinColumn(name = "node_to_id", referencedColumnName = "id")
    private Node nodeTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(Node nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public Node getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(Node nodeTo) {
        this.nodeTo = nodeTo;
    }
}
