package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.repository.BuildRepository;
import ru.amfitel.task.transformer.BuildTransformer;

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

    public List<BuildDTO> loadBuildings() {
        Iterable<Build> iterable = buildRepository.findAll();
        Stream<Build> stream = StreamSupport.stream(iterable.spliterator(), false);
        BuildTransformer transformer = new BuildTransformer();
        List<BuildDTO> result = stream.map(transformer::transform).collect(Collectors.toList());
        return result;
    }


}
