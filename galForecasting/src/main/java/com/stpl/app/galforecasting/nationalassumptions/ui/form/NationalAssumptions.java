package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.app.galforecasting.nationalassumptions.dto.BaselinePeriodDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.PriceTypeDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.SessionDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils.getQuator;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.PROJECTION_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SESSION_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.AMP;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.AVERAGE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.AVERAGE_QUARTER_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.BEGINNING_QUARTER_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.BEST_PRICE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.CHECK;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.CPI_U;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.DAY_WEIGHTED_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.EMPTYSTRING;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.ENDING_QUARTER_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.FREQUENCY;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.GROWTH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.MID_QUARTER_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.NON_FAMP;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PERIOD;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.ROLLING_AVERAGE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.SALES_WEIGHTED_WAC;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.SINGLE_PERIOD;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.TYPE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.WAC_FLEX;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.WEIGHTED_AVG;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class NationalAssumptions.
 */
public class NationalAssumptions extends CustomComponent implements View {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -8731003153382287238L;

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NationalAssumptions.class);

    /**
     * The price type ddlb.
     */
    @UiField("priceTypeDdlb")
    ComboBox priceTypeDdlb;

    /**
     * The baseline start period.
     */
    @UiField("baselineStartPeriod")
    ComboBox baselineStartPeriod;

    /**
     * The baseline end period.
     */
    @UiField("baselineEndPeriod")
    ComboBox baselineEndPeriod;

    /**
     * The rolling start period.
     */
    @UiField("rollingStartPeriod")
    ComboBox rollingStartPeriod;

    /**
     * The rolling end period.
     */
    @UiField("rollingEndPeriod")
    ComboBox rollingEndPeriod;

    /**
     * The priceBasisDdlb.
     */
    ComboBox priceBasisDdlb = new ComboBox(StringUtils.EMPTY);

    /**
     * The baseline methodology.
     */
    @UiField("baselineMethodology")
    OptionGroup baselineMethodology;

    public ExtFilterTable periodsForBaselineTable = new ExtFilterTable();

    /**
     * The periods for rolling avg table.
     */
    public ExtFilterTable periodsForRollingAvgTable = new ExtFilterTable();

    /**
     * The effective start period.
     */
    @UiField("effectiveStartPeriod")
    ComboBox effectiveStartPeriod;

    /**
     * The effective end period.
     */
    @UiField("effectiveEndPeriod")
    ComboBox effectiveEndPeriod;

    /**
     * The price types table.
     */
    @UiField("priceTypesTable")
    Table priceTypesTable;

    /**
     * The ndc btn.
     */
    @UiField("ndcBtn")
    Button ndcBtn;

    /**
     * The v layout.
     */
    @UiField("vLayout")
    VerticalLayout vLayout;

    /**
     * The v layout avg.
     */
    @UiField("vLayoutAvg")
    VerticalLayout vLayoutAvg;

    @UiField("forecastMethodologyLayout")
    GridLayout forecastMethodologyLayout;

    OptionGroup forecastMethodology = new OptionGroup(StringUtils.EMPTY);
    ComboBox frequencyDdlb = new ComboBox(StringUtils.EMPTY);

    TextField growthValue = new TextField();
    DataFormatConverter dollarFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_PERCENT);
    @UiField("populateBtn")
    Button populateBtn;
    /**
     * The ndc btn.
     */
    @UiField("resetBtn")
    Button resetBtn;
    Button deleteBtn = new Button(EMPTYSTRING.getConstant());
    Set<String> ndcList = new HashSet<String>();
    final List<String> listNDC9 = new ArrayList<String>();
    final Map<String, String> wacHashMap = new HashMap<String, String>();
    final Map<String, String> federalWacMap = new HashMap<String, String>();
    final Map<String, String> cpiHashMap = new HashMap<String, String>();

    final Map<Integer, String> ndc9Map = new HashMap<Integer, String>();
    final Map<String, String> itemNo = new HashMap<String, String>();
    final Map<String, String> ampHashMap = new HashMap<String, String>();

    final Map<String, String> nonFampMap = new HashMap<String, String>();
    final Map<String, String> fssMap = new HashMap<String, String>();

    final Map<Integer, String> itemMasterSidMap = new HashMap<Integer, String>();
    NewNdcDTO newNdcDto = new NewNdcDTO();
    final List<String> listItemNo = new ArrayList<String>();
    public NdcPopupForm ndcPopup;
    String growthValueUnFormated = StringUtils.EMPTY;
    public final Resource deleteImage = new ThemeResource("../../../icons/delete.png");
    /**
     * The baseline results bean.
     */
    BeanItemContainer<BaselinePeriodDTO> baselineResultsBean = new BeanItemContainer<BaselinePeriodDTO>(BaselinePeriodDTO.class);

    /**
     * The rolling avg results bean.
     */
    BeanItemContainer<BaselinePeriodDTO> rollingAvgResultsBean = new BeanItemContainer<BaselinePeriodDTO>(BaselinePeriodDTO.class);

    /**
     * The price types bean.
     */
    BeanItemContainer<PriceTypeDTO> priceTypesBean = new BeanItemContainer<PriceTypeDTO>(PriceTypeDTO.class);

    /**
     * The baseline period dto.
     */
    BaselinePeriodDTO baselinePeriodDto = new BaselinePeriodDTO();

    /**
     * The logic.
     */
    NationalAssumptionLogic logic = new NationalAssumptionLogic();
    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    public List<PriceTypeDTO> removedList = new ArrayList<PriceTypeDTO>();
    final Map<Integer, Object> medicaidMap = new HashMap<Integer, Object>();
    final Map<Integer, Object> federalMap = new HashMap<Integer, Object>();

    /**
     * Instantiates a new national assumptions.
     */
    public NationalAssumptions() {
        super();
        LOGGER.info("NationalAssumption Constructor initiated");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/NationalAssumptions.xml"), this));
        vLayout.addComponent(addPeriodsForBaseline());
        vLayoutAvg.addComponent(addPeriodsForRoleAvg());
        forecastMethodologyLayout.addComponent(forecastMethodology, 0, 0, 0, 4);
        forecastMethodologyLayout.addComponent(new Label(" "), 1, 0);
        forecastMethodologyLayout.addComponent(priceBasisDdlb, 1, 1);
        forecastMethodologyLayout.addComponent(new Label(" "), 1, 2);
        forecastMethodologyLayout.addComponent(growthValue, 1, 3);
        forecastMethodologyLayout.addComponent(frequencyDdlb, 1, 4);
        init();
        LOGGER.info("NationalAssumption Constructor ends");

    }

    /**
     * Inits the.
     */
    public void init() {
        LOGGER.info("init method started");
        configureFields();
        LOGGER.info("init method ends");
    }

    /**
     * Configurefields.
     */
    public void configureFields() {
         try {
        LOGGER.info("Entering configurefields method");
        frequencyDdlb.addStyleName("fieldPositionfreq");
        forecastMethodology.addStyleName("disablelabel");
        forecastMethodology.addItem(WAC_FLEX.getConstant());
        forecastMethodology.addItem("Price Basis");
        forecastMethodology.addItem(ROLLING_AVERAGE.getConstant());
        forecastMethodology.addItem(GROWTH.getConstant());
        forecastMethodology.addItem(FREQUENCY.getConstant());
        forecastMethodology.setItemEnabled("Price Basis", false);
        forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
        forecastMethodology.select(WAC_FLEX.getConstant());

        priceBasisDdlb.setImmediate(true);

        priceBasisDdlb.addItem(SELECT_ONE.getConstant());
        priceBasisDdlb.addItem(AVERAGE_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(BEGINNING_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(ENDING_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(MID_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(DAY_WEIGHTED_WAC.getConstant());
        priceBasisDdlb.addItem(SALES_WEIGHTED_WAC.getConstant());
        priceBasisDdlb.select(SELECT_ONE.getConstant());

        priceBasisDdlb.addStyleName("fieldPositionWac");
        priceBasisDdlb.setNullSelectionAllowed(false);

        deleteBtn.setImmediate(true);
        deleteBtn.setIcon(deleteImage);

        priceTypesTable.setImmediate(true);
        priceTypesTable.setSelectable(true);
        priceTypesTable.markAsDirty();
        priceTypesTable.setContainerDataSource(priceTypesBean);
        priceTypesTable.setVisibleColumns(CommonUiUtils.PERIOD_TYPES_COLUMNS);
        priceTypesTable.setColumnHeaders(CommonUiUtils.PERIOD_TYPES_HEADER);
        priceTypesTable.setColumnAlignment(CommonUiUtils.PERIOD_TYPES_COLUMNS[5], Table.Align.RIGHT);
        priceTypesTable.setColumnAlignment(CommonUiUtils.PERIOD_TYPES_COLUMNS[9], Table.Align.CENTER);
        priceTypesTable.setPageLength(7);

        loadFrequency();

        priceTypeDdlb.addItem(BEST_PRICE.getConstant());
        priceTypeDdlb.addItem(CPI_U.getConstant());
        priceTypeDdlb.addItem(AMP.getConstant());
        priceTypeDdlb.addItem("FSS(OGA)");
        priceTypeDdlb.addItem(NON_FAMP.getConstant());
        priceTypeDdlb.select(AMP.getConstant());
        priceTypeDdlb.addStyleName("table-header-center");

        baselineMethodology.addItem(SINGLE_PERIOD.getConstant());
        baselineMethodology.addItem(AVERAGE.getConstant());
        baselineMethodology.addItem(WEIGHTED_AVG.getConstant());
        baselineMethodology.select(SINGLE_PERIOD.getConstant());

        baselineStartPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        baselineEndPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        rollingStartPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        rollingEndPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        effectiveStartPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        effectiveEndPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());

        growthValue.setValidationVisible(true);
        growthValue.addValidator(new RegexpValidator(CommonUtils.GROWTH, CommonUtils.GROWTH_VAL_MSG));
        growthValue.setWidth("177px");
        growthValue.addStyleName(Constant.TXT_RIGHT_ALIGN);
        growthValue.setConverter(dollarFormat);
        frequencyDdlb.setNullSelectionAllowed(false);

        growthValue.setImmediate(true);

        priceTypesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    PriceTypeDTO priceTypesDTO = (PriceTypeDTO) event.getItemId();
                    setPriceTypeProjection(priceTypesDTO);
                }
            }
        });
        if (WAC_FLEX.getConstant().equals(String.valueOf(forecastMethodology.getValue()))) {
            frequencyDdlb.setEnabled(false);
            growthValue.setEnabled(false);
        }

        forecastMethodology.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                LOGGER.info("Inside forecastMethodology listener");
                priceBasisDdlb.setEnabled(true);
                String forecastMethodologyValue = (String.valueOf(forecastMethodology.getValue()));
                if (GROWTH.getConstant().equals(forecastMethodologyValue)) {
                    frequencyDdlb.setEnabled(true);
                    growthValue.setEnabled(true);
                    priceBasisDdlb.setEnabled(false);
                } else {
                    frequencyDdlb.select(ANNUALLY.getConstant());
                    growthValue.setValue(EMPTYSTRING.getConstant());
                    frequencyDdlb.setEnabled(false);
                    growthValue.setEnabled(false);

                }
                if (ROLLING_AVERAGE.getConstant().equals(forecastMethodologyValue)) {

                    try {
                        int newNdcCount = findNewNdcCount();
                        if (newNdcCount == 0) {
                            if (checkSelection(rollingStartPeriod.getValue(), rollingEndPeriod.getValue())) {
                                rollingAvgOnChange();
                            }
                            priceBasisDdlb.setEnabled(false);
                            frequencyDdlb.setEnabled(false);
                            growthValue.setEnabled(false);

                        } else {
                            AbstractNotificationUtils.getInfoNotification("Rolling Average", "Rolling Average is not available because there are new NDC's in the projection that do not have multiple periods with price type data available");
                            forecastMethodology.select(WAC_FLEX.getConstant());
                            priceBasisDdlb.setEnabled(true);
                            priceBasisDdlb.setImmediate(true);
                            frequencyDdlb.setEnabled(false);
                            frequencyDdlb.setImmediate(true);
                            growthValue.setEnabled(false);
                            growthValue.setImmediate(true);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(NationalAssumptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    rollingAvgResultsBean.removeAllItems();
                }
                if (WAC_FLEX.getConstant().equals(forecastMethodologyValue)) {
                    priceBasisDdlb.setEnabled(true);
                    frequencyDdlb.setEnabled(false);
                    growthValue.setEnabled(false);
                }
                LOGGER.info("Inside forecastMethodology listener");

            }
        });

        if (Constant.VIEW.equalsIgnoreCase(mode)) {
            disableFieldsOnView();
        }
        if (Constant.EDIT_SMALL.equalsIgnoreCase(mode)) {
            try {
                callNDCPopupProcedure();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
       
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, "National Assumptions,National Assumptions");
            if (tabItemHM.get("populateBtn") != null && tabItemHM.get("populateBtn").isFunctionFlag()) {
                populateBtn.setVisible(true);
            } else {
                populateBtn.setVisible(false);
            }
            if (tabItemHM.get("resetBtn") != null && tabItemHM.get("resetBtn").isFunctionFlag()) {
                resetBtn.setVisible(true);
            } else {
                resetBtn.setVisible(false);
            }
            if (tabItemHM.get("ndcBtn") != null && tabItemHM.get("ndcBtn").isFunctionFlag()) {
                ndcBtn.setVisible(true);
            } else {
                ndcBtn.setVisible(false);
            }
             CommonUtils.getBaselineStartPeriod(baselineStartPeriod);
             CommonUtils.getBaselineStartPeriod(baselineEndPeriod);
             CommonUtils.getRollingStartPeriod(rollingStartPeriod);
             CommonUtils.getRollingStartPeriod(rollingEndPeriod);
             CommonUtils.getEffectivePeriods(effectiveStartPeriod);
             CommonUtils.getEffectivePeriods(effectiveEndPeriod);
        } catch (PortalException portal) {
            LOGGER.error(portal.getMessage());
        } catch (SystemException system) {
            LOGGER.error(system.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("End of configurefields method");
    }

    /**
     * Adds the periods for baseline.
     *
     * @return the component
     */
    private Component addPeriodsForBaseline() {
        LOGGER.info("Entering addPeriodsForBaseline method");

        periodsForBaselineTable.markAsDirty();
        periodsForBaselineTable.setImmediate(true);
        periodsForBaselineTable.setEditable(true);
        periodsForBaselineTable.setWidth("292px");
        periodsForBaselineTable.setHeight("228px");
        periodsForBaselineTable.setPageLength(5);
        periodsForBaselineTable.removeAllItems();
        periodsForBaselineTable.setVisible(true);
        periodsForBaselineTable.setContainerDataSource(baselineResultsBean);
        periodsForBaselineTable.addStyleName(Constant.FILTER_TABLE);
        periodsForBaselineTable.addStyleName("valo-theme-extfiltertable");
        periodsForBaselineTable.setVisibleColumns(CommonUiUtils.BASELINE_PERIOD_COLUMNS);
        periodsForBaselineTable.setColumnHeaders(CommonUiUtils.BASELINE_PERIOD_HEADER);
        periodsForBaselineTable.setColumnWidth(CHECK.getConstant(), 45);
        periodsForBaselineTable.setColumnWidth(PERIOD.getConstant(), 100);
        periodsForBaselineTable.setColumnWidth(TYPE.getConstant(), 126);
        periodsForBaselineTable.setColumnCheckBox(CHECK.getConstant(), true);
        periodsForBaselineTable.addStyleName("table-header-height");
        periodsForBaselineTable.addStyleName("table-header-padding");

        periodsForBaselineTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Class<?> cls = container.getType(propertyId);

                if (cls.equals(Boolean.class)) {
                    CheckBox ch = new CheckBox();
                    ch.setImmediate(true);
                    return ch;
                }
                return null;
            }
        });
        periodsForBaselineTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (BaselinePeriodDTO obj : baselineResultsBean.getItemIds()) {
                    periodsForBaselineTable.getContainerProperty(obj, Constant.CHECK).setValue(event.isChecked());
                }

            }
        });

        LOGGER.info("End of addPeriodsForBaseline method");
        return periodsForBaselineTable;

    }

    /**
     * Adds the periods for role avg.
     *
     * @return the component
     */
    private Component addPeriodsForRoleAvg() {
        LOGGER.info("Entering of addPeriodsForRoleAvg method");
        periodsForRollingAvgTable.markAsDirty();
        periodsForRollingAvgTable.setImmediate(true);
        periodsForRollingAvgTable.setEditable(true);
        periodsForRollingAvgTable.setEnabled(true);
        periodsForRollingAvgTable.addStyleName("table-header-height");
        periodsForRollingAvgTable.addStyleName("table-header-padding");
        periodsForRollingAvgTable.addStyleName(Constant.FILTER_TABLE);
        periodsForRollingAvgTable.addStyleName("valo-theme-extfiltertable");
        periodsForRollingAvgTable.setHeight("228px");
        periodsForRollingAvgTable.setWidth("292px");
        periodsForRollingAvgTable.setPageLength(5);
        periodsForRollingAvgTable.setContainerDataSource(rollingAvgResultsBean);
        periodsForRollingAvgTable.setVisibleColumns(CommonUiUtils.BASELINE_PERIOD_COLUMNS);
        periodsForRollingAvgTable.setColumnHeaders(CommonUiUtils.BASELINE_PERIOD_HEADER);
        periodsForRollingAvgTable.setColumnWidth(CHECK.getConstant(), 45);
        periodsForBaselineTable.setColumnWidth(PERIOD.getConstant(), 95);
        periodsForBaselineTable.setColumnWidth(TYPE.getConstant(), 126);
        periodsForRollingAvgTable.setColumnCheckBox(CHECK.getConstant(), true);
        periodsForRollingAvgTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Class<?> cls = container.getType(propertyId);
                if (cls.equals(Date.class)) {
                    DateField df = new DateField();
                    df.setResolution(Resolution.YEAR);
                    return df;
                }

                if (cls.equals(Boolean.class)) {
                    CheckBox ch = new CheckBox();
                    ch.setImmediate(true);
                    return ch;
                }
                return null;
            }

        });
        periodsForRollingAvgTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            /**
             *
             */
            private static final long serialVersionUID = -1079006445009817635L;

            @SuppressWarnings("unchecked")
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (BaselinePeriodDTO obj : rollingAvgResultsBean.getItemIds()) {
                    periodsForRollingAvgTable.getContainerProperty(obj, Constant.CHECK).setValue(event.isChecked());
                }

            }
        });
        LOGGER.info("End of addPeriodsForRoleAvg method");
        return periodsForRollingAvgTable;
    }

    /**
     * Baseline start period.
     *
     * @param event the event
     */
    @UiHandler("baselineStartPeriod")
    public void baselineStartPeriod(Property.ValueChangeEvent event) {
        if (baselineStartPeriod.getValue() != null && baselineEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(baselineStartPeriod.getValue().toString(), baselineEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Baseline Period Date Range Error", "Please select a start period that occurs before the selected end period.");
            } else {
                baselineOnChange();
            }
        } else {
            baselineResultsBean.removeAllItems();
        }
    }

    /**
     * Baseline end period.
     *
     * @param event the event
     */
    @UiHandler("baselineEndPeriod")
    public void baselineEndPeriod(Property.ValueChangeEvent event) {
        if (baselineStartPeriod.getValue() != null && baselineEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(baselineStartPeriod.getValue().toString(), baselineEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Baseline Period Date Range Error", "Please select a start period that occurs before the selected end period.");
                baselineEndPeriod.select(Constant.SELECT_ONE);
            } else {
                baselineOnChange();
            }
        } else {
            baselineResultsBean.removeAllItems();
        }

    }

    /**
     * Rolling start period.
     *
     * @param event the event
     */
    @UiHandler("rollingStartPeriod")
    public void rollingStartPeriod(Property.ValueChangeEvent event) {
        String forecastMethodologyValue = (String.valueOf(forecastMethodology.getValue()));
        if (rollingStartPeriod.getValue() != null && rollingEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(rollingStartPeriod.getValue().toString(), rollingEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Forecast Period Date Range Error", "Please select a start period that occurs before the selected end period.");
                rollingStartPeriod.select(Constant.SELECTONE);
            } else if (ROLLING_AVERAGE.getConstant().equals(forecastMethodologyValue)) {
                rollingAvgOnChange();
            } else {
                rollingAvgResultsBean.removeAllItems();
            }
        } else {
            rollingAvgResultsBean.removeAllItems();
        }

    }

    /**
     * Rolling end period.
     *
     * @param event the event
     */
    @UiHandler("rollingEndPeriod")
    public void rollingEndPeriod(Property.ValueChangeEvent event) {
        String forecastMethodologyValue = (String.valueOf(forecastMethodology.getValue()));
        if (rollingStartPeriod.getValue() != null && rollingEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(rollingStartPeriod.getValue().toString(), rollingEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Forecast Period Date Range Error", "Please select a start period that occurs before the selected end period.");
                rollingEndPeriod.select(Constant.SELECTONE);
            } else if (ROLLING_AVERAGE.getConstant().equals(forecastMethodologyValue)) {
                rollingAvgOnChange();
            } else {
                rollingAvgResultsBean.removeAllItems();
            }
        } else {
            rollingAvgResultsBean.removeAllItems();
        }
    }

    /**
     * Ndc btn.
     *
     * @param event the event
     */
    @UiHandler("ndcBtn")
    public void ndcBtn(Button.ClickEvent event) {
        try {

            if (ndcPopup == null) {
                String isCreated = (String) VaadinSession.getCurrent().getAttribute("isCreated");
                if ("Y".equalsIgnoreCase(isCreated)) {
                    ndcPopup = (NdcPopupForm) VaadinSession.getCurrent().getAttribute("ndcCreated");
                    VaadinSession.getCurrent().setAttribute("isCreated", "N");
                    VaadinSession.getCurrent().setAttribute("ndcCreated", null);
                } else {
                    callNDCPopupProcedure();
                    ndcPopup = new NdcPopupForm(newNdcDto, medicaidMap, federalMap);
                }
            }
            ndcPopup.setHeight("900px");
            ndcPopup.setWidth("900px");
            UI.getCurrent().addWindow(ndcPopup);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Baseline on change.
     */
    protected void baselineOnChange() {
        LOGGER.info("Entering baselineOnChange method");
        Object start = baselineStartPeriod.getValue();
        Object end = baselineEndPeriod.getValue();
        periodsForBaselineTable.setColumnCheckBox(CHECK.getConstant(), true);

        if (start != null && end != null) {
            @SuppressWarnings("unchecked")
            List<String> items = (List<String>) baselineStartPeriod.getItemIds();
            int startIndex = items.lastIndexOf(start.toString());
            int endIndex = items.lastIndexOf(end.toString());
            List<BaselinePeriodDTO> list = new ArrayList<BaselinePeriodDTO>();
            list = logic.getBasePeriods(startIndex - 1, endIndex - 1);
            baselineResultsBean.removeAllItems();
            baselineResultsBean.addAll(list);
        } else {
            baselineResultsBean.removeAllItems();
        }
        LOGGER.info("End of baselineOnChange method");
    }

    /**
     * Changing on Rolling Average Native select in National Assumption Screen.
     */
    protected void rollingAvgOnChange() {
        LOGGER.info("Entering rollingAvgOnChange method");
        rollingAvgResultsBean.removeAllItems();
        periodsForRollingAvgTable.setColumnCheckBox(CHECK.getConstant(), true);
        Object start = rollingStartPeriod.getValue();
        Object end = rollingEndPeriod.getValue();
        if (checkSelection(start, end)) {
            @SuppressWarnings("unchecked")
            List<String> items = (List<String>) rollingStartPeriod.getItemIds();
            int startIndex = items.lastIndexOf(start.toString());
            int endIndex = items.lastIndexOf(end.toString());
            List<BaselinePeriodDTO> list = logic.getRollingPeriods(startIndex - 1, endIndex - 1);
            rollingAvgResultsBean.removeAllItems();
            rollingAvgResultsBean.addAll(list);
        }
        LOGGER.info("End of rollingAvgOnChange method");
    }

    @UiHandler("populateBtn")
    public void populateBtn(Button.ClickEvent event) {
        try {
            int selecteditems = 0;
            for (BaselinePeriodDTO bpDTO : baselineResultsBean.getItemIds()) {
                if (Constant.TRUE.equalsIgnoreCase(periodsForBaselineTable.getContainerProperty(bpDTO, Constant.CHECK).getValue().toString())) {
                    selecteditems++;
                }
            }
            boolean excuteFlag = false;
            if (priceTypeDdlb.getValue() != null
                    && effectiveStartPeriod.getValue() != null
                    && effectiveEndPeriod.getValue() != null) {

                if (!CommonUtils.isEndDateGreater(effectiveStartPeriod.getValue().toString(), effectiveEndPeriod.getValue().toString())) {
                    AbstractNotificationUtils.getErrorNotification("Effective Period Date Range Error", "Please select a Effective start period that occurs before the selected Effective end period.");
                    return;
                }

                if (baselineMethodology.getValue() != null && forecastMethodology.getValue() != null) {
                    if (SINGLE_PERIOD.getConstant().equalsIgnoreCase(baselineMethodology.getValue().toString())) {
                        if (selecteditems == 1) {
                            if (WAC_FLEX.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
                                if (priceBasisDdlb.getValue() == null || priceBasisDdlb.getValue() == SELECT_ONE.getConstant()) {
                                    AbstractNotificationUtils.getErrorNotification("No Price Basis Selected",
                                            "Please select a Price Basis.");
                                    return;
                                }
                            }
                            if (GROWTH.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
                                if (frequencyDdlb.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification("No Frequency Selected",
                                            "Please select a Frequency.");
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification("No Growth Rate",
                                            "Please enter in a Growth rate %.");
                                    return;
                                }
                                if (!growthString.matches("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{4})?%?)$")) {
                                    AbstractNotificationUtils.getErrorNotification("Invalid Growth Rate",
                                            "Please enter Growth Rate in Percent with 4 decimal places.");
                                    return;
                                }

                                if (!excuteFlag && growthValue.getValue() != null) {
                                    populateBtnOnClick();
                                    excuteFlag = true;
                                }

                            } else {
                                populateBtnOnClick();
                                excuteFlag = true;
                            }
                        } else if (selecteditems == 0) {
                            AbstractNotificationUtils.getErrorNotification("Single Period Error",
                                    "Please select a single period.");
                            return;
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Single Period Error",
                                    "You can only select a single period when the Single Period Baseline Methodology is selected.");
                            return;
                        }
                    }
                    if (!excuteFlag && AVERAGE.getConstant().equalsIgnoreCase(baselineMethodology.getValue().toString())) {
                        if (selecteditems > 1) {
                            if (WAC_FLEX.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
                                if (priceBasisDdlb.getValue() == null || priceBasisDdlb.getValue() == SELECT_ONE.getConstant()) {
                                    AbstractNotificationUtils.getErrorNotification("No Price Basis Selected",
                                            "Please select a Price Basis.");
                                    return;
                                }
                            }
                            if (GROWTH.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {

                                if (frequencyDdlb.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification("No Frequency Selected",
                                            "Please select a Frequency.");
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification("No Growth Rate",
                                            "Please enter in a Growth rate %.");
                                    return;
                                }
                                if (!growthString.matches("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{4})?%?)$")) {
                                    AbstractNotificationUtils.getErrorNotification("Invalid Growth Rate",
                                            "Please enter Growth Rate in Percent with 4 decimal places.");
                                    return;
                                }

                                if (!excuteFlag && growthValue.getValue() != null) {
                                    populateBtnOnClick();
                                    excuteFlag = true;
                                }
                            } else {
                                populateBtnOnClick();
                                excuteFlag = true;
                            }
                        } else if (selecteditems == 0) {
                            AbstractNotificationUtils.getErrorNotification("Average Error",
                                    "Please select more than 1 single period.");
                            return;
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Average Error",
                                    "You must select more than 1 single period when using the Average Baseline Methodology.");
                            return;
                        }
                    }
                    if (!excuteFlag && WEIGHTED_AVG.getConstant().equalsIgnoreCase(baselineMethodology.getValue().toString())) {
                        if (selecteditems > 1) {
                            if (WAC_FLEX.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
                                if (priceBasisDdlb.getValue() == null || priceBasisDdlb.getValue() == SELECT_ONE.getConstant()) {
                                    AbstractNotificationUtils.getErrorNotification("No Price Basis Selected",
                                            "Please select a Price Basis.");
                                    return;
                                }

                            }
                            if (GROWTH.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {

                                if (frequencyDdlb.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification("No Frequency Selected",
                                            "Please select a Frequency.");
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification("No Growth Rate",
                                            "Please enter in a Growth rate %.");
                                    return;
                                }
                                if (!growthString.matches("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{4})?%?)$")) {
                                    AbstractNotificationUtils.getErrorNotification("Invalid Growth Rate",
                                            "Please enter Growth Rate in Percent with 4 decimal places.");
                                    return;
                                }

                                if (!excuteFlag && growthValue.getValue() != null) {
                                    populateBtnOnClick();
                                    excuteFlag = true;
                                }
                            } else {
                                populateBtnOnClick();
                                excuteFlag = true;
                            }
                        } else if (selecteditems == 0) {
                            AbstractNotificationUtils.getErrorNotification("Weighted Average Error",
                                    "Please select more than 1 single period.");
                            return;
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Weighted Average Error",
                                    "You must select more than 1 single period when using the Weighted Average Baseline Methodology.");
                            return;
                        }
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Alert",
                        "Not all required fields are selected.");
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    /**
     * Contains the logic of populate button after clicking on populate button
     */
    protected void populateBtnOnClick() {

        String priceTypeNew = String.valueOf(priceTypeDdlb.getValue());
        String effectiveStartPeriodNew = String.valueOf(effectiveStartPeriod.getValue());
        String effectiveEndPeriodNew = String.valueOf(effectiveEndPeriod.getValue());
        List currentPeriod = new ArrayList();

        SessionDTO startAndTodate = CommonUtils.sessionDto;
        Date startDate = startAndTodate.getFromDate();
        Date endDate = startAndTodate.getToDate();
        int startYear = startDate.getYear() + 1900;
        int endYear = endDate.getYear() + 1900;
        int years = ((endYear - startYear) + 1);
        int lastPr = 4;
        int endMonth = endDate.getMonth() + 1;
        int endPeriod = getQuator(endMonth);

        for (int i = 0; i < years; i++) {
            int year = (startYear) + i;
            if (year == endYear) {
                lastPr = endPeriod;
            }
            for (int j = 1; j <= lastPr; j++) {
                currentPeriod.add(Constant.Q + j + " " + year);
            }
        }
        int count = 0;
        List<PriceTypeDTO> list = priceTypesBean.getItemIds();
        boolean combinationExists = false;
        for (PriceTypeDTO priceTypeDTO : list) {
            if (priceTypeNew.equals(priceTypeDTO.getPriceType())) {
                int oldStartPeriod = currentPeriod.indexOf(priceTypeDTO.getStartPeriod());
                int oldEndPeriod = currentPeriod.indexOf(priceTypeDTO.getEndPeriod());
                int newStartPeriod = currentPeriod.indexOf(effectiveStartPeriodNew);
                int newEndPeriod = currentPeriod.indexOf(effectiveEndPeriodNew);
                if (((newStartPeriod >= oldStartPeriod)
                        && ((newEndPeriod <= oldEndPeriod && !(newStartPeriod <= oldEndPeriod)))
                        || (newEndPeriod >= oldEndPeriod && !(newStartPeriod >= oldEndPeriod))
                        || ((newStartPeriod >= oldStartPeriod && newStartPeriod <= oldEndPeriod)
                        && (newEndPeriod >= oldStartPeriod && newEndPeriod <= oldEndPeriod)))
                        || ((newStartPeriod <= oldStartPeriod) && ((newEndPeriod <= oldEndPeriod && !(newEndPeriod <= oldStartPeriod)) || (newEndPeriod >= oldEndPeriod)))
                        || (newStartPeriod == oldEndPeriod) || (oldStartPeriod == newEndPeriod)) {
                    combinationExists = true;
                    break;
                }
            }
        }
        if (combinationExists) {
            AbstractNotificationUtils.getErrorNotification("Population Window", "Values already exists. Please change the conditions.");
            return;
        }

        PriceTypeDTO priceTypeDTO = new PriceTypeDTO();
        priceTypeDTO.setPriceType(ObjectUtils.toString(priceTypeDdlb.getValue()));
        priceTypeDTO.setBaselineMethodology(ObjectUtils
                .toString(baselineMethodology.getValue()));
        String actualsPeriod1 = null;
        Boolean selectedFlag = true;
        for (int i = 0; i < baselineResultsBean.size(); i++) {
            BaselinePeriodDTO baseline = baselineResultsBean.getIdByIndex(i);
            if (baseline.getCheck()) {
                count++;
                actualsPeriod1 = (actualsPeriod1 != null) ? actualsPeriod1
                        + "," + baseline.getPeriod() : baseline.getPeriod();
            }
        }
        if (!StringUtils.isNotBlank(actualsPeriod1)) {
            AbstractNotificationUtils.getErrorNotification("Warning", "Baseline is mandatory, Please select baseline.");
            return;
        }
        priceTypeDTO.setBasePeriod(actualsPeriod1);
        priceTypeDTO.setForecastMethodology(ObjectUtils
                .toString(forecastMethodology.getValue()));
        if (GROWTH.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology())) {
            String growthString = growthValue.getValue();
            growthString = StringUtils.isNotBlank(growthString) ? growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY) : DASH;
            priceTypeDTO.setGrowthRate(logic.getFormattedGrowth(growthString));
            priceTypeDTO.setFrequency(String.valueOf(frequencyDdlb.getValue()));
        }
        if (WAC_FLEX.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology())) {
            priceTypeDTO.setPriceBasis(String.valueOf(priceBasisDdlb.getValue()));
        }

        int rollAvgselected = 0;
        String actualsPeriod2 = null;
        for (int i = 0; i < rollingAvgResultsBean.size(); i++) {
            BaselinePeriodDTO baseline = rollingAvgResultsBean.getIdByIndex(i);
            if (baseline.getCheck()) {
                rollAvgselected++;
                actualsPeriod2 = (actualsPeriod2 != null) ? actualsPeriod2
                        + "," + baseline.getPeriod() : baseline.getPeriod();
            }
        }
        priceTypeDTO.setRollingPeriod(actualsPeriod2);
        priceTypeDTO.setStartPeriod(ObjectUtils.toString(effectiveStartPeriod
                .getValue()));
        priceTypeDTO.setEndPeriod(ObjectUtils.toString(effectiveEndPeriod
                .getValue()));
        createDeleteButton(priceTypeDTO);

        priceTypeDTO.setSymbol(deleteBtn);
        if (count > 1) {
            if (baselineMethodology.getValue().equals("Single Period")) {
                selectedFlag = false;
            } else {
                selectedFlag = true;
            }
        }
        if (count == 1) {
            if (baselineMethodology.getValue().equals("Average")) {
                selectedFlag = false;
            } else {
                selectedFlag = true;
            }
        }
        if (ROLLING_AVERAGE.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))
                && !StringUtils.isNotBlank(actualsPeriod2)) {
            AbstractNotificationUtils.getErrorNotification("Warning", "Rolling Average baseline is not selected");
            return;
        }
        if (ROLLING_AVERAGE.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))
                && rollAvgselected < 2) {
            AbstractNotificationUtils.getErrorNotification("Rolling Average Error",
                    "Please select at least 2 ‘Periods for Rolling Average’");
            return;
        }
        if (selectedFlag) {
            priceTypesBean.addItem(priceTypeDTO);
        } else {
            if (baselineMethodology.getValue().equals("Single Period")) {
                AbstractNotificationUtils.getErrorNotification("Warning", "You can only select a single period when the Single Period Baseline Methodology is selected.");
            }
            if (baselineMethodology.getValue().equals("Average")) {
                AbstractNotificationUtils.getErrorNotification("Warning", "You must select more than 1 single period when using the Average Baseline Methodology.");
            }
        }
    }

    public boolean checkSelection(Object startPeriod, Object endPeriod) {
        boolean flag = true;
        if (startPeriod == null || endPeriod == null || SELECT_ONE.getConstant().equals(startPeriod.toString()) || SELECT_ONE.getConstant().equals(endPeriod.toString())) {
            flag = false;
        }
        return flag;
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
       
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                resetBtnOnClick();
            }

            @Override
            public void noMethod() {
               
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(),
                "Are you sure you want to reset the page to default/previous values?");

    }

    public void resetBtnOnClick() {
        priceTypeDdlb.select(Constant.AMP);
        baselineStartPeriod.select(null);
        baselineEndPeriod.select(null);
        rollingStartPeriod.setValue(null);
        rollingEndPeriod.setValue(null);
        baselineMethodology.select("Single Period");
        forecastMethodology.select("WAC % Flex");
        frequencyDdlb.select(ANNUALLY.getConstant());
        periodsForBaselineTable.removeAllItems();
        periodsForBaselineTable.setColumnCheckBox(CHECK.getConstant(), true, false);
        periodsForRollingAvgTable.setColumnCheckBox(CHECK.getConstant(), true, false);
        periodsForRollingAvgTable.removeAllItems();
        effectiveStartPeriod.setValue(null);
        effectiveEndPeriod.setValue(null);
        frequencyDdlb.select(ANNUALLY.getConstant());
        growthValue.setValue(EMPTYSTRING.getConstant());
        priceBasisDdlb.setValue(SELECT_ONE.getConstant());
    }

    public boolean saveNationalAssumptions(boolean saveFlag) {
        try {
            if (ndcPopup != null) {
                ndcPopup.deleteNewNdc();
            }
            List<PriceTypeDTO> list = priceTypesBean.getItemIds();
            List<PriceTypeDTO> savedPriceTypes = logic.saveNationalAssumptions(list, saveFlag);
            List<PriceTypeDTO> savedPriceTypesResults = new ArrayList<PriceTypeDTO>();
            for (PriceTypeDTO priceTypeDTO : savedPriceTypes) {
                createDeleteButton(priceTypeDTO);
                savedPriceTypesResults.add(priceTypeDTO);
            }
            priceTypesBean.removeAllItems();
            priceTypesBean.addAll(savedPriceTypesResults);
            resetBtnOnClick();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

        }
        return true;
    }

    public void resetAll() {
        resetBtnOnClick();
        priceTypesBean.removeAllItems();
    }

    public void callNDCPopupProcedure() throws Exception {
        int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(
                Constant.PROJECTION_ID);
        newNdcDto.setFssFlag(false);
        newNdcDto.setFederalFlag(false);
        ndc9Map.clear();
        listNDC9.clear();
        listItemNo.clear();;
        wacHashMap.clear();
        ampHashMap.clear();
        cpiHashMap.clear();
        itemMasterSidMap.clear();
        nonFampMap.clear();
        fssMap.clear();
        newNdcDto.setIndicator(StringUtils.EMPTY);

        final List<Object[]> result = logic.NewNDCSetupCook(projectionId);
        if (result != null && result.size() > 0) {
            for (Object[] obj : result) {
                String tabName = String.valueOf(obj[9] == null ? StringUtils.EMPTY : obj[9]);

                newNdcDto.setIndicator(Constant.NDC);
                if ("MEDICAID FSS".equals(tabName)) {
                    newNdcDto.setIndicator(Constant.FSS);
                    newNdcDto.setFssFlag(true);
                    String ndcDesc = String.valueOf(obj[2] == null ? StringUtils.EMPTY : obj[2]);
                    if (StringUtils.isNotBlank(ndcDesc)) {
                        ndcDesc = ndcDesc + ", " + String.valueOf(obj[3]);
                    } else {
                        ndcDesc = String.valueOf(obj[3]);
                    }
                    ndc9Map.put(Integer.valueOf(String.valueOf(obj[0])), ndcDesc);
                    ndcList.add(ndcDesc);
                    listNDC9.add(ndcDesc);
                    wacHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[6]));
                    ampHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[4]));
                    cpiHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[5]));

                    NewNdcDTO ndcDto = new NewNdcDTO();
                    ndcDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    ndcDto.setItemNo(String.valueOf(obj[2]));
                    ndcDto.setNdc9(String.valueOf(obj[3]));
                    ndcDto.setWac(String.valueOf(obj[6]));
                    ndcDto.setBaseYearAMP(String.valueOf(obj[4]));
                    ndcDto.setBaseYearCPI(String.valueOf(obj[5]));
                    ndcDto.setNdcDescription(ndcDesc);
                    medicaidMap.put(Integer.valueOf(String.valueOf(obj[0])), ndcDto);

                } else {
                    newNdcDto.setFederalFlag(true);
                    String ndcDesc = String.valueOf(obj[2] == null ? StringUtils.EMPTY : obj[2]);
                    if (StringUtils.isNotBlank(ndcDesc)) {
                        ndcDesc = ndcDesc + ", " + String.valueOf(obj[1]);
                    } else {
                        ndcDesc = String.valueOf(obj[1]);
                    }

                    ndcList.add(ndcDesc);// for showing ndcs in notification
                    listItemNo.add(ndcDesc);
                    itemMasterSidMap.put(Integer.parseInt((String.valueOf(obj[0]))), ndcDesc);
                    nonFampMap.put(String.valueOf(obj[0]), String.valueOf(obj[7]));
                    fssMap.put(String.valueOf(obj[0]), String.valueOf(obj[8]));
                    federalWacMap.put(String.valueOf(obj[0]), String.valueOf(obj[6]));

                    NewNdcDTO ndcDto = new NewNdcDTO();
                    ndcDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    ndcDto.setItemNo(String.valueOf(obj[2]));
                    ndcDto.setWac(String.valueOf(obj[6]));
                    ndcDto.setNonFamp(String.valueOf(obj[7]));
                    ndcDto.setFssOGA(String.valueOf(obj[8]));
                    ndcDto.setNdcDescription(ndcDesc);
                    federalMap.put(Integer.parseInt(String.valueOf(obj[0])), ndcDto);
                }

            }
            newNdcDto.setNdc9Map(ndc9Map);
            newNdcDto.setListNDC9(listNDC9);
            newNdcDto.setListItemNo(listItemNo);
            newNdcDto.setWacHashMap(wacHashMap);
            newNdcDto.setAmpHashMap(ampHashMap);
            newNdcDto.setCpiHashMap(cpiHashMap);
            newNdcDto.setItemMasterSidMap(itemMasterSidMap);
            newNdcDto.setNonFampMap(nonFampMap);
            newNdcDto.setFssMap(fssMap);
            newNdcDto.setFederalWacMap(federalWacMap);

        }
    }

    public void getNDCSetup() throws Exception {
        callNDCPopupProcedure();
        String ndcNo = Arrays.toString(ndcList.toArray()).replace("[", " ").replace("]", " ");
        if (StringUtils.isNotBlank(ndcNo)) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                }

                @Override
                public void yesMethod() {
                    try {
                        if (ndcPopup == null) {
                            ndcPopup = new NdcPopupForm(newNdcDto, medicaidMap, federalMap);
                            VaadinSession.getCurrent().setAttribute("ndcCreated", ndcPopup);
                            VaadinSession.getCurrent().setAttribute("isCreated", "Y");
                        }

                        ndcPopup.setHeight("900px");
                        ndcPopup.setWidth("900px");
                        UI.getCurrent().addWindow(ndcPopup);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }.getConfirmationMessage("NDC Setup Required", "The following NDC’s " + ndcNo + " are not setup with AMP, CPI, FSS(OGA), Non-FAMP or Best Price. Do you want to manually update these NDC’s?");
        }
    }

    public void callNaProcedure() throws Exception {
        int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(
                Constant.PROJECTION_ID);
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        logic.nationalAssumptionsCook(projectionId, userId.intValue(), sessionId);
    }

    public void setPriceTypeProjection(PriceTypeDTO priceTypesDTO) {
        rollingAvgResultsBean.removeAllItems();
        priceTypeDdlb.select(priceTypesDTO.getPriceType());
        baselineMethodology.select(priceTypesDTO.getBaselineMethodology());
        forecastMethodology.select(priceTypesDTO.getForecastMethodology());
        if (GROWTH.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) {
            growthValue.setValue(priceTypesDTO.getGrowthRate());
            frequencyDdlb.select(priceTypesDTO.getFrequency());
        } else {
            growthValue.setValue(StringUtils.EMPTY);
            frequencyDdlb.select(ANNUALLY.getConstant());
        }
        if (WAC_FLEX.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) {
            priceBasisDdlb.select(priceTypesDTO.getPriceBasis());
        } else {
            priceBasisDdlb.select(SELECT_ONE.getConstant());
        }
        effectiveStartPeriod.select(priceTypesDTO.getStartPeriod());
        effectiveEndPeriod.select(priceTypesDTO.getEndPeriod());
        List<String> baseLinePeriods = Arrays.asList(priceTypesDTO.getBasePeriod().split(","));
        if (priceTypesDTO.getBaselineMethodology().equals(SINGLE_PERIOD.getConstant())) {
            baselineEndPeriod.select(SELECT_ONE.getConstant());
        } else {
            baselineEndPeriod.select(baseLinePeriods.get(baseLinePeriods.size() - 1));
        }
        baselineStartPeriod.select(baseLinePeriods.get(0));

        ListIterator baseLineIterator = baseLinePeriods.listIterator();
        List<BaselinePeriodDTO> baseLineList = new ArrayList<BaselinePeriodDTO>();
        BaselinePeriodDTO basePeriod;
        String period = StringUtils.EMPTY;
        String type = StringUtils.EMPTY;
        Calendar calendar = Calendar.getInstance();
        while (baseLineIterator.hasNext()) {
            basePeriod = new BaselinePeriodDTO();
            Object element = baseLineIterator.next();
            period = element.toString().trim();
            type = period.substring(period.length() - 4, period.length());
            if (Integer.parseInt(type) < calendar.get(Calendar.YEAR)) {
                basePeriod.setType(Constant.ACTUALS);
            } else {
                basePeriod.setType(Constant.FORECAST);
            }
            basePeriod.setPeriod(period);
            basePeriod.setCheck(true);
            baseLineList.add(basePeriod);

        }
        baselineResultsBean.removeAllItems();
        baselineResultsBean.addAll(baseLineList);
        if (ROLLING_AVERAGE.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) {
            if ((priceTypesDTO.getRollingPeriod()) != null) {

                List<String> rollingAveragePeriods = Arrays.asList(priceTypesDTO.getRollingPeriod().split(","));
                rollingStartPeriod.select(rollingAveragePeriods.get(0));
                if (rollingAveragePeriods.size() <= 1) {
                    rollingEndPeriod.select(SELECT_ONE.getConstant());
                } else {
                    rollingEndPeriod.select(rollingAveragePeriods.get(rollingAveragePeriods.size() - 1));
                }

                ListIterator rollingAverageIterator = rollingAveragePeriods.listIterator();
                BaselinePeriodDTO rollPeriod;
                List<BaselinePeriodDTO> rollingAverageList = new ArrayList<BaselinePeriodDTO>();
                while (rollingAverageIterator.hasNext()) {
                    rollPeriod = new BaselinePeriodDTO();
                    Object element = rollingAverageIterator.next();
                    period = element.toString().trim();
                    rollPeriod.setType(Constant.ACTUALS);
                    rollPeriod.setPeriod(period);
                    rollPeriod.setCheck(true);
                    rollingAverageList.add(rollPeriod);
                }
                rollingAvgResultsBean.removeAllItems();
                rollingAvgResultsBean.addAll(rollingAverageList);
            }
        }
    }

    public void disableFieldsOnView() {
        priceTypeDdlb.setEnabled(false);
        baselineStartPeriod.setEnabled(false);
        baselineEndPeriod.setEnabled(false);
        rollingStartPeriod.setEnabled(false);
        rollingEndPeriod.setEnabled(false);
        baselineMethodology.setEnabled(false);
        forecastMethodology.setEnabled(false);
        periodsForBaselineTable.setEnabled(false);
        periodsForRollingAvgTable.setEnabled(false);
        growthValue.setEnabled(false);
        frequencyDdlb.setEnabled(false);
        effectiveStartPeriod.setEnabled(false);
        effectiveEndPeriod.setEnabled(false);
        priceTypesTable.setSelectable(false);
        priceTypesTable.setEditable(false);
        priceTypesTable.setEnabled(false);
        populateBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        ndcBtn.setEnabled(false);
        priceBasisDdlb.setEnabled(false);
    }

    @UiHandler("priceTypeDdlb")
    public void priceTypeDdlb(Property.ValueChangeEvent event) {
        loadFrequency();
    }

    void loadFrequency() {
        frequencyDdlb.removeAllItems();
        frequencyDdlb.addItem(QUARTERLY.getConstant());
        frequencyDdlb.addItem(SEMI_ANNUALLY.getConstant());
        frequencyDdlb.addItem(ANNUALLY.getConstant());
        frequencyDdlb.select(ANNUALLY.getConstant());
    }

    public void saveDeletedPrice() throws Exception {
        int projectionId = (Integer) VaadinSession.getCurrent()
                .getAttribute(PROJECTION_ID.getConstant());

        for (PriceTypeDTO removed : removedList) {
            if (removed.getNaProjMasterSid() != 0) {
                logic.deletePriceTypeMain(removed);
            }
        }
        // reload the table 
        reloadTable(projectionId);
        removedList.clear();
    }

    public void reloadTable(int projectionId) {
        priceTypesBean.removeAllItems();
        List<PriceTypeDTO> savedPriceTypesResults = new ArrayList<PriceTypeDTO>();
        List<PriceTypeDTO> savedPriceTypes = logic.getSavedPriceTypes(projectionId);
        for (PriceTypeDTO priceTypeDTO : savedPriceTypes) {
            createDeleteButton(priceTypeDTO);
            savedPriceTypesResults.add(priceTypeDTO);
        }
        priceTypesBean.addAll(savedPriceTypesResults);
    }

    public void createDeleteButton(PriceTypeDTO priceTypeDTO) {

        deleteBtn = new Button();
        deleteBtn.setIcon(deleteImage);
        deleteBtn.setStyleName(Reindeer.BUTTON_LINK);
        deleteBtn.setImmediate(true);
        deleteBtn.setData(priceTypeDTO);
        priceTypeDTO.setSymbol(deleteBtn);
        deleteBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        PriceTypeDTO projDTO = (PriceTypeDTO) event.getButton().getData();
                        int projId = projDTO.getNaProjMasterSid();
                        if (projId != 0) {
                            logic.removePriceType(projDTO);
                            removedList.add(projDTO);
                        }
                        priceTypesBean.removeItem(projDTO);
                    }

                    @Override
                    public void noMethod() {
                        // TODO Auto-generated method stub
                    }
                }.getConfirmationMessage("Delete Confirmation",
                        "Are you sure you want to delete this Price Type Projection? All associated projected Price Types will be lost.");

            }
        });

    }

    public int findNewNdcCount() throws SystemException, Exception {
        int count;
        int projectionId = (Integer) VaadinSession.getCurrent()
                .getAttribute(PROJECTION_ID.getConstant());
        count = logic.getNewNdcCount(projectionId);
        return count;
    }

    public void ndcProcedure() {
        try {
            int newNdcCount = findNewNdcCount();
            if (newNdcCount != 0) {
                callNewNdcProcedure();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

        }

    }

    private void callNewNdcProcedure() {
        int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(
                Constant.PROJECTION_ID);
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        try {
            logic.newNdcCook(projectionId, userId.intValue(), sessionId);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
