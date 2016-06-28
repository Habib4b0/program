/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.FileManagementLookup;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.fileSelection.Util.CommonUIUtil;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementFilterGenerator;
import com.stpl.app.cff.ui.fileSelection.dto.FileMananagementResultDTO;
import com.stpl.app.cff.ui.fileSelection.logic.FileManagementLogic;
import com.stpl.app.cff.ui.fileSelection.logic.tableLogic.FileDetailsTableLogic;
import com.stpl.app.cff.ui.fileSelection.logic.tableLogic.FileResultsTableLogic;
import com.stpl.app.cff.ui.fileSelection.lazyload.ForecastYearContainer;
import com.stpl.app.adminconsole.filemanagement.ui.lazyload.ForecastYearCriteria;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ErrorCodeUtil;
import com.stpl.app.cff.util.ErrorCodes;
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
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
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
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.ifs.ui.DateToStringConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;

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
    private final ComboBox forecastYear = new ComboBox();
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
     * The startDate
     */
//    @UiField("startDate")
//    private PopupDateField startDate;
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
    /**
     * The addLine.
     */
//    @UiField("addLine")
//    private Button addLine;
    /**
     * The SAVE.
     */
//    @UiField("save")
//    private Button save;
    /**
     * The remove.
     */
//    @UiField("remove")
//    private Button remove;
    /**
     * The reset.
     */
//    @UiField("resetBtn")
//    private Button resetBtn;
    /**
     * The populate.
     */
//    @UiField("populate")
//    private Button populate;

//    @UiField("massUpdatePanel")
//    private Panel massUpdatePanel;

    @UiField("summaryPanel")
    private Panel summaryPanel;

    @UiField("cssLayout")
    CssLayout cssLayout;

    @UiField("cssLayoutForecastSection")
    CssLayout cssLayoutForecastSection;

    FileResultsTableLogic tableLogic = new FileResultsTableLogic();
    /**
     * The file history table.
     */
    private ExtPagedTable resultsFilterTable = new ExtPagedTable(tableLogic);
    FileDetailsTableLogic detailstableLogic = new FileDetailsTableLogic();
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
    private TextField fileType;
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
    VerticalLayout tableLayout;

    @UiField("detailsTable")
    VerticalLayout detailsTable;
    /**
     * The file massUpdate.
     */
//    @UiField("massUpdate")
//    private OptionGroup massUpdate;
    /**
     * The file type.
     */
//    @UiField("itemNoSearch")
//    private CustomTextField itemNoSearch;
    /**
     * The file value.
     */
//    @UiField("value")
//    private TextField value;
    /**
     * The details field Name.
     */

    @UiField("horizLayout")
    private HorizontalLayout horizLayout;
//    @UiField("fieldName")
//    private ComboBox fieldName;
    /**
     * The excel image.
     */
    private final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");
    /**
     * The results bean.
     */
    private final BeanItemContainer<FileMananagementResultDTO> resultsBean = new BeanItemContainer<FileMananagementResultDTO>(FileMananagementResultDTO.class);
    /**
     * The details bean.
     */
    private BeanItemContainer<FileMananagementResultDTO> detailsBean = new BeanItemContainer<FileMananagementResultDTO>(FileMananagementResultDTO.class);
    /**
     * The file mgt dto.
     */
    private FileMananagementResultDTO fileMgtDTO = new FileMananagementResultDTO();
    /**
     * The file mgt index dto.
     */
    private FileMananagementResultDTO fileMgtIndexDTO = new FileMananagementResultDTO();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FileManagementLookup.class);// Logger
    // Declaration
    /**
     * The Constant NULLITEM.
     */
    private static final BeanItem<?> NULLITEM = null;
    /**
     * The Constant HelperDTO.
     */
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    /**
     * The selected item.
     */
    public Object selectedItem;

    private Object file;
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
    String selectedFileCountry;
    /**
     * The file unchecked image.
     */
    private final ThemeResource uncheckimg = new ThemeResource("../../icons/uncheckimg.png");
    /**
     * The file checked image.
     */
    private final ThemeResource checkedimg = new ThemeResource("../../icons/checked.png");
    /**
     * The file lookupItemName.
     */
    String selectedFile = ConstantsUtils.EMPTY;
    /*
     The save flag
     */
    boolean saveflag;
    /*
     The selectClose flag
     */
    boolean selectClose;
    /**
     * The file DAO.
     */
    DataFormatConverter priceFormat = new DataFormatConverter("#0.00", DataFormatConverter.INDICATOR_DOLLAR);
    DataFormatConverter unitsFormat = new DataFormatConverter("#0.0");
    DataFormatConverter dollarsFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    List<FileMananagementResultDTO> addlineList = new ArrayList<FileMananagementResultDTO>();
    ComboBox fmFileType;
    String fmCountry;
    FileMananagementResultDTO resultDTO = new FileMananagementResultDTO();
    FileMananagementResultDTO detailsResultDTO = new FileMananagementResultDTO();
    FileMananagementResultDTO demandResultDTO = new FileMananagementResultDTO();
    FileMananagementResultDTO demandDetailsResultDTO = new FileMananagementResultDTO();
    private ExtFilterTable excelTable;
    private BeanItemContainer<FileMananagementResultDTO> excelTableBean;
    private ExtFilterTable excelDetailsTable;
    private BeanItemContainer<FileMananagementResultDTO> excelDetailsBean;
    FileManagementLogic logic = new FileManagementLogic();
    String helperFileType;
    BeanItemContainer searchContainer;
    SessionDTO sessionDTO;
    boolean isdetails;
    FileSelectionDTO fileselectiondto = new FileSelectionDTO();
    String FileType;
    String Country;
    Object itemId;

    /**
     * Instantiates a new file management lookup.
     *
     * @param fileMgtIndexDTO the file mgt index dto
     * @param selectFile the select file
     * @param fileType the file type
     * @param country the country
     */
    public FileManagementLookup() {

    }

    public FileManagementLookup(final String country, final SessionDTO sessionDTO, final boolean isdetails, String fileType, String Country, BeanItemContainer searchContainer, Object itemId) {
        super("File Lookup");
        LOGGER.info("FileManagementLookup constructor initiated");

        this.selectFile = selectFile;
        this.sessionDTO = sessionDTO;
        this.searchContainer = searchContainer;
        this.itemId = itemId;
        this.fmCountry = country.replaceAll(ConstantsUtils.NULL, ConstantsUtils.EMPTY);
        this.isdetails = isdetails;
        this.FileType = fileType;
        this.Country = Country;
        LOGGER.info("FileManagementLookup constructor Ended");
    }

