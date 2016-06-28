/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.ui;

import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.util.FieldNameUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.FileUploader;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.TableResultCustom;
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
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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

	private String masterTableSid;
	private final String dbModuleName;
	private final ErrorfulFieldGroup binder;
	private final String moduleName;
	final String userId;
	private final String userName;
	private final NotesTabLogic logic = new NotesTabLogic();
	private NotesDTO tableBean = new NotesDTO();

	protected final String mode;
	protected final boolean isAddMode;
	protected final boolean isEditMode;
	protected final boolean isViewMode;
        CommonUIUtils commonUiUtil = new CommonUIUtils();
        CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
        IFPLogic ifpLogic = new IFPLogic();

	public NotesTabForm(ErrorfulFieldGroup binder, String moduleName, String dbModuleName, String masterTableSid,String mode) throws Exception {
		super(binder, moduleName);
		this.masterTableSid = masterTableSid;
		this.dbModuleName = dbModuleName;
		this.binder = binder;
		this.moduleName = moduleName;
		userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		userName = StplSecurity.getUserName().get(Integer.valueOf(userId));
		this.mode = mode;
		this.isAddMode = "Add".equalsIgnoreCase(mode);
		this.isEditMode = "Edit".equalsIgnoreCase(mode);
		this.isViewMode = "View".equalsIgnoreCase(mode);
                final StplSecurity stplSecurity = new StplSecurity();
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
                final Map<String, AppPermission> fieldNotesHM = stplSecurity.getFieldOrColumnPermission(userId, moduleName+","+"Notes",false);

                final Map<String, AppPermission> functionNotesHM = stplSecurity.getBusinessFunctionPermission(userId,  moduleName+","+"Notes");
                getNotesTab(fieldNotesHM,functionNotesHM);
                
                final Map<String, AppPermission> fieldNotesTableHM = stplSecurity.getFieldOrColumnPermission(userId, moduleName+","+"Notes",false);
              List<Object> resultList = ifpLogic.getFieldsForSecurity(moduleName, "Notes");
            Object[] obj = new Object[]{"documentName", "dateAdded", "userName"};
            String[] objHeaders = new String[]{"Document Name", "Date Added", "User Name"};
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldNotesTableHM, mode.equals("Copy")?"Edit":mode);
            table.setContainerDataSource(attachmentsListBean);
            if ("Compliance Deduction Rules".equals(this.moduleName)) {
                table.setVisibleColumns(obj);
                table.setColumnHeaders(objHeaders);
            } else {
                if (tableResultCustom.getObjResult().length > 0) {
                    table.setVisibleColumns(tableResultCustom.getObjResult());
                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                } else {
                    tableLayout.setVisible(false);
                }
            }
            LOGGER.info("userid :" + userId + " Username : " + userName);
                if(isViewMode){
			removeAndDisablingComponents();
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
		if(isViewMode){
			removeAndDisablingComponents();
		}
	}

	

	@Override
	public void addEnteredNotes(ClickEvent event,final AbstractNotificationUtils.Parameter flag) {
		if (!"".equals(String.valueOf(newNote.getValue()).trim()) && !"null".equals(String.valueOf(newNote.getValue()))) {
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
    public void uploadComponentSucceededLogic(SucceededEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(ValidationUtils.SPECIAL_CHAR)) {
           
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
                fileNameField.setValue("");
                uploader.setValue("");
            } else {
                AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
                uploader.setValue("");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void uploadComponentStartedLogic(StartedEvent event) {
        try {
            String file = fileNameField.getValue();
            if (StringUtils.isNotBlank(file) && file.matches(ValidationUtils.SPECIAL_CHAR)) {
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
                    } else if (fileExists(file)) {
                        uploadComponent.interruptUpload();
                        AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
                        uploader.setValue("");
                    } else {
                        uploader.setValue("");
                    }
                }
            } else {
                uploadComponent.interruptUpload();
                AbstractNotificationUtils.getErrorNotification("No File Name", "Please Enter a valid File Name");
                uploader.setValue("");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void removeButtonLogic(ClickEvent event) {
        try {
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
        } catch (Exception e) {
            LOGGER.info("Error------>" + e.getMessage());
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
		try{
                    String masterTableSidValue = StringUtils.EMPTY;
                    binder.commit();
                    if ("Compliance Deduction Rules".equals(this.moduleName)) {
                        masterTableSidValue = masterTableSid;
                    } else {
                        masterTableSidValue = String.valueOf(binder.getField(masterTableSid).getValue());
                    }
                    LOGGER.info("masterTableSid :" + masterTableSid);
                    LOGGER.info("masterTableSidValue :" + masterTableSidValue);
                    int systemId = Integer.valueOf(masterTableSidValue.replace(",", ""));
                    if (systemId != 0) {
                        attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, dbModuleName, fileUploadPath));
                   	}
			}catch(CommitException e){
				LOGGER.error("Error while commiting the binder :"+e);
			}
	}

    private void getNotesTab(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {
        LOGGER.info("Entering getFirstTab1");
        try {

            List<Object> resultList = ifpLogic.getFieldsForSecurity(moduleName, "Notes");

            commonUiUtil.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode, binder);

            commonSecurityLogic.removeComponentOnPermission(resultList, cssHistoryNote, fieldCompanyHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssNewNote, fieldCompanyHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode);
            if (functionHM.get(FieldNameUtils.ADD_NOTE) != null && !((AppPermission) functionHM.get(FieldNameUtils.ADD_NOTE)).isFunctionFlag()) {
                addBtnlayout.removeComponent(getAddNote());
            }

            if (functionHM.get(FieldNameUtils.REMOVE_NOTE) != null && !((AppPermission) functionHM.get(FieldNameUtils.REMOVE_NOTE)).isFunctionFlag()) {
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
}
