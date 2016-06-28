
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.tp.ui.layout.CustomTPDetailsLayout;
import com.stpl.app.customtreecontainer.CustomTreeContainer;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ComponentInformationDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanyComponentTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.ID;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.CFP;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.COMPANY_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.IFP;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ITEM_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PRICE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PS_VALUE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.REBATE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.RS_VALUE;
import static com.stpl.app.gcm.util.Constants.NAME;
import static com.stpl.app.gcm.util.Constants.NUMBER;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class ContractProcessingDashboard extends CustomTPDetailsLayout {

    @UiField("componentDetailsTableLayout")
    VerticalLayout componentDetailsTableLayout;
    @UiField("addTradingPartnerTableLayout")
    VerticalLayout addTradingPartnerTableLayout;
    @UiField("contractDashboardTableLayout")
    VerticalLayout contractDashboardTableLayout;

    @UiField("cNumber")
    private TextField cNumber;
    @UiField("status")
    private TextField status;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("startDate")
    private TextField startDate;
    @UiField("cId")
    private TextField cId;
    @UiField("cName")
    private TextField cName;
    @UiField("type")
    private TextField type;
    @UiField("basis")
    private TextField basis;
    @UiField("endDate")
    private TextField endDate;

    @UiField("cNumberLabel")
    private Label cNumberLabel;
    @UiField("statusLabel")
    private Label statusLabel;
    @UiField("rebateFrequencyLabel")
    private Label rebateFrequencyLabel;
    @UiField("startDateLabel")
    private Label startDateLabel;
    @UiField("cIdLabel")
    private Label cIdLabel;
    @UiField("cNameLabel")
    private Label cNameLabel;
    @UiField("typeLabel")
    private Label typeLabel;
    @UiField("basisLabel")
    private Label basisLabel;
    @UiField("endDateLabel")
    private Label endDateLabel;

    @UiField("rsTypeLabel")
    private Label rsTypeLabel;
    @UiField("rsType")
    private TextField rsType;

    @UiField("rsProgramTypeLabel")
    private Label rsProgramTypeLabel;
    @UiField("rsProgramType")
    private TextField rsProgramType;

    @UiField("rsCategoryLabel")
    private Label rsCategoryLabel;
    @UiField("rsCategory")
    private TextField rsCategory;

    @UiField("paymentFrequencyLabel")
    private Label paymentFrequencyLabel;
    @UiField("paymentFrequency")
    private TextField paymentFrequency;

    @UiField("rebatePlanLevelLabel")
    private Label rebatePlanLevelLabel;
    @UiField("rebatePlanLevel")
    private TextField rebatePlanLevel;
    @UiField("populate")
    private Button populateBtn;
    @UiField("excelBtn")
    private Button excelBtn;
    @UiField("excelCompBtn")
    private Button excelCompBtn;
    public ExtFilterTable addTradingPartnerTable = new ExtFilterTable();
    public TreeTable contractDashboardTable = new TreeTable();

    final private BeanItemContainer<ComponentInformationDTO> componentInformationContainer = new BeanItemContainer<ComponentInformationDTO>(ComponentInformationDTO.class);
    private BeanItemContainer<ContractResultDTO> selectedContractContainer = new BeanItemContainer<ContractResultDTO>(ContractResultDTO.class);
    private ExtTreeContainer<ContractsDetailsDto> contractDashboardContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    boolean contractRefresh;

    public List parentList = new ArrayList();
    /**
     * The contract member.
     */
    private ContractsDetailsDto contractDetails;
    /**
     * The expand listener.
     */
    private final ContractProcessingDashboard.StplExpandListener expandListener = new ContractProcessingDashboard.StplExpandListener();
    /**
     * The collapse listener.
     */
    private final ContractProcessingDashboard.StplCollapseListener collapseListener = new ContractProcessingDashboard.StplCollapseListener();
    /**
     * The table bean.
     */
    private ContractsDetailsDto tableBean;
    private static final BeanItem<?> NULL_OBJECT = null;
    /* Current Level Value */
    public int levelValue;
    private CustomTreeContainer<RemoveDiscountDto> componentResultsContainer = new CustomTreeContainer<RemoveDiscountDto>(RemoveDiscountDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    private static final Logger LOGGER = Logger.getLogger(ContractProcessingDashboard.class);
    SessionDTO session = new SessionDTO();
    RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    private CustomFieldGroup discountChBinder;
    private ExtPagedTable resultsTable;
    CommmonLogic logic = new CommmonLogic();

    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    public List<ComponentInformationDTO> componentInformation = new ArrayList<ComponentInformationDTO>();
    private ExtCustomTable exportPeriodViewTable;
    private CustomTreeContainer<ComponentInformationDTO> excelResultBean = new CustomTreeContainer<ComponentInformationDTO>(ComponentInformationDTO.class);
    public String excelName = "Rebate Schedule Information";
    CompanyComponentTableLogic tablelogic = new CompanyComponentTableLogic();
    public ExtPagedTable componentInformationTable = new ExtPagedTable(tablelogic);

    public ContractProcessingDashboard(BeanItemContainer<ContractResultDTO> selectedContractContainer, SessionDTO session) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/contractProcessingDashboard.xml"), this));
        this.session = session;
        this.selectedContractContainer = selectedContractContainer;
        configureFields();
    }

    public void configureFields() {
        componentDetailsTableLayout.addComponent(componentInformationTable);
        addTradingPartnerTableLayout.addComponent(addTradingPartnerTable);
        addTradingPartnerTableLayout.setSizeFull();
        contractDashboardTableLayout.addComponent(contractDashboardTable);
        contractDashboardTableLayout.setSizeFull();
        excelBtn.setIcon(excelExportImage);
        excelCompBtn.setIcon(excelExportImage);
        configureComponentDetailsTable();
        configureAddTradingPartnerTable();
        configureContractDashboardTable();

        status.setEnabled(false);
        startDate.setEnabled(false);
        endDate.setEnabled(false);

        rebateFrequency.setEnabled(false);
        basis.setEnabled(false);

    }

    public void configureComponentDetailsTable() {

        componentInformationTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentInformationTable.setWidth(100, Unit.PERCENTAGE);
        componentInformationTable.setHeight(300, Unit.PIXELS);
        tablelogic.setPageLength(5);
        tablelogic.sinkItemPerPageWithPageLength(false);
        tablelogic.setContainerDataSource(componentInformationContainer);
        componentDetailsTableLayout.addComponent(tablelogic.createControls());
        componentInformationTable.setContainerDataSource(componentInformationContainer);
        componentInformationTable.setFilterBarVisible(true);
        componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
        componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
        componentInformationTable.setFilterBarVisible(Boolean.TRUE);
        componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());
        for (Object object : componentInformationTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                componentInformationTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
    }

    public void configureAddTradingPartnerTable() {
        addTradingPartnerTable.setContainerDataSource(selectedContractContainer);

        addTradingPartnerTable.setVisibleColumns(Constants.SUMMARY_CONTRACT_SELECTION_COLUMNS);
        addTradingPartnerTable.setColumnHeaders(Constants.SUMMARY_CONTRACT_SELECTION_HEADERS);
        addTradingPartnerTable.setImmediate(true);
        addTradingPartnerTable.setWidth(500, Unit.PIXELS);
        addTradingPartnerTable.setHeight(400, Unit.PIXELS);
        addTradingPartnerTable.setPageLength(6);
        addTradingPartnerTable.setEditable(true);
        addTradingPartnerTable.setSelectable(true);
        addTradingPartnerTable.setSizeFull();
        addTradingPartnerTable.setFilterBarVisible(Boolean.TRUE);
        addTradingPartnerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        addTradingPartnerTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            int count = logic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, StringUtils.EMPTY, session);

                        }
                    });
                    return check;
                }

                if (propertyId.equals("compStartDate")) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setDateFormat(Constants.MM_DD_YYYY);
                    compStartDate.setStyleName("dateFieldCenter");
                    compStartDate.setImmediate(true);

                    return compStartDate;
                }

                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    compEndDate.setStyleName("dateFieldCenter");

                    compEndDate.setImmediate(true);

                    return compEndDate;
                }
                if (propertyId.equals("contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    contEndDate.setStyleName("dateFieldCenter");

                    contEndDate.setEnabled(false);

                    return contEndDate;
                }

                if (propertyId.equals("contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat(Constants.MM_DD_YYYY);
                    contStartDate.setStyleName("dateFieldCenter");

                    contStartDate.setEnabled(false);
                    return contStartDate;
                }

                return null;
            }
        });

        for (Object object : addTradingPartnerTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                addTradingPartnerTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        addTradingPartnerTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                LoadDashBoardTree();
                componentInformationContainer.removeAllItems();
                tableBeanId = null;
            }
        });

    }

    public void configureContractDashboardTable() {
        contractDashboardTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardTable.setWidth(527, Unit.PIXELS);
        contractDashboardTable.setHeight(300, Unit.PIXELS);
        contractDashboardTable.setPageLength(7);
        contractDashboardTable.setContainerDataSource(contractDashboardContainer);
        contractDashboardTable.setVisibleColumns(Constants.ADDTP_TREE_COLUMNS);
        contractDashboardTable.setColumnHeaders(Constants.TREE_HEADERS);
        contractDashboardTable.markAsDirty();
        contractDashboardTable.setImmediate(true);
        contractDashboardTable.setSizeFull();
        contractDashboardTable.removeAllItems();
        parentList.clear();
        levelValue = 0;

        contractDashboardTable.addExpandListener(expandListener);
        contractDashboardTable.addCollapseListener(collapseListener);
        contractDashboardTable.setSelectable(true);
        contractDashboardTable.setColumnHeaders(new String[]{"Category", "ID", "Number", "Name"});
        contractDashboardTable.setVisibleColumns(Constants.ADDTP_TREE_COLUMNS);

        contractDashboardTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
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
        contractDashboardTable.setSizeFull();
        LOGGER.info("End of getProcessedTree method");

        for (Object propertyId : contractDashboardTable.getVisibleColumns()) {
            contractDashboardTable.setColumnWidth(propertyId, 120);
        }

    }

    private void LoadDashBoardTree() {
        try {

            LOGGER.info("Entering getProcessedTree method");
            contractDashboardContainer.removeAllItems();

            contractDashboardContainer = logic.loadContainerData(logic.getDasboardResults(logic.getLevelOneHierarchy(session.getUserId(), session.getSessionId()), 1, 0, 0, 0, 0, null, null, null, null), contractDashboardContainer, null);

            contractDashboardTable.setContainerDataSource(contractDashboardContainer);

            setProcessedTableHeader();

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void setProcessedTableHeader() {
        LOGGER.info("Entering setProcessedTableHeader method");
        contractDashboardTable.setVisibleColumns(Constants.ADDTP_TREE_COLUMNS);
        contractDashboardTable.setColumnHeaders(Constants.TREE_HEADERS);
        LOGGER.info("End of setProcessedTableHeader method");
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
        /**
         * Gets the contract dashboard logic.
         *
         * @return the contract dashboard logic
         */
        /**
         * Node Expand Event
         *
         */
        public void nodeExpand(final Tree.ExpandEvent event) {
            try {
                LOGGER.info("Entering StplExpandListener nodeExpand method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                switch (contractDetails.getLevel()) {
                    case ContractsDetailsDto.LEVEL1:
                        configureLevel(event.getItemId());

                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelTwoHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid()), 2, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails, null, null, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();

                        break;
                    case ContractsDetailsDto.LEVEL2:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelThreeHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId()), 3, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails, null, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFourHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId()), 4, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFiveHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId()), 5, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent3(), false);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
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

                        contractDashboardContainer = logic.loadContainerData(logic.getDasboardResults(logic.getLevelOneHierarchy(session.getUserId(), session.getSessionId()), 1, 0, 0, 0, 0, null, null, null, null), contractDashboardContainer, null);

                        contractDashboardTable.setContainerDataSource(contractDashboardContainer);
                        setProcessedTableHeader();

                        break;
                    case ContractsDetailsDto.LEVEL2:
                        levelValue = 1;
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelTwoHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid()), 2, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent1()));

                        contractDashboardTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        levelValue = 2;
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelThreeHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId()), 3, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent2()));

                        contractDashboardTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = 3;
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFourHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId()), 4, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent3()));

                        contractDashboardTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent3(), false);
                        contractDashboardTable.addExpandListener(expandListener);
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
        while (!contractDashboardTable.getContainerDataSource().isRoot(item)) {
            parentList.add(item);
            item = contractDashboardTable.getContainerDataSource().getParent(item);
            levelValue++;
        }
        parentList.add(item);
        Collections.reverse(parentList);
        return levelValue;
    }

    /**
     * To load the Component Information table
     *
     * @param componentSelectionValue
     * @param tableData
     */
    private void loadComponentInformationTable(String componentSelectionValue) {
        componentInformationContainer.removeAllItems();

        if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_PS);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_PS);
        } else if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_CFP);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_CFP);
        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_IFP);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_IFP);
        }
        componentInformationTable.setFilterBarVisible(Boolean.TRUE);
        componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());

    }

    private void loadComponentInformation(String category) {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        if (tableBean != null) {
            String[] id = new String[5];
            ContractsDetailsDto crDTO = tableBean;
            id[0] = String.valueOf(crDTO.getContractSid());
            id[1] = String.valueOf(crDTO.getCfpContractId());
            id[2] = String.valueOf(crDTO.getIfpContractId());
            id[3] = String.valueOf(crDTO.getPsContractId());
            id[4] = String.valueOf(crDTO.getRsContractSId());

            if (category.equals(CFP.getConstant())) {
                category = COMPANY_FAMILY_PLAN.getConstant();
            } else if (category.equals(IFP.getConstant())) {
                category = ITEM_FAMILY_PLAN.getConstant();
            } else if (category.equals(PS_VALUE.getConstant())) {
                category = PRICE_SCHEDULE.getConstant();
            } else if (category.equals(RS_VALUE.getConstant())) {
                category = REBATE_SCHEDULE.getConstant();
            }
            changeOnListener(category);
            loadComponentInformationFields(Arrays.asList(logic.getComponentInformationData(category, id, false, false, 0, 0, null).get(0)));
            loadComponentInformationTable(category);
            tablelogic.loadSetData(category, id, Boolean.TRUE);
        }
    }

    /**
     * To load the Fields inside the component information panel
     *
     * @param componentSelectionValue
     * @param fieldData
     */
    private void loadComponentInformationFields(List<Object> fieldData) {
        if (fieldData != null && !fieldData.isEmpty()) {

            try {
                cId.setValue(getFromList(fieldData, 0));
                cNumber.setValue(getFromList(fieldData, 1));
                cName.setValue(getFromList(fieldData, 2));
                status.setValue(getFromList(fieldData, 3));
                startDate.setValue(formatDate(getFromList(fieldData, 4)));
                endDate.setValue(formatDate(getFromList(fieldData, 5)));
                type.setValue(getFromList(fieldData, 6));
                rebateFrequency.setValue(getFromList(fieldData, 7));
                basis.setValue(getFromList(fieldData, 8));
                rsType.setValue(getFromList(fieldData, 9));
                rsProgramType.setValue(getFromList(fieldData, 10));
                rsCategory.setValue(getFromList(fieldData, 11));
                paymentFrequency.setValue(getFromList(fieldData, 12));
                rebatePlanLevel.setValue(getFromList(fieldData, 13));

            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
    }

    private String getFromList(List<Object> fieldList, int number) {
        String fieldvalue = StringUtils.EMPTY;
        try {
            if (fieldList != null && fieldList.get(number) != null) {
                fieldvalue = String.valueOf(fieldList.get(number));
                if (Constants.SELECT_ONE.equals(fieldvalue)) {
                    fieldvalue = StringUtils.EMPTY;
                }
            }
        } catch (IndexOutOfBoundsException ie) {
                   LOGGER.error(ie.getMessage());
        }
        return fieldvalue;
    }

    @UiHandler("remove")
    public void currentRemoveButtonClickListener(Button.ClickEvent event) {
        removeCheckedItems(selectedContractContainer);
        contractRefresh = true;
    }

    @UiHandler("removeContract")
    public void currentContractRemoveButtonClickListener(Button.ClickEvent event) {
        if (contractDashboardTable.getValue() != null) {
            new AbstractNotificationUtils() {
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        contractDashboardContainer.removeItem(contractDashboardTable.getValue());
                        contractDashboardTable.setValue(null);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }.getConfirmationMessage("Confirm Remove", "Are you sure you want to remove the selected Contract from the Add Customer process? \n It will be removed and added back to the Available List of Contracts in the Contract Selection screen.");

        } else {
            AbstractNotificationUtils.getErrorNotification("No Value Selected", "Please select a value to remove.\n Then try again.");
            return;
            //No Records selected to remove
        }
    }

    private void removeCheckedItems(final BeanItemContainer<ContractResultDTO> tpDetailsContainer) {
        final List<ContractResultDTO> checkedContracts = new ArrayList<ContractResultDTO>();
        final ContractSelectionLogic csLogic = new ContractSelectionLogic();
        List<ContractResultDTO> tableRecords = tpDetailsContainer.getItemIds();
        for (ContractResultDTO contract : tableRecords) {
            if (contract.getCheckRecord()) {
                checkedContracts.add(contract);
            }
        }
        if (checkedContracts.size() > 0) {

            new AbstractNotificationUtils() {
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        for (ContractResultDTO contract : checkedContracts) {
                            tpDetailsContainer.removeItem(contract);
                        }
                        csLogic.updateSubmitFlag(session.getModuleName(), StringUtils.EMPTY, session.getUserId(), session.getSessionId(), false);
                        LoadDashBoardTree();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }.getConfirmationMessage("Confirm Remove", "Are you sure you want to remove the selected Contract from the Add Customer process? \n It will be removed and added back to the Available List of Contracts in the Contract Selection screen.");

        } else {
            AbstractNotificationUtils.getErrorNotification("No Value Selected", "Please select a value to remove.\n Then try again.");
            return;
            //No Records selected to remove
        }

    }

    @Override
    public boolean isCurrentContractRefresh() {
        return contractRefresh;
    }

    @Override
    public boolean isTransferContractRefresh() {
        return false;
    }

    @Override
    public void setCurrentContractRefresh(boolean currentContractRefresh) {
        contractRefresh = currentContractRefresh;
    }

    @Override
    public void setTransferContractRefresh(boolean transferContractRefresh) {

    }

    @UiHandler("populate")
    public void populateButtonLogic(Button.ClickEvent event) {
        CommmonLogic logic = new CommmonLogic();
        if (!tableBean.getCategory().equals(CONTRACT.getConstant())) {
            if (tableBean != null) {
                try {
                    loadComponentInformation(tableBean.getCategory());

                } catch (Exception ex) {
                   LOGGER.error(ex);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Level Selected",
                        "Please select a Level to populate the Component Information.");
            }
        }
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {

            
            List tempVisibleHeaders = new ArrayList(Arrays.asList(Constants.EXCEL_CONTRACT_SELECTION_HEADERS));
            tempVisibleHeaders.remove(0);
            tempVisibleHeaders.remove(0);
            String[] visibleHeaders = Arrays.copyOf(tempVisibleHeaders.toArray(), tempVisibleHeaders.size(), String[].class);

            List list = logic.getSubmittedRecords(session, StringUtils.EMPTY, true);
            int recordCount = CommonUtils.convertToInteger(String.valueOf(list.get(0)));
            ExcelExportforBB.createWorkSheet(visibleHeaders, recordCount, this, UI.getCurrent(), "Add Customer Results");
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    @UiHandler("excelCompBtn")
    public void componentInfoExport(Button.ClickEvent event) {
        try{
        CsvExportforPagedTable.createWorkSheet(componentInformationTable.getColumnHeaders(), componentInformationTable.getVisibleColumns(), tablelogic, excelName);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    public void changeOnListener(String componentSelection) {

        if (componentSelection.equals(COMPANY_FAMILY_PLAN.getConstant())) {
            cIdLabel.setCaption("CFP " + ID);
            cNumberLabel.setCaption("CFP " + NUMBER);
            cNameLabel.setCaption("CFP " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Company Family Plan Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(ITEM_FAMILY_PLAN.getConstant())) {
            cIdLabel.setCaption("IFP " + ID);
            cNumberLabel.setCaption("IFP " + NUMBER);
            cNameLabel.setCaption("IFP " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Item Family Plan Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(PRICE_SCHEDULE.getConstant())) {
            cIdLabel.setCaption("PS " + ID);
            cNumberLabel.setCaption("PS " + NUMBER);
            cNameLabel.setCaption("PS " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Price Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(REBATE_SCHEDULE.getConstant())) {
            cIdLabel.setCaption("RS " + ID);
            cNumberLabel.setCaption("RS " + NUMBER);
            cNameLabel.setCaption("RS " + NAME);
            setComponentInformationVisibility(true);
            excelName = "Rebate Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        }

    }

    private void setComponentInformationVisibility(boolean value) {
        basisLabel.setVisible(value);
        basis.setVisible(value);

        rebateFrequencyLabel.setVisible(value);
        rebateFrequency.setVisible(value);

        rsTypeLabel.setVisible(value);
        rsType.setVisible(value);

        rsProgramTypeLabel.setVisible(value);
        rsProgramType.setVisible(value);

        rsCategoryLabel.setVisible(value);
        rsCategory.setVisible(value);

        paymentFrequencyLabel.setVisible(value);
        paymentFrequency.setVisible(value);

        rebatePlanLevelLabel.setVisible(value);
        rebatePlanLevel.setVisible(value);
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheetContent with start " + start + " end " + end);
        try {
            List tempVisibleColumns = new ArrayList(Arrays.asList(Constants.EXCEL_CONTRACT_SELECTION_COLUMNS));
            tempVisibleColumns.remove(0);
            tempVisibleColumns.remove(0);
            Object[] visibleColumns = tempVisibleColumns.toArray();
            
            List checkedContractList = new ArrayList();
            if (end > 0) {
                checkedContractList = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, StringUtils.EMPTY, false));
            }
            ExcelExportforBB.createFileContent(visibleColumns, checkedContractList, printWriter);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
