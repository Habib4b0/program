
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
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
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class Newcomponent extends CustomComponent {

    public static final Logger LOGGER = Logger.getLogger(Newcomponent.class);
    @UiField("componenttype")
    public ComboBox componenttype;
    @UiField("SearchfieldNC")
    public ComboBox SearchfieldNC;
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
    public TextField SearchvaluedNC;
    @UiField("enableDisableRadio")
    public OptionGroup enableDisableRadio;
    @UiField("componentDetailsSearchTableLayout")
    public VerticalLayout componentDetailsSearchTableLayout;
    @UiField("componentDetailsSelectedItem1")
    public ExtFilterTable componentDetailsSelectedItem;
    @UiField("levelDetailsResultsTable1")
    public ExtFilterTable levelDetailsResultsTable;
    TreeTable dashboardResultsTable;
    @UiField("componentSelectionGrid")
    public GridLayout componentSelectionGrid;
    CopyContractLogic CopyContractLogic = new CopyContractLogic();
    private BeanItemContainer<CopyComponentDTO> copycontractResultsContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    private final BeanItemContainer<NewComponentDTO> componentSearchContainer = new BeanItemContainer<NewComponentDTO>(NewComponentDTO.class);
    private final BeanItemContainer<NewComponentDTO> componentResultsContainer = new BeanItemContainer<NewComponentDTO>(NewComponentDTO.class);
    CustomTreeContainer<CopyComponentDTO> dashBoardContainer;
    private final BeanItemContainer<NewComponentDTO> contractInfoContainer = new BeanItemContainer<NewComponentDTO>(NewComponentDTO.class);
    QueryUtils queryUtils = new QueryUtils();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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
    public ComboBox CFPType;
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
    public Button BtnsearchNC;
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
    public String selectedCompanies = Constants.EMPTY;
    public String selectedItems = Constants.EMPTY;
    public static final String PRICE_VAL = "([0-9|\\.|])*";
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd ");
    CommonDao DAO = CommonImpl.getInstance();
    CommonUtil commonUtil = CommonUtil.getInstance();
    NewComponentsDetailsSearchTableLogic tablelogic = new NewComponentsDetailsSearchTableLogic();
    public ExtPagedTable componentDetailsSearchTable = new ExtPagedTable(tablelogic);
    CopyContractLogic logic = new CopyContractLogic();

    public Newcomponent(TreeTable dashboardTable, CustomTreeContainer<CopyComponentDTO> dashBoardContainer) {
        this.dashBoardContainer = dashBoardContainer;
        this.dashboardResultsTable = dashboardTable;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/Newcomponent.xml"), this));
        dashboardLayout.addComponent(dashboardResultsTable);
        configureFields();

    }

    protected void configureFields() {
        try {
            cfpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpStartDate.setStyleName(Constants.DATE_FIEILD_CENTER);
            cfpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpEndDate.setStyleName(Constants.DATE_FIEILD_CENTER);

            ifpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpStartDate.setStyleName(Constants.DATE_FIEILD_CENTER);
            ifpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpEndDate.setStyleName(Constants.DATE_FIEILD_CENTER);

            psStartDate.setDateFormat(Constants.MM_DD_YYYY);
            psStartDate.setStyleName(Constants.DATE_FIEILD_CENTER);

            rsStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rsStartDate.setStyleName(Constants.DATE_FIEILD_CENTER);

            rsEndDate.setDateFormat(Constants.MM_DD_YYYY);
            rsEndDate.setStyleName(Constants.DATE_FIEILD_CENTER);
            BtnsearchNC.setEnabled(false);
            componenttype.addItem(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            componenttype.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN);
            componenttype.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE);
            componenttype.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE);
            componenttype.select(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            componenttype.setImmediate(true);
            componenttype.setNullSelectionAllowed(false);

            componentDetailsSearch.setImmediate(true);
            componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setNullSelectionAllowed(true);
            componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setImmediate(true);
            componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            configureComponentDetailsTable();
            componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentDetailsSearchTable.setWidth("100%");
            componentDetailsSearchTable.setHeight("100%");
            componentDetailsSearchTable.setPageLength(5);
            componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.NEW_COMPANY_DETAILS_COLUMNS);
            componentDetailsSearchTable.setColumnHeaders(Constants.NEW_COMPANY_DETAILS_HEADERS);
            componentDetailsSearchTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);

            Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSearchTable.setColumnWidth(column, 150);
            }
            componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
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
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                        componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
            searchDDLB.setImmediate(true);
            searchDDLB.setVisible(false);
            commonUtil.loadComboBox(psStatus, UiUtils.STATUS, false);
            dashboardResultsTable.setPageLength(8);
            dashboardResultsTable.markAsDirty();
            dashboardResultsTable.setImmediate(true);
            dashboardResultsTable.setContainerDataSource(dashBoardContainer);
            dashboardResultsTable.setVisibleColumns(Constants.COPYCONTRACT_DASHBOARD_RESULTS_COLUMNS);
            dashboardResultsTable.setColumnHeaders(Constants.COPYCONTRACT_DASHBOARD_RESULTS_HEADERS);
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
            commonUtil.loadComboBox(rebatePlanLevel, UiUtils.REBATE_PLAN_LEVEL, false);
            commonUtil.loadComboBox(paymentFrequency, UiUtils.PAYMENT_FREQUENCY, false);
            commonUtil.loadComboBox(paymentMethod, UiUtils.PAYMENT_METHOD, false);
            commonUtil.loadComboBox(rsType, UiUtils.RS_TYPE, false);
            commonUtil.loadComboBox(calendar, UiUtils.RS_CALENDAR, false);
            getSelectNull(rsStatus);

            searchDDLB.setVisible(false);
            componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

            componentDetailsSelectedItem.setWidth("100%");
            componentDetailsSelectedItem.setHeight("100%");
            componentDetailsSelectedItem.setImmediate(true);
            componentDetailsSelectedItem.setPageLength(5);
            componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
            componentDetailsSelectedItem.setEditable(true);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "companyNo", "companyName", "companyStatus", Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date");
            componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
            componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
            fieldDdlb.addItem("Status");
            fieldDdlb.addItem("Start Date");
            fieldDdlb.addItem("End Date");
            Object[] vColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : vColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, 150);
            }

            componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
                public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                    Field field = null;
                    if (Constants.CHECK.equals(propertyId)) {
                        field = new CheckBox();
                    }
                    if ("companyStatus".equals(propertyId) || "status".equals(propertyId) || "itemStatus".equals(propertyId)) {
                        ComboBox status = new ComboBox();
                        getSelectNull(status);
                        try {
                            commonUtil.loadComboBox(status, UiUtils.STATUS, false);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                        field = status;
                    }
                    if (Constants.START_DATE.equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                        field = field1;
                    }
                    if (Constants.END_DATE.equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                        field = field1;

                    }
                    return field;
                }
            });
            componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentResultsContainer.getItemIds()) {
                        componentResultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            levelDetailsResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            levelDetailsResultsTable.setWidth("100%");
            levelDetailsResultsTable.setHeight("100%");
            levelDetailsResultsTable.setPageLength(9);
            levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
            levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", Constants.PS_START_DATE, Constants.PS_END_DATE);
            levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date");
            levelDetailsResultsTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
            levelDetailsResultsTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
            Object[] vColumns1 = levelDetailsResultsTable.getVisibleColumns();
            for (Object column : vColumns1) {
                levelDetailsResultsTable.setColumnWidth(column, 150);
            }
            enableDisableRadio.addItem("Enable");
            enableDisableRadio.addItem("Disable");
            enableDisableRadio.select("Disable");
            fieldDdlb.setEnabled(false);
            statusddlb.setEnabled(false);
            populateBtn.setEnabled(false);
            SearchfieldNC.setEnabled(false);
            SearchvaluedNC.setEnabled(false);
            SearchfieldNC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            SearchfieldNC.setNullSelectionAllowed(true);
            SearchfieldNC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentDetailsSearch.setWidth("205px");
            fieldDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.setImmediate(true);
            fieldDdlb.setWidth("205px");
            fieldDdlb.setPageLength(5);
            componentDetailsSearch.setPageLength(5);
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
            LOGGER.error(ex);
        }
    }

    private void configureComponentDetailsTable() {

        componentDetailsSearchTableLayout.addComponent(componentDetailsSearchTable);
        componentDetailsSearchTableLayout.addComponent(tablelogic.createControls());
        componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSearchTable.setWidth("100%");
        componentDetailsSearchTable.setHeight("100%");
        componentDetailsSearchTable.setPageLength(5);
        tablelogic.setContainerDataSource(componentSearchContainer);
        componentDetailsSearchTable.setEditable(true);
        componentDetailsSearchTable.setVisibleColumns(Constants.NEW_COMPANY_DETAILS_COLUMNS);
        componentDetailsSearchTable.setColumnHeaders(Constants.NEW_COMPANY_DETAILS_HEADERS);
        componentDetailsSearchTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
        componentDetailsSearchTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
        componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);

        Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
        for (Object column : visibleColumns) {
            componentDetailsSearchTable.setColumnWidth(column, 150);
        }
        componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
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
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                    componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });

    }

    private void loadcomponentSelectionGrid() throws SystemException {
        componentDetailsSelectedItem.removeAllItems();
        componentResultsContainer.removeAllItems();
        componentSearchContainer.removeAllItems();
        componentDetailsSearch.removeAllItems();
        componentDetailsSearch.setImmediate(true);
        componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentDetailsSearch.setNullSelectionAllowed(true);
        componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentDetailsSearch.setImmediate(true);
        componentDetailsSearch.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        if (componenttype.getValue() != null) {
            if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                try {
                    componentSearchContainer.removeAllItems();
                    cfpComponent.setVisible(true);
                    CopyContractLogic.getSelectNull(cfpStatus);
                    commonUtil.loadComboBox(cfpStatus, UiUtils.STATUS, false);
                    componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                    componentDetailsSearchTable.setVisibleColumns(Constants.NEW_COMPANY_DETAILS_COLUMNS);
                    componentDetailsSearchTable.setColumnHeaders(Constants.NEW_COMPANY_DETAILS_HEADERS);
                    componentDetailsSearchTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnCheckBox(Constants.NEW_COMPANY_DETAILS_COLUMNS[0], Boolean.TRUE);

                    Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                    for (Object column : visibleColumns) {
                        componentDetailsSearchTable.setColumnWidth(column, 150);
                    }
                    componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
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
                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            for (NewComponentDTO temp : componentSearchContainer.getItemIds()) {
                                componentSearchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                            }
                        }
                    });
                    cfpStatus.select(Constants.ZEROSTRING);
                    cfpStatus.setImmediate(true);
                    cfpStatus.setValidationVisible(true);
                    CopyContractLogic.getSelectNull(CFPType);
                    commonUtil.loadComboBox(CFPType, UiUtils.CFP_TYPE, false);
                    CFPType.setValidationVisible(true);
                    ifpComponent.setVisible(false);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);
                    componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "companyNo", "companyName", "companyStatus", Constants.START_DATE, Constants.END_DATE);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date");
                    componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearch.setEnabled(true);
                    searchValue.setEnabled(true);
                    searchDDLB.setEnabled(true);
                    componentSearch.setEnabled(true);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                try {
                    cfpComponent.setVisible(false);
                    componentSearchContainer.removeAllItems();
                    CopyContractLogic.getSelectNull(ifpStatus);
                    commonUtil.loadComboBox(ifpStatus, UiUtils.STATUS, false);
                    ifpStatus.setValidationVisible(true);
                    commonUtil.loadComboBox(ifptype, UiUtils.IFP_TYPE, false);
                    componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                    componentDetailsSearchTable.setVisibleColumns(Constants.NEW_IFP_DETAILS_COLUMNS);
                    componentDetailsSearchTable.setColumnHeaders(Constants.NEW_IFP_DETAILS_HEADERS);
                    componentDetailsSearchTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearchTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);

                    componentDetailsSearchTable.setColumnCheckBox(Constants.NEW_IFP_DETAILS_COLUMNS[0], Boolean.TRUE);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
                    Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                    for (Object column : visibleColumns) {
                        componentDetailsSearchTable.setColumnWidth(column, 150);
                    }
                    componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
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
                    ifptype.setImmediate(true);
                    ifptype.setValidationVisible(true);
                    ifpComponent.setVisible(true);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);
                    componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date");

                    componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsSearch.setEnabled(true);
                    searchValue.setEnabled(true);
                    searchDDLB.setEnabled(true);
                    componentSearch.setEnabled(true);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                cfpComponent.setVisible(false);
                ifpComponent.setVisible(false);
                psComponent.setVisible(true);
                rsComponent.setVisible(false);
                componentSearchContainer.removeAllItems();
                componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                componentDetailsSearchTable.setVisibleColumns(Constants.NEW_PS_DETAILS_COLUMNS);
                componentDetailsSearchTable.setColumnHeaders(Constants.NEW_PS_DETAILS_HEADERS);
                componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

                Object[] visibleColumns = componentDetailsSearchTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsSearchTable.setColumnWidth(column, 155);
                }
                componentDetailsSearch.addItem(Constants.IFP_ID);
                componentDetailsSearch.addItem(Constants.IFP_NO);
                componentDetailsSearch.addItem(Constants.IfpNAME);
                componentDetailsSearch.addItem(Constants.IFP_STATUS);
                componentDetailsSearch.addItem(Constants.IFPTYPE);
                componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE, "priceType", "ppStartDate");
                componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Protection Start Date");
                componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

                Object[] visibleColumn = componentDetailsSelectedItem.getVisibleColumns();
                for (Object vcolumn : visibleColumn) {
                    componentDetailsSelectedItem.setColumnWidth(vcolumn, 150);
                }
                componentDetailsSearch.setEnabled(true);
                searchValue.setEnabled(true);
                searchDDLB.setEnabled(true);
                componentSearch.setEnabled(true);
            } else if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                componentDetailsSearch.addItem(Constants.IFP_ID);
                componentDetailsSearch.addItem(Constants.IFP_NO);
                componentDetailsSearch.addItem(Constants.IfpNAME);
                componentDetailsSearch.addItem(Constants.IFP_STATUS);
                componentDetailsSearch.addItem(Constants.IFPTYPE);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem("Status");
                fieldDdlb.addItem("Start Date");
                fieldDdlb.addItem("End Date");

                cfpComponent.setVisible(false);
                ifpComponent.setVisible(false);
                psComponent.setVisible(false);
                rsComponent.setVisible(true);
                componentSearchContainer.removeAllItems();
                componentDetailsSearchTable.setContainerDataSource(componentSearchContainer);
                componentDetailsSearchTable.setVisibleColumns(Constants.NEW_RS_DETAILS_COLUMNS);
                componentDetailsSearchTable.setColumnHeaders(Constants.NEW_RS_DETAILS_HEADERS);
                componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                componentResultsContainer.removeAllItems();
                componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
                componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE, "rebatePlan", "formulaId");
                componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID");
                componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
                componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
                    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                        Field field;

                        if (propertyId.equals("rebatePlan")) {
                            final CustomTextField rebatePlan = new CustomTextField();
                            rebatePlan.addStyleName("searchicon");
                            rebatePlan.setWidth("100%");
                            rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                                public void click(CustomTextField.ClickEvent event) {
                                    RebatePlanLookup lookup = new RebatePlanLookup(rebatePlan);
                                    lookup.addCloseListener(new Window.CloseListener() {
                                        public void windowClose(Window.CloseEvent e) {
                                            if (rebatePlan.getData() != null) {
                                                String newValue = String.valueOf(rebatePlan.getData());
                                            }
                                        }
                                    });
                                    UI.getCurrent().addWindow(lookup);
                                }
                            });
                            return rebatePlan;
                        } else if (propertyId.equals("formulaId")) {
                            final CustomTextField formulaId = new CustomTextField();
                            formulaId.addStyleName("searchicon");
                            formulaId.setWidth("100%");
                            formulaId.addClickListener(new CustomTextField.ClickListener() {
                                public void click(CustomTextField.ClickEvent event) {
                                    FormulaSearchLookup lookup = new FormulaSearchLookup(formulaId);
                                    lookup.addCloseListener(new Window.CloseListener() {
                                        public void windowClose(Window.CloseEvent e) {
                                            if (formulaId.getData() != null) {
                                            }
                                        }
                                    });
                                    UI.getCurrent().addWindow(lookup);
                                }
                            });
                            return formulaId;
                        } else if (String.valueOf(Constants.CHECK).equals(propertyId)) {

                            field = new CheckBox();
                        } else if (Constants.START_DATE.equals(propertyId)) {
                            PopupDateField field1 = new PopupDateField();
                            field1.setDateFormat(Constants.MM_DD_YYYY);
                            field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                            field = field1;
                        } else if (Constants.END_DATE.equals(propertyId)) {
                            PopupDateField field1 = new PopupDateField();
                            field1.setDateFormat(Constants.MM_DD_YYYY);
                            field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                            field = field1;

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
    public void enableDisableRadioChange(Property.ValueChangeEvent event) throws Exception {
        startPeriod.setValue(null);
        fieldDdlb.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
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
    public void componenttypeChange(Property.ValueChangeEvent event) throws Exception {
        loadcomponentSelectionGrid();
        loadComponentDetailsSearchSection();
    }

    @UiHandler("fieldDdlb")
    public void fieldDdlbChange(Property.ValueChangeEvent event) throws Exception {
        changeMassUpdateField();
    }

    @UiHandler("componentDetailsSearch")
    public void componentDetailsSearchChange(Property.ValueChangeEvent event) throws Exception {
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
    public void componentSearchLogic(Button.ClickEvent event) throws ParseException {
        String cType = String.valueOf(componenttype.getValue());
        if (componentDetailsSearch.getValue() != null) {
            String searchValueString = StringUtils.EMPTY;
            if (searchDDLB.getValue() != null) {
                searchValueString = String.valueOf(searchDDLB.getValue());
            } else if (searchValue.getValue() != null && !searchValue.getValue().isEmpty()) {
                searchValueString = searchValue.getValue().toString();
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
        Boolean flag = false;
        String ids = Constants.EMPTY;
        componentResultsContainer.removeAllItems();
        List<NewComponentDTO> list = new ArrayList<NewComponentDTO>();
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
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "companyNo", "companyName", "companyStatus", Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date");
            componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
            fieldDdlb.addItem("Status");
            fieldDdlb.addItem("Start Date");
            fieldDdlb.addItem("End Date");
            Object[] vColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : vColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, 150);
            }
            componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (NewComponentDTO temp : componentResultsContainer.getItemIds()) {
                        componentResultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            String query = queryUtils.LoadmassupdateCompany(ids);
            List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
            if (itemList != null && itemList.size() > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setModelId(String.valueOf(obje[0]));
                    itemDTO.setCompanyNo(String.valueOf(obje[1]));
                    itemDTO.setCompanyName(String.valueOf(obje[2]));
                    itemDTO.setCompanyStatus(String.valueOf(obje[3]));
                    if (obje[4] != null) {
                        String date = df.format(obje[4]);
                        Date date2 = df.parse(date);
                        itemDTO.setStartDate(date2);
                    } else {
                        itemDTO.setStartDate(null);
                    }
                    if (obje[5] != null) {
                        String date = df.format(obje[5]);
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
            String query = queryUtils.Loadmassupdateitem(ids);
            List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setModelId(String.valueOf(obje[6]));
                itemDTO.setItemNo(String.valueOf(obje[1]));
                itemDTO.setItemName(String.valueOf(obje[0]));
                itemDTO.setItemStatus(String.valueOf(obje[2]));
                itemDTO.setBrand(obje[3] == null ? StringUtils.EMPTY : String.valueOf(obje[3]));
                if (obje[4] != null) {
                    String date = df.format(obje[4]);
                    Date date2 = df.parse(date);
                    itemDTO.setStartDate(date2);
                } else {
                    itemDTO.setStartDate(null);
                }
                if (obje[5] != null) {
                    String date = df.format(obje[5]);
                    Date date2 = df.parse(date);
                    itemDTO.setEndDate(date2);
                } else {
                    itemDTO.setEndDate(null);
                }
                if (obje[7] != null && obje[7].equals(Constants.SELECT_ONE)) {
                    itemDTO.setTherapyClass(Constants.EMPTY);
                } else {
                    itemDTO.setTherapyClass(String.valueOf(obje[7]));
                }
                list.add(itemDTO);
            }
            componentResultsContainer.addAll(list);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "itemStatus", Constants.START_DATE, Constants.END_DATE);
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date");
        }
        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {

            String query = queryUtils.getPSInfo(ids);
            List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
            if (itemList != null && itemList.size() > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    if ((obje[2] != null) && !obje[2].equals(Constants.SELECT_ONE) && !obje[2].equals(Constants.NULL)) {
                        itemDTO.setTherapyClass(String.valueOf(obje[2]));
                    } else {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    }
                    itemDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                    itemDTO.setStatus(String.valueOf(obje[4]));
                    if (obje[5] != null) {
                        String date = df.format(obje[5]);
                        Date date2 = df.parse(date);
                        itemDTO.setStartDate(date2);
                    } else {
                        itemDTO.setStartDate(null);
                    }
                    if (obje[6] != null) {
                        String date = df.format(obje[6]);
                        Date date2 = df.parse(date);
                        itemDTO.setEndDate(date2);
                    } else {
                        itemDTO.setEndDate(null);
                    }
                    if ((obje[7] != null) && !obje[7].equals(Constants.SELECT_ONE) && !obje[7].equals(Constants.NULL)) {
                        itemDTO.setPriceType(String.valueOf(obje[8]));
                    } else {
                        itemDTO.setPriceType(Constants.EMPTY);
                    }
                    if (obje[8] != null) {
                        String date = df.format((Date) obje[8]);
                        itemDTO.setPpStartDate(date);
                    } else {
                        itemDTO.setPpStartDate(Constants.EMPTY);
                    }
                    itemDTO.setModelId(String.valueOf(obje[9]));
                    itemDTO.setItemMasterId(String.valueOf(obje[10]));
                    itemDTO.setPrice((obje[11] != null) ? String.valueOf(obje[11]) : Constants.EMPTY);
                    list.add(itemDTO);
                }
                componentResultsContainer.addAll(list);
            }
            componentDetailsSelectedItem.setContainerDataSource(componentResultsContainer);
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "price", "status", Constants.START_DATE, Constants.END_DATE, "priceType", "ppStartDate");
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Price", "Status", "Start Date", "End Date", "Price Type", "Price Protection Start Date");
            componentDetailsSelectedItem.setEditable(true);
            Object[] visibleColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, 150);
            }
        }
        if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            String query = queryUtils.getIFPInfo(ids);
            List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
            if (itemList != null && itemList.size() > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemMasterId(String.valueOf(obje[0]));
                    itemDTO.setItemNo(String.valueOf(obje[1]));
                    itemDTO.setItemName(String.valueOf(obje[2]));
                    if ((obje[2] != null) && !obje[2].equals(Constants.SELECT_ONE) && !obje[2].equals(Constants.NULL)) {
                        itemDTO.setTherapyClass(String.valueOf(obje[3]));
                    } else {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    }
                    itemDTO.setBrand((obje[5] != null) ? String.valueOf(obje[5]) : Constants.EMPTY);
                    itemDTO.setStatus(String.valueOf(obje[4]));
                    if (obje[6] != null) {
                        String date = df.format(obje[6]);
                        Date date2 = df.parse(date);
                        itemDTO.setStartDate(date2);
                    } else {
                        itemDTO.setStartDate(null);
                    }
                    if (obje[7] != null) {
                        String date = df.format(obje[7]);
                        Date date2 = df.parse(date);
                        itemDTO.setEndDate(date2);
                    } else {
                        itemDTO.setEndDate(null);
                    }
                    itemDTO.setModelId(String.valueOf(obje[8]));
                    itemDTO.setRebatePlan((obje[10] != null) ? String.valueOf(obje[10]) : Constants.EMPTY);
                    itemDTO.setFormulaId((obje[11] != null) ? String.valueOf(obje[11]) : Constants.EMPTY);
                    list.add(itemDTO);
                }
                componentResultsContainer.addAll(list);
            }
            componentDetailsSelectedItem.setVisibleColumns(Constants.CHECK, "itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE, "rebatePlan", "formulaId");
            componentDetailsSelectedItem.setColumnHeaders(Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID");
            componentDetailsSelectedItem.setEditable(true);
            Object[] visibleColumns = componentDetailsSelectedItem.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsSelectedItem.setColumnWidth(column, 150);
            }
        }
    }

    public static ComboBox getSelectNull(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(5);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    private void loadSearchFields(String value) throws Exception {
        searchDDLB.removeAllItems();
        String cType = String.valueOf(componenttype.getValue());
        if (cType.equalsIgnoreCase(Constants.PRICE_SCHEDULE)) {
            if (value.equals(Constants.IFP_STATUS)) {
                commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
                searchValue.setVisible(false);
                searchDDLB.setVisible(true);
            } else if (value.equals(Constants.IFPTYPE)) {
                CopyContractLogic.getSelectNull(searchDDLB);
                commonUtil.loadComboBox(searchDDLB, UiUtils.IFP_TYPE, false);
                searchValue.setVisible(false);
                searchDDLB.setVisible(true);
            } else {
                searchValue.setVisible(true);
                searchDDLB.setVisible(false);
            }
            loadMassUpdateField();
        } else if (cType.equalsIgnoreCase(Constants.REBATE_SCHEDULE)) {
            if (value.equals(Constants.IFP_STATUS)) {
                CopyContractLogic.getSelectNull(searchDDLB);
                commonUtil.loadComboBox(searchDDLB, UiUtils.STATUS, false);
                searchValue.setVisible(false);
                searchDDLB.setVisible(true);
            } else if (value.equals(Constants.IFPTYPE)) {
                CopyContractLogic.getSelectNull(searchDDLB);
                commonUtil.loadComboBox(searchDDLB, UiUtils.IFP_TYPE, false);
                searchValue.setVisible(false);
                searchDDLB.setVisible(true);
            } else {
                searchValue.setVisible(true);
                searchDDLB.setVisible(false);
            }
            loadMassUpdateField();
        } else {
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
                List componentList = (List) DAO.executeSelect(query);
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
        Boolean flag = false;
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
        fieldDdlb.addItem("Status");
        fieldDdlb.addItem("Start Date");
        fieldDdlb.addItem("End Date");
    }

    private void changeMassUpdateField() throws SystemException {
        startPeriod.setValue(null);
        String searchField = String.valueOf(fieldDdlb.getValue());
        if (searchField.equals("Status")) {
            try {
                CopyContractLogic.getSelectNull(statusddlb);
                commonUtil.loadComboBox(statusddlb, UiUtils.STATUS, false);
                statusddlb.setVisible(true);
                startPeriod.setVisible(false);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else {
            statusddlb.setVisible(false);
            startPeriod.setVisible(true);
        }
    }

    @UiHandler("populateBtn")
    public void massPopulateLogic(Button.ClickEvent event) throws SystemException {
        if (componenttype.getValue() != null) {
            if (String.valueOf(componenttype.getValue()).equals(Constants.PRICE_SCHEDULE) || String.valueOf(componenttype.getValue()).equals(Constants.COMPANY_FAMILY_PLAN) || String.valueOf(componenttype.getValue()).equals(Constants.ITEM_FAMILY_PLAN)) {
                if (fieldDdlb.getValue() != null && !fieldDdlb.getValue().equals(Constants.SELECT_ONE)) {
                    String searchField = String.valueOf(fieldDdlb.getValue());
                    if (searchField.equals("Status")) {
                        Boolean flag = true;
                        if (statusddlb.getValue() != null) {
                            String value = String.valueOf(statusddlb.getValue());
                            Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                            for (Object item : returnList) {
                                Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                if (checked) {
                                    flag = false;
                                    if (String.valueOf(componenttype.getValue()).equals(Constants.COMPANY_FAMILY_PLAN)) {
                                        componentResultsContainer.getContainerProperty(item, "companyStatus").setValue(value);
                                    }
                                    if (String.valueOf(componenttype.getValue()).equals(Constants.ITEM_FAMILY_PLAN)) {
                                        componentResultsContainer.getContainerProperty(item, "itemStatus").setValue(value);
                                    } else {
                                        componentResultsContainer.getContainerProperty(item, "status").setValue(value);
                                    }

                                }
                            }
                            if (flag) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Section");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Value");
                        }
                    }
                    if (searchField.equals("Start Date")) {
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
                    } else if (searchField.equals("End Date")) {
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
    }

    @UiHandler("addToTree")
    public void addToTreeLogic(Button.ClickEvent event) throws ParseException, SystemException, PortalException {

        try {
            Object root = dashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.valueOf(levelNo);
                String component = String.valueOf(componenttype.getValue());
                if (component.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    if (1 - levelNumber == 1) {
                        if (!cfpId.getValue().equals(Constants.EMPTY) && !cfpNo.getValue().equals(Constants.EMPTY) && !cfpName.getValue().equals(Constants.EMPTY) && cfpStatus.getValue() != null && cfpStartDate.getValue() != null && !cfpfileName.getValue().equals(Constants.EMPTY)) {
                            List listcId = null;
                            List listcNo = null;
                            if (!Constants.EMPTY.equals(cfpId.getValue())) {
                                String query = "select upper(CFP_ID) from CFP_MODEL where CFP_ID='" + cfpId.getValue().toUpperCase() + "'";
                                listcId = CompanyMasterLocalServiceUtil.executeQuery(query);
                            }
                            if (!Constants.EMPTY.equals(cfpNo.getValue())) {
                                String query = "select upper(CFP_NO) from CFP_MODEL where CFP_NO='" + cfpNo.getValue().toUpperCase() + "'";
                                listcNo = CompanyMasterLocalServiceUtil.executeQuery(query);
                            }
                            Object[] itemIds = dashboardResultsTable.getItemIds().toArray();
                            for (int i = 0; i < itemIds.length; i++) {
                                String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "levelNo").getValue());
                                if (level.equalsIgnoreCase("1")) {
                                    String cfpid = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_ID).getValue());
                                    String cfpno = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_NUMBER).getValue());
                                    if (cfpId.getValue().toUpperCase().equalsIgnoreCase(cfpid.toUpperCase())) {
                                        listcId.add(cfpId.getValue());
                                    }
                                    if (cfpNo.getValue().toUpperCase().equalsIgnoreCase(cfpno.toUpperCase())) {
                                        listcNo.add(cfpNo.getValue());
                                    }
                                }
                            }
                            Boolean flag = false;
                            Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                            for (Object item : returnList1) {
                                Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                if (checked) {
                                    flag = true;
                                }
                            }
                            if (cfpId.getValue().toString().length() > 38) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP ID length should be less than 38 characters.");
                            } else if (!cfpId.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*")) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP ID Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpNo.getValue().toString().length() > 50) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP No length should be less than 50 characters.");
                            } else if (!cfpNo.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*")) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP No Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpName.getValue().toString().length() > 100) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP Name length should be less than 100 characters.");
                            } else if (!cfpName.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*")) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP Name Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!");
                            } else if (cfpEndDate.getValue() != null && cfpStartDate.getValue().after(cfpEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP End date should be after CFP Start Date.");
                            } else if (cfpEndDate.getValue() != null && cfpStartDate.getValue().getTime() == cfpEndDate.getValue().getTime()) {
                                AbstractNotificationUtils.getErrorNotification("Error", "CFP Start date and CFP End date are equal.");

                            } else if (listcId != null && listcId.size() > 0) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different CFP ID since the CFP ID  already exists");

                            } else if (listcNo != null && listcNo.size() > 0) {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different CFP No since the CFP No  already exists");
                            } else if (!flag) {

                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Section");
                            } else {
                                final Object rootId = dashboardResultsTable.addItem();
                                dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.CFP);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(cfpId.getValue().toString());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(cfpNo.getValue().toString());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(cfpName.getValue().toString());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.ONE);
                                HelperDTO cfpStatusdto = new HelperDTO();
                                cfpStatusdto.setId(0);
                                cfpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                dashboardResultsTable.getContainerProperty(rootId, "status").setValue(cfpStatusdto);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.START_DATE).setValue(cfpStartDate.getValue());
                                dashboardResultsTable.getContainerProperty(rootId, Constants.END_DATE).setValue(cfpEndDate.getValue());

                                HelperDTO cfpTypedto = new HelperDTO();
                                cfpTypedto.setId(0);
                                cfpTypedto.setDescription(String.valueOf(CFPType.getValue()));
                                dashboardResultsTable.getContainerProperty(rootId, "marketType").setValue(cfpTypedto);
