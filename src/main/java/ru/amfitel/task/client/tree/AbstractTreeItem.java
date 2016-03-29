package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.AbstractDTO;

/**
 * Created by Bublik on 28.03.2016.
 */
public abstract class AbstractTreeItem<O extends AbstractDTO> extends TreeItem {

    abstract ElementType getType();

    public Long getId(O object) {
        return object.getId();
    }

    abstract AbstractTreeItem getObject();

}
