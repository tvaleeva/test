package ru.amfitel.task.client.dto;

/**
 * Created by Bublik on 27.03.2016.
 */

public class CabinetDTO extends AbstractDTO {

    private Integer number;

    private Float square;

    private FloorTypeDTO idFloor;

    private  CabinetTypeDTO cabinetType;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public FloorTypeDTO getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(FloorTypeDTO idFloor) {
        this.idFloor = idFloor;
    }

    public CabinetTypeDTO getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(CabinetTypeDTO cabinetType) {
        this.cabinetType = cabinetType;
    }
}
