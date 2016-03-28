package ru.amfitel.task.entity;

import javax.persistence.*;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "cabinet")
public class Cabinet extends AbstractEntity {

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="type_id")
    private CabinetType typeId;

   @Column(name = "square")
    private Float square;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="floor_id")
    private Floor floorId;

    @Column(name = "number")
    private Integer number;

    public CabinetType getTypeId() {
        return typeId;
    }

    public void setTypeId(CabinetType typeId) {
        this.typeId = typeId;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public Floor getFloorId() {
        return floorId;
    }

    public void setFloorId(Floor floorId) {
        this.floorId = floorId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
