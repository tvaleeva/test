package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.entity.Cabinet;
import ru.amfitel.task.entity.Floor;
import ru.amfitel.task.repository.BuildRepository;
import ru.amfitel.task.repository.CabinetRepository;
import ru.amfitel.task.repository.FloorRepository;
import ru.amfitel.task.transformer.BuildTransformer;
import ru.amfitel.task.transformer.CabinetTransformer;
import ru.amfitel.task.transformer.FloorTransformer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Bublik on 27.03.2016.
 */
@Service("buildingService")
public class BuildingService implements ru.amfitel.task.client.service.BuildingService {

    @Autowired
    private BuildRepository buildRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private CabinetRepository cabinetRepository;


    public List<BuildDTO> loadBuildings() {
        Iterable<Build> iterable = buildRepository.findAll();
        Stream<Build> stream = StreamSupport.stream(iterable.spliterator(), false);
        BuildTransformer transformer = new BuildTransformer();
        List<BuildDTO> result = stream.map(transformer::transform).collect(Collectors.toList());
        return result;
    }

    @Override
    public void saveBuildDTO(BuildDTO b) {
        Build build;
        if (b.getId() == null) {
            build = new Build();
        } else {
            build = buildRepository.findOne(b.getId());
        }

        BuildTransformer transformer = new BuildTransformer();
        transformer.updateEntity(b, build);
        buildRepository.save(build);


    }

    @Override
    public void saveFloorDTO(FloorDTO f) {
        Floor floor;
        if (f.getId() == null) {
            floor = new Floor();
        } else {
            floor = floorRepository.findOne(f.getId());
        }
        FloorTransformer transformer = new FloorTransformer();
        floor.setBuildId(buildRepository.findOne(f.getIdBuild()));
        transformer.updateEntity(f,floor);
        floorRepository.save(floor);
    }

    @Override
    public void saveCabinetDTO(CabinetDTO c) {
        Cabinet cabinet;

        if(c.getId()==null){
            cabinet = new Cabinet();
        } else
        {
            cabinet = cabinetRepository.findOne(c.getId());
        }

        CabinetTransformer transformer = new CabinetTransformer();
        cabinet.setFloorId(floorRepository.findOne(c.getIdFloor()));
        transformer.updateEntity(c,cabinet);
        cabinetRepository.save(cabinet);
    }

    @Override
    public void deleteCabinet(Long id) {
        cabinetRepository.delete(id);
    }

    @Override
    public void deleteBuild(Long id) {
        buildRepository.delete(id);
    }

    @Override
    public void deleteFloor(Long id) {
        floorRepository.delete(id);

    }


}
