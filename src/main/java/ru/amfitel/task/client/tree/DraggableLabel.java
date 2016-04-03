package ru.amfitel.task.client.tree;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.editor.DTOEditor;

/**
 * Created by Bublik on 28.03.2016.
 */


public abstract class DraggableLabel<O extends AbstractDTO> extends Label implements DragStartHandler, DropHandler {

    private O object;

    public DraggableLabel(O object) {
        super(object.toString());
        this.object = object;
        getElement().setDraggable(Element.DRAGGABLE_TRUE);
        addDragStartHandler(this);
    }

    //создать нужный эдитор в реализации
    public abstract  DTOEditor getEditor( final AsyncCallback<Void> callback);

    public O getObject() {
        return object;
    }

    @Override
    public void onDragStart(DragStartEvent event) {
        Window.alert("Drag started!");
    }

    @Override
    public void onDrop(DropEvent event) {

    }
}