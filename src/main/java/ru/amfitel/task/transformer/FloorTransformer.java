package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.dto.ReferenceBookDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.entity.Floor;

/**
 * Created by Bublik on 27.03.2016.
 */
public class FloorTransformer extends AbstractTransformer <Floor,FloorDTO>{


    FloorDTO create() {
        return new FloorDTO();
    }

    @Override
    public FloorDTO transform(Floor object) {
        FloorDTO floorDTO = super.transform(object);
        floorDTO.setCountCabinet(object.getCountCabinet());
        floorDTO.setNumber(object.getNumber());
        floorDTO.setSquare(object.getSquare());
        floorDTO.setFloorTypeDTO(new ReferenceBookTransformer().transform(object.getTypeId()));
        floorDTO.setIdBuild(new ReferenceBookTransformer().transform(object.getBuildId()));
        return floorDTO;
    }
}
