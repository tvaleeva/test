package ru.amfitel.task.client.editor;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.amfitel.task.client.dto.AbstractDTO;

/**
 * @author tvaleeva
 * @since 29.03.2016
 */
public abstract class DTOEditor<D extends AbstractDTO> extends VerticalPanel implements Editor<D> {

    AsyncCallback<Void> callback;

    public DTOEditor(AsyncCallback<Void> callback) {
        this.callback = callback;
    }

    public abstract void edit(D object);

}
