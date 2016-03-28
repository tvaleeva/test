package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.BuildMaterialDTO;
import ru.amfitel.task.client.dto.FloorTypeDTO;
import ru.amfitel.task.entity.BuildMaterial;
import ru.amfitel.task.entity.FloorType;

/**
 * Created by Bublik on 27.03.2016.
 */
public class FloorTypeTransformer extends AbstractTransformer <FloorType,FloorTypeDTO>{


    FloorTypeDTO create() {
        return new FloorTypeDTO();
    }

    @Override
    public FloorTypeDTO transform(FloorType object) {
        FloorTypeDTO b  = super.transform(object);
        b.setCode(object.getCode());
        b.setName(object.getName());
        return b;
    }
}