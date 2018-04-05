/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.gtnforecasting.utils.CommonUIUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.FileUploader;
import com.stpl.app.utils.ValidationUtils;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.Upload.StartedEvent;
import com.vaadin.v7.ui.Upload.SucceededEvent;
import elemental.json.JsonArray;
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

/**
 *
 * @author rohitvignesh.s
 */
public class WorkFlowNotesLookup extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowNotesLookup.class);

    @UiField("fileNameField")
    private TextField fileNameField;

    @UiField("cssLayout1")
    private CssLayout cssLayout1;

    @UiField("notes")
    private TextArea notes;

    @UiField("okBtn")
    private Button okBtn;

    @UiField("table")
    private ExtFilterTable table;

    @UiField("remove")
    private Button remove;

    public TextArea getNotes() {
        return notes;
    }

    public void setNotes(TextArea notes) {
        this.notes = notes;
    }

    private Receiver uploadReceiver;
    private Upload uploadComponent;
    private final TextField uploader = new TextField();
    private final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<>(NotesDTO.class);
    private Object tableBeanId = null;
    private File fileUpload;
    private final FileDownloader fileDownloader = new FileDownloader(new FileResource(CommonUtil.getFilePath("tst")));
    private String fileUploadPath;
    private final List<NotesDTO> removeDetailsList = new ArrayList<>();
    private final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    private boolean isFileRename;
    private NotesDTO tableBean = new NotesDTO();
    private static String SUBMIT_FLAG="";

    public WorkFlowNotesLookup() {
        init();
    }

    private void init() {
        addToContent();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/workFlowNotesLookup.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setWidth(585,Unit.PIXELS);
        setHeight(808, Unit.PIXELS);
        setCaption(Constant.NOTES);
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
        uploader.setStyleName(Constant.SEARCH_TEXT);
        uploader.setImmediate(true);
        uploader.setEnabled(false);
        uploadReceiver = (Receiver) new FileUploader(StringUtils.EMPTY + "/" + userId);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        fileUploadPath = FileUploader.FILE_PATH + StringUtils.EMPTY + "/" + userId + "/";
        uploadComponent.setButtonCaption(Constant.ADD);
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
                    String value = String.valueOf(arguments.get(0).asString());
                    if (StringUtils.isNotEmpty(value)) {

                        fileUpload = CommonUtil.getFilePath(fileUploadPath + value);
                        String name = fileUpload.getAbsolutePath();
                        if (name.contains("\\")) {
                            String replace = name.replace('\\', ',');
                            String[] array = replace.split(",");
                            String filename = array[array.length - 1];
                            uploader.setValue(filename);
                            fileNameField.setValue(StringUtils.isEmpty(fileNameField.getValue()) ? filename.substring(0, filename.lastIndexOf('.')) : fileNameField.getValue());
                        } else if (name.contains("/")) {
                            final String replace = name.replace('/', ',');
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
                removeButtonLogic();
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
                    LOGGER.error(e.getMessage());
                }
            }
        });
        okBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setSUBMIT_FLAG("Success");
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

    public void uploadComponentSucceededLogic(SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(ValidationUtils.SPECIAL_CHAR)) {
                
                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(".");
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf("."), "_" + value);
                File destFileUpload = CommonUtil.getFilePath(fileUploadPath + event.getFilename());
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf("."));
                File renameFileUpload = CommonUtil.getFilePath(fileUploadPath + name);
                isFileRename=destFileUpload.renameTo(renameFileUpload);
                LOGGER.info("File renamed successfully = {} ",isFileRename);
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
                CommonUIUtils.successNotification(attachmentDTO.getDocumentName().substring(0, attachmentDTO.getDocumentName().lastIndexOf('.')) + " uploaded successfully");
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
                fileNameField.setValue(StringUtils.EMPTY);
            }
        } catch (Property.ReadOnlyException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    public void uploadComponentStartedLogic(StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(ValidationUtils.SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase();
                if (event.getFilename().equals(StringUtils.EMPTY)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("No File Name", "Please Enter a valid File Name");
                } else if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
                        || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename
                        .endsWith(".pptx")))) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("Invalid File", "File Not Supported");
                    uploader.setValue(StringUtils.EMPTY);
                    fileNameField.setValue(StringUtils.EMPTY);
                } else if (fileExists(file)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                    uploader.setValue(StringUtils.EMPTY);
                    fileNameField.setValue(StringUtils.EMPTY);
                } else if (StringUtils.isBlank(file) && fileExists(event.getFilename().substring(0, event.getFilename().lastIndexOf('.')))) {
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
        } catch (Property.ReadOnlyException ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    public void removeButtonLogic() {
        
        String temp = tableBean.getUserName();
        if (tableBeanId == null  || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification(Constant.REMOVE_ATTACHMENT, "Please select an attachment to remove ");
        }else{
      
        if (CommonUtils.getUserNameById(userId).equalsIgnoreCase(temp)) {
            AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("Inside Overriden method: do nothing");
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
            notification.getConfirmationMessage(Constant.REMOVE_ATTACHMENT, "Are you sure you want to delete this Attachment?");

        } else {
            AbstractNotificationUtils.getErrorNotification(Constant.REMOVE_ATTACHMENT, "You can only remove attachments that you have uploaded.");
        }
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
                File uploadedFile = CommonUtil.getFilePath(tableBean.getDocumentFullPath());
                Resource res = new FileResource(uploadedFile);
                fileDownloader.setFileDownloadResource(res);
                downloadFile(uploadedFile);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            LOGGER.error(ex.getMessage());
        }
    }

	public static String getSUBMIT_FLAG() {
		return SUBMIT_FLAG;
	}

	public static void setSUBMIT_FLAG(String sUBMIT_FLAG) {
		SUBMIT_FLAG = sUBMIT_FLAG;
	}
}
