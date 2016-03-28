package ru.amfitel.task.client.editor;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.amfitel.task.client.dto.BuildDTO;

/**
 * Created by Bublik on 2ito8.03.2016.
 */
public class BuildEditor  extends VerticalPanel implements Editor<BuildDTO>{
    public TextBox nameEditor;

    public BuildEditor() {
        nameEditor = new TextBox();
        add(nameEditor);
    }
}

