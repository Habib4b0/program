/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.discount.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.NewDiscountSelectedTableLogic;
import com.stpl.app.gcm.discount.logic.NewDiscountTableLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.MMddyyyy;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ENABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SELECT_ONE;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.RsDetails;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
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
 * @author santanukumar
 */
public class NewDiscountTab extends CustomComponent {

    public static final Logger LOGGER = Logger.getLogger(NewDiscountTab.class);
    NewDiscountTableLogic tableLogic = new NewDiscountTableLogic();
    NewDiscountSelectedTableLogic selectedTableLogic = new NewDiscountSelectedTableLogic();
    public ExtPagedTable componentDetailsSearchTable = new ExtPagedTable(tableLogic);
    public ExtPagedTable componentDetailsSelectedItem = new ExtPagedTable(selectedTableLogic);
    @UiField("levelDetailsResultsTable")
    public ExtFilterTable levelDetailsResultsTable;
    @UiField("dashboardResultsTable")
    public TreeTable dashboardTreeTable;
    @UiField("rsStartDate")
    public PopupDateField rsstartDate;
    @UiField("rsEndDate")
    public PopupDateField rsEndDate;
    @UiField("componentTypeddlb")
    public ComboBox componentTypeddlb;
    @UiField("searchField")
    public ComboBox searchFieldDdlb;
    @UiField("searchValue")
    public TextField searchValue;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("tableLayout")
    public VerticalLayout vLayout;
    @UiField("tableLayout1")
    public VerticalLayout vLayout1;
    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField("contractType")
    public TextField contractType;
    @UiField("startDate")
    public TextField startDate;
    @UiField("endDate")
    public TextField endDate;
    @UiField("rsStatusDdlb")
    public ComboBox rsStatusDdlb;
    @UiField("rsProgramType")
    public ComboBox rsProgramType;
    @UiField("rebatePlanLevel")
    public ComboBox rebatePlanLevel;
    @UiField("paymentFrequency")
    public ComboBox paymentFrequency;
    @UiField("paymentMethod")
    public ComboBox paymentMethod;
    @UiField("rsType")
    public ComboBox rsType;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateEnableDisable;
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    @UiField("valueDdlb")
    private ComboBox valueDdlb;
    @UiField("datePeriod")
    private PopupDateField datePeriod;
    @UiField("populateBtn")
    private Button populateBtn;

    /* Component Layout */
    @UiField("componentSelectionLayout")
    public HorizontalLayout componentSelectionLayout;
    @UiField("cfpComponent")
    public GridLayout cfpComponent;
    @UiField("ifpComponent")
    public GridLayout ifpComponent;
    @UiField("psComponent")
    public GridLayout psComponent;
    @UiField("rsComponent")
    public GridLayout rsComponent;
    SessionDTO session;
    @UiField("addToTree")
    public Button addToTree;
    @UiField("rsId")
    public TextField rsId;
    @UiField("rsNumber")
    public TextField rsNumber;
    @UiField("rsStartDate")
    public PopupDateField rsStartDate;
    @UiField("rsName")
    public TextField rsName;
    @UiField("fromCDLabelNo")
    public Label fromCDLabelNo;
    @UiField("fromCDNo")
    public TextField fromCDNo;
    @UiField("fromCDLabelName")
    public Label fromCDLabelName;
    @UiField("fromCDName")
    public TextField fromCDName;
    @UiField("levelRemoveBtn")
    public Button levelRemoveBtn;
    ContractsDetailsDto saveTreeDto;
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
    @UiField("removeBtn")
    public Button removeBtn;
    @UiField("psType")
    public ComboBox psType;
    @UiField("ifpType")
    public ComboBox ifpType;
    @UiField("cfpType")
    public ComboBox cfpType;
    @UiField("cfpStatus")
    public ComboBox cfpStatus;
    @UiField("cfpId")
    public TextField cfpId;
    @UiField("cfpNo")
    public TextField cfpNo;
    @UiField("cfpName")
    public TextField cfpName;
    @UiField("cfpStartDate")
    public PopupDateField cfpStartDate;
    @UiField("cfpEndDate")
    public PopupDateField cfpEndDate;
    @UiField("ifpEndDate")
    public PopupDateField ifpEndDate;
    @UiField("psEndDate")
    public PopupDateField psEndDate;
    @UiField("searchValueStatusDdlb")
    public ComboBox searchValueStatusDdlb;
    public List<ContractsDetailsDto> contListafterRemove = new ArrayList<>();
    private BeanItemContainer<ContractsDetailsDto> componentResultsContainer = new BeanItemContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    private BeanItemContainer<ContractsDetailsDto> selectedContainer = new BeanItemContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    private BeanItemContainer<ContractsDetailsDto> availableItemContainer = new BeanItemContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("MM-dd-yyyy");
    /* Current Level Value */
    public int levelValue;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /* Contains the parent items of an item in the hierarchy */
    public List parentList = new ArrayList();
    List<RemoveDiscountDto> removeDiscountDto;
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
    ContractsDetailsDto newDiscountTabDto = new ContractsDetailsDto();
    List<ContractsDetailsDto> selecteditemList = new ArrayList<ContractsDetailsDto>();
    List<ContractsDetailsDto> selectedResultList = new ArrayList<ContractsDetailsDto>();
    List<Integer> rebateList = new ArrayList<Integer>();
    List<HelperDTO> itemStatusList = new ArrayList<HelperDTO>();
    List<HelperDTO> priceTypeList = new ArrayList<HelperDTO>();
    String selectedComponenttype = StringUtils.EMPTY;
    List<String> newlyAddedRebates = new ArrayList<String>();
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();

    final List<ContractsDetailsDto> cfpList = new ArrayList<ContractsDetailsDto>();
    final List<ContractsDetailsDto> ifpList = new ArrayList<ContractsDetailsDto>();
    final List<ContractsDetailsDto> psList = new ArrayList<ContractsDetailsDto>();
    final List<ContractsDetailsDto> rsList = new ArrayList<ContractsDetailsDto>();
    final List<ContractsDetailsDto> selected = new ArrayList<ContractsDetailsDto>();
    int levelVal = 0;
    int contractMasterId = 0;
//    List<ContractsDetailsDto> cfpListforMap = new ArrayList<ContractsDetailsDto>();
    List<ContractsDetailsDto> ifpListforMap;
    List<ContractsDetailsDto> psListforMap;
    List<ContractsDetailsDto> rsListforMap;

    public NewDiscountTab(List<RemoveDiscountDto> removeDiscountDto, SessionDTO session) {
        this.removeDiscountDto = removeDiscountDto;
        dashBoardTreeContainer = new ExtTreeContainer<ContractsDetailsDto>(ContractsDetailsDto.class);
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/newDiscountTab.xml"), this));
        configureFields();
    }

