/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.workflow.lookup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.workflow.utils.FileUploader;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.ui.Window;
import elemental.json.JsonArray;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Asha.Ravi
 */
public class WorkFlowNotesLookup extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowNotesLookup.class);

    @UiField("fileNameField")
    protected TextField fileNameField;

    @UiField("cssLayout1")
    public CssLayout cssLayout1;

    @UiField("notes")
    protected TextArea notes;

    @UiField("okBtn")
    public Button okBtn;

    @UiField("table")
    protected ExtFilterTable table;

    @UiField("remove")
    public Button remove;

    public TextArea getNotes() {
        return notes;
    }

    public void setNotes(TextArea notes) {
        this.notes = notes;
    }

    protected Upload.Receiver uploadReceiver;
    protected Upload uploadComponent;
    protected final TextField uploader = new TextField();
    protected String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY;
    protected Image wordPngImage = new Image(null, new ThemeResource("../../icons/word.png"));
    protected Image pdfPngImage = new Image(null, new ThemeResource("../../icons/pdf.png"));
    protected final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<>(NotesDTO.class);
    protected Object tableBeanId = null;
    protected File fileUpload;
    protected final FileDownloader fileDownloader = new FileDownloader(new FileResource(new CommonUtil().getFileName("tst")));
    protected String fileName;
    protected String fileUploadPath;
    private List<NotesDTO> removeDetailsList = new ArrayList<>();
    protected String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    private NotesDTO tableBean = new NotesDTO();

    public static String getSubmitFlag() {
        return submitFlag;
    }
    private static String submitFlag = "";

    public WorkFlowNotesLookup() {
        init();
    }

    private void init() {
        addToContent();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(WorkFlowNotesLookup.class.getResourceAsStream("/bussinessprocess/workFlowNotesLookup.xml"), this));
        addStyleName(ARMUtils.BOOTSTRAP_UI);
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setCaption(ARMUtils.NOTES);
        center();
        setClosable(true);
        setModal(true);
    }

    private void configureFields() {
        addTable();
        notes.setInputPrompt("- Enter New Note -");
        Label addAttachmentLable = new Label();
        addAttachmentLable.setValue("Attachment");
        addAttachmentLable.addStyleName("attachment");
        fileNameField.setImmediate(true);
        fileNameField.setMaxLength(NumericConstants.TWO_FIVE_ZERO);
        fileNameField.addValidator(new StringLengthValidator(" File Name Should be less than 250 characters", 0, NumericConstants.TWO_FIVE_ZERO, true));
        uploader.setStyleName(ARMUtils.SEARCH_TEXT);
        uploader.setImmediate(true);
        uploader.setEnabled(false);
        uploadReceiver = new FileUploader(StringUtils.EMPTY + "/" + userId);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        fileUploadPath = FileUploader.FILE_PATH + StringUtils.EMPTY + "/" + userId + "/";
        uploadComponent.setButtonCaption(ARMUtils.ADD);
        uploadComponent.setStyleName("uploadIdBB");
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

        JavaScript.getCurrent().addFunction("callJava", new JavaScriptFunction() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void call(JsonArray arguments) {
                try {
                    String value = String.valueOf(arguments.get(0));
                    if (StringUtils.isNotEmpty(value)) {
                        fileUpload = new CommonUtil().getFileName(fileUploadPath + value);
                        String name = fileUpload.getAbsolutePath();
                        if (name.contains("\\")) {
                            String replace = name.replace("\\", ARMUtils.COMMA);
                            String[] array = replace.split(",");
                            String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : fileNameField.getValue());
                        } else if (name.contains("/")) {
                            final String replace = name.replace("/", ARMUtils.COMMA);
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
                } catch (Exception ex) {
                    LOGGER.error("Error in call :" , ex);
                }
                uploader.focus();
            }
        });

        uploader.addFocusListener(new FocusListener() {
            /**
             * Will execute,when we click an uploader.
             */
            @Override
            public void focus(final FocusEvent event) {
                uploadComponent.focus();
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
                    LOGGER.error("Error while clicking the Item :", e);
                }
            }
        });
        okBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setWorkFlowLookupFlag("Success");
                close();
            }
        });
    }

    public void callJavaScript() {
        uploader.setDebugId("textId");
        uploadComponent.setId("layoutId");
        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var f=document.getElementById('layoutId').firstChild;" + "var f2=f.children[1]; " + "f2.addEventListener('change', function(){" + "var f3=f2.value;" + "callJava(f3)"
                        + "}, false);");

    }

    private void addTable() {
        table.markAsDirty();
        table.addStyleName(ARMUtils.FILTERBAR);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setPageLength(NumericConstants.SEVEN);
        table.setContainerDataSource(attachmentsListBean);
        table.setSelectable(true);
        table.setVisibleColumns(new Object[]{"documentName", "dateAdded", "userName"});
        table.setColumnHeaders(new String[]{"Document Name", "Date Added", "User Name"});
    }

    public void uploadComponentSucceededLogic(Upload.SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(ARMUtils.SPECIAL_CHAR)) {

                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(ARMUtils.DOT);
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf(ARMUtils.DOT), ARMUtils.UNDERSCORE + value);
                File destFileUpload = new CommonUtil().getFileName(fileUploadPath + event.getFilename());
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf(ARMUtils.DOT));
                File renameFileUpload = new File(FilenameUtils.getName(fileUploadPath + name));
                boolean val = destFileUpload.renameTo(renameFileUpload);
                LOGGER.debug("FILE RENAMED {}", val);
                if (!StringUtils.isBlank(file)) {
                    attachmentDTO.setDocumentName(name);
                } else {
                    attachmentDTO.setDocumentName(event.getFilename());
                }
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                TimeZone central = TimeZone.getTimeZone("CST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(Integer.parseInt(userId));
                attachmentDTO.setUserName(StringUtils.EMPTY + CommonUtils.getUserNameById(userId));
                attachmentDTO.setDocumentFullPath(fileUploadPath + name);
                attachmentsListBean.addBean(attachmentDTO);
                fileNameField.setValue(StringUtils.EMPTY);
                uploader.setValue(StringUtils.EMPTY);
                CommonUIUtils.successNotification(attachmentDTO.getDocumentName().substring(0, attachmentDTO.getDocumentName().lastIndexOf(ARMUtils.DOT)) + " uploaded successfully");
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
                fileNameField.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in uploadComponentSucceededLogic :" , ex);
        }

    }

    public void uploadComponentStartedLogic(Upload.StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(ARMUtils.SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase(Locale.ENGLISH);
                if (event.getFilename().equals(StringUtils.EMPTY)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("No File Name", "Please Enter a valid File Name");
                } else if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
                        || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename
                        .endsWith(".pptx")))) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("Invalid File", "File Not Support");
                    uploader.setValue(StringUtils.EMPTY);
                    fileNameField.setValue(StringUtils.EMPTY);
                } else if ((StringUtils.isBlank(file) && fileExists(event.getFilename().substring(0, event.getFilename().lastIndexOf(ARMUtils.DOT)))) || fileExists(file)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                    uploader.setValue(StringUtils.EMPTY);
                    fileNameField.setValue(StringUtils.EMPTY);
                } else {
                    uploader.setValue(StringUtils.EMPTY);
                }
            } else {
                uploadComponent.interruptUpload();
                AbstractNotificationUtils.getErrorNotification("Add Attachment", "Please select an attachment to add");
                uploader.setValue(StringUtils.EMPTY);
                fileNameField.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in uploadComponentStartedLogic :" , ex);
        }

    }

    public void removeButtonLogic(Button.ClickEvent event) {

        String temp = tableBean.getUserName();
        if (tableBeanId == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.REMOVE_ATTACHMENT, "Please select an attachment to remove ");
        }

        if (CommonUtils.getUserNameById(userId).equalsIgnoreCase(temp)) {
            AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("inside removeButtonLogic NO Method");
                }

                @Override
                public void yesMethod() {
                    NotesDTO dtoValue = new NotesDTO();
                    dtoValue.setDocDetailsId(tableBean.getDocDetailsId());
                    dtoValue.setDocumentFullPath(tableBean.getDocumentFullPath());
                    removeDetailsList.add(dtoValue);
                    table.removeItem(tableBeanId);
                    tableBeanId = null;
                    tableBean = null;
                }
            };
            notification.getConfirmationMessage(CommonConstant.REMOVE_ATTACHMENT, "Are you sure you want to delete this Attachment?");

        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.REMOVE_ATTACHMENT, "You can only remove attachments that you have uploaded.");
        }
    }

    public void itemClickLogic(ItemClickEvent event) {
        try {
            tableBeanId = event.getItemId();
            BeanItem<?> targetItem = null;
            if (tableBeanId instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) tableBeanId;
            } else if (tableBeanId instanceof NotesDTO) {
                targetItem = new BeanItem<>((NotesDTO) tableBeanId);
            }
            if (targetItem != null) {
                tableBean = (NotesDTO) targetItem.getBean();
            }
            if (event.isDoubleClick()) {
                File uploadedFile = new CommonUtil().getFileName(tableBean.getDocumentFullPath());
                Resource res = new FileResource(uploadedFile);
                fileDownloader.setFileDownloadResource(res);
                downloadFile(uploadedFile);
            }
        } catch (Exception e) {
            LOGGER.error("Error in itemClickLogic :", e);
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

    public void downloadFile(File uploadedFile) {
        try {
            if (uploadedFile.exists()) {
                Resource res = new FileResource(uploadedFile);
                Page.getCurrent().open(res, "_blank", true);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in downloadFile :" , ex);
        }
    }

    public static void setWorkFlowLookupFlag(String flag) {
        WorkFlowNotesLookup.submitFlag = flag;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
