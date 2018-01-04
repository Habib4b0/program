/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import static com.stpl.app.gcm.discount.logic.DiscountLogic.DBDate;
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
import java.util.logging.Level;
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
    private TreeTable contractDashboardResultsTable = new TreeTable();
    private ExtFilterTable contractComponentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> transferCompContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> selectedContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    @UiField("componentType")
    private ComboBox componentTypeDdlb;
    @UiField("searchField")
    private ComboBox searchFieldDdlb;
    @UiField("rebateScheduleId")
    private TextField rebateScheduleId;
    @UiField("status")
    private TextField status;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("rsNumber")
    private TextField rsNumber;
    @UiField("startDate")
    private TextField startDate;
    @UiField("rarType")
    private TextField rarType;
    @UiField("rsType")
    private TextField rsType;
    @UiField("rsName")
    private TextField rsName;
    @UiField("endDate")
    private TextField endDate;
    @UiField("basis")
    private TextField basis;
    @UiField("excelBtn1")
    private Button excelBtn1;
    @UiField("excelBtn2")
    private Button excelBtn2;
    @UiField("searchValue")
    private TextField searchValue;
    @UiField("searchBtn1")
    private Button searchBtn1;
    @UiField("addToTreeBtn1")
    private Button addToTreeBtn1;
    @UiField("removeBtn2")
    private Button removeBtn2;
    @UiField("populateBtn2")
    private Button populateBtn2;
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
    @UiField("ifpId")
    private TextField ifpId;
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
    private TextField psId;
    @UiField("psNo")
    private TextField psNo;
    @UiField("psName")
    private TextField psName;
    @UiField("psStatus")
    private TextField psStatus;
    @UiField("psStartDate")
    private TextField psStartDate;
    @UiField("psEndDate")
    private TextField psEndDate;
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
    private ExtCustomTable exportPeriodViewTable2 = new ExtCustomTable();;
    private String excelName1 = "Component Results";
    private String excelName2 = "Component Details";
    private PromoteTPLogic logic = new PromoteTPLogic();
    private QueryUtils queryUtils = new QueryUtils();
    private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    private List<HelperDTO> itemStatusList = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private Boolean contractExcelFlag = false;
    private Boolean infoExcelFlag = false;
    private StplSecurity stplSecurity = new StplSecurity();

    public ExistingComponents(SessionDTO session, TreeTable contractDashBoardTable) {
        try {
            this.session = session;
            this.contractDashboardResultsTable = contractDashBoardTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpExistingComponents.xml"), this));
            configureFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected void configureFields() {
        try {

            componentTypeDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            componentTypeDdlb.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant());
            componentTypeDdlb.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE.getConstant());
            componentTypeDdlb.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE.getConstant());
            componentTypeDdlb.setNullSelectionAllowed(true);
            componentTypeDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());

            transferCompPanelTableLayout.addComponent(componentResultsTable);
            configureTransferCompTable();
            HorizontalLayout controls = availableTableLogic.createControls();
            transferCompPanelTableLayout.addComponent(controls);
            contractDashboardResultsTableLayout.addComponent(contractDashboardResultsTable);
            configureContractDashboardResultsTable();
            componentDetailsTableLayout.addComponent(componentDetailsSelectedItem);
            configureComponentDetailsTable();
            HorizontalLayout controls1 = componentDetailsSelectedItem.createControls();
            componentDetailsTableLayout.addComponent(controls1);
            contractComponentDetailsTableLayout.addComponent(contractComponentDetailsTable);
            configureContractComponentDetailsTable();
            excelBtn1.setIcon(excelExportImage);
            excelBtn2.setIcon(excelExportImage);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(false);
            searchType.setVisible(false);
            disableComponentInfoFields();

            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);
            cfpDetailsNo.setEnabled(false);
            cfpDetailsName.setEnabled(false);

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * Disable Component Information Fields
     *
     */
    public void disableComponentInfoFields() {
        rebateScheduleId.setEnabled(false);
        status.setEnabled(false);
        rebateFrequency.setEnabled(false);
        rsNumber.setEnabled(false);
        startDate.setEnabled(false);
        rarType.setEnabled(false);
        rsType.setEnabled(false);
        rsName.setEnabled(false);
        endDate.setEnabled(false);
        basis.setEnabled(false);
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

        componentTypeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String compType = String.valueOf(componentTypeDdlb.getValue());
                loadTableHeader(compType);
            }
        });

        componentResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                ComponentInfoDTO comInfoDto = (ComponentInfoDTO) event.getItemId();
                String compType = String.valueOf(componentTypeDdlb.getValue());
                if (!SELECT_ONE.getConstant().equals(compType)) {
                    loadComponentInfo(compType, comInfoDto);
                }
            }
        });

        searchFieldDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String searchField = String.valueOf(searchFieldDdlb.getValue());
                if (searchField.contains(Constants.STATUS_FIELD) || searchField.contains("Type")) {
                    if (searchField.contains(Constants.STATUS_FIELD)) {
                        try {
                            itemStatusList.clear();
                            itemStatusList = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
                            searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);

                        } catch (SystemException ex) {
                            LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                        } catch (Exception ex) {
                            LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                        }
                    } else {
                        if (searchField.equals(Constants.IFPTYPE)) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("IFP_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } catch (Exception ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            }
                        }
                        if (searchField.equals("PS Type")) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("PS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } catch (Exception ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            }
                        }
                        if (searchField.equals("RS Type")) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("RS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            } catch (Exception ex) {
                                LoggerFactory.getLogger(ExistingComponents.class.getName()).error("", ex);
                            }
                        }
                    }
                    searchType.setVisible(true);
                    searchValue.setVisible(false);
                } else {
                    searchType.setVisible(false);
                    searchValue.setVisible(true);
                }

            }
        });

    }

    public void loadComponentInfo(String compType, ComponentInfoDTO comInfoDto) {
        try {
            LOGGER.debug("Entered populate method");
            if (compType.equals("Item Family Plan")) {
                selectedContainer.removeAllItems();
                ifpId.setValue(comInfoDto.getId());
                ifpNo.setValue(comInfoDto.getNumber());
                ifpName.setValue(comInfoDto.getName());
                ifpStatus.setValue(comInfoDto.getStatus());
                ifpStartDate.setValue(comInfoDto.getStartDate());
                ifpEndDate.setValue(comInfoDto.getEndDate());
            }
            if (compType.equals(Constants.PRICE_SCHEDULE)) {
                selectedContainer.removeAllItems();
                psId.setValue(comInfoDto.getId());
                psNo.setValue(comInfoDto.getNumber());
                psName.setValue(comInfoDto.getName());
                psStatus.setValue(comInfoDto.getStatus());
                psStartDate.setValue(comInfoDto.getStartDate());
                psEndDate.setValue(comInfoDto.getEndDate());
            } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                selectedContainer.removeAllItems();
                rebateScheduleId.setValue(comInfoDto.getId());
                status.setValue(comInfoDto.getStatus());
                rebateFrequency.setValue(comInfoDto.getFrequency());
                rsNumber.setValue(comInfoDto.getNumber());
                startDate.setValue(comInfoDto.getStartDate());
                rarType.setValue(comInfoDto.getRarType());
                rsName.setValue(comInfoDto.getName());
                endDate.setValue(comInfoDto.getEndDate());
                basis.setValue(comInfoDto.getBasis());
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
        contractDashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTable.markAsDirty();
        contractDashboardResultsTable.setWidth("630px");
        contractDashboardResultsTable.setHeight("350px");
        contractDashboardResultsTable.setPageLength(NumericConstants.FIVE);
        contractDashboardResultsTable.setSelectable(true);
        contractDashboardResultsTable.setMultiSelect(false);
        contractDashboardResultsTable.setContainerDataSource(dashBoardTreeContainer1);
        contractDashboardResultsTable.setVisibleColumns(Constants.getInstance().promoteTpContractDashboardTreeColumnsTransfer);
        contractDashboardResultsTable.setColumnHeaders(Constants.getInstance().promoteTpContractDashboardTreeHeaders);
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
        exportPeriodViewTable2.setRefresh(Boolean.FALSE);
        exportPeriodViewTable2.setVisible(false);
        exportPeriodViewTable2.setContainerDataSource(excelResultBean2);
        exportPeriodViewTable2.setVisibleColumns(componentDetailsSelectedItem.getVisibleColumns());
        exportPeriodViewTable2.setColumnHeaders(componentDetailsSelectedItem.getColumnHeaders());
    }

    @UiHandler("componentType")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) {
        searchFieldDdlb = CommonLogic.loadExistingTabSearchField(searchFieldDdlb, componentTypeDdlb);
        loadTableHeaders();
    }

    private void loadTableHeaders() {
        String compType = String.valueOf(componentTypeDdlb.getValue());
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
            newDiscountTabDto.setSearchFieldValue(searchValue.getValue());
        }
        newDiscountTabDto.setSearchField(String.valueOf(searchFieldDdlb.getValue()));
        newDiscountTabDto.setComponentValue(String.valueOf(componentTypeDdlb.getValue()));
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
        if (contractDashboardResultsTable.getItemIds().isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Add Contract Header Node");
        } else {
            final Object root = contractDashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.valueOf(levelNo);
                String level = String.valueOf(componentTypeDdlb.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Component");
                }
                final Object root1 = componentResultsTable.getValue();
                Boolean flag = false;
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
                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(ifpId);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getIfpSystemId());
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTable.setCollapsed(root, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.CORRECT_NODE_ALERT);
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (NumericConstants.THREE - levelNumber == 1) {
                        boolean psFlag = false;
                        ComponentInfoDTO comInfoDto = (ComponentInfoDTO) componentResultsTable.getValue();
                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(comInfoDto.getPsSid());
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getPsSid());
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTable.setCollapsed(root, false);
                        if (psFlag) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "PS does not associate with  IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.CORRECT_NODE_ALERT);
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (NumericConstants.FOUR - levelNumber == 1) {
                        ComponentInfoDTO comInfoDto = (ComponentInfoDTO) componentResultsTable.getValue();
                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(comInfoDto.getId()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(comInfoDto.getNumber()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(comInfoDto.getName()));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(comInfoDto.getRsSid());
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(comInfoDto.getRsSid());
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, false);
                        contractDashboardResultsTable.setCollapsed(root, false);

                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.CORRECT_NODE_ALERT);
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
        Object root = contractDashboardResultsTable.getValue();
        if (root != null) {
            Boolean flag = contractDashboardResultsTable.hasChildren(root);
            if (!flag) {
                contractDashboardResultsTable.removeItem(root);
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
        Object root = contractDashboardResultsTable.getValue();
        if (root != null) {
            String level = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ONE)) {
                String companySid = session.getCompanyMasterSid();
                String componentQuery = queryUtils.getCompanyInformation(companySid);
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> companyList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO companyDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        companyDTO.setCompanyNo(String.valueOf(obje[1]));
                        companyDTO.setCompanyName(String.valueOf(obje[NumericConstants.TWO]));
                        companyDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.FIVE]));
                        if (obje[NumericConstants.THREE] != null) {
                            String date = df.format(obje[NumericConstants.THREE]);
                            companyDTO.setCompanyStartDate(date);
                        } else {
                            companyDTO.setCompanyStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.FOUR] != null) {
                            String date = df.format(obje[NumericConstants.FOUR]);
                            companyDTO.setCompanyEndDate(date);
                        } else {
                            companyDTO.setCompanyEndDate(Constants.EMPTY);
                        }
                        companyList.add(companyDTO);
                    }
                    componentResultsContainer.addAll(companyList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().ccComponentDetailsColumns);
                    contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().ccComponentDetailsHeaders);
                    cfpDetailsGrid.setVisible(true);
                    ifpDetailsGrid.setVisible(false);
                    psDetailsGrid.setVisible(false);
                    rsDetailsGrid.setVisible(false);
                    String detailsNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                    cfpDetailsNo.setValue(detailsNo);
                    String detailsName = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                    cfpDetailsName.setValue(detailsName);
                    cfpDetailsNo.setEnabled(false);
                    cfpDetailsName.setEnabled(false);
                }

            } else if (level.equals(Constants.TWO) || level.equals(Constants.THREE) || level.equals(Constants.FOUR)) {
                String ifpId = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                String componentQuery = Constants.EMPTY;
                if (level.equals(Constants.TWO)) {
                    componentQuery = queryUtils.getItemMasterDetails(ifpId);
                } else if (level.equals(Constants.THREE)) {
                    componentQuery = queryUtils.getPSDetails(ifpId);
                } else if (level.equals(Constants.FOUR)) {
                    componentQuery = queryUtils.getRSDetails(ifpId);
                }
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> itemList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(String.valueOf(obje[NumericConstants.TWO]));
                        itemDTO.setBrand(String.valueOf(obje[NumericConstants.THREE]));
                        itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                        if (obje[NumericConstants.FIVE] != null) {
                            String date = df.format(obje[NumericConstants.FIVE]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.SIX] != null) {
                            String date = df.format(obje[NumericConstants.SIX]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }

                        itemList.add(itemDTO);
                    }
                    componentResultsContainer.addAll(itemList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsItemColumns);
                    contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsItemHeaders);
                    if (level.equals(Constants.TWO)) {
                        cfpDetailsGrid.setVisible(false);
                        ifpDetailsGrid.setVisible(true);
                        psDetailsGrid.setVisible(false);
                        rsDetailsGrid.setVisible(false);
                        String detailsNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NUMBER).getValue());
                        ifpDetailsNo.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        ifpDetailsName.setValue(detailsName);
                        ifpDetailsNo.setEnabled(false);
                        ifpDetailsName.setEnabled(false);
                    } else if (level.equals(Constants.THREE)) {
                        cfpDetailsGrid.setVisible(false);
                        ifpDetailsGrid.setVisible(false);
                        psDetailsGrid.setVisible(true);
                        rsDetailsGrid.setVisible(false);
                        String detailsNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                        psDetailsNo.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        psDetailsName.setValue(detailsName);
                        psDetailsNo.setEnabled(false);
                        psDetailsName.setEnabled(false);
                    } else if (level.equals(Constants.FOUR)) {
                        cfpDetailsGrid.setVisible(false);
                        ifpDetailsGrid.setVisible(false);
                        psDetailsGrid.setVisible(false);
                        rsDetailsGrid.setVisible(true);
                        String detailsNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_ID).getValue());
                        rsDetailsNo.setValue(detailsNo);
                        String detailsName = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.DASHBOARD_NAME).getValue());
                        rsDetailsName.setValue(detailsName);
                        rsDetailsNo.setEnabled(false);
                        rsDetailsName.setEnabled(false);
                    }
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node");
        }
    }

    public List<Integer> saveExistingContract() throws SystemException, PortalException, ParseException {
        int contractMasterSid = 0;
        List<Integer> returnList = new ArrayList<>();
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

            Collection<?> treeItem = dashBoardTreeContainer1.getItemIds(0, dashBoardTreeContainer1.size());

            for (Object item : treeItem) {
                String level = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    String contractId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                    String contractNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                    String contractName = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                    int contractType = Integer.valueOf(String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()));
                    int contStatus = 1;
                    ContractMaster contractMaster;
                    contractMaster = ContractMasterLocalServiceUtil.createContractMaster(0);
                    contractMaster.setContractId(contractId);
                    contractMaster.setContractNo(contractNo);
                    contractMaster.setContractName(contractName);
                    contractMaster.setContractType(contractType);
                    contractMaster.setProcessStatus(true);
                    contractMaster.setSource("BPI");
                    contractMaster.setContractStatus(contStatus);
                    contractMaster.setCreatedBy(Integer.valueOf(userId));
                    contractMaster.setStartDate(new Date());
                    contractMaster.setInboundStatus("A");
                    contractMaster.setCreatedDate(new Date());
                    contractMaster.setModifiedDate(new Date());
                    contractMaster = ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
                    contractMasterSid = contractMaster.getContractMasterSid();
                    session.setContractMasterSid(String.valueOf(contractMasterSid));
                    returnList.add(contractMasterSid);
                    String AliasType = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "type").getValue());
                    String AliasNumber = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "number").getValue());
                    ContractAliasMaster CAM = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    CAM.setContractAliasNo(AliasNumber);
                    CAM.setContractAliasType(AliasType != null && !Constants.NULL.equals(AliasType) && !StringUtils.EMPTY.equals(AliasType) ? Integer.valueOf(AliasType) : 0);
                    CAM.setStartDate(new Date());
                    CAM.setModifiedDate(new Date());
                    CAM.setCreatedBy(1);
                    CAM.setCreatedDate(new Date());
                    CAM.setSource("BPI");
                    CAM.setInboundStatus("A");
                    CAM.setContractMasterSid(contractMasterSid);
                    ContractAliasMaster CAM1 = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);
                    LOGGER.debug("CAM1" + CAM1.getContractAliasMasterSid());

                } else if (level.equals(Constants.ONE)) {

                    CfpModel cfpmodel;
                    cfpmodel = CfpModelLocalServiceUtil.createCfpModel(0);
                    String cfpName = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_NAME).getValue());
                    String cfpId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "id").getValue());
                    String cfpNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "cfpNo").getValue());
                    String cfpStatus = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "cfpStatus").getValue());
                    String cfpEndDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_END_DATE).getValue());
                    String cfpStartDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_START_DATE).getValue());
                    Date sdate = simpleDateFormat.parse(cfpStartDate);
                    Date edate = simpleDateFormat.parse(cfpEndDate);
                    cfpmodel.setCfpName(cfpName);
                    cfpmodel.setCfpNo(cfpNo);
                    cfpmodel.setCfpStatus(Integer.valueOf(cfpStatus));
                    cfpmodel.setCfpId(cfpId);
                    cfpmodel.setCfpStartDate(sdate);
                    cfpmodel.setCfpEndDate(edate);
                    cfpmodel.setSource("GCM");
                    cfpmodel.setInboundStatus("A");
                    cfpmodel.setCreatedDate(new Date());
                    cfpmodel.setModifiedDate(new Date());
                    cfpmodel.setRecordLockStatus(false);
                    cfpmodel.setCreatedBy(Integer.valueOf(userId));
                    cfpmodel.setModifiedBy(Integer.valueOf(userId));
                    cfpmodel = CfpModelLocalServiceUtil.addCfpModel(cfpmodel);
                    String companySid = session.getCompanyMasterSid();

                    returnList.add(Integer.valueOf(companySid));
                    CfpDetails cfpDetails;
                    cfpDetails = CfpDetailsLocalServiceUtil.createCfpDetails(0);
                    cfpDetails.setCfpModelSid(cfpmodel.getCfpModelSid());
                    cfpDetails.setCompanyMasterSid(Integer.valueOf(companySid));
                    cfpDetails.setCompanyStartDate(new Date());
                    cfpDetails.setInboundStatus("A");
                    cfpDetails.setSource("BPI");
                    cfpDetails.setRecordLockStatus(false);
                    cfpDetails.setCreatedDate(new Date());
                    cfpDetails.setModifiedDate(new Date());
                    cfpDetails.setCreatedBy(Integer.valueOf(userId));
                    cfpDetails.setModifiedBy(Integer.valueOf(userId));
                    CfpDetailsLocalServiceUtil.addCfpDetails(cfpDetails);

                    CfpContract cfpcontract;
                    cfpcontract = CfpContractLocalServiceUtil.createCfpContract(0);
                    cfpcontract.setCfpModelSid(cfpmodel.getCfpModelSid());
                    cfpcontract.setCfpName(cfpmodel.getCfpName());
                    cfpcontract.setCfpStatus(1);
                    cfpcontract.setCfpStartDate(new Date());
                    cfpcontract.setContractMasterSid(contractMasterSid);
                    cfpcontract.setInboundStatus("A");
                    cfpcontract.setSource("GCM");
                    cfpcontract.setRecordLockStatus(false);
                    cfpcontract.setCreatedDate(new Date());
                    cfpcontract.setModifiedDate(new Date());
                    cfpcontract.setCreatedBy(Integer.valueOf(userId));
                    cfpcontract.setModifiedBy(Integer.valueOf(userId));
                    cfpcontract = CfpContractLocalServiceUtil.addCfpContract(cfpcontract);
                    String cfpContractSid = String.valueOf(cfpcontract.getCfpContractSid());
                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(cfpContractSid);
                    CfpContractDetails cfpcontractDetails;
                    cfpcontractDetails = CfpContractDetailsLocalServiceUtil.createCfpContractDetails(0);
                    cfpcontractDetails.setCfpContractSid(cfpcontract.getCfpContractSid());
                    cfpcontractDetails.setCompanyMasterSid(Integer.valueOf(session.getCompanyMasterSid()));
                    cfpcontractDetails.setCompanyStartDate(cfpmodel.getCfpStartDate());
                    cfpcontractDetails.setCompanyEndDate(cfpmodel.getCfpEndDate());
                    cfpcontractDetails.setCfpContractAttachedStatus(cfpmodel.getCfpStatus());
                    cfpcontractDetails.setCreatedDate(new Date());
                    cfpcontractDetails.setModifiedDate(new Date());
                    cfpcontractDetails.setCreatedBy(Integer.valueOf(userId));
                    cfpcontractDetails.setModifiedBy(Integer.valueOf(userId));
                    cfpcontractDetails.setInboundStatus("A");
                    cfpcontractDetails.setRecordLockStatus(false);
                    CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpcontractDetails);
                } else if (level.equals(Constants.TWO)) {
                    String ifpModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(item);
                    String cfpContractSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    IfpModel ifpmodel;
                    ifpmodel = IfpModelLocalServiceUtil.getIfpModel(Integer.valueOf(ifpModelId));
                    IfpContract ifpcontract;
                    ifpcontract = IfpContractLocalServiceUtil.createIfpContract(0);
                    ifpcontract.setIfpModelSid(Integer.valueOf(ifpModelId));
                    ifpcontract.setIfpName(ifpmodel.getIfpName());
                    ifpcontract.setIfpType(ifpmodel.getIfpType());
                    ifpcontract.setIfpCategory(ifpmodel.getIfpCategory());
                    ifpcontract.setIfpStatus(ifpmodel.getIfpStatus());
                    ifpcontract.setIfpStartDate(ifpmodel.getIfpStartDate());
                    ifpcontract.setIfpEndDate(ifpmodel.getIfpEndDate());
                    ifpcontract.setCfpContractSid(cfpContractSId);
                    ifpcontract.setContractMasterSid(contractMasterSid);
                    ifpcontract.setInboundStatus("A");
                    ifpcontract.setRecordLockStatus(false);
                    ifpcontract.setSource("GCM");
                    ifpcontract.setCreatedBy(Integer.valueOf(userId));
                    ifpcontract.setModifiedBy(Integer.valueOf(userId));
                    ifpcontract.setCreatedDate(new Date());
                    ifpcontract.setModifiedDate(new Date());
                    ifpcontract = IfpContractLocalServiceUtil.addIfpContract(ifpcontract);
                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(ifpcontract.getIfpContractSid()));

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(ifpcontract.getIfpContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(ifpModelId);
                    input.add(DBDate.format(ifpmodel.getIfpStartDate()));
                    input.add(ifpmodel.getIfpEndDate() == null ? null : DBDate.format(ifpmodel.getIfpEndDate()));
                    IfpContractDetailsImpl.saveIfpDetailsAttached(input, null);
                } else if (level.equals(Constants.THREE)) {

                    String psModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(item);
                    String IFPsystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentItem);
                    String CFPsystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());
                    PsContract psContract;
                    psContract = PsContractLocalServiceUtil.createPsContract(0);
                    psContract.setPsModelSid(Integer.valueOf(psModelId));
                    PsModel psmodel;
                    psmodel = PsModelLocalServiceUtil.getPsModel(Integer.valueOf(psModelId));
                    psContract.setPsName(psmodel.getPsName());
                    psContract.setPsType(psmodel.getPsType());
                    psContract.setPsCategory(psmodel.getPsCategory());
                    psContract.setPsStatus(psmodel.getPsStatus());
                    psContract.setPsStartDate(psmodel.getPsStartDate());
                    psContract.setPsEndDate(psmodel.getPsEndDate());
                    psContract.setContractMasterSid(contractMasterSid);
                    psContract.setCfpContractSid(CFPsystemId);
                    psContract.setIfpContractSid(IFPsystemId);
                    psContract.setInboundStatus("A");
                    psContract.setRecordLockStatus(false);
                    psContract.setSource("GCM");
                    psContract.setCreatedBy(Integer.valueOf(userId));
                    psContract.setModifiedBy(Integer.valueOf(userId));
                    psContract.setCreatedDate(new Date());
                    psContract.setModifiedDate(new Date());
                    psContract = PsContractLocalServiceUtil.addPsContract(psContract);

                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(psContract.getPsContractSid()));
                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(psContract.getPsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(psModelId);
                    input.add(DBDate.format(psmodel.getPsStartDate()));
                    input.add(psmodel.getPsEndDate() == null ? null : DBDate.format(psmodel.getPsEndDate()));
                    PsContractDetailsImpl.savePsDetailsAttached(input, null);

                } else if (level.equals(Constants.FOUR)) {
                    String rsModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object psParentItem = contractDashboardResultsTable.getParent(item);
                    String contractPSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(psParentItem, Constants.HIDDEN_ID).getValue());
                    Object parentIFPItem = contractDashboardResultsTable.getParent(psParentItem);
                    String contractIFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentIFPItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentIFPItem);
                    String contractCFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    RsContract rsContract;
                    rsContract = RsContractLocalServiceUtil.createRsContract(0);
                    rsContract.setRsModelSid(Integer.valueOf(rsModelId));
                    RsModel rsmodel;
                    rsmodel = RsModelLocalServiceUtil.getRsModel(Integer.valueOf(rsModelId));
                    rsContract.setRsId(rsmodel.getRsId());
                    rsContract.setRsNo(rsmodel.getRsNo());
                    rsContract.setRsType(rsmodel.getRsType());
                    rsContract.setRebateProgramType(rsmodel.getRebateProgramType());
                    rsContract.setRsCategory(rsmodel.getRsCategory());
                    rsContract.setRsStatus(rsmodel.getRsStatus());
                    rsContract.setRsDesignation(String.valueOf(rsmodel.getRsDesignation()));
                    rsContract.setRsStartDate(rsmodel.getRsStartDate());
                    rsContract.setRsEndDate(rsmodel.getRsEndDate());
                    rsContract.setRsTradeClass(rsmodel.getRsTradeClass());
                    rsContract.setCfpContractSid(contractCFPId);
                    rsContract.setIfpContractSid(contractIFPId);
                    rsContract.setPsContractSid(contractPSId);
                    rsContract.setContractMasterSid(contractMasterSid);
                    rsContract.setInboundStatus("A");
                    rsContract.setRecordLockStatus(false);
                    rsContract.setSource("GCM");
                    rsContract.setCreatedBy(Integer.valueOf(userId));
                    rsContract.setModifiedBy(Integer.valueOf(userId));
                    rsContract.setCreatedDate(new Date());
                    rsContract.setModifiedDate(new Date());
                    rsContract = RsContractLocalServiceUtil.addRsContract(rsContract);

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(rsContract.getRsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(rsModelId);
                    input.add(rsmodel.getRsStartDate());
                    input.add(rsmodel.getRsEndDate() == null ? null : rsmodel.getRsEndDate());
                    RsContractDetailsImpl.saveRsDetailsAttached(input, null);

                }
            }
            LOGGER.debug("TP Promoted Successfully as Contract Holder");
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return returnList;
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), count, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());

    }

    public void createWorkSheetContent(final Integer end, final PrintWriter printWriter) throws SystemException {
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
