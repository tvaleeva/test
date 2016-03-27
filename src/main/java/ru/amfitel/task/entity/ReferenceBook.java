package ru.amfitel.task.entity;

import ru.amfitel.task.client.dto.AbstractDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "build_material")
public class ReferenceBook extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
