package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.editor.DTOEditor;

/**
 * Created by Bublik on 28.03.2016.
 */


public abstract class AbstractTreeItem<O extends AbstractDTO> extends TreeItem {

    private O object;

    public AbstractTreeItem(O object) {
        this.object = object;
    }

    //создать нужный эдитор в реализации
    public abstract  DTOEditor getEditor();

    public O getObject() {
        return object;
    }



}