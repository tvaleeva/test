package ru.amfitel.task.client.tree;

import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.BuildDTO;

/**
 * Created by Bublik on 28.03.2016.
 */
public class BuildItem extends AbstractTreeItem<BuildDTO> {
    BuildItem buildItem;

    public BuildItem(BuildItem buildItem) {
        this.buildItem = buildItem;
    }

    @Override
    ElementType getType() {
        return ElementType.BuildDTO;
    }

    @Override
    BuildItem getObject() {
        return buildItem;
    }
}
