/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.arm.adjustmentrateconfiguration.AdjustmentRateUI;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateSelection;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.LookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.logic.AdjustmentRateLogic;
import com.stpl.app.arm.adjustmentrateconfiguration.ui.form.lookups.ExclusionAndInventoryRateLookUp;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.MONTHS;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Adjustment Rate Configuration shortens close cycle time for forecasting,
 * increases profitability, optimizes contracting strategies, improves
 * forecasting accuracy, enhances insights to price changes, reduces risk &
 * compliance issues while improving overall operational efficiency and
 * mitigating against large reserve adjustments.This Form Class is used for
 * ADD/EDIT mode in Adjustment Rate Configuration. The Constants and Window
 * Messages are from Property files in GALGTNProperties.'Rate'is appended in
 * each of the fields to represent unique combination of property Id.
 *
 * @author
 */
public class AdjustmentRateForm extends CustomComponent {

    SessionDTO sessionDTO;
    /**
     * // * The Table Layout For Rate Configuration. //
     */
    @UiField("resultsTableLayout")
    VerticalLayout resultsTableLayout;
    /**
     * The Adjustment Type CombBox displays the values of Pipeline Accrual and
     * Pipeline True-up based on that the transactions will be calculated
     */
    @UiField("adjustmentTypeDdlbRate")
    ComboBox adjustmentTypeDdlbRate;
    /**
     * The Company ComboBox displays only Company Master records were the
     * Company Type = GL Comp
     */
    @UiField("companyDdlbRate")
    ComboBox companyDdlbRate;
    /**
     * The Business Unit ComboBox displays only Company Master records were the
     * Company Type = Business Unit Will filter based on the Company selected.
     */
    @UiField("businessDdlbRate")
    ComboBox businessDdlbRate;
    /**
     * The Frequency ComboBox consists of values Monthly, Quarterly,
     * Semi-Annually, Annually which shows the time quantum
     */
    @UiField("frequencyDdlbRate")
    ComboBox frequencyDdlbRate;
    /**
     * The Field ComboBox is to select mass update Field
     */
    @UiField("fieldDdlbRate")
    ComboBox fieldDdlbRate;
    /**
     * The Value ComboBox is to select mass update Value
     */
    @UiField("valueDdlbRate")
    CustomComboBox valueDdlbRate;
    /**
     * valueTxt is used to Populate the exclusion Details and Inclusion
     * Calculation
     */
    @UiField("valueTxt")
    CustomTextField valueTxt;
    /**
     * Click RESET to reset the Adjustment Selection section. If the user
     * proceeds with the reset, all controls in the Ã¢â‚¬ËœAdjustment
     * SelectionÃ¢â‚¬â„¢ section will be returned to their default state.
     */
    @UiField("resetBtnRate")
    Button resetBtn;
    
    @UiField("populateBtnRate")
    Button populateBtnRate;
    /**
     * Populates the Control Table list view with data based on the values the
     * user has selected in the Ã¢â‚¬ËœAdjustment SelectionÃ¢â‚¬â„¢ section.
     */
    @UiField("generateBtnRate")
    Button generateBtn;
    /**
     * Exports the Control Table list view with based on the values the user has
     * generated in the Ã¢â‚¬ËœAdjustment SelectionÃ¢â‚¬â„¢ section.
     */
    @UiField("exportBtn")
    Button exportBtn;
    /**
     * Save the Control Table list view with based on the values the user has
     * generated in the Ã¢â‚¬ËœAdjustment SelectionÃ¢â‚¬â„¢ section.
     */
    @UiField("saveBtn")
    Button saveBtn;

    /**
     * The Results Table Table Logic in Adjustment Rate Configuration.
     */
    /**
     * The Results Table
     */
    private final FreezeTable resultsTable = new FreezeTable();

    /**
     * The Split Positions between the left and right table
     */
    private final float maxSplitPosition = 1000, minSplitPosition = 200, splitPosition = 300;
    /**
     * The Container For the Results Table.
     */
    protected BeanItemContainer<AdjustmentRateDTO> resultBeanContainer = new BeanItemContainer<>(AdjustmentRateDTO.class);
    /**
     * the ArrayList holds the Pipeline accrual month values for combination of
     * Company and business unit
     */
    private final List<AdjustmentRateDTO> accrualList = new ArrayList<>();
    /**
     * the ArrayList holds the Pipeline inventory month values for combination
     * of Company and business unit
     */
    /**
     * The Logic Class For Adjustment Rate Configuration.
     */
    private final AdjustmentRateLogic logic = AdjustmentRateLogic.getInstance();

    ExclusionAndInventoryRateLookUp lookup;
    /**
     * AdjustmentRateSelection holds the current selection of the Adjustment
     * Rate Configuration tab
     */
    AdjustmentRateSelection selection = new AdjustmentRateSelection();
    /**
     * AdjustmentRateSelection holds the generated selection of the Adjustment
     * Rate Configuration tab
     */
    AdjustmentRateSelection generatedSelection = new AdjustmentRateSelection();

