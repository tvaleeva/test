package ru.amfitel.task.client.dto;

import java.util.Date;

/**
 * Created by Bublik on 27.03.2016.
 */

public class FloorDTO extends AbstractDTO {

    private Integer number;

    private Integer countCabinet;

    private Float square;

    private ReferenceBookDTO idBuild;

    private  ReferenceBookDTO floorTypeDTO;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public ReferenceBookDTO getFloorTypeDTO() {
        return floorTypeDTO;
    }

    public void setFloorTypeDTO(ReferenceBookDTO floorTypeDTO) {
        this.floorTypeDTO = floorTypeDTO;
    }

    public ReferenceBookDTO getIdBuild() {
        return idBuild;
    }

    public void setIdBuild(ReferenceBookDTO idBuild) {
        this.idBuild = idBuild;
    }
}
