/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.copycontract.logic.tablelogic.CopyComponentsResultLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.CopyComponentsSearchLogic;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ComponentInfoLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
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
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class Copycomponents extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(Copycomponents.class);
    @UiField("componentitems")
    public OptionGroup componentitems;
    @UiField("contractDashBoardLayout")
    public VerticalLayout contractDashBoardLayout;
    @UiField("ComponenttypeNC")
    public ComboBox ComponenttypeNC;
    TreeTable contractDashBoardTable;
    @UiField("TblLayout")
    public VerticalLayout TblLayout;
    @UiField("infolayout")
    public VerticalLayout infolayout;
    CopyComponentsResultLogic CopyComponentsResultLogic = new CopyComponentsResultLogic();
    CopyComponentsSearchLogic CopyComponentsSearchLogic = new CopyComponentsSearchLogic();
    ComponentInfoLogic ComponentInfoLogic = new ComponentInfoLogic();
    public ExtPagedTable contractComponent = new ExtPagedTable(CopyComponentsResultLogic);
    public ExtPagedTable componentDetailsTable = new ExtPagedTable(CopyComponentsSearchLogic);
    public ExtPagedTable contractInformationTable = new ExtPagedTable(ComponentInfoLogic);
    private final BeanItemContainer<CopyComponentDTO> copycontractResultsContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    private final BeanItemContainer<CopyComponentDTO> contractComponentContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    private final BeanItemContainer<CopyComponentDTO> contractInfoContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    private final BeanItemContainer<CopyComponentDTO> levelDetailsContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    CustomTreeContainer<CopyComponentDTO> dashBoardContainer ;
    public static final SimpleDateFormat dbDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    ItemQueries Queries = new ItemQueries();
    CopyContractLogic copyContractLogic = new CopyContractLogic();
    QueryUtils queryUtils = new QueryUtils();
    @UiField("SearchfieldNC")
    public ComboBox SearchfieldNC;
    @UiField("BtnsearchNC")
    public Button BtnsearchNC;
    @UiField("SearchvaluedNC")
    public TextField SearchvaluedNC;

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
    public TextField cfpStatus;
    @UiField("cfpStartDate")
    public TextField cfpStartDate;
    @UiField("cfpfileName")
    public TextField cfpfileName;
    /*ifp Component */
    @UiField("ifpId")
    public TextField ifpId;
    @UiField("ifpNo")
    public TextField ifpNo;
    @UiField("ifpName")
    public TextField ifpName;
    @UiField("ifpStatus")
    public TextField ifpStatus;
    @UiField("ifpStartDate")
    public TextField ifpStartDate;
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
    public TextField psStatus;
    @UiField("psStartDate")
    public TextField psStartDate;
    @UiField("ifpfileName")
    public TextField psfileName;
    /*rs Component */
    @UiField("rsId")
    public TextField rsId;
    @UiField("rsStatus")
    public TextField rsStatus;
    @UiField("rsFrequency")
    public TextField rsFrequency;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("rsStartDate")
    public TextField rsStartDate;
    @UiField("rsRarType")
    public TextField rsRarType;
    @UiField("rsName")
    public TextField rsName;
    @UiField("rsEndDate")
    public TextField rsEndDate;
    @UiField("rsBasic")
    public TextField rsBasic;
    @UiField("ComponentDetailsLayout")
    public VerticalLayout ComponentDetailsLayout;

    List<ContractSelectionDTO> selectedList;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("addToTree")
    public Button addToTree;
    @UiField("populateDetails")
    public Button populateDetails;
    @UiField("removeBtn")
    public Button removeBtn;
    public boolean checkAll = false;
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
    @UiField("componentDetailsLayout")
    VerticalLayout componentDetailsLayout;
    DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    SimpleDateFormat sdfSource = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    TradingPartnerDAO ccDao = new TradingPartnerDAOImpl();
    private String SELECT_RECORD = "Please Select Atleast one record at Contract Component Results Section";

    public Copycomponents(List<ContractSelectionDTO> selectedList, TreeTable contractDashBoardTable, CustomTreeContainer<CopyComponentDTO> dashBoardContainer) {
        try
        {
        this.dashBoardContainer=dashBoardContainer;
        this.selectedList = selectedList;
        this.contractDashBoardTable = contractDashBoardTable;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/CopyComponents.xml"), this));
        contractDashBoardLayout.addComponent(contractDashBoardTable);
        configureFields();
        }
        catch(Exception e){
           
            LOGGER.info("Error in Copy Components ----------->"+e.getMessage());
        }
    }

    protected void configureFields() {
        try {
            BtnsearchNC.setEnabled(false);
            componentitems.addItem(Constants.COMPANY_FAMILY_PLAN);
            componentitems.addItem(Constants.ITEM_FAMILY_PLAN);
            componentitems.addItem(Constants.PRICE_SCHEDULE);
            componentitems.addItem(Constants.REBATE_SCHEDULE);
            ComponenttypeNC.setNullSelectionAllowed(false);
            ComponenttypeNC.addItem(Constants.COMPANY_FAMILY_PLAN);
            ComponenttypeNC.addItem(Constants.ITEM_FAMILY_PLAN);
            ComponenttypeNC.addItem(Constants.PRICE_SCHEDULE);
            ComponenttypeNC.addItem(Constants.REBATE_SCHEDULE);
            ComponenttypeNC.select(Constants.COMPANY_FAMILY_PLAN);
             
            SearchfieldNC.setEnabled(false);
            SearchvaluedNC.setEnabled(false);
            contractDashBoardTable.setPageLength(10);
            contractDashBoardTable.markAsDirty();
            contractDashBoardTable.setImmediate(true);
            contractDashBoardTable.setContainerDataSource(dashBoardContainer);
            contractDashBoardTable.setVisibleColumns(Constants.COPYCONTRACT_DASHBOARD_RESULTS_COLUMNS);
            contractDashBoardTable.setColumnHeaders(Constants.COPYCONTRACT_DASHBOARD_RESULTS_HEADERS);
            contractDashBoardTable.setWidth("670px");
            contractDashBoardTable.setSelectable(true);
            contractDashBoardTable.setMultiSelect(false);
            TblLayout.addComponent(contractComponent);
            TblLayout.addComponent(CopyComponentsResultLogic.createControls());
            ComponentDetailsLayout.addComponent(componentDetailsTable);
            ComponentDetailsLayout.addComponent(CopyComponentsSearchLogic.createControls());
            infolayout.addComponent(contractInformationTable);
            infolayout.addComponent(ComponentInfoLogic.createControls());

            CopyComponentsSearchLogic.sinkItemPerPageWithPageLength(false);
            contractInformationTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            ComponentInfoLogic.setContainerDataSource(contractInfoContainer);
            ComponentInfoLogic.setPageLength(5);
            ComponentInfoLogic.sinkItemPerPageWithPageLength(false);
            contractInformationTable.setVisibleColumns(Constants.CONTRACT_INFO_COLUMNS);
            contractInformationTable.setColumnHeaders(Constants.CONTRACT_INFO_HEADERS);
            contractInformationTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
            contractInformationTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
            contractInformationTable.setWidth("100%");
            contractInformationTable.setHeight("100%");
            SearchfieldNC.setEnabled(false);
            SearchvaluedNC.setEnabled(false);
            contractComponent.setWidth("100%");
            contractComponent.setHeight("100%");
            contractComponent.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            contractComponent.setSelectable(true);
            CopyComponentsResultLogic.setContainerDataSource(contractComponentContainer);
            CopyComponentsResultLogic.setPageLength(5);
            CopyComponentsResultLogic.sinkItemPerPageWithPageLength(false);
            contractComponent.setEditable(true);
            contractComponent.setVisibleColumns(Constants.CONTRACT_COMPONENT_COLUMNS);
            contractComponent.setColumnHeaders(Constants.CONTRACT_COMPONENT_HEADERS);
            contractComponent.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
            contractComponent.setColumnAlignment("contractStartDate", ExtCustomTable.Align.CENTER);
            contractComponent.setColumnAlignment("contractEndDate", ExtCustomTable.Align.CENTER);
            contractComponent.setTableFieldFactory(new TableFieldFactory() {
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

            contractComponent.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (CopyComponentDTO temp : contractComponentContainer.getItemIds()) {
                        contractComponentContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });

            contractComponent.setColumnWidth(Constants.CHECK, 90);
            contractComponent.setColumnWidth(Constants.CONTRACT_HOLDER, 200);
            contractComponent.setColumnWidth(Constants.CONTRACT_NO, 200);
            contractComponent.setColumnWidth(Constants.CONTRACT_NAME, 200);
            contractComponent.setColumnWidth(Constants.MARKET_TYPE, 200);
            contractComponent.setColumnWidth(Constants.CONTRACT_START_DATE, 210);
            contractComponent.setColumnWidth(Constants.CONTRACT_END_DATE, 210);
            contractComponent.setColumnWidth(Constants.IFP_NAME, 200);
            contractComponent.setColumnWidth(Constants.PS_NAME, 200);
            contractComponent.setColumnWidth(Constants.RS_NAME, 200);
            componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentDetailsTable.setHeight(Constants.HUNDRED_PERCENT);
            CopyComponentsSearchLogic.setContainerDataSource(copycontractResultsContainer);
            CopyComponentsSearchLogic.setPageLength(5);
            componentDetailsTable.setSelectable(true);
            componentDetailsTable.setVisibleColumns(UiUtils.CC_COMPANY_DETAILS_COLUMNS);
            componentDetailsTable.setColumnHeaders(UiUtils.NEW_COMPANY_DETAILS_HEADERS);
            componentDetailsTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
            componentDetailsTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
            componentDetailsTable.setWidth(Constants.NINE_SEVENTY_PXL);

            componentDetailsTable.setColumnWidth(Constants.COMPANY_NO, 200);
            componentDetailsTable.setColumnWidth(Constants.COMPANY_NAME, 200);
            componentDetailsTable.setColumnWidth(Constants.COMPANY_STATUS, 200);
            componentDetailsTable.setColumnWidth(Constants.CONTRACT_NAME, 200);
            componentDetailsTable.setColumnWidth(Constants.COMPANY_START_DATE, 200);
            componentDetailsTable.setColumnWidth(Constants.COMPANY_END_DATE, 210);

            cfpComponent.setWidth(Constants.NINE_SEVENTY_PXL);
            ifpComponent.setWidth(Constants.NINE_SEVENTY_PXL);
            psComponent.setWidth(Constants.NINE_SEVENTY_PXL);
            rsComponent.setWidth(Constants.NINE_SEVENTY_PXL);

            cfpComponent.setVisible(true);
            ifpComponent.setVisible(false);
            psComponent.setVisible(false);
            rsComponent.setVisible(false);

            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);

            loadContractComponentTable();
            contractComponent.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method is called when results value is changed
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    resultsItemClick(event.getProperty().getValue());
                }
            });

            contractComponent.setTableFieldFactory(new DefaultFieldFactory() {
                /**
                 * To create editable fields inside table .
                 */
                public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
                    final CopyComponentDTO dto = (CopyComponentDTO) itemId;
                    if (propertyId.equals(Constants.CHECK)) {
                        final CheckBox select = new CheckBox();
                        select.setImmediate(true);
                        select.setValue(false);
                        return select;
                    }
                    return null;
                }
            });

        } catch (Exception ex) {
          
            LOGGER.error(ex.getMessage());
        }

        ComponenttypeNC.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String compType = String.valueOf(ComponenttypeNC.getValue());
                if (compType.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    cfpComponent.setVisible(true);
                    ifpComponent.setVisible(false);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);

                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(UiUtils.CC_COMPANY_DETAILS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(UiUtils.NEW_COMPANY_DETAILS_HEADERS);
                    componentDetailsTable.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
                    componentDetailsTable.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
                    contractComponent.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
                } else if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
                    cfpComponent.setVisible(false);
                    ifpComponent.setVisible(true);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(false);

                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(UiUtils.CC_IFP_DETAILS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(UiUtils.NEW_IFP_DETAILS_HEADERS);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                    contractComponent.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
                } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
                    cfpComponent.setVisible(false);
                    ifpComponent.setVisible(false);
                    psComponent.setVisible(true);
                    rsComponent.setVisible(false);

                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(UiUtils.CC_PS_DETAILS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(UiUtils.NEW_PS_DETAILS_HEADERS);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                    contractComponent.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
                } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                    cfpComponent.setVisible(false);
                    ifpComponent.setVisible(false);
                    psComponent.setVisible(false);
                    rsComponent.setVisible(true);

                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(UiUtils.CC_RS_DETAILS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(UiUtils.NEW_RS_DETAILS_HEADERS);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
                    componentDetailsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
                    contractComponent.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
                }
                contractComponent.removeAllItems();
                try {
                    loadContractComponentTable();
                } catch (ParseException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        componentDetailsLayout.setSizeUndefined();
    }

    private void loadContractComponentTable() throws ParseException {

        CopyComponentsResultLogic.setData(selectedList);
    }

    protected void resultsItemClick(final Object obj) {
        CopyComponentDTO dto = (CopyComponentDTO) obj;
        String compType = String.valueOf(ComponenttypeNC.getValue());
        if (compType.equals(Constants.COMPANY_FAMILY_PLAN)) {
            if (dto != null) {
                int cfpIdValue = dto.getCFPId();
                String query = queryUtils.getCFPDetails(cfpIdValue);
                List cfpList = ccDao.searchList(query);
                if (cfpList != null && cfpList.size() > 0) {
                    Object[] object = (Object[]) cfpList.get(0);
                    cfpId.setValue(String.valueOf(object[0]));
                    cfpNo.setValue(String.valueOf(object[1]));
                    cfpName.setValue(String.valueOf(object[2]));
                    cfpStatus.setValue(String.valueOf(object[3]));
                    if (object[4] != null) {
                        String date = df.format(object[4]);
                        cfpStartDate.setValue(date);
                    } else {
                        cfpStartDate.setValue(Constants.EMPTY);
                    }
                    if (object[5] != null) {
                        cfpfileName.setValue(String.valueOf(object[5]));
                    } else {
                        cfpfileName.setValue(Constants.EMPTY);
                    }
                }
            }
        } else if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
            if (dto != null) {
                int ifpIdValue = dto.getIFPId();
                String query = queryUtils.getIFPDetails(ifpIdValue);
                List ifpList = ccDao.searchList(query);
                if (ifpList != null && ifpList.size() > 0) {
                    Object[] object = (Object[]) ifpList.get(0);
                    ifpId.setValue(String.valueOf(object[0]));
                    ifpNo.setValue(String.valueOf(object[1]));
                    ifpName.setValue(String.valueOf(object[2]));
                    ifpStatus.setValue(String.valueOf(object[3]));
                    if (object[4] != null) {
                        String date = df.format(object[4]);
                        ifpStartDate.setValue(date);
                    } else {
                        ifpStartDate.setValue(Constants.EMPTY);
                    }
                    if (object[5] != null) {
                        ifpfileName.setValue(String.valueOf(object[5]));
                    } else {
                        ifpfileName.setValue(Constants.EMPTY);
                    }
                }
            }
        } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
            if (dto != null) {
                int psIdValue = dto.getPSId();
                String query = queryUtils.getPSDetails(psIdValue);
                List psList = ccDao.searchList(query);
                if (psList != null && psList.size() > 0) {
                    Object[] object = (Object[]) psList.get(0);
                    psId.setValue(String.valueOf(object[0]));
                    psNo.setValue(String.valueOf(object[1]));
                    psName.setValue(String.valueOf(object[2]));
                    psStatus.setValue(String.valueOf(object[3]));
                    if (object[4] != null) {
                        String date = df.format(object[4]);
                        psStartDate.setValue(date);
                    } else {
                        psStartDate.setValue(Constants.EMPTY);
                    }
                    if (object[5] != null) {
                        psfileName.setValue(String.valueOf(object[5]));
                    } else {
                        psfileName.setValue(Constants.EMPTY);
                    }
                }
            }
        } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
            if (dto != null) {
                int rsIdValue = dto.getRSId();
                String query = queryUtils.rsValue(rsIdValue);
                List rsList = ccDao.searchList(query);
                if (rsList != null && rsList.size() > 0) {
                    Object[] object = (Object[]) rsList.get(0);
                    rsId.setValue(String.valueOf(object[0]));
                    rsStatus.setValue(String.valueOf(object[1]));
                    rsNumber.setValue(String.valueOf(object[2]));
                    if (object[3] != null) {
                        String date = df.format(object[3]);
                        rsStartDate.setValue(date);
                    } else {
                        rsStartDate.setValue(Constants.EMPTY);
                    }
                    rsName.setValue(String.valueOf(object[4]));
                    if (object[5] != null) {
                        String date = df.format(object[5]);
                        rsEndDate.setValue(date);
                    } else {
                        rsEndDate.setValue(Constants.EMPTY);
                    }
                    rsFrequency.setValue(String.valueOf(object[6]));
                    rsRarType.setValue(String.valueOf(object[7]));
                }
            }
        }
    }

    public void checkClearAll(boolean checkClear) {
        Collection<?> returnList = contractComponent.getItemIds();
        for (Object item : returnList) {
            contractComponentContainer.getContainerProperty(item, Constants.CHECK).setValue(checkClear);
        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populateBtn")
    public void populateBtnLogic(Button.ClickEvent event) {
        String compType = String.valueOf(ComponenttypeNC.getValue());
        Boolean checkedFlag = false;
        if (compType.equals(Constants.COMPANY_FAMILY_PLAN)) {
            String ids = Constants.EMPTY;
            Collection<?> returnList = contractComponent.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.CFP_Id).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.CFP_Id).getValue());
                        ids = ids + Constants.COMMA + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String companyQuery = queryUtils.getCompanyMasterSid(ids);
                String value = Constants.EMPTY;
                List companyids = ccDao.searchList(companyQuery);
                if (companyids != null && companyids.size() > 0) {
                    Boolean f = false;
                    for (Object item : companyids) {
                        if (!f) {
                            String company = String.valueOf(item);
                            value = company;
                            f = true;
                        } else {
                            String company = String.valueOf(item);
                            value = value + Constants.COMMA + company;
                        }
                    }
                }
                String componentQuery = queryUtils.compValue(value);

                CopyComponentsSearchLogic.setData(Constants.CFP, componentQuery);
            }
            Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
            for (Object column : visibleColumns) {
                componentDetailsTable.setColumnWidth(column, 180);
            }
        } else if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
            String ids = Constants.EMPTY;
            Collection<?> returnList = contractComponent.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.IFP_Id).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.IFP_Id).getValue());
                        ids = ids + Constants.COMMA + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getItemMasterDetails(ids);
                CopyComponentsSearchLogic.setData(Constants.IFP, componentQuery);
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }
            }
        } else if (compType.equals(Constants.PRICE_SCHEDULE)) {

            String ids = Constants.EMPTY;
            Collection<?> returnList = contractComponent.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.PS_ID).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.PS_ID).getValue());
                        ids = ids + Constants.COMMA + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getPSDetails(ids);
                CopyComponentsSearchLogic.setData(Constants.PS, componentQuery);
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }
            }
        } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
            String ids = Constants.EMPTY;
            Collection<?> returnList = contractComponent.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.RS_ID).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.RS_ID).getValue());
                        ids = ids + Constants.COMMA + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getRSDetails(ids);
                CopyComponentsSearchLogic.setData(Constants.RS, componentQuery);
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }
            }
        }
        if (!checkedFlag) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_RECORD);
            return;
        }

    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("addToTree")
    public void dashBoardLogic(Button.ClickEvent event) {
        if (contractDashBoardTable.getItemIds().isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Add Contract Header Node");
        } else {
            Object root = contractDashBoardTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                String modelSId = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.MODEL_ID).getValue());
                int levelNumber = Integer.valueOf(levelNo);
                String level = String.valueOf(componentitems.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please checkmark components to add to the Contract");
                }
                Collection<?> checkList = contractComponent.getItemIds();
                Boolean flag = false;
                for (Object tmp : checkList) {
                    Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(tmp, Constants.CHECK).getValue();
                    if (checked) {
                        flag = true;
                    }
                }
                if (!flag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, SELECT_RECORD);
                    return;
                }
                if (level.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    if (1 - levelNumber == 1) {
                        Collection<?> returnList = contractComponent.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                String cfpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.CFP_Id).getValue());
                                setA.add(cfpId);
                            }
                        }
                        List<String> tmp = new ArrayList<String>();
                        for (Object cfpContId : setA) {
                            String id = String.valueOf(cfpContId);
                            String query = queryUtils.cfpValue(id);
                            List cfpList = ccDao.searchList(query);
                            if (cfpList != null && cfpList.size() > 0) {
                                Object[] obj = (Object[]) cfpList.get(0);
                                String modelId = String.valueOf(obj[3]);
                                if (!tmp.contains(modelId)) {
                                    tmp.add(modelId);
                                    final Object rootId = contractDashBoardTable.addItem();
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.CFP);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.ONE);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("3");
                                    contractDashBoardTable.addItem(rootId);
                                    contractDashBoardTable.setParent(rootId, root);
                                    contractDashBoardTable.setChildrenAllowed(rootId, true);
                                    contractDashBoardTable.setCollapsed(root, false);
                                }
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                        return;
                    }
                } else if (level.equals(Constants.ITEM_FAMILY_PLAN)) {
                    if (2 - levelNumber == 1) {
                        Collection<?> returnList = contractComponent.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                String ifpId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.IFP_Id).getValue());
                                setA.add(ifpId);
                            }
                        }
                        List<String> tmp = new ArrayList<String>();
                        for (Object ifpContId : setA) {
                            String id = String.valueOf(ifpContId);
                            String query = queryUtils.getIFP(id);
                            List ifpList = ccDao.searchList(query);
                            if (ifpList != null && ifpList.size() > 0) {
                                Object[] obj = (Object[]) ifpList.get(0);
                                String modelId = String.valueOf(obj[3]);
                                if (!tmp.contains(modelId)) {
                                    tmp.add(modelId);
                                    final Object rootId = contractDashBoardTable.addItem();
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("3");
                                    contractDashBoardTable.addItem(rootId);
                                    contractDashBoardTable.setParent(rootId, root);
                                    contractDashBoardTable.setChildrenAllowed(rootId, true);
                                    contractDashBoardTable.setCollapsed(root, false);
                                }
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (3 - levelNumber == 1) {
                        Collection<?> returnList = contractComponent.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                String psId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.PS_ID).getValue());
                                setA.add(psId);
                            }
                        }
                        Boolean psFlag = true;
                        List<String> tmp = new ArrayList<String>();
                        for (Object psContId : setA) {
                            String id = String.valueOf(psContId);
                            String query = queryUtils.psValue(id);
                            List psList = ccDao.searchList(query);
                            if (psList != null && psList.size() > 0) {
                                Object[] obj = (Object[]) psList.get(0);
                                String modelId = String.valueOf(obj[3]);
                                if (!tmp.contains(modelId)) {
                                    String conditionQuery = queryUtils.conditionQuery(modelId, modelSId);
                                    List conditionList = ccDao.searchList(conditionQuery);
                                    if (conditionList != null && conditionList.size() > 0) {
                                        psFlag = false;
                                        tmp.add(modelId);
                                        final Object rootId = contractDashBoardTable.addItem();
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                        contractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("3");
                                        contractDashBoardTable.addItem(rootId);
                                        contractDashBoardTable.setParent(rootId, root);
                                        contractDashBoardTable.setChildrenAllowed(rootId, true);
                                        contractDashBoardTable.setCollapsed(root, false);
                                    }
                                }
                            }
                        }
                        if (psFlag) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS does not associate with  IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (4 - levelNumber == 1) {
                        Collection<?> returnList = contractComponent.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) contractComponentContainer.getContainerProperty(item, Constants.CHECK).getValue();
                            if (checked) {
                                String rsId = String.valueOf(contractComponentContainer.getContainerProperty(item, Constants.RS_ID).getValue());
                                setA.add(rsId);
                            }
                        }
                        List<String> tmp = new ArrayList<String>();
                        for (Object rsContId : setA) {
                            String id = String.valueOf(rsContId);
                            String query = queryUtils.rsValue(id);
                            List rsList = ccDao.searchList(query);
                            if (rsList != null && rsList.size() > 0) {
                                Object[] obj = (Object[]) rsList.get(0);
                                String modelId = String.valueOf(obj[3]);
                                if (!tmp.contains(modelId)) {
                                    tmp.add(modelId);
                                    final Object rootId = contractDashBoardTable.addItem();
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                    contractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("3");
                                    contractDashBoardTable.addItem(rootId);
                                    contractDashBoardTable.setParent(rootId, root);
                                    contractDashBoardTable.setChildrenAllowed(rootId, false);
                                    contractDashBoardTable.setCollapsed(root, false);
                                }
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please checkmark row(s) to be added to the Contract.");
            }

        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populateDetails")
    public void populateDetailsLogic(Button.ClickEvent event) {
        Object root = contractDashBoardTable.getValue();
        if (root != null) {
            String level = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ONE)) {
                String cfpcontId = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                String companyQuery = queryUtils.getCompanyMasterSid(cfpcontId);
                String value = Constants.EMPTY;
                List companyids = ccDao.searchList(companyQuery);
                if (companyids != null && companyids.size() > 0) {
                    Boolean f = false;
                    for (Object item : companyids) {
                        if (!f) {
                            String company = String.valueOf(item);
                            value = company;
                            f = true;
                        } else {
                            String company = String.valueOf(item);
                            value = value + Constants.COMMA + company;
                        }
                    }
                }
                String componentQuery = queryUtils.populateQuery(value);
                ComponentInfoLogic.setData(Constants.CFP, componentQuery);
                contractInformationTable.setVisibleColumns(Constants.CC_COMPONENT_DETAILS_COLUMNS);
                contractInformationTable.setColumnHeaders(Constants.CC_COMPONENT_DETAILS_HEADERS);
                cfpDetailsGrid.setVisible(true);
                ifpDetailsGrid.setVisible(false);
                psDetailsGrid.setVisible(false);
                rsDetailsGrid.setVisible(false);
                String detailsNo = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                cfpDetailsNo.setValue(detailsNo);
                String detailsName = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                cfpDetailsName.setValue(detailsName);
                cfpDetailsNo.setEnabled(false);
                cfpDetailsName.setEnabled(false);

                Object[] vColumns = contractInformationTable.getVisibleColumns();
                for (Object obj : vColumns) {
                    contractInformationTable.setColumnWidth(obj, 180);
                }
            } else if (level.equals(Constants.TWO) || level.equals(Constants.THREE) || level.equals(Constants.FOUR)) {
                String ifpId = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                String componentQuery = Constants.EMPTY;
                if (level.equals(Constants.TWO)) {
                    componentQuery = queryUtils.getItemMasterDetails(ifpId);
                    ComponentInfoLogic.setData(Constants.IFP, componentQuery);
                } else if (level.equals(Constants.THREE)) {
                    componentQuery = queryUtils.getPSDetails(ifpId);
                    ComponentInfoLogic.setData(Constants.PS, componentQuery);
                } else if (level.equals(Constants.FOUR)) {
                    componentQuery = queryUtils.getRSDetails(ifpId);
                    ComponentInfoLogic.setData(Constants.RS, componentQuery);
                }
                contractInformationTable.setVisibleColumns(Constants.COMPONENT_DETAILS_ITEM_COLUMNS);
                contractInformationTable.setColumnHeaders(Constants.COMPONENT_DETAILS_ITEM_HEADERS);
                if (level.equals(Constants.TWO)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(true);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                    ifpDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    ifpDetailsName.setValue(detailsName);
                    ifpDetailsNo.setEnabled(false);
                    ifpDetailsName.setEnabled(false);
                } else if (level.equals(Constants.THREE)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(true);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                    psDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    psDetailsName.setValue(detailsName);
                    psDetailsNo.setEnabled(false);
                    psDetailsName.setEnabled(false);
                } else if (level.equals(Constants.FOUR)) {
                    cfpDetailsGrid.setVisible(false);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(true);
                    String detailsNo = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                    rsDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(contractDashBoardTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    rsDetailsName.setValue(detailsName);
                    rsDetailsNo.setEnabled(false);
                    rsDetailsName.setEnabled(false);
                }
                Object[] vColumns = contractInformationTable.getVisibleColumns();
                for (Object obj : vColumns) {
                    contractInformationTable.setColumnWidth(obj, 180);
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please highlight a row to populate. ");
        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("removeBtn")
    public void removeLogic(Button.ClickEvent event) {
        final Object root = contractDashBoardTable.getValue();
        if (root != null) {
            Boolean flag = contractDashBoardTable.hasChildren(root);
            if (!flag) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        try {
                            contractDashBoardTable.removeItem(root);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    }
                }.getConfirmationMessage("Reset Conformation", "Are you sure you want to remove the selected record?");
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please remove all children nodes before removing a parent node.. ");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please highlight a component to Remove");
        }
    }

    public void savecontract(Object item) throws SystemException, PortalException, ParseException {
        try {
            String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.SESSION_ID));
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            Collection<?> treeItem = contractDashBoardTable.getItemIds();
            int contractMasterSid = 0;
//            for (Object item : treeItem) {
                String level = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    String contractId = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                    String contractNo = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                    String contractName = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                    int contractType = ((HelperDTO)contractDashBoardTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()).getId();
                    int status = ((HelperDTO)contractDashBoardTable.getContainerProperty(item, Constants.STATUS_S).getValue()).getId();
                    String contractHolder = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.CONTRACT_HOLDER).getValue());
                    Date startDate = (Date) contractDashBoardTable.getContainerProperty(item, Constants.START_DATE).getValue();
                    Date endDate = (Date) contractDashBoardTable.getContainerProperty(item, Constants.END_DATE).getValue();
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
                    contractMaster.setInboundStatus(Constants.A);
                    contractMaster.setCreatedDate(new Date());
                    contractMaster.setModifiedDate(new Date());
                    contractMaster = ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
                    contractMasterSid = contractMaster.getContractMasterSid();
                    contractDashBoardTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(String.valueOf(contractMasterSid));
                    int AliasType = ((HelperDTO)contractDashBoardTable.getContainerProperty(item, Constants.ALIAS_TYPE).getValue()).getId();
                    Date AliasSDATE = (Date) contractDashBoardTable.getContainerProperty(item, Constants.ALIAS_START_DATE).getValue();
                    String AliasNumber = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.ALIAS_NUMBER).getValue());
                    Date AliasEDATE = (Date) contractDashBoardTable.getContainerProperty(item, Constants.ALIAS_END_DATE).getValue();
                    ContractAliasMaster CAM = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    CAM.setContractAliasNo(AliasNumber);
                    CAM.setContractAliasType(AliasType);
                    CAM.setStartDate(AliasSDATE);
                    CAM.setEndDate(AliasEDATE);
                    CAM.setModifiedDate(new Date());
                    CAM.setCreatedBy(1);
                    CAM.setCreatedDate(new Date());
                    CAM.setSource("BPI");
                    CAM.setInboundStatus(Constants.A);
                    CAM.setContractMasterSid(contractMasterSid);
                    ContractAliasMaster CAM1 = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);
                } else if (level.equals(Constants.ONE)) {
                    String cfpContractSId = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                    Object contractItem = contractDashBoardTable.getParent(item);
                    String contractSid = String.valueOf(contractDashBoardTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Date date = new Date();
                    String createdDate = dbDate.format(date);
                    String query = queryUtils.cfpContractDetailsInsert(contractSid, createdDate, cfpContractSId);
                    int count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
                    String query1 = queryUtils.idenCFPQuery();
                    List ids = ccDao.searchList(query1);
                    if (ids != null && ids.size() > 0) {
                        String id = String.valueOf(ids.get(0));
                        contractDashBoardTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(id);
                        String cfpModelQuery = queryUtils.cfpModelQuery(id);
                        List cfpModel = ccDao.searchList(cfpModelQuery);
                        if (cfpModel != null && cfpModel.size() > 0) {
                            String cfpModelId = String.valueOf(cfpModel.get(0));
                            SaveCFP(String.valueOf(id), String.valueOf(cfpModelId));
                        }
                    }
                } else if (level.equals(Constants.TWO)) {
                    String ifpContractSId = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                    Object parentItem = contractDashBoardTable.getParent(item);
                    String parentCFPId = String.valueOf(contractDashBoardTable.getContainerProperty(parentItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = contractDashBoardTable.getParent(parentItem);
                    String contractSid = String.valueOf(contractDashBoardTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Date date = new Date();
                    String createdDate = dbDate.format(date);
                    String query = queryUtils.ifpContractDetailsInsert(parentCFPId, contractSid, createdDate, ifpContractSId);
                    int count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
                    String query1 = queryUtils.idenIFPQuery();
                    List ids = ccDao.searchList(query1);
                    if (ids != null && ids.size() > 0) {
                        String id = String.valueOf(ids.get(0));
                        contractDashBoardTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(id);
                        String ifpModelQuery = queryUtils.ifpModelQuery(id);
                        List ifpModel = ccDao.searchList(ifpModelQuery);
                        if (ifpModel != null && ifpModel.size() > 0) {
                            String ifpModelId = String.valueOf(ifpModel.get(0));
                            SaveIFP(String.valueOf(id), String.valueOf(ifpModelId));
                        }
                    }
                } else if (level.equals(Constants.THREE)) {
                    String psContractSId = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                    Object parentItem = contractDashBoardTable.getParent(item);
                    String parentIFPId = String.valueOf(contractDashBoardTable.getContainerProperty(parentItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = contractDashBoardTable.getParent(parentItem);
                    String parentCFPId = String.valueOf(contractDashBoardTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = contractDashBoardTable.getParent(parentCFPItem);
                    String contractSid = String.valueOf(contractDashBoardTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Date date = new Date();
                    String createdDate = dbDate.format(date);
                    String query = queryUtils.psContractDetailsInsert(contractSid, parentCFPId, parentIFPId, createdDate, psContractSId);
                    int count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
                    String query1 = queryUtils.idenPSQuery();
                    List ids = ccDao.searchList(query1);
                    if (ids != null && ids.size() > 0) {
                        String id = String.valueOf(ids.get(0));
                        contractDashBoardTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(id);
                        String psModelQuery = queryUtils.psModelQuery(id);
                        List psModel = ccDao.searchList(psModelQuery);
                        if (psModel != null && psModel.size() > 0) {
                            String psModelId = String.valueOf(psModel.get(0));
                            SavePS(String.valueOf(id), String.valueOf(psModelId));
                        }
                    }
                } else if (level.equals(Constants.FOUR)) {
                    String rsContractSId = String.valueOf(contractDashBoardTable.getContainerProperty(item, Constants.HIDDEN_ID).getValue());
                    Object psParentItem = contractDashBoardTable.getParent(item);
                    String parentPSId = String.valueOf(contractDashBoardTable.getContainerProperty(psParentItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentIFPItem = contractDashBoardTable.getParent(psParentItem);
                    String parentIFPId = String.valueOf(contractDashBoardTable.getContainerProperty(parentIFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object parentCFPItem = contractDashBoardTable.getParent(parentIFPItem);
                    String parentCFPId = String.valueOf(contractDashBoardTable.getContainerProperty(parentCFPItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Object contractItem = contractDashBoardTable.getParent(parentCFPItem);
                    String contractSid = String.valueOf(contractDashBoardTable.getContainerProperty(contractItem, Constants.SAVED_SYSTEM_ID).getValue());
                    Date date = new Date();
                    String bpi = "BPI";
                    String createdDate = dbDate.format(date);
                    String query = queryUtils.rsContractDetailsInsert(contractSid, parentCFPId, parentIFPId, parentPSId, createdDate, rsContractSId);
                    int count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
                    String query1 = queryUtils.idenRSQuery();
                    List ids = ccDao.searchList(query1);
                    if (ids != null && ids.size() > 0) {
                        String id = String.valueOf(ids.get(0));
                        contractDashBoardTable.getContainerProperty(item, Constants.SAVED_SYSTEM_ID).setValue(id);
                        String rsModelQuery = queryUtils.rsModelQuery(id);
                        List rsModel = ccDao.searchList(rsModelQuery);
                        if (rsModel != null && rsModel.size() > 0) {
                            String rsModelId = String.valueOf(rsModel.get(0));
                            SaveRS(String.valueOf(id), String.valueOf(rsModelId));
                        }
                    }
                }
//            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void SaveCFP(String cfpId, String cfpModelId) {
        copyContractLogic.SaveCFPForCopyComponent(cfpId, cfpModelId);
    }

    public void SaveIFP(String ifpId, String ifpModelId) {
        copyContractLogic.SaveIFPForCopyComponent(ifpId, ifpModelId);
    }

    public void SavePS(String psid, String psModelId) {
        copyContractLogic.SavePSForCopyComponent(psid, psModelId);
    }

    public void SaveRS(String rsid, String rsModelId) {
        copyContractLogic.SaveRSForCopyComponent(rsid, rsModelId);

    }
}
