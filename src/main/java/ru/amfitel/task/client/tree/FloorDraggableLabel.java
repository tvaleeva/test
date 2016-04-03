package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.editor.FloorEditor;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class FloorDraggableLabel extends DraggableLabel<FloorDTO> {
    public FloorDraggableLabel(FloorDTO object) {
        super(object);
    }

    @Override
    public DTOEditor getEditor(AsyncCallback<Void> callback) {
        return  new FloorEditor(callback);
    }
}
