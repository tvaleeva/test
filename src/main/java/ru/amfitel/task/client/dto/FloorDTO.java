package ru.amfitel.task.client.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */

public class FloorDTO extends AbstractDTO {

    private Integer number;

    private Integer countCabinet;

    private Float square;

    private BuildDTO idBuild;

    private  FloorTypeDTO floorTypeDTO;

    private List<CabinetDTO> cabinets;

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

    public BuildDTO getIdBuild() {
        return idBuild;
    }

    public void setIdBuild(BuildDTO idBuild) {
        this.idBuild = idBuild;
    }

    public FloorTypeDTO getFloorTypeDTO() {
        return floorTypeDTO;
    }

    public void setFloorTypeDTO(FloorTypeDTO floorTypeDTO) {
        this.floorTypeDTO = floorTypeDTO;
    }

    public List<CabinetDTO> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<CabinetDTO> cabinets) {
        this.cabinets = cabinets;
    }

    @Override
    public String toString() {
        return  getNumber()+"/ " +getCountCabinet();
    }
}
