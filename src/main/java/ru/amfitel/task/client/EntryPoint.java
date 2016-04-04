package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.FailureIgnoreCallback;
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

    final Tree tree = new Tree();
    final FailureIgnoreCallback<Void> callback = new FailureIgnoreCallback<Void>() {

        @Override
        public void onSuccess(Void aVoid) {
            //в случае успеха перерисуем дерево
            refreshTree(tree);
        }
    };


    public void onModuleLoad() {

        panel = new HorizontalPanel();
        RootPanel.get().add(panel);
        left = new VerticalPanel();
        right = new VerticalPanel();
        Anchor a = new Anchor("logout");
        a.setHref("/logout");
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
                DTOEditor editor = label.getEditor();
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
                //реакция дерева на сохранение объекта
                for (final BuildDTO b : buildDTOs) {
                    TreeItem buildItem = new TreeItem(new BuildDraggableLabel(b,callback ));



                    for (final FloorDTO f : b.getFloors()) {


                        TreeItem floorItem = new TreeItem(new FloorDraggableLabel(f, callback));
                        buildItem.addItem(floorItem);

                        for (CabinetDTO c : f.getCabinets()) {
                            TreeItem cabinetItem = new TreeItem(new CabinetDraggableLabel(c, callback));


                           floorItem.addItem(cabinetItem);
                        }
                        Button newCabinetButton = new Button("+ кабинет");
                        newCabinetButton.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent event) {
                                right.clear();
                                CabinetEditor cabinetEditor = new CabinetEditor(new FailureIgnoreCallback<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        refreshTree(tree);
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
                    Button newFloorButton = new Button("+ этаж");
                    buildItem.addItem(newFloorButton);
                    newFloorButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            right.clear();

                            FloorEditor floorEditor = new FloorEditor(new FailureIgnoreCallback<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    refreshTree(tree);
                                }
                            });


                            right.add(floorEditor);
                            FloorDTO floorDTO= new FloorDTO();
                            floorDTO.setIdBuild(b.getId());
                            floorEditor.edit(floorDTO);

                        }
                    });

                    tree.addItem(buildItem);

                }
                Button newBuildingButton = new Button("+ дом");
                tree.addItem(newBuildingButton);
                newBuildingButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        right.clear();
                        BuildEditor buildEditor = new BuildEditor(new FailureIgnoreCallback<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                refreshTree(tree);
                            }
                        });

                        right.add(buildEditor);
                        buildEditor.edit(new BuildDTO());

                    }
                });
            }

        });

    }

    private void refreshTree(Tree tree) {
        right.clear();
        redrawTree(tree);
    }
}
