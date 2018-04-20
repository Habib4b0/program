/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.HeaderUtils;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.VerticalLayout;
import elemental.json.JsonArray;

/**
 *
 * @author sooriya.lakshmanan
 */
public abstract class AbstractNotesTab extends CustomComponent implements View {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNotesTab.class);
    @UiField("fileNameLb")
    protected Label fileNameLb;
    @UiField("addBtnlayout")
    protected VerticalLayout addBtnlayout;
    @UiField("tableLayout")
    protected VerticalLayout tableLayout;
    @UiField("fileNameField")
    protected TextField fileNameField;
    @UiField("cssLayout1")
    public CssLayout cssLayout1;
    @UiField("cssHistoryNote")
    public CssLayout cssHistoryNote;
    @UiField("cssNewNote")
    public CssLayout cssNewNote;
    @UiField("newNotes")
    protected TextArea newNote;
    @UiField("addNote")
    protected Button addNote;
    @UiField("notesHistory")
    protected TextArea internalNotes;
    @UiField("imageLayout")
    private HorizontalLayout imageLayout;
    @UiField("table")
    protected ExtFilterTable table;
    @UiField("remove")
    protected Button remove;
    @UiField("reasoncodeLayout")
    public VerticalLayout reasoncodeLayout;
    @UiField("vlayout")
    protected VerticalLayout vlayout;
    @UiField("root")
    protected HorizontalLayout root;
    @UiField("reasonCode")
    protected ComboBox reasonCodeddlb;
    @UiField("reasonCodeLabel")
    protected Label reasonCodeLabel;

    public Button getAddNote() {
        return addNote;
    }

    public void setAddNote(Button addNote) {
        this.addNote = addNote;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    @UiField("panelnotes")
    private Panel panelnotes;
    protected Receiver uploadReceiver;
    protected Upload uploadComponent;
    protected final TextField uploader = new TextField();
    private Resource wordRes;
    private Resource pdfRes;
    protected FileDownloader wordDownloader;
    protected FileDownloader pdfDownloader;
    AbstractNotificationUtils.Parameter flag = new AbstractNotificationUtils.Parameter();
    protected String basepath = System.getProperty("com.stpl.gtnframework.base.path");	
    protected Image wordPngImage = new Image(null, new ThemeResource("img/word.png"));
    protected Image pdfPngImage = new Image(null, new ThemeResource("img/pdf.png"));
    private File logo = new File(basepath + "/WEB-INF/images/company_logo.png");
    protected final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<NotesDTO>(NotesDTO.class);
    protected Object tableBeanId = null;
    protected File fileUpload;
    protected final FileDownloader fileDownloader = new FileDownloader(new FileResource(new File("tst")));
    protected String fileName;
    private String NOTES_HISTORY = "Notes History";
    protected File filePath;
    protected File wordFile;
    protected File pdfFile;
    private FieldGroup binder;
    protected String fileUploadPath;
    private final Map<Integer, Boolean> reloadVerticalLayoutTabFiveMap = new HashMap<Integer, Boolean>();
    public List<NotesDTO> removeDetailsList = new ArrayList<NotesDTO>();
    protected String moduleName;

    /**
     * constructor for additional information
     *
     */
    public AbstractNotesTab(final FieldGroup binder, final String moduleName) {
        this.moduleName = moduleName;
        this.binder = binder;
        this.fileName = "Notes_History_" + moduleName.replace(" ", "");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/notestabform.xml"), this));
        intailizingObject(moduleName);
        init();

    }

    private void init() {
        binder.bindMemberFields(this);
        addToContent();
        createExportDocs();
        configureFields();
        LOGGER.debug("Ending Notes init");
    }

    public void getContent() {
        addToContent();
    }

    private void addToContent() {
        addNotes();
        addAttachments();
    }

    private void addNotes() {
        imageLayout.addComponent(wordPngImage);
        imageLayout.addComponent(pdfPngImage);
    }

    private void addAttachments() {
        Label addAttachmentLable = new Label("Attachment");
        addAttachmentLable.addStyleName("attachment");
        fileNameField.setImmediate(true);
        fileNameField.setMaxLength(NumericConstants.TWO_FIVE_ZERO);
        fileNameField.addValidator(new StringLengthValidator(" File Name Should be less than 250 characters", 0, NumericConstants.TWO_FIVE_ZERO, true));

        cssLayout1.addComponent(addAttachmentLable);
        cssLayout1.addComponent(uploader);
        cssLayout1.addComponent(uploadComponent);

        cssLayout1.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Will execute,when we click layout.
             */
            @Override
            public void layoutClick(final LayoutEvents.LayoutClickEvent event) {
                callJavaScript();
            }
        });

        addTable();
        addResponsiveVerticalTabFiveLayout(table);
    }

    private void addTable() {
        table.markAsDirty();
        table.addStyleName("filterbar");
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setPageLength(NumericConstants.THREE);
        table.setContainerDataSource(attachmentsListBean);
        table.setSelectable(true);
        table.setVisibleColumns(new Object[]{"documentName", "dateAdded", "userName"});
        table.setColumnHeaders(new String[]{"Document Name", "Date Added", "User Name"});
    }

    /**
     * to configure all the fields
     */
    private void configureFields() {
        fileNameLb.addStyleName("filenamelable");
        uploader.setStyleName("searchText");
        uploader.setImmediate(true);
        uploader.setEnabled(false);
        uploadComponent.setStyleName("uploadIdBB");
        internalNotes.setReadOnly(true);
        if (StringUtils.isEmpty(internalNotes.getValue())) {
            wordPngImage.setEnabled(false);
            pdfPngImage.setEnabled(false);
            wordPngImage.setStyleName(HeaderUtils.IMAGE_DISABLED);
            pdfPngImage.setStyleName(HeaderUtils.IMAGE_DISABLED);
        }else{
            wordPngImage.setEnabled(true);
            pdfPngImage.setEnabled(true);
            wordPngImage.setStyleName(HeaderUtils.IMAGE_ENABLED);
            pdfPngImage.setStyleName(HeaderUtils.IMAGE_ENABLED);
            wordDownloader.extend(wordPngImage);
            pdfDownloader.extend(pdfPngImage);

        }
        internalNotes.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (StringUtils.isNotEmpty(String.valueOf(event.getProperty().getValue()))) {
                    wordPngImage.setEnabled(true);
                    pdfPngImage.setEnabled(true);
                    wordPngImage.setStyleName(HeaderUtils.IMAGE_ENABLED);
                    pdfPngImage.setStyleName(HeaderUtils.IMAGE_ENABLED);
                    wordDownloader.extend(wordPngImage);
                    pdfDownloader.extend(pdfPngImage);
                } else {
                    wordRes = new FileResource(wordFile);
                    pdfRes = new FileResource(pdfFile);
                    wordDownloader.setFileDownloadResource(wordRes);
                    pdfDownloader.setFileDownloadResource(wordRes);
                    wordPngImage.setEnabled(false);
                    pdfPngImage.setEnabled(false);
                    wordPngImage.setStyleName(HeaderUtils.IMAGE_DISABLED);
                    pdfPngImage.setStyleName(HeaderUtils.IMAGE_DISABLED);
                }
            }
        });

        table.addStyleName("table-header-normal");
        table.setWidth("500px");
        JavaScript.getCurrent().addFunction("callJava", new JavaScriptFunction() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;
          

            @Override
            public void call(JsonArray arguments) {
               try {
                   elemental.json.impl.JreJsonString jsonstring=arguments.get(0);
		   String value =jsonstring.asString() ;
                    if (StringUtils.isNotEmpty(value)) {

                        fileUpload = new File(fileUploadPath + value);
                        String name = fileUpload.getAbsolutePath();
                        if (name.contains("\\")) {
                            String replace = name.replace("\\", ",");
                            String[] array = replace.split(",");
                            String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : filename.substring(0, filename.lastIndexOf('.')) );
                        } else if (name.contains("/")) {
                            final String replace = name.replace("/", ",");
                            final String[] array = replace.split(",");
                            final String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : filename.substring(0, filename.lastIndexOf('.')));
                        } else {
                            uploader.setValue(name);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? name.substring(0, name.lastIndexOf('.')) : name.substring(0, name.lastIndexOf('.')) );
                        }
                    } else {
                        uploader.setValue("");
                        fileNameField.setValue("");
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
                uploader.focus();
            }
        });

        uploader.addFocusListener(new FocusListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Will execute,when we click an uploader.
             */
            @Override
            public void focus(final FocusEvent event) {
                uploadComponent.focus();
            }
        });

        newNote.setSizeFull();
        newNote.setRows(NumericConstants.FIVE);
        newNote.setImmediate(true);
        newNote.setMaxLength(NumericConstants.THOUSAND);
        newNote.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        internalNotes.setSizeFull();
        internalNotes.setRows(NumericConstants.FIVE);
        internalNotes.markAsDirty();
        internalNotes.setImmediate(true);
        internalNotes.setEnabled(false);
        uploadComponent.setButtonCaption("ADD");
        addNote.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                if (!flag.isOk()) {
                    flag.setOk(true);
                    addEnteredNotes(event, flag);
                }
            }
        });

        uploadComponent.addSucceededListener(new Upload.SucceededListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                uploadComponentSucceededLogic(event);
            }
        });

        uploadComponent.addStartedListener(new Upload.StartedListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void uploadStarted(Upload.StartedEvent event) {
                uploadComponentStartedLogic(event);
            }
        });

        remove.addClickListener(new Button.ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                removeButtonLogic(event);
            }
        });
        newNote.setInputPrompt("- Enter New Note -");

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void itemClick(ItemClickEvent event) {
                try {
                    itemClickLogic(event);
                } catch (Exception e) {
                    LOGGER.error("Error while clicking the Item :" + e);
                }
            }
        });

    }

    /**
     * to add notes in notes history
     */
    public boolean onAdvance() {
        return false;
    }

    public boolean onBack() {
        return true;
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

    /**
     *
     */
    /**
     * to make field disabled
     */
    public void disableFields() {
        newNote.setEnabled(false);
        addNote.setEnabled(false);
        internalNotes.setEnabled(false);
        remove.setEnabled(false);
        uploadComponent.setEnabled(false);

    }

    /**
     * JavaScript for search icon in text field
     */
    public void callJavaScript() {
        uploader.setDebugId("textId");
        uploadComponent.setId("layoutId");
        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var f=document.getElementById('layoutId').firstChild;" + "var f2=f.children[1]; " + "f2.addEventListener('change', function(){" + "var f3=f2.value;" + "callJava(f3)"
                        + "}, false);");

    }

    public void setUploderValue() {

        uploader.setValue("");
    }

    /**
     * Creating the directory for document
     *
     */
    private void createExportDocs() {
        if (filePath.isDirectory() == false) {
            filePath.mkdirs();
        }
        if (wordFile.exists() != true) {
            try {
                wordFile.createNewFile();
            } catch (Exception ex) {
                LOGGER.error("Error in Creating new Word File :" + ex);
            }
        }
        if (pdfFile.exists() != true) {
            try {
                pdfFile.createNewFile();
            } catch (Exception ex) {
                LOGGER.error("Error in Creating new PDF File :" + ex);
            }
        }

        wordRes = new FileResource(wordFile);
        pdfRes = new FileResource(pdfFile);
        wordDownloader = new FileDownloader(wordRes);
        pdfDownloader = new FileDownloader(pdfRes);
    }

    /**
     * To create pdf and word documents
     *
     */
    public void documentExporter() {
        try {
            ExportWord exportWord = new ExportWord(filePath, wordFile);
            exportWord.export(internalNotes.getValue());
            Resource wordResOnEdit = new FileResource(new File(filePath + File.separator + fileName + ExportWord.DOC_EXT));
            wordDownloader.setFileDownloadResource(wordResOnEdit);

            ExportPdf exportPdf = new ExportPdf(NOTES_HISTORY, filePath, logo, pdfFile);
            exportPdf.export(internalNotes.getValue());
            Resource pdfResOnEdit = new FileResource(new File(filePath + File.separator + fileName + ExportPdf.PDF_EXT));
            pdfDownloader.setFileDownloadResource(pdfResOnEdit);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    /**
     * To upload a file
     *
     * @param uploadedFile
     */
    public void downloadFile(File uploadedFile) {
        try {
            if (uploadedFile.exists()) {
                Resource res = new FileResource(uploadedFile);
                Page.getCurrent().open(res, "_blank", true);

            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public List<NotesDTO> getUploadedData() {

        return attachmentsListBean.getItemIds();
    }

    public boolean fileExists(String fileName) {
        List<NotesDTO> uploadedFiles = getUploadedData();
        for (NotesDTO uploadedFile : uploadedFiles) {
            String doc = uploadedFile.getDocumentName();
            int index = doc.indexOf('.');
            if(index!=-1){
            doc = doc.substring(0, index);
            }
            if (doc.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public String getAddedNotes() {
        return internalNotes.getValue();
    }

    public TextArea getInternalNotes() {
        return internalNotes;
    }

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table, int length) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = length; i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    public void addResponsiveVerticalTabFiveLayout(final ExtFilterTable table) {

        reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFiveMap.put(0, true);
        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(0, true);
                    table.setWidth("500px");
                    getCollapsibleColumnsDefault(table, NumericConstants.THREE);
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(NumericConstants.THREE_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabFiveMap.put(0, true);
                    table.setWidth("310px");
                    getCollapsibleColumnsDefault(table, NumericConstants.TWO);
                } else if (browserWidth < NumericConstants.THREE_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(0)) {
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(0, false);
                    table.setWidth("260px");
                    getCollapsibleColumnsDefault(table, 1);
                }

            }
        });
    }

    /**
     * Call java script for button.
     */
    public void callJavaScriptForButton() {
        LOGGER.debug("Inside of AdditionalInformation callJavaScriptForButton Method");
        uploader.setValue("");
        uploader.setDebugId("textId");
        uploadComponent.setDebugId("fileUpload");
        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var clk=document.getElementById('fileUpload').firstChild.children[2];" + "clk.addEventListener('click', function(){" + "var val1=document.getElementById('textId').value;"
                        + "if(val1==null||val1.length<=1||val1==''){buttonClicked();}" + "}, false);");
        LOGGER.debug("Ends of AdditionalInformation callJavaScriptForButton Method");
    }

    public List<NotesDTO> removeDetailsList() {
        return removeDetailsList;
    }

    public void focusUploaderField() {
        uploader.focus();
    }

    public void setNotesHistoryValue(String value) {
        internalNotes.setValue(value);
    }

    public void focusNewNote() {
        newNote.focus();
    }

    public void setUploaderValue(String values) {
        uploader.setValue(values);
    }

    public void readOnlyNotesHistory(boolean value) {
        internalNotes.setReadOnly(value);
    }

    public void resetBtnLogic(String notesHistoryValue) {
        fileNameField.setValue("");
        newNote.setValue(StringUtils.EMPTY);
        internalNotes.setReadOnly(false);
        internalNotes.setValue(notesHistoryValue);
        internalNotes.setReadOnly(true);
        table.removeAllItems();
        uploader.setValue(StringUtils.EMPTY);
        uploader.setValue(StringUtils.EMPTY);
        wordRes = new FileResource(wordFile);
        pdfRes = new FileResource(pdfFile);
        wordDownloader = new FileDownloader(wordRes);
        pdfDownloader = new FileDownloader(pdfRes);
        refreshTable();
    }

    public void resetAddMode() {
        fileNameField.setValue("");
        newNote.setValue("");
        internalNotes.setReadOnly(false);
        internalNotes.setValue("");
        internalNotes.setReadOnly(true);
        fileNameField.setValue("");
        uploader.setValue(StringUtils.EMPTY);        
        table.removeAllItems();
    }

    public void removeAndDisablingComponents() {
        newNote.setReadOnly(true);
        internalNotes.setReadOnly(true);
        fileNameField.setReadOnly(true);
        uploader.setReadOnly(true);
        table.setReadOnly(true);
        imageLayout.setEnabled(false);
        uploadComponent.setReadOnly(true);
        wordPngImage.setEnabled(false);
        pdfPngImage.setEnabled(false);
        addNote.setEnabled(false);
        remove.setEnabled(false);
    }

    /**
     * Method to add notes to notes history text area
     *
     * @param event
     * @param flag
     */
    public abstract void addEnteredNotes(Button.ClickEvent event, AbstractNotificationUtils.Parameter flag);

    /**
     * Method to initailize the requried object in the class
     * @param moduleName
     */
    public abstract void intailizingObject(String moduleName);

    /**
     * method to execute after the item upload successfully
     *
     * @param event
     */
    public abstract void uploadComponentSucceededLogic(Upload.SucceededEvent event);

    /**
     * method to execute after the item upload started
     *
     * @param event
     */
    public abstract void uploadComponentStartedLogic(@SuppressWarnings("deprecation") Upload.StartedEvent event);

    /**
     * method for remove button logic
     *
     * @param event
     */
    public abstract void removeButtonLogic(Button.ClickEvent event);

    /**
     * method for Item click logic in table
     *
     * @param event
     */
    public abstract void itemClickLogic(ItemClickEvent event);

    /**
     * Method to refresh the table from database
     */
    public abstract void refreshTable();
}