//    /**
//     * Gets the adds the.
//     *
//     * @return the adds the
//     */
//    public Button getAdd() {
//        return addLine;
//    }
//
//    /**
//     * Sets the adds the.
//     *
//     * @param addLine the new adds the
//     */
//    public void setAdd(final Button add) {
//        this.addLine = add;
//    }
//
//    /**
//     * Gets the removes the.
//     *
//     * @return the removes the
//     */
//    public Button getRemove() {
//        return remove;
//    }
//
//    /**
//     * Sets the removes the.
//     *
//     * @param remove the new removes the
//     */
//    public void setRemove(final Button remove) {
//        this.remove = remove;
//    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public HelperDTO getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final HelperDTO dto) {
        this.dto = dto;
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
    public TextField getFileType() {
        return fileType;
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
     * @param selectFile the select file
     */
    public void setSelectFile(final TextField selectFile) {
        this.selectFile = selectFile;
    }

    /**
     * Gets the file mgt dto.
     *
     * @return the file mgt dto
     */
    public FileMananagementResultDTO getFileMgtDTO() {
        return fileMgtDTO;
    }

    /**
     * Sets the file mgt dto.
     *
     * @param fileMgtDTO the file mgt dto
     */
    public void setFileMgtDTO(final FileMananagementResultDTO fileMgtDTO) {
        this.fileMgtDTO = fileMgtDTO;
    }

    /**
     * Gets the file mgt index dto.
     *
     * @return the file mgt index dto
     */
    public FileMananagementResultDTO getFileMgtIndexDTO() {
        return fileMgtIndexDTO;
    }

    /**
     * Sets the file mgt index dto.
     *
     * @param fileMgtIndexDTO the file mgt index dto
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
     * @param selectedItem the new selected item
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
     * @param detailsFlag the new details flag
     */
    public void setDetailsFlag(final char detailsFlag) {
        this.detailsFlag = detailsFlag;
    }
    CommonUtils commonUtil = new CommonUtils();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    /**
     * The customer group binder.
     */
    /**
     * Inits the.
     */
    public void init() {
        try {
            LOGGER.info("init Method initiated");

            addStyleName(Constants.bootstrap);
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
            LOGGER.info("init method Ended");
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        }

    }

//    private void addLineBtnLogic() {
//        addLine.addClickListener(new ClickListener() {
//            /**
//             * button click listener
//             */
//            public void buttonClick(final ClickEvent event) {
//
//                if (detailsFlag == 'N') {
//                    AbstractNotificationUtils.getErrorNotification("Details Error", "Please click on a record within the results list view");
//                    return;
//                }
//                final FileMananagementResultDTO child = new FileMananagementResultDTO();
//                if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
//                    child.setCheck(Boolean.FALSE);
//                    child.setYear(ConstantsUtils.EMPTY);
//                    child.setMonth(ConstantsUtils.EMPTY);
//                    child.setItemNo(ConstantsUtils.EMPTY);
//                    child.setItemName(ConstantsUtils.EMPTY);
//                    child.setStartDate(null);
//                    child.setPrice(ConstantsUtils.EMPTY);
//                    child.setUnits(ConstantsUtils.EMPTY);
//                    child.setDollars(ConstantsUtils.EMPTY);
//                    child.setInterfaceFlag(ConstantsUtils.Y);
//                    child.setRecordLockStatus(false);
//                    child.setForecastSystemId(0);
//                } else if (FileType.equals(ConstantsUtils.DEMAND)) {
//                    child.setCheck(Boolean.FALSE);
//                    child.setForecastType(ConstantsUtils.EMPTY);
//                    child.setForecastYear(ConstantsUtils.EMPTY);
//                    child.setForecastMonth(ConstantsUtils.EMPTY);
//                    child.setItemId(ConstantsUtils.EMPTY);
//                    child.setItemIdentifierCodeQualifier(ConstantsUtils.EMPTY);
//                    child.setItemIdentifier(ConstantsUtils.EMPTY);
//                    child.setBrandId(ConstantsUtils.EMPTY);
//                    child.setSegment(ConstantsUtils.EMPTY);
//                    child.setMarketSizeUnits(ConstantsUtils.EMPTY);
//                    child.setMarketShareUnits(ConstantsUtils.EMPTY);
//                    child.setMarketShareRatio(ConstantsUtils.EMPTY);
//                    child.setUncapturedUnits(ConstantsUtils.EMPTY);
//                    child.setUncapturedUnitsRatio(ConstantsUtils.EMPTY);
//                    child.setTotalDemandUnits(ConstantsUtils.EMPTY);
//                    child.setTotalDemandAmount(ConstantsUtils.EMPTY);
//                    child.setInventoryUnitChange(ConstantsUtils.EMPTY);
//                    child.setGrossUnits(ConstantsUtils.EMPTY);
//                    child.setGrossPrice(ConstantsUtils.EMPTY);
//                    child.setGrossAmount(ConstantsUtils.EMPTY);
//                    child.setNetSalesPrice(ConstantsUtils.EMPTY);
//                    child.setNetSalesAmount(ConstantsUtils.EMPTY);
//                    child.setBatchId(ConstantsUtils.EMPTY);
//                    child.setOrganizationKey(ConstantsUtils.EMPTY);
//                    child.setInterfaceFlag(ConstantsUtils.Y);
//                    child.setRecordLockStatus(false);
//                    child.setForecastSystemId(0);
//                } else if (((HelperDTO) fmFileType.getValue()).getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
//                    child.setCheck(Boolean.FALSE);
//                    child.setForecastYear(ConstantsUtils.EMPTY);
//                    child.setForecastMonth(ConstantsUtils.EMPTY);
//                    child.setItemId(ConstantsUtils.EMPTY);
//                    child.setCompanyId(ConstantsUtils.EMPTY);
//                    child.setUnits(ConstantsUtils.EMPTY);
//                    child.setPriceType(ConstantsUtils.EMPTY);
//                    child.setPrice(ConstantsUtils.EMPTY);
//                    child.setSalesAmount(ConstantsUtils.EMPTY);
//                    child.setSalesInclusion(ConstantsUtils.EMPTY);
//                    child.setDeductionId(ConstantsUtils.EMPTY);
//                    child.setDeductionCategory(ConstantsUtils.EMPTY);
//                    child.setDeductionType(ConstantsUtils.EMPTY);
//                    child.setDeductionProgramType(ConstantsUtils.EMPTY);
//                    child.setAdjustmentCode(ConstantsUtils.EMPTY);
//                    child.setDeductionRate(ConstantsUtils.EMPTY);
//                    child.setDeductionAmount(ConstantsUtils.EMPTY);
//                    child.setDeductionInclusion(ConstantsUtils.EMPTY);
//                    child.setForecastValueType(ConstantsUtils.EMPTY);
//                    child.setBrandId(ConstantsUtils.EMPTY);
//                    child.setSegment(ConstantsUtils.EMPTY);
//                    child.setBatchId(ConstantsUtils.EMPTY);
//                    child.setOrganizationKey(ConstantsUtils.EMPTY);
//                    child.setForecastVersion(ConstantsUtils.EMPTY);
//                    child.setCountry(ConstantsUtils.EMPTY);
//                    child.setForecastName(ConstantsUtils.EMPTY);
//                    child.setForecastDate(ConstantsUtils.EMPTY);
//                    child.setInterfaceFlag(ConstantsUtils.Y);
//                    child.setRecordLockStatus(false);
//                    child.setForecastSystemId(0);
//                } else {
//                    child.setCheck(Boolean.FALSE);
//                    child.setYear(ConstantsUtils.EMPTY);
//                    child.setMonth(ConstantsUtils.EMPTY);
//                    child.setWeek(ConstantsUtils.EMPTY);
//                    child.setDay(ConstantsUtils.EMPTY);
//                    child.setItemId(ConstantsUtils.EMPTY);
//                    child.setItemIdentifierCodeQualifier(ConstantsUtils.EMPTY);
//                    child.setItemIdentifier(ConstantsUtils.EMPTY);
//                    child.setUnitsWithdrawn(ConstantsUtils.EMPTY);
//                    child.setAmountWithdrawn(ConstantsUtils.EMPTY);
//                    child.setBatchId(ConstantsUtils.EMPTY);
//                    child.setOrganizationKey(ConstantsUtils.EMPTY);
//                    child.setInterfaceFlag(ConstantsUtils.Y);
//                    child.setRecordLockStatus(false);
//                    child.setForecastSystemId(0);
//                    if (!FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
//                        child.setCompanyId(ConstantsUtils.EMPTY);
//                        child.setIdentifierCodeQualifier(ConstantsUtils.EMPTY);
//                        child.setCompanyIdentifier(ConstantsUtils.EMPTY);
//                    }
//                }
//                detailsBean.addItem(child);
//                addlineList.add(child);
//                selectClose = true;
//                saveflag = false;
//                CommonUIUtils.getMessageNotification("Line Added Successfully");
//            }
//        });
//    }
//
//    private void removeBtnLogic() {
//
//        remove.addClickListener(new ClickListener() {
//            /**
//             * button click listener
//             */
//            public void buttonClick(final ClickEvent event) {
//                Boolean deleteFlag = true;
//                try {
//                    List<FileMananagementResultDTO> idList = new ArrayList<FileMananagementResultDTO>();
//                    for (final Iterator<?> iterator = detailsFilterTable.getItemIds().iterator(); iterator.hasNext();) {
//                        Object childId = iterator.next();
//                        FileMananagementResultDTO beanItem = getBeanFromId(childId);
//                        if (beanItem.getCheck()) {
//                            idList.add(beanItem);
//                        }
//                    }
//                    if (idList.size() > 0) {
//                        deleteFlag = false;
//                        selectClose = true;
//                        saveflag = false;
//                        for (FileMananagementResultDTO obj : idList) {
//                            if (detailsFilterTable.removeItem(obj)) {
//                                if (addlineList.contains(obj)) {
//                                    addlineList.remove(obj);
//                                } else {
//                                    obj.setCheck(false);
//                                    addlineList.add(obj);
//                                }
//                            }
//                        }
//                    }
//                    if (deleteFlag) {
//                        AbstractNotificationUtils.getErrorNotification("Remove Error", "Please select record to delete");
//                    }
//                } catch (Exception e) {
//                    LOGGER.error(e);
//                }
//            }
//        });
//    }
//
//    private void saveButton() {
//        save.addClickListener(new ClickListener() {
//            /**
//             * button click listener
//             */
//            public void buttonClick(final ClickEvent event) {
//                saveButtonLogic();
//            }
//        });
//    }
//
//    private void resetButtonLogic() {
//        /*
//         * To reset Mass update fields
//         * 
//         */
//        resetBtn.addClickListener(new Button.ClickListener() {
//            /**
//             * Called when a Button has been clicked .
//             *
//             */
//            @SuppressWarnings("PMD")
//            public void buttonClick(final Button.ClickEvent event) {
//                LOGGER.info("In reset.addClickListener started");
//                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
//                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
//                            /**
//                             * Adds the button click listener.
//                             *
//                             *
//                             */
//                            @SuppressWarnings("PMD")
//                            public void buttonClicked(final ButtonId buttonId) {
//                                if (buttonId.name().equals(ConstantsUtils.YES)) {
//                                    try {
//                                        for (int i = 0; i < addlineList.size(); i++) {
//                                            FileMananagementResultDTO addline = addlineList.get(i);
//                                            if (detailsBean.removeItem(addline)) {
//                                                addlineList.remove(addline);
//                                                i = i - 1;
//                                            }
//                                        }
//
//                                        for (FileMananagementResultDTO obj : addlineList) {
//                                            detailsBean.addItem(obj);
//                                        }
//                                        addlineList.clear();
//                                        detailsFilterTable.setColumnCheckBox(ConstantsUtils.CHECK, true, false);
//                                        checkAll(false);
//                                        value.setValue(ConstantsUtils.EMPTY);
//                                        fieldName.setValue(null);
//                                        massUpdate.select(ConstantsUtils.DISABLE);
//
//                                    } catch (Exception e) {
//                                        LOGGER.error(e);
//                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
//                                    }
//                                }
//                            }
//                        }, ButtonId.YES, ButtonId.NO);
//
//                LOGGER.info("In reset.addClickListener Ended");
//            }
//        });
//    }

//    private void populateButtonLogic() {
//
//        populate.addClickListener(new ClickListener() {
//
//            /**
//             * button click listener
//             */
//            public void buttonClick(final ClickEvent event) {
//                Boolean flag = true;
//                if (fieldName.getValue() == null) {
//                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please select Field Name");
//                    return;
//                } else {
//                    if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
//                        if (fieldName.getValue().equals(ConstantsUtils.CAPS_YEAR) || fieldName.getValue().equals(ConstantsUtils.CAPS_MONTH)) {
//                            if (value.getValue() == null || value.getValue().equals(ConstantsUtils.EMPTY)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter value");
//                                return;
//                            }
//                            if (!value.getValue().matches(ValidationUtils.NUMERIC_VALIDATION)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
//                                return;
//                            }
//                            if (fieldName.getValue().equals(ConstantsUtils.CAPS_MONTH)) {
//                                int forecastMonth = Integer.valueOf(value.getValue());
//                                if (forecastMonth > 12) {
//                                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter Valid Month");
//                                    return;
//                                }
//                            }
//                        }
//                        if (fieldName.getValue().equals(ConstantsUtils.CAPS_PRICE) || fieldName.getValue().equals(ConstantsUtils.CAPS_UNITS)) {
//                            if (value.getValue() == null || value.getValue().equals(ConstantsUtils.EMPTY)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter value");
//                            }
//                            if (!value.getValue().matches(ValidationUtils.PRICE)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
//                                return;
//                            }
//                        }
//                        if (fieldName.getValue().equals(ConstantsUtils.CAPS_STARTDATE)) {
//                            if (startDate.getValue() == null || startDate.getValue().equals(ConstantsUtils.EMPTY)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Select Start Date. Recognized Date format is mm/dd/yy");
//                                return;
//                            }
//                        }
//                        if (fieldName.getValue().equals(ConstantsUtils.CAPS_ITEMNO)) {
//                            if (itemNoSearch.getValue() == null || itemNoSearch.getValue().equals(ConstantsUtils.EMPTY)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter Item No");
//                                return;
//                            }
//                        }
//                    } else if (FileType.equals(ConstantsUtils.DEMAND)) {
//                        if (fieldName.getValue().equals("Market Share Ratio") || fieldName.getValue().equals("Market Size Units")
//                                || fieldName.getValue().equals("Market Share Units") || fieldName.getValue().equals("Uncaptured Units")
//                                || fieldName.getValue().equals("Total Demand Units") || fieldName.getValue().equals("Total Demand Amount")) {
//                            if (value.getValue() == null || value.getValue().equals(ConstantsUtils.EMPTY)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter value");
//                            }
//                            if (!value.getValue().matches(ValidationUtils.PRICE)) {
//                                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
//                                return;
//                            }
//                        }
//                    }
//
//                    final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
//                    if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
//                        for (int i = 0; i < itemIds.size(); i++) {
//                            final FileMananagementResultDTO beanItem = itemIds.get(i);
//                            if (beanItem.getCheck()) {
//                                flag = false;
//                                if (fieldName.getValue().equals(ConstantsUtils.CAPS_YEAR)) {
//                                    if (detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.INTERFACE_FLAG).getValue().equals("Y")) {
//                                        detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.YEAR).setValue(value.getValue().toString());
//                                    }
//                                } else if (fieldName.getValue().equals(ConstantsUtils.CAPS_MONTH)) {
//                                    int forecastMonth = Integer.valueOf(value.getValue());
//                                    if (forecastMonth > 12) {
//                                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter Valid ");
//                                    }
//                                    if (detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.INTERFACE_FLAG).getValue().equals("Y")) {
//                                        detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.MONTH).setValue(value.getValue());
//                                    }
//                                } else if (fieldName.getValue().equals(ConstantsUtils.CAPS_ITEMNO)) {
//                                    if (detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.INTERFACE_FLAG).getValue().equals("Y")) {
//                                        detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.ITEM_NO).setValue(itemNoSearch.getValue());
//                                        detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.ITEM_NAME).setValue(lookupItemName.getValue().toString());
//                                    }
//                                } else if (fieldName.getValue().equals(ConstantsUtils.CAPS_STARTDATE)) {
//                                    if (detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.INTERFACE_FLAG).getValue().equals("Y")) {
//                                        detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.START_DATE).setValue(startDate.getValue());
//                                    }
//                                }
//                                if (fieldName.getValue().equals(ConstantsUtils.CAPS_PRICE)) {
//                                    detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.PRICE).setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals(ConstantsUtils.CAPS_UNITS)) {
//                                    detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.UNITS).setValue(value.getValue().toString());
//                                }
//                            }
//                        }
//
//                    } else if (FileType.equals(ConstantsUtils.DEMAND)) {
//                        for (int i = 0; i < itemIds.size(); i++) {
//                            final FileMananagementResultDTO beanItem = itemIds.get(i);
//                            flag = false;
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Market Share Ratio")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "marketShareRatio").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Market Size Units")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "marketSizeUnits").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Market Share Units")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "marketShareUnits").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Uncaptured Units")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "uncapturedUnits").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Total Demand Units")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "totalDemandUnits").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Total Demand Amount")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "totalDemandAmount").setValue(value.getValue().toString());
//                                }
//                            }
//                        }
//                    } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
//                        for (int i = 0; i < itemIds.size(); i++) {
//                            final FileMananagementResultDTO beanItem = itemIds.get(i);
//                            flag = false;
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Units Withdrawn")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "unitsWithdrawn").setValue(value.getValue().toString());
//                                }
//                            }
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Amount Withdrawn")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "amountWithdrawn").setValue(value.getValue().toString());
//                                }
//                            }
//                        }
//                    } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
//                        for (int i = 0; i < itemIds.size(); i++) {
//                            final FileMananagementResultDTO beanItem = itemIds.get(i);
//                            flag = false;
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Units Withdrawn")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "unitsWithdrawn").setValue(value.getValue().toString());
//                                }
//                            }
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Amount Withdrawn")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "amountWithdrawn").setValue(value.getValue().toString());
//                                }
//                            }
//                        }
//                    } else if (((HelperDTO) fmFileType.getValue()).getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
//                        for (int i = 0; i < itemIds.size(); i++) {
//                            final FileMananagementResultDTO beanItem = itemIds.get(i);
//                            flag = false;
//                            if (beanItem.getCheck()) {
//                                if (fieldName.getValue().equals("Deduction Rate")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "deductionRate").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Deduction Amount")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "deductionAmount").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Price")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "price").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Sales Amount")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "salesAmount").setValue(value.getValue().toString());
//                                }
//                                if (fieldName.getValue().equals("Units")) {
//                                    detailsFilterTable.getContainerProperty(beanItem, "units").setValue(value.getValue().toString());
//                                }
//                            }
//                        }
//                    }
//
//                }
//                if (flag) {
//                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Select Atleast one Record in details section");
//                }
//            }
//        });
//    }

    /**
     * Results item click.
     *
     * @param objectid the objectid
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void resultsItemClick(final Object objectid) throws SystemException, PortalException, Exception {
        LOGGER.info("resultsItemClick method started ");
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
                targetItem = new BeanItem<FileMananagementResultDTO>((FileMananagementResultDTO) objectid);
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

        LOGGER.info("resultsItemClick method Ended ");
    }

    /**
     * Details item click.
     *
     * @param objectid the objectid
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void detailsItemClick(final Object objectid) throws SystemException, PortalException, Exception {
        LOGGER.info("resultsItemClick method started ");
        if (objectid == null) {
            selectedItem = null;
        } else {
            selectedItem = detailsFilterTable.getItem(objectid);
        }
        LOGGER.info("resultsItemClick method Ended ");
    }

//    public void checkAll(boolean check) {
//        final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
//        for (int i = 0; i < itemIds.size(); i++) {
//            final FileMananagementResultDTO beanItem = itemIds.get(i);
//            if (!beanItem.isRecordLockStatus()) {
//                detailsFilterTable.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(check);
//            }
//        }
//    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.info("configureFields method initiated ");
        horizLayout.addStyleName("comboBox-Allignment");
        summaryPanel.addStyleName("excepttable");
//        massUpdatePanel.addStyleName("excepttable");
        helperFileType = (fileselectiondto.getHelperfileType());
        fileType.setValue(FileType);

        country.addItem(ConstantsUtils.COUNTRY_US);
        country.addItem(ConstantsUtils.COUNTRY_PR);
        country.select(ConstantsUtils.COUNTRY_US);
        country.setImmediate(true);
       
//        fieldName.setImmediate(true);
//        value.setImmediate(true);
//        populate.setImmediate(true);
//        startDate.setVisible(false);
//        itemNoSearch.addStyleName("searchText");
//        itemNoSearch.setVisible(false);
//        if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
//            fieldName.addItem(ConstantsUtils.CAPS_YEAR);
//            fieldName.addItem(ConstantsUtils.CAPS_MONTH);
//            fieldName.addItem(ConstantsUtils.CAPS_ITEMNO);
//            fieldName.addItem(ConstantsUtils.CAPS_STARTDATE);
//            fieldName.addItem(ConstantsUtils.CAPS_PRICE);
//            fieldName.addItem(ConstantsUtils.CAPS_UNITS);
//        } else if (FileType.equals(ConstantsUtils.DEMAND)) {
//            fieldName.addItem("Market Size Units");
//            fieldName.addItem("Market Share Ratio");
//            fieldName.addItem("Market Share Units");
//            fieldName.addItem("Uncaptured Units");
//            fieldName.addItem("Uncaptured Units Ratio");
//            fieldName.addItem("Total Demand Units");
//            fieldName.addItem("Total Demand Amount");
//        } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
//            fieldName.addItem("Units Withdrawn");
//            fieldName.addItem("Amount Withdrawn");
//        } else if (FileType.equals(ConstantsUtils.CUSTOMERGTS)) {
//            fieldName.addItem("Deduction Rate");
//            fieldName.addItem("Deduction Amount");
//            fieldName.addItem("Price");
//            fieldName.addItem("Sales Amount");
//            fieldName.addItem("Units");
//        }
//
//        massUpdate.addItem(ConstantsUtils.ENABLE);
//        massUpdate.addItem(ConstantsUtils.DISABLE);
//        massUpdate.setImmediate(true);
//
//        massUpdate.select(ConstantsUtils.DISABLE);
//        populate.setEnabled(false);
//        value.setEnabled(false);
//        fieldName.setEnabled(false);
//
//        fieldName.setNullSelectionAllowed(true);
//        fieldName.setInputPrompt(ConstantsUtils.SELECT_ONE);
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
        forecastYear.setImmediate(true);
        fromDate.setImmediate(true);
        toDate.setImmediate(true);
        fileType.setImmediate(true);
        country.setImmediate(true);

        forecastYear.setNullSelectionAllowed(true);
        forecastYear.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        forecastYear.setPageLength(7);
        forecastYear.setInputPrompt(ConstantsUtils.SELECT_ONE);
        forecastYear.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        forecastYear.markAsDirty();
        forecastYear.select(dto);
        forecastYear.setImmediate(true);
        SearchForecastddlb.setImmediate(true);
        SearchForecastddlb.setEnabled(false);
        SearchForecastddlb.setNullSelectionAllowed(true);
        SearchForecastddlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            SearchForecastddlb.setPageLength(7);
            SearchForecastddlb.addItem(ConstantsUtils.SELECT_ONE);
            SearchForecastddlb.select(ConstantsUtils.SELECT_ONE);
            SearchForecastddlb.markAsDirty();
            SearchForecastddlb.setEnabled(true);
            getSearchForecastyear();
            fileType.setReadOnly(true);
//        country.setReadOnly(true);


            type.addValidator(new StringLengthValidator("Type length should be less than 50 characters", 0, 50, true));
            type.setValidationVisible(true);
            type.addValidator(new RegexpValidator("([0-9|a-z|A-Z|*\\\\ ])*", "Type can only be Alphanumeric"));
            fileName.addValidator(new StringLengthValidator("FileName length should be less than 50 characters", 0, 50, true));
            fileName.setValidationVisible(true);
            fileName.addValidator(new RegexpValidator("([0-9|a-z|A-Z|*\\\\ ])*", "FileName can only be Alphanumeric"));
            version.addValidator(new StringLengthValidator("Version length should be less than 50 characters", 0, 50, true));
            version.setValidationVisible(true);
            version.addValidator(new RegexpValidator("([0-9|a-z|A-Z|*\\\\ ])*", "Version can only be Alphanumeric"));

            getForecastYear();
            searchButton();
            resetButton();
            detailsButton();
            closeButton();
            excelExport();
            selectButton();
//        saveButton();
//        populateButtonLogic();
//        addLineBtnLogic();
//        removeBtnLogic();
//        resetButtonLogic();
        makeSummaryReadOnly();

        toDate.addValidator(new FileManagementLookup.DateValidator("Created Date From should be before Created Date To"));
        fromDate.setValidationVisible(true);
        toDate.setValidationVisible(true);

        LOGGER.info("configureFields ended ");

//        massUpdate.addValueChangeListener(new Property.ValueChangeListener() {
//            /**
//             * This method listens to data source value changes and passes the
//             * changes forwards.
//             */
//            public void valueChange(final Property.ValueChangeEvent event) {
//                LOGGER.info("In configureFields levelValueReference.addValueChangeListener started");
//                if (event != null) {
//                    if (String.valueOf(event.getProperty().getValue()).equals(ConstantsUtils.ENABLE)) {
//                        fieldName.setEnabled(true);
//                        value.setEnabled(true);
//                        populate.setEnabled(true);
//                        startDate.setEnabled(true);
//                        itemNoSearch.setEnabled(true);
//                    } else {
//                        fieldName.setEnabled(false);
//                        value.setEnabled(false);
//                        populate.setEnabled(false);
//                        startDate.setEnabled(false);
//                        itemNoSearch.setEnabled(false);
//                    }
//                }
//                LOGGER.info("In configureFields levelValueReference.addValueChangeListener Ended");
//            }
//        });
//
//        fieldName.addValueChangeListener(new Property.ValueChangeListener() {
//            /**
//             * This method listens to data source value changes and passes the
//             * changes forwards.
//             */
//            public void valueChange(final Property.ValueChangeEvent event) {
//                LOGGER.info("In configureFields levelValueReference.addValueChangeListener started");
//                if (event != null) {
//                    if (String.valueOf(event.getProperty().getValue()).equals(ConstantsUtils.CAPS_ITEMNO)) {
//                        itemNoSearch.setVisible(true);
//                        value.setVisible(false);
//                        startDate.setVisible(false);
//                        value.setValue(ConstantsUtils.EMPTY);
//                        itemNoSearch.setValue(ConstantsUtils.EMPTY);
//                    } else if (String.valueOf(event.getProperty().getValue()).equals(ConstantsUtils.CAPS_STARTDATE)) {
//                        itemNoSearch.setVisible(false);
//                        value.setVisible(false);
//                        startDate.setVisible(true);
//                    } else {
//                        itemNoSearch.setVisible(false);
//                        value.setVisible(true);
//                        startDate.setVisible(false);
//                        value.setValue(ConstantsUtils.EMPTY);
//                        itemNoSearch.setValue(ConstantsUtils.EMPTY);
//                    }
//                }
//                LOGGER.info("In configureFields levelValueReference.addValueChangeListener Ended");
//            }
//        });