    protected void configureFields() {
        try {
            startDate.addStyleName("v-align-center");
            endDate.addStyleName("v-align-center");

            rsstartDate.setDateFormat(Constants.MM_DD_YYYY);
            rsstartDate.setStyleName("dateFieldCenter");

            rsEndDate.setDateFormat(Constants.MM_DD_YYYY);
            rsEndDate.setStyleName("dateFieldCenter");

            datePeriod.setDateFormat(Constants.MM_DD_YYYY);
            datePeriod.setStyleName("dateFieldCenter");

            rsStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rsStartDate.setStyleName("dateFieldCenter");

            psStartDate.setDateFormat(Constants.MM_DD_YYYY);
            psStartDate.setStyleName("dateFieldCenter");

            ifpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpStartDate.setStyleName("dateFieldCenter");

            cfpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpStartDate.setStyleName("dateFieldCenter");

            cfpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpEndDate.setStyleName("dateFieldCenter");

            ifpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpEndDate.setStyleName("dateFieldCenter");

            psEndDate.setDateFormat(Constants.MM_DD_YYYY);
            psEndDate.setStyleName("dateFieldCenter");

            contractNo.setValue(removeDiscountDto.get(0).getContractNo());
            contractName.setValue(removeDiscountDto.get(0).getContractName());
            contractType.setValue(removeDiscountDto.get(0).getMarketType());
            startDate.setValue(removeDiscountDto.get(0).getContractstartDate() == null ? StringUtils.EMPTY : DBDate.format((Date) removeDiscountDto.get(0).getContractstartDate()));
            endDate.setValue(removeDiscountDto.get(0).getContractendDate() == null ? StringUtils.EMPTY : DBDate.format((Date) removeDiscountDto.get(0).getContractendDate()));
            isEnable(false);
            componentTypeddlb = CommonLogic.loadComponentType(componentTypeddlb, null, true);
            searchFieldDdlb.setImmediate(true);
            searchFieldDdlb = CommonLogic.loadNewTabSearchDdlb(searchFieldDdlb, selectedComponenttype);
            commonUtil.loadComboBox(rsStatusDdlb, UIUtils.STATUS, false);
            commonUtil.loadComboBox(rsProgramType, UIUtils.REBATE_PROGRAM_TYPE, false);
            commonUtil.loadComboBox(rebatePlanLevel, UIUtils.REBATE_PLAN_LEVEL, false);
            commonUtil.loadComboBox(paymentFrequency, UIUtils.PAYMENT_FREQUENCY, false);
            commonUtil.loadComboBox(paymentMethod, UIUtils.PAYMENT_METHOD, false);
            commonUtil.loadComboBox(rsType, UIUtils.RS_TYPE, false);
            configureTables();
            LoadDashBoardTree();
            for (RemoveDiscountDto remove : removeDiscountDto) {
                rebateList.add(remove.getRsSid());
            }
            massUpdateEnableDisable.addItem(ENABLE.getConstant());
            massUpdateEnableDisable.addItem(DISABLE.getConstant());
            massUpdateEnableDisable.select(DISABLE.getConstant());

            fieldDdlb.setImmediate(true);
            fieldDdlb.setNullSelectionAllowed(false);
            fieldDdlb.setInputPrompt(SELECT_ONE.getConstant());
            fieldDdlb.addItem("Status");
            fieldDdlb.addItem("Start Date");
            fieldDdlb.addItem("End Date");
            valueDdlb.setImmediate(true);
            valueDdlb.setNullSelectionAllowed(false);
            valueDdlb.setInputPrompt(SELECT_ONE.getConstant());
            valueDdlb.setVisible(true);
            datePeriod.setVisible(false);
            loadcomponentSelectionGrid();
            itemStatusList = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
            fromCDNo.setEnabled(false);
            fromCDName.setEnabled(false);
            searchValueStatusDdlb.setImmediate(true);
            searchValueStatusDdlb.setVisible(false);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    protected void configureTables() {
        addResultTable();
        addSelectedResultTable();
        tableLogic.setContainerDataSource(availableItemContainer);
        tableLogic.setPageLength(5);
        tableLogic.sinkItemPerPageWithPageLength(false);

        selectedTableLogic.setContainerDataSource(selectedContainer);
        selectedTableLogic.setPageLength(5);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);

        componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSearchTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
        componentDetailsSearchTable.setHeight(80, Sizeable.Unit.PERCENTAGE);
        componentDetailsSearchTable.setEditable(Boolean.TRUE);
        componentDetailsSearchTable.setFilterBarVisible(Boolean.FALSE);
        componentDetailsSearchTable.setVisibleColumns(Constants.ITEM_SEARCH_RESULTS_COLUMNS);
        componentDetailsSearchTable.setColumnHeaders(Constants.ITEM_SEARCH_RESULTS_HEADERS);
        componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) itemId;
                            boolean isCheck = check.getValue();
                            dto.setCheckRecord(isCheck);
                            dto.setCheckAll(false);
                            if (!isCheck) {
                                componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK_RECORD, true, isCheck);
                            }
                            DiscountLogic.updateTempTableRecord(dto, session, Constants.CHECK_RECORD, true);
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        componentDetailsSearchTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = componentDetailsSearchTable.getItemIds();
                int size = itemList.size();
                if (size > 0) {
                    newDiscountTabDto.setCheckAll(true);
                    newDiscountTabDto.setCheckRecord(event.isChecked());
                    DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, Constants.CHECK_RECORD, true);

                    for (Object obj : itemList) {
                        ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                        dto.setCheckRecord(event.isChecked());
                        componentDetailsSearchTable.getContainerProperty(obj, Constants.CHECK_RECORD).setValue(event.isChecked());
                    }
                }
            }
        });
        componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSelectedItem.setWidth(100, Sizeable.Unit.PERCENTAGE);
        componentDetailsSelectedItem.setHeight(100, Sizeable.Unit.PERCENTAGE);
        componentDetailsSelectedItem.setVisibleColumns(Constants.SELECTED_RESULTS_COLUMNS);
        componentDetailsSelectedItem.setColumnHeaders(Constants.SELECTED_RESULTS_HEADERS);
        componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, Boolean.TRUE);
        componentDetailsSelectedItem.setEditable(Boolean.TRUE);
        componentDetailsSelectedItem.setFilterBarVisible(Boolean.TRUE);
        componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                final ContractsDetailsDto dto = (ContractsDetailsDto) itemId;
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            dto.setCheckRecord(isCheck);
                            dto.setCheckAll(false);
                            if (!isCheck) {
                                componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK_RECORD, true, isCheck);
                                contListafterRemove.add(dto);
                            } else {
                                if (contListafterRemove.contains(dto)) {
                                    contListafterRemove.remove(dto);
                            }
                            }
                            dto.setCheckAll(false);
                            dto.setBulkUpdate(false);
                            int compId = dto.getInternalId();
                            dto.setInternalId(dto.getSystemId());
                            dto.setSystemId(compId);

                        }
                    });
                    return check;
                }
                if (String.valueOf(propertyId).equals("status")) {
                    final ComboBox status = new ComboBox();
                    status.setImmediate(true);
                    status.setNullSelectionAllowed(false);
                    status.setInputPrompt(SELECT_ONE.getConstant());
                    try {
                        commonUtil.loadComboBox(status, UIUtils.STATUS, false);

                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }

                    status.addBlurListener(new BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(((ComboBox) event.getComponent()).getValue());
                            String oldValue = dto.getTempStatus();
                            if (!oldValue.equals(newValue)) {
                                dto.setStatus(newValue);
                                dto.setTempStatus(newValue);
                                DiscountLogic.updateTempTableRecord(dto, session, "status", false);
                            }
                        }
                    });
                    return status;

                };
                if (propertyId.equals("sDate")) {
                    final PopupDateField itemStartDate = new PopupDateField();
                    itemStartDate.setWidth("100%");
                    itemStartDate.setImmediate(true);
                    itemStartDate.setDateFormat(MMddyyyy.getConstant());
                    itemStartDate.setStyleName("dateFieldCenter");
                    itemStartDate.addBlurListener(new BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempSDate());
                            if (!oldValue.equals(newValue)) {
                                dto.setsDate(dt1);
                                dto.setTempSDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, "sDate", false);
                            }
                        }
                    });
                    return itemStartDate;
                }
                if (propertyId.equals("eDate")) {
                    final PopupDateField itemEndDate = new PopupDateField();
                    itemEndDate.setWidth("100%");
                    itemEndDate.setImmediate(true);
                    itemEndDate.setDateFormat(MMddyyyy.getConstant());
                    itemEndDate.setStyleName("dateFieldCenter");
                    itemEndDate.addBlurListener(new BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempEDate());
                            if (!oldValue.equals(newValue)) {
                                dto.seteDate(dt1);
                                dto.setTempEDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, "eDate", false);
                            }
                        }
                    });
                    return itemEndDate;
                }
                if (propertyId.equals("rebatePlan")) {
                    final CustomTextField rebatePlan = new CustomTextField();
                    rebatePlan.setWidth("100%");
                    rebatePlan.addStyleName("searchicon");
                    rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            RebatePlanLookup lookup = new RebatePlanLookup(rebatePlan);
                            lookup.rebatePlanId.focus();
                            lookup.addCloseListener(new Window.CloseListener() {

                                public void windowClose(Window.CloseEvent e) {
                                    if (rebatePlan.getData() != null) {
                                        String newValue = String.valueOf(rebatePlan.getData());
                                        String oldValue = dto.getTempRebatePlan();
                                        if (!oldValue.equals(newValue)) {
                                            dto.setRebatePlan(newValue);
                                            dto.setTempRebatePlan(newValue);
                                            dto.setRebatePlanName(newValue);
                                            DiscountLogic.updateTempTableRecord(dto, session, "rebatePlan", false);
                                        }
                                    }
                                }
                            });
                            UI.getCurrent().addWindow(lookup);
                        }
                    });
                    return rebatePlan;
                }
                if (propertyId.equals("formulaName")) {
                    final CustomTextField formulaId = new CustomTextField();
                    formulaId.setWidth("100%");
                    formulaId.addStyleName("searchicon");
                    formulaId.addClickListener(new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            FormulaSearchLookup lookup = new FormulaSearchLookup(formulaId);
                            lookup.formulaId.focus();
                            lookup.addCloseListener(new Window.CloseListener() {
                                public void windowClose(Window.CloseEvent e) {
                                    if (formulaId.getData() != null) {
                                        String newValue = String.valueOf(formulaId.getData());
                                        String oldValue = dto.getTempFormulaId();
                                        if (!oldValue.equals(newValue)) {
                                            dto.setFormulaId(newValue);
                                            dto.setTempFormulaId(newValue);
                                            DiscountLogic.updateTempTableRecord(dto, session, "formulaName", false);
                                        }
                                    }
                                }
                            });
                            UI.getCurrent().addWindow(lookup);

                        }
                    });

                    return formulaId;
                }
                if (String.valueOf(propertyId).equals("priceType")) {
                    final ComboBox priceType = new ComboBox();
                    priceType.setImmediate(true);
                    priceType.setNullSelectionAllowed(false);
                    priceType.setInputPrompt(SELECT_ONE.getConstant());
                    try {
                        CommonLogic.getNativeSelect(priceType, priceTypeList);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }

                    priceType.addBlurListener(new BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(((ComboBox) event.getComponent()).getValue());
                            String oldValue = dto.getTempPriceType();
                            if (!oldValue.equals(newValue)) {
                                dto.setPriceType(newValue);
                                dto.setTempPriceType(newValue);
                                DiscountLogic.updateTempTableRecord(dto, session, "priceType", false);
                            }
                        }
                    });
                    return priceType;
                }
                if (propertyId.equals("ppSDate")) {
                    final PopupDateField ppStartDate = new PopupDateField();
                    ppStartDate.setWidth("100%");
                    ppStartDate.setImmediate(true);
                    ppStartDate.setDateFormat(MMddyyyy.getConstant());
                    ppStartDate.setStyleName("dateFieldCenter");
                    ppStartDate.addBlurListener(new BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempPpSDate());
                            if (!oldValue.equals(newValue)) {
                                dto.setPpSDate(dt1);
                                dto.setTempPpSDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, "ppSDate", false);
                            }
                        }
                    });
                    return ppStartDate;
                }

                return null;
            }
        });
        componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = componentDetailsSelectedItem.getItemIds();
                int size = itemList.size();
                if (size > 0) {
                    newDiscountTabDto.setCheckAll(true);
                    newDiscountTabDto.setCheckRecord(event.isChecked());
                    newDiscountTabDto.setCheckAll(false);
                    newDiscountTabDto.setBulkUpdate(false);
                    int compId = newDiscountTabDto.getInternalId();
                    newDiscountTabDto.setInternalId(newDiscountTabDto.getSystemId());
                    newDiscountTabDto.setSystemId(compId);
                    DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, Constants.CHECK_RECORD,true);

                    for (Object obj : itemList) {
                        ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                        dto.setCheckRecord(event.isChecked());
                        componentDetailsSelectedItem.getContainerProperty(obj, Constants.CHECK_RECORD).setValue(event.isChecked());
                    }
                }
            }
        });
        for (Object propertyId : componentDetailsSelectedItem.getVisibleColumns()) {
            componentDetailsSelectedItem.setColumnWidth(propertyId, 125);
        }

        dashboardTreeTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
        dashboardTreeTable.setPageLength(10);
        dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
        dashboardTreeTable.setVisibleColumns(Constants.TREE_COLUMNS);
        dashboardTreeTable.setColumnHeaders(Constants.TREE_HEADERS);
        levelDetailsResultsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);
        levelDetailsResultsTable.setPageLength(11);
        levelDetailsResultsTable.setContainerDataSource(componentResultsContainer);
        levelDetailsResultsTable.setVisibleColumns(Constants.CONTRACT_COMPONENT_DETAILS_RESULTS_COLUMNS);
        levelDetailsResultsTable.setColumnHeaders(Constants.CONTRACT_COMPONENT_DETAILS_RESULTS_HEADERS);

    }

    private void LoadDashBoardTree() throws SystemException {
        LOGGER.info("Entering getProcessedTree method");
        final CommonLogic commonLogic = new CommonLogic();
        dashboardTreeTable.markAsDirty();
        dashboardTreeTable.setImmediate(true);
        dashboardTreeTable.setSizeFull();
        dashboardTreeTable.setPageLength(10);
        dashboardTreeTable.removeAllItems();
        parentList.clear();
        levelValue = 0;
        contractMasterId = removeDiscountDto.get(0).getContractSid();

        dashBoardTreeContainer = commonLogic.getLevel1Hierarchy(removeDiscountDto.get(0).getContractNo(), dashBoardTreeContainer, null);
        dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);

        setProcessedTableHeader();

        dashboardTreeTable.addExpandListener(expandListener);
