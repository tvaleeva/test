package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "floor")
@SequenceGenerator(name = "default_gen", sequenceName = "id_cabinet_seq", allocationSize = 1)
//TODO
public class Floor extends AbstractEntity {



    @Column(name = "count_cabinet")
    private Integer countCabinet;

   @Column(name = "square")
    private Float square;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_build")
    private Build buildId;

    @Column(name = "number")
    private Integer number;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "floorId", cascade = CascadeType.ALL)
    private List<Cabinet> cabinets;



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

    public Build getBuildId() {
        return buildId;
    }

    public void setBuildId(Build buildId) {
        this.buildId = buildId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Cabinet> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<Cabinet> cabinets) {
        this.cabinets = cabinets;
    }
}
