package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.dto.BuildDTO;

/**
 * Created by Bublik on 2ito8.03.2016.
 */

public class BuildEditor  extends DTOEditor<BuildDTO>{
    @Editor.Ignore
    BuildEditor editor;
    @Editor.Ignore
    BuildDTO dto;

    interface Driver extends SimpleBeanEditorDriver<BuildDTO, BuildEditor> {
    }

    // Create the Driver
    Driver driver = GWT.create(Driver.class);


    @Override
    public BuildEditor edit(BuildDTO p) {
        // PersonEditor is a DialogBox that extends Editor<Person>
        BuildEditor editor = new BuildEditor();
        // Initialize the driver with the top-level editor
        driver.initialize(editor);
        // Copy the data in the object into the UI
        driver.edit(p);

        return editor;


    }
    public TextBox nameEditor;

    public BuildEditor() {
        nameEditor = new TextBox();
        add(nameEditor);
    }



}


