package ru.amfitel.task.client.dto;

/**
 * Created by Bublik on 27.03.2016.
 */
public class MaterialDTO extends AbstractDTO {

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
