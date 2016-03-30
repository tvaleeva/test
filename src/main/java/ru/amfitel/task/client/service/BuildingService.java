package ru.amfitel.task.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;

import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
@RemoteServiceRelativePath("springGwtServlet/buildingService")
public interface BuildingService extends RemoteService {

    List<BuildDTO> loadBuildings();
    void saveBuildDTO(BuildDTO b);
    void saveFloorDTO(FloorDTO f);
    void saveCabinetDTO(CabinetDTO c);
}
