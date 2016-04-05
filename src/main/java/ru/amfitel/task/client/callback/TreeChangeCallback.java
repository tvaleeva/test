package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.ui.HasTreeItems;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.tree.DraggableLabel;

/**
 * Created by Bublik on 05.04.2016.
 */
public abstract class TreeChangeCallback<T> extends FailureIgnoreCallback<T> {

    protected Tree tree;

    public TreeChangeCallback(Tree tree) {
        this.tree = tree;
    }

    protected TreeItem findElement(AbstractDTO abstractDTO) {
       return findElement(abstractDTO.getObjectType(),abstractDTO.getId());

    }

    protected TreeItem findElement(ObjectType type, Long id) {
        for (int i = 0; i < tree.getItemCount(); i++) {
            TreeItem treeItem = tree.getItem(i);
            Widget widget = treeItem.getWidget();
            if (widget instanceof DraggableLabel){
                //to
                DraggableLabel draggableLabel = (DraggableLabel) treeItem.getWidget();
                AbstractDTO abstractDTO = draggableLabel.getObject();
                if (abstractDTO.getObjectType() == type && abstractDTO.getId().equals(id)) {
                    return treeItem;
                }
            }
        }
        return null;
    }

    private TreeItem find (TreeItem parent, ObjectType type, Long id) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            TreeItem child = parent.getChild(i);
            //if (child)

        }
        return null;
    }




}
