/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.stpl.app.global.common.dto.AttachmentDTO;
import com.stpl.app.global.service.GlobalImpl;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
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
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.TableResultCustom;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.Upload.StartedEvent;
import com.vaadin.v7.ui.Upload.SucceededEvent;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class NotesTabForm extends AbstractNotesTab {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(NotesTabForm.class);

	private String masterTableSid;
	private boolean destFileUploadCheck;
	private final String dbModuleName;
	private final ErrorfulFieldGroup binder;
	private final String moduleName;
	private final String userId;
	private final String userName;
	private final NotesTabLogic logic = new NotesTabLogic();
	private NotesDTO tableBean = new NotesDTO();
	GlobalImpl globalImpl= new GlobalImpl();

	protected final String mode;
	protected final boolean isAddMode;
	protected final boolean isEditMode;
	protected final boolean isViewMode;
	private final CommonUIUtils commonUiUtil = new CommonUIUtils();
	private final CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
	AttachmentDTO attachment= new AttachmentDTO();

	public NotesTabForm(ErrorfulFieldGroup binder, String moduleName, String dbModuleName, String masterTableSid,
			String mode) throws SystemException, PortalException {
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
		final String vUserId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
		final Map<String, AppPermission> fieldNotesHM = stplSecurity.getFieldOrColumnPermission(vUserId,
				moduleName + "," + ConstantsUtils.NOTES, false);

		final Map<String, AppPermission> functionNotesHM = stplSecurity.getBusinessFunctionPermission(vUserId,
				moduleName + "," + ConstantsUtils.NOTES);
		getNotesTab(fieldNotesHM, functionNotesHM);

		final Map<String, AppPermission> fieldNotesTableHM = stplSecurity.getFieldOrColumnPermission(vUserId,
				moduleName + "," + ConstantsUtils.NOTES, false);
		List<Object> resultList = commonUiUtil.getFieldsForSecurity(moduleName, ConstantsUtils.NOTES);
		Object[] obj = new Object[] { "documentName", "dateAdded", "userName" };
		String[] objHeaders = new String[] { "Document Name", "Date Added", "User Name" };
		TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj,
				fieldNotesTableHM, mode.equals("Copy") ? "Edit" : mode);
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
		LOGGER.debug("userid : {} Username : {}", vUserId , userName);
		if (isViewMode) {
			removeAndDisablingComponents();
		}
	}

	@Override
	public void intailizingObject(String moduleName) {
		uploadReceiver = (Receiver) new FileUploader(moduleName);
		uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
		filePath = GtnFileUtil.getFile(basepath + File.separator + "Documents" + File.separator + moduleName);
		wordFile = GtnFileUtil.getFile(filePath + File.separator + fileName + ExportWord.DOC_EXT);
		pdfFile = GtnFileUtil.getFile(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
		fileUploadPath = FileUploader.FILE_PATH + moduleName + "/";
		if (isViewMode) {
			removeAndDisablingComponents();
		}
	}

	@Override
	public void addEnteredNotes(ClickEvent event, final AbstractNotificationUtils.Parameter flag) {
		if (!"".equals(String.valueOf(newNote.getValue()).trim())
				&& !"null".equals(String.valueOf(newNote.getValue()))) {
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
					String addedNotes = internalNotes.getValue() + "<" + format.format(new Date()) + "> " + userName
							+ ":" + String.valueOf(newNote.getValue()) + "\n";
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
			if (file.matches(ValidationUtils.SPECIAL_CHAR)) {

				StringBuilder sb = new StringBuilder(event.getFilename());
				int index = sb.lastIndexOf(".");
				sb.replace(0, index, file);
				Date date = new Date();
				long value = date.getTime();
				sb.insert(sb.lastIndexOf("."), "_" + value);
				File destFileUpload = GtnFileUtil.getFile(fileUploadPath + event.getFilename());
				NotesDTO attachmentDTO = new NotesDTO();
				String name = file + sb.substring(sb.indexOf("."));
				File renameFileUpload = GtnFileUtil.getFile(fileUploadPath + name);
				destFileUploadCheck=destFileUpload.renameTo(renameFileUpload);
				LOGGER.info("File is renamed successfully : {}",destFileUploadCheck);
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
				attachmentDTO.setUserName(userName);
				attachmentDTO.setDocumentFullPath(fileUploadPath + name);
				attachmentsListBean.addBean(attachmentDTO);
				fileNameField.setValue(StringUtils.EMPTY);
				uploader.setValue(StringUtils.EMPTY);
				CommonUIUtils.successNotification(
						attachmentDTO.getDocumentName().substring(0, attachmentDTO.getDocumentName().lastIndexOf('.'))
								+ " uploaded successfully");
			} else {
				AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
				uploader.setValue(StringUtils.EMPTY);
				fileNameField.setValue(StringUtils.EMPTY);
			}
		} catch (Property.ReadOnlyException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
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
					AbstractNotificationUtils.getErrorNotification("Add Attachment",
							"Please select an attachment to add");
				} else {
					if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx")
							|| filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx")
							|| filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv")
							|| filename.endsWith(".jpg") || filename.endsWith(".jpeg")
							|| filename.endsWith(".pptx")))) {
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
		} catch (Property.ReadOnlyException ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	@Override
	public void removeButtonLogic(ClickEvent event) {
		try {
			if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
				AbstractNotificationUtils.getErrorNotification(ConstantsUtils.REMOVE_ATTACHEMENT,
						"Please select an attachment to remove ");
			} else {
				if (!tableBean.getUserName().equalsIgnoreCase(userName)) {
					AbstractNotificationUtils.getInfoNotification(ConstantsUtils.REMOVE_ATTACHEMENT,
							"You can only remove attachments that you have uploaded.");
				} else {
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
					notification.getConfirmationMessage(ConstantsUtils.REMOVE_ATTACHEMENT,
							"Are you sure you want to delete this Attachment?");

				}
			}
		} catch (Exception e) {
			LOGGER.error("Error------> {}" , e);
		}
	}

	@Override
	public void itemClickLogic(ItemClickEvent event) {
		tableBeanId = event.getItemId();
		BeanItem<?> targetItem = null;
		if (tableBeanId instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) tableBeanId;
		} else if (tableBeanId instanceof NotesDTO) {
			targetItem = new BeanItem<>((NotesDTO) tableBeanId);
		}
                if (targetItem != null) {
                tableBean = (NotesDTO) targetItem.getBean();
                if (event.isDoubleClick()) {
				try {
				    byte[] attachmentFile=globalImpl.fetchData(tableBean.getDocDetailsId());
					FileOutputStream fileOuputStream=null ;
					fileOuputStream = GtnFileUtil.getFileOutputStream(tableBean.getDocumentFullPath());
					fileOuputStream.write(attachmentFile);
					fileOuputStream.close();
					File uploadedFile = GtnFileUtil.getFile(tableBean.getDocumentFullPath());
					Resource res = new FileResource(uploadedFile);
					fileDownloader.setFileDownloadResource(res);
					downloadFile(uploadedFile);
        			} catch (Exception e) {
        				LOGGER.error("Error in file is not Found",e);
        			}
                }
            }

	}

	@Override
	public void refreshTable() {
		try {
			String masterTableSidValue = StringUtils.EMPTY;
			binder.commit();
			if ("Compliance Deduction Rules".equals(this.moduleName) ||  "Deduction Calendar".equals(this.moduleName)) {
				masterTableSidValue = masterTableSid;
			} else if (binder.getField(masterTableSid) != null) {
				masterTableSidValue = String.valueOf(binder.getField(masterTableSid).getValue());
			}
			LOGGER.debug("masterTableSid : {}" , masterTableSid);
			LOGGER.debug("masterTableSidValue : {}" , masterTableSidValue);

			int systemId = masterTableSidValue.equals(StringUtils.EMPTY) ? 0
					: Integer.parseInt(masterTableSidValue.replace(',', ' '));
			if (systemId != 0) {
				attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, dbModuleName, fileUploadPath));
			}
		} catch (CommitException e) {
			LOGGER.error("Error while commiting the binder : {}" , e);
		}
	}
        
       	private void getNotesTab(final Map<String, AppPermission> fieldCompanyHM,
			final Map<String, AppPermission> functionHM) {
		LOGGER.debug("Entering getFirstTab1");
		try {

			List<Object> resultList = commonUiUtil.getFieldsForSecurity(moduleName, ConstantsUtils.NOTES);

			commonUiUtil.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode, binder);

			commonSecurityLogic.removeComponentOnPermission(resultList, cssHistoryNote, fieldCompanyHM, mode);
			commonSecurityLogic.removeComponentOnPermission(resultList, cssNewNote, fieldCompanyHM, mode);
			commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode);
			if (functionHM.get(CommonUIUtils.ADD_NOTE) != null
					&& !((AppPermission) functionHM.get(CommonUIUtils.ADD_NOTE)).isFunctionFlag()) {
				addBtnlayout.removeComponent(getAddNote());
			}

			if (functionHM.get(CommonUIUtils.REMOVE_NOTE) != null
					&& !((AppPermission) functionHM.get(CommonUIUtils.REMOVE_NOTE)).isFunctionFlag()) {
				tableLayout.removeComponent(getRemove());
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("Ending getFirstTab1");

	}

	public String getMasterTableSid() {
		return masterTableSid;
	}

	public void setMasterTableSid(String masterTableSid) {
		this.masterTableSid = masterTableSid;
	}
}