//        dashboardTreeTable.addCollapseListener(collapseListener);
        dashboardTreeTable.setSelectable(true);
        dashboardTreeTable.setColumnHeaders(new String[]{"Category", "ID", "Number", "Name"});
        dashboardTreeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
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
                levelVal = tableBean.getLevel();
            }
        });
        LOGGER.info("End of getProcessedTree method");
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
        public void nodeExpand(final Tree.ExpandEvent event) {
            try {
                LOGGER.info("Entering StplExpandListener nodeExpand method");

                contractDetails = (ContractsDetailsDto) event.getItemId();
                if (dashBoardTreeContainer.hasChildren(event.getItemId())) {
                    Collection<?> contractList = dashBoardTreeContainer.getChildren(event.getItemId());
                    for (Iterator<?> iterator = contractList.iterator(); iterator.hasNext();) {
                        Object contractMember = iterator.next();
                        dashboardTreeTable.setCollapsed(contractMember, true);
                    }
                } else {
                    contractDetails.setRebateList(rebateList);
                    switch (contractDetails.getLevel()) {
                        case ContractsDetailsDto.LEVEL1:
                            configureLevel(event.getItemId());
                            int i = commonLogic.getCFPQueriedCount(contractDetails.getSystemId());

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
                }
                LOGGER.info("End of StplExpandListener nodeExpand method");
            } catch (SystemException ex) {
                LOGGER.error(ex.getMessage());;
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            } catch (PortalException ex) {
                LOGGER.error(ex.getMessage());
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
        public void nodeCollapse(final Tree.CollapseEvent event) {
            try {
                LOGGER.info("Entering StplCollapseListener nodeCollapse method");

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
                        levelValue = 2;
                        dashboardTreeTable.setContainerDataSource(commonLogic.getLevel3Hierarchy(contractDetails.getParent2(), dashBoardTreeContainer, ifpList, psList, rsList));
                        dashboardTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        dashboardTreeTable.setCollapsed(contractDetails.getParent1(), false);
                        dashboardTreeTable.setCollapsed(contractDetails.getParent2(), false);
                        dashboardTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractsDetailsDto.LEVEL4:
                        levelValue = 3;
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
                LOGGER.info("End of StplCollapseListener nodeCollapse method");
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            } catch (PortalException ex) {
                LOGGER.error(ex.getMessage());
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

    private void setProcessedTableHeader() {
        LOGGER.info("Entering setProcessedTableHeader method");
        dashboardTreeTable.setVisibleColumns(Constants.TREE_COLUMNS);
        dashboardTreeTable.setColumnHeaders(Constants.TREE_HEADERS);
        LOGGER.info("End of setProcessedTableHeader method");
    }

    private void itemOnClickEvent(ContractsDetailsDto tableBean) {
        componentResultsContainer.removeAllItems();
        List<ContractsDetailsDto> levelDetails = new DiscountLogic().getLevelDetails(tableBean);
        componentResultsContainer.addAll(levelDetails);
    }

    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered search method");
        String compType = String.valueOf(componentTypeddlb.getValue());
        String searchField = String.valueOf(searchFieldDdlb.getValue() != null ? searchFieldDdlb.getValue() : StringUtils.EMPTY);
        String sValue = searchValue.getValue();
        String ddlbValue = searchValueStatusDdlb.getValue() == null || searchValueStatusDdlb.getValue().equals(Constants.ZEROSTRING) ? StringUtils.EMPTY : String.valueOf(searchValueStatusDdlb.getValue());

        if (StringUtils.isNotBlank(searchField) && ((StringUtils.isNotBlank(sValue)) || (StringUtils.isNotBlank(ddlbValue)))) {
            newDiscountTabDto.setSearchField(compType + "-" + searchField);
            if (searchValue.isVisible()) {
                newDiscountTabDto.setSearchFieldValue(searchValue.getValue());
            } else {
                newDiscountTabDto.setSearchFieldValue(ddlbValue);
            }

            if (session.getSearchSessionId().isEmpty()) {
                session.setSearchSessionId(CommonUtils.createSessionId());
                if ((selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant()))) {
                    newDiscountTabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                    DiscountLogic.insertToTempTable(newDiscountTabDto, session);
                } else if ((selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.getConstant()))) {
                    newDiscountTabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                    DiscountLogic.insertToTempTable(newDiscountTabDto, session);
                }
            }
            if (!tableLogic.loadSetData(newDiscountTabDto, session, false)) {
                AbstractNotificationUtils.getErrorNotification("No Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Search",
                    "Please enter a Search Value.");
        }
        LOGGER.info("Ending search method");
    }

    @UiHandler("populateBtn")
    public void populateBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered populate method");
        String massField = String.valueOf(fieldDdlb.getValue());
        String propertyId = StringUtils.EMPTY;
        newDiscountTabDto.setBulkUpdate(true);
        if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true, true) > 0) {
            if (massField != null && !massField.equals(Constants.NULL) && !massField.equals(StringUtils.EMPTY)) {
                if (massField.equals("Status")) {
                    propertyId = "status";
                    String value = String.valueOf(valueDdlb.getValue());
                    if (value != null && !value.equals(Constants.NULL) && !value.equals(StringUtils.EMPTY)) {
                        newDiscountTabDto.setStatus(value);
                        Collection itemList = componentDetailsSelectedItem.getItemIds();
                        for (Object obj : itemList) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                            if (dto.getCheckRecord()) {
                                dto.setStatus(value);
                                dto.setTempStatus(value);
                                componentDetailsSelectedItem.getContainerProperty(obj, propertyId).setValue(value);
                            }
                        }
                        DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, propertyId, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Value",
                                "No value entered for mass populate.");
                    }
                } else if (massField.equalsIgnoreCase("Start Date")) {
                    propertyId = "sDate";
                    String value = String.valueOf(datePeriod.getValue());
                    if (value != null && !value.equals(Constants.NULL) && !value.equals(StringUtils.EMPTY)) {
                        newDiscountTabDto.setsDate(datePeriod.getValue());
                        Collection itemList = componentDetailsSelectedItem.getItemIds();
                        for (Object obj : itemList) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                            if (dto.getCheckRecord()) {
                                dto.setsDate(datePeriod.getValue());
                                dto.setTempSDate(datePeriod.getValue());
                                componentDetailsSelectedItem.getContainerProperty(obj, propertyId).setValue(datePeriod.getValue());
                            }
                        }
                        DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, propertyId, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Value",
                                "No value entered for mass populate.");
                    }

                } else if (massField.contains("End Date")) {
                    propertyId = "eDate";
                    String value = String.valueOf(datePeriod.getValue());
                    if (value != null && !value.equals(Constants.NULL) && !value.equals(StringUtils.EMPTY)) {
                        newDiscountTabDto.seteDate(datePeriod.getValue());
                        Collection itemList = componentDetailsSelectedItem.getItemIds();
                        for (Object obj : itemList) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                            if (dto.getCheckRecord()) {
                                dto.seteDate(datePeriod.getValue());
                                dto.setTempEDate(datePeriod.getValue());
                                componentDetailsSelectedItem.getContainerProperty(obj, propertyId).setValue(datePeriod.getValue());
                            }
                        }
                        DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, propertyId, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Value",
                                "No value entered for mass populate.");
                    }

                } else if (massField.contains("Price Type")) {
                    propertyId = "priceType";
                    String value = String.valueOf(valueDdlb.getValue());
                    if (value != null && !value.equals(Constants.NULL) && !value.equals(StringUtils.EMPTY)) {
                        newDiscountTabDto.setPriceType(value);
                        Collection itemList = componentDetailsSelectedItem.getItemIds();
                        for (Object obj : itemList) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                            if (dto.getCheckRecord()) {
                                dto.setPriceType(value);
                                dto.setTempPriceType(value);
                                componentDetailsSelectedItem.getContainerProperty(obj, propertyId).setValue(value);
                            }
                        }
                        DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, propertyId, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Value",
                                "No value entered for mass populate.");
                    }
                } else if (massField.equalsIgnoreCase("Price Protection Start Date")) {
                    propertyId = "ppSDate";
                    String value = String.valueOf(datePeriod.getValue());
                    if (value != null && !value.equals(Constants.NULL) && !value.equals(StringUtils.EMPTY)) {
                        newDiscountTabDto.setPpSDate(datePeriod.getValue());
                        Collection itemList = componentDetailsSelectedItem.getItemIds();
                        for (Object obj : itemList) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                            if (dto.getCheckRecord()) {
                                dto.setPpSDate(datePeriod.getValue());
                                dto.setTempPpSDate(datePeriod.getValue());
                                componentDetailsSelectedItem.getContainerProperty(obj, propertyId).setValue(datePeriod.getValue());
                            }
                        }
                        DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, propertyId, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Value",
                                "No value entered for mass populate.");
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Field",
                        "No Field selected for mass populate.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Records Selected",
                    "There were no records Selected for mass populate.  Please Select Record.");
        }

