package com.stpl.app.gtnforecasting.additionalinformation.form;

import com.stpl.app.gtnforecasting.abstractforecast.AbsAdditionalInformation;
import com.stpl.app.gtnforecasting.additionalinformation.logic.NotesTabLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.ADDITIONAL_INFORMATION;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import com.stpl.app.gtnforecasting.utils.CommonUIUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.FileUploader;
import com.stpl.app.utils.ValidationUtils;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.Upload.StartedEvent;
import com.vaadin.v7.ui.Upload.SucceededEvent;
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
 * @author gopinath
 */
public class AdditionalInformationForm extends AbsAdditionalInformation {

    final StplSecurity stplSecurity = new StplSecurity();

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AdditionalInformationForm.class);

    private final NotesTabLogic logic = new NotesTabLogic();
    private NotesDTO tableBean = new NotesDTO();

    protected final String mode = StringUtils.EMPTY;
    protected final boolean isAddMode;
    protected final boolean isEditMode;
    protected final boolean isViewMode;
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    /**
     * The logo.
     */
    /**
     * The file name.
     */
    /**
     * The notes history.
     */

    public AdditionalInformationForm(String moduleName, int projectionIds, String mode)  {
        super(moduleName, projectionIds, mode);

        mode = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.MODE));
        this.isAddMode = Constant.ADD_SMALL.equalsIgnoreCase(mode);
        this.isEditMode = Constant.EDIT.equalsIgnoreCase(mode);
        this.isViewMode = "View".equalsIgnoreCase(mode);
        table.setContainerDataSource(attachmentsListBean);
        table.setVisibleColumns("documentName", "dateAdded", "userName");
        table.setColumnHeaders("Document Name", "Date Added", "User Name");
        if (isViewMode) {
            removeAndDisablingComponents();
        }
        addSecurity();

    }

    @Override
    public void intailizingObject() {

        uploadReceiver = (Receiver) new FileUploader(moduleName + "/" + userId);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        filePath = CommonUtil.getFilePath(basepath + File.separator + "Documents" + File.separator + moduleName);
        wordFile = CommonUtil.getFilePath(filePath + File.separator + fileName + ExportWord.DOC_EXT);
        pdfFile = CommonUtil.getFilePath(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
        fileUploadPath = FileUploader.FILE_PATH + moduleName + "/" + userId + "/";

        if (isViewMode) {
            removeAndDisablingComponents();
        }

    }

    @Override
    public void addEnteredNotes(ClickEvent event, final AbstractNotificationUtils.Parameter flag) {
        if (!StringUtils.EMPTY.equals(String.valueOf(newNote.getValue()).trim()) && !Constant.NULL.equals(String.valueOf(newNote.getValue()))) {
            new AbstractNotificationUtils() {

                @Override
                public void noMethod() {
                    flag.setOk(false);
                }

                @Override
                public void yesMethod() {
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    TimeZone central = TimeZone.getTimeZone("CST");
                    format.setTimeZone(central);
                    wordList.add("<" + format.format(new Date()) + ">" + CommonUtils.getUserNameById(userId) + " :" + newNote.getValue());
                    notesList.add("<" + format.format(new Date()) + ">" + CommonUtils.getUserNameById(userId) + " :" + newNote.getValue());

                    internalNotes.setEnabled(true);
                    internalNotes.setValue(internalNotes.getValue() + "<" + format.format(new Date()) + ">" + CommonUtils.getUserNameById(userId) + " :" + ":" + newNote.getValue() + "\n");
                    newNote.setValue(StringUtils.EMPTY);
                    internalNotes.setEnabled(false);
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
                destFileUpload.renameTo(renameFileUpload);
                if (!StringUtils.isBlank(file)) {
                    attachmentDTO.setDocumentName(name);
                } else {
                    attachmentDTO.setDocumentName(event.getFilename());
                }
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                TimeZone central = TimeZone.getTimeZone("CST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(Integer.valueOf(userId));
                attachmentDTO.setUserName(StringUtils.EMPTY + CommonUtils.getUserNameById(userId));
                attachmentDTO.setDocumentFullPath(fileUploadPath + name);
                attachmentsListBean.addBean(attachmentDTO);
                fileNameField.setValue(StringUtils.EMPTY);
                uploader.setValue(StringUtils.EMPTY);
                CommonUIUtils.successNotification(attachmentDTO.getDocumentName().substring(0, attachmentDTO.getDocumentName().lastIndexOf(".")) + " uploaded successfully");
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
                fileNameField.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
              LOGGER.error(ex);
        }

    }

    @Override
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
                } else if (StringUtils.isBlank(file) && fileExists(event.getFilename().substring(0, event.getFilename().lastIndexOf(".")))) {
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
            LOGGER.error(ex);
        }

    }

    @Override
    public void removeButtonLogic(ClickEvent event) {
        String temp = tableBean.getUserName();
        if (tableBeanId == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification(Constant.REMOVE_ATTACHMENT, "Please select an attachment to remove ");
        } else {
            if (CommonUtils.getUserNameById(userId).equalsIgnoreCase(temp)) {
            AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    return;
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
        
    @Override
    public void itemClickLogic(ItemClickEvent event) {
        try {
            tableBeanId = event.getItemId();
            BeanItem<?> targetItem = null;
            if (tableBeanId instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) tableBeanId;
            } else if (tableBeanId instanceof NotesDTO) {
                targetItem = new BeanItem<>((NotesDTO) tableBeanId);
            }
            tableBean = (NotesDTO) targetItem.getBean();
            if (event.isDoubleClick()) {
                File uploadedFile = CommonUtil.getFilePath(tableBean.getDocumentFullPath());
                Resource res = new FileResource(uploadedFile);
                fileDownloader.setFileDownloadResource(res);
                downloadFile(uploadedFile);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void refreshTable() {
        return;
    }

    public void saveNotesInformation(int projectionIdValue, String moduleName) {

        try {
            if (projectionId == 0) {
                projectionId = projectionIdValue;
            }
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
            List<String> addNotesList = getNotesList();
            for (String addNotes : addNotesList) {
                logic.saveNotes(Integer.valueOf(projectionId), userId, addNotes, moduleName);
            }
            notesList.clear();
            for (NotesDTO attached : getUploadedData()) {
                logic.saveUploadedFile(projectionId, attached.getDocumentName(), CommonUtils.getUserNameById(userId), 0, moduleName);
            }

            List<NotesDTO> removedAttachments = removeDetailsList();
            for (NotesDTO removed : removedAttachments) {
                if (removed.getDocDetailsId() != 0) {
                    logic.deleteUploadedFile(removed.getDocDetailsId(),  StringUtils.EMPTY);
                }
            }
            removedAttachments.clear();
            loadAttachments(projectionId);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void addNotesBtn() {
        return;
    }

    public void addSecurity() {

        final StplSecurity stplSecurityNotes = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurityNotes.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + ADDITIONAL_INFORMATION);
            if (tabItemHM.get("addNote") != null && tabItemHM.get("addNote").isFunctionFlag()) {
                addNote.setVisible(true);
            } else {
                addNote.setVisible(false);
            }
            if (tabItemHM.get("remove") != null && tabItemHM.get("remove").isFunctionFlag()) {
                remove.setVisible(true);
            } else {
                remove.setVisible(false);
            }
        } catch (Exception system) {
            LOGGER.error(system);
        }

    }

    public void security() throws PortalException, SystemException{

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.PPA_PROJECTION_RESULTS);

        if (functionPsHM.get(FunctionNameUtil.ADD_NOTES) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.ADD_NOTES)).isFunctionFlag()) {
            addNote.setVisible(true);
        } else {
            addNote.setVisible(false);
        }

        if (functionPsHM.get(FunctionNameUtil.REMOVE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.REMOVE)).isFunctionFlag()) {
            remove.setVisible(true);
        } else {
            remove.setVisible(false);
        }
    }
     /**
     * 
     * Method to re load Attachment Table after save
     * @param projectionId
     * @throws Exception 
     */
    public void loadAttachments(int projectionId) throws Exception {
        attachmentsListBean.removeAllItems();
        final List<NotesDTO> allFiles = logic.getAttachmentDTOList(projectionId, moduleName, fileUploadPath);

        attachmentsListBean.addAll(logic.addUserFile(allFiles));
    }
}
