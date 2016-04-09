package ru.amfitel.task.client.tree.item;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Bublik on 09.04.2016.
 */
public class TreeItemWithButton extends TreeItem implements HasClickHandlers{

    Button button;

    TreeItem buttonItem;

    public TreeItemWithButton() {
        button = new Button("+");
        buttonItem = addItem(button);
    }

    public TreeItemWithButton(SafeHtml html) {
        super(html);
        button = new Button("+");
        buttonItem = addItem(button);
    }

    public TreeItemWithButton(Widget widget) {
        super(widget);
        button = new Button("+");
        buttonItem = addItem(button);
    }

    @Override
    public void addItem(IsTreeItem isItem) {
        super.addItem(isItem);
        moveButtonDown(isItem);
    }

    @Override
    public TreeItem addItem(SafeHtml itemHtml) {
        TreeItem newItem = super.addItem(itemHtml);
        moveButtonDown(newItem);
        return newItem;
    }

    @Override
    public void addItem(TreeItem item) {
        super.addItem(item);
        moveButtonDown(item);
    }

    @Override
    public TreeItem addItem(Widget widget) {
        TreeItem newItem = super.addItem(widget);
        moveButtonDown(newItem);
        return newItem;
    }

    private void moveButtonDown(IsTreeItem treeItem){
        if (buttonItem != null && treeItem != buttonItem){
            removeItem(buttonItem);
            addItem(buttonItem);
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return button.addClickHandler(handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        button.fireEvent(event);
    }
}
