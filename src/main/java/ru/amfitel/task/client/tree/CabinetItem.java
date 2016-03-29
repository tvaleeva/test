package ru.amfitel.task.client.tree;

import ru.amfitel.task.client.ElementType;
import ru.amfitel.task.client.dto.FloorDTO;

/**
 * Created by Bublik on 28.03.2016.
 */
public class CabinetItem extends AbstractTreeItem<FloorDTO> {
    CabinetItem cabinetItem;

    public CabinetItem(CabinetItem cabinetItem) {
        this.cabinetItem = cabinetItem;
    }

    public CabinetItem() {
    }

    @Override
    ElementType getType() {
        return ElementType.CabinetDTO;
    }

    @Override
    AbstractTreeItem getObject() {
        return null;
    }
}
