package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
import ru.amfitel.task.client.tree.BuildDraggableLabel;
import ru.amfitel.task.client.tree.CabinetDraggableLabel;
import ru.amfitel.task.client.tree.DraggableLabel;
import ru.amfitel.task.client.tree.FloorDraggableLabel;
import ru.amfitel.task.client.tree.item.TreeItemWithButton;

import java.util.ArrayList;
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

    final AsyncCallback clearCallback = new AsyncCallback() {
        @Override
        public void onFailure(Throwable caught) {
            right.clear();
        }

        @Override
        public void onSuccess(Object result) {
            right.clear();
        }
    };

    final AsyncCallback deleteCallback = new DeleteCallback(tree);
    final AsyncCallback replaceCallback = new ReplaceCallback(tree);
    final AsyncCallback insertCallback = new InsertCallback(tree);


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

        //AsyncCallback[] replace = new AsyncCallback[]{clearCallback, replaceCallback};
        //AsyncCallback[] delete = new AsyncCallback[]{clearCallback, deleteCallback};
        List<AsyncCallback<AbstractDTO>> delete = new ArrayList<AsyncCallback<AbstractDTO>>();
        delete.add(clearCallback);
        delete.add(deleteCallback);

        List<AsyncCallback<AbstractDTO>> replace = new ArrayList<AsyncCallback<AbstractDTO>>();
        delete.add(clearCallback);
        delete.add(replaceCallback);

        final WrappedCallback replaceCallback = new WrappedCallback(replace);
        final WrappedCallback deleteCallback = new WrappedCallback(delete);

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> selectionEvent) {

                TreeItem item = selectionEvent.getSelectedItem();
                DraggableLabel label = (DraggableLabel)item.getWidget();
                DTOEditor editor = label.getEditor(replaceCallback, deleteCallback);
                editor.edit(label.getObject());
                right.clear();
                right.add(editor);

            }
        });
    }

    private void redrawTree(final Tree tree) {

        tree.clear();
        List<AsyncCallback<AbstractDTO>> delete = new ArrayList<AsyncCallback<AbstractDTO>>();
        delete.add(clearCallback);
        delete.add(deleteCallback);

        List<AsyncCallback<AbstractDTO>> insert = new ArrayList<AsyncCallback<AbstractDTO>>();
        delete.add(clearCallback);
        delete.add(insertCallback);

        final WrappedCallback insertCallback = new WrappedCallback(insert);
        final WrappedCallback deleteCallback = new WrappedCallback(delete);

       buildingService.loadBuildings(new FailureIgnoreCallback<List<BuildDTO>>() {

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                TreeItemWithButton root = new TreeItemWithButton();
                root.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        right.clear();
                        BuildEditor buildEditor = new BuildEditor(insertCallback, deleteCallback);
                        right.add(buildEditor);
                        buildEditor.edit(new BuildDTO());
                    }
                });
                //реакция дерева на сохранение объекта
                for (final BuildDTO b : buildDTOs) {
                    DraggableLabel<BuildDTO> bdl = new BuildDraggableLabel(b);
                    bdl.addDropHandler(new DropHandler() {
                        @Override
                        public void onDrop(DropEvent event) {
                            event.preventDefault();
                            FloorDTO floorDTO = (FloorDTO) DraggableLabel.getDragging().getObject();
                            floorDTO.setIdBuild(b.getId());

                            List<AsyncCallback<AbstractDTO>> deleteAndInsert = new ArrayList<AsyncCallback<AbstractDTO>>();
                            deleteAndInsert.add(deleteCallback);
                            deleteAndInsert.add(insertCallback);


                            final WrappedCallback deleteAndInsertCallback = new WrappedCallback(deleteAndInsert);

                            buildingService.saveFloorDTO(floorDTO, deleteAndInsertCallback);
                        }
                    });
                    TreeItemWithButton buildItem = new TreeItemWithButton();
                    buildItem.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            right.clear();
                            FloorEditor floorEditor = new FloorEditor(insertCallback, deleteCallback);
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
                                CabinetEditor cabinetEditor = new CabinetEditor(insertCallback, deleteCallback);
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
                    }
                    root.addItem(buildItem);

                }
                tree.addItem(root);
            }

        });

    }
}
