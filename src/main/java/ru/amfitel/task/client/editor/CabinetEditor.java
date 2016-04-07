package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dictionary.CabinetType;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class CabinetEditor extends DTOEditor<CabinetDTO> implements ClickHandler {


    public IntegerBox number;
    public DoubleBox square;
    public CabinetTypeEditor type;
    private Label labelNumber;
    private Label labelSquare;
    private Label labelType;
    public Button save;
    public Button deleteButton;

    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    @Override
    public void onClick(ClickEvent clickEvent) {
        CabinetDTO cabinetDTO = driver.flush();
        buildingService.saveCabinetDTO(cabinetDTO,callback);

    }

    interface Driver extends SimpleBeanEditorDriver<CabinetDTO,CabinetEditor>{

     }
    Driver driver = GWT.create(Driver.class);
    public CabinetEditor(AsyncCallback<CabinetDTO> callback, AsyncCallback<CabinetDTO> deleteCallback) {
        super(callback, deleteCallback);
        labelNumber = new Label("№ кабинета: ");
        labelSquare = new Label("Площадь: ");
        labelType = new Label("Тип: ");
        number = new IntegerBox();
        square = new DoubleBox();
        type = new CabinetTypeEditor();
        save = new Button("Сохранить");
        save.addClickHandler(this);
        deleteButton = new Button("Удалить");
        add(labelNumber);
        add(number);
        add(labelSquare);
        add(square);
        add(labelType);
        add(type);
        add(save);

    }

    @Override
    public void edit(final CabinetDTO object) {
        driver.initialize(this);
        driver.edit(object);
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                buildingService.deleteCabinet(object.getId(),deleteCallback);
            }
        });
        if(object.getId()!=null)
            add(deleteButton);

    }

    public IntegerBox number(){
        return number;
    }

    public DoubleBox square(){
        return square;
    }
}
