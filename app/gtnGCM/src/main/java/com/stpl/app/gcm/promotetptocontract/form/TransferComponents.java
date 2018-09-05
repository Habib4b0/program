/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.common.CommonLogic;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.security.StplSecurity;
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
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.impl.IfpContractDetailsImpl;
import com.stpl.app.gcm.impl.PsContractDetailsImpl;
import com.stpl.app.gcm.impl.RsContractDetailsImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TableFieldFactory;
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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferComponents.class);
    
    
    
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("transferCompPanelTableLayout")
    private VerticalLayout transferCompPanelTableLayout;
    @UiField("componentDetailsTableLayout")
    private VerticalLayout componentDetailsTableLayout;

    @UiField("componentTypeDdlb")
    private ComboBox componentTypeDdlb;
    @UiField("dashboardResultsTable")
    private TreeTable contractDashboardResultsTable;
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
    @UiField("populateBtn1")
    private Button populateBtn1;
    @UiField("addToTreeBtn1")
    private Button addToTreeBtn1;
    @UiField("populateBtn2")
    private Button populateBtn2;
    @UiField("removeBtn2")
    private Button removeBtn2;
    /**
     * Added for Dashboard details
     */
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
    @UiField("contractComponentDetailsTable")
    private ExtFilterTable contractComponentDetailsTable;

    @UiField("componentInfoRebateLayout")
    private GridLayout componentInfoRebateLayout;
    @UiField("componentInfoIfpLayout")
    private GridLayout componentInfoIfpLayout;
    @UiField("componentInfoPsLayout")
    private GridLayout componentInfoPsLayout;

    @UiField("ifpId")
    private TextField ifpId;
    @UiField("ifpNo")
    private TextField ifpNo;
    @UiField("ifpName")
    private TextField ifpName;
    @UiField("ifpStatus")
    private TextField ifpStatus;
    @UiField("ifpStartDate")
    private TextField ifpStartDate;
    @UiField("ifpfileName")
    private TextField ifpfileName;

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
    @UiField("psfileName")
    private TextField psfileName;
    @UiField("componentitems")
    private OptionGroup componentitems;
    private ExtFilterTable componentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> componentDetailResultsContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> contractInfoContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private ExtPagedFilterTable transferCompTable1 = new ExtPagedFilterTable();
    private BeanItemContainer<CurrentContractDTO> transferCompContainer1 = new BeanItemContainer<>(CurrentContractDTO.class);
    private List<CurrentContractDTO> selecteditemList = new ArrayList<>();
    private PromoteTPLogic logic = new PromoteTPLogic();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private ExtTreeContainer<ComponentInfoDTO> dashBoardTreeContainer1 = new ExtTreeContainer<>(ComponentInfoDTO.class);
    private QueryUtils queryUtils = new QueryUtils();
    private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    private ExtCustomTable contractExportPeriodViewTable = new ExtCustomTable();
    private String excelName = "Rebate Schedule Information";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    private boolean contractExcelFlag = false;
    private boolean infoExcelFlag = false;
    private final StplSecurity stplSecurity = new StplSecurity();

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
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected final void configureFields() {
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

            LOGGER.error("",ex);
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
        transferCompTable1.setVisibleColumns(Constants.getInstance().contractCompResultsColumns);
        transferCompTable1.setColumnHeaders(Constants.getInstance().contractCompResultsHeaders);
        transferCompTable1.setSizeFull();
        transferCompTable1.setEditable(BooleanConstant.getTrueFlag());
        transferCompTable1.markAsDirty();
        transferCompTable1.setSelectable(true);
        transferCompTable1.setWidth("1660px");
        transferCompTable1.setHeight("290px");

        transferCompTable1.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        transferCompTable1.setColumnCheckBox(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());

        transferCompTable1.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selecteditemList.add((CurrentContractDTO) itemId);
                            } else if (selecteditemList.contains(itemId)) {
                                selecteditemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        transferCompTable1.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
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
            @Override
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
                    componentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsItemColumns);
                    componentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsItemHeaders);

                } else if (compType.equals(Constants.REBATE_SCHEDULE)) {
                    componentInfoRebateLayout.setVisible(true);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(false);
                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsRsColumns);
                    componentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsRsHeaders);

                } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
                    componentInfoRebateLayout.setVisible(false);
                    componentInfoIfpLayout.setVisible(false);
                    componentInfoPsLayout.setVisible(true);
                    componentDetailsTable.removeAllItems();
                    componentDetailsTable.setVisibleColumns(Constants.getInstance().componentDetailsPsColumns);
                    componentDetailsTable.setColumnHeaders(Constants.getInstance().componentDetailsPsHeaders);

                }

                resultsItemClick(transferCompTable1.getValue());
            }
        });
    }

    protected void resultsItemClick(final Object obj) {
        CurrentContractDTO dto = (CurrentContractDTO) obj;
        String compType = String.valueOf(componentTypeDdlb.getValue());

        if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
            setIFPValues(dto);            
        } else if (compType.equals(Constants.PRICE_SCHEDULE)) {
            setPSValues(dto);              
        } else if (dto != null && compType.equals(Constants.REBATE_SCHEDULE)) {
            setRSValues(dto);
        }
    }

    public void configureContractDashboardResultsTable() {
        contractDashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTable.setWidth("630px");
        contractDashboardResultsTable.setHeight("350px");
        contractDashboardResultsTable.setPageLength(NumericConstants.FIVE);
        contractDashboardResultsTable.setSelectable(true);
        contractDashboardResultsTable.setContainerDataSource(dashBoardTreeContainer1);
        contractDashboardResultsTable.setVisibleColumns(Constants.getInstance().promoteTpContractDashboardTreeColumnsTransfer);
        contractDashboardResultsTable.setColumnHeaders(Constants.getInstance().promoteTpContractDashboardTreeHeaders);

    }

    public void configureComponentDetailsTable() {
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsTable.setWidth("890px");
        componentDetailsTable.setHeight("230px");
        componentDetailsTable.setPageLength(NumericConstants.FIVE);
        componentDetailsTable.setContainerDataSource(componentDetailResultsContainer);
        componentDetailsTable.setVisibleColumns(Constants.getPtpComponentInfoColumns());
        componentDetailsTable.setColumnHeaders(Constants.getPtpComponentInfoHeaders());
    }

    public void configureContractComponentDetailsTable() {
        contractComponentDetailsTable.setPageLength(NumericConstants.FIVE);
        contractComponentDetailsTable.setContainerDataSource(contractInfoContainer);
        contractComponentDetailsTable.setVisibleColumns(Constants.getInstance().contractInfoColumns);
        contractComponentDetailsTable.setColumnHeaders(Constants.getInstance().contractInfoHeaders);
        contractComponentDetailsTable.setWidth("790px");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //empty
    }

    public void loadContractCompoenentResults1(String screenName) {
        transferCompContainer1.removeAllItems();
        List<CurrentContractDTO> checkedContractList = logic.getSelectedTPContract(CommmonLogic.getSubmittedRecords(session, screenName, false));

        transferCompContainer1.addAll(checkedContractList);
       
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
        if (transferCompTable1.getValue() != null && !Constants.NULL.equals(componentSelectionValue) && !SELECT_ONE.getConstant().equals(componentSelectionValue)) {
            populateBtnLogic(componentSelectionValue);
        }
    }

    public void populateBtnLogic(String componentSelectionValue) {
        if (componentSelectionValue.equals("Item Family Plan")) {
            Object item = transferCompTable1.getValue();
            String ifpContractId = String.valueOf(transferCompContainer1.getContainerProperty(item, "ifpContSid").getValue());
            String query = queryUtils.getIFP(ifpContractId);
            List ifpList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (ifpList != null && !ifpList.isEmpty()) {
                Object[] obj = (Object[]) ifpList.get(0);
                ifpId.setValue(String.valueOf(obj[0]));
                ifpNo.setValue(String.valueOf(obj[1]));
                ifpName.setValue(String.valueOf(obj[NumericConstants.TWO]));
            }

             else {
                String componentQuery = queryUtils.getItemMasterDetailsTransContract(ifpContractId);
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> itemList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(Converters.convertNullToEmpty(obje[NumericConstants.TWO]));
                        itemDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                        itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                        if (obje[NumericConstants.FIVE] != null) {
                            String date = df.format((Date) obje[NumericConstants.FIVE]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.SIX] != null) {
                            String date = df.format((Date) obje[NumericConstants.SIX]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        itemList.add(itemDTO);
                    }
                    componentDetailResultsContainer.addAll(itemList);
                }
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, NumericConstants.ONE_EIGHT_ZERO);
                }
            }
        } else if (componentSelectionValue.equals(Constants.PRICE_SCHEDULE)) {

            String ids = Constants.EMPTY;
            Collection<?> returnList = transferCompTable1.getItemIds();
            boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                if (checked) {
                    if (!flag) {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.PS_CONT_SID).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.PS_CONT_SID).getValue());
                        ids = ids + "," + cfpId;
                    }
                }
            }
            if (!(ids.equals(Constants.EMPTY))) {
            
                String componentQuery = queryUtils.getPSDetailsTransContract(ids);
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> priceList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO itemDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        itemDTO.setItemNo(String.valueOf(obje[0]));
                        itemDTO.setItemName(String.valueOf(obje[1]));
                        itemDTO.setTherapyClass(Converters.convertNullToEmpty(obje[NumericConstants.TWO]));
                        itemDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                        itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                        if (obje[NumericConstants.FIVE] != null) {
                            String date = df.format((Date) obje[NumericConstants.FIVE]);
                            itemDTO.setIfpStartDate(date);
                        } else {
                            itemDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.SIX] != null) {
                            String date = df.format((Date) obje[NumericConstants.SIX]);
                            itemDTO.setIfpEndDate(date);
                        } else {
                            itemDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        if ((obje[NumericConstants.EIGHT] != null) && !obje[NumericConstants.EIGHT].equals(Constants.SELECT_ONE) && !obje[NumericConstants.EIGHT].equals(Constants.NULL)) {
                            itemDTO.setPriceType(String.valueOf(obje[NumericConstants.EIGHT]));
                        } else {
                            itemDTO.setPriceType(Constants.EMPTY);
                        }

                        if (obje[NumericConstants.SEVEN] != null) {
                            String date = df.format((Date) obje[NumericConstants.SEVEN]);
                            itemDTO.setPpStartDate(date);
                        } else {
                            itemDTO.setPpStartDate(Constants.EMPTY);
                        }
                        priceList.add(itemDTO);
                    }
                    componentDetailResultsContainer.addAll(priceList);
                }
                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, NumericConstants.ONE_EIGHT_ZERO);
                }
            }
        } else if (componentSelectionValue.equals(Constants.REBATE_SCHEDULE)) {
            String ids = Constants.EMPTY;
            Collection<?> returnList = transferCompTable1.getItemIds();
            boolean flag = false;
            for (Object item : returnList) {
                Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                if (checked) {
                    if (!flag) {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.RS_CONT_SID).getValue());
                        ids = cfpId;
                        flag = true;
                    } else {
                        String cfpId = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.RS_CONT_SID).getValue());
                        ids = ids + "," + cfpId;
                    }
                }
            }
            if (! ids.equals(Constants.EMPTY)) {
           
                String componentQuery = queryUtils.getRSDetailsTransContract(ids);

                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    componentDetailResultsContainer.removeAllItems();
                    List<ComponentInfoDTO> rebateList = new ArrayList<>();
                    for (int i = 0; i < componentList.size(); i++) {
                        ComponentInfoDTO rebateDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) componentList.get(i);
                        rebateDTO.setItemNo(String.valueOf(obje[0]));
                        rebateDTO.setItemName(String.valueOf(obje[1]));
                        rebateDTO.setTherapyClass(Converters.convertNullToEmpty(obje[NumericConstants.TWO]));
                        rebateDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                        rebateDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                        if (obje[NumericConstants.FIVE] != null) {
                            String date = df.format((Date) obje[NumericConstants.FIVE]);
                            rebateDTO.setIfpStartDate(date);
                        } else {
                            rebateDTO.setIfpStartDate(Constants.EMPTY);
                        }
                        if (obje[NumericConstants.SIX] != null) {
                            String date = df.format((Date) obje[NumericConstants.SIX]);
                            rebateDTO.setIfpEndDate(date);
                        } else {
                            rebateDTO.setIfpEndDate(Constants.EMPTY);
                        }
                        rebateDTO.setFormulaId((obje[NumericConstants.SEVEN] != null) ? String.valueOf(obje[NumericConstants.SEVEN]) : Constants.EMPTY);
                        rebateDTO.setRebatePlan((obje[NumericConstants.EIGHT] != null) ? String.valueOf(obje[NumericConstants.EIGHT]) : Constants.EMPTY);
                        rebateList.add(rebateDTO);
                    }
                    componentDetailResultsContainer.addAll(rebateList);
                }

                Object[] visibleColumns = componentDetailsTable.getVisibleColumns();
                for (Object column : visibleColumns) {
                    componentDetailsTable.setColumnWidth(column, NumericConstants.ONE_EIGHT_ZERO);
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

        Set<Integer> set = new HashSet<>();

        List<CurrentContractDTO> list = transferCompContainer1.getItemIds();

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                CurrentContractDTO dto = (CurrentContractDTO) list.get(i);
                if (Integer.parseInt(dto.getIfpContSid()) != 0) {
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
                int levelNumber = Integer.parseInt(levelNo);
                String level = String.valueOf(componentitems.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Component");
                }
                Collection<?> checkList = transferCompTable1.getItemIds();
                boolean flag = false;
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
                    int duplicateAlert = 0;
                    if (NumericConstants.TWO - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String ifpID = String.valueOf(transferCompContainer1.getContainerProperty(item, "ifpContSid").getValue());
                                setA.add(ifpID);
                            }
                        }

                        for (Object ifpContId : setA) {
                            String id = String.valueOf(ifpContId);
                            String query = queryUtils.getIFP(id);
                            List ifpList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                            Collection<?> returnList1 = contractDashboardResultsTable.getItemIds();
                            for (Object item : returnList1) {
                                if (contractDashboardResultsTable.getContainerProperty(item, Constants.CATEGORY).getValue().toString().equals(Constants.IFP)) {
                                    String listidvalue = contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue().toString();

                                    Object[] obj = (Object[]) ifpList.get(0);
                                    String modelId = String.valueOf(obj[NumericConstants.THREE]);
                                    if (modelId.equals(listidvalue)) {
                                        duplicateAlert++;
                                    }

                                }
                            }

                            if (ifpList != null && !ifpList.isEmpty() && duplicateAlert == 0) {
                                Object[] obj = (Object[]) ifpList.get(0);
                                String modelId = String.valueOf(obj[NumericConstants.THREE]);

                                final Object rootId = contractDashboardResultsTable.addItem();
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(id);
                                contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(modelId);
                                contractDashboardResultsTable.addItem(rootId);
                                contractDashboardResultsTable.setParent(rootId, root);
                                contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                                contractDashboardResultsTable.setCollapsed(root, false);

                            }

                        }
                        if (duplicateAlert != 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Same IFP(s) already available.Please select different IFP");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    int duplicateAlert = 0;
                    if (NumericConstants.THREE - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String psID = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.PS_CONT_SID).getValue());
                                setA.add(psID);
                            }
                        }
                        List<String> tmp = new ArrayList<>();

                        for (Object psContId : setA) {
                            String id = String.valueOf(psContId);
                            String query = "SELECT\n"
                                    + "	PS_M.PS_ID,\n"
                                    + "	PS_C.PS_NO,\n"
                                    + "	PS_C.PS_NAME,\n"
                                    + "	PS_C.PS_MODEL_SID\n"
                                    + "FROM\n"
                                    + "	PS_MODEL PS_M JOIN PS_CONTRACT PS_C ON ps_m.PS_MODEL_SID=Ps_c.PS_MODEL_SID\n"
                                    + "WHERE\n"
                                    + "	PS_CONTRACT_SID IN(" + id + ")";
                            List psList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                            Collection<?> returnList1 = contractDashboardResultsTable.getChildren(root);
                            if (returnList1 != null) {
                                for (Object item : returnList1) {

                                    if (contractDashboardResultsTable.getContainerProperty(item, Constants.CATEGORY).getValue().toString().equals(Constants.PS)) {
                                        String listidvalue = contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue().toString();

                                        Object[] obj = (Object[]) psList.get(0);
                                        String modelId = String.valueOf(obj[NumericConstants.THREE]);
                                        if (modelId.equals(listidvalue)) {
                                            duplicateAlert++;
                                        }

                                    }
                                }
                            }
                            if (psList != null && !psList.isEmpty()) {
                                Object[] obj = (Object[]) psList.get(0);
                                String modelId = String.valueOf(obj[NumericConstants.THREE]);

                                if (!tmp.contains(modelId)) {
                                    String conditionQuery = "select * from dbo.PS_DETAILS where PS_MODEL_SID=" + modelId + " and IFP_MODEL_SID=" + modelSId;
                                    HelperTableLocalServiceUtil.executeSelectQuery(conditionQuery);
                                    if (duplicateAlert == 0) {
                                        tmp.add(modelId);
                                        final Object rootId = contractDashboardResultsTable.addItem();
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    if (NumericConstants.FOUR - levelNumber == 1) {
                        Collection<?> returnList = transferCompTable1.getItemIds();
                        Set setA = new HashSet();
                        for (Object item : returnList) {
                            Boolean checked = (Boolean) transferCompContainer1.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
                            if (checked) {
                                String rsId = String.valueOf(transferCompContainer1.getContainerProperty(item, Constants.RS_CONT_SID).getValue());
                                setA.add(rsId);
                            }
                        }
                        List<String> tmp = new ArrayList<>();
                        for (Object rsContId : setA) {
                            String id = String.valueOf(rsContId);
                            String query = "SELECT\n"
                                    + "	RS_M.RS_ID,\n"
                                    + "	Rs_C.RS_NO,\n"
                                    + "	Rs_C.RS_NAME,\n"
                                    + "	Rs_C.RS_MODEL_SID\n"
                                    + "FROM\n"
                                    + "	RS_MODEL RS_M JOIN dbo.RS_CONTRACT Rs_C ON rs_m.RS_MODEL_SID=Rs_c.RS_MODEL_SID\n"
                                    + "WHERE\n"
                                    + "	RS_CONTRACT_SID in (" + id + ")";
                            List rsList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                            if (rsList != null && !rsList.isEmpty()) {
                                Object[] obj = (Object[]) rsList.get(0);
                                String modelId = String.valueOf(obj[NumericConstants.THREE]);
                                if (!tmp.contains(modelId)) {
                                    tmp.add(modelId);
                                    final Object rootId = contractDashboardResultsTable.addItem();
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(obj[0]));
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(obj[1]));
                                    contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(obj[NumericConstants.TWO]));
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_CORRECT_NODE);
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
                LOGGER.debug(" populate btn  query {} " , componentQuery);
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    contractInfoContainer.removeAllItems();
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
                    contractInfoContainer.addAll(companyList);
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
                String ifpID = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.HIDDEN_ID).getValue());
                String componentQuery = Constants.EMPTY;
                if (level.equals(Constants.TWO)) {
                    componentQuery = queryUtils.getItemMasterDetails(ifpID);
                } else if (level.equals(Constants.THREE)) {
                    componentQuery = queryUtils.getPSDetails(ifpID);
                } else if (level.equals(Constants.FOUR)) {
                    componentQuery = queryUtils.getRSDetails(ifpID);
                }
                LOGGER.debug(" Populate button query 2 {} " , componentQuery);
                List componentList = HelperTableLocalServiceUtil.executeSelectQuery(componentQuery);
                if (componentList != null && !componentList.isEmpty()) {
                    contractInfoContainer.removeAllItems();
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
                    contractInfoContainer.addAll(itemList);
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

    /**
     * Transfer TP as Contract Holder
     *
     * @throws SystemException
     * @throws PortalException
     * @throws ParseException
     */
    public List<Integer> saveTransferContract() throws  PortalException, ParseException {

        int contractMasterSid = 0;
        List<Integer> returnList = new ArrayList<>();
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            Collection<?> treeItem = contractDashboardResultsTable.getItemIds();

            for (Object item : treeItem) {
                String level = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    String contractId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_ID).getValue());
                    String contractNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NUMBER).getValue());
                    String contractName = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.DASHBOARD_NAME).getValue());
                    int contractType = Integer.parseInt(String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MARKET_TYPE).getValue()));
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
                    contractMaster.setCreatedBy(Integer.parseInt(userId));
                    contractMaster.setStartDate(new Date());
                    contractMaster.setInboundStatus("A");
                    contractMaster.setCreatedDate(new Date());
                    contractMaster.setModifiedDate(new Date());
                    contractMaster.setContHoldCompanyMasterSid(session.getCompanyMasterSid());
                    contractMaster = ContractMasterLocalServiceUtil.addContractMaster(contractMaster);
                    contractMasterSid = contractMaster.getContractMasterSid();
                    session.setContractMasterSid(String.valueOf(contractMasterSid));
                    returnList.add(contractMasterSid);
                    String aliasType = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "type").getValue());
                    String aliasNumber = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, "number").getValue());
                    ContractAliasMaster cam = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    cam.setContractAliasNo(aliasNumber);
                    cam.setContractAliasType(aliasType != null && !Constants.NULL.equals(aliasType) ? Integer.parseInt(aliasType) : 0);
                    cam.setStartDate(new Date());
                    cam.setModifiedDate(new Date());
                    cam.setCreatedBy(1);
                    cam.setCreatedDate(new Date());
                    cam.setSource("BPI");
                    cam.setInboundStatus("A");
                    cam.setContractMasterSid(contractMasterSid);
                    ContractAliasMasterLocalServiceUtil.addContractAliasMaster(cam);

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
                    cfpmodel.setCfpStatus(Integer.parseInt(cfpStatus));
                    cfpmodel.setCfpId(cfpId);
                    cfpmodel.setCfpStartDate(sdate);
                    cfpmodel.setCfpEndDate(edate);
                    cfpmodel.setSource("GCM");
                    cfpmodel.setInboundStatus("A");
                    cfpmodel.setCreatedDate(new Date());
                    cfpmodel.setModifiedDate(new Date());
                    cfpmodel.setRecordLockStatus(false);
                    cfpmodel.setCreatedBy(Integer.parseInt(userId));
                    cfpmodel.setModifiedBy(Integer.parseInt(userId));
                    cfpmodel = CfpModelLocalServiceUtil.addCfpModel(cfpmodel);
                    String companySid = session.getCompanyMasterSid();
                    returnList.add(Integer.valueOf(companySid));
                    CfpDetails cfpDetails;
                    cfpDetails = CfpDetailsLocalServiceUtil.createCfpDetails(0);
                    cfpDetails.setCfpModelSid(cfpmodel.getCfpModelSid());
                    cfpDetails.setCompanyMasterSid(Integer.parseInt(companySid));
                    cfpDetails.setCompanyStartDate(new Date());
                    cfpDetails.setInboundStatus("A");
                    cfpDetails.setSource("BPI");
                    cfpDetails.setRecordLockStatus(false);
                    cfpDetails.setCreatedDate(new Date());
                    cfpDetails.setModifiedDate(new Date());
                    cfpDetails.setCreatedBy(Integer.parseInt(userId));
                    cfpDetails.setModifiedBy(Integer.parseInt(userId));
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
                    cfpcontract.setCreatedBy(Integer.parseInt(userId));
                    cfpcontract.setModifiedBy(Integer.parseInt(userId));
                    cfpcontract = CfpContractLocalServiceUtil.addCfpContract(cfpcontract);
                    String cfpContractSid = String.valueOf(cfpcontract.getCfpContractSid());
                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(cfpContractSid);
                    CfpContractDetails cfpcontractDetails;
                    cfpcontractDetails = CfpContractDetailsLocalServiceUtil.createCfpContractDetails(0);
                    cfpcontractDetails.setCfpContractSid(cfpcontract.getCfpContractSid());
                    cfpcontractDetails.setCompanyMasterSid(Integer.parseInt(session.getCompanyMasterSid()));
                    cfpcontractDetails.setCompanyStartDate(cfpmodel.getCfpStartDate());
                    cfpcontractDetails.setCompanyEndDate(cfpmodel.getCfpEndDate());
                    cfpcontractDetails.setCfpContractAttachedStatus(cfpmodel.getCfpStatus());
                    cfpcontractDetails.setCreatedDate(new Date());
                    cfpcontractDetails.setModifiedDate(new Date());
                    cfpcontractDetails.setCreatedBy(Integer.parseInt(userId));
                    cfpcontractDetails.setModifiedBy(Integer.parseInt(userId));
                    cfpcontractDetails.setInboundStatus("A");
                    cfpcontractDetails.setRecordLockStatus(false);
                    CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpcontractDetails);

                } else if (level.equals(Constants.TWO)) {
                    String ifpModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(item);
                    String cfpContractSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    IfpModel ifpmodel;
                    ifpmodel = IfpModelLocalServiceUtil.getIfpModel(Integer.parseInt(ifpModelId));
                    IfpContract ifpcontract;
                    ifpcontract = IfpContractLocalServiceUtil.createIfpContract(0);
                    ifpcontract.setIfpModelSid(Integer.parseInt(ifpModelId));
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
                    ifpcontract.setCreatedBy(Integer.parseInt(userId));
                    ifpcontract.setModifiedBy(Integer.parseInt(userId));
                    ifpcontract.setCreatedDate(new Date());
                    ifpcontract.setModifiedDate(new Date());
                    ifpcontract = IfpContractLocalServiceUtil.addIfpContract(ifpcontract);

                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(ifpcontract.getIfpContractSid()));

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(ifpcontract.getIfpContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(ifpModelId);
                    input.add(simpleDateFormat.format(ifpmodel.getIfpStartDate()));
                    input.add(ifpmodel.getIfpEndDate() == null ? null : simpleDateFormat.format(ifpmodel.getIfpEndDate()));
                    IfpContractDetailsImpl.saveIfpDetailsAttached(input, null);
                } else if (level.equals(Constants.THREE)) {

                    String psModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(item);
                    String ifpSystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentItem);
                    String cfpSystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    PsContract psContract;
                    psContract = PsContractLocalServiceUtil.createPsContract(0);
                    psContract.setPsModelSid(Integer.parseInt(psModelId));
                    PsModel psmodel;
                    psmodel = PsModelLocalServiceUtil.getPsModel(Integer.parseInt(psModelId));
                    psContract.setPsName(psmodel.getPsName());
                    psContract.setPsType(psmodel.getPsType());
                    psContract.setPsCategory(psmodel.getPsCategory());
                    psContract.setPsStatus(psmodel.getPsStatus());
                    psContract.setPsStartDate(psmodel.getPsStartDate());
                    psContract.setPsEndDate(psmodel.getPsEndDate());
                    psContract.setContractMasterSid(contractMasterSid);
                    psContract.setCfpContractSid(cfpSystemId);
                    psContract.setIfpContractSid(ifpSystemId);
                    psContract.setInboundStatus("A");
                    psContract.setRecordLockStatus(false);
                    psContract.setSource("GCM");
                    psContract.setCreatedBy(Integer.parseInt(userId));
                    psContract.setModifiedBy(Integer.parseInt(userId));
                    psContract.setCreatedDate(new Date());
                    psContract.setModifiedDate(new Date());
                    psContract = PsContractLocalServiceUtil.addPsContract(psContract);

                    contractDashboardResultsTable.getContainerProperty(item, Constants.HIDDEN_ID).setValue(String.valueOf(psContract.getPsContractSid()));
                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(psContract.getPsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(psModelId);
                    input.add(simpleDateFormat.format(psmodel.getPsStartDate()));
                    input.add(psmodel.getPsEndDate() == null ? null : simpleDateFormat.format(psmodel.getPsEndDate()));
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
                    rsContract.setRsModelSid(Integer.parseInt(rsModelId));
                    RsModel rsmodel;
                    rsmodel = RsModelLocalServiceUtil.getRsModel(Integer.parseInt(rsModelId));
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
                    rsContract.setCreatedBy(Integer.parseInt(userId));
                    rsContract.setModifiedBy(Integer.parseInt(userId));
                    rsContract.setCreatedDate(new Date());
                    rsContract.setModifiedDate(new Date());
                    rsContract = RsContractLocalServiceUtil.addRsContract(rsContract);

                    List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
                    input.add(rsContract.getRsContractSid());
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
                    input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    input.add(simpleDateFormat.format(new Date()));
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
            LOGGER.error("",e);
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
            LOGGER.error("",e);
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

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws   NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase(Locale.ENGLISH));

    }

    public void createWorkSheetContent(final Integer end, final PrintWriter printWriter) {
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
            LOGGER.error("",e);
        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Promote Customer", "TransferContractTab");
            populateBtn1.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn1", functionHM));
            removeBtn2.setVisible(CommonLogic.isButtonVisibleAccess("removeBtn2", functionHM));
            populateBtn2.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn2", functionHM));
            addToTreeBtn1.setVisible(CommonLogic.isButtonVisibleAccess("addToTreeBtn1", functionHM));
            excelBtn1.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn1", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    private void setIFPValues(CurrentContractDTO dto) {
        if (dto != null) {
            int ifpIdValue = Integer.parseInt(dto.getIfpContSid());
            String query = queryUtils.getIFPDetails(ifpIdValue);
            List ifpList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (ifpList != null && !ifpList.isEmpty()) {
                Object[] object = (Object[]) ifpList.get(0);
                ifpId.setValue(String.valueOf(object[0]));
                ifpNo.setValue(String.valueOf(object[1]));
                ifpName.setValue(String.valueOf(object[NumericConstants.TWO]));
                ifpStatus.setValue(String.valueOf(object[NumericConstants.THREE]));
                if (object[NumericConstants.FOUR] != null) {
                    String date = df.format(object[NumericConstants.FOUR]);
                    ifpStartDate.setValue(date);
                } else {
                    ifpStartDate.setValue(Constants.EMPTY);
                }
            }
        }
    }
    private void setPSValues(CurrentContractDTO dto) {
        if (dto != null) {
            int psIdValue = Integer.parseInt(dto.getPsContSid());
            String query = queryUtils.getPSDetails(psIdValue);
            List psList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (psList != null && !psList.isEmpty()) {
                Object[] object = (Object[]) psList.get(0);
                psId.setValue(String.valueOf(object[0]));
                psNo.setValue(String.valueOf(object[1]));
                psName.setValue(String.valueOf(object[NumericConstants.TWO]));
                psStatus.setValue(String.valueOf(object[NumericConstants.THREE]));
                if (object[NumericConstants.FOUR] != null) {
                    String date = df.format(object[NumericConstants.FOUR]);
                    psStartDate.setValue(date);
                } else {
                    psStartDate.setValue(Constants.EMPTY);
                }
            }
        }
    }
    private void setRSValues(CurrentContractDTO dto) {
        int rsIdValue = Integer.parseInt(dto.getRsContSid());
        String query = "select rm.RS_ID,h.DESCRIPTION AS STATUS,rc.RS_NO,rc.RS_START_DATE,rc.RS_NAME,rc.RS_END_DATE,h1.DESCRIPTION AS FREQ,h2.DESCRIPTION AS TYPE, h3.DESCRIPTION AS RS_TYPE "
                + " from RS_MODEL rm join RS_CONTRACT rc on rm.RS_MODEL_SID=rc.RS_MODEL_SID and rc.RS_CONTRACT_SID=" + rsIdValue + " left join dbo.HELPER_TABLE h on rm.RS_STATUS=h.HELPER_TABLE_SID join dbo.HELPER_TABLE h1 "
                + " on h1.HELPER_TABLE_SID=rm.REBATE_FREQUENCY join dbo.HELPER_TABLE h2 on h2.HELPER_TABLE_SID=rm.REBATE_PROGRAM_TYPE"
                + " join HELPER_TABLE h3 ON rm.RS_TYPE=h3.HELPER_TABLE_SID";
        List rsList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (rsList != null && !rsList.isEmpty()) {
            Object[] object = (Object[]) rsList.get(0);
            rebateScheduleId.setValue(String.valueOf(object[0]));
            status.setValue(String.valueOf(object[1]));
            rsNumber.setValue(String.valueOf(object[NumericConstants.TWO]));
            if (object[NumericConstants.THREE] != null) {
                String date = df.format(object[NumericConstants.THREE]);
                startDate.setValue(date);
            } else {
                startDate.setValue(Constants.EMPTY);
            }
            rsName.setValue(String.valueOf(object[NumericConstants.FOUR]));
            if (object[NumericConstants.FIVE] != null) {
                String date = df.format(object[NumericConstants.FIVE]);
                endDate.setValue(date);
            } else {
                endDate.setValue(Constants.EMPTY);
            }
            rebateFrequency.setValue(String.valueOf(object[NumericConstants.SIX]));
            rarType.setValue(String.valueOf(object[NumericConstants.SEVEN]));
            rsType.setValue(String.valueOf(object[NumericConstants.EIGHT]));
        }
    }

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
}
