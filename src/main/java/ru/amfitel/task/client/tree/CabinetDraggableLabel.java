package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.editor.CabinetEditor;
import ru.amfitel.task.client.editor.DTOEditor;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class CabinetDraggableLabel extends DraggableLabel<CabinetDTO> {
    public CabinetDraggableLabel(CabinetDTO object, AsyncCallback<Void> redrawCallback) {
        super(object, redrawCallback);
    }

    @Override
    public DTOEditor getEditor() {
        return new CabinetEditor(redrawCallback);
    }

    @Override
    protected boolean isDroppable() {
        return false;
    }

    @Override
    protected void processDrop() {
        //do nothing
    }
}
