package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.CabinetTypeDTO;
import ru.amfitel.task.client.dto.FloorTypeDTO;
import ru.amfitel.task.entity.CabinetType;
import ru.amfitel.task.entity.FloorType;

/**
 * Created by Bublik on 27.03.2016.
 */
public class CabinetTypeTransformer extends DictionaryTransformer <CabinetType,CabinetTypeDTO>{


    CabinetTypeDTO create() {
        return new CabinetTypeDTO();
    }
}