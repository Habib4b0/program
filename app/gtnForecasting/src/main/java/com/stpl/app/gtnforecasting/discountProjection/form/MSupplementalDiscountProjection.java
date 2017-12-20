
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.forecastabstract.lookups.AbstractComparisonLookup;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastDiscountProjection;
import com.stpl.app.gtnforecasting.discountProjection.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.discountProjection.dto.FormulaDTO;
import com.stpl.app.gtnforecasting.discountProjection.dto.LookUpDTO;
import com.stpl.app.gtnforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.SupplementalTableLogic;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.SELECTMETHODOLOGY;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACCESS;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_PRICE;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT1;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT2;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_PARITY;
import static com.stpl.app.utils.Constants.LabelConstants.METHODOLOGY;
import static com.stpl.app.utils.Constants.LabelConstants.PARITY;
import static com.stpl.app.utils.Constants.LabelConstants.PARITY_REFERENCE;
import static com.stpl.app.utils.Constants.LabelConstants.PARITY_SETTINGS;
import static com.stpl.app.utils.Constants.RegexConstants.DOUBLE_CHECK;
import com.stpl.app.utils.converters.DataFormatConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Sizeable;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public class MSupplementalDiscountProjection extends ForecastDiscountProjection {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastDiscountProjection.class);
    /**
     * The period table id.
     */
    
    SupplementalTableLogic tableLogic = new SupplementalTableLogic();
    FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    /**
     * The max split position.
     */
    private final float maxSplitPosition = NumericConstants.THOUSAND;

    /**
     * The min split position.
     */
    private final float minSplitPosition = NumericConstants.TWO_HUNDRED;

    /**
     * The split position.
     */
    private final float splitPosition = NumericConstants.THREE_HUNDRED;
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    /**
     * The result bean Container .
     */
    ExtTreeContainer<DiscountProjectionDTO> resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    private ExtCustomTreeTable exportPeriodViewTable;

    boolean firstGenerated = false;
    public boolean checkAll = false;
    private boolean valueChange = false;

    Object propertyIdValue = null;
    Object itemIdValue = null;
    Property.ValueChangeListener valueChangeListener = null;

    String focusValue = StringUtils.EMPTY;
    String blurValue = StringUtils.EMPTY;
    String dtoItemValue = StringUtils.EMPTY;

    List<LookUpDTO> dtoListValue = new ArrayList<>();
    static Map columnName = new HashMap();
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter contractPriceFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_DOLLAR);
    SupplementalDiscountProjectionLogic supplementalDiscountProjectionLogic = new SupplementalDiscountProjectionLogic();
    CommonLogic commonLogic = new CommonLogic();
    private ExtTreeContainer<DiscountProjectionDTO> excelResultBean = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The bean for loading Field drop down.
     */
    final private BeanItemContainer<String> fieldDdlbBean = new BeanItemContainer<>(String.class);
    private DecimalFormat dollZeroDec = new DecimalFormat("$###,##0.00");
    private static BeanItemContainer<String> methdologyBean = new BeanItemContainer<>(String.class);
    private final HeaderUtils headerUtils = new HeaderUtils();
    Object formulaList;
    boolean canLoad = true;

    public MSupplementalDiscountProjection(SessionDTO session, String screenName)  {
        super(session, screenName);
          projectionDTO.setSessionDTO(session);
    }

    public void configureFieldsForSupplemental() {
        methdologyBean.removeAllItems();
        methdologyBean.addAll(supplementalDiscountProjectionLogic.getNativeDropDown());
        loadLevelFilterValue();
        enableOrDisable(false);
        setPeriod();
        valueOption.addItem("Yes");
        valueOption.addItem("No");
        valueOption.select("Yes");

        value.addValidator(new RegexpValidator(DOUBLE_CHECK.getConstant(), "Please Enter Only Numbers with two decimal places"));
        value.addValidator(new StringLengthValidator("Value should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        value.setValidationVisible(true);
        value.setRequired(true);
        valueDdlb.removeAllItems();
        valueDdlb.setInputPrompt(SELECTMETHODOLOGY.getConstant());
        valueDdlb.setNullSelectionAllowed(true);
        valueDdlb.setNullSelectionItemId(SELECTMETHODOLOGY.getConstant());
        valueDdlb.removeAllItems();
        valueDdlb.setContainerDataSource(methdologyBean);
        valueDdlb.setVisible(false);
        value.setVisible(true);
        formulaList = supplementalDiscountProjectionLogic.loadFormula();
        projectionDTO.setSupplementalList(supplementalDiscountProjectionLogic.getLevelList(session.getCustomerHierarchyId(), session.getProductHierarchyId()));
        value.addBlurListener(new BlurListener() {
            @Override
            public void blur(BlurEvent event) {
                value.setValue(dollZeroDec.format(Double.valueOf(value.getValue())));
            }
        });

        if (ACTION_EDIT.getConstant().equals(session.getAction()) || ACTION_VIEW.getConstant().equals(session.getAction())) {
            Map<Object, Object> map = CommonLogic.getMProjectionSelection(session.getProjectionId(), Constant.SUPPLEMENTAL_DISCOUNT_PROJECTION);
            if (map != null && !map.isEmpty()) {
                Object value = map.get(Constant.VARIABLES);
                if (value != null) {
                    String tempVariables = String.valueOf(value);
                    tempVariables = tempVariables.replace("[", StringUtils.EMPTY);
                    tempVariables = tempVariables.replace("]", StringUtils.EMPTY);
                    String tempVariablesArr[] = tempVariables.split(", ");
                    if (tempVariablesArr.length == 0 || tempVariables.isEmpty()) {
                        selectDefault();
                    } else {
                        for (String string : tempVariablesArr) {
                            variablesForMandated.select(string);
                        }
                    }
                }
            } else {
                selectDefault();
            }

        } else {
            selectDefault();
        }
        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        fieldDdlb.setContainerDataSource(fieldDdlbBean);
        fieldDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                fieldDdlb();
                setPeriod();
            }
        });

        if (ACTION_EDIT.getConstant().equals(session.getAction())) {
            generateBtnClickLogic(true);
        } else if (ACTION_VIEW.getConstant().equals(session.getAction())) {
            generateBtnClickLogic(true);
            buttonDisable();
        }
    }

    private void buttonDisable() {
        populateBtn.setEnabled(false);
    }

    public void setPeriod() {
        startPeriod.removeAllItems();
        endPeriod.removeAllItems();
        startPeriod.setImmediate(true);
        endPeriod.setImmediate(true);
        startPeriod.setNullSelectionAllowed(true);
        endPeriod.setNullSelectionAllowed(true);
        startPeriod.setInputPrompt(SELECT_ONE.getConstant());
        endPeriod.setInputPrompt(SELECT_ONE.getConstant());
        startPeriod.setContainerDataSource(startPeriodBean);
        endPeriod.setContainerDataSource(endPeriodBean);

        startPeriodBean.addItem(SELECT_ONE.getConstant());
        endPeriodBean.addItem(SELECT_ONE.getConstant());

        startPeriodBean.addAll(projectionDTO.getPeriodList());
        endPeriodBean.addAll(projectionDTO.getPeriodList());
        startPeriod.select(SELECT_ONE.getConstant());
        endPeriod.select(SELECT_ONE.getConstant());
    }

    public void selectDefault() {
        variablesForMandated.select(METHODOLOGY.getConstant());
        variablesForMandated.select(CONTRACT_PRICE.getConstant());
    }

    @Override
    public void generateBtnClickLogic(Boolean isGenerate) {
        LOGGER.debug("Waiting for M_DISCOUNT_PROCEDURE_CALL to complete ");
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.SUPPLEMENT_PROCEDURE_CALL));
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.M_DISCOUNT_PROCEDURE_CALL));
        LOGGER.debug(" M_DISCOUNT_PROCEDURE_CALL is complete..");
        LOGGER.debug("Generate button click listener started");
        firstGenerated = true;
        fieldDdlbBean.removeAllItems();
        startPeriodBean.removeAllItems();
        endPeriodBean.removeAllItems();
        if (isProjectionSelection()) {
            tableLogic.setRefresh(false);
            resultsTableLayout.removeAllComponents();
            tableLogic = new SupplementalTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
            loadLevelFilterValue();
            projectionDTO.setIsFilter(false);
            projectionDTO.setGroupFilter(StringUtils.EMPTY);
            levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
            tableLogic.setRefresh(true);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Variables Selected", "There are no variables selected.  Please select at least one variable and try again.");
        }
        loadFieldDdlb();
        setPeriod();
        LOGGER.debug("Generate button click listener ends");
    }

    void loadFieldDdlb() {
        fieldDdlbBean.removeAllItems();
        List<String> discountList = projectionDTO.getVariableList();
        if (discountList.contains(DISCOUNT.getConstant())) {
            discountList.remove(DISCOUNT.getConstant());
            discountList.add(DISCOUNT_PARITY.getConstant());
        }
        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        fieldDdlb.setInputPrompt(SELECT_ONE.getConstant());
        fieldDdlbBean.addAll(discountList);
    }

    public void fieldDdlb() {
        LOGGER.debug(" fieldDdlb ValueChangeEvent initiated ");
        if (METHODOLOGY.getConstant().equals(String.valueOf(fieldDdlb.getValue()))) {
            valueDdlb.setVisible(true);
            valueDdlb.removeAllItems();
            value.setVisible(false);
            valueOption.setVisible(false);
            valueLookUp.setVisible(false);
            valueDdlb.setValue(null);
            supplementalDiscountProjectionLogic.getNativeSelect(valueDdlb);
        } else if (ACCESS.getConstant().equals(String.valueOf(fieldDdlb.getValue()))) {
            valueDdlb.setVisible(true);
            value.setVisible(false);
            valueOption.setVisible(false);
            valueLookUp.setVisible(false);

            valueDdlb.removeAllItems();
            valueDdlb.setImmediate(true);
            valueDdlb.setInputPrompt(Constant.SELECT_ONE);
            valueDdlb.setNullSelectionAllowed(false);
            valueDdlb.addItem(SELECT_ONE.getConstant());
            valueDdlb.addItem(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            valueDdlb.addItem("NP");
            valueDdlb.addItem(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            valueDdlb.select(SELECT_ONE.getConstant());

        } else if (CONTRACT_PRICE.getConstant().equals(String.valueOf(fieldDdlb.getValue())) || DISCOUNT1.getConstant().equals(String.valueOf(fieldDdlb.getValue()))
                || DISCOUNT2.getConstant().equals(String.valueOf(fieldDdlb.getValue())) || DISCOUNT_PARITY.getConstant().equals(String.valueOf(fieldDdlb.getValue()))) {
            valueLookUp.setVisible(false);
            valueDdlb.setVisible(false);
            value.setVisible(true);
            valueOption.setVisible(false);
        } else if (PARITY.getConstant().equals(String.valueOf(fieldDdlb.getValue()))) {
            valueLookUp.setVisible(false);
            valueDdlb.setVisible(false);
            value.setVisible(false);
            valueOption.setVisible(true);
            valueOption.setValue("Yes");

        } else if (PARITY_REFERENCE.getConstant().equals(String.valueOf(fieldDdlb.getValue()))) {
            valueLookUp.setVisible(true);
            valueDdlb.setVisible(false);
            value.setVisible(false);
            valueOption.setVisible(false);
        } else {
            value.setVisible(true);
            valueDdlb.setVisible(false);
            value.setValue(StringUtils.EMPTY);
        }

        LOGGER.debug("fieldDdlb ValueChangeEvent ends ");
    }

    public boolean isProjectionSelection() {
        boolean selected = false;
        Object[] itemIds = variablesForMandated.getItemIds().toArray();
        List<String> selectedItems = new ArrayList<>();
        for (Object itemId : itemIds) {
            if (variablesForMandated.isSelected(itemId)) {
                selected = true;
                if (PARITY_SETTINGS.getConstant().equals(String.valueOf(itemId))) {
                    selectedItems.add(PARITY.getConstant());
                    selectedItems.add(PARITY_REFERENCE.getConstant());
                    selectedItems.add(DISCOUNT.getConstant());
                } else {
                    selectedItems.add(String.valueOf(itemId));
                }
            }

        }
        if (selected) {
            projectionDTO.setFrequency(QUARTERLY.getConstant());
            projectionDTO.setHistory("12 Quarters");
            projectionDTO.setHistoryNum(NumericConstants.TWELVE);
            projectionDTO.setVariableList(selectedItems);
            projectionDTO.setProjectionId(session.getProjectionId());
            projectionDTO.setUserId(Integer.valueOf(session.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.setStartDate(session.getFromDate());
            projectionDTO.setStartDate(session.getToDate());
            projectionDTO.setCustHierarchySid(session.getCustomerHierarchyId());
            projectionDTO.setProdHierarchySid(session.getProductHierarchyId());
            projectionDTO.setForecastStartYear(session.getForecastDTO().getForecastStartYear());
            projectionDTO.setForecastStartMonth(session.getForecastDTO().getForecastStartMonth());
            projectionDTO.setForecastEndYear(session.getForecastDTO().getForecastEndYear());
            projectionDTO.setForecastEndMonth(session.getForecastDTO().getForecastEndMonth());
            projectionDTO.setProjectionStartYear(session.getForecastDTO().getProjectionStartYear());
            projectionDTO.setProjectionStartMonth(session.getForecastDTO().getProjectionStartMonth());
            projectionDTO.setProjectionEndYear(session.getForecastDTO().getProjectionEndYear());
            projectionDTO.setProjectionEndMonth(session.getForecastDTO().getProjectionEndMonth());
            projectionDTO.setProjectionEndDate(session.getForecastDTO().getProjectionEndDate());
            projectionDTO.setForecastEndDate(session.getForecastDTO().getForecastEndDate());
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setLevelFlag(false);
            projectionDTO.setSupplementalLevelNo(1);
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelNo(NumericConstants.FOUR);
            if (session.getFromDate() != null && session.getToDate() != null) {
                projectionDTO.setProjectionNum(CommonUtils.getProjections(new Date(), session.getToDate(), projectionDTO.getFrequency()));
            }
        }
        return selected;
    }

    private void loadLevelFilterValue() {
        levelFilterDdlb.removeAllItems();
        levelFilterDdlb.addItem(SELECT_ONE);
        levelFilterDdlb.setNullSelectionAllowed(true);
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE);
        levelFilterDdlb.setValue(SELECT_ONE);
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionAllowed(true);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);

        List<String> hierarchy = loadLevelFilter();
        if (hierarchy != null) {
            for (int i = 0; i < hierarchy.size(); i++) {
                levelFilterDdlb.addItem(i);
                levelFilterDdlb.setItemCaption(i, hierarchy.get(i));
                if (i < hierarchy.size() - 1) {
                    levelDdlb.addItem(i);
                    levelDdlb.setItemCaption(i, hierarchy.get(i));
                }
            }
        }
    }

    public List<String> loadLevelFilter() {
        List<String> levelFilter = new ArrayList<>();
        levelFilter.add("Level 1 - Customer");
        levelFilter.add("Level 2 - Contract");
        levelFilter.add("Level 3 - Therapeutic Class");
        levelFilter.add("Level 4 - Brand");
        levelFilter.add("Level 5 - Product");
        return levelFilter;
    }

    /**
     * Reset valueLookUp.
     *
     * @param event the event
     */
    @UiHandler("valueLookUp")
    public void valueLookUp(CustomTextField.ClickEvent event) {
        final ParityLookup parityLookup = new ParityLookup(valueLookUp, session, true);
        getUI().addWindow(parityLookup);

        parityLookup.addCloseListener(new Window.CloseListener() {

            public void windowClose(Window.CloseEvent e) {
                dtoListValue = (List<LookUpDTO>) parityLookup.getDtoListValue();
                setDtoListValue(dtoListValue);
            }
        });

    }

    public void setDtoListValue(List<LookUpDTO> dtoListValue) {
        this.dtoListValue = dtoListValue;
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(true);
        periodTableId.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        resultsTableLayout.addComponent(periodTableId);
        controlLayout = tableLogic.createControls();
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTableLayout.addComponent(controlLayout);
        controlLayout.setSizeUndefined();
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(NumericConstants.TWENTY);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSupplementalLeftTableColumns(fullHeader, Constant.CUSTOMER_SMALL);
        rightHeader = HeaderUtils.getSupplementalrightTableColumns(projectionDTO, fullHeader);
        resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
        tableLogic.setTreeNodeMultiClick(false);
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());
        resultBeanContainer.setColumnProperties(rightHeader.getProperties());
        periodTableId.setContainerDataSource(resultBeanContainer);

        leftTable = periodTableId.getLeftFreezeAsTable();
        rightTable = periodTableId.getRightFreezeAsTable();

        periodTableId.setHeight(AbstractComparisonLookup.SIX_FIFTY_PX);
        leftTable.setHeight(AbstractComparisonLookup.SIX_FIFTY_PX);
        rightTable.setHeight(AbstractComparisonLookup.SIX_FIFTY_PX);
        leftTable.setSortEnabled(false);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.CHECK)) {
                leftTable.setColumnCheckBox(obj, true);
                leftTable.setColumnCheckBoxDisable(obj, ACTION_VIEW.getConstant().equals(session.getAction()));
                checkAll = false;
            }
        }

        leftTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                tableLogic.setRefresh(false);
                if (event.isChecked()) {
                    checkAll = true;
                    supplementalDiscountProjectionLogic.headerCheckALLQuery(session, checkAll ? 1 : 0, true);
                    checkClearAll(true);
                } else {
                    checkAll = false;
                    supplementalDiscountProjectionLogic.headerCheckALLQuery(session, checkAll ? 1 : 0, true);
                    checkClearAll(false);
                }
                tableLogic.setRefresh(true);
            }

        });
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterFieldVisible(Constant.CHECK, false);
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (String.valueOf(propertyId).equals(Constant.CHECK)) {
                    DiscountProjectionDTO itemDto1 = getBeanFromId(itemId);
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    if (checkAll) {
                        check.setValue(true);
                    } else {
                        check.setValue(false);
                    }
                    boolean checkEnbl1 = ACTION_VIEW.getConstant().equals(session.getAction());
                    boolean checkEnbl2 = itemDto1.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                            || itemDto1.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS);
                    if (checkEnbl1) {
                        check.setEnabled(false);
                    } else if (checkEnbl2) {
                        check.setValue(false);
                        check.setEnabled(false);
                    }
                    if (checkEnbl1) {

                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                tableLogic.setRefresh(false);
                                DiscountProjectionDTO itemDto = getBeanFromId(itemId);
                                itemDto.addBooleanProperties(Constant.CHECK, check.getValue());
                                supplementalDiscountProjectionLogic.updateCheckedRecord(itemDto, projectionDTO, check.getValue() ? 1 : 0);
                                boolean checkCustomerFlag = false;
                                boolean checkBrandFlag = false;
                                List<Object> queryList = null;
                                Map<String, Object> mapList = tableLogic.getExpandedTreeLevelList();
                                queryList = supplementalDiscountProjectionLogic.getUpdateRecord(itemDto.getParentCcpDetailIdList(), session.getUserId(), session.getSessionId());
                                checkCustomerFlag = queryList.size() == itemDto.getParentCcpDetailIdList().size();
                                int[] levelNo = new int[2];
                                if (itemDto.getSupplementalLevelNo() == 5) {
                                    if (queryList != null && !queryList.isEmpty()) {
                                        int ndcCount = 0;
                                        for (Object queryList1 : queryList) {
                                            Object[] ob = (Object[]) queryList1;
                                            if (ob[1] != null && itemDto.getCcpDetailIds().contains(ob[1].toString())) {
                                                ndcCount++;
                                            }
                                        }
                                        checkBrandFlag = ndcCount == itemDto.getCcpDetailIds().size();
                                    }
                                    if (checkCustomerFlag) {
                                        getCustomerLevelCheckUpdateForBrandChange(tableLogic, check.getValue(), itemDto);
                                    } else if (checkBrandFlag) {
                                        for (Map.Entry<String, Object> entry : mapList.entrySet()) {
                                            DiscountProjectionDTO itemDtoExpanded = (DiscountProjectionDTO) entry.getValue();
                                            if ((!itemDtoExpanded.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                                                    && !itemDtoExpanded.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS)) && (itemDto.getCcpDetailIds().containsAll(itemDtoExpanded.getCcpDetailIds()))) {
                                                    Object tempId = tableLogic.getcurrentTreeData(entry.getKey());
                                                    itemDtoExpanded.addBooleanProperties(Constant.CHECK, check.getValue());
                                                    if (tempId != null) {
                                                        tableLogic.getContainerDataSource().getContainerProperty(tempId, Constant.CHECK).setValue(check.getValue());
                                                    }
                                                }
                                            }
                                    } else {
                                        levelNo[0] = 1;
                                        levelNo[1] = NumericConstants.FOUR;
                                        uncheckUpperLevel(tableLogic, false, itemDto, levelNo);
                                    }
                                } else if (itemDto.getSupplementalLevelNo() == NumericConstants.FOUR) {
                                    if (checkCustomerFlag) {
                                        getCustomerLevelCheckUpdateForBrandChange(tableLogic, check.getValue(), itemDto);
                                        getProductLevelCheckUpdate(tableLogic, check.getValue(), itemDto);
                                    } else {
                                        levelNo[0] = 1;
                                        levelNo[1] = 1;
                                        uncheckUpperLevel(tableLogic, false, itemDto, levelNo);
                                        getProductLevelCheckUpdate(tableLogic, check.getValue(), itemDto);
                                    }
                                } else if (itemDto.getSupplementalLevelNo() == 1) {
                                    getCustomerLevelCheckUpdate(tableLogic, check.getValue(), itemDto);
                                    getProductLevelCheckUpdate(tableLogic, check.getValue(), itemDto);
                                }
                                List<Integer> listStr = supplementalDiscountProjectionLogic.headerCheckALLQuery(session, 0, false);
                                try {
                                    checkAll = listStr.size() != 1 ? false : (listStr.get(0) == 1);
                                    leftTable.setColumnCheckBox(Constant.CHECK, true, checkAll);
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                                tableLogic.setRefresh(true);
                            }
                        });
                    }

                    return check;
                }

                return null;
            }
        }
        );
        leftTable.setEditable(true);

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            if (propertyId.equals("Methodology")) {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
            } else {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
            }
        }

        rightTable.setColumnAlignment(headerUtils.supplementalRightTableOneColumns[1], ExtCustomTable.Align.LEFT);
        rightTable.setColumnAlignment(Constant.CONTRACT_END_DATE, ExtCustomTable.Align.CENTER);

        rightTable.setDoubleHeaderVisible(true);
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());

        rightTable.setDoubleHeaderColumnWidth("actualdiscount", NumericConstants.ONE_SIX_ZERO);
        rightTable.setColumnWidth("actualdiscount", NumericConstants.ONE_SIX_ZERO);

        rightTable.setDoubleHeaderColumnWidth(Constant.CONTRACT_END_DATE, NumericConstants.ONE_SIX_ZERO);
        rightTable.setColumnWidth(Constant.CONTRACT_END_DATE, NumericConstants.ONE_SIX_ZERO);
        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {

                if (!ACTION_VIEW.getConstant().equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.DISABLE) && (String.valueOf(propertyId).contains("Access")
                        && (getBeanFromId(itemId).getLevelName().equals(Constant.BRAND_CAPS) || getBeanFromId(itemId).getLevelName().equals(Constant.PRODUCT_LABEL)))) {
                    final CustomComboBox comboBox = new CustomComboBox();
                    comboBox.setData(propertyId);
                    comboBox.addStyleName(Constant.TXT_RIGHT_ALIGN);

                    if (String.valueOf(propertyId).contains("Access")) {
                        comboBox.setWidth(NumericConstants.ONE_TWO_ZERO, UNITS_PERCENTAGE);
                        comboBox.setImmediate(true);
                        comboBox.addItem(SELECT_ONE.getConstant());
                        comboBox.addItem(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        comboBox.addItem("NP");
                        comboBox.addItem(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                        comboBox.select(SELECT_ONE.getConstant());
                    }
                    comboBox.addFocusListener(new FocusListener() {

                        @Override
                        public void focus(FocusEvent event) {
                            valueChange = true;
                            attachValueChangeListener((AbstractField) event.getComponent());
                            itemIdValue = itemId;
                            propertyIdValue = propertyId;
                        }

                    });
                    comboBox.addBlurListener(new BlurListener() {

                        public void blur(BlurEvent event) {
                            try {
                                detachLisener(comboBox);
                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }
                    });
                    return comboBox;

                } else if (!ACTION_VIEW.getConstant().equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.DISABLE) && ((String.valueOf(propertyId).contains("ContractPrice")
                        || String.valueOf(propertyId).contains("Discount1") || String.valueOf(propertyId).contains("Discount2")
                        || String.valueOf(propertyId).contains(Constant.DISCOUNT_SMALL)) && getBeanFromId(itemId).getLevelName().equals(Constant.PRODUCT_LABEL))) {
                    final TextField textField = new TextField();
                    textField.setData(propertyId);
                    textField.setImmediate(true);
                    textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                    textField.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                    textField.addBlurListener(new BlurListener() {

                        @Override
                        public void blur(BlurEvent event) {
                            blurValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue());
                            if (blurValue.isEmpty()) {
                                blurValue = Constant.DASH;
                            } else {
                                blurValue = blurValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                                blurValue = blurValue.replace("$", StringUtils.EMPTY);
                                blurValue = blurValue.trim();
                            }
                            saveFromTableField(itemId, propertyId, blurValue, false);
                            LOGGER.debug(" group blur Value" + String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).trim());
                        }
                    });

                    if (String.valueOf(propertyId).contains("Discount1") || String.valueOf(propertyId).contains("Discount2") || String.valueOf(propertyId).contains(Constant.DISCOUNT_SMALL)) {
                        textField.setConverter(percentFormat);
                    } else if (String.valueOf(propertyId).contains("ContractPrice")) {
                        textField.setConverter(contractPriceFormat);
                    }

                    return textField;
                } else if (!ACTION_VIEW.getConstant().equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.DISABLE) && (String.valueOf(propertyId).contains("ParityReference") && getBeanFromId(itemId).getLevelName().equals(Constant.PRODUCT_LABEL))) {
                    final CustomTextField parityLookUp = new CustomTextField();
                    parityLookUp.setData(propertyId);
                    parityLookUp.addStyleName(Constant.SEARCH_ICON_STYLE);
                    parityLookUp.setImmediate(true);
                    parityLookUp.addClickListener(new CustomTextField.ClickListener() {

                        public void click(CustomTextField.ClickEvent event) {

                            String splitPeriodYear = parityLookUp.getData().toString().replace("ParityReference", StringUtils.EMPTY).concat("Parity");
                            getBeanFromId(itemId).getPropertyValue(splitPeriodYear);
                            boolean parityCheckFlag = getBeanFromId(itemId).getPropertyValue(splitPeriodYear) == null ? false : (Boolean) getBeanFromId(itemId).getPropertyValue(splitPeriodYear);
                            if (parityCheckFlag) {
                                session.setCcpDetailsSid(getBeanFromId(itemId).getCcpDetailsSID());

                                final ParityLookup parityView = new ParityLookup(parityLookUp, session, false);
                                UI.getCurrent().addWindow(parityView);
                                parityView.addCloseListener(new Window.CloseListener() {
                                    /**
                                     * Close listener for windowClose
                                     */
                                    @SuppressWarnings("PMD")
                                    public void windowClose(final Window.CloseEvent e) {
                                        if (parityLookUp.getValue() != null) {
                                            saveFromTableField(itemId, propertyId, parityLookUp.getValue(), false);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return parityLookUp;
                } else if (!String.valueOf(propertyId).contains(Constant.DISABLE) && (String.valueOf(propertyId).contains("Parity") && getBeanFromId(itemId).getLevelName().equals(Constant.PRODUCT_LABEL))) {

                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setData(propertyId);
                    check.setImmediate(true);
                    check.setValue(false);
                    check.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            saveFromTableField(itemId, propertyId, "CheckFlag", check.getValue());
                        }
                    });
                    check.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
                    return check;
                } else if (!ACTION_VIEW.getConstant().equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.DISABLE) && (String.valueOf(propertyId).contains("Methodology")
                        && (getBeanFromId(itemId).getLevelName().equals(Constant.CUSTOMER_SMALL) || getBeanFromId(itemId).getLevelName().equals(Constant.PRODUCT_LABEL)))) {
                    final CustomTextField formulaNo = new CustomTextField();
                    formulaNo.setImmediate(true);
                    formulaNo.addStyleName(Constant.SEARCH_ICON_STYLE);

                    formulaNo.addClickListener(new CustomTextField.ClickListener() {
                        /**
                         * Method used for formulaNo
                         */
                        public void click(final CustomTextField.ClickEvent event) {

                            try {

                                final MethodologyLookUp formulaLookup = new MethodologyLookUp(formulaList);
                                UI.getCurrent().addWindow(formulaLookup);
                                formulaLookup.addCloseListener(new Window.CloseListener() {
                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        if (formulaLookup.getSelectedItem() != null) {
                                            FormulaDTO formulaDTO = formulaLookup.getSelectedItem();
                                            String formulaName = formulaDTO.getFormulaName();

                                            formulaNo.setValue(formulaName);
                                            itemIdValue = itemId;
                                            propertyIdValue = propertyId;
                                            tableLogic.setRefresh(false);

                                            if (!formulaName.equalsIgnoreCase(SELECTMETHODOLOGY.getConstant()) && !formulaName.isEmpty() && !formulaName.equalsIgnoreCase(SELECT_ONE.getConstant())) {
                                                saveFromTableField(itemIdValue, propertyIdValue, formulaName, false);
                                            }
                                            tableLogic.setRefresh(true);
                                        }

                                    }
                                });
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });
                    return formulaNo;

                }
                return null;
            }
        });
        rightTable.setEditable(true);
        if (firstGenerated) {
            tableLogic.setProjectionResultsData(projectionDTO);
        }
    }
    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public DiscountProjectionDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof DiscountProjectionDTO) {
            targetItem = new BeanItem<>(
                    (DiscountProjectionDTO) obj);
        }
        return (DiscountProjectionDTO) targetItem.getBean();
    }

    public void saveFromTableField(Object itemId, Object propertyId, String value, boolean flag) {
        DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
        if ("CheckFlag".equals(value)) {
            dto.addBooleanProperties(propertyId, flag);
        } else {
            dto.addStringProperties(propertyId, value);
        }
        String period[] = supplementalDiscountProjectionLogic.getYearAndPeriod(propertyId);
        dto.setPeriod(Integer.valueOf(period[0]));
        dto.setYear(Integer.valueOf(period[1]));
        dto.setPropertyName(period[NumericConstants.TWO]);
        if (projectionDTO.isIsFilter()) {
            dto.setParentCcpDetailIdList(dto.getCcpDetailIds());
        }

        supplementalDiscountProjectionLogic.saveProjectionValues(dto, propertyId, session, tableLogic);
    }

    public void checkClearAll(boolean checkClear) {

        boolean flag = false;
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            } else {
                flag = true;
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if (!dto.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                        && !dto.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS)) {
                    dto.addBooleanProperties(Constant.CHECK, checkClear);
                    if (flag) {
                        tableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.CHECK).setValue(checkClear);
                    }
                }
            }
            flag = false;
        }

    }

    private void attachValueChangeListener(AbstractField component) {
        component.removeListener(valueChangeListener);
        component.addValueChangeListener(valueChangeListener);
    }

    private void configureListener() {
        valueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                tableLogic.setRefresh(false);
                if (valueChange) {
                    String selectedValue = String.valueOf(event.getProperty().getValue());
                    if (!selectedValue.equalsIgnoreCase(SELECTMETHODOLOGY.getConstant()) && !selectedValue.isEmpty() && !selectedValue.equalsIgnoreCase(SELECT_ONE.getConstant())) {
                        saveFromTableField(itemIdValue, propertyIdValue, selectedValue, false);
                    }
                    valueChange = false;
                }
                tableLogic.setRefresh(true);
            }
        };
    }

    private void detachLisener(AbstractField abstractField) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) abstractField.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            abstractField.removeValueChangeListener(object);
        }
    }

    private void getProductLevelCheckUpdate(SupplementalTableLogic tableLogic, boolean value, DiscountProjectionDTO checkDto) {
        List<Object> dtoList = (List<Object>) ((Container.Indexed) tableLogic.getContainerDataSource()).getItemIds(0, tableLogic.getContainerDataSource().size());
        for (Object obj : dtoList) {
            DiscountProjectionDTO suppDto = (DiscountProjectionDTO) obj;
            if ((suppDto.getSupplementalLevelNo() == 5 && checkDto.getCcpDetailIds().containsAll(suppDto.getCcpDetailIds())) &&  (!suppDto.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                        && !suppDto.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS))) {
                    tableLogic.getContainerDataSource().getContainerProperty(obj, Constant.CHECK).setValue(value);
                }
            }
        }

    private void getCustomerLevelCheckUpdate(SupplementalTableLogic tableLogic, boolean value, DiscountProjectionDTO checkDto) {
        boolean flag = false;
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            } else {
                flag = true;
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if ((!dto.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                        && !dto.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS)) && (checkDto.getCcpDetailIds().containsAll(dto.getCcpDetailIds()))) {
                        dto.addBooleanProperties(Constant.CHECK, value);
                        if (flag) {
                            tableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.CHECK).setValue(value);
                        }
                    }
                }
            flag = false;
        }
    }

    private void uncheckUpperLevel(SupplementalTableLogic tableLogic, boolean value, DiscountProjectionDTO checkDto, int[] levelNo) {
        boolean flag = false;
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            } else {
                flag = true;
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if ((!dto.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                        && !dto.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS)) && ((dto.getSupplementalLevelNo() == levelNo[0] || dto.getSupplementalLevelNo() == levelNo[1]) && dto.getCcpDetailIds().containsAll(checkDto.getCcpDetailIds()))) {
                        dto.addBooleanProperties(Constant.CHECK, value);
                        if (flag) {
                            tableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.CHECK).setValue(value);
                        }
                    }
                }
            flag = false;
        }
    }

    private void getCustomerLevelCheckUpdateForBrandChange(SupplementalTableLogic tableLogic, boolean value, DiscountProjectionDTO checkDto) {
        boolean flag = false;
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            } else {
                flag = true;
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if ((!dto.getLevelName().equalsIgnoreCase(Constant.CONTRACT_SMALL)
                        && !dto.getLevelName().equalsIgnoreCase(AbstractComparisonLookup.THERAPEUTIC_CLASS)) && (dto.getCcpDetailIds().containsAll(checkDto.getCcpDetailIds()))) {
                        dto.addBooleanProperties(Constant.CHECK, value);
                        if (flag) {
                            tableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.CHECK).setValue(value);
                        }
                    }
                }
            flag = false;
        }
    }

    private Set<String> getCheckedRecordsHierarchyNoForMethodolgy() {
        Set<String> finalHirarechyNo = new HashSet<>();
        List<List<String>> relatedCcpDetailsId = new ArrayList<>();
        List<List<String>> relatedCustomerId = new ArrayList<>();
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if ((Boolean) dto.getPropertyValue(Constant.CHECK) && dto.getSupplementalLevelNo() == 1) {
                    finalHirarechyNo.add(tableTreeLevelNo);
                    relatedCustomerId.add(dto.getCcpDetailIds());
                } else if ((Boolean) dto.getPropertyValue(Constant.CHECK) && dto.getSupplementalLevelNo() == 5) {
                    relatedCcpDetailsId.add(dto.getParentCcpDetailIdList());
                    finalHirarechyNo.add(tableTreeLevelNo);
                }
            }
        }
        LOGGER.debug("relatedCustomerId.size()  " + relatedCustomerId.size());
        LOGGER.debug("relatedCcpDetailsId.size()  " + relatedCcpDetailsId.size());
        getRelatedCcpDetails(relatedCustomerId, finalHirarechyNo);
        getRelatedCcpDetails(relatedCcpDetailsId, finalHirarechyNo);
        return finalHirarechyNo;
    }

    private Set<String> getCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                    finalHirarechyNo.add(tableTreeLevelNo);
                }
            }
        }
        return finalHirarechyNo;
    }

    public Set<String> getRelatedCcpDetails(List<List<String>> relatedCcpDetailsId, Set<String> finalHirarechyNo) {
        for (List<String> relatedCustomerId1 : relatedCcpDetailsId) {
            for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
                Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
                if (itemId == null) {
                    itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
                }
                if (itemId != null) {
                    DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                    if (dto.getSupplementalLevelNo() == 1 && relatedCustomerId1.containsAll(dto.getCcpDetailIds())) {
                        finalHirarechyNo.add(tableTreeLevelNo);
                    }
                }
            }
        }
        return finalHirarechyNo;
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        String levelNoString = String.valueOf(value);
        if (isExpand && !isInteger(levelNoString)) {
            levelNoString = "4";
        }
        if (!isExpand && !isInteger(levelNoString)) {
            levelNoString = Constant.DASH;
        }
        int levelNo = Integer.valueOf(levelNoString) == NumericConstants.FOUR ? NumericConstants.THREE : Integer.valueOf(levelNoString);
        if (!isExpand) {
            levelNo--;
        }
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        levelFilterDdlb.setValue(SELECT_ONE);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
        if (tableLogic.getContainerDataSource() != null) { // For GAL-9221,GAL-9219,GAL-9197 server log issues
            tableLogic.clearAll();
            tableLogic.loadExpandData(levelNo);
        }
    }

    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterValueChangeLogic(event);
        }
    };

    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);

        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportPeriodViewTable = new ExtFilterTreeTable();
        resultsTableLayout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(Boolean.FALSE);
        exportPeriodViewTable.setVisible(false);
        exportPeriodViewTable.setContainerDataSource(excelResultBean);

    }

    public static String getColumnName(Object value) {
        if (columnName.size() == 0) {
            columnName.put(METHODOLOGY.getConstant(), "METHODOLOGY");
            columnName.put(CONTRACT_PRICE.getConstant(), "CONTRACT_PRICE");
            columnName.put(DISCOUNT1.getConstant(), "DISCOUNT_RATE_1");
            columnName.put(DISCOUNT2.getConstant(), "DISCOUNT_RATE_2");
            columnName.put(ACCESS.getConstant(), "ACCESS");
            columnName.put(PARITY.getConstant(), "PARITY");
            columnName.put(PARITY_REFERENCE.getConstant(), "PARITY_REFERENCE");
            columnName.put(DISCOUNT_PARITY.getConstant(), "PARITY_DISCOUNT");
        }
        return String.valueOf(columnName.get(value));
    }

    @Override
    public void populateBtnClickLogic() {
        tableLogic.setRefresh(false);
        boolean accessFlag = String.valueOf(fieldDdlb.getValue()).equals(ACCESS.getConstant()) ? true : false;
        boolean methodologyFlag = String.valueOf(fieldDdlb.getValue()).equals(METHODOLOGY.getConstant()) ? true : false;
        boolean optionGroupFlag = String.valueOf(fieldDdlb.getValue()).equals(PARITY.getConstant()) ? true : false;
        boolean parityFlag = String.valueOf(PARITY_REFERENCE.getConstant()).equals(fieldDdlb.getValue()) ? true : false;
        if (fieldDdlb.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Field Selected", "Please select a Field and enter a Value to Mass Update.");
        } else if (methodologyFlag && valueDdlb.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constant.NO_VALUE_SELECTED, "Please select any Methodology to update");
        } else if (accessFlag && valueDdlb.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constant.NO_VALUE_SELECTED, "Please select any value to update");
        } else if (optionGroupFlag && valueOption.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constant.NO_VALUE_SELECTED, "Please select any value to update");
        } else if (!parityFlag && !accessFlag && !methodologyFlag && !optionGroupFlag && (value.getValue() == null || StringUtils.isEmpty(value.getValue()))) {
            AbstractNotificationUtils.getErrorNotification("No Value Entered", "Please enter any value to update");
        } else if (parityFlag && valueLookUp.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Parity Selected", "Please choose any parity to update.");
        } else if (startPeriod.getValue() == null || startPeriod.getValue().equals(SELECT_ONE.getConstant())) {
            AbstractNotificationUtils.getErrorNotification("No Start Period Selected", "You must select a Start Period in order to use the Mass Update tool.");
        } else {
            int i = 0;
            for (DiscountProjectionDTO dto2 : resultBeanContainer.getBeans()) {

                if (((Boolean) dto2.getPropertyValue(Constant.CHECK)) && (dto2.getSupplementalLevelNo() == NumericConstants.FIVE)) {

                        i = i + 1;
                    }
                }

            String selectedValue;

            final int startQuater = Integer.valueOf(startPeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT);

            final int startYear = Integer.valueOf(startPeriod.getValue().toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            int endQuater = 0;
            int endYear = 0;
            if (endPeriod.getValue() == null || SELECT_ONE.getConstant().equals(endPeriod.getValue())) {
                endQuater = Integer.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - NumericConstants.FORTY_EIGHT);
                endYear = Integer.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).substring(NumericConstants.THREE, NumericConstants.SEVEN));
            } else {
                endQuater = Integer.valueOf(endPeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT);
                endYear = Integer.valueOf(endPeriod.getValue().toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            }

            String columnNameforField = getColumnName(fieldDdlb.getValue());
            boolean flag = false;
            if (columnNameforField.equalsIgnoreCase(ACCESS.getConstant()) || columnNameforField.equalsIgnoreCase(METHODOLOGY.getConstant())) {
                selectedValue = String.valueOf(valueDdlb.getValue());
            } else if (columnNameforField.equalsIgnoreCase(PARITY.getConstant())) {
                selectedValue = Constant.YES.equalsIgnoreCase(valueOption.getValue().toString()) ? Constant.STRING_ONE : Constant.DASH;
            } else if (parityFlag) {

                String[] startPeriodList = supplementalDiscountProjectionLogic.getYearAndPeriod(String.valueOf(startQuater), String.valueOf(startYear));
                String[] endPeriodList = supplementalDiscountProjectionLogic.getYearAndPeriod(String.valueOf(endQuater), String.valueOf(endYear));
                String[] finalPeriodList = new String[NumericConstants.TWO];
                finalPeriodList[0] = startPeriodList[0];
                finalPeriodList[1] = endPeriodList[endPeriodList.length - 1];
                final List<List> returnList = supplementalDiscountProjectionLogic.callParityProcedure(dtoListValue, finalPeriodList, session);
                String notifyContent = StringUtils.EMPTY;
                if (!returnList.get(0).isEmpty()) {
                    List<LookUpDTO> insertDtoList = returnList.get(0);
                    for (int z = 0; z < insertDtoList.size(); z++) {
                        LookUpDTO lookUpDto = insertDtoList.get(i);
                        if (z == 0) {
                            dtoItemValue = lookUpDto.getItemNo();
                        } else if (!dtoItemValue.equals(lookUpDto.getItemNo())) {
                            dtoItemValue = Constant.MULTIPLE;
                        }

                    }
                }

                if (returnList.get(1).isEmpty()) {
                    if (!returnList.get(0).isEmpty()) {
                        supplementalDiscountProjectionLogic.insertInParity(returnList.get(0), session.getProjectionId());

                    }
                    valueLookUp.setValue(dtoItemValue);
                    selectedValue = String.valueOf(valueLookUp.getValue());
                } else {
                    String lookUpValue = StringUtils.EMPTY;
                    List<String> emptyNdcList = returnList.get(1);
                    String emptyValue = StringUtils.EMPTY;
                    if (!emptyNdcList.isEmpty()) {
                        for (int k = 0; k < emptyNdcList.size(); k++) {
                            if (k != 0 && !emptyValue.equals(emptyNdcList.get(k))) {
                                lookUpValue += ",";
                            }
                            emptyValue = emptyNdcList.get(k);
                            lookUpValue += emptyNdcList.get(k);
                        }
                    }
                    int quatValue = 0;
                    List<List> emptyQuarList = returnList.get(NumericConstants.TWO);
                    final String[] quaterName = new String[emptyQuarList.size()];

                    if (!emptyQuarList.isEmpty()) {

                        for (int q = 0; q < emptyQuarList.size(); q++) {

                            List<String> lit = (List<String>) emptyQuarList.get(q);
                            String tempValue = StringUtils.EMPTY;
                            for (String le : lit) {
                                tempValue += le + ", ";
                            }

                            quaterName[q] = tempValue;
                            quatValue += 1;

                        }
                    }

                    List<String> ndcValue = returnList.get(NumericConstants.THREE);

                    if (!StringUtils.EMPTY.equals(lookUpValue)) {
                        notifyContent += lookUpValue;
                        notifyContent += ",";
                    }

                    for (int j = 0; j < ndcValue.size(); j++) {

                        notifyContent += j == 0 ? StringUtils.EMPTY : " and";

                        notifyContent += ndcValue.get(j);

                        if (quaterName.length != 0) {
                            notifyContent += " " + quaterName[j];

                            if (quatValue != quaterName.length) {
                                notifyContent += ",";
                            }
                        } else if (quatValue != quaterName.length) {
                            notifyContent += ", ";
                        }
                    }

                    if (returnList.get(0).size() == 0) {
                        AbstractNotificationUtils.getErrorNotification("Populate Error", "In NDC:" + notifyContent + " are not having any prior values");
                    } else {
                        new AbstractNotificationUtils() {
                            public void noMethod() {

                                valueLookUp.setValue(StringUtils.EMPTY);
                            }

                            @Override
                            /**
                             * The method is triggered when Yes button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            public void yesMethod() {
                                try {

                                    supplementalDiscountProjectionLogic.insertInParity(returnList.get(0), session.getProjectionId());
                                    valueLookUp.setValue(dtoItemValue);
                                    allowMethod(String.valueOf(valueLookUp.getValue()));

                                } catch (Exception ex) {
                                   LOGGER.error(ex);
                                }
                            }
                        }.getConfirmationMessage("Submit Confirmation", "In Ndc:" + notifyContent + " are not having any prior values.Do you wish to continue.?");
                        String emptyNull;
                        emptyNull = notifyContent;
                        notifyContent.replaceAll(emptyNull, StringUtils.EMPTY);
                    }

                    selectedValue = String.valueOf(valueLookUp.getValue());
                    flag = true;
                }

            } else {
                String growthString = String.valueOf(value.getValue().toString());
                if (StringUtils.isNotBlank(growthString)) {
                    growthString = growthString.replace(Constant.PERCENT, StringUtils.EMPTY).replace("$", StringUtils.EMPTY).replaceAll(",", StringUtils.EMPTY);
                }
                Double value1 = Double.valueOf(growthString);
                selectedValue = String.valueOf(value1);
            }

            if (!flag) {
                List<DiscountProjectionDTO> checkedDto = new ArrayList<>();

                for (DiscountProjectionDTO dto : resultBeanContainer.getBeans()) {
                    if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                        if (dto.getSupplementalLevelNo() == NumericConstants.FIVE) {
                            checkedDto.add(dto);
                        } else if (dto.getSupplementalLevelNo() == 1 && String.valueOf(fieldDdlb.getValue()).equals(METHODOLOGY.getConstant())) {
                            checkedDto.add(dto);
                        } else if (dto.getSupplementalLevelNo() == NumericConstants.FOUR && String.valueOf(fieldDdlb.getValue()).equals(ACCESS.getConstant())) {
                            checkedDto.add(dto);
                        }
                    }
                }

                if (!checkedDto.isEmpty()) {
                    boolean checkFlag = false;
                    for (DiscountProjectionDTO supplementalDiscountDTO : checkedDto) {
                        tableLogic.setRefresh(false);
                        supplementalDiscountDTO.setStartYear(startYear);
                        supplementalDiscountDTO.setStartPeriod(startQuater);
                        supplementalDiscountDTO.setEndYear(endYear);
                        supplementalDiscountDTO.setEndPeriod(endQuater);
                        supplementalDiscountProjectionLogic.populateValues(supplementalDiscountDTO, selectedValue, columnName, session);
                        tableLogic.setRefresh(true);
                        if (columnName.equals(METHODOLOGY.getConstant().toUpperCase())) {
                            checkFlag = true;
                        }
                    }
                    if (checkFlag) {
                        refreshTableData(getCheckedRecordsHierarchyNoForMethodolgy());
                    } else {
                        refreshTableData(getCheckedRecordsHierarchyNo());
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification("Populate Warning", "Please check valid selection");
                }
            }
        }
        tableLogic.setRefresh(true);
    }

    public void refreshTableData(Set<String> finalHirarechyNo) {
        tableLogic.setHierarchyToRefresh(finalHirarechyNo);
        tableLogic.setCurrentPage(tableLogic.getCurrentPage());
    }

    public void allowMethod(final String selectedValue) {
        List<DiscountProjectionDTO> checkedDto = new ArrayList<>();
        for (DiscountProjectionDTO dto : resultBeanContainer.getBeans()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                if (dto.getSupplementalLevelNo() == NumericConstants.FIVE) {
                    checkedDto.add(dto);
                } else if (dto.getSupplementalLevelNo() == 1 && String.valueOf(fieldDdlb.getValue()).equals(METHODOLOGY.getConstant())) {
                    checkedDto.add(dto);
                } else if (dto.getSupplementalLevelNo() == NumericConstants.FOUR && String.valueOf(fieldDdlb.getValue()).equals(ACCESS.getConstant())) {
                    checkedDto.add(dto);
                }
            }
        }

        if (!checkedDto.isEmpty()) {
            for (DiscountProjectionDTO supplementalDiscountDTO : checkedDto) {
                supplementalDiscountProjectionLogic.populateValues(supplementalDiscountDTO, selectedValue, columnName, session);
            }
            tableLogic.setProjectionResultsData(projectionDTO);
        } else {
            AbstractNotificationUtils.getErrorNotification("Populate Warning", "Please check valid selection");
        }
    }

    @Override
    protected void viewValueChangeLogic() {
        return;
    }

    @Override
    protected void adjProgramsValueChangeLogic(String adjustmentProgram) {
        return;
    }

    @Override
    protected void adjPeriodValueChangeLogic(String adjustmentPeriods) {
        return;
    }

    @Override
    protected void calculateBtnClickLogic() {
        return;
    }

    @Override
    protected void newBtnClickLogic() {
        return;
    }

    @Override
    protected void editBtnClickLogic() {
        return;
    }

    @Override
    protected void adjustBtnClickLogic() {
        return;
    }

    @Override
    protected void excelExportClickLogic() {
        configureExcelResultTable();

        tableLogic.setRefresh(false);
        loadExcelResultTable(NumericConstants.TWO, StringUtils.EMPTY);
        tableLogic.setRefresh(true);
        exportPeriodViewTable.setRefresh(Boolean.TRUE);
        exportPeriodViewTable.setDoubleHeaderVisible(true);
        ForecastUI.EXCEL_CLOSE = true;
        ExcelExport exp = null;
        int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
        for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
            exportPeriodViewTable.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
            Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
            exportPeriodViewTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
            exportPeriodViewTable
                    .setDoubleHeaderVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.THREE)).toArray());
            Object[] doubleHeader = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FOUR)).toArray();
            exportPeriodViewTable
                    .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));

            exportPeriodViewTable.setDoubleHeaderMap((Map<Object, Object[]>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FIVE));
            exportPeriodViewTable.setRefresh(true);
            String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
            ForecastUI.EXCEL_CLOSE = true;
            if (i == 0) {
                exp = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), sheetName, Constant.SUPPLEMENTAL_DISCOUNT, "Supplemental_Discount_Projection.xls", false);
            } else {
                exp.setNextTableHolder(new ExtCustomTableHolder(exportPeriodViewTable), sheetName);
            }
            if (i == exportAt) {
                exp.exportMultipleTabs(true);
            } else {
                exp.exportMultipleTabs(false);
            }
        }
        resultsTableLayout.removeComponent(exportPeriodViewTable);
    }

    @Override
    protected void levelFilterValueChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug("levelFilterDdlbChangeOption inititated " + levelFilterDdlb.getValue());
        tableLogic.setRefresh(false);
        if (SELECT_ONE.equals(levelFilterDdlb.getValue()) || levelFilterDdlb.getValue() == null) {
            projectionDTO.setIsFilter(false);
            projectionDTO.setCustomFlag(true);
            tableLogic.clearAll();
            loadResultTable(0, StringUtils.EMPTY);
        } else {

            level.setValue(SELECT_ONE);
            tableLogic.clearAll();
            projectionDTO.setIsFilter(true);
            projectionDTO.setSupplementalLevelNo(Integer.valueOf(levelFilterDdlb.getValue().toString()));
            tableLogic.setProjectionResultsData(projectionDTO);

        }
        tableLogic.setRefresh(true);
        LOGGER.debug("levelFilterDdlbChangeOption ends");
    }

    private void loadExcelResultTable(int levelNo, String hierarchyNo) {
        excelResultBean.removeAllItems();
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setExcelFlag(true);
        int count = supplementalDiscountProjectionLogic.getConfiguredSupplementalDiscountCount(new Object(), projectionDTO);
        List<DiscountProjectionDTO> resultList = supplementalDiscountProjectionLogic.getConfiguredSupplementalDiscount(new Object(), 0, count, projectionDTO);

        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<DiscountProjectionDTO> resultList, Object parentId) {
        for (DiscountProjectionDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        projectionDTO.setFilterLevelNo(0);
        projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = supplementalDiscountProjectionLogic.getConfiguredSupplementalDiscountCount(id, projectionDTO);
        List<DiscountProjectionDTO> resultList = supplementalDiscountProjectionLogic.getConfiguredSupplementalDiscount(id, 0, count, projectionDTO);
        loadDataToContainer(resultList, id);
    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setLevelFlag(false);
        projectionDTO.setSupplementalLevelNo(1);
        projectionDTO.setLevelNo(NumericConstants.FOUR);
        tableLogic.setProjectionResultsData(projectionDTO);
    }

    @Override
    protected void resetBtnClickLogic() {
        Object[] itemIds = variablesForMandated.getItemIds().toArray();
        for (Object itemId : itemIds) {
            variablesForMandated.unselect(itemId);
        }
        selectDefault();
    }

    @Override
    protected void expandBtnClickLogic() {
        expandCollapseLevelOption(true, levelDdlb.getValue());
    }

    @Override
    protected void collapseBtnClickLogic() {
        expandCollapseLevelOption(false, levelDdlb.getValue());
    }

    @Override
    protected void massCheckValueChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(" massUpdate ValueChangeEvent initiated ");
        if (Constant.LabelConstants.DISABLE.equals(massCheck.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.debug("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Enable or disable.
     *
     * @param value the value
     */
    public void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        this.value.setEnabled(value);
        valueDdlb.setEnabled(value);
        valueLookUp.setEnabled(value);
        valueOption.setEnabled(value);
        startPeriod.setEnabled(value);
        endPeriod.setEnabled(value);
        populateBtn.setEnabled(value);
    }

    @Override
    protected void startPeriodValueChangeLogic(Property.ValueChangeEvent event) {
        endPeriodBean.removeAllItems();
        endPeriodBean.addAll(projectionDTO.getPeriodList());

        if (startPeriod.getValue() != null) {
            String startPeriodValue = String.valueOf(startPeriod.getValue());
            Object[] itemIds = startPeriod.getItemIds().toArray();
            for (Object itemId : itemIds) {
                if (!startPeriodValue.equals(String.valueOf(itemId))) {
                    endPeriodBean.removeItem(itemId);
                } else {
                    break;
                }
            }
        }
    }

    public void saveSDP() {
        boolean success = supplementalDiscountProjectionLogic.procedureCheck(session);
        if (success) {
            try {
                Map map = new HashMap();
                map.put(Constant.VARIABLES, variablesForMandated.getValue());
                commonLogic.saveProjectionSelectionMandatedDiscountProjection(map, session.getProjectionId(), Constant.SUPPLEMENTAL_DISCOUNT_PROJECTION);
                supplementalDiscountProjectionLogic.supplementalSave(session);
            } catch (Exception ex) {

                LOGGER.error(ex);
            }
        } else {
            try {
                Object[] orderedArgs = {session.getProjectionId(), session.getMarketTypeValue(), session.getUserId(), session.getSessionId()};
                CommonLogic.callProcedureforUpdate(Constant.SUPPLEMENTAL_INSERT_PRC, orderedArgs);
                Map map = new HashMap();
                map.put(Constant.VARIABLES, variablesForMandated.getValue());
                commonLogic.saveProjectionSelectionMandatedDiscountProjection(map, session.getProjectionId(), Constant.SUPPLEMENTAL_DISCOUNT_PROJECTION);
                supplementalDiscountProjectionLogic.supplementalSave(session);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }

    /**
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.SUPPLEMENTAL_DISCOUNT);
            if (functionPsHM.get(FunctionNameUtil.GENERATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag()) {
                generateBtn.setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.EXPAND) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.EXPAND)).isFunctionFlag()) {
                expandBtn.setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.COLLAPSE_BTN) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.COLLAPSE_BTN)).isFunctionFlag()) {
                collapseBtn.setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.POPULATE_BTN) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.POPULATE_BTN)).isFunctionFlag()) {
                populateBtn.setVisible(Boolean.FALSE);
            }
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(MSupplementalDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(MSupplementalDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void customDdlbLogic() {
        return;
    }

    /**
     * To get the Submit flag
     *
     * @return
     */
    public boolean getSubmitFlag() {
        if (!resultBeanContainer.getItemIds().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDefaultAccess() {
        variablesForMandated.focus();

    }

    @Override
    protected void resetBtnForTableLogic() {
        return;
    }

    @Override
    protected void fieldDdlbValueChangeLogic(Property.ValueChangeEvent event) {
        return;
    }

    public void configureScreen() {
        if (canLoad) {
            configureFieldsForSupplemental();
            configureListener();
            setButtonSecurity();
            canLoad = false;
        }
    }

    @Override
    protected void customCalculateBtnClickLogic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void adjustBtnClickLogicCustom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
