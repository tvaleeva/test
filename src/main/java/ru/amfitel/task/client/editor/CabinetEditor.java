package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
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
    public Button save;

    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    @Override
    public void onClick(ClickEvent clickEvent) {
        CabinetDTO cabinetDTO = driver.flush();
        buildingService.saveCabinetDTO(cabinetDTO,callback);

    }

    interface Driver extends SimpleBeanEditorDriver<CabinetDTO,CabinetEditor>{

     }
    Driver driver = GWT.create(Driver.class);
    public CabinetEditor(AsyncCallback<Void> callback) {
        super(callback);
        number = new IntegerBox();
        square = new DoubleBox();
        type = new CabinetTypeEditor();
        save = new Button("Сохранить");
        save.addClickHandler(this);
        add(number);
        add(square);
        add(type);
        add(save);
    }

    @Override
    public void edit(CabinetDTO object) {
        driver.initialize(this);
        driver.edit(object);

    }

    public IntegerBox number(){
        return number;
    }

    public DoubleBox square(){
        return square;
    }
}
