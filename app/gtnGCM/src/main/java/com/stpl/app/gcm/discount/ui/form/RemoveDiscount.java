/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
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
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
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
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
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
    private TextField contractNo;
    @UiField("contractName")
    private TextField contractName;
    @UiField("contractType")
    private TextField contractType;
    @UiField("contractStartDate")
    private TextField contractStartDate;
    @UiField("contractEndDate")
    private TextField contractEndDate;
    private final ComponentInfoTableLogic infoLogic = new ComponentInfoTableLogic();
    private final ExtPagedTable componentResultsTable = new ExtPagedTable(infoLogic);
    private final ContractComponentInfoTableLogic contractLogic = new ContractComponentInfoTableLogic();
    private final ExtPagedTable contractComponentTable = new ExtPagedTable(contractLogic);
    @UiField("populateBtn")
    private Button populateBtn;
    @UiField("populateBtn2")
    private Button populateBtn2;
    @UiField("rsId")
    private TextField rsId;
    @UiField("status")
    private TextField status;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("rsNumber")
    private TextField rsNumber;
    @UiField("startDate")
    private PopupDateField startDate;
    @UiField("rarType")
    private TextField rarType;
    @UiField("rsType")
    private TextField rsType;
    @UiField("rsProgramType")
    private TextField rsProgramType;
    @UiField("rsCategory")
    private TextField rsCategory;
    @UiField("paymentFrequency")
    private TextField paymentFrequency;
    @UiField("rebatePlanLevel")
    private TextField rebatePlanLevel;
    @UiField("rsName")
    private TextField rsName;
    @UiField("endDate")
    private PopupDateField endDate;
    @UiField("basis")
    private TextField basis;
    private final TreeTable dashboardTreeTable = new TreeTable();
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("removeAllBtn")
    private Button removeAllBtn;
    RSInfoTableLogic rsInfoTableLogic = new RSInfoTableLogic();
    private final ExtPagedTable selectedTable = new ExtPagedTable(rsInfoTableLogic);
    @UiField("componentResultsLayout")
    private VerticalLayout componentResultsLayout;
    @UiField("contractComponentsLayout")
    private VerticalLayout contractComponentsLayout;
    @UiField("dashBoardTableLayout")
    private VerticalLayout dashBoardTableLayout;
    @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;
    @UiField("searchField")
    private ComboBox searchField;
    @UiField("searchValue")
    private TextField searchValue;
    @UiField("searchBtn")
    private Button searchBtn;
    /* Contains the parent items of an item in the hierarchy */
    private final List parentList = new ArrayList();
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
    private int levelValue;
    private final BeanItemContainer<RemoveDiscountDto> componentResultsContainer = new BeanItemContainer<>(RemoveDiscountDto.class);
    private final BeanItemContainer<RemoveDiscountDto> contractComponentContainer = new BeanItemContainer<>(RemoveDiscountDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
    private final BeanItemContainer<RemoveDiscountDto> selectedContainer = new BeanItemContainer<>(RemoveDiscountDto.class);
    private static final Logger LOGGER = Logger.getLogger(RemoveDiscount.class);
    private final List<String> removeList = new ArrayList<>();
    private final List<Integer> contractList = new ArrayList<>();
    private final List<Integer> rebateList = new ArrayList<>();
    private List<RemoveDiscountDto> selecteditemList;
    private static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private final StplSecurity stplSecurity = new StplSecurity();
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    private final DiscountLogic logic = new DiscountLogic();
    private Summary summary;
    private TabSheet mainTab;
    private final QueryUtils queryUtils = new QueryUtils();
    @UiField("fromCDLabelNo")
    private Label fromCDLabelNo;
    @UiField("fromCDNo")
    private TextField fromCDNo;
    @UiField("fromCDLabelName")
    private Label fromCDLabelName;
    @UiField("fromCDName")
    private TextField fromCDName;
    private RemoveDiscount removeDiscount;
    private final boolean remove = true;
    private final ContractsDetailsDto contractDto = new ContractsDetailsDto();
    private int userId;
    private int sessionId;
    private final List<Object> rsSidList = new ArrayList<>();

    public Component getContent(final List<RemoveDiscountDto> selecteditemList, TabSheet mainTab, Summary summary, RemoveDiscount removeDiscount, int userID, int sessionID) {
        this.selecteditemList = selecteditemList;
        this.removeDiscount = removeDiscount;
        this.mainTab = mainTab;
        this.summary = summary;
        this.userId = userID;
        this.sessionId = sessionID;
        dashBoardTreeContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/discount/removeDiscount.xml"), this));
        configureFields();
        configureSecurityPermissions();
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
            LoadDashBoardTree();
            loadComponentTable(Collections.EMPTY_LIST);
            fromCDNo.setEnabled(false);
            fromCDName.setEnabled(false);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    protected void configureTables() {
        try {

            for (RemoveDiscountDto remove : selecteditemList) {
                removeList.add(remove.getContractNo());
                contractList.add(remove.getContractSid());
                rebateList.add(remove.getRsSid());
            }
            componentResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            componentResultsTable.setPageLength(NumericConstants.FIVE);
            infoLogic.setContainerDataSource(componentResultsContainer);
            infoLogic.setPageLength(NumericConstants.FIVE);
            infoLogic.sinkItemPerPageWithPageLength(false);
            componentResultsTable.setVisibleColumns(Constants.getInstance().componentResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().componentResultsHeaders);
            componentResultsTable.setSelectable(true);
            componentResultsTable.setFilterBarVisible(true);
            componentResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            componentResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            componentResultsTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
            componentResultsTable.setWidth("1050");
            for (Object propertyId : componentResultsTable.getVisibleColumns()) {
                componentResultsTable.setColumnWidth(propertyId, -1);
            }
            Object[] objColumn = Constants.getInstance().componentResultsColumns;
            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    componentResultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                    componentResultsTable.setConverter(value, new DateToStringConverter());
                }
            }
            componentResultsTable.setFilterGenerator(new ExtFilterGenerator() {
                @Override
                public Container.Filter generateFilter(Object propertyId, Object value) {
                    return null;
                }

                @Override
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

                @Override
                public void filterRemoved(Object propertyId) {
                    return;
                }

                @Override
                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                    return;
                }

                @Override
                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }

                @Override
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
            contractComponentTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            contractComponentTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            contractLogic.setContainerDataSource(contractComponentContainer);
            contractLogic.setPageLength(NumericConstants.THIRTEEN);
            contractLogic.sinkItemPerPageWithPageLength(false);
            contractComponentTable.setVisibleColumns(Constants.getInstance().contractColumns);
            contractComponentTable.setColumnHeaders(Constants.getInstance().contractHeaders);
            for (Object propertyId : contractComponentTable.getVisibleColumns()) {
                contractComponentTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_FIVE);
            }

            dashboardTreeTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            dashboardTreeTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            dashboardTreeTable.setPageLength(NumericConstants.FIVE);
            dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
            dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
            dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);
            dashboardTreeTable.setSelectable(true);
            for (Object propertyId : dashboardTreeTable.getVisibleColumns()) {
                dashboardTreeTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
            }

            rsInfoTableLogic.setContainerDataSource(selectedContainer);
            rsInfoTableLogic.setPageLength(NumericConstants.TEN);
            rsInfoTableLogic.sinkItemPerPageWithPageLength(false);
            selectedTable.setVisibleColumns(Constants.getInstance().selectedResultsColumns);
            selectedTable.setColumnHeaders(Constants.getInstance().selectedResultsHeaders);
            selectedTable.markAsDirtyRecursive();
            selectedTable.setImmediate(true);
            selectedTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
            selectedTable.setSelectable(false);
            selectedTable.markAsDirty();
            selectedTable.setComponentError(null);
            selectedTable.setFilterBarVisible(false);
            for (Object propertyId : selectedTable.getVisibleColumns()) {
                selectedTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_FIVE);
            }
            Object[] objColumnSel = Constants.getInstance().selectedResultsColumns;
            for (Object objColumn1 : objColumnSel) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    selectedTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }

            startDate.setDateFormat(Constants.DATE_FORMAT);
            endDate.setDateFormat(Constants.DATE_FORMAT);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void LoadDashBoardTree() {
        LOGGER.debug("Entering getProcessedTree method");
        try {
            final CommonLogic commonLogic = new CommonLogic();

            dashboardTreeTable.markAsDirty();
            dashboardTreeTable.setImmediate(true);
            dashboardTreeTable.setSizeFull();
            dashboardTreeTable.setPageLength(NumericConstants.TEN);
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
                @Override
                public void itemClick(final ItemClickEvent event) {
                    tableBeanId = event.getItemId();
                    BeanItem<?> targetItem;
                    if (tableBeanId instanceof BeanItem<?>) {
                        targetItem = (BeanItem<?>) tableBeanId;
                    } else if (tableBeanId instanceof ContractsDetailsDto) {
                        targetItem = new BeanItem<>((ContractsDetailsDto) tableBeanId);
                    } else {
                        targetItem = NULL_OBJECT;
                    }
                    tableBean = (ContractsDetailsDto) targetItem.getBean();
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of getProcessedTree method");
    }

    private void setProcessedTableHeader() {
        LOGGER.debug("Entering setProcessedTableHeader method");
        dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
        dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);
        LOGGER.debug("End of setProcessedTableHeader method");
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
        @Override
        public void nodeExpand(final Tree.ExpandEvent event) {
            try {
                LOGGER.debug("Entering StplExpandListener nodeExpand method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                contractDetails.setRebateList(rebateList);
                switch (contractDetails.getLevel()) {
                    case ContractsDetailsDto.LEVEL1:
                        configureLevel(event.getItemId());
                        if (contractDetails != null) {
                            dashBoardTreeContainer = commonLogic.getLevel2Hierarchy(contractDetails, dashBoardTreeContainer, null, null, null, null);
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
                LOGGER.debug("End of StplExpandListener nodeExpand method");
            } catch (Exception ex) {
                LOGGER.error(ex);
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
        @Override
        public void nodeCollapse(final Tree.CollapseEvent event) {
            try {
                LOGGER.debug("Entering StplCollapseListener nodeCollapse method");

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
                        levelValue = NumericConstants.TWO;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails.getParent2(), dashBoardTreeContainer, null, null, null));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = NumericConstants.THREE;
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
                LOGGER.debug("End of StplCollapseListener nodeCollapse method");
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
        infoLogic.loadSetData(userId, sessionId, inpuList, "contract", new ContractsDetailsDto(), false);
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
            LOGGER.debug("Entered populateBtnLogic");
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
                AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
                        "Please highlight a row to populate.");
            }

        } catch (Exception e) {
            LOGGER.error(e);
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
            Object[] objColumn = HeaderUtil.getInstance().adComponentDetailsPsColumn;
            if (Constants.IndicatorConstants.CFP.toString().equals(temp.getCategory())) {
                loadCfpFromCD(temp);
                objColumn = HeaderUtil.getInstance().adComponentDetailsCompanyColumn;

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
            AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
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
                    if (rsSidList.isEmpty() || !rsSidList.contains(dto.getRsSystemId())) {
                        rsSidList.add(dto.getRsSystemId());
                    }
                    mainTab.removeTab(mainTab.getTab(1));
                    dto.setRemovedRsList(rsSidList);
                    mainTab.addTab(summary.getContent(selecteditemList, dto, mainTab, removeDiscount), "Summary", null, 1);
                } else {
                    AbstractNotificationUtils.getErrorNotification("Select RS",
                            "Please select RS to remove.");
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
                        "Please highlight a component to Remove.");
            }
        } catch (Exception e) {
            LOGGER.error(e);
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
                List<String> list = new ArrayList(NumericConstants.FIVE);
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
            LOGGER.error(e);
        }
    }

    /**
     * Remove All btn logic.
     *
     * @param event the event
     */
    @UiHandler("removeAllBtn")
    public void removeBtnAllLogic(Button.ClickEvent event) {
        LOGGER.debug("Entered RemoveAll method");
        try {
            logic.getDiscountRsList(rsSidList, contractDto);
            contractDto.setRemovedRsList(rsSidList);
            mainTab.removeTab(mainTab.getTab(1));
            contractDto.setRsSystemId(CommonUtils.CollectionToString(rebateList, true));
            mainTab.addTab(summary.getContent(selecteditemList, contractDto, mainTab, removeDiscount), "Summary", null, 1);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        LOGGER.debug("Ending RemoveAll method");

    }

    private void loadCfpFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<>(CFPComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.CFP);
        contractComponentTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsCompanyColumn);
        contractComponentTable.setColumnHeaders(HeaderUtil.getInstance().adComponentDetailsCompanyHeader);
    }

    private void loadIfpFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.IFP);
        contractComponentTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        contractComponentTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
    }

    private void loadPsFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.PS);
        contractComponentTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        contractComponentTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
    }

    private void loadRsFromCD(final ContractsDetailsDto parent) {
        contractLogic.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        contractLogic.loadSetData(parent, Constants.RS);
        contractComponentTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        contractComponentTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
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

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(getUserId()), "GCM-Customer Management", "Remove Discount", "Discount Selection");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            removeBtn.setVisible(CommonLogic.isButtonVisibleAccess("removeBtn", functionHM));
            removeAllBtn.setVisible(CommonLogic.isButtonVisibleAccess("removeAllBtn", functionHM));
            populateBtn2.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn2", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
