package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import ru.amfitel.task.client.dto.BuildDTO;
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

    public IntegerBox countCabinet;

    public Button save;

    @Override
    public void onClick(ClickEvent clickEvent) {
        FloorDTO floatDto = driver.flush();
        buildingService.saveFloorDTO(floatDto,callback);

    }


    interface Driver extends SimpleBeanEditorDriver<FloorDTO, FloorEditor> {

    }

    Driver driver = GWT.create(Driver.class);

    public FloorEditor(AsyncCallback<Void> callback) {
        super(callback);
        number = new IntegerBox();
        countCabinet = new IntegerBox();
        save = new Button("Сохранить");
        save.addClickHandler(this);

        add(number);
        add(countCabinet);
        add(save);
    }


    @Override
    public void edit(FloorDTO object) {

        driver.initialize(this);
        driver.edit(object);
    }

    private IntegerBox number(){
        return number;
    }

}
