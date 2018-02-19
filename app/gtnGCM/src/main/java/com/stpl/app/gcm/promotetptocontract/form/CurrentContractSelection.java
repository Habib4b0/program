/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.common.CommonLogic;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
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
import com.vaadin.v7.ui.OptionGroup;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckListener;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentContractSelection.class);
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
     @UiField("resetBtn2")
    public Button resetBtn2;
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

    private BeanItemContainer<ComponentInfoDTO> componentInfoContainer = new BeanItemContainer<>(ComponentInfoDTO.class);
    private CurrentContractDTO currentContractDTO = new CurrentContractDTO();
    private ExtFilterTable resultTable;
    private PromoteTPLogic logic = new PromoteTPLogic();
    private CurrentContractTableLogic tableLogic = new CurrentContractTableLogic();
    private ExtPagedTable currentTradingPartnerTable2 = new ExtPagedTable(tableLogic);
    private BeanItemContainer<CurrentContractDTO> searchContainer = new BeanItemContainer<>(CurrentContractDTO.class);
    private CurrentContractDTO binderDto = new CurrentContractDTO();
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private ExtFilterTable compInfoTable = new ExtFilterTable();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private boolean summaryRefreshed;
    private List<CompanyMaster> companyMasters;
    private String screenName = StringUtils.EMPTY;
    private List<ComponentInfoDTO> componentInformation = new ArrayList<>();
    private List<CurrentContractDTO> contractInfo = new ArrayList<>();
    private String excelName = "Rebate Schedule Information";
    private ExtTreeContainer<ComponentInfoDTO> excelResultBean = new ExtTreeContainer<>(ComponentInfoDTO.class);
    private ExtCustomTable contractExportPeriodViewTable = new ExtCustomTable();
    public static final String NUM = "Number :";
    private static List<CurrentContractDTO> selectedContract = new ArrayList<>();
    private Boolean contractExcelFlag = false;
    private Boolean infoExcelFlag = false;
    private List<Integer> contractid = new ArrayList<>();
    private final StplSecurity stplSecurity = new StplSecurity();

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
    public CurrentContractSelection(SessionDTO session, ExtFilterTable resultTable) {
        try {
            this.session = session;
            this.resultTable = resultTable;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/currentContractSelection.xml"), this));
            configureFields();
            setHeaderValues();
            disableComponentInfoFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
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

    @Override
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
            massUpdateValue.setVisible(false);
            massStartDate.setVisible(false);
            massEndDate.setEnabled(false);
            massStartDate.setDateFormat(Constants.MM_DD_YYYY);
            massEndDate.setDateFormat(Constants.MM_DD_YYYY);
            massUpdateRadio.addItem(ENABLE.getConstant());
            massUpdateRadio.addItem(DISABLE.getConstant());
            massUpdateRadio.select(DISABLE.getConstant());
            massUpdateRadio.setEnabled(true);

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
                @Override
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
                        compInfoTable.setVisibleColumns(Constants.getInstance().componentInfoColumnsCfp);
                        compInfoTable.setColumnHeaders(Constants.getInstance().componentInfoHeadersCfp);
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
                        compInfoTable.setVisibleColumns(Constants.getInstance().adComponentDetailsColumnsIfp);
                        compInfoTable.setColumnHeaders(Constants.getInstance().adComponentDetailsHeadersIfp);
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
                        compInfoTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsPs);
                        compInfoTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersPs);
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
                        compInfoTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsRs);
                        compInfoTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersRs);
                    }
                    loadComponentInformation(String.valueOf(event.getProperty().getValue()), currentTradingPartnerTable2.getValue());
                }
            });

            currTradingPartnerTableLayout.addComponent(currentTradingPartnerTable2);
            HorizontalLayout controls = tableLogic.createControls();
            currTradingPartnerTableLayout.addComponent(controls);
            componentInfoPanelTableLayout.addComponent(compInfoTable);
            configureCurrentTradingPartnerTable();
            
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
            LOGGER.error("",ex);
        }
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Configure Current Contract Table
     *
     */
    private void configureCurrentTradingPartnerTable() {
        tableLogic.setContainerDataSource(searchContainer);        
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        currentTradingPartnerTable2.setVisibleColumns(Constants.getInstance().currentTradingPartnerColumns);
        currentTradingPartnerTable2.setColumnHeaders(Constants.getInstance().currentTradingPartnerHeaders);
        currentTradingPartnerTable2.setSizeFull();
        currentTradingPartnerTable2.setEditable(true);
        currentTradingPartnerTable2.markAsDirty();
        currentTradingPartnerTable2.setSelectable(true);
        currentTradingPartnerTable2.setHeight("290px");
        currentTradingPartnerTable2.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        currentTradingPartnerTable2.setColumnCheckBox(Constants.CHECK_RECORD, true);

        currentTradingPartnerTable2.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            if (check.getValue()) {
                                CurrentContractDTO dto = (CurrentContractDTO) itemId;
                                contractid.add(dto.getContractSid());
                                session.setContractSystemId(dto.getContractSid());
                            } else {
                                currentTradingPartnerTable2.removeColumnCheckListener(checkListener);
                                currentTradingPartnerTable2.setColumnCheckBox("check", true, false);
                                currentTradingPartnerTable2.addColumnCheckListener(checkListener);
                            }
                            logic.callCheckRecUpdate(check.getValue(), (CurrentContractDTO) itemId, screenName, session);
                        }
                    });
                    return check;
                }

                if (propertyId.equals("companyEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat(Constants.MM_DD_YYYY);
                    compEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    compEndDate.setImmediate(true);
                    compEndDate.addValueChangeListener(new Property.ValueChangeListener() {

                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            logic.callDateUpdate(compEndDate.getValue(), (CurrentContractDTO) itemId, session, screenName, "END_DATE");

                        }
                    });
                    return compEndDate;
                }
                if (propertyId.equals("contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat(Constants.MM_DD_YYYY);

                    contEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    contEndDate.setEnabled(false);

                    return contEndDate;
                }
                return null;
            }
        });

        currentTradingPartnerTable2.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = currentTradingPartnerTable2.getItemIds();
                for (Object obj : itemList) {
                    CurrentContractDTO dto = (CurrentContractDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                }
            }
        });

        currentTradingPartnerTable2.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
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
        LOGGER.debug(" Inside configureCompInfoTable method ");
        compInfoTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        compInfoTable.setSizeFull();
        compInfoTable.setHeight("270px");
        compInfoTable.setPageLength(NumericConstants.FIVE);
        compInfoTable.setContainerDataSource(componentInfoContainer);
        compInfoTable.setVisibleColumns(Constants.getInstance().ptpComponentInfoColumns);
        compInfoTable.setColumnHeaders(Constants.getInstance().ptpComponentInfoHeaders);
        LOGGER.debug("configureCompInfoTable method Ended");
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateRadio.getValue())) {
            fieldDdlb.setValue(null);
            massStartDate.setValue(null);
            massEndDate.setValue(null);
            massUpdateValue.setValue(null);
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.debug("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Current Contract Search Functionality
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(" Inside searchButtonLogic ");
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

    private void loadRARCategory() {
        logic.LazyLoadDdlb(rarCategory, "getRARTypeCount", "getRARType");
    }

    @UiHandler("resetBtn1")
    public void resetBtn1Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", " Are you sure you want to reset the Search values?\n ", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        tpSearchReset();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
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
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        currTpSearchResultsReset();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
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
     private ColumnCheckListener checkListener = new ColumnCheckListener() {
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
            currentTradingPartnerTable2.getContainerProperty(dto, Constants.CHECK_RECORD).setValue(checkValue);
        }
    }

    @UiHandler("excelBtn1")
    public void contractExport(Button.ClickEvent event) {
        try {
            contractExcelFlag = true;
            final int recordCount = currentTradingPartnerTable2.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet("Contract_Details", currentTradingPartnerTable2, recordCount);
            }
            currTradingPartnerTableLayout.removeComponent(contractExportPeriodViewTable);
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            contractExcelFlag = false;
        }
    }

    @UiHandler("excelBtn2")
    public void componentInfoExport(Button.ClickEvent event) {

        try {
            infoExcelFlag = true;
            final int recordCount = compInfoTable.getContainerDataSource().size();
            if (recordCount > 0) {
                createWorkSheet(excelName, compInfoTable, recordCount);
            }
            currTradingPartnerTableLayout.removeComponent(contractExportPeriodViewTable);
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            contractExcelFlag = false;
        }

    }

    /**
     * Submit Button Logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitButtonlogic(Button.ClickEvent event) {
        LOGGER.debug("Contract selection submitButtonlogic initiated");
        List<Boolean> startDateAndEndDate = logic.startDateAndEndDateValidation(session.getUserId(), session.getSessionId(), screenName);

        if (startDateAndEndDate.size() > 0 && startDateAndEndDate.get(0)) {
            AbstractNotificationUtils.getErrorNotification("No Record Selected", "Please select any record to submit");
            return;
        }
        if (TAB_CURRENT_CONTRACT.getConstant().equals(screenName) && startDateAndEndDate.size() > 1 && startDateAndEndDate.get(1)) {

            AbstractNotificationUtils.getErrorNotification("No End Date", "Please enter an end date for the records to be submitted");
            return;
        }
        setSummaryRefreshed(true);
        resetComponentInformationData();
        List<CurrentContractDTO> containerList = searchContainer.getItemIds();

        for (CurrentContractDTO dto : containerList) {
            if (dto.getCheckRecord()) {
                logic.updateSubmitFlag(screenName, session.getUserId(), session.getSessionId(), true, dto);
            }
        }

        AbstractNotificationUtils.getAlertNotification("Submit Details", "Selected Contract Holder has been submitted successfully.");
        LOGGER.debug("Contract selection submitButtonlogic ends ");
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
            AbstractNotificationUtils.getErrorNotification(Constants.MASS_UPDATE_ERROR, "Please select at least one contract to apply the Mass Update to.");
            return;
        }
        if (Constants.NULL.equals(fieldValue)) {
            AbstractNotificationUtils.getAlertNotification(Constants.MASS_UPDATE_ERROR, "Please select a Field to Mass Update.");
            return;
        } else if ("Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(massEndDate.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(massEndDate.getValue()))) {
                AbstractNotificationUtils.getAlertNotification(Constants.MASS_UPDATE_ERROR, "Please enter an End Date to Mass Update.");
                return;
            }
            logic.massUpdate(String.valueOf(fieldDdlb.getValue()), session.getUserId(), session.getSessionId(), screenName, massEndDate.getValue());
        }
        List<CurrentContractDTO> containerList = searchContainer.getItemIds();

        for (CurrentContractDTO dto : containerList) {
            if (dto.getCheckRecord() && "Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
                currentTradingPartnerTable2.getContainerProperty(dto, "companyEndDate").setValue(massEndDate.getValue());
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

        final TPContractHolderLookUp contractHolderLookUpWindow = new TPContractHolderLookUp("Contract Holder", contractHolder);
        contractHolderLookUpWindow.setWidth("1320px");
        contractHolderLookUpWindow.setHeight("830px");
        UI.getCurrent().addWindow(contractHolderLookUpWindow);
        contractHolderLookUpWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractHolderLookUpWindow.getSelectedChHolderHierarchy() != null) {
                    setCompanyMasters(contractHolderLookUpWindow.getFilteredCompanies());
                }
            }
        });
    }

    private void loadComponentInformation(String componentSelectionValue, Object selectedItem) {
        LOGGER.debug("Inside loadComponentInformation");
        if (selectedItem != null) {

            String[] id = new String[NumericConstants.FOUR];
            CurrentContractDTO crDTO = (CurrentContractDTO) selectedItem;
            id[0] = String.valueOf(crDTO.getCfpContSid());
            id[1] = String.valueOf(crDTO.getIfpContSid());
            id[NumericConstants.TWO] = String.valueOf(crDTO.getPsContSid());
            id[NumericConstants.THREE] = String.valueOf(crDTO.getRsContSid());
            if (!componentSelectionValue.equals(Constants.NULL) && !SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                changeOnListener(componentSelectionValue);
                Map<String, List> componentInformationMap = logic.getComponentInformation(componentSelectionValue, id);
                loadComponentInformationFields(componentInformationMap.get("FieldData"));
                loadComponentInformationTable(componentSelectionValue, componentInformationMap.get("TableData"));
                componentInformation.addAll(componentInformationMap.get("TableData"));
            } else {
                resetComponentInformationData();
            }
        }
        LOGGER.debug("Exiting loadComponentInformation");
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

    private void loadComponentInformationFields(List<String> fieldData) {
        if (fieldData != null && !fieldData.isEmpty()) {
            try {
                rebateId.setValue(fieldData.get(0));
                rsNumber.setValue(getFromList(fieldData, 1));
                rsName.setValue(getFromList(fieldData, NumericConstants.TWO));
                status.setValue(getFromList(fieldData, NumericConstants.THREE));
                componentEndDate.setValue(formatDate(getFromList(fieldData, NumericConstants.FOUR)));
                rarType.setValue(getFromList(fieldData, NumericConstants.FIVE));
                rebateFrequency.setValue(getFromList(fieldData, NumericConstants.SIX));
                basis.setValue(getFromList(fieldData, NumericConstants.SEVEN));
                String endDate = getFromList(fieldData, NumericConstants.EIGHT).trim();
                if (!endDate.equals(StringUtils.EMPTY)) {
                    componentStartDate.setValue(formatDate(getFromList(fieldData, NumericConstants.EIGHT)));
                }
            } catch (ParseException ex) {
                LOGGER.error("",ex);
            }
        }
    }

    private void loadComponentInformationTable(String componentSelectionValue, List<ComponentInfoDTO> tableData) {
        componentInfoContainer.removeAllItems();
        if (tableData != null && !tableData.isEmpty()) {
            if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsRs);
                compInfoTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersRs);
            } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsPs);
                compInfoTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersPs);
            } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                compInfoTable.setVisibleColumns(Constants.getInstance().tpComponentInformationColumnsIfp);
                compInfoTable.setColumnHeaders(Constants.getInstance().tpComponentInformationHeadersIfp);
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

    public ExtPagedTable getCurrentTradingPartnerTable2() {
        return currentTradingPartnerTable2;
    }

    public void setCurrentTradingPartnerTable2(ExtPagedTable currentTradingPartnerTable2) {
        this.currentTradingPartnerTable2 = currentTradingPartnerTable2;
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());
    }

    public void createWorkSheetContent(final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    int recordCount = currentTradingPartnerTable2.getContainerDataSource().size();
                    final List<CurrentContractDTO> searchList = logic.getSelectedTPContractResults(logic.getContractQuery(binderDto, session.getUserId(), session.getSessionId(), 0, recordCount, false));
                    Object[] columns = currentTradingPartnerTable2.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, Constants.CHECK_RECORD);
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                } else if (infoExcelFlag) {
                    final List<ComponentInfoDTO> searchList = componentInfoContainer.getItemIds();
                    ExcelExportforBB.createFileContent(compInfoTable.getVisibleColumns(), searchList, printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Promote Customer", "CurrentContractTab");
            resetBtn1.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn1", functionHM));
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            resetBtn2.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn2", functionHM));
            submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
            excelBtn1.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn1", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

	public CurrentContractDTO getCurrentContractDTO() {
		return currentContractDTO;
	}

	public void setCurrentContractDTO(CurrentContractDTO currentContractDTO) {
		this.currentContractDTO = currentContractDTO;
	}

	public boolean isSummaryRefreshed() {
		return summaryRefreshed;
	}

	public void setSummaryRefreshed(boolean summaryRefreshed) {
		this.summaryRefreshed = summaryRefreshed;
	}

	public List<CompanyMaster> getCompanyMasters() {
		return companyMasters;
	}

	public void setCompanyMasters(List<CompanyMaster> companyMasters) {
		this.companyMasters = companyMasters;
	}

	public List<CurrentContractDTO> getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(List<CurrentContractDTO> contractInfo) {
		this.contractInfo = contractInfo;
	}

	public static List<CurrentContractDTO> getSelectedContract() {
		return selectedContract;
	}

	public static void setSelectedContract(List<CurrentContractDTO> selectedContract) {
		CurrentContractSelection.selectedContract = selectedContract;
	}
}
