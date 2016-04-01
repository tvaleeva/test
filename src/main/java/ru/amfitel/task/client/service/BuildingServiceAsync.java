package ru.amfitel.task.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;

import java.util.List;

public interface BuildingServiceAsync {
    void loadBuildings(AsyncCallback<List<BuildDTO>> async);

    void saveBuildDTO(BuildDTO b, AsyncCallback<Void> async);

    void saveFloorDTO(FloorDTO f, AsyncCallback<Void> async);

    void saveCabinetDTO(CabinetDTO c, AsyncCallback<Void> async);

    void deleteCabinet(Long id, AsyncCallback<Void> async);

    void deleteBuild(Long id, AsyncCallback<Void> async);

    void deleteFloor(Long id, AsyncCallback<Void> async);
}
