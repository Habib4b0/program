/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.AbstractForms;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.FileUploader;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.errorhandling.ErrorLabel;
import com.stpl.app.utils.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.themes.Reindeer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class NotesTabForm extends AbstractNotesTab implements DefaultFocusable{

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(NotesTabForm.class);
    private String masterTableSid;
    private final CustomFieldGroup binder;
    private final String moduleName;
    private String adjustmentType;
    String userId;
    private String userName = "";
    private NotesDTO tableBean = new NotesDTO();
    ComboBox reasonCode = new ComboBox();
    protected final String mode = StringUtils.EMPTY;
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    private List<String> notesList = new ArrayList<>();
    private final SessionDTO sessionDTO;
    public static final Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();
    NotesDTO binderDto = new NotesDTO();
    public ErrorfulFieldGroup notesBinder = new ErrorfulFieldGroup(new BeanItem<NotesDTO>(binderDto));
    public ErrorLabel errorMsg = new ErrorLabel();
    public NotesTabForm(FieldGroup binder, String moduleName, SessionDTO sessionDTO,String adjustmentType) throws SystemException {
        super(binder, moduleName);
        getNotesBinder();
        configureField();
        this.masterTableSid = masterTableSid;
        this.binder = (CustomFieldGroup) binder;
        this.moduleName = moduleName;
        this.sessionDTO = sessionDTO;
        this.adjustmentType=adjustmentType;
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        getUserName();
        userName = userMap.get(Integer.valueOf(userId));
        Object[] obj = new Object[]{"documentName", "dateAdded", "userName"};
        String[] objHeaders = new String[]{"Document Name", "Date Added", "User Name"};
        table.setContainerDataSource(attachmentsListBean);
        table.setVisibleColumns(obj);
        table.setColumnHeaders(objHeaders);
        configureGeneratedColumn();
        if(!this.adjustmentType.isEmpty()){
            addReasonCodeDdlb();
        }
        if (ARMUtils.VIEW_SMALL.equals(this.sessionDTO.getAction())) {
            List<NotesDTO> list = CommonLogic.retrieveNotesInfo(this.sessionDTO);
            setNotesInformation(list);
            configureFieldsOnViewMode();
        } else if (ARMUtils.EDIT_SMALL.equals(this.sessionDTO.getAction())) {
            List<NotesDTO> list = CommonLogic.retrieveNotesInfo(this.sessionDTO);
            setNotesInformation(list);
        }
    }

    private void configureField() {
        vlayout.addComponent(errorMsg,0);
        newNote.setMaxLength(NumericConstants.TWO_HUNDRED);
        newNote.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        newNote.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\s])*", "Special characters are not allowed"));
        newNote.setImmediate(true);
        newNote.setValidationVisible(true);
    }

    public FieldGroup getNotesBinder() {
        notesBinder.setErrorDisplay(errorMsg);
        notesBinder.bindMemberFields(this);
        notesBinder.setBuffered(true);
        errorMsg.setId("ErrorMessage");
        return notesBinder;
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
            if ("0".equalsIgnoreCase(String.valueOf(reasonCode.getValue())) || "null".equalsIgnoreCase(String.valueOf(reasonCode.getValue())) || GlobalConstants.getSelectOne().equalsIgnoreCase(String.valueOf(reasonCode.getValue()))) {
                AbstractNotificationUtils.getInfoNotification("Choose a Reason Code", "Please choose a reason code", flag);
                return;
            }
        if (StringUtils.EMPTY.equals(String.valueOf(newNote.getValue()).trim()) || "null".equals(String.valueOf(newNote.getValue()))) {
            AbstractNotificationUtils.getInfoNotification("Enter New Note", "Please enter a new note", flag);
            newNote.focus();
            return;
        } else {
            try {
                notesBinder.getErrorDisplay().clearError();
                notesBinder.commit();
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    flag.setOk(false);
                }

                @Override
                public void yesMethod() {
                    SimpleDateFormat format = new SimpleDateFormat("HH/mm/ss");
                    SimpleDateFormat format1 = new SimpleDateFormat(" MM/dd/yyyy");
                    TimeZone central = TimeZone.getTimeZone("CST");
                    format.setTimeZone(central);
                    userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID));
                    CommonUtils.getUserName();
                    notesList.add("<" + format.format(new Date()) + ">" + CommonUtils.getUserNameById(userId) + " :" + newNote.getValue());
                    String addedNotes = internalNotes.getValue() + "<" + CommonUtils.getUserNameById(userId) + "> " + " <" + format.format(new Date()) + "> " + " <" + format1.format(new Date()) + ">" + ":" + String.valueOf(newNote.getValue()) + "\n";
                    internalNotes.setReadOnly(false);
                    internalNotes.setValue(addedNotes);
                    newNote.setValue(StringUtils.EMPTY);
                    internalNotes.setReadOnly(true);
                    documentExporter();
                    flag.setOk(false);
                }
            }.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note?");
            } catch (FieldGroup.CommitException ex) {
                LOGGER.error(ex);
                flag.setOk(Boolean.FALSE);
                return;
            }

        }

    }

    @Override
    public void uploadComponentSucceededLogic(Upload.SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(SPECIAL_CHAR)) {
                CommonUIUtils.successNotification(file + " uploaded successfully");
                fileUpload = new File(fileUploadPath + event.getFilename());
                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(".");
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf("."), "_" + value);
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf("."));
                attachmentDTO.setDocumentName(name);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                TimeZone central = TimeZone.getTimeZone("CST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(Integer.valueOf(userId));
                attachmentDTO.setUserName(CommonUtils.getUserNameById(userId));
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
            if (StringUtils.isNotBlank(file) && file.matches(SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase();
                if (event.getFilename().equals(StringUtils.EMPTY)) {
                    uploadComponent.interruptUpload();
                    AbstractNotificationUtils.getErrorNotification("Add Attachment", "Please select an attachment to add");
                } else if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
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
        String CurrentUserName = tableBean.getUserName();
        if (CurrentUserName.contains(",")) {
            String[] str = CurrentUserName.split(",");
            CurrentUserName = str[1] + " " + str[0];
        }
        if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification("Remove Attachment", "Please select an attachment to remove ");
        } else if (!CurrentUserName.trim().equalsIgnoreCase(userName.trim())) {
            AbstractNotificationUtils.getInfoNotification("Remove Attachment", "You can only remove attachments that you have uploaded.");
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
        tableBean = getBeanItem(tableBeanId);

    }

    private NotesDTO getBeanItem(Object tableBeanId) {
        BeanItem<?> targetItem = null;
        if (tableBeanId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableBeanId;
        } else if (tableBeanId instanceof NotesDTO) {
            targetItem = new BeanItem<>((NotesDTO) tableBeanId);
        }
        return (NotesDTO) targetItem.getBean();
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
            LOGGER.debug("masterTableSid :" + masterTableSid);
            LOGGER.debug("masterTableSidValue :" + masterTableSidValue);
            int systemId = Integer.valueOf(masterTableSidValue.replace(",", StringUtils.EMPTY));
            if (systemId != 0) {
            }
        } catch (FieldGroup.CommitException e) {
            LOGGER.error(e);
        }
    }

    public String getMasterTableSid() {
        return masterTableSid;
    }

    public void setMasterTableSid(String masterTableSid) {
        this.masterTableSid = masterTableSid;
    }

    public void addReasonCodeDdlb() {
        HorizontalLayout lay = new HorizontalLayout();
        Label label = new Label("Reason Code");
        reasonCode.setNullSelectionAllowed(true);
        reasonCode.setNullSelectionItemId(0);
        reasonCode.addItem(0);
        reasonCode.setItemCaption(0, GlobalConstants.getSelectOne());
        reasonCode.select(null);
        CommonLogic.loadComboBoxNonHelperDto(reasonCode, "REASON_CODES");
        lay.addComponent(label);
        lay.addComponent(reasonCode);
        reasoncodeLayout.addComponentAsFirst(lay);
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

    public List<String> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<String> notesList) {
        this.notesList = notesList;
    }

    public void saveNotesInformation(int projectionIdValue, String moduleName) {
        try {
            CommonUtils.getUserName();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID));
            List<String> addNotesList = getNotesList();
              if (!addNotesList.isEmpty()) {
                CommonLogic.saveNotes(projectionIdValue, userId, addNotesList, moduleName, String.valueOf(reasonCode.getValue()));
                notesList.clear();
                CommonLogic.saveUploadedFile(projectionIdValue, getUploadedData(), CommonUtils.getUserNameById(userId), 0, moduleName);
                List<NotesDTO> removedAttachments = removeDetailsList();
                CommonLogic.deleteUploadedFile(removedAttachments);
                removedAttachments.clear();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureFieldsOnViewMode() {
        newNote.setEnabled(false);
        addNote.setEnabled(false);
        fileNameField.setEnabled(false);
        uploader.setEnabled(false);
        uploader.setEnabled(false);
        uploadComponent.setEnabled(false);
        remove.setEnabled(false);
    }

    public void setNotesInformation(List<NotesDTO> notesDTO) {
        internalNotes.setReadOnly(false);
        if (notesDTO.size() != 0) {
            internalNotes.setValue(notesDTO.get(0).getNotesHistory());
            attachmentsListBean.addAll(notesDTO);
            reasonCode.select(notesDTO.get(0).getReasonCode());
        }
        internalNotes.setReadOnly(true);
    }

    public void configureGeneratedColumn() {

        table.addGeneratedColumn("documentName", new ExtFilterTable.ColumnGenerator() {

            @Override
            public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                final NotesDTO tableDto = getBeanItem(itemId);
                Button documentNameLink = new Button(tableDto.getDocumentName());
                documentNameLink.setStyleName(Reindeer.BUTTON_LINK);
                documentNameLink.setImmediate(true);
                documentNameLink.addClickListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final Button.ClickEvent event) {
                        File uploadedFile = new File(tableDto.getDocumentFullPath());
                        Resource res = new FileResource(uploadedFile);
                        fileDownloader.setFileDownloadResource(res);
                        downloadFile(uploadedFile);
                    }
                });
                return documentNameLink;

            }
        });
    }

    private void getUserName() throws SystemException {
        tableBean.setUserName(CommonUtils.getUserNameById(userId));
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
    }
     @Override
    public  Focusable getDefaultFocusComponent(){
        return newNote;
}
}
