/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lookups;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author lokeshwari
 */
public class NotesPopup extends Window {

    private TextArea notes = new TextArea();
    private Button button = new Button("OK");

    public NotesPopup() {
        super("Notes");
        setClosable(false);
        setModal(true);
        setResizable(false);
        center();
        setContent(addToContent());
        configureFields();
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
    }

    public VerticalLayout addToContent() {
        Panel panel = new Panel();
        VerticalLayout layout = new VerticalLayout();
        Label notesLabel = new Label("Notes:");
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.addComponent(notesLabel);
        hlayout.addComponent(notes);
        hlayout.setSpacing(true);
        layout.addComponent(hlayout);
        layout.addComponent(button);
        layout.setMargin(true);
        return layout;
    }

    public TextArea getNotes() {
        return notes;
    }

    public void setNotes(TextArea notes) {
        this.notes = notes;
    }

    public void configureFields() {
        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
    }
}