//        itemNoSearch.addClickListener(new CustomTextField.ClickListener() {
//            /**
//             * Called when a Button has been clicked .
//             *
//             */
//            @SuppressWarnings("PMD")
//            public void click(CustomTextField.ClickEvent event) {
//                final ItemSearchLookup lookUp = new ItemSearchLookup(itemNoSearch, lookupItemName, sessionDTO);
//                try {
//                    lookUp.init();
//                } catch (Exception ex) {
//                    java.util.logging.Logger.getLogger(FileManagementLookup.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                UI.getCurrent().addWindow(lookUp);
//                lookUp.addCloseListener(new Window.CloseListener() {
//                    public void windowClose(final Window.CloseEvent e) {
//                    }
//                });
//            }
//        });
        LOGGER.info("Ending addItemDetailsTable");

    }

    /**
     * Gets the forecast year.
     *
     * @return the forecast year
     */
    private void getForecastYear() {
        try {
            LOGGER.info("In getForecastYear started");
            final LazyContainer results = new LazyContainer(HelperDTO.class, new ForecastYearContainer(), new ForecastYearCriteria());
            results.setMinFilterLength(0);
            forecastYear.setContainerDataSource(results);
            forecastYear.select(dto);
            LOGGER.info("In getForecastYear Ended");
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4008));
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
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In searchButton searchButtonClickLogic started");
                try {
                    searchButtonClickLogic(event);
                } catch (SystemException e) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.info("In searchButton searchButtonClickLogic Ended");
            }
        });
    }

    /**
     * Search button click logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void searchButtonClickLogic(final Button.ClickEvent event) throws SystemException, PortalException, Exception {
        LOGGER.info("entering searchButtonClickLogic");
        final FileManagementLogic fmLogic = new FileManagementLogic();
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
        if (forecastYear.getValue() == null || ConstantsUtils.SELECT_ONE.equals(forecastYear.getValue().toString())) {
            forcaste = StringUtils.EMPTY;
        } else {
            forcaste = forecastYear.getValue().toString();
        }
        if (SearchForecastddlb.getValue() == null || ConstantsUtils.SELECT_ONE.equals(SearchForecastddlb.getValue().toString())) {
            SearchforecastYear = StringUtils.EMPTY;
        } else {
            SearchforecastYear = SearchForecastddlb.getValue().toString();
        }
        if (StringUtils.isEmpty(fileName.getValue().trim()) && StringUtils.isEmpty(type.getValue().trim()) && StringUtils.isEmpty(version.getValue().trim()) && (forcaste == null || forcaste.equals(ConstantsUtils.EMPTY)) && fromDate.getValue() == null && toDate.getValue() == null && SearchforecastYear.equals("")) {
            MessageBox.showPlain(Icon.ERROR, "Search Error", "Please Enter a value within text boxes of the Search Criteria", ButtonId.OK);
        } /*  else if (isContainsSpecialChar(version.getValue())) {
         MessageBox.showPlain(Icon.ERROR, "Search Error", " Version can contain only digits,alphabets", ButtonId.OK);
         } else if (isContainsSpecialChar(fileName.getValue())) {
         MessageBox.showPlain(Icon.ERROR, "Search Error", " File name can contain only digits,alphabets", ButtonId.OK);
         } else if (isContainsSpecialChar(type.getValue())) {
         MessageBox.showPlain(Icon.ERROR, "Search Error", " Type can contain only digits,alphabets", ButtonId.OK);
         } */ else {
            resultDTO.setFileType(String.valueOf(fileType.getValue()));
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
                MessageBox.showPlain(Icon.INFO, "No Results Found", "No results were found that match the entered search criteria.", ButtonId.OK);
            } else {
                CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);
            }
        }
        makeSummaryReadOnly();
        LOGGER.info("searchButtonClickLogic method Ended");
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
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In detailsButton detailsButtonClickLogic started");
                try {
                    if (ConstantsUtils.EMPTY.equals(fileNameList.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Details Error", "Please click on a record within the results list view");
                    } else {
                        addlineList.clear();
                        detailsButtonClickLogic();
//                        List<Object> propertyIdList = Arrays.asList(detailsFilterTable.getVisibleColumns());
//                        for (Object propertyId : propertyIdList) {
//                            formatData(propertyId);
//                        }
                    }
                } catch (SystemException e) {
                    String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4009));
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4009));
                }
                LOGGER.info("In detailsButton detailsButtonClickLogic Ended");
            }
        });

    }

    /**
     * Details button click logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void detailsButtonClickLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("entering detailsButtonClickLogic methhod");

        try {
            final FileManagementLogic fmLogic = new FileManagementLogic();
            String finalVersion;
            String etlVersion = ConstantsUtils.EMPTY;
            String selectedVersion = versionList.getValue();
            if (selectedVersion.contains(".")) {
                String[] array = selectedVersion.split("\\.");
                etlVersion = array[0];
                finalVersion = etlVersion + "~" + selectedVersion;
            } else {
                etlVersion = selectedVersion;
                finalVersion = selectedVersion;
            }
//            String latestVersion = fmLogic.getLatestVersion(country.getValue().toString(), fileNameList.getValue(), selectedYear, FileType, etlVersion, selectedFile);
//            addLine.setEnabled(true);
//            remove.setEnabled(true);
//            save.setEnabled(true);
//            if (!latestVersion.equals(versionList.getValue())) {
//                addLine.setEnabled(false);
//                remove.setEnabled(false);
//                save.setEnabled(false);
//            }
            detailsBean.removeAllItems();

            detailsResultDTO.setFileName(String.valueOf(fileNameList.getValue()));
            detailsResultDTO.setVersion(finalVersion);
            detailsResultDTO.setFileType(String.valueOf(fileType.getValue()));
            detailsResultDTO.setCountry(String.valueOf(country.getValue()));
            detailsResultDTO.setHelperType(FileType);

            detailstableLogic.configureSearchData(detailsResultDTO);
            detailsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
            detailsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
            detailsFilterTable.setImmediate(true);
            detailsFilterTable.setWidth(100, UNITS_PERCENTAGE);
          
//            detailsFilterTable.addStyleName("TableCheckBox");
            detailsFilterTable.setSelectable(true);
            detailsFilterTable.markAsDirtyRecursive();

            if (tableLogic.isResultsEmpty()) {
                CommonUIUtils.getMessageNotification(ConstantsUtils.NO_RESULSTS);
            } else {
                if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                    final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), FileType, String.valueOf(country.getValue()));
                    changeReadOnlyState();
                    forecastName.setValue(fileManageDTO.getForecastName());
                    forecastVersion.setValue(fileManageDTO.getForecastVersion());
                    forecastDate.setValue(fileManageDTO.getForecastDate());
                    createdDate.setValue(fileManageDTO.getCreatedDate());
                    makeSummaryReadOnly();
                    detailsFlag = 'Y';
                } else if (FileType.equals(ConstantsUtils.DEMAND) || (FileType).equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
                    final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), FileType, String.valueOf(country.getValue()));
                    changeReadOnlyState();
                    forecastName.setValue(fileManageDTO.getForecastName());
                    forecastVersion.setValue(fileManageDTO.getForecastVersion());
                    createdDate.setValue(fileManageDTO.getCreatedDate());
                    makeSummaryReadOnly();
                    detailsFlag = 'Y';
                } else if (FileType.equals(ConstantsUtils.DEMAND) || (FileType).equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL) || (FileType).equals(ConstantsUtils.CUSTOMERGTS)) {
                    final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), FileType, String.valueOf(country.getValue()));
                    changeReadOnlyState();
                    forecastName.setValue(fileManageDTO.getForecastName());
                    forecastVersion.setValue(fileManageDTO.getForecastVersion());
                    createdDate.setValue(fileManageDTO.getCreatedDate());
                    makeSummaryReadOnly();
                    detailsFlag = 'Y';
            } else if (FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
                final FileManagementDTO fileManageDTO = fmLogic.getDetailsSumm(String.valueOf(fileNameList.getValue()), String.valueOf(versionList.getValue()), FileType, String.valueOf(country.getValue()));
                changeReadOnlyState();
                forecastName.setValue(fileManageDTO.getForecastName());
                forecastVersion.setValue(fileManageDTO.getForecastVersion());
                createdDate.setValue(fileManageDTO.getCreatedDate());
                makeSummaryReadOnly();
                detailsFlag = 'Y';
                }

            }
        } catch (Exception e) {
            LOGGER.error(e);

        }

        LOGGER.info("detailsButtonClickLogic method Ended");
    }

    /**
     * Close button.
     */
    public void closeButton() {
        LOGGER.info("closeButton method started");

        close.addClickListener(new Button.ClickListener() {
            /**
             * @ Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                if (selectClose == true && saveflag == false) {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to close the File Lookup ?\n"
                            + " Any changes you have made will not be saved.", new MessageBoxListener() {
                                /**
                                 * Adds the button click listener.
                                 *
                                 *
                                 */
                                @SuppressWarnings("PMD")
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

        LOGGER.info("closeButton method Ended");
    }

    /**
     * Excel export.
     */
    public void excelExport() {
        LOGGER.info("excelExport method started");
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
                LOGGER.info("In configureFields excelExportResult.addClickListener started");

                try {
                     ConsolidatedFinancialForecastUI.EXCEL_CLOSE=true;
                    configureExcelResultTable();
                    loadExcelTable(resultDTO);
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), "File Management Results", "File Management Results", "FileManagementResults.xls", false);
                    excel.export();
                    tableLayout.removeComponent(excelTable);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.info("In configureFields excelExportResult.addClickListener Ended");
            }
        });
        excelExportDetail.addClickListener(new ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            @Override
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields excelExportDetail.addClickListener started");
                try {
                     ConsolidatedFinancialForecastUI.EXCEL_CLOSE=true;
                    configureExcelDetailsTable();
                    loadExcelDetailsTable(detailsResultDTO);
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelDetailsTable), "File Management Details", "File Management Details", "FileManagemenDetails.xls", false);
                    excel.export();
                    detailsTable.removeComponent(excelDetailsTable);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.info("In configureFields excelExportDetail.addClickListener Ended");
            }
        });
        LOGGER.info("excelExport method Ended");
    }

    /**
     * Select button.
     */
    public void selectButton() {
        LOGGER.info("selectButton method started");
        select.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In select.addClickListener started");
                try {
                    if (ConstantsUtils.EMPTY.equals(fileNameList.getValue()) || fileNameList.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Select Error", "Please click on a record within the results list view");
                    } else {
                        final SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
                        if (selectClose == true && saveflag == false) {
                            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "File/version has been updated but has not be saved. "
                                    + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                                        /**
                                         * Adds the button click listener.
                                         *
                                         *
                                         */
                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                      
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                             try {
                                                 saveButtonLogic();
                                                 searchContainer.getContainerProperty(itemId, "fileName").setValue(fileNameList.getValue());
                                                 searchContainer.getContainerProperty(itemId, "activeFromDate").setValue(df.format(fileMgtDTO.getFromDate()));
                                                 searchContainer.getContainerProperty(itemId, "activeToDate").setValue(df.format(fileMgtDTO.getToDate()));
                                                 searchContainer.getContainerProperty(itemId, "fileManagementSid").setValue(null);
                                                 searchContainer.getContainerProperty(itemId, "fileChanged").setValue(true);
                                                 searchContainer.getContainerProperty(itemId, "version").setValue(fileMgtDTO.getVersion());
                                                 fileMgtIndexDTO.setVersion(fileMgtDTO.getVersion());
                                                 fileMgtIndexDTO.setFileName(fileMgtDTO.getFileName());
                                                 fileMgtIndexDTO.setFileType(fileMgtDTO.getFileType());
                                                 fileMgtIndexDTO.setType(fileMgtDTO.getType());
                                                 close();
                                             } catch (Exception ex) {
                                                 java.util.logging.Logger.getLogger(FileManagementLookup.class.getName()).log(Level.SEVERE, null, ex);
                                             }
                                            }
                                            if (buttonId.name().equals("NO")) {
                                                close();
                                            }
                                        }
                                    }, ButtonId.YES, ButtonId.NO);

                        } else {

                            searchContainer.getContainerProperty(itemId, "fileName").setValue(fileNameList.getValue());
                            searchContainer.getContainerProperty(itemId, "activeFromDate").setValue(fileMgtDTO.getFromDate());
                            searchContainer.getContainerProperty(itemId, "activeToDate").setValue(fileMgtDTO.getToDate());
                            searchContainer.getContainerProperty(itemId, "fileManagementSid").setValue(null);
                            searchContainer.getContainerProperty(itemId, "fileChanged").setValue(true);
                            searchContainer.getContainerProperty(itemId, "version").setValue(fileMgtDTO.getVersion());
                            selectFile.setValue(String.valueOf(fileNameList.getValue()));
                            fileMgtIndexDTO.setVersion(fileMgtDTO.getVersion());
                            fileMgtIndexDTO.setFileName(fileMgtDTO.getFileName());
                            fileMgtIndexDTO.setFileType(fileMgtDTO.getFileType());
                            fileMgtIndexDTO.setType(fileMgtDTO.getType());
                            close();
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
                }
                LOGGER.info("In select.addClickListener Ended");
            }
        });

        LOGGER.info("selectButton method Ended");
    }

    /**
     * Reset button.
     */
    private void resetButton() {
        LOGGER.info("resetButton method started");

        reset.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In reset.addClickListener started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                            /**
                             * Adds the button click listener.
                             *
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    try {
                                        fileName.setValue(ConstantsUtils.EMPTY);
                                        type.setValue(ConstantsUtils.EMPTY);
                                        version.setValue(ConstantsUtils.EMPTY);
                                        forecastYear.select(dto);
                                        fromDate.setValue(null);
                                        toDate.setValue(null);
                                        SearchForecastddlb.select(ConstantsUtils.SELECT_ONE);
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                LOGGER.info("In reset.addClickListener Ended");
            }
        });

        LOGGER.info("resetButton method Ended");
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
        LOGGER.info("makeSummaryReadOnly method started");
        forecastName.setReadOnly(true);
        forecastVersion.setReadOnly(true);
        forecastDate.setReadOnly(true);
        createdDate.setReadOnly(true);
        LOGGER.info("makeSummaryReadOnly method Ended");
    }

    /**
     * To change the read-only state of summary fields.
     */
    private void changeReadOnlyState() {
        LOGGER.info("changeReadOnlyState method started");
        forecastName.setReadOnly(false);
        forecastVersion.setReadOnly(false);
        forecastDate.setReadOnly(false);
        createdDate.setReadOnly(false);
        LOGGER.info("changeReadOnlyState method Ended");
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the id
     * @return the bean from id
     */
    public FileMananagementResultDTO getBeanFromId(final Object obj) {
        LOGGER.info("Entering getBeanFromId method");

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof FileMananagementResultDTO) {
            targetItem = new BeanItem<FileMananagementResultDTO>((FileMananagementResultDTO) obj);
        }
        LOGGER.info("End of getBeanFromId method");
        return (FileMananagementResultDTO) targetItem.getBean();
    }

    private void resetLogic() {
        LOGGER.info("resetLogic method started");
//        itemNoSearch.setValue(ConstantsUtils.EMPTY);
//        value.setValue(ConstantsUtils.EMPTY);
//        fieldName.setValue(null);
//        startDate.setValue(null);
//        startDate.setVisible(false);
//        value.setVisible(true);
//        itemNoSearch.setVisible(false);
        LOGGER.info("resetLogic method Ended");

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
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.info("validate Method started");
            if (fromDate.getValue() != null && toDate.getValue() != null) {
                if (fromDate.getValue().after(toDate.getValue())) {
                    toDate.setValue(null);
                    AbstractNotificationUtils.getErrorNotification("Search Error", "Created Date To should be greater than Created Date From");

                } else if (fromDate.getValue().equals(toDate.getValue())) {
                    toDate.setValue(null);
                    AbstractNotificationUtils.getErrorNotification("Search Error", "Created Date To should be greater than Created Date From");
                }
            }
            LOGGER.info("validate Method Ended");
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("isValidValue Method started");
            boolean flag;
            if (fromDate.getValue() == null || toDate.getValue() == null) {
                flag = true;
            } else {
                flag = fromDate.getValue().compareTo(toDate.getValue()) <= 0;
            }
            LOGGER.info("isValidValue Method returns true");
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
        LOGGER.info("Enters Inside Save Button Logic");
        Boolean changeFlag = false;
        final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
        final List<FileMananagementResultDTO> insertionItemIds = new ArrayList<FileMananagementResultDTO>();
        List<Integer> currentSystemId = new ArrayList<Integer>();
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
                String itemId = beanItem.getItemId();
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
                    if (year.equals(currentYear) && (FileType.equals(ConstantsUtils.EX_FACTORY_SALES))) {
                        if (month.equals(currentMonth)) {
                            if (itemName.equals(currentItemName)) {
                                AbstractNotificationUtils.getErrorNotification("Details Error", "Unique combination error");
                                return;
                            }
                        }
                    }
                    if (currentForecastType.equals(forecastType) &&((FileType.equals(ConstantsUtils.DEMAND)) || (FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) )) {
                        if (currentForecastYear.equals(forecastYear)) {
                            if (currentForecastMonth.equals(forecastMonth)) {
                                if (currentItemId.equals(itemId)) {
                                    if (currentOrganisationKey.equals(organisationKey)) {
                                        AbstractNotificationUtils.getErrorNotification("Details Error", "Unique combination error");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    if (currentYear.equals(forecastType) && FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
                        if (currentMonth.equals(month)) {
                            if (currentDay.equals(day)) {
                                if (currentWeek.equals(week)) {
                                    if (currentItemId.equals(itemId)) {
                                        if (currentBatchId.equals(batchId)) {
                                            if (currentOrganisationKey.equals(organisationKey)) {
                                                AbstractNotificationUtils.getErrorNotification("Details Error", "Unique combination error");
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (currentYear.equals(forecastType) && FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                        if (currentMonth.equals(month)) {
                            if (currentDay.equals(day)) {
                                if (currentWeek.equals(week)) {
                                    if (currentCompanyId.equals(companyId)) {
                                        if (currentItemId.equals(itemId)) {
                                            if (currentBatchId.equals(batchId)) {
                                                if (currentOrganisationKey.equals(organisationKey)) {
                                                    AbstractNotificationUtils.getErrorNotification("Details Error", "Unique combination error");
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < itemIds.size(); i++) {
                final FileMananagementResultDTO beanItem = itemIds.get(i);
                if (!beanItem.isRecordLockStatus()) {

                    if (((beanItem.getYear().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getMonth().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getUnits().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getPrice().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getDollars().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getItemNo().toString().equals(ConstantsUtils.EMPTY))
                            || (beanItem.getItemName().toString().equals(ConstantsUtils.EMPTY)))
                            && FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter the value at all fields");
                        return;
                    }
                    currentSystemId.add(beanItem.getForecastSystemId());
                    if (beanItem.getForecastSystemId() == 0
                            && (FileType.equals(ConstantsUtils.DEMAND)
                            || FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)
                            || FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
                            || FileType.equals(ConstantsUtils.CUSTOMERGTS)
                            || FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL))) {
                        insertionItemIds.add(beanItem);
                    }
                }
            }
            if (currentSystemId.contains(0)) {
                changeFlag = true;
            }
            DynamicQuery dynamicQuery;

            final HashMap savedForecast = new HashMap();
            List<Integer> existingSystemId = new ArrayList<Integer>();

            if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class
                );
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORECAST_YEAR, String.valueOf(selectedYear)));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME, fileNameList.getValue().toString()));
                List<ForecastingMaster> listToRemove = ForecastingMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (final Iterator<ForecastingMaster> iterator = listToRemove.iterator();
                        iterator.hasNext();) {
                    final ForecastingMaster itemDetail = iterator.next();
                    existingSystemId.add(itemDetail.getForecastMasterSid());
                    savedForecast.put(itemDetail.getForecastMasterSid(), itemDetail);
                }
            } else if (FileType.equals(ConstantsUtils.DEMAND)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(DemandForecast.class
                );
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME, fileNameList.getValue().toString()));
                List<DemandForecast> listToRemove = DemandForecastLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (final Iterator<DemandForecast> iterator = listToRemove.iterator();
                        iterator.hasNext();) {
                    final DemandForecast itemDetail = iterator.next();
                    existingSystemId.add(itemDetail.getDemandForecastSid());
                    savedForecast.put(itemDetail.getDemandForecastSid(), itemDetail);
                }
            } else if (FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(AdjustedDemandForecast.class);
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME, fileNameList.getValue().toString()));
                List<AdjustedDemandForecast> listToRemove = AdjustedDemandForecastLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (final Iterator<AdjustedDemandForecast> iterator = listToRemove.iterator();
                        iterator.hasNext();) {
                    final AdjustedDemandForecast itemDetail = iterator.next();
                    existingSystemId.add(itemDetail.getAdjustedDemandForecastSid());
                    savedForecast.put(itemDetail.getAdjustedDemandForecastSid(), itemDetail);
                }
            } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                List<String> query = new ArrayList<String>();
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
            } else if (FileType.equals(ConstantsUtils.CUSTOMERGTS)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsForecast.class
                );
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, versionList.getValue().toString()));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME, fileNameList.getValue().toString()));
                List<CustomerGtsForecast> listToRemove = CustomerGtsForecastLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (final Iterator<CustomerGtsForecast> iterator = listToRemove.iterator();
                        iterator.hasNext();) {
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
                                    && FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                            } else if (beanItem.getForecastType().equals(beanItem.getHiddenForecastType())
                                    && beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
                                    && beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
                                    && beanItem.getItemId().equals(beanItem.getHiddenItemId())
                                    && beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
                                    && FileType.equals(ConstantsUtils.DEMAND)) {

                            } else if (beanItem.getForecastType().equals(beanItem.getHiddenForecastType())
                                    && beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
                                    && beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
                                    && beanItem.getItemId().equals(beanItem.getHiddenItemId())
                                    && beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
                                    && FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {

                            } else if (beanItem.getYear().equals(beanItem.getHiddenYear())
                                    && beanItem.getMonth().equals(beanItem.getHiddenMonth())
                                    && beanItem.getDay().equals(beanItem.getHiddenDay())
                                    && beanItem.getWeek().equals(beanItem.getHiddenWeek())
                                    && beanItem.getItemId().equals(beanItem.getHiddenItemId())
                                    && beanItem.getBatchId().equals(beanItem.getHiddenbatchId())
                                    && beanItem.getOrganizationKey().equals(beanItem.getHiddenOrganisationKey())
                                    && FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {

                            } else if (beanItem.getYear().equals(beanItem.getHiddenYear())
                                    && beanItem.getMonth().equals(beanItem.getHiddenMonth())
                                    && beanItem.getDay().equals(beanItem.getHiddenDay())
                                    && beanItem.getWeek().equals(beanItem.getHiddenWeek())
                                    && beanItem.getCompanyId().equals(beanItem.getHiddenCompanyId())
                                    && beanItem.getItemId().equals(beanItem.getHiddenItemId())
                                    && beanItem.getBatchId().equals(beanItem.getHiddenbatchId())
                                    && beanItem.getOrganizationKey().equals(beanItem.getHiddenOrganisationKey())
                                    && FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {

                            } else if (beanItem.getForcastYear().equals(beanItem.getHiddenForecastYear())
                                    && beanItem.getForecastMonth().equals(beanItem.getHiddenForecastMonth())
                                    && beanItem.getItemId().equals(beanItem.getHiddenItemId())
                                    && beanItem.getOrganizationKey().equals(beanItem.getOrganizationKey())
                                    && ((HelperDTO) fmFileType.getValue()).getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {

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
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + forecastName.getValue() + "?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            String finalVersion;
                            if (versionList.getValue().contains(".")) {
                                String[] array = versionList.getValue().split("\\.");
                                int minorVersion = Integer.valueOf(array[1]);
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
                                if (FileType.equals(ConstantsUtils.DEMAND)
                                        ||FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)
                                        || FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
                                        || FileType.equals(ConstantsUtils.CUSTOMERGTS)
                                        || FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                                    msg = logic.saveForecastDetails(insertionItemIds, selectedFile, selectedFileCountry, finalVersion, fileNameList.getValue(), FileType);
                                } else if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                                    msg = logic.saveForecastDetails(itemIds, selectedFile, selectedFileCountry, finalVersion, fileNameList.getValue(), FileType);
                                }

                                if (msg.equals("success")) {
                                    saveflag = true;
                                    Notification.show("Records saved Successfully");
                                    addlineList.clear();
                                    try {
                                        resultDTO.setFileType(String.valueOf(fileType.getValue()));
                                        resultDTO.setCountry(String.valueOf(country.getValue()));
                                        resultDTO.setFileName(String.valueOf(fileName.getValue()));
                                        resultDTO.setType(String.valueOf(type.getValue().trim()));
                                        resultDTO.setForecastYear("");
                                        resultDTO.setFromPeriod(fromDate.getValue());
                                        resultDTO.setToPeriod(toDate.getValue());
                                        resultDTO.setVersion(String.valueOf(version.getValue().trim()));
                                        loadResultsTable();

                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            } else {
                AbstractNotificationUtils.getErrorNotification("Details Error", "No changed has done From Source version");
                return;
            }
            LOGGER.info("Ending Save Button Logic");
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * setDefaultTableWidth
     *
     * @param table
     * @param container
     */
    public void setDefaultTableWidth(final ExtFilterTable table, final BeanItemContainer<FileMananagementResultDTO> container) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > 1516) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < 1516 && browserWidth > 978) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 978 && browserWidth > 600) {
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
            } else if (browserWidth < 600 && browserWidth > 480) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 480 && browserWidth > 320) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 320) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0;
                i < 6; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
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
     * @param table the table
     * @return the collapsible columns600 px
     */
    private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns480 px.
     *
     * @param table the table
     * @return the collapsible columns480 px
     */
    private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns978 px.
     *
     * @param table the table
     * @return the collapsible columns978 px
     */
    private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default1515 px.
     *
     * @param table the table
     * @return the collapsible columns default1515 px
     */
    private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default.
     *
     * @param table the table
     * @return the collapsible columns default
     */
    private static String[] getCollapsibleColumnsDefault(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        for (int i = 0;
                i
                < 10 && !list.isEmpty();
                i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

//    public void formatData(Object propertyId) {
//
////        if (String.valueOf(propertyId).contains(ConstantsUtils.PRICE)) {
////            detailsFilterTable.setConverter(propertyId, priceFormat);
////        } else if (String.valueOf(propertyId).contains(ConstantsUtils.UNITS)) {
////            detailsFilterTable.setConverter(propertyId, unitsFormat);
////        } else
//        if (String.valueOf(propertyId).contains("activeFromDate")) {
//            detailsFilterTable.setConverter(propertyId, dollarsFormat);
////            DateField d=(DateField)field;
////            d.setDateFormat("MM/dd/yyyy");
//        }
//    }



    private void configureTable() {
        tableLayout.addComponent(resultsFilterTable);
        controlLayout = tableLogic.createControls();
        controlLayout = CommonLogic.getResponsiveControls(controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(10);
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
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In resultsTable resultsItemClick started");
                try {
                    resultsItemClick(event.getProperty().getValue());
                } catch (SystemException e) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
                }
                LOGGER.info("In resultsTable resultsItemClick Ended");
            }
        });
        resultsFilterTable.setErrorHandler(new ErrorHandler() {
            /**
             * * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
        }

    public void setTableDefaultConfig(ExtPagedTable resultsTable) {
        resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_RESULT_COLUMNS);
        resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_RESULT_HEADER);
        resultsTable.markAsDirtyRecursive();
        resultsTable.setImmediate(true);
        resultsTable.setWidth(99, UNITS_PERCENTAGE);
        resultsTable.setHeight("450px");
        resultsTable.setColumnAlignment("version", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment("fromDate", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment("toDate", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnWidth("fromDate", 198);
        resultsTable.setColumnWidth("toDate", 198);
    }

    private void configureDetailsTable() {
        try {
            detailsTable.addComponent(detailsFilterTable);
            detailsControlLayout = detailstableLogic.createControls();
            controlLayout = CommonLogic.getResponsiveControls(detailsControlLayout);
            detailsTable.addComponent(controlLayout);

            detailstableLogic.setContainerDataSource(detailsBean);
            detailstableLogic.setPageLength(15);
            detailstableLogic.sinkItemPerPageWithPageLength(false);
            setDetailsTableDefaultConfig(FileType, detailsFilterTable);
            detailsFilterTable.markAsDirty();
            detailsFilterTable.setComponentError(null);
            detailsFilterTable.setFilterBarVisible(true);
            detailsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
            detailsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
            detailsFilterTable.setValidationVisible(false);
            detailsFilterTable.addStyleName("filterbar");

            detailsFilterTable.setEditable(true);
            detailsFilterTable.setSelectable(true);

//            detailsFilterTable.setColumnWidth("check", 110);
            fileselectiondto.getHelperfileType();
            if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                detailsFilterTable.setColumnAlignment("month", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("year", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("itemNo", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("itemName", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("startDate", ExtCustomTable.Align.CENTER);
                detailsFilterTable.setColumnAlignment("price", ExtCustomTable.Align.RIGHT);
                detailsFilterTable.setColumnAlignment("units", ExtCustomTable.Align.RIGHT);
                detailsFilterTable.setColumnAlignment("dollars", ExtCustomTable.Align.RIGHT);
                
                
              detailsFilterTable.setConverter("startDate", new DateToStringConverter());

            } else if (FileType.toString().equals(ConstantsUtils.DEMAND)) {
                detailsFilterTable.setColumnAlignment("forecastType", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("forecastYear", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("forecastMonth", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("itemId", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("itemIdentifierCodeQualifier", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("itemIdentifier", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("brandId", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("segment", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("marketSizeUnits", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("marketShareRatio", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("marketShareUnits", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("uncapturedUnits", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("uncapturedUnitsRatio", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("totalDemandUnits", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("totalDemandAmount", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("inventoryUnitChange", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("grossUnits", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("grossPrice", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("grossAmount", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("netSalesPrice", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("netSalesAmount", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("batchId", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("source", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("forecastName", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("forecastVersion", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("country", ExtCustomTable.Align.LEFT);
                detailsFilterTable.setColumnAlignment("organizationKey", ExtCustomTable.Align.LEFT);
            }
            else if (FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {

            detailsFilterTable.setColumnAlignment("itemId", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("itemName", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("brandId", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("brandName", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("segment", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("marketSizeUnits", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("marketShareRatio", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("marketShareUnits", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("uncapturedUnits", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("uncapturedUnitsRatio", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("totalDemandUnits", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("totalDemandAmount", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("inventoryUnitChange", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("grossUnits", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("grossPrice", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("grossAmount", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("netSalesPrice", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("netSalesAmount", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("batchId", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("source", ExtCustomTable.Align.LEFT);
            detailsFilterTable.setColumnAlignment("organizationKey", ExtCustomTable.Align.LEFT);

        }
//            detailsFilterTable.setColumnCheckBox(ConstantsUtils.CHECK, true, false);

//            final ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
//                @Override
//                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
//                    checkAll(event.isChecked());
//                }
//            };
//
//            detailsFilterTable.addColumnCheckListener(checkListener);
            if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                exFactoryFieldFactory();
            } else if (FileType.equals(ConstantsUtils.DEMAND) || FileType.equals(ConstantsUtils.ADJUSTED_DEMAND) ) {
                demandFieldFactory();
            } else if (FileType.equals(ConstantsUtils.CUSTOMERGTS)) {
                customerFieldFactory();
            } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)
                    || FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                inventoryFieldFactory(fileselectiondto.getFileType());
            }
            detailsFilterTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs .
                 *
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
                }
            });

            final Object[] obj = new Object[]{ConstantsUtils.PRICE, ConstantsUtils.UNITS, ConstantsUtils.DOLLARS};
            for (int i = 0; i < obj.length; i++) {
                detailsFilterTable.setColumnAlignment(obj[i], ExtFilterTable.Align.RIGHT);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void setDetailsTableDefaultConfig(String fileType, ExtPagedTable resultsTable) {
        try {
            resultsTable.removeAllItems();
            if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_DETAILS_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_DETAILS_HEADER);
            } else if (fileType.equals(ConstantsUtils.DEMAND)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER);
            } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADER);
            } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_HEADER);
            } else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_CUSTOMER_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_CUSTOMER_HEADER);
            } else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
                resultsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS);
                resultsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER);
            } 
            resultsTable.markAsDirtyRecursive();
            resultsTable.setImmediate(true);
            resultsTable.setWidth(99, UNITS_PERCENTAGE);
            resultsTable.setHeight("579px");
            resultsTable.setColumnAlignment("version", ExtCustomTable.Align.CENTER);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FileManagementLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadResultsTable() {
        LOGGER.info("Entering Load Results Table");
        resultsFilterTable.addStyleName(Constants.FILTER_TABLE);
        resultsFilterTable.addStyleName("table-header-normal");
        resultsFilterTable.setConverter("fromDate", new DateToStringConverter());
        resultsFilterTable.setConverter("toDate", new DateToStringConverter());
        tableLogic.configureSearchData(resultDTO);
        resultsFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsFilterTable.setFilterGenerator(new FileManagementFilterGenerator());
        resultsFilterTable.setImmediate(true);
        resultsFilterTable.setWidth(99, UNITS_PERCENTAGE);
        resultsFilterTable.addStyleName("TableCheckBox");
        resultsFilterTable.setSelectable(true);
        resultsFilterTable.markAsDirtyRecursive();
        LOGGER.info("Ending Load Results Table");

    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelResultTable() {
        excelTableBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
        excelTable = new ExtFilterTable();
        tableLayout.addComponent(excelTable);
        excelTable.setVisible( false);
        excelTable.setContainerDataSource(excelTableBean);
        excelTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_RESULT_COLUMNS);
        excelTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_RESULT_HEADER);
        excelTable.markAsDirtyRecursive();
    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelTable(FileMananagementResultDTO fileObject) throws Exception {
        excelTableBean.removeAllItems();
        if (resultsFilterTable.size() != 0) {
            FileMananagementResultDTO excelDTO = fileObject;
            int recordCount = (Integer) logic.getFileResults(excelDTO, 0, 0, null, null, true);
            List<FileMananagementResultDTO> resultList = (List<FileMananagementResultDTO>) logic.getFileResults(excelDTO, 0, recordCount, null, null, false);
            excelTableBean.addAll(resultList);

        }
    }

    /**
     * To configure Excel Details Results Table
     */
    private void configureExcelDetailsTable() {
        LOGGER.info("Configure ExcelDetailsTable Starts---==============================================");
     
        excelDetailsBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
        excelDetailsTable = new ExtFilterTable();

        detailsTable.addComponent(excelDetailsTable);

        excelDetailsTable.setVisible(false);
        excelDetailsTable.setContainerDataSource(excelDetailsBean);
           
        if (FileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_DETAILS_COLUMNS_EXCEL);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_DETAILS_HEADER_EXCEL);
        } else if (FileType.equals(ConstantsUtils.DEMAND)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS_EXCEL);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER_EXCEL);
        } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_COLUMNS_EXCEL);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADER_EXCEL);
        } else if (FileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_COLUMNS_EXCEL);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_HEADER_EXCEL);
        } else if (FileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER);
        } else if (FileType.equals(ConstantsUtils.CUSTOMERGTS)) {
            excelDetailsTable.setVisibleColumns(CommonUIUtil.FILE_MGT_LOOKUP_CUSTOMER_COLUMNS);
            excelDetailsTable.setColumnHeaders(CommonUIUtil.FILE_MGT_LOOKUP_CUSTOMER_HEADER);
        }
        excelDetailsTable.markAsDirtyRecursive();
        LOGGER.info("Configure ExcelDetailsTable ends");
    }

    /**
     * To load excel Table similar to Details Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelDetailsTable(Object fileObject) throws Exception {
        excelDetailsBean.removeAllItems();
        if (detailsFilterTable.size() != 0) {
            FileMananagementResultDTO detailsDTO = (FileMananagementResultDTO) fileObject;
            int recordCount = (Integer) logic.getDetailsResults(detailsDTO, 0, 0, null, null, true);
            List<FileMananagementResultDTO> resultList= (List<FileMananagementResultDTO>) logic.getDetailsResults(detailsDTO, 0, recordCount, null, null, false);
            excelDetailsBean.addAll(resultList);
        }
    }

    public void exFactoryFieldFactory() {
        detailsFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
                final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
                final Boolean flag = dto.isRecordLockStatus();
                final String interfaceFlag = dto.getInterfaceFlag();
                if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
                    if (flag) {
//                        if (propertyId.equals(ConstantsUtils.CHECK)) {
//                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
//                            select.setImmediate(true);
//                            select.setEnabled(false);
//                            return select;
//                        }
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
//                        if (propertyId.equals(ConstantsUtils.CHECK)) {
//                            final CheckBox select = new CheckBox();
//                            select.setImmediate(true);
//                            select.setEnabled(true);
//                            return select;
//                        }
                        if (propertyId.equals(ConstantsUtils.UNITS)) {
                            final TextField unit = new TextField();
                            unit.setImmediate(true);
                            unit.addBlurListener(new FieldEvents.BlurListener() {
                                public void blur(FieldEvents.BlurEvent event) {
                                    LOGGER.info("In configureFields levelValueReference.addBlurListener started");

                                    String unitValue = unit.getValue();
                                    if (!unitValue.matches(ValidationUtils.PRICE)) {
                                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
                                        unit.setValue(ConstantsUtils.EMPTY);
                                        return;
                                    }
                                    String priceValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.PRICE).getValue().toString().replace("$", ConstantsUtils.EMPTY);
                                    if (priceValue.length() == 0) {
                                        AbstractNotificationUtils.getErrorNotification("Remove Error", "Please Enter Price Value");
                                        return;
                                    }
                                    String unitsValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
                                    Double dollarValue = Double.parseDouble(priceValue) * Double.parseDouble(unitsValue);
                                    detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS).setValue(String.valueOf(dollarValue));
                                }
                            });
                            unit.setConverter(unitsFormat);
                            return unit;
                        }
                        if (propertyId.equals(ConstantsUtils.PRICE)) {
                            final TextField price = new TextField();
                            price.setImmediate(true);
                            price.addBlurListener(new FieldEvents.BlurListener() {
                                public void blur(FieldEvents.BlurEvent event) {

                                    LOGGER.info("In configureFields levelValueReference.addValueChangeListener started");
                                    String priceValue = price.getValue();
                                    String priceWithoutDollar = priceValue.replace("$", ConstantsUtils.EMPTY);
                                    if (!priceWithoutDollar.matches(ValidationUtils.PRICE)) {
                                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
                                        price.setValue(ConstantsUtils.EMPTY);
                                        return;
                                    }

                                    String unitsValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
                                    String uniWithoutDollar = unitsValue.replace("$", ConstantsUtils.EMPTY);
                                    if (uniWithoutDollar.length() == 0) {
                                        uniWithoutDollar = "0";
                                    }
                                    Double dollarValue = Double.parseDouble(priceWithoutDollar) * Double.parseDouble(uniWithoutDollar);
                                    detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS).setValue(String.valueOf(dollarValue));
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
//                    if (propertyId.equals(ConstantsUtils.CHECK)) {
//                        final CheckBox select = new CheckBox();
//                        select.setImmediate(true);
//                        select.setEnabled(true);
//                        return select;
//                    }
                    
                    if (propertyId.equals(ConstantsUtils.UNITS)) {
                        final TextField unit = new TextField();
                        unit.setImmediate(true);
                        unit.addBlurListener(new FieldEvents.BlurListener() {
                            public void blur(FieldEvents.BlurEvent event) {
                                LOGGER.info("In configureFields levelValueReference.addBlurListener started");

                                String unitValue = unit.getValue();
                                if (!unitValue.matches(ValidationUtils.PRICE)) {
                                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
                                    unit.setValue(ConstantsUtils.EMPTY);
                                    return;
                                }
                                String priceValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.PRICE).getValue().toString().replace("$", ConstantsUtils.EMPTY);
                                if (priceValue.length() == 0) {
                                    AbstractNotificationUtils.getErrorNotification("Remove Error", "Please Enter Price Value");
                                    return;
                                }
                                String unitsValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
                                Double dollarValue = Double.parseDouble(priceValue) * Double.parseDouble(unitsValue);
                                detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS).setValue(String.valueOf(dollarValue));
                            }
                        });
                        unit.setConverter(unitsFormat);
                        return unit;
                    }
                    if (propertyId.equals(ConstantsUtils.PRICE)) {
                        final TextField price = new TextField();
                        price.setImmediate(true);
                        price.addBlurListener(new FieldEvents.BlurListener() {
                            public void blur(FieldEvents.BlurEvent event) {
                                LOGGER.info("In configureFields levelValueReference.addBlurListener started");

                                String priceValue = price.getValue();
                                String priceWithoutDollar = priceValue.replace("$", ConstantsUtils.EMPTY);
                                if (!priceWithoutDollar.matches(ValidationUtils.PRICE)) {
                                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
                                    price.setValue(ConstantsUtils.EMPTY);
                                    return;
                                }
                                String unitsValue = detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.UNITS).getValue().toString();
                                String uniWithoutDollar = unitsValue.replace("$", ConstantsUtils.EMPTY);
                                if (uniWithoutDollar.length() == 0) {
                                    uniWithoutDollar = "0";
                                }
                                Double dollarValue = Double.parseDouble(priceValue) * Double.parseDouble(uniWithoutDollar);
                                detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.DOLLARS).setValue(String.valueOf(dollarValue));

                            }
                        });
                        price.setConverter(priceFormat);
                        return price;
                    }
                    if (propertyId.equals(ConstantsUtils.YEAR)) {

                        final TextField year1 = new TextField();
                        year1.setImmediate(true);
                        year1.addBlurListener(new FieldEvents.BlurListener() {
                            public void blur(FieldEvents.BlurEvent event) {
                                LOGGER.info("In configureFields levelValueReference.addBlurListener started");

                                String year = year1.getValue();
                                year = year.replace("$", ConstantsUtils.EMPTY);
                                if (!year.matches(ValidationUtils.NUMERIC_VALIDATION)) {
                                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
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
                        month.addBlurListener(new FieldEvents.BlurListener() {
                            public void blur(FieldEvents.BlurEvent event) {
                                LOGGER.info("In configureFields levelValueReference.addBlurListener started");

                                String enteredMonth = month.getValue();
                                enteredMonth = enteredMonth.replace("$", ConstantsUtils.EMPTY);
                                if (!enteredMonth.matches(ValidationUtils.NUMERIC_VALIDATION)) {
                                    AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Only Numbers are allowed");
                                    month.setValue(ConstantsUtils.EMPTY);
                                    return;
                                } else {
                                    int forecastMonth = Integer.valueOf(enteredMonth);
                                    if (forecastMonth > 12 || forecastMonth == 0) {
                                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Please Enter valid Month");
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
//                        itemName.addClickListener(new CustomTextField.ClickListener() {
//                            /**
//                             * Called when a Button has been clicked .
//                             *
//                             */
//                            @SuppressWarnings("PMD")
//                            public void click(CustomTextField.ClickEvent event) {
//                                final ItemSearchLookup lookUp = new ItemSearchLookup(itemNoSearch, itemName, sessionDTO);
//                                try {
//                                    lookUp.init();
//                                } catch (Exception ex) {
//                                    LOGGER.error(ex);
//                                }
//                                UI.getCurrent().addWindow(lookUp);
//                                lookUp.addCloseListener(new Window.CloseListener() {
//                                    public void windowClose(final Window.CloseEvent e) {
//                                        detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.ITEM_NO).setValue(itemNoSearch.getValue());
//                                    }
//                                });
//                            }
//                        });
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
                            public void click(CustomTextField.ClickEvent event) {
                                final ItemSearchLookup lookUp = new ItemSearchLookup(itemNo, lookupItemName, sessionDTO);
                                try {
                                    lookUp.init();
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                                UI.getCurrent().addWindow(lookUp);
                                lookUp.addCloseListener(new Window.CloseListener() {
                                    public void windowClose(final Window.CloseEvent e) {
                                        detailsFilterTable.getContainerProperty(itemId, ConstantsUtils.ITEM_NAME).setValue(lookupItemName.getValue().toString());
                                    }
                                });
                            }
                        });
                        return itemNo;
                    }

