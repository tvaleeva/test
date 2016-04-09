package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.BuildEditor;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.entity.Floor;

/**
 * Created by Bublik on 28.03.2016.
 */
public class BuildDraggableLabel extends DraggableLabel<BuildDTO> {

    public BuildDraggableLabel(BuildDTO object) {
        super(object);
    }

    @Override
    public DTOEditor getEditor(AsyncCallback<BuildDTO> redrawCallback, AsyncCallback<BuildDTO> deleteCallback) {
        return new BuildEditor(redrawCallback, deleteCallback);
    }

    @Override
    protected boolean isDroppable() {
        return dragging.getObject().getObjectType()== ObjectType.FLOOR;
    }

}
