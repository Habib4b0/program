/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.dto.LevelDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.dto.WorkflowMasterDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.logic.FixedDollarCalculationLogic;
import com.stpl.app.accountclose.logic.SuggestedAdjLookUpLogic;
import com.stpl.app.accountclose.logic.TreeTableLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.ui.form.BaseRateCalculation.LOGGER;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Constants.FrequencyConstants;
import com.stpl.app.accountclose.utils.Constants.IndicatorConstants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.ACCRUALS;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.ACTUALS;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.AUTO_RECONCILE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.DUMPING_FACTOR;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.EXPONENTIAL_SMOOTHING;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.GROWTH;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.MANUAL;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.MOVING_AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.PROVISION_GROWTH;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.RATE_ADJUSTMENT;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.SALES;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.SALES_GROWTH;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.SELECT_ONE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.TIME_WEIGHTED_AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.WEIGHTED_AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.app.accountclose.utils.DataFormatConverter;
import com.stpl.app.accountclose.utils.HeaderUtils;
import org.asi.container.ExtTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer.DataStructureMode;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class BaseRateCalculation extends CustomComponent implements View {

    @UiField("company")
    public ComboBox company;

    @UiField("companyTxt")
    public TextField companyTxt;
    @UiField("marketTypeTxt")
    public TextField marketTypeTxt;

    @UiField("acctTypeTxt")
    public TextField acctTypeTxt;
    @UiField("acctSubTypeTxt")
    public TextField acctSubTypeTxt;

    @UiField("ndcTxt")
    public TextField ndcTxt;
    @UiField("productNameTxt")
    public TextField productNameTxt;
    @UiField("contractIdTxt")
    public TextField contractIdTxt;

    @UiField("customerGroupTxt")
    public TextField customerGroupTxt;
    @UiField("productGroupTxt")
    public TextField productGroupTxt;

    /**
     * The mode option.
     */
    @UiField("manualRadio")
    public OptionGroup manualRadio;
    @UiField("tableLayout")
    public VerticalLayout tableLayout;
    @UiField("frequency")
    public ComboBox frequency;
    @UiField("ndc")
    public ComboBox ndc;
    @UiField("productName")
    public ComboBox productName;
    @UiField("marketType")
    public ComboBox marketType;
    @UiField("acctType")
    public ComboBox acctType;
    @UiField("acctSubType")
    public ComboBox acctSubType;
    @UiField("contractId")
    public ComboBox contract;
    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    public Button graphBtn;
    /**
     * The from date.
     */
    @UiField("startDate")
    private PopupDateField startDate;
    /**
     * The to date.
     */
    @UiField("endDate")
    private PopupDateField endDate;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("generateBtn")
    public Button generateBtn;
    @UiField("customerGroup")
    public CustomTextField customerGroup;
    @UiField("productGroup")
    public CustomTextField productGroup;
    public ExtFilterTable baseRateCalcTable = new ExtFilterTable();

    SessionDTO session;
    public BaseRateDTO baseRateDTO;
    public static final Logger LOGGER = Logger.getLogger(BaseRateCalculation.class);
    private BeanItemContainer<BaseRateDTO> resultsContainer = new BeanItemContainer<BaseRateDTO>(BaseRateDTO.class);
    CustomTableHeaderDTO headerDTO = new CustomTableHeaderDTO();
    private Map<Object, Boolean> chtCheckBoxMap = new HashMap<Object, Boolean>();
    private ExtTreeContainer<BaseRateDTO> baseRateCalcContainer = new ExtTreeContainer<BaseRateDTO>(BaseRateDTO.class,DataStructureMode.MAP);
    List<LevelDTO> innerProdLevels = new ArrayList<LevelDTO>();
    List<String> helperTableListNames;
    TreeDTO dto = new TreeDTO();
    /* Data Format Converter */
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter percentFor = new DataFormatConverter(StringUtils.EMPTY, DataFormatConverter.INDICATOR_PERCENT);
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    private LazyContainer companyDdlbLazyContainer;
    HelperDTO companyDdlbDefault = new HelperDTO(0, SELECT_ONE.getConstant());
    CommonLogic commonLogic = new CommonLogic();
    TreeTableLogic logic = new TreeTableLogic();
    TreeTableLogic selLogic = new TreeTableLogic();
    TreeTableLogic brSelLogic = new TreeTableLogic();
    BaseRateCalculationLogic brLogic = new BaseRateCalculationLogic();
    public ExtPagedTreeTable availableCustomer = new ExtPagedTreeTable(logic);
    ExtFilterTreeTable leftSelTable;
    ExtFilterTreeTable rightSelTable;
    public FreezePagedTreeTable selectedCustomer = new FreezePagedTreeTable(selLogic);
    public ExtPagedTreeTable brSelectedCustomer = new ExtPagedTreeTable(brSelLogic);
    private ExtTreeContainer<TreeDTO> availTreeContainer = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP);
    private ExtTreeContainer<TreeDTO> selTreeContainer = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP);
    HeaderUtils utils = new HeaderUtils();
    private TreeDTO tableBean;
    private static final BeanItem<?> NULL_OBJECT = null;
    public List parentList = new ArrayList();
    public int levelValue = 0;
    public TreeDTO treeDto = new TreeDTO();
    @UiField("availableTableLayout")
    public VerticalLayout availableTableLayout;
    @UiField("selectedTableLayout")
    public VerticalLayout selectedTableLayout;
    VerticalLayout mainLayout = new VerticalLayout();
    @UiField("moveButtonLayout")
    public VerticalLayout moveButtonLayout;
    @UiField("bottomButtonLayout")
    public HorizontalLayout bottomButtonLayout;
    final List checkedSelList = new ArrayList();
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    private String custGroupSid;
    private String prodGroupSid;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO leftDTO;
    private ExtTreeContainer<TreeDTO> resultBean = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP);
    private ExtTreeContainer<TreeDTO> selectedBean = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP);
    @UiField("fromDateDdlb")
    public ComboBox fromDateDdlb;
    @UiField("toDateDdlb")
    public ComboBox toDateDdlb;
    CommonUtils comutils = new CommonUtils();
    /* The Excel container */
    ExtTreeContainer<BaseRateDTO> excelContainer = new ExtTreeContainer<BaseRateDTO>(BaseRateDTO.class,DataStructureMode.MAP);

    /* The Excel table */
    ExtCustomTable excelTable = new ExtCustomTable();
    /* The Excel header Dto (Right Header) */
    CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    @UiField("salesBasisDdlb")
    public ComboBox salesBasisDdlb;
    boolean generate = true;
    List<String> doubleHeaderList = new ArrayList<String>();
    List<String> checkedList = new ArrayList<String>();
    @UiField("manual")
    public TextField manual;
    @UiField("average")
    public TextField average;
    @UiField("weightedAverage")
    public TextField weightedAverage;
    @UiField("timeWeightedAverage")
    public TextField timeWeightedAverage;
    @UiField("movingAverage")
    public TextField movingAverage;
    @UiField("growth")
    public TextField growth;
    @UiField("provisionGrowth")
    public TextField provisionGrowth;
    @UiField("salesGrowth")
    public TextField salesGrowth;
    @UiField("exponentialSmoothing")
    public TextField exponentialSmoothing;
    @UiField("dumpingFactor")
    public TextField dumpingFactor;
    List<String> headersList = new ArrayList<String>();
    List<String> doubleheadList = new ArrayList<String>();
    String values = StringUtils.EMPTY;
    boolean lookUpFlag = false;
    String accMasSid = "0";
    String calcultedValue = "0";
    BaseRateLookup lookUp;
    @UiField("grossTadeSales")
    public CheckBox grossTadeSales;
    @UiField("demand")
    public CheckBox demand;
    @UiField("inventoryWithdraws")
    public CheckBox inventoryWithdraws;
    @UiField("useSelected")
    public CheckBox useSelected;
    @UiField("alternateHistory")
    public CheckBox alternateHistory;
    FixedDollarDTO fixedDto;
    @UiField("lookUpDsLayout")
    public GridLayout lookUpDsLayout;
    @UiField("dsLayout")
    public GridLayout dsLayout;

    @UiField("projectionTypeLayout")
    public GridLayout projectionTypeLayout;

    @UiField("projectionType")
    public OptionGroup projectionType;
    boolean status = false;
    boolean useSelect = false;
    /* isGenerate is to supress Frequency and Date ValueChangeListner During Generate Button*/
    boolean isGenerate = false;
    /* isFrequencyChanged is to supress Date ValueChangeListner During Value change in Frequency*/
    boolean isFrequencyChanged = false;
    /* isDateChanged is to not change dates During  Generate Logic*/
    boolean isDateChanged = false;

    @UiField("autoaccruals")
    public Label autoaccruals;

    @UiField("manualadjust")
    public Label manualadjust;

    @UiField("paymenttrueup")
    public Label paymenttrueup;

    @UiField("other")
    public Label other;
    @UiField("resetBtn")
    public Button resetBtn;

    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##00.00%");

    private static final DecimalFormat RATE = new DecimalFormat("#,##0.0");

    public Component getContent() {
        LOGGER.info("getContent method starts");
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/baseRateCalculation.xml"), this));
        Panel panel = new Panel();
        panel.setContent(layout);
        configureFields();
        LOGGER.info("getContent method ends");
        return panel;
    }

    /**
     * Base Rate Calculation Tab Design
     *
     * @param session
     * @param baseRateDTO
     */
    public BaseRateCalculation(final SessionDTO session, BaseRateDTO baseRateDTO, boolean lookUpFlag, List<String> ccpIds, String accMasSid, BaseRateLookup lookUp, FixedDollarDTO fixedDto) {
        this.session = session;
        this.baseRateDTO = baseRateDTO;
        this.lookUpFlag = lookUpFlag;
        this.accMasSid = accMasSid;
        this.lookUp = lookUp;
        this.fixedDto = fixedDto;
        baseRateDTO.setRateOrPayment("0");
        selLogic.setCcpIds(ccpIds);
        selLogic.setAccMasSid(accMasSid);
        baseRateDTO.setSessionId(session.getSessionId());
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/baseRateCalculation.xml"), this));
        configureFields();
    }

    /**
     * Configure Fields method
     *
     */
    protected void configureFields() {
        try {
            LOGGER.info("Inside Configure Fields================>");
            autoaccruals.addStyleName("leftacclabel");
            manualadjust.addStyleName("leftacclabel");
            paymenttrueup.addStyleName("leftacclabel");
            other.addStyleName("leftacclabel");
            manualRadio.setImmediate(true);
            manualRadio.addItem(MANUAL.getConstant());
            manualRadio.addItem(AVERAGE.getConstant());
            manualRadio.addItem(WEIGHTED_AVERAGE.getConstant());
            manualRadio.addItem(TIME_WEIGHTED_AVERAGE.getConstant());
            manualRadio.addItem(MOVING_AVERAGE.getConstant());
            manualRadio.addItem(GROWTH.getConstant());
            manualRadio.addItem(PROVISION_GROWTH.getConstant());
            manualRadio.addItem(SALES_GROWTH.getConstant());
            manualRadio.addItem(EXPONENTIAL_SMOOTHING.getConstant());
            manualRadio.addItem(DUMPING_FACTOR.getConstant());
            manualRadio.select(AVERAGE.getConstant());
            manualRadio.setItemEnabled(PROVISION_GROWTH.getConstant(), false);
            manualRadio.setItemEnabled(SALES_GROWTH.getConstant(), false);
            manualRadio.setItemEnabled(DUMPING_FACTOR.getConstant(), false);
            manualRadio.addStyleName("disableRadio");
            projectionType.addItem("Rate");
            projectionType.addItem("Payments");
            projectionType.select("Rate");
            projectionType.addStyleName("horizontal");
            projectionType.addStyleName("optionGroup-margin-left-align-size");
            manualRadio.setEnabled(true);
            provisionGrowth.setEnabled(Boolean.FALSE);
            salesGrowth.setEnabled(Boolean.FALSE);
            dumpingFactor.setEnabled(Boolean.FALSE);
            manual.setEnabled(Boolean.FALSE);
            manual.setImmediate(Boolean.TRUE);
            provisionGrowth.setImmediate(Boolean.TRUE);
            salesGrowth.setImmediate(Boolean.TRUE);
            dumpingFactor.setImmediate(Boolean.TRUE);
            useSelected.setImmediate(true);
            startDate.setDateFormat("MM/dd/yyyy");
            endDate.setDateFormat("MM/dd/yyyy");
            startDate.addStyleName("dateField-align-center");
            endDate.addStyleName("dateField-align-center");

            excelBtn.setIcon(excelExportImage);
            graphBtn.setIcon(graphImage);

            frequency.setNullSelectionAllowed(true);
            frequency.setImmediate(true);
            frequency.addItem(FrequencyConstants.ANNUALLY.getConstant());
            frequency.addItem(FrequencyConstants.SEMI_ANNUALLY.getConstant());
            frequency.addItem(FrequencyConstants.QUARTERLY.getConstant());
            frequency.addItem(FrequencyConstants.MONTHLY.getConstant());
            frequency.setValue(FrequencyConstants.QUARTERLY.getConstant());
            dumpingFactor.setValue("0.25");
            acctType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            acctType.setNullSelectionAllowed(true);
            acctType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            acctSubType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            acctSubType.setNullSelectionAllowed(true);
            acctSubType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            marketType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            marketType.setNullSelectionAllowed(true);
            marketType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            productName.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            productName.setNullSelectionAllowed(true);
            productName.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            ndc.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            ndc.setNullSelectionAllowed(true);
            ndc.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            ndc.addItem(IndicatorConstants.NDC8.getConstant());
            ndc.addItem(IndicatorConstants.NDC10.getConstant());
            ndc.addItem(IndicatorConstants.NDC11.getConstant());

            contract.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            contract.setNullSelectionAllowed(true);
            contract.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            company.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            company.setNullSelectionAllowed(true);
            company.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            salesBasisDdlb.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            salesBasisDdlb.setNullSelectionAllowed(true);
            salesBasisDdlb.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            salesBasisDdlb.addItem(IndicatorConstants.GROSS_TRADE_SALES.getConstant());
            salesBasisDdlb.addItem(IndicatorConstants.DEMAND.getConstant());
            salesBasisDdlb.addItem(IndicatorConstants.INVENTORY_WITHDRAWS.getConstant());
            salesBasisDdlb.addItem(IndicatorConstants.CONTRACT_SALES.getConstant());

            configureBaseRateCalcTable();
            tableLayout.addComponent(baseRateCalcTable);
            loadCompanyDdlb();

            configureAvailTable();
            if (!lookUpFlag) {
                availableTableLayout.addComponent(availableCustomer);

                HorizontalLayout controls = logic.createControls();
                HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
                availableTableLayout.addComponent(controlLayout);
                availableTableLayout.setSpacing(Boolean.TRUE);
            } else {
                availableTableLayout.setVisible(false);
                moveButtonLayout.setVisible(false);
                bottomButtonLayout.setVisible(false);
            }

            Panel sel = new Panel();
            if (!lookUpFlag) {
                sel.setContent(brSelectedCustomer);
            } else {
                sel.setContent(selectedCustomer);
            }
            selectedTableLayout.addComponent(sel);
            configureSelTable();
            HorizontalLayout selcontrols = selLogic.createControls();
            HorizontalLayout selcontrolLayout = CommonLogic.getResponsiveControls(selcontrols);
            selectedTableLayout.addComponent(selcontrolLayout);
            if (!comutils.getNull(String.valueOf(session.getAcctCloserMasterId())) && session.getAcctCloserMasterId() != 0) {
                if ("Withdrawn".equalsIgnoreCase(session.getWorkflowStatus())) {
                    status = true;
                }
                loadEditValues(status);
            }
            toDateDdlb.setImmediate(true);
            fromDateDdlb.setImmediate(true);
            startDate.setValue(new Date());

            grossTadeSales.setValue(Boolean.TRUE);
            demand.setEnabled(false);
            inventoryWithdraws.setEnabled(false);
            alternateHistory.setEnabled(false);
            grossTadeSales.setEnabled(false);
            grossTadeSales.setImmediate(true);
            demand.setImmediate(true);
            inventoryWithdraws.setImmediate(true);
            alternateHistory.setImmediate(true);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        if (lookUpFlag) {
            dsLayout.setVisible(false);
            try {

                companyTxt.setValue(fixedDto.getCompanyMasterSid());
                companyTxt.setEnabled(false);
                marketTypeTxt.setValue(fixedDto.getMarketType());
                marketTypeTxt.setEnabled(false);
                acctTypeTxt.setValue(fixedDto.getAcctType());
                acctTypeTxt.setEnabled(false);
                acctSubTypeTxt.setValue(fixedDto.getAcctSubType());
                acctSubTypeTxt.setEnabled(false);
                contractIdTxt.setValue(fixedDto.getContractSid());
                contractIdTxt.setEnabled(false);
                productNameTxt.setValue(fixedDto.getProduct());
                productNameTxt.setEnabled(false);
                ndcTxt.setValue(fixedDto.getProductIdentifier());
                ndcTxt.setEnabled(false);

                customerGroupTxt.setValue(fixedDto.getCustomerGroup());
                customerGroupTxt.setEnabled(false);
                productGroupTxt.setValue(fixedDto.getProductGroup());
                productGroupTxt.setEnabled(false);

                final String method = fixedDto.getMethodology();

                if (AUTO_RECONCILE.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", false);
                    projectionType.setItemEnabled("Payments", false);
                } else if (SALES.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", false);
                    projectionType.setItemEnabled("Payments", true);
                    projectionType.select("Payments");
                } else if (RATE_ADJUSTMENT.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", true);
                    projectionType.setItemEnabled("Payments", false);
                    projectionType.select("Rate");
                } else if (ACCRUALS.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", false);
                    projectionType.setItemEnabled("Payments", true);
                    projectionType.select("Payments");
                } else if (ACTUALS.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", false);
                    projectionType.setItemEnabled("Payments", true);
                    projectionType.select("Payments");
                } else if (MANUAL.getConstant().equals(method)) {
                    projectionType.setItemEnabled("Rate", false);
                    projectionType.setItemEnabled("Payments", false);
                    projectionType.select("Payments");
                }

                dumpingFactor.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Notifies this listener that the Property's value has
                     * changed.
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void valueChange(final Property.ValueChangeEvent event) {
                        if (Integer.valueOf(dumpingFactor.getValue()) > 1) {

                            AbstractNotificationUtils.getErrorNotification("Error", " damping factor value should not exceed 1");

                        }
                    }
                });

            } catch (Exception ex) {
                LOGGER.error(ex);
            }

            selLogic.setLookUpData();
            SuggestedAdjLookUpLogic suggestedAdjLookUpLogic = new SuggestedAdjLookUpLogic();
            baseRateDTO.setAcMasterSid(Integer.parseInt(selLogic.getAccMasSid()));

            List<BaseRateDTO> searchResults = suggestedAdjLookUpLogic.getGenerateResult(selLogic.getCcpIds(), String.valueOf(frequency.getValue()), selLogic.getAccMasSid());
            baseRateCalcContainer.addAll(searchResults);
        } else {
            projectionTypeLayout.setVisible(false);
            lookUpDsLayout.setVisible(false);
        }

        average.setConverter(percentFormat);
        weightedAverage.setConverter(percentFormat);
        timeWeightedAverage.setConverter(percentFormat);
        movingAverage.setConverter(percentFormat);
        growth.setConverter(percentFormat);
        provisionGrowth.setConverter(percentFor);
        salesGrowth.setConverter(percentFor);
        exponentialSmoothing.setConverter(percentFormat);

    }

    public void configureBaseRateCalcTable() {
        try {
            baseRateCalcTable.markAsDirty();
            tableLayout.removeAllComponents();
            baseRateCalcTable = new ExtFilterTable();
            String frequencyVal = String.valueOf(frequency.getValue());
            headerDTO = new CustomTableHeaderDTO();
            checkedList.clear();
            if (!isDateChanged) {
                doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "DS", String.valueOf(frequency.getValue()));
            }
            final String timePeriodFrom = String.valueOf(fromDateDdlb.getValue());
            final String timePeriodTo = String.valueOf(toDateDdlb.getValue());
            headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, true, null);
            baseRateCalcTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            baseRateCalcTable.setWidth("830px");
            baseRateCalcTable.setHeight("500px");
            baseRateCalcTable.setPageLength(5);
            baseRateCalcTable.setSortEnabled(false);
            baseRateCalcContainer = new ExtTreeContainer<BaseRateDTO>(BaseRateDTO.class,DataStructureMode.MAP);
            baseRateCalcContainer.setColumnProperties(headerDTO.getProperties());
            baseRateCalcTable.setContainerDataSource(baseRateCalcContainer);
            baseRateCalcTable.setVisibleColumns(headerDTO.getSingleColumns().subList(1, headerDTO.getSingleColumns().size()).toArray());
            baseRateCalcTable.setColumnHeaders(headerDTO.getSingleHeaders().subList(1, headerDTO.getSingleHeaders().size()).toArray(new String[headerDTO.getSingleHeaders().size() - 1]));
            for (Object visibleColumns : headerDTO.getSingleColumns()) {
                baseRateCalcTable.setColumnWidth(visibleColumns, 150);

            }

            for (Object property : baseRateCalcTable.getVisibleColumns()) {
                baseRateCalcTable.setColumnAlignment(property, ExtCustomTable.ALIGN_RIGHT);
            }
            for (Object obj : headerDTO.getSingleColumns()) {
                baseRateCalcTable.setColumnCheckBox(obj, true, false);

            }

            baseRateCalcTable.addStyleName("center-check");
            baseRateCalcTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if (event.isChecked()) {

                        checkedList.add(event.getPropertyId().toString());

                    } else {
                        checkedList.remove(event.getPropertyId().toString());
                    }
                }

            });

            tableLayout.addComponent(baseRateCalcTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.info("Inside Enter Method------->");
    }

    @UiHandler("customerGroup")
    public void customerGroup(CustomTextField.ClickEvent event) {
        LOGGER.info("Customer Group Popup Clicked");
        List<String> companiesLevel = new ArrayList<String>();
        List<String> companies = null;
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        final CustomerGroupLookUp customerGroupLookupWindow = new CustomerGroupLookUp("Customer Group", "Customer Group Lookup", customerGroup, StringUtils.EMPTY, custGroupSid);
        UI.getCurrent().addWindow(customerGroupLookupWindow);
        customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                custGroupSid = customerGroupLookupWindow.getGroupSid();
            }
        });
        LOGGER.info("Customer Group Popup Ended");
    }

    /**
     * Product Group Popup
     *
     * @param event
     */
    @UiHandler("productGroup")
    public void productGroup(CustomTextField.ClickEvent event) {
        LOGGER.info("Product Group Popup Clicked");
        List<String> companiesLevel = new ArrayList<String>();
        List<String> companies = null;
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        final ProductGroupLookUp prodGroupLookupWindow = new ProductGroupLookUp("Product Group", "Product Group Lookup", productGroup, StringUtils.EMPTY, prodGroupSid);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                prodGroupSid = prodGroupLookupWindow.getGroupSid();
            }
        });
        LOGGER.info("Product Group Popup Ended");
    }

    /**
     * Load Company Ddlb
     *
     */
    private void loadCompanyDdlb() throws Exception {
        LOGGER.info("Entering loadCompanyDdlb method");
        company = commonLogic.commonLoadingDdlb(company, "0", "companyForm");
        LOGGER.info("End of loadCompanyDdlb method");
    }
    /*Load Market Type*/

    @UiHandler("company")
    public void loadMarketDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadMarketDdlb method");

        marketType = commonLogic.commonLoadingDdlb(marketType, "0", "marketTypeForm");
        LOGGER.info("End of loadMarketDdlb method");
    }

    /*Load Account Type*/
    @UiHandler("marketType")
    public void loadDiscDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadDiscDdlb method");
        String id = (String.valueOf(marketType.getValue()));
        detachListeners(acctType);
        acctType = commonLogic.commonLoadingDdlb(acctType, String.valueOf(id == null ? "0" : id), "acctTypeForm");
        attachListeners(acctType);
        LOGGER.info("End of loadDiscDdlb method");
    }

    /*Load Account SubType*/
    @UiHandler("acctType")
    public void loadDiscSubDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadDiscSubDdlb method");
        String id = (String.valueOf(acctType.getValue()));
        acctSubType = commonLogic.commonLoadingDdlb(acctSubType, id, "acctSubTypeForm");
        LOGGER.info("End of loadDiscSubDdlb method");
    }

    /*Load Account SubType*/
    @UiHandler("acctType")
    public void loadcontractDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        String id = (String.valueOf(acctType.getValue()));
        contract = commonLogic.commonLoadingDdlb(contract, id, "contractForm");
        LOGGER.info("End of loadcontractDdlb method");
    }

    /*Load Account SubType*/
    @UiHandler("acctType")
    public void loadProductDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        String id = (String.valueOf(acctType.getValue()));
        productName = commonLogic.commonLoadingDdlb(productName, id, "productForm");
        LOGGER.info("End of loadcontractDdlb method");
    }

    /*Load Account SubType*/
    @UiHandler("ndc")
    public void loadAvailTables(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadAvailTables method");
        LoadDashBoardTree();
        LOGGER.info("End of loadAvailTables method");
    }

    @UiHandler("fromDateDdlb")
    public void fromDateGenerate(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Value of isFrequency in FromDAte" + isFrequencyChanged);
        LOGGER.info("Value of Boolean in FromDAte" + (isGenerate && (!isFrequencyChanged)));
        if (isGenerate && (!isFrequencyChanged)) {
            LOGGER.info("Entering From Date");
            isDateChanged = true;
            generateLogic();
            isDateChanged = false;
        }

    }

    @UiHandler("toDateDdlb")
    public void toDateGenerate(Property.ValueChangeEvent event) throws Exception {
        if (isGenerate && !isFrequencyChanged) {
            isDateChanged = true;
            LOGGER.info("Entering From Date");
            generateLogic();
            isDateChanged = false;
        }
    }

    public void LoadDashBoardTree() {
        LOGGER.info("Entering getProcessedTree method");
        try {
            setValues(dto);
            logic.setData(dto, "ac.availCustomers", true);
            for (Object propertyId : baseRateCalcTable.getVisibleColumns()) {
                baseRateCalcTable.setConverter(propertyId, percentFormat);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("End of getProcessedTree method" + availTreeContainer.size());
    }

    public void setValues(TreeDTO dto) {
        if (contract.getValue() != null && contract.getValue() != StringUtils.EMPTY && contract.getValue() != "null") {
            String contractDto = String.valueOf(contract.getValue());
            dto.setContractSid(contractDto);
        }
        if (productName.getValue() != null && productName.getValue() != StringUtils.EMPTY && productName.getValue() != "null") {
            String itemDto = String.valueOf(productName.getValue());
            dto.setItemSid(itemDto);
        }

        if (custGroupSid != null && custGroupSid != StringUtils.EMPTY && custGroupSid != "null") {
            dto.setCustGroupSid(custGroupSid);
        }
        if (prodGroupSid != null && prodGroupSid != StringUtils.EMPTY && prodGroupSid != "null") {
            dto.setPrdGroupSid(prodGroupSid);
        }
        dto.setContractTypeSid(String.valueOf(marketType.getValue()));
        dto.setAcctTypeSid(String.valueOf(acctType.getValue()));
        dto.setAcctSubTypeSid(String.valueOf(acctSubType.getValue()));
        dto.setSessionId(Integer.parseInt(session.getSessionId()));
        dto.setFrequency(String.valueOf(frequency.getValue()));
        dto.setNdc(String.valueOf(ndc.getValue()));
    }

    public void configureAvailTable() {
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = utils.getTableColumns(fullHeader);
        resultBean = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP);
        logic.setControlTable(availableCustomer);
        resultBean.setColumnProperties(rightDTO.getProperties());
        logic.sinkItemPerPageWithPageLength(false);
        logic.setContainerDataSource(resultBean);
        logic.setPageLength(10);
        availableCustomer.setWidth(100, Unit.PERCENTAGE);
        availableCustomer.markAsDirty();
        availableCustomer.addStyleName("availableCustomer");
        availableCustomer.addStyleName("table-scroll");
        availableCustomer.addStyleName("centerCheckBox");
        availableCustomer.setEditable(
                true);
        availableCustomer.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        availableCustomer.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        availableCustomer.setColumnCheckBox(
                "checkRecord", Boolean.TRUE);
        availableCustomer.setTableFieldFactory(new TableFieldFactory() {

            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            TreeDTO trDto = (TreeDTO) itemId;
                            Boolean checkValue = check.getValue();
                            trDto.addBooleanProperties(propertyId, checkValue);

                            int updatedRecordsNo = updateCheckedRecord(trDto, checkValue ? 1 : 0, "BaseRateAvail");
                            availableCustomer.setRefresh(false);
                            availableCustomer.setRefresh(true);
                        }
                    });
                    return check;
                }
                return null;
            }
        }
        );
        availableCustomer.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection list = availableCustomer.getItemIds();
                TreeDTO allCheck = new TreeDTO();
                for (Object obj : list) {
                    resultBean.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    updateCheckedRecord(allCheck, event.isChecked() ? 1 : 0, "BaseRateAvail");
                }

            }
        });

        for (Object property : availableCustomer.getVisibleColumns()) {
            if (String.valueOf(property).contains("checkRecord")) {

                availableCustomer.setColumnWidth(property, 122);

            }
        }
    }

    public void configureSelTable() {
        if (!lookUpFlag) {
            fullHeader = new CustomTableHeaderDTO();
            checkedSelList.clear();
            leftDTO = utils.getTableColumns(fullHeader);
            selectedBean
                    = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP
                    );
            brSelLogic.setControlTable(brSelectedCustomer);
            selectedBean.setColumnProperties(rightDTO.getProperties());
            brSelLogic.sinkItemPerPageWithPageLength(false);
            brSelLogic.setContainerDataSource(selectedBean);
            brSelLogic.setPageLength(10);
            brSelectedCustomer.setWidth(
                    100, Unit.PERCENTAGE);
            brSelectedCustomer.markAsDirty();
            brSelectedCustomer.addStyleName("table-scroll");
            brSelectedCustomer.addStyleName("centerCheckBox");
            brSelectedCustomer.setEditable(
                    true);
            brSelectedCustomer.setVisibleColumns(leftDTO.getSingleColumns().toArray());
            brSelectedCustomer.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
            brSelectedCustomer.setColumnCheckBox(
                    "checkRecord", Boolean.TRUE);
            brSelectedCustomer.setTableFieldFactory(new TableFieldFactory() {

                public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                    if (propertyId.equals("checkRecord")) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();

                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                TreeDTO trDto = (TreeDTO) itemId;
                                Boolean checkValue = check.getValue();
                                trDto.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = updateCheckedRecord(trDto, checkValue ? 0 : 1, "BaseRateAvail");
                                brSelectedCustomer.setRefresh(false);
                                brSelectedCustomer.setRefresh(true);
                                if (checkValue) {
                                    if (!checkedSelList.contains(itemId)) {
                                        checkedSelList.add(((TreeDTO) itemId));
                                    }
                                } else {
                                    checkedSelList.remove(itemId);
                                }
                            }
                        });
                        return check;
                    }
                    return null;
                }
            }
            );

            brSelectedCustomer.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    Collection list = brSelectedCustomer.getItemIds();
                    TreeDTO allSelCheck = new TreeDTO();
                    for (Object obj : list) {
                        selectedBean.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                        updateCheckedRecord(allSelCheck, event.isChecked() ? 1 : 0, StringUtils.EMPTY);
                    }
                }
            });

            for (Object property : brSelectedCustomer.getVisibleColumns()) {
                if (String.valueOf(property).contains("checkRecord")) {
                    brSelectedCustomer.setColumnWidth(property, 122);
                }
            }
        } else {
            fullHeader = new CustomTableHeaderDTO();

            leftDTO = utils.getTableColumnsLeft(fullHeader);

            rightDTO = utils.getTableColumnsForSuggestedAdj(fullHeader);

            checkedSelList.clear();

            selectedBean
                    = new ExtTreeContainer<TreeDTO>(TreeDTO.class,DataStructureMode.MAP
                    );
            selectedBean.setColumnProperties(rightDTO.getProperties());
            selLogic.setPageLength(
                    8);
            selLogic.setContainerDataSource(selectedBean);

            selLogic.sinkItemPerPageWithPageLength(
                    false);
            selectedCustomer.setWidth(
                    100, Unit.PERCENTAGE);
            selectedCustomer.setHeight(100, Unit.PERCENTAGE);
            selectedCustomer.setSplitPosition(
                    100, Unit.PIXELS);
            selectedCustomer.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            leftSelTable = selectedCustomer.getLeftFreezeAsTable();
            rightSelTable = selectedCustomer.getRightFreezeAsTable();
            leftSelTable.setEditable(
                    true);
            leftSelTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
            leftSelTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
            leftSelTable.setColumnCheckBox("checkRecord", Boolean.TRUE);
            rightSelTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
            rightSelTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
            leftSelTable.setTableFieldFactory(new TableFieldFactory() {

                public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                    if (propertyId.equals("checkRecord")) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();

                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                TreeDTO trDto = (TreeDTO) itemId;
                                Boolean checkValue = check.getValue();
                                trDto.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = updateCheckedRecord(trDto, checkValue ? 1 : 0, StringUtils.EMPTY);
                                selectedCustomer.getLeftFreezeAsTable().setRefresh(false);
                                selectedCustomer.getLeftFreezeAsTable().setRefresh(true);
                                if (checkValue) {
                                    if (!checkedSelList.contains(itemId)) {
                                        checkedSelList.add(((TreeDTO) itemId));
                                    }
                                } else {
                                    checkedSelList.remove(itemId);
                                }
                            }
                        });
                        return check;
                    }
                    return null;
                }
            }
            );

            leftSelTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    Collection list = leftSelTable.getItemIds();
                    checkedSelList.clear();
                    TreeDTO allSelCheck = new TreeDTO();
                    for (Object obj : list) {
                        selectedBean.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                        updateCheckedRecord(allSelCheck, event.isChecked() ? 1 : 0, StringUtils.EMPTY);
                    }

                }
            });

            for (Object property : leftSelTable.getVisibleColumns()) {

                leftSelTable.setColumnWidth(property, 100);
                leftSelTable.setColumnAlignment(property, ExtCustomTable.ALIGN_CENTER);

            }

            for (Object property : rightSelTable.getVisibleColumns()) {
                rightSelTable.setColumnWidth(property, 100);
                rightSelTable.setColumnAlignment(property, ExtCustomTable.ALIGN_CENTER);

            }

        }
    }

    /**
     * next Btn logic
     *
     * @param event
     */
    @UiHandler("nextBtn")
    public void nextBtnClick(Button.ClickEvent event
    ) {
        if (!lookUpFlag) {
            brSelectedCustomer.removeAllItems();
        } else {
            leftSelTable.removeAllItems();
        }
        setValues(dto);
        if (!lookUpFlag) {
            brSelLogic.setData(dto, "ac.brselected", true);
        } else {
            selLogic.setData(dto, "ac.brselected", true);
        }
        logic.setData(dto, "ac.availCustomers", true);
        isGenerate = false;
    }

    /**
     * previous Btn logic
     *
     * @param event
     */
    @UiHandler("preBtn")
    public void preBtnClick(Button.ClickEvent event
    ) {
        brSelectedCustomer.removeAllItems();
        for (int i = 0; i < checkedSelList.size(); i++) {
            selectedBean.removeItem(checkedSelList.get(i));
        }
        checkedSelList.clear();
        dto.setSelCompList(checkedSelList);
        setValues(dto);

        if (!lookUpFlag) {
            brSelLogic.setData(dto, "ac.brselected", true);
        } else {
            selLogic.setData(dto, "ac.brselected", true);
        }
        isGenerate = false;
    }

    /**
     * next All Btn logic
     *
     * @param event
     */
    @UiHandler("nextallBtn")
    public void nextallBtnClick(Button.ClickEvent event) {
        setValues(dto);
        if (!lookUpFlag) {
            brSelLogic.setData(dto, "ac.availCustomers", true);
        } else {
            selLogic.setData(dto, "ac.availCustomers", true);
        }
        isGenerate = false;
    }

    /**
     * previous All Btn logic
     *
     * @param event
     */
    @UiHandler("preallBtn")
    public void preallBtnClick(Button.ClickEvent event
    ) {
        if (!lookUpFlag) {
            brSelectedCustomer.removeAllItems();
        } else {
            leftSelTable.removeAllItems();
        }

        isGenerate = false;
    }

    /**
     * previous All Btn logic
     *
     * @param event
     */
    @UiHandler("frequency")
    public void loadFrom(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering Frequency");
        isFrequencyChanged = true;
        String freq = String.valueOf(frequency.getValue());
        if (!comutils.getNull(freq)) {
            brLogic.setSessionDates(session);
            DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "DS", String.valueOf(frequency.getValue()));
            if (isGenerate) {
                generateLogic();
            }
            isFrequencyChanged = false;
        } else {
            AbstractNotificationUtils.getErrorNotification("Select Frequency", "Please select a frequency");
        }
    }

    /**
     * Detach listeners.
     *
     * @param field the field
     */
    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners
                = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class
                );
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }

    }

    public void attachListeners(final AbstractField field) {

        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    loadDiscSubDdlb(event);
                    loadcontractDdlb(event);
                    loadProductDdlb(event);

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    /**
     * To update the checked record
     */
    private int updateCheckedRecord(TreeDTO dto, int val, String checkTable) {
        LOGGER.info("Entering updateCheckedRecord ->" + dto.getComapnySid() + dto.getCcpSid());
        String hierarchyNo = StringUtils.EMPTY;
        int updatedRecordCount = 0;
        int maxTreeLevelno = 0;
        int treeLevelNo = 0;
        int count = 0;
        boolean checkValue = true;
        setValues(dto);
        commonLogic.updateCheckRecord(dto, val, checkTable);
        LOGGER.info(" Ending updateCheckedRecord ");
        return updatedRecordCount;
    }

    /**
     * To update check records count for parent
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {
        TreeDTO tempDto = (TreeDTO) itemId;
        boolean check = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties("checkRecord", checkValue);
        if (checkValue) {
            logic.getContainerDataSource().getContainerProperty(itemId, "checkRecord").setValue(checkValue);
        }
    }

    /**
     * next Btn logic
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering generate");
        if (selectedBean.size() > 0) {
            dto.setGlComapnySid(String.valueOf(company.getValue()));
            dto.setLevelNo(0);
            setValues(dto);
            if (!isGenerate) {
                if (session.getAcctCloserMasterId() == 0) {
                    baseRateDTO = brLogic.saveBRMaster(dto, generate);
                    session.setAcctCloserMasterId(baseRateDTO.getAcMasterSid());
                } else {
                    baseRateDTO.setAcMasterSid(session.getAcctCloserMasterId());
                }
            }
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.INFO, "No Selected Records", "Please verify that all required fields have been populated. At least one value must be populated in the ‘Selected’ Customers/Products’ list view.", ButtonId.OK);
        }
        LOGGER.info("Ending generate");
    }

    private void generateLogic() {

        isGenerate = false;
        LOGGER.info("Generate Logic has been Called");
        configureBaseRateCalcTable();
        baseRateCalcContainer.removeAllItems();
        String toDate = toDateDdlb.getValue().toString();
        List<BaseRateDTO> searchResults = brLogic.setBRValues(String.valueOf(frequency.getValue()), baseRateDTO.getAcMasterSid(), String.valueOf(fromDateDdlb.getValue()), toDate);
        baseRateCalcContainer.addAll(searchResults);
        isGenerate = true;
    }

    /**
     * next Btn logic
     *
     * @param event
     */
    @UiHandler("calculateBtn")
    public void calculateBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering calculate" + checkedList.size());
        List periodSid = new ArrayList();
        try {
            setMethodologyValues(baseRateDTO);
            final String manual = manualRadio.getValue().toString();
            if (checkedList.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("No Periods Selected", "Please select the periods for calculation");
            } else if (baseRateDTO.getMethodStartDate() == null) {
                AbstractNotificationUtils.getErrorNotification("No Start Date", "Please select the methodology Start Date");
            } else if (baseRateDTO.getMethodEndDate() == null) {
                AbstractNotificationUtils.getErrorNotification("No End Date", "Please select the methodology End Date");
            } else if (baseRateDTO.getMethodEndDate() != null && startDate.getValue() != null && (startDate.getValue().after(baseRateDTO.getMethodEndDate()) || baseRateDTO.getMethodStartDate().compareTo(baseRateDTO.getMethodEndDate()) == 0)) {
                MessageBox.showPlain(Icon.ERROR, "ERROR", "Start Date should be less than end Date.", ButtonId.OK);
            } else if (baseRateDTO.getMethodStartDate() != null && endDate.getValue() != null && (endDate.getValue().before(baseRateDTO.getMethodStartDate()) || baseRateDTO.getMethodEndDate().compareTo(baseRateDTO.getMethodStartDate()) == 0)) {
                MessageBox.showPlain(Icon.ERROR, "ERROR", "Start Date should be less than end Date.", ButtonId.OK);
            } else {
                if (lookUpFlag) {
                    if (String.valueOf(projectionType.getValue()).equals("Rate")) {
                        baseRateDTO.setRateOrPayment("0");
                    } else {
                        baseRateDTO.setRateOrPayment("1");
                    }
                }
                final HeaderLookUP variableLookup = new HeaderLookUP(checkedList, baseRateDTO, manual);
                UI.getCurrent().addWindow(variableLookup);
                variableLookup.setImmediate(true);
                for (Object columns : baseRateCalcTable.getDoubleHeaderVisibleColumns()) {
                    baseRateCalcTable.setDoubleHeaderColumnCheckBox(columns, true, false);
                }
                variableLookup.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        String values = variableLookup.calc;
                        if (comutils.getNull(values)) {
                            values = "0";
                        }
                        setMethodValues(values, manual);
                    }
                });
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending calculate");
    }

    /**
     * reset Btn logic
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering resetBtnClick");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    resetSelectionCriteria();
                    resetLogic();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset all the fields to default ");

        LOGGER.info("Ending resetBtnClick");
    }

    public WorkflowMasterDTO setWorkflow(String status, boolean flag, WorkflowMasterDTO wfSId) {
        WorkflowMasterDTO wfId = new WorkflowMasterDTO();
        wfSId.setAacSid(baseRateDTO.getAcMasterSid());
        wfSId.setProjectionId(baseRateDTO.getAcMasterSid());
        wfId = brLogic.setWorkflow(baseRateDTO.getAcMasterSid(), status, flag, wfSId);

        return wfId;

    }

    /**
     * excel Btn logic
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelBtnClick(Button.ClickEvent event) {
        try {
            tableLayout.addComponent(excelTable);
            excelTable.setVisible(false);
            excelContainer
                    = new ExtTreeContainer<BaseRateDTO>(BaseRateDTO.class,DataStructureMode.MAP
                    );
            CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
            doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "DS", String.valueOf(frequency.getValue()));
            final String timePeriodFrom = String.valueOf(fromDateDdlb.getValue());
            final String timePeriodTo = String.valueOf(toDateDdlb.getValue());
            excelHeader = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, true, null);

            excelContainer.setColumnProperties(headerDTO.getProperties());
            excelTable.setContainerDataSource(excelContainer);

            excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
            List<BaseRateDTO> searchResults = brLogic.setBRValues(String.valueOf(frequency.getValue()), baseRateDTO.getAcMasterSid(), String.valueOf(fromDateDdlb.getValue()), String.valueOf(toDateDdlb.getValue()));
            for (BaseRateDTO summary : searchResults) {
                excelContainer.addBean(summary);
            }
            Map<String, String> formatter = new HashMap<String, String>();

            formatter.put(
                    "currencyTwoDecimal", "Amount");
            formatter.put(
                    "percentTwoDecimal", "Rate");

            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Base Rate Calculation", "Base Rate Calculation", "BRCalculation.xls", false);

            export.export();

            tableLayout.removeComponent(excelTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Inserts the record for summary
     */
    public void insertSummary() {
        baseRateDTO.setSessionId(session.getSessionId());
        brLogic.insertSummary(baseRateDTO);
    }

    public void setMethodologyValues(BaseRateDTO dto) {
        dto.setMethodologyName(manualRadio.getValue().toString());
        dto.setMethodStartDate(startDate.getValue());
        dto.setMethodEndDate(endDate.getValue());
        dto.setFrequecny(frequency.getValue().toString());
        dto.setProvisionGrowthRate(String.valueOf(provisionGrowth.getValue()).replace("%", StringUtils.EMPTY));
        dto.setSalesGrowthRate(String.valueOf(salesGrowth.getValue()).replace("%", StringUtils.EMPTY));
        dto.setDampingFactor(String.valueOf(dumpingFactor.getValue()).replace("%", StringUtils.EMPTY));
    }

    public void setMethodValues(String value, String field) {
        LOGGER.info("Entering setMethodValues" + value);
        if (MANUAL.getConstant().equals(field)) {
            manual.setValue(value);
        } else if (AVERAGE.getConstant().equals(field)) {
            average.setValue(value);

        } else if (WEIGHTED_AVERAGE.getConstant().equals(field)) {
            weightedAverage.setValue(value);
        } else if (TIME_WEIGHTED_AVERAGE.getConstant().equals(field)) {
            timeWeightedAverage.setValue(value);
        } else if (MOVING_AVERAGE.getConstant().equals(field)) {
            movingAverage.setValue(value);
        } else if (GROWTH.getConstant().equals(field)) {
            growth.setValue(value);
        } else if (EXPONENTIAL_SMOOTHING.getConstant().equals(field)) {
            exponentialSmoothing.setValue(value);
        }

        calcultedValue = value;
    }

    /**
     * reset Btn logic for methodology section
     *
     * @param event
     */
    @UiHandler("resetCalcBtn")
    public void resetCalcBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering resetCalcBtnClick");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                resetCalculationSelection();
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset all the fields to default ");

        LOGGER.info("Ending resetCalcBtnClick");
    }

    /*Change of manual radio button*/
    @UiHandler("manualRadio")
    public void manualChange(Property.ValueChangeEvent event) throws Exception {
        String value = String.valueOf(manualRadio.getValue());
        manual.setEnabled(Boolean.FALSE);
        provisionGrowth.setEnabled(Boolean.FALSE);
        salesGrowth.setEnabled(Boolean.FALSE);
        dumpingFactor.setEnabled(Boolean.FALSE);
        if (GROWTH.getConstant().equals(value)) {
            provisionGrowth.setEnabled(Boolean.TRUE);
            salesGrowth.setEnabled(Boolean.TRUE);
            dumpingFactor.setEnabled(Boolean.TRUE);
        }
        if (EXPONENTIAL_SMOOTHING.getConstant().equals(value)) {
            dumpingFactor.setEnabled(true);
        }

        if (MANUAL.getConstant().equals(value)) {
            manual.setEnabled(true);
        }

    }

    /**
     * reset Btn logic for methodology section
     *
     * @param event
     */
    @UiHandler("submitMethodBtn")
    public void submitMethodBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering submitMethodBtnClick");
        if (lookUpFlag) {
            lookUp.close();
        } else {
            final String selMethod = manualRadio.getValue().toString();
            insertSummary();
            brLogic.updateMethodologyFlag(baseRateDTO.getAcMasterSid(), selMethod);
        }
        LOGGER.info("Ending submitMethodBtnClick");
    }

    @UiHandler("graphBtn")
    public void graphBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering Graph");
        BaseRateGraph graph = new BaseRateGraph(baseRateCalcContainer.getBeans(), headerDTO.getSingleColumns(), headerDTO.getSingleHeaders());
        UI.getCurrent().addWindow(graph);
        graph.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
            }
        });
        LOGGER.info("Ending Graph");
    }

    /**
     * This method will save the master records
     */
    public void saveLogic() {
        LOGGER.info("Entering saveLogic");
        brLogic.saveLogic(baseRateDTO.getAcMasterSid());
        LOGGER.info("Ending saveLogic");
    }

    public String getCalcultedValue() {
        return calcultedValue;
    }

    public void setCalcultedValue(String calcultedValue) {
        this.calcultedValue = calcultedValue;
    }

    /**
     * Reset Selection criteria
     */
    public void resetSelectionCriteria() {
        try {
            company.setValue("0");
            acctType.setValue("0");
            acctSubType.setValue("0");
            marketType.setValue("0");
            contract.setValue("0");
            productName.setValue("0");
            ndc.setValue(IndicatorConstants.SELECT_ONE.getConstant());
            productGroup.setValue(StringUtils.EMPTY);
            customerGroup.setValue(StringUtils.EMPTY);
            availableCustomer.removeAllItems();
            brSelectedCustomer.removeAllItems();
            if (lookUpFlag) {
                leftSelTable.removeAllItems();
            }

            checkedSelList.clear();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Reset calculation selection
     */
    public void resetCalculationSelection() {
        manual.setValue(StringUtils.EMPTY);
        average.setValue(StringUtils.EMPTY);
        weightedAverage.setValue(StringUtils.EMPTY);
        timeWeightedAverage.setValue(StringUtils.EMPTY);
        movingAverage.setValue(StringUtils.EMPTY);
        growth.setValue(StringUtils.EMPTY);
        provisionGrowth.setValue(StringUtils.EMPTY);
        salesGrowth.setValue(StringUtils.EMPTY);
        exponentialSmoothing.setValue(StringUtils.EMPTY);
        dumpingFactor.setValue(StringUtils.EMPTY);
    }

    /**
     * Reset Base Rate container
     */
    public void resetBaseRateCalcContainer() {
        baseRateCalcContainer.removeAllItems();
    }

    /**
     * .
     * This method is to save the accruals once the base rate is saved
     */
    public void saveAccruals() {
        brLogic.callAccrualProcedure(baseRateDTO);
    }

    public void loadEditValues(boolean status) {
        LOGGER.info("Entering loadEditValues" + session.getAcctCloserMasterId());
        try {
            baseRateDTO = brLogic.loadEdit(session.getAcctCloserMasterId());
            company.setValue(StringUtils.EMPTY + baseRateDTO.getGlComapnySid());
            detachListeners(marketType);
            marketType = commonLogic.commonLoadingDdlb(marketType, "0", "marketTypeForm");
            marketType.setValue(StringUtils.EMPTY + baseRateDTO.getContractTypeSid());
            detachListeners(acctType);
            acctType = commonLogic.commonLoadingDdlb(acctType, String.valueOf(marketType.getValue()), "acctTypeForm");
            acctType.setValue(StringUtils.EMPTY + baseRateDTO.getDiscType());
            detachListeners(acctSubType);
            acctSubType = commonLogic.commonLoadingDdlb(acctSubType, String.valueOf(acctType.getValue()), "acctSubTypeForm");
            acctSubType.setValue(StringUtils.EMPTY + baseRateDTO.getDiscSubType());
            detachListeners(contract);
            contract = commonLogic.commonLoadingDdlb(contract, String.valueOf(acctType.getValue()), "contractForm");
            contract.setValue(StringUtils.EMPTY + baseRateDTO.getContract());
            detachListeners(productName);
            productName = commonLogic.commonLoadingDdlb(productName, String.valueOf(acctType.getValue()), "productForm");
            productName.setValue(StringUtils.EMPTY + baseRateDTO.getProduct());
            detachListeners(ndc);
            ndc.setValue(baseRateDTO.getNdc());
            TreeDTO tDto = new TreeDTO();
            setValues(tDto);
            tDto.setAccountClosureMasterSid(String.valueOf(session.getAcctCloserMasterId()));
            brSelLogic.setData(tDto, "ac.wfSelComp", true);
            company.setEnabled(status);
            marketType.setEnabled(status);
            acctType.setEnabled(status);
            acctSubType.setEnabled(status);
            contract.setEnabled(status);
            productName.setEnabled(status);
            ndc.setEnabled(status);
            resetBtn.setEnabled(status);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending loadEditValues");
    }

    @UiHandler("useSelected")
    public void useSelectedClick(Property.ValueChangeEvent event) {
        if (checkedList.size() > 0) {
            AbstractNotificationUtils.getErrorNotification("Only One Period", "only one accrual period can be selected");
            useSelected.setValue(false);
        }
    }

    /**
     * This method will reset the check record in database
     */
    public void resetLogic() {
        LOGGER.info("Entering reset logic");
        brLogic.resetBRSummary(session.getSessionId());
        LOGGER.info("Ending reset logic");

    }
}
