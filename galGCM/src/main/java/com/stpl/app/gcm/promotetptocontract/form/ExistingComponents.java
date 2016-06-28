/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import static com.stpl.app.gcm.discount.logic.DiscountLogic.DBDate;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.logic.ExistingComponentDetailTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.ExistingComponentSearchTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
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
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

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
    private static final Logger LOGGER = Logger.getLogger(ExistingComponents.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("transferCompPanelTableLayout")
    public VerticalLayout transferCompPanelTableLayout;
    @UiField("contractDashboardResultsTableLayout")
    public VerticalLayout contractDashboardResultsTableLayout;
    @UiField("componentDetailsTableLayout")
    public VerticalLayout componentDetailsTableLayout;
    @UiField("contractComponentDetailsTableLayout")
    public VerticalLayout contractComponentDetailsTableLayout;
    TreeTable contractDashboardResultsTable = new TreeTable();
    public ExtFilterTable contractComponentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> transferCompContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> selectedContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    @UiField("componentType")
    public ComboBox componentTypeDdlb;
    @UiField("searchField")
    public ComboBox searchFieldDdlb;
    @UiField("rebateScheduleId")
    public TextField rebateScheduleId;
    @UiField("status")
    public TextField status;
    @UiField("rebateFrequency")
    public TextField rebateFrequency;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("startDate")
    public TextField startDate;
    @UiField("rarType")
    public TextField rarType;
    @UiField("rsType")
    public TextField rsType;
    @UiField("rsName")
    public TextField rsName;
    @UiField("endDate")
    public TextField endDate;
    @UiField("basis")
    public TextField basis;
    @UiField("excelBtn1")
    public Button excelBtn1;
    @UiField("excelBtn2")
    public Button excelBtn2;
    @UiField("searchValue")
    public TextField searchValue;
    @UiField("searchBtn1")
    public Button searchBtn1;
    @UiField("addToTreeBtn1")
    public Button addToTreeBtn1;
    @UiField("removeBtn2")
    public Button removeBtn2;
    @UiField("populateBtn2")
    public Button populateBtn2;
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
    @UiField("ifpId")
    public TextField ifpId;
    @UiField("ifpNo")
    public TextField ifpNo;
    @UiField("ifpName")
    public TextField ifpName;
    @UiField("ifpStatus")
    public TextField ifpStatus;
    @UiField("ifpType")
    public TextField ifpType;
    @UiField("ifpStartDate")
    public TextField ifpStartDate;
    @UiField("ifpEndDate")
    public TextField ifpEndDate;
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
    @UiField("psEndDate")
    public TextField psEndDate;
    @UiField("searchType")
    public ComboBox searchType;
    @UiField("componentInformationLayout1")
    public GridLayout componentInformationLayout1;
    @UiField("componentInfoIfpLayout")
    public GridLayout componentInfoIfpLayout;
    @UiField("componentInfoPsLayout")
    public GridLayout componentInfoPsLayout;
    private ExtTreeContainer<ComponentInfoDTO> dashBoardTreeContainer = new ExtTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    CustomTreeContainer<ComponentInfoDTO> dashBoardTreeContainer1 = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> componentResultsContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    public List parentList = new ArrayList();
    public int levelValue;
    ComponentInfoDTO componentInfoDTO = new ComponentInfoDTO();
    private Object tableBeanId;
    private static final BeanItem<?> NULL_OBJECT = null;
    private ComponentInfoDTO tableBean;
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    ExistingComponentSearchTableLogic availableTableLogic = new ExistingComponentSearchTableLogic();
    public ExtPagedTable componentResultsTable = new ExtPagedTable(availableTableLogic);
    ComponentInfoDTO newDiscountTabDto = new ComponentInfoDTO();
    public ExtPagedFilterTable componentDetailsSelectedItem = new ExtPagedFilterTable();
    List<Integer> newlyAddedRebates = new ArrayList<Integer>();
    Object treeBeanId;
    private CustomTreeContainer<ComponentInfoDTO> excelResultBean = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private CustomTreeContainer<ComponentInfoDTO> excelResultBean2 = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    public List<ComponentInfoDTO> componentInformation = new ArrayList<ComponentInfoDTO>();
    public List<ComponentInfoDTO> componentInfo = new ArrayList<ComponentInfoDTO>();
    private ExtCustomTable exportPeriodViewTable;
    private ExtCustomTable exportPeriodViewTable2;
    String excelName1 = "Component Results";
    String excelName2 = "Component Details";
    PromoteTPLogic logic = new PromoteTPLogic();
    QueryUtils queryUtils = new QueryUtils();
    DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    List<HelperDTO> itemStatusList = new ArrayList<HelperDTO>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    Boolean contractExcelFlag=false;
    Boolean infoExcelFlag=false;

    public ExistingComponents(SessionDTO session, TreeTable contractDashBoardTable) {
        try {
            this.session = session;
            this.contractDashboardResultsTable = contractDashBoardTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpExistingComponents.xml"), this));
            configureFields();

        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
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

            searchFieldDdlb.setImmediate(true);
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
            LOGGER.error(ex.getMessage());
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
        availableTableLogic.setPageLength(8);
        availableTableLogic.sinkItemPerPageWithPageLength(false);

        componentResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentResultsTable.setWidth("890px");
        componentResultsTable.setHeight("230px");
        componentResultsTable.setPageLength(5);
        componentResultsTable.setContainerDataSource(transferCompContainer);
        componentResultsTable.setVisibleColumns(Constants.RS_RESULTS_COLUMNS);
        componentResultsTable.setColumnHeaders(Constants.RS_RESULTS_HEADERS);
        componentResultsTable.setSelectable(true);
        for (Object propertyId : componentResultsTable.getVisibleColumns()) {
            componentResultsTable.setColumnWidth(propertyId, 125);
        }

        componentTypeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String compType = String.valueOf(componentTypeDdlb.getValue());
                loadTableHeader(compType);
            }
        });


        componentResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
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
                if (searchField.contains("Status") || searchField.contains("Type")) {
                    if (searchField.contains("Status")) {
                        try {
                            itemStatusList.clear();
                            itemStatusList = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
                            searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);

                        } catch (SystemException ex) {
                            java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if (searchField.equals(Constants.IFPTYPE)) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("IFP_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (searchField.equals("PS Type")) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("PS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (searchField.equals("RS Type")) {
                            try {
                                itemStatusList.clear();
                                itemStatusList = CommonLogic.getDropDownList("RS_TYPE");
                                searchType = CommonLogic.getNativeSelect(searchType, itemStatusList);
                            } catch (SystemException ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
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
            LOGGER.info("Entered populate method");
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
            
            
            List<ComponentInfoDTO> list=logic.getItemsFromRs(comInfoDto);
            selectedContainer.addAll(list);
            componentDetailsSelectedItem.setContainerDataSource(selectedContainer);
            loadTableHeader(compType);
         LOGGER.info("Ended populate method");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ExistingComponents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configureContractDashboardResultsTable() {
        contractDashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTable.markAsDirty();
        contractDashboardResultsTable.setImmediate(true);
        contractDashboardResultsTable.setWidth("630px");
        contractDashboardResultsTable.setHeight("350px");
        contractDashboardResultsTable.setPageLength(5);
        contractDashboardResultsTable.setSelectable(true);
        contractDashboardResultsTable.setMultiSelect(false);
        contractDashboardResultsTable.setContainerDataSource(dashBoardTreeContainer1);
        contractDashboardResultsTable.setVisibleColumns(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_COLUMNS_TRANSFER);
        contractDashboardResultsTable.setColumnHeaders(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_HEADERS);
    }

    public void configureComponentDetailsTable() {

        componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSelectedItem.setWidth("890px");
        componentDetailsSelectedItem.setHeight("230px");
        componentDetailsSelectedItem.setPageLength(5);
        componentDetailsSelectedItem.setContainerDataSource(selectedContainer);
        componentDetailsSelectedItem.setVisibleColumns(Constants.EXISTING_SELECTED_RESULTS_COLUMNS);
        componentDetailsSelectedItem.setColumnHeaders(Constants.EXISTING_SELECTED_RESULTS_HEADERS);
        componentDetailsSelectedItem.setSelectable(false);
    }

    public void configureContractComponentDetailsTable() {
        contractComponentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractComponentDetailsTable.setWidth("630px");
        contractComponentDetailsTable.setHeight("430px");
        contractComponentDetailsTable.setPageLength(5);
        contractComponentDetailsTable.setContainerDataSource(componentResultsContainer);
        contractComponentDetailsTable.setVisibleColumns(Constants.COMPONENT_DETAILS_COLUMNS);
        contractComponentDetailsTable.setColumnHeaders(Constants.COMPONENT_DETAILS_HEADERS);
    }

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
            contractExcelFlag=true;
             final int recordCount = componentResultsTable.getContainerDataSource().size();
           if (recordCount > 0) {
                 createWorkSheet(excelName1, componentResultsTable,recordCount);
            }
            transferCompPanelTableLayout.removeComponent(exportPeriodViewTable);
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }finally{
          contractExcelFlag=false;  
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
            infoExcelFlag=true;
             final int recordCount = componentDetailsSelectedItem.getContainerDataSource().size();
           if (recordCount > 0) {
                 createWorkSheet(excelName2, componentDetailsSelectedItem,recordCount);
            }          
            
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }finally{
             infoExcelFlag=false;
        }
    }

    private void configureExcelSelItemResultTable() {
        excelResultBean2 = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
        exportPeriodViewTable2 = new ExtCustomTable();
        transferCompPanelTableLayout.addComponent(exportPeriodViewTable2);
        exportPeriodViewTable2.setRefresh(Boolean.FALSE);
        exportPeriodViewTable2.setVisible(false);
        exportPeriodViewTable2.setContainerDataSource(excelResultBean2);
        exportPeriodViewTable2.setVisibleColumns(componentDetailsSelectedItem.getVisibleColumns());
        exportPeriodViewTable2.setColumnHeaders(componentDetailsSelectedItem.getColumnHeaders());
    }

    @UiHandler("componentType")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) throws SystemException {
        searchFieldDdlb = CommonLogic.loadExistingTabSearchField(searchFieldDdlb, componentTypeDdlb);
        loadTableHeaders();
    }

    private void loadTableHeaders() {
        String compType = String.valueOf(componentTypeDdlb.getValue());
        if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.AD_CFP_IFP_RESULTS_COLUMNS);
            componentResultsTable.setColumnHeaders(Constants.AD_CFP_IFP_RESULTS_HEADERS);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.AD_CFP_IFP_RESULTS_COLUMNS);
            componentResultsTable.setColumnHeaders(Constants.AD_CFP_IFP_RESULTS_HEADERS);
            for(Object obj:componentResultsTable.getVisibleColumns()){
                componentResultsTable.setColumnWidth(obj, 195);
                    }
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.AD_PS_RESULTS_COLUMNS);
            componentResultsTable.setColumnHeaders(Constants.AD_PS_RESULTS_HEADERS);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.RS_RESULTS_COLUMNS);
            componentResultsTable.setColumnHeaders(Constants.RS_RESULTS_HEADERS);
        }
    }

    /**
     * Search Button Logic
     *
     * @param event
     */
    @UiHandler("searchBtn1")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered search method");
        transferCompContainer.removeAllItems();
        String searchField = String.valueOf(searchFieldDdlb.getValue());
        if (searchField.contains("Status") || searchField.contains("Type")) {
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

        LOGGER.info("Ending search method");
    }

    @UiHandler("addToTreeBtn1")
    public void addToTreeLogic(Button.ClickEvent event) throws SystemException {
        try {
            addToContDashboardTree();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
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
                    if (2 - levelNumber == 1) {
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (3 - levelNumber == 1) {
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (4 - levelNumber == 1) {
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        }
    }

    private ComponentInfoDTO getBeanFromID(final Object tableID) {
        BeanItem<?> targetItem;
        if (tableID instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableID;
        } else if (tableID instanceof ComponentInfoDTO) {
            targetItem = new BeanItem<ComponentInfoDTO>((ComponentInfoDTO) tableID);
        } else {
            targetItem = NULL_OBJECT;
        }
        return (ComponentInfoDTO) targetItem.getBean();
    }

    private void setTreeNode(final ComponentInfoDTO bean, final VerticalDropLocation location, final Object targetItemId) {

        LOGGER.info("Entering setTreeNode method");

        if (location == VerticalDropLocation.MIDDLE) {

            final String dommyId = bean.getCategory() + "-" + bean.getContractId() + "-" + bean.getContractNo() + "-" + bean.getContractName();
            final Collection list = dashBoardTreeContainer.rootItemIds();
            boolean flag = false;
            for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                final Object idValue = iterator.next();
                final ComponentInfoDTO availableContract = getBeanFromID(idValue);
                final String treeCaption = availableContract.getCategory() + "-" + availableContract.getId() + "-" + availableContract.getContractNo() + "-" + availableContract.getContractName();
                if (treeCaption.equals(dommyId)) {
                    flag = true;
                }
            }
            if (flag) {
                AbstractNotificationUtils.getWarningNotification("Duplicate Contract ID", "Selected Contract ID is already exist");
            } else {
                dashBoardTreeContainer.addBean(bean);
                dashBoardTreeContainer.setChildrenAllowed(bean, false);
                dashBoardTreeContainer.setParent(bean, targetItemId);
            }

        } // Drop at the top of a subtree -> make it previous
        else if (location == VerticalDropLocation.TOP) {
            AbstractNotificationUtils.getWarningNotification("Drop Criteria", "Drop the child node on the parent node");
            return;
        } // Drop below another item -> make it next
        else if (location == VerticalDropLocation.BOTTOM) {
            AbstractNotificationUtils.getWarningNotification("Drop Criteria", "Drop the child node on the parent node");
            return;
        }
        LOGGER.info("End of setTreeNode method");
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
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> companyList = new ArrayList<ComponentInfoDTO>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO companyDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        companyDTO.setCompanyNo(String.valueOf(obje[1]));
                        companyDTO.setCompanyName(String.valueOf(obje[2]));
                        companyDTO.setCompanyStatus(String.valueOf(obje[5]));
                        if (obje[3] != null) {
                            String date = df.format(obje[3]);
                            companyDTO.setCompanyStartDate(date);
                        } else {
                            companyDTO.setCompanyStartDate(Constants.EMPTY);
                        }
                        if (obje[4] != null) {
                            String date = df.format(obje[4]);
                            companyDTO.setCompanyEndDate(date);
                        } else {
                            companyDTO.setCompanyEndDate(Constants.EMPTY);
                        }
                        companyList.add(companyDTO);
                    }
                    componentResultsContainer.addAll(companyList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.CC_COMPONENT_DETAILS_COLUMNS);
                    contractComponentDetailsTable.setColumnHeaders(Constants.CC_COMPONENT_DETAILS_HEADERS);
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
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> itemList = new ArrayList<ComponentInfoDTO>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(String.valueOf(obje[2]));
                        itemDTO.setBrand(String.valueOf(obje[3]));
                        itemDTO.setIfpStatus(String.valueOf(obje[4]));
                        if (obje[5] != null) {
                            String date = df.format(obje[5]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[6] != null) {
                            String date = df.format(obje[6]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }

                        itemList.add(itemDTO);
                    }
                    componentResultsContainer.addAll(itemList);
                    contractComponentDetailsTable.setVisibleColumns(Constants.COMPONENT_DETAILS_ITEM_COLUMNS);
                    contractComponentDetailsTable.setColumnHeaders(Constants.COMPONENT_DETAILS_ITEM_HEADERS);
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
        List<Integer> returnList = new ArrayList<Integer>();
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
                   LOGGER.info("CAM1" + CAM1.getContractAliasMasterSid());

                } else if (level.equals(Constants.ONE)) {

                    CfpModel cfpmodel;
                    cfpmodel = CfpModelLocalServiceUtil.createCfpModel(0);
                    String cfpName = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_NAME).getValue());
                    String cfpId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "id").getValue());
                    String cfpNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "cfpNo").getValue());
                    String cfpStatus = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "cfpStatus").getValue());
                    String cfpEndDate =String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_END_DATE).getValue());
                    String cfpStartDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_START_DATE).getValue());
                    Date sdate=simpleDateFormat.parse(cfpStartDate);
                    Date edate=simpleDateFormat.parse(cfpEndDate);
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
                    cfpDetails = CfpDetailsLocalServiceUtil.addCfpDetails(cfpDetails);

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

                    List<Object> input = new ArrayList<Object>(8);
                    input.add(ifpcontract.getIfpContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(ifpModelId);
                    input.add(DBDate.format(ifpmodel.getIfpStartDate()));
                    input.add(ifpmodel.getIfpEndDate() == null ? null : DBDate.format(ifpmodel.getIfpEndDate()));
                    IfpContractDetailsLocalServiceUtil.saveIfpDetailsAttached(input, null);
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
                    List<Object> input = new ArrayList<Object>(8);
                    input.add(psContract.getPsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(psModelId);
                    input.add(DBDate.format(psmodel.getPsStartDate()));
                    input.add(psmodel.getPsEndDate() == null ? null : DBDate.format(psmodel.getPsEndDate()));
                    PsContractDetailsLocalServiceUtil.savePsDetailsAttached(input, null);

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

                    List<Object> input = new ArrayList<Object>(8);
                    input.add(rsContract.getRsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(DBDate.format(new Date()));
                    input.add(rsModelId);
                    input.add(rsmodel.getRsStartDate());
                    input.add(rsmodel.getRsEndDate() == null ? null : rsmodel.getRsEndDate());
                    RsContractDetailsLocalServiceUtil.saveRsDetailsAttached(input, null);

                }
            }
            LOGGER.info("TP Promoted Successfully as Contract Holder");
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        return returnList;
    }
    
    
  public void createWorkSheet(String moduleName, ExtCustomTable resultTable,int count) throws SystemException, PortalException, Exception {
         ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), count, this, UI.getCurrent(), moduleName.toUpperCase());

    }
    
    
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
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
            LOGGER.error(e.getMessage());
        }
    }   
    
    private void loadTableHeader(String compType){
           if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
                    componentInformationLayout1.setVisible(false);
                    componentInfoIfpLayout.setVisible(true);
                    componentInfoPsLayout.setVisible(false);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_COLUMNS_IFP_NEW);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_HEADERS_IFP_NEW);
                } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                    componentInformationLayout1.setVisible(true);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(false);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_RS_COLUMN);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_RS_HEADER);

                } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
                    componentInformationLayout1.setVisible(false);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(true);
                    componentDetailsSelectedItem.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_PS_COLUMN);
                    componentDetailsSelectedItem.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_PS_HEADER);
                    
                }
    }
    
}
