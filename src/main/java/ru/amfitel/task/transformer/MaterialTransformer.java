package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.MaterialDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.entity.Material;

/**
 * Created by Bublik on 27.03.2016.
 */
public class MaterialTransformer extends AbstractTransformer <Material,MaterialDTO>{


    MaterialDTO create() {
        return new MaterialDTO();
    }

    @Override
    public MaterialDTO transform(Material object) {
        MaterialDTO materialDTO = super.transform(object);
        materialDTO.setName(object.getName());
        materialDTO.setCode(object.getCode());
        return materialDTO;
    }
}
