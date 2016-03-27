package ru.amfitel.task.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Bublik on 27.03.2016.
 */
@MappedSuperclass
public class AbstractEntity {
    @Id
    @Column(name = "id")
    public Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

