/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsAttachmentRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.VerticalLayout;
import elemental.json.JsonArray;

public class GtnUIFrameworkNotesTab extends CustomComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkNotesTab.class);

	private static final long serialVersionUID = 1L;

	protected Label fileNameLb;
	protected VerticalLayout addBtnlayout;
	protected VerticalLayout tableLayout;
	protected TextField fileNameField;
	private CssLayout cssLayout1;
	protected TextArea newNote;
	protected Button addNote;
	protected TextArea internalNotes;
	private HorizontalLayout imageLayout;
	protected ExtFilterTable table;
	protected Button remove;
	protected HorizontalLayout root;
	protected ComboBox reasonCodeddlb;
	protected Label reasonCodeLabel;
	protected Receiver uploadReceiver;
	protected Upload uploadComponent;
	protected final TextField uploader = new TextField();
	private Resource wordRes;
	private Resource pdfRes;
	protected FileDownloader wordDownloader;
	protected FileDownloader pdfDownloader;       

	protected String basepath = System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH);			
        
	protected Image wordPngImage = new Image(null, new ThemeResource("img/word.png"));
	protected Image pdfPngImage = new Image(null, new ThemeResource("img/pdf.png"));
	protected final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<>(NotesDTO.class);
	protected Object tableBeanId = null;
	protected File fileUpload;
	protected final FileDownloader fileDownloader = new FileDownloader(
			new FileResource(GtnFileNameUtils.getFile("tst")));
	protected String fileName;
	private String notesHistory = "Notes History";
	protected File filePath;
	protected File wordFile;
	protected File pdfFile;
	protected String fileUploadPath;
	private List<NotesDTO> removeDetailsList = new ArrayList<>();

	private final String moduleName;
	private final String userName;
	private NotesDTO tableBean = new NotesDTO();

	protected final String mode;
	protected final boolean isAddMode;
	protected final boolean isEditMode;
	protected final boolean isViewMode;

	private List<String> validFormats = Arrays.asList("doc", "docx", "ppt", "xls", "xlsx", "pdf", "txt", "csv", "jpg",
			"jpeg", "pptx");

	/**
	 * constructor for additional information
	 *
	 */
	public GtnUIFrameworkNotesTab(final GtnUIFrameworkNotesTabConfig notesTabConfig) {

		this.fileName = "Notes_History_" + notesTabConfig.getModuleName().replace(" ", "");

		this.moduleName = notesTabConfig.getModuleName();
		VaadinSession.getCurrent().setAttribute("USER_ID", "1");
		userName = notesTabConfig.getUserName();
		this.mode = notesTabConfig.getMode();
		this.isAddMode = "Add".equalsIgnoreCase(mode);
		this.isEditMode = "Edit".equalsIgnoreCase(mode);
		this.isViewMode = "View".equalsIgnoreCase(mode);
		setCompositionRoot(getRootComponent());
		intailizingObject();
		init();
		configNotesTabForm();
	}

	private VerticalLayout getRootComponent() {

		gtnLogger.info("Entering into the getRootComponent() of GtnUIFrameworkNotesTab");
		VerticalLayout vlayout = new VerticalLayout();
		vlayout.setSpacing(true);
		Panel panelnotes = new Panel();
		vlayout.addComponent(panelnotes);

		root = new HorizontalLayout();
		root.setMargin(true);
		root.setSpacing(true);
		panelnotes.setContent(root);

		Panel notes = new Panel();
		notes.setCaption("Notes");
		notes.addStyleName("attachmentpanel");

		root.addComponent(notes);

		VerticalLayout notesPanelInnerLayout = new VerticalLayout();
		notesPanelInnerLayout.setMargin(true);
		notes.setContent(notesPanelInnerLayout);

		VerticalLayout notesPanelSubLayout1 = new VerticalLayout();
		VerticalLayout notesPanelSubLayout2 = new VerticalLayout();

		CssLayout cssNewNote = new CssLayout();
		cssNewNote.setWidth("100%");
		cssNewNote.setHeight("100%");
		notesPanelSubLayout1.addComponent(cssNewNote);

		VerticalLayout reasoncodeLayout = new VerticalLayout();
		cssNewNote.addComponent(reasoncodeLayout);

		VerticalLayout notesLayout = new VerticalLayout();
		notesLayout.setSpacing(true);

		HorizontalLayout addBtnInnerHozLayout = new HorizontalLayout();
		addBtnInnerHozLayout.setSpacing(true);

		Label newNoteLabel = new Label("Enter New Note");
		newNoteLabel.addStyleName("noteslabel");

		newNote = new TextArea();
		newNote.addStyleName("addInfoText");

		addBtnlayout = new VerticalLayout();
		addBtnlayout.setSpacing(true);

		HorizontalLayout addBtnInnerlayout = new HorizontalLayout();
		addBtnInnerlayout.setSpacing(true);
		addNote = new Button("ADD NOTE");

		reasonCodeLabel = new Label("Reason Code ");
		reasonCodeLabel.setContentMode(ContentMode.HTML);
		reasonCodeLabel.setImmediate(true);
		reasonCodeLabel.setVisible(false);

		reasonCodeddlb = new ComboBox();
		reasonCodeddlb.setImmediate(true);
		reasonCodeddlb.setVisible(false);

		addBtnInnerlayout.addComponent(addNote);
		addBtnInnerlayout.addComponent(reasonCodeLabel);
		addBtnInnerlayout.addComponent(reasonCodeddlb);

		notesLayout.addComponent(addBtnInnerHozLayout);
		addBtnlayout.addComponent(addBtnInnerlayout);

		reasoncodeLayout.addComponent(newNoteLabel);
		reasoncodeLayout.addComponent(newNote);
		reasoncodeLayout.addComponent(notesLayout);
		reasoncodeLayout.addComponent(addBtnlayout);

		notesPanelInnerLayout.addComponent(notesPanelSubLayout1);

		CssLayout cssHistoryNote = new CssLayout();
		cssHistoryNote.setWidth("100%");
		cssHistoryNote.setHeight("100%");
		notesPanelSubLayout2.addComponent(cssHistoryNote);

		VerticalLayout cssHistoryInnerNoteLayout = new VerticalLayout();
		cssHistoryNote.addComponent(cssHistoryInnerNoteLayout);

		Label notesHistoryLabel = new Label("Notes History");
		notesHistoryLabel.addStyleName("noteshistory");

		cssHistoryInnerNoteLayout.addComponent(notesHistoryLabel);

		internalNotes = new TextArea();
		internalNotes.addStyleName("addInfoText");
		cssHistoryInnerNoteLayout.addComponent(internalNotes);

		imageLayout = new HorizontalLayout();

		cssHistoryInnerNoteLayout.addComponent(imageLayout);
		notesPanelInnerLayout.addComponent(notesPanelSubLayout2);

		Panel attachment = new Panel();
		attachment.setCaption("Attachments");
		attachment.addStyleName("attachmentpanel");

		VerticalLayout attachmentInnerLayout = new VerticalLayout();
		attachmentInnerLayout.setMargin(true);
		attachmentInnerLayout.setSpacing(true);
		attachment.setContent(attachmentInnerLayout);

		HorizontalLayout attachmentSubLayout1 = new HorizontalLayout();
		attachmentSubLayout1.setSpacing(true);

		fileNameLb = new Label("File Name");
		fileNameLb.setContentMode(ContentMode.HTML);
		fileNameLb.setImmediate(true);

		fileNameField = new TextField();
		fileNameField.setEnabled(true);
		fileNameField.setImmediate(true);

		attachmentSubLayout1.addComponent(fileNameLb);
		attachmentSubLayout1.addComponent(fileNameField);

		HorizontalLayout attachmentSubLayout2 = new HorizontalLayout();
		attachmentSubLayout2.setWidth("100%");

		cssLayout1 = new CssLayout();
		cssLayout1.setWidth("100%");
		cssLayout1.setHeight("100%");
		attachmentSubLayout2.addComponent(cssLayout1);

		HorizontalLayout attachmentSubLayout3 = new HorizontalLayout();

		CssLayout cssLayout2 = new CssLayout();
		cssLayout2.setWidth("100%");
		cssLayout2.setHeight("100%");

		Label tableAttachments = new Label("Attachments");
		tableAttachments.setContentMode(ContentMode.HTML);
		tableAttachments.setImmediate(true);
		tableAttachments.addStyleName("attachment");

		tableLayout = new VerticalLayout();
		table = new ExtFilterTable();
		remove = new Button("REMOVE");
		table.setId("NotesTsaab");
		table.setStyleName(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		tableLayout.addComponent(table);
		tableLayout.addComponent(remove);

		tableLayout.setSpacing(true);

		cssLayout2.addComponent(tableAttachments);
		cssLayout2.addComponent(tableLayout);

		attachmentSubLayout3.addComponent(cssLayout2);

		attachmentInnerLayout.addComponent(attachmentSubLayout1);
		attachmentInnerLayout.addComponent(attachmentSubLayout2);
		attachmentInnerLayout.addComponent(attachmentSubLayout3);

		root.addComponent(attachment);
		gtnLogger.info("End into the getRootComponent() of GtnUIFrameworkNotesTab");

		return vlayout;
	}

	public void configNotesTabForm() {

		Object[] obj = new Object[] { "documentName", "dateAdded", "userName" };
		String[] objHeaders = new String[] { "Document Name", "Date Added", "User Name" };
		table.setContainerDataSource(attachmentsListBean);
		table.setVisibleColumns(obj);
		table.setColumnHeaders(objHeaders);

		removeAndDisablingComponents(isViewMode);
		
		documentExporter();
	}

	private void init() {
		addToContent();
		createExportDocs();
		configureFields();
	}

	public void getContent() {
		addToContent();
	}

	private void addToContent() {
		addNotes();
		addAttachments();
	}

	private void addNotes() {
		imageLayout.addComponent(wordPngImage);
		imageLayout.addComponent(pdfPngImage);
	}

	private void addAttachments() {
		Label addAttachmentLable = new Label("Attachment");
		addAttachmentLable.addStyleName("attachment");
		fileNameField.setImmediate(true);
		fileNameField.setMaxLength(250);
		fileNameField
				.addValidator(new StringLengthValidator(" File Name Should be less than 250 characters", 0, 250, true));

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

		addTable();
	}

	private void addTable() {
		table.markAsDirty();
		table.addStyleName("filterbar");
		table.setFilterBarVisible(true);
		table.setFilterDecorator(new ExtDemoFilterDecorator());
		table.setPageLength(3);
		table.setContainerDataSource(attachmentsListBean);
		table.setSelectable(true);
		table.setVisibleColumns("documentName", "dateAdded", "userName");
		table.setColumnHeaders("Document Name", "Date Added", "User Name");
	}

	/**
	 * to configure all the fields
	 */
	private void configureFields() {
		fileNameLb.addStyleName("filenamelable");
		uploader.setStyleName("searchText");
		uploader.setImmediate(true);
		uploader.setEnabled(false);
		uploadComponent.setStyleName("uploadIdBB");
		internalNotes.setReadOnly(true);
		enableDisableExportIcons();
		wordDownloader.extend(wordPngImage);
		pdfDownloader.extend(pdfPngImage);
		internalNotes.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				if (!"".equals(String.valueOf(event.getProperty().getValue()))) {
					wordPngImage.setEnabled(true);
					pdfPngImage.setEnabled(true);
					wordPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_ENABLED);
					pdfPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_ENABLED);
				} else {
					wordPngImage.setEnabled(false);
					pdfPngImage.setEnabled(false);
					wordPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_DISABLED);
					pdfPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_DISABLED);
				}
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
					setFileNameToField(arguments);
				} catch (Exception ex) {
					gtnLogger.error("Exception in handling through JSON", ex);
				}
				uploader.focus();
                    }

                 

                 
		});

		uploader.addFocusListener(new FocusListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Will execute,when we click an uploader.
			 */
			@Override
			public void focus(final FocusEvent event) {
				uploadComponent.focus();
			}
		});

		newNote.setSizeFull();
		newNote.setRows(5);
		newNote.setImmediate(true);
		newNote.setMaxLength(1000);
		newNote.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, 200, true));
		internalNotes.setSizeFull();
		internalNotes.setRows(5);
		internalNotes.markAsDirty();
		internalNotes.setImmediate(true);
		internalNotes.setEnabled(false);
		uploadComponent.setButtonCaption("ADD");
		addNote.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {

				addEnteredNotes();

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

		newNote.setInputPrompt("- Enter New Note -");

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
					gtnLogger.error("Exeception in Item Click of notes tab", e);
				}
			}
		});

	}

	@Override
	public void setEnabled(boolean enabled) {
		newNote.setEnabled(enabled);
		addNote.setEnabled(enabled);
		internalNotes.setEnabled(enabled);
		remove.setEnabled(enabled);
		uploadComponent.setEnabled(enabled);
		fileNameField.setEnabled(enabled);
	}

	/**
	 * JavaScript for search icon in text field
	 */
	public void callJavaScript() {
		uploader.setId("textId");
		uploadComponent.setId("layoutId");
		Page.getCurrent().getJavaScript()
				.execute("var f=document.getElementById('layoutId').firstChild;" + "var f2=f.children[1]; "
						+ "f2.addEventListener('change', function(){" + "var f3=f2.value;" + "callJava(f3)"
						+ "}, false);");

	}

	public void setUploderValue() {

		uploader.setValue("");
	}

	public void setFileNameField(String value) {

		fileNameField.setValue(value);
	}

	/**
	 * Creating the directory for document
	 *
	 */
	private void createExportDocs() {
		if (!filePath.isDirectory()) {
			filePath.mkdirs();
		}
		if (!wordFile.exists()) {
			try {
				if (!wordFile.createNewFile()) {
					throw new FileNotFoundException("File Could not be created " + wordFile.getName());
				}
			} catch (Exception ex) {
				gtnLogger.error("Exeception in Word file export", ex);
			}
		}
		if (!pdfFile.exists()) {
			try {
				if (!pdfFile.createNewFile()) {
					throw new FileNotFoundException("File Could not be created " + pdfFile.getName());
				}

			} catch (Exception ex) {
				gtnLogger.error("Exeception in PDF export", ex);
			}
		}

		wordRes = new FileResource(wordFile);
		pdfRes = new FileResource(pdfFile);
		wordDownloader = new FileDownloader(wordRes);
		pdfDownloader = new FileDownloader(pdfRes);
	}

	/**
	 * To create pdf and word documents
	 *
	 */
	public void documentExporter() {
		try {
			ExportWord exportWord = new ExportWord(filePath, wordFile);
			exportWord.export(internalNotes.getValue());
			Resource wordResOnEdit = new FileResource(
					GtnFileNameUtils.getFile(filePath + File.separator + fileName + ExportWord.DOC_EXT));
			wordDownloader.setFileDownloadResource(wordResOnEdit);

			ExportPdf exportPdf = new ExportPdf(notesHistory, filePath, pdfFile);
			exportPdf.export(internalNotes.getValue());
			Resource pdfResOnEdit = new FileResource(
					GtnFileNameUtils.getFile(filePath + File.separator + fileName + ExportPdf.PDF_EXT));
			pdfDownloader.setFileDownloadResource(pdfResOnEdit);

		} catch (Exception ex) {
			gtnLogger.error("Error in Document Export", ex);
		}

	}

	/**
	 * To upload a file
	 *
	 * @param uploadedFile
	 */
	@SuppressWarnings("deprecation")
	public void downloadFile(File uploadedFile) {
		try {
			if (uploadedFile.exists()) {
				Resource res = new FileResource(uploadedFile);
				Page.getCurrent().open(res, "_blank", true);

			}
		} catch (Exception ex) {
			gtnLogger.error("Error in download file", ex);
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

	public String getAddedNotes() {
		return internalNotes.getValue();
	}

	public TextArea getInternalNotes() {
		return internalNotes;
	}

	public static Object[] getCollapsibleColumnsDefault(ExtFilterTable table, int length) {
		table.setColumnCollapsingAllowed(true);
		table.setImmediate(true);
		Object[] visibleColumns = table.getVisibleColumns();
		Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
		List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
		for (int i = 0, j = length; i < j; i++) {
			list.remove(propertyIds[i]);
		}
		propertyIds = list.toArray(new Object[list.size()]);

		for (Object propertyId : table.getVisibleColumns()) {
			table.setColumnCollapsed(propertyId, false);
		}

		for (Object propertyId : propertyIds) {
			table.setColumnCollapsed(propertyId, true);
		}

		return propertyIds;
	}

	/**
	 * Call java script for button.
	 */
	public void callJavaScriptForButton() {

		uploader.setValue("");
		uploader.setId("textId");
		uploadComponent.setId("fileUpload");
		Page.getCurrent().getJavaScript()
				.execute("var clk=document.getElementById('fileUpload').firstChild.children[2];"
						+ "clk.addEventListener('click', function(){"
						+ "var val1=document.getElementById('textId').value;"
						+ "if(val1==null||val1.length<=1||val1==''){buttonClicked();}" + "}, false);");

	}

	public List<NotesDTO> removeDetailsList() {
		return new ArrayList<>(removeDetailsList);
	}

	public void focusUploaderField() {
		uploader.focus();
	}

	public void setNotesHistoryValue(String value) {
		internalNotes.setValue(value);
	}

	public void focusNewNote() {
		newNote.focus();
	}

	public void setUploaderValue(String values) {
		uploader.setValue(values);
	}

	public void readOnlyNotesHistory(boolean value) {
		internalNotes.setReadOnly(value);
	}

	public void resetBtnLogic(String notesHistoryValue) {
		fileNameField.setValue("");
		newNote.setValue("");
		internalNotes.setReadOnly(false);
		internalNotes.setValue(notesHistoryValue);
		internalNotes.setReadOnly(true);
		table.removeAllItems();
		uploader.setValue("");
		uploader.setValue("");
		wordRes = new FileResource(wordFile);
		pdfRes = new FileResource(pdfFile);
		wordDownloader = new FileDownloader(wordRes);
		pdfDownloader = new FileDownloader(pdfRes);

	}

	public void resetAddMode() {
		fileNameField.setValue("");
		newNote.setValue("");
		uploader.setValue("");
		internalNotes.setReadOnly(false);
		internalNotes.setValue("");
		internalNotes.setReadOnly(true);
		table.removeAllItems();
	}

	public void removeAndDisablingComponents(boolean isView) {
		newNote.setReadOnly(isView);
		// internal notes always Read only
		internalNotes.setReadOnly(true);
		fileNameField.setReadOnly(isView);
		uploader.setReadOnly(isView);
		table.setReadOnly(isView);
		imageLayout.setEnabled(!isView);
		uploadComponent.setReadOnly(isView);
		wordPngImage.setEnabled(!isView);
		pdfPngImage.setEnabled(!isView);
		addNote.setEnabled(!isView);
		remove.setEnabled(!isView);
	}

	public void intailizingObject() {
		uploadReceiver = new FileUploader(moduleName);
		uploadComponent = new Upload(null, uploadReceiver);
		filePath = GtnFileNameUtils.getFile(basepath + File.separator + GtnFrameworkCommonStringConstants.ATTACHMENTS
				+ File.separator + moduleName);
		wordFile = GtnFileNameUtils.getFile(filePath + File.separator + fileName + ExportWord.DOC_EXT);
		pdfFile = GtnFileNameUtils.getFile(filePath + File.separator + fileName + ExportPdf.PDF_EXT);
		fileUploadPath = FileUploader.FILE_PATH + moduleName + GtnFrameworkCommonStringConstants.STR_SLASH;

		removeAndDisablingComponents(isViewMode);

	}

	public void addEnteredNotes() {
		if (!"".equals(String.valueOf(newNote.getValue()).trim())
				&& !"null".equals(String.valueOf(newNote.getValue()))) {
			new AbstractNotificationUtils() {

				@Override
				public void noMethod() {
					return;
				}

				@Override
				public void yesMethod() {
					SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					TimeZone central = TimeZone.getTimeZone("CST");
					format.setTimeZone(central);
					String addedNotes = internalNotes.getValue() + "<" + format.format(new Date()) + "> " + userName
							+ ":" + newNote.getValue() + "\n";
					internalNotes.setReadOnly(false);
					internalNotes.setValue(addedNotes);
					newNote.setValue("");
					internalNotes.setReadOnly(true);
					documentExporter();
				}
			}.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note?");
		} else {
			AbstractNotificationUtils.getInfoNotification("Enter New Note", "Please enter a new note");
			newNote.focus();
		}

	}

	public void uploadComponentSucceededLogic(Upload.SucceededEvent event) {
		try {
			String file = fileNameField.getValue();
			if (!"".equals(file)
					&& file.matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*")) {

				fileUpload = GtnFileNameUtils.getFile(fileUploadPath + event.getFilename());
				StringBuilder sb = new StringBuilder(event.getFilename());
				int index = sb.lastIndexOf(".");
				sb.replace(0, index, file);
				Date date = new Date();
				long value = date.getTime();
				sb.insert(sb.lastIndexOf("."), "_" + value);
				File destFileUpload = GtnFileNameUtils.getFile(fileUploadPath + sb.toString());
				Files.move(fileUpload.toPath(), Paths.get(destFileUpload.getAbsolutePath()),
						StandardCopyOption.REPLACE_EXISTING);
				NotesDTO attachmentDTO = new NotesDTO();
				String name = file + sb.substring(sb.indexOf("."));
				attachmentDTO.setDocumentName(name);
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				TimeZone central = TimeZone.getTimeZone("CST");
				format.setTimeZone(central);
				attachmentDTO.setDateAdded(format.format(new Date()));
				attachmentDTO.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				attachmentDTO.setUserName(userName);
				attachmentDTO.setDocumentFullPath(fileUploadPath + sb.toString());
				attachmentsListBean.addBean(attachmentDTO);
				fileNameField.setValue("");
				uploader.setValue("");
                 GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
                 notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
                 String filename = event.getFilename();
                 String message = filename + " Uploaded successfully";
                 notificationAction.addActionParameter(message);
                 notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
                 GtnUIFrameworkActionExecutor.executeSingleAction(uploadComponent.getId(), notificationAction);
			} else {
				AbstractNotificationUtils.getErrorNotification("File Name", "Please Enter a valid File Name");
				uploader.setValue("");
			}
		} catch (Exception ex) {
			gtnLogger.error("Error in upload sucession logic", ex);
		}

	}

	public void uploadComponentStartedLogic(Upload.StartedEvent event) {
		try {
			String file = fileNameField.getValue();
			if (!"".equals(file)
					&& file.matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*")) {
				String filename = event.getFilename().toLowerCase(Locale.ENGLISH);
				if (event.getFilename().equals("")) {
					uploadComponent.interruptUpload();
					AbstractNotificationUtils.getErrorNotification("Add Attachment",
							"Please select an attachment to add");
				} else {
					String fileExtension = "";

					gtnLogger.debug("Uploading started for file :" + filename);

					fileExtension = filename.contains(".") ? filename.substring(filename.lastIndexOf('.') + 1) : "";

					if (!getValidFormats().contains(fileExtension)) {
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
				AbstractNotificationUtils.getErrorNotification("Add Attachment", "Please select an attachment to add");
				uploader.setValue("");
			}
		} catch (Exception ex) {
			gtnLogger.error("Exception in Upload Started Logic", ex);
		}

	}

	public void removeButtonLogic() {
		try {
			if (tableBeanId == null || tableBean == null || !table.isSelected(tableBeanId)) {
				AbstractNotificationUtils.getErrorNotification(GtnFrameworkCommonConstants.REMOVE_ATTACHMENT,
						"Please select an attachment to remove ");
			} else if (!tableBean.getUserName().equalsIgnoreCase(userName)) {
				AbstractNotificationUtils.getInfoNotification(GtnFrameworkCommonConstants.REMOVE_ATTACHMENT,
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
				notification.getConfirmationMessage(GtnFrameworkCommonConstants.REMOVE_ATTACHMENT,
						"Are you sure you want to delete this Attachment?");

			}
		} catch (Exception e) {
			gtnLogger.error("Error in remove", e);
		}
	}

	public void itemClickLogic(ItemClickEvent event) {
		tableBeanId = event.getItemId();
		BeanItem<?> targetItem = null;
		if (tableBeanId instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) tableBeanId;
		} else if (tableBeanId instanceof NotesDTO) {
			targetItem = new BeanItem<>((NotesDTO) tableBeanId);
		}
		if (targetItem == null) {
			return;
		}
		tableBean = (NotesDTO) targetItem.getBean();
		GtnUIFrameworkWebserviceRequest gtnRequest= new GtnUIFrameworkWebserviceRequest();
		GtnWsAttachmentRequest gtnRequest1= new GtnWsAttachmentRequest();
		gtnRequest1.setDocDetailsSid(tableBean.getDocDetailsId());
		gtnRequest.setGtnWsAttachmentRequest(gtnRequest1);
		if (event.isDoubleClick()) {	
		    GtnUIFrameworkWebserviceResponse attachResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_DOWNLOAD_FILE_SERVICE, gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			byte[] attachment =attachResponse.getGtnWsAttachmentResponse().getGtnWsAttachmentBean().getFileData();
			try {
				FileOutputStream fileOuputStream = null ;
			    fileOuputStream = GtnFileNameUtils.getFileOutputStream(tableBean.getDocumentFullPath());
				fileOuputStream.write(attachment);		
				fileOuputStream.close();	
				File uploadedFile = GtnFileNameUtils.getFile(tableBean.getDocumentFullPath());
				Resource res = new FileResource(uploadedFile);
				fileDownloader.setFileDownloadResource(res);
				downloadFile(uploadedFile);
			} catch (Exception e) {
				gtnLogger.error("Error in file is not Found",e);
			}

		}

	}
	

	public List<String> getValidFormats() {
		return validFormats == null ? validFormats : Collections.unmodifiableList(validFormats);
	}

	public void setValidFormats(List<String> validFormats) {
		this.validFormats = new ArrayList<>(validFormats);
	}

	public List<Object> getNotesTabData() {
		List<Object> data = new ArrayList<>();
		data.add(internalNotes.getValue());
		data.add(attachmentsListBean.getItemIds());

		return data;
	}

	@SuppressWarnings("unchecked")
	public void setNotesTabData(List<Object> data) {
		if (data != null) {
			internalNotes.setReadOnly(false);
			String internalNote = getInternoteString(data.get(0));
			internalNotes.setValue(internalNote);
			internalNotes.setReadOnly(true);
			attachmentsListBean.removeAllItems();
			attachmentsListBean.addAll((List<NotesDTO>) (data.get(1)));
		}
		documentExporter();
	}

	public void refreshNotesTab() {
		resetAddMode();
		removeAndDisablingComponents(false);
	}

	public void loadNotesTab(List<Object> result) {
		setNotesTabData(result);

	}

	public void setNotesTabEnable(boolean isEditMode) {
		removeAndDisablingComponents(!isEditMode);
	}

	private String getInternoteString(Object internalNote) {
		if (internalNote != null && GtnFrameworkCommonStringConstants.STRING_NULL.equals(internalNote)) {
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}
		return String.valueOf(internalNote);
	}

	private void setFileNameToField(JsonArray arguments)  {
              elemental.json.impl.JreJsonString jsonstring=arguments.get(0);
		String value =jsonstring.asString() ;
		if (!"".equals(value)) {

			fileUpload = GtnFileNameUtils.getFile(fileUploadPath + value);
			String name = fileUpload.getAbsolutePath();
			if (name.contains("\\")) {
				String replace = name.replace("\\", ",");
				String[] array = replace.split(",");
				String filename = array[array.length - 1];
				uploader.setValue(filename);
				fileNameField.setValue(filename.substring(0, filename.lastIndexOf('.')));
			} else if (name.contains("/")) {
				final String replace = name.replace("/", ",");
				final String[] array = replace.split(",");
				final String filename = array[array.length - 1];
				uploader.setValue(filename);
				fileNameField.setValue(filename.substring(0, filename.lastIndexOf('.')));
			} else {
				uploader.setValue(name);
				fileNameField.setValue(name.substring(0, name.lastIndexOf('.')));
			}
		} else {
			uploader.setValue("");
			fileNameField.setValue("");
		}

	}

	private void enableDisableExportIcons() {
		if ("".equals(internalNotes.getValue())) {
			wordPngImage.setEnabled(false);
			pdfPngImage.setEnabled(false);
			wordPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_DISABLED);
			pdfPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_DISABLED);
		} else {
			wordPngImage.setEnabled(true);
			pdfPngImage.setEnabled(true);
			wordPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_ENABLED);
			pdfPngImage.setStyleName(GtnFrameworkCommonConstants.IMAGE_ENABLED);
		}
	}

}