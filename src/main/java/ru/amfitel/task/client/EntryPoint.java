package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.BuildEditor;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;
import ru.amfitel.task.client.tree.AbstractTreeItem;
import ru.amfitel.task.client.tree.BuildItem;

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

         final
         Tree tree = new Tree();
        redrawTree(tree);
       final  AsyncCallback<Void> callback = new AsyncCallback<Void>() {
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
                   /* TreeItem build = new TreeItem(new Label("Название здания: "));

                    build.setText(b.getName() + " " + b.getIdMaterial().getName());*/

                    /*for (FloorDTO f : b.getFloors()) {

                        TreeItem floor = new TreeItem(new Label("Информация по этажам: "));
                        floor.setText(f.getNumber() + " " + f.getFloorTypeDTO().getName() + " " + f.getSquare());
                        build.addItem(floor);

                        for (CabinetDTO c : f.getCabinets()) {
                            TreeItem cabinet = new TreeItem(new Label("Информация по кабинетам: "));
                            cabinet.setText(c.getNumber() + " " + c.getCabinetType().getName() + " " + c.getSquare());
                            floor.addItem(cabinet);
                        }
                    }*/

                    tree.addItem(buildItem);
                    left.add(tree);

                }
            }

        });

    }
}
