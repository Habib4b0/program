package com.stpl.app.gtnworkflow.ui.lookup;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.dto.WorkFlowHistoryLookupDTO;
import com.stpl.app.gtnworkflow.filtergenerator.HistoryTableGenerator;
import com.stpl.app.gtnworkflow.logic.WorkflowLogic;
import com.stpl.app.gtnworkflow.logic.tablelogic.WorkflowHistoryTableLogic;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.app.gtnworkflow.util.ConstantUtils;
import com.stpl.app.gtnworkflow.util.FileUploader;
import com.stpl.app.gtnworkflow.util.xmlparser.SQlUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lokeshwari
 */
public class WorkFlowHistoryLookup extends Window {

	private static final Logger LOGGER = Logger.getLogger(WorkFlowHistoryLookup.class);
	@UiField("businessProcess")
	private TextField businessProcess;
	@UiField("workflowid")
	private TextField workflowId;
	@UiField("workflowName")
	private TextField workflowName;
	@UiField("closebtn")
	private Button closebtn;
	@UiField("excelExport")
	private Button excelExport;
	@UiField("tablepanel")
	private Panel tablepanel;
	@UiField("resultslayout")
	private VerticalLayout resultslayout;
	@UiField("controlLayout")
	private HorizontalLayout controlLayout;
	@UiField("tablepanel1")
	private Panel tablepanel1;
	@UiField("resultslayout1")
	private VerticalLayout resultslayout1;
	@UiField("table")
	protected ExtFilterTable table;
	@UiField("hLayout")
	private GridLayout hLayout;
	@UiField("detailsTable")
	private HorizontalLayout detailsTable;
	@UiField("companyIdLb")
	private Label companyIdLb;
	@UiField("companyId")
	private TextField companyId;
	@UiField("companyNoLb")
	private Label companyNoLb;
	@UiField("companyNo")
	private TextField companyNo;
	@UiField("companyNameLb")
	private Label companyNameLb;
	@UiField("companyName")
	private TextField companyName;
	@UiField("businessIdLb")
	private Label businessIdLb;
	@UiField("businessUnitId")
	private TextField businessUnitId;
	@UiField("businessNoLb")
	private Label businessNoLb;
	@UiField("businessUnitNo")
	private TextField businessUnitNo;
	@UiField("businessNameLb")
	private Label businessNameLb;
	@UiField("businessUnitName")
	private TextField businessUnitName;
	@UiField("workflowDesLb")
	private Label workflowDesLb;
	@UiField("workflowDes")
	private TextField workflowDes;
	@UiField("workflowDesLb1")
	private Label workflowDesLb1;
	@UiField("workflowDes1")
	private TextField workflowDes1;
	protected Object tableBeanId = null;

	WorkflowHistoryTableLogic tableLogic = new WorkflowHistoryTableLogic();
	final ExtPagedTable results = new ExtPagedTable(tableLogic);
	BeanItemContainer<WorkFlowHistoryLookupDTO> wfHistoryLookupitemBean = new BeanItemContainer<WorkFlowHistoryLookupDTO>(
			WorkFlowHistoryLookupDTO.class);
	InboxDashboardDTO inboxDashBoardDTOHidden;
	WorkFlowHistoryLookupDTO WFHistoryDTO = new WorkFlowHistoryLookupDTO();
	int workFlowMasterSystemId;
	WorkflowLogic workFlowLogic = new WorkflowLogic();
	protected String fileUploadPath;
	private NotesDTO notesdto = new NotesDTO();
	private WorkFlowHistoryLookupDTO historydto = new WorkFlowHistoryLookupDTO();
	protected final FileDownloader fileDownloader = new FileDownloader(new FileResource(GtnFileUtil.getFile("tst")));
	protected final BeanItemContainer<NotesDTO> attachmentsListBean = new BeanItemContainer<NotesDTO>(NotesDTO.class);
	protected String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

