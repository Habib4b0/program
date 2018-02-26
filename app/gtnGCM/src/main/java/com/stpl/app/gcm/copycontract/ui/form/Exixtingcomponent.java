/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import static com.stpl.app.gcm.common.QueryUtils.CHAR_ASTERISK;
import static com.stpl.app.gcm.common.QueryUtils.CHAR_PERCENT;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingComponentDetailsTableLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingComponentResultsTableLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingLevelDataTableLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
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
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class Exixtingcomponent extends CustomComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(Exixtingcomponent.class);
    @UiField("componentDetailsSelectedLayout")
    private VerticalLayout componentDetailsSelectedLayout;
    @UiField("componentDetailsSelectedItemLayout")
    private VerticalLayout componentDetailsSelectedItemLayout;
    @UiField("levelDetailsResultsLayout")
    private VerticalLayout levelDetailsResultsLayout;
    private final TreeTable dashboardResultsTable;
    @UiField("ComponenttypeEC")
    private ComboBox ComponenttypeNC;
    @UiField("SearchfieldEC")
    private ComboBox SearchfieldEC;
    @UiField("SearchfieldECDDlb")
    private ComboBox SearchfieldECDDlb;
    @UiField("SearchvaluedEC")
    private TextField SearchvaluedEC;
    @UiField("componentInformationGrid")
    private GridLayout componentInformationGrid;
    private final BeanItemContainer componentDetailsContainer = new BeanItemContainer(ExistingComponentDTO.class);
    public static final String PLEASE_SELECT_CORRECT_NODE = "Please Select Correct Node";
    public static final String THERE_WERE_NO_RECORDS_MATCHING = "There were no records matching the search criteria.  Please try again.";
    private final TextField CFPname = new TextField();
    @UiField("contractDashBoardLayout")
    private VerticalLayout contractDashBoardLayout;
    private final TextField CFPno = new TextField();
    private final TextField CFPid = new TextField();
    private final TextField CFPstatus = new TextField();
    private final TextField sdate = new TextField();
    private final TextField edate = new TextField();
    private final TextField CFPtype = new TextField();
    private final TextField CFPcategory = new TextField();
    private final TextField CFPtradeclass = new TextField();
    private final TextField CFPdesignation = new TextField();
    @UiField("componentDetails")
    private Panel componentDetails;
    @UiField("componentDetailsSelectedItemPanel")
    private Panel componentDetailsSelectedItemPanel;
    @UiField("contractDashBoardResults")
    private Panel contractDashBoardResults;
    @UiField("contractComponentDetails")
    private Panel contractComponentDetails;
    @UiField("BtnsearchEC")
    private Button BtnsearchEC;
    @UiField("resultpop")
    private Button resultpop;
    @UiField("levelRemoveBtn")
    private Button levelRemoveBtn;
    @UiField("levelpop")
    private Button levelpop;
    @UiField("cfpDetailsGrid")
    private GridLayout cfpDetailsGrid;
    @UiField("ifpDetailsGrid")
    private GridLayout ifpDetailsGrid;
    @UiField("psDetailsGrid")
    private GridLayout psDetailsGrid;
    @UiField("rsDetailsGrid")
    private GridLayout rsDetailsGrid;
    @UiField("cfpDetailsNo")
    private TextField cfpDetailsNo;
    @UiField("cfpDetailsName")
    private TextField cfpDetailsName;
    @UiField("ifpDetailsNo")
    private TextField ifpDetailsNo;
    @UiField("ifpDetailsName")
    private TextField ifpDetailsName;
    @UiField("psDetailsNo")
    private TextField psDetailsNo;
    @UiField("psDetailsName")
    private TextField psDetailsName;
    @UiField("rsDetailsNo")
    private TextField rsDetailsNo;
    @UiField("rsDetailsName")
    private TextField rsDetailsName;
    @UiField("addToTree")
    private Button addToTree;
    
    private LazyBeanItemContainer<CFPCompanyDTO> resultsLazyContainer1;
    private LazyBeanItemContainer<IFPItemDTO> resultsLazyContainer3;
    private LazyBeanItemContainer<PSIFPDTO> resultsLazyContainer6;
    private LazyBeanItemContainer<RsIfpDto> resultsLazyContainer7;
    private final BeanItemContainer<CopyComponentDTO> contractInfoContainer = new BeanItemContainer<>(CopyComponentDTO.class);
    private ExistingComponentDTO selectedItemDto;
    
    private final CopyContractLogic CopyContractLogic = new CopyContractLogic();
    private final ExtTreeContainer<CopyComponentDTO> dashBoardContainer;
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    public static final String SAVED_SYSTEM_ID = "savedSystemId";
    private final BeanItemContainer componentResultsContainer = new BeanItemContainer(ExistingComponentDTO.class);
    private final ExistingComponentResultsTableLogic componentReseultsTableLogic = new ExistingComponentResultsTableLogic();
    private final ExtPagedTable componentResultsSearchTable = new ExtPagedTable(componentReseultsTableLogic);
    private final ExistingComponentDetailsTableLogic componentDetailsTableLogic = new ExistingComponentDetailsTableLogic();
    private final ExtPagedTable componentDetailsTable = new ExtPagedTable(componentDetailsTableLogic);
    private final ExistingLevelDataTableLogic componentLevelTableLogic = new ExistingLevelDataTableLogic();
    private final ExtPagedTable levelDetailsResultsTable = new ExtPagedTable(componentLevelTableLogic);

    public Exixtingcomponent(TreeTable contractDashBoardTable,ExtTreeContainer<CopyComponentDTO> dashBoardContainer) {
        this.dashboardResultsTable = contractDashBoardTable;
        this.dashBoardContainer=dashBoardContainer;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/Existing Component.xml"), this));
        contractDashBoardLayout.addComponent(contractDashBoardTable);
        configureFields();
        configureSecurityPermissions();
    }

    protected void configureFields() {
        try {

            configureDetailsSearchTable();
            configureResultsSearchTable();
            componentDetailsSelectedItemPanel.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
            componentDetailsSelectedItemPanel.setHeight(NumericConstants.HUNDRED,Unit.PERCENTAGE);
            contractDashBoardResults.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
            ComponenttypeNC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            ComponenttypeNC.setNullSelectionAllowed(true);
            ComponenttypeNC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            ComponenttypeNC.addItem(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN);
            ComponenttypeNC.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN);
            ComponenttypeNC.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE);
            ComponenttypeNC.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE);
            ComponenttypeNC.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            SearchfieldEC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            SearchfieldEC.setNullSelectionAllowed(true);
            SearchfieldEC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            ComponenttypeNC.setImmediate(true);
            dashboardResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            dashboardResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            
            dashboardResultsTable.setPageLength(NumericConstants.TEN);
            dashboardResultsTable.setContainerDataSource(dashBoardContainer);
            dashboardResultsTable.setVisibleColumns(Constants.getInstance().copycontractDashboardResultsColumns);
            dashboardResultsTable.setColumnHeaders(Constants.getInstance().copycontractDashboardResultsHeaders);
            
            dashboardResultsTable.setSelectable(true);
            dashboardResultsTable.setMultiSelect(false);
            
            CFPname.setEnabled(false);
            CFPno.setEnabled(false);
            CFPid.setEnabled(false);
            CFPstatus.setEnabled(false);
            sdate.setEnabled(false);
            edate.setEnabled(false);
            CFPtype.setEnabled(false);
            CFPcategory.setEnabled(false);
            CFPtradeclass.setEnabled(false);
            CFPdesignation.setEnabled(false);
            componentResultsSearchTable.addListener(new ItemClickEvent.ItemClickListener() {
                @Override
                public void itemClick(ItemClickEvent event) {

                    if (resultsLazyContainer1 != null) {
                        resultsLazyContainer1.removeAllItems();
                    }
                    if (resultsLazyContainer3 != null) {
                        resultsLazyContainer3.removeAllItems();
                    }
                    if (resultsLazyContainer6 != null) {
                        resultsLazyContainer6.removeAllItems();
                    }
                    if (Constants.COMPANY_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                        selectedItemDto = (ExistingComponentDTO) event.getItemId();
                    }
                    if (Constants.ITEM_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                        selectedItemDto = (ExistingComponentDTO) event.getItemId();
                    }
                    if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                        selectedItemDto = (ExistingComponentDTO) event.getItemId();
                    }
                    if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                        selectedItemDto = (ExistingComponentDTO) event.getItemId();
                    }
                    
                }
            });
            
            dashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            dashboardResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            dashboardResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            dashboardResultsTable.setPageLength(NumericConstants.FIVE);
            dashboardResultsTable.addListener(new ItemClickEvent.ItemClickListener() {
                @Override
                public void itemClick(ItemClickEvent event) {
                    return;
                }
            });
            configureLevelSearchTable();
            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);
            cfpDetailsNo.setEnabled(false);
            cfpDetailsName.setEnabled(false);
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

    @UiHandler("ComponenttypeEC")
    public void ComponenttypeChange(Property.ValueChangeEvent event) {

        loadComponentResultsSection();
        componentInformationGrid.removeAllComponents();
        if (componentResultsSearchTable != null) {
            componentResultsSearchTable.removeAllItems();
        }
        if (resultsLazyContainer1 != null) {
            resultsLazyContainer1.removeAllItems();
        }
        if (resultsLazyContainer3 != null) {
            resultsLazyContainer3.removeAllItems();
        }

        if (resultsLazyContainer6 != null) {
            resultsLazyContainer6.removeAllItems();
        }
        if (resultsLazyContainer7 != null) {
            resultsLazyContainer7.removeAllItems();
        }
        loadComponentInformationSection();
    }

    @UiHandler("SearchfieldEC")
    public void SearchfieldECChange(Property.ValueChangeEvent event) {
        SearchfieldECDDlb.removeAllItems();
        if (SearchfieldEC.getValue() != null && SearchfieldEC.getValue().toString().contains(Constants.STATUS_FIELD)) {
            SearchfieldECDDlb.setVisible(true);
            commonUtil.loadComboBox(SearchfieldECDDlb, UiUtils.STATUS, false);
            SearchfieldECDDlb.setValidationVisible(true);
            SearchvaluedEC.setVisible(false);
        } else if (SearchfieldEC.getValue() != null && SearchfieldEC.getValue().toString().contains("Type")) {

            SearchfieldECDDlb.setVisible(true);
            SearchfieldECDDlb.setNullSelectionItemId(Constants.ZEROSTRING);
            if (SearchfieldEC.getValue().toString().contains(Constants.CFP)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UiUtils.CFP_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.IFP)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UiUtils.IFP_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.PS)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UiUtils.PS_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.RS)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UiUtils.RS_TYPE, false);
            }
            SearchfieldECDDlb.setImmediate(true);
            SearchfieldECDDlb.setValidationVisible(true);
            SearchvaluedEC.setVisible(false);

        } else {
            SearchfieldECDDlb.removeAllItems();
            SearchfieldECDDlb.setVisible(false);
            SearchvaluedEC.setVisible(true);
            SearchvaluedEC.setValue(StringUtils.EMPTY);
        }

    }

    private void loadComponentResultsSection() {
        SearchfieldEC.removeAllItems();
        SearchfieldEC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        SearchfieldEC.setNullSelectionAllowed(true);
        SearchfieldEC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        if (Constants.COMPANY_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            SearchfieldEC.addItem("CFP ID");
            SearchfieldEC.addItem("CFP No");
            SearchfieldEC.addItem("CFP Name");
            SearchfieldEC.addItem("CFP Status");
            SearchfieldEC.addItem("CFP Type");
            componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractCfpResultsColumns);
            componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractCfpResultsHeaders);
            componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newCompanyDetailsColumns);
            componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newCompanyDetailsHeaders);
        }

        if (Constants.ITEM_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            SearchfieldEC.addItem(Constants.IFP_ID);
            SearchfieldEC.addItem(Constants.IFP_NO);
            SearchfieldEC.addItem(Constants.IFP_NAME_LABEL);
            SearchfieldEC.addItem(Constants.IFP_STATUS);
            SearchfieldEC.addItem(Constants.IFPTYPE);
            componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractIfpResultsColumns);
            componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractIfpResultsHeaders);
            componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newIfpDetailsColumns);
            componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newIfpDetailsHeaders);
        }

        if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractPsResultsColumns);
            componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractPsResultsHeaders);
            SearchfieldEC.addItem("PS ID");
            SearchfieldEC.addItem("PS No");
            SearchfieldEC.addItem("PS Name");
            SearchfieldEC.addItem("PS Status");
            SearchfieldEC.addItem("PS Type");
            componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newPsDetailsColumns);
            componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newPsDetailsHeaders);

        }

        if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractRsResultsColumns);
            componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractRsResultsHeaders);
            SearchfieldEC.addItem("RS ID");
            SearchfieldEC.addItem("RS No");
            SearchfieldEC.addItem("RS Name");
            SearchfieldEC.addItem("RS Status");
            SearchfieldEC.addItem("RS Type");
            componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newRsDetailsColumns);
            componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newRsDetailsHeaders);
        }
    }

    private void loadComponentInformationSection() {
        if (Constants.COMPANY_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentInformationGrid.addComponent(new Label("CFP ID:"));
            componentInformationGrid.addComponent(CFPid);
            CFPid.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP No:"));
            componentInformationGrid.addComponent(CFPno);
            CFPno.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Name:"));
            componentInformationGrid.addComponent(CFPname);
            CFPname.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Status:"));
            componentInformationGrid.addComponent(CFPstatus);
            CFPstatus.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Start Date:"));
            componentInformationGrid.addComponent(sdate);
            sdate.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP End Date:"));
            componentInformationGrid.addComponent(edate);
            edate.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Type:"));
            componentInformationGrid.addComponent(CFPtype);
            CFPtype.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Category:"));
            componentInformationGrid.addComponent(CFPcategory);
            CFPcategory.setValue(StringUtils.EMPTY);
            componentInformationGrid.addComponent(new Label("CFP Designation:"));
            componentInformationGrid.addComponent(CFPdesignation);
            CFPdesignation.setValue(StringUtils.EMPTY);
        } else if (Constants.ITEM_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentInformationGrid.addComponent(new Label("IFP ID:"));
            componentInformationGrid.addComponent(CFPid);
            componentInformationGrid.addComponent(new Label("IFP No:"));
            componentInformationGrid.addComponent(CFPno);
            componentInformationGrid.addComponent(new Label("IFP Name:"));
            componentInformationGrid.addComponent(CFPname);
            componentInformationGrid.addComponent(new Label("IFP Status:"));
            componentInformationGrid.addComponent(CFPstatus);
            componentInformationGrid.addComponent(new Label("IFP Start Date:"));
            componentInformationGrid.addComponent(sdate);
            componentInformationGrid.addComponent(new Label("IFP End Date:"));
            componentInformationGrid.addComponent(edate);
            componentInformationGrid.addComponent(new Label("IFP Type:"));
            componentInformationGrid.addComponent(CFPtype);
            componentInformationGrid.addComponent(new Label("IFP Designation:"));
            componentInformationGrid.addComponent(CFPdesignation);
            CFPid.setValue(StringUtils.EMPTY);
            CFPno.setValue(StringUtils.EMPTY);
            CFPname.setValue(StringUtils.EMPTY);
            CFPstatus.setValue(StringUtils.EMPTY);
            sdate.setValue(StringUtils.EMPTY);
            edate.setValue(StringUtils.EMPTY);
            CFPtype.setValue(StringUtils.EMPTY);
            CFPdesignation.setValue(StringUtils.EMPTY);

        } else if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentInformationGrid.addComponent(new Label("PS ID:"));
            componentInformationGrid.addComponent(CFPid);
            componentInformationGrid.addComponent(new Label("PS No:"));
            componentInformationGrid.addComponent(CFPno);
            componentInformationGrid.addComponent(new Label("PS Name:"));
            componentInformationGrid.addComponent(CFPname);
            componentInformationGrid.addComponent(new Label("PS Status:"));
            componentInformationGrid.addComponent(CFPstatus);
            componentInformationGrid.addComponent(new Label("PS Start Date:"));
            componentInformationGrid.addComponent(sdate);
            componentInformationGrid.addComponent(new Label("PS End Date:"));
            componentInformationGrid.addComponent(edate);
            componentInformationGrid.addComponent(new Label("PS Type:"));
            componentInformationGrid.addComponent(CFPtype);
            componentInformationGrid.addComponent(new Label("PS Designation:"));
            componentInformationGrid.addComponent(CFPdesignation);
            CFPid.setValue(StringUtils.EMPTY);
            CFPno.setValue(StringUtils.EMPTY);
            CFPname.setValue(StringUtils.EMPTY);
            CFPstatus.setValue(StringUtils.EMPTY);
            sdate.setValue(StringUtils.EMPTY);
            edate.setValue(StringUtils.EMPTY);
            CFPtype.setValue(StringUtils.EMPTY);
            CFPdesignation.setValue(StringUtils.EMPTY);
        }
        if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentInformationGrid.addComponent(new Label("RS ID:"));
            componentInformationGrid.addComponent(CFPid);
            componentInformationGrid.addComponent(new Label("RS Status:"));
            componentInformationGrid.addComponent(CFPstatus);
            componentInformationGrid.addComponent(new Label("RS Frequency:"));
            componentInformationGrid.addComponent(CFPtype);
            componentInformationGrid.addComponent(new Label("RS Number:"));
            componentInformationGrid.addComponent(CFPno);
            componentInformationGrid.addComponent(new Label("Start Date:"));
            componentInformationGrid.addComponent(sdate);
            componentInformationGrid.addComponent(new Label("RAR Type:"));
            componentInformationGrid.addComponent(CFPcategory);
            componentInformationGrid.addComponent(new Label("RS Name:"));
            componentInformationGrid.addComponent(CFPname);
            componentInformationGrid.addComponent(new Label("End Date:"));
            componentInformationGrid.addComponent(edate);
            componentInformationGrid.addComponent(new Label("Basis:"));
            componentInformationGrid.addComponent(CFPdesignation);
            CFPid.setValue(StringUtils.EMPTY);
            CFPstatus.setValue(StringUtils.EMPTY);
            CFPtype.setValue(StringUtils.EMPTY);
            CFPno.setValue(StringUtils.EMPTY);
            sdate.setValue(StringUtils.EMPTY);
            edate.setValue(StringUtils.EMPTY);
            CFPtype.setValue(StringUtils.EMPTY);
            CFPdesignation.setValue(StringUtils.EMPTY);
            CFPcategory.setValue(StringUtils.EMPTY);

        }
    }

    @UiHandler("BtnsearchEC")
    public void BtnsearchECClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");

        if (ComponenttypeNC.getValue() != null && SearchfieldEC.getValue() != null && (!SearchvaluedEC.getValue().equalsIgnoreCase(StringUtils.EMPTY) || SearchfieldECDDlb.getValue() != null)) {
            List input = new ArrayList();
            if (SearchfieldEC.getValue().toString().contains("ID")) {
                input.add(SearchvaluedEC.getValue().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("No")) {
                input.add(SearchvaluedEC.getValue().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("Name")) {
                input.add(SearchvaluedEC.getValue().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("Type")) {
                input.add(SearchfieldECDDlb.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
                input.add("");
            } else {
                input.add(Constants.PERCENT);
                if (SearchfieldEC.getValue().toString().contains("PS")) {
                    input.add(" OR ps.PS_TYPE IS NULL");
                } else if (SearchfieldEC.getValue().toString().contains("RS")) {
                    input.add(" OR rs.RS_TYPE IS NULL");
                } else if (SearchfieldEC.getValue().toString().contains("CFP")) {
                    input.add(" OR cfp.CFP_TYPE IS NULL");
                } else if (SearchfieldEC.getValue().toString().contains("IFP")) {
                    input.add(" OR ifp.IFP_TYPE IS NULL");
                }
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.STATUS_FIELD)) {
                input.add(SearchfieldECDDlb.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (Constants.COMPANY_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractCfpResultsColumns);
                componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractCfpResultsHeaders);
                componentResultsSearchTable.setColumnAlignment("companyFamilyPlanStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("companyFamilyPlanEndDate", ExtCustomTable.Align.CENTER);
            }
            if (Constants.ITEM_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractIfpResultsColumns);
                componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractIfpResultsHeaders);
                componentResultsSearchTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setRefresh(true);
            }

            if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractPsResultsColumns);
                componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractPsResultsHeaders);
                componentResultsSearchTable.setColumnAlignment("priceScheduleStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("priceScheduleEndDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setRefresh(true);
            }
            if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractRsResultsColumns);
                componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractRsResultsHeaders);
                componentResultsSearchTable.setRefresh(true);
                componentResultsSearchTable.setColumnAlignment("itemRebateStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("itemRebateEndDate", ExtCustomTable.Align.CENTER);
            }
            if (componentReseultsTableLogic.loadSetData(String.valueOf(ComponenttypeNC.getValue()), input, true)) {
                AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS, THERE_WERE_NO_RECORDS_MATCHING);
            }
            LOGGER.debug(
                    "Ending search method");
        } else {
            AbstractNotificationUtils.getErrorNotification("Search",
                    "Please enter a Search Value.");
        }
    }
    
    @UiHandler("resultpop")
    public void resultpopClick(Button.ClickEvent event) {
        selectedItemDto = (ExistingComponentDTO) componentResultsSearchTable.getValue();
        
        if (componentResultsSearchTable.getValue() != null) {
            if (Constants.COMPANY_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getCompanyFamilyPlanId());
                CFPname.setValue(selectedItemDto.getCompanyFamilyPlanName());
                CFPno.setValue(selectedItemDto.getCompanyFamilyPlanNo());
                CFPstatus.setValue(selectedItemDto.getCompanyFamilyPlanStatusValue());
                CFPtype.setValue(selectedItemDto.getCompanyFamilyPlanTypeValue());
                CFPcategory.setValue(selectedItemDto.getCompanyFamilyPlanCategoryValue());
                CFPdesignation.setValue(selectedItemDto.getCompanyFamilyPlanDesignation());
                sdate.setValue(selectedItemDto.getCompanyFamilyPlanStartDate());
                edate.setValue(selectedItemDto.getCompanyFamilyPlanEndDate());
                componentDetailsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newCompanyDetailsColumns);
                componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newCompanyDetailsHeaders);
                componentDetailsTable.setColumnAlignment("tradeClassStartDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment("tradeClassEndDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setRefresh(true);
                if (componentDetailsTableLogic.loadSetData(Constants.COMPANY_FAMILY_PLAN, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS, THERE_WERE_NO_RECORDS_MATCHING);
                }

            }
            if (Constants.ITEM_FAMILY_PLAN.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getItemFamilyplanId());
                CFPname.setValue(selectedItemDto.getItemFamilyplanName());
                CFPno.setValue(selectedItemDto.getItemFamilyplanNo());
                CFPstatus.setValue(selectedItemDto.getDisplayIFPStatus());
                CFPtype.setValue(selectedItemDto.getItemFamilyplanType());
                sdate.setValue(selectedItemDto.getIfpStartDate());
                edate.setValue(selectedItemDto.getIfpEndDate());
                CFPdesignation.setValue(selectedItemDto.getItemFamilyplanDesignation());
                componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newIfpDetailsColumns);
                componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newIfpDetailsHeaders);
                componentDetailsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_START_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_END_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData(Constants.ITEM_FAMILY_PLAN, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS, THERE_WERE_NO_RECORDS_MATCHING);
                }

            }

            if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getPriceScheduleIdValue());
                CFPname.setValue(selectedItemDto.getPriceScheduleNameValue());
                CFPtype.setValue(selectedItemDto.getPriceScheduleTypeValue());
                CFPno.setValue(selectedItemDto.getPriceScheduleNoValue());
                CFPstatus.setValue(selectedItemDto.getPriceScheduleStatusValue());
                sdate.setValue(selectedItemDto.getPriceScheduleStartDate());
                edate.setValue(selectedItemDto.getPriceScheduleEndDate());
                CFPdesignation.setValue(selectedItemDto.getPriceScheduleDesignationValue());
                componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newPsDetailsColumns);
                componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newPsDetailsHeaders);
                componentDetailsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_START_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_END_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData(Constants.PRICE_SCHEDULE, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS, THERE_WERE_NO_RECORDS_MATCHING);
                }

            }

            if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getRebateScheduleId());
                CFPname.setValue(selectedItemDto.getRebateScheduleName());
                CFPno.setValue(selectedItemDto.getRebateScheduleNo());
                CFPstatus.setValue(selectedItemDto.getStatusRebate());
                sdate.setValue(selectedItemDto.getItemRebateStartDate());
                edate.setValue(selectedItemDto.getItemRebateEndDate());
                componentDetailsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newRsDetailsColumns);
                componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newRsDetailsHeaders);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_START_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment(Constants.ITEM_END_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData(Constants.REBATE_SCHEDULE, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS, THERE_WERE_NO_RECORDS_MATCHING);
                }

            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Populate",
                    "Please highlight a row to populate.");

        }
    }

    @UiHandler("addToTree")
    public void addToTreeClick(Button.ClickEvent event) {

        if (dashboardResultsTable.getItemIds().isEmpty()) {

            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Add Contract Header Node");

        } else if (componentResultsSearchTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select a record to Add.");
        } else {
            Object root = dashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.parseInt(levelNo);
                String level = String.valueOf(ComponenttypeNC.getValue());
                if (level.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    if (1 - levelNumber == 1) {

                        Integer cfpId = selectedItemDto.getCompanyFamilyPlanSystemId();
                        String query = "select CFP_ID,CFP_NO,CFP_NAME from dbo.CFP_MODEL where CFP_MODEL_SID=" + cfpId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.CFP);
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue("1");
                            dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(String.valueOf(cfpId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, true);
                            dashboardResultsTable.setCollapsed(root, false);
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.ITEM_FAMILY_PLAN)) {
                    if (NumericConstants.TWO - levelNumber == 1) {

                        Integer ifpId = selectedItemDto.getIfpDetailsSystemId();
                        String query = "select IFP_ID,IFP_NO,IFP_NAME from dbo.IFP_MODEL where IFP_MODEL_SID=" + ifpId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue("2");
                            dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(String.valueOf(ifpId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, true);
                            dashboardResultsTable.setCollapsed(root, false);
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (NumericConstants.THREE - levelNumber == 1) {

                        String psId = selectedItemDto.getPriceScheduleSystemId();
                        String Parent = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                        String conditionQuery = "select * from PS_DETAILS where PS_MODEL_SID=" + psId + " and IFP_MODEL_SID=" + Parent;
                        List conditionList = HelperTableLocalServiceUtil.executeSelectQuery(conditionQuery);
                        if (conditionList != null && conditionList.size() > 0) {
                            String query = "select PS_ID,PS_NO,PS_NAME from dbo.PS_MODEL where PS_MODEL_SID=" + psId;
                            List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                            if (cfpList != null && cfpList.size() > 0) {
                                Object[] obj = (Object[]) cfpList.get(0);
                                final Object rootId = dashboardResultsTable.addItem();
                                dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
                                dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue("3");
                                dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(psId);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("2");
                                dashboardResultsTable.addItem(rootId);
                                dashboardResultsTable.setParent(rootId, root);
                                dashboardResultsTable.setChildrenAllowed(rootId, true);
                                dashboardResultsTable.setCollapsed(root, false);
                            }
                            
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS does not associate with  IFP");
                        }
                        
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (NumericConstants.FOUR - levelNumber == 1) {

                        Integer rsId = selectedItemDto.getRebateScheduleSystemId();
                        String query = "select RS_ID,RS_NO,RS_NAME from dbo.RS_MODEL where RS_MODEL_SID=" + rsId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue("4");
                            dashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(String.valueOf(rsId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.getADDBY()).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, false);
                            dashboardResultsTable.setCollapsed(root, false);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, PLEASE_SELECT_CORRECT_NODE);
                    }
                }
                
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }
            
        }
    }
    
    @UiHandler("levelRemoveBtn")
    public void levelRemoveBtnClick(Button.ClickEvent event) {
        Object root = dashboardResultsTable.getValue();
        if (root != null) {
            Boolean flag = dashboardResultsTable.hasChildren(root);
            if (!flag) {
                dashboardResultsTable.removeItem(root);
            } else {
                AbstractNotificationUtils.getErrorNotification("Remove", "Please remove all children nodes before removing a parent node");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Remove", "Please highlight a component to Remove");
        }
    }

    @UiHandler("levelpop")
    public void levelpopBtnClick(Button.ClickEvent event) {
        Object root = dashboardResultsTable.getValue();
        if (root != null) {
            Integer level = Integer.valueOf(String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue()));
            Integer cfpId = Integer.valueOf(String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue()));
            if (level.equals(1)) {
                levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", Constants.COMPANY_START_DATE, Constants.COMPANY_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYSTATUS, "Start Date", "Company End Date");
                levelDetailsResultsTable.setColumnAlignment(Constants.COMPANY_START_DATE, ExtCustomTable.Align.CENTER);
                levelDetailsResultsTable.setColumnAlignment(Constants.COMPANY_END_DATE, ExtCustomTable.Align.CENTER);
                cfpDetailsGrid.setVisible(true);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                cfpDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                cfpDetailsName.setValue(detailsName);
                cfpDetailsNo.setReadOnly(true);
                cfpDetailsName.setReadOnly(true);

            } else if (level.equals(NumericConstants.TWO) || level.equals(NumericConstants.THREE) || level.equals(NumericConstants.FOUR)) {
                levelDetailsResultsTable.setVisibleColumns("itemNo", "itemName", "therapyClass", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, "Start Date", "End Date");
                levelDetailsResultsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                levelDetailsResultsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                if (level.equals(NumericConstants.TWO)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(true);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                    ifpDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    ifpDetailsName.setValue(detailsName);
                    ifpDetailsNo.setReadOnly(true);
                    ifpDetailsName.setReadOnly(true);
                } else if (level.equals(NumericConstants.THREE)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(true);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                    psDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    psDetailsName.setValue(detailsName);
                    psDetailsNo.setReadOnly(true);
                    psDetailsName.setReadOnly(true);
                } else if (level.equals(NumericConstants.FOUR)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(true);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                    rsDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    rsDetailsName.setValue(detailsName);
                    rsDetailsNo.setReadOnly(true);
                    rsDetailsName.setReadOnly(true);
                }
            }
            componentLevelTableLogic.loadSetData(level, cfpId, true);

        } else {
            AbstractNotificationUtils.getErrorNotification("Populate", "Please highlight a component to Populate");
        }
    }
    
    public int getTabNumber() {
        return 1;
    }
    
    public void savecontract(Object item) throws PortalException {
        
        String contractsidvalue;
        Object[] itemIds = {item};
        for (int i = 0; i < itemIds.length; i++) {
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.LEVELNO).getValue());
            if (level.equalsIgnoreCase(Constants.ZEROSTRING)) {
                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_ID).getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_NUMBER).getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.DASHBOARD_NAME).getValue());
                int contractType = ((HelperDTO) dashboardResultsTable.getContainerProperty(itemIds[i], "marketType").getValue()).getId();
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], Constants.END_DATE).getValue();
                String contractHolder = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "contractHolder").getValue());
                int status1 = ((HelperDTO) dashboardResultsTable.getContainerProperty(itemIds[i], "status").getValue()).getId();
                int AliasType = ((HelperDTO) dashboardResultsTable.getContainerProperty(itemIds[i], "aliasType").getValue()).getId();
                Date AliasSDATE = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], "aliasstartdate").getValue();
                String AliasNumber = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "aliasNumber").getValue());
                Date AliasEDATE = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], "aliasenddate").getValue();
                ContractMaster cm;
                cm = ContractMasterLocalServiceUtil.createContractMaster(0);
                cm.setContractId(contractId);
                cm.setContractNo(contractNo);
                cm.setContractName(contractName);
                cm.setCreatedDate(new Date());
                cm.setCreatedBy(1);
                cm.setContractStatus(status1);
                cm.setContHoldCompanyMasterSid(contractHolder);
                cm.setSource("BPI");
                cm.setStartDate(startDate);
                cm.setEndDate(endDate);
                cm.setInboundStatus("A");
                cm.setProcessStatus(true);
                cm.setContractType(contractType);
                cm.setRecordLockStatus(false);
                cm.setModifiedDate(new Date());
                ContractMaster cm1 = ContractMasterLocalServiceUtil.addContractMaster(cm);
                contractsidvalue = String.valueOf(cm1.getContractMasterSid());
                dashboardResultsTable.getContainerProperty(itemIds[i], SAVED_SYSTEM_ID).setValue(contractsidvalue);
                ContractAliasMaster CAM = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                CAM.setContractAliasNo(AliasNumber);
                CAM.setContractAliasType(Integer.valueOf(AliasType));
                CAM.setStartDate(AliasSDATE);
                CAM.setEndDate(AliasEDATE);
                CAM.setModifiedDate(new Date());
                CAM.setCreatedBy(1);
                CAM.setCreatedDate(new Date());
                CAM.setSource("BPI");
                CAM.setInboundStatus("A");
                CAM.setContractMasterSid(cm1.getContractMasterSid());
                ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);
            }
            if (level.equalsIgnoreCase("1")) {
                String category = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.CATEGORY).getValue());
                if (category.equalsIgnoreCase(Constants.CFP)) {
                    
                    Object contractParent = dashboardResultsTable.getParent(itemIds[i]);
                    String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.HIDDEN_ID).getValue());
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractParent, SAVED_SYSTEM_ID).getValue());
                    
                    final CfpModel companyFamily = CfpModelLocalServiceUtil.getCfpModel(Integer.parseInt(idvalue));
                    final CfpContract cfpMasterAttached = CfpContractLocalServiceUtil.createCfpContract(0);
                    cfpMasterAttached.setCfpName(companyFamily.getCfpName());
                    cfpMasterAttached.setCfpNo(companyFamily.getCfpNo());
                    cfpMasterAttached.setCfpModelSid(companyFamily.getCfpModelSid());
                    cfpMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
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
                    SaveCFP(String.valueOf(cm1.getCfpContractSid()), companyFamily.getCfpModelSid());
                    dashboardResultsTable.getContainerProperty(itemIds[i], SAVED_SYSTEM_ID).setValue(String.valueOf(cm1.getCfpContractSid()));
                }

            }
            if (level.equalsIgnoreCase("2")) {
                String category = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.CATEGORY).getValue());
                if (category.equalsIgnoreCase(Constants.IFP)) {
                    String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.HIDDEN_ID).getValue());
                    final IfpModel itemFamily = IfpModelLocalServiceUtil.getIfpModel(Integer.parseInt(idvalue));
                    final IfpContract ifpMasterAttached = IfpContractLocalServiceUtil.createIfpContract(0);
                    
                    ifpMasterAttached.setIfpModelSid(itemFamily.getIfpModelSid());
                    ifpMasterAttached.setIfpName(itemFamily.getIfpName());
                    ifpMasterAttached.setIfpNo(itemFamily.getIfpNo());
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

                    Object parentItem = dashboardResultsTable.getParent(itemIds[i]);
                    String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, SAVED_SYSTEM_ID).getValue());
                    ifpMasterAttached.setCfpContractSid(parentCFPId);
                    Object contractItem = dashboardResultsTable.getParent(parentItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, SAVED_SYSTEM_ID).getValue());
                    ifpMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
                    IfpContract im1 = IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
                    SaveIFP(String.valueOf(im1.getIfpContractSid()), itemFamily.getIfpModelSid());
                    dashboardResultsTable.getContainerProperty(itemIds[i], SAVED_SYSTEM_ID).setValue(String.valueOf(im1.getIfpContractSid()));
                }
            }
            if (level.equalsIgnoreCase("3")) {
                String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.HIDDEN_ID).getValue());
                final PsModel priceSchedule = PsModelLocalServiceUtil.getPsModel(Integer.parseInt(idvalue));
                final PsContract psMasterAttached = PsContractLocalServiceUtil.createPsContract(0);
                
                psMasterAttached.setPsName(priceSchedule.getPsName());
                psMasterAttached.setPsNo(priceSchedule.getPsNo());
                psMasterAttached.setPsType(priceSchedule.getPsType());
                psMasterAttached.setPsCategory(priceSchedule.getPsCategory());
                
                psMasterAttached.setPsDesignation(priceSchedule.getPsDesignation());
                psMasterAttached.setPsStatus(priceSchedule.getPsStatus());
                psMasterAttached.setPsStartDate(priceSchedule.getPsStartDate());
                psMasterAttached.setPsEndDate(priceSchedule.getPsEndDate());

                psMasterAttached.setPsContractAttachedDate(new Date());
                psMasterAttached.setSource("BPI");
                psMasterAttached.setCreatedBy(1);
                psMasterAttached.setCreatedDate(new Date());
                psMasterAttached.setModifiedBy(1);
                psMasterAttached.setModifiedDate(new Date());
                psMasterAttached.setRecordLockStatus(false);
                psMasterAttached.setInboundStatus("A");
                psMasterAttached.setPsModelSid(priceSchedule.getPsModelSid());
                Object parentItem = dashboardResultsTable.getParent(itemIds[i]);
                String parentIFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, SAVED_SYSTEM_ID).getValue());
                Object parentCFPItem = dashboardResultsTable.getParent(parentItem);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, SAVED_SYSTEM_ID).getValue());
                Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, SAVED_SYSTEM_ID).getValue());

                psMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
                psMasterAttached.setCfpContractSid(parentCFPId);
                psMasterAttached.setIfpContractSid(parentIFPId);
                PsContract im1 = PsContractLocalServiceUtil.addPsContract(psMasterAttached);
                SavePS(String.valueOf(im1.getPsContractSid()), priceSchedule.getPsModelSid());
                dashboardResultsTable.getContainerProperty(itemIds[i], SAVED_SYSTEM_ID).setValue(String.valueOf(im1.getPsContractSid()));
            } else if (level.equals("4")) {
                Object psParentItem = dashboardResultsTable.getParent(itemIds[i]);
                String parentPSId = String.valueOf(dashboardResultsTable.getContainerProperty(psParentItem, SAVED_SYSTEM_ID).getValue());
                Object parentIFPItem = dashboardResultsTable.getParent(psParentItem);
                String parentIFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentIFPItem, SAVED_SYSTEM_ID).getValue());
                Object parentCFPItem = dashboardResultsTable.getParent(parentIFPItem);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, SAVED_SYSTEM_ID).getValue());
                Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, SAVED_SYSTEM_ID).getValue());

                String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], Constants.HIDDEN_ID).getValue());
                final RsModel rebateMaster = RsModelLocalServiceUtil.getRsModel(Integer.parseInt(idvalue));
                final RsContract rsMasterAttached = RsContractLocalServiceUtil.createRsContract(0);
                rsMasterAttached.setContractMasterSid(Integer.parseInt(contractSId));
                rsMasterAttached.setCfpContractSid(parentCFPId);
                rsMasterAttached.setIfpContractSid(parentIFPId);
                rsMasterAttached.setPsContractSid(parentPSId);
                rsMasterAttached.setRsModelSid(rebateMaster.getRsModelSid());
                rsMasterAttached.setRsName(rebateMaster.getRsName());
                rsMasterAttached.setRsId(rebateMaster.getRsId());
                rsMasterAttached.setRsNo(rebateMaster.getRsNo());
                rsMasterAttached.setRsType(rebateMaster.getRsType());
                rsMasterAttached.setRebateProgramType(rebateMaster.getRebateProgramType());
                rsMasterAttached.setRsCategory(rebateMaster.getRsCategory());
                rsMasterAttached.setRsStartDate(rebateMaster.getRsStartDate());
                rsMasterAttached.setRsEndDate(rebateMaster.getRsEndDate());
                rsMasterAttached.setRsTradeClass(rebateMaster.getRsTradeClass());
                rsMasterAttached.setRsDesignation(String.valueOf(rebateMaster.getRsDesignation()));
                rsMasterAttached.setParentRsId(rebateMaster.getParentRsId());
                rsMasterAttached.setParentRsName(rebateMaster.getParentRsName());
                rsMasterAttached.setRsStatus(rebateMaster.getRsStatus());
                rsMasterAttached.setRsTransRefId(rebateMaster.getRsTransRefId());
                rsMasterAttached.setRsTransRefNo(rebateMaster.getRsTransRefNo());
                rsMasterAttached.setRsTransRefName(rebateMaster.getRsTransRefName());
                rsMasterAttached.setPaymentMethod(rebateMaster.getPaymentMethod());
                rsMasterAttached.setPaymentFrequency(rebateMaster.getPaymentFrequency());
                rsMasterAttached.setPaymentTerms(rebateMaster.getPaymentTerms());
                rsMasterAttached.setRebateFrequency(rebateMaster.getRebateFrequency());
                rsMasterAttached.setRsCalendar(String.valueOf(rebateMaster.getRsCalendar()));
                rsMasterAttached.setRebateProcessingType(rebateMaster.getRebateProcessingType());
                rsMasterAttached.setRsValidationProfile(rebateMaster.getRsValidationProfile());
                rsMasterAttached.setRebateRuleType(rebateMaster.getRebateRuleType());
                rsMasterAttached.setRebateRuleAssociation(rebateMaster.getRebateRuleAssociation());
                rsMasterAttached.setRebatePlanLevel(rebateMaster.getRebatePlanLevel());
                rsMasterAttached.setInterestBearingIndicator(rebateMaster.getInterestBearingIndicator());
                rsMasterAttached.setInterestBearingBasis(rebateMaster.getInterestBearingBasis());
                rsMasterAttached.setPaymentGracePeriod(rebateMaster.getPaymentGracePeriod());
                rsMasterAttached.setRsContractAttachedDate(new Date());
                rsMasterAttached.setMakePayableTo(rebateMaster.getMakePayableTo());
                rsMasterAttached.setAddress1(rebateMaster.getAddress1());
                rsMasterAttached.setAddress2(rebateMaster.getAddress2());
                rsMasterAttached.setCity(rebateMaster.getCity());
                rsMasterAttached.setState(rebateMaster.getState());
                rsMasterAttached.setZipCode(rebateMaster.getZipCode());
                rsMasterAttached.setCreatedBy(1);
                rsMasterAttached.setCreatedDate(new Date());
                rsMasterAttached.setModifiedBy(1);
                rsMasterAttached.setModifiedDate(new Date());
                rsMasterAttached.setFormulaMethodId(rebateMaster.getFormulaMethodId());
                rsMasterAttached.setRecordLockStatus(false);
                rsMasterAttached.setInboundStatus("A");
                rsMasterAttached.setSource("BPI");
                RsContract rsContract = RsContractLocalServiceUtil.addRsContract(rsMasterAttached);
                dashboardResultsTable.getContainerProperty(itemIds[i], SAVED_SYSTEM_ID).setValue(String.valueOf(rsContract.getRsContractSid()));
                SaveRS(String.valueOf(rsContract.getRsContractSid()), rebateMaster.getRsModelSid());
                
            }
            
        }
        
    }
    
    public void SaveCFP(String cfpId, Integer CFPmodelid) {
        CopyContractLogic.SaveCFP(cfpId, CFPmodelid);
        
    }
    
    public void SaveIFP(String ifpId, Integer IFPmodelid) {
        CopyContractLogic.SaveIFP(ifpId, IFPmodelid);
        
    }
    
    public void SavePS(String psid, Integer IFPmodelid) {
        CopyContractLogic.SavePS(psid, IFPmodelid);
        
    }
    
    public void SaveRS(String rsid, Integer RSmodalid) {
        CopyContractLogic.SaveRS(rsid, RSmodalid);
        
    }
    
    private void configureResultsSearchTable() {
        componentResultsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentResultsSearchTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentResultsSearchTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentResultsSearchTable.setPageLength(NumericConstants.FIVE);
        componentResultsSearchTable.setSelectable(true);
        componentResultsSearchTable.setMultiSelect(false);
        componentResultsSearchTable.addStyleName("filtertable");
        componentResultsSearchTable.addStyleName("table-header-normal");
        componentResultsSearchTable.setImmediate(true);
        componentReseultsTableLogic.setContainerDataSource(componentResultsContainer);
        componentReseultsTableLogic.sinkItemPerPageWithPageLength(false);
        componentDetailsSelectedItemLayout.addComponent(componentResultsSearchTable);
        componentDetailsSelectedItemLayout.addComponent(componentReseultsTableLogic.createControls());
        componentResultsSearchTable.setVisibleColumns(Constants.getInstance().copycontractCfpResultsColumns);
        componentResultsSearchTable.setColumnHeaders(Constants.getInstance().copycontractCfpResultsHeaders);
    }
    
    private void configureDetailsSearchTable() {
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentDetailsTable.setHeight("85%");
        componentDetailsTable.setPageLength(NumericConstants.FIVE);
        componentDetailsTable.addStyleName("filtertable");
        componentDetailsTable.addStyleName("table-header-normal");
        componentDetailsTable.setImmediate(true);
        componentDetailsSelectedLayout.addComponent(componentDetailsTable);
        componentDetailsSelectedLayout.addComponent(componentDetailsTableLogic.createControls());
        componentDetailsTableLogic.setContainerDataSource(componentDetailsContainer);
        componentDetailsTableLogic.sinkItemPerPageWithPageLength(false);
        componentDetailsTable.setVisibleColumns(UiUtils.getInstance().newCompanyDetailsColumns);
        componentDetailsTable.setColumnHeaders(UiUtils.getInstance().newCompanyDetailsHeaders);
        
    }
    
    private void configureLevelSearchTable() {
        levelDetailsResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        levelDetailsResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        levelDetailsResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        levelDetailsResultsTable.setPageLength(NumericConstants.FIVE);
        componentLevelTableLogic.setContainerDataSource(contractInfoContainer);
        componentLevelTableLogic.sinkItemPerPageWithPageLength(false);
        levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", Constants.COMPANY_START_DATE, Constants.COMPANY_END_DATE);
        levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYSTATUS, "Company Start Date", "Company End Date");
        levelDetailsResultsTable.setColumnAlignment(Constants.COMPANY_START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.COMPANY_END_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsLayout.addComponent(levelDetailsResultsTable);
        levelDetailsResultsLayout.addComponent(componentLevelTableLogic.createControls());

    }
    /**
     * Configure security
     *
     */
    private void configureSecurityPermissions() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Contract Management", "Copy Contract", "Existing Tab Screen");
            BtnsearchEC.setVisible(CommonLogic.isButtonVisibleAccess("BtnsearchEC", functionHM));
            resultpop.setVisible(CommonLogic.isButtonVisibleAccess("resultpop", functionHM));
            levelRemoveBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelRemoveBtn", functionHM));
            levelpop.setVisible(CommonLogic.isButtonVisibleAccess("levelpop", functionHM));
            addToTree.setVisible(CommonLogic.isButtonVisibleAccess("addToTree", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
