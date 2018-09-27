/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.abstractforms;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.FileUploader;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.errorhandling.ErrorLabel;
import com.stpl.app.utils.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.themes.Reindeer;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.G
 */
public class NotesTabForm extends AbstractNotesTab implements DefaultFocusable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesTabForm.class);
    private String masterTableSid;
    private final CustomFieldGroup binder;
    private String adjustmentType;
    private String userId;
    private String userName = "";
    private NotesDTO tableBean = new NotesDTO();
    private final ComboBox reasonCode = new ComboBox();
    protected String mode = StringUtils.EMPTY;
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    private List<String> notesList = new ArrayList<>();
    private final SessionDTO sessionDTO;
    protected final Map<Integer, String> userMap = new ConcurrentHashMap<>();
    private final NotesDTO binderDto = new NotesDTO();
    private final ErrorfulFieldGroup notesBinder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private final ErrorLabel errorMsg = new ErrorLabel();
    private Object[] visibleColumnsobj = new Object[]{"documentName", "dateAdded", "userName"};
    private String[] columnHeadersobj = new String[]{"Document Name", "Date Added", "User Name"};

    public NotesTabForm(CustomFieldGroup binder, String moduleName, SessionDTO sessionDTO, String adjustmentType) throws SystemException {
        super(binder, moduleName);
        getNotesBinder();
        configureField();
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        this.adjustmentType = adjustmentType;
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        getUserName();
        userName = userMap.get(Integer.valueOf(userId));
        table.setContainerDataSource(attachmentsListBean);
        table.setVisibleColumns(visibleColumnsobj);
        table.setColumnHeaders(columnHeadersobj);
        configureGeneratedColumn();
        init();
    }

    private void init() {
        if (!this.adjustmentType.isEmpty()) {
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
        vlayout.addComponent(errorMsg, 0);
        newNote.setMaxLength(NumericConstants.TWO_HUNDRED);
        newNote.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        newNote.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\s])*", "Special characters are not allowed"));
        newNote.setImmediate(true);
        newNote.setValidationVisible(true);
    }

    private FieldGroup getNotesBinder() {
        notesBinder.setErrorDisplay(errorMsg);
        notesBinder.bindMemberFields(this);
        notesBinder.setBuffered(true);
        errorMsg.setId("ErrorMessage");
        return notesBinder;
    }

    @Override
    public void intailizingObject(String moduleName) {
        uploadReceiver = new FileUploader(basepath, moduleName);
        uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
        filePath = new CommonUtil().getFileName(basepath + File.separator + "Documents" + File.separator + moduleName);
        wordFile = new CommonUtil().getFileName(filePath + File.separator + fileName + ExportWord.DOC_EXT);
        pdfFile = new CommonUtil().getFileName(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
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
                LOGGER.error("Error in addEnteredNotes :", ex);
                flag.setOk(false);
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
                fileUpload = new CommonUtil().getFileName(fileUploadPath + event.getFilename());
                StringBuilder sb = new StringBuilder(event.getFilename());
                int index = sb.lastIndexOf(ARMUtils.DOT);
                sb.replace(0, index, file);
                Date date = new Date();
                long value = date.getTime();
                sb.insert(sb.lastIndexOf(ARMUtils.DOT), ARMUtils.UNDERSCORE + value);
                NotesDTO attachmentDTO = new NotesDTO();
                String name = file + sb.substring(sb.indexOf(ARMUtils.DOT));
                attachmentDTO.setDocumentName(name);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                TimeZone central = TimeZone.getTimeZone("CST");
                format.setTimeZone(central);
                attachmentDTO.setDateAdded(format.format(new Date()));
                attachmentDTO.setUserId(ARMUtils.getIntegerValue(userId));
                attachmentDTO.setUserName(CommonUtils.getUserNameById(userId));
                attachmentDTO.setDocumentFullPath(fileUploadPath + name);
                attachmentsListBean.addBean(attachmentDTO);
                fileNameField.setValue(StringUtils.EMPTY);
                uploader.setValue(StringUtils.EMPTY);
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in uploadComponentSucceededLogic :", ex);
        }

    }

    @Override
    public void uploadComponentStartedLogic(Upload.StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(SPECIAL_CHAR)) {
                String filename = event.getFilename().toLowerCase(Locale.ENGLISH);
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
            LOGGER.error("Error in uploadComponentStartedLogic :", ex);
        }

    }

    @Override
    public void removeButtonLogic(Button.ClickEvent event) {
        String currentUsersName = tableBean.getUserName();
        if (currentUsersName.contains(",")) {
            String[] str = currentUsersName.split(",");
            currentUsersName = str[1] + ARMUtils.SPACE + str[0];
        }
        if (tableBeanId == null || !table.isSelected(tableBeanId)) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.REMOVE_ATTACHMENT, "Please select an attachment to remove ");
        } else if (!currentUsersName.trim().equalsIgnoreCase(userName.trim())) {
            AbstractNotificationUtils.getInfoNotification(CommonConstant.REMOVE_ATTACHMENT, "You can only remove attachments that you have uploaded.");
        } else {
            AbstractNotificationUtils removeNotification = new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("Inside the removeButtonLogic Listener NO Method");
                }

                @Override
                public void yesMethod() {
                    NotesDTO notesDtoValue = new NotesDTO();
                    notesDtoValue.setDocDetailsId(tableBean.getDocDetailsId());
                    notesDtoValue.setDocumentFullPath(tableBean.getDocumentFullPath());
                    removeDetailsList.add(notesDtoValue);
                    table.removeItem(tableBeanId);
                    tableBeanId = null;
                    tableBean = null;
                }
            };
            removeNotification.getConfirmationMessage(CommonConstant.REMOVE_ATTACHMENT, "Are you sure you want to delete this Attachment?");

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
        if (targetItem == null) {
            return new NotesDTO();
        } else {
            return (NotesDTO) targetItem.getBean();
        }
    }

    @Override
    public void refreshTable() {
        try {
            String masterTableSidValue;
            binder.commit();
            if ("Compliance Deduction Rules".equals(this.moduleName)) {
                masterTableSidValue = masterTableSid;
            } else {
                masterTableSidValue = String.valueOf(binder.getField(masterTableSid).getValue());
            }
            LOGGER.debug("masterTableSid :{}", masterTableSid);
            LOGGER.debug("masterTableSidValue :{}", masterTableSidValue);
        } catch (FieldGroup.CommitException e) {
            LOGGER.error("Error in refreshTable :", e);
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
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = new QueryUtils().fetchFieldsForSecurity(moduleName, tabName);
        } catch (Exception ex) {
            LOGGER.error("Error in getFieldsForSecurity :", ex);
        }
        return resultList;
    }

    public void saveNotesInformation(int projectionIdValue, String moduleName) {
        try {
            CommonUtils.getUserName();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID));
            if (!notesList.isEmpty()) {
                CommonLogic.saveNotes(projectionIdValue, userId, notesList, moduleName, String.valueOf(reasonCode.getValue()));
                notesList.clear();
                CommonLogic.saveUploadedFile(projectionIdValue, getUploadedData(), CommonUtils.getUserNameById(userId), 0, moduleName);
                List<NotesDTO> removedAttachments = removeDetailsList();
                CommonLogic.deleteUploadedFile(removedAttachments);
                removedAttachments.clear();
            }
        } catch (Exception ex) {
            LOGGER.error("Error in saveNotesInformation :", ex);
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
        if (!notesDTO.isEmpty()) {
            internalNotes.setValue(notesDTO.get(0).getNotesHistory());
            attachmentsListBean.addAll(notesDTO);
            reasonCode.select(notesDTO.get(0).getReasonCode());
        }
        internalNotes.setReadOnly(true);
    }

    private void configureGeneratedColumn() {

        table.addGeneratedColumn("documentName", new ExtFilterTable.ColumnGenerator() {

            @Override
            public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                final NotesDTO tableDto = getBeanItem(itemId);
                Button documentNameLink = new Button(tableDto.getDocumentName());
                documentNameLink.setStyleName(Reindeer.BUTTON_LINK);
                documentNameLink.addClickListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final Button.ClickEvent event) {
                        File uploadedFile = new CommonUtil().getFileName(tableDto.getDocumentFullPath());
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
            userMap.put((int) user.getUserId(), user.getFullName());
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return newNote;
    }

    private void writeObject(ObjectOutputStream notesOut) throws IOException {
        notesOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream notesOut) throws IOException, ClassNotFoundException {
        notesOut.defaultReadObject();
    }
}
