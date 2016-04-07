package ru.amfitel.task.client.dto;

import com.google.gwt.i18n.client.DateTimeFormat;
import ru.amfitel.task.client.dictionary.Material;
import ru.amfitel.task.client.dictionary.ObjectType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */

public class BuildDTO extends AbstractDTO {

    @NotNull
    private String name;
    @NotNull
    private String address;

    @NotNull
    private Date date;

    @NotNull
    private Material material;

    @NotNull
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
    public ObjectType getObjectType(){
        return ObjectType.BUILDING;
    }

    @Override
    public String toString() {

        return "Название:" + name +
                "/ Адрес:" +address +
                "/ Дата постройки:" + (date==null? null: DateTimeFormat.getFormat("dd.M.y").format(date)) +
                "/ Материал:" + (material == null ? null : material.getName()) +
                "/ Кол-во этажей:" + getCountFloor();
    }
}
