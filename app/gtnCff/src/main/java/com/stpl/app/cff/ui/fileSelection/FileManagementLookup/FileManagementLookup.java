/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.FileManagementLookup;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.Util.CommonUIUtil;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementFilterGenerator;
import com.stpl.app.cff.ui.fileSelection.dto.FileMananagementResultDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.ui.fileSelection.lazyload.ForecastYearContainer;
import com.stpl.app.cff.ui.fileSelection.lazyload.ForecastYearCriteria;
import com.stpl.app.cff.ui.fileSelection.logic.FileManagementLogic;
import com.stpl.app.cff.ui.fileSelection.logic.tableLogic.FileDetailsTableLogic;
import com.stpl.app.cff.ui.fileSelection.logic.tableLogic.FileResultsTableLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ErrorCodeUtil;
import com.stpl.app.cff.util.ErrorCodes;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.ValidationUtils;
import com.stpl.app.cff.util.converters.DataFormatConverter;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.GtnWsCsvExportUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.data.validator.AbstractValidator;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class FileManagementLookup.
 *
 * @author Elangovan
 */
public class FileManagementLookup extends Window {

	/**
	 * The file name.
	 */
	@UiField("fileName")
	private TextField fileName;
	/**
	 * The type.
	 */
	@UiField("type")
	private TextField type;
	/**
	 * The version.
	 */
	@UiField("version")
	private TextField version;
	/**
	 * The from date.
	 */
	@UiField("fromDate")
	private PopupDateField fromDate;
	/**
	 * The to date.
	 */
	@UiField("toDate")
	private PopupDateField toDate;
	/**
	 * The forecast name.
	 */
	@UiField("forecastName")
	private TextField forecastName;
	/**
	 * The forecast version.
	 */
	@UiField("forecastVersion")
	private TextField forecastVersion;
	/**
	 * The forecast date.
	 */
	@UiField("forecastDate")
	private TextField forecastDate;
	/**
	 * The created date.
	 */
	@UiField("createdDate")
	private TextField createdDate;
	@UiField("SearchForecastddlb")
	private ComboBox SearchForecastddlb;

	/**
	 * The forecast year.
	 */
	private final ComboBox forecastYearCombo = new ComboBox();
	/**
	 * The file name list.
	 */
	private final TextField fileNameList = new TextField();
	/**
	 * The version list.
	 */
	private final TextField versionList = new TextField();
	/**
	 * The selectedYear
	 */
	private int selectedYear;
	/**
	 * The search.
	 */
	@UiField("search")
	private Button search;
	/**
	 * The reset.
	 */
	@UiField("reset")
	private Button reset;
	/**
	 * The select.
	 */
	@UiField("select")
	private Button select;
	/**
	 * The details.
	 */
	@UiField("details")
	private Button details;
	/**
	 * The close.
	 */
	@UiField("close")
	private Button close;
	@UiField("summaryPanel")
	private Panel summaryPanel;

	@UiField("cssLayoutForecastSection")
	private CssLayout cssLayoutForecastSection;

	private FileResultsTableLogic tableLogic = new FileResultsTableLogic();
	/**
	 * The file history table.
	 */
	private ExtPagedTable resultsFilterTable = new ExtPagedTable(tableLogic);
	private FileDetailsTableLogic detailstableLogic = new FileDetailsTableLogic();
	private HorizontalLayout controlLayout = new HorizontalLayout();
	private HorizontalLayout detailsControlLayout = new HorizontalLayout();
	/**
	 * The details table.
	 */
	private ExtPagedTable detailsFilterTable = new ExtPagedTable(detailstableLogic);
	/**
	 * The select file.
	 */
	private TextField selectFile = new TextField();
	/**
	 * The file type.
	 */
	@UiField("fileType")
	private TextField vFileType;
	/**
	 * The country.
	 */
	@UiField("country")
	private ComboBox country;
	/**
	 * The excel export result.
	 */
	@UiField("excelExportResult")
	private Button excelExportResult;
	/**
	 * The excel export detail.
	 */
	@UiField("excelExportDetail")
	private Button excelExportDetail;

	@UiField("tableLayout")
	private VerticalLayout tableLayout;

	@UiField("detailsTable")
	private VerticalLayout detailsTable;
        
	@UiField("horizLayout")
	private HorizontalLayout horizLayout;
        
