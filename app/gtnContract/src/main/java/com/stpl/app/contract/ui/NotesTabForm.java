/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.ui;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.global.logic.NotesTabLogic;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.FileUploader;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author
 */
public class NotesTabForm extends AbstractNotesTab {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(NotesTabForm.class);
    private final String masterTableSid;
    private final String dbModuleName;
    private final CustomFieldGroup binder;
    private final String moduleName;
    final String userId;
    private final String userName;
    private final NotesTabLogic logic = new NotesTabLogic();
    private NotesDTO tableBean = new NotesDTO();
    protected final String mode;
    protected final boolean isAddMode;
    protected final boolean isEditMode;
    protected final boolean isViewMode;
    CommonUIUtils commonUIUtils = new CommonUIUtils();
    final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * The Constant SPECIAL_CHAR.
     */
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public NotesTabForm(CustomFieldGroup binder, String moduleName, String dbModuleName, String masterTableSid, String mode) {
        super(binder, moduleName);
        this.masterTableSid = masterTableSid;
        this.dbModuleName = dbModuleName;
        this.binder = binder;
        this.moduleName = moduleName;
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        userName = StplSecurity.userMap.get(Integer.valueOf(userId));
        this.mode = mode;
        this.isAddMode = "Add".equals(mode);
        this.isEditMode = "Edit".equals(mode);
        this.isViewMode = "View".equals(mode);
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            getNotesTab();
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldNotesTableHM = stplSecurity.getFieldOrColumnPermission(userId, moduleName + "," + "Notes", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(moduleName, "Notes");
            Object[] obj = new Object[]{"documentName", "dateAdded", "userName"};
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldNotesTableHM, "View".equals(mode) ? "view" : mode);
            table.setContainerDataSource(attachmentsListBean);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());
            configureOnView();
            LOGGER.debug("userid :" + userId + " Username : " + userName);
        } catch (SystemException ex) {
           LOGGER.error(ex);
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }

    @Override
    public void intailizingObject() {
        uploadReceiver = (Receiver) new FileUploader(moduleName);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        filePath = new File(basepath + File.separator + "Documents" + File.separator + moduleName);
        wordFile = new File(filePath + File.separator + fileName + ExportWord.DOC_EXT);
        pdfFile = new File(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
        fileUploadPath = FileUploader.FILE_PATH + moduleName + "/";
    }

    private void configureOnView() {
        if ("View".equals(mode)) {
            removeAndDisablingComponents();
        }
    }

    @Override
    public void addEnteredNotes(ClickEvent event, final com.stpl.ifs.ui.util.AbstractNotificationUtils.Parameter flag) {
        if (!"".equals(String.valueOf(newNote.getValue()).trim()) && !"null".equals(String.valueOf(newNote.getValue()))) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    flag.setOk(false);
                }

                @Override
                public void yesMethod() {
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    TimeZone central = TimeZone.getTimeZone("EST");
                    format.setTimeZone(central);
                    String addedNotes = internalNotes.getValue() + "<" + format.format(new Date()) + "> " + userName + ":" + String.valueOf(newNote.getValue()) + "\n";
                    internalNotes.setReadOnly(false);
                    internalNotes.setValue(addedNotes);
                    newNote.setValue(StringUtils.EMPTY);
                    internalNotes.setReadOnly(true);

                    documentExporter();
                    flag.setOk(false);
                }
            }.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note?");
        } else {
            AbstractNotificationUtils.getInfoNotification("Enter New Note", "Please enter a new note", flag);
            newNote.focus();

        }

    }

    @Override
    public void uploadComponentSucceededLogic(SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(SPECIAL_CHAR)) {
                fileUpload = new File(fileUploadPath + event.getFilename());
                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(".");
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf("."), "_" + value);
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf("."));
                if (!StringUtils.isBlank(file)) {
                    attachmentDTO.setDocumentName(name);
                } else {
                    attachmentDTO.setDocumentName(event.getFilename());
                }

                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                TimeZone central = TimeZone.getTimeZone("EST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(Integer.valueOf(userId));
                attachmentDTO.setUserName(userName);
                attachmentDTO.setDocumentFullPath(fileUploadPath + sb.toString());
                attachmentsListBean.addBean(attachmentDTO);
                fileNameField.setValue("");
                uploader.setValue("");
                CommonUIUtils.successNotification(attachmentDTO.getDocumentName().substring(0, attachmentDTO.getDocumentName().lastIndexOf(".")) + " uploaded successfully");
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue("");
                fileNameField.setValue("");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void uploadComponentStartedLogic(StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (file.matches(SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase();
                if (event.getFilename().equals("")) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("Add Attachment", "Please select an attachment to add");
                } else {
                    if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
                            || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename
                            .endsWith(".pptx")))) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getErrorNotification("Invalid File", "File Not Support");
                        uploader.setValue("");
                        fileNameField.setValue("");
                    } else if (fileExists(file)) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                        uploader.setValue("");
                        fileNameField.setValue("");
                    }  else if (StringUtils.isBlank(file) && fileExists(event.getFilename().substring(0, event.getFilename().lastIndexOf(".")))) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                        uploader.setValue("");
                        fileNameField.setValue("");
                    }else {
                        uploader.setValue("");
                    }
                }
            } else {
                uploadComponent.interruptUpload();
                AbstractNotificationUtils.getErrorNotification("No File Name", "Please Enter a valid File Name");
                uploader.setValue("");
                fileNameField.setValue("");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void removeButtonLogic(ClickEvent event) {
        if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification("Remove Attachment", "Please select an attachment to remove ");
        } else {
            AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                   
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
            notification.getConfirmationMessage("Remove Attachment", "Are you sure you want to delete this Attachment?");

        }

    }

    @Override
    public void itemClickLogic(ItemClickEvent event) {
        tableBeanId = event.getItemId();
        BeanItem<?> targetItem = null;
        if (tableBeanId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableBeanId;
        } else if (tableBeanId instanceof NotesDTO) {
            targetItem = new BeanItem<NotesDTO>((NotesDTO) tableBeanId);
        }
        tableBean = (NotesDTO) targetItem.getBean();
        if (event.isDoubleClick()) {
            File uploadedFile = new File(tableBean.getDocumentFullPath());
            Resource res = new FileResource(uploadedFile);
            fileDownloader.setFileDownloadResource(res);
            downloadFile(uploadedFile);
        }

    }

    @Override
    public void refreshTable() {
        try {
            binder.commit();
            final String masterTableSidValue = String.valueOf(binder.getField(masterTableSid).getValue());
            LOGGER.debug("masterTableSid :" + masterTableSid);
            LOGGER.debug("masterTableSidValue :" + masterTableSidValue);
            int systemId = Integer.valueOf(masterTableSidValue.replace(",", ""));
            if (systemId != 0) {
                attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, dbModuleName, fileUploadPath));
            }
        } catch (CommitException e) {
            LOGGER.error("Error while commiting the binder :" + e);
        }
    }

    private void getNotesTab() {
        LOGGER.debug("Entering getFirstTab1");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        try {
            final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, moduleName + "," + "Notes", false);
            final Map<String, AppPermission> functionNotesHM = stplSecurity.getBusinessFunctionPermission(userId, moduleName + "," + "Notes");
            List<Object> resultList = contractLogic.getFieldsForSecurity(moduleName, "Notes");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssHistoryNote, fieldContract, "View".equals(mode) ? "view" : mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssNewNote, fieldContract, "View".equals(mode) ? "view" : mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, fieldContract, "View".equals(mode) ? "view" : mode);
            if (functionNotesHM.get(CHFunctionNameUtils.ADD_NOTE) != null && !((AppPermission) functionNotesHM.get(CHFunctionNameUtils.ADD_NOTE)).isFunctionFlag()) {
                getAddNote().setVisible(false);
            }
            if (functionNotesHM.get(CHFunctionNameUtils.REMOVE_NOTE) != null && !((AppPermission) functionNotesHM.get(CHFunctionNameUtils.REMOVE_NOTE)).isFunctionFlag()) {
                getRemove().setVisible(false);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");

    }
}
