package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.ReferenceBookDTO;
import ru.amfitel.task.entity.AbstractReferenceBook;



/**
 * Created by Bublik on 28.03.2016.
 */
public abstract class DictionaryTransformer<O extends AbstractReferenceBook, D extends ReferenceBookDTO> extends AbstractTransformer<O, D> {

    @Override
    public D transform(O object) {
        D result = super.transform(object);
        result.setCode(object.getCode());
        result.setName(object.getName());
        return result;
    }
}
