/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.discount.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.ExistingTabSearchTableLogic;
import com.stpl.app.gcm.discount.logic.ExistingTabSelectedTableLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.RsDetails;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Tree;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.gcm.discount.ui.form.NewDiscountTab.DB_DATE;

/**
 *
 * @author santanukumar
 */
public class ExistingDiscountTab extends CustomComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExistingDiscountTab.class);
    private final ExistingTabSearchTableLogic availableTableLogic = new ExistingTabSearchTableLogic();
    private final ExistingTabSelectedTableLogic selectedTableLogic = new ExistingTabSelectedTableLogic();
    private final ExtPagedTable componentResultsTable = new ExtPagedTable(availableTableLogic);
    private final ExtPagedTable componentDetailsSelectedItem = new ExtPagedTable(selectedTableLogic);
    private final CommonLogic logic = new CommonLogic();
    @UiField("levelDetailsResultsTable")
    private  ExtFilterTable levelDetailsResultsTable;
    @UiField("dashboardResultsTable")
    private TreeTable dashboardTreeTable;

    @UiField("componentTypeDdlb")
    public ComboBox componentTypeDdlb;
    @UiField("searchFieldDdlb")
    private ComboBox searchFieldDdlb;
    @UiField("valueTxt")
    private TextField valueTxt;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("levelPopulateBtn")
    private Button levelPopulateBtn;
    @UiField("addToTree")
    private Button addToTree;
    @UiField("populateBtn")
    private Button populateBtn;
    @UiField("rsId")
    private TextField rsId;
    @UiField("statusDdlb")
    private TextField statusDdlb;
    @UiField("rebateFrequencyDdlb")
    private TextField rebateFrequencyDdlb;
    @UiField("rsNumber")
    private TextField rsNumber;
    @UiField("startDate_")
    private TextField startDate_;
    @UiField("rarDdlb")
    private TextField rarDdlb;
    @UiField("rsName")
    private TextField rsName;
    @UiField("rsEndDate")
    private TextField rsEndDate;
    @UiField("basicDdlb")
    private TextField basicDdlb;

    @UiField("contractNo")
    private TextField contractNo;
    @UiField("contractName")
    private TextField contractName;
    @UiField("contractType")
    private TextField contractType;
    @UiField("startDate")
    private TextField startDate;
    @UiField("endDate")
    private TextField endDate;
    @UiField("resultsComponentTypeDdlb")
    private ComboBox resultsComponentTypeDdlb;
    @UiField("searchValueStatusDdlb")
    private ComboBox searchValueStatusDdlb;
    @UiField("componentResultsTable")
    private VerticalLayout availableLayout;
    @UiField("componentDetailsSelectedItem")
    private VerticalLayout selectedLayout;
    @UiField("levelRemoveBtn")
    private Button levelRemoveBtn;

    @UiField("fromCDLabelNo")
    private Label fromCDLabelNo;

    @UiField("fromCDNo")
    private TextField fromCDNo;

    @UiField("fromCDLabelName")
    private Label fromCDLabelName;

    @UiField("fromCDName")
    private TextField fromCDName;
    @UiField("componentInformationID")
    private Label componentInformationID;
    @UiField("componentInformationNo")
    private Label componentInformationNo;
    @UiField("componentInformationName")
    private Label componentInformationName;
    @UiField("rebateFrequency")
    private Label rebateFrequency;
    @UiField("rarType")
    private Label rarType;
    @UiField("rsBasic")
    private Label rsBasic;
    /* Current Level Value */
    private int levelValue;
    private Object treeBeanId;
    private final List<Integer> newlyAddedRebates = new ArrayList<>();
    private final CommonUtil commonUtil = CommonUtil.getInstance();

    private final List parentList = new ArrayList();
    private final List<RemoveDiscountDto> removeDiscountDto;
    /**
     * The table bean.
     */
    private ContractsDetailsDto tableBean;
    /**
     * The expand listener.
     */
    private final StplExpandListener expandListener = new StplExpandListener();
    /**
     * The collapse listener.
     */
    private final StplCollapseListener collapseListener = new StplCollapseListener();
    private static final BeanItem<?> NULL_OBJECT = null;
    /**
     * The contract member.
     */
    private ContractsDetailsDto contractDetails;
    private final BeanItemContainer<ContractsDetailsDto> componentResultsContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
    private final BeanItemContainer<ContractsDetailsDto> selectedContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    private final BeanItemContainer<ContractsDetailsDto> availableItemContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    private ContractsDetailsDto newDiscountTabDto = new ContractsDetailsDto();
    private final List<Integer> rebateList = new ArrayList<>();
    private List<HelperDTO> itemStatusList = new ArrayList<>();
    private final List<ContractsDetailsDto> cfpList = new ArrayList<>();
    private final List<ContractsDetailsDto> ifpList = new ArrayList<>();
    private final List<ContractsDetailsDto> psList = new ArrayList<>();
    private final List<ContractsDetailsDto> rsList = new ArrayList<>();
    private List<ContractsDetailsDto> ifpListforMap;
    private List<ContractsDetailsDto> psListforMap;
    private List<ContractsDetailsDto> rsListforMap;
    private final StplSecurity stplSecurity = new StplSecurity();
    private final SessionDTO session;

    public ExistingDiscountTab(List<RemoveDiscountDto> removeDiscountDto,SessionDTO session) {
        this.removeDiscountDto = removeDiscountDto == null ? removeDiscountDto : new ArrayList<>(removeDiscountDto);
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/existingDiscountTab.xml"), this));
        configureFields();
        configureSecurityPermissions();
    }

    protected void configureFields() {
        try {
            startDate.addStyleName("v-align-center");
            endDate.addStyleName("v-align-center");
            contractNo.setValue(removeDiscountDto.get(0).getContractNo());
            contractName.setValue(removeDiscountDto.get(0).getContractName());
            contractType.setValue(removeDiscountDto.get(0).getMarketType());
            startDate.setValue(removeDiscountDto.get(0).getContractstartDate() == null ? StringUtils.EMPTY : DB_DATE.format((Date) removeDiscountDto.get(0).getContractstartDate()));
            endDate.setValue(removeDiscountDto.get(0).getContractendDate() == null ? StringUtils.EMPTY : DB_DATE.format((Date) removeDiscountDto.get(0).getContractendDate()));

            isEnable(false);

            componentTypeDdlb = CommonLogic.loadComponentType(componentTypeDdlb, null, true);

            searchValueStatusDdlb.setVisible(false);
            configureTables();
            LoadDashBoardTree();
            for (RemoveDiscountDto remove : removeDiscountDto) {
                rebateList.add(remove.getRsSid());
            }
            fromCDNo.setEnabled(false);
            fromCDName.setEnabled(false);
            itemStatusList = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected void configureTables() {

        addResultTable();
        addSelectedResultTable();
        availableTableLogic.setContainerDataSource(availableItemContainer);
        availableTableLogic.setPageLength(NumericConstants.EIGHT);
        availableTableLogic.sinkItemPerPageWithPageLength(false);

        selectedTableLogic.setContainerDataSource(selectedContainer);
        selectedTableLogic.setPageLength(NumericConstants.EIGHT);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);

        componentResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentResultsTable.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentResultsTable.setPageLength(NumericConstants.FIVE);
        componentResultsTable.setContainerDataSource(availableItemContainer);
        componentResultsTable.setVisibleColumns(Constants.getInstance().rsResultsColumns);
        componentResultsTable.setColumnHeaders(Constants.getInstance().rsResultsHeaders);
        componentResultsTable.setSelectable(true);
        componentResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

        componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSelectedItem.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentDetailsSelectedItem.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        componentDetailsSelectedItem.setPageLength(NumericConstants.FIVE);
        componentDetailsSelectedItem.setContainerDataSource(selectedContainer);
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().existingSelectedResultsColumns);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().existingSelectedResultsHeaders);
        componentDetailsSelectedItem.setColumnAlignment("itemStartDate", ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment("itemEndDate", ExtCustomTable.Align.CENTER);

        dashboardTreeTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        dashboardTreeTable.setPageLength(NumericConstants.TEN);
        dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
        dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
        dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);

        levelDetailsResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        levelDetailsResultsTable.setPageLength(NumericConstants.ELEVEN);
        levelDetailsResultsTable.setContainerDataSource(componentResultsContainer);
        levelDetailsResultsTable.setVisibleColumns(Constants.getInstance().contractComponentDetailsResultsColumns);
        levelDetailsResultsTable.setColumnHeaders(Constants.getInstance().contractComponentDetailsResultsHeaders);

    }

    private void LoadDashBoardTree() {
        LOGGER.debug("Entering getProcessedTree method");
        final CommonLogic commonLogic = new CommonLogic();
        dashboardTreeTable.markAsDirty();
        dashboardTreeTable.setSizeFull();
        dashboardTreeTable.setPageLength(NumericConstants.TEN);
        dashboardTreeTable.removeAllItems();
        parentList.clear();
        levelValue = 0;
        dashBoardTreeContainer = commonLogic.getLevel1Hierarchy(removeDiscountDto.get(0).getContractNo(), dashBoardTreeContainer, null);
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
                treeBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (treeBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) treeBeanId;
                } else if (treeBeanId instanceof ContractsDetailsDto) {
                    targetItem = new BeanItem<>((ContractsDetailsDto) treeBeanId);
                } else {
                    targetItem = NULL_OBJECT;
                }
                tableBean = (ContractsDetailsDto) targetItem.getBean();

            }
        });
        LOGGER.debug("End of getProcessedTree method");
    }

    private void setProcessedTableHeader() {
        LOGGER.debug("Entering setProcessedTableHeader method");
        dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
        dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);
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
                        dashBoardTreeContainer = commonLogic.getLevel2Hierarchy(contractDetails, dashBoardTreeContainer, cfpList, ifpList, psList, rsList);
                        setProcessedTableHeader();

                        break;
                    case ContractsDetailsDto.LEVEL2:
                        configureLevel(event.getItemId());
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails, dashBoardTreeContainer, ifpList, psList, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails, false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        configureLevel(event.getItemId());
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel4Hierarchy(contractDetails, dashBoardTreeContainer, psList, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.setCollapsed(contractDetails, false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        configureLevel(event.getItemId());
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel5Hierarchy(contractDetails, dashBoardTreeContainer, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent3(), false);
                        dashboardTreeTable.setCollapsed(contractDetails, false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    default:
                        break;
                }
                LOGGER.debug("End of StplExpandListener nodeExpand method");
            } catch (SystemException ex) {
                LOGGER.error(" ErrorCodeUtil.getErrorMessage(ex) {} ",ErrorCodeUtil.getErrorMessage(ex));
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getErrorMessage(ex));
            } catch (PortalException ex) {
                LOGGER.error("",ex);
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
                        dashBoardTreeContainer = commonLogic.getLevel1Hierarchy(removeDiscountDto.get(0).getContractNo(), dashBoardTreeContainer, null);
                        setProcessedTableHeader();
                        break;
                    case ContractsDetailsDto.LEVEL2:
                        levelValue = 1;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel2Hierarchy(contractDetails.getParent1(), dashBoardTreeContainer, cfpList, ifpList, psList, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL3:
                        levelValue = NumericConstants.TWO;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails.getParent2(), dashBoardTreeContainer, ifpList, psList, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = NumericConstants.THREE;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel4Hierarchy(contractDetails.getParent3(), dashBoardTreeContainer, psList, rsList));
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
            } catch (SystemException ex) {
                
                LOGGER.error("error message == {} ",ErrorCodeUtil.getErrorMessage(ex));
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getErrorMessage(ex));
            } catch (PortalException ex) {
                LOGGER.error("",ex);
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

    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");
        availableItemContainer.removeAllItems();
        String searchField = String.valueOf(searchFieldDdlb.getValue());
        String sValue = valueTxt.getValue();
        String ddlbValue = searchValueStatusDdlb.getValue() == null || searchValueStatusDdlb.getValue().equals(Constants.ZEROSTRING) ? StringUtils.EMPTY : String.valueOf(searchValueStatusDdlb.getValue());
        if (StringUtils.isNotBlank(searchField) && (StringUtils.isNotBlank(sValue) || StringUtils.isNotBlank(ddlbValue))) {
            newDiscountTabDto.setSearchField(searchField);
            if (valueTxt.isVisible()) {
                newDiscountTabDto.setSearchFieldValue(sValue);
            } else {
                newDiscountTabDto.setSearchFieldValue(ddlbValue);
            }

            if (!availableTableLogic.loadSetData(newDiscountTabDto, false)) {
                AbstractNotificationUtils.getErrorNotification("No Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Search",
                    "Please enter a Search Value.");
        }

        LOGGER.debug("Ending search method");
    }

    @UiHandler("populateBtn")
    public void populateBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered populate method");
        if (componentResultsTable.getValue() != null) {
            newDiscountTabDto = (ContractsDetailsDto) componentResultsTable.getValue();
            selectedContainer.removeAllItems();
            componentInformationID.setValue(newDiscountTabDto.getCategory() + " ID:");
            componentInformationNo.setValue(newDiscountTabDto.getCategory() + " No:");
            componentInformationName.setValue(newDiscountTabDto.getCategory() + " Name:");
            if (newDiscountTabDto.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.getConstant())) {
                rebateFrequency.setVisible(true);
                rarType.setVisible(true);
                rsBasic.setVisible(true);
                rebateFrequencyDdlb.setVisible(true);
                rarDdlb.setVisible(true);
                basicDdlb.setVisible(true);
                rebateFrequencyDdlb.setValue(newDiscountTabDto.getFrequency());
                rarDdlb.setValue(newDiscountTabDto.getRarType());
                basicDdlb.setValue(newDiscountTabDto.getBasis());
            } else {
                rebateFrequency.setVisible(false);
                rarType.setVisible(false);
                rsBasic.setVisible(false);
                rebateFrequencyDdlb.setVisible(false);
                rarDdlb.setVisible(false);
                basicDdlb.setVisible(false);
            }
            rsId.setValue(newDiscountTabDto.getId());
            statusDdlb.setValue(newDiscountTabDto.getStatus());

            rsNumber.setValue(newDiscountTabDto.getNumber());
            startDate_.setValue(newDiscountTabDto.getStartDate() == null ? StringUtils.EMPTY : newDiscountTabDto.getStartDate());

            rsName.setValue(newDiscountTabDto.getName());
            rsEndDate.setValue(newDiscountTabDto.getEndDate() == null ? StringUtils.EMPTY : newDiscountTabDto.getEndDate());
            if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
                loadCfpFromResults();
            } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
                loadIfpFromResults();
            } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
                loadPsFromResults();
            } else if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
                loadRsFromResults();
            }
            if (!selectedTableLogic.loadSetData(newDiscountTabDto, false)) {
                AbstractNotificationUtils.getErrorNotification("No Records",
                        "Please select other record.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Populate",
                    "Please highlight a row to populate.");
        }
        LOGGER.debug("Ended populate method");

    }

    @UiHandler("levelPopulateBtn")
    public void levelPopulateBtnClick(Button.ClickEvent event) {
        if (dashboardTreeTable.getValue() != null) {
            ContractsDetailsDto temp = tableBean;
            fromCDLabelName.setValue(temp.getCategory() + "Name: ");
            fromCDLabelNo.setValue(temp.getCategory() + " No: ");
            fromCDNo.setValue(temp.getNumber());
            fromCDName.setValue(temp.getName());
            if (Constants.IndicatorConstants.CFP.toString().equals(temp.getCategory())) {
                loadCfpFromCD(temp);
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
        } else {
            AbstractNotificationUtils.getWarningNotification("Populate", "Please highlight a component to Populate.");
        }

    }

    @UiHandler("componentTypeDdlb")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) {
        searchFieldDdlb = CommonLogic.loadExistingTabSearchField(searchFieldDdlb, componentTypeDdlb);
        resultsComponentTypeDdlb = CommonLogic.loadComponentType(resultsComponentTypeDdlb, String.valueOf(componentTypeDdlb.getValue()), false);
        searchValueStatusDdlb.removeAllItems();
        loadTableHeaders();
        clearContainers();
    }

    @UiHandler("searchFieldDdlb")
    public void searchFieldDdlbLogic(Property.ValueChangeEvent event) {
        if (event != null) {

            String value = String.valueOf(event.getProperty().getValue());

            if ("RS No".equals(value) || "RS Name".equals(value) || "RS ID".equals(value)
                    || Constants.IFP_NO.equals(value) || Constants.IFP_NAME_LABEL.equals(value) || Constants.IFP_ID.equals(value)
                    || "CFP No".equals(value) || "CFP Name".equals(value) || "CFP ID".equals(value)
                    || "PS ID".equals(value) || "PS No".equals(value) || "PS Name".equals(value)) {
                valueTxt.setValue(StringUtils.EMPTY);
                valueTxt.setVisible(true);
                searchValueStatusDdlb.setVisible(false);

            } else if ("RS Status".equals(value) || "CFP Status".equals(value) || Constants.IFP_STATUS.equals(value) || "PS Status".equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                valueTxt.setVisible(false);
                searchValueStatusDdlb = CommonLogic.getNativeSelect(searchValueStatusDdlb, itemStatusList);
                searchValueStatusDdlb.setVisible(true);
            } else if ("RS Type".equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                valueTxt.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.RS_TYPE, false);

                searchValueStatusDdlb.setVisible(true);
            } else if ("CFP Type".equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                valueTxt.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.CFP_TYPE, false);

                searchValueStatusDdlb.setVisible(true);
            } else if ("IFP_TYPE".equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                valueTxt.setVisible(false);

                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.IFP_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if ("PS_TYPE".equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                valueTxt.setVisible(false);

                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.PS_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            }
        }
    }

    public void isEnable(boolean value) {
        contractNo.setEnabled(value);
        contractName.setEnabled(value);
        contractType.setEnabled(value);
        startDate.setEnabled(value);
        endDate.setEnabled(value);

        rsId.setEnabled(value);
        statusDdlb.setEnabled(value);
        rebateFrequencyDdlb.setEnabled(value);
        rsNumber.setEnabled(value);
        startDate_.setEnabled(value);
        rarDdlb.setEnabled(value);
        rsName.setEnabled(value);
        rsEndDate.setEnabled(value);
        basicDdlb.setEnabled(value);

    }

    private void addResultTable() {
        availableLayout.addComponent(componentResultsTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(availableTableLogic.createControls());
        availableLayout.addComponent(controls);
    }

    private void addSelectedResultTable() {
        selectedLayout.addComponent(componentDetailsSelectedItem);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls());
        selectedLayout.addComponent(controls);
    }

    @UiHandler("addToTree")
    public void addToTreeLogic(Button.ClickEvent event) {
        try {
            Object id = componentTypeDdlb.getValue();
            if (id != null) {
                String compType = String.valueOf(id);
                ContractsDetailsDto srcTableBean = null;
                ContractsDetailsDto treeBean = null;
                treeBean = dashboardTreeTable.getValue() == null ? null : getBeanFromID(dashboardTreeTable.getValue());
                Object srcTableBeanId;
                srcTableBeanId = componentResultsTable.getValue();
                if (srcTableBeanId != null) {
                    srcTableBean = getBeanFromID(srcTableBeanId);
                } else {
                    srcTableBean = null;
                }
                if (srcTableBean == null) {
                    MessageBox.showPlain(Icon.ERROR, Constants.ERROR, "Please select Search Result", ButtonId.OK);
                    return;
                }
                if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                    rsListforMap = new ArrayList<>();
                    if (addToTreeMethod(srcTableBean)) {
                        rsList.add(srcTableBean);
                        rsListforMap.add(srcTableBean);
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                    psListforMap = new ArrayList<>();
                    if (addToTreeMethod(srcTableBean)) {
                        psList.add(srcTableBean);
                        psListforMap.add(srcTableBean);
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                    ifpListforMap = new ArrayList<>();
                    if (addToTreeMethod(srcTableBean)) {
                        ifpList.add(srcTableBean);
                        ifpListforMap.add(srcTableBean);
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.getConstant())) {
                    List list = logic.cfpDuplicateCheck(srcTableBean, treeBean.getInternalId());
                    if (list.isEmpty()) {
                        if (addToTreeMethod(srcTableBean)) {
                            cfpList.add(srcTableBean);
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Duplicate CFP", "Selected CFP has been added already to this Contract");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private boolean addToTreeMethod(ContractsDetailsDto srcTableBean)  {
        ContractsDetailsDto treeBean;
        boolean returnFlag = false;
        if (srcTableBean == null) {
            MessageBox.showPlain(Icon.ERROR, Constants.ERROR, "Please select Search Result", ButtonId.OK);
        } else {

            treeBean = dashboardTreeTable.getValue() == null ? null : getBeanFromID(dashboardTreeTable.getValue());
            Object treeBeanId = dashboardTreeTable.getValue() == null ? null : dashboardTreeTable.getValue();
            if (treeBean == null) {
                if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.toString())) {

                    setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean);
                    returnFlag = true;
                } else {
                    final String MESSAGE = "Cannot make a " + srcTableBean.getCategory() + " as contracts header";
                    AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, MESSAGE);
                }
            } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.toString())) {
                final String message = "Cannot make a " + srcTableBean.getCategory() + " as child node";
                AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, message);
            } else if (srcTableBean.getCategory().equals(treeBean.getCategory())) {
                final String message = srcTableBean.getCategory() + " cannot be added to  " + treeBean.getCategory();
                AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, message);
            } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.toString())) {
                final DynamicQuery psDynamicQuery = PsDetailsLocalServiceUtil.dynamicQuery();

                psDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PS_MODEL_SID_PROPERTY, Integer.valueOf(srcTableBean.getPsSid())));
                psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(StringConstantsUtil.IFP_MODEL_SID)));
                final List<PsDetails> priceScheduleDetailsList = PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery);
                if (priceScheduleDetailsList.isEmpty()) {
                    AbstractNotificationUtils.getWarningNotification("No Items", "No items Exists in PS");
                } else {
                    final String psSystem = String.valueOf(priceScheduleDetailsList.get(0)).trim();
                    int id = 0;
                    if (treeBean.getInternalId() == 0) {
                        id = treeBean.getIfpId();
                    } else {
                        id = treeBean.getModelSysId();
                    }
                    if (psSystem.equals(String.valueOf(id).trim())) {
                        if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                            final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                            for (final Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
                                final Object childId = iterator.next();
                                final ContractsDetailsDto object = getBeanFromID(childId);
                                if (srcTableBean.getInternalId() == object.getModelSysId()) {
                                    final String messageStr = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                                    AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, messageStr);
                                    return false;
                                }
                            }
                        }
                        srcTableBean.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), psListforMap);
                        setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean);
                        returnFlag = true;
                    } else {
                        final String message = srcTableBean.getCategory() + StringConstantsUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                    }
                }
            } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.toString())) {
                LOGGER.debug("Inside Expected Code");
                final DynamicQuery rsDynamicQuery = RsDetailsLocalServiceUtil.dynamicQuery();
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", srcTableBean.getInternalId()));
                rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(StringConstantsUtil.IFP_MODEL_SID)));
                final List<RsDetails> rebateScheduleDetailsList = RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
                if (rebateScheduleDetailsList.isEmpty()) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "No items Exists in RS");
                } else {
                    final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();
                    if (rsSystem.equals(String.valueOf(treeBean.getPsSid()).trim())) {
                        if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                            final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                            for (final Object id : collection) {
                                final ContractsDetailsDto object = getBeanFromID(id);
                                if (srcTableBean.getInternalId() == object.getModelSysId()) {
                                    final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                                    AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                                    return false;
                                }
                            }
                        }
                        newlyAddedRebates.add(srcTableBean.getInternalId());
                        srcTableBean.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                        setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean);
                        returnFlag = true;
                    } else {
                        final String message = srcTableBean.getCategory() + StringConstantsUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                    }
                }
            } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.toString())) {
                final DynamicQuery rsDynamicQuery = RsDetailsLocalServiceUtil.dynamicQuery();
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", srcTableBean.getInternalId()));
                rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(StringConstantsUtil.IFP_MODEL_SID)));
                final List<RsDetails> rebateScheduleDetailsList = RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
                if (rebateScheduleDetailsList != null) {
                    if (rebateScheduleDetailsList.isEmpty()) {
                        AbstractNotificationUtils.getWarningNotification("No Items", "No items Exists in RS");
                    } else {
                        final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();
                        final DynamicQuery psDynamicQuery = PsDetailsLocalServiceUtil.dynamicQuery();
                        if (treeBean.getInternalId() != 0) {
                            psDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PS_MODEL_SID_PROPERTY, Integer.valueOf(treeBean.getModelSysId())));
                        } else {
                            psDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PS_MODEL_SID_PROPERTY, Integer.valueOf(treeBean.getPsSid())));
                        }
                        psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(StringConstantsUtil.IFP_MODEL_SID)));
                        final List<PsDetails> priceScheduleDetailsList = PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery);
                        if (rsSystem.equals(String.valueOf(priceScheduleDetailsList.get(0)).trim())) {
                            if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                                for (final Object id : collection) {
                                    final ContractsDetailsDto object = getBeanFromID(id);
                                    if (srcTableBean.getInternalId().equals(object.getModelSysId())) {
                                        final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                                        AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                                        return false;
                                    }
                                }
                            }
                            newlyAddedRebates.add(srcTableBean.getInternalId());
                            srcTableBean.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                            setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                            returnFlag = true;
                        } else {
                            final String message = srcTableBean.getCategory() + StringConstantsUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                            AbstractNotificationUtils.getWarningNotification(Constants.ERROR, message);
                        }
                    }
                }
            } else {
                if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                    final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                    for (final Object id : collection) {
                        final ContractsDetailsDto object = getBeanFromID(id);
                        if (srcTableBean.getInternalId() == object.getModelSysId()) {
                            final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                            AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                            return false;
                        }
                    }
                }
                if ("IFP".equals(srcTableBean.getCategory())) {
                    srcTableBean.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), ifpListforMap);
                }
                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                returnFlag = true;
            }
        }
        return returnFlag;
    }

    
    private ContractsDetailsDto getBeanFromID(final Object tableID) {
        BeanItem<?> targetItem;
        if (tableID instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableID;
        } else if (tableID instanceof ContractsDetailsDto) {
            targetItem = new BeanItem<>((ContractsDetailsDto) tableID);
        } else {
            targetItem = NULL_OBJECT;
        }
        return (ContractsDetailsDto) targetItem.getBean();
    }

    private void setTreeNode(final ContractsDetailsDto bean, final VerticalDropLocation location, final Object targetItemId) {

        LOGGER.debug("Entering setTreeNode method");

        if (location == VerticalDropLocation.MIDDLE) {

            final String dommyId = bean.getCategory() + "-" + bean.getContractId() + "-" + bean.getContractNo() + "-" + bean.getContractName();
            final Collection list = dashBoardTreeContainer.rootItemIds();
            boolean flag = false;
            for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                final Object idValue = iterator.next();
                final ContractsDetailsDto availableContract = getBeanFromID(idValue);
                final String treeCaption = availableContract.getCategory() + "-" + availableContract.getId() + "-" + availableContract.getContractNo() + "-" + availableContract.getContractName();
                if (treeCaption.equals(dommyId)) {
                    flag = true;
                }
            }
            if (flag) {
                AbstractNotificationUtils.getWarningNotification("Duplicate Contract ID", "Selected Contract ID is already exist");
            } else {
                if (bean.getCategory().equals("CFP")) {
                    bean.setLevel(NumericConstants.TWO);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("IFP")) {
                    bean.setLevel(NumericConstants.THREE);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("PS")) {
                    bean.setLevel(NumericConstants.FOUR);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent2());
                    bean.setParent3(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("RS")) {
                    bean.setLevel(NumericConstants.FIVE);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent2());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent3());
                    bean.setParent4(getBeanFromID(dashboardTreeTable.getValue()));
                }
                dashBoardTreeContainer.addBean(bean);
                if (bean.getCategory().equals("RS")) {
                    dashBoardTreeContainer.setChildrenAllowed(bean, false);
                } else {
                    dashBoardTreeContainer.setChildrenAllowed(bean, true);
                }
                dashBoardTreeContainer.setParent(bean, targetItemId);
                dashboardTreeTable.setCollapsed(bean, false);
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
        LOGGER.debug("End of setTreeNode method");
    }

    private void loadTableHeaders() {
        String compType = String.valueOf(componentTypeDdlb.getValue());
        if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adCfpIfpResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adCfpIfpResultsHeaders);
            componentResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adCfpIfpResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adCfpIfpResultsHeaders);
            componentResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().adPsResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().adPsResultsHeaders);
            componentResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            componentResultsTable.setVisibleColumns(Constants.getInstance().rsResultsColumns);
            componentResultsTable.setColumnHeaders(Constants.getInstance().rsResultsHeaders);
            componentResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        }
    }

    @UiHandler("levelRemoveBtn")
    public void levelRemoveBtnLogic(Button.ClickEvent event) {
        LOGGER.debug(" buttonClick ( ClickEvent event ) name= {} " , event.getButton().getCaption());
        if (dashBoardTreeContainer.getItemIds().size() > Constants.ZERO) {
            if (dashboardTreeTable.getValue() == null) {
                AbstractNotificationUtils.getWarningNotification(Constants.REMOVE_HEADER, "Please highlight a component to Remove.");
            } else {
                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                if (collection == null) {
                    Object ob = dashboardTreeTable.getValue();
                    boolean removeFlag = false;
                    ContractsDetailsDto treeBean = getBeanFromID(dashboardTreeTable.getValue());
                    if (treeBean.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant()) && cfpList.contains(treeBean)) {
                        cfpList.remove(treeBean);
                        removeFlag = true;
                    } else if (treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant()) && ifpList.contains(treeBean)) {
                        ifpList.remove(treeBean);
                        if (ifpListforMap.contains(treeBean)) {
                            ifpListforMap.remove(treeBean);
                        }
                        removeFlag = true;
                    } else if (treeBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant()) && psList.contains(treeBean)) {
                        psList.remove(treeBean);
                        if (psListforMap.contains(treeBean)) {
                            psListforMap.remove(treeBean);
                        }
                        removeFlag = true;
                    } else if (treeBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.getConstant()) && rsList.contains(treeBean)) {
                        rsList.remove(treeBean);
                        if (rsListforMap.contains(treeBean)) {
                            rsListforMap.remove(treeBean);
                        }
                        removeFlag = true;
                    }
                    if (removeFlag) {
                        dashBoardTreeContainer.removeItem(ob);
                    }
                } else {
                    AbstractNotificationUtils.getWarningNotification(Constants.REMOVE_HEADER, "Please remove all children nodes before removing a parent node.");
                }
            }
        } else {
            AbstractNotificationUtils.getWarningNotification(Constants.REMOVE_HEADER, "No data to remove");
        }
    }

    public void addDiscountSaveLogic() {
        boolean check = false;
        final Collection idList = dashboardTreeTable.rootItemIds();
        String[] level = {Constants.IndicatorConstants.CONTRACT.toString(),
            Constants.IndicatorConstants.CFP.toString(),
            Constants.IndicatorConstants.IFP.toString(),
            Constants.IndicatorConstants.PS_VALUE.toString(),
            Constants.IndicatorConstants.RS_VALUE.toString()};
        check = checkForAllLevels(idList, level, 0);
        if (check) {
            MessageBox.showPlain(Icon.QUESTION, "Create", "Are you sure you want to save the contract ?", new MessageBoxListener() {
                @Override
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals("YES")) {
                        try {
                            saveTree(dashBoardTreeContainer.getItemIds());
                            final Notification notif = new Notification("Contract successfully saved", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                        } catch (Exception ex) {
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000));
                            LOGGER.error("",ex);
                        }
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
        } else {
            AbstractNotificationUtils.getErrorNotification("Create",
                    "Please ensure the Contract has all components (Header, CFP, IFP, PS, RS).");
        }
    }
    private ContractsDetailsDto contract = new ContractsDetailsDto();
    private ContractsDetailsDto cfp = null;
    private ContractsDetailsDto ifp = null;
    private ContractsDetailsDto priceSchedule = null;
    private ContractsDetailsDto rebateSchedule = null;
    
    public void saveTree(final Collection list) {
        LOGGER.debug("Entering saveTree method");
        try {

            for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                final Object idValue = iterator.next();
                final ContractsDetailsDto temp = getBeanFromID(idValue);

                if (Constants.IndicatorConstants.CONTRACT.toString().equalsIgnoreCase(temp.getCategory())) {
                    contract = temp;
                    cfp = new ContractsDetailsDto();
                    ifp = new ContractsDetailsDto();
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                }
                if (Constants.IndicatorConstants.CFP.toString().equalsIgnoreCase(temp.getCategory())) {

                    cfp = temp;
                    ifp = new ContractsDetailsDto();
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                    DiscountLogic.saveCFp(contract.getInternalId(), cfp ,false);
                }
                if (Constants.IndicatorConstants.IFP.toString().equalsIgnoreCase(temp.getCategory())) {
                    ifp = temp;
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                    DiscountLogic.saveIFP(contract.getInternalId(), cfp.getCfpContractId(), ifp ,false);
                }
                if (Constants.IndicatorConstants.PS_VALUE.toString().equalsIgnoreCase(temp.getCategory())) {
                    priceSchedule = temp;
                    rebateSchedule = new ContractsDetailsDto();
                    DiscountLogic.savePS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule ,false);
                }
                if (Constants.IndicatorConstants.RS_VALUE.toString().equalsIgnoreCase(temp.getCategory())) {
                    rebateSchedule = temp;
                    DiscountLogic.saveRS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule.getPsContractId(), rebateSchedule,false);
                }

                final Collection childlist = dashboardTreeTable.getChildren(idValue);
                if (childlist != null || !childlist.isEmpty()) {
                    saveTree(childlist);
                }
            }
            cfpList.clear();
            ifpList.clear();
            psList.clear();
            rsList.clear();
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        LOGGER.debug("End of saveTree method");
    }

    private void loadCfpFromCD(final ContractsDetailsDto parent) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(CFPComponentDetailsDTO.class));
        levelDetailsResultsTable.addItems(new DiscountLogic().getFromCfpCD(parent));
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsCompanyColumn);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getInstance().adComponentDetailsCompanyHeader);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadIfpFromCD(final ContractsDetailsDto parent) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        levelDetailsResultsTable.addItems(new DiscountLogic().getFromIfpCD(parent));
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadPsFromCD(final ContractsDetailsDto parent) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        levelDetailsResultsTable.addItems(new DiscountLogic().getFromPsCD(parent));
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadRsFromCD(final ContractsDetailsDto parent) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        levelDetailsResultsTable.addItems(new DiscountLogic().getFromRsCD(parent));
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getInstance().adComponentDetailsPsColumn);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getInstance().aDComponentDetailsPsHeader);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadCfpFromResults() {
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsColumnsCfp);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsHeadersCfp);
        componentDetailsSelectedItem.setColumnAlignment(Constants.ATTACHED_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadIfpFromResults() {
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsColumnsIfp);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsHeadersIfp);
        componentDetailsSelectedItem.setColumnAlignment(Constants.ATTACHED_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadPsFromResults() {
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsColumnsPs);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsHeadersPs);
        componentDetailsSelectedItem.setColumnAlignment(Constants.ATTACHED_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadRsFromResults() {
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adComponentDetailsRsColumn);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adComponentDetailsRsHeader);
        componentDetailsSelectedItem.setColumnAlignment(Constants.ATTACHED_DATE_PROPERTY, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentDetailsSelectedItem.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    public boolean checkForAllLevels(final Collection list, String[] level, int index) {
        boolean check1 = true;

        for (final Iterator iterator = list.iterator(); iterator.hasNext() && check1;) {
            boolean check = false;
            final Object idValue = iterator.next();
            final ContractsDetailsDto temp = getBeanFromID(idValue);

            if (level.length > index) {

                if (temp.getCategory().equals(level[index]) && dashboardTreeTable.hasChildren(idValue)) {

                    final Collection childlist = dashboardTreeTable.getChildren(idValue);
                    check = checkForAllLevels(childlist, level, index + 1);
                } else if (level.length == index + 1) {
                    check = true;
                }
            } else {

                check = true;
            }
            check1 = check;

        }

        return check1;
    }

    public void clearContainers() {
        selectedTableLogic.loadSetData(newDiscountTabDto, true);
        availableTableLogic.loadSetData(newDiscountTabDto, true);
        componentDetailsSelectedItem.setValue(null);
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Add Discount", "ExistingDiscount Screen");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            levelRemoveBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelRemoveBtn", functionHM));
            levelPopulateBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelPopulateBtn", functionHM));
            addToTree.setVisible(CommonLogic.isButtonVisibleAccess("addToTree", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    }
