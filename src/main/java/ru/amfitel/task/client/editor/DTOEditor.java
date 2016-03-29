package ru.amfitel.task.client.editor;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.amfitel.task.client.dto.AbstractDTO;

/**
 * @author tvaleeva
 * @since 29.03.2016
 */
public class DTOEditor<D extends AbstractDTO> extends VerticalPanel implements Editor<D> {


    public DTOEditor() {

    }

   public DTOEditor edit(D object) {
        return new DTOEditor();
    }

    ;
}
