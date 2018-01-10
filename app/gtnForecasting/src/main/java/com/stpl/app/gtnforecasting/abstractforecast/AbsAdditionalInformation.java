package com.stpl.app.gtnforecasting.abstractforecast;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.additionalinformation.logic.NotesTabLogic;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.PROJECTION_ID;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import elemental.json.JsonArray;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author gopinath
 */
public abstract class AbsAdditionalInformation extends CustomComponent implements View {

    /**
     *
     */
    protected static final long serialVersionUID = 1L;
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbsAdditionalInformation.class);
    @UiField("fileNameLb")
    protected Label fileNameLb;
    @UiField("fileNameField")
    protected TextField fileNameField;
    @UiField("cssLayout1")
    protected CssLayout cssLayout1;
    @UiField("newNotes")
    protected TextArea newNote;
    @UiField("addNote")
    protected Button addNote;
    @UiField("notesHistory")
    protected TextArea internalNotes;
    @UiField("imageLayout")
    protected HorizontalLayout imageLayout;
    @UiField("table")
    protected ExtFilterTable table;
    @UiField("remove")
    protected Button remove;

    @UiField("notes")
    protected Panel notes;

    @UiField("attachment")
    protected Panel attachment;

    protected Receiver uploadReceiver;
    protected Upload uploadComponent;
    protected final TextField uploader = new TextField();
    protected Resource wordRes;
    protected Resource pdfRes;
    protected FileDownloader wordDownloader;
    protected FileDownloader pdfDownloader;
    protected AbstractNotificationUtils.Parameter flag = new AbstractNotificationUtils.Parameter();
    protected static String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY;
    protected Image wordPngImage = new Image(null, new ThemeResource("../../icons/word.png"));
    protected Image pdfPngImage = new Image(null, new ThemeResource("../../icons/pdf.png"));
    protected final File logo = CommonUtil.getFilePath(basepath + "/WEB-INF/images/company_logo.png");
    protected final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<>(NotesDTO.class);
    protected Object tableBeanId = null;
    protected File fileUpload;
    protected final FileDownloader fileDownloader = new FileDownloader(new FileResource(CommonUtil.getFilePath("tst")));
    protected static String fileName;
    protected final String NOTES_HISTORY = "Notes History";
    protected File filePath;
    protected File wordFile;
    protected File pdfFile;
    protected static String fileUploadPath;
    protected final Map<Integer, Boolean> reloadVerticalLayoutTabFiveMap = new HashMap<>();
    protected List<NotesDTO> removeDetailsList = new ArrayList<>();
    private final NotesTabLogic logic = new NotesTabLogic();
    /**
     * The module name.
     */
    public final static String MODULE_NAME = "National Assumptions";
    /**
     * The move back.
     */
    public static final String MOVE_BACK = "../";
    /**
     * The file path.
     */
    protected final File filePathForLink = CommonUtil.getFilePath(basepath + File.separator + MOVE_BACK + MOVE_BACK + MOVE_BACK + File.separator + "Documents" + File.separator + "National Assumptions");
    protected List<String> notesList = new ArrayList<>();
    protected List<String> wordList = new ArrayList<>();
    protected int projectionId = 0;
    protected static String moduleName = StringUtils.EMPTY;
    private String mode = StringUtils.EMPTY;
    protected static String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    protected static String alphaNumericChars = "([0-9|a-z|A-Z|\\ |\\*])*";
    protected static String screenName = (String) VaadinSession.getCurrent().getAttribute(Constant.PORTLET_NAME);

    public AbsAdditionalInformation(final String moduleName, final int projectionId, final String mode) {
        AbsAdditionalInformation.fileName = "Notes_History_" + moduleName.replace(" ", StringUtils.EMPTY);
        this.projectionId = projectionId;
        AbsAdditionalInformation.moduleName = moduleName;
        this.mode = mode;
        setCompositionRoot(Clara.create(ForecastDataSelection.class.getResourceAsStream("/ui/notestabform.xml"), this));
        intailizingObject();
        init();

    }

    public void init() {

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
        table.addStyleName(Constant.FILTERBAR);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setPageLength(NumericConstants.SEVEN);
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
        fileNameLb.addStyleName("mandatory");
        uploader.setStyleName(Constant.SEARCH_TEXT);
        uploader.setImmediate(true);
        uploader.setEnabled(false);
        uploadComponent.setStyleName("uploadIdBB");
        internalNotes.setEnabled(true);
        if (StringUtils.isEmpty(internalNotes.getValue())) {
            wordPngImage.setEnabled(false);
            pdfPngImage.setEnabled(false);
            wordPngImage.setStyleName(Constant.IMAGE_DISABLED);
            pdfPngImage.setStyleName(Constant.IMAGE_DISABLED);
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
                    wordPngImage.setStyleName("Image-Enabled");
                    pdfPngImage.setStyleName("Image-Enabled");
                    wordDownloader.extend(wordPngImage);
                    pdfDownloader.extend(pdfPngImage);
                } else {
                    wordDownloader.remove();
                    pdfDownloader.remove();
                    wordPngImage.setEnabled(false);
                    pdfPngImage.setEnabled(false);
                    wordPngImage.setStyleName(Constant.IMAGE_DISABLED);
                    pdfPngImage.setStyleName(Constant.IMAGE_DISABLED);
                }
            }
        });

    
        table.addStyleName("table-header-normal");
        table.setWidth("500px");
        notes.setHeight("575px");
        attachment.setHeight("575px");
        attachment.setWidth("655px");
        JavaScript.getCurrent().addFunction("callJava", new JavaScriptFunction() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void call(JsonArray arguments) {
                 try {
                    String value = String.valueOf(arguments.get(0).asString());
                    if (StringUtils.isNotEmpty(value)) {

                        fileUpload = CommonUtil.getFilePath(fileUploadPath + value);
                        String name = fileUpload.getAbsolutePath();
                        if (name.contains("\\")) {
                            String replace = name.replace("\\", ",");
                            String[] array = replace.split(",");
                            String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : fileNameField.getValue());
                        } else if (name.contains("/")) {
                            final String replace = name.replace("/", ",");
                            final String[] array = replace.split(",");
                            final String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : fileNameField.getValue());
                        } else {
                            uploader.setValue(name);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? name.substring(0, name.lastIndexOf('.')) : fileNameField.getValue());
                        }
                    } else {
                        uploader.setValue(StringUtils.EMPTY);
                        fileNameField.setValue(StringUtils.EMPTY);
                    }
                } catch (Property.ReadOnlyException ex) {
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
            public void focus(FocusEvent event) {
                  uploadComponent.focus();
            }
        });

        newNote.setSizeFull();
        newNote.setRows(NumericConstants.SEVEN);
        newNote.setImmediate(true);
        newNote.setMaxLength(NumericConstants.THOUSAND);
        newNote.addValidator(new StringLengthValidator(" New Note Should be less than 1000 characters", 0, NumericConstants.THOUSAND, true));
        internalNotes.setSizeFull();
        internalNotes.setRows(NumericConstants.SEVEN);
        internalNotes.markAsDirty();
        internalNotes.setImmediate(true);
        internalNotes.setEnabled(false);
        uploadComponent.setButtonCaption(Constant.ADD);
        addNote.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!flag.isOk()) {
                    flag.setOk(true);
                    if (screenName.equals(getGovernmentConstant())) {
                        RegexpValidator exp = new RegexpValidator(alphaNumericChars, "Special Characters are not allowed in  New Note ");
                        if (newNote.getValue().length() > NumericConstants.TWO_HUNDRED) {
                            AbstractNotificationUtils.getWarningNotification("Enter New Note", "Entered New Note Should be less than 200 characters");
                            flag.setOk(false);
                        } else if (!StringUtils.EMPTY.equals(newNote.getValue()) && !exp.isValid(newNote.getValue())) {
                            AbstractNotificationUtils.getWarningNotification("Enter New Note", exp.getErrorMessage());
                            flag.setOk(false);
                        } else {
                            addEnteredNotes(event, flag);
                        }

                    } else {
                        addEnteredNotes(event, flag);
                    }
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

            @Override
            public void itemClick(ItemClickEvent event) {
                try {
                    itemClickLogic(event);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });

        addNotesBtn();
        try {
            setValues(mode.equalsIgnoreCase(ACTION_EDIT.getConstant()) || mode.equalsIgnoreCase(ACTION_VIEW.getConstant()) ? true : false);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

        }

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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       
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

        uploader.setValue(StringUtils.EMPTY);
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
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if (pdfFile.exists() != true) {
            try {
                pdfFile.createNewFile();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
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
            Resource wordResOnEdit = new FileResource(CommonUtil.getFilePath(filePath + File.separator + fileName + ExportWord.DOC_EXT));
            wordDownloader.setFileDownloadResource(wordResOnEdit);

            ExportPdf exportPdf = new ExportPdf(NOTES_HISTORY, filePath, logo, pdfFile);
            exportPdf.export(internalNotes.getValue());
            Resource pdfResOnEdit = new FileResource(CommonUtil.getFilePath(filePath + File.separator + fileName + ExportPdf.PDF_EXT));
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
            doc = doc.substring(0, index);
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

    public List<String> getNotesList() {
        return notesList;
    }

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table, int length) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
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

        reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO , true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO , true);
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
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO , true);
                    reloadVerticalLayoutTabFiveMap.put(0, true);
                    table.setWidth("500px");
                    getCollapsibleColumnsDefault(table, NumericConstants.THREE);
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(NumericConstants.THREE_EIGHT_ZERO )) {
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO , false);
                    reloadVerticalLayoutTabFiveMap.put(0, true);
                    table.setWidth("310px");
                    getCollapsibleColumnsDefault(table, NumericConstants.TWO);
                } else if (browserWidth < NumericConstants.THREE_EIGHT_ZERO  && reloadVerticalLayoutTabFiveMap.get(0)) {
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO , true);
                    reloadVerticalLayoutTabFiveMap.put(0, false);
                    table.setWidth("260px");
                    getCollapsibleColumnsDefault(table, NumericConstants.ONE);
                }

            }
        });
    }

    /**
     * Call java script for button.
     */
    public void callJavaScriptForButton() {
        LOGGER.debug("Inside of AdditionalInformation callJavaScriptForButton Method");
        uploader.setValue(StringUtils.EMPTY);
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
        internalNotes.setEnabled(value);
    }

    public void resetBtnLogic(String notesHistoryValue) {
        fileNameField.setValue(StringUtils.EMPTY);
        newNote.setValue(StringUtils.EMPTY);
        internalNotes.setEnabled(true);
        internalNotes.setValue(notesHistoryValue);
        internalNotes.setEnabled(false);
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
        fileNameField.setValue(StringUtils.EMPTY);
        newNote.setValue(StringUtils.EMPTY);
        internalNotes.setEnabled(true);
        internalNotes.setValue(StringUtils.EMPTY);
        internalNotes.setEnabled(false);
        table.removeAllItems();
    }

    public void removeAndDisablingComponents() {
        newNote.setReadOnly(true);
        internalNotes.setEnabled(false);
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
     *
     */

    /**
     * Method to initailize the requried object in the class
     */
    public abstract void intailizingObject();

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
    public abstract void uploadComponentStartedLogic(Upload.StartedEvent event);

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

    public abstract void addNotesBtn();


    /**
     * Sets the values.
     *
     * @throws SystemException ,PortalException
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */

    public void setValues(boolean saveFlag) {
        LOGGER.debug("Inside of AdditionalInformation setValues Method");
        try {
            String mode2 = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.MODE));
            if (ACTION_EDIT.getConstant().equalsIgnoreCase(mode2) || ACTION_VIEW.getConstant().equalsIgnoreCase(mode2) || saveFlag) {
                attachmentsListBean.removeAllItems();
                if (projectionId == 0) {
                    projectionId = (Integer) VaadinSession.getCurrent().getAttribute(PROJECTION_ID.getConstant());
                }

                final List<NotesDTO> allFiles = logic.getAttachmentDTOList(projectionId, moduleName, fileUploadPath);

                attachmentsListBean.addAll(logic.addUserFile(allFiles));

                wordList.clear();

                internalNotes.setEnabled(true);
                internalNotes.setValue(StringUtils.EMPTY);
                internalNotes.setValue(logic.getNotes(projectionId, moduleName, wordList));
                internalNotes.setEnabled(false);
                newNote.setValue(StringUtils.EMPTY);
            }
        } catch (SystemException | Property.ReadOnlyException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Ends of AdditionalInformation setValues Method");
    }
}