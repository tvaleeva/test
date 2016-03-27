package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    public void onModuleLoad() {


        buildingService.loadBuildings(new AsyncCallback<List<BuildDTO>>() {
            Tree tree = new Tree();
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                for(BuildDTO b : buildDTOs) {
                    TreeItem build = new TreeItem(new Label());
                    build.setText(b.getName()+b.getIdMaterial().getName());
                    tree.addItem(build);
                    RootPanel.get().add(tree);
                }
            }

        });
    }
}
