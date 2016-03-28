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
public class BuildMaterialTransformer extends DictionaryTransformer <BuildMaterial,BuildMaterialDTO>{


    BuildMaterialDTO create() {
        return new BuildMaterialDTO();
    }

   }