//        newDiscountTabDto.setCheckRecordCount(false);
        newDiscountTabDto.setBulkUpdate(false);
        LOGGER.info("Ending populate method");
    }

    private void addResultTable() {
        vLayout.addComponent(componentDetailsSearchTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
        vLayout.addComponent(controls);
    }

    private void addSelectedResultTable() {
        vLayout1.addComponent(componentDetailsSelectedItem);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls());
        vLayout1.addComponent(controls);
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     */
    @UiHandler("addBtn")
    public void addItemsButtonClick(Button.ClickEvent event) {
        LOGGER.info("Entering addItemsButtonClick method ");
        ContractsDetailsDto newDiscountDto = null;
        boolean selected = false;
        if (componentDetailsSearchTable.isSelectable()) {
            Object itemId = componentDetailsSearchTable.getValue();
            if (itemId != null) {
                selected = true;
                newDiscountDto = (ContractsDetailsDto) itemId;
                newDiscountTabDto.setCategory(newDiscountDto.getCategory());
//                session.setSearchSessionId(CommonUtils.createSessionId());
                DiscountLogic.insertToTempTable(newDiscountDto, session);
                if (!selectedTableLogic.loadSetData(newDiscountDto, session, false)) {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, Boolean.TRUE);
                    AbstractNotificationUtils.getErrorNotification("No Records Found",
                            "Please select other record.");
                } else {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, Boolean.FALSE);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Add", "Please check a record to Add.");
            }
        } else {
            if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
            } else {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
            }

            new DiscountLogic().updateDataOperation(newDiscountTabDto.getCategory(), session, true);
            if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true, false) > 0) {
                if (!selectedTableLogic.loadSetData(newDiscountTabDto, session, false)) {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, Boolean.TRUE);
                    AbstractNotificationUtils.getErrorNotification("No Records Found",
                            "Please select other record.");
                } else {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, Boolean.FALSE);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Add", "Please check a record to Add.");
            }
        }
        LOGGER.info("addItemsButtonClick method Ended");

    }

    @UiHandler("componentTypeddlb")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) throws Exception {
        String oldValue = selectedComponenttype;
        boolean load = true;
        Object id = componentTypeddlb.getValue();
        if (id != null) {
            String compType = String.valueOf(id);
            selectedComponenttype = compType;
            if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString()) || compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                if (oldValue.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString()) || oldValue.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                    load = false;
                }
                selectedComponenttype = compType;
            } else if (!compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString()) && !compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                selectedComponenttype = StringUtils.EMPTY;
            }
        }
        if (load) {
            searchFieldDdlb = CommonLogic.loadNewTabSearchDdlb(searchFieldDdlb, selectedComponenttype);
        }
        newDiscountTabDto.setSearchSessionId(StringUtils.EMPTY);
        selectedTableLogic.resetAndLoadData(true);
        tableLogic.resetAndLoadData(true);
        loadTableHeaders();
        loadcomponentSelectionGrid();
    }

    private void loadTableHeaders() {
        if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setSelectable(false);
            componentDetailsSearchTable.setVisibleColumns(Constants.AD_SEARCH_RESULTS_COLUMNS_CFP);
            componentDetailsSearchTable.setColumnHeaders(Constants.AD_SEARCH_RESULTS_HEADERS_CFP);
            componentDetailsSelectedItem.setVisibleColumns(Constants.AD_SELECTED_RESULTS_COLUMNS_CFP);
            componentDetailsSelectedItem.setColumnHeaders(Constants.AD_SELECTED_RESULTS_HEADERS_CFP);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setSelectable(false);
            componentDetailsSearchTable.setVisibleColumns(Constants.AD_SEARCH_RESULTS_COLUMNS_IFP);
            componentDetailsSearchTable.setColumnHeaders(Constants.AD_SEARCH_RESULTS_HEADERS_IFP);
            componentDetailsSelectedItem.setVisibleColumns(Constants.AD_SELECTED_RESULTS_COLUMNS_IFP);
            componentDetailsSelectedItem.setColumnHeaders(Constants.AD_SELECTED_RESULTS_HEADERS_IFP);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            componentDetailsSearchTable.setEditable(false);
            componentDetailsSearchTable.setSelectable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.AD_IFP_RESULTS_COLUMNS);
            componentDetailsSearchTable.setColumnHeaders(Constants.AD_IFP_RESULTS_HEADERS);
            componentDetailsSelectedItem.setVisibleColumns(Constants.AD_ND_PS_COMPONENT_DETAILS_COLUMNS);
            componentDetailsSelectedItem.setColumnHeaders(Constants.AD_ND_PS_COMPONENT_DETAILS_HEADERS);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            componentDetailsSearchTable.setEditable(false);
            componentDetailsSearchTable.setSelectable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.AD_IFP_RESULTS_COLUMNS);
            componentDetailsSearchTable.setColumnHeaders(Constants.AD_IFP_RESULTS_HEADERS);
            componentDetailsSelectedItem.setVisibleColumns(Constants.AD_ND_RS_COMPONENT_DETAILS_COLUMNS);
            componentDetailsSelectedItem.setColumnHeaders(Constants.AD_ND_RS_COMPONENT_DETAILS_HEADERS);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        }
    }

    private void loadCfpFromResults(final ContractsDetailsDto parent) {
        componentDetailsSelectedItem.setVisibleColumns(Constants.AD_COMPONENT_DETAILS_COLUMNS_CFP);
        componentDetailsSelectedItem.setColumnHeaders(Constants.AD_COMPONENT_DETAILS_HEADERS_CFP);
    }

    public void isEnable(boolean value) {
        contractNo.setEnabled(value);
        contractName.setEnabled(value);
        contractType.setEnabled(value);
        startDate.setEnabled(value);
        endDate.setEnabled(value);
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.info(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateEnableDisable.getValue())) {
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
    private void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        valueDdlb.setEnabled(value);
        populateBtn.setEnabled(value);
        datePeriod.setEnabled(value);
    }

    @UiHandler("fieldDdlb")
    public void fieldDdlbValueChange(Property.ValueChangeEvent event) {
        LOGGER.info(" FieldDdlb ValueChangeEvent initiated ");
        datePeriod.setValue(null);
        if ("Status".equals(String.valueOf(fieldDdlb.getValue())) || "Price Type".equals(String.valueOf(fieldDdlb.getValue()))) {
            datePeriod.setVisible(false);
            valueDdlb.setVisible(true);
            if ("Status".equals(String.valueOf(fieldDdlb.getValue()))) {
                try {
                    valueDdlb.removeAllItems();
                    valueDdlb = CommonLogic.getNativeSelect(valueDdlb, itemStatusList);
                } catch (Exception ex) {
                }
            }
            if ("Price Type".equals(String.valueOf(fieldDdlb.getValue()))) {
                try {
                    valueDdlb.removeAllItems();
                    valueDdlb = CommonLogic.getNativeSelect(valueDdlb, priceTypeList);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

        } else {
            valueDdlb.setVisible(false);
            datePeriod.setVisible(true);
        }

        LOGGER.info("FieldDdlb ValueChangeEvent ends ");
    }

    private void loadcomponentSelectionGrid() throws SystemException, Exception {
        componentSelectionLayout.removeAllComponents();
        if (componentTypeddlb.getValue() != null) {
            if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                componentSelectionLayout.addComponent(cfpComponent);
                cfpType = commonUtil.loadComboBox(cfpType, UIUtils.CFP_TYPE, false);
                cfpStatus = CommonLogic.getNativeSelect(cfpStatus, itemStatusList);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem("Status");
                fieldDdlb.addItem("Start Date");
                fieldDdlb.addItem("End Date");
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                componentSelectionLayout.addComponent(ifpComponent);
                ifpStatus = CommonLogic.getNativeSelect(ifpStatus, itemStatusList);
                ifpType = commonUtil.loadComboBox(ifpType, UIUtils.IFP_TYPE, false);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem("Status");
                fieldDdlb.addItem("Start Date");
                fieldDdlb.addItem("End Date");
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                componentSelectionLayout.addComponent(psComponent);
                priceTypeList = CommonLogic.getPriceTypeResults();
                psStatus = CommonLogic.getNativeSelect(psStatus, itemStatusList);
                psType = commonUtil.loadComboBox(psType, UIUtils.PS_TYPE, false);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem("Status");
                fieldDdlb.addItem("Start Date");
                fieldDdlb.addItem("End Date");
                fieldDdlb.addItem("Price Type");
                fieldDdlb.addItem("Price Protection Start Date");
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                componentSelectionLayout.addComponent(rsComponent);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem("Status");
                fieldDdlb.addItem("Start Date");
                fieldDdlb.addItem("End Date");
            }
        }
    }

    @UiHandler("addToTree")
    public void addToTreeLogic(Button.ClickEvent event) throws SystemException {
        try {
            Object id = componentTypeddlb.getValue();
            boolean addedFlag = false;
            if (id != null) {
                String compType = String.valueOf(id);
                if (compType.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                    String rebateName = rsName.getValue();
                    String rebateId = rsId.getValue();
                    String rebateNo = rsNumber.getValue();
                    String pFrequency = String.valueOf(paymentFrequency.getValue() != null ? paymentFrequency.getValue() : StringUtils.EMPTY);
                    String rebateStatus = String.valueOf(rsStatusDdlb.getValue() != null ? rsStatusDdlb.getValue() : StringUtils.EMPTY);
                    String rebateStartdate = rsStartDate.getValue() != null ? rsStartDate.getValue().toString() : StringUtils.EMPTY;
                    String rebateEnddate = rsEndDate.getValue() != null ? rsStartDate.getValue().toString() : StringUtils.EMPTY;
                    String pMethod = String.valueOf(paymentMethod.getValue() != null ? paymentMethod.getValue() : StringUtils.EMPTY);
                    String rebateType = String.valueOf(rsType.getValue() != null ? rsType.getValue() : StringUtils.EMPTY);
                    String rebateProgType = String.valueOf(rsProgramType.getValue() != null ? rsProgramType.getValue() : StringUtils.EMPTY);
                    String rebatePlan = String.valueOf(rebatePlanLevel.getValue() != null ? rebatePlanLevel.getValue() : StringUtils.EMPTY);
                    if (StringUtils.isNotBlank(rebateName) && StringUtils.isNotBlank(rebateId) && StringUtils.isNotBlank(rebateNo)
                            && StringUtils.isNotBlank(pFrequency) && StringUtils.isNotBlank(rebateStatus) && StringUtils.isNotBlank(rebateStartdate)
                            && StringUtils.isNotBlank(rebateEnddate) && StringUtils.isNotBlank(pMethod) && StringUtils.isNotBlank(rebateType)
                            && StringUtils.isNotBlank(rebateProgType) && StringUtils.isNotBlank(rebatePlan)) {
                        List list = DiscountLogic.duplicateCheck(compType, rebateId, "RS_ID");
                        if (list != null && list.size() > 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule ID is already exist. Please enter different Rebate Schedule ID");
                            return;
                        }
                        List listNo = DiscountLogic.duplicateCheck(compType, rebateNo, "RS_NO");
                        if (listNo != null && listNo.size() > 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule No already exists. Please enter different Rebate Schedule No");
                            return;
                        }
                        List listName = DiscountLogic.duplicateCheck(compType, rebateName, "RS_NAME");
                        if (listName != null && listName.size() > 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule Name already exists. Please enter different Rebate Schedule Name");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        rsListforMap = new ArrayList<ContractsDetailsDto>();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
                        saveTreeDto.setId(rebateId);
                        saveTreeDto.setName(rebateName);
                        saveTreeDto.setNumber(rebateNo);
                        saveTreeDto.setPaymentFrequency(pFrequency);
                        saveTreeDto.setStatus(rebateStatus);
                        saveTreeDto.setStartDate(rebateStartdate);
                        saveTreeDto.setEndDate(rebateEnddate);
                        saveTreeDto.setPaymentMethod(pMethod);
                        saveTreeDto.setRebateType(rebateType);
                        saveTreeDto.setRebateProgramType(rebateProgType);
                        saveTreeDto.setRebatePlan(rebatePlan);
                        saveTreeDto.setLevel(ContractsDetailsDto.LEVEL5);
                        saveTreeDto.setSystemId(contractMasterId);
                        saveTreeDto.setAttachedList(new DiscountLogic().getSelectedTableData(saveTreeDto.getCategory(), session));
                        saveTreeDto.setSearchSessionId(session.getSearchSessionId());
                        if (componentDetailsSearchTable.isSelectable()) {
                            Object itemId = componentDetailsSearchTable.getValue();
                            if (itemId != null) {
                                ContractsDetailsDto newDiscountDto = (ContractsDetailsDto) itemId;
                                saveTreeDto.setInternalId(newDiscountDto.getInternalId());
                            }
                        }

                        if (addToTreeMethod(saveTreeDto)) {
                            rsList.add(saveTreeDto);
                            rsListforMap.add(saveTreeDto);
                            addedFlag = true;
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Add to Tree", "Please ensure all mandatory fields are completed for the Component and Component Details.");
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                    String priceId = psId.getValue();
                    String priceNo = psNo.getValue();
                    String priceName = psName.getValue();
                    String priceStatus = String.valueOf(psStatus.getValue() != null ? psStatus.getValue() : StringUtils.EMPTY);
                    String priceStartdate = psStartDate.getValue() != null ? psStartDate.getValue().toString() : StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(priceId) && StringUtils.isNotBlank(priceNo) && StringUtils.isNotBlank(priceName)
                            && StringUtils.isNotBlank(priceStatus) && StringUtils.isNotBlank(priceStartdate)) {

                        List list = DiscountLogic.duplicateCheck(compType, priceName, "PS_ID");
                        if (list != null && list.size() > 0) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Price Schedule Name already exist. Please Enter Different Name");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        psListforMap = new ArrayList<ContractsDetailsDto>();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
                        saveTreeDto.setId(priceId);
                        saveTreeDto.setName(priceName);
                        saveTreeDto.setNumber(priceNo);
                        saveTreeDto.setStatus(priceStatus);
                        saveTreeDto.setStartDate(priceStartdate);
                        saveTreeDto.setLevel(ContractsDetailsDto.LEVEL4);
                        saveTreeDto.setSystemId(contractMasterId);
                        saveTreeDto.setAttachedList(new DiscountLogic().getSelectedTableData(saveTreeDto.getCategory(), session));
                        saveTreeDto.setSearchSessionId(session.getSearchSessionId());
                        if (componentDetailsSearchTable.isSelectable()) {
                            Object itemId = componentDetailsSearchTable.getValue();
                            if (itemId != null) {
                                ContractsDetailsDto newDiscountDto = (ContractsDetailsDto) itemId;
                                saveTreeDto.setInternalId(newDiscountDto.getInternalId());
                            }
                        }

                        if (addToTreeMethod(saveTreeDto)) {
                            psList.add(saveTreeDto);
                            psListforMap.add(saveTreeDto);
                            addedFlag = true;
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Add to Tree", "Please ensure all mandatory fields are completed for the Component and Component Details.");
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                    String ifpId = this.ifpId.getValue();
                    String ifpNo = this.ifpNo.getValue();
                    String ifpName = this.ifpName.getValue();
                    String ifpStatus = String.valueOf(this.ifpStatus.getValue() != null ? this.ifpStatus.getValue() : StringUtils.EMPTY);
                    String ifpStartdate = ifpStartDate.getValue() != null ? ifpStartDate.getValue().toString() : StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(ifpId) && StringUtils.isNotBlank(ifpNo) && StringUtils.isNotBlank(ifpName)
                            && StringUtils.isNotBlank(ifpStatus) && StringUtils.isNotBlank(ifpStartdate)) {
                        List list = DiscountLogic.duplicateCheck(compType, ifpId, "IFP_ID");
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Item Family Plan ID already exist. Please enter different ID.");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        ifpListforMap = new ArrayList<ContractsDetailsDto>();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                        saveTreeDto.setId(ifpId);
                        saveTreeDto.setName(ifpName);
                        saveTreeDto.setNumber(ifpNo);
                        saveTreeDto.setStatus(ifpStatus);
                        saveTreeDto.setStartDate(ifpStartdate);
                        saveTreeDto.setLevel(ContractsDetailsDto.LEVEL3);
                        saveTreeDto.setSystemId(contractMasterId);
                        saveTreeDto.setAttachedList(new DiscountLogic().getSelectedTableData(saveTreeDto.getCategory(), session));
                        saveTreeDto.setSearchSessionId(session.getSearchSessionId());
                        if (addToTreeMethod(saveTreeDto)) {
                            ifpList.add(saveTreeDto);
                            ifpListforMap.add(saveTreeDto);
                            addedFlag = true;
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Add to Tree", "Please ensure all mandatory fields are completed for the Component and Component Details.");
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.getConstant())) {
                    String cfpId = this.cfpId.getValue();
                    String cfpNo = this.cfpNo.getValue();
                    String cfpName = this.cfpName.getValue();
                    String cfpStatus = String.valueOf(this.cfpStatus.getValue() != null ? this.cfpStatus.getValue() : StringUtils.EMPTY);
                    String cfpStartdate = cfpStartDate.getValue() != null ? cfpStartDate.getValue().toString() : StringUtils.EMPTY;
                    String cfpType = String.valueOf(this.cfpType.getValue() != null ? this.cfpType.getValue() : StringUtils.EMPTY);
                    String cfpEndDate = cfpStartDate.getValue() != null ? cfpStartDate.getValue().toString() : StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(cfpId) && StringUtils.isNotBlank(cfpNo) && StringUtils.isNotBlank(cfpName)
                            && StringUtils.isNotBlank(cfpStatus) && StringUtils.isNotBlank(cfpStartdate)) {
                        List list = DiscountLogic.duplicateCheck(compType, cfpId, "CFP_ID");
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Company Family Plan ID already exist. Please enter different ID.");
                            return;
                        }
                        List listNo = DiscountLogic.duplicateCheck(compType, cfpNo, "CFP_NO");
                        if (listNo != null && !listNo.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Company Family Plan No already exist. Please enter different No.");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                        saveTreeDto.setId(cfpId);
                        saveTreeDto.setName(cfpName);
                        saveTreeDto.setNumber(cfpNo);
                        saveTreeDto.setStatus(cfpStatus);
                        saveTreeDto.setStartDate(cfpStartdate);
                        saveTreeDto.setLevel(ContractsDetailsDto.LEVEL2);
                        saveTreeDto.setSystemId(contractMasterId);
                        saveTreeDto.setAttachedList(new DiscountLogic().getSelectedTableData(saveTreeDto.getCategory(), session));
                        saveTreeDto.setSearchSessionId(session.getSearchSessionId());
                        if (addToTreeMethod(saveTreeDto)) {
                            cfpList.add(saveTreeDto);
                            addedFlag = true;
                        }
                        System.out.println(" cfpList " + cfpList.size());
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Add to Tree", "Please ensure all mandatory fields are completed for the Component and Component Details.");
                    }
                }

                if (addedFlag) {
                    session.setSearchSessionId(StringUtils.EMPTY);
                    tableLogic.resetAndLoadData(true);
                    selectedTableLogic.resetAndLoadData(true);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private boolean addToTreeMethod(ContractsDetailsDto srcTableBean) throws SystemException {

        ContractsDetailsDto treeBean = dashboardTreeTable.getValue() == null ? null : getBeanFromID(dashboardTreeTable.getValue());
        Object treeBeanId = dashboardTreeTable.getValue() == null ? null : dashboardTreeTable.getValue();
        boolean returnFlag = false;
        if (treeBean == null) {
            if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.toString())) {

                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, true);
                returnFlag = true;
            } else {
                final String message = "Cannot make a " + srcTableBean.getCategory() + " as contracts header";
                AbstractNotificationUtils.getWarningNotification("Criteria Mismatch", message);
            }
        } else {

            if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.toString())) {
                final String message = "Cannot make a " + srcTableBean.getCategory() + " as child node";
                AbstractNotificationUtils.getWarningNotification("Criteria Mismatch", message);
            } else {
                if (srcTableBean.getCategory().equals(treeBean.getCategory())) {
                    final String message = srcTableBean.getCategory() + " cannot be added to  " + treeBean.getCategory();
                    AbstractNotificationUtils.getWarningNotification("Criteria Mismatch", message);
                } else {
                    if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.toString())) {
                        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class);
                        rsDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid", treeBean.getInternalId()));
                        rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("itemMasterSid")));
                        final List<Object> itemsList = IfpContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
                        String query1 = "getItemForIFPInNewDiscount";
                        String query2 = "getItemForPsInNewDiscount";
                        if ((treeBean.getInternalId() == 0 && !checkSameItemInPs(query1, query2)) || (treeBean.getInternalId() != 0 && !checkSameItemInPs(query2, itemsList))) {
                            AbstractNotificationUtils.getErrorNotification("Error", "Please Select the same items that are in IFP");;
                        } else {
                            final String psSystem = String.valueOf(srcTableBean.getInternalId()).trim();
//                            if (psSystem.equals(String.valueOf(treeBean.getModelSysId()).trim())) {
                            if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                                for (final Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
                                    final Object childId = iterator.next();
                                    final ContractsDetailsDto object = getBeanFromID(childId);
                                    String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                                    String dest = object.getId() + object.getName() + object.getNumber();
                                    if (src.equals(dest)) {
                                        final String messageStr = srcTableBean.getCategory() + "Already Added";
                                        AbstractNotificationUtils.getWarningNotification("Duplicate Criteria", messageStr);
                                        return false;
                                    }
                                }
                            }
                            saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), psListforMap);
                            newlyAddedRebates.add(srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber());
                            setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, true);
                            returnFlag = true;