    /**
     * The excel export image.
     */
    public final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentRateForm.class);
    public final Map<String, String> listNameMapper = new HashMap<>(ARMUtils.listNameMapper());
    private int lastPopulatedFrequency = 0;
    private int lastPopulatedInventCustomer = 0;
    RatesTableFeildFactory resetTableFeildFactory = new RatesTableFeildFactory();
    List<String> priceList = new ArrayList();

    /**
     * The Constructor for Adjustment Rate Configuration where the UI is
     * Declared and Initialized.
     *
     * @param sessionDTO
     */
    public AdjustmentRateForm(SessionDTO sessionDTO) {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/adjustment-rate-form.xml"), this));
        this.sessionDTO = sessionDTO;
        adjustmentTypeDdlbRate.focus();
        configurePermission();
        configureFields();
    }

    /**
     * Configuring Fields for the Adjustment Rate Configuration.
     */
    private void configureFields() {
        CommonLogic.configureDropDowns(companyDdlbRate, "getCompanyQuery", Boolean.TRUE);
        CommonLogic.configureDropDowns(businessDdlbRate, "getBusinessQuery", Boolean.TRUE);
        resultsTableLayout.addComponent(resultsTable);
        CommonLogic.setComboBoxItemIDAndCaption(adjustmentTypeDdlbRate, "LoadAdjustmentTypeForRateConfig", Collections.EMPTY_LIST);
        setImmediate(true);
        frequencyDdlbRate.addItem(ARMConstants.getMonthly());
        frequencyDdlbRate.select(ARMConstants.getMonthly());
        frequencyDdlbRate.setEnabled(false);
        fieldDdlbRate.setNullSelectionAllowed(true);
        fieldDdlbRate.setNullSelectionItemId(GlobalConstants.getSelectOne());
        fieldDdlbRate.addItem(GlobalConstants.getSelectOne());
        fieldDdlbRate.select(GlobalConstants.getSelectOne());
        valueDdlbRate.setNullSelectionAllowed(true);
        valueDdlbRate.setNullSelectionItemId(GlobalConstants.getSelectOne());
        valueDdlbRate.addItem(GlobalConstants.getSelectOne());
        valueDdlbRate.select(GlobalConstants.getSelectOne());
        valueTxt.setValue(StringUtils.EMPTY);
        exportBtn.setIcon(excelExportImage);
        exportBtn.setStyleName(Reindeer.BUTTON_LINK);
        valueTxt.setVisible(false);
        valueTxt.setStyleName(VariableConstants.SEARCH_ICON);
        initializeResultTable();
        configureResultTable();

    }

    /**
     * Initialization Of Result Table
     */
    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
    }

    /**
     * Configuration of the left table and right table
     */
    private void configureResultTable() {
        try {
            resultsTable.setContainerDataSource(resultBeanContainer);
            final ExtCustomTable leftTable = resultsTable.getLeftFreezeAsTable();
            final ExtCustomTable rightTable = resultsTable.getRightFreezeAsTable();
            leftTable.setImmediate(true);
            rightTable.setImmediate(true);
            resultsTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_LEFT_COLUMNS);
            leftTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_LEFT_HEADERS);
            leftTable.setVisibleColumns(ARMUtils.SECOND_ROW_LEFT_COLUMNS);
            leftTable.setColumnHeaders(ARMUtils.SECOND_ROW_LEFT_HEADERS);
            rightTable.setVisibleColumns(ARMUtils.SECOND_ROW_SALES_RATE_RIGHT_COLUMNS);
            rightTable.setColumnHeaders(ARMUtils.SECOND_ROW_SALES_RATE_RIGHT_HEADERS);
            leftTable.setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
            rightTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_COLUMNS);
            rightTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_HEADERS);
            reLoadTable(ARMConstants.getPipelineAccrual());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Configuration of Reload the left table and right table
     */
    public void reLoadTable(String doubleHeaderMapValue) {

        resultsTable.setDoubleHeaderVisible(true);
        final ExtCustomTable leftTable = resultsTable.getLeftFreezeAsTable();
        final ExtCustomTable rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        resultsTable.setHeight("555px");
        leftTable.setHeight("555px");
        rightTable.setHeight("555px");
        leftTable.setColumnWidth(VariableConstants.CHECK_RECORD, NumericConstants.SEVENTY_FIVE);
        leftTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                List<AdjustmentRateDTO> ids = resultBeanContainer.getItemIds();
                for (Iterator<AdjustmentRateDTO> it = ids.iterator(); it.hasNext();) {
                    AdjustmentRateDTO adjustmentRateDTO = it.next();
                    resultBeanContainer.getContainerProperty(adjustmentRateDTO, VariableConstants.CHECK_RECORD).setValue(event.isChecked());
                }
            }
        });
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        leftTable.setColumnAlignment(leftTable.getVisibleColumns()[0], ExtCustomTable.Align.CENTER);
        leftTable.setColumnAlignment(leftTable.getVisibleColumns()[1], ExtCustomTable.Align.LEFT);
        rightTable.setEditable(true);
        leftTable.setEditable(true);
        rightTable.setTableFieldFactory(resetTableFeildFactory);
        leftTable.setTableFieldFactory(resetTableFeildFactory);
        rightTable.setDoubleHeaderMap(configure_RightDoubleHeaderMap(doubleHeaderMapValue));
        leftTable.setDoubleHeaderMap(configure_LeftDoubleHeaderMap());
    }

    /**
     * The Double Header map for the Result table to show the exact parent child
     * relationship
     *
     * @return
     */
    private Map<Object, Object[]> configure_RightDoubleHeaderMap(String tabName) {
        Map<Object, Object[]> headerMap = new HashMap<>();
        if (ARMConstants.getPipelineAccrual().equals(tabName)) {
            headerMap.put(VariableConstants.SALES, ARMUtils.SECOND_ROW_SALES_COLUMNS);
            headerMap.put(VariableConstants.RATE, ARMUtils.SECOND_ROW_RATE_COLUMNS);
        } else if (ARMConstants.getPipelineInventoryTrueUp().equals(tabName)) {
            headerMap.put(VariableConstants.INVENTORY, ARMUtils.SECOND_ROW_INVENTORY_COLUMNS);
            headerMap.put(VariableConstants.RATE, ARMUtils.SECOND_ROW_RATE_COLUMNS);
        } else if (ARMConstants.getTransaction6().equals(tabName)) {
            headerMap.put(VariableConstants.INVENTORY, ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX6);
        } else if (ARMConstants.getTransaction7().equals(tabName)) {
            headerMap.put(VariableConstants.SALES, ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX7);
        }
        return headerMap;
    }

    private Map<Object, Object[]> configure_EXCELDoubleHeaderMap(String tabName) {
        Map<Object, Object[]> headerMap = new HashMap<>();
        if (ARMConstants.getPipelineAccrual().equals(tabName)) {
            headerMap.put(VariableConstants.SALES, ARMUtils.EXCEL_ROW_SALES_COLUMNS);
            headerMap.put(VariableConstants.RATE, ARMUtils.SECOND_ROW_RATE_COLUMNS);
        } else if (ARMConstants.getPipelineInventoryTrueUp().equals(tabName)) {
            headerMap.put(VariableConstants.INVENTORY, ARMUtils.EXCEL_ROW_INVENTORY_COLUMNS);
            headerMap.put(VariableConstants.RATE, ARMUtils.SECOND_ROW_RATE_COLUMNS);
        } else if (ARMConstants.getTransaction6().equals(tabName)) {
            headerMap.put(VariableConstants.INVENTORY, ARMUtils.EXCEL_ROW_Trx6_SINGLE_COLUMNS);
        } else if (ARMConstants.getTransaction7().equals(tabName)) {
            headerMap.put(VariableConstants.SALES, ARMUtils.EXCEL_ROW_Trx7_SINGLE_COLUMNS);
        }
        return headerMap;
    }

    /**
     * The Double Header map for the Result table to show the exact parent child
     * relationship
     *
     * @return
     */
    private Map<Object, Object[]> configure_LeftDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(VariableConstants.MONTH, new String[]{VariableConstants.CHECK_RECORD, VariableConstants.MONTH});
        return headerMap;
    }

    @UiHandler("generateBtnRate")
    public void generateBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Inside Generate Btn");
        try {
            if (selection.getAdjustmentType().isEmpty() || selection.getBu_companyMasterSid() == 0 || selection.getGl_companyMasterSid() == 0) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getGenerateMessageID001());
            } else {
                resultsTable.getLeftFreezeAsTable().setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
                resultBeanContainer.removeAllItems();
                resultsTable.constructRightFreeze(true);
                final ExtCustomTable rightTable = resultsTable.getRightFreezeAsTable();
                rightTable.setContainerDataSource(resultBeanContainer);
                if (ARMConstants.getPipelineAccrual().equals(selection.getAdjustmentType())) {
                    rightTable.setVisibleColumns(ARMUtils.SECOND_ROW_SALES_RATE_RIGHT_COLUMNS);
                    rightTable.setColumnHeaders(ARMUtils.SECOND_ROW_SALES_RATE_RIGHT_HEADERS);
                    rightTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_COLUMNS);
                    rightTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_HEADERS);
                } else if (ARMConstants.getPipelineInventoryTrueUp().equals(selection.getAdjustmentType())) {
                    rightTable.setVisibleColumns(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS);
                    rightTable.setColumnHeaders(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS);
                    rightTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS);
                    rightTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS);
                } else if (ARMConstants.getTransaction6().equals(selection.getAdjustmentType())) {
                    rightTable.setVisibleColumns(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX6);
                    rightTable.setColumnHeaders(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX6);
                    rightTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX6);
                    rightTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX6);
                } else if (ARMConstants.getTransaction7().equals(selection.getAdjustmentType())) {
                    rightTable.setVisibleColumns(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX7);
                    rightTable.setColumnHeaders(ARMUtils.SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX7);
                    rightTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX7);
                    rightTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX7);
                }
                reLoadTable(selection.getAdjustmentType());
                accrualList.clear();
                List tempList = logic.selectRateConfig(selection);
                if (tempList.isEmpty()) {
                    logic.deleteRateConfig(selection);
                    createMonths();
                    logic.saveRateConfig(selection, accrualList);
                    tempList = logic.selectRateConfig(selection);
                    accrualList.clear();
                }

                accrualList.addAll(tempList);
                tempList.clear();
                tempList = null;

                resultBeanContainer.addAll(accrualList);
                rightTable.setDoubleHeaderMap(configure_RightDoubleHeaderMap(selection.getAdjustmentType()));
                configureMassUpdateSection();
                rightTable.setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
                generatedSelection.setInternal(selection);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureMassUpdateSection() {
        fieldDdlbRate.removeAllItems();
        fieldDdlbRate.addItem(GlobalConstants.getSelectOne());
        valueDdlbRate.removeAllItems();
        valueDdlbRate.addItem(GlobalConstants.getSelectOne());
        valueDdlbRate.select(GlobalConstants.getSelectOne());
        valueTxt.setValue(StringUtils.EMPTY);
        if (ARMConstants.getPipelineAccrual().equals(selection.getAdjustmentType())) {
            fieldDdlbRate.addItem(VariableConstants.DATE_TYPE);
            fieldDdlbRate.setItemCaption(VariableConstants.DATE_TYPE, ARMConstants.getDateType());
            fieldDdlbRate.addItem(VariableConstants.PRICE);
            fieldDdlbRate.setItemCaption(VariableConstants.PRICE, ARMConstants.getPrice());
            fieldDdlbRate.addItem(VariableConstants.EXCLUSION_DETAILS);
            fieldDdlbRate.setItemCaption(VariableConstants.EXCLUSION_DETAILS, ARMConstants.getExclusionDetails());
            fieldDdlbRate.addItem(VariableConstants.RATE_BASIS);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_BASIS, ARMConstants.getRateBasis());
            fieldDdlbRate.addItem(VariableConstants.RATE_FREQUENCY);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_FREQUENCY, ARMConstants.getRateFrequency());
            fieldDdlbRate.addItem(VariableConstants.RATE_PERIOD);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_PERIOD, ARMConstants.getRatePeriod());
        } else if (ARMConstants.getPipelineInventoryTrueUp().equals(selection.getAdjustmentType())) {
            fieldDdlbRate.addItem(VariableConstants.INVENTORY_DETAILS);
            fieldDdlbRate.setItemCaption(VariableConstants.INVENTORY_DETAILS, ARMConstants.getInventoryDetailsConstant());
            fieldDdlbRate.addItem(VariableConstants.INVENTORY_CUSTOMER);
            fieldDdlbRate.setItemCaption(VariableConstants.INVENTORY_CUSTOMER, ARMConstants.getInventoryCustomer());
            fieldDdlbRate.addItem(VariableConstants.INVENTORYCAL_CULATION);
            fieldDdlbRate.setItemCaption(VariableConstants.INVENTORYCAL_CULATION, ARMConstants.getInventoryCalculation());
            fieldDdlbRate.addItem(VariableConstants.PRICE);
            fieldDdlbRate.setItemCaption(VariableConstants.PRICE, ARMConstants.getPrice());
            fieldDdlbRate.addItem(VariableConstants.RESERVE_DATE);
            fieldDdlbRate.setItemCaption(VariableConstants.RESERVE_DATE, ARMConstants.getReserveDate());
            fieldDdlbRate.addItem(VariableConstants.RATE_BASIS);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_BASIS, ARMConstants.getRateBasis());
            fieldDdlbRate.addItem(VariableConstants.RATE_FREQUENCY);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_FREQUENCY, ARMConstants.getRateFrequency());
            fieldDdlbRate.addItem(VariableConstants.RATE_PERIOD);
            fieldDdlbRate.setItemCaption(VariableConstants.RATE_PERIOD, ARMConstants.getRatePeriod());
        } else if (ARMConstants.getTransaction6().equals(selection.getAdjustmentType())) {
            fieldDdlbRate.addItem(VariableConstants.INVENTORY_DETAILS);
            fieldDdlbRate.setItemCaption(VariableConstants.INVENTORY_DETAILS, ARMConstants.getInventoryDetailsConstant());
            fieldDdlbRate.addItem(VariableConstants.ADJUSTED_PRICE);
            fieldDdlbRate.setItemCaption(VariableConstants.ADJUSTED_PRICE, ARMConstants.getAdjusted_price());
            fieldDdlbRate.addItem(VariableConstants.BASELINE_PRICE);
            fieldDdlbRate.setItemCaption(VariableConstants.BASELINE_PRICE, ARMConstants.getBase_line_price());
        } else if (ARMConstants.getTransaction7().equals(selection.getAdjustmentType())) {
            fieldDdlbRate.addItem(VariableConstants.DATE_TYPE);
            fieldDdlbRate.setItemCaption(VariableConstants.DATE_TYPE, ARMConstants.getDateType());
            fieldDdlbRate.addItem(VariableConstants.PRICE);
            fieldDdlbRate.setItemCaption(VariableConstants.PRICE, ARMConstants.getPrice());
            fieldDdlbRate.addItem(VariableConstants.EXCLUSION_DETAILS);
            fieldDdlbRate.setItemCaption(VariableConstants.EXCLUSION_DETAILS, ARMConstants.getExclusionDetails());
        }
        fieldDdlbRate.select(GlobalConstants.getSelectOne());
    }

    @UiHandler("adjustmentTypeDdlbRate")
    public void adjustmentTypeVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val != null) {
            HelperDTO dto = (HelperDTO) val;
            selection.setAdjustmentId(dto.getId());
            selection.setAdjustmentType(dto.getDescription());
        } else {
            selection.setAdjustmentType(StringUtils.EMPTY);
        }

    }

    @UiHandler("companyDdlbRate")
    public void companyDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val == null) {
            selection.setGl_companyMasterSid(0);
        } else {
            selection.setGl_companyMasterSid((int) val);
        }

    }

    @UiHandler("businessDdlbRate")
    public void businessDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val == null) {
            selection.setBu_companyMasterSid(0);
        } else {
            selection.setBu_companyMasterSid((int) val);
        }

    }

    @UiHandler("fieldDdlbRate")
    public void massFieldVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val != null) {
            if (VariableConstants.EXCLUSION_DETAILS.equals(val)) {
                valueTxt.setVisible(true);
                valueTxt.setReadOnly(false);
                valueDdlbRate.setVisible(false);
                valueTxt.addClickListener(massupdateListener);
            } else if (VariableConstants.INVENTORYCAL_CULATION.equals(val)) {
                valueTxt.setVisible(true);
                valueTxt.setReadOnly(false);
                valueTxt.setEnabled(true);
                valueTxt.setDescription("InventCal");
                valueTxt.setValue(StringUtils.EMPTY);
                valueDdlbRate.setVisible(false);
                lastPopulatedInventCustomer = lastPopulatedInventCustomer == 0 ? findCustomerValue() : lastPopulatedInventCustomer;
                if (lastPopulatedInventCustomer == 0) {
                    AbstractNotificationUtils.getErrorNotification("Inventory Customer Error", "The selected Inventory Customer values do not match for all the selected records.  The Inventory Calculation options are dependent on the Inventory Customer values.");
                    valueTxt.setEnabled(false);
                    return;
                }
                valueTxt.addClickListener(massupdateListener);
            } else if (VariableConstants.RATE_PERIOD.equals(val)) {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                lastPopulatedFrequency = lastPopulatedFrequency == 0 ? findFrequency() : lastPopulatedFrequency;
                if (lastPopulatedFrequency == 0) {
                    AbstractNotificationUtils.getErrorNotification("Rate Frequency Error", "The selected Rate Frequencies do not match for all the selected records.  The Rate Period options are dependent on the Rate Frequencies.");
                    return;
                }
                CommonUtils.loadRatePeriodComboBox(valueDdlbRate, findFrequency(lastPopulatedFrequency), listNameMapper.get(val.toString()));
            } else if (VariableConstants.RESERVE_DATE.equals(val)) {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                CommonUtils.loadRatePeriodComboBox(valueDdlbRate, NumericConstants.TWO, listNameMapper.get(val.toString()));
            } else if (VariableConstants.PRICE.equals(val)) {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                CommonUtils.loadPriceComboBox(valueDdlbRate, priceList);
            } else if (VariableConstants.ADJUSTED_PRICE.equals(val)) {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                CommonUtils.loadPriceComboBox(valueDdlbRate, priceList);
            } else if (VariableConstants.BASELINE_PRICE.equals(val)) {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                CommonUtils.loadPriceComboBox(valueDdlbRate, priceList);
            } else {
                valueTxt.setVisible(false);
                valueDdlbRate.setVisible(true);
                CommonUtils.loadComboBoxWithInteger(valueDdlbRate, listNameMapper.get(val.toString()), false);
                if (VariableConstants.RATE_FREQUENCY.equals((String) fieldDdlbRate.getValue())) {
                    lastPopulatedFrequency = valueDdlbRate.getValue() == null ? 0 : (int) valueDdlbRate.getValue();
                }
                if (VariableConstants.INVENTORY_CUSTOMER.equals((String) fieldDdlbRate.getValue())) {
                    lastPopulatedInventCustomer = valueDdlbRate.getValue() == null ? 0 : (int) valueDdlbRate.getValue();
                }
            }
        } else {
            valueTxt.setVisible(false);
            valueDdlbRate.setVisible(true);
            valueDdlbRate.removeAllItems();
            valueDdlbRate.addItem(GlobalConstants.getSelectOne());
            valueDdlbRate.select(GlobalConstants.getSelectOne());
        }

    }

