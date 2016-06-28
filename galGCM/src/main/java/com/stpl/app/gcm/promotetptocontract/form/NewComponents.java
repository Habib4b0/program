/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import static com.stpl.app.gcm.discount.logic.DiscountLogic.DBDate;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.logic.NewComponentSearchTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpDetails;
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
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
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
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Nandha kumar
 */
public class NewComponents extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NewComponents.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    boolean isTableUpdate = false;

    @UiField("massValue")
    public ComboBox massValue;

    @UiField("massStartDate")
    public PopupDateField massStartDate;

    @UiField("massEndDate")
    public PopupDateField massEndDate;

    @UiField("compItemSearchTableLayout")
    public VerticalLayout compItemSearchTableLayout;
    @UiField("contractDashboardResultsTableLayout")
    public VerticalLayout contractDashboardResultsTableLayout;
    @UiField("contractComponentDetailsTableLayout")
    public VerticalLayout contractComponentDetailsTableLayout;
    @UiField("componentDetailsTableLayout")
    public VerticalLayout componentDetailsTableLayout;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateRadio;
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    @UiField("massPopulateBtn")
    public Button massPopulateBtn;
    @UiField("componentType")
    public ComboBox componentType;
    @UiField("status")
    public ComboBox status;
    @UiField("searchType")
    public ComboBox searchType;
    @UiField("rsType")
    public ComboBox rsType;
    @UiField("searchFieldDdlb")
    public ComboBox searchFieldDdlb;
    @UiField("excelBtn1")
    public Button excelBtn1;
    @UiField("excelBtn2")
    public Button excelBtn2;
    @UiField("removeBtn2")
    public Button removeBtn2;
    @UiField("searchBtn1")
    public Button searchBtn1;
    @UiField("searchValueTextField")
    public TextField searchValueTextField;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;
    @UiField("addItemBtn")
    public Button addItemBtn;
    @UiField("removeFromTreeBtn")
    public Button removeFromTreeBtn;
    @UiField("populateBtn2")
    public Button populateBtn2;
    /**
     * IFP Layout
     */
    @UiField("componentInfoIfpLayout")
    public GridLayout componentInfoIfpLayout;
    /**
     * PS Layout
     */
    @UiField("componentInfoPsLayout")
    public GridLayout componentInfoPsLayout;
    /**
     * RS Layout
     */
    @UiField("componentInfoRebateLayout")
    public GridLayout componentInfoRebateLayout;
    /*ifp Component */
    @UiField("ifpId")
    public TextField ifpId;
    @UiField("ifpNo")
    public TextField ifpNo;
    @UiField("ifpName")
    public TextField ifpName;
    @UiField("ifpStatus")
    public ComboBox ifpStatus;
    @UiField("ifpStartDate")
    public PopupDateField ifpStartDate;
    @UiField("ifpEndDate")
    public PopupDateField ifpEndDate;
    @UiField("ifpType")
    public ComboBox ifpType;
    /*ps Component */
    @UiField("psId")
    public TextField psId;
    @UiField("psNo")
    public TextField psNo;
    @UiField("psName")
    public TextField psName;
    @UiField("psStatus")
    public ComboBox psStatus;
    @UiField("psStartDate")
    public PopupDateField psStartDate;
    @UiField("psEndDate")
    public PopupDateField psEndDate;
    @UiField("psType")
    public ComboBox psType;
    /**
     * Rebate Components
     */
    @UiField("rebateScheduleId")
    public TextField rebateScheduleId;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("rsName")
    public TextField rsName;
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
    @UiField("paymentMethod")
    public ComboBox paymentMethod;
    @UiField("rsProgramType")
    public ComboBox rsProgramType;
    @UiField("paymentFrequency")
    public ComboBox paymentFrequency;
    @UiField("rebatePlanLevel")
    public ComboBox rebatePlanLevel;

    private TreeTable contractDashboardResultsTable = new TreeTable();
    public ExtFilterTable contractComponentDetailsTable = new ExtFilterTable();
    public ExtFilterTable componentDetailsTable = new ExtFilterTable();
    private BeanItemContainer<ComponentInfoDTO> compItemSearchResultsContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> componentDetailResultsContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<ComponentInfoDTO> componentResultsContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    CustomTreeContainer<ComponentInfoDTO> dashBoardTreeContainer = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    public List parentList = new ArrayList();
    public int levelValue;
    CurrentContractDTO currentContractDTO = new CurrentContractDTO();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    NewComponentSearchTableLogic compItemSearchTableLogic = new NewComponentSearchTableLogic();
    public ExtPagedTable compItemSearchTable = new ExtPagedTable(compItemSearchTableLogic);
    ComponentInfoDTO contInfoDto = new ComponentInfoDTO();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    QueryUtils queryUtils = new QueryUtils();
    List<ComponentInfoDTO> selecteditemList = new ArrayList<ComponentInfoDTO>();
    PromoteTPLogic logic = new PromoteTPLogic();
    private BeanItemContainer<ComponentInfoDTO> compItemExcelResultBean = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private ExtCustomTable compItemExportPeriodViewTable;
    private CustomTreeContainer<ComponentInfoDTO> excelResultBean = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private ExtCustomTable exportPeriodViewTable;
    String excelName = "Component Details";
    public List<ComponentInfoDTO> compInfo = new ArrayList<ComponentInfoDTO>();
    List<HelperDTO> itemStatusList = new ArrayList<HelperDTO>();
    List<IdDescriptionDTO> IFPStatusList = new ArrayList<IdDescriptionDTO>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    PromoteTPLogic tpLogic = new PromoteTPLogic();
    Boolean contractExcelFlag = false;
    Boolean infoExcelFlag = false;
    String ifpModelId = "1";
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
    public List<ComponentInfoDTO> contListafterRemove = new ArrayList<>();

    public NewComponents(SessionDTO session, TreeTable contractDashBoardTable) {
        try {
            this.session = session;
            this.contractDashboardResultsTable = contractDashBoardTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpNewComponents.xml"), this));
            configureFields();

        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
        }
    }
    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            LOGGER.info("Column Check VAlue=" + compItemSearchTable.getColumnCheckBox("checkRecord"));
            if (event.isChecked()) {

                checkClearAll(event.isChecked());
            }
        }
    };

    public void checkClearAll(boolean checkValue) {

        List<ComponentInfoDTO> containerList = compItemSearchResultsContainer.getItemIds();

        for (ComponentInfoDTO dto : containerList) {
            compItemSearchTable.getContainerProperty(dto, "checkRecord").setValue(checkValue);
        }
    }

    protected void configureFields() {
        try {
            startDate.setDateFormat("MM/dd/yyyy");
            endDate.setDateFormat("MM/dd/yyyy");
            ifpStartDate.setDateFormat("MM/dd/yyyy");
            ifpEndDate.setDateFormat("MM/dd/yyyy");
            psStartDate.setDateFormat("MM/dd/yyyy");
            psEndDate.setDateFormat("MM/dd/yyyy");
            massStartDate.setDateFormat(Constants.MM_DD_YYYY);
            massEndDate.setDateFormat(Constants.MM_DD_YYYY);
            massUpdateRadio.setImmediate(true);
            massUpdateRadio.addItem(ENABLE.getConstant());
            massUpdateRadio.addItem(DISABLE.getConstant());
            massUpdateRadio.select(DISABLE.getConstant());
            massUpdateRadio.setEnabled(true);
            massValue.addItem(SELECT_ONE.getConstant());
            massValue.setNullSelectionAllowed(true);
            massValue.setNullSelectionItemId(SELECT_ONE.getConstant());
            massValue.setValue(SELECT_ONE.getConstant());
            componentType.addItem(SELECT_ONE.getConstant());
            componentType.addItem(ITEM_FAMILY_PLAN.getConstant());
            componentType.addItem(PRICE_SCHEDULE.getConstant());
            componentType.addItem(REBATE_SCHEDULE.getConstant());
            componentType.setNullSelectionAllowed(true);
            componentType.setNullSelectionItemId(SELECT_ONE.getConstant());

            status.addItem(SELECT_ONE.getConstant());
            status.setNullSelectionAllowed(true);
            status.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            status.addItems(CommonLogic.getDropDownList("STATUS"));
            status.setNullSelectionItemId(SELECT_ONE.getConstant());

            ifpStatus.addItem(SELECT_ONE.getConstant());
            ifpStatus.setNullSelectionAllowed(true);
            ifpStatus.setNullSelectionItemId(SELECT_ONE.getConstant());
            ifpStatus.addItems(CommonLogic.getDropDownList("STATUS"));

            searchType.setVisible(false);
            rsType.addItem(SELECT_ONE.getConstant());
            rsType.setNullSelectionAllowed(true);

            rsType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rsType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rsType.addItems(CommonLogic.getDropDownList("RS_TYPE"));

            ifpStatus.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            ifpStatus.setNullSelectionAllowed(true);
            ifpStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            ifpStatus.addItems(CommonLogic.getDropDownList("STATUS"));

            fieldDdlb.addItem(SELECT_ONE.getConstant());
            fieldDdlb.addItem(PR_TP_STATUS.getConstant());
            fieldDdlb.addItem(PR_TP_START_DATE.getConstant());
            fieldDdlb.addItem(PR_TP_END_DATE.getConstant());
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
            fieldDdlb.setValue(SELECT_ONE.getConstant());

            searchFieldDdlb.addItem(SELECT_ONE.getConstant());
            searchFieldDdlb.setNullSelectionAllowed(true);
            searchFieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
            excelBtn1.setIcon(excelExportImage);
            excelBtn2.setIcon(excelExportImage);

            compItemSearchTableLayout.addComponent(compItemSearchTable);
            configureCompItemSearchTable();
            HorizontalLayout controls1 = compItemSearchTableLogic.createControls();
            compItemSearchTableLayout.addComponent(controls1);

            contractDashboardResultsTableLayout.addComponent(contractDashboardResultsTable);
            configureContractDashboardResultsTable();
            contractComponentDetailsTableLayout.addComponent(contractComponentDetailsTable);
            configureContractComponentDetailsTable();
            componentDetailsTableLayout.addComponent(componentDetailsTable);
            configureComponentDetailsTable();

            psType.addItem(SELECT_ONE.getConstant());
            psType.setNullSelectionAllowed(true);
            psType.setNullSelectionItemId(SELECT_ONE.getConstant());
            psType.setValue(SELECT_ONE.getConstant());
            psType.addItems(CommonLogic.getDropDownList("PS_TYPE"));

            psStatus.addItem(SELECT_ONE.getConstant());
            psStatus.setNullSelectionAllowed(true);
            psStatus.setNullSelectionItemId(SELECT_ONE.getConstant());
            psStatus.addItems(CommonLogic.getDropDownList("STATUS"));

            ifpType.addItem(SELECT_ONE.getConstant());
            ifpType.setNullSelectionAllowed(true);
            ifpType.setNullSelectionItemId(SELECT_ONE.getConstant());
            ifpType.addItems(CommonLogic.getDropDownList("IFP_TYPE"));

        
            componentInfoRebateLayout.setVisible(true);
            componentInfoIfpLayout.setVisible(false);
            componentInfoPsLayout.setVisible(false);
            cfpDetailsGrid.setVisible(true);
            ifpDetailsGrid.setVisible(false);
            psDetailsGrid.setVisible(false);
            rsDetailsGrid.setVisible(false);
            cfpDetailsNo.setEnabled(false);
            cfpDetailsName.setEnabled(false);

            paymentMethod.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            paymentMethod.setNullSelectionAllowed(true);
            paymentMethod.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            paymentMethod.addItems(CommonLogic.getDropDownList("PAYMENT_METHOD"));

            rsProgramType.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rsProgramType.setNullSelectionAllowed(true);
            rsProgramType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rsProgramType.addItems(CommonLogic.getDropDownList("REBATE_PROGRAM_TYPE"));

            paymentFrequency.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            paymentFrequency.setNullSelectionAllowed(true);
            paymentFrequency.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            paymentFrequency.addItems(CommonLogic.getDropDownList("PAYMENT_FREQUENCY"));

            rebatePlanLevel.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rebatePlanLevel.setNullSelectionAllowed(true);
            rebatePlanLevel.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rebatePlanLevel.addItems(CommonLogic.getDropDownList("REBATE_PLAN_LEVEL"));

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
                            if (searchField.equals(Constants.ITEM_TYPE)) {
                                try {
                                    itemStatusList.clear();
                                    itemStatusList = CommonLogic.getDropDownList("ITEM_TYPE");
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
                        searchValueTextField.setVisible(false);
                    } else {
                        searchType.setVisible(false);
                        searchValueTextField.setValue(StringUtils.EMPTY);
                        searchValueTextField.setVisible(true);
                    }

                }
            });
            
            compItemSearchTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = compItemSearchTable.getItemIds();
                int size = itemList.size();
                System.out.println("size =============================>>> " + size);
                if (size > 0) {
                    contInfoDto.setCheckAll(true);
                    contInfoDto.setCheckRecord(event.isChecked());


                    for (Object obj : itemList) {
                        ComponentInfoDTO dto = (ComponentInfoDTO) obj;
                        dto.setCheckRecord(event.isChecked());
                        compItemSearchTable.getContainerProperty(obj, Constants.CHECK_RECORD).setValue(event.isChecked());
                    }
                }
            }
        });
        for (Object propertyId : compItemSearchTable.getVisibleColumns()) {
            compItemSearchTable.setColumnWidth(propertyId, 125);
        }

            componentType.addValueChangeListener(new Property.ValueChangeListener() {

                @Override

                public void valueChange(Property.ValueChangeEvent event) {
                    String compType = String.valueOf(componentType.getValue());
                    if (compType.equals(ITEM_FAMILY_PLAN.getConstant())) {
                        componentInfoRebateLayout.setVisible(false);
                        componentInfoIfpLayout.setVisible(true);
                        componentInfoPsLayout.setVisible(false);
                        searchFieldDdlb.removeAllItems();
                        searchFieldDdlb.addItem(SELECT_ONE.getConstant());
                        searchFieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
                        searchFieldDdlb.addItem(Constants.ITEM_ID);
                        searchFieldDdlb.addItem(Constants.ITEM_NAME);
                        searchFieldDdlb.addItem(Constants.ITEM_STATUS);
                        searchFieldDdlb.addItem(Constants.ITEM_TYPE);
                        compItemSearchTable.removeAllItems();
                        compItemSearchTable.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_COLUMNS_IFP);
                        compItemSearchTable.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_HEADERS_IFP);
                        componentDetailsTable.removeAllItems();
                        componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS_IFP);
                        componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS_IFP);

                    } else if (compType.equals(PRICE_SCHEDULE.getConstant())) {
                        componentInfoRebateLayout.setVisible(false);
                        componentInfoIfpLayout.setVisible(false);
                        componentInfoPsLayout.setVisible(true);
                        searchFieldDdlb.removeAllItems();
                        compItemSearchTable.removeAllItems();
                        searchFieldDdlb.addItem(SELECT_ONE.getConstant());
                        searchFieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
                        searchFieldDdlb.addItem(Constants.ITEM_ID);
                        searchFieldDdlb.addItem(Constants.ITEM_NAME);
                        searchFieldDdlb.addItem(Constants.ITEM_STATUS);
                        searchFieldDdlb.addItem(Constants.ITEM_TYPE);
                        compItemSearchTable.setVisibleColumns(Constants.COMPONENT_ITEM_SEARCH_COLUMNS_PS);
                        compItemSearchTable.setColumnHeaders(Constants.COMPONENT_ITEM_SEARCH_HEADERS_PS);
                        componentDetailsTable.removeAllItems();
                        componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS_PS);
                        componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS_PS);
                    } else //if (compType.equals(REBATE_SCHEDULE.getConstant())) {
                    {
                        componentInfoRebateLayout.setVisible(true);
                        componentInfoIfpLayout.setVisible(false);
                        componentInfoPsLayout.setVisible(false);
                        searchFieldDdlb.removeAllItems();
                        searchFieldDdlb.addItem(SELECT_ONE.getConstant());
                        searchFieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
                        searchFieldDdlb.addItem(Constants.ITEM_ID);
                        searchFieldDdlb.addItem(Constants.ITEM_NAME);
                        searchFieldDdlb.addItem(Constants.ITEM_STATUS);
                        searchFieldDdlb.addItem(Constants.ITEM_TYPE);
                        compItemSearchTable.removeAllItems();
                        compItemSearchTable.setVisibleColumns(Constants.COMPONENT_ITEM_SEARCH_COLUMNS_RS);
                        compItemSearchTable.setColumnHeaders(Constants.COMPONENT_ITEM_SEARCH_HEADERS_RS);
                        componentDetailsTable.removeAllItems();
                        componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS);
                        componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS);
                    }

                }
            });

            fieldDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    String searchField = String.valueOf(fieldDdlb.getValue());
                    if (searchField.equals("Start Date")) {
                        massStartDate.setVisible(true);
                        massEndDate.setVisible(false);
                        massValue.setVisible(false);
                    } else if (searchField.equals("End Date")) {
                        massStartDate.setVisible(false);
                        massEndDate.setVisible(true);
                        massValue.setVisible(false);
                    } else if (searchField.equals("Status")) {
                        try {
                            itemStatusList.clear();
                            massValue.setVisible(true);
                            massStartDate.setVisible(false);
                            massEndDate.setVisible(false);
                            itemStatusList = CommonLogic.getDropDownList(STATUS.getConstant());
                            massValue = CommonLogic.getNativeSelect(massValue, itemStatusList);
                        } catch (SystemException ex) {
                            java.util.logging.Logger.getLogger(NewComponents.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(NewComponents.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });

        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
        }
    }

    public void configureCompItemSearchTable() {
        LOGGER.info("Entering configureCompItemSearchTable");
        compItemSearchTableLogic.setContainerDataSource(compItemSearchResultsContainer);
        compItemSearchTableLogic.setPageLength(8);
        compItemSearchTableLogic.sinkItemPerPageWithPageLength(false);

        compItemSearchTable.setVisibleColumns(Constants.COMP_ITEM_RESULTS_COLUMNS);
        compItemSearchTable.setColumnHeaders(Constants.COMP_ITEM_RESULTS_HEADERS);
        compItemSearchTable.setSizeFull();
        compItemSearchTable.setEditable(Boolean.TRUE);
        compItemSearchTable.markAsDirty();
        compItemSearchTable.setSelectable(true);
        compItemSearchTable.setWidth("890px");
        compItemSearchTable.setHeight("230px");
        compItemSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        compItemSearchTable.setColumnCheckBox("checkRecord", true, false);

        compItemSearchTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                             compItemSearchTable.getContainerProperty(itemId, "checkRecord").setValue(check.getValue());
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        LOGGER.info("Exiting configureCompItemSearchTable");

                }
    
    public void configureContractDashboardResultsTable() {
        LOGGER.info("Entering configureContractDashboardResultsTable");
        contractDashboardResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractDashboardResultsTable.setWidth("630px");
        contractDashboardResultsTable.setHeight("350px");
        contractDashboardResultsTable.setPageLength(5);
        contractDashboardResultsTable.setSelectable(true);
        contractDashboardResultsTable.setContainerDataSource(dashBoardTreeContainer);
        contractDashboardResultsTable.setVisibleColumns(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_COLUMNS_TRANSFER);
        contractDashboardResultsTable.setColumnHeaders(Constants.PROMOTE_TP_CONTRACT_DASHBOARD_TREE_HEADERS);
        LOGGER.info("Exiting configureContractDashboardResultsTable");
    }

    public void configureContractComponentDetailsTable() {
        LOGGER.info("Entering configureContractComponentDetailsTable");
        contractComponentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractComponentDetailsTable.setWidth("630px");
        contractComponentDetailsTable.setHeight("470px");
        contractComponentDetailsTable.setPageLength(5);
        contractComponentDetailsTable.setContainerDataSource(componentResultsContainer);
        contractComponentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_DETAILS_COLUMNS);
        contractComponentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_DETAILS_HEADERS);
        LOGGER.info("Entering configureContractComponentDetailsTable");
    }

    public void configureComponentDetailsTable() {
        LOGGER.info("Entering configureComponentDetailsTable");
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsTable.setWidth("890px");
        componentDetailsTable.setHeight("230px");
        componentDetailsTable.setPageLength(5);
        componentDetailsTable.setContainerDataSource(componentDetailResultsContainer);
        componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS);
        componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS);
        componentDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        componentDetailsTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                System.out.println("PROPERTY ID----------->>>"+propertyId);
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selecteditemList.add((ComponentInfoDTO) itemId);
                            } else if (selecteditemList.contains(itemId)) {
                                selecteditemList.remove(itemId);
                                }
                            }
                    });
                    return check;
                }
                componentDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                    public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                        Collection itemList = componentDetailsTable.getItemIds();
                        for (Object obj : itemList) {
                            ComponentInfoDTO dto = (ComponentInfoDTO) obj;
                            dto.setCheckRecord(event.isChecked());
                        }
                    }
                });
                if (propertyId.equals("statusId")) {
                    try {
                        final ComboBox itemStatus = new ComboBox();
                        itemStatus.addItems(CommonLogic.getDropDownList("STATUS"));
                        itemStatus.setWidth("150px");
                        return itemStatus;
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    }
                }
                if (propertyId.equals("itemStartDate")) {
                    final PopupDateField itemSDate = new PopupDateField();
                    itemSDate.setDateFormat(Constants.MM_DD_YYYY);
                    itemSDate.setWidth("150px");
                    itemSDate.setImmediate(true);
                    return itemSDate;
                }
                if (propertyId.equals("itemEndDate")) {
                    final PopupDateField itemEDate = new PopupDateField();
                    itemEDate.setDateFormat(Constants.MM_DD_YYYY);
                    itemEDate.setWidth("150px");
                    itemEDate.setImmediate(true);
                    return itemEDate;
                }
                if (propertyId.equals("rebatePlan")) {
                    final CustomTextField rebatePlan = new CustomTextField();
                    rebatePlan.addStyleName("searchicon");
                    rebatePlan.setWidth("150px");
                    rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            final RebatePlanLookup rebatePlanLookupWindow = new RebatePlanLookup("Rebate Plan", "Rebate Plan Lookup", rebatePlan, StringUtils.EMPTY);
                            rebatePlanLookupWindow.setWidth("1320px");
                            rebatePlanLookupWindow.setHeight("830px");
                            UI.getCurrent().addWindow(rebatePlanLookupWindow);
                        }
                    });
                    rebatePlan.setImmediate(true);
                    return rebatePlan;
                }
                if (propertyId.equals("formulaId")) {
                    final CustomTextField formulaId = new CustomTextField();
                    formulaId.addStyleName("searchicon");
                    formulaId.setWidth("150px");
                    formulaId.addClickListener(new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            final FormulaLookUp formulaLookUpWindow = new FormulaLookUp(formulaId);
                            formulaLookUpWindow.setWidth("1020px");
                            formulaLookUpWindow.setHeight("830px");
                            UI.getCurrent().addWindow(formulaLookUpWindow);
                        }
                    });
                    formulaId.setImmediate(true);
                    return formulaId;
                }
                return null;
            }
        });
        LOGGER.info("Exiting configureComponentDetailsTable");
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.info(" massUpdate ValueChangeEvent initiated ");
        massStartDate.setVisible(false);
        massEndDate.setVisible(false);
        massValue.setEnabled(false);
        resetMassUpdate();
        if ("Disable".equals(massUpdateRadio.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.info("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Enable and Disable Logic
     *
     * @param value
     */
    public void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        massPopulateBtn.setEnabled(value);
        massValue.setEnabled(value);
        massStartDate.setEnabled(value);
        massEndDate.setEnabled(value);
    }

    private void resetMassUpdate() {
        fieldDdlb.setValue(SELECT_ONE.getConstant());
        massValue.setValue(SELECT_ONE.getConstant());
        massStartDate.setValue(null);
        massEndDate.setValue(null);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //empty
    }

    @UiHandler("excelBtn1")
    public void compItemDetailsExport(Button.ClickEvent event) {
        try {

            contractExcelFlag = true;
            final int recordCount = logic.getComponentItemSearchCount(contInfoDto);
            if (recordCount > 0) {
                createWorkSheet("ComponentDetails", compItemSearchTable, recordCount);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        } finally {
            contractExcelFlag = false;
        }
    }

    @UiHandler("excelBtn2")
    public void componentInfoExport(Button.ClickEvent event) {

        try {
            infoExcelFlag = true;
            final int recordCount = compInfo.size();
            if (recordCount > 0) {
                createWorkSheet("ComponentDetails-SelectedItems", componentDetailsTable, recordCount);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        } finally {
            infoExcelFlag = false;
        }

    }

    /**
     * Tree Node Removal from Contract Dashboard Results
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
                componentResultsContainer.removeAllItems();
                contractComponentDetailsTable.setRefresh(true);
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Remove Children");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node to Remove at Dashboard");
        }
    }

    /**
     * Search Button Click Logic
     *
     * @param event
     */
    @UiHandler("searchBtn1")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered search method");
        compItemSearchResultsContainer.removeAllItems();
        contInfoDto.setSearchField(String.valueOf(searchFieldDdlb.getValue()));
        String searchField = String.valueOf(searchFieldDdlb.getValue());
        if (searchField.contains("Status") || searchField.contains("Type")) {
            contInfoDto.setSearchFieldValue(String.valueOf(searchType.getValue()));
        } else {
            contInfoDto.setSearchFieldValue(searchValueTextField.getValue());
        }
        contInfoDto.setComponentValue(String.valueOf(componentType.getValue()));
        if (StringUtils.EMPTY.equals(contInfoDto.getSearchFieldValue())) {
            AbstractNotificationUtils.getErrorNotification("Error", "Please enter a Search Value.");
            return;
        }
        if (!compItemSearchTableLogic.loadItemData(contInfoDto)) {
            AbstractNotificationUtils.getErrorNotification("No Records",
                    "There were no records matching the search criteria.  Please try again.");
        }

        LOGGER.info("Ending search method");
    }

    /**
     * Add Item Method Click Logic
     *
     * @param event
     */
    @UiHandler("addItemBtn")
    public void addItemBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered Add Item method");
        componentDetailResultsContainer.removeAllItems();
        Collection<?> returnList = compItemSearchTable.getItemIds();
        Set setA = new HashSet();
        Set ifpModelIDs = new HashSet();
        Boolean flag = false;
        String ids = Constants.EMPTY;
        String ifps = Constants.EMPTY;

        String id = StringUtils.EMPTY;
        String ifpID = StringUtils.EMPTY;
        List<ComponentInfoDTO> list = new ArrayList<ComponentInfoDTO>();
        for (Object item : returnList) {
            Boolean checked = (Boolean) compItemSearchResultsContainer.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
            if (checked) {
                flag = true;
                if (componentType.getValue().toString().equalsIgnoreCase(REBATE_SCHEDULE.toString())) {
                    ifpID = String.valueOf(compItemSearchResultsContainer.getContainerProperty(item, "modelId").getValue());
                    id = String.valueOf(compItemSearchResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                } else {
                    id = String.valueOf(compItemSearchResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                }
                setA.add(id);
                ifpModelIDs.add(ifpID);
            }
        }
        if (!flag) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please check a record to Add.");
        }
        if (!setA.isEmpty()) {
            ids = getIdString(setA);
        }
        if (!ifpModelIDs.isEmpty()) {
            ifps = getIdString(ifpModelIDs);
        }
        if (componentType.getValue().toString().equalsIgnoreCase(REBATE_SCHEDULE.toString())) {
            try {
                Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
                String query = queryUtils.getItemInfo(ids,ifps);
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (itemList != null && itemList.size() > 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        ComponentInfoDTO itemInfoDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) itemList.get(i);
                        itemInfoDTO.setItemMasterId(String.valueOf(obje[1]));
                        itemInfoDTO.setItemNo(String.valueOf(obje[2]));
                        itemInfoDTO.setItemName(String.valueOf(obje[3]));
                        if ((obje[4] != null) && !obje[4].equals(Constants.SELECT_ONE) && !obje[4].equals(Constants.NULL)) {
                            itemInfoDTO.setTherapyClass(String.valueOf(obje[4]));
                        } else {
                            itemInfoDTO.setTherapyClass(Constants.EMPTY);
                        }
                        itemInfoDTO.setBrand((obje[5] != null) ? String.valueOf(obje[5]) : Constants.EMPTY);
                        if (obje[6] != null) {
                            itemInfoDTO.setStatusId(idHelperDTOMap.get((int) obje[6]) == null || idHelperDTOMap.get((int) obje[6]).getDescription().trim().equals(Constants.SELECT_ONE) ? new HelperDTO(0,"-Select One-") : idHelperDTOMap.get((int) obje[6]));
                        }
                        if (obje[7] != null) {
                            String date = df.format(obje[7]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemStartDate(date2);
                        } else {
                            itemInfoDTO.setItemStartDate(null);
                        }
                        if (obje[8] != null) {
                            String date = df.format(obje[8]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemEndDate(date2);
                        } else {
                            itemInfoDTO.setItemEndDate(null);
                        }
                        list.add(itemInfoDTO);
                    }
                    componentDetailResultsContainer.addAll(list);
                    compInfo.addAll(list); 
                }
                componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS);
                componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS);
                componentDetailsTable.setEditable(true);
                componentDetailsTable.setSelectable(true);

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if (componentType.getValue().toString().equalsIgnoreCase(ITEM_FAMILY_PLAN.toString())) {
            try {
                String query = queryUtils.getIFPInformation(ids);
                Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (itemList != null && itemList.size() > 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        ComponentInfoDTO itemInfoDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) itemList.get(i);
                        itemInfoDTO.setItemMasterId(String.valueOf(obje[0]));
                        itemInfoDTO.setItemId(String.valueOf(obje[1]));
                        itemInfoDTO.setItemNo(String.valueOf(obje[3]));
                        itemInfoDTO.setItemName(String.valueOf(obje[2]));
                        if ((obje[4] != null) && !obje[4].equals(Constants.SELECT_ONE) && !obje[4].equals(Constants.NULL)) {
                            itemInfoDTO.setTherapyClass(String.valueOf(obje[4]));
                        } else {
                            itemInfoDTO.setTherapyClass(Constants.EMPTY);
                        }
                        itemInfoDTO.setBrand((obje[5] != null) ? String.valueOf(obje[5]) : Constants.EMPTY);
                        itemInfoDTO.setForm((obje[6] != null) ? String.valueOf(obje[6]) : Constants.EMPTY);
                        itemInfoDTO.setStrength((obje[7] != null) ? String.valueOf(obje[7]) : Constants.EMPTY);
                        if (obje[8] != null) {
                            itemInfoDTO.setStatusId(idHelperDTOMap.get((int) obje[8]) == null || idHelperDTOMap.get((int) obje[8]).getDescription().trim().equals(Constants.SELECT_ONE) ? new HelperDTO(0,"-Select One-") : idHelperDTOMap.get((int) obje[8]));
                        }
                        if (obje[9] != null) {
                            String date = df.format(obje[9]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemStartDate(date2);
                        } else {
                            itemInfoDTO.setItemStartDate(null);
                        }
                        if (obje[10] != null) {
                            String date = df.format(obje[10]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemEndDate(date2);
                        } else {
                            itemInfoDTO.setItemEndDate(null);
                        }
                        list.add(itemInfoDTO);
                    }
                    componentDetailResultsContainer.addAll(list);
                    compInfo.addAll(list); 
                }
                componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS_IFP);
                componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS_IFP);
                componentDetailsTable.setEditable(true);
                componentDetailsTable.setSelectable(true);

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if (componentType.getValue().toString().equalsIgnoreCase(PRICE_SCHEDULE.toString())) {
            try {
                String query = queryUtils.getIFPInformation(ids);
                List itemList = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (itemList != null && itemList.size() > 0) {
                    Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
                    for (int i = 0; i < itemList.size(); i++) {
                        ComponentInfoDTO itemInfoDTO = new ComponentInfoDTO();
                        Object[] obje = (Object[]) itemList.get(i);
                        itemInfoDTO.setItemMasterId(String.valueOf(obje[0]));
                        itemInfoDTO.setItemId(String.valueOf(obje[1]));
                        itemInfoDTO.setItemNo(String.valueOf(obje[3]));
                        itemInfoDTO.setItemName(String.valueOf(obje[2]));
                        if ((obje[4] != null) && !obje[4].equals(Constants.SELECT_ONE) && !obje[4].equals(Constants.NULL)) {
                            itemInfoDTO.setTherapyClass(String.valueOf(obje[4]));
                        } else {
                            itemInfoDTO.setTherapyClass(Constants.EMPTY);
                        }
                        itemInfoDTO.setBrand((obje[5] != null) ? String.valueOf(obje[5]) : Constants.EMPTY);
                        itemInfoDTO.setForm((obje[6] != null) ? String.valueOf(obje[6]) : Constants.EMPTY);
                        itemInfoDTO.setStrength((obje[7] != null) ? String.valueOf(obje[7]) : Constants.EMPTY);
                        HelperDTO dto = new HelperDTO();                      
                        if (obje[8] != null) {
                            itemInfoDTO.setStatusId(idHelperDTOMap.get((int) obje[8]) == null || idHelperDTOMap.get((int) obje[8]).getDescription().trim().equals(Constants.SELECT_ONE) ? new HelperDTO(0,"-Select One-") : idHelperDTOMap.get((int) obje[8]));
                        }
                        if (obje[9] != null) {
                            String date = df.format(obje[9]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemStartDate(date2);
                        } else {
                            itemInfoDTO.setItemStartDate(null);
                        }
                        if (obje[10] != null) {
                            String date = df.format(obje[10]);
                            Date date2 = df.parse(date);
                            itemInfoDTO.setItemEndDate(date2);
                        } else {
                            itemInfoDTO.setItemEndDate(null);
                        }
                        list.add(itemInfoDTO);
                    }
                    componentDetailResultsContainer.addAll(list);
                    compInfo.addAll(list); 
                }
                componentDetailsTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS_PS);
                componentDetailsTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS_PS);
                componentDetailsTable.setEditable(true);
                componentDetailsTable.setSelectable(true);

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }

    /**
     * Getting the Id Value
     *
     * @param set
     * @return
     */
    private String getIdString(Set set) {
        String ids = Constants.EMPTY;
        Boolean flag = false;
        for (Object id : set) {
            String idvalue = String.valueOf(id);
            if (!flag) {
                ids = idvalue;
                flag = true;
            } else {
                ids = ids + "," + idvalue;
            }
        }
        return ids;
    }

    /**
     * Remove from Tree Button Logic
     *
     * @param event
     */
    @UiHandler("removeFromTreeBtn")
    public void removeFromTreeBtnClick(Button.ClickEvent event) {
        if (componentDetailResultsContainer == null || componentDetailResultsContainer.size() == 0) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select a record to Remove.");
        } else {
            boolean isChecked = false;
            for (ComponentInfoDTO dto : componentDetailResultsContainer.getItemIds()) {
                if (dto.getCheckRecord()) {
                    isChecked = true;
                    break;
                }
            }
            if (isChecked) {
              
                List<ComponentInfoDTO> addList = new ArrayList<>();
                for (ComponentInfoDTO component : componentDetailResultsContainer.getItemIds()) {
                    if (!component.getCheckRecord()) {
                        addList.add(component);
                    }
                }
                componentDetailResultsContainer.removeAllItems();
                componentDetailResultsContainer.addAll(addList);
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select a record to Remove.");
            }
        }
    }

    /**
     * Add to tree button click logic
     *
     * @param event
     */
    @UiHandler("addToTreeBtn1")
    public void addToTreeBtnLogic(Button.ClickEvent event) throws SystemException {
        String component = String.valueOf(componentType.getValue());

        /*used to check whether all component selection fields are entered or not */
        if (component.equals(Constants.ITEM_FAMILY_PLAN)) {

            /*used to check whether Entered IFP ID is already exist in the system or not */
            if (!ifpId.getValue().equals(StringUtils.EMPTY) && !ifpNo.getValue().equals(StringUtils.EMPTY) && !ifpName.getValue().equals(StringUtils.EMPTY) && ifpStartDate.getValue() != null && ifpEndDate.getValue() != null && ifpType.getValue() != null && ifpStatus.getValue() != null) {
                Boolean ifpIdFlag = tpLogic.duplicateCheck("IFP_ID", String.valueOf(ifpId.getValue()), "ifp");
                if (ifpIdFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered IFP ID " + String.valueOf(ifpId.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered IFP NAME is already exist in the system or not */
                Boolean ifpNameFlag = tpLogic.duplicateCheck("IFP_NAME", String.valueOf(ifpName.getValue()), "ifp");
                if (ifpNameFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered IFP Name " + String.valueOf(ifpName.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered IFP NO is already exist in the system or not */
                Boolean ifpNoFlag = tpLogic.duplicateCheck("IFP_NO", String.valueOf(ifpNo.getValue()), "ifp");
                if (ifpNoFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered IFP No " + String.valueOf(ifpNo.getValue()) + " Already exist in the system.");
                    return;
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter all the values in Component Selection Section");
                return;
            }
        }
        /*used to check whether all component selection fields are entered or not */
        if (component.equals(Constants.PRICE_SCHEDULE)) {

            /*used to check whether Entered PS ID is already exist in the system or not */
            if (!psId.getValue().equals(StringUtils.EMPTY) && !psNo.getValue().equals(StringUtils.EMPTY) && !psName.getValue().equals(StringUtils.EMPTY) && psStartDate.getValue() != null && psEndDate.getValue() != null && psType.getValue() != null && psStatus.getValue() != null) {
                Boolean psIdFlag = tpLogic.duplicateCheck("PS_ID", String.valueOf(psId.getValue()), "ps");
                if (psIdFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered PS ID " + String.valueOf(psId.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered PS NAME is already exist in the system or not */
                Boolean psNameFlag = tpLogic.duplicateCheck("PS_NAME", String.valueOf(psName.getValue()), "ps");
                if (psNameFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered PS Name " + String.valueOf(psName.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered PS No is already exist in the system or not */
                Boolean psNoFlag = tpLogic.duplicateCheck("PS_NO", String.valueOf(psNo.getValue()), "ps");
                if (psNoFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered PS No " + String.valueOf(psNo.getValue()) + " Already exist in the system.");
                    return;
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter all the values in Component Selection Section");
                return;
            }
        }
        /*used to check whether all component selection fields are entered or not */
        if (component.equals(Constants.REBATE_SCHEDULE)) {
            if (!rebateScheduleId.getValue().equals(StringUtils.EMPTY) && !rsNumber.getValue().equals(StringUtils.EMPTY) && !rsName.getValue().equals(StringUtils.EMPTY) && startDate.getValue() != null && endDate.getValue() != null && rsType.getValue() != null && status.getValue() != null && rebatePlanLevel.getValue() != null && rsProgramType.getValue() != null && paymentFrequency.getValue() != null && paymentMethod.getValue() != null && rsType.getValue() != null) {
                /*used to check whether Entered RS ID is already exist in the system or not */
                Boolean psIdFlag = tpLogic.duplicateCheck("RS_ID", String.valueOf(rebateScheduleId.getValue()), "rs");
                if (psIdFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered RS ID " + String.valueOf(rebateScheduleId.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered RS Name is already exist in the system or not */
                Boolean psNameFlag = tpLogic.duplicateCheck("RS_NAME", String.valueOf(rsName.getValue()), "rs");
                if (psNameFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered RS Name " + String.valueOf(rsName.getValue()) + " Already exist in the system.");
                    return;
                }
                /*used to check whether Entered RS NO is already exist in the system or not */
                Boolean psNoFlag = tpLogic.duplicateCheck("RS_NO", String.valueOf(rsNumber.getValue()), "rs");
                if (psNoFlag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered RS No " + String.valueOf(rsNumber.getValue()) + " Already exist in the system.");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Enter all the values in Component Selection Section");
                return;
            }
        }
        Boolean flag = false;
        /* This loop is used to check atleast one record is selected at component Details-selected Item table or not */
        List<ComponentInfoDTO> list = componentDetailResultsContainer.getItemIds();
        for (int i = 0; i < list.size(); i++) {
            ComponentInfoDTO dto = (ComponentInfoDTO) list.get(i);
            if (dto.getCheckRecord()) {
                flag = true;
            }
        }

        if (!flag) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.TP_NO_ROW_SELECTED.getConstant(), Constants.MessageConstants.EMPTY_SPACE.getConstant() + "Please check mark at least one component to add to the Contract.");
        } else {
            addToContDashboardTree();
        }
    }

    /**
     * Populate Button Click Logic
     *
     * @param event
     */
    @UiHandler("populateBtn2")
    public void populateContractInfoLogic(Button.ClickEvent event) {
        Object root = contractDashboardResultsTable.getValue();
        if (root == null) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.TP_NO_ROW_SELECTED.getConstant(), Constants.MessageConstants.EMPTY_SPACE.getConstant() + "Please highlight a row to populate.");
        } else {
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
                String ifpId = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.MODEL_ID).getValue());
                String componentQuery = Constants.EMPTY;
                if (level.equals(Constants.TWO)) {
                    componentResultsContainer.removeAllItems();
                    componentResultsContainer.addAll((List)contractDashboardResultsTable.getContainerProperty(root, "ifpList").getValue());
                } else if (level.equals(Constants.THREE)) {
                    componentResultsContainer.removeAllItems();
                    componentResultsContainer.addAll((List)contractDashboardResultsTable.getContainerProperty(root, "psList").getValue());
                } else if (level.equals(Constants.FOUR)) {
                    componentResultsContainer.removeAllItems();
                    componentResultsContainer.addAll((List)contractDashboardResultsTable.getContainerProperty(root, "rsList").getValue());
                }
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
    }

    public void addToContDashboardTree() throws SystemException {
        if (contractDashboardResultsTable.getItemIds().isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Add Contract Header Node");
        } else {
            final Object root = contractDashboardResultsTable.getValue();
            if (root != null) {
                String levelNo = String.valueOf(contractDashboardResultsTable.getContainerProperty(root, Constants.LEVELNO).getValue());
                int levelNumber = Integer.valueOf(levelNo);
                String level = String.valueOf(componentType.getValue());
                if (Constants.NULL.equals(level)) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Component");
                }
                Collection<?> checkList = componentDetailsTable.getItemIds();
                Boolean flag = false;
                for (Object tmp : checkList) {
                    Boolean checked = (Boolean) componentDetailResultsContainer.getContainerProperty(tmp, Constants.CHECK_RECORD).getValue();
                    if (checked) {
                        flag = true;
                    }
                }
                if (!flag) {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Atleast one record at Contract Component Results section");
                }

                if (level.equals(Constants.ITEM_FAMILY_PLAN)) {
                    try{
                    if (2 - levelNumber == 1) {
                        List itemLst = new ArrayList();
                        /*used to save ifp details at Temp table */
                        GcmContractDetails gcmContractDetails;
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.createGcmContractDetails(0);
                        gcmContractDetails.setComponentName(String.valueOf(ifpName.getValue()));
                        gcmContractDetails.setComponentId(String.valueOf(ifpId.getValue()));
                        gcmContractDetails.setComponentNo(String.valueOf(ifpNo.getValue()));
                        gcmContractDetails.setStartDate(ifpStartDate.getValue());
                        HelperDTO statusDto = (HelperDTO) ifpStatus.getValue();
                        gcmContractDetails.setComponentStatus(String.valueOf(statusDto.getId()));
                        HelperDTO dto = (HelperDTO) ifpType.getValue();
                        gcmContractDetails.setComponentType(String.valueOf(dto.getId()));
                        gcmContractDetails.setEndDate(ifpEndDate.getValue());
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.addGcmContractDetails(gcmContractDetails);
                        Collection<ComponentInfoDTO> returnList = componentDetailResultsContainer.getItemIds();
                        for (ComponentInfoDTO compDto : returnList) {
                            Boolean checked = compDto.getCheckRecord();
                            if (checked) {
                                GcmGlobalDetails gcmGlobalDetails;
                                gcmGlobalDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                gcmGlobalDetails.setIfpModelSid(gcmContractDetails.getGcmContractDetailsSid());
                                String itemMasterId = compDto.getItemMasterId();
                                gcmGlobalDetails.setItemMasterSid(Integer.valueOf(itemMasterId));
                                gcmGlobalDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(gcmGlobalDetails);
                                itemLst.add(compDto);
                            }
                        }
                        String ifpID = String.valueOf(ifpId.getValue());
                        String ifpNum = String.valueOf(ifpNo.getValue());
                        String ifpNam = String.valueOf(ifpName.getValue());

                        /*Used to add at Tree*/
                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.IFP);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(ifpID));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(ifpNum));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(ifpNam));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.TWO);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(String.valueOf(gcmContractDetails.getGcmContractDetailsSid()));
                        contractDashboardResultsTable.getContainerProperty(rootId, "ifpList").setValue(itemLst);
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTable.setCollapsed(root, false);

                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                    } catch(Exception ex){
                        LOGGER.error(ex.getMessage());
                    }
                } else if (level.equals(Constants.PRICE_SCHEDULE)) {
                    if (3 - levelNumber == 1) {
                        /* Used to save ps details at Temp Table*/
                        List psList = new ArrayList();
                        GcmContractDetails gcmContractDetails;
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.createGcmContractDetails(0);
                        gcmContractDetails.setComponentName(String.valueOf(psName.getValue()));
                        gcmContractDetails.setComponentId(String.valueOf(psId.getValue()));
                        gcmContractDetails.setComponentNo(String.valueOf(psNo.getValue()));
                        gcmContractDetails.setStartDate(psStartDate.getValue());
                        HelperDTO statusDto = (HelperDTO) psStatus.getValue();
                        gcmContractDetails.setComponentStatus(String.valueOf(statusDto.getId()));
                        HelperDTO dto = (HelperDTO) psType.getValue();
                        gcmContractDetails.setComponentType(String.valueOf(dto.getId()));
                        gcmContractDetails.setEndDate(psEndDate.getValue());
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.addGcmContractDetails(gcmContractDetails);

                        Collection <ComponentInfoDTO> returnList = componentDetailResultsContainer.getItemIds();
                        for (ComponentInfoDTO item : returnList) {
                            Boolean checked = item.getCheckRecord();
                            if (checked) {
                                String itemMasterId = String.valueOf(componentDetailResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                                int psSid = gcmContractDetails.getGcmContractDetailsSid();
                                String query = "INSERT INTO dbo.GCM_GLOBAL_DETAILS (ITEM_MASTER_SID,IFP_MODEL_SID,PS_MODEL_SID) VALUES(" + itemMasterId + "," + 1 + "," + psSid + ")";
                                psList.add(item);
                            }
                        }
                        if (checkIfpPsRsDetails(psList,(List)contractDashboardResultsTable.getContainerProperty(root, "ifpList").getValue())) {
                        String psID = String.valueOf(psId.getValue());
                        String psNumber = String.valueOf(psNo.getValue());
                        String psNam = String.valueOf(psName.getValue());
                        /*Used to add at Tree*/
                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.PS);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(psID));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(psNumber));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(psNam));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.THREE);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(StringUtils.EMPTY);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(String.valueOf(gcmContractDetails.getGcmContractDetailsSid()));
                            contractDashboardResultsTable.getContainerProperty(rootId, "psList").setValue(psList);
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, true);
                        contractDashboardResultsTable.setCollapsed(root, false);
                    } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP");
                        }
                        
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                } else if (level.equals(Constants.REBATE_SCHEDULE)) {
                    try{
                    if (4 - levelNumber == 1) {
                        List rsList = new ArrayList();
                        /* Used to save at Temp Table*/
                        GcmContractDetails gcmContractDetails;
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.createGcmContractDetails(0);
                        gcmContractDetails.setComponentName(String.valueOf(rsName.getValue()));
                        gcmContractDetails.setComponentId(String.valueOf(rebateScheduleId.getValue()));
                        gcmContractDetails.setComponentNo(String.valueOf(rsNumber.getValue()));
                        gcmContractDetails.setStartDate(startDate.getValue());
                        HelperDTO statusDto = (HelperDTO) status.getValue();
                        gcmContractDetails.setComponentStatus(String.valueOf(statusDto.getId()));
                        HelperDTO dto = (HelperDTO) rsType.getValue();
                        gcmContractDetails.setComponentType(String.valueOf(dto.getId()));
                        gcmContractDetails.setEndDate(endDate.getValue());
                        HelperDTO programTypeDTO = (HelperDTO) rsProgramType.getValue();
                        gcmContractDetails.setProgramType(String.valueOf(programTypeDTO.getId()));
                        HelperDTO rpLevelDTO = (HelperDTO) rebatePlanLevel.getValue();
                        gcmContractDetails.setPlanLevel(String.valueOf(rpLevelDTO.getId()));
                        HelperDTO paymentFreqDTO = (HelperDTO) paymentFrequency.getValue();
                        gcmContractDetails.setPaymentFrequency(String.valueOf(paymentFreqDTO.getId()));
                        HelperDTO paymentMethodDTO = (HelperDTO) paymentMethod.getValue();
                        gcmContractDetails.setPaymentMethod(String.valueOf(paymentMethodDTO.getId()));
                        gcmContractDetails = GcmContractDetailsLocalServiceUtil.addGcmContractDetails(gcmContractDetails);
                        Collection<ComponentInfoDTO> returnList = componentDetailResultsContainer.getItemIds();
                        Boolean checkFlag = false;
                        for (ComponentInfoDTO item : returnList) {
                            Boolean checked = item.getCheckRecord();
                            if (checked) {
                                GcmGlobalDetails gcmGlobalDetails;
                                gcmGlobalDetails = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
                                String itemMasterId = String.valueOf(componentDetailResultsContainer.getContainerProperty(item, "itemMasterId").getValue());
                                gcmGlobalDetails.setRsModelSid(gcmContractDetails.getGcmContractDetailsSid());
                                gcmGlobalDetails.setItemMasterSid(Integer.valueOf(itemMasterId));
                                gcmGlobalDetails = GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(gcmGlobalDetails);
                                checkFlag = true;
                                rsList.add(item);
                            }
                        }
                        if(checkIfpPsRsDetails(rsList,(List)contractDashboardResultsTable.getContainerProperty(root, "psList").getValue())){
                        String rebateID = String.valueOf(rebateScheduleId.getValue());
                        String rsNum = String.valueOf(rsNumber.getValue());
                        String rsNam = String.valueOf(rsName.getValue());

                        final Object rootId = contractDashboardResultsTable.addItem();
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.CATEGORY).setValue(Constants.RS);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_ID).setValue(String.valueOf(rebateID));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NUMBER).setValue(String.valueOf(rsNum));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.DASHBOARD_NAME).setValue(String.valueOf(rsNam));
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.LEVELNO).setValue(Constants.FOUR);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.HIDDEN_ID).setValue(StringUtils.EMPTY);
                        contractDashboardResultsTable.getContainerProperty(rootId, Constants.MODEL_ID).setValue(String.valueOf(gcmContractDetails.getGcmContractDetailsSid()));
                            contractDashboardResultsTable.getContainerProperty(rootId, "rsList").setValue(rsList);
                        contractDashboardResultsTable.addItem(rootId);
                        contractDashboardResultsTable.setParent(rootId, root);
                        contractDashboardResultsTable.setChildrenAllowed(rootId, false);
                        contractDashboardResultsTable.setCollapsed(root, false);
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select the same items that are in IFP");
                        }
                           
                           
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Correct Node");
                    }
                    }catch(Exception ex){
                        LOGGER.error(ex.getMessage());
                }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select Node at Dashboard");
            }

        }
    }

    public List<Integer> saveNewContract() throws SystemException, PortalException, ParseException {
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

                    /* Used to save At Contract Master table */
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

                    /* Used to save At Contract Alias Master table */
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
                    String cfpEndDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_END_DATE).getValue());
                    String cfpStartDate = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_START_DATE).getValue());
                    Date sdate = simpleDateFormat.parse(cfpStartDate);
                    Date edate = simpleDateFormat.parse(cfpEndDate);

                    /* Used to save at cfp_model table */
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

                    /* Used to save at cfp_Details table */
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

                    /* Used to save at cfp_contract table */
                    CfpContract cfpcontract;
                    cfpcontract = CfpContractLocalServiceUtil.createCfpContract(0);
                    cfpcontract.setCfpModelSid(cfpmodel.getCfpModelSid());
                    cfpcontract.setCfpName(cfpName);
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

                    /* Used to save at cfp_contract_Details table */
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
                    IfpModel ifpmodel;
                    ifpmodel = IfpModelLocalServiceUtil.createIfpModel(0);
                    /*below query is used to get cfp value from temp table */
                    String query = "select COMPONENT_NAME,COMPONENT_ID,COMPONENT_NO,START_DATE,END_DATE,COMPONENT_TYPE,COMPONENT_STATUS from dbo.GCM_CONTRACT_DETAILS WHERE GCM_CONTRACT_DETAILS_SID='" + ifpModelId + "'";
                    List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

                    /*below loop is used to save value to ifp_model table */
                    if (list != null && list.size() > 0) {
                        Object[] obj = (Object[]) list.get(0);
                        ifpmodel.setIfpName(String.valueOf(obj[0]));
                        ifpmodel.setIfpId(String.valueOf(obj[1]));
                        ifpmodel.setIfpNo(String.valueOf(obj[2]));
                        ifpmodel.setIfpStatus(Integer.valueOf(String.valueOf(obj[6])));
                        ifpmodel.setIfpType(Integer.valueOf(String.valueOf(obj[5])));
                        if (obj[3] != null) {
                            ifpmodel.setIfpStartDate((Date) obj[3]);
                        }
                        if (obj[4] != null) {
                            ifpmodel.setIfpEndDate((Date) obj[4]);

                            ifpmodel.setSource("GCM");
                            ifpmodel.setInboundStatus("A");
                            ifpmodel.setCreatedDate(new Date());
                            ifpmodel.setModifiedDate(new Date());
                            ifpmodel.setRecordLockStatus(false);
                            ifpmodel.setCreatedBy(Integer.valueOf(userId));
                            ifpmodel.setModifiedBy(Integer.valueOf(userId));
                            ifpmodel = IfpModelLocalServiceUtil.addIfpModel(ifpmodel);
                        }

                        /*Below Mentioned query is used to get selected item for this particular ifp from temp table */
                        String itemQuery = "select ITEM_MASTER_SID from dbo.GCM_GLOBAL_DETAILS where IFP_MODEL_SID='" + ifpModelId + "'";
                        List itemList = HelperTableLocalServiceUtil.executeSelectQuery(itemQuery);

                        /*Below loop is used to save the details in ifp_Details table */
                        if (itemList != null && itemList.size() > 0) {
                            for (int i = 0; i < itemList.size(); i++) {
                                IfpDetails ifpDetails;
                                ifpDetails = IfpDetailsLocalServiceUtil.createIfpDetails(0);
                                int itemMasterId = Integer.valueOf(String.valueOf(itemList.get(i)));
                                String itemDetails = "select ITEM_MASTER_SID,ITEM_START_DATE,ITEM_END_DATE from dbo.ITEM_MASTER WHERE ITEM_MASTER_SID='" + itemMasterId + "'";
                                List detList = HelperTableLocalServiceUtil.executeSelectQuery(itemDetails);
                                if (detList != null && detList.size() > 0) {
                                    Object[] obje = (Object[]) detList.get(0);
                                    ifpDetails.setIfpModelSid(ifpmodel.getIfpModelSid());
                                    ifpDetails.setItemMasterSid(Integer.valueOf(itemMasterId));
                                    if (obje[1] != null) {
                                        ifpDetails.setStartDate((Date) obje[1]);
                                    } else {
                                        ifpDetails.setStartDate(new Date());
                                    }
                                    ifpDetails.setEndDate((Date) obje[2]);

                                }
                                ifpDetails.setSource("GCM");
                                ifpDetails.setInboundStatus("A");
                                ifpDetails.setCreatedDate(new Date());
                                ifpDetails.setModifiedDate(new Date());
                                ifpDetails.setRecordLockStatus(false);
                                ifpDetails.setCreatedBy(Integer.valueOf(userId));
                                ifpDetails.setModifiedBy(Integer.valueOf(userId));
                                ifpDetails = IfpDetailsLocalServiceUtil.addIfpDetails(ifpDetails);

                            }
                        }

                        String cfpName = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.CFP_NAME).getValue());
                        Object parentItem = contractDashboardResultsTable.getParent(item);
                        String cfpContractSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                        IfpModel ifpmodel1;
                        ifpmodel1 = IfpModelLocalServiceUtil.getIfpModel(ifpmodel.getIfpModelSid());

                        /* Used to save ifp_contract table */
                        IfpContract ifpcontract;
                        ifpcontract = IfpContractLocalServiceUtil.createIfpContract(0);
                        ifpcontract.setIfpModelSid(ifpmodel.getIfpModelSid());
                        ifpcontract.setIfpName(ifpmodel1.getIfpName());
                        ifpcontract.setIfpType(ifpmodel1.getIfpType());
                        ifpcontract.setIfpCategory(ifpmodel1.getIfpCategory());
                        ifpcontract.setIfpStatus(ifpmodel1.getIfpStatus());
                        ifpcontract.setIfpStartDate(ifpmodel1.getIfpStartDate());
                        ifpcontract.setIfpEndDate(ifpmodel1.getIfpEndDate());
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

                        /*Used to save ifp_contract_details table */
                        List<Object> input = new ArrayList<Object>(8);
                        input.add(ifpcontract.getIfpContractSid());
                        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                        input.add(DBDate.format(new Date()));
                        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                        input.add(DBDate.format(new Date()));
                        input.add(ifpmodel.getIfpModelSid());
                        input.add(DBDate.format(ifpmodel.getIfpStartDate()));
                        input.add(ifpmodel.getIfpEndDate() == null ? null : DBDate.format(ifpmodel.getIfpEndDate()));
                        IfpContractDetailsLocalServiceUtil.saveIfpDetailsAttached(input, null);
                    }
                } else if (level.equals(Constants.THREE)) {
                    String psModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());
                    PsModel psModel;
                    psModel = PsModelLocalServiceUtil.createPsModel(0);

                    /*Below query is used to get ps details from temp table */
                    String query = "select COMPONENT_ID,COMPONENT_NO,COMPONENT_NAME,COMPONENT_TYPE,START_DATE,END_DATE,COMPONENT_STATUS from dbo.GCM_CONTRACT_DETAILS WHERE GCM_CONTRACT_DETAILS_SID='" + psModelId + "'";
                    List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

                    /*Below loop is used to save ps value in ps_details table */
                    if (list != null && list.size() > 0) {
                        Object[] obj = (Object[]) list.get(0);
                        psModel.setPsName(String.valueOf(obj[2]));
                        psModel.setPsNo(String.valueOf(obj[1]));
                        psModel.setPsId(String.valueOf(obj[0]));
                        psModel.setPsType(Integer.valueOf(String.valueOf(obj[3])));
                        psModel.setPsStatus(Integer.valueOf(String.valueOf(obj[6])));
                        if (obj[4] != null) {
                            psModel.setPsStartDate((Date) obj[4]);
                        } else {
                            psModel.setPsStartDate(new Date());
                        }
                        if (obj[5] != null) {
                            psModel.setPsEndDate((Date) obj[5]);
                        }

                        psModel.setSource("GCM");
                        psModel.setInboundStatus("A");
                        psModel.setCreatedDate(new Date());
                        psModel.setModifiedDate(new Date());
                        psModel.setRecordLockStatus(false);
                        psModel.setCreatedBy(Integer.valueOf(userId));
                        psModel.setModifiedBy(Integer.valueOf(userId));
                        psModel = PsModelLocalServiceUtil.addPsModel(psModel);
                    }

                    /*Below query is used to get selected item for this ps from temp table */
                    String itemQuery = "select ITEM_MASTER_SID from dbo.GCM_GLOBAL_DETAILS where PS_MODEL_SID='" + psModelId + "'";
                    List itemList = HelperTableLocalServiceUtil.executeSelectQuery(itemQuery);

                    /*Below loop is used to save values in ps_details */
                    if (itemList != null && itemList.size() > 0) {
                        for (int i = 0; i < itemList.size(); i++) {
                            int itemMasterId = 0;
                            itemMasterId = Integer.valueOf(String.valueOf(itemList.get(i)));
                            String itemDetails = "select ITEM_MASTER_SID,ITEM_START_DATE,ITEM_END_DATE from dbo.ITEM_MASTER WHERE ITEM_MASTER_SID='" + itemMasterId + "'";
                            List detList = HelperTableLocalServiceUtil.executeSelectQuery(itemDetails);
                            if (detList != null && detList.size() > 0) {
                                Object[] obje = (Object[]) detList.get(0);
                                String query1 = "INSERT INTO dbo.PS_DETAILS(PS_MODEL_SID,IFP_MODEL_SID,ITEM_MASTER_SID,CONTRACT_PRICE_START_DATE,CONTRACT_PRICE_END_DATE,\"SOURCE\",INBOUND_STATUS,CREATED_DATE,MODIFIED_DATE,RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY,PRICE) VALUES";
                                query1 += "(?PS_MODEL_ID,?IFP_MODEL_ID,?ITEM_MASTER_ID,?START_DATE,?END_DATE,'GCM','A','?CREATED_DATE','?MODIFIED_DATE',?RECORD_LOCK_STATUS,?USER_ID,?USER_ID,?PRICE)";
                                query1 = query1.replaceAll("\\?PS_MODEL_ID", String.valueOf(psModel.getPsModelSid()));
                                query1 = query1.replaceAll("\\?IFP_MODEL_ID", "1");
                                query1 = query1.replaceAll("\\?ITEM_MASTER_ID", String.valueOf(itemMasterId));
                                if (obje[1] != null) {
                                    String date = simpleDateFormat.format((Date) obje[1]);
                                    query1 = query1.replaceAll("\\?START_DATE", "'" + date + "'");
                                } else {
                                    query1 = query1.replaceAll("\\?START_DATE", "NULL");
                                }
                                if (obje[2] != null) {
                                    String date = simpleDateFormat.format((Date) obje[2]);
                                    query1 = query1.replaceAll("\\?END_DATE", "'" + date + "'");
                                } else {
                                    query1 = query1.replaceAll("\\?END_DATE", "NULL");
                                }
                                query1 = query1.replaceAll("\\?RECORD_LOCK_STATUS", "'false'");
                                String createdDate = simpleDateFormat.format(new Date());
                                query1 = query1.replaceAll("\\?CREATED_DATE", createdDate);
                                query1 = query1.replaceAll("\\?MODIFIED_DATE", createdDate);
                                query1 = query1.replaceAll("\\?USER_ID", userId);
                                query1 = query1.replaceAll("\\?PRICE", Constants.ZEROSTRING);
                                HelperTableLocalServiceUtil.executeUpdateQuery(query1);
                            }
                        }
                    }

                    Object parentItem = contractDashboardResultsTable.getParent(item);
                    String IFPsystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentItem, Constants.HIDDEN_ID).getValue());
                    Object parentCFPItem = contractDashboardResultsTable.getParent(parentItem);
                    String CFPsystemId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                    /* Used to save value in ps_contract table */
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

                    /* Used to save value in ps_contract_details table */
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
                    /*Below line is used to get temp table sid from tree node */
                    String rsModelId = String.valueOf(contractDashboardResultsTable.getContainerProperty(item, Constants.MODEL_ID).getValue());

                    /*below query is used to get rs value from temp table */
                    String query = queryUtils.getRsValueFromTempTable(rsModelId);
                    List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

                    /*Below loop is used to save ps value in ps_details table */
                    if (list != null && list.size() > 0) {
                        RsModel rsModel;
                        rsModel = RsModelLocalServiceUtil.createRsModel(0);
                        Object[] obj = (Object[]) list.get(0);
                        rsModel.setRsName(String.valueOf(obj[0]));
                        rsModel.setRsId(String.valueOf(obj[1]));
                        rsModel.setRsNo(String.valueOf(obj[2]));
                        if (obj[3] != null) {
                            rsModel.setRsStartDate((Date) obj[3]);
                        } else {
                            rsModel.setRsStartDate(new Date());
                        }
                        if (obj[4] != null) {
                            rsModel.setRsEndDate((Date) obj[4]);
                        }
                        rsModel.setRsType(Integer.valueOf(String.valueOf(obj[5])));
                        rsModel.setRsStatus(Integer.valueOf(String.valueOf(obj[6])));
                        rsModel.setRebateProgramType(Integer.valueOf(String.valueOf(obj[7])));
                        rsModel.setRebatePlanLevel(String.valueOf(obj[8]));
                        rsModel.setPaymentFrequency(Integer.valueOf(String.valueOf(obj[9])));
                        rsModel.setPaymentMethod(Integer.valueOf(String.valueOf(obj[10])));
                        rsModel.setSource("GCM");
                        rsModel.setInboundStatus("A");
                        rsModel.setCreatedDate(new Date());
                        rsModel.setModifiedDate(new Date());
                        rsModel.setRecordLockStatus(false);
                        rsModel.setCreatedBy(Integer.valueOf(userId));
                        rsModel.setModifiedBy(Integer.valueOf(userId));
                        rsModel = RsModelLocalServiceUtil.addRsModel(rsModel);
                   

                        String rsQuery = queryUtils.getRSDetailsFromTempTable(rsModelId);
                        List itemList = HelperTableLocalServiceUtil.executeSelectQuery(rsQuery);

                        /*Below loop is used to save values in ps_details */
                        if (itemList != null && itemList.size() > 0) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String itemMasterId = String.valueOf(itemList.get(i));
                                String itemDetails = "select ITEM_MASTER_SID,ITEM_START_DATE,ITEM_END_DATE from dbo.ITEM_MASTER WHERE ITEM_MASTER_SID='" + itemMasterId + "'";
                                List detList = HelperTableLocalServiceUtil.executeSelectQuery(itemDetails);
                                if (detList != null && detList.size() > 0) {
                                    Object[] obje = (Object[]) detList.get(0);
                                    String rsquery = queryUtils.insertIntoRsDeatils(String.valueOf(rsModel.getRsModelSid()), ifpModelId, itemMasterId, obje[1]);
                                    HelperTableLocalServiceUtil.executeUpdateQuery(rsquery);
                                }
                            }
                        }

                        Object psParentItem = contractDashboardResultsTable.getParent(item);
                        String contractPSId = String.valueOf(contractDashboardResultsTable.getContainerProperty(psParentItem, Constants.HIDDEN_ID).getValue());
                        Object parentIFPItem = contractDashboardResultsTable.getParent(psParentItem);
                        String contractIFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentIFPItem, Constants.HIDDEN_ID).getValue());
                        Object parentCFPItem = contractDashboardResultsTable.getParent(parentIFPItem);
                        String contractCFPId = String.valueOf(contractDashboardResultsTable.getContainerProperty(parentCFPItem, Constants.HIDDEN_ID).getValue());

                        /*Used to save at rs_contract table */
                        RsContract rsContract;
                        rsContract = RsContractLocalServiceUtil.createRsContract(0);
                        rsContract.setRsModelSid(rsModel.getRsModelSid());
                        rsContract.setRsId(rsModel.getRsId());
                        rsContract.setRsNo(rsModel.getRsNo());
                        rsContract.setRsType(rsModel.getRsType());
                        rsContract.setRebateProgramType(rsModel.getRebateProgramType());
                        rsContract.setRsCategory(rsModel.getRsCategory());
                        rsContract.setRsStatus(rsModel.getRsStatus());
                        rsContract.setRsDesignation(String.valueOf(rsModel.getRsDesignation()));
                        rsContract.setRsStartDate(rsModel.getRsStartDate());
                        rsContract.setRsEndDate(rsModel.getRsEndDate());
                        rsContract.setRsTradeClass(rsModel.getRsTradeClass());
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
                        input.add(rsModel.getRsModelSid());
                        input.add(rsModel.getRsStartDate());
                        input.add(rsModel.getRsEndDate() == null ? null : rsModel.getRsEndDate());
                        RsContractDetailsLocalServiceUtil.saveRsDetailsAttached(input, null);

                    }
                }
            }
            LOGGER.info("TP Promoted Successfully as Contract Holder");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return returnList;
    }

    /*
     This method is used to create work sheet
     */
    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, Exception {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY); //used to remove checkbox header in excel
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    /*
     This method is used to create work sheet Content
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                /*This if loop is used to write Component Selection excel */
                if (contractExcelFlag) {
                    contInfoDto.setStartIndex(start);
                    contInfoDto.setEndIndex(end);
                    Object[] columns = compItemSearchTable.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, "checkRecord");
                    List<ComponentInfoDTO> searchList = logic.getComponentItemSearchResult(contInfoDto);
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                } else if (infoExcelFlag) {
                    /* This loop is used to write Component Details selected items in the excel */
                    List<ComponentInfoDTO> searchList = componentDetailResultsContainer.getItemIds();
                    Object[] columns = componentDetailsTable.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, "checkRecord");
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @UiHandler("massPopulateBtn")
    public void populateButtonLogic(Button.ClickEvent event) {
        final CommmonLogic logic = new CommmonLogic();
        String fieldValue = String.valueOf(fieldDdlb.getValue());

        if (componentDetailResultsContainer.size() == 0) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please add an item to apply the Mass Update to");
            return;
        }

        int checkCount = 0;
        for (ComponentInfoDTO dto : componentDetailResultsContainer.getItemIds()) {
            if (dto.getCheckRecord()) {
                checkCount++;
            }
        }

        if (checkCount == 0) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please select at least one item to apply the Mass Update to");
            return;
        }
        if (Constants.NULL.equals(fieldValue)) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please select a Field to Mass Update");
            return;
        }

        if ("Status".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(massValue.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(massValue.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter any value to Mass Update.");
                return;
            }
        } else if ("Start Date".equals(String.valueOf(fieldDdlb.getValue()))) {

            if (!String.valueOf(massStartDate.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(massStartDate.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter a Start Date to Mass Update.");
                return;
            }
        } else if ("End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(massEndDate.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(massEndDate.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter an End Date to Mass Update.");
                return;
            }


        }

        performMassUpdate(String.valueOf(fieldDdlb.getValue()));

    }

    private void performMassUpdate(String fieldValue) {
        LOGGER.info("Entering performMassUpdate");

        List<ComponentInfoDTO> containerList = componentDetailResultsContainer.getItemIds();
        isTableUpdate = true;

        try {
            for (ComponentInfoDTO dto : containerList) {
                if (dto.getCheckRecord()) {
                    if ("Status".equals(fieldValue)) {
                        componentDetailsTable.getContainerProperty(dto, "statusId").setValue(idHelperDTOMap.get(CommonUtils.convertToInteger(String.valueOf(massValue.getValue()))));
                    } else if ("Start Date".equals(fieldValue)) {
                        componentDetailsTable.getContainerProperty(dto, "itemStartDate").setValue(massStartDate.getValue());
                    } else if ("End Date".equals(fieldValue)) {
                        componentDetailsTable.getContainerProperty(dto, "itemEndDate").setValue(massEndDate.getValue());
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(" Mass populate error" + ex.getMessage());

        }
        isTableUpdate = false;
        LOGGER.info("Exiting performMassUpdate");
    }

    private boolean checkIfpPsRsDetails(List<ComponentInfoDTO> childList, List<ComponentInfoDTO> parentList) {
        boolean retval = false;
        int count=0;
        if (childList != null && parentList != null) {
            if (childList.size() == parentList.size()) {
                List<String> psIfpID = new ArrayList<>();
                List<String> ifpID = new ArrayList<>();
                int length = childList.size();
                for (int i = 0; i < length; i++) {
                    psIfpID.add(childList.get(i).getItemMasterId());
                    ifpID.add(parentList.get(i).getItemMasterId());
}
                Collections.sort(psIfpID);
                Collections.sort(ifpID);
                for (int i = 0; i < length; i++) {
                    if (psIfpID.get(i).trim().equals((ifpID.get(i)).trim())) {
                        count++;
                    }
                }
                if(count == length || count == length-1){
                    retval=true;
                }
            }
        }
        return retval;
    }

}
