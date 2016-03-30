package ru.amfitel.task.entity;

import javax.persistence.*;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "cabinet")
@SequenceGenerator(name = "default_gen", sequenceName = "id_cabinet_seq", allocationSize = 1)
public class Cabinet extends AbstractEntity {


   @Column(name = "square")
    private Double square;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="floor_id")
    private Floor floorId;

    @Column(name = "number")
    private Integer number;



    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
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
