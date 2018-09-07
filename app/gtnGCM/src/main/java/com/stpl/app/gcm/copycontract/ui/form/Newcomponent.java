
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.dto.NewComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.copycontract.logic.tablelogic.NewComponentsDetailsSearchTableLogic;
import com.stpl.app.gcm.discount.ui.form.FormulaSearchLookup;
import com.stpl.app.gcm.discount.ui.form.RebatePlanLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.Locale;

/**
 *
 * @author kasiammal.m
 */
public class Newcomponent extends CustomComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(Newcomponent.class);
    
    @UiField("componenttype")
    public ComboBox componenttype;
    @UiField("SearchfieldNC")
    public ComboBox searchFieldNC;
    @UiField("componentDetailsSearch")
    public ComboBox componentDetailsSearch;
    @UiField("searchDDLB")
    public ComboBox searchDDLB;
    @UiField("statusddlb")
    public ComboBox statusddlb;
    @UiField("startPeriod")
    public PopupDateField startPeriod;
    @UiField("componentSearch")
    public Button componentSearch;
    @UiField("searchValue")
    public TextField searchValue;
    @UiField("addBtn")
    public Button addBtn;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("addToTree")
    public Button addToTree;
    @UiField("levelRemoveBtn")
    public Button levelRemoveBtn;
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    @UiField("SearchvaluedNC")
    public TextField searchValuedNC;
    @UiField("enableDisableRadio")
    public OptionGroup enableDisableRadio;
    @UiField("componentDetailsSearchTableLayout")
    public VerticalLayout componentDetailsSearchTableLayout;
    @UiField("componentDetailsSelectedItem1")
    public ExtFilterTable componentDetailsSelectedItem;
    @UiField("levelDetailsResultsTable1")
    public ExtFilterTable levelDetailsResultsTable;
    private final TreeTable dashboardResultsTable;
    @UiField("componentSelectionGrid")
    public GridLayout componentSelectionGrid;
    private final CopyContractLogic copyContractLogic = new CopyContractLogic();
    private final BeanItemContainer<NewComponentDTO> componentSearchContainer = new BeanItemContainer<>(NewComponentDTO.class);
    private final BeanItemContainer<NewComponentDTO> componentResultsContainer = new BeanItemContainer<>(NewComponentDTO.class);
    private final ExtTreeContainer<CopyComponentDTO> dashBoardContainer;
    private final BeanItemContainer<NewComponentDTO> contractInfoContainer = new BeanItemContainer<>(NewComponentDTO.class);
    private final QueryUtils queryUtils = new QueryUtils();
    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    @UiField("dashboardid")
    public VerticalLayout dashboardLayout;
    /* Component Layout */
    @UiField("cfpComponent")
    public GridLayout cfpComponent;
    @UiField("ifpComponent")
    public GridLayout ifpComponent;
    @UiField("psComponent")
    public GridLayout psComponent;
    @UiField("rsComponent")
    public GridLayout rsComponent;
    /*cfp Component */
    @UiField("cfpId")
    public TextField cfpId;
    @UiField("cfpNo")
    public TextField cfpNo;
    @UiField("cfpName")
    public TextField cfpName;
    @UiField("cfpStatus")
    public ComboBox cfpStatus;
    @UiField("cfpStartDate")
    public PopupDateField cfpStartDate;
    @UiField("cfpEndDate")
    public PopupDateField cfpEndDate;
    @UiField("cfpfileName")
    public TextField cfpfileName;
    @UiField("CFPType")
    public ComboBox cfpType;
    @UiField("salesInclusion")
    public ComboBox salesInclusion;
    /*ifp Component */
    @UiField("ifpId")
    public TextField ifpId;
    @UiField("ifpNo")
    public TextField ifpNo;
    @UiField("ifpName")
    public TextField ifpName;
    @UiField("ifpStatus")
    public ComboBox ifpStatus;
    @UiField("ifptype")
    public ComboBox ifptype;
    @UiField("ifpStartDate")
    public PopupDateField ifpStartDate;
    @UiField("ifpEndDate")
    public PopupDateField ifpEndDate;
    @UiField("ifpfileName")
    public TextField ifpfileName;
    /*ps Component */
    @UiField("psId")
    public TextField psId;
    @UiField("psNo")
    public TextField psNo;
    @UiField("psName")
    public TextField psName;
    @UiField("psStatus")
    public ComboBox psStatus;
    @UiField("psStartDate")
    public PopupDateField psStartDate;
    @UiField("psfileName")
    public TextField psfileName;
    /*rs Component */
    @UiField("rsId")
    public TextField rsId;
    @UiField("rsStatus")
    public ComboBox rsStatus;
    @UiField("paymentFrequency")
    public ComboBox paymentFrequency;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("rsStartDate")
    public PopupDateField rsStartDate;
    @UiField("rsRarType")
    public ComboBox rsRarType;
    @UiField("rsName")
    public TextField rsName;
    @UiField("rsEndDate")
    public PopupDateField rsEndDate;
    @UiField("BtnsearchNC")
    public Button btnSearchNC;
    @UiField("populateDetails")
    public Button populateDetails;
    @UiField("calendar")
    public ComboBox calendar;
    /* Component Layout */
    @UiField("cfpDetailsGrid")
    public GridLayout cfpDetailsGrid;
    @UiField("ifpDetailsGrid")
    public GridLayout ifpDetailsGrid;
    @UiField("psDetailsGrid")
    public GridLayout psDetailsGrid;
    @UiField("rsDetailsGrid")
    public GridLayout rsDetailsGrid;
    @UiField("cfpDetailsNo")
    public TextField cfpDetailsNo;
    @UiField("cfpDetailsName")
    public TextField cfpDetailsName;
    @UiField("ifpDetailsNo")
    public TextField ifpDetailsNo;
    @UiField("ifpDetailsName")
    public TextField ifpDetailsName;
    @UiField("psDetailsNo")
    public TextField psDetailsNo;
    @UiField("psDetailsName")
    public TextField psDetailsName;
    @UiField("rsDetailsNo")
    public TextField rsDetailsNo;
    @UiField("rsDetailsName")
    public TextField rsDetailsName;
    @UiField("paymentMethod")
    public ComboBox paymentMethod;
    @UiField("rebatePlanLevel")
    public ComboBox rebatePlanLevel;
    @UiField("rsType")
    public ComboBox rsType;
    @UiField("deductionInclusion")
    public ComboBox deductionInclusion;
    @UiField("calculationLevel")
    public ComboBox calculationLevel;
    @UiField("calculationType")
    public ComboBox calculationType;
     @UiField("rebateFrequency")
    public ComboBox rebateFrequency;
    
    private String selectedCompanies = Constants.EMPTY;
    private String selectedItems = Constants.EMPTY;
    public static final String PRICE_VAL = "([0-9|\\.|])*";
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
    private final CommonDao dao = CommonImpl.getInstance();
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    private final NewComponentsDetailsSearchTableLogic tablelogic = new NewComponentsDetailsSearchTableLogic();
    private final ExtPagedTable componentDetailsSearchTable = new ExtPagedTable(tablelogic);
    private final CopyContractLogic logic = new CopyContractLogic();
    public static final String REGEX_STRING = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";
    public static final String SELECT_ATLEAST_ONE_RECORD = "Please Select Atleast one Record at Component Details Section";
    public static final String STRING_REGEX = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    public static final String PLEASE_ENTER_ALL_THE_FIELDS = "Please Enter All the fields in Component selection section";

    public Newcomponent(TreeTable dashboardTable, ExtTreeContainer<CopyComponentDTO> dashBoardContainer) {
        this.dashBoardContainer = dashBoardContainer;
        this.dashboardResultsTable = dashboardTable;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/Newcomponent.xml"), this));
        dashboardLayout.addComponent(dashboardResultsTable);
        configureFields();
        configureSecurityPermissions();

    }

    protected final void configureFields() {
        try {
            cfpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
            cfpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            ifpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
            ifpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            psStartDate.setDateFormat(Constants.MM_DD_YYYY);
            psStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            rsStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rsStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            rsEndDate.setDateFormat(Constants.MM_DD_YYYY);
            rsEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
            btnSearchNC.setEnabled(false);
            componenttype.addItem(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            componenttype.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN);
            componenttype.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE);
            componenttype.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE);
            componenttype.select(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            componenttype.setNullSelectionAllowed(false);

            componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setNullSelectionAllowed(true);
            componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            configureComponentDetailsTable();
            componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentDetailsSearchTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentDetailsSearchTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentDetailsSearchTable.setPageLength(NumericConstants.FIVE);
            componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newCompanyDetailsColumns);
            componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newCompanyDetailsHeaders);
            componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_END_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK, BooleanConstant.getTrueFlag());

            Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSearchTable.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }
            componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
                @Override
                public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                    Field field;
                    if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                        field = new CheckBox();
                    } else {
                        field = null;
                    }
                    return field;
                }
            });

            componentDetailsSearchTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                        componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
            searchDDLB.setVisible(false);
            commonUtil.loadComboBox(psStatus, UiUtils.STATUS, false);
            dashboardResultsTable.setPageLength(NumericConstants.EIGHT);
            dashboardResultsTable.markAsDirty();
            dashboardResultsTable.setContainerDataSource(dashBoardContainer);
            dashboardResultsTable.setVisibleColumns(Constants.getInstance().copycontractDashboardResultsColumns);
            dashboardResultsTable.setColumnHeaders(Constants.getInstance().copycontractDashboardResultsHeaders);
            dashboardResultsTable.setWidth("670px");
            dashboardResultsTable.setSelectable(true);
            dashboardResultsTable.setMultiSelect(false);
            componentDetailsSearchTable.setEditable(true);
            cfpComponent.setVisible(false);
            ifpComponent.setVisible(false);
            psComponent.setVisible(false);
            rsComponent.setVisible(false);
            commonUtil.loadComboBox(rsStatus, UiUtils.STATUS, false);
            commonUtil.loadComboBox(rsRarType, UiUtils.REBATE_PROGRAM_TYPE, false);
            commonUtil.loadComboBox(rebatePlanLevel, Constants.RS_CATEGORY_LISTNAME, false);
            commonUtil.loadComboBox(paymentFrequency, UiUtils.PAYMENT_FREQUENCY, false);
            commonUtil.loadComboBox(paymentMethod, UiUtils.PAYMENT_METHOD, false);
            commonUtil.loadComboBox(rsType, UiUtils.RS_TYPE, false);
            commonUtil.loadComboBox(calendar, UiUtils.RS_CALENDAR, false);
            commonUtil.loadComboBox(deductionInclusion, Constants.LOCKED_STATUS_LISTNAME, false);
            commonUtil.loadComboBox(calculationLevel, Constants.CALCULATION_LEVEL_LISTNAME, false);
            commonUtil.loadComboBox(calculationType, Constants.CALCULATION_TYPE_LISTNAME, false);
            commonUtil.loadComboBox(rebateFrequency, Constants.REBATE_FREQUENCY_LISTNAME, false);
            
            
            getSelectNull(rsStatus);
            
            searchDDLB.setVisible(false);
            componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentDetailsSelectedItem.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentDetailsSelectedItem.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentDetailsSelectedItem.setPageLength(NumericConstants.FIVE);
            componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
            componentDetailsSelectedItem.setEditable(true);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
            componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK, BooleanConstant.getTrueFlag());
            componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

            Object[] vColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : vColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }

            componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
                @Override
                public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                    Field field = null;
                    if (Constants.CHECK.equals(propertyId)) {
                        field = new CheckBox();
                    }
                    if (Constants.COMPANY_STATUS.equals(propertyId) || Constants.STATUS_S.equals(propertyId) || Constants.ITEM_STATUS_PROPERTY.equals(propertyId)) {
                        ComboBox status = new ComboBox();
                        getSelectNull(status);
                        try {
                            commonUtil.loadComboBox(status, UiUtils.STATUS, false);
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                        field = status;
                    }
                    if (Constants.START_DATE.equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIELD_CENTER);
                        field = field1;
                    }
                    if (Constants.END_DATE.equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIELD_CENTER);
                        field = field1;

                    }
                    return field;
                }
            });
            componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentResultsContainer.getItemIds()) {
                        componentResultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            levelDetailsResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            levelDetailsResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            levelDetailsResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            levelDetailsResultsTable.setPageLength(NumericConstants.NINE);
            levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
            levelDetailsResultsTable.setVisibleColumns(Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, Constants.PS_START_DATE, Constants.PS_END_DATE);
            levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
            levelDetailsResultsTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
            levelDetailsResultsTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
            Object[] vColumns1 = levelDetailsResultsTable.getVisibleColumns();
            for (Object column : vColumns1) {
                levelDetailsResultsTable.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }
            enableDisableRadio.addItem("Enable");
            enableDisableRadio.addItem(DISABLE.getConstant());
            enableDisableRadio.select(DISABLE.getConstant());
            fieldDdlb.setEnabled(false);
            statusddlb.setEnabled(false);
            populateBtn.setEnabled(false);
            searchFieldNC.setEnabled(false);
            searchValuedNC.setEnabled(false);
            searchFieldNC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            searchFieldNC.setNullSelectionAllowed(true);
            searchFieldNC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setWidth("205px");
            fieldDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.addItem(Constants.STATUS_FIELD);
            fieldDdlb.addItem(Constants.START_DATE_HEADER);
            fieldDdlb.addItem(Constants.END_DATE_HEADER);
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.setWidth("205px");
            fieldDdlb.setPageLength(NumericConstants.FIVE);
            componentDetailsSearch.setPageLength(NumericConstants.FIVE);
            cfpComponent.setVisible(true);
            ifpComponent.setVisible(false);
            psComponent.setVisible(false);
            rsComponent.setVisible(false);
            startPeriod.setVisible(false);
            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    private void configureComponentDetailsTable() {

        componentDetailsSearchTableLayout.addComponent(componentDetailsSearchTable);
        componentDetailsSearchTableLayout.addComponent(tablelogic.createControls());
        componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSearchTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentDetailsSearchTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentDetailsSearchTable.setPageLength(NumericConstants.FIVE);
        tablelogic.setContainerDataSource(componentSearchContainer);
        tablelogic.sinkItemPerPageWithPageLength(false);
        componentDetailsSearchTable.setEditable(true);
        componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newCompanyDetailsColumns);
        componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newCompanyDetailsHeaders);
        componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_START_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_END_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK, BooleanConstant.getTrueFlag());

        Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
        for (Object column : visibleColumns) {
            componentDetailsSearchTable.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
        }
        componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Field field;
                if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                    field = new CheckBox();
                } else {
                    field = null;
                }
                return field;
            }
        });

        componentDetailsSearchTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                    componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });

    }

    private void loadcomponentSelectionGrid() {
        componentDetailsSelectedItem.removeAllItems();
        componentResultsContainer.removeAllItems();
        componentSearchContainer.removeAllItems();
        componentDetailsSearch.removeAllItems();
        componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentDetailsSearch.setNullSelectionAllowed(true);
        componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        if (componenttype.getValue() != null) {
            if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                try {
                    componentSearchContainer.removeAllItems();
                    cfpComponent.setVisible(true);
                    copyContractLogic.getSelectNull(cfpStatus);
                    commonUtil.loadComboBox(cfpStatus, UiUtils.STATUS, false);
                    componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                    componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newCompanyDetailsColumns);
                    componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newCompanyDetailsHeaders);
                    componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnAlignment(Constants.COMPANY_END_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnCheckBox(Constants.getInstance().newCompanyDetailsColumns[0], BooleanConstant.getTrueFlag());

                    Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                    for (Object column : visibleColumns) {
                        componentDetailsSearchTable.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
                    }
                    componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
                        @Override
                        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                            Field field;
                            if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                                field = new CheckBox();
                            } else {
                                field = null;
                            }
                            return field;
                        }
                    });
                    componentDetailsSearchTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                        @Override
                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                                componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                            }
                        }
                    });
                    cfpStatus.select(Constants.ZEROSTRING);
                    cfpStatus.setValidationVisible(true);
                    copyContractLogic.getSelectNull(cfpType);
                    commonUtil.loadComboBox(cfpType, UiUtils.CFP_TYPE, false);
                    cfpType.setValidationVisible(true);
                    commonUtil.loadComboBox(salesInclusion, Constants.LOCKED_STATUS_LISTNAME, false);
                    salesInclusion.setValidationVisible(true);
                    ifpComponent.setVisible(false);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);
                    componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, Constants.START_DATE, Constants.END_DATE);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearch.setEnabled(true);
                    searchValue.setEnabled(true);
                    searchDDLB.setEnabled(true);
                    componentSearch.setEnabled(true);
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                try {
                    cfpComponent.setVisible(false);
                    componentSearchContainer.removeAllItems();
                    copyContractLogic.getSelectNull(ifpStatus);
                    commonUtil.loadComboBox(ifpStatus, UiUtils.STATUS, false);
                    ifpStatus.setValidationVisible(true);
                    commonUtil.loadComboBox(ifptype, UiUtils.IFP_TYPE, false);
                    componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                    componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newIfpDetailsColumns);
                    componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newIfpDetailsHeaders);
                    componentDetailsSearchTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);

                    componentDetailsSearchTable.setColumnCheckBox(Constants.getInstance().newIfpDetailsColumns[0], BooleanConstant.getTrueFlag());
                    componentDetailsSelectedItem.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
                    Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                    for (Object column : visibleColumns) {
                        componentDetailsSearchTable.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
                    }
                    componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
                        @Override
                        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                            Field field;
                            if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                                field = new CheckBox();
                            } else {
                                field = null;
                            }
                            return field;
                        }
                    });
                    ifptype.select(Constants.ZEROSTRING);
                    ifptype.setValidationVisible(true);
                    ifpComponent.setVisible(true);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);
                    componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);

                    componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearch.setEnabled(true);
                    searchValue.setEnabled(true);
                    searchDDLB.setEnabled(true);
                    componentSearch.setEnabled(true);
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                cfpComponent.setVisible(false);
                ifpComponent.setVisible(false);
                psComponent.setVisible(true);
                rsComponent.setVisible(false);
                componentSearchContainer.removeAllItems();
                componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newPsDetailsColumns);
                componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newPsDetailsHeaders);
                componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

                Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsSearchTable.setColumnWidth(column, NumericConstants.ONE_FIVE_FIVE);
                }
                componentDetailsSearch.addItem(Constants.IFP_ID);
                componentDetailsSearch.addItem(Constants.IFP_NO);
                componentDetailsSearch.addItem(Constants.IFP_NAME_LABEL);
                componentDetailsSearch.addItem(Constants.IFP_STATUS);
                componentDetailsSearch.addItem(Constants.IFPTYPE);
                componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "priceType", "ppStartDate");
                componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Price Type", "Price Protection Start Date");
                componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

                Object[] visibleColumn = componentDetailsSelectedItem.getVisibleColumns();
                for (Object vcolumn : visibleColumn) {
                    componentDetailsSelectedItem.setColumnWidth(vcolumn, NumericConstants.ONE_FIVE_ZERO);
                }
                componentDetailsSearch.setEnabled(true);
                searchValue.setEnabled(true);
                searchDDLB.setEnabled(true);
                componentSearch.setEnabled(true);
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                componentDetailsSearch.addItem(Constants.IFP_ID);
                componentDetailsSearch.addItem(Constants.IFP_NO);
                componentDetailsSearch.addItem(Constants.IFP_NAME_LABEL);
                componentDetailsSearch.addItem(Constants.IFP_STATUS);
                componentDetailsSearch.addItem(Constants.IFPTYPE);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem(Constants.STATUS_FIELD);
                fieldDdlb.addItem(Constants.START_DATE_HEADER);
                fieldDdlb.addItem(Constants.END_DATE_HEADER);

                cfpComponent.setVisible(false);
                ifpComponent.setVisible(false);
                psComponent.setVisible(false);
                rsComponent.setVisible(true);
                componentSearchContainer.removeAllItems();
                componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().newRsDetailsColumns);
                componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().newRsDetailsHeaders);
                componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                componentResultsContainer.removeAllItems();
                componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, Constants.REBATE_PLAN_PROPERTY, Constants.FORMULA_ID_PROPERTY);
                componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Rebate Plan", "Formula ID");
                componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
                    @Override
                    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                        Field field;

                        if (propertyId.equals(Constants.REBATE_PLAN_PROPERTY)) {
                            final CustomTextField rebatePlan = new CustomTextField();
                            rebatePlan.addStyleName("searchicon");
                            rebatePlan.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                            rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                                @Override
                                public void click(CustomTextField.ClickEvent event) {
                                    RebatePlanLookup lookup = new RebatePlanLookup(rebatePlan);
                                    lookup.addCloseListener(new Window.CloseListener() {
                                        @Override
                                        public void windowClose(Window.CloseEvent e) {
                                            return;
                                        }
                                    });
                                    UI.getCurrent().addWindow(lookup);
                                }
                            });
                            return rebatePlan;
                        } else if (propertyId.equals(Constants.FORMULA_ID_PROPERTY)) {
                            final CustomTextField formulaId = new CustomTextField();
                            formulaId.addStyleName("searchicon");
                            formulaId.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                            formulaId.addClickListener(new CustomTextField.ClickListener() {
                                @Override
                                public void click(CustomTextField.ClickEvent event) {
                                    FormulaSearchLookup lookup = new FormulaSearchLookup(formulaId);
                                    UI.getCurrent().addWindow(lookup);
                                }
                            });
                            return formulaId;
                        } else if (String.valueOf(Constants.CHECK).equals(propertyId)) {

                            field = new CheckBox();
                        } else if (Constants.START_DATE.equals(propertyId) || Constants.END_DATE.equals(propertyId)) {
                            PopupDateField field1 = new PopupDateField();
                            field1.setDateFormat(Constants.MM_DD_YYYY);
                            field1.setStyleName(Constants.DATE_FIELD_CENTER);
                            field = field1;
                        } else if (Constants.STATUS_S.equals(propertyId)) {
                            ComboBox status = new ComboBox();
                            getSelectNull(status);
                            try {
                                commonUtil.loadComboBox(status, UiUtils.STATUS, false);
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                            field = status;
                        } else {
                            field = null;
                        }
                        return field;
                    }
                });

                componentDetailsSearch.setEnabled(true);
                searchValue.setEnabled(true);
                searchDDLB.setEnabled(true);
                componentSearch.setEnabled(true);
            }
            for (Object obj : componentDetailsSearchTable.getVisibleColumns()) {
                if (obj.toString().contains("Date")) {
                    componentDetailsSelectedItem.setColumnAlignment(obj, ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnAlignment(obj, ExtCustomTable.Align.CENTER);
                }
            }
        }

    }

    @UiHandler("enableDisableRadio")
    public void enableDisableRadioChange(Property.ValueChangeEvent event) {
        startPeriod.setValue(null);
        fieldDdlb.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        statusddlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        statusddlb.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        if (enableDisableRadio.getValue().toString().equalsIgnoreCase("Enable")) {
            fieldDdlb.setEnabled(true);
            statusddlb.setEnabled(true);
            populateBtn.setEnabled(true);
            startPeriod.setEnabled(true);
        } else {
            fieldDdlb.setEnabled(false);
            statusddlb.setEnabled(false);
            populateBtn.setEnabled(false);
            startPeriod.setEnabled(false);
        }
    }

    @UiHandler("componenttype")
    public void componenttypeChange(Property.ValueChangeEvent event) {
        loadcomponentSelectionGrid();
        loadComponentDetailsSearchSection();
    }

    @UiHandler("fieldDdlb")
    public void fieldDdlbChange(Property.ValueChangeEvent event) {
        changeMassUpdateField();
    }

    @UiHandler("componentDetailsSearch")
    public void componentDetailsSearchChange(Property.ValueChangeEvent event) {
        searchValue.setValue(Constants.EMPTY);
        if (event != null) {
            String value = String.valueOf(componentDetailsSearch.getValue());
            loadSearchFields(value);
        }
    }

    public int getTabNumber() {
        return 0;
    }

    @UiHandler("componentSearch")
    public void componentSearchLogic(Button.ClickEvent event) {
        String cType = String.valueOf(componenttype.getValue());
        if (componentDetailsSearch.getValue() != null) {
            String searchValueString = StringUtils.EMPTY;
            if (searchDDLB.getValue() != null) {
                searchValueString = String.valueOf(searchDDLB.getValue());
            } else if (searchValue.getValue() != null && !searchValue.getValue().isEmpty()) {
                searchValueString = searchValue.getValue();
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Search Value");
            }
            tablelogic.loadSetData(cType, componentDetailsSearch.getValue().toString(), searchValueString, true);
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Search Field");
        }

    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) throws ParseException {

        componentDetailsSelectedItem.removeAllItems();
        componentResultsContainer.removeAllItems();
        Collection<?> returnList = componentDetailsSearchTable.getItemIds();

        Set setA = new HashSet();
        boolean flag = false;
        String ids = Constants.EMPTY;
        componentResultsContainer.removeAllItems();
        List<NewComponentDTO> list = new ArrayList<>();
        for (Object item : returnList) {
            Boolean checked = (Boolean) componentSearchContainer.getContainerProperty(item, Constants.CHECK).getValue();
            if (checked) {
                flag = true;
                String id = String.valueOf(componentSearchContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                setA.add(id);
            }
        }
        if (!flag) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Search Section");
        }
        if (!setA.isEmpty()) {
            ids = getIdString(setA);
        }

        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentDetailsSelectedItem.setEditable(true);
            componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
            componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK, BooleanConstant.getTrueFlag());
            fieldDdlb.addItem(Constants.STATUS_FIELD);
            fieldDdlb.addItem(Constants.START_DATE_HEADER);
            fieldDdlb.addItem(Constants.END_DATE_HEADER);
            Object[] vColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : vColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }
            componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentResultsContainer.getItemIds()) {
                        componentResultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            String query = queryUtils.LoadmassupdateCompany(ids);
            List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (itemList != null && !itemList.isEmpty()) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setModelId(String.valueOf(obje[0]));
                    itemDTO.setCompanyNo(String.valueOf(obje[1]));
                    itemDTO.setCompanyName(String.valueOf(obje[NumericConstants.TWO]));
                    itemDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.THREE]));
                    addBtnSetStartDateMethod(obje, itemDTO);
                    if (obje[NumericConstants.FIVE] != null) {
                        String date = df.format(obje[NumericConstants.FIVE]);
                        Date date2 = df.parse(date);
                        itemDTO.setEndDate(date2);
                    } else {
                        itemDTO.setEndDate(null);
                    }
                    list.add(itemDTO);
                }
                componentResultsContainer.addAll(list);
            }
        }
        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            String query = queryUtils.loadMassUpdateItem(ids);
            List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setModelId(String.valueOf(obje[NumericConstants.SIX]));
                itemDTO.setItemNo(String.valueOf(obje[1]));
                itemDTO.setItemName(String.valueOf(obje[0]));
                itemDTO.setItemStatus(String.valueOf(obje[NumericConstants.TWO]));
                itemDTO.setBrand(obje[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
                addBtnSetStartDateMethod(obje, itemDTO);
                if (obje[NumericConstants.FIVE] != null) {
                    String date = df.format(obje[NumericConstants.FIVE]);
                    Date date2 = df.parse(date);
                    itemDTO.setEndDate(date2);
                } else {
                    itemDTO.setEndDate(null);
                }
                if (obje[NumericConstants.SEVEN] != null && obje[NumericConstants.SEVEN].equals(Constants.SELECT_ONE)) {
                    itemDTO.setTherapyClass(Constants.EMPTY);
                } else {
                    itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.SEVEN]));
                }
                list.add(itemDTO);
            }
            componentResultsContainer.addAll(list);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.ITEM_STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
        }
        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {

            String query = queryUtils.getPSInfo(ids);
            List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (itemList != null && !itemList.isEmpty()) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    if ((obje[NumericConstants.TWO] != null) && !obje[NumericConstants.TWO].equals(Constants.SELECT_ONE) && !obje[NumericConstants.TWO].equals(Constants.NULL)) {
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.TWO]));
                    } else {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    }
                    itemDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                    itemDTO.setStatus(String.valueOf(obje[NumericConstants.FOUR]));
                    if (obje[NumericConstants.FIVE] != null) {
                        String date = df.format(obje[NumericConstants.FIVE]);
                        Date date2 = df.parse(date);
                        itemDTO.setStartDate(date2);
                    } else {
                        itemDTO.setStartDate(null);
                    }
                    if (obje[NumericConstants.SIX] != null) {
                        String date = df.format(obje[NumericConstants.SIX]);
                        Date date2 = df.parse(date);
                        itemDTO.setEndDate(date2);
                    } else {
                        itemDTO.setEndDate(null);
                    }
                    if ((obje[NumericConstants.SEVEN] != null) && !obje[NumericConstants.SEVEN].equals(Constants.SELECT_ONE) && !obje[NumericConstants.SEVEN].equals(Constants.NULL)) {
                        itemDTO.setPriceType(String.valueOf(obje[NumericConstants.SEVEN]));
                    } else {
                        itemDTO.setPriceType(Constants.EMPTY);
                    }
                    if (obje[NumericConstants.EIGHT] != null) {
                        String date = df.format((Date) obje[NumericConstants.EIGHT]);
                        itemDTO.setPpStartDate(date);
                    } else {
                        itemDTO.setPpStartDate(Constants.EMPTY);
                    }
                    itemDTO.setModelId(String.valueOf(obje[NumericConstants.NINE]));
                    itemDTO.setItemMasterId(String.valueOf(obje[NumericConstants.TEN]));
                    itemDTO.setPrice((obje[NumericConstants.ELEVEN] != null) ? String.valueOf(obje[NumericConstants.ELEVEN]) : Constants.EMPTY);
                    list.add(itemDTO);
                }
                componentResultsContainer.addAll(list);
            }
            componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, StringConstantsUtil.PRICE_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "priceType", "ppStartDate");
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Price", Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Price Type", "Price Protection Start Date");
            componentDetailsSelectedItem.setEditable(true);
            Object[] visibleColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }
        }
        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            String query = queryUtils.getIFPInfo(ids);
            List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (itemList != null && !itemList.isEmpty()) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemMasterId(String.valueOf(obje[0]));
                    itemDTO.setItemNo(String.valueOf(obje[1]));
                    itemDTO.setItemName(String.valueOf(obje[NumericConstants.TWO]));
                    if ((obje[NumericConstants.TWO] != null) && !obje[NumericConstants.TWO].equals(Constants.SELECT_ONE) && !obje[NumericConstants.TWO].equals(Constants.NULL)) {
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.THREE]));
                    } else {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    }
                    itemDTO.setBrand((obje[NumericConstants.FIVE] != null) ? String.valueOf(obje[NumericConstants.FIVE]) : Constants.EMPTY);
                    itemDTO.setStatus(String.valueOf(obje[NumericConstants.FOUR]));
                    if (obje[NumericConstants.SIX] != null) {
                        String date = df.format(obje[NumericConstants.SIX]);
                        Date date2 = df.parse(date);
                        itemDTO.setStartDate(date2);
                    } else {
                        itemDTO.setStartDate(null);
                    }
                    if (obje[NumericConstants.SEVEN] != null) {
                        String date = df.format(obje[NumericConstants.SEVEN]);
                        Date date2 = df.parse(date);
                        itemDTO.setEndDate(date2);
                    } else {
                        itemDTO.setEndDate(null);
                    }
                    itemDTO.setModelId(String.valueOf(obje[NumericConstants.EIGHT]));
                    itemDTO.setRebatePlan((obje[NumericConstants.TEN] != null) ? String.valueOf(obje[NumericConstants.TEN]) : Constants.EMPTY);
                    itemDTO.setFormulaId((obje[NumericConstants.ELEVEN] != null) ? String.valueOf(obje[NumericConstants.ELEVEN]) : Constants.EMPTY);
                    list.add(itemDTO);
                }
                componentResultsContainer.addAll(list);
            }
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, Constants.REBATE_PLAN_PROPERTY, Constants.FORMULA_ID_PROPERTY);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Rebate Plan", "Formula ID");
            componentDetailsSelectedItem.setEditable(true);
            Object[] visibleColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, NumericConstants.ONE_FIVE_ZERO);
            }
        }
    }

    public static ComboBox getSelectNull(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(NumericConstants.FIVE);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    private void loadSearchFields(String value) {
        searchDDLB.removeAllItems();
        String cType = String.valueOf(componenttype.getValue());
        if (cType.equalsIgnoreCase(Constants.PRICE_SCHEDULE) || cType.equalsIgnoreCase(Constants.REBATE_SCHEDULE)) {
            loadSearchFieldsIfpStatus(value);
            loadMassUpdateField();
        }  else {
            if (value.equalsIgnoreCase(Constants.COMPANYSTATUS)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.TRADECLASS)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.COMPANY_TRADE_CLASS, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.COMPANYCATEGORY)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.COMPANY_CATEGORY, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.COMPANYTYPE)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.COMPANY_TYPE, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.ITEM_STATUS)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.ITEM_TYPE)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.ITEM_TYPE, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.FORM)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.FORM, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.STRENGTH)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.STRENGTH, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.THERAPY_CLASS)) {
                searchDDLB.setVisible(true);
                commonUtil.loadComboBox(searchDDLB, UiUtils.THERAPEUTIC_CLASS, false);
                searchDDLB.setValidationVisible(true);
                searchValue.setVisible(false);
                searchValue.setValue(Constants.EMPTY);
            } else if (value.equalsIgnoreCase(Constants.BRAND)) {
                String query = "select BRAND_MASTER_SID,BRAND_NAME from BRAND_MASTER where INBOUND_STATUS<>'D' and BRAND_NAME is not null order by BRAND_NAME";
                List componentList =  dao.executeSelect(query);
                searchDDLB.setVisible(true);
                searchDDLB.setNullSelectionItemId(Constants.ZEROSTRING);
                for (int i = 0; i < componentList.size(); i++) {
                    Object[] obje = (Object[]) componentList.get(i);
                    searchDDLB.addItem(String.valueOf(obje[0]));
                    searchDDLB.setItemCaption(String.valueOf(obje[0]), String.valueOf(obje[1]));
                }
            } else {
                searchValue.setVisible(true);
                searchDDLB.setVisible(false);
            }
            }
        }

    private String getIdString(Set set) {
        String ids = Constants.EMPTY;
        boolean flag = false;
        for (Object id : set) {
            String idvalue = String.valueOf(id);
            if (!flag) {
                ids = idvalue;
                flag = true;
            } else {
                ids = ids + "," + idvalue;
            }
        }
        return ids;
    }

    private void loadMassUpdateField() {
        fieldDdlb.addItem(Constants.STATUS_FIELD);
        fieldDdlb.addItem(Constants.START_DATE_HEADER);
        fieldDdlb.addItem(Constants.END_DATE_HEADER);
    }

    private void changeMassUpdateField() {
        startPeriod.setValue(null);
        String searchField = String.valueOf(fieldDdlb.getValue());
        if (searchField.equals(Constants.STATUS_FIELD)) {
            try {
                copyContractLogic.getSelectNull(statusddlb);
                commonUtil.loadComboBox(statusddlb, UiUtils.STATUS, false);
                statusddlb.setVisible(true);
                startPeriod.setVisible(false);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        } else if (searchField.equals("null")) {
            startPeriod.setVisible(false);
            statusddlb.setVisible(true);
            statusddlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            statusddlb.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        } else {
            statusddlb.setVisible(false);
            startPeriod.setVisible(true);
        }
    }

    @UiHandler("populateBtn")
    public void massPopulateLogic(Button.ClickEvent event) {
        if (componenttype.getValue() != null && (String.valueOf(componenttype.getValue()).equals(Constants.PRICE_SCHEDULE) || String.valueOf(componenttype.getValue()).equals(Constants.COMPANY_FAMILY_PLAN)
                || String.valueOf(componenttype.getValue()).equals(Constants.ITEM_FAMILY_PLAN) || String.valueOf(componenttype.getValue()).equals(Constants.REBATE_SCHEDULE))) {
            if (fieldDdlb.getValue() != null && !fieldDdlb.getValue().equals(Constants.SELECT_ONE)) {
                String searchField = String.valueOf(fieldDdlb.getValue());
                if (searchField.equals(Constants.STATUS_FIELD)) {
                    boolean flag = true;
                    if (statusddlb.getValue() != null) {
                        String value = String.valueOf(statusddlb.getValue());
                        Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                flag = false;
                                if (String.valueOf(componenttype.getValue()).equals(Constants.COMPANY_FAMILY_PLAN)) {
                                    componentResultsContainer.getContainerProperty(item, Constants.COMPANY_STATUS).setValue(value);
                                }
                                if (String.valueOf(componenttype.getValue()).equals(Constants.ITEM_FAMILY_PLAN)) {
                                    componentResultsContainer.getContainerProperty(item, Constants.ITEM_STATUS_PROPERTY).setValue(value);
                                } else {
                                    componentResultsContainer.getContainerProperty(item, Constants.STATUS_S).setValue(value);
                                }

                            }
                        }
                        if (flag) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_ATLEAST_ONE_RECORD);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Value");
                    }
                }
                if (searchField.equals(Constants.START_DATE_HEADER)) {
                    if (startPeriod.getValue() != null) {
                        Date value = startPeriod.getValue();
                        Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                componentResultsContainer.getContainerProperty(item, Constants.START_DATE).setValue(value);
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Start Date");
                    }
                } else if (searchField.equals(Constants.END_DATE_HEADER)) {
                    if (startPeriod.getValue() != null) {
                        Date value = startPeriod.getValue();
                        Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                componentResultsContainer.getContainerProperty(item, Constants.END_DATE).setValue(value);
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select End Date");
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Field");
            }
        }
    }

    @UiHandler("addToTree")
    public void addToTreeLogic(Button.ClickEvent event) throws ParseException {

        try {
            Object root = dashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.parseInt(levelNo);
                String component = String.valueOf(componenttype.getValue());
                String userID = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                if (component.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    if (1 - levelNumber == 1) {
                        if (!cfpId.getValue().equals(Constants.EMPTY) && !cfpNo.getValue().equals(Constants.EMPTY) && !cfpName.getValue().equals(Constants.EMPTY) && cfpStatus.getValue() != null && cfpStartDate.getValue() != null && !cfpfileName.getValue().equals(Constants.EMPTY)  && salesInclusion.getValue() != null) {
                            List listcId = null;
                            List listcNo = null;
                            if (!Constants.EMPTY.equals(cfpId.getValue())) {
                                String query = "select upper(CFP_ID) from CFP_MODEL where CFP_ID='" + cfpId.getValue().toUpperCase(Locale.ENGLISH) + "'";
                                listcId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                            }
                            if (!Constants.EMPTY.equals(cfpNo.getValue())) {
                                String query = "select upper(CFP_NO) from CFP_MODEL where CFP_NO='" + cfpNo.getValue().toUpperCase(Locale.ENGLISH) + "'";
                                listcNo = HelperTableLocalServiceUtil.executeSelectQuery(query);
                            }
                            Object[] itemIds = dashboardResultsTable.getItemIds().toArray();
                            for (int i = 0; i < itemIds.length; i++) {
                                String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "levelNo").getValue());
                                if (level.equalsIgnoreCase("1")) {
                                    String cfpid = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_ID).getValue());
                                    String cfpno = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_NUMBER).getValue());
                                    if (cfpId.getValue().equalsIgnoreCase(cfpid)) {
                                        listcId.add(cfpId.getValue());
                                    }
                                    if (cfpNo.getValue().equalsIgnoreCase(cfpno)) {
                                        listcNo.add(cfpNo.getValue());
                                    }
                                }
                            }
                            boolean flag = false;
                            Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                            for (Object item : returnList1) {
                                Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                if (checked) {
                                    flag = true;
                                }
                            }
                            if (cfpId.getValue().length() > NumericConstants.THIRTY_EIGHT) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP ID length should be less than 38 characters.");
                            } else if (!cfpId.getValue().matches(STRING_REGEX)) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP ID Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpNo.getValue().length() > NumericConstants.FIFTY) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP No length should be less than 50 characters.");
                            } else if (!cfpNo.getValue().matches(STRING_REGEX)) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP No Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpName.getValue().length() > NumericConstants.HUNDRED) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP Name length should be less than NumericConstants.HUNDRED characters.");
                            } else if (!cfpName.getValue().matches(STRING_REGEX)) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP Name Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpEndDate.getValue() != null && cfpStartDate.getValue().after(cfpEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP End date should be after CFP Start Date.");
                            } else if (cfpEndDate.getValue() != null && cfpStartDate.getValue().getTime() == cfpEndDate.getValue().getTime()) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "CFP Start date and CFP End date are equal.");

                            } else if (listcId != null && !listcId.isEmpty()) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different CFP ID since the CFP ID  already exists");

                            } else if (listcNo != null && !listcNo.isEmpty()) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different CFP No since the CFP No  already exists");
                            } else if (!flag) {

                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_ATLEAST_ONE_RECORD);
                            } else {
                                final Object rootId = dashboardResultsTable.addItem();
                                dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.CFP);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(cfpId.getValue());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(cfpNo.getValue());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(cfpName.getValue());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.ONE);
                                HelperDTO cfpStatusdto = new HelperDTO();
                                cfpStatusdto.setId(0);
                                cfpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                dashboardResultsTable.getContainerProperty(rootId, Constants.STATUS_S).setValue(cfpStatusdto);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.START_DATE).setValue(cfpStartDate.getValue());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.END_DATE).setValue(cfpEndDate.getValue());

                                HelperDTO cfpTypedto = new HelperDTO();
                                cfpTypedto.setId(0);
                                cfpTypedto.setDescription(String.valueOf(cfpType.getValue()));
                                dashboardResultsTable.getContainerProperty(rootId, "marketType").setValue(cfpTypedto);
                                
                                HelperDTO salesInclusionDto = new HelperDTO();
                                salesInclusionDto.setId(0);
                                salesInclusionDto.setDescription(String.valueOf(salesInclusion.getValue()));
                                dashboardResultsTable.getContainerProperty(rootId, Constants.SALES_INCLUSION).setValue(salesInclusionDto);
                                
                                dashboardResultsTable.getContainerProperty(rootId, "marketType").setValue(cfpTypedto);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("1");
                                dashboardResultsTable.setParent(rootId, root);
                                dashboardResultsTable.setChildrenAllowed(rootId, true);
                                dashboardResultsTable.setCollapsed(root, false);
                                GcmGlobalDetails imtdItemPriceRebateDetails;
                                imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                imtdItemPriceRebateDetails.setItemId(cfpId.getValue());
                                imtdItemPriceRebateDetails.setItemName(cfpName.getValue());
                                imtdItemPriceRebateDetails.setItemStatusSid(Integer.parseInt(cfpStatus.getValue().toString()));
                                imtdItemPriceRebateDetails.setStartDate(cfpStartDate.getValue());
                                imtdItemPriceRebateDetails.setEndDate(cfpEndDate.getValue());
                                imtdItemPriceRebateDetails.setCreatedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                GcmGlobalDetails imtdItemPriceRebateDetails1 = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                String hiddenId = String.valueOf(imtdItemPriceRebateDetails1.getGcmGlobalDetailsSid());
                                Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                Set setA = new HashSet();
                                for (Object item : returnList) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        String companysid = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                        setA.add(companysid);
                                        String cname = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.COMPANY_NAME).getValue());
                                        String cno = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.COMPANY_NO).getValue());
                                        Date startDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        Date endDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                        String companyStatus = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.COMPANY_STATUS).getValue());
                                        GcmCompanyDetails tempCFP = GcmCompanyDetailsLocalServiceUtil.createGcmCompanyDetails(0);
                                        tempCFP.setCompanyMasterSid(Integer.parseInt(companysid));
                                        tempCFP.setCompanyStringId(companysid);
                                        tempCFP.setCompanyNo(cno);
                                        tempCFP.setCompanyName(cname);

                                        tempCFP.setCfpDetailsTradeClass(StringUtils.EMPTY);//Trading Partner Contract NO
                                        tempCFP.setCompanyStatusSid(Integer.parseInt(companyStatus));
                                        tempCFP.setCompanyStartDate(startDate);
                                        tempCFP.setCompanyEndDate(endDate);
                                        tempCFP.setCreatedDate(new Date());
                                        tempCFP.setModifiedDate(new Date());

                                        tempCFP.setCfpModelSid(Integer.parseInt(hiddenId));
                                        tempCFP.setCfpDetailsStartDate(cfpStartDate.getValue());
                                        tempCFP.setCfpDetailsEndDate(cfpEndDate.getValue());
                                        tempCFP.setCompanyStatus(companyStatus);

                                        tempCFP.setOperation("A");
                                        GcmCompanyDetailsLocalServiceUtil.addGcmCompanyDetails(tempCFP);
                                    }
                                }
                                if (!setA.isEmpty()) {
                                    selectedCompanies = getIdString(setA);
                                }
                                dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                clearCFPFields();
                            }

                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_ENTER_ALL_THE_FIELDS);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
                if (component.equals(Constants.ITEM_FAMILY_PLAN)) {
                    if (NumericConstants.TWO - levelNumber == 1) {
                        if (!ifpId.getValue().equals(Constants.EMPTY) && !ifpNo.getValue().equals(Constants.EMPTY) && !ifpName.getValue().equals(Constants.EMPTY) && ifpStatus.getValue() != null && ifpStartDate.getValue() != null && !ifpfileName.getValue().equals(Constants.EMPTY)) {
                            if (checkStartDate()) {
                                List listcId = null;
                                if (!Constants.EMPTY.equals(ifpId.getValue())) {
                                    String query = "select upper(IFP_ID) from IFP_MODEL where IFP_ID='" + ifpId.getValue().toUpperCase(Locale.ENGLISH) + "'";
                                    listcId = HelperTableLocalServiceUtil.executeSelectQuery(query);

                                }
                                Object[] itemIds = dashboardResultsTable.getItemIds().toArray();
                                for (int i = 0; i < itemIds.length; i++) {
                                    String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "levelNo").getValue());
                                    if (level.equalsIgnoreCase("2")) {
                                        String ifpid = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_ID).getValue());
                                        if (ifpId.getValue().equalsIgnoreCase(ifpid)) {
                                            listcId.add(ifpId.getValue());
                                        }

                                    }
                                }
                                boolean flag = false;
                                Set setA = new HashSet();
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                    }
                                }
                                if (ifpId.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP ID length should be less than 50 characters.");
                                } else if (!ifpId.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpNo.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP No length should be less than 50 characters.");
                                } else if (!ifpNo.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP No Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpName.getValue().length() > NumericConstants.HUNDRED) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP Name length should be less than NumericConstants.HUNDRED characters.");
                                } else if (!ifpName.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpEndDate.getValue() != null && ifpStartDate.getValue().after(ifpEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP End date should be after IFP Start Date.");
                                } else if (ifpEndDate.getValue() != null && ifpStartDate.getValue().getTime() == ifpEndDate.getValue().getTime()) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "IFP Start date and IFP End date are equal.");

                                } else if (listcId != null && !listcId.isEmpty()) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different IFP ID since the IFP ID  already exists");

                                } else if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_ATLEAST_ONE_RECORD);
                                } else {
                                    final Object rootId = dashboardResultsTable.addItem();
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(ifpId.getValue());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(ifpNo.getValue());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(ifpName.getValue());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                                    HelperDTO cfpStatusdto = new HelperDTO();
                                    cfpStatusdto.setId(0);
                                    cfpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.STATUS_S).setValue(cfpStatusdto);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.START_DATE).setValue(ifpStartDate.getValue());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.END_DATE).setValue(ifpEndDate.getValue());

                                    HelperDTO ifpStatusdto = new HelperDTO();
                                    ifpStatusdto.setId(0);
                                    ifpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                    
                                    HelperDTO ifpStatusdto1 = new HelperDTO();
                                    ifpStatusdto1.setId(0);
                                    ifpStatusdto1.setDescription(String.valueOf(ifpStatus.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.STATUS_S).setValue(ifpStatusdto1);
                                    
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.MARKET_TYPE).setValue(ifpStatusdto);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("1");
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, true);
                                    dashboardResultsTable.setCollapsed(root, false);
                                    GcmGlobalDetails imtdItemPriceRebateDetails;
                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                    imtdItemPriceRebateDetails.setItemId(ifpId.getValue());
                                    imtdItemPriceRebateDetails.setItemName(ifpName.getValue());
                                    imtdItemPriceRebateDetails.setItemStatusSid(Integer.parseInt(ifpStatus.getValue().toString()));
                                    imtdItemPriceRebateDetails.setStartDate(ifpStartDate.getValue());
                                    imtdItemPriceRebateDetails.setEndDate(ifpEndDate.getValue());
                                    imtdItemPriceRebateDetails.setItemType(ifptype.getValue().toString());
                                    imtdItemPriceRebateDetails.setCreatedBy(Integer.parseInt(userID));
                                    imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                    imtdItemPriceRebateDetails.setModifiedBy(Integer.parseInt(userID));
                                    imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                    GcmGlobalDetails imtdItemPriceRebateDetails1 = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails1.getGcmGlobalDetailsSid());

                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                        for (Object item : returnList) {
                                            Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                            if (checked) {
                                                GcmItemDetails tempIFP = GcmItemDetailsLocalServiceUtil.createGcmItemDetails(0);
                                                String itemsid = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                                setA.add(itemsid);
                                                tempIFP.setItemMasterSid(Integer.parseInt(itemsid));
                                                String iname = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_NAME_PROPERTY).getValue());
                                                String cno = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_NO_PROPERTY).getValue());
                                                Date startDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                                Date endDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                                String itemStatus = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_STATUS_PROPERTY).getValue());
                                                tempIFP.setItemNo(cno);
                                                tempIFP.setItemName(iname);
                                                tempIFP.setItemStatus(itemStatus);
                                                tempIFP.setIfpModelSid(Integer.parseInt(hiddenId));
                                                tempIFP.setCreatedDate(new Date());
                                                tempIFP.setCreatedBy(Integer.parseInt(userID));
                                                tempIFP.setItemStatusSid(Integer.parseInt(itemStatus));
                                                tempIFP.setIfpDetailsStartDate(startDate);
                                                tempIFP.setIfpDetailsEndDate(endDate);
                                                tempIFP.setItemEndDate(endDate);
                                                tempIFP.setModifiedDate(new Date());
                                                tempIFP.setOperation("A");
                                                GcmItemDetailsLocalServiceUtil.addGcmItemDetails(tempIFP);
                                            }
                                        }

                                        if (!setA.isEmpty()) {
                                            selectedItems = getIdString(setA);
                                        }
                                    

                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                    clearIFPFields();
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter Start date for all the selected item");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_ENTER_ALL_THE_FIELDS);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
                if (component.equals(Constants.PRICE_SCHEDULE)) {
                    if (NumericConstants.THREE - levelNumber == 1) {
                        String itemFamilyPlanID = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                        String queryName = "temp Table IFP Select";
                        if (checkSameItemInPs(itemFamilyPlanID, componentDetailsSelectedItem.getItemIds(), queryName)) {
                            if (!psId.getValue().equals(Constants.EMPTY) && !psNo.getValue().equals(Constants.EMPTY) && !psName.getValue().equals(Constants.EMPTY) && psStatus.getValue() != null && psStartDate.getValue() != null && !psfileName.getValue().equals(Constants.EMPTY)) {
                                String query = "select * from PS_MODEL where PS_NAME='" + psName.getValue() + "'";
                                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                                if (list != null && !list.isEmpty()) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Price Schedule Name already exist. Please Enter Different Name");
                                    return;
                                }
                                boolean flag = false;
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                        Object sDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        if (sDate == null) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_NAME_PROPERTY).getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select " + itemName + " Start Date");
                                            return;
                                        }
                                        String price = String.valueOf(componentResultsContainer.getContainerProperty(item, StringConstantsUtil.PRICE_PROPERTY).getValue());
                                        if (price.equals(StringUtils.EMPTY)) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_NAME_PROPERTY).getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter " + itemName + " Price");
                                            return;
                                        }
                                    }
                                }

                                if (psId.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS ID length should be less than 50 characters.");
                                    return;
                                } else if (!psId.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (psNo.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS No length should be less than 50 characters.");
                                    return;
                                } else if (!psNo.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS No Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (psName.getValue().length() > NumericConstants.HUNDRED) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS Name length should be less than NumericConstants.HUNDRED characters.");
                                    return;
                                } else if (!psName.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                }

                                if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_ATLEAST_ONE_RECORD);
                                }
                                String priceScheduleId = String.valueOf(psId.getValue());
                                String priceScheduleNo = psNo.getValue();
                                String priceScheduleName = psName.getValue();
                                int priceScheduleStatus = Integer.parseInt(String.valueOf(psStatus.getValue()));
                                Date psDate = psStartDate.getValue();
                                String fName = psfileName.getValue();

                                GcmGlobalDetails imtdItemPriceRebateDetails;
                                imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                imtdItemPriceRebateDetails.setItemId(priceScheduleId);
                                imtdItemPriceRebateDetails.setItemNo(priceScheduleNo);
                                imtdItemPriceRebateDetails.setItemName(priceScheduleName);
                                imtdItemPriceRebateDetails.setItemStatusSid(priceScheduleStatus);
                                imtdItemPriceRebateDetails.setStartDate(psDate);
                                imtdItemPriceRebateDetails.setFormulaName(fName);
                                imtdItemPriceRebateDetails.setCreatedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    final Object rootId = dashboardResultsTable.addItem();
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(priceScheduleId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(priceScheduleNo);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(priceScheduleName);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("1");
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, true);
                                    dashboardResultsTable.setCollapsed(root, false);
                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                    for (Object item : returnList) {
                                        Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                        if (checked) {
                                            String ifpModelId = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                            String itemId = String.valueOf(componentResultsContainer.getContainerProperty(item, StringConstantsUtil.ITEM_MASTER_ID).getValue());
                                            Object startDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                            String price = String.valueOf(componentResultsContainer.getContainerProperty(item, StringConstantsUtil.PRICE_PROPERTY).getValue());
                                            String sDate = df.format(startDate);
                                            String eDate = Constants.EMPTY;
                                            if (componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue() != null) {
                                                Object endDate = componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                                eDate = df.format(endDate);
                                            }

                                            ImtdPsDetails imtdPsDetails;
                                            imtdPsDetails = ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(0);
                                            imtdPsDetails.setPsModelSid(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                            imtdPsDetails.setIfpModelSid(Integer.parseInt(ifpModelId));
                                            imtdPsDetails.setItemMasterSid(Integer.parseInt(itemId));
                                            imtdPsDetails.setPsDtlsContPriceStartdate(df.parse(sDate));
                                            if (!eDate.equals(Constants.EMPTY)) {
                                                imtdPsDetails.setPsDtlsContPriceEnddate(df.parse(eDate));
                                            }
                                            imtdPsDetails.setPsDetailsPrice(Double.parseDouble(price));
                                            imtdPsDetails.setCreatedBy(Integer.parseInt(userID));
                                            imtdPsDetails.setCreatedDate(new Date());
                                            imtdPsDetails.setModifiedBy(Integer.parseInt(userID));
                                            imtdPsDetails.setModifiedDate(new Date());
                                            ImtdPsDetailsLocalServiceUtil.addImtdPsDetails(imtdPsDetails);

                                            clearPSFields();
                                        }
                                    }
                                
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_ENTER_ALL_THE_FIELDS);
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
                if (component.equals(Constants.REBATE_SCHEDULE)) {
                    if (NumericConstants.FOUR - levelNumber == 1) {
                        String itemFamilyPlanId = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                        if (checkSameItemInPs(itemFamilyPlanId, componentDetailsSelectedItem.getItemIds(), "temp Table PS Select")) {
                            if (!rsId.getValue().equals(Constants.EMPTY) && !rsNumber.getValue().equals(Constants.EMPTY)
                                    && !rsName.getValue().equals(Constants.EMPTY) && !rsStatus.getValue().equals(Constants.ZEROSTRING)
                                    && !rsType.getValue().equals(Constants.ZEROSTRING) && rsStartDate.getValue() != null && rsEndDate.getValue() != null
                                    && !paymentFrequency.getValue().equals(Constants.ZEROSTRING) && !paymentMethod.getValue().equals(Constants.ZEROSTRING)
                                    && (!rebatePlanLevel.getValue().equals(Constants.ZEROSTRING) ||  rebatePlanLevel.getValue() != null) && !rsRarType.getValue().equals(Constants.ZEROSTRING)
                                    && !calendar.getValue().equals(Constants.ZEROSTRING)
                                    && deductionInclusion.getValue() != null && calculationLevel.getValue() != null && rebateFrequency.getValue() != null
                                    && calculationType.getValue() != null) {
                                String query = "select * from RS_MODEL where RS_NAME='" + rsName.getValue() + "'";
                                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                                if (list != null && !list.isEmpty()) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Rebate Schedule Name already exist. Please Enter Different Name");
                                    return;
                                }
                                boolean flag = false;
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                        Object sDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        if (sDate == null) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.ITEM_NAME_PROPERTY).getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select " + itemName + " Start Date");
                                            return;
                                        }
                                    }
                                }

                                if (rsId.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS ID length should be less than 50 characters.");
                                    return;
                                } else if (!rsId.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (rsNumber.getValue().length() > NumericConstants.FIFTY) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS No length should be less than 50 characters.");
                                    return;
                                } else if (!rsNumber.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS No Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (rsName.getValue().length() > NumericConstants.HUNDRED) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS Name length should be less than NumericConstants.HUNDRED characters.");
                                    return;
                                } else if (!rsName.getValue().matches(REGEX_STRING)) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (rsEndDate.getValue() != null && rsStartDate.getValue().after(rsEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS End date should be after RS Start Date.");
                                    return;
                                } else if (rsEndDate.getValue() != null && rsStartDate.getValue().getTime() == rsEndDate.getValue().getTime()) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "RS Start date and RS End date are equal.");
                                    return;
                                }

                                if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_ATLEAST_ONE_RECORD);
                                }
                                String rebateScheduleId = String.valueOf(rsId.getValue());
                                String rebateScheduleNo = rsNumber.getValue();
                                String rebateScheduleName = rsName.getValue();
                                int rebateScheduleStatus = Integer.parseInt(String.valueOf(rsStatus.getValue()));
                                Date startDate = rsStartDate.getValue();
                                Date endDate = rsEndDate.getValue();
                                String paymentFre = String.valueOf(paymentFrequency.getValue());
                                String rebateType = String.valueOf(rsType.getValue());
                                String rsProgramType = String.valueOf(rsRarType.getValue());
                                String rpLevel = String.valueOf(rebatePlanLevel.getValue());
                                String payMethod = String.valueOf(paymentMethod.getValue());
                                String cal = String.valueOf(calendar.getValue());
                                GcmGlobalDetails imtdItemPriceRebateDetails;
                                imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                imtdItemPriceRebateDetails.setItemId(rebateScheduleId);
                                imtdItemPriceRebateDetails.setItemNo(rebateScheduleNo);
                                imtdItemPriceRebateDetails.setItemName(rebateScheduleName);
                                imtdItemPriceRebateDetails.setItemStatusSid(rebateScheduleStatus);
                                imtdItemPriceRebateDetails.setStartDate(startDate);
                                imtdItemPriceRebateDetails.setEndDate(endDate);
                                imtdItemPriceRebateDetails.setItemType(rebateType);
                                imtdItemPriceRebateDetails.setPaymentFrequency(paymentFre);
                                imtdItemPriceRebateDetails.setFormulaId(rsProgramType);
                                imtdItemPriceRebateDetails.setFormulaName(rpLevel);
                                imtdItemPriceRebateDetails.setPaymentMethod(payMethod);
                                imtdItemPriceRebateDetails.setCalendar(cal);
                                imtdItemPriceRebateDetails.setCreatedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(Integer.parseInt(userID));
                                imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                final Object rootId = dashboardResultsTable.addItem();

                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(rebateScheduleId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(rebateScheduleNo);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(rebateScheduleName);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                    
                                    HelperDTO paymentFrequencyDto = new HelperDTO();
                                    paymentFrequencyDto.setId(0);
                                    paymentFrequencyDto.setDescription(String.valueOf(paymentFrequency.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.PAYMENT_FREQUENCY_PROP).setValue(paymentFrequencyDto);
                                    HelperDTO paymentMethodDto = new HelperDTO();
                                    paymentMethodDto.setId(0);
                                    paymentMethodDto.setDescription(String.valueOf(paymentMethod.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.PAYMENT_METHOD_PROP).setValue(paymentMethodDto);
                                    HelperDTO rsrebateProgramTypeDto = new HelperDTO();
                                    rsrebateProgramTypeDto.setId(0);
                                    rsrebateProgramTypeDto.setDescription(String.valueOf(rsRarType.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId,Constants.REBATE_PROGRAM_TYPE).setValue(rsrebateProgramTypeDto);
                                    HelperDTO deductionInclusionDto = new HelperDTO();
                                    deductionInclusionDto.setId(0);
                                    deductionInclusionDto.setDescription(String.valueOf(deductionInclusion.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DEDUCTION_INCLUSION).setValue(deductionInclusionDto);
                                    HelperDTO calculationLevelDto = new HelperDTO();
                                    calculationLevelDto.setId(0);
                                    calculationLevelDto.setDescription(String.valueOf(calculationLevel.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CALCULATION_LEVEL).setValue(calculationLevelDto);
                                    HelperDTO calculationTypeDto = new HelperDTO();
                                    calculationTypeDto.setId(0);
                                    calculationTypeDto.setDescription(String.valueOf(calculationType.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CALCULATION_TYPE).setValue(calculationTypeDto);
                                    HelperDTO rebatePlanLevelDto = new HelperDTO();
                                    rebatePlanLevelDto.setId(0);
                                    rebatePlanLevelDto.setDescription(String.valueOf(rebatePlanLevel.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.REBATE_PLAN_LEVEL_PROP).setValue(rebatePlanLevelDto);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.REBATE_SCHEDULE_CATEGORY).setValue(String.valueOf(rebateFrequency.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("1");
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, false);
                                    dashboardResultsTable.setCollapsed(root, false);

                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                    for (Object item : returnList) {
                                        Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                        if (checked) {
                                            String ifpModelId = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                            String itemId = String.valueOf(componentResultsContainer.getContainerProperty(item, StringConstantsUtil.ITEM_MASTER_ID).getValue());
                                            Object sDate1 = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                            String sDate = df.format(sDate1);

                                            ImtdPsDetails imtdPsDetails;
                                            imtdPsDetails = ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(0);
                                            imtdPsDetails.setPsModelSid(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());//hidden id
                                            imtdPsDetails.setIfpModelSid(Integer.parseInt(ifpModelId));
                                            imtdPsDetails.setItemMasterSid(Integer.parseInt(itemId));
                                            imtdPsDetails.setPsDtlsContPriceStartdate(df.parse(sDate));
                                            imtdPsDetails.setPsDetailsPrice(Double.parseDouble("10"));
                                            imtdPsDetails.setCreatedBy(Integer.parseInt(userID));
                                            imtdPsDetails.setCreatedDate(new Date());
                                            imtdPsDetails.setModifiedBy(Integer.parseInt(userID));
                                            imtdPsDetails.setModifiedDate(new Date());
                                            ImtdPsDetailsLocalServiceUtil.addImtdPsDetails(imtdPsDetails);
                                            clearPSFields();
                                        }
                                    }
                                
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Enter all the fields in Component Selection section");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP and PS");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void loadComponentDetailsSearchSection() {
        if (componenttype.getValue() != null) {
            if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                componentDetailsSearch.removeAllItems();
                componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setNullSelectionAllowed(true);
                componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.addItem(Constants.COMPANY_ID);
                componentDetailsSearch.addItem(Constants.COMPANYNAME);
                componentDetailsSearch.addItem(Constants.COMPANYNO);
                componentDetailsSearch.addItem(Constants.COMPANYSTATUS);
                componentDetailsSearch.addItem(Constants.COMPANYTYPE);
                componentDetailsSearch.addItem(Constants.COMPANYCATEGORY);
                componentDetailsSearch.addItem(Constants.TRADECLASS);
            }
            if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                componentDetailsSearch.removeAllItems();
                componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setNullSelectionAllowed(true);
                componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.addItem(Constants.ITEM_ID);
                componentDetailsSearch.addItem(Constants.ITEM_NAME);
                componentDetailsSearch.addItem(Constants.ITEM_NO);
                componentDetailsSearch.addItem(Constants.ITEM_STATUS);
                componentDetailsSearch.addItem(Constants.ITEM_TYPE);
                componentDetailsSearch.addItem(Constants.BRAND);
                componentDetailsSearch.addItem(Constants.FORM);
                componentDetailsSearch.addItem(Constants.STRENGTH);
                componentDetailsSearch.addItem(Constants.THERAPY_CLASS);
                componentDetailsSearch.addItem(" Item Start Date");
                componentDetailsSearch.addItem(Constants.ITEM_END_DATE);
            }
        }
    }

    public void savecontract(Object item) throws  ParseException {
        try {
            Map<String, String> map = new HashMap<>();
            int psModelSid = 0;
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            int contractMasterSid = 0;
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ZEROSTRING)) {
                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                String contractHolder = String.valueOf(dashboardResultsTable.getContainerProperty(item, "contractHolder").getValue());
                int contractType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int status = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.STATUS_S).getValue()).getId();
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.END_DATE).getValue();
                ContractMaster newComponentContractMaster;
                newComponentContractMaster = ContractMasterLocalServiceUtil.createContractMaster(0);
                newComponentContractMaster.setContractId(contractId);
                newComponentContractMaster.setContractNo(contractNo);
                newComponentContractMaster.setContractName(contractName);
                newComponentContractMaster.setContractType(contractType);
                newComponentContractMaster.setContHoldCompanyMasterSid(contractHolder);
                newComponentContractMaster.setProcessStatus(true);
                newComponentContractMaster.setSource("BPI");
                newComponentContractMaster.setContractStatus(status);
                newComponentContractMaster.setCreatedBy(Integer.parseInt(userId));
                newComponentContractMaster.setStartDate(startDate);
                newComponentContractMaster.setEndDate(endDate);
                newComponentContractMaster.setInboundStatus("A");
                newComponentContractMaster.setCreatedDate(new Date());
                newComponentContractMaster.setModifiedDate(new Date());
                newComponentContractMaster = ContractMasterLocalServiceUtil.addContractMaster(newComponentContractMaster);
                contractMasterSid = newComponentContractMaster.getContractMasterSid();
                dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(contractMasterSid));
                int aliasType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, "aliasType").getValue()).getId();
                Date aliasSdate = (Date) dashboardResultsTable.getContainerProperty(item, "aliasstartdate").getValue();
                String aliasNumber = String.valueOf(dashboardResultsTable.getContainerProperty(item, "aliasNumber").getValue());
                Date aliasEdate = (Date) dashboardResultsTable.getContainerProperty(item, "aliasenddate").getValue();
                ContractAliasMaster caMaster = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                caMaster.setContractAliasNo(aliasNumber);
                caMaster.setContractAliasType(aliasType);
                caMaster.setStartDate(aliasSdate);
                caMaster.setEndDate(aliasEdate);
                caMaster.setModifiedDate(new Date());
                caMaster.setCreatedBy(Integer.parseInt(userId));
                caMaster.setCreatedDate(new Date());
                caMaster.setSource("BPI");
                caMaster.setInboundStatus("A");
                caMaster.setContractMasterSid(contractMasterSid);
                ContractAliasMasterLocalServiceUtil.addContractAliasMaster(caMaster);

            } else if (level.equals(Constants.THREE)) {
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                String query = queryUtils.getTempTableValue(temptableSId);
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (list != null && !list.isEmpty()) {
                    Object[] obj = (Object[]) list.get(0);
                    PsModel psModel;
                    psModel = PsModelLocalServiceUtil.createPsModel(0);
                    psModel.setPsId(String.valueOf(obj[0]));
                    psModel.setPsNo(String.valueOf(obj[1]));
                    psModel.setPsName(String.valueOf(obj[NumericConstants.TWO]));
                    psModel.setPsStatus(obj[NumericConstants.THREE] != null && !obj[NumericConstants.THREE].toString().equals(StringUtils.EMPTY) ? Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])) : 0);
                    if (obj[NumericConstants.FOUR] != null) {
                        String date1 = df.format(obj[NumericConstants.FOUR]);
                        psModel.setPsStartDate(df.parse(date1));
                    } else {
                        psModel.setPsStartDate(null);
                    }
                    psModel.setCreatedBy(Integer.parseInt(userId));
                    psModel.setCreatedDate(new Date());
                    psModel.setModifiedBy(Integer.parseInt(userId));
                    psModel.setModifiedDate(new Date());
                    psModel.setInboundStatus("A");
                    psModel = PsModelLocalServiceUtil.addPsModel(psModel);
                    psModelSid = psModel.getPsModelSid();
                    copyContractLogic.insertPsDetails(psModelSid, userId, temptableSId);
                    Object ifpItem = dashboardResultsTable.getParent(item);
                    String ifpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(ifpItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = dashboardResultsTable.getParent(ifpItem);
                    String cfpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    PsContract psContract = PsContractLocalServiceUtil.createPsContract(0);
                    psContract.setPsModelSid(psModel.getPsModelSid());
                    psContract.setPsName(psModel.getPsName());
                    psContract.setPsNo(psModel.getPsNo());
                    psContract.setPsStartDate(psModel.getPsStartDate());
                    psContract.setPsStatus(psModel.getPsStatus());
                    psContract.setContractMasterSid(Integer.parseInt(contractSId));
                    psContract.setCfpContractSid(cfpContractId);
                    psContract.setIfpContractSid(ifpContractId);
                    psContract.setInboundStatus("A");
                    psContract.setRecordLockStatus(false);
                    psContract.setCreatedBy(Integer.parseInt(userId));
                    psContract.setCreatedDate(new Date());
                    psContract.setModifiedBy(Integer.parseInt(userId));
                    psContract.setModifiedDate(new Date());
                    psContract = PsContractLocalServiceUtil.addPsContract(psContract);
                    savePs(String.valueOf(psContract.getPsContractSid()), psModel.getPsModelSid());
                    dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(psContract.getPsContractSid()));
                }

            } else if (level.equals(Constants.FOUR)) {
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                String query = queryUtils.getTempTableValueForPS(temptableSId);
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (list != null && !list.isEmpty()) {
                    Object[] obj = (Object[]) list.get(0);
                    RsModel rsModel = RsModelLocalServiceUtil.createRsModel(0);
                    rsModel.setRsId(String.valueOf(obj[0]));
                    rsModel.setRsNo(String.valueOf(obj[1]));
                    rsModel.setRsName(String.valueOf(obj[NumericConstants.TWO]));
                    rsModel.setRsStatus(obj[NumericConstants.THREE] != null && !obj[NumericConstants.THREE].toString().equals(StringUtils.EMPTY) ? Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])) : 0);
                    if (obj[NumericConstants.FOUR] != null) {
                        String date1 = df.format(obj[NumericConstants.FOUR]);
                        rsModel.setRsStartDate(df.parse(date1));
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        String date1 = df.format(obj[NumericConstants.FIVE]);
                        rsModel.setRsEndDate(df.parse(date1));
                    }
                    rsModel.setRsType(Integer.parseInt(String.valueOf(obj[NumericConstants.SIX])));
                    rsModel.setPaymentFrequency(Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN])));
                    rsModel.setRebateProgramType(Integer.parseInt(String.valueOf(obj[NumericConstants.EIGHT])));
                    rsModel.setRebatePlanLevel(String.valueOf(obj[NumericConstants.NINE]));
                    rsModel.setPaymentMethod(Integer.parseInt(String.valueOf(obj[NumericConstants.TEN])));
                    rsModel.setRsCalendar(Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])));
                    rsModel.setCreatedBy(Integer.parseInt(userId));
                    rsModel.setCreatedDate(new Date());
                    rsModel.setModifiedBy(Integer.parseInt(userId));
                    rsModel.setModifiedDate(new Date());
                    rsModel.setInboundStatus("A");
                    rsModel = RsModelLocalServiceUtil.addRsModel(rsModel);
                    copyContractLogic.insertIntoRsDetails(temptableSId, userId, rsModel.getRsModelSid());
                    Object psItem = dashboardResultsTable.getParent(item);
                    String psContractId = String.valueOf(dashboardResultsTable.getContainerProperty(psItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentIFPItem = dashboardResultsTable.getParent(psItem);
                    String ifpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentIFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = dashboardResultsTable.getParent(parentIFPItem);
                    String cfpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    
                    int rsPaymentFrequency = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.PAYMENT_FREQUENCY_PROP).getValue().toString());
                    int rsPaymentMethod = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.PAYMENT_METHOD_PROP).getValue().toString());
                    int rsProgramType= Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.REBATE_PROGRAM_TYPE).getValue().toString());
                    int rsCalculationLevel = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.CALCULATION_LEVEL).getValue().toString());
                    int rsCalculationType = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.CALCULATION_TYPE).getValue().toString());
                    int reBateFrequency = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.REBATE_SCHEDULE_CATEGORY).getValue().toString());
                    String rsDeductionInclusion = dashboardResultsTable.getContainerProperty(item, Constants.DEDUCTION_INCLUSION).getValue().toString();
                    String rsRebateCategory = dashboardResultsTable.getContainerProperty(item, Constants.REBATE_PLAN_LEVEL_PROP).getValue().toString();
                    
                    
                    
                    RsContract rsContract = RsContractLocalServiceUtil.createRsContract(0);
                    int modelId = rsModel.getRsModelSid();
                    String name = rsModel.getRsName();
                    String id = rsModel.getRsId();
                    String no = rsModel.getRsNo();
                    int type = rsModel.getRsType();
                    int rpType = rsModel.getRebateProgramType();
                    Date stDate = rsModel.getRsStartDate();
                    int calender = rsModel.getRsCalendar();
                    int status=rsModel.getRsStatus();
                    rsContract.setRsModelSid(modelId);
                    rsContract.setRsId(id);
                    rsContract.setRsNo(no);
                    rsContract.setRsName(name);
                    rsContract.setRsType(type);
                    rsContract.setRsStatus(status);
                    rsContract.setRebateProgramType(rsProgramType);
                    rsContract.setRebatePlanLevel(rsRebateCategory);
                    rsContract.setRsStartDate(stDate);
                    rsContract.setContractMasterSid(Integer.parseInt(contractSId));
                    rsContract.setCfpContractSid(cfpContractId);
                    rsContract.setIfpContractSid(ifpContractId);
                    rsContract.setPsContractSid(psContractId);
                    rsContract.setRebateFrequency(reBateFrequency);
                    rsContract.setPaymentFrequency(rsPaymentFrequency);
                    rsContract.setPaymentMethod(rsPaymentMethod);
                    rsContract.setRsCalendar(String.valueOf(calender));
                    rsContract.setInboundStatus("A");
                    rsContract.setRecordLockStatus(false);
                    rsContract.setCreatedBy(Integer.parseInt(userId));
                    rsContract.setCreatedDate(new Date());
                    rsContract.setModifiedBy(Integer.parseInt(userId));
                    rsContract.setModifiedDate(new Date());
                    
                    rsContract.setDeductionInclusion(rsDeductionInclusion);
                    rsContract.setCalculationLevel(rsCalculationLevel);
                    rsContract.setCalculationType(rsCalculationType);
                    rsContract = RsContractLocalServiceUtil.addRsContract(rsContract);
                    saveRs(String.valueOf(rsContract.getRsContractSid()), rsModel.getRsModelSid());
                }
            } else if (level.equals(Constants.ONE)) {

                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                int contractType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int salesInclusionValue = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.SALES_INCLUSION).getValue().toString());
                int status = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.STATUS_S).getValue().toString());
                                
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.END_DATE).getValue();
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                Object contractItem = dashboardResultsTable.getParent(item);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                CfpModel cfpmodel = CfpModelLocalServiceUtil.createCfpModel(0);
                cfpmodel.setCfpId(contractId);
                cfpmodel.setCfpName(contractName);
                cfpmodel.setCfpNo(contractNo);
                cfpmodel.setCfpStatus(status);
                cfpmodel.setCfpStartDate(startDate);
                cfpmodel.setCfpType(contractType);
                cfpmodel.setSalesInclusion(salesInclusionValue);
                cfpmodel.setCreatedBy(Integer.parseInt(userId));
                cfpmodel.setCreatedDate(new Date());
                cfpmodel.setCfpEndDate(endDate);
                cfpmodel.setSource("BPI");
                cfpmodel.setInboundStatus("A");
                cfpmodel.setModifiedDate(new Date());
                final CfpModel companyFamily = CfpModelLocalServiceUtil.addCfpModel(cfpmodel);
                updateToCFPDetails(companyFamily.getCfpModelSid(), temptableSId);
                final CfpContract cfpMasterAttached = CfpContractLocalServiceUtil.createCfpContract(0);
                cfpMasterAttached.setCfpName(companyFamily.getCfpName());
                cfpMasterAttached.setCfpModelSid(companyFamily.getCfpModelSid());
                cfpMasterAttached.setCfpNo(companyFamily.getCfpNo());
                cfpMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
                cfpMasterAttached.setSource("BPI");
                cfpMasterAttached.setCfpType(companyFamily.getCfpType());
                cfpMasterAttached.setSalesInclusion(companyFamily.getSalesInclusion());
                cfpMasterAttached.setCfpCategory(companyFamily.getCfpCategory());
                cfpMasterAttached.setCfpDesignation(companyFamily.getCfpDesignation());
                cfpMasterAttached.setParentCfpId(companyFamily.getParentCfpId());
                cfpMasterAttached.setParentCfpName(companyFamily.getParentCfpName());
                cfpMasterAttached.setCfpStatus(companyFamily.getCfpStatus());
                cfpMasterAttached.setCfpTradeClass(companyFamily.getCfpTradeClass());
                cfpMasterAttached.setCfpStartDate(companyFamily.getCfpStartDate());
                cfpMasterAttached.setCfpEndDate(companyFamily.getCfpEndDate());
                cfpMasterAttached.setCreatedBy(Integer.parseInt(userId));
                cfpMasterAttached.setCreatedDate(new Date());
                cfpMasterAttached.setModifiedBy(Integer.parseInt(userId));
                cfpMasterAttached.setModifiedDate(new Date());
                cfpMasterAttached.setCfpContractAttachedDate(new Date());
                cfpMasterAttached.setRecordLockStatus(false);
                cfpMasterAttached.setInboundStatus("A");
                CfpContract cm1 = CfpContractLocalServiceUtil.addCfpContract(cfpMasterAttached);
                dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(cm1.getCfpContractSid()));
                saveCfp(String.valueOf(cm1.getCfpContractSid()), companyFamily.getCfpModelSid());

            } else if (level.equals(Constants.TWO)) {
                String id = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String no = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String name = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                int type = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int status = Integer.parseInt(dashboardResultsTable.getContainerProperty(item, Constants.STATUS_S).getValue().toString());
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.END_DATE).getValue();
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                IfpModel ifpmodel = IfpModelLocalServiceUtil.createIfpModel(0);
                ifpmodel.setIfpId(id);
                ifpmodel.setIfpName(name);
                ifpmodel.setIfpNo(no);
                ifpmodel.setIfpStatus(status);
                ifpmodel.setIfpStartDate(startDate);
                ifpmodel.setIfpEndDate(endDate);
                ifpmodel.setIfpType(type);
                ifpmodel.setCreatedBy(Integer.parseInt(userId));
                ifpmodel.setCreatedDate(new Date());
                ifpmodel.setSource("BPI");
                ifpmodel.setInboundStatus("A");
                ifpmodel.setModifiedDate(new Date());
                final IfpModel itemFamily = IfpModelLocalServiceUtil.addIfpModel(ifpmodel);
                map.put(temptableSId, String.valueOf(itemFamily.getIfpModelSid()));
                updateToIfpDetails(itemFamily.getIfpModelSid(), temptableSId);
                final IfpContract newComponentIfpMasterAttached = IfpContractLocalServiceUtil.createIfpContract(0);

                newComponentIfpMasterAttached.setIfpModelSid(itemFamily.getIfpModelSid());
                newComponentIfpMasterAttached.setIfpName(itemFamily.getIfpName());
                newComponentIfpMasterAttached.setIfpNo(itemFamily.getIfpNo());
                newComponentIfpMasterAttached.setSource("BPI");
                newComponentIfpMasterAttached.setIfpType(itemFamily.getIfpType());
                newComponentIfpMasterAttached.setIfpCategory(itemFamily.getIfpCategory());
                newComponentIfpMasterAttached.setIfpDesignation(itemFamily.getIfpDesignation());
                newComponentIfpMasterAttached.setParentIfpId(itemFamily.getParentIfpId());
                newComponentIfpMasterAttached.setParentIfpName(itemFamily.getParentIfpName());
                newComponentIfpMasterAttached.setIfpStatus(itemFamily.getIfpStatus());
                newComponentIfpMasterAttached.setIfpStartDate(itemFamily.getIfpStartDate());
                newComponentIfpMasterAttached.setIfpEndDate(itemFamily.getIfpEndDate());
                newComponentIfpMasterAttached.setIfpContractAttachedDate(new Date());
                newComponentIfpMasterAttached.setCreatedBy(Integer.parseInt(userId));
                newComponentIfpMasterAttached.setCreatedDate(new Date());
                newComponentIfpMasterAttached.setModifiedBy(Integer.parseInt(userId));
                newComponentIfpMasterAttached.setModifiedDate(new Date());
                newComponentIfpMasterAttached.setRecordLockStatus(false);
                newComponentIfpMasterAttached.setInboundStatus("A");
                Object parentItem = dashboardResultsTable.getParent(item);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, Constants.SAVED_SYSTEM_ID).getValue());
                Object contractItem = dashboardResultsTable.getParent(parentItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                newComponentIfpMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
                newComponentIfpMasterAttached.setCfpContractSid(parentCFPId);
                updatePsAndRSModelSid(dashboardResultsTable.getChildren(item), itemFamily.getIfpModelSid());
                IfpContract im1 = IfpContractLocalServiceUtil.addIfpContract(newComponentIfpMasterAttached);
                saveIfp(String.valueOf(im1.getIfpContractSid()), itemFamily.getIfpModelSid());
                dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(im1.getIfpContractSid()));
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public void saveCfp(String cfpId, Integer cfpModelId) {
        copyContractLogic.saveCfp(cfpId, cfpModelId);
    }

    public void saveIfp(String ifpId, Integer ifpModelId) {
        copyContractLogic.saveIfp(ifpId, ifpModelId);
    }

    public void savePs(String psid, Integer psModelId) {
        copyContractLogic.savePs(psid, psModelId);
    }

    public void saveRs(String rsid, Integer rsModelId) {
        copyContractLogic.saveRs(rsid, rsModelId);
    }

    @UiHandler("levelRemoveBtn")
    public void removeLogic(Button.ClickEvent event) {

        Object root = dashboardResultsTable.getValue();
        if (root != null) {
            Boolean flag = dashboardResultsTable.hasChildren(root);
            if (!flag) {
                dashboardResultsTable.removeItem(root);
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please remove all children nodes before removing a parent node.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please highlight a component to Remove");
        }
    }

    @UiHandler("populateDetails")
    public void populateDetailsLogic(Button.ClickEvent event) throws ParseException {
        contractInfoContainer.removeAllItems();
        levelDetailsResultsTable.removeAllItems();
        Object root = dashboardResultsTable.getValue();
        List<NewComponentDTO> list = new ArrayList<>();
        if (root != null) {
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ONE)) {
                cfpDetailsGrid.setVisible(true);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                cfpDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                cfpDetailsName.setValue(detailsName);
                cfpDetailsNo.setEnabled(false);
                cfpDetailsName.setEnabled(false);
                String query = queryUtils.LoadmassupdateCompany(selectedCompanies);
                List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (itemList != null && !itemList.isEmpty()) {
                    for (int i = 0; i < itemList.size(); i++) {
                        NewComponentDTO itemDTO = new NewComponentDTO();
                        Object[] obje = (Object[]) itemList.get(i);
                        itemDTO.setModelId(String.valueOf(obje[0]));
                        itemDTO.setCompanyNo(String.valueOf(obje[1]));
                        itemDTO.setCompanyName(String.valueOf(obje[NumericConstants.TWO]));
                        itemDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.SIX]));
                        if (obje[NumericConstants.FOUR] != null) {

                            Date date =  format.parse(String.valueOf(obje[NumericConstants.FOUR]));
                            String finalString = df.format(date);
                            itemDTO.setPsStartDate(finalString);
                        } else {
                            itemDTO.setPsStartDate(null);
                        }
                        if (obje[NumericConstants.FIVE] != null) {
                            Date date =  format.parse(String.valueOf(obje[NumericConstants.FIVE]));
                            String finalString = df.format(date);
                            itemDTO.setPsEndDate(finalString);
                        } else {
                            itemDTO.setPsEndDate(null);
                        }
                        list.add(itemDTO);
                    }
                    contractInfoContainer.addAll(list);
                }
                levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
                levelDetailsResultsTable.setVisibleColumns(Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
            } else if (level.equals(Constants.TWO)) {

                cfpDetailsGrid.setVisible(false);
                ifpDetailsGrid.setVisible(true);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                ifpDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                ifpDetailsName.setValue(detailsName);
                ifpDetailsNo.setEnabled(false);
                ifpDetailsName.setEnabled(false);
                String query = queryUtils.loadMassUpdateItem(selectedItems);
                List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setModelId(String.valueOf(obje[NumericConstants.SIX]));
                    itemDTO.setItemNo(String.valueOf(obje[1]));
                    itemDTO.setItemName(String.valueOf(obje[0]));
                    itemDTO.setItemStatus(String.valueOf(obje[NumericConstants.EIGHT]));
                    itemDTO.setBrand(String.valueOf(obje[NumericConstants.THREE]));
                    populateDetailsPsStartDate(obje, itemDTO);
                    if (obje[NumericConstants.FIVE] != null) {
                        Date date =  format.parse(String.valueOf(obje[NumericConstants.FIVE]));
                        String finalString = df.format(date);
                        itemDTO.setPsEndDate(finalString);
                    } else {
                        itemDTO.setPsEndDate(null);
                    }
                    if (obje[NumericConstants.SEVEN] != null && obje[NumericConstants.SEVEN].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.SEVEN]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);
                levelDetailsResultsTable.setVisibleColumns(Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.ITEM_STATUS_PROPERTY, Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);

            } else if (level.equals(Constants.THREE)) {
                contractInfoContainer.removeAllItems();
                String hiddenId = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());

                cfpDetailsGrid.setVisible(false);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(true);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                psDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                psDetailsName.setValue(detailsName);
                psDetailsNo.setEnabled(false);
                psDetailsName.setEnabled(false);

                String query = "select im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,h1.DESCRIPTION AS STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE from dbo.ITEM_MASTER im join dbo.IMTD_PS_DETAILS ipd \n"
                        + "on im.ITEM_MASTER_SID=ipd.ITEM_MASTER_SID and ipd.PS_MODEL_SID='" + hiddenId + "' join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID\n"
                        + "join dbo.HELPER_TABLE h1 on h1.HELPER_TABLE_SID=im.ITEM_STATUS";
                List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    itemDTO.setBrand(String.valueOf(obje[NumericConstants.THREE]));
                    itemDTO.setStatus(String.valueOf(obje[NumericConstants.FOUR]));
                    if (obje[NumericConstants.FIVE] != null) {
                        Date date = format.parse(String.valueOf(obje[NumericConstants.FIVE]));
                        String finalString = df.format(date);
                        itemDTO.setPsStartDate(finalString);
                    } else {
                        itemDTO.setPsStartDate(null);
                    }
                    populateDetailsPsEndDate(obje, itemDTO);
                    if (obje[NumericConstants.TWO] != null && obje[NumericConstants.TWO].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.TWO]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);

                levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
                levelDetailsResultsTable.setVisibleColumns(Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, "Theraphy Class", Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
                contractInfoTableAlign();

            } else if (level.equals(Constants.FOUR)) {
                contractInfoContainer.removeAllItems();
                String hiddenId = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                cfpDetailsGrid.setVisible(false);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(true);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                rsDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                rsDetailsName.setValue(detailsName);
                rsDetailsNo.setEnabled(false);
                rsDetailsName.setEnabled(false);

                String query = "select im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,h1.DESCRIPTION AS STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE from dbo.ITEM_MASTER im join dbo.IMTD_PS_DETAILS ipd \n"
                        + "on im.ITEM_MASTER_SID=ipd.ITEM_MASTER_SID and ipd.PS_MODEL_SID='" + hiddenId + "' join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID\n"
                        + "join dbo.HELPER_TABLE h1 on h1.HELPER_TABLE_SID=im.ITEM_STATUS";
                List itemList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    itemDTO.setBrand(String.valueOf(obje[NumericConstants.THREE]));
                    itemDTO.setStatus(String.valueOf(obje[NumericConstants.FOUR]));
                    populateDetailsPsStartDate(obje, itemDTO);
                    populateDetailsPsEndDate(obje, itemDTO);
                    if (obje[NumericConstants.TWO] != null && obje[NumericConstants.TWO].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.TWO]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);

                levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
                levelDetailsResultsTable.setVisibleColumns(Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.THERAPY_CLASS_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, "Theraphy Class", Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER);
                contractInfoTableAlign();

            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please highlight a component to Populate.");
        }
    }

    private void clearCFPFields() {
        cfpId.setValue(Constants.EMPTY);
        cfpNo.setValue(Constants.EMPTY);
        cfpName.setValue(Constants.EMPTY);
        cfpStatus.setValue(null);
        cfpType.setValue(null);
        salesInclusion.setValue(null);
        cfpStartDate.setValue(null);
        cfpEndDate.setValue(null);
        cfpfileName.setValue(Constants.EMPTY);
    }

    private void clearIFPFields() {
        ifpId.setValue(Constants.EMPTY);
        ifpNo.setValue(Constants.EMPTY);
        ifpName.setValue(Constants.EMPTY);
        ifpStatus.setValue(null);
        ifptype.setValue(null);
        ifpStartDate.setValue(null);
        ifpEndDate.setValue(null);
        ifpfileName.setValue(Constants.EMPTY);
    }

    private void clearPSFields() {
        psId.setValue(Constants.EMPTY);
        psNo.setValue(Constants.EMPTY);
        psName.setValue(Constants.EMPTY);
        psStatus.setValue(null);
        psStartDate.setValue(null);
        psfileName.setValue(Constants.EMPTY);
    }

    private void clearRSFields() {
        rsId.setValue(Constants.EMPTY);
        rsNumber.setValue(Constants.EMPTY);
        rsName.setValue(Constants.EMPTY);
        rsStatus.setValue(null);
        rsStartDate.setValue(null);
        rsEndDate.setValue(null);
        paymentFrequency.setValue(null);
        paymentMethod.setValue(null);
        rsType.setValue(null);
        rebatePlanLevel.setValue(null);
        calendar.setValue(null);
        rsRarType.setValue(null);
        deductionInclusion.setValue(null);
        calculationLevel.setValue(null);
        calculationType.setValue(null);
        rebateFrequency.setValue(null);
        
    }

    private void contractInfoTableAlign() {
        Object[] visibleColumns = levelDetailsResultsTable.getVisibleColumns();
        for (Object column : visibleColumns) {
            levelDetailsResultsTable.setColumnWidth(column, NumericConstants.ONE_SIX_ZERO);
        }
    }

    private void updateToCFPDetails(int cfpModelSid, String temptableSId) {
        String query = "DELETE FROM CFP_DETAILS WHERE CFP_MODEL_SID = " + cfpModelSid + ";";
        query += " INSERT INTO CFP_DETAILS (COMPANY_MASTER_SID,COMPANY_START_DATE,COMPANY_END_DATE,TRADING_PARTNER_CONTRACT_NO,COMPANY_CFP_ATTACHED_STATUS,COMPANY_CFP_ATTACHED_DATE,CREATED_BY,CREATED_DATE,CFP_MODEL_SID,INBOUND_STATUS,RECORD_LOCK_STATUS,MODIFIED_BY,\"SOURCE\")\n"
                + "select COMPANY_MASTER_SID,CFP_DETAILS_START_DATE,CFP_DETAILS_END_DATE,CFP_DETAILS_TRADE_CLASS,COMPANY_STATUS,CFP_DETAILS_START_DATE,CREATED_BY,CREATED_DATE," + cfpModelSid + ",'A',0,1,'BPI' from GCM_COMPANY_DETAILS"
                + " where CFP_MODEL_SID=" + temptableSId + ";";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    private void updateToIfpDetails(int ifpModelSid, String temptableSId) {
        String query = "DELETE FROM IFP_DETAILS WHERE IFP_MODEL_SID = " + ifpModelSid + ";";
        query += " INSERT INTO IFP_DETAILS(IFP_MODEL_SID, ITEM_MASTER_SID,ITEM_IFP_ATTACHED_STATUS,\n"
                + "  		START_DATE,END_DATE,INBOUND_STATUS,\n"
                + "  		RECORD_LOCK_STATUS,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,\"SOURCE\") "
                + " select " + ifpModelSid + ",ITEM_MASTER_SID,ITEM_STATUS_SID,IFP_DETAILS_START_DATE,IFP_DETAILS_END_DATE,'A',0,1,CREATED_DATE,1,MODIFIED_DATE,'BPI' from GCM_ITEM_DETAILS  where IFP_MODEL_SID=" + temptableSId + ";";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    private boolean checkStartDate() {
        List<NewComponentDTO> list = componentResultsContainer.getItemIds();
        boolean flag = BooleanConstant.getTrueFlag();
        for (NewComponentDTO list1 : list) {
            if (list1.getStartDate() == null) {
                flag = BooleanConstant.getFalseFlag();
            }
        }
        return flag;
    }

    private void updatePsAndRSModelSid(Collection<?> children, int modelSid) {
        if (children != null) {
            for (Object item : children) {
                String id = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                logic.updatePsAndRsids(modelSid, id, "UPdate PS-IFP details");
            }
        }
    }

    public boolean checkSameItemInPs(String ifpId, Collection<?> itemIds, String queryName) {
        boolean flag = false;
        List input = new ArrayList();
        input.add(ifpId);
        List<Object> list = ItemQueries.getItemData(input, queryName, null);
        List dataList = new ArrayList<>();
        for (Object data : itemIds) {
            Integer itemId = Integer.valueOf(String.valueOf(componentResultsContainer.getContainerProperty(data, StringConstantsUtil.ITEM_MASTER_ID).getValue()));
            dataList.add(itemId);
        }
        for (Object dataList1 : list) {
            if (dataList.contains(dataList1)) {
                flag = true;
            }
        }
        return flag;

    }

    public void resetFieldsNewComponetTab() {

        // Component Details
        if (fieldDdlb.getValue() != null && !fieldDdlb.getValue().equals(Constants.SELECT_ONE)) {
            if (fieldDdlb.getValue().equals(Constants.STATUS_FIELD)) {
                statusddlb.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            } else {
                startPeriod.setValue(null);
            }
        }

        // Component Details Search
        if (componentDetailsSearch.getValue() != null && !componentDetailsSearch.getValue().equals(Constants.SELECT_ONE)) {
            if (componentDetailsSearch.getValue().equals("Company Status")) {
                componentDetailsSearch.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            } else {
                searchValue.setValue(StringUtils.EMPTY);
            }
        }

        enableDisableRadio.select(DISABLE.getConstant());
        cfpDetailsNo.setValue(StringUtils.EMPTY);
        cfpDetailsName.setValue(StringUtils.EMPTY);
        cfpDetailsNo.setEnabled(false);
        cfpDetailsName.setEnabled(false);
        ifpDetailsNo.setValue(StringUtils.EMPTY);
        ifpDetailsName.setValue(StringUtils.EMPTY);
        ifpDetailsNo.setEnabled(false);
        ifpDetailsName.setEnabled(false);
        psDetailsNo.setValue(StringUtils.EMPTY);
        psDetailsName.setValue(StringUtils.EMPTY);
        psDetailsNo.setEnabled(false);
        psDetailsName.setEnabled(false);
        rsDetailsNo.setValue(StringUtils.EMPTY);
        rsDetailsName.setValue(StringUtils.EMPTY);
        rsDetailsNo.setEnabled(false);
        rsDetailsName.setEnabled(false);

        if (!levelDetailsResultsTable.isEmpty()) {
            levelDetailsResultsTable.removeAllItems();
        }
        if (!componentDetailsSearchTable.isEmpty()) {
            componentDetailsSearchTable.removeAllItems();
        }
        if (!componentDetailsSelectedItem.isEmpty()) {
            componentDetailsSelectedItem.removeAllItems();
        }
        if (!dashboardResultsTable.isEmpty()) {
            dashboardResultsTable.removeAllItems();
        }
        clearCFPFields();
        clearIFPFields();
        clearPSFields();
        clearRSFields();
    }

    /**
     * Configure security
     *
     */
    private void configureSecurityPermissions() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Contract Management", "Copy Contract", "New Tab Screen");
            btnSearchNC.setVisible(CommonLogic.isButtonVisibleAccess("BtnsearchNC", functionHM));
            componentSearch.setVisible(CommonLogic.isButtonVisibleAccess("componentSearch", functionHM));
            addBtn.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", functionHM));
            levelRemoveBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelRemoveBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelRemoveBtn", functionHM));
            populateDetails.setVisible(CommonLogic.isButtonVisibleAccess("populateDetails", functionHM));
            addToTree.setVisible(CommonLogic.isButtonVisibleAccess("addToTree", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    
    private void addBtnSetStartDateMethod(Object[] obje, NewComponentDTO itemDTO) throws ParseException {
        if (obje[NumericConstants.FOUR] != null) {
            String date = df.format(obje[NumericConstants.FOUR]);
            Date date2 = df.parse(date);
            itemDTO.setStartDate(date2);
        } else {
            itemDTO.setStartDate(null);
        }
    }

    private void loadSearchFieldsIfpStatus(String value) {
        if (value.equals(Constants.IFP_STATUS)) {
            commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
            searchValue.setVisible(false);
            searchDDLB.setVisible(true);
        } else if (value.equals(Constants.IFPTYPE)) {
            copyContractLogic.getSelectNull(searchDDLB);
            commonUtil.loadComboBox(searchDDLB, UiUtils.IFP_TYPE, false);
            searchValue.setVisible(false);
            searchDDLB.setVisible(true);
        } else {
            searchValue.setVisible(true);
            searchDDLB.setVisible(false);
        }
    }

    public void populateDetailsPsStartDate(Object[] obje, NewComponentDTO itemDTO) throws ParseException {
        if (obje[NumericConstants.FOUR] != null) {
            Date date =  format.parse(String.valueOf(obje[NumericConstants.FOUR]));
            String finalString = df.format(date);
            itemDTO.setPsStartDate(finalString);
        } else {
            itemDTO.setPsStartDate(null);
        }
    }

    public void populateDetailsPsEndDate(Object[] obje, NewComponentDTO itemDTO) throws ParseException {
        if (obje[NumericConstants.SIX] != null) {
            Date date =  format.parse(String.valueOf(obje[NumericConstants.SIX]));
            String finalString = df.format(date);
            itemDTO.setPsEndDate(finalString);
        } else {
            itemDTO.setPsEndDate(null);
        }
    }
}
