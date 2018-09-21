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
                        ComponentInfoDTO itemDto=  new ComponentInfoDTO();
                        Object[] objeTrans = (Object[]) componentList.get(i);
                        itemDto.setItemNo(String.valueOf(objeTrans[0]));
                        itemDto.setItemName(String.valueOf(objeTrans[1]));
                        itemDto.setTherapyClass(Converters.convertNullToEmpty(objeTrans[NumericConstants.TWO]));
                        itemDto.setBrand((objeTrans[NumericConstants.THREE] != null) ? String.valueOf(objeTrans[NumericConstants.THREE]) : Constants.EMPTY);
                        itemDto.setIfpStatus(String.valueOf(objeTrans[NumericConstants.FOUR]));
                        if (objeTrans[NumericConstants.FIVE] != null) {
                            String dateGcm = df.format((Date) objeTrans[NumericConstants.FIVE]);
                            itemDto.setIfpStartDate(dateGcm);
                        } else {
                            itemDto.setIfpStartDate(Constants.EMPTY);
                        }
                        if (objeTrans[NumericConstants.SIX] != null) {
                            String date = df.format((Date) objeTrans[NumericConstants.SIX]);
                            itemDto.setIfpEndDate(date);
                        } else {
                            itemDto.setIfpEndDate(Constants.EMPTY);
                        }
                        itemList.add(itemDto);
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
                        ComponentInfoDTO itemDtoPs = new ComponentInfoDTO();
                        Object[] objePs = (Object[]) componentList.get(i);
                        itemDtoPs.setItemNo(String.valueOf(objePs[0]));
                        itemDtoPs.setItemName(String.valueOf(objePs[1]));
                        itemDtoPs.setTherapyClass(Converters.convertNullToEmpty(objePs[NumericConstants.TWO]));
                        itemDtoPs.setBrand((objePs[NumericConstants.THREE] != null) ? String.valueOf(objePs[NumericConstants.THREE]) : Constants.EMPTY);
                        itemDtoPs.setIfpStatus(String.valueOf(objePs[NumericConstants.FOUR]));
                        if (objePs[NumericConstants.FIVE] != null) {
                            String date = df.format((Date) objePs[NumericConstants.FIVE]);
                            itemDtoPs.setIfpStartDate(date);
                        } else {
                            itemDtoPs.setIfpStartDate(Constants.EMPTY);
                        }
                        if (objePs[NumericConstants.SIX] != null) {
                            String datePs = df.format((Date) objePs[NumericConstants.SIX]);
                            itemDtoPs.setIfpEndDate(datePs);
                        } else {
                            itemDtoPs.setIfpEndDate(Constants.EMPTY);
                        }
                        if ((objePs[NumericConstants.EIGHT] != null) && !objePs[NumericConstants.EIGHT].equals(Constants.SELECT_ONE) && !objePs[NumericConstants.EIGHT].equals(Constants.NULL)) {
                            itemDtoPs.setPriceType(String.valueOf(objePs[NumericConstants.EIGHT]));
                        } else {
                            itemDtoPs.setPriceType(Constants.EMPTY);
                        }

                        if (objePs[NumericConstants.SEVEN] != null) {
                            String datePrice = df.format((Date) objePs[NumericConstants.SEVEN]);
                            itemDtoPs.setPpStartDate(datePrice);
                        } else {
                            itemDtoPs.setPpStartDate(Constants.EMPTY);
                        }
                        priceList.add(itemDtoPs);
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
                CurrentContractDTO dto = list.get(i);
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
                        ComponentInfoDTO companyDtoPopulate = new ComponentInfoDTO();
                        Object[] objePopulate = (Object[]) componentList.get(i);
                        companyDtoPopulate.setCompanyNo(String.valueOf(objePopulate[1]));
                        companyDtoPopulate.setCompanyName(String.valueOf(objePopulate[NumericConstants.TWO]));
                        companyDtoPopulate.setCompanyStatus(String.valueOf(objePopulate[NumericConstants.FIVE]));
                        if (objePopulate[NumericConstants.THREE] != null) {
                            String date = df.format(objePopulate[NumericConstants.THREE]);
                            companyDtoPopulate.setCompanyStartDate(date);
                        } else {
                            companyDtoPopulate.setCompanyStartDate(Constants.EMPTY);
                        }
                        if (objePopulate[NumericConstants.FOUR] != null) {
                            String date = df.format(objePopulate[NumericConstants.FOUR]);
                            companyDtoPopulate.setCompanyEndDate(date);
                        } else {
                            companyDtoPopulate.setCompanyEndDate(Constants.EMPTY);
                        }
                        companyList.add(companyDtoPopulate);
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

        int contractMasterSidTC = 0;
        List<Integer> returnListTc=  new ArrayList<>();
        try {
            String userIdTc = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            Collection<?> treeItem = contractDashboardResultsTable.getItemIds();

            for (Object itemTransContract : treeItem) {
                String level = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    String contractId = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.DASHBOARD_ID).getValue());
                    String contractNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.DASHBOARD_NUMBER).getValue());
                    String contractName = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.DASHBOARD_NAME).getValue());
                    int contractType = Integer.parseInt(String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.MARKET_TYPE).getValue()));
                    int contStatus = 1;
                    ContractMaster contractMasterTransContract;
                    contractMasterTransContract = ContractMasterLocalServiceUtil.createContractMaster(0);
                    contractMasterTransContract.setContractId(contractId);
                    contractMasterTransContract.setContractNo(contractNo);
                    contractMasterTransContract.setContractName(contractName);
                    contractMasterTransContract.setContractType(contractType);
                    contractMasterTransContract.setProcessStatus(true);
                    contractMasterTransContract.setSource("GCM");
                    contractMasterTransContract.setContractStatus(contStatus);
                    contractMasterTransContract.setCreatedBy(Integer.parseInt(userIdTc));
                    contractMasterTransContract.setStartDate(new Date());
                    contractMasterTransContract.setInboundStatus("A");
                    contractMasterTransContract.setCreatedDate(new Date());
                    contractMasterTransContract.setModifiedDate(new Date());
                    contractMasterTransContract.setContHoldCompanyMasterSid(session.getCompanyMasterSid());
                    contractMasterTransContract = ContractMasterLocalServiceUtil.addContractMaster(contractMasterTransContract);
                    contractMasterSidTC = contractMasterTransContract.getContractMasterSid();
                    session.setContractMasterSid(String.valueOf(contractMasterSidTC));
                    returnListTc.add(contractMasterSidTC);
                    String aliasTypeTc=  String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, "type").getValue());
                    String aliasNumberTc=  String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, "number").getValue());
                    ContractAliasMaster camTc = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                    camTc.setContractAliasNo(aliasNumberTc);
                    camTc.setContractAliasType(aliasTypeTc != null && !Constants.NULL.equals(aliasTypeTc) ? Integer.parseInt(aliasTypeTc) : 0);
                    camTc.setStartDate(new Date());
                    camTc.setModifiedDate(new Date());
                    camTc.setCreatedBy(1);
                    camTc.setCreatedDate(new Date());
                    camTc.setSource("BPI");
                    camTc.setInboundStatus("A");
                    camTc.setContractMasterSid(contractMasterSidTC);
                    ContractAliasMasterLocalServiceUtil.addContractAliasMaster(camTc);

                } else if (level.equals(Constants.ONE)) {

                    CfpModel cfpmodelTc;
                    cfpmodelTc = CfpModelLocalServiceUtil.createCfpModel(0);
                    String cfpNameTc=  String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.CFP_NAME).getValue());
                    String cfpIdTc = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, "id").getValue());
                    String cfpNoTc = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, "cfpNo").getValue());
                    String cfpStatus = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, "cfpStatus").getValue());
                    String cfpEndDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.CFP_END_DATE).getValue());
                    String cfpStartDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.CFP_START_DATE).getValue());
                    Date sdate = simpleDateFormat.parse(cfpStartDate);
                    Date edate = simpleDateFormat.parse(cfpEndDate);
                    cfpmodelTc.setCfpName(cfpNameTc);
                    cfpmodelTc.setCfpNo(cfpNoTc);
                    cfpmodelTc.setCfpStatus(Integer.parseInt(cfpStatus));
                    cfpmodelTc.setCfpId(cfpIdTc);
                    cfpmodelTc.setCfpStartDate(sdate);
                    cfpmodelTc.setCfpEndDate(edate);
                    cfpmodelTc.setSource("GCM");
                    cfpmodelTc.setInboundStatus("A");
                    cfpmodelTc.setCreatedDate(new Date());
                    cfpmodelTc.setModifiedDate(new Date());
                    cfpmodelTc.setRecordLockStatus(false);
                    cfpmodelTc.setCreatedBy(Integer.parseInt(userIdTc));
                    cfpmodelTc.setModifiedBy(Integer.parseInt(userIdTc));
                    cfpmodelTc = CfpModelLocalServiceUtil.addCfpModel(cfpmodelTc);
                    String companySid = session.getCompanyMasterSid();
                    returnListTc.add(Integer.valueOf(companySid));
                    CfpDetails cfpDetailsTc;
                    cfpDetailsTc = CfpDetailsLocalServiceUtil.createCfpDetails(0);
                    cfpDetailsTc.setCfpModelSid(cfpmodelTc.getCfpModelSid());
                    cfpDetailsTc.setCompanyMasterSid(Integer.parseInt(companySid));
                    cfpDetailsTc.setCompanyStartDate(new Date());
                    cfpDetailsTc.setInboundStatus("A");
                    cfpDetailsTc.setSource("BPI");
                    cfpDetailsTc.setRecordLockStatus(false);
                    cfpDetailsTc.setCreatedDate(new Date());
                    cfpDetailsTc.setModifiedDate(new Date());
                    cfpDetailsTc.setCreatedBy(Integer.parseInt(userIdTc));
                    cfpDetailsTc.setModifiedBy(Integer.parseInt(userIdTc));
                    CfpDetailsLocalServiceUtil.addCfpDetails(cfpDetailsTc);

                    CfpContract cfpcontractTranContract;
                    cfpcontractTranContract = CfpContractLocalServiceUtil.createCfpContract(0);
                    cfpcontractTranContract.setCfpModelSid(cfpmodelTc.getCfpModelSid());
                    cfpcontractTranContract.setCfpName(cfpmodelTc.getCfpName());
                    cfpcontractTranContract.setCfpStatus(1);
                    cfpcontractTranContract.setCfpStartDate(new Date());
                    cfpcontractTranContract.setContractMasterSid(contractMasterSidTC);
                    cfpcontractTranContract.setInboundStatus("A");
                    cfpcontractTranContract.setSource("GCM");
                    cfpcontractTranContract.setRecordLockStatus(false);
                    cfpcontractTranContract.setCreatedDate(new Date());
                    cfpcontractTranContract.setModifiedDate(new Date());
                    cfpcontractTranContract.setCreatedBy(Integer.parseInt(userIdTc));
                    cfpcontractTranContract.setModifiedBy(Integer.parseInt(userIdTc));
                    cfpcontractTranContract = CfpContractLocalServiceUtil.addCfpContract(cfpcontractTranContract);
                    String cfpContractSid = String.valueOf(cfpcontractTranContract.getCfpContractSid());
                    contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.HIDDEN_ID).setValue(cfpContractSid);
                    CfpContractDetails cfpcontractDetailsTransContract;
                    cfpcontractDetailsTransContract = CfpContractDetailsLocalServiceUtil.createCfpContractDetails(0);
                    cfpcontractDetailsTransContract.setCfpContractSid(cfpcontractTranContract.getCfpContractSid());
                    cfpcontractDetailsTransContract.setCompanyMasterSid(Integer.parseInt(session.getCompanyMasterSid()));
                    cfpcontractDetailsTransContract.setCompanyStartDate(cfpmodelTc.getCfpStartDate());
                    cfpcontractDetailsTransContract.setCompanyEndDate(cfpmodelTc.getCfpEndDate());
                    cfpcontractDetailsTransContract.setCfpContractAttachedStatus(cfpmodelTc.getCfpStatus());
                    cfpcontractDetailsTransContract.setCreatedDate(new Date());
                    cfpcontractDetailsTransContract.setModifiedDate(new Date());
                    cfpcontractDetailsTransContract.setCreatedBy(Integer.parseInt(userIdTc));
                    cfpcontractDetailsTransContract.setModifiedBy(Integer.parseInt(userIdTc));
                    cfpcontractDetailsTransContract.setInboundStatus("A");
                    cfpcontractDetailsTransContract.setRecordLockStatus(false);
                    CfpContractDetailsLocalServiceUtil.addCfpContractDetails(cfpcontractDetailsTransContract);

                } else if (level.equals(Constants.TWO)) {
                    String ifpModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(itemTransContract);
                    String cfpContractSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    IfpModel ifpmodelTransContract;
                    ifpmodelTransContract = IfpModelLocalServiceUtil.getIfpModel(Integer.parseInt(ifpModelId));
                    IfpContract ifpcontractTc;
                    ifpcontractTc = IfpContractLocalServiceUtil.createIfpContract(0);
                    ifpcontractTc.setIfpModelSid(Integer.parseInt(ifpModelId));
                    ifpcontractTc.setIfpName(ifpmodelTransContract.getIfpName());
                    ifpcontractTc.setIfpType(ifpmodelTransContract.getIfpType());
                    ifpcontractTc.setIfpCategory(ifpmodelTransContract.getIfpCategory());
                    ifpcontractTc.setIfpStatus(ifpmodelTransContract.getIfpStatus());
                    ifpcontractTc.setIfpStartDate(ifpmodelTransContract.getIfpStartDate());
                    ifpcontractTc.setIfpEndDate(ifpmodelTransContract.getIfpEndDate());
                    ifpcontractTc.setCfpContractSid(cfpContractSId);
                    ifpcontractTc.setContractMasterSid(contractMasterSidTC);
                    ifpcontractTc.setInboundStatus("A");
                    ifpcontractTc.setRecordLockStatus(false);
                    ifpcontractTc.setSource("GCM");
                    ifpcontractTc.setCreatedBy(Integer.parseInt(userIdTc));
                    ifpcontractTc.setModifiedBy(Integer.parseInt(userIdTc));
                    ifpcontractTc.setCreatedDate(new Date());
                    ifpcontractTc.setModifiedDate(new Date());
                    ifpcontractTc = IfpContractLocalServiceUtil.addIfpContract(ifpcontractTc);

                    contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.HIDDEN_ID).setValue(String.valueOf(ifpcontractTc.getIfpContractSid()));

                    List<Object> inputTc = new ArrayList<>(NumericConstants.EIGHT);
                    inputTc.add(ifpcontractTc.getIfpContractSid());
                    inputTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputTc.add(simpleDateFormat.format(new Date()));
                    inputTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputTc.add(simpleDateFormat.format(new Date()));
                    inputTc.add(ifpModelId);
                    inputTc.add(simpleDateFormat.format(ifpmodelTransContract.getIfpStartDate()));
                    inputTc.add(ifpmodelTransContract.getIfpEndDate() == null ? null : simpleDateFormat.format(ifpmodelTransContract.getIfpEndDate()));
                    IfpContractDetailsImpl.saveIfpDetailsAttached(inputTc);
                } else if (level.equals(Constants.THREE)) {

                    String psModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.MODEL_ID).getValue());
                    Object parentItem = contractDashboardResultsTable.getParent(itemTransContract);
                    String ifpSystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentItem);
                    String cfpSystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    PsContract psContractTc;
                    psContractTc = PsContractLocalServiceUtil.createPsContract(0);
                    psContractTc.setPsModelSid(Integer.parseInt(psModelId));
                    PsModel psmodelTc;
                    psmodelTc = PsModelLocalServiceUtil.getPsModel(Integer.parseInt(psModelId));
                    psContractTc.setPsName(psmodelTc.getPsName());
                    psContractTc.setPsType(psmodelTc.getPsType());
                    psContractTc.setPsCategory(psmodelTc.getPsCategory());
                    psContractTc.setPsStatus(psmodelTc.getPsStatus());
                    psContractTc.setPsStartDate(psmodelTc.getPsStartDate());
                    psContractTc.setPsEndDate(psmodelTc.getPsEndDate());
                    psContractTc.setContractMasterSid(contractMasterSidTC);
                    psContractTc.setCfpContractSid(cfpSystemId);
                    psContractTc.setIfpContractSid(ifpSystemId);
                    psContractTc.setInboundStatus("A");
                    psContractTc.setRecordLockStatus(false);
                    psContractTc.setSource("GCM");
                    psContractTc.setCreatedBy(Integer.parseInt(userIdTc));
                    psContractTc.setModifiedBy(Integer.parseInt(userIdTc));
                    psContractTc.setCreatedDate(new Date());
                    psContractTc.setModifiedDate(new Date());
                    psContractTc = PsContractLocalServiceUtil.addPsContract(psContractTc);

                    contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.HIDDEN_ID).setValue(String.valueOf(psContractTc.getPsContractSid()));
                    List<Object> inputPsTc = new ArrayList<>(NumericConstants.EIGHT);
                    inputPsTc.add(psContractTc.getPsContractSid());
                    inputPsTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputPsTc.add(simpleDateFormat.format(new Date()));
                    inputPsTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputPsTc.add(simpleDateFormat.format(new Date()));
                    inputPsTc.add(psModelId);
                    inputPsTc.add(simpleDateFormat.format(psmodelTc.getPsStartDate()));
                    inputPsTc.add(psmodelTc.getPsEndDate() == null ? null : simpleDateFormat.format(psmodelTc.getPsEndDate()));
                    PsContractDetailsImpl.savePsDetailsAttached(inputPsTc);

                } else if (level.equals(Constants.FOUR)) {
                    String rsModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(itemTransContract, Constants.MODEL_ID).getValue());
                    Object psParentItem = contractDashboardResultsTable.getParent(itemTransContract);
                    String contractPSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(psParentItem, Constants.HIDDEN_ID).getValue());
                    Object parentIFPItem = contractDashboardResultsTable.getParent(psParentItem);
                    String contractIFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentIFPItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentIFPItem);
                    String contractCFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    RsContract rsContractTc;
                    rsContractTc = RsContractLocalServiceUtil.createRsContract(0);
                    rsContractTc.setRsModelSid(Integer.parseInt(rsModelId));
                    RsModel rsmodelTc;
                    rsmodelTc = RsModelLocalServiceUtil.getRsModel(Integer.parseInt(rsModelId));
                    rsContractTc.setRsId(rsmodelTc.getRsId());
                    rsContractTc.setRsNo(rsmodelTc.getRsNo());
                    rsContractTc.setRsType(rsmodelTc.getRsType());
                    rsContractTc.setRebateProgramType(rsmodelTc.getRebateProgramType());
                    rsContractTc.setRsCategory(rsmodelTc.getRsCategory());
                    rsContractTc.setRsStatus(rsmodelTc.getRsStatus());
                    rsContractTc.setRsDesignation(String.valueOf(rsmodelTc.getRsDesignation()));
                    rsContractTc.setRsStartDate(rsmodelTc.getRsStartDate());
                    rsContractTc.setRsEndDate(rsmodelTc.getRsEndDate());
                    rsContractTc.setRsTradeClass(rsmodelTc.getRsTradeClass());
                    rsContractTc.setCfpContractSid(contractCFPId);
                    rsContractTc.setIfpContractSid(contractIFPId);
                    rsContractTc.setPsContractSid(contractPSId);
                    rsContractTc.setContractMasterSid(contractMasterSidTC);
                    rsContractTc.setInboundStatus("A");
                    rsContractTc.setRecordLockStatus(false);
                    rsContractTc.setSource("GCM");
                    rsContractTc.setCreatedBy(Integer.parseInt(userIdTc));
                    rsContractTc.setModifiedBy(Integer.parseInt(userIdTc));
                    rsContractTc.setCreatedDate(new Date());
                    rsContractTc.setModifiedDate(new Date());
                    rsContractTc = RsContractLocalServiceUtil.addRsContract(rsContractTc);

                    List<Object> inputRsTc = new ArrayList<>(NumericConstants.EIGHT);
                    inputRsTc.add(rsContractTc.getRsContractSid());
                    inputRsTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputRsTc.add(simpleDateFormat.format(new Date()));
                    inputRsTc.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    inputRsTc.add(simpleDateFormat.format(new Date()));
                    inputRsTc.add(rsModelId);
                    inputRsTc.add(rsmodelTc.getRsStartDate());
                    inputRsTc.add(rsmodelTc.getRsEndDate() == null ? null : rsmodelTc.getRsEndDate());
                    RsContractDetailsImpl.saveRsDetailsAttached(inputRsTc);

                }
            }
            LOGGER.debug("TP Promoted Successfully as Contract Holder");
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return returnListTc;
    }

    public void saveCfp(String cfpId, String cfpModelId) {
        logic.saveCfpForTransferComponent(cfpId, cfpModelId);
    }

    public void saveIfp(String ifpId, String ifpModelId) {
        logic.saveIfpForTransferComponent(ifpId, ifpModelId);
    }

    public void savePs(String psid, String psModelId) {
        logic.savePsForTransferComponent(psid, psModelId);
    }

    public void saveRs(String rsid, String rsModelId) {
        logic.saveRsForTransferComponent(rsid, rsModelId);

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
                Object[] psValues = (Object[]) psList.get(0);
                psId.setValue(String.valueOf(psValues[0]));
                psNo.setValue(String.valueOf(psValues[1]));
                psName.setValue(String.valueOf(psValues[NumericConstants.TWO]));
                psStatus.setValue(String.valueOf(psValues[NumericConstants.THREE]));
                if (psValues[NumericConstants.FOUR] != null) {
                    String date = df.format(psValues[NumericConstants.FOUR]);
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
