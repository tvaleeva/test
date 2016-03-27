package ru.amfitel.task.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import ru.amfitel.task.client.dto.BuildDTO;

import java.util.List;

public interface BuildingServiceAsync {
    void loadBuildings(AsyncCallback<List<BuildDTO>> async);
}
