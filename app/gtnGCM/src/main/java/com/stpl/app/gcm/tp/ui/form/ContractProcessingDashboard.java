
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.tp.ui.layout.CustomTPDetailsLayout;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.security.StplSecurity;
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
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.ifs.util.ExcelExportforBB;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Tree;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
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
    @UiField("removeContract")
    private Button removeContract;
    @UiField("remove")
    private Button remove;
    public ExtFilterTable addTradingPartnerTable = new ExtFilterTable();
    public TreeTable contractDashboardTable = new TreeTable();
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<>();

    final private BeanItemContainer<ComponentInformationDTO> componentInformationContainer = new BeanItemContainer<>(ComponentInformationDTO.class);
    private BeanItemContainer<ContractResultDTO> selectedContractContainer = new BeanItemContainer<>(ContractResultDTO.class);
    private ExtTreeContainer<ContractsDetailsDto> contractDashboardContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
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
    CommmonLogic logic = new CommmonLogic();

    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    public List<ComponentInformationDTO> componentInformation = new ArrayList<>();
    private ExtTreeContainer<ComponentInformationDTO> excelResultBean = new ExtTreeContainer<>(ComponentInformationDTO.class);
    public String excelName = "Rebate Schedule Information";
    CompanyComponentTableLogic tablelogic = new CompanyComponentTableLogic();
    public ExtPagedTable componentInformationTable = new ExtPagedTable(tablelogic);

    public ContractProcessingDashboard(BeanItemContainer<ContractResultDTO> selectedContractContainer, SessionDTO session) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/contractProcessingDashboard.xml"), this));
        this.session = session;
        this.selectedContractContainer = selectedContractContainer;
        configureFields();
        configureSecurityPermissions();
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
        componentInformationTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentInformationTable.setHeight(NumericConstants.THREE_HUNDRED, Unit.PIXELS);
        tablelogic.setPageLength(NumericConstants.FIVE);
        tablelogic.sinkItemPerPageWithPageLength(false);
        tablelogic.setContainerDataSource(componentInformationContainer);
        componentDetailsTableLayout.addComponent(tablelogic.createControls());
        componentInformationTable.setContainerDataSource(componentInformationContainer);
        componentInformationTable.setFilterBarVisible(true);
        componentInformationTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsRs);
        componentInformationTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersRs);
        componentInformationTable.setFilterBarVisible(Boolean.TRUE);
        componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());
        componentInformationTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (propertyId.equals(Constants.STATUS_S)) {
                    try {
                        ComboBox status = new ComboBox();
                        CommonUtil.loadComboBoxForGCM(status, Constants.STATUS, true);
                        return status;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
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

            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }
        });
        componentInformationTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.STATUS_S)) {
                    try {
                        ComboBox status = new ComboBox();
                        CommonUtil.loadComboBoxForGCM(status, Constants.STATUS, true);
                        return status;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                } 
                return null;
            }
        });
        for (Object object : componentInformationTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                componentInformationTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
    }

    public void configureAddTradingPartnerTable() {
        addTradingPartnerTable.setContainerDataSource(selectedContractContainer);

        addTradingPartnerTable.setVisibleColumns(Constants.getInstance().summaryContractSelectionColumns);
        addTradingPartnerTable.setColumnHeaders(Constants.getInstance().summaryContractSelectionHeaders);
        addTradingPartnerTable.setWidth(NumericConstants.FIVE_HUNDRED, Unit.PIXELS);
        addTradingPartnerTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        addTradingPartnerTable.setPageLength(NumericConstants.SIX);
        addTradingPartnerTable.setEditable(true);
        addTradingPartnerTable.setSelectable(true);
        addTradingPartnerTable.setSizeFull();
        addTradingPartnerTable.setFilterBarVisible(Boolean.TRUE);
        addTradingPartnerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        addTradingPartnerTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            logic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, StringUtils.EMPTY, session);

                        }
                    });
                    return check;
                }

                if (propertyId.equals("compStartDate")) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setDateFormat(Constants.MM_DD_YYYY);
                    compStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

                    return compStartDate;
                }

                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    compEndDate.setStyleName(Constants.DATE_FIELD_CENTER);


                    return compEndDate;
                }
                if (propertyId.equals("contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    contEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

                    contEndDate.setEnabled(false);

                    return contEndDate;
                }

                if (propertyId.equals("contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat(Constants.MM_DD_YYYY);
                    contStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

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
        addTradingPartnerTable.setFilterFieldVisible(Constants.CHECK_RECORD, false);

    }

    public void configureContractDashboardTable() {
        contractDashboardTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardTable.setWidth(NumericConstants.FIVE_TWO_SEVEN, Unit.PIXELS);
        contractDashboardTable.setHeight(NumericConstants.THREE_HUNDRED, Unit.PIXELS);
        contractDashboardTable.setPageLength(NumericConstants.SEVEN);
        contractDashboardTable.setContainerDataSource(contractDashboardContainer);
        contractDashboardTable.setVisibleColumns(Constants.getInstance().addtpTreeColumns);
        contractDashboardTable.setColumnHeaders(Constants.getInstance().treeHeaders);
        contractDashboardTable.markAsDirty();
        contractDashboardTable.setSizeFull();
        contractDashboardTable.removeAllItems();
        parentList.clear();
        levelValue = 0;

        contractDashboardTable.addExpandListener(expandListener);
        contractDashboardTable.addCollapseListener(collapseListener);
        contractDashboardTable.setSelectable(true);
        contractDashboardTable.setColumnHeaders(new String[]{"Category", "ID", "Number", "Name"});
        contractDashboardTable.setVisibleColumns(Constants.getInstance().addtpTreeColumns);

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
                    targetItem = new BeanItem<>((ContractsDetailsDto) tableBeanId);
                } else {
                    targetItem = NULL_OBJECT;
                }
                tableBean = (ContractsDetailsDto) targetItem.getBean();

            }
        });
        contractDashboardTable.setSizeFull();
        LOGGER.debug("End of getProcessedTree method");

        for (Object propertyId : contractDashboardTable.getVisibleColumns()) {
            contractDashboardTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
        }

    }

    private void LoadDashBoardTree() {
        LOGGER.debug("Entering getProcessedTree method");
        contractDashboardContainer.removeAllItems();
        contractDashboardContainer = logic.loadContainerData(logic.getDasboardResults(logic.getLevelOneHierarchy(session.getUserId(), session.getSessionId()), 1, 0, 0, 0, 0, null, null, null, null), contractDashboardContainer, null);
        contractDashboardTable.setContainerDataSource(contractDashboardContainer);
        setProcessedTableHeader();
    }

    private void setProcessedTableHeader() {
        LOGGER.debug("Entering setProcessedTableHeader method");
        contractDashboardTable.setVisibleColumns(Constants.getInstance().addtpTreeColumns);
        contractDashboardTable.setColumnHeaders(Constants.getInstance().treeHeaders);
        LOGGER.debug("End of setProcessedTableHeader method");
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
                LOGGER.debug("Entering StplExpandListener nodeExpand method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                switch (contractDetails.getLevel()) {
                    case ContractsDetailsDto.LEVEL1:
                        configureLevel(event.getItemId());

                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelTwoHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid()), NumericConstants.TWO, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails, null, null, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();

                        break;
                    case ContractsDetailsDto.LEVEL2:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelThreeHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId()), NumericConstants.THREE, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails, null, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFourHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId()), NumericConstants.FOUR, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails, null), contractDashboardContainer, contractDetails));
                        contractDashboardTable.removeExpandListener(expandListener);
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.setCollapsed(contractDetails, false);
                        contractDashboardTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        configureLevel(event.getItemId());
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFiveHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId()), NumericConstants.FIVE, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails), contractDashboardContainer, contractDetails));
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
         * Method used to node collapse and its event.
         *
         * @param event the event
         */
        public void nodeCollapse(final Tree.CollapseEvent event) {
            try {
                LOGGER.debug("Entering StplCollapseListener nodeCollapse method");

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
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelTwoHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid()), NumericConstants.TWO, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent1()));

                        contractDashboardTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        levelValue = NumericConstants.TWO;
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelThreeHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId()), NumericConstants.THREE, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent2()));

                        contractDashboardTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        contractDashboardTable.setCollapsed(contractDetails.getParent1(), false);
                        contractDashboardTable.setCollapsed(contractDetails.getParent2(), false);
                        contractDashboardTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = NumericConstants.THREE;
                        contractDashboardTable.setContainerDataSource(logic.loadContainerData(logic.getDasboardResults(logic.getLevelFourHierarchy(session.getUserId(), session.getSessionId(), contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId()), NumericConstants.FOUR, contractDetails.getContractSid(), contractDetails.getCfpContractId(), contractDetails.getIfpContractId(), contractDetails.getPsContractId(), contractDetails.getParent1(), contractDetails.getParent2(), contractDetails.getParent3(), contractDetails.getParent4()), contractDashboardContainer, contractDetails.getParent3()));

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
            componentInformationTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsRs);
            componentInformationTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersRs);
        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsPs);
            componentInformationTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersPs);
        } else if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsCfp);
            componentInformationTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersCfp);
        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsIfp);
            componentInformationTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersIfp);
        }
        componentInformationTable.setFilterBarVisible(Boolean.TRUE);
        componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());

    }

    private void loadComponentInformation(String category) {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        if (tableBean != null) {
            String[] id = new String[NumericConstants.FIVE];
            ContractsDetailsDto crDTO = tableBean;
            id[0] = String.valueOf(crDTO.getContractSid());
            id[1] = String.valueOf(crDTO.getCfpContractId());
            id[NumericConstants.TWO] = String.valueOf(crDTO.getIfpContractId());
            id[NumericConstants.THREE] = String.valueOf(crDTO.getPsContractId());
            id[NumericConstants.FOUR] = String.valueOf(crDTO.getRsContractSId());

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
                cName.setValue(getFromList(fieldData, NumericConstants.TWO));
                status.setValue(getFromList(fieldData, NumericConstants.THREE));
                startDate.setValue(formatDate(getFromList(fieldData, NumericConstants.FOUR)));
                endDate.setValue(formatDate(getFromList(fieldData, NumericConstants.FIVE)));
                type.setValue(getFromList(fieldData, NumericConstants.SIX));
                rebateFrequency.setValue(getFromList(fieldData, NumericConstants.SEVEN));
                basis.setValue(getFromList(fieldData, NumericConstants.EIGHT));
                rsType.setValue(getFromList(fieldData, NumericConstants.NINE));
                rsProgramType.setValue(getFromList(fieldData, NumericConstants.TEN));
                rsCategory.setValue(getFromList(fieldData, NumericConstants.ELEVEN));
                paymentFrequency.setValue(getFromList(fieldData, NumericConstants.TWELVE));
                rebatePlanLevel.setValue(getFromList(fieldData, NumericConstants.THIRTEEN));

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
            LOGGER.error(ie);
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
                        LOGGER.error(ex);
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
        final List<ContractResultDTO> checkedContracts = new ArrayList<>();
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
                        LOGGER.error(ex);
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
        return;
    }

    @UiHandler("populate")
    public void populateButtonLogic(Button.ClickEvent event) {
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

            List tempVisibleHeaders = new ArrayList(Arrays.asList(Constants.getInstance().excelContractSelectionHeaders));
            tempVisibleHeaders.remove(0);
            tempVisibleHeaders.remove(0);
            String[] visibleHeaders = Arrays.copyOf(tempVisibleHeaders.toArray(), tempVisibleHeaders.size(), String[].class);

            List list = logic.getSubmittedRecords(session, StringUtils.EMPTY, true);
            int recordCount = CommonUtils.convertToInteger(String.valueOf(list.get(0)));
            ExcelExportforBB.createWorkSheet(visibleHeaders, recordCount, this, UI.getCurrent(), "Add_Customer_Results");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("excelCompBtn")
    public void componentInfoExport(Button.ClickEvent event) {
        try {
            CsvExportforPagedTable.createWorkSheet(componentInformationTable.getColumnHeaders(), componentInformationTable.getVisibleColumns(), tablelogic, excelName.replace(" ", "_"));
        } catch (Exception e) {
            LOGGER.error(e);
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

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
        LOGGER.debug("Entering createWorkSheetContent with start " + start + " end " + end);
        try {
            List tempVisibleColumns = new ArrayList(Arrays.asList(Constants.getInstance().excelContractSelectionColumns));
            tempVisibleColumns.remove(0);
            tempVisibleColumns.remove(0);
            Object[] visibleColumns = tempVisibleColumns.toArray();

            List checkedContractList = new ArrayList();
            if (end > 0) {
                checkedContractList = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, StringUtils.EMPTY, false));
            }
            ExcelExportforBB.createFileContent(visibleColumns, checkedContractList, printWriter);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureSecurityPermissions() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Add Customer", "Add Customer Screen");
            removeContract.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn2", functionHM));
            remove.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn2", functionHM));

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

}
