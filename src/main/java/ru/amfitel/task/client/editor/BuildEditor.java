package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

/**
 * Created by Bublik on 2ito8.03.2016.
 */

public class BuildEditor  extends DTOEditor<BuildDTO> implements ClickHandler{
    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);


    @Override
    public void onClick(ClickEvent clickEvent) {
        BuildDTO buildDTO = driver.flush();
        buildingService.saveBuildDTO(buildDTO, callback);
    }

    interface Driver extends SimpleBeanEditorDriver<BuildDTO, BuildEditor> {
    }

    // Create the Driver
    Driver driver = GWT.create(Driver.class);


    @Override
    public void edit(BuildDTO p) {
        driver.initialize(this);
        // Copy the data in the object into the UI
        driver.edit(p);
    }

    public TextBox name;

    public Button saveButton;

    public BuildEditor(AsyncCallback<Void> callback) {
        super(callback);
        name = new TextBox();

        saveButton = new Button("Сохранить");
        saveButton.addClickHandler(this);

        add(name);
        add(saveButton);
    }

    public TextBox name() {
        return name;
    }
}


