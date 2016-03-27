package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.ReferenceBookDTO;
import ru.amfitel.task.entity.ReferenceBook;

/**
 * Created by Bublik on 27.03.2016.
 */
public class ReferenceBookTransformer extends AbstractTransformer <ReferenceBook,ReferenceBookDTO>{


    ReferenceBookDTO create() {
        return new ReferenceBookDTO();
    }

    @Override
    public ReferenceBookDTO transform(ReferenceBook object) {
        ReferenceBookDTO materialDTO = super.transform(object);
        materialDTO.setName(object.getName());
        materialDTO.setCode(object.getCode());
        return materialDTO;
    }
}