	protected Upload uploadComponent;
	public FileDownloader wordDownloader;
	public FileDownloader pdfDownloader;
	protected File filePath;
	protected File wordFile;
	protected File pdfFile;
	protected String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
			? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY;

	public WorkFlowHistoryLookup(InboxDashboardDTO inboxDashBoardDTOHidden) {
		super("Workflow History");
		this.inboxDashBoardDTOHidden = inboxDashBoardDTOHidden;
		workFlowMasterSystemId = inboxDashBoardDTOHidden.getWorkflowMasterSystemId();
		filePath = GtnFileUtil.getFile(basepath + File.separator + "Documents" + File.separator + StringUtils.EMPTY);
		init();
	}

	public void init() {

		addStyleName("bootstrap-ui");
		addStyleName("bootstrap");
		addStyleName("bootstrap-forecast bootstrap-nm");
		setContent(Clara.create(getClass().getResourceAsStream("/clara/HistoryLookup.xml"), this));
		center();
		setClosable(true);
		setModal(true);
		setWidth("1020px");
		setHeight("965px");
		configureFields();
		configureTable();

	}

	public void configureTable() {
		LOGGER.debug("Inside Configure Table results");
		tablepanel.setSizeFull();
		List<Integer> pageLength = new ArrayList<Integer>();
		pageLength.add(NumericConstants.FIVE);
		pageLength.add(NumericConstants.TEN);
		pageLength.add(NumericConstants.FIFTEEN);
		pageLength.add(NumericConstants.TWENTY);
		pageLength.add(NumericConstants.TWENTY_FIVE);
		pageLength.add(NumericConstants.FIFTY);
		pageLength.add(NumericConstants.HUNDRED);
		tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
		resultslayout.addComponent(results);
		CommonUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
		resultslayout.addComponent(controlLayout);
		tableLogic.setContainerDataSource(wfHistoryLookupitemBean);
		if ("ARM".equals(businessProcess.getValue())) {
			results.setVisibleColumns(ConstantUtils.getInstance().wfHistoryLookupColumnsArm);
			results.setColumnHeaders(ConstantUtils.getInstance().wfHistoryLookupHeaderArm);
		} else {
			results.setVisibleColumns(ConstantUtils.getInstance().wfHistoryLookupColumns);
			results.setColumnHeaders(ConstantUtils.getInstance().wfHistoryLookupHeader);
		}
		tableLogic.setPageLength(NumericConstants.FIVE);
		tableLogic.sinkItemPerPageWithPageLength(false);
		results.setSelectable(true);
		results.markAsDirty();
		results.setComponentError(null);
		results.setFilterBarVisible(true);
		results.setFilterDecorator(new ExtDemoFilterDecorator());
		results.setFilterGenerator(new HistoryTableGenerator());
		results.setImmediate(true);
		results.setValidationVisible(false);
		results.setWidth("100%");
		results.addStyleName("filterbar");
		hLayout.setHeight("93px");
		tableLogic.setSearchData(inboxDashBoardDTOHidden, true);
		results.setFilterDecorator(new ExtDemoFilterDecorator());
		results.setFilterGenerator(new HistoryTableGenerator());
		results.setImmediate(true);
		DateToStringConverter dateTimeConverter = new DateToStringConverter();
		dateTimeConverter.setDateFormat("MM/dd/yyyy hh:mm:ss");
		results.setConverter("modifiedDate", dateTimeConverter);
		results.setColumnAlignment("attachmentLink", ExtCustomTable.Align.CENTER);
		results.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);

