package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

/**
 * Created by Bublik on 2ito8.03.2016.
 */

public class BuildEditor extends DTOEditor<BuildDTO> implements ClickHandler {

    public TextBox name;
    public DateBox date;
    public TextBox address;
    public IntegerBox countFloor;
    public MaterialEditor material;
    public Button saveButton;
    public Button deleteButton;
    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);
    // Create the Driver
    Driver driver = GWT.create(Driver.class);

    public BuildEditor(AsyncCallback<Void> callback) {
        super(callback);
        name = new TextBox();
        name.setName("Название");
        date = new DateBox();
        address = new TextBox();
        countFloor = new IntegerBox();
        material = new MaterialEditor();

        deleteButton = new Button("Удалить");
        saveButton = new Button("Сохранить");
        saveButton.addClickHandler(this);


        add(name);
        add(date);
        add(address);
        add(countFloor);
        add(material);
        add(saveButton);


    }

    @Override
    public void onClick(ClickEvent clickEvent) {
        BuildDTO buildDTO = driver.flush();
        buildingService.saveBuildDTO(buildDTO, callback);
    }

    @Override
    public void edit(final BuildDTO p) {
        driver.initialize(this);
        // Copy the data in the object into the UI
        driver.edit(p);
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                buildingService.deleteBuild(p.getId(), callback);
            }
        });
        if (p.getId() != null)
            add(deleteButton);
    }

    public TextBox name() {
        return name;
    }

    public DateBox date() {
        return date;
    }

    public TextBox address() {
        return address;
    }

    public IntegerBox countFloor() {
        return countFloor;
    }

    interface Driver extends SimpleBeanEditorDriver<BuildDTO, BuildEditor> {
    }
}


