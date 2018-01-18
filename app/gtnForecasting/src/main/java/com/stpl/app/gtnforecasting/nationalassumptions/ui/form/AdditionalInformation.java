/*
 * 
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.AttachmentDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.AdditionalInfoLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.PROJECTION_ID;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import com.stpl.app.gtnforecasting.nationalassumptions.util.FileUploader;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.VerticalLayout;
import elemental.json.JsonArray;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class AdditionalInformation.
 *
 */
public class AdditionalInformation extends CustomComponent {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalInformation.class);

    /**
     * The excel export image.
     */
    private final Resource wordImage = new ThemeResource("../../icons/word.png");
    /**
     * The graph image.
     */
    private final Resource pdfImage = new ThemeResource("../../icons/pdf.png");
    /**
     * The excelBtn btn.
     */
    @UiField("wordBtn")
    private Button wordBtn;

    /**
     * The add notes btn.
     */
    @UiField("addNotesBtn")
    private Button addNotesBtn;

    /**
     * The pdf btn.
     */
    @UiField("pdfBtn")
    private Button pdfBtn;

    /**
     * The remove btn.
     */
    @UiField("removeBtn")
    private Button removeBtn;
    /**
     * The results table.
     */
    @UiField("resultsTable")
    private Table resultsTable;
    /**
     * The basepath.
     */
    private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() == null ? StringUtils.EMPTY : VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    /**
     * The module name.
     */
    private final static String MODULE_NAME = "National Assumptions";
    /**
     * The new notes.
     */
    @UiField("newNotes")
    private TextArea newNotes;

    /**
     * The notes history.
     */
    @UiField("notesHistory")
    private TextArea notesHistory;

    /**
     * The layout.
     */
    @UiField("layout")
    private VerticalLayout layout;
    /**
     * The table bean.
     */
    private AttachmentDTO tableBean;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The file downloader.
     */
    /**
     * The attachments list bean.
     */
    private BeanItemContainer<AttachmentDTO> attachmentsListBean = new BeanItemContainer<>(AttachmentDTO.class);

    private List<AttachmentDTO> newFileDto = new ArrayList<>();

    /**
     * The move back.
     */
    private final String moveBack = "../";

    /**
     * The user id.
     */
    private final String userId = (String) VaadinSession.getCurrent().getAttribute(USER_ID.getConstant());
    /**
     * The file size.
     */
    private Double fileSize = 0.00;
    public static final String DOCUMENTS = "Documents";
    /**
     * The file path.
     */
    private final File filePath = CommonUtil.getFilePath(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + DOCUMENTS + File.separator + MODULE_NAME + File.separator + userId);
    
    /**
     * The upload receiver.
     */
    private FileUploader uploadReceiver = new FileUploader(basepath, MODULE_NAME);
    /**
     * The add attachment.
     */
    private Upload addAttachment = new Upload(null, uploadReceiver);
    /**
     * The uploader.
     */
    private TextField uploader = new TextField();

    /**
     * The notes history.
     */
    private static final String NOTES_HISTORY = "Notes History";
    /**
     * The logo.
     */
    private File logo = CommonUtil.getFilePath(basepath + "/WEB-INF/images/company_logo.png");
    /**
     * The file name.
     */
    private final static String FILENAME = "Notes_History_National_Assumptions";
    /**
     * The word file.
     */
    private File wordFile = CommonUtil.getFilePath(filePath + File.separator + FILENAME + ExportWord.DOC_EXT);
    /**
     * The pdf file.
     */
    private File pdfFile = CommonUtil.getFilePath(filePath + File.separator + FILENAME + ExportPdf.PDF_EXT);
    /**
     * The word downloader.
     */
    private FileDownloader wordDownloader;
    /**
     * The pdf downloader.
     */
    private FileDownloader pdfDownloader;
    private  List<AttachmentDTO> removedDetailsList = new ArrayList<>();
    /**
     * The common logic.
     */
    private AdditionalInfoLogic addInfoLogic = new AdditionalInfoLogic();
    private List<String> notesList = new ArrayList<>();
    private List<String> wordList = new ArrayList<>();
    private String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);

    /**
     * The file path.
     */
    private final File filePathForLink = CommonUtil.getFilePath(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + DOCUMENTS + File.separator + MODULE_NAME);

    /**
     * Instantiates a new additional information.
     */
    public AdditionalInformation() {
        try {
            LOGGER.debug("AdditionalInformation Constructor initiated");
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/AdditionalInformation.xml"), this));
            layout.addComponent(uploader);
            layout.addComponent(addAttachment);
            createExportDocs();
            configureFields();
            LOGGER.debug("AdditionalInformation Constructor ends");
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Configure fields.
     */
    private void configureFields() throws SystemException  {

        newNotes.setInputPrompt("- Enter New Note -");
        wordBtn.setIcon(wordImage);
        pdfBtn.setIcon(pdfImage);
        resultsTable.setContainerDataSource(attachmentsListBean);
        resultsTable.setVisibleColumns(CommonUiUtils.getAttachmentColumns());
        resultsTable.setColumnHeaders(CommonUiUtils.getAttachmentHeader());
        resultsTable.setSelectable(true);
        resultsTable.setColumnAlignment(CommonUiUtils.getAttachmentColumns()[0], Table.Align.LEFT);
        resultsTable.setColumnAlignment(CommonUiUtils.getAttachmentColumns()[1], Table.Align.CENTER);
        resultsTable.setColumnAlignment(CommonUiUtils.getAttachmentColumns()[NumericConstants.TWO], Table.Align.LEFT);
        uploader.setStyleName(Constant.SEARCH_TEXT);
        layout.setStyleName("uploadId");
        notesHistory.setEnabled(false);
        newNotes.setInputPrompt("- Enter New Note -");
        if (Constant.VIEW.equalsIgnoreCase(mode)) {
            disableFieldsOnView();
        }
        uploader.addFocusListener(new FocusListener() {
            /**
             * Will execute,when we click an uploader.
             */
            @Override
            public void focus(final FocusEvent event) {
                addAttachment.focus();
            }
        });
        JavaScript.getCurrent().addFunction("callJava", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                 File fileUpload;
                final String value = String.valueOf(arguments.get(0));
                fileUpload = CommonUtil.getFilePath(value);
                final String name = fileUpload.getAbsolutePath();
                if (name.contains("\\")) {
                    final String replace = name.replace("\\", ",");
                    final String[] array = replace.split(",");
                    final String filename = array[array.length - 1];
                    uploader.setValue(filename);
                } else if (name.contains("/")) {
                    final String replace = name.replace("/", ",");
                    final String[] array = replace.split(",");
                    final String filename = array[array.length - 1];
                    uploader.setValue(filename);
                } else {
                    uploader.setValue(name);
                }
                uploader.focus();
            }
          
        });
        addAttachment.addChangeListener(new Upload.ChangeListener() {
            @Override
            public void filenameChanged(Upload.ChangeEvent event) {
                uploader.setValue(event.getFilename());
            }
        });
        addAttachment.setButtonCaption(Constant.ADD);
        addAttachment.addStartedListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(Upload.StartedEvent event) {
                LOGGER.debug("uploadStarted method in addStartedListener started");
                String fileName = event.getFilename();

                String[] format = {"doc", "docx", "ppt", "xls", "xlsx", "pdf", "txt", "csv", "jpeg"};
                List<String> formatList = Arrays.asList(format);

                if (StringUtils.isBlank(event.getFilename()) || StringUtils.isBlank(uploader.getValue())) {
                    AbstractNotificationUtils.getErrorNotification("Add attachment", "There is no file to add. Please locate a file to upload.");
                    uploadReceiver.setUpload(false);
                } else if (!formatList.contains(fileName.substring(fileName.lastIndexOf('.') + 1))) {
                    AbstractNotificationUtils.getErrorNotification("Add attachment Error", "This is an unsupported file type. Please upload a supported file type: doc, docx, ppt, xls, xlsx, pdf, txt, csv, jpeg.");
                    uploadReceiver.setUpload(false);
                    uploader.setValue(StringUtils.EMPTY);
                } else {
                    uploadReceiver.setUpload(true);
                }
                LOGGER.debug("uploadStarted method in addStartedListener ends");
            }
        });

        addAttachment.addSucceededListener(new Upload.SucceededListener() {
            /**
             * Will execute,when we add an attachment.
             */
            @Override
            public void uploadSucceeded(final Upload.SucceededEvent event) {
                try {
                    LOGGER.debug("uploadSucceeded method in addSucceededListener started , the user id is " + userId);
                    String fileName = event.getFilename();
                    String[] format = {"doc", "docx", "ppt", "xls", "xlsx", "pdf", "txt", "csv", "jpeg"};
                    List<String> formatList = Arrays.asList(format);
                    if (StringUtils.isNotBlank(event.getFilename()) && StringUtils.isNotBlank(uploader.getValue()) && formatList.contains(fileName.substring(fileName.lastIndexOf('.') + 1))) {
                        Object[] items = attachmentsListBean.getItemIds().toArray();
                        for (Object item : items) {
                            BeanItem<AttachmentDTO> dtoBean = attachmentsListBean.getItem(item);
                            String docName = dtoBean.getBean().getDocumentName().getCaption();
                            String docUserName = dtoBean.getBean().getUserName();
                            if (docName.equals(event.getFilename()) && docUserName.equals(CommonUtils.userMap.get(userId))) {
                                attachmentsListBean.removeItem(item);
                                break;
                            }
                        }
                        AttachmentDTO attachDto = new AttachmentDTO();
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_FORMAT.getConstant());
                        attachDto.setDateAdded(dateTimeFormat.format(new Date()));
                        attachDto.setDocumentName(AdditionalInfoLogic.configureDownloader(event.getFilename(), filePath.getAbsolutePath()));
                        attachDto.setUserName(CommonUtils.getUserNameById(userId));
                        attachDto.setDocumentFullPath(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + DOCUMENTS + File.separator + MODULE_NAME + File.separator + userId
                                + File.separator + event.getFilename());
                        attachmentsListBean.addBean(attachDto);
                        newFileDto.add(attachDto);
                        CommonUtils.getMessageNotification(event.getFilename() + " uploaded successfully");
                        uploader.setValue(StringUtils.EMPTY);
                    }
                    LOGGER.debug("uploadSucceeded method in addSucceededListener ends");
                } catch (Property.ReadOnlyException e) {
                    LOGGER.error(e.getMessage());
                }
            }

        });
        addAttachment.addProgressListener(new Upload.ProgressListener() {
            /**
             * Will execute,when we add an attachment.
             */
            @Override
            public void updateProgress(final long readBytes, final long contentLength) {
                LOGGER.debug("updateProgress method in addProgressListener started");
                fileSize = Double.valueOf(contentLength) / NumericConstants.ONE_ZERO_TWO_FOUR;
                LOGGER.debug("updateProgress method in addProgressListener ends");
            }
        });
        layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            /**
             * Will execute,when we click layout.
             */
            @Override
            public void layoutClick(final LayoutEvents.LayoutClickEvent event) {
                callJavaScript();
            }
        });

        uploader.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.debug("uploader.addValueChangeListener started");
                File[] listOfFiles = CommonUtil.getFilePath(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + DOCUMENTS + File.separator + MODULE_NAME + File.separator + userId)
                        .listFiles();
                boolean fileExist = false;
                for (File file : listOfFiles) {
                    if (event.getProperty().getValue().toString().equals(file.getName())) {
                        fileExist = true;
                    }
                }
                if (fileExist) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            uploader.setValue(StringUtils.EMPTY);
                        }

                        @Override
                        /**
                         * The method is triggered when Yes button of the
                         * message box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        public void yesMethod() {
                            // do nothing
                        }
                    }.getConfirmationMessage("File Exists", "Selected file already Exists. Do you want to replace it?");
                }
                LOGGER.debug("uploader.addValueChangeListener ends");
            }
        });

        setValues(false);
    }

    /**
     * Results table.
     *
     * @param event the event
     */
    @UiHandler("resultsTable")
    public void resultsTable(final ItemClickEvent event) {
        LOGGER.debug("Inside resultsTable itemClick method");

        tableBeanId = event.getItemId();
        BeanItem<?> targetItem = null;
        if (tableBeanId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableBeanId;
        } else if (tableBeanId instanceof AttachmentDTO) {
            targetItem = new BeanItem<>((AttachmentDTO) tableBeanId);
        }
        tableBean = (AttachmentDTO) targetItem.getBean();
        LOGGER.debug("Ends resultsTable itemClick method");
    }

    /**
     * Adds the notes btn.
     *
     * @param event the event
     */
    @UiHandler("addNotesBtn")
    public void addNotesBtn(final Button.ClickEvent event) {
        LOGGER.debug("Inside addNotesBtn listener method");
        final String enteredvalue = newNotes.getValue();
        if ((StringUtils.EMPTY).equals(enteredvalue.trim())) {
            AbstractNotificationUtils.getErrorNotification("Add Note Error", "There is no note to add. Please type a note in the Notes Section. ");
        } else {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("<hh:mm:ss a> <MM/dd/yyyy>");
                        int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(PROJECTION_ID.getConstant());
                        if (addInfoLogic.saveNotes(projectionId, CommonUtils.getUserNameById(userId), enteredvalue, MODULE_NAME)) {
                            wordList.add(enteredvalue + "<" + CommonUtils.getUserNameById(userId) + ">" + dateTimeFormat.format(new Date()));
                            notesList.add(enteredvalue + "<" + CommonUtils.getUserNameById(userId) + ">" + dateTimeFormat.format(new Date()));

                            notesHistory.setValue(notesHistory.getValue() + newNotes.getValue() + "<" + CommonUtils.getUserNameById(userId) + ">" + dateTimeFormat.format(new Date()) + "\n");
                            newNotes.setValue(StringUtils.EMPTY);
                            documentExporter();
                        }
                    } catch (SystemException | Property.ReadOnlyException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note");
        }
        LOGGER.debug("End of addNotesBtn listener method");
    }

    /**
     * Removes the btn.
     *
     * @param event the event
     */
    @UiHandler("removeBtn")
    public void removeBtn(final Button.ClickEvent event) {
        LOGGER.debug("Inside removeBtn listener method");
        if (tableBeanId == null || resultsTable.size() <= 0 || !resultsTable.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getInfoNotification(Constant.REMOVE_ATTACHMENT, "Please select an attachment to remove.");
        } else {
            if (tableBean.getUserName().equalsIgnoreCase(CommonUtils.getUserNameById(userId))) {

                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        try {
                            AttachmentDTO dto = new AttachmentDTO();
                            dto.setDocDetailsId(tableBean.getDocDetailsId());
                            dto.setDocumentFullPath(tableBean.getDocumentFullPath());
                            removedDetailsList.add(dto);

                            File file = CommonUtil.getFilePath(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + DOCUMENTS + File.separator + MODULE_NAME + File.separator + userId
                                    + File.separator + tableBean.getDocumentName());
                            file.delete();
                            resultsTable.removeItem(tableBeanId);
                            tableBeanId = null;
                            tableBean = (AttachmentDTO) null;

                        } catch (Exception e) {
                            LOGGER.error(e.getMessage());
                        }
                    }
                }.getConfirmationMessage(Constant.REMOVE_ATTACHMENT, "Are you sure you want to delete this Attachment?");

            } else {
                AbstractNotificationUtils.getInfoNotification(Constant.REMOVE_ATTACHMENT, "You can only remove attachments that you have uploaded.");

            }
        }

        LOGGER.debug("End of removeBtn listener method");
    }

    /**
     * Creates the export docs.
     */
    private void createExportDocs() {
        // Creates directory
        Resource wordRes;
        Resource pdfRes;
        LOGGER.debug("Inside AdditionalInformation create Export Docs mehtod " + filePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        wordRes = new FileResource(wordFile);
        pdfRes = new FileResource(pdfFile);
        wordDownloader = new FileDownloader(wordRes);
        pdfDownloader = new FileDownloader(pdfRes);
        wordDownloader.extend(wordBtn);
        pdfDownloader.extend(pdfBtn);
        LOGGER.debug("End of AdditionalInformation create export docs method");
    }

    /**
     * Call java script.
     */
    public void callJavaScript() {
        LOGGER.debug("Inside of AdditionalInformation callJavaScript Method");

        uploader.setDebugId("textId");
        layout.setDebugId("layoutId");

        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var f=document.getElementById('layoutId').children[1];" + "var f2=f.firstChild.firstChild.children[1]; " + "f2.addEventListener('change', function(){" + "var f3=f2.value;"
                        + "callJava(f3)" + "}, false);");
        LOGGER.debug("Ends of AdditionalInformation callJavaScript Method");
    }

    /**
     * Sets the uploder value.
     */
    public void setUploderValue() {
        LOGGER.debug("Inside of AdditionalInformation setUploderValue Method");
        uploader.setValue(StringUtils.EMPTY);
        newNotes.focus();
        LOGGER.debug("Ends of AdditionalInformation setUploderValue Method");
    }

    /**
     * Document exporter.
     */
    public void documentExporter() {
        LOGGER.debug("Indide AdditionalInformation document Exporter");

        final ExportWord exportWord = new ExportWord(filePath, wordFile);
        exportWord.wordExport(wordList);
        final Resource wordResOnEdit = new FileResource(CommonUtil.getFilePath(filePath + File.separator + FILENAME + ExportWord.DOC_EXT));
        wordDownloader.setFileDownloadResource(wordResOnEdit);

        final ExportPdf exportPdf = new ExportPdf(NOTES_HISTORY, filePath, logo, pdfFile);
        exportPdf.export(notesHistory.getValue());
        final Resource pdfResOnEdit = new FileResource(CommonUtil.getFilePath(filePath + File.separator + FILENAME + ExportPdf.PDF_EXT));
        pdfDownloader.setFileDownloadResource(pdfResOnEdit);

        LOGGER.debug("Ends AdditionalInformation document Exporter");
    }

    /**
     * Sets the values.
     *
     * @throws SystemException ,PortalException
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void setValues(boolean saveFlag) throws SystemException {
        LOGGER.debug("Inside of AdditionalInformation setValues Method");
        String modeValue = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.MODE));
        if (Constant.EDIT_SMALL.equalsIgnoreCase(modeValue) || Constant.VIEW.equalsIgnoreCase(modeValue) || saveFlag) {
            attachmentsListBean.removeAllItems();
            final int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(PROJECTION_ID.getConstant());
            final List<AttachmentDTO> allFiles = addInfoLogic.getAttachmentDTOList(projectionId, MODULE_NAME, filePathForLink);

            attachmentsListBean.addAll(addInfoLogic.addUserFile(allFiles));
            wordList.clear();
            notesHistory.setValue(addInfoLogic.getNotes(projectionId, MODULE_NAME, wordList));
            newNotes.setValue(StringUtils.EMPTY);
        }
        LOGGER.debug("Ends of AdditionalInformation setValues Method");
    }

    public List<AttachmentDTO> getUploadedData() {
        return attachmentsListBean.getItemIds();
    }

    public List<String> getAddedNotes() {
        return notesList == null ? notesList : new ArrayList<>(notesList);
    }

    public List<AttachmentDTO> getRemoveDocDetailsItem() {
        return removedDetailsList == null ? removedDetailsList : new ArrayList<>(removedDetailsList);
    }

    public void saveAdditionalInformation() throws SystemException, PortalException  {
        int projectionId = (Integer) VaadinSession.getCurrent()
                .getAttribute(PROJECTION_ID.getConstant());
        List<String> addedNotes = getAddedNotes();
        for (String enteredNotes : addedNotes) {
            addInfoLogic.saveNotes(projectionId, CommonUtils.getUserNameById(userId), enteredNotes, MODULE_NAME);
        }
        notesList.clear();
        for (AttachmentDTO attached : newFileDto) {
            addInfoLogic.saveUploadedFile(projectionId, attached.getDocumentName().getCaption(), CommonUtils.getUserNameById(userId), fileSize, MODULE_NAME);
        }
        List<AttachmentDTO> removedAttachments = getRemoveDocDetailsItem();
        for (AttachmentDTO removed : removedAttachments) {
            if (removed.getDocDetailsId() != 0) {
                addInfoLogic.deleteUploadedFile(removed.getDocDetailsId());
            }
        }
        removedDetailsList.clear();
        // reload the table and text area
        setValues(true);
    }

    public void disableFieldsOnView() {
        addNotesBtn.setEnabled(false);
        newNotes.setEnabled(false);
        notesHistory.setEnabled(false);
        addAttachment.setEnabled(false);
        removeBtn.setEnabled(false);
    }
}
