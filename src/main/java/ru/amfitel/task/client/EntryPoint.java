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
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    interface Driver extends SimpleBeanEditorDriver<BuildDTO, BuildEditor> {}
    // Create the Driver
    Driver driver = GWT.create(Driver.class);
    HorizontalPanel panel;
    VerticalPanel left;
    VerticalPanel right;


    void edit(BuildDTO p) {
        // PersonEditor is a DialogBox that extends Editor<Person>
        BuildEditor editor = new BuildEditor();
        // Initialize the driver with the top-level editor
        driver.initialize(editor);
        // Copy the data in the object into the UI
        driver.edit(p);
        right.add(editor);

    }

    public void onModuleLoad() {
        panel = new HorizontalPanel();
        RootPanel.get().add(panel);
        left = new VerticalPanel();
        right = new VerticalPanel();
        panel.add(left);
        panel.add(right);










        buildingService.loadBuildings(new AsyncCallback<List<BuildDTO>>() {

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                Tree tree = new Tree();

                for(BuildDTO b : buildDTOs) {
                    TreeItem build = new TreeItem(new Label("Название здания: "));

                    build.setText(b.getName()+" "+b.getIdMaterial().getName());

                    for(FloorDTO f : b.getFloors()) {

                        TreeItem floor = new TreeItem(new Label("Информация по этажам: "));
                        floor.setText(f.getNumber() +" "+f.getFloorTypeDTO().getName()+" "+ f.getSquare() );
                        build.addItem(floor);

                        for (CabinetDTO c : f.getCabinets()) {
                            TreeItem cabinet = new TreeItem(new Label("Информация по кабинетам: "));
                            cabinet.setText(c.getNumber() + " " + c.getCabinetType().getName() + " " + c.getSquare());
                            floor.addItem(cabinet);
                        }
                    }

                    tree.addItem(build);
                    left.add(tree);

                }
                tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
                    @Override
                    public void onSelection(SelectionEvent<TreeItem> selectionEvent) {
                      TreeItem  item= selectionEvent.getSelectedItem();
                        if ( item.getType().equals(ElementType.BuildDTO)){
                            edit(item.getObject());
                        }
                    }
                })
            }

        });
    }
}
