/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.etlupload.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.app.gtnutilities.serverlogging.logic.SearchLogic;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.ChangeListener;
import com.vaadin.ui.Upload.SucceededEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class FileUploadForm extends CustomComponent implements View, Upload.SucceededListener,
        Upload.FailedListener,
        Upload.Receiver {

    private static final Logger LOGGER = Logger.getLogger(FileUploadForm.class);
    final SearchLogic searchLogic = SearchLogic.getInstance();
    static String pathFormat = "E:\\Karthik/Jboss/jboss-as-7.1.1.Final/ETL/%s/%s/Input/";
    @UiField("submitBtn")
    private Button submitBtn;
    @UiField("cancelBtn")
    private Button cancelBtn;
    private Upload uploadFile;
    @UiField("interfacepath")
    private TextField interfacepath;
    @UiField("basepath")
    private TextField basepath;
    @UiField("interfacepathLabel")
    private Label interfacepathLabel;
    @UiField("selectedInterface")
    private ComboBox selectedInterface;
    @UiField("uploadLayout")
    private HorizontalLayout uploadLayout;
    public String logDestinationValue;
    private File tempFile;
    @UiField("selectType")
    OptionGroup selectType;
    boolean fileSelected = false;

    /**
     * Instantiates a new FileUploadForm
     */
    public FileUploadForm() {

        super();
        try {
            init();
            configureFields();
            addButtonListeners();
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/EtlFileUpload.xml"), this));
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        interfacepathLabel.setVisible(false);
        interfacepath.setVisible(false);
        basepath.setWidth("600px");

        configureUploadButton();
        selectedInterface.setWidth("200px");
        interfacepath.setValue(StringUtils.EMPTY);

      
        selectType.addItem(Constants.INBOUND);
        selectType.addItem(Constants.OUTBOUND);
        selectType.setValue(Constants.INBOUND);
        loadInterfaceDdlb();
        Property.ValueChangeListener valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (selectType.getValue() != null &&  selectedInterface.getValue()!=null) {
                    basepath.setValue(String.format(pathFormat, selectType.getValue(), selectedInterface.getValue()));
                }
                basepath.setDescription(basepath.getValue());
            }
        };

        selectType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (selectType.getValue() != null &&  selectedInterface.getValue()!=null) {
                    basepath.setValue(String.format(pathFormat, selectType.getValue(), selectedInterface.getValue()));
                }
                basepath.setDescription(basepath.getValue());
                loadInterfaceDdlb();
            }
        });
        selectedInterface.addValueChangeListener(valueChangeListener);
    }

    private void addButtonListeners() {
        submitBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                if (selectedInterface.getValue() != null && !String.valueOf(selectedInterface.getValue()).equals(Constants.SELECT_ONE)) {
                    if (fileSelected) {
                        uploadFile.submitUpload();
                    } else {
                        CommonMethods.getErrorNotification("Halt", "Please Select the File to Upload");
                    }

                } else {
                    CommonMethods.getErrorNotification("Halt", "Please Select any interface");
                }
            }
        });
        cancelBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                clearAll();
            }
        });
    }

    void clearAll() {
        interfacepath.setValue(Constants.EMPTY);
        basepath.setValue(Constants.EMPTY);
        selectType.select(Constants.INBOUND);
        selectedInterface.setValue(null);
        uploadFile.interruptUpload();
        uploadLayout.removeComponent(uploadFile);
        configureUploadButton();
    }

    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            tempFile = File.createTempFile(filename, "csv");
            tempFile.deleteOnExit();
            return new FileOutputStream(tempFile);
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e);
        }

        return null;
    }

    public void uploadSucceeded(SucceededEvent event) {
        try {
            File destinationFile = new File(String.format(pathFormat, selectType.getValue(), selectedInterface.getValue()) + event.getFilename());
            FileUtils.moveFile(tempFile, destinationFile);
            Notification.show(event.getFilename()+"  is Sucessfully Uploaded");
             fileSelected = false;
            // TODO read and parse destinationFile
        } catch (Exception e) {
            LOGGER.error(e);
//            Notification.show("Upload Failed because of" + e.getMessage());
        }
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        Notification.show("Upload Failed");
    }

    private void configureUploadButton() {
        uploadFile = new Upload(Constants.EMPTY, this);
        uploadLayout.addComponent(uploadFile);
        uploadFile.setButtonCaption(null);
        uploadFile.addListener((Upload.SucceededListener) this);
        uploadFile.addListener((Upload.FailedListener) this);
        uploadFile.setReceiver(this);
        uploadFile.addChangeListener(new ChangeListener() {
            @Override
            public void filenameChanged(ChangeEvent event) {
                if (event.getFilename() != null) {
                    fileSelected = true;
                }
            }
        });
    }

    private void loadInterfaceDdlb() {
        List interFacesList = null;
        selectedInterface.removeAllItems();
        selectedInterface.addItem(Constants.SELECT_ONE);
        selectedInterface.setNullSelectionAllowed(true);
        selectedInterface.setNullSelectionItemId(Constants.SELECT_ONE);
        if (String.valueOf(selectType.getValue()).equals(Constants.INBOUND)) {
            interFacesList = Arrays.asList(Constants.interFacesList);
            Collections.sort(interFacesList);
        } else {
            interFacesList = Arrays.asList(Constants.outBoundInterfaceList);
            Collections.sort(interFacesList);
        }

        selectedInterface.addItems(interFacesList);
    }
}