//                            } else {
//                                final String message = srcTableBean.getCategory() + " does not associate with  " + treeBean.getCategory();
//                                AbstractNotificationUtils.getErrorNotification("Error", message);
//                            }
                        }
//                        }
                    } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.toString())) {
                        LOGGER.info("Inside Expected Code");
                        final DynamicQuery rsDynamicQuery1 = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class);
                        rsDynamicQuery1.add(RestrictionsFactoryUtil.eq("ifpContractSid", treeBean.getInternalId()));
                        rsDynamicQuery1.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("itemMasterSid")));
                        final List<Object> itemsList = IfpContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery1);
                        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
                        rsDynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", srcTableBean.getInternalId()));
                        rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("ifpModelSid")));
                        final List<RsDetails> rebateScheduleDetailsList = RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
                        String query1 = "getItemForIFPInNewDiscount";
                        String query2 = "getItemForRsInNewDiscount";
                        if ((treeBean.getInternalId() == 0 && !checkSameItemInPs(query1, query2)) || (treeBean.getInternalId() != 0 && !checkSameItemInPs(query2, itemsList))) {
                            AbstractNotificationUtils.getErrorNotification("Error", "Please Select the same items that are in IFP");;
                        } else {
                            final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();
//                            if (rsSystem.equals(String.valueOf(treeBean.getModelSysId()).trim())) {
                            if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                                for (final Object id : collection) {
                                    final ContractsDetailsDto object = getBeanFromID(id);
                                    String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                                    String dest = object.getId() + object.getName() + object.getNumber();
                                    if (src.equals(dest)) {
                                        final String message = srcTableBean.getCategory() + " Already Added";
                                        AbstractNotificationUtils.getWarningNotification("Duplicate Criteria", message);
                                        return false;
                                    }
                                }
                            }
                            saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                            newlyAddedRebates.add(srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber());
                            setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, false);
                            returnFlag = true;
