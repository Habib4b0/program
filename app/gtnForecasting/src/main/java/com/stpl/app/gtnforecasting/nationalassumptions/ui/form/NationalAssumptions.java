/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.BaselinePeriodDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.PriceTypeDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils.getQuator;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.PROJECTION_ID;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.AMP;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.AVERAGE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.AVERAGE_QUARTER_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.BEGINNING_QUARTER_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.BEST_PRICE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.CHECK;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.CPI_U;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.DAY_WEIGHTED_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.EMPTYSTRING;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.ENDING_QUARTER_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.FREQUENCY;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.GROWTH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.MID_QUARTER_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NON_FAMP;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PERIOD;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PER_OF_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PRICE_TRENDING;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.ROLLING_AVERAGE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.SALES_WEIGHTED_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.SINGLE_PERIOD;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.TYPE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.WEIGHTED_AVG;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.v7.ui.themes.Reindeer;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javax.naming.NamingException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(NationalAssumptions.class);
    
    

    /**
     * The price type ddlb.
     */
    @UiField("priceTypeDdlb")
    protected ComboBox priceTypeDdlb;

    /**
     * The baseline start period.
     */
    @UiField("baselineStartPeriod")
    private ComboBox baselineStartPeriod;

    /**
     * The baseline end period.
     */
    @UiField("baselineEndPeriod")
    private ComboBox baselineEndPeriod;

    /**
     * The rolling start period.
     */
    @UiField("rollingStartPeriod")
    private ComboBox rollingStartPeriod;

    /**
     * The rolling end period.
     */
    @UiField("rollingEndPeriod")
    private ComboBox rollingEndPeriod;

    /**
     * The priceBasisDdlb.
     */
    private final ComboBox priceBasisDdlb;
    private final ComboBox priceTrendDdlb = new ComboBox();

    /**
     * The baseline methodology.
     */
    @UiField("baselineMethodology")
    private OptionGroup baselineMethodology;

    private final ExtFilterTable periodsForBaselineTable = new ExtFilterTable();

    /**
     * The periods for rolling avg table.
     */
    private final ExtFilterTable periodsForRollingAvgTable = new ExtFilterTable();

    /**
     * The effective start period.
     */
    @UiField("effectiveStartPeriod")
    private ComboBox effectiveStartPeriod;

    /**
     * The effective end period.
     */
    @UiField("effectiveEndPeriod")
    private ComboBox effectiveEndPeriod;

    /**
     * The price types table.
     */
    @UiField("priceTypesTable")
    private Table priceTypesTable;

    /**
     * The ndc btn.
     */
    @UiField("ndcBtn")
    private Button ndcBtn;

    /**
     * The v layout.
     */
    @UiField("vLayout")
    private VerticalLayout vLayout;

    /**
     * The v layout avg.
     */
    @UiField("vLayoutAvg")
    private VerticalLayout vLayoutAvg;

    @UiField("forecastMethodologyLayout")
    private GridLayout forecastMethodologyLayout;

    private final OptionGroup forecastMethodology = new OptionGroup(StringUtils.EMPTY);
    private final ComboBox frequencyDdlb = new ComboBox(StringUtils.EMPTY);

    private final TextField growthValue = new TextField();
    private final TextField wacvalue = new TextField();
    private final DecimalFormat twoDecimalFormat = new DecimalFormat("#,##0.00");
    private final DecimalFormat fourDecimalFormat = new DecimalFormat("#,##0.0000");
    public static final String PERCENT = "%";
    
    @UiField("populateBtn")
    private Button populateBtn;
    /**
     * The ndc btn.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    
    private Button deleteBtn = new Button(EMPTYSTRING.getConstant());
    private final Set<String> ndcList = new HashSet<>();
    private final List<String> listNDC9 = new ArrayList<>();
    private final Map<String, String> wacHashMap = new HashMap<>();
    private final Map<String, String> federalWacMap = new HashMap<>();
    private final Map<String, String> cpiHashMap = new HashMap<>();

    private final Map<Integer, String> ndc9Map = new HashMap<>();
    private final Map<String, String> ampHashMap = new HashMap<>();

    private final Map<String, String> nonFampMap = new HashMap<>();
    private final Map<String, String> fssMap = new HashMap<>();

    private final Map<Integer, String> itemMasterSidMap = new HashMap<>();
    private final NewNdcDTO newNdcDto = new NewNdcDTO();
    private final List<String> listItemNo = new ArrayList<>();
    private NdcPopupForm ndcPopup;
    public static final String NDC_CREATED = "ndcCreated";
    private final Resource deleteImage = new ThemeResource("img/delete.png");
    /**
     * The baseline results bean.
     */
    private final BeanItemContainer<BaselinePeriodDTO> baselineResultsBean = new BeanItemContainer<>(BaselinePeriodDTO.class);

    /**
     * The rolling avg results bean.
     */
    private final BeanItemContainer<BaselinePeriodDTO> rollingAvgResultsBean = new BeanItemContainer<>(BaselinePeriodDTO.class);

    /**
     * The price types bean.
     */
    private final BeanItemContainer<PriceTypeDTO> priceTypesBean = new BeanItemContainer<>(PriceTypeDTO.class);

    /**
     * The logic.
     */
    private final NationalAssumptionLogic logic = new NationalAssumptionLogic();
    private final String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    private final List<PriceTypeDTO> removedList = new ArrayList<>();
    private final Map<Integer, Object> medicaidMap = new HashMap<>();
    private final Map<Integer, Object> federalMap = new HashMap<>();
    
    @UiField("cpiCompounding")
    private OptionGroup cpiCompounding;
    private final Label growthLabel = new Label(" ");


    private final SessionDTO sessionDTO;
    private final BlurListener listener = new BlurListener() {

        @Override
        public void blur(BlurEvent event) {
            final TextField field = (TextField) event.getComponent();
            final DecimalFormat format = (DecimalFormat)field.getData();
            field.setValue(format.format(Double.parseDouble(field.getValue().replace(",", StringUtils.EMPTY).replace(PERCENT, StringUtils.EMPTY))) + PERCENT);
        }
    };

   
    public NationalAssumptions(SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        LOGGER.debug("NationalAssumption Constructor initiated");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/NationalAssumptions.xml"), this));
        vLayout.addComponent(addPeriodsForBaseline());
        vLayoutAvg.addComponent(addPeriodsForRoleAvg());
        if (CommonUtil.isValueEligibleForLoading()) {
            priceBasisDdlb = new ComboBox();
            forecastMethodologyLayout.addComponent(forecastMethodology, 0, 0, 0, NumericConstants.SIX);
            forecastMethodologyLayout.addComponent(new Label(" "), 1, 0, 1, 0);
            forecastMethodologyLayout.addComponent(new Label(" "), 1, 1, 1, 1);
            forecastMethodologyLayout.addComponent(priceTrendDdlb, 1, 2, 1, 2);
            forecastMethodologyLayout.addComponent(wacvalue, 1, 3, 1, 3);

            forecastMethodologyLayout.addComponent(priceBasisDdlb, 1, NumericConstants.FOUR, 1, NumericConstants.FOUR);
            forecastMethodologyLayout.addComponent(growthValue, 1, NumericConstants.FIVE, 1, NumericConstants.FIVE);
            forecastMethodologyLayout.addComponent(frequencyDdlb, 1, NumericConstants.SIX, 1, NumericConstants.SIX);
        } else {
            priceBasisDdlb = new ComboBox(StringUtils.EMPTY);
            forecastMethodologyLayout.addComponent(forecastMethodology, 0, 0, 0, NumericConstants.FOUR);
            forecastMethodologyLayout.addComponent(new Label(" "), 1, 0, 1, 0);
            forecastMethodologyLayout.addComponent(priceBasisDdlb, 1, 1, 1, 1);
            forecastMethodologyLayout.addComponent(growthLabel, 1, NumericConstants.TWO, 1, NumericConstants.TWO);
            forecastMethodologyLayout.addComponent(growthValue, 1, NumericConstants.THREE, 1, NumericConstants.THREE);
            forecastMethodologyLayout.addComponent(frequencyDdlb, 1, NumericConstants.FOUR, 1, NumericConstants.FOUR);
        }
        init();
        LOGGER.debug("NationalAssumption Constructor ends");

    }

    /**
     * Inits the.
     */
    public void init() {
        LOGGER.debug("init method started");
        configureFields();
        LOGGER.debug("init method ends");
    }

    /**
     * Configurefields.
     */
    public void configureFields() {
        try {
            LOGGER.debug("Entering configurefields method");
            growthLabel.setHeight("49px");
            frequencyDdlb.addStyleName("fieldPositionfreq");
            forecastMethodology.addStyleName("disablelabel");
            forecastMethodology.addStyleName("hideLabel");
            forecastMethodology.addItem(PRICE_TRENDING.getConstant());
            if (CommonUtil.isValueEligibleForLoading()) {
                forecastMethodology.addItem(PER_OF_WAC.getConstant());
            }
            forecastMethodology.addItem(PRICE_BASIS);
            forecastMethodology.addItem(ROLLING_AVERAGE.getConstant());
            forecastMethodology.addItem(GROWTH.getConstant());
            forecastMethodology.addItem(FREQUENCY.getConstant());
            forecastMethodology.setItemEnabled(PRICE_BASIS, false);
            forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
            forecastMethodology.select(PRICE_TRENDING.getConstant());
            if ((forecastMethodology.getValue().equals(GROWTH.getConstant())
                    || (forecastMethodology.getValue().equals(ROLLING_AVERAGE.getConstant()))) && CPI_U.getConstant().equals(priceTypeDdlb.getValue())) {
                cpiCompounding.setEnabled(true);
            } else if (forecastMethodology.getValue().equals(GROWTH.getConstant()) && !CPI_U.getConstant().equals(priceTypeDdlb.getValue()) && !Constant.ANNUAL_FSS.equals(priceTypeDdlb.getValue())) {
                cpiCompounding.setEnabled(true);
            } else {
                cpiCompounding.setEnabled(false);
            }
            priceBasisDdlb.addStyleName("fieldPositionPrice");
            priceBasisDdlb.setImmediate(true);

            priceBasisDdlb.addItem(SELECT_ONE.getConstant());
            priceBasisDdlb.addItem(AVERAGE_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(BEGINNING_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(ENDING_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(MID_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(DAY_WEIGHTED_WAC.getConstant());
            priceBasisDdlb.addItem(SALES_WEIGHTED_WAC.getConstant());
            priceBasisDdlb.select(SELECT_ONE.getConstant());

            if (CommonUtil.isValueEligibleForLoading()) {
                priceTrendDdlb.setImmediate(true);
                priceTrendDdlb.addItem(SELECT_ONE.getConstant());
                priceTrendDdlb.addItem(AVERAGE_QUARTER_WAC.getConstant());
                priceTrendDdlb.addItem(BEGINNING_QUARTER_WAC.getConstant());
                priceTrendDdlb.addItem(ENDING_QUARTER_WAC.getConstant());
                priceTrendDdlb.addItem(MID_QUARTER_WAC.getConstant());
                priceTrendDdlb.addItem(DAY_WEIGHTED_WAC.getConstant());
                priceTrendDdlb.addItem(SALES_WEIGHTED_WAC.getConstant());
                priceTrendDdlb.select(SELECT_ONE.getConstant());
            }

            priceTrendDdlb.addStyleName("fieldPositionPriceTrend");
            priceTypeDdlb.addStyleName("table-header-center");

            priceBasisDdlb.setNullSelectionAllowed(false);
            priceTrendDdlb.setNullSelectionAllowed(false);

            deleteBtn.setIcon(deleteImage);

            priceTypesTable.setImmediate(true);
            priceTypesTable.setSelectable(true);
            priceTypesTable.markAsDirty();
            priceTypesTable.setContainerDataSource(priceTypesBean);
            priceTypesTable.setVisibleColumns(CommonUiUtils.getPeriodTypeColumns());
            priceTypesTable.setColumnHeaders(CommonUiUtils.getPeriodTypesHeader());
            priceTypesTable.setColumnAlignment(CommonUiUtils.getPeriodTypeColumns()[NumericConstants.FIVE], Table.Align.LEFT);
            priceTypesTable.setColumnAlignment(CommonUiUtils.getPeriodTypeColumns()[NumericConstants.NINE], Table.Align.CENTER);
            priceTypesTable.setPageLength(NumericConstants.SEVEN);

            loadFrequency();

            priceTypeDdlb.addItem(BEST_PRICE.getConstant());
            priceTypeDdlb.addItem(CPI_U.getConstant());
            priceTypeDdlb.addItem(AMP.getConstant());
            priceTypeDdlb.addItem(Constant.ANNUAL_FSS);
            priceTypeDdlb.addItem(NON_FAMP.getConstant());
            priceTypeDdlb.select(AMP.getConstant());

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
            growthValue.setWidth("177px");
            growthValue.addStyleName(Constant.TXT_RIGHT_ALIGN);
            growthValue.addBlurListener(listener);
            growthValue.setData(fourDecimalFormat);
            wacvalue.setValidationVisible(true);
            wacvalue.setWidth("177px");
            wacvalue.addStyleName(Constant.TXT_RIGHT_ALIGN);
            wacvalue.setImmediate(true);
            wacvalue.addBlurListener(listener);
            wacvalue.setData(twoDecimalFormat);
            frequencyDdlb.setNullSelectionAllowed(false);

            growthValue.setImmediate(true);

            priceTypesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void itemClick(ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        PriceTypeDTO priceTypesDTO = (PriceTypeDTO) event.getItemId();
                        setPriceTypeProjection(priceTypesDTO);
                    }
                }
            });
            if (PRICE_TRENDING.getConstant().equals(String.valueOf(forecastMethodology.getValue()))) {
                frequencyDdlb.setEnabled(false);
                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                growthValue.setEnabled(false);
                if (CommonUtil.isValueEligibleForLoading()) {
                    priceBasisDdlb.setEnabled(false);
                    forecastMethodology.setItemEnabled(PRICE_BASIS, false);
                    wacvalue.setEnabled(false);
                }
            }

            forecastMethodology.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    LOGGER.debug("Inside forecastMethodology listener");
                    priceBasisDdlb.setEnabled(true);
                    forecastMethodology.setItemEnabled(PRICE_BASIS, true);
                    String forecastMethodologyValue = String.valueOf(forecastMethodology.getValue());
                    if (GROWTH.getConstant().equals(forecastMethodologyValue)) {
                        cpiCompounding.setEnabled(true);
                        if (priceTypeDdlb.getValue() != null) {

                            if (cpiCompounding.getValue() != null && ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(cpiCompounding.getValue()))) {
                                frequencyDdlb.setEnabled(false);
                                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                            } else {
                                frequencyDdlb.setEnabled(true);
                                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), true);
                            }
                        }
                        growthValue.setEnabled(true);
                        priceBasisDdlb.setEnabled(false);
                        forecastMethodology.setItemEnabled(PRICE_BASIS, false);
                        if (CommonUtil.isValueEligibleForLoading()) {
                            priceTrendDdlb.setEnabled(false);
                            wacvalue.setEnabled(false);
                            wacvalue.setValue(EMPTYSTRING.getConstant());
                        }
                    } else {
                        cpiCompounding.setEnabled(false);
                        frequencyDdlb.select(ANNUAL.getConstant());
                        growthValue.setValue(EMPTYSTRING.getConstant());
                        frequencyDdlb.setEnabled(false);
                        forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                        growthValue.setEnabled(false);
                        if (CommonUtil.isValueEligibleForLoading()) {
                            priceBasisDdlb.select(SELECT_ONE.getConstant());
                            wacvalue.setValue(EMPTYSTRING.getConstant());
                            priceTrendDdlb.select(SELECT_ONE.getConstant());
                        }
                    }
                    if (ROLLING_AVERAGE.getConstant().equals(forecastMethodologyValue)) {

                        try {
                            int newNdcCount = findNewNdcCount();
                            if (newNdcCount == 0) {
                                if (checkSelection(rollingStartPeriod.getValue(), rollingEndPeriod.getValue())) {
                                    rollingAvgOnChange();
                                }
                                priceBasisDdlb.setEnabled(false);
                                forecastMethodology.setItemEnabled(PRICE_BASIS, false);
                                frequencyDdlb.setEnabled(false);
                                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                                growthValue.setEnabled(false);
                                if (CommonUtil.isValueEligibleForLoading()) {
                                    priceTrendDdlb.setEnabled(false);
                                    priceBasisDdlb.select(SELECT_ONE.getConstant());
                                    wacvalue.setValue(EMPTYSTRING.getConstant());
                                    priceTrendDdlb.select(SELECT_ONE.getConstant());
                                }

                            } else {
                                AbstractNotificationUtils.getInfoNotification("Rolling Average", "Rolling Average is not available because there are new NDC's in the projection that do not have multiple periods with price type data available");
                                forecastMethodology.select(PRICE_TRENDING.getConstant());
                                priceBasisDdlb.setEnabled(true);
                                forecastMethodology.setItemEnabled(PRICE_BASIS, true);
                                priceBasisDdlb.setImmediate(true);
                                frequencyDdlb.setEnabled(false);
                                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                                frequencyDdlb.setImmediate(true);
                                growthValue.setEnabled(false);
                                growthValue.setImmediate(true);
                            }
                            if (CPI_U.getConstant().equals(priceTypeDdlb.getValue())) {
                                cpiCompounding.setEnabled(true);
                            } else {
                                cpiCompounding.setEnabled(false);
                            }
                        } catch (PortalException | SystemException | Property.ReadOnlyException ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    } else {
                        rollingAvgResultsBean.removeAllItems();
                    }

                    if (CommonUtil.isValueEligibleForLoading()) {
                        if (PRICE_TRENDING.getConstant().equals(forecastMethodologyValue)) {
                            priceTrendDdlb.setEnabled(true);
                            priceBasisDdlb.setEnabled(false);
                            forecastMethodology.setItemEnabled(PRICE_BASIS, false);
                            wacvalue.setEnabled(false);
                            frequencyDdlb.setEnabled(false);
                            forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                            growthValue.setEnabled(false);
                            priceBasisDdlb.select(SELECT_ONE.getConstant());
                            wacvalue.setValue(EMPTYSTRING.getConstant());
                        }
                        if (PER_OF_WAC.getConstant().equals(forecastMethodologyValue)) {
                            baselineMethodology.setEnabled(false);
                            periodsForBaselineTable.setEnabled(false);
                            priceTrendDdlb.setEnabled(false);
                            wacvalue.setEnabled(true);
                            priceBasisDdlb.setEnabled(true);
                            forecastMethodology.setItemEnabled(PRICE_BASIS, true);
                            periodsForRollingAvgTable.setEnabled(false);
                            frequencyDdlb.setEnabled(false);
                            forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                            growthValue.setEnabled(false);
                            priceTrendDdlb.select(SELECT_ONE.getConstant());
                            baselineStartPeriod.setEnabled(false);
                            baselineEndPeriod.setEnabled(false);
                        } else {
                            baselineMethodology.setEnabled(true);
                            periodsForBaselineTable.setEnabled(true);
                            periodsForRollingAvgTable.setEnabled(true);
                            baselineStartPeriod.setEnabled(true);
                            baselineEndPeriod.setEnabled(true);
                        }
                    } else if (PRICE_TRENDING.getConstant().equals(forecastMethodologyValue)) {
                        priceBasisDdlb.setEnabled(true);
                        forecastMethodology.setItemEnabled(PRICE_BASIS, true);
                        frequencyDdlb.setEnabled(false);
                        forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                        growthValue.setEnabled(false);
                    }

                    LOGGER.debug("Inside forecastMethodology listener");

                }
            });

            if (Constant.VIEW.equalsIgnoreCase(mode)) {
                disableFieldsOnView();
            }
            if (Constant.EDIT_SMALL.equalsIgnoreCase(mode)) {
                try {
                    callNDCPopupProcedure();
                } catch (SQLException | NamingException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + NATIONAL_ASSUMPTIONS.getConstant());
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
            CommonUtils.getEffectivePeriods(effectiveStartPeriod, priceTypeDdlb.getValue());
            CommonUtils.getEffectivePeriods(effectiveEndPeriod, priceTypeDdlb.getValue());

            cpiCompounding.addItem(QUARTERLY.getConstant());
            cpiCompounding.addItem(ANNUAL.getConstant());
            cpiCompounding.select(ANNUAL.getConstant());
            cpiCompounding.setValue(ANNUAL.getConstant());
            cpiCompounding.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    String fmValue = String.valueOf(forecastMethodology.getValue());

                    if ((GROWTH.getConstant()).equalsIgnoreCase(fmValue)) {
                        if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(cpiCompounding.getValue()))) {
                            frequencyDdlb.select(ANNUAL.getConstant());;
                            frequencyDdlb.setEnabled(false);
                            forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                        } else {
                            frequencyDdlb.setEnabled(true);
                            forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), true);
                        }
                    }
                }
            });

        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        } 
        LOGGER.debug("End of configurefields method");
    }
    public static final String PRICE_BASIS = "Price Basis";

    /**
     * Adds the periods for baseline.
     *
     * @return the component
     */
    private Component addPeriodsForBaseline() {
        LOGGER.debug("Entering addPeriodsForBaseline method");

        periodsForBaselineTable.markAsDirty();
        periodsForBaselineTable.setImmediate(true);
        periodsForBaselineTable.setEditable(true);
        periodsForBaselineTable.setWidth("292px");
        periodsForBaselineTable.setHeight("228px");
        periodsForBaselineTable.setPageLength(NumericConstants.FIVE);
        periodsForBaselineTable.removeAllItems();
        periodsForBaselineTable.setVisible(true);
        periodsForBaselineTable.setContainerDataSource(baselineResultsBean);
        periodsForBaselineTable.addStyleName(Constant.FILTER_TABLE);
        periodsForBaselineTable.addStyleName("valo-theme-extfiltertable");
        periodsForBaselineTable.addStyleName("border-zero-table");
        periodsForBaselineTable.setVisibleColumns(CommonUiUtils.getBaselinePeriodColumns());
        periodsForBaselineTable.setColumnHeaders(CommonUiUtils.getBaselinePeriodHeader());
        periodsForBaselineTable.setColumnWidth(CHECK.getConstant(), NumericConstants.FORTY_FIVE);
        periodsForBaselineTable.setColumnWidth(PERIOD.getConstant(), NumericConstants.HUNDRED);
        periodsForBaselineTable.setColumnWidth(TYPE.getConstant(), NumericConstants.ONE_TWO_SIX);
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
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (BaselinePeriodDTO obj : baselineResultsBean.getItemIds()) {
                    periodsForBaselineTable.getContainerProperty(obj, Constant.CHECK).setValue(event.isChecked());
                }

            }
        });

        LOGGER.debug("End of addPeriodsForBaseline method");
        return periodsForBaselineTable;

    }

    /**
     * Adds the periods for role avg.
     *
     * @return the component
     */
    private Component addPeriodsForRoleAvg() {
        LOGGER.debug("Entering of addPeriodsForRoleAvg method");
        periodsForRollingAvgTable.markAsDirty();
        periodsForRollingAvgTable.setImmediate(true);
        periodsForRollingAvgTable.setEditable(true);
        periodsForRollingAvgTable.setEnabled(true);
        periodsForRollingAvgTable.addStyleName("table-header-height");
        periodsForRollingAvgTable.addStyleName("table-header-padding");
        periodsForRollingAvgTable.addStyleName(Constant.FILTER_TABLE);
        periodsForRollingAvgTable.addStyleName("valo-theme-extfiltertable");
        periodsForRollingAvgTable.addStyleName("border-zero-table");
        periodsForRollingAvgTable.setHeight("228px");
        periodsForRollingAvgTable.setWidth("292px");
        periodsForRollingAvgTable.setPageLength(NumericConstants.FIVE);
        periodsForRollingAvgTable.setContainerDataSource(rollingAvgResultsBean);
        periodsForRollingAvgTable.setVisibleColumns(CommonUiUtils.getBaselinePeriodColumns());
        periodsForRollingAvgTable.setColumnHeaders(CommonUiUtils.getBaselinePeriodHeader());
        periodsForRollingAvgTable.setColumnWidth(CHECK.getConstant(), NumericConstants.FORTY_FIVE);
        periodsForRollingAvgTable.setColumnWidth(PERIOD.getConstant(), NumericConstants.NINTY_FIVE);
        periodsForRollingAvgTable.setColumnWidth(TYPE.getConstant(), NumericConstants.ONE_TWO_SIX);
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
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (BaselinePeriodDTO obj : rollingAvgResultsBean.getItemIds()) {
                    periodsForRollingAvgTable.getContainerProperty(obj, Constant.CHECK).setValue(event.isChecked());
                }

            }
        });
        LOGGER.debug("End of addPeriodsForRoleAvg method");
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
                AbstractNotificationUtils.getErrorNotification("Baseline Period Date Range Error", Constant.PLEASE_SELECT_A_START_PERIOD);
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
                AbstractNotificationUtils.getErrorNotification("Baseline Period Date Range Error", Constant.PLEASE_SELECT_A_START_PERIOD);
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
        String forecastMethodologyValue = String.valueOf(forecastMethodology.getValue());
        if (rollingStartPeriod.getValue() != null && rollingEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(rollingStartPeriod.getValue().toString(), rollingEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Forecast Period Date Range Error", Constant.PLEASE_SELECT_A_START_PERIOD);
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
        String forecastMethodologyValue = String.valueOf(forecastMethodology.getValue());
        if (rollingStartPeriod.getValue() != null && rollingEndPeriod.getValue() != null) {
            if (!CommonUtils.isEndDateGreater(rollingStartPeriod.getValue().toString(), rollingEndPeriod.getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("Forecast Period Date Range Error", Constant.PLEASE_SELECT_A_START_PERIOD);
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
                String isCreated = (String) VaadinSession.getCurrent().getAttribute(Constant.IS_CREATED);
                if ("Y".equalsIgnoreCase(isCreated)) {
                    ndcPopup = (NdcPopupForm) VaadinSession.getCurrent().getAttribute(NDC_CREATED);
                    VaadinSession.getCurrent().setAttribute(Constant.IS_CREATED, "N");
                    VaadinSession.getCurrent().setAttribute(NDC_CREATED, null);
                } else {
                    callNDCPopupProcedure();
                    ndcPopup = new NdcPopupForm(newNdcDto, medicaidMap, federalMap, sessionDTO);
                }
            }
            ndcPopup.setHeight(Constant.NINE_THOUSAND_PX);
            ndcPopup.setWidth(Constant.NINE_THOUSAND_PX);
            UI.getCurrent().addWindow(ndcPopup);
        } catch (SystemException | IllegalArgumentException | NullPointerException | SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Baseline on change.
     */
    protected void baselineOnChange() {
        LOGGER.debug("Entering baselineOnChange method");
        Object start = baselineStartPeriod.getValue();
        Object end = baselineEndPeriod.getValue();
        periodsForBaselineTable.setColumnCheckBox(CHECK.getConstant(), true);

        if (start != null && end != null) {
            @SuppressWarnings("unchecked")
            List<String> items = (List<String>) baselineStartPeriod.getItemIds();
            int startIndex = items.lastIndexOf(start.toString());
            int endIndex = items.lastIndexOf(end.toString());
            List<BaselinePeriodDTO> list;
            list = logic.getBasePeriods(startIndex - 1, endIndex - 1);
            baselineResultsBean.removeAllItems();
            baselineResultsBean.addAll(list);
        } else {
            baselineResultsBean.removeAllItems();
        }
        LOGGER.debug("End of baselineOnChange method");
    }

    /**
     * Changing on Rolling Average Native select in National Assumption Screen.
     */
    protected void rollingAvgOnChange() {
        LOGGER.debug("Entering rollingAvgOnChange method");
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
        LOGGER.debug("End of rollingAvgOnChange method");
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

                if (!priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && baselineMethodology.getValue() != null && forecastMethodology.getValue() != null) {
                    if (baselineMethodology.isEnabled() && SINGLE_PERIOD.getConstant().equalsIgnoreCase(baselineMethodology.getValue().toString())) {
                        if (selecteditems == 1) {
                            if ((PRICE_TRENDING.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue())))
                                    && ((CommonUtil.isValueEligibleForLoading() && (priceTrendDdlb.getValue() == null || priceTrendDdlb.getValue() == SELECT_ONE.getConstant())))) {
                                AbstractNotificationUtils.getErrorNotification(Constant.NO_PRICE_TREND_SELECTED, Constant.PLEASE_SELECT_A_PRICE_TREND);
                                return;
                            }
                            if (GROWTH.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
                                if (frequencyDdlb.getValue() == null && !ANNUAL.getConstant().equals(String.valueOf(cpiCompounding.getValue()))) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_FREQUENCY_SELECTED, Constant.PLEASE_SELECT_A_FREQUENCY);
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_GROWTH_RATE, Constant.PLEASE_ENTER_IN_A_GROWTH_RATE);
                                    return;
                                }
                                if (!growthString.matches(Constant.SPECIAL_STRING_REGEX)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.INVALID_GROWTH_RATE, Constant.GROWTH_RATE_IN_PERCENT);
                                    return;
                                }

                                if (!excuteFlag && growthValue.getValue() != null) {
                                    populateBtnOnClick();
                                    excuteFlag = true;
                                }

                            }
                            if (CommonUtil.isValueEligibleForLoading() && PER_OF_WAC.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {
                                if ((priceBasisDdlb.getValue() == null || priceBasisDdlb.getValue() == SELECT_ONE.getConstant())) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_PRICE_BASIS_SELECTED, Constant.PLEASE_SELECT_A_PRICE_BASIS);
                                    return;
                                }
                                String perWacString = String.valueOf(wacvalue.getValue());
                                if (StringUtils.isNotBlank(perWacString)) {
                                    perWacString = perWacString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(perWacString)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_WAC_RATE, Constant.PLEASE_ENTER_IN_A_WAC_RATE);
                                    return;
                                }
                                if (!perWacString.matches(Constant.SPECIAL_STRING_REGEX)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.INVALID_WAC_RATE, Constant.WAC_RATE_IN_PERCENT);
                                    return;
                                } else {
                                    populateBtnOnClick();
                                    excuteFlag = true;
                                }
                            } else if (!excuteFlag) {
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
                    if (CommonUtil.isValueEligibleForLoading() && PER_OF_WAC.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {
                        if ((priceBasisDdlb.getValue() == null || priceBasisDdlb.getValue() == SELECT_ONE.getConstant())) {
                            AbstractNotificationUtils.getErrorNotification(Constant.NO_PRICE_BASIS_SELECTED, "Please select a value from the Price Basis field.");
                            return;
                        }
                        String perWacString = String.valueOf(wacvalue.getValue());
                        if (StringUtils.isNotBlank(perWacString)) {
                            perWacString = perWacString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                            perWacString = perWacString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace("%", StringUtils.EMPTY);
                        }
                        if (StringUtils.isBlank(perWacString)) {
                            AbstractNotificationUtils.getErrorNotification(Constant.NO_WAC_RATE, Constant.PLEASE_ENTER_IN_A_WAC_RATE);
                            return;
                        }
                        if (!perWacString.matches(Constant.WAC_SPECIAL_STRING_REGEX)) {
                            AbstractNotificationUtils.getErrorNotification(Constant.INVALID_WAC_RATE, Constant.WAC_RATE_IN_PERCENT);
                            return;
                        } else {
                            populateBtnOnClick();
                            excuteFlag = true;
                        }
                    }
                    if (baselineMethodology.isEnabled() && !excuteFlag && AVERAGE.getConstant().equalsIgnoreCase(baselineMethodology.getValue().toString())) {
                        if (selecteditems > 1) {
                            if ((PRICE_TRENDING.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) && (priceTrendDdlb.getValue() == null || priceTrendDdlb.getValue() == SELECT_ONE.getConstant())) {
                                AbstractNotificationUtils.getErrorNotification(Constant.NO_PRICE_TREND_SELECTED, Constant.PLEASE_SELECT_A_PRICE_TREND);
                                return;
                            }
                            if (GROWTH.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {

                                if (frequencyDdlb.getValue() == null && !ANNUAL.getConstant().equals(String.valueOf(cpiCompounding.getValue()))) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_FREQUENCY_SELECTED, Constant.PLEASE_SELECT_A_FREQUENCY);
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_GROWTH_RATE, Constant.PLEASE_ENTER_IN_A_GROWTH_RATE);
                                    return;
                                }
                                if (!growthString.matches(Constant.SPECIAL_STRING_REGEX)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.INVALID_GROWTH_RATE, Constant.GROWTH_RATE_IN_PERCENT);
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
                            if ((PRICE_TRENDING.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue())))
                                    && ((priceTrendDdlb.getValue() == null || priceTrendDdlb.getValue() == SELECT_ONE.getConstant())
                                    || (CommonUtil.isValueEligibleForLoading() && (priceTrendDdlb.getValue() == null || priceTrendDdlb.getValue() == SELECT_ONE.getConstant())))) {
                                AbstractNotificationUtils.getErrorNotification(Constant.NO_PRICE_TREND_SELECTED, Constant.PLEASE_SELECT_A_PRICE_TREND);
                                return;
                            }

                            if (GROWTH.getConstant().equalsIgnoreCase(forecastMethodology.getValue().toString())) {

                                if (frequencyDdlb.getValue() == null && !ANNUAL.getConstant().equals(String.valueOf(cpiCompounding.getValue()))) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_FREQUENCY_SELECTED, Constant.PLEASE_SELECT_A_FREQUENCY);
                                    return;
                                }
                                String growthString = String.valueOf(growthValue.getValue());
                                if (StringUtils.isNotBlank(growthString)) {
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                    growthString = growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace("%", StringUtils.EMPTY);
                                }
                                if (StringUtils.isBlank(growthString)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.NO_GROWTH_RATE, Constant.PLEASE_ENTER_IN_A_GROWTH_RATE);
                                    return;
                                }
                                if (!growthString.matches(Constant.SPECIAL_STRING_REGEX)) {
                                    AbstractNotificationUtils.getErrorNotification(Constant.INVALID_GROWTH_RATE, Constant.GROWTH_RATE_IN_PERCENT);
                                    return;
                                }

                                if (!excuteFlag && growthValue.getValue() != null) {
                                    populateBtnOnClick();
                                }
                            } else {
                                populateBtnOnClick();
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
                } else if (priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS)) {
                    populateBtnOnClick();
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

        com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO startAndTodate = CommonUtils.getSessionDto();
        Date startDate = startAndTodate.getFromDate();
        Date endDate = startAndTodate.getToDate();
        int startYear = startDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        int endYear = endDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        int years = (endYear - startYear) + 1;
        int lastPr = NumericConstants.FOUR;
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
                        && (newEndPeriod <= oldEndPeriod && !(newStartPeriod <= oldEndPeriod))
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

        priceTypeDTO.setBaselineMethodology((priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) || ((PER_OF_WAC.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))))) ? ((!Constant.ANNUAL_FSS.equals(priceTypeDdlb.getValue()) && (PER_OF_WAC.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue())))) ? "" : "Mandated Calculation") : ObjectUtils
                .toString(baselineMethodology.getValue()));

        String actualsPeriod1 = null;
        boolean selectedFlag = true;
        for (int i = 0; i < baselineResultsBean.size(); i++) {
            BaselinePeriodDTO baseline = baselineResultsBean.getIdByIndex(i);
            if (baseline.getCheck()) {
                count++;
                actualsPeriod1 = (actualsPeriod1 != null) ? actualsPeriod1
                        + "," + baseline.getPeriod() : baseline.getPeriod();
            }
        }
        if (!priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && !StringUtils.isNotBlank(actualsPeriod1) && baselineMethodology.isEnabled()) {
            AbstractNotificationUtils.getErrorNotification(Constant.WARNING, "Baseline is mandatory, Please select baseline.");
            return;
        }

        priceTypeDTO.setBasePeriod(((priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS))||PER_OF_WAC.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) ? StringUtils.EMPTY : actualsPeriod1);

        priceTypeDTO.setForecastMethodology(priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) ? "Mandated Calculation" : ObjectUtils
                .toString(forecastMethodology.getValue()));

        priceTypeDTO.setCpiCompounding(String.valueOf(cpiCompounding.getValue()));
        if (GROWTH.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology()) || (PER_OF_WAC.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue())))) {
            String growthString = growthValue.getValue();
            String perWacString = wacvalue.getValue();
            growthString = StringUtils.isNotBlank(growthString) ? growthString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY) : DASH;
            perWacString = StringUtils.isNotBlank(perWacString) ? perWacString.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY) : DASH;
            priceTypeDTO.setGrowthRate(priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) ? StringUtils.EMPTY
                    : PER_OF_WAC.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue())) ? logic.getFormattedGrowth(perWacString, false) : logic.getFormattedGrowth(growthString, true));
            if (!ANNUAL.getConstant().equalsIgnoreCase(priceTypeDTO.getCpiCompounding())) {
                priceTypeDTO.setFrequency(String.valueOf(frequencyDdlb.getValue()));
            }
        }
        if (PRICE_TRENDING.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology()) || PER_OF_WAC.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology())) {

            priceTypeDTO.setPriceBasis((priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS)) ? StringUtils.EMPTY : (PER_OF_WAC.getConstant().equalsIgnoreCase(priceTypeDTO.getForecastMethodology()) ? String.valueOf(priceBasisDdlb.getValue()) : String.valueOf(CommonUtil.isValueEligibleForLoading() ? priceTrendDdlb.getValue() : priceBasisDdlb.getValue())));
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
        priceTypeDTO.setRollingPeriod(priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) ? StringUtils.EMPTY : actualsPeriod2);
        priceTypeDTO.setStartPeriod(ObjectUtils.toString(effectiveStartPeriod
                .getValue()));
        priceTypeDTO.setEndPeriod(ObjectUtils.toString(effectiveEndPeriod
                .getValue()));
        createDeleteButton(priceTypeDTO);

        priceTypeDTO.setSymbol(deleteBtn);
        if (!Constant.ANNUAL_FSS.equals(priceTypeDdlb.getValue())) {
            if (count > 1) {
                if (baselineMethodology.getValue().equals(Constant.SINGLE_PERIOD)) {
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
        }
        if (!priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && ROLLING_AVERAGE.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))
                && !StringUtils.isNotBlank(actualsPeriod2)) {
            AbstractNotificationUtils.getErrorNotification(Constant.WARNING, "Rolling Average baseline is not selected");
            return;
        }
        if (rollAvgselected < NumericConstants.TWO && !priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && ROLLING_AVERAGE.getConstant().equalsIgnoreCase(String.valueOf(forecastMethodology.getValue()))) {
            AbstractNotificationUtils.getErrorNotification("Rolling Average Error",
                    "Please select at least 2 ‘Periods for Rolling Average’");
            return;
        }
        if (selectedFlag) {
            priceTypesBean.addItem(priceTypeDTO);
        } else {
            if (!priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && baselineMethodology.getValue().equals(Constant.SINGLE_PERIOD)) {
                AbstractNotificationUtils.getErrorNotification(Constant.WARNING, "You can only select a single period when the Single Period Baseline Methodology is selected.");
            }
            if (!priceTypeDdlb.getValue().equals(Constant.ANNUAL_FSS) && baselineMethodology.getValue().equals("Average")) {
                AbstractNotificationUtils.getErrorNotification(Constant.WARNING, "You must select more than 1 single period when using the Average Baseline Methodology.");
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //Default method
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
                return;
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
        baselineMethodology.select(Constant.SINGLE_PERIOD);
        forecastMethodology.select(PRICE_TRENDING.getConstant());
        frequencyDdlb.select(ANNUAL.getConstant());
        periodsForBaselineTable.removeAllItems();
        periodsForBaselineTable.setColumnCheckBox(CHECK.getConstant(), true, false);
        periodsForRollingAvgTable.setColumnCheckBox(CHECK.getConstant(), true, false);
        periodsForRollingAvgTable.removeAllItems();
        effectiveStartPeriod.setValue(null);
        effectiveEndPeriod.setValue(null);
        frequencyDdlb.select(ANNUAL.getConstant());
        growthValue.setValue(EMPTYSTRING.getConstant());
        priceBasisDdlb.setValue(SELECT_ONE.getConstant());
    }

    public boolean saveNationalAssumptions(boolean saveFlag) {
        try {
            if (ndcPopup != null) {
                ndcPopup.deleteNewNdc();
            }
            List<PriceTypeDTO> list = priceTypesBean.getItemIds();
            List<PriceTypeDTO> savedPriceTypes = logic.saveNationalAssumptions(list, saveFlag, sessionDTO);
            List<PriceTypeDTO> savedPriceTypesResults = new ArrayList<>();
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

    public void callNDCPopupProcedure() throws NamingException, SQLException {
        int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(
                Constant.PROJECTION_ID);
        newNdcDto.setFssFlag(false);
        newNdcDto.setFederalFlag(false);
        ndc9Map.clear();
        listNDC9.clear();
        listItemNo.clear();
        wacHashMap.clear();
        ampHashMap.clear();
        cpiHashMap.clear();
        itemMasterSidMap.clear();
        nonFampMap.clear();
        fssMap.clear();
        newNdcDto.setIndicator(StringUtils.EMPTY);

        final List<Object[]> result = logic.NewNDCSetupCook(projectionId);
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                String tabName = String.valueOf(obj[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : obj[NumericConstants.SEVEN]);
                newNdcDto.setIndicator(Constant.NDC);
                if ("MEDICAID FSS".equals(tabName)) {
                    newNdcDto.setIndicator(Constant.FSS);
                    newNdcDto.setFssFlag(true);
                    String ndcDesc = String.valueOf(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO]);
                    if (StringUtils.isNotBlank(ndcDesc)) {
                        ndcDesc = ndcDesc + ", " + String.valueOf(obj[NumericConstants.THREE]);
                    } else {
                        ndcDesc = String.valueOf(obj[NumericConstants.THREE]);
                    }
                    ndc9Map.put(Integer.valueOf(String.valueOf(obj[0])), ndcDesc);
                    ndcList.add(ndcDesc);
                    listNDC9.add(ndcDesc);
                    wacHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.FOUR]));
                    ampHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.FOUR]));
                    cpiHashMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.FIVE]));

                    NewNdcDTO ndcDto = new NewNdcDTO();
                    ndcDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    ndcDto.setItemNo(String.valueOf(obj[NumericConstants.ONE]));
                    ndcDto.setNdc9(String.valueOf(obj[NumericConstants.THREE]));
                    ndcDto.setWac(String.valueOf(obj[NumericConstants.FOUR]));
                    ndcDto.setBaseYearAMP(String.valueOf(obj[NumericConstants.FOUR]));
                    ndcDto.setBaseYearCPI(String.valueOf(obj[NumericConstants.FIVE]));
                    ndcDto.setNdcDescription(ndcDesc);
                    medicaidMap.put(Integer.valueOf(String.valueOf(obj[0])), ndcDto);

                } else {
                    newNdcDto.setFederalFlag(true);
                    String ndcDesc = String.valueOf(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO]);
                    if (StringUtils.isNotBlank(ndcDesc)) {
                        ndcDesc = ndcDesc + ", " + String.valueOf(obj[1]);
                    } else {
                        ndcDesc = String.valueOf(obj[1]);
                    }
                    ndcList.add(ndcDesc);
                    listItemNo.add(ndcDesc);
                    itemMasterSidMap.put(DataTypeConverter.convertObjectToInt(obj[0]), ndcDesc);
                    nonFampMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.FIVE]));
                    fssMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.SIX]));
                    federalWacMap.put(String.valueOf(obj[0]), String.valueOf(obj[NumericConstants.FOUR]));

                    NewNdcDTO ndcDto = new NewNdcDTO();
                    ndcDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    ndcDto.setItemNo(String.valueOf(obj[NumericConstants.ONE]));
                    ndcDto.setWac(String.valueOf(obj[NumericConstants.FOUR]));
                    ndcDto.setNonFamp(String.valueOf(obj[NumericConstants.FIVE]));
                    ndcDto.setFssOGA(String.valueOf(obj[NumericConstants.SIX]));
                    ndcDto.setNdcDescription(ndcDesc);
                    federalMap.put(DataTypeConverter.convertObjectToInt(obj[0]), ndcDto);
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

    public void getNDCSetup(String projectionId) throws NamingException, SQLException {
        callNDCPopupProcedure();
        String ndcNo = Arrays.toString(ndcList.toArray()).replace('[', ' ').replace(']', ' ');
        if (StringUtils.isNotBlank(ndcNo)) {
            if (logic.isAFSSPriceTypeAvailable(projectionId)) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        LOGGER.debug("NationalAssumption: noMethod(): Inside overriden method");
                    }

                    @Override
                    public void yesMethod() {
                        loadNDCSetup();
                    }
                }.getConfirmationMessage("NDC Setup Required", "The following NDC’s " + ndcNo + " are not setup with AMP, CPI, AFSS, Non-FAMP or Best Price. Do you want to manually update these NDC’s?");
            }
        }
    }

    private void loadNDCSetup() {
        try {
            if (ndcPopup == null) {
                ndcPopup = new NdcPopupForm(newNdcDto, medicaidMap, federalMap, sessionDTO);
                VaadinSession.getCurrent().setAttribute(NDC_CREATED, ndcPopup);
                VaadinSession.getCurrent().setAttribute(Constant.IS_CREATED, "Y");
            }

            ndcPopup.setHeight(Constant.NINE_THOUSAND_PX);
            ndcPopup.setWidth(Constant.NINE_THOUSAND_PX);
            UI.getCurrent().addWindow(ndcPopup);
        } catch (SystemException | IllegalArgumentException | NullPointerException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void callNaProcedure() throws NamingException, SQLException {
        logic.nationalAssumptionsCook(sessionDTO);
    }

    public void setPriceTypeProjection(PriceTypeDTO priceTypesDTO) {
        rollingAvgResultsBean.removeAllItems();
        priceTypeDdlb.select(priceTypesDTO.getPriceType());
        baselineMethodology.select(priceTypesDTO.getBaselineMethodology());
        forecastMethodology.select(priceTypesDTO.getForecastMethodology());
        if (GROWTH.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) {
            growthValue.setValue(priceTypesDTO.getGrowthRate());
            if (!ANNUAL.getConstant().equalsIgnoreCase(priceTypesDTO.getCpiCompounding())) {
                frequencyDdlb.select(priceTypesDTO.getFrequency());
            } else {
                frequencyDdlb.select(ANNUAL.getConstant());
            }
        } else {
            growthValue.setValue(StringUtils.EMPTY);
            frequencyDdlb.select(ANNUAL.getConstant());
        }
        if (PRICE_TRENDING.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) {
            priceBasisDdlb.select(priceTypesDTO.getPriceBasis());
        } else {
            priceBasisDdlb.select(SELECT_ONE.getConstant());
        }
        cpiCompounding.select(priceTypesDTO.getCpiCompounding());

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
        List<BaselinePeriodDTO> baseLineList = new ArrayList<>();
        BaselinePeriodDTO basePeriod;
        String period;
        String type;
        Calendar calendar = Calendar.getInstance();
        while (baseLineIterator.hasNext()) {
            basePeriod = new BaselinePeriodDTO();
            Object element = baseLineIterator.next();
            period = element.toString().trim();
            type = period.substring(period.length() - NumericConstants.FOUR, period.length());
            if (Integer.parseInt(type) < calendar.get(Calendar.YEAR)) {
                basePeriod.setType(Constant.ACTUALS);
            } else {
                basePeriod.setType(Constant.FORECAST);
            }
            basePeriod.setPeriod(period);
            basePeriod.setCheck(BooleanConstant.getTrueFlag());
            baseLineList.add(basePeriod);

        }
        baselineResultsBean.removeAllItems();
        baselineResultsBean.addAll(baseLineList);
        if ((ROLLING_AVERAGE.getConstant().equalsIgnoreCase(priceTypesDTO.getForecastMethodology())) && ((priceTypesDTO.getRollingPeriod()) != null)) {

            List<String> rollingAveragePeriods = Arrays.asList(priceTypesDTO.getRollingPeriod().split(","));
            rollingStartPeriod.select(rollingAveragePeriods.get(0));
            if (rollingAveragePeriods.size() <= 1) {
                rollingEndPeriod.select(SELECT_ONE.getConstant());
            } else {
                rollingEndPeriod.select(rollingAveragePeriods.get(rollingAveragePeriods.size() - 1));
            }

            ListIterator rollingAverageIterator = rollingAveragePeriods.listIterator();
            BaselinePeriodDTO rollPeriod;
            List<BaselinePeriodDTO> rollingAverageList = new ArrayList<>();
            while (rollingAverageIterator.hasNext()) {
                rollPeriod = new BaselinePeriodDTO();
                Object element = rollingAverageIterator.next();
                period = element.toString().trim();
                rollPeriod.setType(Constant.ACTUALS);
                rollPeriod.setPeriod(period);
                rollPeriod.setCheck(BooleanConstant.getTrueFlag());
                rollingAverageList.add(rollPeriod);
            }
            rollingAvgResultsBean.removeAllItems();
            rollingAvgResultsBean.addAll(rollingAverageList);
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
        forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
        effectiveStartPeriod.setEnabled(false);
        effectiveEndPeriod.setEnabled(false);
        priceTypesTable.setSelectable(false);
        priceTypesTable.setEditable(false);
        priceTypesTable.setEnabled(false);
        populateBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        ndcBtn.setEnabled(false);
        priceBasisDdlb.setEnabled(false);
        forecastMethodology.setItemEnabled(PRICE_BASIS, false);
        priceTrendDdlb.setEnabled(false);
        reloadTable();
    }

    @UiHandler("priceTypeDdlb")
    public void priceTypeDdlb(Property.ValueChangeEvent event) {
        loadFrequency();
        baselineMethodology.setEnabled(true);
        cpiCompounding.setEnabled(true);
        baselineStartPeriod.setEnabled(true);
        baselineEndPeriod.setEnabled(true);
        rollingStartPeriod.setEnabled(true);
        rollingEndPeriod.setEnabled(true);
        periodsForBaselineTable.setEnabled(true);
        forecastMethodology.setEnabled(true);
        priceBasisDdlb.setEnabled(true);
        forecastMethodology.setItemEnabled(PRICE_BASIS, true);
        periodsForRollingAvgTable.setEnabled(true);
        frequencyDdlb.setEnabled(true);
        forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), true);
        growthValue.setEnabled(true);
        effectiveStartPeriod.removeAllItems();
        effectiveEndPeriod.removeAllItems();
        CommonUtils.getEffectivePeriods(effectiveStartPeriod, priceTypeDdlb.getValue());
        CommonUtils.getEffectivePeriods(effectiveEndPeriod, priceTypeDdlb.getValue());
        String forecastMethodologyValue = String.valueOf(forecastMethodology.getValue());
        if (priceTypeDdlb.getValue() != null && CPI_U.getConstant().equals(String.valueOf(priceTypeDdlb.getValue()))) {
            if ((PRICE_TRENDING.getConstant()).equalsIgnoreCase(forecastMethodologyValue)) {
                forecastMethodology.select(GROWTH.getConstant());
            }
            forecastMethodology.setItemEnabled(PRICE_TRENDING.getConstant(), false);
            forecastMethodology.setItemEnabled(PER_OF_WAC.getConstant(), false);
            priceBasisDdlb.setValue(SELECT_ONE.getConstant());
            priceBasisDdlb.setEnabled(false);
            forecastMethodology.setItemEnabled(PRICE_BASIS, false);
            if ((GROWTH.getConstant()).equalsIgnoreCase(forecastMethodologyValue)) {
                if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(cpiCompounding.getValue()))) {
                    frequencyDdlb.setEnabled(false);
                    forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), false);
                } else {
                    frequencyDdlb.setEnabled(true);
                    forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), true);
                }
            }
            wacvalue.setEnabled(false);
        } else if (priceTypeDdlb.getValue() != null && Constant.ANNUAL_FSS.equals(String.valueOf(priceTypeDdlb.getValue()))) {
            cpiCompounding.setEnabled(false);
            baselineStartPeriod.setEnabled(false);
            baselineEndPeriod.setEnabled(false);
            rollingStartPeriod.setEnabled(false);
            rollingEndPeriod.setEnabled(false);
            baselineMethodology.setEnabled(false);
            periodsForBaselineTable.setEnabled(false);
            forecastMethodology.setEnabled(false);
            priceBasisDdlb.setEnabled(false);
            forecastMethodology.setItemEnabled(PRICE_BASIS, false);
            priceTrendDdlb.setEnabled(false);
            periodsForRollingAvgTable.setEnabled(false);
            frequencyDdlb.setEnabled(false);
            growthValue.setEnabled(false);
            effectiveStartPeriod.removeAllItems();
            effectiveEndPeriod.removeAllItems();
            growthValue.setEnabled(false);
            wacvalue.setEnabled(false);
            CommonUtils.getEffectivePeriods(effectiveStartPeriod, priceTypeDdlb.getValue());
            CommonUtils.getEffectivePeriods(effectiveEndPeriod, priceTypeDdlb.getValue());
        } else {
            forecastMethodology.setItemEnabled(PRICE_TRENDING.getConstant(), true);
            forecastMethodology.setItemEnabled(PER_OF_WAC.getConstant(), true);
            priceTrendDdlb.setEnabled(false);
            wacvalue.setEnabled(false);
            priceBasisDdlb.setEnabled(false);
            forecastMethodology.setItemEnabled(PRICE_BASIS, false);
            if (GROWTH.getConstant().equals(forecastMethodologyValue) && !ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(cpiCompounding.getValue()))) {
                frequencyDdlb.setEnabled(true);
                forecastMethodology.setItemEnabled(FREQUENCY.getConstant(), true);
            } else {
                frequencyDdlb.setEnabled(false);
            }
            if (PRICE_TRENDING.getConstant().equals(forecastMethodologyValue)) {
                priceTrendDdlb.setEnabled(true);
                growthValue.setEnabled(false);
            } else if (PER_OF_WAC.getConstant().equals(forecastMethodologyValue)) {
                baselineMethodology.setEnabled(false);
                periodsForBaselineTable.setEnabled(false);
                priceTrendDdlb.setEnabled(false);
                wacvalue.setEnabled(true);
                priceBasisDdlb.setEnabled(true);
                forecastMethodology.setItemEnabled(PRICE_BASIS, true);
                periodsForRollingAvgTable.setEnabled(false);
                frequencyDdlb.setEnabled(false);
                growthValue.setEnabled(false);
                priceTrendDdlb.select(SELECT_ONE.getConstant());
                baselineStartPeriod.setEnabled(false);
                baselineEndPeriod.setEnabled(false);
            }
        }
        if ((forecastMethodology.getValue().equals(GROWTH.getConstant())
                || (forecastMethodology.getValue().equals(ROLLING_AVERAGE.getConstant()))) && CPI_U.getConstant().equals(priceTypeDdlb.getValue())) {
            cpiCompounding.setEnabled(true);
        } else if (forecastMethodology.getValue().equals(GROWTH.getConstant()) && !CPI_U.getConstant().equals(priceTypeDdlb.getValue()) && !Constant.ANNUAL_FSS.equals(priceTypeDdlb.getValue())) {
            cpiCompounding.setEnabled(true);
        } else if (CPI_U.getConstant().equals(priceTypeDdlb.getValue())) {
            cpiCompounding.setEnabled(true);
        } else {
            cpiCompounding.setEnabled(false);
        }
        cpiCompounding.select(ANNUAL.getConstant());
    }

    void loadFrequency() {
        frequencyDdlb.removeAllItems();
        frequencyDdlb.addItem(QUARTERLY.getConstant());
        frequencyDdlb.addItem(SEMI_ANNUAL.getConstant());
        frequencyDdlb.addItem(ANNUAL.getConstant());
        frequencyDdlb.select(ANNUAL.getConstant());
    }

    public void saveDeletedPrice() {

        for (PriceTypeDTO removed : removedList) {
            if (removed.getNaProjMasterSid() != 0) {
                logic.deletePriceTypeMain(removed);
            }
        }
        reloadTable();
        removedList.clear();
    }

    public void reloadTable() {
        priceTypesBean.removeAllItems();
        List<PriceTypeDTO> savedPriceTypesResults = new ArrayList<>();
        List<PriceTypeDTO> savedPriceTypes = logic.getSavedPriceTypes(sessionDTO);
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
                            logic.removePriceType(projDTO, sessionDTO);
                            removedList.add(projDTO);
                        }
                        priceTypesBean.removeItem(projDTO);
                    }

                    @Override
                    public void noMethod() {
                       LOGGER.debug("NationalAssumption: noMethod(): InsideNMSalesProjectionResults.java method");
                    }
                }.getConfirmationMessage("Delete Confirmation",
                        "Are you sure you want to delete this Price Type Projection? All associated projected Price Types will be lost.");

            }
        });

    }

    public int findNewNdcCount() throws SystemException, PortalException {
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
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());

        }

    }

    private void callNewNdcProcedure() {
        try {
            logic.newNdcCook(sessionDTO);
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void callCumulativeCalculation() {
        List<PriceTypeDTO> list = priceTypesBean.getItemIds();
        for (PriceTypeDTO priceTypeDTO : list) {
            if (priceTypeDTO.getBaselineMethodology().equals(SINGLE_PERIOD.getConstant()) || priceTypeDTO.getBaselineMethodology().equals(AVERAGE.getConstant())) {
                logic.cumulativeCalculation(priceTypeDTO.getBaselineMethodology(), sessionDTO);
            }
        }

    }
}