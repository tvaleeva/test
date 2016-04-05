package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.editor.FloorEditor;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class FloorDraggableLabel extends DraggableLabel<FloorDTO> {

    public FloorDraggableLabel(FloorDTO object, AsyncCallback<FloorDTO> redrawCallback, DeleteCallback deleteCallback) {
        super(object, redrawCallback, deleteCallback);
    }

    @Override
    public DTOEditor getEditor() {
        return  new FloorEditor(redrawCallback, deleteCallback);
    }

    @Override
    protected boolean isDroppable() {
        return dragging.getObject().getObjectType()== ObjectType.CABINET;
    }

    @Override
    protected void processDrop() {
        //CabinetDTO cabinet = (CabinetDTO) dragging.getObject();
        //cabinet.setIdFloor(getObject().getId());
        //buildingService.saveCabinetDTO(cabinet,redrawCallback);
    }
}