//                            } else {
//                                final String message = srcTableBean.getCategory() + " does not associate with  " + treeBean.getCategory();
//                                AbstractNotificationUtils.getErrorNotification("Error", message);
//                            }
                        }
                    } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.toString()) && treeBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.toString())) {
                        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class);
                        rsDynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid", treeBean.getInternalId()));
                        rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("itemMasterSid")));
                        final List<Object> itemsList = PsContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
                        String query1 = "getItemForPsInNewDiscount";
                        String query2 = "getItemForRsInNewDiscount";
                        if ((treeBean.getInternalId() == 0 && !checkSameItemInPs(query1, query2)) || (treeBean.getInternalId() != 0 && !checkSameItemInPs(query2, itemsList))) {
                            AbstractNotificationUtils.getErrorNotification("Error", "Please Select the same items that are in IFP");;
                        } else {
                            final String rsSystem = String.valueOf(srcTableBean.getInternalId()).trim();
                            final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);
                            psDynamicQuery.add(RestrictionsFactoryUtil.eq("psModelSid", treeBean.getModelSysId()));
                            psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("ifpModelSid")));
                            final List<PsDetails> priceScheduleDetailsList = PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery);
//                            if (rsSystem.equals(String.valueOf(priceScheduleDetailsList.get(0)).trim())) {
                            if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                                for (final Object id : collection) {
                                    final ContractsDetailsDto object = getBeanFromID(id);
                                    String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                                    String dest = object.getId() + object.getName() + object.getNumber();
                                    if (src.equals(dest)) {
                                        final String message = srcTableBean.getCategory() + " Already Added";
                                        AbstractNotificationUtils.getWarningNotification("Duplicate Criteria", message);
                                        return false;
                                    }
                                }
                            }
                            saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                            newlyAddedRebates.add(srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber());
                            setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId, false);
                            returnFlag = true;
