package ru.amfitel.task.client.tree;

import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.FloorDTO;

/**
 * Created by Bublik on 28.03.2016.
 */
public class FloorItem extends AbstractTreeItem<FloorDTO> {
    FloorItem floorItem;

    public FloorItem(FloorItem floorItem) {
        this.floorItem = floorItem;
    }

    @Override
    ElementType getType() {
        return ElementType.FloorDTO;
    }

    @Override
    AbstractTreeItem getObject() {
        return null;
    }
}