//                                 dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue(1);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("1");
                                dashboardResultsTable.addItem(rootId);
                                dashboardResultsTable.setParent(rootId, root);
                                dashboardResultsTable.setChildrenAllowed(rootId, true);
                                dashboardResultsTable.setCollapsed(root, false);
                                GcmGlobalDetails imtdItemPriceRebateDetails;
                                imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                imtdItemPriceRebateDetails.setItemId(cfpId.getValue().toString());
                                imtdItemPriceRebateDetails.setItemName(cfpName.getValue().toString());
                                imtdItemPriceRebateDetails.setItemStatusSid(Integer.parseInt(cfpStatus.getValue().toString()));
                                imtdItemPriceRebateDetails.setStartDate(cfpStartDate.getValue());
                                imtdItemPriceRebateDetails.setEndDate(cfpEndDate.getValue());
                                imtdItemPriceRebateDetails.setCreatedBy(1);
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(1);
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
                                        String cname = String.valueOf(componentResultsContainer.getContainerProperty(item, "companyName").getValue());
                                        String cno = String.valueOf(componentResultsContainer.getContainerProperty(item, "companyNo").getValue());
                                        Date startDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        Date endDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                        String companyStatus = String.valueOf(componentResultsContainer.getContainerProperty(item, "companyStatus").getValue());
                                        GcmCompanyDetails tempCFP = GcmCompanyDetailsLocalServiceUtil.createGcmCompanyDetails(0);
                                        tempCFP.setCompanyMasterSid(Integer.valueOf(companysid));
                                        tempCFP.setCompanyId(companysid);
                                        tempCFP.setCompanyNo(cno);
                                        tempCFP.setCompanyName(cname);

                                        tempCFP.setCfpDetailsTradeClass(StringUtils.EMPTY);//Trading Partner Contract NO
                                        tempCFP.setCompanyStatusSid(Integer.valueOf(companyStatus));
                                        tempCFP.setCompanyStartDate(startDate);
                                        tempCFP.setCompanyEndDate(endDate);
                                        tempCFP.setCreatedDate(new Date());
                                        tempCFP.setModifiedDate(new Date());

                                        tempCFP.setCfpModelSid(Integer.valueOf(hiddenId));
                                        tempCFP.setCfpDetailsStartDate(cfpStartDate.getValue());
                                        tempCFP.setCfpDetailsEndDate(cfpEndDate.getValue());
                                        tempCFP.setCompanyStatus(companyStatus);

                                        tempCFP.setOperation("A");
                                        GcmCompanyDetails newobj = GcmCompanyDetailsLocalServiceUtil.addGcmCompanyDetails(tempCFP);
                                    }
                                }
                                if (!setA.isEmpty()) {
                                    selectedCompanies = getIdString(setA);
                                }
                                dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                clearCFPFields();
                            }

                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter All the fields in Component selection section");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
                if (component.equals(Constants.ITEM_FAMILY_PLAN)) {
                    if (2 - levelNumber == 1) {
                        if (!ifpId.getValue().equals(Constants.EMPTY) && !ifpNo.getValue().equals(Constants.EMPTY) && !ifpName.getValue().equals(Constants.EMPTY) && ifpStatus.getValue() != null && ifpStartDate.getValue() != null && !ifpfileName.getValue().equals(Constants.EMPTY)) {
                            if (checkStartDate()) {
                                List listcId = null;
                                if (!Constants.EMPTY.equals(ifpId.getValue())) {
                                    String query = "select upper(IFP_ID) from IFP_MODEL where IFP_ID='" + ifpId.getValue().toUpperCase() + "'";
                                    listcId = CompanyMasterLocalServiceUtil.executeQuery(query);

                                }
                                Object[] itemIds = dashboardResultsTable.getItemIds().toArray();
                                for (int i = 0; i < itemIds.length; i++) {
                                    String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "levelNo").getValue());
                                    if (level.equalsIgnoreCase("2")) {
                                        String ifpid = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_ID).getValue());
                                        if (ifpId.getValue().toUpperCase().equalsIgnoreCase(ifpid.toUpperCase())) {
                                            listcId.add(ifpId.getValue());
                                        }

                                    }
                                }
                                Boolean flag = false;
                                Set setA = new HashSet();
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                    }
                                }
                                if (ifpId.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP ID length should be less than 50 characters.");
                                } else if (!ifpId.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpNo.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP No length should be less than 50 characters.");
                                } else if (!ifpNo.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP No Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpName.getValue().length() > 100) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP Name length should be less than 100 characters.");
                                } else if (!ifpName.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (ifpEndDate.getValue() != null && ifpStartDate.getValue().after(ifpEndDate.getValue())) {
                                    //throw new Validator.InvalidValueException("Contract End date should be after Contract Start Date");
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP End date should be after IFP Start Date.");
                                } else if (ifpEndDate.getValue() != null && ifpStartDate.getValue().getTime() == ifpEndDate.getValue().getTime()) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "IFP Start date and IFP End date are equal.");

                                } else if (listcId != null && listcId.size() > 0) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different IFP ID since the IFP ID  already exists");

                                } else if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Section");
                                } else {
                                    final Object rootId = dashboardResultsTable.addItem();
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(ifpId.getValue().toString());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(ifpNo.getValue().toString());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(ifpName.getValue().toString());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                                    HelperDTO cfpStatusdto = new HelperDTO();
                                    cfpStatusdto.setId(0);
                                    cfpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, "status").setValue(cfpStatusdto);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.START_DATE).setValue(ifpStartDate.getValue());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.END_DATE).setValue(ifpEndDate.getValue());

                                    HelperDTO ifpStatusdto = new HelperDTO();
                                    ifpStatusdto.setId(0);
                                    ifpStatusdto.setDescription(String.valueOf(cfpStatus.getValue()));
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.MARKET_TYPE).setValue(ifpStatusdto);
//                                dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue(1);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("1");
                                    dashboardResultsTable.addItem(rootId);
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, true);
                                    dashboardResultsTable.setCollapsed(root, false);
                                    GcmGlobalDetails imtdItemPriceRebateDetails;
                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                    imtdItemPriceRebateDetails.setItemId(ifpId.getValue().toString());
                                    imtdItemPriceRebateDetails.setItemName(ifpName.getValue().toString());
                                    imtdItemPriceRebateDetails.setItemStatusSid(Integer.parseInt(ifpStatus.getValue().toString()));
                                    imtdItemPriceRebateDetails.setStartDate(ifpStartDate.getValue());
                                    imtdItemPriceRebateDetails.setEndDate(ifpEndDate.getValue());
                                    imtdItemPriceRebateDetails.setItemType(ifptype.getValue().toString());
                                    imtdItemPriceRebateDetails.setCreatedBy(1);
                                    imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                    imtdItemPriceRebateDetails.setModifiedBy(1);
                                    imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                    GcmGlobalDetails imtdItemPriceRebateDetails1 = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails1.getGcmGlobalDetailsSid());

                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                    try {
                                        for (Object item : returnList) {
                                            Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                            if (checked) {
                                                GcmItemDetails tempIFP = GcmItemDetailsLocalServiceUtil.createGcmItemDetails(0);
                                                String itemsid = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                                setA.add(itemsid);
                                                tempIFP.setItemMasterSid(Integer.valueOf(itemsid));
                                                String iname = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemName").getValue());
                                                String cno = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemNo").getValue());
                                                Date startDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                                Date endDate = (Date) componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                                String itemStatus = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemStatus").getValue());
                                                tempIFP.setItemNo(cno);
                                                tempIFP.setItemName(iname);
                                                tempIFP.setItemStatus(itemStatus);
                                                tempIFP.setIfpModelSid(Integer.valueOf(hiddenId));
                                                tempIFP.setCreatedDate(new Date());
                                                tempIFP.setCreatedBy(1);
                                                tempIFP.setItemStatusSid(Integer.valueOf(itemStatus));
                                                tempIFP.setIfpDetailsStartDate(startDate);
                                                tempIFP.setIfpDetailsEndDate(endDate);
                                                tempIFP.setItemEndDate(endDate);
                                                tempIFP.setModifiedDate(new Date());
                                                tempIFP.setOperation("A");
                                                GcmItemDetails updated = GcmItemDetailsLocalServiceUtil.addGcmItemDetails(tempIFP);
                                            }
                                        }

                                        if (!setA.isEmpty()) {
                                            selectedItems = getIdString(setA);
                                        }
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                    }

                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                    clearIFPFields();
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter Start date for all the selected item");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter All the fields in Component selection section");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
                if (component.equals(Constants.PRICE_SCHEDULE)) {
                    if (3 - levelNumber == 1) {
                        String ifpId = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                        String queryName = "temp Table IFP Select";
                        if (checkSameItemInPs(ifpId, componentDetailsSelectedItem.getItemIds(), queryName)) {
                            if (!psId.getValue().equals(Constants.EMPTY) && !psNo.getValue().equals(Constants.EMPTY) && !psName.getValue().equals(Constants.EMPTY) && psStatus.getValue() != null && psStartDate.getValue() != null && !psfileName.getValue().equals(Constants.EMPTY)) {
                                String query = "select * from PS_MODEL where PS_NAME='" + psName.getValue() + "'";
                                List list = CompanyMasterLocalServiceUtil.executeQuery(query);
                                if (list != null && list.size() > 0) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Price Schedule Name already exist. Please Enter Different Name");
                                    return;
                                }
                                Boolean flag = false;
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                        Object sDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        if (sDate == null) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemName").getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select " + itemName + " Start Date");
                                            return;
                                        }
                                        String price = String.valueOf(componentResultsContainer.getContainerProperty(item, "price").getValue());
                                        if (price.equals(StringUtils.EMPTY)) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemName").getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter " + itemName + " Price");
                                            return;
                                        }
                                    }
                                }

                                if (psId.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS ID length should be less than 50 characters.");
                                    return;
                                } else if (!psId.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (psNo.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS No length should be less than 50 characters.");
                                    return;
                                } else if (!psNo.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS No Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (psName.getValue().length() > 100) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS Name length should be less than 100 characters.");
                                    return;
                                } else if (!psName.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                }

                                if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Section");
                                }
                                String priceScheduleId = String.valueOf(psId.getValue());
                                String priceScheduleNo = psNo.getValue();
                                String priceScheduleName = psName.getValue();
                                int priceScheduleStatus = Integer.valueOf(String.valueOf(psStatus.getValue()));
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
                                imtdItemPriceRebateDetails.setCreatedBy(1);
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(1);
                                imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                try {
                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    final Object rootId = dashboardResultsTable.addItem();
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(priceScheduleId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(priceScheduleNo);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(priceScheduleName);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("1");
                                    dashboardResultsTable.addItem(rootId);
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, true);
                                    dashboardResultsTable.setCollapsed(root, false);
                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                    for (Object item : returnList) {
                                        Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                        if (checked) {
                                            String ifpModelId = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                            String itemId = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                                            Object startDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                            String price = String.valueOf(componentResultsContainer.getContainerProperty(item, "price").getValue());
                                            String sDate = df.format(startDate);
                                            String eDate = Constants.EMPTY;
                                            if (componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue() != null) {
                                                Object endDate = componentResultsContainer.getContainerProperty(item, Constants.END_DATE).getValue();
                                                eDate = df.format(endDate);
                                            }

                                            ImtdPsDetails imtdPsDetails;
                                            imtdPsDetails = ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(0);
                                            imtdPsDetails.setPsModelSid(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                            imtdPsDetails.setIfpModelSid(Integer.valueOf(ifpModelId));
                                            imtdPsDetails.setItemMasterSid(Integer.valueOf(itemId));
                                            imtdPsDetails.setPsDtlsContPriceStartdate(df.parse(sDate));
                                            if (!eDate.equals(Constants.EMPTY)) {
                                                imtdPsDetails.setPsDtlsContPriceEnddate(df.parse(eDate));
                                            }
                                            imtdPsDetails.setPsDetailsPrice(Double.parseDouble(price));
                                            imtdPsDetails.setCreatedBy(1);
                                            imtdPsDetails.setCreatedDate(new Date());
                                            imtdPsDetails.setModifiedBy(1);
                                            imtdPsDetails.setModifiedDate(new Date());
                                            imtdPsDetails = ImtdPsDetailsLocalServiceUtil.addImtdPsDetails(imtdPsDetails);

                                            clearPSFields();
                                        }
                                    }
                                } catch (SystemException ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter All the fields in Component selection section");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
                if (component.equals(Constants.REBATE_SCHEDULE)) {
                    if (4 - levelNumber == 1) {
                        String ifpId = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                        if (checkSameItemInPs(ifpId, componentDetailsSelectedItem.getItemIds(), "temp Table PS Select")) {
                            if (!rsId.getValue().equals(Constants.EMPTY) && !rsNumber.getValue().equals(Constants.EMPTY) && !rsName.getValue().equals(Constants.EMPTY) && !rsStatus.getValue().equals(Constants.ZEROSTRING) && !rsType.getValue().equals(Constants.ZEROSTRING) && rsStartDate.getValue() != null && rsEndDate.getValue() != null && !paymentFrequency.getValue().equals(Constants.ZEROSTRING) && !paymentMethod.getValue().equals(Constants.ZEROSTRING) && !rebatePlanLevel.getValue().equals(Constants.ZEROSTRING) && !rsRarType.getValue().equals(Constants.ZEROSTRING) && !calendar.getValue().equals(Constants.ZEROSTRING)) {
                                String query = "select * from RS_MODEL where RS_NAME='" + rsName.getValue() + "'";
                                List list = CompanyMasterLocalServiceUtil.executeQuery(query);
                                if (list != null && list.size() > 0) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Rebate Schedule Name already exist. Please Enter Different Name");
                                    return;
                                }
                                Boolean flag = false;
                                Collection<?> returnList1 = componentDetailsSelectedItem.getItemIds();
                                for (Object item : returnList1) {
                                    Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                    if (checked) {
                                        flag = true;
                                        Object sDate = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                        if (sDate == null) {
                                            String itemName = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemName").getValue());
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select " + itemName + " Start Date");
                                            return;
                                        }
                                    }
                                }

                                if (rsId.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS ID length should be less than 50 characters.");
                                    return;
                                } else if (!rsId.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS ID Allowed Special characters are @,*,#,.,$,&,_,-");
                                } else if (rsNumber.getValue().length() > 50) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS No length should be less than 50 characters.");
                                    return;
                                } else if (!rsNumber.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS No Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (rsName.getValue().length() > 100) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS Name length should be less than 100 characters.");
                                    return;
                                } else if (!rsName.getValue().matches("([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*")) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS Name Allowed Special characters are @,*,#,.,$,&,_,-");
                                    return;
                                } else if (rsEndDate.getValue() != null && rsStartDate.getValue().after(rsEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS End date should be after RS Start Date.");
                                    return;
                                } else if (rsEndDate.getValue() != null && rsStartDate.getValue().getTime() == rsEndDate.getValue().getTime()) {
                                    AbstractNotificationUtils.getErrorNotification("Error", "RS Start date and RS End date are equal.");
                                    return;
                                }

                                if (!flag) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one Record at Component Details Section");
                                }
                                String rebateScheduleId = String.valueOf(rsId.getValue());
                                String rebateScheduleNo = rsNumber.getValue();
                                String rebateScheduleName = rsName.getValue();
                                int rebateScheduleStatus = Integer.valueOf(String.valueOf(rsStatus.getValue()));
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
                                imtdItemPriceRebateDetails.setCreatedBy(1);
                                imtdItemPriceRebateDetails.setCreatedDate(new Date());
                                imtdItemPriceRebateDetails.setModifiedBy(1);
                                imtdItemPriceRebateDetails.setModifiedDate(new Date());
                                final Object rootId = dashboardResultsTable.addItem();

                                try {
                                    imtdItemPriceRebateDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(imtdItemPriceRebateDetails);
                                    String hiddenId = String.valueOf(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(rebateScheduleId);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(rebateScheduleNo);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(rebateScheduleName);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(hiddenId);
//                                        dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue(1);
                                    dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("1");
                                    dashboardResultsTable.addItem(rootId);
                                    dashboardResultsTable.setParent(rootId, root);
                                    dashboardResultsTable.setChildrenAllowed(rootId, false);
                                    dashboardResultsTable.setCollapsed(root, false);

                                    Collection<?> returnList = componentDetailsSelectedItem.getItemIds();
                                    for (Object item : returnList) {
                                        Boolean checked = (Boolean) componentResultsContainer.getContainerProperty(item, Constants.CHECK).getValue();
                                        if (checked) {
                                            String ifpModelId = String.valueOf(componentResultsContainer.getContainerProperty(item, Constants.MODEL_ID).getValue());
                                            String itemId = String.valueOf(componentResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                                            Object sDate1 = componentResultsContainer.getContainerProperty(item, Constants.START_DATE).getValue();
                                            String sDate = df.format(sDate1);

                                            ImtdPsDetails imtdPsDetails;
                                            imtdPsDetails = ImtdPsDetailsLocalServiceUtil.createImtdPsDetails(0);
                                            imtdPsDetails.setPsModelSid(imtdItemPriceRebateDetails.getGcmGlobalDetailsSid());//hidden id
                                            imtdPsDetails.setIfpModelSid(Integer.valueOf(ifpModelId));
                                            imtdPsDetails.setItemMasterSid(Integer.valueOf(itemId));
                                            imtdPsDetails.setPsDtlsContPriceStartdate(df.parse(sDate));
                                            imtdPsDetails.setPsDetailsPrice(Double.parseDouble("10"));
                                            imtdPsDetails.setCreatedBy(1);
                                            imtdPsDetails.setCreatedDate(new Date());
                                            imtdPsDetails.setModifiedBy(1);
                                            imtdPsDetails.setModifiedDate(new Date());
                                            ImtdPsDetailsLocalServiceUtil.addImtdPsDetails(imtdPsDetails);
                                            clearPSFields();
                                        }
                                    }
                                } catch (SystemException ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Enter all the fields in Component Selection section");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP and PS");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void loadComponentDetailsSearchSection() {
        if (componenttype.getValue() != null) {
            if (componenttype.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                componentDetailsSearch.removeAllItems();
                componentDetailsSearch.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setNullSelectionAllowed(true);
                componentDetailsSearch.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                componentDetailsSearch.setImmediate(true);
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
                componentDetailsSearch.setImmediate(true);
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

    public void savecontract(Object item) throws SystemException, PortalException, ParseException {
        try {
            Map<String, String> map = new HashMap<String, String>();
            int psModelSid = 0;
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            int contractMasterSid = 0;
//            for (Object item : treeItem) {
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ZEROSTRING)) {
                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                String contractHolder = String.valueOf(dashboardResultsTable.getContainerProperty(item, "contractHolder").getValue());
                int contractType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int status = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, "status").getValue()).getId();
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.END_DATE).getValue();
                ContractMaster contractMaster;
                contractMaster = ContractMasterLocalServiceUtil.createContractMaster(0);
                contractMaster.setContractId(contractId);
                contractMaster.setContractNo(contractNo);
                contractMaster.setContractName(contractName);
                contractMaster.setContractType(contractType);
                contractMaster.setContHoldCompanyMasterSid(contractHolder);
                contractMaster.setProcessStatus(true);
                contractMaster.setSource("BPI");
                contractMaster.setContractStatus(status);
                contractMaster.setCreatedBy(Integer.valueOf(userId));
                contractMaster.setStartDate(startDate);
                contractMaster.setEndDate(endDate);
                contractMaster.setInboundStatus("A");
                contractMaster.setCreatedDate(new Date());
                contractMaster.setModifiedDate(new Date());
                contractMaster = ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
                contractMasterSid = contractMaster.getContractMasterSid();
                dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(contractMasterSid));
                int AliasType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, "aliasType").getValue()).getId();
                Date AliasSDATE = (Date) dashboardResultsTable.getContainerProperty(item, "aliasstartdate").getValue();
                String AliasNumber = String.valueOf(dashboardResultsTable.getContainerProperty(item, "aliasNumber").getValue());
                Date AliasEDATE = (Date) dashboardResultsTable.getContainerProperty(item, "aliasenddate").getValue();
                ContractAliasMaster CAM = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                CAM.setContractAliasNo(AliasNumber);
                CAM.setContractAliasType(AliasType);
                CAM.setStartDate(AliasSDATE);
                CAM.setEndDate(AliasEDATE);
                CAM.setModifiedDate(new Date());
                CAM.setCreatedBy(1);
                CAM.setCreatedDate(new Date());
                CAM.setSource("BPI");
                CAM.setInboundStatus("A");
                CAM.setContractMasterSid(contractMasterSid);
                ContractAliasMaster CAM1 = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);

            } else if (level.equals(Constants.THREE)) {
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                String query = queryUtils.getTempTableValue(temptableSId);
                List list = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (list != null && list.size() > 0) {
                    Object[] obj = (Object[]) list.get(0);
                    PsModel psModel;
                    psModel = PsModelLocalServiceUtil.createPsModel(0);
                    psModel.setPsId(String.valueOf(obj[0]));
                    psModel.setPsNo(String.valueOf(obj[1]));
                    psModel.setPsName(String.valueOf(obj[2]));
                    psModel.setPsStatus(obj[3] != null && !obj[3].toString().equals(StringUtils.EMPTY) ? Integer.valueOf(String.valueOf(obj[3])) : 0);
                    if (obj[3] != null) {
                        String date1 = df.format(obj[4]);
                        psModel.setPsStartDate(df.parse(date1));
                    } else {
                        psModel.setPsStartDate(null);
                    }
                    psModel.setCreatedBy(Integer.valueOf(userId));
                    psModel.setCreatedDate(new Date());
                    psModel.setModifiedBy(Integer.valueOf(userId));
                    psModel.setModifiedDate(new Date());
                    psModel = PsModelLocalServiceUtil.addPsModel(psModel);
                    psModelSid = psModel.getPsModelSid();
                    CopyContractLogic.insertPsDetails(psModelSid, userId, temptableSId);
                    Object ifpItem = dashboardResultsTable.getParent(item);
                    String ifpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(ifpItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = dashboardResultsTable.getParent(ifpItem);
                    String cfpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    PsContract psContract = PsContractLocalServiceUtil.createPsContract(0);
                    psContract.setPsModelSid(psModel.getPsModelSid());
                    psContract.setPsName(psModel.getPsName());
                    psContract.setPsStartDate(psModel.getPsStartDate());
                    psContract.setContractMasterSid(Integer.valueOf(contractSId));
                    psContract.setCfpContractSid(cfpContractId);
                    psContract.setIfpContractSid(ifpContractId);
                    psContract.setInboundStatus("A");
                    psContract.setRecordLockStatus(false);
                    psContract.setCreatedBy(Integer.valueOf(userId));
                    psContract.setCreatedDate(new Date());
                    psContract.setModifiedBy(Integer.valueOf(userId));
                    psContract.setModifiedDate(new Date());
                    psContract = PsContractLocalServiceUtil.addPsContract(psContract);
                    SavePS(String.valueOf(psContract.getPsContractSid()), psModel.getPsModelSid());
                    dashboardResultsTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(psContract.getPsContractSid()));
                }

            } else if (level.equals(Constants.FOUR)) {
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                String query = queryUtils.getTempTableValueForPS(temptableSId);
                List list = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (list != null && list.size() > 0) {
                    Object[] obj = (Object[]) list.get(0);
                    RsModel rsModel = RsModelLocalServiceUtil.createRsModel(0);
                    rsModel.setRsId(String.valueOf(obj[0]));
                    rsModel.setRsNo(String.valueOf(obj[1]));
                    rsModel.setRsName(String.valueOf(obj[2]));
                    rsModel.setRsStatus(obj[3] != null && !obj[3].toString().equals(StringUtils.EMPTY) ? Integer.valueOf(String.valueOf(obj[3])) : 0);
                    if (obj[4] != null) {
                        String date1 = df.format(obj[4]);
                        rsModel.setRsStartDate(df.parse(date1));
                    }
                    if (obj[5] != null) {
                        String date1 = df.format(obj[5]);
                        rsModel.setRsEndDate(df.parse(date1));
                    }
                    rsModel.setRsType(Integer.valueOf(String.valueOf(obj[6])));
                    rsModel.setPaymentFrequency(Integer.valueOf(String.valueOf(obj[7])));
                    rsModel.setRebateProgramType(Integer.valueOf(String.valueOf(obj[8])));
                    rsModel.setRebatePlanLevel(String.valueOf(obj[9]));
                    rsModel.setPaymentMethod(Integer.valueOf(String.valueOf(obj[10])));
                    rsModel.setRsCalendar(Integer.valueOf(String.valueOf(obj[11])));
                    rsModel.setCreatedBy(Integer.valueOf(userId));
                    rsModel.setCreatedDate(new Date());
                    rsModel.setModifiedBy(Integer.valueOf(userId));
                    rsModel.setModifiedDate(new Date());
                    rsModel = RsModelLocalServiceUtil.addRsModel(rsModel);
//                        updatePsAndRSModelSid(dashboardResultsTable.getChildren(item), rsModel.getRsModelSid(), temptableSId);
                    CopyContractLogic.insertIntoRsDetails(temptableSId, userId, rsModel.getRsModelSid());
                    Object psItem = dashboardResultsTable.getParent(item);
                    String psContractId = String.valueOf(dashboardResultsTable.getContainerProperty(psItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentIFPItem = dashboardResultsTable.getParent(psItem);
                    String ifpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentIFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = dashboardResultsTable.getParent(parentIFPItem);
                    String cfpContractId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    RsContract rsContract = RsContractLocalServiceUtil.createRsContract(0);
                    int modelId = rsModel.getRsModelSid();
                    String name = rsModel.getRsName();
                    String id = rsModel.getRsId();
                    String no = rsModel.getRsNo();
                    int type = rsModel.getRsType();
                    int rpType = rsModel.getRebateProgramType();
                    Date stDate = rsModel.getRsStartDate();
                    int reFre = 0;
                    int calender = rsModel.getRsCalendar();
                    rsContract.setRsModelSid(modelId);
                    rsContract.setRsId(id);
                    rsContract.setRsNo(no);
                    rsContract.setRsName(name);
                    rsContract.setRsType(type);
                    rsContract.setRebateProgramType(rpType);
                    rsContract.setRsStartDate(stDate);
                    rsContract.setContractMasterSid(Integer.valueOf(contractSId));
                    rsContract.setCfpContractSid(cfpContractId);
                    rsContract.setIfpContractSid(ifpContractId);
                    rsContract.setPsContractSid(psContractId);
                    rsContract.setRebateFrequency(reFre);
                    rsContract.setRsCalendar(String.valueOf(calender));
                    rsContract.setInboundStatus("A");
                    rsContract.setRecordLockStatus(false);
                    rsContract.setCreatedBy(Integer.valueOf(userId));
                    rsContract.setCreatedDate(new Date());
                    rsContract.setModifiedBy(Integer.valueOf(userId));
                    rsContract.setModifiedDate(new Date());
                    rsContract = RsContractLocalServiceUtil.addRsContract(rsContract);
                    SaveRS(String.valueOf(rsContract.getRsContractSid()), rsModel.getRsModelSid());
                }
            } else if (level.equals(Constants.ONE)) {

                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                int contractType = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int status = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, "status").getValue()).getId();
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
                cfpmodel.setCreatedBy(1);
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
                cfpMasterAttached.setContractMasterSid(Integer.valueOf(contractSId));
                cfpMasterAttached.setSource("BPI");
                cfpMasterAttached.setCfpType(companyFamily.getCfpType());
                cfpMasterAttached.setCfpCategory(companyFamily.getCfpCategory());
                cfpMasterAttached.setCfpDesignation(companyFamily.getCfpDesignation());
                cfpMasterAttached.setParentCfpId(companyFamily.getParentCfpId());
                cfpMasterAttached.setParentCfpName(companyFamily.getParentCfpName());
                cfpMasterAttached.setCfpStatus(companyFamily.getCfpStatus());
                cfpMasterAttached.setCfpTradeClass(companyFamily.getCfpTradeClass());
                cfpMasterAttached.setCfpStartDate(companyFamily.getCfpStartDate());
                cfpMasterAttached.setCfpEndDate(companyFamily.getCfpEndDate());
                cfpMasterAttached.setCreatedBy(1);
                cfpMasterAttached.setCreatedDate(new Date());
                cfpMasterAttached.setModifiedBy(1);
                cfpMasterAttached.setModifiedDate(new Date());
                cfpMasterAttached.setCfpContractAttachedDate(new Date());
                cfpMasterAttached.setRecordLockStatus(false);
                cfpMasterAttached.setInboundStatus("A");
                CfpContract cm1 = CfpContractLocalServiceUtil.addCfpContract(cfpMasterAttached);
                dashboardResultsTable.getContainerProperty(item, "savedSystemId").setValue(String.valueOf(cm1.getCfpContractSid()));
                SaveCFP(String.valueOf(cm1.getCfpContractSid()), companyFamily.getCfpModelSid());

            } else if (level.equals(Constants.TWO)) {
                String Id = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                String No = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                String Name = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                int Type = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                int status = ((HelperDTO) dashboardResultsTable.getContainerProperty(item, "status").getValue()).getId();
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(item, Constants.END_DATE).getValue();
                String temptableSId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                IfpModel ifpmodel = IfpModelLocalServiceUtil.createIfpModel(0);
                ifpmodel.setIfpId(Id);
                ifpmodel.setIfpName(Name);
                ifpmodel.setIfpNo(No);
                ifpmodel.setIfpStatus(status);
                ifpmodel.setIfpStartDate(startDate);
                ifpmodel.setIfpEndDate(endDate);
                ifpmodel.setIfpType(Type);
                ifpmodel.setCreatedBy(1);
                ifpmodel.setCreatedDate(new Date());
                ifpmodel.setSource("BPI");
                ifpmodel.setInboundStatus("A");
                ifpmodel.setModifiedDate(new Date());
                final IfpModel itemFamily = IfpModelLocalServiceUtil.addIfpModel(ifpmodel);
                map.put(temptableSId, String.valueOf(itemFamily.getIfpModelSid()));
                updateToIfpDetails(itemFamily.getIfpModelSid(), temptableSId);
                final IfpContract ifpMasterAttached = IfpContractLocalServiceUtil.createIfpContract(0);

                ifpMasterAttached.setIfpModelSid(itemFamily.getIfpModelSid());
                ifpMasterAttached.setIfpName(itemFamily.getIfpName());
                ifpMasterAttached.setSource("BPI");
                ifpMasterAttached.setIfpType(itemFamily.getIfpType());
                ifpMasterAttached.setIfpCategory(itemFamily.getIfpCategory());
                ifpMasterAttached.setIfpDesignation(itemFamily.getIfpDesignation());
                ifpMasterAttached.setParentIfpId(itemFamily.getParentIfpId());
                ifpMasterAttached.setParentIfpName(itemFamily.getParentIfpName());
                ifpMasterAttached.setIfpStatus(itemFamily.getIfpStatus());
                ifpMasterAttached.setIfpStartDate(itemFamily.getIfpStartDate());
                ifpMasterAttached.setIfpEndDate(itemFamily.getIfpEndDate());
                ifpMasterAttached.setIfpContractAttachedDate(new Date());
                ifpMasterAttached.setCreatedBy(1);
                ifpMasterAttached.setCreatedDate(new Date());
                ifpMasterAttached.setModifiedBy(1);
                ifpMasterAttached.setModifiedDate(new Date());
                ifpMasterAttached.setRecordLockStatus(false);
                ifpMasterAttached.setInboundStatus("A");
                Object parentItem = dashboardResultsTable.getParent(item);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, "savedSystemId").getValue());
                Object contractItem = dashboardResultsTable.getParent(parentItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                ifpMasterAttached.setContractMasterSid(Integer.valueOf(contractSId));
                ifpMasterAttached.setCfpContractSid(parentCFPId);
                updatePsAndRSModelSid(dashboardResultsTable.getChildren(item), itemFamily.getIfpModelSid(), temptableSId);
                IfpContract im1 = IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
                SaveIFP(String.valueOf(im1.getIfpContractSid()), itemFamily.getIfpModelSid());
                dashboardResultsTable.getContainerProperty(item, "savedSystemId").setValue(String.valueOf(im1.getIfpContractSid()));
            }
