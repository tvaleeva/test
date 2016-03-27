package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.entity.Build;

/**
 * Created by Bublik on 27.03.2016.
 */
public class BuildTransformer  extends AbstractTransformer <Build,BuildDTO>{


    BuildDTO create() {
        return new BuildDTO();
    }

    @Override
    public BuildDTO transform(Build object) {
        BuildDTO buildDTO = super.transform(object);
        buildDTO.setName(object.getName());
        buildDTO.setIdMaterial(new MaterialTransformer().transform(object.getIdMaterial()));
        buildDTO.setAddress(object.getAddress());
        buildDTO.setCountFloor(object.getCountFloor());
        buildDTO.setDate(object.getDate());
        return buildDTO;
    }
}
