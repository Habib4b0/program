/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import static com.stpl.app.gcm.discount.logic.DiscountLogic.DBDate;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SELECT_ONE;
import com.stpl.app.gcm.util.Converters;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
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
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class TransferComponents extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TransferComponents.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("transferCompPanelTableLayout")
    public VerticalLayout transferCompPanelTableLayout;
    @UiField("componentDetailsTableLayout")
    public VerticalLayout componentDetailsTableLayout;

    @UiField("componentTypeDdlb")
    public ComboBox componentTypeDdlb;
    @UiField("dashboardResultsTable")
    public TreeTable contractDashboardResultsTable;
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
    @UiField("populateBtn1")
    public Button populateBtn1;
    @UiField("addToTreeBtn1")
    public Button addToTreeBtn1;
    @UiField("populateBtn2")
    public Button populateBtn2;
    @UiField("removeBtn2")
    public Button removeBtn2;
    /**
     * Added for Dashboard details
     */
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
    @UiField("contractComponentDetailsTable")
    public ExtFilterTable contractComponentDetailsTable;

    @UiField("componentInfoRebateLayout")
    public GridLayout componentInfoRebateLayout;
    @UiField("componentInfoIfpLayout")
    public GridLayout componentInfoIfpLayout;
    @UiField("componentInfoPsLayout")
    public GridLayout componentInfoPsLayout;

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
    @UiField("psfileName")
    public TextField psfileName;
    @UiField("componentitems")
    public OptionGroup componentitems;
    public ExtFilterTable componentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> componentDetailResultsContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> contractInfoContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    public List parentList = new ArrayList();
    public int levelValue;
    CurrentContractDTO currentContractDTO = new CurrentContractDTO();
    ExtPagedFilterTable transferCompTable1 = new ExtPagedFilterTable();
    BeanItemContainer<CurrentContractDTO> transferCompContainer1 = new BeanItemContainer<CurrentContractDTO>(CurrentContractDTO.class);
    List<CurrentContractDTO> selecteditemList = new ArrayList<CurrentContractDTO>();
    PromoteTPLogic logic = new PromoteTPLogic();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    CustomTreeContainer<ComponentInfoDTO> dashBoardTreeContainer1 = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    QueryUtils queryUtils = new QueryUtils();
    ComponentInfoDTO componentInfoDTO = new ComponentInfoDTO();
    DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    public static final SimpleDateFormat dbDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private BeanItemContainer<CurrentContractDTO> contractExcelResultBean = new BeanItemContainer<CurrentContractDTO>(CurrentContractDTO.class);
    private ExtCustomTable contractExportPeriodViewTable;
    private BeanItemContainer<ComponentInfoDTO> excelResultBean = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private ExtCustomTable exportPeriodViewTable;
    public List<ComponentInfoDTO> componentInformation = new ArrayList<ComponentInfoDTO>();
    String excelName = "Rebate Schedule Information";
    boolean isLoad = false;
    boolean isRebateLoad = false;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    Boolean contractExcelFlag=false;
    Boolean infoExcelFlag=false;
    /**
     * Transfer Contract Constructor
     *
     * @param session
     * @param contractDashBoardTable
     */
    public TransferComponents(SessionDTO session, TreeTable contractDashBoardTable) {
        try {
            this.session = session;
            this.contractDashboardResultsTable = contractDashBoardTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpTransferComponents.xml"), this));
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

            transferCompPanelTableLayout.addComponent(transferCompTable1);
            configureTransferCompTable();

            configureContractDashboardResultsTable();
            componentDetailsTableLayout.addComponent(componentDetailsTable);
            configureComponentDetailsTable();

            configureContractComponentDetailsTable();

            excelBtn1.setIcon(excelExportImage);
            excelBtn2.setIcon(excelExportImage);
            disableRebateInfoFields();
            disableIfpInfoFields();
            disablePsInfoFields();

            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);
            componentInfoRebateLayout.setVisible(true);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(false);
            cfpDetailsNo.setEnabled(false);
            cfpDetailsName.setEnabled(false);

            componentitems.addItem("Item Family Plan");
            componentitems.addItem(Constants.PRICE_SCHEDULE);
            componentitems.addItem(Constants.REBATE_SCHEDULE);

        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    /**
     * Disable Component Information Fields
     *
     */
    public void disableRebateInfoFields() {
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

    public void disableIfpInfoFields() {
        ifpId.setEnabled(false);
        ifpNo.setEnabled(false);
        ifpName.setEnabled(false);
        ifpStatus.setEnabled(false);
        ifpStartDate.setEnabled(false);
        ifpfileName.setEnabled(false);
    }

    public void disablePsInfoFields() {
        psId.setEnabled(false);
        psNo.setEnabled(false);
        psName.setEnabled(false);
        psStatus.setEnabled(false);
        psStartDate.setEnabled(false);
        psfileName.setEnabled(false);
    }

    public void configureTransferCompTable() {

        transferCompTable1.setContainerDataSource(transferCompContainer1);
        transferCompTable1.setVisibleColumns(Constants.CONTRACT_COMP_RESULTS_COLUMNS);
        transferCompTable1.setColumnHeaders(Constants.CONTRACT_COMP_RESULTS_HEADERS);
        transferCompTable1.setSizeFull();
        transferCompTable1.setEditable(Boolean.TRUE);
        transferCompTable1.markAsDirty();
        transferCompTable1.setSelectable(true);
        transferCompTable1.setWidth("1660px");
        transferCompTable1.setHeight("290px");

        transferCompTable1.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        transferCompTable1.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);

        transferCompTable1.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selecteditemList.add((CurrentContractDTO) itemId);
                            } else {
                                if (selecteditemList.contains(itemId)) {
                                    selecteditemList.remove(itemId);
                                }
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        transferCompTable1.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = transferCompTable1.getItemIds();
                for (Object obj : itemList) {
                    CurrentContractDTO dto = (CurrentContractDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                }
            }
        });

        transferCompTable1.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method is called when results value is changed
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });

        componentTypeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String compType = String.valueOf(componentTypeDdlb.getValue());
                if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
                    componentInfoRebateLayout.setVisible(false);
                    componentInfoIfpLayout.setVisible(true);
                    componentInfoPsLayout.setVisible(false);
                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(Constants.COMPONENT_DETAILS_ITEM_COLUMNS);
                    componentDetailsTable.setColumnHeaders(Constants.COMPONENT_DETAILS_ITEM_HEADERS);

                } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                    componentInfoRebateLayout.setVisible(true);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(false);
                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(Constants.COMPONENT_DETAILS_RS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(Constants.COMPONENT_DETAILS_RS_HEADERS);

                } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
                    componentInfoRebateLayout.setVisible(false);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(true);
                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(Constants.COMPONENT_DETAILS_PS_COLUMNS);
                    componentDetailsTable.setColumnHeaders(Constants.COMPONENT_DETAILS_PS_HEADERS);

                }

                resultsItemClick(transferCompTable1.getValue());
            }
        });
    }

    protected void resultsItemClick(final Object obj) {
        CurrentContractDTO dto = (CurrentContractDTO) obj;
        String compType = String.valueOf(componentTypeDdlb.getValue());

        if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
            if (dto != null) {
                int ifpIdValue = Integer.parseInt(dto.getIfpContSid());
                String query = queryUtils.getIFPDetails(ifpIdValue);
                List ifpList = CompanyMasterLocalServiceUtil.executeQuery(query);
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
                }
            }
        } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
            if (dto != null) {
                int psIdValue = Integer.parseInt(dto.getPsContSid());
                String query = queryUtils.getPSDetails(psIdValue);
                List psList = CompanyMasterLocalServiceUtil.executeQuery(query);
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
                }
            }
        } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
            if (dto != null) {
                int rsIdValue = Integer.parseInt(dto.getRsContSid());
                String query = "select rm.RS_ID,h.DESCRIPTION AS STATUS,rm.RS_NO,rm.RS_START_DATE,rm.RS_NAME,rm.RS_END_DATE,h1.DESCRIPTION AS FREQ,h2.DESCRIPTION AS TYPE, h3.DESCRIPTION AS RS_TYPE "
                        + " from dbo.RS_MODEL rm join dbo.RS_CONTRACT rc on rm.RS_MODEL_SID=rc.RS_MODEL_SID and rc.RS_CONTRACT_SID=" + rsIdValue + " left join dbo.HELPER_TABLE h on rm.RS_STATUS=h.HELPER_TABLE_SID join dbo.HELPER_TABLE h1 "
                        + " on h1.HELPER_TABLE_SID=rm.REBATE_FREQUENCY join dbo.HELPER_TABLE h2 on h2.HELPER_TABLE_SID=rm.REBATE_PROGRAM_TYPE"
                        + " join HELPER_TABLE h3 ON rm.RS_TYPE=h3.HELPER_TABLE_SID";
                List rsList = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (rsList != null && rsList.size() > 0) {
                    Object[] object = (Object[]) rsList.get(0);
                    rebateScheduleId.setValue(String.valueOf(object[0]));
                    status.setValue(String.valueOf(object[1]));
                    rsNumber.setValue(String.valueOf(object[2]));
                    if (object[3] != null) {
                        String date = df.format(object[3]);
                        startDate.setValue(date);
                    } else {
                        startDate.setValue(Constants.EMPTY);
                    }
                    rsName.setValue(String.valueOf(object[4]));
                    if (object[5] != null) {
                        String date = df.format(object[5]);
                        endDate.setValue(date);
                    } else {
                        endDate.setValue(Constants.EMPTY);
                    }
                    rebateFrequency.setValue(String.valueOf(object[6]));
                    rarType.setValue(String.valueOf(object[7]));
                    rsType.setValue(String.valueOf(object[8]));
                }
            }
        }
    }

    public void configureContractDashboardResultsTable() {
        contractDashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTable.setWidth("630px");
        contractDashboardResultsTable.setHeight("350px");
        contractDashboardResultsTable.setPageLength(5);
        contractDashboardResultsTable.setSelectable(true);
        contractDashboardResultsTable.setContainerDataSource(dashBoardTreeContainer1);
        contractDashboardResultsTable.setVisibleColumns(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_COLUMNS_TRANSFER);
        contractDashboardResultsTable.setColumnHeaders(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_HEADERS);

    }

    public void configureComponentDetailsTable() {
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsTable.setWidth("890px");
        componentDetailsTable.setHeight("230px");
        componentDetailsTable.setPageLength(5);
        componentDetailsTable.setContainerDataSource(componentDetailResultsContainer);
        componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS);
        componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS);
    }

    public void configureContractComponentDetailsTable() {
        contractComponentDetailsTable.setPageLength(5);
        contractComponentDetailsTable.setContainerDataSource(contractInfoContainer);
        contractComponentDetailsTable.setVisibleColumns(Constants.CONTRACT_INFO_COLUMNS);
        contractComponentDetailsTable.setColumnHeaders(Constants.CONTRACT_INFO_HEADERS);
        contractComponentDetailsTable.setWidth("790px");
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //empty
    }

    public void loadContractCompoenentResults1(String screenName) {
        transferCompContainer1.removeAllItems();
        List<CurrentContractDTO> checkedContractList = logic.getSelectedTPContract(CommmonLogic.getSubmittedRecords(session, screenName, false));

        transferCompContainer1.addAll(checkedContractList);
        isLoad = true;
        isRebateLoad = true;
    }

    /**
     * Populate Component Information button click Logic
     *
     * @param event
     */
    @UiHandler("populateBtn1")
    public void populateComponentInfo(Button.ClickEvent event) {
        String componentSelectionValue = String.valueOf(componentTypeDdlb.getValue());
        if (transferCompTable1.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.TP_NO_ROW_SELECTED.getConstant(), Constants.MessageConstants.EMPTY_SPACE.getConstant() + "Please highlight a row to populate.");
        }
        if (transferCompTable1.getValue() != null && !Constants.NULL.equals(componentSelectionValue)) {
            if (!SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                populateBtnLogic(componentSelectionValue);
            }
        }
    }

    public void populateBtnLogic(String componentSelectionValue) {
        Boolean checkedFlag = false;
        if (componentSelectionValue.equals("Item Family Plan")) {
            String ids = Constants.EMPTY;
            Object item = transferCompTable1.getValue();
            String ifpContractId = String.valueOf(transferCompContainer1.getContainerProperty(item, "ifpContSid").getValue());
            String query = queryUtils.getIFP(ifpContractId);
            List ifpList = CompanyMasterLocalServiceUtil.executeQuery(query);
            if (ifpList != null && ifpList.size() > 0) {
                Object[] obj = (Object[]) ifpList.get(0);
                String modelId = String.valueOf(obj[3]);
                ifpId.setValue(String.valueOf(obj[0]));
                ifpNo.setValue(String.valueOf(obj[1]));
                ifpName.setValue(String.valueOf(obj[2]));
            }

            if (ifpContractId.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getItemMasterDetails(ifpContractId);
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> itemList = new ArrayList<ComponentInfoDTO>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(Converters.convertNullToEmpty(obje[2]));
                        itemDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                        itemDTO.setIfpStatus(String.valueOf(obje[4]));
                        if (obje[5] != null) {
                            String date = df.format((Date) obje[5]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[6] != null) {
                            String date = df.format((Date) obje[6]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        itemList.add(itemDTO);
                    }
                    componentDetailResultsContainer.addAll(itemList);
                    componentInformation.addAll(itemList);
                }
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }
            }
        } else if (componentSelectionValue.equals(Constants.PRICE_SCHEDULE)) {

            String ids = Constants.EMPTY;
            Collection<?> returnList = transferCompTable1.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, "psContSid").getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, "psContSid").getValue());
                        ids = ids + "," + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getPSDetails(ids);
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> priceList = new ArrayList<ComponentInfoDTO>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(Converters.convertNullToEmpty(obje[2]));
                        itemDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                        itemDTO.setIfpStatus(String.valueOf(obje[4]));
                        if (obje[5] != null) {
                            String date = df.format((Date) obje[5]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[6] != null) {
                            String date = df.format((Date) obje[6]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        if ((obje[8] != null) && !obje[8].equals(Constants.SELECT_ONE) && !obje[8].equals(Constants.NULL)) {
                            itemDTO.setPriceType(String.valueOf(obje[8]));
                        } else {
                            itemDTO.setPriceType(Constants.EMPTY);
                        }

                        if (obje[7] != null) {
                            String date = df.format((Date) obje[7]);
                            itemDTO.setPpStartDate(date);
                        } else {
                            itemDTO.setPpStartDate(Constants.EMPTY);
                        }
                        priceList.add(itemDTO);
                    }
                    componentDetailResultsContainer.addAll(priceList);
                    componentInformation.addAll(priceList);
                }
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }
            }
        } else if (componentSelectionValue.equals(Constants.REBATE_SCHEDULE)) {
            String ids = Constants.EMPTY;
            Collection<?> returnList = transferCompTable1.getItemIds();
            Boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                if (checked) {
                    checkedFlag = true;
                    if (!flag) {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, "rsContSid").getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, "rsContSid").getValue());
                        ids = ids + "," + cfpId;
                    }
                }
            }
            if (ids.equals(Constants.EMPTY)) {
            } else {
                String componentQuery = queryUtils.getRSDetails(ids);

                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> rebateList = new ArrayList<ComponentInfoDTO>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO rebateDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        rebateDTO.setItemNo(String.valueOf(obje[0]));
                        rebateDTO.setItemName(String.valueOf(obje[1]));
                        rebateDTO.setTherapyClass(Converters.convertNullToEmpty(obje[2]));
                        rebateDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                        rebateDTO.setIfpStatus(String.valueOf(obje[4]));
                        if (obje[5] != null) {
                            String date = df.format((Date) obje[5]);
                            rebateDTO.setIfpStartDate(date);
                        } else {
                            rebateDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[6] != null) {
                            String date = df.format((Date) obje[6]);
                            rebateDTO.setIfpEndDate(date);
                        } else {
                            rebateDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        rebateDTO.setFormulaId((obje[7] != null) ? String.valueOf(obje[7]) : Constants.EMPTY);
                        rebateDTO.setRebatePlan((obje[8] != null) ? String.valueOf(obje[8]) : Constants.EMPTY);
                        rebateList.add(rebateDTO);
                    }
                    componentDetailResultsContainer.addAll(rebateList);
                    componentInformation.addAll(rebateList);
                }

                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, 180);
                }

            }
        }
    }

    /**
     * Add to Tree Button Click Logic
     *
     * @param event
     */
    @UiHandler("addToTreeBtn1")
    public void addToTreeBtnLogic(Button.ClickEvent event) {

        Set<Integer> set = new HashSet<Integer>();
        String componentValue = String.valueOf(componentitems.getValue());

        List<CurrentContractDTO> list = transferCompContainer1.getItemIds();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CurrentContractDTO dto = (CurrentContractDTO) list.get(i);
                if (Integer.valueOf(dto.getIfpContSid()) != 0) {
                    set.add(Integer.valueOf(dto.getIfpContSid()));
                }
            }
        }

        if (set.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.TP_NO_ROW_SELECTED.getConstant(), Constants.MessageConstants.EMPTY_SPACE.getConstant() + "Please check mark at least one component to add to the Contract.");
            return;
        }
        addToContDashboardTree();

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
                String modelSId = "14";
                int levelNumber = Integer.valueOf(levelNo);
                String level = String.valueOf(componentitems.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Component");
                }
                Collection<?> checkList = transferCompTable1.getItemIds();
                Boolean flag = false;
                for (Object tmp : checkList) {
                    Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(tmp, Constants.CHECK_RECORD).getValue();
                    if (checked) {
                        flag = true;
                    }
                }
                if (!flag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one record at Contract Component Results section");
                }

                if (level.equals(Constants.ITEM_FAMILY_PLAN)) {
                    int Duplicatealert = 0;
                    if (2 - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String ifpId = String.valueOf(transferCompContainer1.getContainerProperty(item, "ifpContSid").getValue());
                                setA.add(ifpId);
                            }
                        }

                        List<String> tmp = new ArrayList<String>();
                        for (Object ifpContId : setA) {
                            String id = String.valueOf(ifpContId);
                            String query = queryUtils.getIFP(id);
                            List ifpList = CompanyMasterLocalServiceUtil.executeQuery(query);
                            String cfpName = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, "dashboardName").getValue());
                            Collection<?> returnList1 = contractDashboardResultsTable.getItemIds();
                            for (Object item : returnList1) {
                                if (contractDashboardResultsTable.getContainerProperty(item, Constants.CATEGORY).getValue().toString().equals(Constants.IFP)) {
                                    String listidvalue = contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue().toString();

                                    Object[] obj = (Object[]) ifpList.get(0);
                                    String modelId = String.valueOf(obj[3]);
                                    if (modelId.equals(listidvalue)) {
                                        Duplicatealert++;
                                    }

                                }
                            }

                            if (ifpList != null && ifpList.size() > 0 && Duplicatealert == 0) {
                                Object[] obj = (Object[]) ifpList.get(0);
                                String modelId = String.valueOf(obj[3]);

                                final Object rootId = contractDashboardResultsTable.addItem();
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                contractDashboardResultsTable.addItem(rootId);
                                contractDashboardResultsTable.setParent(rootId, root);
                                contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                                contractDashboardResultsTable.setCollapsed(root, false);

                            }

                        }
                        if (Duplicatealert != 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Same IFP(s) already available.Please select different IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    int Duplicatealert = 0;
                    if (3 - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String psId = String.valueOf(transferCompContainer1.getContainerProperty(item, "psContSid").getValue());
                                setA.add(psId);
                            }
                        }
                        Boolean psFlag = true;
                        List<String> tmp = new ArrayList<String>();

                        for (Object psContId : setA) {
                            String id = String.valueOf(psContId);
                            String query = "select PS_ID,PS_NO,PS_NAME,PS_MODEL_SID from dbo.PS_MODEL where PS_MODEL_SID in (select PS_MODEL_SID from dbo.PS_CONTRACT where PS_CONTRACT_SID=" + id + ")";
                            List psList = CompanyMasterLocalServiceUtil.executeQuery(query);
                            Collection<?> returnList1 = contractDashboardResultsTable.getChildren(root);
                            if (returnList1 != null) {
                                for (Object item : returnList1) {

                                    if (contractDashboardResultsTable.getContainerProperty(item, Constants.CATEGORY).getValue().toString().equals(Constants.PS)) {
                                        String listidvalue = contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue().toString();

                                        Object[] obj = (Object[]) psList.get(0);
                                        String modelId = String.valueOf(obj[3]);
                                        if (modelId.equals(listidvalue)) {
                                            Duplicatealert++;
                                        }

                                    }
                                }
                            }
                            if (psList != null && psList.size() > 0) {
                                Object[] obj = (Object[]) psList.get(0);
                                String modelId = String.valueOf(obj[3]);

                                if (!tmp.contains(modelId)) {
                                    String conditionQuery = "select * from dbo.PS_DETAILS where PS_MODEL_SID=" + modelId + " and IFP_MODEL_SID=" + modelSId;
                                    List conditionList = CompanyMasterLocalServiceUtil.executeQuery(conditionQuery);
                                    psFlag = false;
                                    if (Duplicatealert == 0) {
                                        tmp.add(modelId);
                                        final Object rootId = contractDashboardResultsTable.addItem();
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                        contractDashboardResultsTable.addItem(rootId);
                                        contractDashboardResultsTable.setParent(rootId, root);
                                        contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                                        contractDashboardResultsTable.setCollapsed(root, false);

                                    }
                                }
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (4 - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String rsId = String.valueOf(transferCompContainer1.getContainerProperty(item, "rsContSid").getValue());
                                setA.add(rsId);
                            }
                        }
                        List<String> tmp = new ArrayList<String>();
                        for (Object rsContId : setA) {
                            String id = String.valueOf(rsContId);
                            String query = "select RS_ID,RS_NO,RS_NAME,RS_MODEL_SID from dbo.RS_MODEL where RS_MODEL_SID in (select RS_MODEL_SID from dbo.RS_CONTRACT where RS_CONTRACT_SID=" + id + ")";
                            List rsList = CompanyMasterLocalServiceUtil.executeQuery(query);
                            if (rsList != null && rsList.size() > 0) {
                                Object[] obj = (Object[]) rsList.get(0);
                                String modelId = String.valueOf(obj[3]);
                                if (!tmp.contains(modelId)) {
                                    tmp.add(modelId);
                                    final Object rootId = contractDashboardResultsTable.addItem();
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[2]));
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                    contractDashboardResultsTable.addItem(rootId);
                                    contractDashboardResultsTable.setParent(rootId, root);
                                    contractDashboardResultsTable.setChildrenAllowed(rootId, false);
                                    contractDashboardResultsTable.setCollapsed(root, false);
                                }
                            }
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        }
    }

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

    @UiHandler("populateBtn2")
    public void populateDetailsLogic(Button.ClickEvent event) {
        Object root = contractDashboardResultsTable.getValue();
        if (root != null) {
            String level = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
            if (level.equals(Constants.ONE)) {
                String companySid = session.getCompanyMasterSid();
                String componentQuery = queryUtils.getCompanyInformation(companySid);
                LOGGER.info(" populate btn  query " + componentQuery);
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    contractInfoContainer.removeAllItems();
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
                    contractInfoContainer.addAll(companyList);
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
                LOGGER.info(" Populate button query 2" + componentQuery);
                List componentList = CompanyMasterLocalServiceUtil.executeQuery(componentQuery);
                if (componentList != null && componentList.size() > 0) {
                    contractInfoContainer.removeAllItems();
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
                    contractInfoContainer.addAll(itemList);
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

    /**
     * Transfer TP as Contract Holder
     *
     * @throws SystemException
     * @throws PortalException
     * @throws ParseException
     */
    public List<Integer> saveTransferContract() throws SystemException, PortalException, ParseException {

        int contractMasterSid = 0;
        List<Integer> returnList = new ArrayList<Integer>();
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            Collection<?> treeItem = contractDashboardResultsTable.getItemIds();

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
                    contractMaster.setSource("GCM");
                    contractMaster.setContractStatus(contStatus);
                    contractMaster.setCreatedBy(Integer.valueOf(userId));
                    contractMaster.setStartDate(new Date());
                    contractMaster.setInboundStatus("A");
                    contractMaster.setCreatedDate(new Date());
                    contractMaster.setModifiedDate(new Date());
                    contractMaster.setContHoldCompanyMasterSid(session.getCompanyMasterSid());
                    contractMaster = ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
                    contractMasterSid = contractMaster.getContractMasterSid();
                    session.setContractMasterSid(String.valueOf(contractMasterSid));
                    returnList.add(contractMasterSid);
                    String AliasType = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "type").getValue());
                    String AliasNumber = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "number").getValue());
                    ContractAliasMaster CAM = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    CAM.setContractAliasNo(AliasNumber);
                    CAM.setContractAliasType(AliasType != null && !Constants.NULL.equals(AliasType) ? Integer.valueOf(AliasType) : 0);
                    CAM.setStartDate(new Date());
                    CAM.setModifiedDate(new Date());
                    CAM.setCreatedBy(1);
                    CAM.setCreatedDate(new Date());
                    CAM.setSource("BPI");
                    CAM.setInboundStatus("A");
                    CAM.setContractMasterSid(contractMasterSid);
                    ContractAliasMaster CAM1 = ContractAliasMasterLocalServiceUtil.addContractAliasMaster(CAM);

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
                    Date date = new Date();

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
            LOGGER.error(ex);
        }
        return returnList;
    }

    public void SaveCFP(String cfpId, String cfpModelId) {
        logic.SaveCFPForTransferComponent(cfpId, cfpModelId);
    }

    public void SaveIFP(String ifpId, String ifpModelId) {
        logic.SaveIFPForTransferComponent(ifpId, ifpModelId);
    }

    public void SavePS(String psid, String psModelId) {
        logic.SavePSForTransferComponent(psid, psModelId);
    }

    public void SaveRS(String rsid, String rsModelId) {
        logic.SaveRSForTransferComponent(rsid, rsModelId);

    }

    public void loadCurrentComponentResults() {

        loadContractCompoenentResults1(StringUtils.EMPTY);

    }

    /**
     * Excel Export for Selected Current Contract
     *
     * @param event
     */
    @UiHandler("excelBtn1")
    public void contractExport(Button.ClickEvent event) {
        try {

            contractExcelFlag = true;
            final int recordCount = transferCompTable1.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet("Contract_Details", transferCompTable1, recordCount);
            }

            transferCompPanelTableLayout.removeComponent(contractExportPeriodViewTable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        } finally {
            contractExcelFlag = false;
        }
    }

    /**
     * Excel Export for Selected Contract Details
     *
     * @param event
     */
    @UiHandler("excelBtn2")
    public void componentInfoExport(Button.ClickEvent event) {

        try {
            infoExcelFlag = true;
            String excelName1 = String.valueOf(componentTypeDdlb.getValue());
            excelName1 = excelName1.replaceAll(" ", StringUtils.EMPTY);
            final int recordCount = componentDetailsTable.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet(excelName1, componentDetailsTable, recordCount);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        } finally {
            infoExcelFlag = false;
        }

    }

    public BeanItemContainer<CurrentContractDTO> getTransferCompContainer1() {
        return transferCompContainer1;
    }

    public void setTransferCompContainer1(BeanItemContainer<CurrentContractDTO> transferCompContainer1) {
        this.transferCompContainer1 = transferCompContainer1;
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, Exception {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase());

    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    final List<CurrentContractDTO> searchList = transferCompContainer1.getItemIds();
                    Object[] columns = transferCompTable1.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, "checkRecord");
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                } else if (infoExcelFlag) {
                    final List<ComponentInfoDTO> searchList = componentDetailResultsContainer.getItemIds();
                    ExcelExportforBB.createFileContent(componentDetailsTable.getVisibleColumns(), searchList, printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
