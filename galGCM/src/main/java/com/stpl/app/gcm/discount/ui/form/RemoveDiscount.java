/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.logic.ComponentInfoTableLogic;
import com.stpl.app.gcm.discount.logic.ContractComponentInfoTableLogic;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.RSInfoTableLogic;
import com.stpl.app.gcm.discount.logic.RemoveDiscountRsSearchLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class RemoveDiscount extends CustomComponent {

    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField("contractType")
    public TextField contractType;
    @UiField("contractStartDate")
    public TextField contractStartDate;
    @UiField("contractEndDate")
    public TextField contractEndDate;
    ComponentInfoTableLogic infoLogic = new ComponentInfoTableLogic();
    public ExtPagedTable componentResultsTable = new ExtPagedTable(infoLogic);
    ContractComponentInfoTableLogic contractLogic = new ContractComponentInfoTableLogic();
    public ExtPagedTable contractComponentTable = new ExtPagedTable(contractLogic);
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("populateBtn2")
    public Button populateBtn2;
    @UiField("rsId")
    public TextField rsId;
    @UiField("status")
    public TextField status;
    @UiField("rebateFrequency")
    public TextField rebateFrequency;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("rarType")
    public TextField rarType;
    @UiField("rsType")
    public TextField rsType;
    @UiField("rsProgramType")
    public TextField rsProgramType;
    @UiField("rsCategory")
    public TextField rsCategory;
    @UiField("paymentFrequency")
    public TextField paymentFrequency;
    @UiField("rebatePlanLevel")
    public TextField rebatePlanLevel;
    @UiField("rsName")
    public TextField rsName;
    @UiField("endDate")
    public PopupDateField endDate;
    @UiField("basis")
    public TextField basis;
    public TreeTable dashboardTreeTable = new TreeTable();
    @UiField("removeBtn")
    public Button removeBtn;
    @UiField("removeAllBtn")
    public Button removeAllBtn;
    RSInfoTableLogic rsInfoTableLogic = new RSInfoTableLogic();
    public ExtPagedTable selectedTable = new ExtPagedTable(rsInfoTableLogic);
    @UiField("componentResultsLayout")
    public VerticalLayout componentResultsLayout;
    @UiField("contractComponentsLayout")
    public VerticalLayout contractComponentsLayout;
    @UiField("dashBoardTableLayout")
    public VerticalLayout dashBoardTableLayout;
    @UiField("selectedTableLayout")
    public VerticalLayout selectedTableLayout;
    @UiField("searchField")
    public ComboBox searchField;
    @UiField("searchValue")
    public TextField searchValue;
    @UiField("searchBtn")
    public Button searchBtn;
    /* Contains the parent items of an item in the hierarchy */
    public List parentList = new ArrayList();
    /**
     * The contract member.
     */
    private ContractsDetailsDto contractDetails;
    /**
     * The expand listener.
     */
    private final StplExpandListener expandListener = new StplExpandListener();
    /**
     * The collapse listener.
     */
    private final StplCollapseListener collapseListener = new StplCollapseListener();
    /**
     * The table bean.
     */
    private ContractsDetailsDto tableBean;
    private static final BeanItem<?> NULL_OBJECT = null;
    public int levelValue;
    private BeanItemContainer<RemoveDiscountDto> componentResultsContainer = new BeanItemContainer<RemoveDiscountDto>(RemoveDiscountDto.class);
    private BeanItemContainer<RemoveDiscountDto> contractComponentContainer = new BeanItemContainer<RemoveDiscountDto>(RemoveDiscountDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    private BeanItemContainer<RemoveDiscountDto> selectedContainer = new BeanItemContainer<RemoveDiscountDto>(RemoveDiscountDto.class);
    private static final Logger LOGGER = Logger.getLogger(RemoveDiscount.class);
    SessionDTO session = new SessionDTO();
    RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
    List<String> removeList = new ArrayList<String>();
    List<Integer> contractList = new ArrayList<Integer>();
    List<Integer> rebateList = new ArrayList<Integer>();
    List<RemoveDiscountDto> selecteditemList;
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    private CustomFieldGroup discountChBinder;
    DiscountLogic logic = new DiscountLogic();
    Summary summary;
    TabSheet mainTab;
    RemoveDiscountLookUp lookUp;
    QueryUtils queryUtils = new QueryUtils();
    @UiField("fromCDLabelNo")
    public Label fromCDLabelNo;
    @UiField("fromCDNo")
    public TextField fromCDNo;
    @UiField("fromCDLabelName")
    public Label fromCDLabelName;
    @UiField("fromCDName")
    public TextField fromCDName;
    RemoveDiscount removeDiscount;
    boolean remove = true;
    CommonUtils commonUtils = new CommonUtils();
    private ContractsDetailsDto contractDto = new ContractsDetailsDto();
    int userId;
    int sessionId;

    public Component getContent(final List<RemoveDiscountDto> selecteditemList, TabSheet mainTab, Summary summary, RemoveDiscount removeDiscount, int userId, int sessionId) {
        this.selecteditemList = selecteditemList;
        this.removeDiscount = removeDiscount;
        this.mainTab = mainTab;
        this.summary = summary;
        this.userId = userId;
        this.sessionId = sessionId;
        dashBoardTreeContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/discount/removeDiscount.xml"), this));
        configureFields();
        return vLayout;
    }

    /**
     * Configure Scheduled Processes Table
     *
     */
    protected void configureFields() {
        try {
            searchField.focus();
            dashboardTreeTable.removeAllItems();
            searchField.setImmediate(true);
            searchField.setNullSelectionAllowed(true);
            searchField.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            searchField.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            searchField.addItem("Rebate Schedule ID");
            searchField.addItem("Rebate Schedule No");
            searchField.addItem("Rebate Schedule Name");
            searchField.addItem("Rebate Schedule Status");
            searchField.addItem("Rebate Schedule Type");
            searchField.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            dashBoardTableLayout.addComponent(dashboardTreeTable);
            selectedTableLayout.addComponent(selectedTable);
            HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(rsInfoTableLogic.createControls());
            selectedTableLayout.addComponent(controls);
            contractComponentsLayout.addComponent(contractComponentTable);
            HorizontalLayout contractControls = ResponsiveUtils.getResponsiveControls(contractLogic.createControls());
            contractComponentsLayout.addComponent(contractControls);
            componentResultsLayout.addComponent(componentResultsTable);
            HorizontalLayout componentControls = ResponsiveUtils.getResponsiveControls(infoLogic.createControls());
            componentResultsLayout.addComponent(componentControls);
            configureTables();
            LoadDashBoardTree(selecteditemList);
            loadComponentTable(new ArrayList<String>());
            fromCDNo.setEnabled(false);
            fromCDName.setEnabled(false);
        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
        }
    }

    protected void configureTables() {
        try {

            for (RemoveDiscountDto remove : selecteditemList) {
                removeList.add(remove.getContractNo());
                contractList.add(remove.getContractSid());
                rebateList.add(remove.getRsSid());
            }
            componentResultsTable.setWidth(100, Unit.PERCENTAGE);
            componentResultsTable.setHeight(100, Unit.PERCENTAGE);
            componentResultsTable.setPageLength(5);
            infoLogic.setContainerDataSource(componentResultsContainer);
            infoLogic.setPageLength(5);
            infoLogic.sinkItemPerPageWithPageLength(false);
            componentResultsTable.setVisibleColumns(Constants.COMPONENT_RESULTS_COLUMNS);
            componentResultsTable.setColumnHeaders(Constants.COMPONENT_RESULTS_HEADERS);
            componentResultsTable.setSelectable(true);
            componentResultsTable.setFilterBarVisible(true);
            componentResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            componentResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentResultsTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
            componentResultsTable.setWidth("1050");
            for (Object propertyId : componentResultsTable.getVisibleColumns()) {
                componentResultsTable.setColumnWidth(propertyId, -1);
            }
            Object[] objColumn = Constants.COMPONENT_RESULTS_COLUMNS;
            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    componentResultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                    componentResultsTable.setConverter(value, new DateToStringConverter());
                }
            }
            componentResultsTable.setFilterGenerator(new ExtFilterGenerator() {
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
                  
                    AbstractLogic logic = AbstractLogic.getInstance();
                    if ("contractStatus".equals(propertyId)) {
                        ComboBox marketTypeDdlb = new ComboBox();
                        logic.LazyLoadDdlb(marketTypeDdlb, "Load Contract Status Count", "Load Contract Status", true);
                        return marketTypeDdlb;
                    }
                    return null;
                }
            });
            componentResultsTable.setFilterBarVisible(true);
            componentResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            contractComponentTable.setWidth(100, Unit.PERCENTAGE);
            contractComponentTable.setHeight(100, Unit.PERCENTAGE);
            contractLogic.setContainerDataSource(contractComponentContainer);
            contractLogic.setPageLength(13);
            contractLogic.sinkItemPerPageWithPageLength(false);
            contractComponentTable.setVisibleColumns(Constants.CONTRACT_COLUMNS);
            contractComponentTable.setColumnHeaders(Constants.CONTRACT_HEADERS);
            for (Object propertyId : contractComponentTable.getVisibleColumns()) {
                contractComponentTable.setColumnWidth(propertyId, 125);
            }

            dashboardTreeTable.setWidth(100, Unit.PERCENTAGE);
            dashboardTreeTable.setHeight(100, Unit.PERCENTAGE);
            dashboardTreeTable.setPageLength(5);
            dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
            dashboardTreeTable.setVisibleColumns(Constants.TREE_COLUMNS);
            dashboardTreeTable.setColumnHeaders(Constants.TREE_HEADERS);
            dashboardTreeTable.setSelectable(true);
            for (Object propertyId : dashboardTreeTable.getVisibleColumns()) {
                dashboardTreeTable.setColumnWidth(propertyId, 120);
            }

            rsInfoTableLogic.setContainerDataSource(selectedContainer);
            rsInfoTableLogic.setPageLength(10);
            rsInfoTableLogic.sinkItemPerPageWithPageLength(false);
            selectedTable.setVisibleColumns(Constants.SELECTED_RESULTS_COLUMNS);
            selectedTable.setColumnHeaders(Constants.SELECTED_RESULTS_HEADERS);
            selectedTable.markAsDirtyRecursive();
            selectedTable.setImmediate(true);
            selectedTable.setWidth(100, UNITS_PERCENTAGE);
            selectedTable.setSelectable(false);
            selectedTable.markAsDirty();
            selectedTable.setComponentError(null);
            selectedTable.setFilterBarVisible(false);
            for (Object propertyId : selectedTable.getVisibleColumns()) {
                selectedTable.setColumnWidth(propertyId, 125);
            }
            Object[] objColumnSel = Constants.SELECTED_RESULTS_COLUMNS;
            for (Object objColumn1 : objColumnSel) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    selectedTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }

            startDate.setDateFormat(Constants.DATE_FORMAT);
            endDate.setDateFormat(Constants.DATE_FORMAT);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void LoadDashBoardTree(List<RemoveDiscountDto> selecteditemList) {
        LOGGER.info("Entering getProcessedTree method");
        try {
            final CommonLogic commonLogic = new CommonLogic();

            dashboardTreeTable.markAsDirty();
            dashboardTreeTable.setImmediate(true);
            dashboardTreeTable.setSizeFull();
            dashboardTreeTable.setPageLength(10);
            dashboardTreeTable.removeAllItems();
            parentList.clear();
            levelValue = 0;
            dashBoardTreeContainer.removeAllItems();
            dashBoardTreeContainer = commonLogic.getLevel1Hierarchy(removeList.get(0).toString(), dashBoardTreeContainer, null);
            dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
            setProcessedTableHeader();

            dashboardTreeTable.addExpandListener(expandListener);
            dashboardTreeTable.addCollapseListener(collapseListener);
            dashboardTreeTable.setSelectable(true);
            dashboardTreeTable.setColumnHeaders(new String[]{"Category", "ID", "Number", "Name"});

            dashboardTreeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * Called when a Button has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void itemClick(final ItemClickEvent event) {
                    tableBeanId = event.getItemId();
                    BeanItem<?> targetItem;
                    if (tableBeanId instanceof BeanItem<?>) {
                        targetItem = (BeanItem<?>) tableBeanId;
                    } else if (tableBeanId instanceof ContractsDetailsDto) {
                        targetItem = new BeanItem<ContractsDetailsDto>((ContractsDetailsDto) tableBeanId);
                    } else {
                        targetItem = NULL_OBJECT;
                    }
                    tableBean = (ContractsDetailsDto) targetItem.getBean();
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
        LOGGER.info("End of getProcessedTree method");
    }

    private void setProcessedTableHeader() {
        LOGGER.info("Entering setProcessedTableHeader method");
        dashboardTreeTable.setVisibleColumns(Constants.TREE_COLUMNS);
        dashboardTreeTable.setColumnHeaders(Constants.TREE_HEADERS);
        LOGGER.info("End of setProcessedTableHeader method");
    }

    private void setEnableFields(Boolean isValue) {
        rsId.setReadOnly(isValue);
        rsName.setReadOnly(isValue);
        rsNumber.setReadOnly(isValue);
        status.setReadOnly(isValue);
        startDate.setReadOnly(isValue);
        endDate.setReadOnly(isValue);
        rebateFrequency.setReadOnly(isValue);
        rarType.setReadOnly(isValue);
        rsType.setReadOnly(isValue);
        rsProgramType.setReadOnly(isValue);
        rsCategory.setReadOnly(isValue);
        paymentFrequency.setReadOnly(isValue);
        rebatePlanLevel.setReadOnly(isValue);
        basis.setReadOnly(isValue);
    }

    /**
     * The Class StplExpandListener.
     *
     * @see StplExpandEvent
     */
    class StplExpandListener implements Tree.ExpandListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        /**
         * The contract dashboard logic.
         */
        private final CommonLogic commonLogic = new CommonLogic();

        /**
         * Gets the contract dashboard logic.
         *
         * @return the contract dashboard logic
         */
        public CommonLogic getCommonLogic() {
            return commonLogic;
        }

        /**
         * Node Expand Event
         *
         */
        public void nodeExpand(final Tree.ExpandEvent event) {
            try {
                LOGGER.info("Entering StplExpandListener nodeExpand method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                contractDetails.setRebateList(rebateList);
                switch (contractDetails.getLevel()) {
                    case ContractsDetailsDto.LEVEL1:
                        configureLevel(event.getItemId());
                        int i = commonLogic.getCFPQueriedCount(contractDetails.getSystemId());
                        if(contractDetails!=null){
                        dashBoardTreeContainer = commonLogic.getLevel2Hierarchy(contractDetails, dashBoardTreeContainer,null, null, null, null);
                        }
                        setProcessedTableHeader();

                        break;
                    case ContractsDetailsDto.LEVEL2:
                        configureLevel(event.getItemId());
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails, dashBoardTreeContainer, null, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails, false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        configureLevel(event.getItemId());
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel4Hierarchy(contractDetails, dashBoardTreeContainer, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.setCollapsed(contractDetails, false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        if (remove) {
                            configureLevel(event.getItemId());
                            dashboardTreeTable.setContainerDataSource(commonLogic.getLevel5Hierarchy(contractDetails, dashBoardTreeContainer, null));
                            dashboardTreeTable.removeExpandListener(expandListener);
                            dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                            dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                            dashboardTreeTable.setCollapsed(contractDetails.getParent3(), false);
                            dashboardTreeTable.setCollapsed(contractDetails, false);
                            dashboardTreeTable.addExpandListener(expandListener);
                            setProcessedTableHeader();
                        }
                        break;
                    default:
                        break;
                }
                LOGGER.info("End of StplExpandListener nodeExpand method");
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());;
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            }

        }
    }

    /**
     * The Class StplCollapseListener.
     *
     * @see StplCollapseEvent
     */
    class StplCollapseListener implements Tree.CollapseListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        /**
         * The contract dashboard logic.
         */
        private final CommonLogic commonLogic = new CommonLogic();

        /**
         * Gets the contract dashboard logic.
         *
         * @return the contract dashboard logic
         */
        public CommonLogic getCommonLogic() {
            return commonLogic;
        }

        /**
         * Method used to node collapse and its event.
         *
         * @param event the event
         */
        public void nodeCollapse(final Tree.CollapseEvent event) {
            try {
                LOGGER.info("Entering StplCollapseListener nodeCollapse method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                switch (contractDetails.getLevel()) {
                    case ContractsDetailsDto.LEVEL1:
                        levelValue = 0;
                        dashBoardTreeContainer = commonLogic.getLevel1Hierarchy(removeList.get(0).toString(), dashBoardTreeContainer, null);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL2:
                        levelValue = 1;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel2Hierarchy(contractDetails.getParent1(), dashBoardTreeContainer, null, null, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        levelValue = 2;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails.getParent2(), dashBoardTreeContainer, null, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = 3;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel4Hierarchy(contractDetails.getParent3(), dashBoardTreeContainer, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent3(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    default:
                        break;
                }
                int temp = parentList.indexOf(event.getItemId());
                for (int i = temp; i < parentList.size();) {
                    parentList.remove(i);
                }
                LOGGER.info("End of StplCollapseListener nodeCollapse method");
            } catch (Exception ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            }

        }
    }

    private int configureLevel(Object item) {
        levelValue = 1;
        parentList.clear();
        while (!dashboardTreeTable.getContainerDataSource().isRoot(item)) {
            parentList.add(item);
            item = dashboardTreeTable.getContainerDataSource().getParent(item);
            levelValue++;
        }
        parentList.add(item);
        Collections.reverse(parentList);
        return levelValue;
    }

    private void loadComponentTable(List<String> inpuList) {
        infoLogic.loadSetData(userId, sessionId, inpuList, "contract");
        for (RemoveDiscountDto remove : selecteditemList) {
            contractNo.setValue(remove.getContractNo());
            contractName.setValue(remove.getContractName());
            contractType.setValue(remove.getMarketType());
            contractStartDate.setValue(remove.getContractstartDate() == null ? StringUtils.EMPTY : DBDate.format((Date) remove.getContractstartDate()));
            contractEndDate.setValue(remove.getContractendDate() == null ? StringUtils.EMPTY : DBDate.format((Date) remove.getContractendDate()));
        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populateBtn")
    public void populateBtnLogic(Button.ClickEvent event) {
        try {
            LOGGER.info("Entered populateBtnLogic");
            selectedContainer.removeAllItems();
            if (componentResultsTable.getValue() != null) {
                RemoveDiscountDto discountDto = (RemoveDiscountDto) componentResultsTable.getValue();
                List<RemoveDiscountDto> list = logic.getItems(discountDto, 0, 0, true);
                rsInfoTableLogic.loadSetData(discountDto);
                setEnableFields(Boolean.FALSE);
                if (!list.isEmpty()) {
                    RemoveDiscountDto remove = list.get(0);
                    rsId.setValue(remove.getRsId());
                    rsName.setValue(remove.getRsName());
                    rsNumber.setValue(remove.getRsNo());
                    status.setValue(remove.getRsStatus());
                    startDate.setValue(DBDate.parse(remove.getRsStartDate()));
                    if (remove.getRsEndDate() != null && remove.getRsEndDate() != Constants.NULL) {
                        endDate.setValue(DBDate.parse(remove.getRsEndDate()));
                    }
                    rebateFrequency.setValue(remove.getRebateFrequency());
                    rarType.setValue(remove.getRarType());
                    rsType.setValue(remove.getRsType());
                    rsProgramType.setValue(remove.getProgramType());
                    rsCategory.setValue(remove.getRsCategory());
                    paymentFrequency.setValue(remove.getPaymentFrequency());
                    rebatePlanLevel.setValue(remove.getRebatePlanLevel());
                }
                setEnableFields(Boolean.TRUE);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Records Selected",
                        "Please highlight a row to populate.");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populateBtn2")
    public void populateBtn2Logic(Button.ClickEvent event) {
        if (dashboardTreeTable.getValue() != null) {
            ContractsDetailsDto temp = tableBean;
            fromCDLabelName.setValue(temp.getCategory() + "Name: ");
            fromCDLabelNo.setValue(temp.getCategory() + " No: ");
            fromCDNo.setValue(temp.getNumber());
            fromCDName.setValue(temp.getName());
            Object[] objColumn = HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN;
            if (Constants.IndicatorConstants.CFP.toString().equals(temp.getCategory())) {
                loadCfpFromCD(temp);
                objColumn = HeaderUtil.AD_COMPONENT_DETAILS_COMPANY_COLUMN;

            } else if (Constants.IndicatorConstants.IFP.toString().equals(temp.getCategory())) {
                loadIfpFromCD(temp);
            } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(temp.getCategory())) {
                fromCDLabelName.setValue("Price Schedule No:");
                fromCDLabelNo.setValue("Price Schedule Name:");
                loadPsFromCD(temp);
            } else if (Constants.IndicatorConstants.RS_VALUE.toString().equals(temp.getCategory())) {
                fromCDLabelName.setValue("Rebate Schedule No:");
                fromCDLabelNo.setValue("Rebate Schedule Name:");
                loadRsFromCD(temp);
            }
            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    contractComponentTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Records Selected",
                    "Please highlight a component to Populate.");
        }
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("removeBtn")
    public void removeBtnLogic(Button.ClickEvent event) {
        try {
            ContractsDetailsDto dto = (ContractsDetailsDto) dashboardTreeTable.getValue();
            if (dashboardTreeTable.getValue() != null) {
                if (Constants.RS.equals(dto.getCategory())) {
                    mainTab.removeTab(mainTab.getTab(1));
                    dto.setRebateList(rebateList);
                    mainTab.addTab(summary.getContent(selecteditemList, dto, mainTab, removeDiscount), "Summary", null, 1);
                } else {
                    AbstractNotificationUtils.getErrorNotification("Select RS",
                            "Please select RS to remove.");
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Records Selected",
                        "Please highlight a component to Remove.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        try {
            String field = String.valueOf(searchField.getValue());
            String value = String.valueOf(searchValue.getValue());
            if (!queryUtils.getNull(field)) {
                AbstractNotificationUtils.getErrorNotification("No field Selected",
                        "Please select a search criteria.");
            } else if (!queryUtils.getNull(value)) {
                AbstractNotificationUtils.getErrorNotification("No Value",
                        "Please enter a Search Value..");
            } else {
                List<String> list = new ArrayList(5);
                if (field.equals("Rebate Schedule ID")) {
                    list.add(value.replace("*", "%"));
                    list.add(null);
                    list.add(null);
                    list.add(null);
                    list.add(null);
                } else if (field.equals("Rebate Schedule No")) {
                    list.add(null);
                    list.add(value.replace("*", "%"));
                    list.add(null);
                    list.add(null);
                    list.add(null);
                }
                if (field.equals("Rebate Schedule Name")) {
                    list.add(null);
                    list.add(null);
                    list.add(value.replace("*", "%"));
                    list.add(null);
                    list.add(null);
                }
                if (field.equals("Rebate Schedule Status")) {
                    list.add(null);
                    list.add(null);
                    list.add(null);
                    list.add(value.replace("*", "%"));
                    list.add(null);
                }
                if (field.equals("Rebate Schedule Type")) {
                    list.add(null);
                    list.add(null);
                    list.add(null);
                    list.add(null);
                    list.add(value.replace("*", "%"));
                }
                loadComponentTable(list);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Remove All btn logic.
     *
     * @param event the event
     */
    @UiHandler("removeAllBtn")
    public void removeBtnAllLogic(Button.ClickEvent event) {
        LOGGER.info("Entered RemoveAll method");

        try {
            mainTab.removeTab(mainTab.getTab(1));
            contractDto.setRsSystemId(commonUtils.CollectionToString(rebateList, true));
            mainTab.addTab(summary.getContent(selecteditemList, contractDto, mainTab, removeDiscount), "Summary", null, 1);

        } catch (Exception ex) {
            ex.getMessage();
            LOGGER.error(ex.getMessage() + " - in table removeAllBtn");
        }

        LOGGER.info("Ending RemoveAll method");

    }

    private void loadCfpFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<CFPComponentDetailsDTO>(CFPComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.CFP);
        contractComponentTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_COMPANY_COLUMN);
        contractComponentTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_COMPANY_HEADER);
    }

    private void loadIfpFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.IFP);
        contractComponentTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        contractComponentTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
    }

    private void loadPsFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.PS);
        contractComponentTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        contractComponentTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
    }

    private void loadRsFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.RS);
        contractComponentTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        contractComponentTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
