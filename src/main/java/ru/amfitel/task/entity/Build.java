package ru.amfitel.task.entity;

import ru.amfitel.task.client.dto.FloorDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "build")
public class Build extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private Date date;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_material")
    private ReferenceBook idMaterial;

    @Column(name = "count_floor")
    private Integer countFloor;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buildId", cascade = CascadeType.ALL)
    private List<Floor> floors;

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

    public ReferenceBook getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(ReferenceBook idMaterial) {
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

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
