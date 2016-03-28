package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.FloorTypeDTO;
import ru.amfitel.task.entity.FloorType;

/**
 * Created by Bublik on 27.03.2016.
 */
public class FloorTypeTransformer extends DictionaryTransformer<FloorType, FloorTypeDTO> {

    FloorTypeDTO create() {
        return new FloorTypeDTO();
    }
}