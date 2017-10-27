/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.security.StplSecurity;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.FileUploader;
import com.stpl.app.accountclose.utils.NotesTabLogic;
import com.stpl.app.accountclose.utils.ValidationUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import de.steinwedel.messagebox.MessageBox;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author porchelvi.g
 */
public class NotesTabForm extends AbstractNotesTab {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(NotesTabForm.class);
    private String masterTableSid;
    private final CustomFieldGroup binder;
    private final String moduleName;
    final String userId;
    private final String userName;
    private final NotesTabLogic logic = new NotesTabLogic();
    private NotesDTO tableBean = new NotesDTO();
    ComboBox reasonCode = new ComboBox();
    protected final String mode = StringUtils.EMPTY;
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    public NotesTabForm(CustomFieldGroup binder, String moduleName) throws Exception {
        super(binder, moduleName);
        this.masterTableSid = masterTableSid;
        this.binder = binder;
        this.moduleName = moduleName;
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        userName = StplSecurity.userMap.get(Integer.valueOf(userId));
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        final Map<String, AppPermission> fieldNotesHM = stplSecurity.getFieldOrColumnPermission(userId, moduleName + "," + "Additional Information", false);
        final Map<String, AppPermission> functionNotesHM = stplSecurity.getBusinessFunctionPermission(userId, moduleName + "," + "Additional Information");
        getNotesTab(fieldNotesHM, functionNotesHM);
        final Map<String, AppPermission> fieldNotesTableHM = stplSecurity.getFieldOrColumnPermission(userId, moduleName + "," + "Additional Information", false);
        List<Object> resultList = getFieldsForSecurity(moduleName, "Additional Information");
        Object[] obj = new Object[]{"documentName", "dateAdded", "userName"};
        String[] objHeaders = new String[]{"Document Name", "Date Added", "User Name"};
        table.setContainerDataSource(attachmentsListBean);
        table.setVisibleColumns(obj);
        table.setColumnHeaders(objHeaders);
        LOGGER.info("userid :" + userId + " Username : " + userName);
        if (moduleName == "Fixed Dollar Adjustment") {
            addReasonCodeDdlb();
        }
    }

    @Override
    public void intailizingObject() {
        uploadReceiver = (Upload.Receiver) new FileUploader(basepath, moduleName);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        filePath = new File(basepath + File.separator + "Documents" + File.separator + moduleName);
        wordFile = new File(filePath + File.separator + fileName + ExportWord.DOC_EXT);
        pdfFile = new File(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
        fileUploadPath = FileUploader.FILE_PATH + moduleName + "/";
    }

    @Override
    public void addEnteredNotes(Button.ClickEvent event, final AbstractNotificationUtils.Parameter flag) {
        if (!StringUtils.EMPTY.equals(String.valueOf(newNote.getValue()).trim()) && !"null".equals(String.valueOf(newNote.getValue()))) {
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
    public void uploadComponentSucceededLogic(Upload.SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(ValidationUtil.SPECIAL_CHAR)) {
                CommonUIUtils.successNotification(file + " uploaded successfully");
                fileUpload = new File(fileUploadPath + event.getFilename());
                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(".");
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf("."), "_" + value);
                File destFileUpload = new File(fileUploadPath + sb.toString());
                boolean status = fileUpload.renameTo(new File(destFileUpload.getAbsolutePath()));
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf("."));
                attachmentDTO.setDocumentName(name);
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                TimeZone central = TimeZone.getTimeZone("CST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(Integer.valueOf(userId));
                attachmentDTO.setUserName(userName);
                attachmentDTO.setDocumentFullPath(fileUploadPath + sb.toString());
                attachmentsListBean.addBean(attachmentDTO);
                fileNameField.setValue(StringUtils.EMPTY);
                uploader.setValue(StringUtils.EMPTY);
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void uploadComponentStartedLogic(Upload.StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(ValidationUtil.SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase();
                if (event.getFilename().equals(StringUtils.EMPTY)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("Add Attachment", "Please select an attachment to add");
                } else {
                    if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
                            || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename
                            .endsWith(".pptx")))) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getErrorNotification("Invalid File", "File Not Support");
                        uploader.setValue(StringUtils.EMPTY);
                    } else if (fileExists(file)) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                        uploader.setValue(StringUtils.EMPTY);
                    } else {
                        uploader.setValue(StringUtils.EMPTY);
                    }
                }
            } else {
                uploadComponent.interruptUpload();
                AbstractNotificationUtils.getErrorNotification("No File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void removeButtonLogic(Button.ClickEvent event) {
        if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification("Remove Attachment", "Please select an attachment to remove ");
        } else {
            if (!tableBean.getUserName().equalsIgnoreCase(userName)) {
                final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification("Remove Attachment", "You can only remove attachments that you have uploaded.");
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
            String masterTableSidValue = StringUtils.EMPTY;
            binder.commit();
            if ("Compliance Deduction Rules".equals(this.moduleName)) {
                masterTableSidValue = masterTableSid;
            } else {
                masterTableSidValue = String.valueOf(binder.getField(masterTableSid).getValue());
            }
            LOGGER.info("masterTableSid :" + masterTableSid);
            LOGGER.info("masterTableSidValue :" + masterTableSidValue);
            int systemId = Integer.valueOf(masterTableSidValue.replace(",", StringUtils.EMPTY));
            if (systemId != 0) {
                attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, "ACC_CLOSURE_MASTER", fileUploadPath));
            }
        } catch (FieldGroup.CommitException e) {
            LOGGER.error(e);
        }
    }

    private void getNotesTab(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {
        LOGGER.info("Entering getFirstTab1");
        try {

            List<Object> resultList = getFieldsForSecurity(moduleName, "Additional Information");

            commonUiUtil.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode, binder);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssHistoryNote, fieldCompanyHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssNewNote, fieldCompanyHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode);
            if (functionHM.get(Constants.ADD_NOTE) != null && !((AppPermission) functionHM.get(Constants.ADD_NOTE)).isFunctionFlag()) {
                addBtnlayout.removeComponent(getAddNote());
            }

            if (functionHM.get(Constants.REMOVE_NOTE) != null && !((AppPermission) functionHM.get(Constants.REMOVE_NOTE)).isFunctionFlag()) {
                tableLayout.removeComponent(getRemove());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending getFirstTab1");

    }

    public String getMasterTableSid() {
        return masterTableSid;
    }

    public void setMasterTableSid(String masterTableSid) {
        this.masterTableSid = masterTableSid;
    }

    public void addReasonCodeDdlb() throws Exception {
        CommonUtil commonUtil = CommonUtil.getInstance();
        HorizontalLayout lay = new HorizontalLayout();
        Label label = new Label("Reason Code");
        reasonCode.setInvalidAllowed(false);
        reasonCode.setNullSelectionAllowed(false);
        commonUtil.loadComboBox(reasonCode, "REASON_CODES", false);
        lay.addComponent(label);
        lay.addComponent(reasonCode);
        reasoncodeLayout.addComponentAsFirst(lay);
    }

    public ComboBox getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ComboBox reasonCode) {
        this.reasonCode = reasonCode;
    }

    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
}
