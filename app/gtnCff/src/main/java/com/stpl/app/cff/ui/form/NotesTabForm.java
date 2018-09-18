/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.cff.dto.AttachmentDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.AdditionalInfoLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.FileUploader;
import com.stpl.app.cff.util.NotesTabLogic;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.AbstractNotesTab;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.ifs.util.GtnFileUtil;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.Upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author porchelvi.g
 */
public class NotesTabForm extends AbstractNotesTab {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(NotesTabForm.class);
	private String masterTableSid;
	private final CustomFieldGroup binder;
	private final String moduleName;
	private final String userId;
	private final String userName;
	private final NotesTabLogic logic = new NotesTabLogic();
	private NotesDTO tableBean = new NotesDTO();
	/**
	 * The common logic.
	 */
	private final AdditionalInfoLogic addInfoLogic = new AdditionalInfoLogic();
	private final  List<NotesDTO> removeDetailsList = new ArrayList<>();
	private final Double fileSize = 0.00;
	protected final String mode = "";
	private CffApprovalDetailsForm approvalWindow;
	private final Button close = new Button("Close");
	private static final String MODULE_NAME = "Consolidated Financial Forecast";
        private static final Object[] NOTES_COLUMN = new Object[] { "documentName", "dateAdded", "userName" };
        private static final String[] NOTES_HEADERS = new String[] { "Document Name", "Date Added", "User Name" };
	
	

	public NotesTabForm(SessionDTO sessionDTO, CustomFieldGroup binder, String moduleName,
			CffApprovalDetailsForm approvalWindow) throws SystemException {
		super(binder, moduleName);
		this.binder = binder;
		this.moduleName = moduleName;
		this.approvalWindow = approvalWindow;
		userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		userName = StplSecurity.getUserMap().get(Integer.valueOf(userId));

		final String vUserId = String.valueOf(
				VaadinSession.getCurrent().getAttribute(com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils.USER_ID));
		
		table.setContainerDataSource(attachmentsListBean);
		table.setVisibleColumns(NOTES_COLUMN);
		table.setColumnHeaders(NOTES_HEADERS);
		vlayout.setSpacing(true);
		vlayout.setMargin(true);
		configureFields();
		setValues(false, sessionDTO);

		LOGGER.debug("userid= {} and Username= {}", vUserId, 
                        userName);
	}

	@Override
	public void intailizingObject(String moduleName) {
		uploadReceiver = (Upload.Receiver) new FileUploader(basepath, MODULE_NAME);
		uploadComponent = new Upload(null, (FileUploader) uploadReceiver);
		filePath = GtnFileUtil.getFile(basepath + File.separator + "Attachments" + File.separator + MODULE_NAME);
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
							+ ":" + (newNote.getValue()) + "\n";
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
				attachmentDTO.setUserId(Integer.parseInt(userId));
				attachmentDTO.setUserName(userName);
				attachmentDTO.setDocumentFullPath(fileUploadPath + sb.toString());
				attachmentsListBean.addBean(attachmentDTO);
				fileNameField.setValue("");
				uploader.setValue("");
			} else {
				AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
				uploader.setValue("");
			}
		} catch (Property.ReadOnlyException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
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
		} catch (Property.ReadOnlyException ex) {
			LOGGER.error(ex.getMessage());
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
		AttachmentDTO attachmentSaveDTO;
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
            try {
            	attachmentSaveDTO=fetchData(tableBean.getDocDetailsId());
                FileOutputStream fileOuputStream = null;
                fileOuputStream = GtnFileUtil.getFileOutputStream(tableBean.getDocumentFullPath());
                fileOuputStream.write(attachmentSaveDTO.getFileData());
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
			LOGGER.debug("masterTableSid= {}", masterTableSid);
			LOGGER.debug("masterTableSidValue= {}", masterTableSidValue);
			int systemId = Integer.parseInt(masterTableSidValue.replace(",", ""));
			if (systemId != 0) {
				attachmentsListBean.addAll(logic.getAttachmentDTOList(systemId, "ACC_CLOSURE_MASTER", fileUploadPath));
			}
		} catch (FieldGroup.CommitException e) {
			LOGGER.error("Error while commiting the binder :{}",e);
		}
	}

	public String getMasterTableSid() {
		return masterTableSid;
	}

	public void setMasterTableSid(String masterTableSid) {
		this.masterTableSid = masterTableSid;
	}

	public final void configureFields() {

		close.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                                        @Override
					public void noMethod() {
						// To change body of generated methods, choose Tools
						// | Templates.
					}

                                        @Override
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
			resultList = CommonServiceImpl.getInstance().fetchFieldsForSecurity(moduleName, tabName);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
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
			throws PortalException {
		LOGGER.debug("Entering saveAdditionalInformation");
		int projectionId = cffmastersystemid;
		String vUserId = userid;
		String addedNotes = getAddedNotes();
		addInfoLogic.saveNotes(projectionId, userid, addedNotes, MODULE_NAME);
		List<NotesDTO> addedAttachments = getUploadedData();
		for (NotesDTO attached : addedAttachments) {
			addInfoLogic.saveUploadedFile(projectionId, attached.getDocumentName(), vUserId, fileSize, MODULE_NAME);
			addInfoLogic.saveAttachFile( addedAttachments, MODULE_NAME, projectionId);
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
		return Collections.unmodifiableList(removeDetailsList);
	}

	public final void setValues(boolean saveFlag, SessionDTO sessionDTO) throws SystemException {
		LOGGER.debug("Inside of AdditionalInformation setValues Method");
		String vMode = sessionDTO.getAction();
		if ("edit".equalsIgnoreCase(vMode) || "view".equalsIgnoreCase(vMode) || saveFlag) {
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
			if ("view".equalsIgnoreCase(vMode) || "edit".equalsIgnoreCase(vMode)) {
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
	public AttachmentDTO fetchData(int documentSid) {
		   
		   AttachmentDTO attachmentBean=new AttachmentDTO();
		    String query = SQlUtil.getQuery("selectAttachQuery");
			query = query.replace("?attachmentSid", "'" + documentSid + "'");
			List<AttachmentDTO> fileData=HelperTableLocalServiceUtil.executeSelectQuery(query);
			  ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(bos);
					oos.writeObject(fileData);
				} catch (IOException e) {
					LOGGER.error("Error in file is not Found",e);
				}
			    byte[] bytes = bos.toByteArray();
			attachmentBean.setFileData(bytes);
			return attachmentBean;
		   }
}
