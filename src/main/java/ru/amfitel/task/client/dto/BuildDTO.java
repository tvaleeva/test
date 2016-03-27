package ru.amfitel.task.client.dto;

import ru.amfitel.task.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Bublik on 27.03.2016.
 */

public class BuildDTO extends AbstractDTO {

    private String name;

    private String address;

    private Date date;

    private MaterialDTO idMaterial;

    private Integer countFloor;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MaterialDTO getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(MaterialDTO idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getCountFloor() {
        return countFloor;
    }

    public void setCountFloor(Integer countFloor) {
        this.countFloor = countFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