//            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void SaveCFP(String cfpId, Integer cfpModelId) {
        CopyContractLogic.SaveCFP(cfpId, cfpModelId);
    }

    public void SaveIFP(String ifpId, Integer ifpModelId) {
        CopyContractLogic.SaveIFP(ifpId, ifpModelId);
    }

    public void SavePS(String psid, Integer psModelId) {
        CopyContractLogic.SavePS(psid, psModelId);
    }

    public void SaveRS(String rsid, Integer rsModelId) {
        CopyContractLogic.SaveRS(rsid, rsModelId);
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
        List<NewComponentDTO> list = new ArrayList<NewComponentDTO>();
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
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (itemList != null && itemList.size() > 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        NewComponentDTO itemDTO = new NewComponentDTO();
                        Object[] obje = (Object[]) itemList.get(i);
                        itemDTO.setModelId(String.valueOf(obje[0]));
                        itemDTO.setCompanyNo(String.valueOf(obje[1]));
                        itemDTO.setCompanyName(String.valueOf(obje[2]));
                        itemDTO.setCompanyStatus(String.valueOf(obje[6]));
                        if (obje[4] != null) {

                            Date date = (Date) FORMAT.parse(String.valueOf(obje[4]));
                            String finalString = df.format(date);
                            itemDTO.setPsStartDate(finalString);
                        } else {
                            itemDTO.setPsStartDate(null);
                        }
                        if (obje[5] != null) {
                            Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
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
                levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date");
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
                String query = queryUtils.Loadmassupdateitem(selectedItems);
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setModelId(String.valueOf(obje[6]));
                    itemDTO.setItemNo(String.valueOf(obje[1]));
                    itemDTO.setItemName(String.valueOf(obje[0]));
                    itemDTO.setItemStatus(String.valueOf(obje[8]));
                    itemDTO.setBrand(String.valueOf(obje[3]));
                    if (obje[4] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[4]));
                        String finalString = df.format(date);
                        itemDTO.setPsStartDate(finalString);
                    } else {
                        itemDTO.setPsStartDate(null);
                    }
                    if (obje[5] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                        String finalString = df.format(date);
                        itemDTO.setPsEndDate(finalString);
                    } else {
                        itemDTO.setPsEndDate(null);
                    }
                    if (obje[7] != null && obje[7].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[7]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);
                levelDetailsResultsTable.setVisibleColumns("itemNo", "itemName", "therapyClass", "brand", "itemStatus", Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date");

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
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    itemDTO.setBrand(String.valueOf(obje[3]));
                    itemDTO.setStatus(String.valueOf(obje[4]));
                    if (obje[5] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                        String finalString = df.format(date);
                        itemDTO.setPsStartDate(finalString);
                    } else {
                        itemDTO.setPsStartDate(null);
                    }
                    if (obje[6] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[6]));
                        String finalString = df.format(date);
                        itemDTO.setPsEndDate(finalString);
                    } else {
                        itemDTO.setPsEndDate(null);
                    }
                    if (obje[2] != null && obje[2].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[2]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);

                levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
                levelDetailsResultsTable.setVisibleColumns("itemNo", "itemName", "therapyClass", "brand", "status", Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, "Theraphy Class", Constants.BRAND, "Status", "Start Date", "End Date");
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
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                for (int i = 0; i < itemList.size(); i++) {
                    NewComponentDTO itemDTO = new NewComponentDTO();
                    Object[] obje = (Object[]) itemList.get(i);
                    itemDTO.setItemNo(String.valueOf(obje[0]));
                    itemDTO.setItemName(String.valueOf(obje[1]));
                    itemDTO.setBrand(String.valueOf(obje[3]));
                    itemDTO.setStatus(String.valueOf(obje[4]));
                    if (obje[5] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                        String finalString = df.format(date);
                        itemDTO.setPsStartDate(finalString);

                    } else {
                        itemDTO.setPsStartDate(null);
                    }
                    if (obje[6] != null) {
                        Date date = (Date) FORMAT.parse(String.valueOf(obje[6]));
                        String finalString = df.format(date);
                        itemDTO.setPsEndDate(finalString);
                    } else {
                        itemDTO.setPsEndDate(null);
                    }
                    if (obje[2] != null && obje[2].equals(Constants.SELECT_ONE)) {
                        itemDTO.setTherapyClass(Constants.EMPTY);
                    } else {
                        itemDTO.setTherapyClass(String.valueOf(obje[2]));
                    }
                    list.add(itemDTO);
                }
                contractInfoContainer.addAll(list);

                levelDetailsResultsTable.setContainerDataSource(contractInfoContainer);
                levelDetailsResultsTable.setVisibleColumns("itemNo", "itemName", "therapyClass", "brand", "status", Constants.PS_START_DATE, Constants.PS_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, "Theraphy Class", Constants.BRAND, "Status", "Start Date", "End Date");
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
        CFPType.setValue(null);
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

    private void contractInfoTableAlign() {
        Object[] visibleColumns = levelDetailsResultsTable.getVisibleColumns();
        for (Object column : visibleColumns) {
            levelDetailsResultsTable.setColumnWidth(column, 160);
        }
    }

    private void updateToCFPDetails(int cfpModelSid, String temptableSId) {
        String query = "DELETE FROM CFP_DETAILS WHERE CFP_MODEL_SID = " + cfpModelSid + ";";
        query += " INSERT INTO CFP_DETAILS (COMPANY_MASTER_SID,COMPANY_START_DATE,COMPANY_END_DATE,TRADING_PARTNER_CONTRACT_NO,COMPANY_CFP_ATTACHED_STATUS,COMPANY_CFP_ATTACHED_DATE,CREATED_BY,CREATED_DATE,CFP_MODEL_SID,INBOUND_STATUS,RECORD_LOCK_STATUS,MODIFIED_BY,\"SOURCE\")\n"
                + "select COMPANY_MASTER_SID,CFP_DETAILS_START_DATE,CFP_DETAILS_END_DATE,CFP_DETAILS_TRADE_CLASS,COMPANY_STATUS,CFP_DETAILS_START_DATE,CREATED_BY,CREATED_DATE," + cfpModelSid + ",'A',0,1,'BPI' from GCM_COMPANY_DETAILS"
                + " where CFP_MODEL_SID=" + temptableSId + ";";
        int i = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    private void updateToIfpDetails(int ifpModelSid, String temptableSId) {
        String query = "DELETE FROM IFP_DETAILS WHERE IFP_MODEL_SID = " + ifpModelSid + ";";
        query += " INSERT INTO IFP_DETAILS(IFP_MODEL_SID, ITEM_MASTER_SID,ITEM_IFP_ATTACHED_STATUS,\n"
                + "  		START_DATE,END_DATE,INBOUND_STATUS,\n"
                + "  		RECORD_LOCK_STATUS,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,\"SOURCE\") "
                + " select " + ifpModelSid + ",ITEM_MASTER_SID,ITEM_STATUS_SID,IFP_DETAILS_START_DATE,IFP_DETAILS_END_DATE,'A',0,1,CREATED_DATE,1,MODIFIED_DATE,'BPI' from GCM_ITEM_DETAILS  where IFP_MODEL_SID=" + temptableSId + ";";
        int i = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    private void loadIFPResultsContainer() {
        List<String> ids = new ArrayList<String>();
        String sids = Constants.EMPTY;
        Collection<?> treeItem = dashboardResultsTable.getItemIds();
        for (Object item : treeItem) {
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.LEVELNO).getValue());
            if (level.equals("2")) {
                String hiddenId = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                ids.add(hiddenId);
                sids = hiddenId;
            }
        }
        String query = queryUtils.getTempTableIFP(sids);
        List ifpList = CompanyMasterLocalServiceUtil.executeQuery(query);
        if (ifpList != null && ifpList.size() > 0) {
            List<NewComponentDTO> list = new ArrayList<NewComponentDTO>();
            for (int i = 0; i < ifpList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) ifpList.get(i);
                itemDTO.setIfpId(String.valueOf(obje[0]));
                itemDTO.setIfpName(String.valueOf(obje[1]));
                itemDTO.setIfpNo(String.valueOf(obje[2]));
                itemDTO.setIfpStatus((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                if ((obje[6] != null) && (!obje[6].equals(Constants.SELECT_ONE))) {
                    itemDTO.setIfpType(String.valueOf(obje[6]));
                } else {
                    itemDTO.setIfpType(Constants.EMPTY);
                }
                itemDTO.setModelId(String.valueOf(obje[7]));
                list.add(itemDTO);
            }
            componentSearchContainer.addAll(list);
        }
        componentDetailsSearchTable.setVisibleColumns(Constants.CHECK, "ifpId", Constants.IFPNAME, "ifpNo", "ifpStatus", "ifpType");
        componentDetailsSearchTable.setColumnHeaders(Constants.EMPTY, "IFP Id", Constants.IfpNAME, Constants.IFP_NO, "Status", Constants.IFPTYPE);
    }

    private boolean checkStartDate() {
        List<NewComponentDTO> list = componentResultsContainer.getItemIds();
        boolean flag = Boolean.TRUE;
        for (NewComponentDTO list1 : list) {
            if (list1.getStartDate() == null) {
                flag = Boolean.FALSE;
            }
        }
        return flag;
    }

    private void updatePsAndRSModelSid(Collection<?> children, int modelSid, String temptableSId) {
        if (children != null) {
            for (Object item : children) {
               String id = String.valueOf(dashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
               logic.updatePsAndRsids(modelSid, id, "UPdate PS-IFP details");
            }
        }
    }

    public boolean checkSameItemInPs(String ifpId, Collection<?> itemIds, String queryName) {
        List input = new ArrayList();
        input.add(ifpId);
        List<Object> list = ItemQueries.getItemData(input, queryName, null);
        List dataList = new ArrayList<>();
        for (Object data : itemIds) {
            Integer itemId = Integer.valueOf(String.valueOf(componentResultsContainer.getContainerProperty(data, "itemMasterId").getValue()));
            dataList.add(itemId);
        }
        if (list.size() == dataList.size()) {
            Object[] listArr = dataList.toArray();
            Object[] dataArr = list.toArray();
            Arrays.sort(dataArr);
            Arrays.sort(listArr);
            return Arrays.deepEquals(dataArr, listArr);
        }
        return false;

    }

}
