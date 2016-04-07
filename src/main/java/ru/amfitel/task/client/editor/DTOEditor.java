package ru.amfitel.task.client.editor;

import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dto.AbstractDTO;

/**
 * @author tvaleeva
 * @since 29.03.2016
 */
public abstract class DTOEditor<D extends AbstractDTO> extends VerticalPanel implements Editor<D> {

    AsyncCallback<D> callback;

    AsyncCallback<D> deleteCallback;

    public DTOEditor(AsyncCallback<D> callback, AsyncCallback<D> deleteCallback) {
        this.callback = callback;
        this.deleteCallback = deleteCallback;
    }

    public abstract void edit(D object);


}
