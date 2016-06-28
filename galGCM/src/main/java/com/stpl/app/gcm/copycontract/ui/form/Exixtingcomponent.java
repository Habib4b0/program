/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import static com.stpl.app.gcm.common.QueryUtils.CHAR_ASTERISK;
import static com.stpl.app.gcm.common.QueryUtils.CHAR_PERCENT;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.logic.CFPSearchCriteria;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingComponentDetailsTableLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingComponentResultsTableLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ExistingLevelDataTableLogic;
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
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class Exixtingcomponent extends CustomComponent {

    public static final Logger LOGGER = Logger.getLogger(Exixtingcomponent.class);
    private final Tree tree = new Tree();
    @UiField("componentDetailsSelectedLayout")
    public VerticalLayout componentDetailsSelectedLayout;
    @UiField("componentDetailsSelectedItemLayout")
    public VerticalLayout componentDetailsSelectedItemLayout;
    @UiField("levelDetailsResultsLayout")
    public VerticalLayout levelDetailsResultsLayout;
    public TreeTable dashboardResultsTable;
    @UiField("ComponenttypeEC")
    public ComboBox ComponenttypeNC;
    @UiField("SearchfieldEC")
    public ComboBox SearchfieldEC;
    @UiField("SearchfieldECDDlb")
    public ComboBox SearchfieldECDDlb;
    @UiField("SearchvaluedEC")
    public TextField SearchvaluedEC;
    @UiField("componentInformationGrid")
    public GridLayout componentInformationGrid;
    private BeanItemContainer copycontractResultsContainerCFP;
    private BeanItemContainer componentDetailsContainer = new BeanItemContainer(ExistingComponentDTO.class);
    TextField CFPname = new TextField();
    @UiField("contractDashBoardLayout")
    public VerticalLayout contractDashBoardLayout;
    TextField CFPno = new TextField();
    TextField CFPid = new TextField();
    TextField CFPstatus = new TextField();
    TextField sdate = new TextField();
    TextField edate = new TextField();
    TextField CFPtype = new TextField();
    TextField CFPcategory = new TextField();
    TextField CFPtradeclass = new TextField();
    TextField CFPdesignation = new TextField();
    @UiField("componentDetails")
    public Panel componentDetails;
    @UiField("componentDetailsSelectedItemPanel")
    public Panel componentDetailsSelectedItemPanel;
    @UiField("contractDashBoardResults")
    public Panel contractDashBoardResults;
    @UiField("contractComponentDetails")
    public Panel contractComponentDetails;
    @UiField("BtnsearchEC")
    public Button BtnsearchEC;
    @UiField("resultpop")
    public Button resultpop;
    @UiField("levelRemoveBtn")
    public Button levelRemoveBtn;
    @UiField("levelpop")
    public Button levelpop;
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
    @UiField("addToTree")
    public Button addToTree;
    private Object tableBeanId;
    private static final BeanItem<?> NULL_OBJECT = null;
    LazyBeanItemContainer<CFPCompanyDTO> resultsLazyContainer;
    LazyBeanItemContainer<CFPCompanyDTO> resultsLazyContainer1;
    LazyBeanItemContainer<IFPItemDTO> resultsLazyContainer2;
    LazyBeanItemContainer<IFPItemDTO> resultsLazyContainer3;
    LazyBeanItemContainer<PSIFPDTO> resultsLazyContainer4;
    LazyBeanItemContainer<RsIfpDto> resultsLazyContainer5;
    LazyBeanItemContainer<PSIFPDTO> resultsLazyContainer6;
    LazyBeanItemContainer<RsIfpDto> resultsLazyContainer7;
    CFPSearchCriteria CFPSearchCriteria = new CFPSearchCriteria();
    private final BeanItemContainer<CopyComponentDTO> contractInfoContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    ExistingComponentDTO selectedItemDto;

    CopyContractLogic CopyContractLogic = new CopyContractLogic();
    CustomTreeContainer<CopyComponentDTO> dashBoardContainer ;
    SessionDTO session;
    DateFormat format = new SimpleDateFormat("MM/dd/yyy");
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd ");
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();
    private BeanItemContainer componentResultsContainer = new BeanItemContainer(ExistingComponentDTO.class);
    ExistingComponentResultsTableLogic componentReseultsTableLogic = new ExistingComponentResultsTableLogic();
    public ExtPagedTable componentResultsSearchTable = new ExtPagedTable(componentReseultsTableLogic);
    ExistingComponentDetailsTableLogic componentDetailsTableLogic = new ExistingComponentDetailsTableLogic();
    public ExtPagedTable componentDetailsTable = new ExtPagedTable(componentDetailsTableLogic);
    ExistingLevelDataTableLogic componentLevelTableLogic = new ExistingLevelDataTableLogic();
    public ExtPagedTable levelDetailsResultsTable = new ExtPagedTable(componentLevelTableLogic);

    public Exixtingcomponent(TreeTable contractDashBoardTable,CustomTreeContainer<CopyComponentDTO> dashBoardContainer) {
        this.dashboardResultsTable = contractDashBoardTable;
        this.dashBoardContainer=dashBoardContainer;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/Existing Component.xml"), this));
        contractDashBoardLayout.addComponent(contractDashBoardTable);
        configureFields();
    }

    protected void configureFields() {
        try {

            configureDetailsSearchTable();
            configureResultsSearchTable();
            componentDetailsSelectedItemPanel.setWidth("100%");
            componentDetailsSelectedItemPanel.setHeight("100%");
            contractDashBoardResults.setWidth("100%");
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
            dashboardResultsTable.setWidth("100%");
            dashboardResultsTable.setHeight("100%");

            dashboardResultsTable.setPageLength(10);
            dashboardResultsTable.setContainerDataSource(dashBoardContainer);
            dashboardResultsTable.setVisibleColumns(Constants.COPYCONTRACT_DASHBOARD_RESULTS_COLUMNS);
            dashboardResultsTable.setColumnHeaders(Constants.COPYCONTRACT_DASHBOARD_RESULTS_HEADERS);

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
                public void itemClick(ItemClickEvent event) {
                    tableBeanId = event.getItemId();

                    if (resultsLazyContainer1 != null) {
                        resultsLazyContainer1.removeAllItems();
                    }
                    if (resultsLazyContainer3 != null) {
                        resultsLazyContainer3.removeAllItems();
                    }
                    if (resultsLazyContainer6 != null) {
                        resultsLazyContainer6.removeAllItems();
                    }
                    if ("Company Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                        selectedItemDto = (ExistingComponentDTO) event.getItemId();
                    }
                    if ("Item Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
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
            dashboardResultsTable.setWidth("100%");
            dashboardResultsTable.setHeight("100%");
            dashboardResultsTable.setPageLength(5);
            dashboardResultsTable.addListener(new ItemClickEvent.ItemClickListener() {
                public void itemClick(ItemClickEvent event) {
                }
            });
            configureLevelSearchTable();
            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }

    @UiHandler("ComponenttypeEC")
    public void ComponenttypeChange(Property.ValueChangeEvent event) throws Exception {

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
    public void SearchfieldECChange(Property.ValueChangeEvent event) throws Exception {
        SearchfieldECDDlb.removeAllItems();
        if (SearchfieldEC.getValue() != null && SearchfieldEC.getValue().toString().contains("Status")) {
            SearchfieldECDDlb.setVisible(true);
            commonUtil.loadComboBox(SearchfieldECDDlb, UIUtils.STATUS, false);
            SearchfieldECDDlb.setValidationVisible(true);
            SearchvaluedEC.setVisible(false);
        } else if (SearchfieldEC.getValue() != null && SearchfieldEC.getValue().toString().contains("Type")) {

            SearchfieldECDDlb.setVisible(true);
            SearchfieldECDDlb.setNullSelectionItemId(Constants.ZEROSTRING);
            if (SearchfieldEC.getValue().toString().contains(Constants.CFP)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UIUtils.CFP_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.IFP)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UIUtils.IFP_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.PS)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UIUtils.PS_TYPE, false);
            }
            if (SearchfieldEC.getValue().toString().contains(Constants.RS)) {
                commonUtil.loadComboBox(SearchfieldECDDlb, UIUtils.RS_TYPE, false);
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
        if ("Company Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            SearchfieldEC.addItem("CFP ID");
            SearchfieldEC.addItem("CFP No");
            SearchfieldEC.addItem("CFP Name");
            SearchfieldEC.addItem("CFP Status");
            SearchfieldEC.addItem("CFP Type");
            componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_CFP_RESULTS_COLUMNS);
            componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_CFP_RESULTS_HEADERS);
            componentDetailsTable.setVisibleColumns(UiUtils.NEW_COMPANY_DETAILS_COLUMNS);
            componentDetailsTable.setColumnHeaders(UiUtils.NEW_COMPANY_DETAILS_HEADERS);
        }

        if ("Item Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            SearchfieldEC.addItem(Constants.IFP_ID);
            SearchfieldEC.addItem(Constants.IFP_NO);
            SearchfieldEC.addItem(Constants.IfpNAME);
            SearchfieldEC.addItem(Constants.IFP_STATUS);
            SearchfieldEC.addItem(Constants.IFPTYPE);
            componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_IFP_RESULTS_COLUMNS);
            componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_IFP_RESULTS_HEADERS);
            componentDetailsTable.setVisibleColumns(UiUtils.NEW_IFP_DETAILS_COLUMNS);
            componentDetailsTable.setColumnHeaders(UiUtils.NEW_IFP_DETAILS_HEADERS);
        }

        if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_PS_RESULTS_COLUMNS);
            componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_PS_RESULTS_HEADERS);
            SearchfieldEC.addItem("PS ID");
            SearchfieldEC.addItem("PS No");
            SearchfieldEC.addItem("PS Name");
            SearchfieldEC.addItem("PS Status");
            SearchfieldEC.addItem("PS Type");
            componentDetailsTable.setVisibleColumns(UiUtils.NEW_PS_DETAILS_COLUMNS);
            componentDetailsTable.setColumnHeaders(UiUtils.NEW_PS_DETAILS_HEADERS);

        }

        if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
            componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_RS_RESULTS_COLUMNS);
            componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_RS_RESULTS_HEADERS);
            SearchfieldEC.addItem("RS ID");
            SearchfieldEC.addItem("RS No");
            SearchfieldEC.addItem("RS Name");
            SearchfieldEC.addItem("RS Status");
            SearchfieldEC.addItem("RS Type");
            componentDetailsTable.setVisibleColumns(UiUtils.NEW_RS_DETAILS_COLUMNS);
            componentDetailsTable.setColumnHeaders(UiUtils.NEW_RS_DETAILS_HEADERS);
        }
    }

    private void loadComponentInformationSection() {
        if ("Company Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
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
        } else if ("Item Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
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
        LOGGER.info("Entered search method");

        if (ComponenttypeNC.getValue() != null && SearchfieldEC.getValue() != null && (!SearchvaluedEC.getValue().toString().equalsIgnoreCase(StringUtils.EMPTY) || SearchfieldECDDlb.getValue() != null)) {
            List input = new ArrayList();
            if (SearchfieldEC.getValue().toString().contains("ID")) {
                input.add(SearchvaluedEC.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("No")) {
                input.add(SearchvaluedEC.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("Name")) {
                input.add(SearchvaluedEC.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("Type")) {
                input.add(SearchfieldECDDlb.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if (SearchfieldEC.getValue().toString().contains("Status")) {
                input.add(SearchfieldECDDlb.getValue().toString().replace(CHAR_ASTERISK, CHAR_PERCENT));
            } else {
                input.add(Constants.PERCENT);
            }
            if ("Company Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_CFP_RESULTS_COLUMNS);
                componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_CFP_RESULTS_HEADERS);
                componentResultsSearchTable.setColumnAlignment("companyFamilyPlanStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("companyFamilyPlanEndDate", ExtCustomTable.Align.CENTER);
            }
            if ("Item Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_IFP_RESULTS_COLUMNS);
                componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_IFP_RESULTS_HEADERS);
                componentResultsSearchTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setRefresh(true);
            }

            if (Constants.PRICE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_PS_RESULTS_COLUMNS);
                componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_PS_RESULTS_HEADERS);
                componentResultsSearchTable.setColumnAlignment("priceScheduleStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("priceScheduleEndDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setRefresh(true);
            }
            if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                componentResultsSearchTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_RS_RESULTS_COLUMNS);
                componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_RS_RESULTS_HEADERS);
                componentResultsSearchTable.setRefresh(true);
                componentResultsSearchTable.setColumnAlignment("itemRebateStartDate", ExtCustomTable.Align.CENTER);
                componentResultsSearchTable.setColumnAlignment("itemRebateEndDate", ExtCustomTable.Align.CENTER);
            }
            if (componentReseultsTableLogic.loadSetData(String.valueOf(ComponenttypeNC.getValue()), input, true)) {
                AbstractNotificationUtils.getErrorNotification("No Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
            LOGGER.info(
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
            if ("Company Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getCompanyFamilyPlanId());
                CFPname.setValue(selectedItemDto.getCompanyFamilyPlanName());
                CFPno.setValue(selectedItemDto.getCompanyFamilyPlanNo());
                CFPstatus.setValue(selectedItemDto.getCompanyFamilyPlanStatusValue());
                CFPtype.setValue(selectedItemDto.getCompanyFamilyPlanTypeValue());
                CFPcategory.setValue(selectedItemDto.getCompanyFamilyPlanCategoryValue());
                CFPdesignation.setValue(selectedItemDto.getCompanyFamilyPlanDesignation());
                sdate.setValue(selectedItemDto.getCompanyFamilyPlanStartDate());
                edate.setValue(selectedItemDto.getCompanyFamilyPlanEndDate());
                componentDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
               componentDetailsTable.setVisibleColumns(UiUtils.NEW_COMPANY_DETAILS_COLUMNS);
              componentDetailsTable.setColumnHeaders(UiUtils.NEW_COMPANY_DETAILS_HEADERS);
                componentDetailsTable.setColumnAlignment("tradeClassStartDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment("tradeClassEndDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setRefresh(true);
                if (componentDetailsTableLogic.loadSetData("Company Family Plan", selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification("No Records",
                            "There were no records matching the search criteria.  Please try again.");
                }

            }
            if ("Item Family Plan".equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getItemFamilyplanId());
                CFPname.setValue(selectedItemDto.getItemFamilyplanName());
                CFPno.setValue(selectedItemDto.getItemFamilyplanNo());
                CFPstatus.setValue(selectedItemDto.getDisplayIFPStatus());
                CFPtype.setValue(selectedItemDto.getItemFamilyplanType());
                sdate.setValue(selectedItemDto.getIfpStartDate());
                edate.setValue(selectedItemDto.getIfpEndDate());
                CFPdesignation.setValue(selectedItemDto.getItemFamilyplanDesignation());
                componentDetailsTable.setVisibleColumns(UiUtils.NEW_IFP_DETAILS_COLUMNS);
                componentDetailsTable.setColumnHeaders(UiUtils.NEW_IFP_DETAILS_HEADERS);
                componentDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment("itemStartDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment("itemEndDate", ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData("Item Family Plan", selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification("No Records",
                            "There were no records matching the search criteria.  Please try again.");
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
                componentDetailsTable.setVisibleColumns(UiUtils.NEW_PS_DETAILS_COLUMNS);
                 componentDetailsTable.setColumnHeaders(UiUtils.NEW_PS_DETAILS_HEADERS);
                componentDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment("itemStartDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment("itemEndDate", ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData(Constants.PRICE_SCHEDULE, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification("No Records",
                            "There were no records matching the search criteria.  Please try again.");
                }

            }

            if (Constants.REBATE_SCHEDULE.equalsIgnoreCase(String.valueOf(ComponenttypeNC.getValue()))) {
                CFPid.setValue(selectedItemDto.getRebateScheduleId());
                CFPname.setValue(selectedItemDto.getRebateScheduleName());
                CFPno.setValue(selectedItemDto.getRebateScheduleNo());
                CFPstatus.setValue(selectedItemDto.getStatusRebate());
                sdate.setValue(selectedItemDto.getItemRebateStartDate());
                edate.setValue(selectedItemDto.getItemRebateEndDate());
                componentDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
                componentDetailsTable.setVisibleColumns(UiUtils.NEW_RS_DETAILS_COLUMNS);
                 componentDetailsTable.setColumnHeaders(UiUtils.NEW_RS_DETAILS_HEADERS);
                componentDetailsTable.setRefresh(true);
                componentDetailsTable.setColumnAlignment("itemStartDate", ExtCustomTable.Align.CENTER);
                componentDetailsTable.setColumnAlignment("itemEndDate", ExtCustomTable.Align.CENTER);
                if (componentDetailsTableLogic.loadSetData(Constants.REBATE_SCHEDULE, selectedItemDto, true)) {
                    AbstractNotificationUtils.getErrorNotification("No Records",
                            "There were no records matching the search criteria.  Please try again.");
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

            AbstractNotificationUtils.getErrorNotification("Error", "Please Add Contract Header Node");

        } else if (componentResultsSearchTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Error", "Please Select a record to Add.");
        } else {
            Object root = dashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, "levelNo").getValue());
                int levelNumber = Integer.valueOf(levelNo);
                String level = String.valueOf(ComponenttypeNC.getValue());
                if (level.equals("Company Family Plan")) {
                    if (1 - levelNumber == 1) {

                        Integer cfpId = selectedItemDto.getCompanyFamilyPlanSystemId();
                        String query = "select CFP_ID,CFP_NO,CFP_NAME from dbo.CFP_MODEL where CFP_MODEL_SID=" + cfpId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, "category").setValue(Constants.CFP);
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardId").setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardNumber").setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardName").setValue(String.valueOf(obj[2]));
                            dashboardResultsTable.getContainerProperty(rootId, "levelNo").setValue("1");
                            dashboardResultsTable.getContainerProperty(rootId, "hiddenId").setValue(String.valueOf(cfpId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, true);
                            dashboardResultsTable.setCollapsed(root, false);
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please Select Correct Node");
                    }
                } else if (level.equals("Item Family Plan")) {
                    if (2 - levelNumber == 1) {

                        Integer ifpId = selectedItemDto.getIfpDetailsSystemId();
                        String query = "select IFP_ID,IFP_NO,IFP_NAME from dbo.IFP_MODEL where IFP_MODEL_SID=" + ifpId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, "category").setValue(Constants.IFP);
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardId").setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardNumber").setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardName").setValue(String.valueOf(obj[2]));
                            dashboardResultsTable.getContainerProperty(rootId, "levelNo").setValue("2");
                            dashboardResultsTable.getContainerProperty(rootId, "hiddenId").setValue(String.valueOf(ifpId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, true);
                            dashboardResultsTable.setCollapsed(root, false);
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (3 - levelNumber == 1) {

                        String psId = selectedItemDto.getPriceScheduleSystemId();
                        String Parent = String.valueOf(dashboardResultsTable.getContainerProperty(root, "hiddenId").getValue());
                        String conditionQuery = "select * from PS_DETAILS where PS_MODEL_SID=" + psId + " and IFP_MODEL_SID=" + Parent;
                        List conditionList = CompanyMasterLocalServiceUtil.executeQuery(conditionQuery);
                        if (conditionList != null && conditionList.size() > 0) {
                            String query = "select PS_ID,PS_NO,PS_NAME from dbo.PS_MODEL where PS_MODEL_SID=" + psId;
                            List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                            if (cfpList != null && cfpList.size() > 0) {
                                Object[] obj = (Object[]) cfpList.get(0);
                                final Object rootId = dashboardResultsTable.addItem();
                                dashboardResultsTable.getContainerProperty(rootId, "category").setValue(Constants.PS);
                                dashboardResultsTable.getContainerProperty(rootId, "dashboardId").setValue(String.valueOf(obj[0]));
                                dashboardResultsTable.getContainerProperty(rootId, "dashboardNumber").setValue(String.valueOf(obj[1]));
                                dashboardResultsTable.getContainerProperty(rootId, "dashboardName").setValue(String.valueOf(obj[2]));
                                dashboardResultsTable.getContainerProperty(rootId, "levelNo").setValue("3");
                                dashboardResultsTable.getContainerProperty(rootId, "hiddenId").setValue(psId);
                                dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("2");
                                dashboardResultsTable.addItem(rootId);
                                dashboardResultsTable.setParent(rootId, root);
                                dashboardResultsTable.setChildrenAllowed(rootId, true);
                                dashboardResultsTable.setCollapsed(root, false);
                            }

                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "PS does not associate with  IFP");
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (4 - levelNumber == 1) {

                        Integer rsId = selectedItemDto.getRebateScheduleSystemId();
                        String query = "select RS_ID,RS_NO,RS_NAME from dbo.RS_MODEL where RS_MODEL_SID=" + rsId;
                        List cfpList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                        if (cfpList != null && cfpList.size() > 0) {
                            Object[] obj = (Object[]) cfpList.get(0);
                            final Object rootId = dashboardResultsTable.addItem();
                            dashboardResultsTable.getContainerProperty(rootId, "category").setValue(Constants.RS);
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardId").setValue(String.valueOf(obj[0]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardNumber").setValue(String.valueOf(obj[1]));
                            dashboardResultsTable.getContainerProperty(rootId, "dashboardName").setValue(String.valueOf(obj[2]));
                            dashboardResultsTable.getContainerProperty(rootId, "levelNo").setValue("4");
                            dashboardResultsTable.getContainerProperty(rootId, "hiddenId").setValue(String.valueOf(rsId));
                            dashboardResultsTable.getContainerProperty(rootId, Constants.ADDBY).setValue("2");
                            dashboardResultsTable.addItem(rootId);
                            dashboardResultsTable.setParent(rootId, root);
                            dashboardResultsTable.setChildrenAllowed(rootId, false);
                            dashboardResultsTable.setCollapsed(root, false);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please Select Correct Node");
                    }
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("Error", "Please Select Node at Dashboard");
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
    public void levelpopBtnClick(Button.ClickEvent event) throws ParseException {
        Object root = dashboardResultsTable.getValue();
        if (root != null) {
            Integer level = Integer.valueOf(String.valueOf(dashboardResultsTable.getContainerProperty(root, "levelNo").getValue()));
            Integer cfpId = Integer.valueOf(String.valueOf(dashboardResultsTable.getContainerProperty(root, "hiddenId").getValue()));
            if (level.equals(1)) {
                levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", "companyStartDate", "companyEndDate");
                levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYSTATUS, "Start Date", "Company End Date");
                levelDetailsResultsTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
                levelDetailsResultsTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
                cfpDetailsGrid.setVisible(true);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardId").getValue());
                cfpDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardName").getValue());
                cfpDetailsName.setValue(detailsName);

            } else if (level.equals(2) || level.equals(3) || level.equals(4)) {
                levelDetailsResultsTable.setVisibleColumns("itemNo", "itemName", "therapyClass", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE);
                levelDetailsResultsTable.setColumnHeaders(Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date");
                levelDetailsResultsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                levelDetailsResultsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                if (level.equals(2)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(true);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardId").getValue());
                    ifpDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardName").getValue());
                    ifpDetailsName.setValue(detailsName);
                } else if (level.equals(3)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(true);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardId").getValue());
                    psDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardName").getValue());
                    psDetailsName.setValue(detailsName);
                } else if (level.equals(4)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(true);
                    String detailsNo = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardId").getValue());
                    rsDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(dashboardResultsTable.getContainerProperty(root, "dashboardName").getValue());
                    rsDetailsName.setValue(detailsName);
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

    public void savecontract(Object item) throws SystemException, PortalException {

        String contractsidvalue = StringUtils.EMPTY;
        Object[] itemIds = {item};
        for (int i = 0; i < itemIds.length; i++) {
            String level = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "levelNo").getValue());
            if (level.equalsIgnoreCase(Constants.ZEROSTRING)) {
                String contractId = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "dashboardId").getValue());
                String contractNo = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "dashboardNumber").getValue());
                String contractName = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "dashboardName").getValue());
                int contractType = ((HelperDTO)dashboardResultsTable.getContainerProperty(itemIds[i], "marketType").getValue()).getId();
                Date startDate = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], Constants.START_DATE).getValue();
                Date endDate = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], Constants.END_DATE).getValue();
                String contractHolder = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "contractHolder").getValue());
                int status1 = ((HelperDTO)dashboardResultsTable.getContainerProperty(itemIds[i], "status").getValue()).getId();
                int  AliasType = ((HelperDTO)dashboardResultsTable.getContainerProperty(itemIds[i], "aliasType").getValue()).getId();
                Date AliasSDATE = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], "aliasstartdate").getValue();
                String AliasNumber = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "aliasNumber").getValue());
                Date AliasEDATE = (Date) dashboardResultsTable.getContainerProperty(itemIds[i], "aliasenddate").getValue();
                ContractMaster cm;
                cm=ContractMasterLocalServiceUtil.createContractMaster(0);
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
                dashboardResultsTable.getContainerProperty(itemIds[i], "savedSystemId").setValue(contractsidvalue);
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
                ContractAliasMaster CAM1 = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);
            }
            if (level.equalsIgnoreCase("1")) {
                String category = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "category").getValue());
                if (category.equalsIgnoreCase(Constants.CFP)) {

                    Object contractParent = dashboardResultsTable.getParent(itemIds[i]);
                    String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "hiddenId").getValue());
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractParent, "savedSystemId").getValue());

                    final CfpModel companyFamily = CfpModelLocalServiceUtil.getCfpModel(Integer.valueOf(idvalue));
                    final CfpContract cfpMasterAttached =CfpContractLocalServiceUtil.createCfpContract(0);
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
                    SaveCFP(String.valueOf(cm1.getCfpContractSid()), companyFamily.getCfpModelSid());
                    dashboardResultsTable.getContainerProperty(itemIds[i], "savedSystemId").setValue(String.valueOf(cm1.getCfpContractSid()));
                }

            }
            if (level.equalsIgnoreCase("2")) {
                String category = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "category").getValue());
                if (category.equalsIgnoreCase(Constants.IFP)) {
                    String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "hiddenId").getValue());
                    final IfpModel itemFamily = IfpModelLocalServiceUtil.getIfpModel(Integer.valueOf(idvalue));
                    final IfpContract ifpMasterAttached =IfpContractLocalServiceUtil.createIfpContract(0);

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

                    Object parentItem = dashboardResultsTable.getParent(itemIds[i]);
                    String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, "savedSystemId").getValue());
                    ifpMasterAttached.setCfpContractSid(parentCFPId);
                    Object contractItem = dashboardResultsTable.getParent(parentItem);
                    String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, "savedSystemId").getValue());
                    ifpMasterAttached.setContractMasterSid(Integer.valueOf(contractSId));
                    IfpContract im1 = IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
                    SaveIFP(String.valueOf(im1.getIfpContractSid()), itemFamily.getIfpModelSid());
                    dashboardResultsTable.getContainerProperty(itemIds[i], "savedSystemId").setValue(String.valueOf(im1.getIfpContractSid()));
                }
            }
            if (level.equalsIgnoreCase("3")) {
                String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "hiddenId").getValue());
                final PsModel priceSchedule = PsModelLocalServiceUtil.getPsModel(Integer.valueOf(idvalue));
                final PsContract psMasterAttached = PsContractLocalServiceUtil.createPsContract(0);

                psMasterAttached.setPsName(priceSchedule.getPsName());
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
                String parentIFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentItem, "savedSystemId").getValue());
                Object parentCFPItem = dashboardResultsTable.getParent(parentItem);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, "savedSystemId").getValue());
                Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, "savedSystemId").getValue());

                psMasterAttached.setContractMasterSid(Integer.valueOf(contractSId));
                psMasterAttached.setCfpContractSid(parentCFPId);
                psMasterAttached.setIfpContractSid(parentIFPId);
                PsContract im1 = PsContractLocalServiceUtil.addPsContract(psMasterAttached);
                SavePS(String.valueOf(im1.getPsContractSid()), priceSchedule.getPsModelSid());
                dashboardResultsTable.getContainerProperty(itemIds[i], "savedSystemId").setValue(String.valueOf(im1.getPsContractSid()));
            } else if (level.equals("4")) {
                String rsContractSId = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "hiddenId").getValue());
                Object psParentItem = dashboardResultsTable.getParent(itemIds[i]);
                String parentPSId = String.valueOf(dashboardResultsTable.getContainerProperty(psParentItem, "savedSystemId").getValue());
                Object parentIFPItem = dashboardResultsTable.getParent(psParentItem);
                String parentIFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentIFPItem, "savedSystemId").getValue());
                Object parentCFPItem = dashboardResultsTable.getParent(parentIFPItem);
                String parentCFPId = String.valueOf(dashboardResultsTable.getContainerProperty(parentCFPItem, "savedSystemId").getValue());
                Object contractItem = dashboardResultsTable.getParent(parentCFPItem);
                String contractSId = String.valueOf(dashboardResultsTable.getContainerProperty(contractItem, "savedSystemId").getValue());

                String idvalue = String.valueOf(dashboardResultsTable.getContainerProperty(itemIds[i], "hiddenId").getValue());
                final RsModel rebateMaster = RsModelLocalServiceUtil.getRsModel(Integer.parseInt(idvalue));
                final RsContract rsMasterAttached = RsContractLocalServiceUtil.createRsContract(0);
                rsMasterAttached.setContractMasterSid(Integer.valueOf(contractSId));
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
                dashboardResultsTable.getContainerProperty(itemIds[i], "savedSystemId").setValue(String.valueOf(rsContract.getRsContractSid()));
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
        componentResultsSearchTable.setWidth("100%");
        componentResultsSearchTable.setHeight("100%");
        componentResultsSearchTable.setPageLength(5);
        componentResultsSearchTable.setSelectable(true);
        componentResultsSearchTable.setMultiSelect(false);
        componentResultsSearchTable.addStyleName("filtertable");
        componentResultsSearchTable.addStyleName("table-header-normal");
        componentResultsSearchTable.setImmediate(true);
        componentReseultsTableLogic.setContainerDataSource(componentResultsContainer);
        componentDetailsSelectedItemLayout.addComponent(componentResultsSearchTable);
        componentDetailsSelectedItemLayout.addComponent(componentReseultsTableLogic.createControls());
        componentResultsSearchTable.setVisibleColumns(Constants.COPYCONTRACT_CFP_RESULTS_COLUMNS);
        componentResultsSearchTable.setColumnHeaders(Constants.COPYCONTRACT_CFP_RESULTS_HEADERS);
    }

    private void configureDetailsSearchTable() {
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsTable.setWidth("100%");
        componentDetailsTable.setHeight("85%");
        componentDetailsTable.setPageLength(5);
        componentDetailsTable.addStyleName("filtertable");
        componentDetailsTable.addStyleName("table-header-normal");
        componentDetailsTable.setImmediate(true);
        componentDetailsSelectedLayout.addComponent(componentDetailsTable);
        componentDetailsSelectedLayout.addComponent(componentDetailsTableLogic.createControls());
        componentDetailsTableLogic.setContainerDataSource(componentDetailsContainer);
        componentDetailsTable.setVisibleColumns(UiUtils.NEW_COMPANY_DETAILS_COLUMNS);
        componentDetailsTable.setColumnHeaders(UiUtils.NEW_COMPANY_DETAILS_HEADERS);

    }

    private void configureLevelSearchTable() {
        levelDetailsResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        levelDetailsResultsTable.setWidth("100%");
        levelDetailsResultsTable.setHeight("100%");
        levelDetailsResultsTable.setPageLength(5);
        componentLevelTableLogic.setContainerDataSource(contractInfoContainer);
        levelDetailsResultsTable.setVisibleColumns("companyNo", "companyName", "companyStatus", "companyStartDate", "companyEndDate");
        levelDetailsResultsTable.setColumnHeaders(Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYSTATUS, "Company Start Date", "Company End Date");
        levelDetailsResultsTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
        levelDetailsResultsLayout.addComponent(levelDetailsResultsTable);
        levelDetailsResultsLayout.addComponent(componentLevelTableLogic.createControls());

    }
}
