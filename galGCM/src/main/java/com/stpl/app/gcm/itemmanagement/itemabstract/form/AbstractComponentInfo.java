
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gcm.common.CommonLogic;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.ComponentInfoTableLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
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

    @UiField("compId")
    public Label compId;
    @UiField("componentSelection")
    public ComboBox componentSelection;
    @UiField("labelId")
    public Label labelId;
    @UiField("componentId")
    public TextField componentId;
    @UiField("labelNo")
    public Label labelNo;
    @UiField("componentNo")
    TextField componentNo;
    @UiField("labelName")
    public Label labelName;
    @UiField("componentName")
    TextField componentName;
    @UiField("labelStatus")
    public Label labelStatus;
    @UiField("componentStatus")
    TextField componentStatus;
    @UiField("labelType")
    public Label labelType;
    @UiField("componentType")
    TextField componentType;
    @UiField("lableStartDate")
    public Label lableStartDate;
    @UiField("compStartDate")
    PopupDateField compStartDate;
    @UiField("lableEndDate")
    public Label lableEndDate;
    @UiField("compEndDate")
    PopupDateField compEndDate;
    @UiField("labelRebateFreq")
    public Label labelRebateFreq;
    @UiField("rebateFrequency")
    TextField rebateFrequency;
    @UiField("labelBasis")
    public Label labelBasis;
    @UiField("basis")
    TextField basis;
    @UiField("labelRsType")
    public Label labelRsType;
    @UiField("rsType")
    public ComboBox rsType_DTO;
    @UiField("lableRsProgramType")
    public Label lableRsProgramType;
    @UiField("rsProgramType")
    public ComboBox rsProgramType_DTO;
    @UiField("lableRsCategory")
    public Label lableRsCategory;
    @UiField("rsCategory")
    public ComboBox rsCategory_DTO;
    @UiField("lablePaymentFreq")
    public Label lablePaymentFreq;
    @UiField("paymentFrequency")
    public ComboBox paymentFrequency_DTO;
    @UiField("lableRebatePlanLevel")
    public Label lableRebatePlanLevel;
    @UiField("rebatePlanLevel")
    public ComboBox rebatePlanLevel_DTO;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("itemSearchGrid")
    GridLayout itemSearchGrid;
    @UiField("exportBtn")
    public Button exportBtn;
    TextField rsTypeText = new TextField();
    TextField rsProgramTypeText = new TextField();
    TextField rsCategoryText = new TextField();
    TextField paymentFrequencyText = new TextField();
    TextField rebatePlanLevelText = new TextField();
    ComponentInfoTableLogic tablelogic = new ComponentInfoTableLogic();
    public ExtPagedTable currentComponentTable = new ExtPagedTable(tablelogic);
    BeanItemContainer<ComponentInfoDTO> searchContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    String layoutIndicator = new String();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    public static final Object[] IFP_COL = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "attachedDate"};
    public static final String[] IFP_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Attached Date"};
    public static final Object[] CFP_COL = {"itemNo", "itemName", "itemStatus", Constants.START_DATE, Constants.END_DATE, "status", "tradeClass", "attachedDate"};
    public static final String[] CFP_HEADER = {Constants.COMPANYNO, Constants.COMPANYNAME, "Company status", "Start Date", "End Date", "Status", Constants.TRADECLASS, "Attached Date"};
    public static final Object[] PS_COL = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType", "priceTolerance", "maxIncrementalChange", "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "attachedDate"};
    public static final String[] PS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date", "Price Protection EndDate", "PriceProtection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object[] RS_COL = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String[] RS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula ID", "Formula Name", "RebatePlan ID", "RebatePlan Name", "Rebate Amount", "Bundle No", "Attached Date"};
    ComponentInfoDTO binderDto = new ComponentInfoDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<ComponentInfoDTO>(binderDto));
    SelectionDTO selection;
    AbstractLogic abstractLogic = AbstractLogic.getInstance();
    String componentFlag = StringUtils.EMPTY;
    private BeanItemContainer<ComponentInfoDTO> contractExcelResultBean = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private ExtCustomTable contractExcelTable;
    VerticalLayout contractDashboardLay = new VerticalLayout();
    ComponentInfoDTO componentDto = new ComponentInfoDTO();
    AbstractLogic logic = AbstractLogic.getInstance();

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
        tableLayout.setWidth("1878px");
        tableLayout.addComponent(controlLayout);
        getBinder();
        configureFields();
    }

    Component addResultsToTable() {
        tablelogic.setContainerDataSource(searchContainer);
        tablelogic.setPageLength(5);
        tablelogic.sinkItemPerPageWithPageLength(false);
        currentComponentTable.setVisibleColumns(RS_COL);
        currentComponentTable.setColumnHeaders(RS_HEADER);
        currentComponentTable.setWidth("1800px");
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
    public void componentChangeListener(Property.ValueChangeEvent event) throws Exception {
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

    public void loadData(String value) throws Exception {
        if (value.equals(Constants.PS)) {
            currentComponentTable.setVisibleColumns(PS_COL);
            currentComponentTable.setColumnHeaders(PS_HEADER);
            currentComponentTable.addStyleName("filterbar");
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setHeight(400, Sizeable.Unit.PIXELS);
            currentComponentTable.setPageLength(5);
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
            currentComponentTable.setVisibleColumns(IFP_COL);
            currentComponentTable.setColumnHeaders(IFP_HEADER);
            currentComponentTable.addStyleName("filterbar");
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setHeight(400, Sizeable.Unit.PIXELS);
            currentComponentTable.setPageLength(5);
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
            currentComponentTable.setVisibleColumns(CFP_COL);
            currentComponentTable.setColumnHeaders(CFP_HEADER);
            currentComponentTable.addStyleName("filterbar");
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setHeight(400, Sizeable.Unit.PIXELS);
            currentComponentTable.setPageLength(5);
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
            currentComponentTable.setVisibleColumns(RS_COL);
            currentComponentTable.setColumnHeaders(RS_HEADER);
            currentComponentTable.addStyleName("filterbar");
            removeFilter();
            setFilter();
            currentComponentTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
            currentComponentTable.setHeight(400, Sizeable.Unit.PIXELS);
            currentComponentTable.setPageLength(5);

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
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getDescription()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
               
                if (componentSelection.getValue() != null && !"null".equals(String.valueOf(componentSelection.getValue()))) {
                    if (componentSelection.getValue().equals(Constants.CFP)) {
                        if ("status".equals(propertyId) || "itemStatus".equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, "Load Item Status Count", "Load Item Status", true);
                            return status;
                        }
                    }

                    if (componentSelection.getValue().equals(Constants.IFP)) {
                        if ("status".equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, "Load Item Status Count", "Load Item Status", true);
                            return status;
                        }
                        if ("brand".equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, "LoadBrandCount", "LoadBrand", true);
                            return brandDdlb;
                        }
                    }

                    if (componentSelection.getValue().equals(Constants.PS)) {
                        if ("status".equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, "Load Item Status Count", "Load Item Status", true);
                            return status;
                        }
                        if ("brand".equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, "LoadBrandCount", "LoadBrand", true);
                            return brandDdlb;
                        }
                        if ("priceProtectionStatus".equals(propertyId)) {
                            ComboBox priceToleranceType = new ComboBox();
                            abstractLogic.LazyLoadDdlb(priceToleranceType, "Load Item Status Count", "Load Item Status", true);
                            return priceToleranceType;
                        }

                    }

                    if (componentSelection.getValue().equals(Constants.RS)) {

                        if ("brand".equals(propertyId)) {
                            ComboBox brandDdlb = new ComboBox();
                            abstractLogic.LazyLoadDdlb(brandDdlb, "LoadBrandCount", "LoadBrand", true);
                            return brandDdlb;
                        }
                        if ("status".equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            abstractLogic.LazyLoadDdlb(status, "Load Item Status Count", "Load Item Status", true);
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

    public void setFlags() throws Exception {
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
            LOGGER.error(ex);
        }
    }

    /**
     * loadRsType
     */
    private void loadRsType() throws Exception {

        abstractLogic.loadComboBox(rsType_DTO, "RS_TYPE", false);

    }

    /**
     * loadRsCategory
     */
    private void loadRsCategory() throws Exception {
        abstractLogic.loadComboBox(rsCategory_DTO, "RS_CATEGORY", false);
    }

    /**
     * loadRptype
     */
    private void loadRptype() throws Exception {
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
     * @return CustomFieldGroup
     */
    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<ComponentInfoDTO>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("rsType")
    public void rsTypeDdlbChange(Property.ValueChangeEvent event) throws Exception {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
        print();
    }

    @UiHandler("rsProgramType")
    public void rsProgramCategoryDdlbChange(Property.ValueChangeEvent event) throws Exception {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
        print();
    }

    @UiHandler("rsCategory")
    public void rsCategoryDdlbChange(Property.ValueChangeEvent event) throws Exception {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
        print();
    }

    @UiHandler("paymentFrequency")
    public void paymentDdlbChange(Property.ValueChangeEvent event) throws Exception {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
        print();
    }

    @UiHandler("rebatePlanLevel")
    public void RPLevelDdlbChange(Property.ValueChangeEvent event) throws Exception {
        binder.commit();
        tablelogic.loadSetData(binderDto, selection);
        print();
    }

    void print() {
    }

    @UiHandler("exportBtn")
    public void exportButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        try {
            if (searchContainer.size() > 0) {
                createWorkSheet("Component_Information", currentComponentTable);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, Exception {
        long recordCount = 0;
        if (resultTable.size() != 0) {
            binderDto.setIsCount(false);
            selection.setFilters(tablelogic.getFilters());
            recordCount = logic.getComponentInfoCount(binderDto, selection);
        }
        ExcelExportforBB.createWorkSheet(currentComponentTable.getColumnHeaders(), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
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
            LOGGER.error(e.getMessage());
        }
    }

    public void excelExport() {
        try {
            configureContractExcelResultTable();
            binderDto.setIsCount(true);
            binderDto.setStartIndex(0);
            binderDto.setEndIndex(1000000000);
            List<ComponentInfoDTO> list = logic.getComponentInfoResults(binderDto, selection);
            for (ComponentInfoDTO summary : list) {
                contractExcelResultBean.addBean(summary);
            }
            ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(contractExcelTable), "Component Information", "Component Information", "Component Information.xls", false);
            excel.export();
            contractDashboardLay.removeComponent(contractDashboardLay);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureContractExcelResultTable() {
        contractExcelResultBean = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
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
