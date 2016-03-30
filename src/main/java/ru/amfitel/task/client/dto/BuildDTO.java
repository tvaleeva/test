package ru.amfitel.task.client.dto;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */

public class BuildDTO extends AbstractDTO {

    private String name;

    private String address;

    private Date date;

    private ReferenceBookDTO idMaterial;

    private Integer countFloor;

    private List<FloorDTO> floors;

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

    public ReferenceBookDTO getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(ReferenceBookDTO idMaterial) {
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

    public List<FloorDTO> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorDTO> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {


        return getName() + "/ " + DateTimeFormat.getFormat("d.M.y").format(getDate()) + "/ " +getAddress()+"/ "+ getIdMaterial().getName();
    }
}
