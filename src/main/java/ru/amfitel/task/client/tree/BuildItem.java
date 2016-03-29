package ru.amfitel.task.client.tree;

import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.editor.BuildEditor;
import ru.amfitel.task.client.editor.DTOEditor;

/**
 * Created by Bublik on 28.03.2016.
 */
public class BuildItem extends AbstractTreeItem<BuildDTO> {


    public BuildItem(BuildDTO object) {
        super(object);
    }


    @Override
    public DTOEditor getEditor() {
        return new BuildEditor();
    }

    @Override
    public BuildDTO getObject(){
        return  new BuildDTO();
    }
}