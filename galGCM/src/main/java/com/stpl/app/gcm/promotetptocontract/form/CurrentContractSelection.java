/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.dto.ContractHolderDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.CurrentContractTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.ID;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.ParseException;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.customtextfield.CustomTextField;

/**
 *
 * @author alok.v
 */
public class CurrentContractSelection extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CurrentContractSelection.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateRadio;
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    @UiField("massUpdateValue")
    public TextField massUpdateValue;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("salesProjectionRadio")
    public OptionGroup salesProjectionRadio;
    @UiField("currTradingPartnerTableLayout")
    public VerticalLayout currTradingPartnerTableLayout;
    @UiField("componentInfoPanelTableLayout")
    public VerticalLayout componentInfoPanelTableLayout;
    @UiField("rebateScheduleCategory")
    public ComboBox rebateScheduleCategory;
    @UiField("rebateProgramType")
    public ComboBox rebateProgramType;
    @UiField("rarCategory")
    public ComboBox rarCategory;
    @UiField("rebateScheduleType")
    public ComboBox rebateScheduleType;
    @UiField("marketType")
    public ComboBox marketType;
    @UiField("componentSelection")
    public ComboBox componentSelection;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("companyName")
    public TextField companyName;
    @UiField("companyType")
    public TextField companyType;
    @UiField("companyCategory")
    public TextField companyCategory;
    @UiField("tradeClass")
    public TextField tradeClass;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField(Constants.CONTRACT_HOLDER)
    public CustomTextField contractHolder;
    @UiField("rsNumber")
    private TextField rsNumber;
    @UiField("status")
    private TextField status;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("rsName")
    private TextField rsName;
    @UiField("rarType")
    private TextField rarType;
    @UiField("basis")
    private TextField basis;
    @UiField("resetBtn1")
    public Button resetBtn1;
    @UiField("rebateScheduleNo")
    private TextField rebateScheduleNo;
    @UiField("rebateScheduleId")
    private TextField rebateScheduleId;
    @UiField("rebateScheduleAlias")
    private TextField rebateScheduleAlias;
    @UiField("rebateScheduleName")
    private TextField rebateScheduleName;
    @UiField("excelBtn1")
    public Button excelBtn1;
    @UiField("excelBtn2")
    public Button excelBtn2;
    @UiField("submitBtn")
    public Button submitBtn;
    /**
     * RS
     */
    @UiField("startDate")
    private TextField componentStartDate;
    @UiField("rsId")
    private TextField rebateId;
    @UiField("endDate")
    private TextField componentEndDate;
    @UiField("rsNumberLabel")
    private Label rsNumberLabel;
    @UiField("rebateFrequencyLabel")
    private Label rebateFrequencyLabel;
    @UiField("rsIdLabel")
    private Label rsIdLabel;
    @UiField("rsNameLabel")
    private Label rsNameLabel;
    @UiField("rarTypeLabel")
    private Label rarTypeLabel;
    @UiField("basisLabel")
    private Label basisLabel;
    @UiField("RSProgramType")
    private TextField RSProgramType;
    @UiField("RSCategory")
    private TextField RSCategory;
    @UiField("RebatePlanlevel")
    private TextField RebatePlanlevel;
    @UiField("PaymentFrequency")
    private TextField PaymentFrequency;
     @UiField("rsProgrmtypeLabel")
    private Label rsProgrmtypeLabel;
    @UiField("rsCategoryLabel")
    private Label rsCategoryLabel;
    @UiField("paymentFrequencyLabel")
    private Label paymentFrequencyLabel;
    @UiField("rebatePlanLevelLabel")
    private Label rebatePlanLevelLabel;
    
    private BeanItemContainer<ComponentInfoDTO> componentInfoContainer = new BeanItemContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    LazyBeanItemContainer<CurrentContractDTO> resultsLazyContainer;
    public CurrentContractDTO currentContractDTO = new CurrentContractDTO();
    ExtFilterTable resultTable;
    PromoteTPLogic logic = new PromoteTPLogic();
    public CurrentContractTableLogic tableLogic = new CurrentContractTableLogic();
    public ExtPagedTable currentTradingPartnerTable2 = new ExtPagedTable(tableLogic);
    List<CurrentContractDTO> selecteditemList = new ArrayList<CurrentContractDTO>();
    BeanItemContainer<CurrentContractDTO> searchContainer = new BeanItemContainer<CurrentContractDTO>(CurrentContractDTO.class);
    CurrentContractDTO binderDto = new CurrentContractDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<CurrentContractDTO>(binderDto));
    public ExtFilterTable compInfoTable = new ExtFilterTable();
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    List<String> selection = new ArrayList<String>();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    boolean summaryRefreshed;
    private ContractHolderDTO selectedChHolderGroupDTO;
    List<CompanyMaster> companyMasters;
    private String screenName = StringUtils.EMPTY;
    public List<ComponentInfoDTO> componentInformation = new ArrayList<ComponentInfoDTO>();
    public List<CurrentContractDTO> contractInfo = new ArrayList<CurrentContractDTO>();
    String excelName = "Rebate Schedule Information";
    private CustomTreeContainer<ComponentInfoDTO> excelResultBean = new CustomTreeContainer<ComponentInfoDTO>(ComponentInfoDTO.class);
    private BeanItemContainer<CurrentContractDTO> contractExcelResultBean = new BeanItemContainer<CurrentContractDTO>(CurrentContractDTO.class);
    private ExtCustomTable exportPeriodViewTable;
    private ExtCustomTable contractExportPeriodViewTable;
    public static final String NUM = "Number :";
    public static List<CurrentContractDTO> selectedContract=new ArrayList<CurrentContractDTO>();
    Boolean contractExcelFlag=false;
    Boolean infoExcelFlag=false;
    List<Integer> contractid=new ArrayList<Integer>();
    public List<CurrentContractDTO> getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(List<CurrentContractDTO> selectedContract) {
        this.selectedContract = selectedContract;
    }
    
    /**
     * The from date.
     */
    @UiField("massStartDate")
    private PopupDateField massStartDate;
    /**
     * The to date.
     */
    @UiField("massEndDate")
    private PopupDateField massEndDate;

    /**
     * Current Contract Selection Constructor
     *
     * @param session
     * @param resultTable
     */
    public CurrentContractSelection(SessionDTO session, ExtFilterTable resultTable, int tabPosition, TabSheet tabSheet) {
        try {
            this.session = session;
            this.resultTable = resultTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/currentContractSelection.xml"), this));
            configureFields();
            setHeaderValues();
            disableComponentInfoFields();
            setContData();
        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
        }
    }

    /**
     * Set Read Only Header Values
     *
     */
    public void setHeaderValues() {
        PromoteTpToChDto dto = (PromoteTpToChDto) resultTable.getValue();
        companyNo.setValue(dto.getCompanyNo());
        companyName.setValue(dto.getCompanyName());
        companyType.setValue(dto.getCompanyType());
        companyCategory.setValue(dto.getCompanyCategory());
        tradeClass.setValue(dto.getTradeClass());
        companyNo.setEnabled(false);
        companyName.setEnabled(false);
        companyType.setEnabled(false);
        companyCategory.setEnabled(false);
        tradeClass.setEnabled(false);
        binderDto.setCompanySystemId(dto.getCompanySystemId());
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // empty
    }

    public void loadCurrentContractGrid() {
        if (!tableLogic.loadSetData(binderDto, session)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        }
    }

    protected void configureFields() {
        try {
            screenName = TAB_CURRENT_CONTRACT.getConstant();
            contractNo.setData("maxlengthvalidationhundred,maxlengthvalidationcontractno,specialchar,specialcharcontractno");
            contractNo.setImmediate(true);
            contractNo.setValidationVisible(true);

            contractName.setData("maxlengthvalidationhundred,maxlengthvalidationcontractname,specialchar,specialcharcontractname");
            contractName.setImmediate(true);
            contractName.setValidationVisible(true);
            
            rebateScheduleId.setData("maxlengthvalidation,maxlengthvalidationrebatescheduleid,specialchar,specialcharrebateschedule");
            rebateScheduleId.setImmediate(true);
            rebateScheduleId.setValidationVisible(true);

            rebateScheduleName.setData("maxlengthvalidationhundred,maxlengthvalidationrebateschedule,specialchar,specialcharrebateschedulename");
            rebateScheduleName.setImmediate(true);
            rebateScheduleName.setValidationVisible(true);

            rebateScheduleNo.setData("maxlengthvalidationhundred,maxlengthvalidationrsno,specialchar,specialcharrsno");
            rebateScheduleNo.setImmediate(true);
            rebateScheduleNo.setValidationVisible(true);

            rebateScheduleAlias.setData("maxlengthvalidationhundred,maxlengthvalidationrsalias,specialchar,specialcharRrsalias");
            rebateScheduleAlias.setImmediate(true);
            rebateScheduleAlias.setValidationVisible(true);

            massStartDate.setEnabled(false);
            massStartDate.setDateFormat("MM/dd/yyyy");
            massEndDate.setDateFormat("MM/dd/yyyy");
            massUpdateRadio.setImmediate(true);
            massUpdateRadio.addItem(ENABLE.getConstant());
            massUpdateRadio.addItem(DISABLE.getConstant());
            massUpdateRadio.select(DISABLE.getConstant());
            massUpdateRadio.setEnabled(true);

            salesProjectionRadio.setImmediate(true);
            salesProjectionRadio.addItem(YES.getConstant());
            salesProjectionRadio.addItem(NO.getConstant());
            salesProjectionRadio.select(YES.getConstant());
            salesProjectionRadio.setEnabled(true);

            fieldDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.addItem(Constants.IndicatorConstants.TP_COMPANY_END_DATE.getConstant());
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());

            componentSelection.addItem(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant());
            componentSelection.addItem(Constants.IndicatorConstants.PRICE_SCHEDULE.getConstant());
            componentSelection.addItem(Constants.IndicatorConstants.REBATE_SCHEDULE.getConstant());
            componentSelection.setValue(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant());
            
            rarCategory.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            rarCategory.setNullSelectionAllowed(true);
            rarCategory.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            
                  componentSelection.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    String compType = String.valueOf(componentSelection.getValue());
                if (compType.equals(Constants.COMPANY_FAMILY_PLAN)) {
                    rsIdLabel.setCaption("CFP ID:"); 
                    rsNumberLabel.setCaption("CFP Number:");
                    rsNameLabel.setCaption("CFP Name:");
                    rebateFrequencyLabel.setVisible(false); 
                    rebateFrequency.setVisible(false);
                    rarTypeLabel.setVisible(false);
                    rarType.setVisible(false);
                    basisLabel.setVisible(false);
                    basis.setVisible(false);
                    rsProgrmtypeLabel.setVisible(false);
                    RSProgramType.setVisible(false);
                    rsCategoryLabel.setVisible(false);
                    RSCategory.setVisible(false);
                    rebatePlanLevelLabel.setVisible(false);
                    RebatePlanlevel.setVisible(false);
                    paymentFrequencyLabel.setVisible(false);
                    PaymentFrequency.setVisible(false);
                    compInfoTable.setVisibleColumns(Constants.COMPONENT_INFO_COLUMNS_CFP);
                    compInfoTable.setColumnHeaders(Constants.COMPONENT_INFO_HEADERS_CFP);
                }
                if (compType.equals(Constants.ITEM_FAMILY_PLAN)) {
                    rsIdLabel.setCaption("IFP ID:"); 
                    rsNumberLabel.setCaption("IFP Number:");
                    rsNameLabel.setCaption("IFP Name:");
                    rebateFrequencyLabel.setVisible(false); 
                    rebateFrequency.setVisible(false);
                    rarTypeLabel.setVisible(false);
                    rarType.setVisible(false);
                    basisLabel.setVisible(false);
                    basis.setVisible(false);
                    rsProgrmtypeLabel.setVisible(false);
                    RSProgramType.setVisible(false);
                    rsCategoryLabel.setVisible(false);
                    RSCategory.setVisible(false);
                    rebatePlanLevelLabel.setVisible(false);
                    RebatePlanlevel.setVisible(false);
                    paymentFrequencyLabel.setVisible(false);
                    PaymentFrequency.setVisible(false);
                    compInfoTable.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_COLUMNS_IFP);
                    compInfoTable.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_HEADERS_IFP);
                }
                 if (compType.equals(Constants.PRICE_SCHEDULE)) {
                    rsIdLabel.setCaption("PS ID:"); 
                    rsNumberLabel.setCaption("PS Number:");
                    rsNameLabel.setCaption("PS Name:");
                    rebateFrequencyLabel.setVisible(false); 
                    rebateFrequency.setVisible(false);
                    rarTypeLabel.setVisible(false);
                    rarType.setVisible(false);
                    basisLabel.setVisible(false);
                    basis.setVisible(false);
                    rsProgrmtypeLabel.setVisible(false);
                    RSProgramType.setVisible(false);
                    rsCategoryLabel.setVisible(false);
                    RSCategory.setVisible(false);
                    rebatePlanLevelLabel.setVisible(false);
                    RebatePlanlevel.setVisible(false);
                    paymentFrequencyLabel.setVisible(false);
                    PaymentFrequency.setVisible(false);
                     compInfoTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_PS);
                    compInfoTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_PS);
                }
                 if (compType.equals(Constants.REBATE_SCHEDULE)) {
                    rsIdLabel.setCaption("RS ID:"); 
                    rsNumberLabel.setCaption("RS Number:");
                    rsNameLabel.setCaption("RS Name:");
                    rebateFrequencyLabel.setVisible(true); 
                    rebateFrequency.setVisible(true);
                    rarTypeLabel.setVisible(true);
                    rarType.setVisible(true);
                    basisLabel.setVisible(true);
                    basis.setVisible(true);
                    rsProgrmtypeLabel.setVisible(true);
                    RSProgramType.setVisible(true);
                    rsCategoryLabel.setVisible(true);
                    RSCategory.setVisible(true);
                    rebatePlanLevelLabel.setVisible(true);
                    RebatePlanlevel.setVisible(true);
                    paymentFrequencyLabel.setVisible(true);
                    PaymentFrequency.setVisible(true);
                     compInfoTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
                    compInfoTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
                }
                    loadComponentInformation(String.valueOf(event.getProperty().getValue()), currentTradingPartnerTable2.getValue());
                }
            });

            currTradingPartnerTableLayout.addComponent(currentTradingPartnerTable2);
            configureCurrentTradingPartnerTable();
            HorizontalLayout controls = tableLogic.createControls();
            currTradingPartnerTableLayout.addComponent(controls);
            componentInfoPanelTableLayout.addComponent(compInfoTable);
            excelBtn1.setIcon(excelExportImage);
            excelBtn2.setIcon(excelExportImage);
            configureCompInfoTable();
            loadRebateScheduleCategory();
            loadRebateScheduleType();
            loadRebateProgramType();
            loadMarketType();
            loadRARCategory();
            getBinder();

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<CurrentContractDTO>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Configure Current Contract Table
     *
     */
    private void configureCurrentTradingPartnerTable() {
        tableLogic.setContainerDataSource(searchContainer);
        tableLogic.setPageLength(100);
        tableLogic.sinkItemPerPageWithPageLength(true);
        currentTradingPartnerTable2.setVisibleColumns(Constants.CURRENT_TRADING_PARTNER_COLUMNS);
        currentTradingPartnerTable2.setColumnHeaders(Constants.CURRENT_TRADING_PARTNER_HEADERS);
        currentTradingPartnerTable2.setSizeFull();
        currentTradingPartnerTable2.setEditable(true);
        currentTradingPartnerTable2.markAsDirty();
        currentTradingPartnerTable2.setSelectable(true);
        currentTradingPartnerTable2.setWidth("1660px");
        currentTradingPartnerTable2.setHeight("290px");
        currentTradingPartnerTable2.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        currentTradingPartnerTable2.setColumnCheckBox(Constants.CHECK_RECORD, true);

        currentTradingPartnerTable2.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            if(check.getValue()){
                            CurrentContractDTO dto=(CurrentContractDTO) itemId ;
                            contractid.add(dto.getContractSid());
                            session.setContractSystemId(dto.getContractSid());
                            }
                            int count = logic.callCheckRecUpdate(check.getValue(), (CurrentContractDTO) itemId, screenName, session);

                            if (!check.getValue()) {
                                currentTradingPartnerTable2.removeColumnCheckListener(checkListener);
                                currentTradingPartnerTable2.setColumnCheckBox("check", true, false);
                                currentTradingPartnerTable2.addColumnCheckListener(checkListener);

                            }
                        }
                    });
                    return check;
                }
                
                if (propertyId.equals("companyEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    compEndDate.setStyleName("dateFieldCenter");
                    compEndDate.setImmediate(true);
                    compEndDate.addValueChangeListener(new Property.ValueChangeListener() {

                        public void valueChange(Property.ValueChangeEvent event) {
                            int count = logic.callDateUpdate(compEndDate.getValue(), (CurrentContractDTO) itemId, session, screenName, "END_DATE");

                        }
                    });
                    return compEndDate;
                }
                if (propertyId.equals("contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat(Constants.MM_DD_YYYY);
         
                    contEndDate.setStyleName("dateFieldCenter");
                    contEndDate.setEnabled(false);

                    return contEndDate;
                }
                return null;
            }
        });

        currentTradingPartnerTable2.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = currentTradingPartnerTable2.getItemIds();
                for (Object obj : itemList) {
                    CurrentContractDTO dto = (CurrentContractDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                }
            }
        });

        currentTradingPartnerTable2.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                String componentSelectionValue = String.valueOf(componentSelection.getValue());
                if (!SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                    loadComponentInformation(componentSelectionValue, event.getItemId());
                }
            }
        });
    }

    /**
     * Converting list to String
     *
     * @param fieldList
     * @param number
     * @return
     */
    private String getFromList(List<String> fieldList, int number) {
        String fieldvalue = StringUtils.EMPTY;
        if (fieldList != null && fieldList.get(number) != null) {
            fieldvalue = String.valueOf(fieldList.get(number));
        }
        return fieldvalue;
    }

    /**
     * Configure Component Info Table
     *
     */
    public void configureCompInfoTable() {
        LOGGER.info(" Inside configureCompInfoTable method ");
        compInfoTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        compInfoTable.setWidth("1660px");
        compInfoTable.setHeight("270px");
        compInfoTable.setPageLength(5);
        compInfoTable.setContainerDataSource(componentInfoContainer);
        compInfoTable.setVisibleColumns(Constants.PTP_COMPONENT_INFO_COLUMNS);
        compInfoTable.setColumnHeaders(Constants.PTP_COMPONENT_INFO_HEADERS);
        LOGGER.info("configureCompInfoTable method Ended");
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.info(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateRadio.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.info("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Current Contract Search Functionality
     *
     * @param event
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        LOGGER.info(" Inside searchButtonLogic ");
        binderDto = new CurrentContractDTO();
        binderDto.setCompanySystemId(session.getCompanyMasterSid());
        boolean searCriteria = false;
        if (!contractNo.getValue().equals(StringUtils.EMPTY) && !contractNo.getValue().equals(Constants.NULL)) {
            binderDto.setContractNo(contractNo.getValue());
            searCriteria = true;
        }
        if (!contractName.getValue().equals(StringUtils.EMPTY) && !contractName.getValue().equals(Constants.NULL)) {
            binderDto.setContractName(contractName.getValue());
            searCriteria = true;
        }
        if (!contractHolder.getValue().equals(StringUtils.EMPTY) && !contractHolder.getValue().equals(Constants.NULL)) {
            binderDto.setContractHolder(contractHolder.getValue());
            searCriteria = true;
        }
        if (!String.valueOf(marketType.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(marketType.getValue()).equals(Constants.NULL)) {
            binderDto.setMarketType(String.valueOf(marketType.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleId.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleId.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateScheduleId(String.valueOf(rebateScheduleId.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleNo.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleNo.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateScheduleNo(String.valueOf(rebateScheduleNo.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleName.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleName.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateScheduleName(String.valueOf(rebateScheduleName.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleCategory.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleCategory.getValue()).equals(Constants.NULL)
                && rebateScheduleCategory.getValue() != null) {
            binderDto.setRebateScheduleCategory(String.valueOf(rebateScheduleCategory.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleType.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleType.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateScheduleType(String.valueOf(rebateScheduleType.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateProgramType.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateProgramType.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateProgramType(String.valueOf(rebateProgramType.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rarCategory.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rarCategory.getValue()).equals(Constants.NULL)) {
            binderDto.setRarCategory(String.valueOf(rarCategory.getValue()));
            searCriteria = true;
        }
        if (!String.valueOf(rebateScheduleAlias.getValue()).equals(StringUtils.EMPTY) && !String.valueOf(rebateScheduleAlias.getValue()).equals(Constants.NULL)) {
            binderDto.setRebateScheduleAlias(String.valueOf(rebateScheduleAlias.getValue()));
            searCriteria = true;
        }
        if (!searCriteria) {
            AbstractNotificationUtils.getErrorNotification(MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    MessageConstants.NO_SEARCH_CRITERIA_TP.getConstant());
            return;
        } else {
            binderDto.setSearch(true);
        }
        binderDto.setReset(false);
        if (!tableLogic.loadSetData(binderDto, session)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            CommonUIUtils.getMessageNotification("Search Completed");
        }
    }

    /**
     * Enable and Disable Logic
     *
     * @param value
     */
    public void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        massUpdateValue.setEnabled(value);
        massEndDate.setEnabled(value);
        populateBtn.setEnabled(value);
    }

    private void loadRebateScheduleCategory() {
        logic.LazyLoadDdlb(rebateScheduleCategory, "getRSCategoryCountForPromoteTpToCh", "getRSCategoryForPromoteTpToCh");
    }

    private void loadRebateScheduleType() {
        logic.LazyLoadDdlb(rebateScheduleType, "getRSTypeCountForPromoteTpToCh", "getRSTypeForPromoteTpToCh");
    }

    private void loadRebateProgramType() {
        logic.LazyLoadDdlb(rebateProgramType, "getRPTypeCountForPromoteTpToCh", "getRPTypeForPromoteTpToCh");
    }

    private void loadMarketType() {
        logic.LazyLoadDdlb(marketType, "getMarketTypeCountForPromoteTpToCh", "getMarketTypeForPromoteTpToCh");
    }
    private void loadRARCategory(){
       logic.LazyLoadDdlb(rarCategory, "getRARTypeCount", "getRARType");
    }

    @UiHandler("resetBtn1")
    public void resetBtn1Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", " Are you sure you want to reset the Search values?\n ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        tpSearchReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public void tpSearchReset() {
        rebateScheduleCategory.setValue(StringUtils.EMPTY);
        rebateScheduleType.setValue(StringUtils.EMPTY);
        rebateProgramType.setValue(StringUtils.EMPTY);
        marketType.setValue(StringUtils.EMPTY);
        rarCategory.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        contractNo.setValue(StringUtils.EMPTY);
        contractName.setValue(StringUtils.EMPTY);
        contractHolder.setValue(StringUtils.EMPTY);
        rebateScheduleNo.setValue(StringUtils.EMPTY);
        rebateScheduleId.setValue(StringUtils.EMPTY);
        rebateScheduleAlias.setValue(StringUtils.EMPTY);
        rebateScheduleName.setValue(StringUtils.EMPTY);
    }

    @UiHandler("resetBtn2")
    public void resetBtn2Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", " Are you sure you want to reset the values \n "
                + " in the Current-Customer Details list view? ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        currTpSearchResultsReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public void currTpSearchResultsReset() {
        currentTradingPartnerTable2.removeAllItems();
    }

    public void disableComponentInfoFields() {
        rebateId.setEnabled(false);
        rsNumber.setEnabled(false);
        rsName.setEnabled(false);
        status.setEnabled(false);
        rarType.setEnabled(false);
        rebateFrequency.setEnabled(false);
        basis.setEnabled(false);
        componentStartDate.setEnabled(false);
        componentEndDate.setEnabled(false);
    }
    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (event.isChecked()) {
                checkClearAll(event.isChecked());
            }
        }
    };

    public void checkClearAll(boolean checkValue) {
        List<CurrentContractDTO> containerList = searchContainer.getItemIds();
        for (CurrentContractDTO dto : containerList) {
            currentTradingPartnerTable2.getContainerProperty(dto, "checkRecord").setValue(checkValue);
        }
    }

    @UiHandler("excelBtn1")
    public void contractExport(Button.ClickEvent event) {
        try {
           contractExcelFlag=true;
            final int recordCount = currentTradingPartnerTable2.getContainerDataSource().size();
           if (recordCount > 0) {
                 createWorkSheet("Contract_Details", currentTradingPartnerTable2,recordCount);
            }
            currTradingPartnerTableLayout.removeComponent(contractExportPeriodViewTable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }finally{
           contractExcelFlag=false;  
        }
    }

    @UiHandler("excelBtn2")
    public void componentInfoExport(Button.ClickEvent event) {
        
         try {  
            infoExcelFlag=true;
             final int recordCount = compInfoTable.getContainerDataSource().size();
           if (recordCount > 0) {
                 createWorkSheet(excelName, compInfoTable,recordCount);
            }
            currTradingPartnerTableLayout.removeComponent(contractExportPeriodViewTable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }finally{
               contractExcelFlag=false;
         }
        
    }


    /**
     * Submit Button Logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitButtonlogic(Button.ClickEvent event) {
        LOGGER.info("Contract selection submitButtonlogic initiated");
        List<Boolean> startDateAndEndDate = logic.startDateAndEndDateValidation(session.getUserId(), session.getSessionId(), screenName);

        if (startDateAndEndDate.size() > 0 && startDateAndEndDate.get(0)) {
            AbstractNotificationUtils.getErrorNotification("No Record Selected", "Please select any record to submit");
            return;
        }
        if (TAB_CURRENT_CONTRACT.getConstant().equals(screenName)) {
            
            if (startDateAndEndDate.size() > 1 && startDateAndEndDate.get(1)) {
                AbstractNotificationUtils.getErrorNotification("No End Date", "Please enter an end date for the records to be submitted");
                return;
            }
        }
        summaryRefreshed = true;
        resetComponentInformationData();
           List<CurrentContractDTO> containerList = searchContainer.getItemIds();

        for (CurrentContractDTO dto : containerList) {
            if (dto.getCheckRecord()) {
                logic.updateSubmitFlag(screenName, session.getUserId(), session.getSessionId(), true,dto);
            }
        }
   

        AbstractNotificationUtils.getAlertNotification("Submit Details", "Selected Contract Holder has been submitted successfully.");
        LOGGER.info("Contract selection submitButtonlogic ends ");
    }
    /**
     * Populate Button Logic
     * 
     * @param event 
     */
    @UiHandler("populateBtn")
    public void populateButtonLogic(Button.ClickEvent event) {

        String fieldValue = String.valueOf(fieldDdlb.getValue());

        if (!this.logic.isAnyRecordSelected(session.getUserId(), session.getSessionId(), screenName)) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please select at least one contract to apply the Mass Update to.");
            return;
        }
        if (Constants.NULL.equals(fieldValue)) {
            AbstractNotificationUtils.getAlertNotification("Mass Update Error", "Please select a Field to Mass Update.");
            return;
        } else if ("Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(massEndDate.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(massEndDate.getValue()))) {
                AbstractNotificationUtils.getAlertNotification("Mass Update Error", "Please enter an End Date to Mass Update.");
                return;
            }
            logic.massUpdate(String.valueOf(fieldDdlb.getValue()), session.getUserId(), session.getSessionId(), screenName, massEndDate.getValue());
        }
        List<CurrentContractDTO> containerList = searchContainer.getItemIds();

        for (CurrentContractDTO dto : containerList) {
            if (dto.getCheckRecord()) {
                if ("Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
                    currentTradingPartnerTable2.getContainerProperty(dto, "companyEndDate").setValue(massEndDate.getValue());
                }

            }
        }

    }

    /**
     * Contract Holder Lookup
     *
     * @param event
     */
    @UiHandler(Constants.CONTRACT_HOLDER)
    public void contractHolderLookup(CustomTextField.ClickEvent event) {

        PromoteTPLogic logic = new PromoteTPLogic();
        final TPContractHolderLookUp contractHolderLookUpWindow = new TPContractHolderLookUp("Contract Holder", "Contract Holder Lookup", contractHolder, StringUtils.EMPTY);
        contractHolderLookUpWindow.setWidth("1320px");
        contractHolderLookUpWindow.setHeight("830px");
        UI.getCurrent().addWindow(contractHolderLookUpWindow);
        contractHolderLookUpWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractHolderLookUpWindow.getSelectedChHolderHierarchy() != null) {
                    selectedChHolderGroupDTO = contractHolderLookUpWindow.getSelectedChHolderHierarchy();
                    companyMasters = contractHolderLookUpWindow.getFilteredCompanies();
                }
            }
        });
    }

    public void setContData() {
        CurrentContractDTO currDto = (CurrentContractDTO) currentTradingPartnerTable2.getValue();
    }

    private void loadComponentInformation(String componentSelectionValue, Object selectedItem) {
        LOGGER.info("Inside loadComponentInformation");
        if (selectedItem != null) {
           
            String[] id = new String[4];
            CurrentContractDTO crDTO = (CurrentContractDTO) selectedItem;
            id[0] = String.valueOf(crDTO.getCfpContSid());
            id[1] = String.valueOf(crDTO.getIfpContSid());
            id[2] = String.valueOf(crDTO.getPsContSid());
            id[3] = String.valueOf(crDTO.getRsContSid());
            if (!componentSelectionValue.equals(Constants.NULL) && !SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                changeOnListener(componentSelectionValue);
                Map<String, List> componentInformationMap = logic.getComponentInformation(componentSelectionValue, id);
                loadComponentInformationFields(componentSelectionValue, componentInformationMap.get("FieldData"));
                loadComponentInformationTable(componentSelectionValue, componentInformationMap.get("TableData"));
                componentInformation.addAll(componentInformationMap.get("TableData"));
            } else {
                resetComponentInformationData();
            }
        }
        LOGGER.info("Exiting loadComponentInformation");
    }

    private void resetComponentInformationData() {
        rebateId.setValue(StringUtils.EMPTY);
        rsNumber.setValue(StringUtils.EMPTY);
        rsName.setValue(StringUtils.EMPTY);
        status.setValue(StringUtils.EMPTY);

        componentEndDate.setValue(StringUtils.EMPTY);
        rarType.setValue(StringUtils.EMPTY);
        rebateFrequency.setValue(StringUtils.EMPTY);
        basis.setValue(StringUtils.EMPTY);
        componentStartDate.setValue(StringUtils.EMPTY);
        componentInfoContainer.removeAllItems();
    }

    private void loadComponentInformationFields(String componentSelectionValue, List<String> fieldData) {
        if (fieldData != null && !fieldData.isEmpty()) {
            try {
                rebateId.setValue(fieldData.get(0));
                rsNumber.setValue(getFromList(fieldData, 1));
                rsName.setValue(getFromList(fieldData, 2));
                status.setValue(getFromList(fieldData, 3));
                componentEndDate.setValue(formatDate(getFromList(fieldData, 4)));
                rarType.setValue(getFromList(fieldData, 5));
                rebateFrequency.setValue(getFromList(fieldData, 6));
                basis.setValue(getFromList(fieldData, 7));
                String endDate=getFromList(fieldData, 8).trim();
               if(!endDate.equals(StringUtils.EMPTY)){
                   componentStartDate.setValue(formatDate(getFromList(fieldData, 8)));
               }
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
    }

    private void loadComponentInformationTable(String componentSelectionValue, List<ComponentInfoDTO> tableData) {
        componentInfoContainer.removeAllItems();
        if (tableData != null && !tableData.isEmpty()) {
            if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
                compInfoTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
            } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_PS);
                compInfoTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_PS);
            } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_IFP);
                compInfoTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_IFP);
            }
            componentInfoContainer.addAll(tableData);
        }
    }

    public void changeOnListener(String componentSelection) {

        if (componentSelection.equals(ITEM_FAMILY_PLAN.getConstant())) {
            rsIdLabel.setCaption("IFP " + ID);
            rsNumberLabel.setCaption("IFP " + NUM);
            rsNameLabel.setCaption("IFP Name :");
            rarTypeLabel.setVisible(false);
            basisLabel.setVisible(false);
            rebateFrequencyLabel.setVisible(false);
            rarType.setVisible(false);
            basis.setVisible(false);
            rebateFrequency.setVisible(false);
            excelName = "Item Family Plan Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(PRICE_SCHEDULE.getConstant())) {
            rsIdLabel.setCaption("PS " + ID);
            rsNumberLabel.setCaption("PS " + NUM);
            rsNameLabel.setCaption("PS Name :");
            rarTypeLabel.setVisible(false);
            basisLabel.setVisible(false);
            rebateFrequencyLabel.setVisible(false);
            rarType.setVisible(false);
            basis.setVisible(false);
            rebateFrequency.setVisible(false);
            excelName = "Price Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(REBATE_SCHEDULE.getConstant())) {
            rsIdLabel.setCaption("RS " + ID);
            rsNumberLabel.setCaption("RS " + NUM);
            rsNameLabel.setCaption("RS Name :");
            rarTypeLabel.setVisible(true);
            basisLabel.setVisible(true);
            rebateFrequencyLabel.setVisible(true);
            rarType.setVisible(true);
            basis.setVisible(true);
            rebateFrequency.setVisible(true);
            excelName = "Rebate Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        }

    }
     private void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }
     
    public ExtPagedTable getCurrentTradingPartnerTable2() {
        return currentTradingPartnerTable2;
    }

    public void setCurrentTradingPartnerTable2(ExtPagedTable currentTradingPartnerTable2) {
        this.currentTradingPartnerTable2 = currentTradingPartnerTable2;
    }
     
     public void createWorkSheet(String moduleName, ExtCustomTable resultTable,int count) throws SystemException, PortalException, Exception {
         String[] header=resultTable.getColumnHeaders();
         header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
         ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase());
    }
    
    
      public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
          try {
            if (end != 0) {
                if(contractExcelFlag){
             int recordCount = currentTradingPartnerTable2.getContainerDataSource().size();
             final List<CurrentContractDTO> searchList=logic.getSelectedTPContractResults(logic.getContractQuery(binderDto, session.getUserId(), session.getSessionId(),0, recordCount,false));
             Object[] columns=currentTradingPartnerTable2.getVisibleColumns();
             columns = ArrayUtils.removeElement(columns, "checkRecord");
             ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                }else if(infoExcelFlag){
             final List<ComponentInfoDTO> searchList=componentInfoContainer.getItemIds();
              ExcelExportforBB.createFileContent(compInfoTable.getVisibleColumns(), searchList, printWriter);
                }
              }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    
    
}
