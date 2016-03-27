package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "floor")
public class Floor extends AbstractEntity {

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="type_id")
    private ReferenceBook typeId;

    @Column(name = "count_cabinet")
    private Integer countCabinet;

   @Column(name = "square")
    private Float square;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_build")
    private ReferenceBook buildId;

    @Column(name = "number")
    private Integer number;

    public ReferenceBook getTypeId() {
        return typeId;
    }

    public void setTypeId(ReferenceBook typeId) {
        this.typeId = typeId;
    }

    public Integer getCountCabinet() {
        return countCabinet;
    }

    public void setCountCabinet(Integer countCabinet) {
        this.countCabinet = countCabinet;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public ReferenceBook getBuildId() {
        return buildId;
    }

    public void setBuildId(ReferenceBook buildId) {
        this.buildId = buildId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
