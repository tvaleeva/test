package ru.amfitel.task.client.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import org.springframework.scheduling.annotation.Async;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

/**
 * Created by Bublik on 28.03.2016.
 */


public abstract class DraggableLabel<O extends AbstractDTO> extends Label implements DragStartHandler, DropHandler, DragOverHandler {
    protected static DraggableLabel dragging = null;
    private O object;

    public DraggableLabel(O object) {
        super();
        this.object = object;
        updateText();
        getElement().setDraggable(Element.DRAGGABLE_TRUE);
        addDragStartHandler(this);
        addDragOverHandler(this);
        addDropHandler(this);
    }

    private void updateText() {
        setText(object.toString());
    }

    //создать нужный эдитор в реализации
    public abstract DTOEditor getEditor(AsyncCallback<O> insert, AsyncCallback<O> delete);

    public O getObject() {
        return object;
    }

    public  void setObject(O object){
        this.object=object;
        updateText();
    }

    @Override
    public void onDragStart(DragStartEvent event) {
        dragging = this;
    }

    protected abstract boolean isDroppable();

    protected abstract void processDrop();

    @Override
    public void onDrop(DropEvent event) {
        event.preventDefault();
        processDrop();

    }

    @Override
    public void onDragOver(DragOverEvent event) {
        if (isDroppable()) {
            event.preventDefault();
        }
    }
}