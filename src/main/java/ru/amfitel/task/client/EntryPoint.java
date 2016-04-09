package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.*;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.BuildEditor;
import ru.amfitel.task.client.editor.CabinetEditor;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.editor.FloorEditor;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;
import ru.amfitel.task.client.tree.DraggableLabel;
import ru.amfitel.task.client.tree.BuildDraggableLabel;
import ru.amfitel.task.client.tree.CabinetDraggableLabel;
import ru.amfitel.task.client.tree.FloorDraggableLabel;
import ru.amfitel.task.client.tree.item.TreeItemWithButton;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    //обращение  к серверу
    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    HorizontalPanel panel;
    VerticalPanel left;
    VerticalPanel right;

    final Tree tree = new Tree();

    final WrappedCallback wrappedDeleteCallback = new WrappedClearCalback(new DeleteCallback(tree));
    final WrappedCallback wrappedReplaceCallback = new WrappedClearCalback(new ReplaceCallback(tree));
    final WrappedCallback wrappedInsertCallback = new WrappedClearCalback(new InsertCallback(tree));


    public void onModuleLoad() {

        panel = new HorizontalPanel();
        RootPanel.get().add(panel);
        left = new VerticalPanel();
        right = new VerticalPanel();
        Anchor a = new Anchor("logout");
        a.setHref(GWT.getHostPageBaseURL()+"logout");
        left.add(a);
        panel.add(left);
        panel.add(right);

        redrawTree(tree);
        left.add(tree);

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> selectionEvent) {

                TreeItem item = selectionEvent.getSelectedItem();
                DraggableLabel label = (DraggableLabel)item.getWidget();
                DTOEditor editor = label.getEditor(wrappedReplaceCallback, wrappedDeleteCallback);
                editor.edit(label.getObject());
                right.clear();
                right.add(editor);

            }
        });
    }

    private void redrawTree(final Tree tree) {
        tree.clear();
        buildingService.loadBuildings(new FailureIgnoreCallback<List<BuildDTO>>() {

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                TreeItemWithButton root = new TreeItemWithButton();
                root.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        right.clear();
                        BuildEditor buildEditor = new BuildEditor(wrappedInsertCallback, wrappedDeleteCallback);
                        right.add(buildEditor);
                        buildEditor.edit(new BuildDTO());
                    }
                });
                //реакция дерева на сохранение объекта
                for (final BuildDTO b : buildDTOs) {
                    TreeItemWithButton buildItem = new TreeItemWithButton(new BuildDraggableLabel(b));
                    buildItem.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            right.clear();
                            FloorEditor floorEditor = new FloorEditor(wrappedInsertCallback, wrappedDeleteCallback);
                            right.add(floorEditor);
                            FloorDTO floorDTO= new FloorDTO();
                            floorDTO.setIdBuild(b.getId());
                            floorEditor.edit(floorDTO);
                        }
                    });
                    for (final FloorDTO f : b.getFloors()) {
                        TreeItemWithButton floorItem = new TreeItemWithButton(new FloorDraggableLabel(f));
                        buildItem.addItem(floorItem);
                        floorItem.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent event) {
                                right.clear();
                                CabinetEditor cabinetEditor = new CabinetEditor(wrappedInsertCallback, wrappedDeleteCallback);
                                right.add(cabinetEditor);
                                CabinetDTO cabinetDTO= new CabinetDTO();
                                cabinetDTO.setIdFloor(f.getId());
                                cabinetEditor.edit(cabinetDTO);

                            }
                        });

                        for (CabinetDTO c : f.getCabinets()) {
                            TreeItem cabinetItem = new TreeItemWithButton(new CabinetDraggableLabel(c));
                            floorItem.addItem(cabinetItem);
                        }
                        /*Button newCabinetButton = new Button("+ кабинет");
                        newCabinetButton.addClickHandler();
                        floorItem.addItem(newCabinetButton);*/
                    }
                    /*Button newFloorButton = new Button("+ этаж");
                    buildItem.addItem(newFloorButton);
                    newFloorButton.addClickHandler();
                    */
                    root.addItem(buildItem);

                }
                tree.addItem(root);
                /*
                Button newBuildingButton = new Button("+ дом");
                tree.addItem(newBuildingButton);
                newBuildingButton.addClickHandler(;


                    */
            }

        });

    }

    class WrappedClearCalback<T> extends WrappedCallback<T> {

        public WrappedClearCalback(AsyncCallback<T> wrapped) {
            super(wrapped);
        }

        @Override
        public void onConstracintViolation(Set<ConstraintViolation<?>> violations) {

        }

        @Override
        public void actionOnSuccess(T t) {
            right.clear();
        }

        @Override
        public void actionOnFailure(Throwable throwable) {

        }
    }
}
