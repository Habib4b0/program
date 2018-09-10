/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.logic.ExistingComponentSearchTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SELECT_ONE;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsModel;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.impl.IfpContractDetailsImpl;
import com.stpl.app.gcm.impl.PsContractDetailsImpl;
import com.stpl.app.gcm.impl.RsContractDetailsImpl;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
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
 * @author alok.v
 */
public class ExistingComponents extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExistingComponents.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("transferCompPanelTableLayout")
    private VerticalLayout transferCompPanelTableLayout;
    @UiField("contractDashboardResultsTableLayout")
    private VerticalLayout contractDashboardResultsTableLayout;
    @UiField("componentDetailsTableLayout")
    private VerticalLayout componentDetailsTableLayout;
    @UiField("contractComponentDetailsTableLayout")
    private VerticalLayout contractComponentDetailsTableLayout;
    private TreeTable contractDashboardResultsTableInEC = new TreeTable();
    private ExtFilterTable contractComponentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> transferCompContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> selectedContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    @UiField("componentType")
    private ComboBox componentTypeDdlbInEC;
    @UiField("searchField")
    private ComboBox searchFieldDdlb;
    @UiField("rebateScheduleId")
    private TextField ecrebateScheduleId;
    @UiField("status")
    private TextField ecstatus;
    @UiField("rebateFrequency")
    private TextField ecrebateFrequency;
    @UiField("rsNumber")
    private TextField ecrsNumber;
    @UiField("startDate")
    private TextField ecstartDate;
    @UiField("rarType")
    private TextField ecrarType;
    @UiField("rsType")
    private TextField ecrsType;
    @UiField("rsName")
    private TextField ecrsName;
    @UiField("endDate")
    private TextField ecendDate;
    @UiField("basis")
    private TextField ecbasis;
    @UiField("excelBtn1")
    private Button ecexcelBtn1;
    @UiField("excelBtn2")
    private Button ecexcelBtn2;
    @UiField("searchValue")
    private TextField ecsearchValue;
    @UiField("searchBtn1")
    private Button searchBtn1;
    @UiField("addToTreeBtn1")
    private Button addToTreeBtn1;
    @UiField("removeBtn2")
    private Button removeBtn2;
    @UiField("populateBtn2")
    private Button populateBtn2;
    @UiField("cfpDetailsGrid")
    private GridLayout cfpDetailsGridEC;
    @UiField("ifpDetailsGrid")
    private GridLayout ifpDetailsGridEC;
    @UiField("psDetailsGrid")
    private GridLayout psDetailsGridEC;
    @UiField("rsDetailsGrid")
    private GridLayout rsDetailsGridEC;
    @UiField("cfpDetailsNo")
    private TextField cfpDetailsNoEC;
    @UiField("cfpDetailsName")
    private TextField cfpDetailsNameEC;
    @UiField("ifpDetailsNo")
    private TextField ifpDetailsNoEC;
    @UiField("ifpDetailsName")
    private TextField ifpDetailsNameEC;
    @UiField("psDetailsNo")
    private TextField psDetailsNoEC;
    @UiField("psDetailsName")
    private TextField psDetailsNameEC;
    @UiField("rsDetailsNo")
    private TextField rsDetailsNoEC;
    @UiField("rsDetailsName")
    private TextField rsDetailsNameEC;
    @UiField("ifpId")
    private TextField ifpIdEC;
    @UiField("ifpNo")
    private TextField ifpNo;
    @UiField("ifpName")
    private TextField ifpName;
    @UiField("ifpStatus")
    private TextField ifpStatus;
    @UiField("ifpType")
    private TextField ifpType;
    @UiField("ifpStartDate")
    private TextField ifpStartDate;
    @UiField("ifpEndDate")
    private TextField ifpEndDate;
    @UiField("psId")
    private TextField psIdEC;
    @UiField("psNo")
    private TextField psNoEC;
    @UiField("psName")
    private TextField psNameEC;
    @UiField("psStatus")
    private TextField psStatusEC;
    @UiField("psStartDate")
    private TextField psStartDateEC;
    @UiField("psEndDate")
    private TextField psEndDateEC;
    @UiField("searchType")
    private ComboBox searchType;
    @UiField("componentInformationLayout1")
    private GridLayout componentInformationLayout1;
    @UiField("componentInfoIfpLayout")
    private GridLayout componentInfoIfpLayout;
    @UiField("componentInfoPsLayout")
    private GridLayout componentInfoPsLayout;
    private  ExtTreeContainer<ComponentInfoDTO> dashBoardTreeContainer1 = new ExtTreeContainer<>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> componentResultsContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private ExistingComponentSearchTableLogic availableTableLogic = new ExistingComponentSearchTableLogic();
    private ExtPagedTable componentResultsTable = new ExtPagedTable(availableTableLogic);
    private ComponentInfoDTO newDiscountTabDto = new ComponentInfoDTO();
    private ExtPagedFilterTable componentDetailsSelectedItem = new ExtPagedFilterTable();
    
    private ExtTreeContainer<ComponentInfoDTO> excelResultBean2 = new ExtTreeContainer<>(ComponentInfoDTO.class);
    
    private ExtCustomTable exportPeriodViewTable = new ExtCustomTable();
    private ExtCustomTable exportPeriodViewTable2 = new ExtCustomTable();
    private String excelName1 = "Component Results";
    private String excelName2 = "Component Details";
    private PromoteTPLogic logic = new PromoteTPLogic();
    private QueryUtils queryUtils = new QueryUtils();
    private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    private List<HelperDTO> itemStatusListInEC = new ArrayList<>();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private boolean contractExcelFlag = false;
    private boolean infoExcelFlag = false;
    private StplSecurity stplSecurity = new StplSecurity();
    
    public ExistingComponents(SessionDTO sessionDTO, TreeTable contractDashboardTable) {
        try {
            this.session = sessionDTO;
            this.contractDashboardResultsTableInEC = contractDashboardTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpExistingComponents.xml"), this));
            configureFieldsForExistComp();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected final void configureFieldsForExistComp() {
        try {

            componentTypeDdlbInEC.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentTypeDdlbInEC.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant());
            componentTypeDdlbInEC.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE.getConstant());
            componentTypeDdlbInEC.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE.getConstant());
            componentTypeDdlbInEC.setNullSelectionAllowed(true);
            componentTypeDdlbInEC.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());

            transferCompPanelTableLayout.addComponent(componentResultsTable);
            configureTransferCompTable();
            HorizontalLayout controls = availableTableLogic.createControls();
            transferCompPanelTableLayout.addComponent(controls);
            contractDashboardResultsTableLayout.addComponent(contractDashboardResultsTableInEC);
            configureContractDashboardResultsTable();
            componentDetailsTableLayout.addComponent(componentDetailsSelectedItem);
            configureComponentDetailsTable();
            HorizontalLayout controls1 = componentDetailsSelectedItem.createControls();
            componentDetailsTableLayout.addComponent(controls1);
            contractComponentDetailsTableLayout.addComponent(contractComponentDetailsTable);
            configureContractComponentDetailsTable();
            ecexcelBtn1.setIcon(excelExportImage);
            ecexcelBtn2.setIcon(excelExportImage);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(false);
            searchType.setVisible(false);
            disableComponentInfoFields();

            cfpDetailsGridEC.setVisible(true);
            ifpDetailsGridEC.setVisible(false);
            psDetailsGridEC.setVisible(false);
            rsDetailsGridEC.setVisible(false);
            cfpDetailsNoEC.setEnabled(false);
            cfpDetailsNameEC.setEnabled(false);

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * Disable Component Information Fields
     *
     */
    public void disableComponentInfoFields() {
        ecrebateScheduleId.setEnabled(false);
        ecstatus.setEnabled(false);
        ecrebateFrequency.setEnabled(false);
        ecrsNumber.setEnabled(false);
        ecstartDate.setEnabled(false);
        ecrarType.setEnabled(false);
        ecrsType.setEnabled(false);
        ecrsName.setEnabled(false);
        ecendDate.setEnabled(false);
        ecbasis.setEnabled(false);
    }

    public void configureTransferCompTable() {
        availableTableLogic.setContainerDataSource(transferCompContainer);
        availableTableLogic.setPageLength(NumericConstants.EIGHT);
        availableTableLogic.sinkItemPerPageWithPageLength(false);

        componentResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentResultsTable.setWidth("890px");
        componentResultsTable.setHeight("230px");
        componentResultsTable.setPageLength(NumericConstants.FIVE);
        componentResultsTable.setContainerDataSource(transferCompContainer);
        componentResultsTable.setVisibleColumns(Constants.getInstance().rsResultsColumns);
        componentResultsTable.setColumnHeaders(Constants.getInstance().rsResultsHeaders);
        componentResultsTable.setSelectable(true);
        for (Object propertyId : componentResultsTable.getVisibleColumns()) {
            componentResultsTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_FIVE);
        }

        componentTypeDdlbInEC.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String compType = String.valueOf(componentTypeDdlbInEC.getValue());
                loadTableHeader(compType);
            }
        });

        componentResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                ComponentInfoDTO comInfoDto = (ComponentInfoDTO) event.getItemId();
                String compType = String.valueOf(componentTypeDdlbInEC.getValue());
                if (!SELECT_ONE.getConstant().equals(compType)) {
                    loadComponentInfo(compType, comInfoDto);
                }
            }
        });

        searchFieldDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String searchFieldInEC = String.valueOf(searchFieldDdlb.getValue());
                if (searchFieldInEC.contains(Constants.STATUS_FIELD) || searchFieldInEC.contains("Type")) {
                    if (searchFieldInEC.contains(Constants.STATUS_FIELD)) {
                        try {
                            itemStatusListInEC.clear();
                            itemStatusListInEC = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
                            searchType = CommonLogic.getNativeSelect(searchType, itemStatusListInEC);

                        } catch (SystemException ex) {
                            LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                        }
                    } else {
                        if (searchFieldInEC.equals(Constants.IFPTYPE)) {
                            try {
                                itemStatusListInEC.clear();
                                itemStatusListInEC = CommonLogic.getDropDownList("IFP_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusListInEC);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } 
                        }
                        if (searchFieldInEC.equals("PS Type")) {
                            try {
                                itemStatusListInEC.clear();
                                itemStatusListInEC = CommonLogic.getDropDownList("PS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusListInEC);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } 
                        }
                        if (searchFieldInEC.equals("RS Type")) {
                            try {
                                itemStatusListInEC.clear();
                                itemStatusListInEC = CommonLogic.getDropDownList("RS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusListInEC);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } 
                        }
                    }
                    searchType.setVisible(true);
                    ecsearchValue.setVisible(false);
                } else {
                    searchType.setVisible(false);
                    ecsearchValue.setVisible(true);
                }

            }
        });

    }

    public void loadComponentInfo(String compType, ComponentInfoDTO comInfoDto) {
        try {
            LOGGER.debug("Entered populate method");
            if (compType.equals("Item Family Plan")) {
                selectedContainer.removeAllItems();
                ifpIdEC.setValue(comInfoDto.getId());
                ifpNo.setValue(comInfoDto.getNumber());
                ifpName.setValue(comInfoDto.getName());
                ifpStatus.setValue(comInfoDto.getStatus());
                ifpStartDate.setValue(comInfoDto.getStartDate());
                ifpEndDate.setValue(comInfoDto.getEndDate());
            }
            if (compType.equals(Constants.PRICE_SCHEDULE)) {
                selectedContainer.removeAllItems();
                psIdEC.setValue(comInfoDto.getId());
                psNoEC.setValue(comInfoDto.getNumber());
                psNameEC.setValue(comInfoDto.getName());
                psStatusEC.setValue(comInfoDto.getStatus());
                psStartDateEC.setValue(comInfoDto.getStartDate());
                psEndDateEC.setValue(comInfoDto.getEndDate());
            } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                selectedContainer.removeAllItems();
                ecrebateScheduleId.setValue(comInfoDto.getId());
                ecstatus.setValue(comInfoDto.getStatus());
                ecrebateFrequency.setValue(comInfoDto.getFrequency());
                ecrsNumber.setValue(comInfoDto.getNumber());
                ecstartDate.setValue(comInfoDto.getStartDate());
                ecrarType.setValue(comInfoDto.getRarType());
                ecrsName.setValue(comInfoDto.getName());
                ecendDate.setValue(comInfoDto.getEndDate());
                ecbasis.setValue(comInfoDto.getBasis());
            }
            comInfoDto.setComponentValue(compType);

            List<ComponentInfoDTO> list = logic.getItemsFromRs(comInfoDto);
            selectedContainer.addAll(list);
            componentDetailsSelectedItem.setContainerDataSource(selectedContainer);
            loadTableHeader(compType);
            LOGGER.debug("Ended populate method");
        } catch (Exception ex) {
            LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
        }
    }

    public void configureContractDashboardResultsTable() {
        contractDashboardResultsTableInEC.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTableInEC.markAsDirty();
        contractDashboardResultsTableInEC.setWidth("630px");
        contractDashboardResultsTableInEC.setHeight("350px");
        contractDashboardResultsTableInEC.setPageLength(NumericConstants.FIVE);
        contractDashboardResultsTableInEC.setSelectable(true);
        contractDashboardResultsTableInEC.setMultiSelect(false);
        contractDashboardResultsTableInEC.setContainerDataSource(dashBoardTreeContainer1);
        contractDashboardResultsTableInEC.setVisibleColumns(Constants.getInstance().promoteTpContractDashboardTreeColumnsTransfer);
        contractDashboardResultsTableInEC.setColumnHeaders(Constants.getInstance().promoteTpContractDashboardTreeHeaders);
    }

    public void configureComponentDetailsTable() {

        componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSelectedItem.setWidth("890px");
        componentDetailsSelectedItem.setHeight("230px");
        componentDetailsSelectedItem.setPageLength(NumericConstants.FIVE);
        componentDetailsSelectedItem.setContainerDataSource(selectedContainer);
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().existingSelectedResultsColumns);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().existingSelectedResultsHeaders);
        componentDetailsSelectedItem.setSelectable(false);
    }

    public void configureContractComponentDetailsTable() {
        contractComponentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractComponentDetailsTable.setWidth("630px");
        contractComponentDetailsTable.setHeight("430px");
        contractComponentDetailsTable.setPageLength(NumericConstants.FIVE);
        contractComponentDetailsTable.setContainerDataSource(componentResultsContainer);
        contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsColumns);
        contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsHeaders);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //empty
    }

    /**
     * Excel Export Logic
     *
     * @param event
     */
    @UiHandler("excelBtn1")
    public void componentSelectionExport(Button.ClickEvent event) {
        try {
            contractExcelFlag = true;
            final int recordCount = componentResultsTable.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet(excelName1, componentResultsTable, recordCount);
            }
            transferCompPanelTableLayout.removeComponent(exportPeriodViewTable);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        } finally {
            contractExcelFlag = false;
        }
    }

    /**
     * Excel Export for Component Information
     *
     * @param event
     */
    @UiHandler("excelBtn2")
    public void componentSelItemsExport(Button.ClickEvent event) {
        configureExcelSelItemResultTable();
        try {
            infoExcelFlag = true;
            final int recordCount = componentDetailsSelectedItem.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet(excelName2, componentDetailsSelectedItem, recordCount);
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        } finally {
            infoExcelFlag = false;
        }
    }

    private void configureExcelSelItemResultTable() {
        excelResultBean2 = new ExtTreeContainer<>(ComponentInfoDTO.class);
        exportPeriodViewTable2 = new ExtCustomTable();
        transferCompPanelTableLayout.addComponent(exportPeriodViewTable2);
        exportPeriodViewTable2.setRefresh(BooleanConstant.getFalseFlag());
        exportPeriodViewTable2.setVisible(false);
        exportPeriodViewTable2.setContainerDataSource(excelResultBean2);
        exportPeriodViewTable2.setVisibleColumns(componentDetailsSelectedItem.getVisibleColumns());
        exportPeriodViewTable2.setColumnHeaders(componentDetailsSelectedItem.getColumnHeaders());
    }

    @UiHandler("componentType")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) {
        searchFieldDdlb = CommonLogic.loadExistingTabSearchField(searchFieldDdlb, componentTypeDdlbInEC);
        loadTableHeaders();
    }

    private void loadTableHeaders() {
        String compType = String.valueOf(componentTypeDdlbInEC.getValue());
        if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adCfpIfpResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adCfpIfpResultsHeaders);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adCfpIfpResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adCfpIfpResultsHeaders);
            for (Object obj : componentResultsTable.getVisibleColumns()) {
                componentResultsTable.setColumnWidth(obj, NumericConstants.ONE_NINE_FIVE);
            }
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adPsResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adPsResultsHeaders);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().rsResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().rsResultsHeaders);
        }
    }

    /**
     * Search Button Logic
     *
     * @param event
     */
    @UiHandler("searchBtn1")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");
        transferCompContainer.removeAllItems();
        String searchField = String.valueOf(searchFieldDdlb.getValue());
        if (searchField.contains(Constants.STATUS_FIELD) || searchField.contains("Type")) {
            newDiscountTabDto.setSearchFieldValue(String.valueOf(searchType.getValue()));
        } else {
            newDiscountTabDto.setSearchFieldValue(ecsearchValue.getValue());
        }
        newDiscountTabDto.setSearchField(String.valueOf(searchFieldDdlb.getValue()));
        newDiscountTabDto.setComponentValue(String.valueOf(componentTypeDdlbInEC.getValue()));
        if (!availableTableLogic.loadSetData(newDiscountTabDto)) {
            AbstractNotificationUtils.getErrorNotification("No Records",
                    "There were no records matching the search criteria.  Please try again.");
        }

        LOGGER.debug("Ending search method");
    }

    @UiHandler("addToTreeBtn1")
    public void addToTreeLogic(Button.ClickEvent event) {
        try {
            addToContDashboardTree();
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    /**
     * Adding a Tree Node to Contract Dashboard
     *
     */
    public void addToContDashboardTree() {
        if (contractDashboardResultsTableInEC.getItemIds().isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Add Contract Header Node");
        } else {
            final Object root = contractDashboardResultsTableInEC.getValue();
            if (root != null) {
                String levelNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.parseInt(levelNo);
                String level = String.valueOf(componentTypeDdlbInEC.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Component");
                }
                final Object root1 = componentResultsTable.getValue();
                boolean flag = false;
                if (root1 != null) {
                    flag = true;
                }
                if (!flag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one record at Contract Component Results section");
                }

                if (level.equals(Constants.ITEM_FAMILY_PLAN)) {
                    if (NumericConstants.TWO - levelNumber == NumericConstants.ONE) {
                        final Object root2 = componentResultsTable.getValue();
                        String ifpId = String.valueOf(transferCompContainer.getContainerProperty(root2, "ifpSystemId").getValue());
                        ComponentInfoDTO comInfoDto = (ComponentInfoDTO) componentResultsTable.getValue();
                        final Object rootId = contractDashboardResultsTableInEC.addItem();
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(ifpId);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getIfpSystemId());
                        contractDashboardResultsTableInEC.addItem(rootId);
                        contractDashboardResultsTableInEC.setParent(rootId, root);
                        contractDashboardResultsTableInEC.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTableInEC.setCollapsed(root, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (NumericConstants.THREE - levelNumber == 1) {
                        ComponentInfoDTO comInfoDto = (ComponentInfoDTO) componentResultsTable.getValue();
                        final Object rootId = contractDashboardResultsTableInEC.addItem();
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(comInfoDto.getPsSid());
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getPsSid());
                        contractDashboardResultsTableInEC.addItem(rootId);
                        contractDashboardResultsTableInEC.setParent(rootId, root);
                        contractDashboardResultsTableInEC.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTableInEC.setCollapsed(root, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (NumericConstants.FOUR - levelNumber == 1) {
                        ComponentInfoDTO comInfoDto = (ComponentInfoDTO) componentResultsTable.getValue();
                        final Object rootId = contractDashboardResultsTableInEC.addItem();
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(comInfoDto.getRsSid());
                        contractDashboardResultsTableInEC.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getRsSid());
                        contractDashboardResultsTableInEC.addItem(rootId);
                        contractDashboardResultsTableInEC.setParent(rootId, root);
                        contractDashboardResultsTableInEC.setChildrenAllowed(rootId, false);
                        contractDashboardResultsTableInEC.setCollapsed(root, false);

                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        }
    }

    /**
     * Remove Button Logic
     *
     * @param event
     */
    @UiHandler("removeBtn2")
    public void removeLogic(Button.ClickEvent event) {
        Object root = contractDashboardResultsTableInEC.getValue();
        if (root != null) {
            Boolean flag = contractDashboardResultsTableInEC.hasChildren(root);
            if (!flag) {
                contractDashboardResultsTableInEC.removeItem(root);
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Remove Children");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node to Remove at Dashboard");
        }
    }

    /**
     * Populate Button Logic
     *
     * @param event
     */
    @UiHandler("populateBtn2")
    public void populateDetailsLogic(Button.ClickEvent event) {
        Object root = contractDashboardResultsTableInEC.getValue();
        if (root != null) {
            String level = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ONE)) {
                String companySysId = session.getCompanyMasterSid();
                String componentQueryStr = queryUtils.getCompanyInformation(companySysId);
                List componentsList = HelperTableLocalServiceUtil.executeSelectQuery(componentQueryStr);
                if (componentsList != null && !componentsList.isEmpty()) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> companyList = new ArrayList<>();
                    for (int i = 0; i < componentsList.size(); i++) {
                        ComponentInfoDTO compInfoDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentsList.get(i);
                        compInfoDTO.setCompanyNo(String.valueOf(obje[1]));
                        compInfoDTO.setCompanyName(String.valueOf(obje[NumericConstants.TWO]));
                        compInfoDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.FIVE]));
                        if (obje[NumericConstants.THREE] != null) {
                            String date = df.format(obje[NumericConstants.THREE]);
                            compInfoDTO.setCompanyStartDate(date);
                        } else {
                            compInfoDTO.setCompanyStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.FOUR] != null) {
                            String date = df.format(obje[NumericConstants.FOUR]);
                            compInfoDTO.setCompanyEndDate(date);
                        } else {
                            compInfoDTO.setCompanyEndDate(Constants.EMPTY);
                        }
                        companyList.add(compInfoDTO);
                    }
                    componentResultsContainer.addAll(companyList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().ccComponentDetailsColumns);
                    contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().ccComponentDetailsHeaders);
                    cfpDetailsGridEC.setVisible(true);
                    ifpDetailsGridEC.setVisible(false);
                    psDetailsGridEC.setVisible(false);
                    rsDetailsGridEC.setVisible(false);
                    String detailsNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                    cfpDetailsNoEC.setValue(detailsNo);
                    String detailsName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    cfpDetailsNameEC.setValue(detailsName);
                    cfpDetailsNoEC.setEnabled(false);
                    cfpDetailsNameEC.setEnabled(false);
                }

            } else if (level.equals(Constants.TWO) || level.equals(Constants.THREE) || level.equals(Constants.FOUR)) {
                String ifpId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                String componentLevelQuery = Constants.EMPTY;
                if (level.equals(Constants.TWO)) {
                    componentLevelQuery = queryUtils.getItemMasterDetails(ifpId);
                } else if (level.equals(Constants.THREE)) {
                    componentLevelQuery = queryUtils.getPSDetails(ifpId);
                } else if (level.equals(Constants.FOUR)) {
                    componentLevelQuery = queryUtils.getRSDetails(ifpId);
                }
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentLevelQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> itemList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemCompInfoDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemCompInfoDTO.setItemNo(String.valueOf(obje[0]));
                        itemCompInfoDTO.setItemName(String.valueOf(obje[1]));
                        itemCompInfoDTO.setTherapyClass(String.valueOf(obje[NumericConstants.TWO]));
                        itemCompInfoDTO.setBrand(String.valueOf(obje[NumericConstants.THREE]));
                        itemCompInfoDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                        if (obje[NumericConstants.FIVE] != null) {
                            String date = df.format(obje[NumericConstants.FIVE]);
                            itemCompInfoDTO.setIfpStartDate(date);
                        } else {
                            itemCompInfoDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.SIX] != null) {
                            String date = df.format(obje[NumericConstants.SIX]);
                            itemCompInfoDTO.setIfpEndDate(date);
                        } else {
                            itemCompInfoDTO.setIfpEndDate(Constants.EMPTY);
                        }

                        itemList.add(itemCompInfoDTO);
                    }
                    componentResultsContainer.addAll(itemList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsItemColumns);
                    contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsItemHeaders);
                    if (level.equals(Constants.TWO)) {
                        cfpDetailsGridEC.setVisible(false);
                        ifpDetailsGridEC.setVisible(true);
                        psDetailsGridEC.setVisible(false);
                        rsDetailsGridEC.setVisible(false);
                        String detailsNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                        ifpDetailsNoEC.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        ifpDetailsNameEC.setValue(detailsName);
                        ifpDetailsNoEC.setEnabled(false);
                        ifpDetailsNameEC.setEnabled(false);
                    } else if (level.equals(Constants.THREE)) {
                        cfpDetailsGridEC.setVisible(false);
                        ifpDetailsGridEC.setVisible(false);
                        psDetailsGridEC.setVisible(true);
                        rsDetailsGridEC.setVisible(false);
                        String detailsNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                        psDetailsNoEC.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        psDetailsNameEC.setValue(detailsName);
                        psDetailsNoEC.setEnabled(false);
                        psDetailsNameEC.setEnabled(false);
                    } else if (level.equals(Constants.FOUR)) {
                        cfpDetailsGridEC.setVisible(false);
                        ifpDetailsGridEC.setVisible(false);
                        psDetailsGridEC.setVisible(false);
                        rsDetailsGridEC.setVisible(true);
                        String detailsNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                        rsDetailsNoEC.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        rsDetailsNameEC.setValue(detailsName);
                        rsDetailsNoEC.setEnabled(false);
                        rsDetailsNameEC.setEnabled(false);
                    }
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node");
        }
    }

    public List<Integer> saveExistingContract() throws  PortalException, ParseException {
        int contractMasterSid = 0;
        List<Integer> returnList = new ArrayList<>();
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            Collection<?> treeItem = dashBoardTreeContainer1.getItemIds(0, dashBoardTreeContainer1.size());

            for (Object item : treeItem) {
                String level = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    String contractId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                    String contractNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                    String contractName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                    int contractType = Integer.parseInt(String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.MARKET_TYPE).getValue()));
                    int contStatus = 1;
                    ContractMaster contractMasterExis;
                    contractMasterExis = ContractMasterLocalServiceUtil.createContractMaster(0);
                    contractMasterExis.setContractId(contractId);
                    contractMasterExis.setContractNo(contractNo);
                    contractMasterExis.setContractName(contractName);
                    contractMasterExis.setContractType(contractType);
                    contractMasterExis.setProcessStatus(true);
                    contractMasterExis.setSource("BPI");
                    contractMasterExis.setContractStatus(contStatus);
                    contractMasterExis.setCreatedBy(Integer.parseInt(userId));
                    contractMasterExis.setStartDate(new Date());
                    contractMasterExis.setInboundStatus("A");
                    contractMasterExis.setCreatedDate(new Date());
                    contractMasterExis.setModifiedDate(new Date());
                    contractMasterExis = ContractMasterLocalServiceUtil.addContractMaster(contractMasterExis);
                    contractMasterSid = contractMasterExis.getContractMasterSid();
                    session.setContractMasterSid(String.valueOf(contractMasterSid));
                    returnList.add(contractMasterSid);
                    String aliasType = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, "type").getValue());
                    String aliasNumber = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, "number").getValue());
                    ContractAliasMaster camInExis = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    camInExis.setContractAliasNo(aliasNumber);
                    camInExis.setContractAliasType(aliasType != null && !Constants.NULL.equals(aliasType) && !StringUtils.EMPTY.equals(aliasType) ? Integer.parseInt(aliasType) : 0);
                    camInExis.setStartDate(new Date());
                    camInExis.setModifiedDate(new Date());
                    camInExis.setCreatedBy(1);
                    camInExis.setCreatedDate(new Date());
                    camInExis.setSource("BPI");
                    camInExis.setInboundStatus("A");
                    camInExis.setContractMasterSid(contractMasterSid);
                    ContractAliasMaster camMaster = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(camInExis);
                    LOGGER.debug("CAM1 {} " , camMaster.getContractAliasMasterSid());

                } else if (level.equals(Constants.ONE)) {

                    CfpModel cfpModelInExis;
                    cfpModelInExis = CfpModelLocalServiceUtil.createCfpModel(0);
                    String cfpName = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.CFP_NAME).getValue());
                    String cfpId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, "id").getValue());
                    String cfpNo = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, "cfpNo").getValue());
                    String cfpStatus = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, "cfpStatus").getValue());
                    String cfpEndDate = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.CFP_END_DATE).getValue());
                    String cfpStartDate = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.CFP_START_DATE).getValue());
                    Date sdate = simpleDateFormat.parse(cfpStartDate);
                    Date edate = simpleDateFormat.parse(cfpEndDate);
                    cfpModelInExis.setCfpName(cfpName);
                    cfpModelInExis.setCfpNo(cfpNo);
                    cfpModelInExis.setCfpStatus(Integer.parseInt(cfpStatus));
                    cfpModelInExis.setCfpId(cfpId);
                    cfpModelInExis.setCfpStartDate(sdate);
                    cfpModelInExis.setCfpEndDate(edate);
                    cfpModelInExis.setSource("GCM");
                    cfpModelInExis.setInboundStatus("A");
                    cfpModelInExis.setCreatedDate(new Date());
                    cfpModelInExis.setModifiedDate(new Date());
                    cfpModelInExis.setRecordLockStatus(false);
                    cfpModelInExis.setCreatedBy(Integer.parseInt(userId));
                    cfpModelInExis.setModifiedBy(Integer.parseInt(userId));
                    cfpModelInExis = CfpModelLocalServiceUtil.addCfpModel(cfpModelInExis);
                    String companySid = session.getCompanyMasterSid();

                    returnList.add(Integer.valueOf(companySid));
                    CfpDetails cfpDetailsExis;
                    cfpDetailsExis = CfpDetailsLocalServiceUtil.createCfpDetails(0);
                    cfpDetailsExis.setCfpModelSid(cfpModelInExis.getCfpModelSid());
                    cfpDetailsExis.setCompanyMasterSid(Integer.parseInt(companySid));
                    cfpDetailsExis.setCompanyStartDate(new Date());
                    cfpDetailsExis.setInboundStatus("A");
                    cfpDetailsExis.setSource("BPI");
                    cfpDetailsExis.setRecordLockStatus(false);
                    cfpDetailsExis.setCreatedDate(new Date());
                    cfpDetailsExis.setModifiedDate(new Date());
                    cfpDetailsExis.setCreatedBy(Integer.parseInt(userId));
                    cfpDetailsExis.setModifiedBy(Integer.parseInt(userId));
                    CfpDetailsLocalServiceUtil.addCfpDetails(cfpDetailsExis);

                    CfpContract cfpContractExis;
                    cfpContractExis = CfpContractLocalServiceUtil.createCfpContract(0);
                    cfpContractExis.setCfpModelSid(cfpModelInExis.getCfpModelSid());
                    cfpContractExis.setCfpName(cfpModelInExis.getCfpName());
                    cfpContractExis.setCfpStatus(1);
                    cfpContractExis.setCfpStartDate(new Date());
                    cfpContractExis.setContractMasterSid(contractMasterSid);
                    cfpContractExis.setInboundStatus("A");
                    cfpContractExis.setSource("GCM");
                    cfpContractExis.setRecordLockStatus(false);
                    cfpContractExis.setCreatedDate(new Date());
                    cfpContractExis.setModifiedDate(new Date());
                    cfpContractExis.setCreatedBy(Integer.parseInt(userId));
                    cfpContractExis.setModifiedBy(Integer.parseInt(userId));
                    cfpContractExis = CfpContractLocalServiceUtil.addCfpContract(cfpContractExis);
                    String cfpContractSid = String.valueOf(cfpContractExis.getCfpContractSid());
                    contractDashboardResultsTableInEC.getContainerProperty(item, Constants.HIDDEN_ID).setValue(cfpContractSid);
                    CfpContractDetails cfpcontractDetails;
                    cfpcontractDetails = CfpContractDetailsLocalServiceUtil.createCfpContractDetails(0);
                    cfpcontractDetails.setCfpContractSid(cfpContractExis.getCfpContractSid());
                    cfpcontractDetails.setCompanyMasterSid(Integer.parseInt(session.getCompanyMasterSid()));
                    cfpcontractDetails.setCompanyStartDate(cfpModelInExis.getCfpStartDate());
                    cfpcontractDetails.setCompanyEndDate(cfpModelInExis.getCfpEndDate());
                    cfpcontractDetails.setCfpContractAttachedStatus(cfpModelInExis.getCfpStatus());
                    cfpcontractDetails.setCreatedDate(new Date());
                    cfpcontractDetails.setModifiedDate(new Date());
                    cfpcontractDetails.setCreatedBy(Integer.parseInt(userId));
                    cfpcontractDetails.setModifiedBy(Integer.parseInt(userId));
                    cfpcontractDetails.setInboundStatus("A");
                    cfpcontractDetails.setRecordLockStatus(false);
                    CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpcontractDetails);
                } else if (level.equals(Constants.TWO)) {
                    String ifpModelId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTableInEC.getParent(item);
                    String cfpContractSId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    IfpModel ifpModelExis;
                    ifpModelExis = IfpModelLocalServiceUtil.getIfpModel(Integer.parseInt(ifpModelId));
                    IfpContract ifpContractExis;
                    ifpContractExis = IfpContractLocalServiceUtil.createIfpContract(0);
                    ifpContractExis.setIfpModelSid(Integer.parseInt(ifpModelId));
                    ifpContractExis.setIfpName(ifpModelExis.getIfpName());
                    ifpContractExis.setIfpType(ifpModelExis.getIfpType());
                    ifpContractExis.setIfpCategory(ifpModelExis.getIfpCategory());
                    ifpContractExis.setIfpStatus(ifpModelExis.getIfpStatus());
                    ifpContractExis.setIfpStartDate(ifpModelExis.getIfpStartDate());
                    ifpContractExis.setIfpEndDate(ifpModelExis.getIfpEndDate());
                    ifpContractExis.setCfpContractSid(cfpContractSId);
                    ifpContractExis.setContractMasterSid(contractMasterSid);
                    ifpContractExis.setInboundStatus("A");
                    ifpContractExis.setRecordLockStatus(false);
                    ifpContractExis.setSource("GCM");
                    ifpContractExis.setCreatedBy(Integer.parseInt(userId));
                    ifpContractExis.setModifiedBy(Integer.parseInt(userId));
                    ifpContractExis.setCreatedDate(new Date());
                    ifpContractExis.setModifiedDate(new Date());
                    ifpContractExis = IfpContractLocalServiceUtil.addIfpContract(ifpContractExis);
                    contractDashboardResultsTableInEC.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(ifpContractExis.getIfpContractSid()));

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(ifpContractExis.getIfpContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(ifpModelId);
                    input.add(simpleDateFormat.format(ifpModelExis.getIfpStartDate()));
                    input.add(ifpModelExis.getIfpEndDate() == null ? null : simpleDateFormat.format(ifpModelExis.getIfpEndDate()));
                    IfpContractDetailsImpl.saveIfpDetailsAttached(input);
                } else if (level.equals(Constants.THREE)) {

                    String psModelId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTableInEC.getParent(item);
                    String ifpSystemId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTableInEC.getParent(parentItem);
                    String cfpSystemId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());
                    PsContract psContractExis;
                    psContractExis = PsContractLocalServiceUtil.createPsContract(0);
                    psContractExis.setPsModelSid(Integer.parseInt(psModelId));
                    PsModel psModelExis;
                    psModelExis = PsModelLocalServiceUtil.getPsModel(Integer.parseInt(psModelId));
                    psContractExis.setPsName(psModelExis.getPsName());
                    psContractExis.setPsType(psModelExis.getPsType());
                    psContractExis.setPsCategory(psModelExis.getPsCategory());
                    psContractExis.setPsStatus(psModelExis.getPsStatus());
                    psContractExis.setPsStartDate(psModelExis.getPsStartDate());
                    psContractExis.setPsEndDate(psModelExis.getPsEndDate());
                    psContractExis.setContractMasterSid(contractMasterSid);
                    psContractExis.setCfpContractSid(cfpSystemId);
                    psContractExis.setIfpContractSid(ifpSystemId);
                    psContractExis.setInboundStatus("A");
                    psContractExis.setRecordLockStatus(false);
                    psContractExis.setSource("GCM");
                    psContractExis.setCreatedBy(Integer.parseInt(userId));
                    psContractExis.setModifiedBy(Integer.parseInt(userId));
                    psContractExis.setCreatedDate(new Date());
                    psContractExis.setModifiedDate(new Date());
                    psContractExis = PsContractLocalServiceUtil.addPsContract(psContractExis);

                    contractDashboardResultsTableInEC.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(psContractExis.getPsContractSid()));
                    List<Object> psInput = new ArrayList<>(NumericConstants.EIGHT);
                    psInput.add(psContractExis.getPsContractSid());
                    psInput.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    psInput.add(simpleDateFormat.format(new Date()));
                    psInput.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    psInput.add(simpleDateFormat.format(new Date()));
                    psInput.add(psModelId);
                    psInput.add(simpleDateFormat.format(psModelExis.getPsStartDate()));
                    psInput.add(psModelExis.getPsEndDate() == null ? null : simpleDateFormat.format(psModelExis.getPsEndDate()));
                    PsContractDetailsImpl.savePsDetailsAttached(psInput);

                } else if (level.equals(Constants.FOUR)) {
                    String rsModelId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object psParentItem = contractDashboardResultsTableInEC.getParent(item);
                    String contractPSId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(psParentItem, Constants.HIDDEN_ID).getValue());
                    Object parentIFPItem = contractDashboardResultsTableInEC.getParent(psParentItem);
                    String contractIFPId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(parentIFPItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTableInEC.getParent(parentIFPItem);
                    String contractCFPId = String.valueOf(contractDashboardResultsTableInEC.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    RsContract rsContractExis;
                    rsContractExis = RsContractLocalServiceUtil.createRsContract(0);
                    rsContractExis.setRsModelSid(Integer.parseInt(rsModelId));
                    RsModel rsModelExis;
                    rsModelExis = RsModelLocalServiceUtil.getRsModel(Integer.parseInt(rsModelId));
                    rsContractExis.setRsId(rsModelExis.getRsId());
                    rsContractExis.setRsNo(rsModelExis.getRsNo());
                    rsContractExis.setRsType(rsModelExis.getRsType());
                    rsContractExis.setRebateProgramType(rsModelExis.getRebateProgramType());
                    rsContractExis.setRsCategory(rsModelExis.getRsCategory());
                    rsContractExis.setRsStatus(rsModelExis.getRsStatus());
                    rsContractExis.setRsDesignation(String.valueOf(rsModelExis.getRsDesignation()));
                    rsContractExis.setRsStartDate(rsModelExis.getRsStartDate());
                    rsContractExis.setRsEndDate(rsModelExis.getRsEndDate());
                    rsContractExis.setRsTradeClass(rsModelExis.getRsTradeClass());
                    rsContractExis.setCfpContractSid(contractCFPId);
                    rsContractExis.setIfpContractSid(contractIFPId);
                    rsContractExis.setPsContractSid(contractPSId);
                    rsContractExis.setContractMasterSid(contractMasterSid);
                    rsContractExis.setInboundStatus("A");
                    rsContractExis.setRecordLockStatus(false);
                    rsContractExis.setSource("GCM");
                    rsContractExis.setCreatedBy(Integer.parseInt(userId));
                    rsContractExis.setModifiedBy(Integer.parseInt(userId));
                    rsContractExis.setCreatedDate(new Date());
                    rsContractExis.setModifiedDate(new Date());
                    rsContractExis = RsContractLocalServiceUtil.addRsContract(rsContractExis);

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(rsContractExis.getRsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(rsModelId);
                    input.add(rsModelExis.getRsStartDate());
                    input.add(rsModelExis.getRsEndDate() == null ? null : rsModelExis.getRsEndDate());
                    RsContractDetailsImpl.saveRsDetailsAttached(input);

                }
            }
            LOGGER.debug("TP Promoted Successfully as Contract Holder");
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return returnList;
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws   NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), count, this, UI.getCurrent(), moduleName.replace(' ', '_').toUpperCase(Locale.ENGLISH));

    }

    public void createWorkSheetContent(final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    List<ComponentInfoDTO> searchList = logic.getRebateSchedule(newDiscountTabDto);
                    ExcelExportforBB.createFileContent(componentResultsTable.getVisibleColumns(), searchList, printWriter);
                } else if (infoExcelFlag) {
                    List<ComponentInfoDTO> searchList = selectedContainer.getItemIds();
                    ExcelExportforBB.createFileContent(componentDetailsSelectedItem.getVisibleColumns(), searchList, printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void loadTableHeader(String compType) {
        if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
            componentInformationLayout1.setVisible(false);
            componentInfoIfpLayout.setVisible(true);
            componentInfoPsLayout.setVisible(false);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsColumnsIfpNew);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsHeadersIfpNew);
        } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
            componentInformationLayout1.setVisible(true);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(false);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsRsColumn);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsRsHeader);

        } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
            componentInformationLayout1.setVisible(false);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(true);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsPsColumn);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsPsHeader);

        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Promote Customer", "TransferContractTab");
            searchBtn1.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn1", functionHM));
            removeBtn2.setVisible(CommonLogic.isButtonVisibleAccess("removeBtn2", functionHM));
            populateBtn2.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn2", functionHM));
            addToTreeBtn1.setVisible(CommonLogic.isButtonVisibleAccess("addToTreeBtn1", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
