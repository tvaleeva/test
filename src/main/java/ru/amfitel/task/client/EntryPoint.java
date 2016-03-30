package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.CabinetEditor;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;
import ru.amfitel.task.client.tree.AbstractTreeItem;
import ru.amfitel.task.client.tree.BuildItem;
import ru.amfitel.task.client.tree.CabinetItem;
import ru.amfitel.task.client.tree.FloorItem;

import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    //обращение  к серверу
    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    HorizontalPanel panel;
    VerticalPanel left;
    VerticalPanel right;


    public void onModuleLoad() {

        panel = new HorizontalPanel();
        RootPanel.get().add(panel);
        left = new VerticalPanel();
        right = new VerticalPanel();
        panel.add(left);
        panel.add(right);

        final Tree tree = new Tree();
        redrawTree(tree);
        left.add(tree);
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(Void aVoid) {
                //в случае успеха перерисуем дерево
                right.clear();
                redrawTree(tree);
            }
        };

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> selectionEvent) {

                AbstractTreeItem item = (AbstractTreeItem) selectionEvent.getSelectedItem();
                DTOEditor editor = item.getEditor(callback);
                editor.edit(item.getObject());
                right.clear();
                right.add(editor);
            }
        });
    }

    private void redrawTree(final Tree tree) {
        tree.clear();
        buildingService.loadBuildings(new AsyncCallback<List<BuildDTO>>() {

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                //реакция дерева на сохранение объекта
                for (BuildDTO b : buildDTOs) {
                    BuildItem buildItem = new BuildItem(b);


                    for (final FloorDTO f : b.getFloors()) {
                        FloorItem floorItem = new FloorItem(f);

                       buildItem.addItem(floorItem);

                        for (CabinetDTO c : f.getCabinets()) {
                            CabinetItem cabinetItem = new CabinetItem(c);
                           floorItem.addItem(cabinetItem);
                        }
                        Button newCabinetButton = new Button("+ каб");
                        newCabinetButton.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent event) {
                                CabinetEditor cabinetEditor = new CabinetEditor(new AsyncCallback<Void>() {
                                    @Override
                                    public void onFailure(Throwable caught) {

                                    }

                                    @Override
                                    public void onSuccess(Void result) {
                                        right.clear();
                                        redrawTree(tree);
                                    }
                                });
                                right.add(cabinetEditor);
                                CabinetDTO cabinetDTO= new CabinetDTO();
                                cabinetDTO.setIdFloor(f.getId());
                                cabinetEditor.edit(cabinetDTO);

                            }
                        });
                        floorItem.addItem(newCabinetButton);
                    }
                    Button newFloorButton = new Button("+ эт");
                    buildItem.addItem(newFloorButton);

                    tree.addItem(buildItem);

                }
                Button newBuildingButton = new Button("+ дом");
                tree.addItem(newBuildingButton);
            }

        });

    }
}
