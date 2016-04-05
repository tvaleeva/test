package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class FloorEditor extends DTOEditor<FloorDTO> implements ClickHandler  {

    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    public IntegerBox number;

    private Label labelNumber;

    public IntegerBox countCabinet;

    private Label labelCount;

    public FloorTypeEditor type;

    private Label labelType;

    public Button save;

    public Button deleteButton;

    @Override
    public void onClick(ClickEvent clickEvent) {
        FloorDTO floatDto = driver.flush();
        buildingService.saveFloorDTO(floatDto,callback);
    }


    interface Driver extends SimpleBeanEditorDriver<FloorDTO, FloorEditor> {

    }

    Driver driver = GWT.create(Driver.class);

    public FloorEditor(AsyncCallback<FloorDTO> callback, DeleteCallback deleteCallback) {
        super(callback, deleteCallback);
        labelNumber = new Label("№ этажа: ");
        labelCount = new Label("Кол-во кабинетов: ");
        labelType = new Label("Тип: ");
        number = new IntegerBox();
        countCabinet = new IntegerBox();
        type = new FloorTypeEditor();
        save = new Button("Сохранить");
        deleteButton = new Button("Удалить");
        save.addClickHandler(this);

        add(labelNumber);
        add(number);
        add(labelCount);
        add(countCabinet);
        add(labelType);
        add(type);
        add(save);

    }


    @Override
    public void edit(final FloorDTO object) {

        driver.initialize(this);
        driver.edit(object);
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                buildingService.deleteFloor(object.getId(),deleteCallback);
            }
        });
        if (object.getId()!=null)
            add(deleteButton);
    }

    private IntegerBox number(){
        return number;
    }

}