//                            } else {
//                                final String message = srcTableBean.getCategory() + " does not associate with  " + treeBean.getCategory();
//                                AbstractNotificationUtils.getWarningNotification("Error", message);
//                            }
                        }
                    } else {
                        System.out.println(" asdasd asd");
                        if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                            final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                            System.out.println(" collection " + collection.size());
                            for (final Object id : collection) {
                                final ContractsDetailsDto object = getBeanFromID(id);
                                String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                                String dest = object.getId() + object.getName() + object.getNumber();
                                System.out.println(" src " + src + " Dest " + dest);
                                if (src.equals(dest)) {
                                    final String message = srcTableBean.getCategory() + " Already Added";
                                    AbstractNotificationUtils.getWarningNotification("Duplicate Criteria", message);
                                    return false;
                                }
                            }
                        }
                        if ("IFP".equals(srcTableBean.getCategory())) {
                            saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), ifpListforMap);
                        }
                        newlyAddedRebates.add(srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber());
                        setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId, true);
                        returnFlag = true;
                    }
                }
            }
        }
        return returnFlag;
    }

    private ContractsDetailsDto getBeanFromID(final Object tableID) {
        BeanItem<?> targetItem;
        if (tableID instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableID;
        } else if (tableID instanceof ContractsDetailsDto) {
            targetItem = new BeanItem<ContractsDetailsDto>((ContractsDetailsDto) tableID);
        } else {
            targetItem = NULL_OBJECT;
        }
        return (ContractsDetailsDto) targetItem.getBean();
    }

    private void setTreeNode(final ContractsDetailsDto bean, final VerticalDropLocation location, final Object targetItemId, boolean childrenAllowed) {

        LOGGER.info("Entering setTreeNode method");

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
                bean.setInternalId(0);
                if (bean.getCategory().equals("CFP")) {
                    bean.setLevel(2);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("IFP")) {
                    bean.setLevel(3);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("PS")) {
                    bean.setLevel(4);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent2());
                    bean.setParent3(getBeanFromID(dashboardTreeTable.getValue()));
                } else if (bean.getCategory().equals("RS")) {
                    bean.setLevel(5);
                    bean.setParent1(getBeanFromID(dashboardTreeTable.getValue()).getParent1());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent2());
                    bean.setParent2(getBeanFromID(dashboardTreeTable.getValue()).getParent3());
                    bean.setParent4(getBeanFromID(dashboardTreeTable.getValue()));
                }
                dashBoardTreeContainer.addBean(bean);
                dashBoardTreeContainer.setChildrenAllowed(bean, childrenAllowed);
                dashBoardTreeContainer.setParent(bean, targetItemId);
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
        LOGGER.info("End of setTreeNode method");
    }

    @UiHandler("levelPopulateBtn")
    public void levelPopulateBtnClick(Button.ClickEvent event) {
        System.out.println(" Inside Level populate btn ");
        if (dashboardTreeTable.getValue() != null) {
            ContractsDetailsDto temp = tableBean;
            System.out.println(" temp.getCategory() - " + temp.getCategory());
            System.out.println(" temp.getSearchSessionId() - " + temp.getSearchSessionId());
            fromCDLabelName.setValue(temp.getCategory() + "Name: ");
            fromCDLabelNo.setValue(temp.getCategory() + " No: ");
            fromCDNo.setValue(temp.getNumber());
            fromCDName.setValue(temp.getName());
            ContractsDetailsDto dto = (ContractsDetailsDto) dashboardTreeTable.getValue();
            if (Constants.IndicatorConstants.CFP.getConstant().equals(temp.getCategory())) {
                loadCfpFromCD(temp, dto.getInternalId(), session.getUserId(), dto.getSearchSessionId());
            } else if (Constants.IndicatorConstants.IFP.getConstant().equals(temp.getCategory())) {
                loadIfpFromCD(temp, dto.getInternalId(), session.getUserId(), dto.getSearchSessionId());
            } else if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(temp.getCategory())) {
                fromCDLabelName.setValue("Price Schedule No:");
                fromCDLabelNo.setValue("Price Schedule Name:");
                loadPsFromCD(temp, dto.getInternalId(), session.getUserId(), dto.getSearchSessionId());
            } else if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(temp.getCategory())) {
                fromCDLabelName.setValue("Rebate Schedule No:");
                fromCDLabelNo.setValue("Rebate Schedule Name:");
                loadRsFromCD(temp, dto.getInternalId(), session.getUserId(), dto.getSearchSessionId());
            }
        } else {
            AbstractNotificationUtils.getWarningNotification("Populate", "Please highlight a component to Populate.");
        }
    }

    private void loadCfpFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        System.out.println(" Inside loadCfpFromCD");
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<CFPComponentDetailsDTO>(CFPComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForCFP(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromCfpCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_COMPANY_COLUMN);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_COMPANY_HEADER);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadIfpFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForIFP(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromIfpCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadPsFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForPS_RS(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromPsCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadRsFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForPS_RS(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromRsCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.AD_COMPONENT_DETAILS_PS_COLUMN);
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.AD_COMPONENT_DETAILS_PS_HEADER);
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    @UiHandler("levelRemoveBtn")
    public void levelRemoveBtnLogic(Button.ClickEvent event) {

        LOGGER.info(" buttonClick ( ClickEvent event ) name=" + event.getButton().getCaption());
        if (dashBoardTreeContainer.getItemIds().size() > Constants.ZERO) {
            if (dashboardTreeTable.getValue() == null) {
                AbstractNotificationUtils.getWarningNotification("Remove", "Please highlight a component to Remove.");
            } else {
                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(tableBeanId);
                if (collection == null) {
                    Object ob = (ContractsDetailsDto) dashboardTreeTable.getValue();
                    ContractsDetailsDto treeBean = getBeanFromID(dashboardTreeTable.getValue());
                    if (treeBean.getInternalId() == 0) {
                        dashBoardTreeContainer.removeItem(ob);
                        if (levelVal == ContractsDetailsDto.LEVEL2) {
                            cfpList.remove(ob);
                        } else if (levelVal == ContractsDetailsDto.LEVEL3) {
                            ifpList.remove(ob);
                            if (ifpListforMap.contains(ob)) {
                                ifpListforMap.remove(ob);
                            }
                        } else if (levelVal == ContractsDetailsDto.LEVEL4) {
                            psList.remove(ob);
                            if (psListforMap.contains(ob)) {
                                psListforMap.remove(ob);
                            }
                        } else if (levelVal == ContractsDetailsDto.LEVEL5) {
                            rsList.remove(ob);
                            if (rsListforMap.contains(ob)) {
                                rsListforMap.remove(ob);
                            }
                        }

                    } //                    if (newlyAddedRebates.contains(tableBean.getId() + tableBean.getName() + tableBean.getNumber())) {
                    //                        dashBoardTreeContainer.removeItem(tableBeanId);
                    //                        newlyAddedRebates.remove(tableBean.getId() + tableBean.getName() + tableBean.getNumber());
                    //} 
                    else {
                        AbstractNotificationUtils.getWarningNotification("Remove", "You can remove only newly added Rebates.");
                    }

                } else {
                    final String message = "Please remove all children nodes before removing a parent node.";
                    AbstractNotificationUtils.getWarningNotification("Remove", message);
                }
            }
        } else {
            AbstractNotificationUtils.getWarningNotification("Remove", "No data to remove");
        }
    }

    public void addDiscountSaveLogic() {

        boolean check = false;
        final Collection idList = dashBoardTreeContainer.getItemIds();
        String[] level = {Constants.IndicatorConstants.CONTRACT.toString(),
            Constants.IndicatorConstants.CFP.toString(),
            Constants.IndicatorConstants.IFP.toString(),
            Constants.IndicatorConstants.PS_VALUE.toString(),
            Constants.IndicatorConstants.RS_VALUE.toString()};
        check = checkForAllLevels(idList, level, 0);

        MessageBox.showPlain(Icon.QUESTION, "Create", "Are you sure you want to save the contract ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        int rsModelSysId = 0;

                        if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true, true) > 0) {
                            for (final Iterator iterator = idList.iterator(); iterator.hasNext();) {
                                final Object idValue = iterator.next();
                                final ContractsDetailsDto temp = getBeanFromID(idValue);
                                if (temp.getInternalId() == 0 && Constants.IndicatorConstants.RS_VALUE.getConstant().equals(temp.getCategory())) {
                                    List list = DiscountLogic.duplicateCheck(Constants.IndicatorConstants.REBATE_SCHEDULE.toString(), temp.getId(), "RS_ID");
                                    if (list.isEmpty()) {
                                        rsModelSysId = saveNewRebateToRsModel(temp, session, temp.getSearchSessionId());
                                        saveTree(idList, rsModelSysId, Constants.IndicatorConstants.RS_VALUE.getConstant());
                                    }
                                } else if (temp.getInternalId() == 0 && Constants.IndicatorConstants.PS_VALUE.getConstant().equals(temp.getCategory())) {
                                    List list = DiscountLogic.duplicateCheck(Constants.IndicatorConstants.PRICE_SCHEDULE.toString(), temp.getId(), "PS_ID");
                                    if (list.isEmpty()) {
                                        rsModelSysId = saveNewPriceToPsModel(temp, session, temp.getSearchSessionId());
                                        saveTree(idList, rsModelSysId, Constants.IndicatorConstants.PS_VALUE.getConstant());
                                    }
                                } else if (temp.getInternalId() == 0 && Constants.IndicatorConstants.IFP.getConstant().equals(temp.getCategory())) {
                                    List list = DiscountLogic.duplicateCheck(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString(), temp.getId(), "IFP_ID");
                                    if (list.isEmpty()) {
                                        rsModelSysId = saveNewIFPToIfpModel(temp, session, temp.getSearchSessionId());
                                        saveTree(idList, rsModelSysId, Constants.IndicatorConstants.IFP.getConstant());
                                    }
                                } else if (temp.getInternalId() == 0 && Constants.IndicatorConstants.CFP.getConstant().equals(temp.getCategory())) {
                                    List list = DiscountLogic.duplicateCheck(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString(), temp.getId(), "CFP_ID");
                                    if (list.isEmpty()) {
                                        rsModelSysId = saveNewCFPToCfpModel(temp, session, temp.getSearchSessionId());
                                        saveTree(idList, rsModelSysId, Constants.IndicatorConstants.CFP.getConstant());
                                    }
                                }
                            }
                            cfpList.clear();
                            ifpList.clear();
                            psList.clear();
                            rsList.clear();
                            Notification notif = new Notification("Contract successfully saved", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.BOTTOM_CENTER);
                            notif.setStyleName("mystyle");
                            notif.setDelayMsec(3000);
                            notif.show(Page.getCurrent());
                        } else {
                            AbstractNotificationUtils.getErrorNotification("No Records Selected",
                                    "There were no records Selected from Results Table.  Please Select Record.");
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

            }
        }, ButtonId.YES, ButtonId.NO);
    }
    ContractsDetailsDto contract = new ContractsDetailsDto();
    ContractsDetailsDto cfp = null;
    ContractsDetailsDto ifp = null;
    ContractsDetailsDto priceSchedule = null;
    ContractsDetailsDto rebateSchedule = null;

    public void saveTree(final Collection list, int rsModelSysId, String category) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering saveTree method");
        try {
            final DiscountLogic discountLogic = new DiscountLogic();

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
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.CFP.getConstant())) {
                        cfp.setModelSysId(rsModelSysId);
                        cfp.setCfpId(rsModelSysId);
                        DiscountLogic.saveCFp(contract.getInternalId(), cfp);
                    }
                }
                if (Constants.IndicatorConstants.IFP.toString().equalsIgnoreCase(temp.getCategory())) {
                    ifp = temp;
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.IFP.getConstant())) {
                        ifp.setModelSysId(rsModelSysId);
                        ifp.setIfpId(rsModelSysId);
                        DiscountLogic.saveIFP(contract.getInternalId(), cfp.getCfpContractId(), ifp);
                    }
                }
                if (Constants.IndicatorConstants.PS_VALUE.toString().equalsIgnoreCase(temp.getCategory())) {
                    priceSchedule = temp;
                    rebateSchedule = new ContractsDetailsDto();
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
                        priceSchedule.setModelSysId(rsModelSysId);
                        priceSchedule.setPsSid(String.valueOf(rsModelSysId));
                        DiscountLogic.savePS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule);
                    }
                }
                if (Constants.IndicatorConstants.RS_VALUE.toString().equalsIgnoreCase(temp.getCategory())) {
                    rebateSchedule = temp;
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.getConstant())) {
                        rebateSchedule.setRsSid(String.valueOf(rsModelSysId));
                        int internalId = temp.getInternalId();
                        if (internalId == 0) {
                            new DiscountLogic().saveRS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule.getPsContractId(), rebateSchedule);
                        }
                    }
                }

                final Collection childlist = dashboardTreeTable.getChildren(idValue);
                if (childlist == null || childlist.isEmpty()) {
                } else {
                    saveTree(childlist, rsModelSysId, category);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End of saveTree method");
    }

    public int saveNewRebateToRsModel(ContractsDetailsDto saveDto, SessionDTO session, String searchSessionId) {
        int rsModelSid = 0;
        rsModelSid = DiscountLogic.saveNewRebateToRsModel(saveDto, session.getUserId(), searchSessionId);
        return rsModelSid;
    }

    public int saveNewPriceToPsModel(ContractsDetailsDto saveDto, SessionDTO session, String searchSessionId) {
        int psModelSid = 0;
        psModelSid = DiscountLogic.saveNewPriceToPsModel(saveDto, session.getUserId(), searchSessionId);
        return psModelSid;
    }

    public int saveNewIFPToIfpModel(ContractsDetailsDto saveDto, SessionDTO session, String searchSessionId) {
        int ifpModelSid = 0;
        ifpModelSid = DiscountLogic.saveNewIfpToIFPModel(saveDto, session.getUserId(), searchSessionId);
        return ifpModelSid;
    }

    public int saveNewCFPToCfpModel(ContractsDetailsDto saveDto, SessionDTO session, String searchSessionId) {
        int cfpModelSid = 0;
        cfpModelSid = DiscountLogic.saveNewCfpToCFPModel(saveDto, session.getUserId(), searchSessionId);
        return cfpModelSid;
    }

    @UiHandler("removeBtn")
    public void removeBtnLogic(Button.ClickEvent event) {
        LOGGER.info(" buttonClick ( ClickEvent event ) name=" + event.getButton().getCaption());
        if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true, false) > 0) {
            Object itemId = componentDetailsSearchTable.getValue();
            ContractsDetailsDto newDiscountDto = (ContractsDetailsDto) itemId;
            if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
            }
            selectedContainer.removeAllItems();
            selectedContainer.addAll(contListafterRemove);
            contListafterRemove.clear();
        } else {
            AbstractNotificationUtils.getErrorNotification("Remove", "Please check a record to Remove.");
        }

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

    @UiHandler("searchField")
    public void searchFieldDdlb(Property.ValueChangeEvent event) throws SystemException, Exception {
        searchValue.setValue(StringUtils.EMPTY);
        if (event != null) {

            String value = String.valueOf(event.getProperty().getValue());

            if (Constants.COMPANY_ID.equals(value) || Constants.COMPANYNAME.equals(value) || Constants.COMPANYNO.equals(value)
                    || Constants.ITEM_ID.equals(value) || Constants.ITEM_NAME.equals(value) || Constants.ITEM_NO.equals(value)
                    || Constants.IFP_ID.equals(value) || Constants.IfpNAME.equals(value) || Constants.IFP_NO.equals(value)) {
                searchValue.setValue(StringUtils.EMPTY);
                searchValue.setVisible(true);
                searchValueStatusDdlb.setVisible(false);

            } else if (Constants.COMPANYSTATUS.equals(value) || Constants.ITEM_STATUS.equals(value) || Constants.IFP_STATUS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchValueStatusDdlb = CommonLogic.getNativeSelect(searchValueStatusDdlb, itemStatusList);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.COMPANYTYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.COMPANY_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.ITEM_TYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.ITEM_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.IFPTYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.IFP_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.COMPANYCATEGORY.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.COMPANY_CATEGORY, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.TRADECLASS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.COMPANY_TRADE_CLASS, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.BRAND.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                List<HelperDTO> temp = new ArrayList<HelperDTO>();
                temp = CommonLogic.getBrand("ad.getBrand");
                searchValueStatusDdlb = CommonLogic.getNativeSelect(searchValueStatusDdlb, temp);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.FORM.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.FORM, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.STRENGTH.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.STRENGTH, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.THERAPY_CLASS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UIUtils.THERAPEUTIC_CLASS, false);
                searchValueStatusDdlb.setVisible(true);
            }

        }
    }

    public boolean checkSameItemInPs(String query1, String query2) {
        List input = new ArrayList();
        input.add(session.getUserId());
        input.add(session.getSearchSessionId());
        List<Object> list = ItemQueries.getItemData(input, query1, null);
        List<Object> dataList = ItemQueries.getItemData(input, query2, null);
        if (list.size() == dataList.size()) {
            Object[] listArr = dataList.toArray();
            Object[] dataArr = list.toArray();
            Arrays.sort(dataArr);
            Arrays.sort(listArr);
            return Arrays.deepEquals(dataArr, listArr);
        }
        return false;
    }

    public boolean checkSameItemInPs(String query1, List<Object> dataList) {
        List input = new ArrayList();
        input.add(session.getUserId());
        input.add(session.getSearchSessionId());
        List<Object> list = ItemQueries.getItemData(input, query1, null);
        if (list.size() == dataList.size()) {
            Object[] listArr = dataList.toArray();
            Object[] dataArr = list.toArray();
            Arrays.sort(dataArr);
            Arrays.sort(listArr);
            return Arrays.deepEquals(dataArr, listArr);
        }
        return false;
    }
}
