package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildMaterialDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.dto.ReferenceBookDTO;
import ru.amfitel.task.entity.BuildMaterial;
import ru.amfitel.task.entity.Floor;

import static javafx.scene.input.KeyCode.O;

/**
 * Created by Bublik on 27.03.2016.
 */
public class BuildMaterialTransformer extends AbstractTransformer <BuildMaterial,BuildMaterialDTO>{


    BuildMaterialDTO create() {
        return new BuildMaterialDTO();
    }

    @Override
    public BuildMaterialDTO transform(BuildMaterial object) {
        BuildMaterialDTO b  = super.transform(object);
        b.setCode(object.getCode());
        b.setName(object.getName());
        return b;
    }
}