	public static final String SEARCH_ERROR = "Search Error";
	public static final String FORCAST_YEAR = "forcastYear";
	/**
	 * The excel image.
	 */
	private final ThemeResource excelImage = new ThemeResource("img/excel.png");
	/**
	 * The results bean.
	 */
	private final BeanItemContainer<FileMananagementResultDTO> resultsBean = new BeanItemContainer<>(
			FileMananagementResultDTO.class);
	/**
	 * The details bean.
	 */
	private BeanItemContainer<FileMananagementResultDTO> detailsBean = new BeanItemContainer<>(
			FileMananagementResultDTO.class);
	/**
	 * The file mgt helperDto.
	 */
	private FileMananagementResultDTO fileMgtDTO = new FileMananagementResultDTO();
	/**
	 * The file mgt index helperDto.
	 */
	private FileMananagementResultDTO fileMgtIndexDTO = new FileMananagementResultDTO();
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementLookup.class);// Logger
	// Declaration
	/**
	 * The Constant NULLITEM.
	 */
	private static final BeanItem<?> NULLITEM = null;
	/**
	 * The Constant HelperDTO.
	 */
	private HelperDTO helperDto = new HelperDTO(ConstantsUtils.SELECT_ONE);
	/**
	 * The selected item.
	 */
	private transient Object selectedItem;

	/**
	 * The details flag.
	 */
	private char detailsFlag = 'N';

	/**
	 * The file lookupItemName.
	 */
	private TextField lookupItemName = new TextField();
	/**
	 * The file lookupItemName.
	 */
	private String selectedFileCountry;
	/**
	 * The file lookupItemName.
	 */
	private String selectedFile = ConstantsUtils.EMPTY;
	/*
	 * The save flag
	 */
	private boolean saveflag;
	/*
	 * The selectClose flag
	 */
	private boolean selectClose;
	/**
	 * The file DAO.
	 */
	private DataFormatConverter priceFormat = new DataFormatConverter("#0.00", DataFormatConverter.INDICATOR_DOLLAR);
	private DataFormatConverter unitsFormat = new DataFormatConverter("#0.0");
	private DataFormatConverter dollarsFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
	private DataFormatConverter zeroDecimalFormat = new DataFormatConverter("#0");
	private List<FileMananagementResultDTO> addlineList = new ArrayList<>();
	private ComboBox fmFileType;
	private FileMananagementResultDTO resultDTO = new FileMananagementResultDTO();
	private FileMananagementResultDTO detailsResultDTO = new FileMananagementResultDTO();
	private ExtFilterTable excelTable;
	private BeanItemContainer<FileMananagementResultDTO> excelTableBean;
	private ExtFilterTable excelDetailsTable;
	private BeanItemContainer<FileMananagementResultDTO> excelDetailsBean;
	private FileManagementLogic vFileMgmtLogic = new FileManagementLogic();
	private String helperFileType;
	private BeanItemContainer searchContainer;
	private SessionDTO sessionDTO;
	private boolean isdetails;
	public static final String REGEX = "([0-9|a-z|A-Z|*\\\\ ])*";
	private FileSelectionDTO fileselectiondto = new FileSelectionDTO();
	private String fileType;
	private Object itemId;
	private String businessUnit;
	public static final String UNIQUE_COMBINATION_ERROR = "Unique combination error";

	public FileManagementLookup() {

	}

	public FileManagementLookup(final SessionDTO sessionDTO, final boolean isdetails,
			String fileType, BeanItemContainer searchContainer, Object itemId, String businessUnit) {
		super("File Lookup");
		LOGGER.debug("FileManagementLookup constructor initiated");

		this.sessionDTO = sessionDTO;
		this.searchContainer = searchContainer;
		this.itemId = itemId;
		this.isdetails = isdetails;
		this.fileType = fileType;
		this.businessUnit = businessUnit;
		LOGGER.debug("FileManagementLookup constructor Ended");
	}

	/**
	 * Gets the helperDto.
	 *
	 * @return the helperDto
	 */
	public HelperDTO getHelperDto() {
		return helperDto;
	}

	/**
	 * Sets the helperDto.
	 *
	 * @param helperDto
	 *            the new helperDto
	 */
	public void setHelperDto(final HelperDTO helperDto) {
		this.helperDto = helperDto;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public TextField getFileName() {
		return fileName;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TextField getType() {
		return type;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public TextField getVersion() {
		return version;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public PopupDateField getFromDate() {
		return fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public PopupDateField getToDate() {
		return toDate;
	}

	public FileSelectionDTO getFileselectiondto() {
		return fileselectiondto;
	}

	public void setFileselectiondto(FileSelectionDTO fileselectiondto) {
		this.fileselectiondto = fileselectiondto;
	}

	/**
	 * Gets the forecast name.
	 *
	 * @return the forecast name
	 */
	public TextField getForecastName() {
		return forecastName;
	}

	/**
	 * Gets the forecast version.
	 *
	 * @return the forecast version
	 */
	public TextField getForecastVersion() {
		return forecastVersion;
	}

	/**
	 * Gets the forecast date.
	 *
	 * @return the forecast date
	 */
	public TextField getForecastDate() {
		return forecastDate;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public TextField getCreatedDate() {
		return createdDate;
	}

	/**
	 * Gets the file name list.
	 *
	 * @return the file name list
	 */
	public TextField getFileNameList() {
		return fileNameList;
	}

	/**
	 * Gets the version list.
	 *
	 * @return the version list
	 */
	public TextField getVersionList() {
		return versionList;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public Button getSearch() {
		return search;
	}

	/**
	 * Gets the reset.
	 *
	 * @return the reset
	 */
	public Button getReset() {
		return reset;
	}

	/**
	 * Gets the select.
	 *
	 * @return the select
	 */
	public Button getSelect() {
		return select;
	}

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public Button getDetails() {
		return details;
	}

	public ComboBox getSearchForecastddlb() {
		return SearchForecastddlb;
	}

	public void setSearchForecastddlb(ComboBox SearchForecastddlb) {
		this.SearchForecastddlb = SearchForecastddlb;
	}

	/**
	 * Gets the close.
	 *
	 * @return the close
	 */
	public Button getClose() {
		return close;
	}

	/**
	 * Gets the results filter table.
	 *
	 * @return the results filter table
	 */
	public ExtPagedTable getResultsFilterTable() {
		return resultsFilterTable;
	}

	/**
	 * Gets the details filter table.
	 *
	 * @return the details filter table
	 */
	public ExtPagedTable getDetailsFilterTable() {
		return detailsFilterTable;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public TextField getvFileType() {
		return vFileType;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public ComboBox getCountry() {
		return country;
	}

	/**
	 * Gets the excel export result.
	 *
	 * @return the excel export result
	 */
	public Button getExcelExportResult() {
		return excelExportResult;
	}

	/**
	 * Gets the excel export detail.
	 *
	 * @return the excel export detail
	 */
	public Button getExcelExportDetail() {
		return excelExportDetail;
	}

	/**
	 * Gets the excel image.
	 *
	 * @return the excel image
	 */
	public ThemeResource getExcelImage() {
		return excelImage;
	}

	/**
	 * Gets the results bean.
	 *
	 * @return the results bean
	 */
	public BeanItemContainer<FileMananagementResultDTO> getResultsBean() {
		return resultsBean;
	}

	/**
	 * Gets the details bean.
	 *
	 * @return the details bean
	 */
	public BeanItemContainer<FileMananagementResultDTO> getDetailsBean() {
		return detailsBean;
	}

	/**
	 * Gets the select file.
	 *
	 * @return the select file
	 */
	public TextField getSelectFile() {
		return selectFile;
	}

	/**
	 * Sets the select file.
	 *
	 * @param selectFile
	 *            the select file
	 */
	public void setSelectFile(final TextField selectFile) {
		this.selectFile = selectFile;
	}

	/**
	 * Gets the file mgt helperDto.
	 *
	 * @return the file mgt helperDto
	 */
	public FileMananagementResultDTO getFileMgtDTO() {
		return fileMgtDTO;
	}

	/**
	 * Sets the file mgt helperDto.
	 *
	 * @param fileMgtDTO
	 *            the file mgt helperDto
	 */
	public void setFileMgtDTO(final FileMananagementResultDTO fileMgtDTO) {
		this.fileMgtDTO = fileMgtDTO;
	}

	/**
	 * Gets the file mgt index helperDto.
	 *
	 * @return the file mgt index helperDto
	 */
	public FileMananagementResultDTO getFileMgtIndexDTO() {
		return fileMgtIndexDTO;
	}

	/**
	 * Sets the file mgt index helperDto.
	 *
	 * @param fileMgtIndexDTO
	 *            the file mgt index helperDto
	 */
	public void setFileMgtIndexDTO(final FileMananagementResultDTO fileMgtIndexDTO) {
		this.fileMgtIndexDTO = fileMgtIndexDTO;
	}

	/**
	 * Gets the selected item.
	 *
	 * @return the selected item
	 */
	public Object getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Sets the selected item.
	 *
	 * @param selectedItem
	 *            the new selected item
	 */
	public void setSelectedItem(final Object selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * Gets the details flag.
	 *
	 * @return the details flag
	 */
	public char getDetailsFlag() {
		return detailsFlag;
	}

	/**
	 * Sets the details flag.
	 *
	 * @param detailsFlag
	 *            the new details flag
	 */
	public void setDetailsFlag(final char detailsFlag) {
		this.detailsFlag = detailsFlag;
	}

	/**
	 * The customer group binder.
	 */
	/**
	 * Inits the.
	 */
	public void init() {
		try {
			LOGGER.debug("init Method initiated");

			addStyleName(Constants.BOOTSTRAP);
			addStyleName("bootstrap-bb");
			addStyleName("fileMgmt");
			addStyleName("popupwinwidth");
			center();
			setClosable(true);
			setModal(true);
			setWidth("1620px");
			setHeight("950px");
			setContent(Clara.create(getClass().getResourceAsStream("/cff/tabs/fileManagementLookup.xml"), this));

			configureFields();
			configureTable();
			configureDetailsTable();

			if (isdetails) {
				select.setEnabled(false);
			}
			LOGGER.debug("init method Ended");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
					ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
		}

	}

	/**
	 * Results item click.
	 *
	 * @param objectid
	 *            the objectid
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void resultsItemClick(final Object objectid) {
		LOGGER.debug("resultsItemClick method started ");
		if (objectid == null) {
			fileNameList.setValue(null);
			versionList.setValue(null);
			selectedFileCountry = ConstantsUtils.EMPTY;
			selectedYear = 0;
		} else {
			BeanItem<?> targetItem;
			if (objectid instanceof BeanItem<?>) {
				targetItem = (BeanItem<?>) objectid;
			} else if (objectid instanceof FileMananagementResultDTO) {
				targetItem = new BeanItem<>((FileMananagementResultDTO) objectid);
			} else {
				targetItem = NULLITEM;
			}
			final String fileNameListValue = ((FileMananagementResultDTO) targetItem.getBean()).getFileName();
			final String versionListValue = ((FileMananagementResultDTO) targetItem.getBean()).getVersion();
			selectedFileCountry = ((FileMananagementResultDTO) targetItem.getBean()).getCountry();
			selectedFile = ((FileMananagementResultDTO) targetItem.getBean()).getFileType();
			fileMgtDTO = (FileMananagementResultDTO) targetItem.getBean();
			fileNameList.setValue(String.valueOf(fileNameListValue));
			versionList.setValue(String.valueOf(versionListValue));
		}

		LOGGER.debug("resultsItemClick method Ended ");
	}

	/**
	 * Details item click.
	 *
	 * @param objectid
	 *            the objectid
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void detailsItemClick(final Object objectid) {
		LOGGER.debug("resultsItemClick method started ");
		if (objectid == null) {
			selectedItem = null;
		} else {
			selectedItem = detailsFilterTable.getItem(objectid);
		}
		LOGGER.debug("resultsItemClick method Ended ");
	}

	/**
	 * Configure fields.
	 */
	private void configureFields() {
		LOGGER.debug("configureFields method initiated ");
		horizLayout.addStyleName("comboBox-Allignment");
		summaryPanel.addStyleName("excepttable");
		horizLayout.setMargin(true);
		helperFileType = fileselectiondto.getHelperfileType();
		vFileType.setValue(fileType);
		country.addItem(ConstantsUtils.COUNTRY_US);
		country.addItem(ConstantsUtils.COUNTRY_PR);
		country.select(ConstantsUtils.COUNTRY_US);
		country.setImmediate(true);
		fromDate.setDescription(ConstantsUtils.DATE_DES);
		fromDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		toDate.setDescription(ConstantsUtils.DATE_DES);
		toDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		excelExportDetail.setDescription(ConstantsUtils.EXCEL_EXPORT);
		excelExportDetail.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
		excelExportResult.setDescription(ConstantsUtils.EXCEL_EXPORT);
		excelExportResult.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
		setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
		fileName.focus();
		fileName.setImmediate(true);
		version.setImmediate(true);
		forecastYearCombo.setImmediate(true);
		fromDate.setImmediate(true);
		toDate.setImmediate(true);
		vFileType.setImmediate(true);
		country.setImmediate(true);
		forecastYearCombo.setNullSelectionAllowed(true);
		forecastYearCombo.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
		forecastYearCombo.setPageLength(NumericConstants.SEVEN);
		forecastYearCombo.setInputPrompt(ConstantsUtils.SELECT_ONE);
		forecastYearCombo.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
		forecastYearCombo.markAsDirty();
		forecastYearCombo.select(helperDto);
		forecastYearCombo.setImmediate(true);
		SearchForecastddlb.setImmediate(true);
		SearchForecastddlb.setEnabled(false);
		SearchForecastddlb.setNullSelectionAllowed(true);
		SearchForecastddlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
		SearchForecastddlb.setPageLength(NumericConstants.SEVEN);
		SearchForecastddlb.addItem(ConstantsUtils.SELECT_ONE);
		SearchForecastddlb.select(ConstantsUtils.SELECT_ONE);
		SearchForecastddlb.markAsDirty();
		SearchForecastddlb.setEnabled(true);
		getSearchForecastyear();
		vFileType.setReadOnly(true);
		type.addValidator(new StringLengthValidator("Type length should be less than 50 characters", 0,
				NumericConstants.FIFTY, true));
		type.setValidationVisible(true);
		type.addValidator(new RegexpValidator(REGEX, "Type can only be Alphanumeric"));
		fileName.addValidator(new StringLengthValidator("FileName length should be less than 50 characters", 0,
				NumericConstants.FIFTY, true));
		fileName.setValidationVisible(true);
		fileName.addValidator(new RegexpValidator(REGEX, "FileName can only be Alphanumeric"));
		version.addValidator(new StringLengthValidator("Version length should be less than 50 characters", 0,
				NumericConstants.FIFTY, true));
		version.setValidationVisible(true);
		version.addValidator(new RegexpValidator(REGEX, "Version can only be Alphanumeric"));

		getForecastYear();
		searchButton();
		resetButton();
		detailsButton();
		closeButton();
		excelExport();
		selectButton();
		makeSummaryReadOnly();

		toDate.addValidator(
				new FileManagementLookup.DateValidator("Created Date From should be before Created Date To"));
		fromDate.setValidationVisible(true);
		toDate.setValidationVisible(true);

		LOGGER.debug("configureFields ended ");
		LOGGER.debug("Ending addItemDetailsTable");

	}

	/**
	 * Gets the forecast year.
	 *
	 * @return the forecast year
	 */
	private void getForecastYear() {
		try {
			LOGGER.debug("In getForecastYear started");
			final LazyContainer results = new LazyContainer(HelperDTO.class, new ForecastYearContainer(),
					new ForecastYearCriteria());
			results.setMinFilterLength(0);
			forecastYearCombo.setContainerDataSource(results);
			forecastYearCombo.select(helperDto);
			LOGGER.debug("In getForecastYear Ended");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
					ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4008));
		}
	}

	/**
	 * Search button.
	 */
	private void searchButton() {
		search.addClickListener(new Button.ClickListener() {
			/**
			 * Called when Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void buttonClick(final Button.ClickEvent event) {
				LOGGER.debug("In searchButton searchButtonClickLogic started");
				try {
					searchButtonClickLogic();
				} catch (ParseException e) {
					LOGGER.error(e.getMessage());
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
				}
				LOGGER.debug("In searchButton searchButtonClickLogic Ended");
			}
		});
	}

	/**
	 * Search button click logic.
	 *
	 * @param event
	 *            the event
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void searchButtonClickLogic() throws ParseException {
		LOGGER.debug("entering searchButtonClickLogic");
		resultsBean.removeAllItems();
		detailsBean.removeAllItems();
		changeReadOnlyState();
		forecastName.setValue(ConstantsUtils.EMPTY);
		forecastVersion.setValue(ConstantsUtils.EMPTY);
		forecastDate.setValue(ConstantsUtils.EMPTY);
		createdDate.setValue(ConstantsUtils.EMPTY);
		fromDate.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(fromDate.getValue()));
		toDate.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(toDate.getValue()));
		String forcaste;
		String SearchforecastYear;
		if (forecastYearCombo.getValue() == null || ConstantsUtils.SELECT_ONE.equals(forecastYearCombo.getValue().toString())) {
			forcaste = StringUtils.EMPTY;
		} else {
			forcaste = forecastYearCombo.getValue().toString();
		}
		if (SearchForecastddlb.getValue() == null
				|| ConstantsUtils.SELECT_ONE.equals(SearchForecastddlb.getValue().toString())) {
			SearchforecastYear = StringUtils.EMPTY;
		} else {
			SearchforecastYear = SearchForecastddlb.getValue().toString();
		}
		if (StringUtils.isEmpty(fileName.getValue().trim()) && StringUtils.isEmpty(type.getValue().trim())
				&& StringUtils.isEmpty(version.getValue().trim())
				&& (forcaste == null || forcaste.equals(ConstantsUtils.EMPTY)) && fromDate.getValue() == null
				&& toDate.getValue() == null && SearchforecastYear.equals("")) {
			MessageBox.showPlain(Icon.ERROR, SEARCH_ERROR,
					"Please Enter a value within text boxes of the Search Criteria", ButtonId.OK);
		} else {
			resultDTO.setFileType(String.valueOf(vFileType.getValue()));
			resultDTO.setCountry(String.valueOf(country.getValue()));
			resultDTO.setFileName(String.valueOf(fileName.getValue()));
			resultDTO.setType(String.valueOf(type.getValue()));
			resultDTO.setForecastYear(forcaste);
			resultDTO.setFromPeriod(fromDate.getValue());
			resultDTO.setToPeriod(toDate.getValue());
			resultDTO.setVersion(String.valueOf(version.getValue().trim()));
			resultDTO.setHelperType(helperFileType);
			resultDTO.setSearchforcastYear(SearchforecastYear);
			loadResultsTable();

			if (tableLogic.isResultsEmpty()) {
				MessageBox.showPlain(Icon.INFO, "No Results Found",
						"No results were found that match the entered search criteria.", ButtonId.OK);
			} else {
				CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);
			}
		}
		makeSummaryReadOnly();
		LOGGER.debug("searchButtonClickLogic method Ended");
	}

	/**
	 * Details button.
	 */
	private void detailsButton() {
		details.addClickListener(new Button.ClickListener() {
			/**
			 * Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void buttonClick(final Button.ClickEvent event) {
				LOGGER.debug("In detailsButton detailsButtonClickLogic started");
				try {
					if (ConstantsUtils.EMPTY.equals(fileNameList.getValue())) {
						AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
								"Please click on a record within the results list view");
					} else {
						addlineList.clear();
						detailsButtonClickLogic();
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4009));
				}
				LOGGER.debug("In detailsButton detailsButtonClickLogic Ended");
			}
		});

	}

	/**
	 * Details button click logic.
	 *
	 * @param event
	 *            the event
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void detailsButtonClickLogic() {
		LOGGER.debug("entering detailsButtonClickLogic methhod");

		try {
			final FileManagementLogic fmLogic = new FileManagementLogic();
			String finalVersion;
			String etlVersion;
			String selectedVersion = versionList.getValue();
			if (selectedVersion.contains(".")) {
				String[] array = selectedVersion.split("\\.");
				etlVersion = array[0];
				finalVersion = etlVersion + "~" + selectedVersion;
			} else {
				finalVersion = selectedVersion;
			}
			detailsBean.removeAllItems();
			detailsResultDTO.setFileName(String.valueOf(fileNameList.getValue()));
			detailsResultDTO.setVersion(finalVersion);
			detailsResultDTO.setFileType(String.valueOf(vFileType.getValue()));
			detailsResultDTO.setCountry(String.valueOf(country.getValue()));
			detailsResultDTO.setHelperType(fileType);
			detailstableLogic.configureSearchData(detailsResultDTO);
			detailsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
			detailsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
			detailsFilterTable.setImmediate(true);
			detailsFilterTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
			detailsFilterTable.setSelectable(true);
			detailsFilterTable.markAsDirtyRecursive();

			if (tableLogic.isResultsEmpty()) {
				CommonUIUtils.getMessageNotification(ConstantsUtils.NO_RESULSTS);
			} else {
				if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
					final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(
							String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), fileType,
							String.valueOf(country.getValue()));
					changeReadOnlyState();
					forecastName.setValue(fileManageDTO.getForecastName());
					forecastVersion.setValue(fileManageDTO.getForecastVersion());
					forecastDate.setValue(fileManageDTO.getForecastDate());
					createdDate.setValue(fileManageDTO.getCreatedDate());
					makeSummaryReadOnly();
					detailsFlag = 'Y';
				} else if (fileType.equals(ConstantsUtils.DEMAND)
						|| (fileType).equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
					final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(
							String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), fileType,
							String.valueOf(country.getValue()));
					changeReadOnlyState();
					forecastName.setValue(fileManageDTO.getForecastName());
					forecastVersion.setValue(fileManageDTO.getForecastVersion());
					createdDate.setValue(fileManageDTO.getCreatedDate());
					makeSummaryReadOnly();
					detailsFlag = 'Y';
				} else if (fileType.equals(ConstantsUtils.DEMAND)
						|| (fileType).equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)
						|| (fileType).equals(ConstantsUtils.CUSTOMERGTS)) {
					final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(
							String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), fileType,
							String.valueOf(country.getValue()));
					changeReadOnlyState();
					forecastName.setValue(fileManageDTO.getForecastName());
					forecastVersion.setValue(fileManageDTO.getForecastVersion());
					createdDate.setValue(fileManageDTO.getCreatedDate());
					makeSummaryReadOnly();
					detailsFlag = 'Y';
				} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
					final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(
							String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), fileType,
							String.valueOf(country.getValue()));
					changeReadOnlyState();
					forecastName.setValue(fileManageDTO.getForecastName());
					forecastVersion.setValue(fileManageDTO.getForecastVersion());
					createdDate.setValue(fileManageDTO.getCreatedDate());
					makeSummaryReadOnly();
					detailsFlag = 'Y';
				}

			}
		} catch (SystemException | Property.ReadOnlyException e) {
			LOGGER.error(e.getMessage());

		}

		LOGGER.debug("detailsButtonClickLogic method Ended");
	}

	/**
	 * Close button.
	 */
	public void closeButton() {
		LOGGER.debug("closeButton method started");

		close.addClickListener(new Button.ClickListener() {
			/**
			 * @ Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void buttonClick(final Button.ClickEvent event) {

				if (selectClose == true && saveflag == false) {
					MessageBox
							.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
									"Are you sure you want to close the File Lookup ?\n"
											+ " Any changes you have made will not be saved.",
									new MessageBoxListener() {
										/**
										 * Adds the button click listener.
										 *
										 *
										 */
										@SuppressWarnings("PMD")
                                    @Override
										public void buttonClicked(final ButtonId buttonId) {

											if (buttonId.name().equals(ConstantsUtils.YES)) {
												close();
											}
											if (buttonId.name().equals("NO")) {
											}
										}
									}, ButtonId.YES, ButtonId.NO);

				} else {
					close();
				}
			}
		});

		LOGGER.debug("closeButton method Ended");
	}

	/**
	 * Excel export.
	 */
	public void excelExport() {
		LOGGER.debug("excelExport method started");
		excelExportResult.setIcon(excelImage);
		excelExportResult.setStyleName("link");

		excelExportDetail.setIcon(excelImage);
		excelExportDetail.setStyleName("link");
		excelExportResult.setTabIndex(-1);
		excelExportDetail.setTabIndex(-1);
		excelExportResult.addClickListener(new ClickListener() {
			/**
			 * Method is called when available excel export button is clicked
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				LOGGER.debug("In configureFields excelExportResult.addClickListener started");

				try {
					ConsolidatedFinancialForecastUI.setEXCEL_CLOSE(true);
					configureExcelResultTable();
					loadExcelTable(resultDTO);
					ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), "File Management Results",
							"File Management Results", "FileManagementResults.xls", false);
					excel.export();
					tableLayout.removeComponent(excelTable);
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
				}
				LOGGER.debug("In configureFields excelExportResult.addClickListener Ended");
			}
		});
		LOGGER.debug("excelExport method Ended");
	}

	/**
	 * Select button.
	 */
	public void selectButton() {
		LOGGER.debug("selectButton method started");
		select.addClickListener(new Button.ClickListener() {
			/**
			 * Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void buttonClick(final Button.ClickEvent event) {
				LOGGER.debug("In select.addClickListener started");
				try {
					if (ConstantsUtils.EMPTY.equals(fileNameList.getValue()) || fileNameList.getValue() == null) {
						AbstractNotificationUtils.getErrorNotification("Select Error",
								"Please click on a record within the results list view");
					} else {
						final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						if (selectClose == true && saveflag == false) {
							MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
									"File/version has been updated but has not be saved. "
											+ ConstantsUtils.QUESTION_MARK,
									new MessageBoxListener() {
										/**
										 * Adds the button click listener.
										 *
										 *
										 */
										@SuppressWarnings("PMD")
                                            @Override
										public void buttonClicked(final ButtonId buttonId) {

											if (buttonId.name().equals(ConstantsUtils.YES)) {
												try {
													saveButtonLogic();
													searchContainer
															.getContainerProperty(itemId, StringConstantsUtil.FILE_NAME)
															.setValue(fileNameList.getValue());
													searchContainer.getContainerProperty(itemId, "activeFromDate")
															.setValue(df.format(fileMgtDTO.getFromDate()));
													searchContainer.getContainerProperty(itemId, "activeToDate")
															.setValue(df.format(fileMgtDTO.getToDate()));
													searchContainer.getContainerProperty(itemId, "fileManagementSid")
															.setValue(null);
													searchContainer.getContainerProperty(itemId, "fileChanged")
															.setValue(true);
													searchContainer
															.getContainerProperty(itemId, StringConstantsUtil.VERSION)
															.setValue(fileMgtDTO.getVersion());
													fileMgtIndexDTO.setVersion(fileMgtDTO.getVersion());
													fileMgtIndexDTO.setFileName(fileMgtDTO.getFileName());
													fileMgtIndexDTO.setFileType(fileMgtDTO.getFileType());
													fileMgtIndexDTO.setType(fileMgtDTO.getType());
													close();
												} catch (Property.ReadOnlyException ex) {
													java.util.logging.Logger
															.getLogger(FileManagementLookup.class.getName())
															.log(Level.SEVERE, null, ex);
												}
											}
											if (buttonId.name().equals("NO")) {
												close();
											}
										}
									}, ButtonId.YES, ButtonId.NO);

						} else {

							searchContainer.getContainerProperty(itemId, StringConstantsUtil.FILE_NAME)
									.setValue(fileNameList.getValue());
							searchContainer.getContainerProperty(itemId, "activeFromDate")
									.setValue(fileMgtDTO.getFromDate());
							searchContainer.getContainerProperty(itemId, "activeToDate")
									.setValue(fileMgtDTO.getToDate());
							searchContainer.getContainerProperty(itemId, "fileManagementSid").setValue(null);
							searchContainer.getContainerProperty(itemId, "fileChanged").setValue(true);
							searchContainer.getContainerProperty(itemId, StringConstantsUtil.VERSION)
									.setValue(fileMgtDTO.getVersion());
							selectFile.setValue(String.valueOf(fileNameList.getValue()));
							fileMgtIndexDTO.setVersion(fileMgtDTO.getVersion());
							fileMgtIndexDTO.setFileName(fileMgtDTO.getFileName());
							fileMgtIndexDTO.setFileType(fileMgtDTO.getFileType());
							fileMgtIndexDTO.setType(fileMgtDTO.getType());
							close();
						}
					}
				} catch (Property.ReadOnlyException e) {
					LOGGER.error(e.getMessage());
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
				}
				LOGGER.debug("In select.addClickListener Ended");
			}
		});

		LOGGER.debug("selectButton method Ended");
	}

	/**
	 * Reset button.
	 */
	private void resetButton() {
		LOGGER.debug("resetButton method started");

		reset.addClickListener(new Button.ClickListener() {
			/**
			 * Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void buttonClick(final Button.ClickEvent event) {
				LOGGER.debug("In reset.addClickListener started");
				MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
						"Are you sure you want to reset the page to default/previous values "
								+ ConstantsUtils.QUESTION_MARK,
						new MessageBoxListener() {
							/**
							 * Adds the button click listener.
							 *
							 *
							 */
							@SuppressWarnings("PMD")
                                @Override
							public void buttonClicked(final ButtonId buttonId) {
								if (buttonId.name().equals(ConstantsUtils.YES)) {
									try {
										fileName.setValue(ConstantsUtils.EMPTY);
										type.setValue(ConstantsUtils.EMPTY);
										version.setValue(ConstantsUtils.EMPTY);
										forecastYearCombo.select(helperDto);
										fromDate.setValue(null);
										toDate.setValue(null);
										SearchForecastddlb.select(ConstantsUtils.SELECT_ONE);
										country.select(ConstantsUtils.COUNTRY_US);
									} catch (Property.ReadOnlyException | Converter.ConversionException e) {
										LOGGER.error(e.getMessage());
										AbstractNotificationUtils.getErrorNotification(
												ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
												ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
									}
								}
							}
						}, ButtonId.YES, ButtonId.NO);

				LOGGER.debug("In reset.addClickListener Ended");
			}
		});

		LOGGER.debug("resetButton method Ended");
	}

	/**
	 * gets the null bean item.
	 *
	 * @return Null beanitem
	 */
	public BeanItem<?> getNULLITEM() {
		return NULLITEM;
	}

	/**
	 * To make summary fields read only.
	 */
	private void makeSummaryReadOnly() {
		LOGGER.debug("makeSummaryReadOnly method started");
		forecastName.setReadOnly(true);
		forecastVersion.setReadOnly(true);
		forecastDate.setReadOnly(true);
		createdDate.setReadOnly(true);
		LOGGER.debug("makeSummaryReadOnly method Ended");
	}

	/**
	 * To change the read-only state of summary fields.
	 */
	private void changeReadOnlyState() {
		LOGGER.debug("changeReadOnlyState method started");
		forecastName.setReadOnly(false);
		forecastVersion.setReadOnly(false);
		forecastDate.setReadOnly(false);
		createdDate.setReadOnly(false);
		LOGGER.debug("changeReadOnlyState method Ended");
	}

	/**
	 * Gets the bean from id.
	 *
	 * @param obj
	 *            the id
	 * @return the bean from id
	 */
	public FileMananagementResultDTO getBeanFromId(final Object obj) {
		LOGGER.debug("Entering getBeanFromId method");

		BeanItem<?> targetItem = null;
		if (obj instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) obj;
		} else if (obj instanceof FileMananagementResultDTO) {
			targetItem = new BeanItem<>((FileMananagementResultDTO) obj);
		}
		LOGGER.debug("End of getBeanFromId method");
		return (FileMananagementResultDTO) targetItem.getBean();
	}

	/**
	 * The Class DateValidator.
	 */
	@SuppressWarnings("rawtypes")
	private class DateValidator extends AbstractValidator {

		/**
		 * Instantiates a new date validator.
		 */
		public DateValidator() {
			super(ConstantsUtils.EMPTY);
		}

		/**
		 * Instantiates a new date validator.
		 *
		 * @param errorMessage
		 *            the error message
		 */
		public DateValidator(final String errorMessage) {
			super(errorMessage);
		}

		/**
		 * (non-Javadoc).
		 *
		 * @param value
		 *            the value
		 * @throws InvalidValueException
		 *             the invalid value exception
		 * @see com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
		 */
		@Override
		public void validate(final Object value) {
			LOGGER.debug("validate Method started");
			if (fromDate.getValue() != null && toDate.getValue() != null) {
				if (fromDate.getValue().after(toDate.getValue())) {
					toDate.setValue(null);
					AbstractNotificationUtils.getErrorNotification(SEARCH_ERROR,
							"Created Date To should be greater than Created Date From");

				} else if (fromDate.getValue().equals(toDate.getValue())) {
					toDate.setValue(null);
					AbstractNotificationUtils.getErrorNotification(SEARCH_ERROR,
							"Created Date To should be greater than Created Date From");
				}
			}
			LOGGER.debug("validate Method Ended");
		}

		/**
		 * (non-Javadoc).
		 *
		 * @param value
		 *            the value
		 * @return true, if is valid value
		 * @see com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
		 */
		@Override
		protected boolean isValidValue(final Object value) {
			LOGGER.debug("isValidValue Method started");
			boolean flag;
			if (fromDate.getValue() == null || toDate.getValue() == null) {
				flag = true;
			} else {
				flag = fromDate.getValue().compareTo(toDate.getValue()) <= 0;
			}
			LOGGER.debug("isValidValue Method returns true");
			return flag;
		}

		/**
		 * (non-Javadoc).
		 *
		 * @return the type
		 * @see com.vaadin.data.validator.AbstractValidator#getType()
		 */
		@Override
		public Class getType() {
			return null;
		}
	}

	public void saveButtonLogic() {
		LOGGER.debug("Enters Inside Save Button Logic");
		Boolean changeFlag = false;
		final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
		final List<FileMananagementResultDTO> insertionItemIds = new ArrayList<>();
		List<Integer> currentSystemId = new ArrayList<>();
		try {
			for (int i = 0; i < itemIds.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIds.get(i);
				String year = beanItem.getYear();
				String month = beanItem.getMonth();
				String itemName = beanItem.getItemName();
				String forecastType = beanItem.getForecastType();
				String forecastYear = beanItem.getForcastYear();
				String forecastMonth = beanItem.getForecastMonth();
				String companyId = beanItem.getCompanyId();
				String vItemId = beanItem.getItemId();
				String organisationKey = beanItem.getOrganizationKey();
				String batchId = beanItem.getBatchId();
				String week = beanItem.getWeek();
				String day = beanItem.getDay();
				for (int j = i + 1; j < itemIds.size(); j++) {
					final FileMananagementResultDTO dto = itemIds.get(j);
					String currentYear = dto.getYear();
					String currentMonth = dto.getMonth();
					String currentItemName = dto.getItemName();
					String currentForecastType = dto.getForecastType();
					String currentForecastYear = dto.getForcastYear();
					String currentForecastMonth = dto.getForecastMonth();
					String currentCompanyId = dto.getCompanyId();
					String currentItemId = dto.getItemId();
					String currentOrganisationKey = dto.getOrganizationKey();
					String currentBatchId = dto.getBatchId();
					String currentWeek = dto.getWeek();
					String currentDay = dto.getDay();
					if (year.equals(currentYear) && (fileType.equals(ConstantsUtils.EX_FACTORY_SALES))
							&& month.equals(currentMonth) && itemName.equals(currentItemName)) {
						AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
								UNIQUE_COMBINATION_ERROR);
						return;
					}
					if ((currentForecastType.equals(forecastType) && ((fileType.equals(ConstantsUtils.DEMAND))
							|| (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND))))
							&& currentForecastYear.equals(forecastYear) && currentForecastMonth.equals(forecastMonth)
							&& currentItemId.equals(vItemId) && currentOrganisationKey.equals(organisationKey)) {
						AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
								UNIQUE_COMBINATION_ERROR);
						return;
					}
					if (currentYear.equals(forecastType) && fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
							&& currentMonth.equals(month) && currentDay.equals(day) && currentWeek.equals(week)
							&& currentItemId.equals(vItemId) && currentBatchId.equals(batchId)
							&& currentOrganisationKey.equals(organisationKey)) {
						AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
								UNIQUE_COMBINATION_ERROR);
						return;
					}
					if (currentYear.equals(forecastType) && fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)
							&& currentMonth.equals(month) && currentDay.equals(day) && currentWeek.equals(week)
							&& currentCompanyId.equals(companyId) && currentItemId.equals(vItemId)
							&& currentBatchId.equals(batchId) && currentOrganisationKey.equals(organisationKey)) {
						AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
								UNIQUE_COMBINATION_ERROR);
						return;
					}
				}
			}

			for (int i = 0; i < itemIds.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIds.get(i);
				if (!beanItem.isRecordLockStatus()) {

					if (((beanItem.getYear().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getMonth().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getUnits().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getPrice().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getDollars().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getItemNo().equals(ConstantsUtils.EMPTY))
							|| (beanItem.getItemName().equals(ConstantsUtils.EMPTY)))
							&& fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
						AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
								"Please Enter the value at all fields");
						return;
					}
					currentSystemId.add(beanItem.getForecastSystemId());
					if (beanItem.getForecastSystemId() == 0 && (fileType.equals(ConstantsUtils.DEMAND)
							|| fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)
							|| fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
							|| fileType.equals(ConstantsUtils.CUSTOMERGTS)
							|| fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL))) {
						insertionItemIds.add(beanItem);
					}
				}
			}
			if (currentSystemId.contains(0)) {
				changeFlag = true;
			}
			DynamicQuery dynamicQuery;

			final HashMap savedForecast = new HashMap();
			List<Integer> existingSystemId = new ArrayList<>();

			if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
				dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
				dynamicQuery
						.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORECAST_YEAR, String.valueOf(selectedYear)));
				dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
				dynamicQuery.add(
						RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME,
						fileNameList.getValue().toString()));
				List<ForecastingMaster> listToRemove = ForecastingMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
				for (final Iterator<ForecastingMaster> iterator = listToRemove.iterator(); iterator.hasNext();) {
					final ForecastingMaster itemDetail = iterator.next();
					existingSystemId.add(itemDetail.getForecastMasterSid());
					savedForecast.put(itemDetail.getForecastMasterSid(), itemDetail);
				}
			} else if (fileType.equals(ConstantsUtils.DEMAND)) {
				dynamicQuery = DemandForecastLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
				dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
				dynamicQuery.add(
						RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME,
						fileNameList.getValue().toString()));
				List<DemandForecast> listToRemove = DemandForecastLocalServiceUtil.dynamicQuery(dynamicQuery);
				for (final Iterator<DemandForecast> iterator = listToRemove.iterator(); iterator.hasNext();) {
					final DemandForecast itemDetail = iterator.next();
					existingSystemId.add(itemDetail.getDemandForecastSid());
					savedForecast.put(itemDetail.getDemandForecastSid(), itemDetail);
				}
			} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
				dynamicQuery = AdjustedDemandForecastLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
				dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
				dynamicQuery.add(
						RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME,
						fileNameList.getValue().toString()));
				List<AdjustedDemandForecast> listToRemove = AdjustedDemandForecastLocalServiceUtil
						.dynamicQuery(dynamicQuery);
				for (final Iterator<AdjustedDemandForecast> iterator = listToRemove.iterator(); iterator.hasNext();) {
					final AdjustedDemandForecast itemDetail = iterator.next();
					existingSystemId.add(itemDetail.getAdjustedDemandForecastSid());
					savedForecast.put(itemDetail.getAdjustedDemandForecastSid(), itemDetail);
				}
			} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
				List<String> query = new ArrayList<>();
				query.add(selectedFile);
				query.add(country.getValue().toString());
				query.add(versionList.getValue().toString());
				query.add(fileNameList.getValue().toString());
				List<FileMananagementResultDTO> list = FileManagementLogic.getInventoryDemandValidationQuery(query);
				for (final Iterator<FileMananagementResultDTO> iterator = list.iterator(); iterator.hasNext();) {
					final FileMananagementResultDTO itemDetail = iterator.next();
					existingSystemId.add(itemDetail.getInventoryForecastDetailsSysId());
					savedForecast.put(itemDetail.getInventoryForecastDetailsSysId(), itemDetail);

				}
			} else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
				dynamicQuery = CustomerGtsForecastLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
				dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
				dynamicQuery.add(
						RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME,
						fileNameList.getValue().toString()));
				List<CustomerGtsForecast> listToRemove = CustomerGtsForecastLocalServiceUtil.dynamicQuery(dynamicQuery);
				for (final Iterator<CustomerGtsForecast> iterator = listToRemove.iterator(); iterator.hasNext();) {
					final CustomerGtsForecast itemDetail = iterator.next();
					existingSystemId.add(itemDetail.getCustomerGtsForecastSid());
					savedForecast.put(itemDetail.getCustomerGtsForecastSid(), itemDetail);
				}
			}

			if (!changeFlag) {
				Collections.sort(existingSystemId);
				Collections.sort(currentSystemId);
				if (existingSystemId.equals(currentSystemId)) {
					for (int i = 0; i < itemIds.size(); i++) {
						final FileMananagementResultDTO beanItem = itemIds.get(i);
						if (!beanItem.isRecordLockStatus()) {
							if ((beanItem.getPrice().equals(beanItem.getHiddenPrice())
									&& beanItem.getUnits().equals(beanItem.getHiddenUnits()))
									&& fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
							} else if (beanItem.getForecastType().equals(beanItem.getHiddenForecastType())
									&& beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
									&& beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
									&& beanItem.getItemId().equals(beanItem.getHiddenItemId())
									&& beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
									&& fileType.equals(ConstantsUtils.DEMAND)) {

							} else if (beanItem.getForecastType().equals(beanItem.getHiddenForecastType())
									&& beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
									&& beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
									&& beanItem.getItemId().equals(beanItem.getHiddenItemId())
									&& beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
									&& fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {

							} else if (beanItem.getYear().equals(beanItem.getHiddenYear())
									&& beanItem.getMonth().equals(beanItem.getHiddenMonth())
									&& beanItem.getDay().equals(beanItem.getHiddenDay())
									&& beanItem.getWeek().equals(beanItem.getHiddenWeek())
									&& beanItem.getItemId().equals(beanItem.getHiddenItemId())
									&& beanItem.getBatchId().equals(beanItem.getHiddenbatchId())
									&& beanItem.getOrganizationKey().equals(beanItem.getHiddenOrganisationKey())
									&& fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {

							} else if (beanItem.getYear().equals(beanItem.getHiddenYear())
									&& beanItem.getMonth().equals(beanItem.getHiddenMonth())
									&& beanItem.getDay().equals(beanItem.getHiddenDay())
									&& beanItem.getWeek().equals(beanItem.getHiddenWeek())
									&& beanItem.getCompanyId().equals(beanItem.getHiddenCompanyId())
									&& beanItem.getItemId().equals(beanItem.getHiddenItemId())
									&& beanItem.getBatchId().equals(beanItem.getHiddenbatchId())
									&& beanItem.getOrganizationKey().equals(beanItem.getHiddenOrganisationKey())
									&& fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {

							} else if (beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
									&& beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
									&& beanItem.getItemId().equals(beanItem.getHiddenItemId())
									&& beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
									&& ((HelperDTO) fmFileType.getValue()).getDescription()
											.equals(ConstantsUtils.CUSTOMERGTS)) {

							} else {
								changeFlag = true;
							}
						}
					}
				} else {
					changeFlag = true;
				}
			}

			if (changeFlag) {
				MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
						"Save record " + forecastName.getValue() + "?", new MessageBoxListener() {
                                                        @Override
							public void buttonClicked(ButtonId buttonId) {
								if (buttonId.name().equals(ConstantsUtils.YES)) {
									String finalVersion;
									if (versionList.getValue().contains(".")) {
										String[] array = versionList.getValue().split("\\.");
										int minorVersion = Integer.parseInt(array[1]);
										minorVersion = minorVersion + 1;
										String s1 = String.valueOf(minorVersion);
										finalVersion = array[0] + "." + s1;
									} else {
										finalVersion = versionList.getValue().toString();
										finalVersion = finalVersion + "." + "1";
									}
									FileManagementLogic logic = new FileManagementLogic();
									try {
										String msg = "fail";
										if (fileType.equals(ConstantsUtils.DEMAND)
												|| fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)
												|| fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
												|| fileType.equals(ConstantsUtils.CUSTOMERGTS)
												|| fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
											msg = logic.saveForecastDetails(insertionItemIds, selectedFile,
													selectedFileCountry, finalVersion, fileNameList.getValue(),
													fileType);
										} else if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
											msg = logic.saveForecastDetails(itemIds, selectedFile, selectedFileCountry,
													finalVersion, fileNameList.getValue(), fileType);
										}

										if (msg.equals("success")) {
											saveflag = true;
											Notification.show("Records saved Successfully");
											addlineList.clear();
											try {
												resultDTO.setFileType(String.valueOf(vFileType.getValue()));
												resultDTO.setCountry(String.valueOf(country.getValue()));
												resultDTO.setFileName(String.valueOf(fileName.getValue()));
												resultDTO.setType(String.valueOf(type.getValue().trim()));
												resultDTO.setForecastYear("");
												resultDTO.setFromPeriod(fromDate.getValue());
												resultDTO.setToPeriod(toDate.getValue());
												resultDTO.setVersion(String.valueOf(version.getValue().trim()));
												loadResultsTable();

											} catch (Exception ex) {
												LOGGER.error(ex.getMessage());
											}
										}
									} catch (SystemException ex) {
										LOGGER.error(ex.getMessage());
									}
								}
							}
						}, ButtonId.YES, ButtonId.NO);
			} else {
				AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.DETAILS_ERROR,
						"No changed has done From Source version");
				return;
			}
			LOGGER.debug("Ending Save Button Logic");
		} catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * setDefaultTableWidth
	 *
	 * @param table
	 * @param container
	 */
	public void setDefaultTableWidth(final ExtFilterTable table,
			final BeanItemContainer<FileMananagementResultDTO> container) {

		try {
			table.setColumnCollapsingAllowed(true);
			int browserWidth = Page.getCurrent().getBrowserWindowWidth();
			if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {

				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}
				for (Object propertyId : getSixColumns(table)) {
					table.setColumnCollapsed(propertyId, true);
				}

			} else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX
					&& browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}
				for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
					table.setColumnCollapsed(propertyId, true);
				}
			} else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT
					&& browserWidth > NumericConstants.SIX_HUNDRED) {
				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}

				if (container != null && container.getItemIds().isEmpty()) {
					for (Object propertyId : getCollapsibleColumns978Px(table)) {
						table.setColumnCollapsed(propertyId, true);
					}
				} else {
					for (Object propertyId : getCollapsibleColumns600Px(table)) {
						table.setColumnCollapsed(propertyId, true);
					}
				}
			} else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}
				for (Object propertyId : getCollapsibleColumns600Px(table)) {
					table.setColumnCollapsed(propertyId, true);
				}
			} else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO
					&& browserWidth > NumericConstants.THREE_TWO_ZERO) {
				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}
				for (Object propertyId : getCollapsibleColumns480Px(table)) {
					table.setColumnCollapsed(propertyId, true);
				}
			} else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
				for (Object propertyId : table.getVisibleColumns()) {
					table.setColumnCollapsed(propertyId, false);
				}
				for (Object propertyId : getCollapsibleColumns480Px(table)) {
					table.setColumnCollapsed(propertyId, true);
				}
			}
		} catch (IllegalStateException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * getSixColumns
	 *
	 * @param table
	 * @return object
	 */
	private static Object[] getSixColumns(ExtFilterTable table) {
		table.setColumnCollapsingAllowed(true);
		table.setImmediate(true);
		Object[] visibleColumns = table.getVisibleColumns();
		Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
		List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
		for (int i = 0; i < NumericConstants.SIX; i++) {
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
	 * Gets the collapsible columns600 px.
	 *
	 * @param table
	 *            the table
	 * @return the collapsible columns600 px
	 */
	private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
		final Object[] visibleColumns = table.getVisibleColumns();
		String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
		final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

		list.remove(propertyIds[0]);
		list.remove(propertyIds[1]);
		propertyIds = list.toArray(new String[list.size()]);
		return propertyIds;
	}

	/**
	 * Gets the collapsible columns480 px.
	 *
	 * @param table
	 *            the table
	 * @return the collapsible columns480 px
	 */
	private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
		final Object[] visibleColumns = table.getVisibleColumns();
		String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
		final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

		list.remove(propertyIds[0]);
		list.remove(propertyIds[1]);
		propertyIds = list.toArray(new String[list.size()]);
		return propertyIds;
	}

	/**
	 * Gets the collapsible columns978 px.
	 *
	 * @param table
	 *            the table
	 * @return the collapsible columns978 px
	 */
	private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
		final Object[] visibleColumns = table.getVisibleColumns();
		String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
		final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

		list.remove(propertyIds[0]);
		list.remove(propertyIds[1]);
		list.remove(propertyIds[NumericConstants.TWO]);
		list.remove(propertyIds[NumericConstants.THREE]);
		propertyIds = list.toArray(new String[list.size()]);
		return propertyIds;
	}

	/**
	 * Gets the collapsible columns default1515 px.
	 *
	 * @param table
	 *            the table
	 * @return the collapsible columns default1515 px
	 */
	private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
		final Object[] visibleColumns = table.getVisibleColumns();
		String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
		final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

		list.remove(propertyIds[0]);
		list.remove(propertyIds[1]);
		list.remove(propertyIds[NumericConstants.TWO]);
		list.remove(propertyIds[NumericConstants.THREE]);
		propertyIds = list.toArray(new String[list.size()]);
		return propertyIds;
	}

	private void configureTable() {
		tableLayout.addComponent(resultsFilterTable);
		controlLayout = tableLogic.createControls();
		controlLayout = CommonLogic.getResponsiveControls(controlLayout);
		tableLayout.addComponent(controlLayout);

		tableLogic.setContainerDataSource(resultsBean);
		tableLogic.setPageLength(NumericConstants.TEN);
		tableLogic.sinkItemPerPageWithPageLength(false);
		setTableDefaultConfig(resultsFilterTable);
		resultsFilterTable.setSelectable(true);
		resultsFilterTable.markAsDirty();
		resultsFilterTable.setComponentError(null);
		resultsFilterTable.setFilterBarVisible(true);
		resultsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
		resultsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
		resultsFilterTable.setValidationVisible(false);
		resultsFilterTable.addStyleName("filterbar");

		resultsFilterTable.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed .
			 *
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("In resultsTable resultsItemClick started");
				try {
					resultsItemClick(event.getProperty().getValue());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
				}
				LOGGER.debug("In resultsTable resultsItemClick Ended");
			}
		});
		resultsFilterTable.setErrorHandler(new ErrorHandler() {
			/**
			 * * Invoked when an error occurs.
			 */
                        @Override
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty method
			}
		});
	}

	public void setTableDefaultConfig(ExtPagedTable resultsTable) {
		resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupResColsList());
		resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupResHeaderList());
		resultsTable.markAsDirtyRecursive();
		resultsTable.setImmediate(true);
		resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
		resultsTable.setHeight("450px");
		resultsTable.setColumnAlignment(StringConstantsUtil.VERSION, ExtCustomTable.Align.CENTER);
		resultsTable.setColumnAlignment(StringConstantsUtil.FROM_DATE, ExtCustomTable.Align.CENTER);
		resultsTable.setColumnAlignment(StringConstantsUtil.TO_DATE, ExtCustomTable.Align.CENTER);
		resultsTable.setColumnWidth(StringConstantsUtil.FROM_DATE, NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth(StringConstantsUtil.TO_DATE, NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth("fileType", NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth(StringConstantsUtil.COUNTRY, NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth(StringConstantsUtil.FILE_NAME, NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth("type", NumericConstants.ONE_NINE_EIGHT);
		resultsTable.setColumnWidth(StringConstantsUtil.VERSION, NumericConstants.ONE_NINE_EIGHT);
	}

	private void configureDetailsTable() {
		try {
			detailsTable.addComponent(detailsFilterTable);
			detailsControlLayout = detailstableLogic.createControls();
			controlLayout = CommonLogic.getResponsiveControls(detailsControlLayout);
			detailsTable.addComponent(controlLayout);
			detailstableLogic.setContainerDataSource(detailsBean);
			detailstableLogic.setPageLength(NumericConstants.FIFTEEN);
			detailstableLogic.sinkItemPerPageWithPageLength(false);
			setDetailsTableDefaultConfig(fileType, detailsFilterTable);
			detailsFilterTable.markAsDirty();
			detailsFilterTable.setComponentError(null);
			detailsFilterTable.setFilterBarVisible(true);
			detailsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
			detailsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
			detailsFilterTable.setValidationVisible(false);
			detailsFilterTable.addStyleName("filterbar");
			detailsFilterTable.setEditable(true);
			detailsFilterTable.setSelectable(true);

			fileselectiondto.getHelperfileType();
			if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MONTH_PROPERTY, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("year", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("itemNo", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("itemName", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("startDate", ExtCustomTable.Align.CENTER);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.PRICE_PROPERTY, ExtCustomTable.Align.RIGHT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.UNITS_PROPERTY, ExtCustomTable.Align.RIGHT);
				detailsFilterTable.setColumnAlignment("dollars", ExtCustomTable.Align.RIGHT);

				detailsFilterTable.setConverter("startDate", new DateToStringConverter());

			} else if (fileType.toString().equals(ConstantsUtils.DEMAND)) {
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.FORECAST_TYPE, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("forecastYear", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.FORECAST_MONTH, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ITEM_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ITEM_IDENTIFIER, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.BRAND_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.SEGMENT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SIZE_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SHARE_RATIO,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SHARE_UNITS,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.UNCAPTURED_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.UNCAPTURED_UNITS_RATIO,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.TOTAL_DEMAND_UNITS,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.TOTAL_DEMAND_AMOUNT,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.INVENTORY_UNIT_CHANGE,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_PRICE, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_AMOUNT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.NET_SALES_PRICE, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.NET_SALES_AMOUNT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.BATCH_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.SOURCE_PROPERTY, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.FORECAST_NAME, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.FORECAST_VERSION, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.COUNTRY, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ORGANIZATION_KEY, ExtCustomTable.Align.LEFT);
			} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {

				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ITEM_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("itemName", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.BRAND_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment("brandName", ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.SEGMENT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SIZE_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SHARE_RATIO,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.MARKET_SHARE_UNITS,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.UNCAPTURED_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.UNCAPTURED_UNITS_RATIO,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.TOTAL_DEMAND_UNITS,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.TOTAL_DEMAND_AMOUNT,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.INVENTORY_UNIT_CHANGE,
						ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_UNITS, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_PRICE, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.GROSS_AMOUNT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.NET_SALES_PRICE, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.NET_SALES_AMOUNT, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.BATCH_ID, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.SOURCE_PROPERTY, ExtCustomTable.Align.LEFT);
				detailsFilterTable.setColumnAlignment(StringConstantsUtil.ORGANIZATION_KEY, ExtCustomTable.Align.LEFT);

			}
			if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
				exFactoryFieldFactory();
			} else if (fileType.equals(ConstantsUtils.DEMAND) || fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
				demandFieldFactory();
			} else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
				customerFieldFactory();
			} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
					|| fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
				inventoryFieldFactory(fileselectiondto.getFileType());
			}
			detailsFilterTable.setErrorHandler(new ErrorHandler() {
				/**
				 * Invoked when an error occurs .
				 *
				 */
				@SuppressWarnings("PMD")
                                @Override
				public void error(final com.vaadin.server.ErrorEvent event) {
					// empty method
				}
			});

			final Object[] obj = new Object[] { ConstantsUtils.PRICE, ConstantsUtils.UNITS, ConstantsUtils.DOLLARS };
			for (int i = 0; i < obj.length; i++) {
				detailsFilterTable.setColumnAlignment(obj[i], ExtFilterTable.Align.RIGHT);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void setDetailsTableDefaultConfig(String fileType, ExtPagedTable resultsTable) {
		try {
			resultsTable.removeAllItems();
			if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupDetailsColsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupDetailsHeaderList());
			} else if (fileType.equals(ConstantsUtils.DEMAND)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupDemandDetailsColsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupDemandDetailsHeaderList());
			} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupInvDetailsSummaryColsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupInvDetailsSummaryHeaderList());
			} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupAdjDemandDetailsColsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupInvDetailsHeaderList());
			} else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupCustomerColumnsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupCusHeaderList());
			} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
				resultsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupAdjDemandDetailsColsList());
				resultsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupAdjDemandDetailsHeaderList());
			}
			resultsTable.markAsDirtyRecursive();
			resultsTable.setImmediate(true);
			resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
			resultsTable.setHeight("579px");
			resultsTable.setColumnAlignment(StringConstantsUtil.VERSION, ExtCustomTable.Align.CENTER);

		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(FileManagementLookup.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	void loadResultsTable() {
		LOGGER.debug("Entering Load Results Table");
		resultsFilterTable.addStyleName(Constants.FILTER_TABLE);
		resultsFilterTable.addStyleName("table-header-normal");
		resultsFilterTable.setConverter(StringConstantsUtil.FROM_DATE, new DateToStringConverter());
		resultsFilterTable.setConverter(StringConstantsUtil.TO_DATE, new DateToStringConverter());
		resultDTO.setBusinessUnit(businessUnit);
		resultDTO.setCompany(sessionDTO.getCompanySystemId());
		tableLogic.configureSearchData(resultDTO);
		resultsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
		resultsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
		resultsFilterTable.setImmediate(true);
		resultsFilterTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
		resultsFilterTable.addStyleName("TableCheckBox");
		resultsFilterTable.setSelectable(true);
		resultsFilterTable.markAsDirtyRecursive();
		LOGGER.debug("Ending Load Results Table");

	}

	/**
	 * To configure Excel Results Table
	 */
	private void configureExcelResultTable() {
		excelTableBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
		excelTable = new ExtFilterTable();
		tableLayout.addComponent(excelTable);
		excelTable.setVisible(false);
		excelTable.setContainerDataSource(excelTableBean);
		excelTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupResColsList());
		excelTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupResHeaderList());
		excelTable.markAsDirtyRecursive();
	}

	/**
	 * To load excel Table similar to Table in UI
	 *
	 * @param tableFieldLookUpDTO
	 * @throws Exception
	 */
	private void loadExcelTable(FileMananagementResultDTO fileObject) {
		excelTableBean.removeAllItems();
		if (resultsFilterTable.size() != 0) {
			FileMananagementResultDTO excelDTO = fileObject;
			int recordCount = (Integer) vFileMgmtLogic.getFileResults(excelDTO, 0, 0, null, null, true);
			List<FileMananagementResultDTO> resultList = (List<FileMananagementResultDTO>) vFileMgmtLogic
					.getFileResults(excelDTO, 0, recordCount, null, null, false);
			excelTableBean.addAll(resultList);

		}
	}

	/**
	 * To configure Excel Details Results Table
	 */
	private void configureExcelDetailsTable() {
		LOGGER.debug("Configure ExcelDetailsTable Starts---==============================================");

		excelDetailsBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
		excelDetailsTable = new ExtFilterTable();

		detailsTable.addComponent(excelDetailsTable);

		excelDetailsTable.setVisible(false);
		excelDetailsTable.setContainerDataSource(excelDetailsBean);

		if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
			excelDetailsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupDetailsColsExcelList());
			excelDetailsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupDemandDetailsHeaderExcelList());
		} else if (fileType.equals(ConstantsUtils.DEMAND)) {
			excelDetailsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupDemandDetailsColsExcelList());
			excelDetailsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupDemandDetailsHeaderExcelList());
		} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			excelDetailsTable.setVisibleColumns(CommonUIUtil.getFileMgmtInvDetailsSumColsExcelList());
			excelDetailsTable
					.setColumnHeaders(CommonUIUtil.getFileMgmtLookupInvDetailsSumHeaderList());
		} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			excelDetailsTable
					.setVisibleColumns(CommonUIUtil.getFileMgmtLookupInvDetailsColsExcelList());
			excelDetailsTable
					.setColumnHeaders(CommonUIUtil.getFileMgmtLookupInvDetailsHeaderExcelList());
		} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			excelDetailsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupAdjDemandDetailsColsList());
			excelDetailsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupAdjDemandDetailsHeaderList());
		} else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
			excelDetailsTable.setVisibleColumns(CommonUIUtil.getFileMgmtLookupCustomerColumnsList());
			excelDetailsTable.setColumnHeaders(CommonUIUtil.getFileMgmtLookupCusHeaderList());
		}
		excelDetailsTable.markAsDirtyRecursive();
		LOGGER.debug("Configure ExcelDetailsTable ends");
	}

	/**
	 * To load excel Table similar to Details Table in UI
	 *
	 * @param tableFieldLookUpDTO
	 * @throws Exception
	 */
	private void loadExcelDetailsTable(Object fileObject) throws ParseException {
		excelDetailsBean.removeAllItems();
		if (detailsFilterTable.size() != 0) {
			FileMananagementResultDTO detailsDTO = (FileMananagementResultDTO) fileObject;
			int recordCount = (Integer) vFileMgmtLogic.getDetailsResults(detailsDTO, 0, 0, null, null, true);
			List<FileMananagementResultDTO> resultList = (List<FileMananagementResultDTO>) vFileMgmtLogic
					.getDetailsResults(detailsDTO, 0, recordCount, null, null, false);
			excelDetailsBean.addAll(resultList);
		}
	}

	public void exFactoryFieldFactory() {
		detailsFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
			/**
			 * To create editable fields inside table .
			 */
                        @Override
			public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
					final Component uiContext) {
				final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
				final Boolean flag = dto.isRecordLockStatus();
				final String interfaceFlag = dto.getInterfaceFlag();
				if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
					if (flag) {
						if (propertyId.equals(ConstantsUtils.UNITS)) {
							final TextField unit = new TextField();
							unit.setConverter(unitsFormat);
							unit.setReadOnly(true);
							unit.setImmediate(true);
							return unit;
						}
						if (propertyId.equals(ConstantsUtils.PRICE)) {
							final TextField price = new TextField();
							price.setConverter(priceFormat);
							price.setReadOnly(true);
							price.setImmediate(true);
							return price;
						}
						if (propertyId.equals(ConstantsUtils.DOLLARS)) {
							final TextField dollar = new TextField();
							dollar.setConverter(dollarsFormat);
							dollar.setReadOnly(true);
							dollar.setImmediate(true);
							return dollar;
						}
					} else {
						if (propertyId.equals(ConstantsUtils.UNITS)) {
							final TextField unit = new TextField();
							unit.setImmediate(true);
							unit.addBlurListener(new BlurListener() {
                                            @Override
								public void blur(BlurEvent event) {
									LOGGER.debug("In configureFields levelValueReference.addBlurListener started ");

									String unitValue = unit.getValue();
									if (!unitValue.matches(ValidationUtils.PRICE)) {
										AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
												StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
										unit.setValue(ConstantsUtils.EMPTY);
										return;
									}
									String priceValue = detailsFilterTable
											.getContainerProperty(itemId, ConstantsUtils.PRICE).getValue().toString()
											.replace("$", ConstantsUtils.EMPTY);
									if (priceValue.length() == 0) {
										AbstractNotificationUtils.getErrorNotification("Remove Error",
												"Please Enter Price Value");
										return;
									}
									String unitsValue = detailsFilterTable
											.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
									Double dollarValue = Double.parseDouble(priceValue)
											* Double.parseDouble(unitsValue);
									detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS)
											.setValue(String.valueOf(dollarValue));
								}
							});
							unit.setConverter(unitsFormat);
							return unit;
						}
						if (propertyId.equals(ConstantsUtils.PRICE)) {
							final TextField price = new TextField();
							price.setImmediate(true);
							price.addBlurListener(new BlurListener() {
                                            @Override
								public void blur(BlurEvent event) {

									LOGGER.debug(
											"In configureFields levelValueReference.addValueChangeListener started");
									String priceValue = price.getValue();
									String priceWithoutDollar = priceValue.replace("$", ConstantsUtils.EMPTY);
									if (!priceWithoutDollar.matches(ValidationUtils.PRICE)) {
										AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
												StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
										price.setValue(ConstantsUtils.EMPTY);
										return;
									}

									String unitsValue = detailsFilterTable
											.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
									String uniWithoutDollar = unitsValue.replace("$", ConstantsUtils.EMPTY);
									if (uniWithoutDollar.length() == 0) {
										uniWithoutDollar = "0";
									}
									Double dollarValue = Double.parseDouble(priceWithoutDollar)
											* Double.parseDouble(uniWithoutDollar);
									detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS)
											.setValue(String.valueOf(dollarValue));
								}
							});
							price.setConverter(priceFormat);
							return price;
						}
						if (propertyId.equals(ConstantsUtils.DOLLARS)) {
							final TextField dollars = new TextField();
							dollars.setConverter(dollarsFormat);
							dollars.setReadOnly(true);
							dollars.setImmediate(true);
							return dollars;
						}
					}
				} else {
					if (propertyId.equals(ConstantsUtils.UNITS)) {
						final TextField unit = new TextField();
						unit.setImmediate(true);
						unit.addBlurListener(new BlurListener() {
                                        @Override
							public void blur(BlurEvent event) {
								LOGGER.debug("In configureFields levelValueReference.addBlurListener  started");

								String unitValue = unit.getValue();
								if (!unitValue.matches(ValidationUtils.PRICE)) {
									AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
											StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
									unit.setValue(ConstantsUtils.EMPTY);
									return;
								}
								String priceValue = detailsFilterTable
										.getContainerProperty(itemId, ConstantsUtils.PRICE).getValue().toString()
										.replace("$", ConstantsUtils.EMPTY);
								if (priceValue.length() == 0) {
									AbstractNotificationUtils.getErrorNotification("Remove Error",
											"Please Enter Price Value");
									return;
								}
								String unitsValue = detailsFilterTable
										.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
								Double dollarValue = Double.parseDouble(priceValue) * Double.parseDouble(unitsValue);
								detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS)
										.setValue(String.valueOf(dollarValue));
							}
						});
						unit.setConverter(unitsFormat);
						return unit;
					}
					if (propertyId.equals(ConstantsUtils.PRICE)) {
						final TextField price = new TextField();
						price.setImmediate(true);
						price.addBlurListener(new BlurListener() {
                                        @Override
							public void blur(BlurEvent event) {
								LOGGER.debug("In configureFields  levelValueReference.addBlurListener started");

								String priceValue = price.getValue();
								String priceWithoutDollar = priceValue.replace("$", ConstantsUtils.EMPTY);
								if (!priceWithoutDollar.matches(ValidationUtils.PRICE)) {
									AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
											StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
									price.setValue(ConstantsUtils.EMPTY);
									return;
								}
								String unitsValue = detailsFilterTable
										.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
								String uniWithoutDollar = unitsValue.replace("$", ConstantsUtils.EMPTY);
								if (uniWithoutDollar.length() == 0) {
									uniWithoutDollar = "0";
								}
								Double dollarValue = Double.parseDouble(priceValue)
										* Double.parseDouble(uniWithoutDollar);
								detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS)
										.setValue(String.valueOf(dollarValue));

							}
						});
						price.setConverter(priceFormat);
						return price;
					}
					if (propertyId.equals(ConstantsUtils.YEAR)) {

						final TextField year1 = new TextField();
						year1.setImmediate(true);
						year1.addBlurListener(new BlurListener() {
                                        @Override
							public void blur(BlurEvent event) {
								LOGGER.debug("In configureFields levelValueReference.addBlurListener started");

								String year = year1.getValue();
								year = year.replace("$", ConstantsUtils.EMPTY);
								if (!year.matches(ValidationUtils.NUMERIC_VALIDATION)) {
									AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
											StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
									year1.setValue(ConstantsUtils.EMPTY);
									return;
								}

							}
						});
						return year1;
					}
					if (propertyId.equals(ConstantsUtils.MONTH)) {
						final TextField month = new TextField();
						month.setImmediate(true);
						month.addBlurListener(new BlurListener() {
                                        @Override
							public void blur(BlurEvent event) {
								LOGGER.debug("In configureFields levelValueReference.addBlurListener started");

								String enteredMonth = month.getValue();
								enteredMonth = enteredMonth.replace("$", ConstantsUtils.EMPTY);
								if (!enteredMonth.matches(ValidationUtils.NUMERIC_VALIDATION)) {
									AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
											StringConstantsUtil.ONLY_NUMBERS_ARE_ALLOWED);
									month.setValue(ConstantsUtils.EMPTY);
									return;
								} else {
									int forecastMonth = Integer.parseInt(enteredMonth);
									if (forecastMonth > NumericConstants.TWELVE || forecastMonth == 0) {
										AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR,
												"Please Enter valid Month");
										month.setValue(ConstantsUtils.EMPTY);
										return;
									}
								}
							}
						});
						return month;
					}
					if (propertyId.equals(ConstantsUtils.ITEM_NAME)) {

						final CustomTextField itemName = new CustomTextField();
						itemName.setImmediate(true);
						itemName.addStyleName("searchText-nonabsolute");
						return itemName;
					}
					if (propertyId.equals(ConstantsUtils.ITEM_NO)) {
						final CustomTextField itemNo = new CustomTextField();
						itemNo.setImmediate(true);
						itemNo.addStyleName("searchText-nonabsolute");
						itemNo.addClickListener(new CustomTextField.ClickListener() {
							/**
							 * Called when a Button has been clicked .
							 *
							 */
							@SuppressWarnings("PMD")
                                        @Override
							public void click(CustomTextField.ClickEvent event) {
								final ItemSearchLookup lookUp = new ItemSearchLookup(itemNo, lookupItemName);
								try {
									lookUp.init();
								} catch (PortalException | SystemException ex) {
									LOGGER.error(ex.getMessage());
								}
								UI.getCurrent().addWindow(lookUp);
								lookUp.addCloseListener(new Window.CloseListener() {
                                                @Override
									public void windowClose(final Window.CloseEvent e) {
										detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.ITEM_NAME)
												.setValue(lookupItemName.getValue().toString());
									}
								});
							}
						});
						return itemNo;
					}
					if (propertyId.equals(ConstantsUtils.DOLLARS)) {
						final TextField dollars = new TextField();
						dollars.setConverter(dollarsFormat);
						dollars.setImmediate(true);
						return dollars;
					}
				}
				if (propertyId.equals(ConstantsUtils.START_DATE)) {
					final PopupDateField startDate = new PopupDateField();
					startDate.setImmediate(true);
					startDate.setReadOnly(true);
					startDate.setDateFormat("MM/dd/yyyy");
					return startDate;
				}
				final Field field = super.createField(container, itemId, propertyId, uiContext);
				field.setReadOnly(true);
				return field;
			}
		});
	}

	public void demandFieldFactory() {

		detailsFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
                        @Override
			public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
					final Component uiContext) {
				final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
				final Boolean flag = dto.isRecordLockStatus();
				final String interfaceFlag = dto.getInterfaceFlag();
				if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
					if (flag) {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox extCustCheckBoxSelect = new ExtCustomCheckBox();
							extCustCheckBoxSelect.setImmediate(true);
							extCustCheckBoxSelect.setEnabled(false);
							return extCustCheckBoxSelect;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_TYPE)) {
							final TextField forecastType = new TextField();
							forecastType.setImmediate(true);
							forecastType.setReadOnly(true);
							forecastType.setEnabled(false);
							return forecastType;
						}
						if (propertyId.equals(FORCAST_YEAR)) {
							final TextField vForecastYear = new TextField();
							vForecastYear.setImmediate(true);
							vForecastYear.setReadOnly(true);
							vForecastYear.setEnabled(false);
							return vForecastYear;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
							final TextField forecastMonth = new TextField();
							forecastMonth.setImmediate(true);
							forecastMonth.setReadOnly(true);
							forecastMonth.setEnabled(false);
							return forecastMonth;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							final TextField itemIde = new TextField();
							itemIde.setImmediate(true);
							itemIde.setReadOnly(true);
							itemIde.setEnabled(false);
							return itemIde;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
							final TextField itemIdentifierCodeQualifier = new TextField();
							itemIdentifierCodeQualifier.setImmediate(true);
							itemIdentifierCodeQualifier.setReadOnly(true);
							itemIdentifierCodeQualifier.setEnabled(false);
							return itemIdentifierCodeQualifier;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
							final TextField itemIdentifier = new TextField();
							itemIdentifier.setImmediate(true);
							itemIdentifier.setReadOnly(true);
							itemIdentifier.setEnabled(false);
							return itemIdentifier;
						}
						if (propertyId.equals(StringConstantsUtil.BRAND_ID)) {
							final TextField brandId = new TextField();

							brandId.setImmediate(true);
							brandId.setReadOnly(true);
							brandId.setEnabled(false);
							return brandId;
						}
						if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
							final TextField segment = new TextField();
							segment.setImmediate(true);
							segment.setReadOnly(true);
							segment.setEnabled(false);
							return segment;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SIZE_UNITS)) {
							final TextField marketSizeUnits = new TextField();
							marketSizeUnits.setConverter(unitsFormat);
							marketSizeUnits.setImmediate(true);
							marketSizeUnits.setReadOnly(true);
							marketSizeUnits.setEnabled(false);
							return marketSizeUnits;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_RATIO)) {
							final TextField marketShareRatio = new TextField();
							marketShareRatio.setImmediate(true);
							marketShareRatio.setReadOnly(true);
							marketShareRatio.setEnabled(false);
							return marketShareRatio;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_UNITS)) {
							final TextField marketShareUnits = new TextField();
							marketShareUnits.setImmediate(true);
							marketShareUnits.setConverter(zeroDecimalFormat);
							marketShareUnits.setReadOnly(true);
							marketShareUnits.setEnabled(false);
							return marketShareUnits;
						}
						if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS)) {
							final TextField uncapturedUnits = new TextField();
							uncapturedUnits.setImmediate(true);
							uncapturedUnits.setConverter(zeroDecimalFormat);
							uncapturedUnits.setReadOnly(true);
							uncapturedUnits.setEnabled(false);
							return uncapturedUnits;
						}
						if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS_RATIO)) {
							final TextField uncapturedUnitsRatio = new TextField();
							uncapturedUnitsRatio.setImmediate(true);
							uncapturedUnitsRatio.setReadOnly(true);
							uncapturedUnitsRatio.setEnabled(false);
							return uncapturedUnitsRatio;
						}
						if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_UNITS)) {
							final TextField totalDemandUnits = new TextField();
							totalDemandUnits.setImmediate(true);
							totalDemandUnits.setConverter(zeroDecimalFormat);
							totalDemandUnits.setReadOnly(true);
							totalDemandUnits.setEnabled(false);
							return totalDemandUnits;
						}
						if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_AMOUNT)) {
							final TextField totalDemandAmount = new TextField();
							totalDemandAmount.setImmediate(true);
							totalDemandAmount.setConverter(priceFormat);
							totalDemandAmount.setReadOnly(true);
							totalDemandAmount.setEnabled(false);
							return totalDemandAmount;
						}
						if (propertyId.equals(StringConstantsUtil.INVENTORY_UNIT_CHANGE)) {
							final TextField inventoryUnitChange = new TextField();
							inventoryUnitChange.setImmediate(true);
							inventoryUnitChange.setConverter(zeroDecimalFormat);
							inventoryUnitChange.setReadOnly(true);
							inventoryUnitChange.setEnabled(false);
							return inventoryUnitChange;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_UNITS)) {
							final TextField grossUnits = new TextField();
							grossUnits.setImmediate(true);
							grossUnits.setConverter(zeroDecimalFormat);
							grossUnits.setReadOnly(true);
							grossUnits.setEnabled(false);
							return grossUnits;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_PRICE)) {
							final TextField grossPrice = new TextField();
							grossPrice.setImmediate(true);
							grossPrice.setConverter(dollarsFormat);
							grossPrice.setReadOnly(true);
							grossPrice.setEnabled(false);
							return grossPrice;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_AMOUNT)) {
							final TextField grossAmount = new TextField();
							grossAmount.setImmediate(true);
							grossAmount.setConverter(priceFormat);
							grossAmount.setReadOnly(true);
							grossAmount.setEnabled(false);
							return grossAmount;
						}
						if (propertyId.equals(StringConstantsUtil.NET_SALES_PRICE)) {
							final TextField netSalesPrice = new TextField();
							netSalesPrice.setImmediate(true);
							netSalesPrice.setConverter(priceFormat);
							netSalesPrice.setReadOnly(true);
							netSalesPrice.setEnabled(false);
							return netSalesPrice;
						}
						if (propertyId.equals(StringConstantsUtil.NET_SALES_AMOUNT)) {
							final TextField netSalesAmount = new TextField();
							netSalesAmount.setImmediate(true);
							netSalesAmount.setConverter(priceFormat);
							netSalesAmount.setReadOnly(true);
							netSalesAmount.setEnabled(false);
							return netSalesAmount;
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							final TextField batchId = new TextField();
							batchId.setImmediate(true);
							batchId.setReadOnly(true);
							batchId.setEnabled(false);
							return batchId;
						}
						if (propertyId.equals(StringConstantsUtil.SOURCE_PROPERTY)) {
							final TextField source = new TextField();
							source.setImmediate(true);
							source.setReadOnly(true);
							source.setEnabled(false);
							return source;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
							final TextField vForecastName = new TextField();
							vForecastName.setImmediate(true);
							vForecastName.setReadOnly(true);
							vForecastName.setEnabled(false);
							return vForecastName;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
							final TextField vForecastVersion = new TextField();
							vForecastVersion.setImmediate(true);
							vForecastVersion.setReadOnly(true);
							vForecastVersion.setEnabled(false);
							return vForecastVersion;
						}
						if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
							final TextField vCountry = new TextField();
							vCountry.setImmediate(true);
							vCountry.setReadOnly(true);
							vCountry.setEnabled(false);
							return vCountry;
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							final TextField organizationKey = new TextField();
							organizationKey.setImmediate(true);
							organizationKey.setReadOnly(true);
							organizationKey.setEnabled(false);
							return organizationKey;
						}

					} else {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox extCustCheckSelect = new ExtCustomCheckBox();
							extCustCheckSelect.setImmediate(true);
							extCustCheckSelect.setEnabled(true);
							return extCustCheckSelect;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_TYPE)) {
							final TextField forecastType = new TextField();
							forecastType.setImmediate(true);
							forecastType.setReadOnly(true);
							forecastType.setEnabled(false);
							return forecastType;
						}
						if (propertyId.equals(FORCAST_YEAR)) {
							final TextField forecastYear = new TextField();
							forecastYear.setImmediate(true);
							forecastYear.setReadOnly(true);
							forecastYear.setEnabled(false);
							return forecastYear;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
							final TextField forecastMonth = new TextField();
							forecastMonth.setImmediate(true);
							forecastMonth.setReadOnly(true);
							forecastMonth.setEnabled(false);
							return forecastMonth;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							final TextField itemIde = new TextField();
							itemIde.setImmediate(true);
							itemIde.setReadOnly(true);
							itemIde.setEnabled(false);
							return itemIde;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
							final TextField itemIdentifierCodeQualifier = new TextField();
							itemIdentifierCodeQualifier.setImmediate(true);
							itemIdentifierCodeQualifier.setReadOnly(true);
							itemIdentifierCodeQualifier.setEnabled(false);
							return itemIdentifierCodeQualifier;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
							final TextField itemIdentifier = new TextField();
							itemIdentifier.setImmediate(true);
							itemIdentifier.setReadOnly(true);
							itemIdentifier.setEnabled(false);
							return itemIdentifier;
						}
						if (propertyId.equals(StringConstantsUtil.BRAND_ID)) {
							final TextField brandId = new TextField();

							brandId.setImmediate(true);
							brandId.setReadOnly(true);
							brandId.setEnabled(false);
							return brandId;
						}
						if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
							final TextField segment = new TextField();
							segment.setImmediate(true);
							segment.setReadOnly(true);
							segment.setEnabled(false);
							return segment;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SIZE_UNITS)) {
							final TextField marketSizeUnits = new TextField();
							marketSizeUnits.setImmediate(true);
							marketSizeUnits.setConverter(unitsFormat);
							marketSizeUnits.setReadOnly(true);
							marketSizeUnits.setEnabled(false);
							return marketSizeUnits;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_RATIO)) {
							final TextField marketShareRatio = new TextField();
							marketShareRatio.setImmediate(true);
							marketShareRatio.setReadOnly(true);
							marketShareRatio.setEnabled(false);
							return marketShareRatio;
						}
						if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_UNITS)) {
							final TextField marketShareUnits = new TextField();
							marketShareUnits.setImmediate(true);
							marketShareUnits.setConverter(unitsFormat);
							marketShareUnits.setReadOnly(true);
							marketShareUnits.setEnabled(false);
							return marketShareUnits;
						}
						if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS)) {
							final TextField uncapturedUnits = new TextField();
							uncapturedUnits.setImmediate(true);
							uncapturedUnits.setConverter(unitsFormat);
							uncapturedUnits.setReadOnly(true);
							uncapturedUnits.setEnabled(false);
							return uncapturedUnits;
						}
						if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS_RATIO)) {
							final TextField uncapturedUnitsRatio = new TextField();
							uncapturedUnitsRatio.setImmediate(true);
							uncapturedUnitsRatio.setReadOnly(true);
							uncapturedUnitsRatio.setEnabled(false);
							return uncapturedUnitsRatio;
						}
						if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_UNITS)) {
							final TextField totalDemandUnits = new TextField();
							totalDemandUnits.setImmediate(true);
							totalDemandUnits.setConverter(unitsFormat);
							totalDemandUnits.setReadOnly(true);
							totalDemandUnits.setEnabled(false);
							return totalDemandUnits;
						}
						if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_AMOUNT)) {
							final TextField totalDemandAmount = new TextField();
							totalDemandAmount.setImmediate(true);
							totalDemandAmount.setConverter(priceFormat);
							totalDemandAmount.setReadOnly(true);
							totalDemandAmount.setEnabled(false);
							return totalDemandAmount;
						}
						if (propertyId.equals(StringConstantsUtil.INVENTORY_UNIT_CHANGE)) {
							final TextField inventoryUnitChange = new TextField();
							inventoryUnitChange.setImmediate(true);
							inventoryUnitChange.setConverter(unitsFormat);
							inventoryUnitChange.setReadOnly(true);
							inventoryUnitChange.setEnabled(false);
							return inventoryUnitChange;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_UNITS)) {
							final TextField grossUnits = new TextField();
							grossUnits.setImmediate(true);
							grossUnits.setConverter(unitsFormat);
							grossUnits.setReadOnly(true);
							grossUnits.setEnabled(false);
							return grossUnits;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_PRICE)) {
							final TextField grossPrice = new TextField();
							grossPrice.setImmediate(true);
							grossPrice.setConverter(priceFormat);
							grossPrice.setReadOnly(true);
							grossPrice.setEnabled(false);
							return grossPrice;
						}
						if (propertyId.equals(StringConstantsUtil.GROSS_AMOUNT)) {
							final TextField grossAmount = new TextField();
							grossAmount.setImmediate(true);
							grossAmount.setConverter(priceFormat);
							grossAmount.setReadOnly(true);
							grossAmount.setEnabled(false);
							return grossAmount;
						}
						if (propertyId.equals(StringConstantsUtil.NET_SALES_PRICE)) {
							final TextField netSalesPrice = new TextField();
							netSalesPrice.setImmediate(true);
							netSalesPrice.setConverter(priceFormat);
							netSalesPrice.setReadOnly(true);
							netSalesPrice.setEnabled(false);
							return netSalesPrice;
						}
						if (propertyId.equals(StringConstantsUtil.NET_SALES_AMOUNT)) {
							final TextField netSalesAmount = new TextField();
							netSalesAmount.setImmediate(true);
							netSalesAmount.setConverter(priceFormat);
							netSalesAmount.setReadOnly(true);
							netSalesAmount.setEnabled(false);
							return netSalesAmount;
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							final TextField batchId = new TextField();
							batchId.setImmediate(true);
							batchId.setReadOnly(true);
							batchId.setEnabled(false);
							return batchId;
						}
						if (propertyId.equals(StringConstantsUtil.SOURCE_PROPERTY)) {
							final TextField source = new TextField();
							source.setImmediate(true);
							source.setReadOnly(true);
							source.setEnabled(false);
							return source;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
							final TextField vForecastName = new TextField();
							vForecastName.setImmediate(true);
							vForecastName.setReadOnly(true);
							vForecastName.setEnabled(false);
							return vForecastName;
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
							final TextField vForecastVersion = new TextField();
							vForecastVersion.setImmediate(true);
							vForecastVersion.setReadOnly(true);
							vForecastVersion.setEnabled(false);
							return vForecastVersion;
						}
						if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
							final TextField vCountry = new TextField();
							vCountry.setImmediate(true);
							vCountry.setReadOnly(true);
							vCountry.setEnabled(false);
							return vCountry;
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							final TextField organizationKey = new TextField();
							organizationKey.setImmediate(true);
							organizationKey.setReadOnly(true);
							organizationKey.setEnabled(false);
							return organizationKey;
						}
					}
				} else {
					if (propertyId.equals(ConstantsUtils.CHECK)) {
						final ExtCustomCheckBox extCustomCheckSelect = new ExtCustomCheckBox();
						extCustomCheckSelect.setImmediate(true);
						extCustomCheckSelect.setEnabled(true);
						return extCustomCheckSelect;
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_TYPE)) {
						final TextField forecastType = new TextField();
						forecastType.setImmediate(true);
						forecastType.setEnabled(true);
						return forecastType;
					}
					if (propertyId.equals(FORCAST_YEAR)) {
						final TextField vForecastYear = new TextField();
						vForecastYear.setEnabled(true);
						vForecastYear.setImmediate(true);
						return vForecastYear;
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
						final TextField forecastMonth = new TextField();
						forecastMonth.setEnabled(true);
						forecastMonth.setImmediate(true);
						return forecastMonth;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
						final TextField itemIde = new TextField();
						itemIde.setEnabled(true);
						itemIde.setImmediate(true);
						return itemIde;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
						final TextField itemIdentifierCodeQualifier = new TextField();
						itemIdentifierCodeQualifier.setEnabled(true);
						itemIdentifierCodeQualifier.setImmediate(true);
						return itemIdentifierCodeQualifier;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
						final TextField itemIdentifier = new TextField();
						itemIdentifier.setEnabled(true);
						itemIdentifier.setImmediate(true);
						return itemIdentifier;
					}
					if (propertyId.equals(StringConstantsUtil.BRAND_ID)) {
						final TextField brandId = new TextField();
						brandId.setEnabled(true);
						brandId.setImmediate(true);
						return brandId;
					}
					if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
						final TextField segment = new TextField();
						segment.setEnabled(true);
						segment.setImmediate(true);
						return segment;
					}
					if (propertyId.equals(StringConstantsUtil.MARKET_SIZE_UNITS)) {
						final TextField marketSizeUnits = new TextField();
						marketSizeUnits.setEnabled(true);
						marketSizeUnits.setImmediate(true);
						return marketSizeUnits;
					}
					if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_RATIO)) {
						final TextField marketShareRatio = new TextField();
						marketShareRatio.setEnabled(true);
						marketShareRatio.setImmediate(true);
						return marketShareRatio;
					}
					if (propertyId.equals(StringConstantsUtil.MARKET_SHARE_UNITS)) {
						final TextField marketShareUnits = new TextField();
						marketShareUnits.setEnabled(true);
						marketShareUnits.setImmediate(true);
						return marketShareUnits;
					}
					if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS)) {
						final TextField uncapturedUnits = new TextField();
						uncapturedUnits.setEnabled(true);
						uncapturedUnits.setImmediate(true);
						return uncapturedUnits;
					}
					if (propertyId.equals(StringConstantsUtil.UNCAPTURED_UNITS_RATIO)) {
						final TextField uncapturedUnitsRatio = new TextField();
						uncapturedUnitsRatio.setEnabled(true);
						uncapturedUnitsRatio.setImmediate(true);
						return uncapturedUnitsRatio;
					}
					if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_UNITS)) {
						final TextField totalDemandUnits = new TextField();
						totalDemandUnits.setEnabled(true);
						totalDemandUnits.setImmediate(true);
						return totalDemandUnits;
					}
					if (propertyId.equals(StringConstantsUtil.TOTAL_DEMAND_AMOUNT)) {
						final TextField totalDemandAmount = new TextField();
						totalDemandAmount.setEnabled(true);
						totalDemandAmount.setImmediate(true);
						return totalDemandAmount;
					}
					if (propertyId.equals(StringConstantsUtil.INVENTORY_UNIT_CHANGE)) {
						final TextField inventoryUnitChange = new TextField();
						inventoryUnitChange.setEnabled(true);
						inventoryUnitChange.setImmediate(true);
						return inventoryUnitChange;
					}
					if (propertyId.equals(StringConstantsUtil.GROSS_UNITS)) {
						final TextField grossUnits = new TextField();
						grossUnits.setEnabled(true);
						grossUnits.setImmediate(true);
						return grossUnits;
					}
					if (propertyId.equals(StringConstantsUtil.GROSS_PRICE)) {
						final TextField grossPrice = new TextField();
						grossPrice.setEnabled(true);
						grossPrice.setImmediate(true);
						return grossPrice;
					}
					if (propertyId.equals(StringConstantsUtil.GROSS_AMOUNT)) {
						final TextField grossAmount = new TextField();
						grossAmount.setEnabled(true);
						grossAmount.setImmediate(true);
						return grossAmount;
					}
					if (propertyId.equals(StringConstantsUtil.NET_SALES_PRICE)) {
						final TextField netSalesPrice = new TextField();
						netSalesPrice.setEnabled(true);
						netSalesPrice.setImmediate(true);
						return netSalesPrice;
					}
					if (propertyId.equals(StringConstantsUtil.NET_SALES_AMOUNT)) {
						final TextField netSalesAmount = new TextField();
						netSalesAmount.setEnabled(true);
						netSalesAmount.setImmediate(true);
						return netSalesAmount;
					}
					if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
						final TextField batchId = new TextField();
						batchId.setEnabled(true);
						batchId.setImmediate(true);
						return batchId;
					}
					if (propertyId.equals(StringConstantsUtil.SOURCE_PROPERTY)) {
						final TextField source = new TextField();
						source.setEnabled(true);
						source.setImmediate(true);
						return source;
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
						final TextField vForecastName = new TextField();
						vForecastName.setEnabled(true);
						vForecastName.setImmediate(true);
						return vForecastName;
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
						final TextField vForecastVersion = new TextField();
						vForecastVersion.setEnabled(true);
						vForecastVersion.setImmediate(true);
						return vForecastVersion;
					}
					if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
						final TextField vCountry = new TextField();
						vCountry.setEnabled(true);
						vCountry.setImmediate(true);
						return vCountry;
					}
					if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
						final TextField organizationKey = new TextField();
						organizationKey.setEnabled(true);
						organizationKey.setImmediate(true);
						return organizationKey;
					}
				}
				final Field field = super.createField(container, itemId, propertyId, uiContext);
				field.setReadOnly(true);
				return field;
			}
		});
	}

	public void inventoryFieldFactory(final String fileType) {
		detailsFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
			/**
			 * To create editable fields inside table .
			 */
                        @Override
			public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
					final Component uiContext) {
				final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
				final Boolean flag = dto.isRecordLockStatus();
				final String interfaceFlag = dto.getInterfaceFlag();
				if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
					if (flag) {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox vExtCustomCheckSelect = new ExtCustomCheckBox();
							vExtCustomCheckSelect.setImmediate(true);
							vExtCustomCheckSelect.setEnabled(false);
							return vExtCustomCheckSelect;
						}
						if (propertyId.equals("year")) {
							final TextField year = new TextField();
							year.setImmediate(true);
							year.setReadOnly(true);
							year.setEnabled(false);
							return year;
						}
						if (propertyId.equals(StringConstantsUtil.MONTH_PROPERTY)) {
							final TextField month = new TextField();
							month.setImmediate(true);
							month.setReadOnly(true);
							month.setEnabled(false);
							return month;
						}
						if (propertyId.equals("week")) {
							final TextField week = new TextField();
							week.setImmediate(true);
							week.setReadOnly(true);
							week.setEnabled(false);
							return week;
						}
						if (propertyId.equals("day")) {
							final TextField day = new TextField();
							day.setImmediate(true);
							day.setReadOnly(true);
							day.setEnabled(false);
							return day;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							final TextField itemIde = new TextField();
							itemIde.setImmediate(true);
							itemIde.setReadOnly(true);
							itemIde.setEnabled(false);
							return itemIde;
						}
						if (propertyId.equals(StringConstantsUtil.UNITS_WITHDRAWN)) {
							final TextField unitsWithdrawn = new TextField();
							unitsWithdrawn.setImmediate(true);
							unitsWithdrawn.setReadOnly(true);
							unitsWithdrawn.setEnabled(false);
							return unitsWithdrawn;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
							final TextField itemIdentifier = new TextField();
							itemIdentifier.setImmediate(true);
							itemIdentifier.setReadOnly(true);
							itemIdentifier.setEnabled(false);
							return itemIdentifier;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
							final TextField itemIdentifierCodeQualifier = new TextField();
							itemIdentifierCodeQualifier.setImmediate(true);
							itemIdentifierCodeQualifier.setReadOnly(true);
							itemIdentifierCodeQualifier.setEnabled(false);
							return itemIdentifierCodeQualifier;
						}
						if (propertyId.equals(StringConstantsUtil.AMOUNT_WITHDRAWN)) {
							final TextField amountWithdrawn = new TextField();
							amountWithdrawn.setImmediate(true);
							amountWithdrawn.setReadOnly(true);
							amountWithdrawn.setEnabled(false);
							return amountWithdrawn;
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							final TextField batchId = new TextField();
							batchId.setImmediate(true);
							batchId.setReadOnly(true);
							batchId.setEnabled(false);
							return batchId;
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							final TextField organizationKey = new TextField();
							organizationKey.setImmediate(true);
							organizationKey.setReadOnly(true);
							organizationKey.setEnabled(false);
							return organizationKey;
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
							final TextField price = new TextField();
							price.setImmediate(true);
							price.setReadOnly(true);
							price.setEnabled(false);
							return price;
						}
						if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
							if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
								final TextField companyId = new TextField();
								companyId.setImmediate(true);
								companyId.setReadOnly(true);
								companyId.setEnabled(false);
								return companyId;
							}
							if (propertyId.equals(StringConstantsUtil.IDENTIFIER_CODE_QUALIFIER)) {
								final TextField identifierCodeQualifier = new TextField();
								identifierCodeQualifier.setImmediate(true);
								identifierCodeQualifier.setReadOnly(true);
								identifierCodeQualifier.setEnabled(false);
								return identifierCodeQualifier;
							}
							if (propertyId.equals(StringConstantsUtil.COMPANY_IDENTIFIER)) {
								final TextField companyIdentifier = new TextField();
								companyIdentifier.setImmediate(true);
								companyIdentifier.setReadOnly(true);
								companyIdentifier.setEnabled(false);
								return companyIdentifier;
							}
						}

					} else {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox extCustomCheckSelect = new ExtCustomCheckBox();
							extCustomCheckSelect.setImmediate(true);
							extCustomCheckSelect.setEnabled(true);
							return extCustomCheckSelect;
						}
						if (propertyId.equals("year")) {
							final TextField year = new TextField();
							year.setImmediate(true);
							year.setReadOnly(true);
							year.setEnabled(false);
							return year;
						}
						if (propertyId.equals(StringConstantsUtil.MONTH_PROPERTY)) {
							final TextField month = new TextField();
							month.setImmediate(true);
							month.setReadOnly(true);
							month.setEnabled(false);
							return month;
						}
						if (propertyId.equals("week")) {
							final TextField week = new TextField();
							week.setImmediate(true);
							week.setReadOnly(true);
							week.setEnabled(false);
							return week;
						}
						if (propertyId.equals("day")) {
							final TextField day = new TextField();
							day.setImmediate(true);
							day.setReadOnly(true);
							day.setEnabled(false);
							return day;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							final TextField itemIde = new TextField();
							itemIde.setImmediate(true);
							itemIde.setReadOnly(true);
							itemIde.setEnabled(false);
							return itemIde;
						}
						if (propertyId.equals(StringConstantsUtil.UNITS_WITHDRAWN)) {
							final TextField unitsWithdrawn = new TextField();
							unitsWithdrawn.setImmediate(true);
							unitsWithdrawn.setReadOnly(true);
							unitsWithdrawn.setEnabled(false);
							return unitsWithdrawn;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
							final TextField itemIdentifier = new TextField();
							itemIdentifier.setImmediate(true);
							itemIdentifier.setReadOnly(true);
							itemIdentifier.setEnabled(false);
							return itemIdentifier;
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
							final TextField itemIdentifierCodeQualifier = new TextField();
							itemIdentifierCodeQualifier.setImmediate(true);
							itemIdentifierCodeQualifier.setReadOnly(true);
							itemIdentifierCodeQualifier.setEnabled(false);
							return itemIdentifierCodeQualifier;
						}
						if (propertyId.equals(StringConstantsUtil.AMOUNT_WITHDRAWN)) {
							final TextField amountWithdrawn = new TextField();
							amountWithdrawn.setImmediate(true);
							amountWithdrawn.setReadOnly(true);
							amountWithdrawn.setEnabled(false);
							return amountWithdrawn;
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							final TextField batchId = new TextField();
							batchId.setImmediate(true);
							batchId.setReadOnly(true);
							batchId.setEnabled(false);
							return batchId;
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							final TextField organizationKey = new TextField();
							organizationKey.setImmediate(true);
							organizationKey.setReadOnly(true);
							organizationKey.setEnabled(false);
							return organizationKey;
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
							final TextField price = new TextField();
							price.setImmediate(true);
							price.setReadOnly(true);
							price.setEnabled(false);
							return price;
						}
						if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
							if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
								final TextField companyId = new TextField();
								companyId.setImmediate(true);
								companyId.setReadOnly(true);
								companyId.setEnabled(false);
								return companyId;
							}
							if (propertyId.equals(StringConstantsUtil.IDENTIFIER_CODE_QUALIFIER)) {
								final TextField identifierCodeQualifier = new TextField();
								identifierCodeQualifier.setImmediate(true);
								identifierCodeQualifier.setReadOnly(true);
								identifierCodeQualifier.setEnabled(false);
								return identifierCodeQualifier;
							}
							if (propertyId.equals(StringConstantsUtil.COMPANY_IDENTIFIER)) {
								final TextField companyIdentifier = new TextField();
								companyIdentifier.setImmediate(true);
								companyIdentifier.setReadOnly(true);
								companyIdentifier.setEnabled(false);
								return companyIdentifier;
							}
						}
					}
				} else {
					if (propertyId.equals(ConstantsUtils.CHECK)) {
						final ExtCustomCheckBox extCustomCheckSelect = new ExtCustomCheckBox();
						extCustomCheckSelect.setImmediate(true);
						extCustomCheckSelect.setEnabled(true);
						return extCustomCheckSelect;
					}
					if (propertyId.equals("year")) {
						final TextField year = new TextField();
						year.setImmediate(true);
						year.setReadOnly(false);
						year.setEnabled(true);
						return year;
					}
					if (propertyId.equals(StringConstantsUtil.MONTH_PROPERTY)) {
						final TextField month = new TextField();
						month.setImmediate(true);
						month.setReadOnly(false);
						month.setEnabled(true);
						return month;
					}
					if (propertyId.equals("week")) {
						final TextField week = new TextField();
						week.setImmediate(true);
						week.setReadOnly(false);
						week.setEnabled(true);
						return week;
					}
					if (propertyId.equals("day")) {
						final TextField day = new TextField();
						day.setImmediate(true);
						day.setReadOnly(false);
						day.setEnabled(true);
						return day;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
						final TextField itemIde = new TextField();
						itemIde.setImmediate(true);
						itemIde.setReadOnly(false);
						itemIde.setEnabled(true);
						return itemIde;
					}
					if (propertyId.equals(StringConstantsUtil.UNITS_WITHDRAWN)) {
						final TextField unitsWithdrawn = new TextField();
						unitsWithdrawn.setImmediate(true);
						unitsWithdrawn.setReadOnly(false);
						unitsWithdrawn.setEnabled(true);
						return unitsWithdrawn;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER)) {
						final TextField itemIdentifier = new TextField();
						itemIdentifier.setImmediate(true);
						itemIdentifier.setReadOnly(false);
						itemIdentifier.setEnabled(true);
						return itemIdentifier;
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER)) {
						final TextField itemIdentifierCodeQualifier = new TextField();
						itemIdentifierCodeQualifier.setImmediate(true);
						itemIdentifierCodeQualifier.setReadOnly(false);
						itemIdentifierCodeQualifier.setEnabled(true);
						return itemIdentifierCodeQualifier;
					}
					if (propertyId.equals(StringConstantsUtil.AMOUNT_WITHDRAWN)) {
						final TextField amountWithdrawn = new TextField();
						amountWithdrawn.setImmediate(true);
						amountWithdrawn.setReadOnly(false);
						amountWithdrawn.setEnabled(true);
						return amountWithdrawn;
					}
					if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
						final TextField batchId = new TextField();
						batchId.setImmediate(true);
						batchId.setReadOnly(false);
						batchId.setEnabled(true);
						return batchId;
					}
					if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
						final TextField organizationKey = new TextField();
						organizationKey.setImmediate(true);
						organizationKey.setReadOnly(false);
						organizationKey.setEnabled(true);
						return organizationKey;
					}
					if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
						final TextField price = new TextField();
						price.setImmediate(true);
						price.setReadOnly(false);
						price.setEnabled(true);
						return price;
					}
					if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
						if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
							final TextField companyId = new TextField();
							companyId.setImmediate(true);
							companyId.setReadOnly(false);
							companyId.setEnabled(true);
							return companyId;
						}
						if (propertyId.equals(StringConstantsUtil.IDENTIFIER_CODE_QUALIFIER)) {
							final TextField identifierCodeQualifier = new TextField();
							identifierCodeQualifier.setImmediate(true);
							identifierCodeQualifier.setReadOnly(false);
							identifierCodeQualifier.setEnabled(true);
							return identifierCodeQualifier;
						}
						if (propertyId.equals(StringConstantsUtil.COMPANY_IDENTIFIER)) {
							final TextField companyIdentifier = new TextField();
							companyIdentifier.setImmediate(true);
							companyIdentifier.setReadOnly(false);
							companyIdentifier.setEnabled(true);
							return companyIdentifier;
						}
					}
				}
				final Field field = super.createField(container, itemId, propertyId, uiContext);
				field.setReadOnly(true);
				return field;
			}
		});
	}

	public void customerFieldFactory() {

		detailsFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
                        @Override
			public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
					final Component uiContext) {
				final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
				final Boolean flag = dto.isRecordLockStatus();
				final String interfaceFlag = dto.getInterfaceFlag();
				if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
					if (flag) {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox ectCustomCheckSelect = new ExtCustomCheckBox();
							ectCustomCheckSelect.setImmediate(true);
							ectCustomCheckSelect.setEnabled(false);
							return ectCustomCheckSelect;
						}
						if (propertyId.equals(FORCAST_YEAR)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.UNITS_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SALES_AMOUNT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SALES_INCLUSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_CATEGORY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_PROGRAM_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ADJUSTMENT_CODE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_RATE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_AMOUNT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_INCLUSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VALUE_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals("brand")) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_DATE)) {
							return createCustField(true);
						}

					} else {
						if (propertyId.equals(ConstantsUtils.CHECK)) {
							final ExtCustomCheckBox extCustomCheckSelect = new ExtCustomCheckBox();
							extCustomCheckSelect.setImmediate(true);
							extCustomCheckSelect.setEnabled(false);
							return extCustomCheckSelect;
						}
						if (propertyId.equals(FORCAST_YEAR)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.UNITS_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SALES_AMOUNT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SALES_INCLUSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_CATEGORY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_PROGRAM_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ADJUSTMENT_CODE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_RATE)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_AMOUNT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.DEDUCTION_INCLUSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VALUE_TYPE)) {
							return createCustField(true);
						}
						if (propertyId.equals("brand")) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
							return createCustField(true);
						}
						if (propertyId.equals(StringConstantsUtil.FORECAST_DATE)) {
							return createCustField(true);
						}
					}
				} else {
					if (propertyId.equals(ConstantsUtils.CHECK)) {
						final ExtCustomCheckBox extCustomCheckSelect = new ExtCustomCheckBox();
						extCustomCheckSelect.setImmediate(true);
						extCustomCheckSelect.setEnabled(true);
						return extCustomCheckSelect;
					}
					if (propertyId.equals(FORCAST_YEAR)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_MONTH)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.ITEM_ID)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.COMPANY_ID_PROPERTY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.UNITS_PROPERTY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.PRICE_TYPE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.PRICE_PROPERTY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.SALES_AMOUNT)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.SALES_INCLUSION)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_ID)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_CATEGORY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_TYPE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_PROGRAM_TYPE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.ADJUSTMENT_CODE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_RATE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_AMOUNT)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.DEDUCTION_INCLUSION)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_VALUE_TYPE)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.BRAND_ID)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.SEGMENT)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.BATCH_ID)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.ORGANIZATION_KEY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_VERSION)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.COUNTRY)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_NAME)) {
						return createCustField(false);
					}
					if (propertyId.equals(StringConstantsUtil.FORECAST_DATE)) {
						return createCustField(false);
					}
				}
				final Field field = super.createField(container, itemId, propertyId, uiContext);
				field.setReadOnly(true);
				return field;
			}
		});
	}

	public TextField createCustField(boolean flag) {

		TextField createdField = new TextField();
		createdField.setImmediate(true);
		createdField.setReadOnly(flag);
		createdField.setEnabled(!flag);
		return createdField;
	}

	public void getSearchForecastyear() {
		int year;
		List currentyear = new ArrayList();
		year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = NumericConstants.FIVE; i >= 1; i--) {
			currentyear.add(year - i);

		}
		currentyear.add(year);
		for (int i = 1; i <= NumericConstants.FIVE; i++) {
			currentyear.add(year + i);
		}
		for (Object i : currentyear) {
			SearchForecastddlb.addItem(i);
		}

	}

	public boolean isContainsSpecialChar(String toCheck) {
		boolean isContainsSC = false;
		if (toCheck != null && !toCheck.equals("")) {
			Matcher m = Pattern.compile("[\\W]").matcher(toCheck);
			while (m.find()) {
				isContainsSC = true;

			}
		}
		return isContainsSC;
	}

	/**
	 * To configure Excel Details Results Table
	 */
	private String[] configureExcelDetailsTableBCP() {
		String[] bcpHeader;
		if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupDetailsHeaderExcelList();
		} else if (fileType.equals(ConstantsUtils.DEMAND)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupDemandDetailsHeaderExcelList();
		} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupAdjDemandDetailsHeaderList();
		} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupInvDetailsSumHeaderList();
		} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupInvDetailsHeaderExcelList();
		} else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
			bcpHeader = CommonUIUtil.getFileMgmtLookupCusHeaderList();
		} else {
			bcpHeader = new String[1];
		}
		return bcpHeader;
	}

	@UiHandler("excelExportDetail")
	public void excelExportDetailLogic(Button.ClickEvent event) {
		try {
			if (null != detailsResultDTO.getHelperType()) {
				String countQuery;
				String dataQuery;
				switch (detailsResultDTO.getHelperType()) {
				case ConstantsUtils.CUSTOMERGTS:
					dataQuery = String.valueOf(vFileMgmtLogic.getCustomerSalesResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getCustomerSalesResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				case ConstantsUtils.ADJUSTED_DEMAND:
					dataQuery = String.valueOf(vFileMgmtLogic.getAdjustedDemandDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getAdjustedDemandDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				case ConstantsUtils.DEMAND:
					dataQuery = String.valueOf(vFileMgmtLogic.getDemandDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getDemandDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				case ConstantsUtils.EX_FACTORY_SALES:
					dataQuery = String.valueOf(vFileMgmtLogic.getForecastDetails(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getForecastDetails(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				case ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL:
					dataQuery = String.valueOf(vFileMgmtLogic.getInventoryDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getInventoryDetailsResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				default:
					dataQuery = String.valueOf(vFileMgmtLogic.getInventorySummaryResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), false, true));
					countQuery = String.valueOf(vFileMgmtLogic.getInventorySummaryResults(detailsResultDTO, 0, 0,
							tableLogic.getSortByColumns(), tableLogic.getFilters(), true, true));
					break;
				}
				SessionDTO sessionDto = getSessionDto();
				String fileAbsolutePath = GtnWsCsvExportUtil.getExportFileName(detailsResultDTO.getHelperType(),
						countQuery, dataQuery, Arrays.asList(configureExcelDetailsTableBCP()), sessionDto.getUserId(),
						sessionDto.getSessionId(), 2);
				GtnWsCsvExportUtil.sendTheExcelToUser(detailsResultDTO.getHelperType(), fileAbsolutePath, true,
						sessionDto.getUserId(), sessionDto.getSessionId());
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public SessionDTO getSessionDto() {
		return (SessionDTO) UI.getCurrent().getData();
	}
}