//                    if (propertyId.equals(ConstantsUtils.START_DATE)) {
//                        final PopupDateField startDate = new PopupDateField();
//                        startDate.setImmediate(true);
//                        startDate.setDateFormat("MM/dd/yyyy");
//                        return startDate;
//                    }

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
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
                final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
                final Boolean flag = dto.isRecordLockStatus();
                final String interfaceFlag = dto.getInterfaceFlag();
                if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
                    if (flag) {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(false);
                            return select;
                        }
                        if (propertyId.equals("forecastType")) {
                            final TextField forecastType = new TextField();
                            forecastType.setImmediate(true);
                            forecastType.setReadOnly(true);
                            forecastType.setEnabled(false);
                            return forecastType;
                        }
                        if (propertyId.equals("forcastYear")) {
                            final TextField forecastYear = new TextField();
                            forecastYear.setImmediate(true);
                            forecastYear.setReadOnly(true);
                            forecastYear.setEnabled(false);
                            return forecastYear;
                        }
                        if (propertyId.equals("forecastMonth")) {
                            final TextField forecastMonth = new TextField();
                            forecastMonth.setImmediate(true);
                            forecastMonth.setReadOnly(true);
                            forecastMonth.setEnabled(false);
                            return forecastMonth;
                        }
                        if (propertyId.equals("itemId")) {
                            final TextField itemIde = new TextField();
                            itemIde.setImmediate(true);
                            itemIde.setReadOnly(true);
                            itemIde.setEnabled(false);
                            return itemIde;
                        }
                        if (propertyId.equals("itemIdentifierCodeQualifier")) {
                            final TextField itemIdentifierCodeQualifier = new TextField();
                            itemIdentifierCodeQualifier.setImmediate(true);
                            itemIdentifierCodeQualifier.setReadOnly(true);
                            itemIdentifierCodeQualifier.setEnabled(false);
                            return itemIdentifierCodeQualifier;
                        }
                        if (propertyId.equals("itemIdentifier")) {
                            final TextField itemIdentifier = new TextField();
                            itemIdentifier.setImmediate(true);
                            itemIdentifier.setReadOnly(true);
                            itemIdentifier.setEnabled(false);
                            return itemIdentifier;
                        }
                        if (propertyId.equals("brandId")) {
                            final TextField brandId = new TextField();

                            brandId.setImmediate(true);
                            brandId.setReadOnly(true);
                            brandId.setEnabled(false);
                            return brandId;
                        }
                        if (propertyId.equals("segment")) {
                            final TextField segment = new TextField();
                            segment.setImmediate(true);
                            segment.setReadOnly(true);
                            segment.setEnabled(false);
                            return segment;
                        }
                        if (propertyId.equals("marketSizeUnits")) {
                            final TextField marketSizeUnits = new TextField();
                            marketSizeUnits.setConverter(unitsFormat);
                            marketSizeUnits.setImmediate(true);
                            marketSizeUnits.setReadOnly(true);
                            marketSizeUnits.setEnabled(false);
                            return marketSizeUnits;
                        }
                        if (propertyId.equals("marketShareRatio")) {
                            final TextField marketShareRatio = new TextField();
                            marketShareRatio.setImmediate(true);
                            marketShareRatio.setReadOnly(true);
                            marketShareRatio.setEnabled(false);
                            return marketShareRatio;
                        }
                        if (propertyId.equals("marketShareUnits")) {
                            final TextField marketShareUnits = new TextField();
                            marketShareUnits.setImmediate(true);
                            marketShareUnits.setConverter(unitsFormat);
                            marketShareUnits.setReadOnly(true);
                            marketShareUnits.setEnabled(false);
                            return marketShareUnits;
                        }
                        if (propertyId.equals("uncapturedUnits")) {
                            final TextField uncapturedUnits = new TextField();
                            uncapturedUnits.setImmediate(true);
                            uncapturedUnits.setConverter(unitsFormat);
                            uncapturedUnits.setReadOnly(true);
                            uncapturedUnits.setEnabled(false);
                            return uncapturedUnits;
                        }
                        if (propertyId.equals("uncapturedUnitsRatio")) {
                            final TextField uncapturedUnitsRatio = new TextField();
                            uncapturedUnitsRatio.setImmediate(true);
                            uncapturedUnitsRatio.setReadOnly(true);
                            uncapturedUnitsRatio.setEnabled(false);
                            return uncapturedUnitsRatio;
                        }
                        if (propertyId.equals("totalDemandUnits")) {
                            final TextField totalDemandUnits = new TextField();
                            totalDemandUnits.setImmediate(true);
                            totalDemandUnits.setConverter(unitsFormat);
                            totalDemandUnits.setReadOnly(true);
                            totalDemandUnits.setEnabled(false);
                            return totalDemandUnits;
                        }
                        if (propertyId.equals("totalDemandAmount")) {
                            final TextField totalDemandAmount = new TextField();
                            totalDemandAmount.setImmediate(true);
                            totalDemandAmount.setConverter(priceFormat);
                            totalDemandAmount.setReadOnly(true);
                            totalDemandAmount.setEnabled(false);
                            return totalDemandAmount;
                        }
                        if (propertyId.equals("inventoryUnitChange")) {
                            final TextField inventoryUnitChange = new TextField();
                            inventoryUnitChange.setImmediate(true);
                            inventoryUnitChange.setConverter(priceFormat);
                            inventoryUnitChange.setReadOnly(true);
                            inventoryUnitChange.setEnabled(false);
                            return inventoryUnitChange;
                        }
                        if (propertyId.equals("grossUnits")) {
                            final TextField grossUnits = new TextField();
                            grossUnits.setImmediate(true);
                            grossUnits.setConverter(unitsFormat);
                            grossUnits.setReadOnly(true);
                            grossUnits.setEnabled(false);
                            return grossUnits;
                        }
                        if (propertyId.equals("grossPrice")) {
                            final TextField grossPrice = new TextField();
                            grossPrice.setImmediate(true);
                            grossPrice.setConverter(unitsFormat);
                            grossPrice.setReadOnly(true);
                            grossPrice.setEnabled(false);
                            return grossPrice;
                        }
                        if (propertyId.equals("grossAmount")) {
                            final TextField grossAmount = new TextField();
                            grossAmount.setImmediate(true);
                            grossAmount.setConverter(priceFormat);
                            grossAmount.setReadOnly(true);
                            grossAmount.setEnabled(false);
                            return grossAmount;
                        }
                        if (propertyId.equals("netSalesPrice")) {
                            final TextField netSalesPrice = new TextField();
                            netSalesPrice.setImmediate(true);
                            netSalesPrice.setConverter(priceFormat);
                            netSalesPrice.setReadOnly(true);
                            netSalesPrice.setEnabled(false);
                            return netSalesPrice;
                        }
                        if (propertyId.equals("netSalesAmount")) {
                            final TextField netSalesAmount = new TextField();
                            netSalesAmount.setImmediate(true);
                            netSalesAmount.setConverter(priceFormat);
                            netSalesAmount.setReadOnly(true);
                            netSalesAmount.setEnabled(false);
                            return netSalesAmount;
                        }
                        if (propertyId.equals("batchId")) {
                            final TextField batchId = new TextField();
                            batchId.setImmediate(true);
                            batchId.setReadOnly(true);
                            batchId.setEnabled(false);
                            return batchId;
                        }
                        if (propertyId.equals("source")) {
                            final TextField source = new TextField();
                            source.setImmediate(true);
                            source.setReadOnly(true);
                            source.setEnabled(false);
                            return source;
                        }
                        if (propertyId.equals("forecastName")) {
                            final TextField forecastName = new TextField();
                            forecastName.setImmediate(true);
                            forecastName.setReadOnly(true);
                            forecastName.setEnabled(false);
                            return forecastName;
                        }
                        if (propertyId.equals("forecastVersion")) {
                            final TextField forecastVersion = new TextField();
                            forecastVersion.setImmediate(true);
                            forecastVersion.setReadOnly(true);
                            forecastVersion.setEnabled(false);
                            return forecastVersion;
                        }
                        if (propertyId.equals("country")) {
                            final TextField country = new TextField();
                            country.setImmediate(true);
                            country.setReadOnly(true);
                            country.setEnabled(false);
                            return country;
                        }
                        if (propertyId.equals("organizationKey")) {
                            final TextField organizationKey = new TextField();
                            organizationKey.setImmediate(true);
                            organizationKey.setReadOnly(true);
                            organizationKey.setEnabled(false);
                            return organizationKey;
                        }

                    } else {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(true);
                            return select;
                        }
                        if (propertyId.equals("forecastType")) {
                            final TextField forecastType = new TextField();
                            forecastType.setImmediate(true);
                            forecastType.setReadOnly(true);
                            forecastType.setEnabled(false);
                            return forecastType;
                        }
                        if (propertyId.equals("forcastYear")) {
                            final TextField forecastYear = new TextField();
                            forecastYear.setImmediate(true);
                            forecastYear.setReadOnly(true);
                            forecastYear.setEnabled(false);
                            return forecastYear;
                        }
                        if (propertyId.equals("forecastMonth")) {
                            final TextField forecastMonth = new TextField();
                            forecastMonth.setImmediate(true);
                            forecastMonth.setReadOnly(true);
                            forecastMonth.setEnabled(false);
                            return forecastMonth;
                        }
                        if (propertyId.equals("itemId")) {
                            final TextField itemIde = new TextField();
                            itemIde.setImmediate(true);
                            itemIde.setReadOnly(true);
                            itemIde.setEnabled(false);
                            return itemIde;
                        }
                        if (propertyId.equals("itemIdentifierCodeQualifier")) {
                            final TextField itemIdentifierCodeQualifier = new TextField();
                            itemIdentifierCodeQualifier.setImmediate(true);
                            itemIdentifierCodeQualifier.setReadOnly(true);
                            itemIdentifierCodeQualifier.setEnabled(false);
                            return itemIdentifierCodeQualifier;
                        }
                        if (propertyId.equals("itemIdentifier")) {
                            final TextField itemIdentifier = new TextField();
                            itemIdentifier.setImmediate(true);
                            itemIdentifier.setReadOnly(true);
                            itemIdentifier.setEnabled(false);
                            return itemIdentifier;
                        }
                        if (propertyId.equals("brandId")) {
                            final TextField brandId = new TextField();

                            brandId.setImmediate(true);
                            brandId.setReadOnly(true);
                            brandId.setEnabled(false);
                            return brandId;
                        }
                        if (propertyId.equals("segment")) {
                            final TextField segment = new TextField();
                            segment.setImmediate(true);
                            segment.setReadOnly(true);
                            segment.setEnabled(false);
                            return segment;
                        }
                        if (propertyId.equals("marketSizeUnits")) {
                            final TextField marketSizeUnits = new TextField();
                            marketSizeUnits.setImmediate(true);
                            marketSizeUnits.setConverter(unitsFormat);
                            marketSizeUnits.setReadOnly(true);
                            marketSizeUnits.setEnabled(false);
                            return marketSizeUnits;
                        }
                        if (propertyId.equals("marketShareRatio")) {
                            final TextField marketShareRatio = new TextField();
                            marketShareRatio.setImmediate(true);
                            marketShareRatio.setReadOnly(true);
                            marketShareRatio.setEnabled(false);
                            return marketShareRatio;
                        }
                        if (propertyId.equals("marketShareUnits")) {
                            final TextField marketShareUnits = new TextField();
                            marketShareUnits.setImmediate(true);
                            marketShareUnits.setConverter(unitsFormat);
                            marketShareUnits.setReadOnly(true);
                            marketShareUnits.setEnabled(false);
                            return marketShareUnits;
                        }
                        if (propertyId.equals("uncapturedUnits")) {
                            final TextField uncapturedUnits = new TextField();
                            uncapturedUnits.setImmediate(true);
                            uncapturedUnits.setConverter(unitsFormat);
                            uncapturedUnits.setReadOnly(true);
                            uncapturedUnits.setEnabled(false);
                            return uncapturedUnits;
                        }
                        if (propertyId.equals("uncapturedUnitsRatio")) {
                            final TextField uncapturedUnitsRatio = new TextField();
                            uncapturedUnitsRatio.setImmediate(true);
                            uncapturedUnitsRatio.setReadOnly(true);
                            uncapturedUnitsRatio.setEnabled(false);
                            return uncapturedUnitsRatio;
                        }
                        if (propertyId.equals("totalDemandUnits")) {
                            final TextField totalDemandUnits = new TextField();
                            totalDemandUnits.setImmediate(true);
                            totalDemandUnits.setConverter(unitsFormat);
                            totalDemandUnits.setReadOnly(true);
                            totalDemandUnits.setEnabled(false);
                            return totalDemandUnits;
                        }
                        if (propertyId.equals("totalDemandAmount")) {
                            final TextField totalDemandAmount = new TextField();
                            totalDemandAmount.setImmediate(true);
                            totalDemandAmount.setConverter(priceFormat);
                            totalDemandAmount.setReadOnly(true);
                            totalDemandAmount.setEnabled(false);
                            return totalDemandAmount;
                        }
                        if (propertyId.equals("inventoryUnitChange")) {
                            final TextField inventoryUnitChange = new TextField();
                            inventoryUnitChange.setImmediate(true);
                            inventoryUnitChange.setConverter(unitsFormat);
                            inventoryUnitChange.setReadOnly(true);
                            inventoryUnitChange.setEnabled(false);
                            return inventoryUnitChange;
                        }
                        if (propertyId.equals("grossUnits")) {
                            final TextField grossUnits = new TextField();
                            grossUnits.setImmediate(true);
                            grossUnits.setConverter(unitsFormat);
                            grossUnits.setReadOnly(true);
                            grossUnits.setEnabled(false);
                            return grossUnits;
                        }
                        if (propertyId.equals("grossPrice")) {
                            final TextField grossPrice = new TextField();
                            grossPrice.setImmediate(true);
                            grossPrice.setConverter(priceFormat);
                            grossPrice.setReadOnly(true);
                            grossPrice.setEnabled(false);
                            return grossPrice;
                        }
                        if (propertyId.equals("grossAmount")) {
                            final TextField grossAmount = new TextField();
                            grossAmount.setImmediate(true);
                            grossAmount.setConverter(priceFormat);
                            grossAmount.setReadOnly(true);
                            grossAmount.setEnabled(false);
                            return grossAmount;
                        }
                        if (propertyId.equals("netSalesPrice")) {
                            final TextField netSalesPrice = new TextField();
                            netSalesPrice.setImmediate(true);
                            netSalesPrice.setConverter(priceFormat);
                            netSalesPrice.setReadOnly(true);
                            netSalesPrice.setEnabled(false);
                            return netSalesPrice;
                        }
                        if (propertyId.equals("netSalesAmount")) {
                            final TextField netSalesAmount = new TextField();
                            netSalesAmount.setImmediate(true);
                            netSalesAmount.setConverter(priceFormat);
                            netSalesAmount.setReadOnly(true);
                            netSalesAmount.setEnabled(false);
                            return netSalesAmount;
                        }
                        if (propertyId.equals("batchId")) {
                            final TextField batchId = new TextField();
                            batchId.setImmediate(true);
                            batchId.setReadOnly(true);
                            batchId.setEnabled(false);
                            return batchId;
                        }
                        if (propertyId.equals("source")) {
                            final TextField source = new TextField();
                            source.setImmediate(true);
                            source.setReadOnly(true);
                            source.setEnabled(false);
                            return source;
                        }
                        if (propertyId.equals("forecastName")) {
                            final TextField forecastName = new TextField();
                            forecastName.setImmediate(true);
                            forecastName.setReadOnly(true);
                            forecastName.setEnabled(false);
                            return forecastName;
                        }
                        if (propertyId.equals("forecastVersion")) {
                            final TextField forecastVersion = new TextField();
                            forecastVersion.setImmediate(true);
                            forecastVersion.setReadOnly(true);
                            forecastVersion.setEnabled(false);
                            return forecastVersion;
                        }
                        if (propertyId.equals("country")) {
                            final TextField country = new TextField();
                            country.setImmediate(true);
                            country.setReadOnly(true);
                            country.setEnabled(false);
                            return country;
                        }
                        if (propertyId.equals("organizationKey")) {
                            final TextField organizationKey = new TextField();
                            organizationKey.setImmediate(true);
                            organizationKey.setReadOnly(true);
                            organizationKey.setEnabled(false);
                            return organizationKey;
                        }
                    }
                } else {
                    if (propertyId.equals(ConstantsUtils.CHECK)) {
                        final ExtCustomCheckBox select = new ExtCustomCheckBox();
                        select.setImmediate(true);
                        select.setEnabled(true);
                        return select;
                    }
                    if (propertyId.equals("forecastType")) {
                        final TextField forecastType = new TextField();
                        forecastType.setImmediate(true);
                        forecastType.setEnabled(true);
                        return forecastType;
                    }
                    if (propertyId.equals("forcastYear")) {
                        final TextField forecastYear = new TextField();
                        forecastYear.setEnabled(true);
                        forecastYear.setImmediate(true);
                        return forecastYear;
                    }
                    if (propertyId.equals("forecastMonth")) {
                        final TextField forecastMonth = new TextField();
                        forecastMonth.setEnabled(true);
                        forecastMonth.setImmediate(true);
                        return forecastMonth;
                    }
                    if (propertyId.equals("itemId")) {
                        final TextField itemIde = new TextField();
                        itemIde.setEnabled(true);
                        itemIde.setImmediate(true);
                        return itemIde;
                    }
                    if (propertyId.equals("itemIdentifierCodeQualifier")) {
                        final TextField itemIdentifierCodeQualifier = new TextField();
                        itemIdentifierCodeQualifier.setEnabled(true);
                        itemIdentifierCodeQualifier.setImmediate(true);
                        return itemIdentifierCodeQualifier;
                    }
                    if (propertyId.equals("itemIdentifier")) {
                        final TextField itemIdentifier = new TextField();
                        itemIdentifier.setEnabled(true);
                        itemIdentifier.setImmediate(true);
                        return itemIdentifier;
                    }
                    if (propertyId.equals("brandId")) {
                        final TextField brandId = new TextField();
                        brandId.setEnabled(true);
                        brandId.setImmediate(true);
                        return brandId;
                    }
                    if (propertyId.equals("segment")) {
                        final TextField segment = new TextField();
                        segment.setEnabled(true);
                        segment.setImmediate(true);
                        return segment;
                    }
                    if (propertyId.equals("marketSizeUnits")) {
                        final TextField marketSizeUnits = new TextField();
                        marketSizeUnits.setEnabled(true);
                        marketSizeUnits.setImmediate(true);
                        return marketSizeUnits;
                    }
                    if (propertyId.equals("marketShareRatio")) {
                        final TextField marketShareRatio = new TextField();
                        marketShareRatio.setEnabled(true);
                        marketShareRatio.setImmediate(true);
                        return marketShareRatio;
                    }
                    if (propertyId.equals("marketShareUnits")) {
                        final TextField marketShareUnits = new TextField();
                        marketShareUnits.setEnabled(true);
                        marketShareUnits.setImmediate(true);
                        return marketShareUnits;
                    }
                    if (propertyId.equals("uncapturedUnits")) {
                        final TextField uncapturedUnits = new TextField();
                        uncapturedUnits.setEnabled(true);
                        uncapturedUnits.setImmediate(true);
                        return uncapturedUnits;
                    }
                    if (propertyId.equals("uncapturedUnitsRatio")) {
                        final TextField uncapturedUnitsRatio = new TextField();
                        uncapturedUnitsRatio.setEnabled(true);
                        uncapturedUnitsRatio.setImmediate(true);
                        return uncapturedUnitsRatio;
                    }
                    if (propertyId.equals("totalDemandUnits")) {
                        final TextField totalDemandUnits = new TextField();
                        totalDemandUnits.setEnabled(true);
                        totalDemandUnits.setImmediate(true);
                        return totalDemandUnits;
                    }
                    if (propertyId.equals("totalDemandAmount")) {
                        final TextField totalDemandAmount = new TextField();
                        totalDemandAmount.setEnabled(true);
                        totalDemandAmount.setImmediate(true);
                        return totalDemandAmount;
                    }
                    if (propertyId.equals("inventoryUnitChange")) {
                        final TextField inventoryUnitChange = new TextField();
                        inventoryUnitChange.setEnabled(true);
                        inventoryUnitChange.setImmediate(true);
                        return inventoryUnitChange;
                    }
                    if (propertyId.equals("grossUnits")) {
                        final TextField grossUnits = new TextField();
                        grossUnits.setEnabled(true);
                        grossUnits.setImmediate(true);
                        return grossUnits;
                    }
                    if (propertyId.equals("grossPrice")) {
                        final TextField grossPrice = new TextField();
                        grossPrice.setEnabled(true);
                        grossPrice.setImmediate(true);
                        return grossPrice;
                    }
                    if (propertyId.equals("grossAmount")) {
                        final TextField grossAmount = new TextField();
                        grossAmount.setEnabled(true);
                        grossAmount.setImmediate(true);
                        return grossAmount;
                    }
                    if (propertyId.equals("netSalesPrice")) {
                        final TextField netSalesPrice = new TextField();
                        netSalesPrice.setEnabled(true);
                        netSalesPrice.setImmediate(true);
                        return netSalesPrice;
                    }
                    if (propertyId.equals("netSalesAmount")) {
                        final TextField netSalesAmount = new TextField();
                        netSalesAmount.setEnabled(true);
                        netSalesAmount.setImmediate(true);
                        return netSalesAmount;
                    }
                    if (propertyId.equals("batchId")) {
                        final TextField batchId = new TextField();
                        batchId.setEnabled(true);
                        batchId.setImmediate(true);
                        return batchId;
                    }
                    if (propertyId.equals("source")) {
                        final TextField source = new TextField();
                        source.setEnabled(true);
                        source.setImmediate(true);
                        return source;
                    }
                    if (propertyId.equals("forecastName")) {
                        final TextField forecastName = new TextField();
                        forecastName.setEnabled(true);
                        forecastName.setImmediate(true);
                        return forecastName;
                    }
                    if (propertyId.equals("forecastVersion")) {
                        final TextField forecastVersion = new TextField();
                        forecastVersion.setEnabled(true);
                        forecastVersion.setImmediate(true);
                        return forecastVersion;
                    }
                    if (propertyId.equals("country")) {
                        final TextField country = new TextField();
                        country.setEnabled(true);
                        country.setImmediate(true);
                        return country;
                    }
                    if (propertyId.equals("organizationKey")) {
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
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
                final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
                final Boolean flag = dto.isRecordLockStatus();
                final String interfaceFlag = dto.getInterfaceFlag();
                if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
                    if (flag) {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(false);
                            return select;
                        }
                        if (propertyId.equals("year")) {
                            final TextField year = new TextField();
                            year.setImmediate(true);
                            year.setReadOnly(true);
                            year.setEnabled(false);
                            return year;
                        }
                        if (propertyId.equals("month")) {
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
                        if (propertyId.equals("itemId")) {
                            final TextField itemIde = new TextField();
                            itemIde.setImmediate(true);
                            itemIde.setReadOnly(true);
                            itemIde.setEnabled(false);
                            return itemIde;
                        }
                        if (propertyId.equals("unitsWithdrawn")) {
                            final TextField unitsWithdrawn = new TextField();
                            unitsWithdrawn.setImmediate(true);
                            unitsWithdrawn.setReadOnly(true);
                            unitsWithdrawn.setEnabled(false);
                            return unitsWithdrawn;
                        }
                        if (propertyId.equals("itemIdentifier")) {
                            final TextField itemIdentifier = new TextField();
                            itemIdentifier.setImmediate(true);
                            itemIdentifier.setReadOnly(true);
                            itemIdentifier.setEnabled(false);
                            return itemIdentifier;
                        }
                        if (propertyId.equals("itemIdentifierCodeQualifier")) {
                            final TextField itemIdentifierCodeQualifier = new TextField();
                            itemIdentifierCodeQualifier.setImmediate(true);
                            itemIdentifierCodeQualifier.setReadOnly(true);
                            itemIdentifierCodeQualifier.setEnabled(false);
                            return itemIdentifierCodeQualifier;
                        }
                        if (propertyId.equals("amountWithdrawn")) {
                            final TextField amountWithdrawn = new TextField();
                            amountWithdrawn.setImmediate(true);
                            amountWithdrawn.setReadOnly(true);
                            amountWithdrawn.setEnabled(false);
                            return amountWithdrawn;
                        }
                        if (propertyId.equals("batchId")) {
                            final TextField batchId = new TextField();
                            batchId.setImmediate(true);
                            batchId.setReadOnly(true);
                            batchId.setEnabled(false);
                            return batchId;
                        }
                        if (propertyId.equals("organizationKey")) {
                            final TextField organizationKey = new TextField();
                            organizationKey.setImmediate(true);
                            organizationKey.setReadOnly(true);
                            organizationKey.setEnabled(false);
                            return organizationKey;
                        }
                        if (propertyId.equals("price")) {
                            final TextField price = new TextField();
                            price.setImmediate(true);
                            price.setReadOnly(true);
                            price.setEnabled(false);
                            return price;
                        }
                        if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                            if (propertyId.equals("companyId")) {
                                final TextField companyId = new TextField();
                                companyId.setImmediate(true);
                                companyId.setReadOnly(true);
                                companyId.setEnabled(false);
                                return companyId;
                            }
                            if (propertyId.equals("identifierCodeQualifier")) {
                                final TextField identifierCodeQualifier = new TextField();
                                identifierCodeQualifier.setImmediate(true);
                                identifierCodeQualifier.setReadOnly(true);
                                identifierCodeQualifier.setEnabled(false);
                                return identifierCodeQualifier;
                            }
                            if (propertyId.equals("companyIdentifier")) {
                                final TextField companyIdentifier = new TextField();
                                companyIdentifier.setImmediate(true);
                                companyIdentifier.setReadOnly(true);
                                companyIdentifier.setEnabled(false);
                                return companyIdentifier;
                            }
                        }

                    } else {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(true);
                            return select;
                        }
                        if (propertyId.equals("year")) {
                            final TextField year = new TextField();
                            year.setImmediate(true);
                            year.setReadOnly(true);
                            year.setEnabled(false);
                            return year;
                        }
                        if (propertyId.equals("month")) {
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
                        if (propertyId.equals("itemId")) {
                            final TextField itemIde = new TextField();
                            itemIde.setImmediate(true);
                            itemIde.setReadOnly(true);
                            itemIde.setEnabled(false);
                            return itemIde;
                        }
                        if (propertyId.equals("unitsWithdrawn")) {
                            final TextField unitsWithdrawn = new TextField();
                            unitsWithdrawn.setImmediate(true);
                            unitsWithdrawn.setReadOnly(true);
                            unitsWithdrawn.setEnabled(false);
                            return unitsWithdrawn;
                        }
                        if (propertyId.equals("itemIdentifier")) {
                            final TextField itemIdentifier = new TextField();
                            itemIdentifier.setImmediate(true);
                            itemIdentifier.setReadOnly(true);
                            itemIdentifier.setEnabled(false);
                            return itemIdentifier;
                        }
                        if (propertyId.equals("itemIdentifierCodeQualifier")) {
                            final TextField itemIdentifierCodeQualifier = new TextField();
                            itemIdentifierCodeQualifier.setImmediate(true);
                            itemIdentifierCodeQualifier.setReadOnly(true);
                            itemIdentifierCodeQualifier.setEnabled(false);
                            return itemIdentifierCodeQualifier;
                        }
                        if (propertyId.equals("amountWithdrawn")) {
                            final TextField amountWithdrawn = new TextField();
                            amountWithdrawn.setImmediate(true);
                            amountWithdrawn.setReadOnly(true);
                            amountWithdrawn.setEnabled(false);
                            return amountWithdrawn;
                        }
                        if (propertyId.equals("batchId")) {
                            final TextField batchId = new TextField();
                            batchId.setImmediate(true);
                            batchId.setReadOnly(true);
                            batchId.setEnabled(false);
                            return batchId;
                        }
                        if (propertyId.equals("organizationKey")) {
                            final TextField organizationKey = new TextField();
                            organizationKey.setImmediate(true);
                            organizationKey.setReadOnly(true);
                            organizationKey.setEnabled(false);
                            return organizationKey;
                        }
                        if (propertyId.equals("price")) {
                            final TextField price = new TextField();
                            price.setImmediate(true);
                            price.setReadOnly(true);
                            price.setEnabled(false);
                            return price;
                        }
                        if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                            if (propertyId.equals("companyId")) {
                                final TextField companyId = new TextField();
                                companyId.setImmediate(true);
                                companyId.setReadOnly(true);
                                companyId.setEnabled(false);
                                return companyId;
                            }
                            if (propertyId.equals("identifierCodeQualifier")) {
                                final TextField identifierCodeQualifier = new TextField();
                                identifierCodeQualifier.setImmediate(true);
                                identifierCodeQualifier.setReadOnly(true);
                                identifierCodeQualifier.setEnabled(false);
                                return identifierCodeQualifier;
                            }
                            if (propertyId.equals("companyIdentifier")) {
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
                        final ExtCustomCheckBox select = new ExtCustomCheckBox();
                        select.setImmediate(true);
                        select.setEnabled(true);
                        return select;
                    }
                    if (propertyId.equals("year")) {
                        final TextField year = new TextField();
                        year.setImmediate(true);
                        year.setReadOnly(false);
                        year.setEnabled(true);
                        return year;
                    }
                    if (propertyId.equals("month")) {
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
                    if (propertyId.equals("itemId")) {
                        final TextField itemIde = new TextField();
                        itemIde.setImmediate(true);
                        itemIde.setReadOnly(false);
                        itemIde.setEnabled(true);
                        return itemIde;
                    }
                    if (propertyId.equals("unitsWithdrawn")) {
                        final TextField unitsWithdrawn = new TextField();
                        unitsWithdrawn.setImmediate(true);
                        unitsWithdrawn.setReadOnly(false);
                        unitsWithdrawn.setEnabled(true);
                        return unitsWithdrawn;
                    }
                    if (propertyId.equals("itemIdentifier")) {
                        final TextField itemIdentifier = new TextField();
                        itemIdentifier.setImmediate(true);
                        itemIdentifier.setReadOnly(false);
                        itemIdentifier.setEnabled(true);
                        return itemIdentifier;
                    }
                    if (propertyId.equals("itemIdentifierCodeQualifier")) {
                        final TextField itemIdentifierCodeQualifier = new TextField();
                        itemIdentifierCodeQualifier.setImmediate(true);
                        itemIdentifierCodeQualifier.setReadOnly(false);
                        itemIdentifierCodeQualifier.setEnabled(true);
                        return itemIdentifierCodeQualifier;
                    }
                    if (propertyId.equals("amountWithdrawn")) {
                        final TextField amountWithdrawn = new TextField();
                        amountWithdrawn.setImmediate(true);
                        amountWithdrawn.setReadOnly(false);
                        amountWithdrawn.setEnabled(true);
                        return amountWithdrawn;
                    }
                    if (propertyId.equals("batchId")) {
                        final TextField batchId = new TextField();
                        batchId.setImmediate(true);
                        batchId.setReadOnly(false);
                        batchId.setEnabled(true);
                        return batchId;
                    }
                    if (propertyId.equals("organizationKey")) {
                        final TextField organizationKey = new TextField();
                        organizationKey.setImmediate(true);
                        organizationKey.setReadOnly(false);
                        organizationKey.setEnabled(true);
                        return organizationKey;
                    }
                    if (propertyId.equals("price")) {
                        final TextField price = new TextField();
                        price.setImmediate(true);
                        price.setReadOnly(false);
                        price.setEnabled(true);
                        return price;
                    }
                    if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                        if (propertyId.equals("companyId")) {
                            final TextField companyId = new TextField();
                            companyId.setImmediate(true);
                            companyId.setReadOnly(false);
                            companyId.setEnabled(true);
                            return companyId;
                        }
                        if (propertyId.equals("identifierCodeQualifier")) {
                            final TextField identifierCodeQualifier = new TextField();
                            identifierCodeQualifier.setImmediate(true);
                            identifierCodeQualifier.setReadOnly(false);
                            identifierCodeQualifier.setEnabled(true);
                            return identifierCodeQualifier;
                        }
                        if (propertyId.equals("companyIdentifier")) {
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
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
                final FileMananagementResultDTO dto = (FileMananagementResultDTO) itemId;
                final Boolean flag = dto.isRecordLockStatus();
                final String interfaceFlag = dto.getInterfaceFlag();
                if (interfaceFlag.equals(ConstantsUtils.CHAR_N)) {
                    if (flag) {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(false);
                            return select;
                        }
                        if (propertyId.equals("forcastYear")) {
                            return createCustField("forecastMonth", true, propertyId);
                        }
                        if (propertyId.equals("forecastMonth")) {
                            return createCustField("forecastMonth", true, propertyId);
                        }
                        if (propertyId.equals("itemId")) {
                            return createCustField("itemId", true, propertyId);
                        }
                        if (propertyId.equals("companyId")) {
                            return createCustField("companyId", true, propertyId);
                        }
                        if (propertyId.equals("units")) {
                            return createCustField("units", true, propertyId);
                        }
                        if (propertyId.equals("priceType")) {
                            return createCustField("priceType", true, propertyId);
                        }
                        if (propertyId.equals("price")) {
                            return createCustField("price", true, propertyId);
                        }
                        if (propertyId.equals("salesAmount")) {
                            return createCustField("salesAmount", true, propertyId);
                        }
                        if (propertyId.equals("salesInclusion")) {
                            return createCustField("salesInclusion", true, propertyId);
                        }
                        if (propertyId.equals("deductionId")) {
                            return createCustField("deductionId", true, propertyId);
                        }
                        if (propertyId.equals("deductionCategory")) {
                            return createCustField("deductionCategory", true, propertyId);
                        }
                        if (propertyId.equals("deductionType")) {
                            return createCustField("deductionType", true, propertyId);
                        }
                        if (propertyId.equals("deductionProgramType")) {
                            return createCustField("deductionProgramType", true, propertyId);
                        }
                        if (propertyId.equals("adjustmentCode")) {
                            return createCustField("adjustmentCode", true, propertyId);
                        }
                        if (propertyId.equals("deductionRate")) {
                            return createCustField("deductionRate", true, propertyId);
                        }
                        if (propertyId.equals("deductionAmount")) {
                            return createCustField("deductionAmount", true, propertyId);
                        }
                        if (propertyId.equals("deductionInclusion")) {
                            return createCustField("deductionInclusion", true, propertyId);
                        }
                        if (propertyId.equals("forecastValueType")) {
                            return createCustField("forecastValueType", true, propertyId);
                        }
                        if (propertyId.equals("brand")) {
                            return createCustField("brand", true, propertyId);
                        }
                        if (propertyId.equals("segment")) {
                            return createCustField("segment", true, propertyId);
                        }
                        if (propertyId.equals("batchId")) {
                            return createCustField("batchId", true, propertyId);
                        }
                        if (propertyId.equals("organizationKey")) {
                            return createCustField("organizationKey", true, propertyId);
                        }
                        if (propertyId.equals("forecastVersion")) {
                            return createCustField("forecastVersion", true, propertyId);
                        }
                        if (propertyId.equals("country")) {
                            return createCustField("country", true, propertyId);
                        }
                        if (propertyId.equals("forecastName")) {
                            return createCustField("forecastName", true, propertyId);
                        }
                        if (propertyId.equals("forecastDate")) {
                            return createCustField("forecastDate", true, propertyId);
                        }

                    } else {
                        if (propertyId.equals(ConstantsUtils.CHECK)) {
                            final ExtCustomCheckBox select = new ExtCustomCheckBox();
                            select.setImmediate(true);
                            select.setEnabled(false);
                            return select;
                        }
                        if (propertyId.equals("forcastYear")) {
                            return createCustField("forcastYear", true, propertyId);
                        }
                        if (propertyId.equals("forecastMonth")) {
                            return createCustField("forecastMonth", true, propertyId);
                        }
                        if (propertyId.equals("itemId")) {
                            return createCustField("itemId", true, propertyId);
                        }
                        if (propertyId.equals("companyId")) {
                            return createCustField("companyId", true, propertyId);
                        }
                        if (propertyId.equals("units")) {
                            return createCustField("units", true, propertyId);
                        }
                        if (propertyId.equals("priceType")) {
                            return createCustField("priceType", true, propertyId);
                        }
                        if (propertyId.equals("price")) {
                            return createCustField("price", true, propertyId);
                        }
                        if (propertyId.equals("salesAmount")) {
                            return createCustField("salesAmount", true, propertyId);
                        }
                        if (propertyId.equals("salesInclusion")) {
                            return createCustField("salesInclusion", true, propertyId);
                        }
                        if (propertyId.equals("deductionId")) {
                            return createCustField("deductionId", true, propertyId);
                        }
                        if (propertyId.equals("deductionCategory")) {
                            return createCustField("deductionCategory", true, propertyId);
                        }
                        if (propertyId.equals("deductionType")) {
                            return createCustField("deductionType", true, propertyId);
                        }
                        if (propertyId.equals("deductionProgramType")) {
                            return createCustField("deductionProgramType", true, propertyId);
                        }
                        if (propertyId.equals("adjustmentCode")) {
                            return createCustField("adjustmentCode", true, propertyId);
                        }
                        if (propertyId.equals("deductionRate")) {
                            return createCustField("deductionRate", true, propertyId);
                        }
                        if (propertyId.equals("deductionAmount")) {
                            return createCustField("deductionAmount", true, propertyId);
                        }
                        if (propertyId.equals("deductionInclusion")) {
                            return createCustField("deductionInclusion", true, propertyId);
                        }
                        if (propertyId.equals("forecastValueType")) {
                            return createCustField("forecastValueType", true, propertyId);
                        }
                        if (propertyId.equals("brand")) {
                            return createCustField("brand", true, propertyId);
                        }
                        if (propertyId.equals("segment")) {
                            return createCustField("segment", true, propertyId);
                        }
                        if (propertyId.equals("batchId")) {
                            return createCustField("batchId", true, propertyId);
                        }
                        if (propertyId.equals("organizationKey")) {
                            return createCustField("organizationKey", true, propertyId);
                        }
                        if (propertyId.equals("forecastVersion")) {
                            return createCustField("forecastVersion", true, propertyId);
                        }
                        if (propertyId.equals("country")) {
                            return createCustField("country", true, propertyId);
                        }
                        if (propertyId.equals("forecastName")) {
                            return createCustField("forecastName", true, propertyId);
                        }
                        if (propertyId.equals("forecastDate")) {
                            return createCustField("forecastDate", true, propertyId);
                        }
                    }
                } else {
                    if (propertyId.equals(ConstantsUtils.CHECK)) {
                        final ExtCustomCheckBox select = new ExtCustomCheckBox();
                        select.setImmediate(true);
                        select.setEnabled(true);
                        return select;
                    }
                    if (propertyId.equals("forcastYear")) {
                        return createCustField("forcastYear", false, propertyId);
                    }
                    if (propertyId.equals("forecastMonth")) {
                        return createCustField("forecastMonth", false, propertyId);
                    }
                    if (propertyId.equals("itemId")) {
                        return createCustField("itemId", false, propertyId);
                    }
                    if (propertyId.equals("companyId")) {
                        return createCustField("companyId", false, propertyId);
                    }
                    if (propertyId.equals("units")) {
                        return createCustField("units", false, propertyId);
                    }
                    if (propertyId.equals("priceType")) {
                        return createCustField("priceType", false, propertyId);
                    }
                    if (propertyId.equals("price")) {
                        return createCustField("price", false, propertyId);
                    }
                    if (propertyId.equals("salesAmount")) {
                        return createCustField("salesAmount", false, propertyId);
                    }
                    if (propertyId.equals("salesInclusion")) {
                        return createCustField("salesInclusion", false, propertyId);
                    }
                    if (propertyId.equals("deductionId")) {
                        return createCustField("deductionId", false, propertyId);
                    }
                    if (propertyId.equals("deductionCategory")) {
                        return createCustField("deductionCategory", false, propertyId);
                    }
                    if (propertyId.equals("deductionType")) {
                        return createCustField("deductionType", false, propertyId);
                    }
                    if (propertyId.equals("deductionProgramType")) {
                        return createCustField("deductionProgramType", false, propertyId);
                    }
                    if (propertyId.equals("adjustmentCode")) {
                        return createCustField("adjustmentCode", false, propertyId);
                    }
                    if (propertyId.equals("deductionRate")) {
                        return createCustField("deductionRate", false, propertyId);
                    }
                    if (propertyId.equals("deductionAmount")) {
                        return createCustField("deductionAmount", false, propertyId);
                    }
                    if (propertyId.equals("deductionInclusion")) {
                        return createCustField("deductionInclusion", false, propertyId);
                    }
                    if (propertyId.equals("forecastValueType")) {
                        return createCustField("forecastValueType", false, propertyId);
                    }
                    if (propertyId.equals("brandId")) {
                        return createCustField("brandId", false, propertyId);
                    }
                    if (propertyId.equals("segment")) {
                        return createCustField("segment", false, propertyId);
                    }
                    if (propertyId.equals("batchId")) {
                        return createCustField("batchId", false, propertyId);
                    }
                    if (propertyId.equals("organizationKey")) {
                        return createCustField("organizationKey", false, propertyId);
                    }
                    if (propertyId.equals("forecastVersion")) {
                        return createCustField("forecastVersion", false, propertyId);
                    }
                    if (propertyId.equals("country")) {
                        return createCustField("country", false, propertyId);
                    }
                    if (propertyId.equals("forecastName")) {
                        return createCustField("forecastName", false, propertyId);
                    }
                    if (propertyId.equals("forecastDate")) {
                        return createCustField("forecastDate", false, propertyId);
                    }
                }
                final Field field = super.createField(container, itemId, propertyId, uiContext);
                field.setReadOnly(true);
                return field;
            }
        });
    }

    public TextField createCustField(String property, boolean flag, Object propertyId) {

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
        for (int i = 5; i >= 1; i--) {
            currentyear.add(year - i);

        }
        currentyear.add(year);
        for (int i = 1; i <= 5; i++) {
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

}