		table.setWidth("100%");
		table.markAsDirty();
		table.addStyleName("filterbar");
		table.setFilterBarVisible(true);
		table.setFilterDecorator(new ExtDemoFilterDecorator());
		table.setImmediate(true);
		table.setPageLength(NumericConstants.SIX);
		table.setContainerDataSource(attachmentsListBean);
		table.setSelectable(true);
		results.setValidationVisible(false);
		table.setVisibleColumns(ConstantUtils.getInstance().wfHistoryLookupAttachmentsColumns);
		table.setColumnHeaders(ConstantUtils.getInstance().wfHistoryLookupAttachmentsHeader);

	}

	public void configureFields() {
		businessProcess.setImmediate(true);
		businessProcess.focus();
		businessProcess.setEnabled(false);
		companyNoLb.setVisible(false);
		companyNo.setVisible(false);
		companyNameLb.setVisible(false);
		companyName.setVisible(false);
		businessNoLb.setVisible(false);
		businessUnitNo.setVisible(false);
		businessNameLb.setVisible(false);
		businessUnitName.setVisible(false);
		workflowDesLb.setVisible(false);
		workflowDesLb1.setVisible(false);
		workflowDes.setVisible(false);
		workflowDes1.setVisible(false);

		businessUnitId.setEnabled(false);
		companyId.setEnabled(false);
		companyName.setEnabled(false);
		businessUnitName.setEnabled(false);

		if (inboxDashBoardDTOHidden.getWorkflowId().startsWith(WorkflowConstants.getComWorkflowParameter())
				|| inboxDashBoardDTOHidden.getWorkflowId().startsWith(WorkflowConstants.getGovWorkflowParameter())) {
			businessProcess.setValue("Forecasting");
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("BRF")) {
			businessProcess.setValue("Base Rate");
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("FDF")) {
			businessProcess.setValue("Fixed Dollar");
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("CMF")) {
			businessProcess.setValue(CommonUtils.CONTRACT_MANAGEMENT);
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("ARF")) {
			businessProcess.setValue("Accrual Rate Projection");
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("REF")) {
			businessProcess.setValue("Returns");
		} else if (inboxDashBoardDTOHidden.getWorkflowId().startsWith("ARM_TRX")) {
			businessProcess.setValue("ARM");
		}
		if ("ARM".equals(businessProcess.getValue())) {
			tablepanel1.setVisible(false);
			detailsTable.setWidth("100%");
			detailsTable.setHeight("100%");
			companyNoLb.setVisible(true);
			companyNo.setVisible(true);
			companyNameLb.setVisible(true);
			companyName.setVisible(true);
			businessNoLb.setVisible(true);
			businessUnitNo.setVisible(true);
			workflowDesLb.setVisible(true);
			workflowDesLb1.setVisible(true);
			workflowDes.setVisible(true);
			workflowDes1.setVisible(true);
			businessNameLb.setVisible(true);
			businessUnitName.setVisible(true);

			businessIdLb.setVisible(false);
			businessUnitId.setVisible(false);
			companyId.setVisible(false);
			companyIdLb.setVisible(false);
			companyNo.setEnabled(false);
			companyName.setEnabled(false);
			businessUnitNo.setEnabled(false);
			businessUnitName.setEnabled(false);
			workflowDes.setEnabled(false);
			workflowDes1.setEnabled(false);
		} else {
			tablepanel1.setVisible(true);
			detailsTable.setWidth("50%");
			companyNoLb.setVisible(false);
			companyNo.setVisible(false);
			companyNameLb.setVisible(false);
			companyName.setVisible(false);
			businessNoLb.setVisible(false);
			businessUnitNo.setVisible(false);
			businessNameLb.setVisible(false);
			businessUnitName.setVisible(false);
			workflowDesLb.setVisible(false);
			workflowDesLb1.setVisible(false);
			workflowDes.setVisible(false);
			workflowDes1.setVisible(false);
			if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcess.getValue())) {
				businessIdLb.setVisible(true);
				businessUnitId.setVisible(true);
				companyId.setVisible(true);
				companyIdLb.setVisible(true);
				companyNameLb.setVisible(true);
				companyName.setVisible(true);
				businessNameLb.setVisible(true);
				businessUnitName.setVisible(true);
			}

		}

		workflowId.setImmediate(true);
		workflowId.setEnabled(false);
		workflowName.setImmediate(true);
		workflowName.setDescription(workflowName.getValue());
		workflowName.setEnabled(false);

		closebtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(Button.ClickEvent event) {
				getUI().setData(null);
				close();
			}
		});

		workflowId.setValue(inboxDashBoardDTOHidden.getWorkflowId());
		workflowName.setValue(inboxDashBoardDTOHidden.getWorkflowName());
		workflowDes.setValue(inboxDashBoardDTOHidden.getWorkflowDescription());

		if ("ARM".equals(businessProcess.getValue())) {
			StringBuilder sql = new StringBuilder(SQlUtil.getQuery("wi.historyPopup"));
			sql.append(" where WORKFLOW_ID = '").append(inboxDashBoardDTOHidden.getWorkflowId()).append("'");
			List resultList = CompanyMasterLocalServiceUtil.executeQuery(sql.toString());
			getWorkFlowHistory(resultList, businessProcess.getValue());
		} else {
			if (!CommonUtils.CONTRACT_MANAGEMENT.equals(businessProcess.getValue())) {
				List resultList = workFlowLogic
						.getDetailsForHistory(inboxDashBoardDTOHidden.getWorkflowMasterSystemId());
				if (resultList != null) {
					getWorkFlowHistory(resultList, businessProcess.getValue());
				}
			}
		}

		workFlowMasterSystemId = inboxDashBoardDTOHidden.getWorkflowMasterSystemId();

		excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
		excelExport.setStyleName("link");
		excelExport.setDescription("Export to excel");
		excelExport.setIconAlternateText("Excel export");
		excelExport.setHtmlContentAllowed(true);
		excelExport.addClickListener(new Button.ClickListener() {

			public void buttonClick(Button.ClickEvent event) {
				try {
					excelExportLogic();
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		});

		results.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
				try {
					historyitemClickLogic(event);
				} catch (Exception e) {
					LOGGER.error("Error while clicking the historyitemClickLogic Item :" + e);
				}
			}

			public void historyitemClickLogic(ItemClickEvent event) {
				tableBeanId = event.getItemId();
				BeanItem<?> targetItem = null;
				if (tableBeanId instanceof BeanItem<?>) {
					targetItem = (BeanItem<?>) tableBeanId;
				} else if (tableBeanId instanceof WorkFlowHistoryLookupDTO) {
					targetItem = new BeanItem<WorkFlowHistoryLookupDTO>((WorkFlowHistoryLookupDTO) tableBeanId);
				}
				historydto = (WorkFlowHistoryLookupDTO) targetItem.getBean();

				if (event.isDoubleClick()) {
					final NotesDTO dto = new NotesDTO();

					if (historydto.getFileName() != null && !historydto.getFileName().isEmpty()) {
						String sql = "SELECT DD.FILE_NAME,DD.UPLOADED_DATE,DD.UPLOADED_BY,DD.FILE_TYPE FROM DOC_DETAILS DD WHERE DOC_DETAILS_ID IN ("
								+ historydto.getFileName() + ")";
						List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sql);

						for (int i = 0; i < list.size(); i++) {
							Object[] obj = list.get(i);
							dto.setDocumentName(obj[0] != null && !String.valueOf(obj[0]).isEmpty()
									? String.valueOf(obj[0]) : StringUtils.EMPTY);
							dto.setDateAdded(obj[1] != null ? String.valueOf((Date) obj[1]) : null);
							dto.setUserName(obj[NumericConstants.TWO] != null
									&& !String.valueOf(obj[NumericConstants.TWO]).isEmpty()
											? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);
							dto.setFileType(String.valueOf(obj[NumericConstants.THREE]));
							attachmentsListBean.addItem(dto);
						}
						String fileUploadPath = FileUploader.FILE_PATH + StringUtils.EMPTY + userId + "/";
						String name = dto.getDocumentName();
						String type = dto.getFileType();
						dto.setDocumentFullPath(fileUploadPath + name + "." + type);
						attachmentsListBean.addItem(dto);
					}
				}
			}
		});

		table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
				try {
					attachmentitemClickLogic(event);
				} catch (Exception e) {
					LOGGER.error("Error while clicking the attachmentitemClickLogic Item :" + e);
				}
			}

			public void attachmentitemClickLogic(ItemClickEvent event) {
				try {
					tableBeanId = event.getItemId();
					BeanItem<?> targetItem = null;
					if (tableBeanId instanceof BeanItem<?>) {
						targetItem = (BeanItem<?>) tableBeanId;
					} else if (tableBeanId instanceof NotesDTO) {
						targetItem = new BeanItem<NotesDTO>((NotesDTO) tableBeanId);
					}
					notesdto = (NotesDTO) targetItem.getBean();
					if (event.isDoubleClick()) {
						File uploadedFile = GtnFileUtil.getFile(notesdto.getDocumentFullPath());
						Resource res = new FileResource(uploadedFile);
						fileDownloader.setFileDownloadResource(res);
						downloadFile(uploadedFile);
					}
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		});
	}

	public void downloadFile(File uploadedFile) {
		try {
			if (uploadedFile.exists()) {
				Resource res = new FileResource(uploadedFile);
				Page.getCurrent().open(res, "_blank", true);
			}
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	protected void excelExportLogic() {
		LOGGER.debug("Entering excelExportLogic");
		createWorkSheet();
		LOGGER.debug("Ending excelExportLogic");
	}

	private void createWorkSheet() {
		LOGGER.debug("Entering createWorkSheet");
		try {
			final long recordCount = workFlowLogic.workFlowHistorySearchCount(workFlowMasterSystemId,
					tableLogic.getFilters());
			ExcelExportforBB.createWorkSheet(results.getColumnHeaders(), recordCount, this, getUI(),
					"WorkFlow_History");
			LOGGER.debug("Ending createWorkSheet");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Logic for creating worksheet content.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param printWriter
	 *            the print writer
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
		WorkFlowHistoryLookupDTO dto;
		final List<WorkFlowHistoryLookupDTO> searchList = workFlowLogic.workFlowHistorySearch(workFlowMasterSystemId,
				tableLogic.getFilters(), tableLogic.getSortByColumns());
		final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
		String date;
		for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

			dto = searchList.get(rowCount);

			printWriter.print(ConstantsUtils.QUOTE + dto.getStatus() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
			if (dto.getModifiedDate() == null) {
				printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
			} else {
				date = dateFormat.format(dto.getModifiedDate());
				printWriter.print(date + ConstantsUtils.COMMA);
			}
			String modified = dto.getModifiedBy() == null ? StringUtils.EMPTY : dto.getModifiedBy();
			printWriter.print(ConstantsUtils.QUOTE + modified + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
			printWriter.println(ConstantsUtils.QUOTE + dto.getNotes() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);

		}
	}

	/**
	 * To get work flow history
	 *
	 * @param resultList
	 * @return
	 */
	private void getWorkFlowHistory(List resultList, String businessProcess) {
		for (int i = 0; i < resultList.size(); i++) {
			Object[] obj = (Object[]) resultList.get(i);

			if ("ARM".equals(businessProcess)) {
				companyNo.setValue(String.valueOf(obj[0]));
				businessUnitNo.setValue(String.valueOf(obj[NumericConstants.TWO]));
			} else {
				companyId.setValue(String.valueOf(obj[0]));
				businessUnitId.setValue(String.valueOf(obj[NumericConstants.TWO]));
			}
			companyName.setValue(String.valueOf(obj[1]));
			businessUnitName.setValue(String.valueOf(obj[NumericConstants.THREE]));
		}
	}
}
