/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import static com.stpl.app.gcm.itemmanagement.add.form.AddContractSelection.loadPriceType;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.ComponentInfoTableLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class AbstractComponentInfo extends CustomComponent {

    @UiField("componentSelection")
    private ComboBox componentSelection;
    @UiField("labelId")
    private Label labelId;
    @UiField("componentId")
    private TextField componentId;
    @UiField("labelNo")
    private Label labelNo;
    @UiField("componentNo")
    private TextField componentNo;
    @UiField("labelName")
    private Label labelName;
    @UiField("componentName")
    private TextField componentName;
    @UiField("labelStatus")
    private Label labelStatus;
    @UiField("componentStatus")
    private TextField componentStatus;
    @UiField("labelType")
    private Label labelType;
    @UiField("componentType")
    private TextField componentType;
    
    @UiField("compStartDate")
    private PopupDateField compStartDate;
    
    @UiField("compEndDate")
    private PopupDateField compEndDate;
    @UiField("labelRebateFreq")
    private Label labelRebateFreq;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("labelBasis")
    private Label labelBasis;
    @UiField("basis")
    private TextField basis;
    @UiField("labelRsType")
    private Label labelRsType;
    @UiField("rsType")
    protected ComboBox rsType_DTO;
    @UiField("lableRsProgramType")
    protected Label lableRsProgramType;
    @UiField("rsProgramType")
    protected ComboBox rsProgramType_DTO;
    @UiField("lableRsCategory")
    protected Label lableRsCategory;
    @UiField("rsCategory")
    protected ComboBox rsCategory_DTO;
    @UiField("lablePaymentFreq")
    private Label lablePaymentFreq;
    @UiField("paymentFrequency")
    protected ComboBox paymentFrequency_DTO;
    @UiField("lableRebatePlanLevel")
    private Label lableRebatePlanLevel;
    @UiField("rebatePlanLevel")
    protected ComboBox rebatePlanLevel_DTO;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("itemSearchGrid")
    private GridLayout itemSearchGrid;
    @UiField("exportBtn")
    private Button exportBtn;
    protected final TextField rsTypeText = new TextField();
    protected final TextField rsProgramTypeText = new TextField();
    protected final TextField rsCategoryText = new TextField();
    protected final TextField paymentFrequencyText = new TextField();
    protected final TextField rebatePlanLevelText = new TextField();
    private final ComponentInfoTableLogic tablelogic = new ComponentInfoTableLogic();
    private final ExtPagedTable currentComponentTable = new ExtPagedTable(tablelogic);
    private final BeanItemContainer<ComponentInfoDTO> searchContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private String layoutIndicator = new String();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private final Object[] ifpCol = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, Constants.ATTACHED_DATE_PROPERTY};
    private final String[] ifpHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.ATTACHED_DATE_FIELD};
    private final Object[] cfpCol = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, "itemStatus", Constants.START_DATE, Constants.END_DATE, Constants.STATUS_S, "tradeClass", Constants.ATTACHED_DATE_PROPERTY};
    private final String[] cfpHeader = {Constants.COMPANYNO, Constants.COMPANYNAME, "Company status", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.TRADECLASS, Constants.ATTACHED_DATE_FIELD};

    private final Object[] psCol = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY,
        Constants.PRICE_PROTECTION_STATUS_PROPERTY, Constants.PRICE_PROTECTION_START_DATE_PROPERTY, Constants.PRICE_PROTECTION_END_DATE_PROPERTY,
        Constants.MEASUREMENT_PRICE_PROPERTY, Constants.NEP_PROPERTY, Constants.NEP_FORMULA_PROPERTY, Constants.BASE_PRICE_PROPERTY, Constants.BASELINE_WAC_PROPERTY,
        Constants.BASELINE_NET_WAC_PROPERTY, Constants.NET_BASELINE_WAC_FORMULA_PROPERTY, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY,
        Constants.PRICE_TOLERANCE_INTERVAL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY, Constants.PRICE_TOLERANCE_TYPE_PROPERTY, Constants.PRICE_TOLERANCE_PROPERTY,
        Constants.MAX_INCREMENTAL_CHANGE_PROPERTY, Constants.RESET_ELIGIBLE_PROPERTY, Constants.RESET_TYPE_PROPERTY, Constants.RESET_DATE_PROPERTY, Constants.RESET_INTERVAL_PROPERTY, Constants.RESET_FREQUENCY_PROPERTY,
        Constants.RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_FORMULA_PROPERTY, Constants.NET_PRICE_TYPE_PROPERTY, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY, Constants.ATTACHED_DATE_PROPERTY};

    private final String[] psHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND,
        Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_LABEL,
        Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.NEP_LABLE_NAME, Constants.NEP_FORMULA_LABLE_NAME, Constants.BASE_PRICE_TYPE_LABLE_NAME, Constants.BASELINE_WAC_LABLE_NAME,
        Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME,
        Constants.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_LABEL,
        Constants.MAX_INCREMENTAL_CHANGE_LABEL, Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_TYPE_LABEL, Constants.RESET_DATE_LABEL, Constants.RESET_INTERVAL_LABEL, Constants.RESET_FREQUENCY_LABEL,
        Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME, Constants.NET_PRICE_TYPE_LABLE_NAME, Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME, Constants.ATTACHED_DATE_FIELD};


    public final Object[] rsCol = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", Constants.ATTACHED_DATE_PROPERTY};
    public final String[] rsHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Formula Type", "Formula ID", "Formula Name", "RebatePlan ID", "RebatePlan Name", "Rebate Amount", "Bundle No", Constants.ATTACHED_DATE_FIELD};
    public final ComponentInfoDTO binderDto = new ComponentInfoDTO();
    public static final String FILTERBAR = "filterbar";
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private SelectionDTO selection;
    public final AbstractLogic abstractLogic = AbstractLogic.getInstance();
    private String componentFlag = StringUtils.EMPTY;
    private BeanItemContainer<ComponentInfoDTO> contractExcelResultBean = new BeanItemContainer<>(ComponentInfoDTO.class);
    private ExtCustomTable contractExcelTable;
    private final VerticalLayout contractDashboardLay = new VerticalLayout();
    private ComponentInfoDTO componentDto = new ComponentInfoDTO();
    private final AbstractLogic logic = AbstractLogic.getInstance();

    public AbstractComponentInfo(final String indicator, final SelectionDTO selection) {
        this.selection = selection;
        getComponentInformation(indicator);
    }

    public void getComponentInformation(final String layoutIndicator) {
        this.layoutIndicator = layoutIndicator;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/item/abstractComponentInfo.xml"), this));
        HorizontalLayout controls = tablelogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(addResultsToTable());
        tableLayout.addComponent(controlLayout);
        getBinder();
        configureFields();
    }

    Component addResultsToTable() {
        tablelogic.setContainerDataSource(searchContainer);
        tablelogic.setPageLength(NumericConstants.FIVE);
        tablelogic.sinkItemPerPageWithPageLength(false);
        currentComponentTable.setVisibleColumns(rsCol);
        currentComponentTable.setColumnHeaders(rsHeader);
        currentComponentTable.markAsDirty();
        currentComponentTable.setSelectable(false);
        currentComponentTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        return currentComponentTable;
    }

    public void configureFields() {
        componentSelection.addItem(ConstantsUtils.SELECT_ONE);
        componentSelection.addItem(Constants.CFP);
        componentSelection.addItem(Constants.IFP);
        componentSelection.addItem(Constants.PS);
        componentSelection.addItem(Constants.RS);
        componentSelection.setNullSelectionAllowed(true);
        componentSelection.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        setLabelCaption(layoutIndicator);
        compEndDate.setWidthUndefined();
        compEndDate.setWidth("180");
        compEndDate.setWidth("180");
        rsTypeText.setWidth("180");
        rsProgramTypeText.setWidth("180");
        rsCategoryText.setWidth("180");
        paymentFrequencyText.setWidth("180");
        rebatePlanLevelText.setWidth("180");
        exportBtn.setIcon(excelExportImage, "Excel Export");
        loadRSDdlb();
        compStartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
        compEndDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
    }

    @UiHandler("componentSelection")
    public void componentChangeListener(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        Object value = componentSelection.getValue();
        if (value != null) {
            loadData(String.valueOf(value));
        } else {
            resetField();
        }
        for (Object object : currentComponentTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                currentComponentTable.setConverter(object, new DateToStringConverter());
            }
        }
    }

    public void loadData(String value) throws FieldGroup.CommitException {
        if (value.equals(Constants.PS)) {
            currentComponentTable.setVisibleColumns(psCol);
            currentComponentTable.setColumnHeaders(psHeader);
            currentComponentTable.addStyleName(FILTERBAR);
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setPageLength(NumericConstants.FIVE);
            for (Object object : currentComponentTable.getVisibleColumns()) {
                if (String.valueOf(object).contains("Date")) {
                    currentComponentTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                    currentComponentTable.setConverter(object, new DateToStringConverter());
                }
            }

            for (Object propertyId : currentComponentTable.getVisibleColumns()) {
                currentComponentTable.setColumnWidth(propertyId, -1);
            }
            setVisibleComponents(value);
            setLabelCaption(Constants.PS);
            binderDto.setSystemId(selection.getPsContractSid());
            setFlags();
            loadTextFields(selection.getPsContractSid());
        } else if (value.equals(Constants.IFP)) {
            currentComponentTable.setVisibleColumns(ifpCol);
            currentComponentTable.setColumnHeaders(ifpHeader);
            currentComponentTable.addStyleName(FILTERBAR);
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setPageLength(NumericConstants.FIVE);
            for (Object object : currentComponentTable.getVisibleColumns()) {
                if (String.valueOf(object).contains("Date")) {
                    currentComponentTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                    currentComponentTable.setConverter(object, new DateToStringConverter());
                }
            }

            for (Object propertyId : currentComponentTable.getVisibleColumns()) {
                currentComponentTable.setColumnWidth(propertyId, -1);
            }
            setVisibleComponents(value);
            setLabelCaption(Constants.IFP);
            binderDto.setSystemId(selection.getIfpConteractSid());
            setFlags();
            loadTextFields(selection.getIfpConteractSid());
        } else if (value.equals(Constants.CFP)) {
            currentComponentTable.setVisibleColumns(cfpCol);
            currentComponentTable.setColumnHeaders(cfpHeader);
            currentComponentTable.addStyleName(FILTERBAR);
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setPageLength(NumericConstants.FIVE);
            for (Object object : currentComponentTable.getVisibleColumns()) {
                if (String.valueOf(object).contains("Date")) {
                    currentComponentTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                    currentComponentTable.setConverter(object, new DateToStringConverter());
                }
                currentComponentTable.setColumnWidth(object, -1);
            }
            setVisibleComponents(value);
            setLabelCaption(Constants.CFP);
            binderDto.setSystemId(selection.getCfpContractSid());
            setFlags();
            loadTextFields(selection.getCfpContractSid());
        } else if (value.equals(Constants.RS)) {
            currentComponentTable.setVisibleColumns(rsCol);
            currentComponentTable.setColumnHeaders(rsHeader);
            currentComponentTable.addStyleName(FILTERBAR);
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setPageLength(NumericConstants.FIVE);

            for (Object object : currentComponentTable.getVisibleColumns()) {
                if (String.valueOf(object).contains("Date")) {
                    currentComponentTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                    currentComponentTable.setConverter(object, new DateToStringConverter());
                }
            }
            for (Object propertyId : currentComponentTable.getVisibleColumns()) {
                currentComponentTable.setColumnWidth(propertyId, -1);
            }
            setVisibleComponents(value);
            setLabelCaption(Constants.RS);
            binderDto.setSystemId(selection.getRsContractSid());
            loadRSDdlb();
            setFlags();
            loadTextFields(selection.getRsContractSid());

        }

    }

    public void removeFilter() {
        tablelogic.getFilters().clear();
        tablelogic.removeAllContainerFilters();
        tablelogic.clearAll();
    }

    public void setFilter() {
        currentComponentTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        Object id = originatingField.getValue();
                        if (originatingField.getValue() instanceof HelperDTO) {
                            HelperDTO dto = (HelperDTO) originatingField.getValue();
                            id = dto.getId();
                        }
                        return new SimpleStringFilter(propertyId, String.valueOf(id), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
               
                if (componentSelection.getValue() != null && !"null".equals(String.valueOf(componentSelection.getValue()))) {
                    if (componentSelection.getValue().equals(Constants.CFP) && (Constants.STATUS_S.equals(propertyId) || "itemStatus".equals(propertyId))) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, StringConstantsUtil.LOAD_ITEM_STATUS_COUNT, StringConstantsUtil.LOAD_ITEM_STATUS, true);
                            return status;
                    }

                    if (componentSelection.getValue().equals(Constants.IFP)) {
                        if (Constants.STATUS_S.equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, StringConstantsUtil.LOAD_ITEM_STATUS_COUNT, StringConstantsUtil.LOAD_ITEM_STATUS, true);
                            return status;
                        }
                        if (Constants.BRAND_PROPERTY.equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, StringConstantsUtil.LOAD_BRAND_COUNT, StringConstantsUtil.LOAD_BRAND, true);
                            return brandDdlb;
                        }
                    }

                    if (componentSelection.getValue().equals(Constants.PS)) {
                        if (Constants.BRAND_PROPERTY.equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, StringConstantsUtil.LOAD_BRAND_COUNT, StringConstantsUtil.LOAD_BRAND, true);
                            return brandDdlb;
                        }
                        if (Constants.PRICE_PROTECTION_STATUS_PROPERTY.equals(propertyId)) {
                            ComboBox priceProtectionDdlb = new ComboBox();
                            CommonUtil.getComboBoxByListName(priceProtectionDdlb, UiUtils.STATUS, true);
                            return priceProtectionDdlb;
                        }
                        if (Constants.BASE_PRICE_PROPERTY.equals(propertyId)) {
                            ComboBox basePriceType = new ComboBox();
                            CommonUtil.getComboBoxByListName(basePriceType, Constants.BASE_PRICE_TYPE_COLUMN_NAME, true);
                            return basePriceType;
                        }
                        if (Constants.PRICE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            loadPriceType(comboBox, true);
                            return comboBox;
                        }
                        if (Constants.MEASUREMENT_PRICE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            loadPriceType(comboBox, true);
                            return comboBox;
                        }
                        if (Constants.RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            loadPriceType(comboBox, true);
                            return comboBox;
                        }
                        if (Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            loadPriceType(comboBox, true);
                            return comboBox;
                        }
                        if (Constants.RESET_ELIGIBLE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                            return comboBox;
                        }
                        if (Constants.RESET_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.RESET_TYPE_COLUMN_NAME, true);
                            return comboBox;
                        }
                        if (Constants.RESET_INTERVAL_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, true);
                            return comboBox;
                        }
                        if (Constants.RESET_FREQUENCY_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, true);
                            return comboBox;
                        }
                        if (Constants.NET_RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                            return comboBox;
                        }
                        if (Constants.NET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                            return comboBox;
                        }
                        if (Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                            return comboBox;
                        }
                        if (Constants.BASELINE_NET_WAC_PROPERTY.equals(propertyId)) {
                            ComboBox comboBox = new ComboBox();
                            CommonUtil.getComboBoxByListName(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                            return comboBox;
                        }
                        if (Constants.PRICE_TOLERANCE_INTERVAL.equals(propertyId)) {
                            ComboBox pricetolerenceintDdlb = new ComboBox();
                            logic.LazyLoadDdlb(pricetolerenceintDdlb, "Load PS_INTERVAL Count", "Load PS_INTERVAL", true);
                            return pricetolerenceintDdlb;
                        }
                        if (Constants.PRICE_TOLERANCE_TYPE_PROPERTY.equals(propertyId)) {
                            ComboBox pricetolerencetypeDdlb = new ComboBox();
                            logic.LazyLoadDdlb(pricetolerencetypeDdlb, "Load PS_TYPE Count", "Load PS_TYPE", true);
                            return pricetolerencetypeDdlb;
                        }
                        if (Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY.equals(propertyId)) {
                            ComboBox pricetolerencefreqDdlb = new ComboBox();
                            logic.LazyLoadDdlb(pricetolerencefreqDdlb, "Load PS_FREQ Count", "Load PS_FREQ", true);
                            return pricetolerencefreqDdlb;
                        }
                    }

                    if (componentSelection.getValue().equals(Constants.RS)) {

                        if (Constants.BRAND_PROPERTY.equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, StringConstantsUtil.LOAD_BRAND_COUNT, StringConstantsUtil.LOAD_BRAND, true);
                            return brandDdlb;
                        }
                        if (Constants.STATUS_S.equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, StringConstantsUtil.LOAD_ITEM_STATUS_COUNT, StringConstantsUtil.LOAD_ITEM_STATUS, true);
                            return status;
                        }
                    }
                }
                return null;
            }
        });
        currentComponentTable.setFilterBarVisible(true);
        currentComponentTable.setFilterDecorator(new ExtDemoFilterDecorator());

    }

    public void setFlags() throws FieldGroup.CommitException {
        selection.setComponent(layoutIndicator);
        if (layoutIndicator.equalsIgnoreCase(Constants.CFP)) {
            selection.setComponentCount("CFP count");
            selection.setComponentLoad("CFP query");
            componentFlag = "CFP text";
        } else if (layoutIndicator.equalsIgnoreCase(Constants.IFP)) {
            selection.setComponentCount("IFP count");
            selection.setComponentLoad("IFP query");
            componentFlag = "IFP text";
        } else if (layoutIndicator.equalsIgnoreCase(Constants.PS)) {
            selection.setComponentCount("PS count");
            selection.setComponentLoad("PS query");
            componentFlag = "PS text";
        } else {
            selection.setComponentCount("RS count");
            selection.setComponentLoad("RS query");
            componentFlag = "RS text";
        }
        binder.commit();
        tablelogic.clearAll();
        tablelogic.getFilters().clear();
        tablelogic.removeAllContainerFilters();
        binderDto.setReset(false);
        tablelogic.loadSetData(binderDto, selection);
    }

    public void setVisibleComponents(final Object value) {
        if (!value.equals(Constants.RS)) {
            rebateFrequency.setVisible(false);
            basis.setVisible(false);
            rsType_DTO.setVisible(false);
            rsProgramType_DTO.setVisible(false);
            rsCategory_DTO.setVisible(false);
            paymentFrequency_DTO.setVisible(false);
            rebatePlanLevel_DTO.setVisible(false);
            rsTypeText.setVisible(false);
            rsProgramTypeText.setVisible(false);
            rsCategoryText.setVisible(false);
            paymentFrequencyText.setVisible(false);
            rebatePlanLevelText.setVisible(false);
            labelRebateFreq.setVisible(false);
            labelBasis.setVisible(false);
            labelRsType.setVisible(false);
            lableRsProgramType.setVisible(false);
            lableRsCategory.setVisible(false);
            lablePaymentFreq.setVisible(false);
            lableRebatePlanLevel.setVisible(false);
            labelType.setVisible(false);
            componentType.setVisible(false);
        } else {
            rebateFrequency.setVisible(true);
            basis.setVisible(true);
            rsType_DTO.setVisible(true);
            rsProgramType_DTO.setVisible(true);
            rsCategory_DTO.setVisible(true);
            paymentFrequency_DTO.setVisible(true);
            rebatePlanLevel_DTO.setVisible(true);
            rsTypeText.setVisible(true);
            rsProgramTypeText.setVisible(true);
            rsCategoryText.setVisible(true);
            paymentFrequencyText.setVisible(true);
            rebatePlanLevelText.setVisible(true);
            labelRebateFreq.setVisible(true);
            labelBasis.setVisible(true);
            labelRsType.setVisible(true);
            lableRsProgramType.setVisible(true);
            lableRsCategory.setVisible(true);
            lablePaymentFreq.setVisible(true);
            lableRebatePlanLevel.setVisible(true);
            labelType.setVisible(true);
            componentType.setVisible(true);
        }

    }

    public void setLabelCaption(final String label) {
        layoutIndicator = label;
        labelId.setCaption(label + " ID:");
        labelNo.setCaption(label + " Number:");
        labelName.setCaption(label + " Name:");
        labelStatus.setCaption(label + " Status:");
        labelType.setCaption("RAR Type:");
    }

    private void loadTextFields(Integer id) {
        ComponentInfoDTO dto = abstractLogic.getComponentTextFields(componentFlag,id,selection.isIsItemAddTab());
        setComponentDto(dto);
        setReadOnlyComponents(Boolean.FALSE);
        componentId.setValue(dto.getComponenId());
        componentNo.setValue(dto.getComponenNumber());
        componentName.setValue(dto.getComponenName());
        componentType.setValue(dto.getComponentType());
        componentStatus.setValue(dto.getComponentStatus());
        compStartDate.setValue(dto.getComponentStartDate());
        compEndDate.setValue(dto.getComponentEndDate());
        if (componentFlag.equals("RS text")) {
            rsTypeText.setValue(dto.getRsType_Value());
            rsProgramTypeText.setValue(dto.getRsProgramType_Value());
            rsCategoryText.setValue(dto.getRsCategory_Value());
            paymentFrequencyText.setValue(dto.getPaymentFrequency_Value());
            rebatePlanLevelText.setValue(dto.getRebatePlanLevel_Value());
        }
        setReadOnlyComponents(Boolean.TRUE);
    }

    private void resetField() {
        currentComponentTable.resetFilters();
        setReadOnlyComponents(Boolean.FALSE);
        componentId.setValue(StringUtils.EMPTY);
        componentNo.setValue(StringUtils.EMPTY);
        componentName.setValue(StringUtils.EMPTY);
        componentType.setValue(StringUtils.EMPTY);
        componentStatus.setValue(StringUtils.EMPTY);
        compStartDate.setValue(null);
        compEndDate.setValue(null);
        rsTypeText.setValue(StringUtils.EMPTY);
        rsProgramTypeText.setValue(StringUtils.EMPTY);
        rsCategoryText.setValue(StringUtils.EMPTY);
        paymentFrequencyText.setValue(StringUtils.EMPTY);
        rebatePlanLevelText.setValue(StringUtils.EMPTY);
        setReadOnlyComponents(Boolean.TRUE);
        binderDto.setReset(true);
        tablelogic.loadSetData(binderDto, selection);
    }

    /**
     * load All RS ddlbs
     */
    private void loadRSDdlb() {
        try {
            loadRsType();
            loadRsCategory();
            loadRptype();
            loadPaymentFrequency();
            loadRPLevel();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * loadRsType
     */
    private void loadRsType() {

        abstractLogic.loadComboBox(rsType_DTO, "RS_TYPE", false);

    }

    /**
     * loadRsCategory
     */
    private void loadRsCategory() {
        abstractLogic.loadComboBox(rsCategory_DTO, "RS_CATEGORY", false);
    }

    /**
     * loadRptype
     */
    private void loadRptype() {
        abstractLogic.loadComboBox(rsProgramType_DTO, "REBATE_PROGRAM_TYPE", false);
    }

    /**
     * loadPaymentFrequency
     */
    private void loadPaymentFrequency() {
        abstractLogic.LazyLoadDdlb(paymentFrequency_DTO, "LoadPaymentFreqCount", "LoadPaymentFreq", false);
    }

    /**
     * loadRPLevel
     */
    private void loadRPLevel() {
        abstractLogic.LazyLoadDdlb(rebatePlanLevel_DTO, "LoadRPLevelCount", "LoadRPLevel", false);
    }

    public void fireComponentListener(final String value, final SelectionDTO selection) {
        this.selection = selection;
        componentSelection.setValue(value);
    }

    public void setReadOnlyComponents(Boolean flag) {
        componentId.setReadOnly(flag);
        componentNo.setReadOnly(flag);
        componentName.setReadOnly(flag);
        componentType.setReadOnly(flag);
        componentStatus.setReadOnly(flag);
        compStartDate.setReadOnly(flag);
        compEndDate.setReadOnly(flag);
        rebateFrequency.setReadOnly(flag);
        basis.setReadOnly(flag);
        basis.setReadOnly(flag);
        rsTypeText.setReadOnly(flag);
        rsProgramTypeText.setReadOnly(flag);
        rsCategoryText.setReadOnly(flag);
        paymentFrequencyText.setReadOnly(flag);
        rebatePlanLevelText.setReadOnly(flag);
    }

    /**
     * get Binder
     *
     * @return ErrorfulFieldGroup
     */
    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("rsType")
    public void rsTypeDdlbChange(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
    }

    @UiHandler("rsProgramType")
    public void rsProgramCategoryDdlbChange(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
    }

    @UiHandler("rsCategory")
    public void rsCategoryDdlbChange(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
    }

    @UiHandler("paymentFrequency")
    public void paymentDdlbChange(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
    }

    @UiHandler("rebatePlanLevel")
    public void RPLevelDdlbChange(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
    }
    @UiHandler("exportBtn")
    public void exportButton(Button.ClickEvent event) {
        try {
            if (searchContainer.size() > 0) {
                createWorkSheet("Component_Information", currentComponentTable);
            }

        } catch (Exception e) {
             LOGGER.error("",e);
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws  NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount = 0;
        if (resultTable.size() != 0) {
            binderDto.setIsCount(false);
            selection.setFilters(tablelogic.getFilters());
            recordCount = logic.getComponentInfoCount(binderDto, selection);
        }
        ExcelExportforBB.createWorkSheet(currentComponentTable.getColumnHeaders(), recordCount, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
                binderDto.setStartIndex(start);
                binderDto.setEndIndex(end);
                binderDto.setIsCount(true);
                selection.setFilters(tablelogic.getFilters());
                List<ComponentInfoDTO> resultList = logic.getComponentInfoResults(binderDto, selection);
                ExcelExportforBB.createFileContent(currentComponentTable.getVisibleColumns(), resultList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public void excelExport() {
        try {
            configureContractExcelResultTable();
            binderDto.setIsCount(true);
            binderDto.setStartIndex(0);
            binderDto.setEndIndex(Integer.MAX_VALUE);
            List<ComponentInfoDTO> list = logic.getComponentInfoResults(binderDto, selection);
            for (ComponentInfoDTO summary : list) {
                contractExcelResultBean.addBean(summary);
            }
            ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(contractExcelTable), "Component Information", "Component Information", "Component_Information.xls", false);
            excel.export();
            contractDashboardLay.removeComponent(contractDashboardLay);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void configureContractExcelResultTable() {
        contractExcelResultBean = new BeanItemContainer<>(ComponentInfoDTO.class);
        contractExcelTable = new ExtCustomTable();
        tableLayout.addComponent(contractDashboardLay);
        contractDashboardLay.addComponent(contractExcelTable);
        contractExcelTable.setRefresh(Boolean.FALSE);
        contractExcelTable.setVisible(false);
        contractExcelTable.setContainerDataSource(contractExcelResultBean);
        contractExcelTable.setVisibleColumns(currentComponentTable.getVisibleColumns());
        contractExcelTable.setColumnHeaders(currentComponentTable.getColumnHeaders());
    }

    public ComponentInfoDTO getComponentDto() {
        return componentDto;
    }

    public void setComponentDto(ComponentInfoDTO componentDto) {
        this.componentDto = componentDto;
    }

    public void replaceComponent() {
        itemSearchGrid.replaceComponent(rsType_DTO, rsTypeText);
        itemSearchGrid.replaceComponent(rsProgramType_DTO, rsProgramTypeText);
        itemSearchGrid.replaceComponent(rsCategory_DTO, rsCategoryText);
        itemSearchGrid.replaceComponent(paymentFrequency_DTO, paymentFrequencyText);
        itemSearchGrid.replaceComponent(rebatePlanLevel_DTO, rebatePlanLevelText);
    }
}
