/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.AdditionalInfoLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.FileUploader;
import com.stpl.app.cff.util.NotesTabLogic;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Upload;

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
	private final String userId;
	private final String userName;
	private final NotesTabLogic logic = new NotesTabLogic();
	private NotesDTO tableBean = new NotesDTO();
	private List<String> notesList = new ArrayList<>();
	/**
	 * The common logic.
	 */
	private AdditionalInfoLogic addInfoLogic = new AdditionalInfoLogic();
	private  List<NotesDTO> removeDetailsList = new ArrayList<>();
	private Double fileSize = 0.00;
	protected final String mode = "";
	private CffApprovalDetailsForm approvalWindow;
	private Button close = new Button("Close");
	private static String MODULE_NAME = "Consolidated Financial Forecast";

	public NotesTabForm(SessionDTO sessionDTO, CustomFieldGroup binder, String moduleName,
			CffApprovalDetailsForm approvalWindow) throws SystemException {
		super(binder, moduleName);
		this.binder = binder;
		this.moduleName = moduleName;
		this.approvalWindow = approvalWindow;
		userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		userName = StplSecurity.userMap.get(Integer.valueOf(userId));

		final String userId = String.valueOf(
				VaadinSession.getCurrent().getAttribute(com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils.USER_ID));
		Object[] obj = new Object[] { "documentName", "dateAdded", "userName" };
		String[] objHeaders = new String[] { "Document Name", "Date Added", "User Name" };
		table.setContainerDataSource(attachmentsListBean);
		table.setVisibleColumns(obj);
		table.setColumnHeaders(objHeaders);
		vlayout.setSpacing(true);
		vlayout.setMargin(true);
		configureFields();
		setValues(false, sessionDTO);

		LOGGER.debug("userid :" + userId + " Username : " + userName);
	}

	@Override
	public void intailizingObject() {
		uploadReceiver = (Upload.Receiver) new FileUploader(basepath, MODULE_NAME);
		uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
		filePath = GtnFileUtil.getFile(basepath + File.separator + "Documents" + File.separator + MODULE_NAME);
		wordFile = GtnFileUtil.getFile(filePath + File.separator + fileName + ExportWord.DOC_EXT);
		pdfFile = GtnFileUtil.getFile(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
		fileUploadPath = FileUploader.FILE_PATH + MODULE_NAME + "/";
	}

	@Override
	public void addEnteredNotes(Button.ClickEvent event, final AbstractNotificationUtils.Parameter flag) {
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
	public void uploadComponentSucceededLogic(Upload.SucceededEvent event) {
		try {
			String file = fileNameField.getValue();
			if (StringUtils.isNotBlank(file) && file.matches(CommonUtils.SPECIAL_CHAR)) {
				CommonUIUtils.successNotification(file + " uploaded successfully");
				fileUpload = GtnFileUtil.getFile(fileUploadPath + event.getFilename());
				StringBuilder sb = new StringBuilder(event.getFilename());
				int index = sb.lastIndexOf(".");
				sb.replace(0, index, file);
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
	public void uploadComponentStartedLogic(Upload.StartedEvent event) {
		try {
			String file = fileNameField.getValue();
			if (StringUtils.isNotBlank(file) && file.matches(CommonUtils.SPECIAL_CHAR)) {
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
		} catch (Exception ex) {
			LOGGER.error(ex);
		}

	}

	@Override
	public void removeButtonLogic(Button.ClickEvent event) {
		if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
			AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.REMOVE_ATTACHMENT,
					"Please select an attachment to remove ");
		} else {
			if (!tableBean.getUserName().equalsIgnoreCase(userName)) {
				AbstractNotificationUtils.getInfoNotification(StringConstantsUtil.REMOVE_ATTACHMENT,
						"You can only remove attachments that you have uploaded.");
			} else {
				AbstractNotificationUtils notification = new AbstractNotificationUtils() {
					@Override
					public void noMethod() {
						// To change body of generated methods, choose Tools
						// | Templates.
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
				notification.getConfirmationMessage(StringConstantsUtil.REMOVE_ATTACHMENT,
						"Are you sure you want to delete this Attachment?");

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
			targetItem = new BeanItem<>((NotesDTO) tableBeanId);
		}
		tableBean = (NotesDTO) targetItem.getBean();
		if (event.isDoubleClick()) {
			File uploadedFile = GtnFileUtil.getFile(tableBean.getDocumentFullPath());
			Resource res = new FileResource(uploadedFile);
			fileDownloader.setFileDownloadResource(res);
			downloadFile(uploadedFile);
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
			LOGGER.debug("masterTableSid :" + masterTableSid);
			LOGGER.debug("masterTableSidValue :" + masterTableSidValue);
			int systemId = Integer.valueOf(masterTableSidValue.replace(",", ""));
			if (systemId != 0) {
				attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, "ACC_CLOSURE_MASTER", fileUploadPath));
			}
		} catch (FieldGroup.CommitException e) {
			LOGGER.error("Error while commiting the binder :" + e);
		}
	}

	public String getMasterTableSid() {
		return masterTableSid;
	}

	public void setMasterTableSid(String masterTableSid) {
		this.masterTableSid = masterTableSid;
	}

	public void configureFields() {

		close.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				AbstractNotificationUtils notification = new AbstractNotificationUtils() {
					public void noMethod() {
						// To change body of generated methods, choose Tools
						// | Templates.
					}

					public void yesMethod() {

						approvalWindow.close();
					}
				};
				notification.getConfirmationMessage("Close Confirmation",
						"Are you sure you want to close the Consolidated Financial Forecast Approval window");

			}
		});
	}

	public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
		List<Object> resultList = new ArrayList<>();
		try {
			resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return resultList;
	}

	public CffApprovalDetailsForm getApprovalWindow() {
		return approvalWindow;
	}

	public void setApprovalWindow(CffApprovalDetailsForm approvalWindow) {
		this.approvalWindow = approvalWindow;
	}

	public void saveAdditionalInformation(int cffmastersystemid, String userid, SessionDTO sessionDTO)
			throws SystemException, PortalException {
		LOGGER.debug("Entering saveAdditionalInformation");
		int projectionId = cffmastersystemid;
		String userId = userid;
		String addedNotes = getAddedNotes();
		addInfoLogic.saveNotes(projectionId, userid, addedNotes, MODULE_NAME);
		notesList.clear();
		List<NotesDTO> addedAttachments = getUploadedData();
		for (NotesDTO attached : addedAttachments) {
			addInfoLogic.saveUploadedFile(projectionId, attached.getDocumentName(), userId, fileSize, MODULE_NAME);
		}
		attachmentsListBean.removeAllItems();
		List<NotesDTO> removedAttachments = getRemoveDocDetailsItem();
		for (NotesDTO removed : removedAttachments) {
			if (removed.getDocDetailsId() != 0) {
				addInfoLogic.deleteUploadedFile(removed.getDocDetailsId());
			}
		}
		removeDetailsList.clear();
		// reload the table and text area
		setValues(true, sessionDTO);
		LOGGER.debug("Exists saveAdditionalInformation");
	}

	public List<NotesDTO> getRemoveDocDetailsItem() {
		return removeDetailsList;
	}

	public void setValues(boolean saveFlag, SessionDTO sessionDTO) throws SystemException {
		LOGGER.debug("Inside of AdditionalInformation setValues Method");
		String mode = sessionDTO.getAction();
		if ("edit".equalsIgnoreCase(mode) || "view".equalsIgnoreCase(mode) || saveFlag) {
			LOGGER.debug("Inside of EDIT setValues Method");
			attachmentsListBean.removeAllItems();
			final int projectionId = sessionDTO.getProjectionId();
			final List<NotesDTO> allFiles = addInfoLogic.getAttachmentDTOList(projectionId, MODULE_NAME);

			attachmentsListBean.addAll(addInfoLogic.addUserFile(allFiles));
			String notes = addInfoLogic.getNotes(projectionId, MODULE_NAME);
			internalNotes.setReadOnly(false);
			internalNotes.setValue(notes);
			internalNotes.setReadOnly(true);
			newNote.setValue(StringUtils.EMPTY);
			if ("view".equalsIgnoreCase(mode) || "edit".equalsIgnoreCase(mode)) {
				fileNameField.setReadOnly(true);
				uploadComponent.setReadOnly(true);
				remove.setEnabled(false);
				addNote.setEnabled(false);
				newNote.setReadOnly(true);
				wordPngImage.setEnabled(false);
				pdfPngImage.setEnabled(false);
			}
		}
		LOGGER.debug("Ends of AdditionalInformation setValues Method");
	}
}
