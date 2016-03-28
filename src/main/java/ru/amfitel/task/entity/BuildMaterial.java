package ru.amfitel.task.entity;

import ru.amfitel.task.client.dto.AbstractDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "build_material")
public class BuildMaterial extends AbstractReferenceBook {

}
