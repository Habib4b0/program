/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.serverlogging.form;

import com.stpl.app.gtnutilities.serverlogging.dto.LoggingDto;
import com.stpl.app.gtnutilities.serverlogging.logic.SearchLogic;
import com.stpl.app.gtnutilities.util.Constants;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class ViewLog extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ViewLog.class);
    private final SearchLogic searchLogic = SearchLogic.getInstance();
    @UiField("refreshBtn")
    private Button refreshBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("backBtn")
    private Button backBtn;
    @UiField("linesFrom")
    private TextField lineTo;
    @UiField("filterText")
    private TextField filterText;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
    @UiField("fileName")
    private ComboBox fileName;
    @UiField("customCommand")
    private TextField customCommand;
    // Create a  text area
    private final TextArea textViewer = new TextArea();
    /**
     * String name.
     */
    public static final String NAME = Constants.SERVER_LOGGING;
    /**
     * to store result
     */
    private String result =  Constants.EMPTY;

    /**
     * Instantiates a ViewLog
     */
    public ViewLog() {

        super();
        try {
            init();
            configureFields();
            addTextArea();
            addFieldListeners();
            addButtonListeners();
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    /**
     * Inits the UI.
     *
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/LoggingView.xml"), this));

    }

    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter of ViewLog Class");
        configureFields();
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        lineTo.setValue("20");
        filterText.setValue(Constants.EMPTY);
        customCommand.setValue(Constants.EMPTY);
        String logDestinationValue = String.valueOf(VaadinSession.getCurrent().getAttribute("LOG_DESTINATION"));
        loadFileNameDdlb();
        if (logDestinationValue != null) {
            fileName.setValue(logDestinationValue);
        }
        result = searchFile(String.valueOf(fileName.getValue()), String.valueOf(lineTo.getValue()), String.valueOf(filterText.getValue()), Constants.EMPTY);
        textViewer.setValue(result);

    }

    /**
     * button logic
     */
    private void addButtonListeners() {
        refreshBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                result = searchFile(String.valueOf(fileName.getValue()), String.valueOf(lineTo.getValue()), String.valueOf(filterText.getValue()), Constants.EMPTY);
                textViewer.setValue(result);
            }
        });
        backBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(LoggingSearchView.NAME);
            }
        });
        resetBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    configureFields();
                } catch (Exception e) {
                    LOGGER.debug(e);
                }

            }
        });
    }

    void addFieldListeners() {
        Property.ValueChangeListener valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                result = searchFile(String.valueOf(fileName.getValue()), String.valueOf(lineTo.getValue()),
                        String.valueOf(filterText.getValue()), Constants.EMPTY);
                textViewer.setValue(result);
            }
        };
        lineTo.addValueChangeListener(valueChangeListener);
        fileName.addValueChangeListener(valueChangeListener);
        filterText.addValueChangeListener(valueChangeListener);
        customCommand.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                result = searchFile(String.valueOf(fileName.getValue()), String.valueOf(lineTo.getValue()),
                        String.valueOf(filterText.getValue()), String.valueOf(event.getProperty().getValue()));
                textViewer.setValue(result);
            }
        });
    }

    private void addTextArea() {
        Styles styles = Page.getCurrent().getStyles();
        //add Style dynamically
        styles.add(".v-app .v-textarea.text-label {background-color: black; color: white; width: 100%; }");
        styles.add(".stpl .bootstrap-bb .v-label {min-width: 80px; }");


        textViewer.setValue(Constants.EMPTY);
        textViewer.setStyleName("text-label");
        textViewer.setRows(20);
        tableLayout.addComponent(textViewer);
    }

    String searchFile(String filePath, String lineTo, String filterText, String customcommand) {
        Process process;
        String line;
        String output = Constants.EMPTY;
        String execcommand;
        if (filePath == null || filePath.isEmpty()) {
            return Constants.EMPTY;
        }
        BufferedReader inStreamReader = null;
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
        if (!filterText.isEmpty()) {
            //replacing using format method
            execcommand = isWindows ? String.format("FINDSTR /N \"%s\" %s  ", filterText, filePath) : String.format("tail  %s -n %s | grep -i \"%s\" ", filePath, lineTo, filterText);
        } else {
            execcommand = isWindows ? String.format("FINDSTR /N \"%s\" %s  ", filterText, filePath) : String.format("tail %s -n %s ", filePath, lineTo);
        }
        try {
            if (isWindows) {
                process = Runtime.getRuntime().exec(customcommand.isEmpty() ? execcommand : customcommand);
            } else {
                String[] cmd = {"/bin/sh", "-c", customcommand.isEmpty() ? execcommand : customcommand};
                process = Runtime.getRuntime().exec(cmd);
            }

            inStreamReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            while ((line = inStreamReader.readLine()) != null) {
                output += line + "\n";
            }
            process.waitFor();
            process.destroy();
            return output;
        } catch (InterruptedException e) {
            LOGGER.debug(e);
            return Constants.EMPTY;
        } catch (IOException ex) {
            LOGGER.debug(ex);
            return "Invalid command :" + execcommand;
        }
    }

    void loadFileNameDdlb() {
        List countlist = SearchLogic.getInstance().searchResults(true, 0, 0);
        int count = countlist.get(0) == null ? 0 : Integer.valueOf(String.valueOf(countlist.get(0)));
        List list = SearchLogic.getInstance().searchResults(false, 0, count);
        for (Object object : list) {
            LoggingDto dto = (LoggingDto) object;
            fileName.addItem(dto.getLogDestination());
        }
    }
}
