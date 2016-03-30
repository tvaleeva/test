package ru.amfitel.task.client.dto;

/**
 * Created by Bublik on 27.03.2016.
 */

public class CabinetDTO extends AbstractDTO {

    private Integer number;

    private Double square;

    private Long idFloor;



    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Long getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(Long idFloor) {
        this.idFloor = idFloor;
    }



    @Override
    public String toString() {
        return getNumber()+"";
    }
}