//    @UiHandler("populateBtnRate")
//    public void populateButtonClick(Button.ClickEvent event) {
     public void populateBtnRate() {
        populateBtnRate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside populateButtonClick Btn :" + fieldDdlbRate.getValue());
        LOGGER.debug("Inside populateButtonClick Btn :" + fieldDdlbRate.getItemCaption(fieldDdlbRate.getValue()));
        try {

            Object val;
            boolean mandatory = false;
            if (VariableConstants.EXCLUSION_DETAILS.equals(fieldDdlbRate.getValue()) || VariableConstants.INVENTORYCAL_CULATION.equals(fieldDdlbRate.getValue())) {
                val = valueTxt.getValue();
                mandatory = val.equals(StringUtils.EMPTY);
            } else if (VariableConstants.PRICE.equals(fieldDdlbRate.getValue()) || VariableConstants.ADJUSTED_PRICE.equals(fieldDdlbRate.getValue()) || VariableConstants.BASELINE_PRICE.equals(fieldDdlbRate.getValue())) {
                val = valueDdlbRate.getValue();
                mandatory = val == null || "-Select One-".equals(val.toString());
            } else {
                val = valueDdlbRate.getValue();
                mandatory = val == null || ((int) val == 0);
            }
            if (mandatory) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getPropertyMessage003());
            } else {

                List<AdjustmentRateDTO> list = resultBeanContainer.getItemIds();
                boolean checkFlag = true;
                for (AdjustmentRateDTO adjustmentRateDTO : list) {
                    if (adjustmentRateDTO.getCheckRecord()) {
                        checkFlag = false;
                    }
                }
                if (checkFlag) {
                    AbstractNotificationUtils.getErrorNotification("Populate Error", "Please select at least one item to populate. Please try again.");
                    return;
                }

                for (AdjustmentRateDTO adjustmentRateDTO : list) {
                    boolean checkAll = resultsTable.getLeftFreezeAsTable().getColumnCheckBox(VariableConstants.CHECK_RECORD);
                    if (checkAll || adjustmentRateDTO.getCheckRecord()) {

                        if (VariableConstants.RATE_FREQUENCY.equals((String) fieldDdlbRate.getValue())) {
                            if ((int) val > 0) {
                                CommonUtils.loadRatePeriodComboBox((CustomComboBox) adjustmentRateDTO.getComponent(VariableConstants.RATE_PERIOD), findFrequency(val == null ? 0 : (int) val), listNameMapper.get(VariableConstants.RATE_PERIOD));
                            }
                        } else if (VariableConstants.EXCLUSION_DETAILS.equals((String) fieldDdlbRate.getValue()) || VariableConstants.INVENTORYCAL_CULATION.equals((String) fieldDdlbRate.getValue())) {
                            LookUpDTO viewSidDTO = (LookUpDTO) valueTxt.getData();
                            adjustmentRateDTO.setViewMasterSid(viewSidDTO.getViewMasterSid());
                        } else if (VariableConstants.INVENTORY_CUSTOMER.equals((String) fieldDdlbRate.getValue())) {
                            resultBeanContainer.getContainerProperty(adjustmentRateDTO, "inventoryCalculation").setValue(StringUtils.EMPTY);
                        } else if (String.valueOf(fieldDdlbRate.getValue()).equalsIgnoreCase(ARMConstants.getPrice())) {
                            val = String.valueOf(valueDdlbRate.getItemCaption(valueDdlbRate.getValue()));
                        }
                        resultBeanContainer.getContainerProperty(adjustmentRateDTO, fieldDdlbRate.getValue()).setValue(val);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error While Mass populate for the feild :" + fieldDdlbRate.getItemCaption(fieldDdlbRate.getValue()) + " With Exception :" + e);
        }
    }
        });
     }

    public boolean checkValidFrequency() {
        LOGGER.debug("Inside Check Valid Frequency");
        boolean frequencyCheckFlag = true;

        List<AdjustmentRateDTO> list = resultBeanContainer.getItemIds();
        for (AdjustmentRateDTO adjustmentRateDTO : list) {

            if (lastPopulatedFrequency != adjustmentRateDTO.getRateFrequency()) {
                frequencyCheckFlag = false;
                break;
            }
        }
        return frequencyCheckFlag;
    }

    @UiHandler("resetBtnRate")
    public void resetButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside populateButtonClick Btn");
        try {
            notifier.setButtonName("reset");
            notifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessageID002());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("saveBtn")
    public void saveButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside saveBtn : selection" + selection.toString());
        try {
            /**
             * Seems Selected Adjustment selection is different from Generated
             * Adjustment Selection; selection != generatedSelection. If is true
             * don't allow to save
             */
            if (!selection.equals(generatedSelection)) {
                return;
            }
            notifier.setButtonName("save");
            notifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getSaveMessageID004());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("exportBtn")
    public void exportButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside ExportBtn :");
        try {
            final ExtCustomTable excelTable = new ExtCustomTable();
            resultsTableLayout.addComponent(excelTable);
            final BeanItemContainer<AdjustmentRateDTO> excelContainer = new BeanItemContainer<AdjustmentRateDTO>(AdjustmentRateDTO.class);
            configureAndLoadDataForExcel(excelTable, excelContainer);
            if (excelTable.size() > 0) {
                AdjustmentRateUI.EXCEL_CLOSE = true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), "Adjustment Rate Configuration", "Adjustment Rate Configuration", "Adjustment Rate Configuration.xls", false);
                exp.export();
            }
            resultsTableLayout.removeComponent(excelTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Method used to load the data for excel export.
     *
     * @param excelTable
     * @param excelContainer
     */
    public void configureAndLoadDataForExcel(final ExtCustomTable excelTable, final BeanItemContainer<AdjustmentRateDTO> excelContainer) {
        try {
            excelTable.setImmediate(true);
            excelTable.setVisible(false);

            excelTable.setContainerDataSource(excelContainer);
            if (ARMConstants.getPipelineAccrual().equals(selection.getAdjustmentType())) {
                excelTable.setVisibleColumns(ARMUtils.EXCEL_ROW_SALES_RATE_SINGLE_COLUMNS);
                excelTable.setColumnHeaders(ARMUtils.EXCEL_ROW_SALES_RATE_SINGLE_HEADERS);
                excelTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_COLUMNS);
                excelTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_SALES_RATE_RIGHT_HEADERS);
            } else if (ARMConstants.getPipelineInventoryTrueUp().equals(selection.getAdjustmentType())) {
                excelTable.setVisibleColumns(ARMUtils.EXCEL_ROW_INVENTORY_RATE_SINGLE_COLUMNS);
                excelTable.setColumnHeaders(ARMUtils.EXCEL_ROW_INVENTORY_RATE_SINGLE_HEADERS);
                excelTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS);
                excelTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS);
            } else if (ARMConstants.getTransaction6().equals(selection.getAdjustmentType())) {
                excelTable.setVisibleColumns(ARMUtils.EXCEL_ROW_Trx6_SINGLE_COLUMNS);
                excelTable.setColumnHeaders(ARMUtils.EXCEL_ROW_Trx6_RATE_SINGLE_HEADERS);
                excelTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_Trx6_RATE_RIGHT_COLUMNS);
                excelTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_Trx6_RATE_RIGHT_HEADERS);
            } else if (ARMConstants.getTransaction7().equals(selection.getAdjustmentType())) {
                excelTable.setVisibleColumns(ARMUtils.EXCEL_ROW_Trx7_SINGLE_COLUMNS);
                excelTable.setColumnHeaders(ARMUtils.EXCEL_ROW_Trx7_RATE_SINGLE_HEADERS);
                excelTable.setDoubleHeaderVisibleColumns(ARMUtils.FIRST_ROW_Trx7_RATE_RIGHT_COLUMNS);
                excelTable.setDoubleHeaderColumnHeaders(ARMUtils.FIRST_ROW_Trx7_RATE_RIGHT_HEADERS);
            }

            excelTable.setDoubleHeaderMap(configure_EXCELDoubleHeaderMap(selection.getAdjustmentType()));
            excelTable.setDoubleHeaderVisible(true);
            excelTable.setRefresh(true);
            List<AdjustmentRateDTO> dtoList = (List<AdjustmentRateDTO>) resultBeanContainer.getItemIds();
            excelContainer.addAll(logic.customizeExcel(dtoList, priceList,selection));
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {

            if (null != buttonName) {
                LOGGER.debug("buttonName :" + buttonName);
                switch (buttonName) {
                    case "reset":
                        adjustmentTypeDdlbRate.select(null);
                        companyDdlbRate.select(0);
                        businessDdlbRate.select(0);
                        selection.reset();
                        break;
                    case "save":
                        // save logic
                        logic.updateRateConfig(resultBeanContainer.getItemIds());
                        Notification.show(ARMMessages.getSaveSuccessfulMessage());
                        break;
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    private void createMonths() {
        for (int i = 0; i < MONTHS.length - 1; i++) {
            AdjustmentRateDTO rate = new AdjustmentRateDTO();
            rate.setMonth(MONTHS[i]);
            accrualList.add(rate);
        }
    }

    class RatesTableFeildFactory implements TableFieldFactory {

        @Override
        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
            AdjustmentRateDTO dto = (AdjustmentRateDTO) itemId;
            if (VariableConstants.CHECK_RECORD.equals(propertyId.toString())) {
                ExtCustomCheckBox checkRecord = new ExtCustomCheckBox();
                checkRecord.setImmediate(true);
                checkRecord.addClickListener(checkListener);
                return checkRecord;

            }
            if (VariableConstants.DATE_TYPE.equals(propertyId.toString())) {
                CustomComboBox dateType = new CustomComboBox();
                CommonUtils.loadComboBoxWithInteger(dateType, listNameMapper.get(propertyId.toString()), false);
                return dateType;

            }
            if (VariableConstants.PRICE.equals(propertyId.toString())) {
                CustomComboBox price = new CustomComboBox();
                CommonUtils.loadPriceComboBox(price, priceList);
                return price;

            }
            if (VariableConstants.EXCLUSION_DETAILS.equals(propertyId.toString())) {
                CustomTextField exclusionDetails = new CustomTextField();
                exclusionDetails.setImmediate(true);
                exclusionDetails.setStyleName(VariableConstants.SEARCH_ICON);
                exclusionDetails.setDescription(VariableConstants.EXCLUSION_DETAILS);
                exclusionDetails.setData(itemId);
                dto.setComponent(VariableConstants.EXCLUSION_DETAILS, exclusionDetails);
                exclusionDetails.addClickListener(exclusionListener);
                return exclusionDetails;

            }
            if (VariableConstants.RATE_BASIS.equals(propertyId.toString())) {
                CustomComboBox price = new CustomComboBox();
                CommonUtils.loadComboBoxWithInteger(price, listNameMapper.get(propertyId.toString()), false);
                return price;
            }
            if (VariableConstants.RATE_FREQUENCY.equals(propertyId.toString())) {
                final CustomComboBox rateFrequency = new CustomComboBox();
                CommonUtils.loadComboBoxWithInteger(rateFrequency, listNameMapper.get(propertyId.toString()), false);
                rateFrequency.setData(itemId);
                rateFrequency.addFocusListener(new FieldEvents.FocusListener() {

                    @Override
                    public void focus(FieldEvents.FocusEvent event) {
                        rateFrequency.addValueChangeListener(frequencyListener);
                        rateFrequency.removeFocusListener(this);
                    }
                });
                return rateFrequency;
            }

            if (VariableConstants.RATE_PERIOD.equals(propertyId.toString())) {
                CustomComboBox ratePeriod = new CustomComboBox();
                CommonUtils.loadRatePeriodComboBox(ratePeriod, 1, listNameMapper.get(propertyId.toString()));
                dto.setComponent(VariableConstants.RATE_PERIOD, ratePeriod);
                return ratePeriod;
            }
            if (VariableConstants.INVENTORY_CUSTOMER.equals(propertyId.toString())) {
                CustomComboBox price = new CustomComboBox();
                CommonUtils.loadComboBoxWithInteger(price, listNameMapper.get(propertyId.toString()), false);
                price.setData(itemId);
                price.addValueChangeListener(inventory_CustomerListener);
                return price;
            }
            if (VariableConstants.INVENTORY_DETAILS.equals(propertyId.toString())) {
                CustomComboBox inventoryDetails = new CustomComboBox();
                CommonUtils.loadRatePeriodComboBox(inventoryDetails, 1, listNameMapper.get(propertyId.toString()));
                return inventoryDetails;
            }
            if (VariableConstants.INVENTORYCAL_CULATION.equals(propertyId.toString())) {
                CustomTextField inventoryCalculation = new CustomTextField();
                inventoryCalculation.setDescription(VariableConstants.INVENTORYCAL_CULATION);
                inventoryCalculation.setImmediate(true);
                inventoryCalculation.setStyleName(VariableConstants.SEARCH_ICON);
                inventoryCalculation.setData(itemId);
                dto.setComponent(VariableConstants.INVENTORYCAL_CULATION, inventoryCalculation);
                inventoryCalculation.addClickListener(inventory_CalculationListener);
                return inventoryCalculation;
            }
            if (VariableConstants.RESERVE_DATE.equals(propertyId.toString())) {
                CustomComboBox reserveDate = new CustomComboBox();
                CommonUtils.loadRatePeriodComboBox(reserveDate, NumericConstants.TWO, listNameMapper.get(propertyId.toString()));
                return reserveDate;
            }
            if (VariableConstants.ADJUSTED_PRICE.equals(propertyId.toString())) {
                CustomComboBox adjustedPrice = new CustomComboBox();
                CommonUtils.loadPriceComboBox(adjustedPrice, priceList);
                return adjustedPrice;
            } else if (VariableConstants.BASELINE_PRICE.equals(propertyId.toString())) {
                CustomComboBox baseLine_Price = new CustomComboBox();
                CommonUtils.loadPriceComboBox(baseLine_Price, priceList);
                return baseLine_Price;
            }
            return null;
        }
        ExtCustomCheckBox.ClickListener checkListener = new ExtCustomCheckBox.ClickListener() {

            @Override
            public void click(ExtCustomCheckBox.ClickEvent event) {

                Boolean val = ((ExtCustomCheckBox) event.getComponent()).getValue();
                if (val == null || !val) {
                    if (resultsTable.getLeftFreezeAsTable().getColumnCheckBox(VariableConstants.CHECK_RECORD)) {
                        resultsTable.getLeftFreezeAsTable().setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
                    }
                } else {
                    resultsTable.getLeftFreezeAsTable().setColumnCheckBox(VariableConstants.CHECK_RECORD, true, findAllChecked());
                }
            }

            private boolean findAllChecked() {
                List<AdjustmentRateDTO> list = resultBeanContainer.getItemIds();
                boolean checkAll = true;
                for (AdjustmentRateDTO adjustmentRateDTO : list) {
                    if (!adjustmentRateDTO.getCheckRecord()) {
                        checkAll = false;
                        break;
                    }
                }
                return checkAll;
            }
        };

        Property.ValueChangeListener frequencyListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    AdjustmentRateDTO val = (AdjustmentRateDTO) ((CustomComboBox) event.getProperty()).getData();
                    int frequency = (int) event.getProperty().getValue();
                    CustomComboBox ratePeriod = (CustomComboBox) val.getComponent(VariableConstants.RATE_PERIOD);
                    if (frequency > 0) {
                        CommonUtils.loadRatePeriodComboBox(ratePeriod, findFrequency(frequency), listNameMapper.get(VariableConstants.RATE_PERIOD));
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        };
        Property.ValueChangeListener inventory_CustomerListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {

                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        };

    }

    private int findFrequency(int val) {
        String frequency = HelperListUtil.getInstance().getIdDescMap().get(val);
        int freq = 1;
        if (ARMConstants.getAnnually().equals(frequency)) {
            freq = NumericConstants.TWELVE;
        } else if (ARMConstants.getSemiAnnually().equals(frequency)) {
            freq = NumericConstants.SIX;
        } else if (ARMConstants.getQuarterly().equals(frequency)) {
            freq = NumericConstants.THREE;
        }
        return freq;
    }

    private int findFrequency() {
        int freq = 0;
        List<AdjustmentRateDTO> list = resultBeanContainer.getItemIds();
        for (int i = 0; i < list.size(); i++) {
            AdjustmentRateDTO dto = list.get(i);
            freq = i == 0 ? dto.getRateFrequency() : freq;
            if (freq != dto.getRateFrequency()) {
                freq = 0;
                break;
            }
        }
        return freq;
    }

    private int findCustomerValue() {
        int cusValue = 0;
        List<AdjustmentRateDTO> list = resultBeanContainer.getItemIds();
        for (int i = 0; i < list.size(); i++) {
            AdjustmentRateDTO dto = list.get(i);
            cusValue = i == 0 ? dto.getInventoryCustomer() : cusValue;
            if (cusValue != dto.getInventoryCustomer()) {
                cusValue = 0;
                break;
            }
        }
        return cusValue;

    }

    CustomTextField.ClickListener exclusionListener = new CustomTextField.ClickListener() {

        @Override
        public void click(CustomTextField.ClickEvent event) {
            final CustomTextField exclusionDetails = (CustomTextField) event.getComponent();
            final AdjustmentRateDTO exclusionDetailsDto = (AdjustmentRateDTO) exclusionDetails.getData();
            String invenCalculationValue = StringUtils.EMPTY;
            if (lookup == null) {
                lookup = new ExclusionAndInventoryRateLookUp(exclusionDetails.getDescription(), invenCalculationValue);
            } else {
                lookup.reloadScreen(invenCalculationValue);
            }
            getUI().addWindow(lookup);
            lookup.addCloseListener(new CloseListener() {

                @Override
                public void windowClose(CloseEvent e) {
                    if (exclusionDetailsDto == null) {
                        exclusionDetails.setData(lookup.getExRateDTO());
                    } else {
                        exclusionDetailsDto.setViewMasterSid(lookup.getExRateDTO().getViewMasterSid());
                    }
                    if (ExclusionAndInventoryRateLookUp.flag) {
                        exclusionDetails.setValue(lookup.getExRateDTO().getViewName());
                    }
                }

            });

        }
    };
    CustomTextField.ClickListener inventory_CalculationListener = new CustomTextField.ClickListener() {

        @Override
        public void click(CustomTextField.ClickEvent event) {
            final CustomTextField exclusionDetails = (CustomTextField) event.getComponent();
            final AdjustmentRateDTO exclusionDetailsDto = (AdjustmentRateDTO) exclusionDetails.getData();
            String invenCalculationValue = StringUtils.EMPTY;
            int inventoryCusHelpValue = resultBeanContainer.getContainerProperty(exclusionDetailsDto, VariableConstants.INVENTORY_CUSTOMER).getValue() == null ? 0 : (int) resultBeanContainer.getContainerProperty(exclusionDetailsDto, VariableConstants.INVENTORY_CUSTOMER).getValue();
            invenCalculationValue = HelperListUtil.getInstance().getIdDescMap().get(inventoryCusHelpValue);

            if (lookup == null) {
                lookup = new ExclusionAndInventoryRateLookUp(exclusionDetails.getDescription(), invenCalculationValue);
            } else {
                lookup.reloadScreen(invenCalculationValue);
            }
            getUI().addWindow(lookup);
            lookup.addCloseListener(new CloseListener() {

                @Override
                public void windowClose(CloseEvent e) {
                    if (exclusionDetailsDto == null) {
                        exclusionDetails.setData(lookup.getExRateDTO());
                    } else {
                        exclusionDetailsDto.setViewMasterSid(lookup.getExRateDTO().getViewMasterSid());
                    }
                    if (lookup.getExRateDTO().isSelectFlag()) {
                        exclusionDetails.setValue(lookup.getExRateDTO().getViewName());
                    }
                }

            });

        }
    };
    CustomTextField.ClickListener massupdateListener = new CustomTextField.ClickListener() {

        @Override
        public void click(CustomTextField.ClickEvent event) {
            final CustomTextField exclusionDetails = (CustomTextField) event.getComponent();
            final AdjustmentRateDTO exclusionDetailsDto = null;
            String invenCalculationValue = StringUtils.EMPTY;
            if ("InventCal".equalsIgnoreCase(exclusionDetails.getDescription())) {
                int inventoryCusHelpValue = findCustomerValue();
                invenCalculationValue = HelperListUtil.getInstance().getIdDescMap().get(inventoryCusHelpValue);
            }
            if (lookup == null) {
                lookup = new ExclusionAndInventoryRateLookUp(exclusionDetails.getDescription(), invenCalculationValue);
            } else {
                lookup.reloadScreen(invenCalculationValue);
            }
            getUI().addWindow(lookup);
            lookup.addCloseListener(new CloseListener() {

                @Override
                public void windowClose(CloseEvent e) {
                    if (exclusionDetailsDto == null) {
                        exclusionDetails.setData(lookup.getExRateDTO());
                    } else {
                        exclusionDetailsDto.setViewMasterSid(lookup.getExRateDTO().getViewMasterSid());
                    }

                    exclusionDetails.setValue(lookup.getExRateDTO().getViewName());
                }

            });

        }
    };

    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"));
        return input;
    }

    public void configurePermission() {

        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Adjustment Rate Configuration" + "," + "Landing screen");
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("generateBtn") != null && !((AppPermission) functionHM.get("generateBtn")).isFunctionFlag()) {
                generateBtn.setVisible(false);
            } else {
                generateBtn.setVisible(true);
            }
            if (functionHM.get("saveBtn") != null && !((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
                saveBtn.setVisible(false);
            } else {
                saveBtn.setVisible(true);
            }
            if (functionHM.get("exportBtn") != null && !((AppPermission) functionHM.get("exportBtn")).isFunctionFlag()) {
                exportBtn.setVisible(false);
            } else {
                exportBtn.setVisible(true);
            }
            if (functionHM.get("populateBtnRate") != null && ((AppPermission) functionHM.get("populateBtnRate")).isFunctionFlag()) {
                populateBtnRate();
            } else {
                populateBtnRate.setVisible(false);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

    }

}
