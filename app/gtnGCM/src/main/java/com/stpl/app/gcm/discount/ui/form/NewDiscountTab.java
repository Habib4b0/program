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
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.MMDDYYYY;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ENABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SELECT_ONE;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Tree;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
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
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class NewDiscountTab extends CustomComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(NewDiscountTab.class);
    
    private final NewDiscountTableLogic tableLogic = new NewDiscountTableLogic();
    private final NewDiscountSelectedTableLogic selectedTableLogic = new NewDiscountSelectedTableLogic();
    private final ExtPagedTable componentDetailsSearchTable = new ExtPagedTable(tableLogic);
    private final ExtPagedTable componentDetailsSelectedItem = new ExtPagedTable(selectedTableLogic);
    @UiField("levelDetailsResultsTable")
    private  ExtFilterTable levelDetailsResultsTable;
    @UiField("dashboardResultsTable")
    private  TreeTable dashboardTreeTable;
    @UiField("rsStartDate")
    private PopupDateField rebateScheduleStartDate;
    @UiField("rsEndDate")
    private PopupDateField rsEndDate;
    @UiField("componentTypeddlb")
    public ComboBox componentTypeddlb;
    @UiField("searchField")
    private ComboBox searchDdlb;
    @UiField("searchValue")
    private TextField searchValue;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("tableLayout")
    private VerticalLayout vLayout;
    @UiField("tableLayout1")
    private VerticalLayout vLayout1;
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
    @UiField("rsStatusDdlb")
    private ComboBox rsStatusDdlb;
    @UiField("rsProgramType")
    private ComboBox rsProgramType;
    @UiField("rebatePlanLevel")
    private ComboBox rebatePlanLevel;
    @UiField("paymentFrequency")
    private ComboBox paymentFrequency;
    @UiField("paymentMethod")
    private ComboBox paymentMethod;
    @UiField("rsType")
    private ComboBox rsType;
    @UiField("massUpdateRadio")
    private OptionGroup massUpdateEnableDisable;
    @UiField("fieldDdlb")
    private ComboBox fieldDdlb;
    @UiField("valueDdlb")
    private ComboBox valueDdlb;
    @UiField("datePeriod")
    private PopupDateField datePeriod;
    @UiField("populateBtn")
    private Button populateBtn;

    /* Component Layout */
    @UiField("componentSelectionLayout")
    private HorizontalLayout componentSelectionLayout;
    @UiField("cfpComponent")
    private GridLayout cfpComponent;
    @UiField("ifpComponent")
    private GridLayout ifpComponent;
    @UiField("psComponent")
    private GridLayout psComponent;
    @UiField("rsComponent")
    private GridLayout rsComponent;
    private final SessionDTO session;
    @UiField("addToTree")
    private Button addToTree;
    @UiField("rsId")
    private TextField rsId;
    @UiField("rsNumber")
    private TextField rsNumber;
    @UiField("rsStartDate")
    private PopupDateField rsStartDate;
    @UiField("rsName")
    private TextField rsName;
    @UiField("fromCDLabelNo")
    private Label fromCDLabelNo;
    @UiField("fromCDNo")
    private TextField fromCDNo;
    @UiField("fromCDLabelName")
    private Label fromCDLabelName;
    @UiField("fromCDName")
    private TextField fromCDName;
    @UiField("levelRemoveBtn")
    private Button levelRemoveBtn;
    private ContractsDetailsDto saveTreeDto;
    @UiField("psId")
    private TextField psId;
    @UiField("psNo")
    private TextField psNo;
    @UiField("psName")
    private TextField psName;
    @UiField("psStatus")
    private ComboBox psStatus;
    @UiField("psStartDate")
    private PopupDateField psStartDate;
    @UiField("ifpId")
    private TextField ifpId;
    @UiField("ifpNo")
    private TextField ifpNo;
    @UiField("ifpName")
    private TextField ifpName;
    @UiField("ifpStatus")
    private ComboBox ifpStatus;
    @UiField("ifpStartDate")
    private PopupDateField ifpStartDate;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("levelPopulateBtn")
    private Button levelPopulateBtn;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("psType")
    private ComboBox psType;
    @UiField("ifpType")
    private ComboBox ifpType;
    @UiField("cfpType")
    private ComboBox cfpType;
    @UiField("cfpStatus")
    private ComboBox cfpStatus;
    @UiField("cfpId")
    private TextField cfpId;
    @UiField("cfpNo")
    private TextField cfpNo;
    @UiField("cfpName")
    private TextField cfpName;
    @UiField("cfpStartDate")
    private PopupDateField cfpStartDate;
    @UiField("cfpEndDate")
    private PopupDateField cfpEndDate;
    @UiField("ifpEndDate")
    private PopupDateField ifpEndDate;
    @UiField("psEndDate")
    private PopupDateField psEndDate;
    @UiField("searchValueStatusDdlb")
    private ComboBox searchValueStatusDdlb;
    @UiField("searchDatePeriod")
    private PopupDateField searchDatePeriod;
    private final List<ContractsDetailsDto> contListafterRemove = new ArrayList<>();
    private final BeanItemContainer<ContractsDetailsDto> componentResultsContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    private ExtTreeContainer<ContractsDetailsDto> dashBoardTreeContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
    private final BeanItemContainer<ContractsDetailsDto> selectedContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    private final BeanItemContainer<ContractsDetailsDto> availableItemContainer = new BeanItemContainer<>(ContractsDetailsDto.class);
    public final SimpleDateFormat dbDate = new SimpleDateFormat("MM-dd-yyyy");
    public static final String S_DATE_PROPERTY = "sDate";
    /* Current Level Value */
    private int levelValue;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /* Contains the parent items of an item in the hierarchy */
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
    private final ContractsDetailsDto newDiscountTabDto = new ContractsDetailsDto();
    private final List<Integer> rebateList = new ArrayList<>();
    private List<HelperDTO> itemStatusList = new ArrayList<>();
    private List<HelperDTO> priceTypeList = new ArrayList<>();
    private String selectedComponenttype = StringUtils.EMPTY;
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    private final StplSecurity stplSecurity = new StplSecurity();
    private final List<ContractsDetailsDto> cfpList = new ArrayList<>();
    private final List<ContractsDetailsDto> ifpList = new ArrayList<>();
    private final List<ContractsDetailsDto> psList = new ArrayList<>();
    private final List<ContractsDetailsDto> rsList = new ArrayList<>();
    private int levelVal = 0;
    private int contractMasterId = 0;
    private List<ContractsDetailsDto> ifpListforMap;
    private List<ContractsDetailsDto> psListforMap;
    private List<ContractsDetailsDto> rsListforMap;

    public NewDiscountTab(List<RemoveDiscountDto> removeDiscountDto, SessionDTO session) {
        this.removeDiscountDto = removeDiscountDto == null ? removeDiscountDto : new ArrayList<>(removeDiscountDto);
        dashBoardTreeContainer = new ExtTreeContainer<>(ContractsDetailsDto.class);
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/newDiscountTab.xml"), this));
        configureFields();
        configureSecurityPermissions();
    }

    protected final void configureFields() {
        try {
            startDate.addStyleName("v-align-center");
            endDate.addStyleName("v-align-center");

            rebateScheduleStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rebateScheduleStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            rsEndDate.setDateFormat(Constants.MM_DD_YYYY);
            rsEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            datePeriod.setDateFormat(Constants.MM_DD_YYYY);
            datePeriod.setStyleName(Constants.DATE_FIELD_CENTER);

            rsStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rsStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            psStartDate.setDateFormat(Constants.MM_DD_YYYY);
            psStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            ifpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            cfpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpStartDate.setStyleName(Constants.DATE_FIELD_CENTER);

            cfpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            ifpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            ifpEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            psEndDate.setDateFormat(Constants.MM_DD_YYYY);
            psEndDate.setStyleName(Constants.DATE_FIELD_CENTER);

            searchDatePeriod.setDateFormat(Constants.MM_DD_YYYY);

            contractNo.setValue(removeDiscountDto.get(0).getContractNo());
            contractName.setValue(removeDiscountDto.get(0).getContractName());
            contractType.setValue(removeDiscountDto.get(0).getMarketType());
            startDate.setValue(removeDiscountDto.get(0).getContractstartDate() == null ? StringUtils.EMPTY : dbDate.format((Date) removeDiscountDto.get(0).getContractstartDate()));
            endDate.setValue(removeDiscountDto.get(0).getContractendDate() == null ? StringUtils.EMPTY : dbDate.format((Date) removeDiscountDto.get(0).getContractendDate()));
            isEnable(false);
            componentTypeddlb = CommonLogic.loadComponentType(componentTypeddlb, null, true);
            searchDdlb = CommonLogic.loadNewTabSearchDdlb(searchDdlb, selectedComponenttype);
            commonUtil.loadComboBox(rsStatusDdlb, UiUtils.STATUS, false);
            commonUtil.loadComboBox(rsProgramType, UiUtils.REBATE_PROGRAM_TYPE, false);
            commonUtil.loadComboBox(rebatePlanLevel, UiUtils.REBATE_PLAN_LEVEL, false);
            commonUtil.loadComboBox(paymentFrequency, UiUtils.PAYMENT_FREQUENCY, false);
            commonUtil.loadComboBox(paymentMethod, UiUtils.PAYMENT_METHOD, false);
            commonUtil.loadComboBox(rsType, UiUtils.RS_TYPE, false);
            configureTables();
            loadDashBoardTree();
            for (RemoveDiscountDto remove : removeDiscountDto) {
                rebateList.add(remove.getRsSid());
            }
            massUpdateEnableDisable.addItem(ENABLE.getConstant());
            massUpdateEnableDisable.addItem(DISABLE.getConstant());
            massUpdateEnableDisable.select(DISABLE.getConstant());

            fieldDdlb.setNullSelectionAllowed(false);
            fieldDdlb.setInputPrompt(SELECT_ONE.getConstant());
            fieldDdlb.addItem(Constants.STATUS_FIELD);
            fieldDdlb.addItem(Constants.START_DATE_HEADER);
            fieldDdlb.addItem(Constants.END_DATE_HEADER);
            valueDdlb.setNullSelectionAllowed(false);
            valueDdlb.setInputPrompt(SELECT_ONE.getConstant());
            valueDdlb.setVisible(true);
            datePeriod.setVisible(false);
            loadcomponentSelectionGrid();
            itemStatusList = CommonLogic.getDropDownList(Constants.IndicatorConstants.STATUS.getConstant());
            fromCDNo.setEnabled(false);
            fromCDName.setEnabled(false);
            searchValueStatusDdlb.setVisible(false);
            searchDatePeriod.setVisible(false);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected void configureTables() {
        addResultTable();
        addSelectedResultTable();
        tableLogic.setContainerDataSource(availableItemContainer);
        tableLogic.setPageLength(NumericConstants.FIVE);
        tableLogic.sinkItemPerPageWithPageLength(false);

        selectedTableLogic.setContainerDataSource(selectedContainer);
        selectedTableLogic.setPageLength(NumericConstants.FIVE);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);

        componentDetailsSearchTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSearchTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        componentDetailsSearchTable.setHeight(NumericConstants.EIGHTY, Sizeable.Unit.PERCENTAGE);
        componentDetailsSearchTable.setEditable(BooleanConstant.getTrueFlag());
        componentDetailsSearchTable.setFilterBarVisible(BooleanConstant.getFalseFlag());
        componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().itemSearchResultsColumns);
        componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().itemSearchResultsHeaders);
        componentDetailsSearchTable.setColumnCheckBox(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());
        componentDetailsSearchTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            ContractsDetailsDto dto = (ContractsDetailsDto) itemId;
                            boolean isCheck = check.getValue();
                            dto.setCheckRecord(isCheck);
                            dto.setCheckAll(BooleanConstant.getFalseFlag());
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
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = componentDetailsSearchTable.getItemIds();
                int size = itemList.size();
                if (size > 0) {
                    newDiscountTabDto.setCheckAll(BooleanConstant.getTrueFlag());
                    newDiscountTabDto.setCheckRecord(event.isChecked());
                    DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, Constants.CHECK_RECORD, true);

                    tableLogic.loadSetData(newDiscountTabDto, session, false);
                }
            }
        });
        componentDetailsSelectedItem.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentDetailsSelectedItem.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        componentDetailsSelectedItem.setHeight(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().selectedResultsColumns);
        componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().selectedResultsHeaders);
        componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());
        componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());
        componentDetailsSelectedItem.setEditable(BooleanConstant.getTrueFlag());
        componentDetailsSelectedItem.setFilterBarVisible(BooleanConstant.getTrueFlag());
        componentDetailsSelectedItem.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                final ContractsDetailsDto dto = (ContractsDetailsDto) itemId;
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            dto.setCheckRecord(isCheck);
                            dto.setCheckAll(BooleanConstant.getFalseFlag());
                            if (!isCheck) {
                                componentDetailsSelectedItem.setColumnCheckBox(Constants.CHECK_RECORD, true, isCheck);
                                contListafterRemove.add(dto);
                            } else if (contListafterRemove.contains(dto)) {
                                contListafterRemove.remove(dto);
                            }
                            dto.setCheckAll(BooleanConstant.getFalseFlag());
                            dto.setBulkUpdate(BooleanConstant.getFalseFlag());
                            int compId = dto.getInternalId();
                            dto.setInternalId(dto.getSystemId());
                            dto.setSystemId(compId);
                            DiscountLogic.updateTempTableRecord(dto, session, Constants.CHECK_RECORD, true);
                        }
                    });
                    return check;
                }
                if (String.valueOf(propertyId).equals(Constants.STATUS_S)) {
                    final ComboBox status = new ComboBox();
                    status.setNullSelectionAllowed(false);
                    status.setInputPrompt(SELECT_ONE.getConstant());
                    try {
                        commonUtil.loadComboBox(status, UiUtils.STATUS, false);

                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }

                    status.addBlurListener(new BlurListener() {
                        @Override
                        public void blur(BlurEvent event) {
                            String newValue = String.valueOf(((ComboBox) event.getComponent()).getValue());
                            String oldValue = dto.getTempStatus();
                            if (!oldValue.equals(newValue)) {
                                dto.setStatus(newValue);
                                dto.setTempStatus(newValue);
                                DiscountLogic.updateTempTableRecord(dto, session, Constants.STATUS_S, false);
                            }
                        }
                    });
                    return status;

                }
                if (propertyId.equals(S_DATE_PROPERTY)) {
                    final PopupDateField itemStartDate = new PopupDateField();
                    itemStartDate.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                    itemStartDate.setDateFormat(MMDDYYYY.getConstant());
                    itemStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    itemStartDate.addBlurListener(new BlurListener() {
                        @Override
                        public void blur(BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempSDate());
                            if (!oldValue.equals(newValue)) {
                                dto.setsDate(dt1);
                                dto.setTempSDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, S_DATE_PROPERTY, false);
                            }
                        }
                    });
                    return itemStartDate;
                }
                if (propertyId.equals(Constants.E_DATE_PROPERTY)) {
                    final PopupDateField itemEndDate = new PopupDateField();
                    itemEndDate.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                    itemEndDate.setDateFormat(MMDDYYYY.getConstant());
                    itemEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    itemEndDate.addBlurListener(new BlurListener() {
                        @Override
                        public void blur(BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempEDate());
                            if (!oldValue.equals(newValue)) {
                                dto.seteDate(dt1);
                                dto.setTempEDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, Constants.E_DATE_PROPERTY, false);
                            }
                        }
                    });
                    return itemEndDate;
                }
                if (propertyId.equals("rebatePlan")) {
                    final CustomTextField rebatePlan = new CustomTextField();
                    rebatePlan.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                    rebatePlan.addStyleName("searchicon");
                    rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            RebatePlanLookup lookup = new RebatePlanLookup(rebatePlan);
                            lookup.rebatePlanId.focus();
                            lookup.addCloseListener(new Window.CloseListener() {

                                @Override
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
                    formulaId.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                    formulaId.addStyleName("searchicon");
                    formulaId.addClickListener(new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            FormulaSearchLookup lookup = new FormulaSearchLookup(formulaId);
                            lookup.formulaId.focus();
                            lookup.addCloseListener(new Window.CloseListener() {
                                @Override
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
                if (String.valueOf(propertyId).equals(Constants.PRICE_TYPE_PROPERTY)) {
                    final ComboBox priceType = new ComboBox();
                    priceType.setNullSelectionAllowed(false);
                    priceType.setInputPrompt(SELECT_ONE.getConstant());
                    try {
                        CommonLogic.getNativeSelect(priceType, priceTypeList);
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }

                    priceType.addBlurListener(new BlurListener() {
                        @Override
                        public void blur(BlurEvent event) {
                            String newValue = String.valueOf(((ComboBox) event.getComponent()).getValue());
                            String oldValue = dto.getTempPriceType();
                            if (!oldValue.equals(newValue)) {
                                dto.setPriceType(newValue);
                                dto.setTempPriceType(newValue);
                                DiscountLogic.updateTempTableRecord(dto, session, Constants.PRICE_TYPE_PROPERTY, false);
                            }
                        }
                    });
                    return priceType;
                }
                if (propertyId.equals(Constants.PP_S_DATE)) {
                    final PopupDateField ppStartDate = new PopupDateField();
                    ppStartDate.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                    ppStartDate.setDateFormat(MMDDYYYY.getConstant());
                    ppStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    ppStartDate.addBlurListener(new BlurListener() {
                        @Override
                        public void blur(BlurEvent event) {
                            Date dt1 = ((PopupDateField) event.getComponent()).getValue();
                            String newValue = String.valueOf(dt1);
                            String oldValue = String.valueOf(dto.getTempPpSDate());
                            if (!oldValue.equals(newValue)) {
                                dto.setPpSDate(dt1);
                                dto.setTempPpSDate(dt1);
                                DiscountLogic.updateTempTableRecord(dto, session, Constants.PP_S_DATE, false);
                            }
                        }
                    });
                    return ppStartDate;
                }

                return null;
            }
        });
        componentDetailsSelectedItem.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = componentDetailsSelectedItem.getItemIds();
                int size = itemList.size();
                if (size > 0) {
                    newDiscountTabDto.setCheckAll(BooleanConstant.getTrueFlag());
                    newDiscountTabDto.setCheckRecord(event.isChecked());
                    newDiscountTabDto.setBulkUpdate(BooleanConstant.getFalseFlag());
                    int compId = newDiscountTabDto.getInternalId();
                    newDiscountTabDto.setInternalId(newDiscountTabDto.getSystemId());
                    newDiscountTabDto.setSystemId(compId);
                    DiscountLogic.updateTempTableRecord(newDiscountTabDto, session, Constants.CHECK_RECORD, true);
                    newDiscountTabDto.setCheckAll(BooleanConstant.getFalseFlag());
                    selectedTableLogic.loadSetData(newDiscountTabDto, session, false);
                }
            }
        });
        for (Object propertyId : componentDetailsSelectedItem.getVisibleColumns()) {
            componentDetailsSelectedItem.setColumnWidth(propertyId, NumericConstants.ONE_TWO_FIVE);
        }

        dashboardTreeTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        dashboardTreeTable.setPageLength(NumericConstants.TEN);
        dashboardTreeTable.setContainerDataSource(dashBoardTreeContainer);
        dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
        dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);
        levelDetailsResultsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        levelDetailsResultsTable.setPageLength(NumericConstants.ELEVEN);
        levelDetailsResultsTable.setContainerDataSource(componentResultsContainer);
        levelDetailsResultsTable.setVisibleColumns(Constants.getInstance().contractComponentDetailsResultsColumns);
        levelDetailsResultsTable.setColumnHeaders(Constants.getInstance().contractComponentDetailsResultsHeaders);
        componentDetailsSearchTable.setFilterFieldVisible(Constants.CHECK_RECORD, false);

    }

    private void loadDashBoardTree() {
        LOGGER.debug("Entering getProcessedTree method");
        final CommonLogic commonLogic = new CommonLogic();
        dashboardTreeTable.markAsDirty();
        dashboardTreeTable.setSizeFull();
        dashboardTreeTable.setPageLength(NumericConstants.TEN);
        dashboardTreeTable.removeAllItems();
        parentList.clear();
        levelValue = 0;
        contractMasterId = removeDiscountDto.get(0).getContractSid();

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
                tableBeanId = event.getItemId();
                BeanItem<?> targetItem = null;
                if (tableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) tableBeanId;
                } else if (tableBeanId instanceof ContractsDetailsDto) {
                    targetItem = new BeanItem<>((ContractsDetailsDto) tableBeanId);
                } 
                if(targetItem != null)
                {
                tableBean = (ContractsDetailsDto) targetItem.getBean();
                }
                levelVal = tableBean.getLevel();
            }
        });
        LOGGER.debug("End of getProcessedTree method");
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
                LOGGER.error("",ex);
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
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
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
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

    private void setProcessedTableHeader() {
        LOGGER.debug("Entering setProcessedTableHeader method");
        dashboardTreeTable.setVisibleColumns(Constants.getInstance().treeColumns);
        dashboardTreeTable.setColumnHeaders(Constants.getInstance().treeHeaders);
        LOGGER.debug("End of setProcessedTableHeader method");
    }

    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");
        String compType = String.valueOf(componentTypeddlb.getValue());
        String searchField = String.valueOf(searchDdlb.getValue() != null ? searchDdlb.getValue() : StringUtils.EMPTY);
        String sValue = searchValue.getValue();
        String ddlbValue = searchValueStatusDdlb.getValue() == null || searchValueStatusDdlb.getValue().equals(Constants.ZEROSTRING) ? StringUtils.EMPTY : String.valueOf(searchValueStatusDdlb.getValue());

        if (StringUtils.isNotBlank(searchField) && ((StringUtils.isNotBlank(sValue)) || (StringUtils.isNotBlank(ddlbValue)) || searchDatePeriod.getValue() != null)) {
            newDiscountTabDto.setSearchField(compType + "-" + searchField);
            if (searchValue.isVisible()) {
                newDiscountTabDto.setSearchFieldValue(searchValue.getValue());
            } else if (Constants.getInstance().ifpSearch[NumericConstants.TEN].equals(searchField) || Constants.getInstance().ifpSearch[NumericConstants.ELEVEN].equals(searchField) || Constants.getInstance().ifpSearch[NumericConstants.TWELVE].equals(searchField)
                    || Constants.getInstance().ifpSearch[NumericConstants.THIRTEEN].equals(searchField) || Constants.getInstance().cfpSearch[NumericConstants.ELEVEN].equals(searchField)) {
                Object ddlb = searchValueStatusDdlb.getValue();
                newDiscountTabDto.setSearchFieldValue(searchValueStatusDdlb.getItemCaption(ddlb));
            } else if (searchField.contains("Date")) {
                newDiscountTabDto.setSearchFieldValue(dbDate.format(searchDatePeriod.getValue()));
            } else {
                newDiscountTabDto.setSearchFieldValue(ddlbValue);
            }

            if (session.getSearchSessionId().isEmpty()) {
                session.setSearchSessionId(CommonUtils.createSessionId());
                if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.getConstant())) {
                    newDiscountTabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                    DiscountLogic.insertToTempTable(newDiscountTabDto, session);
                } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.getConstant())) {
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
        LOGGER.debug("Ending search method");
    }

    @UiHandler("populateBtn")
    public void populateBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered populate method");
        String massField = String.valueOf(fieldDdlb.getValue());
        String propertyId;
        newDiscountTabDto.setBulkUpdate(BooleanConstant.getTrueFlag());
        if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true,false) > 0) {
            if (massField != null && !massField.equals(Constants.NULL) && !massField.equals(StringUtils.EMPTY)) {
                if (massField.equals(Constants.STATUS_FIELD)) {
                    propertyId = Constants.STATUS_S;
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
                        AbstractNotificationUtils.getErrorNotification(Constants.NO_VALUE, Constants.NO_VALUE_ENTERED_FOR_MASS_POPULATE);
                    }
                } else if (massField.equalsIgnoreCase(Constants.START_DATE_HEADER)) {
                    propertyId = S_DATE_PROPERTY;
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
                        AbstractNotificationUtils.getErrorNotification(Constants.NO_VALUE, Constants.NO_VALUE_ENTERED_FOR_MASS_POPULATE);
                    }

                } else if (massField.contains(Constants.END_DATE_HEADER)) {
                    propertyId = Constants.E_DATE_PROPERTY;
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
                        AbstractNotificationUtils.getErrorNotification(Constants.NO_VALUE, Constants.NO_VALUE_ENTERED_FOR_MASS_POPULATE);
                    }

                } else if (massField.contains(Constants.PRICE_TYPE_LABEL)) {
                    propertyId = Constants.PRICE_TYPE_PROPERTY;
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
                        AbstractNotificationUtils.getErrorNotification(Constants.NO_VALUE, Constants.NO_VALUE_ENTERED_FOR_MASS_POPULATE);
                    }
                } else if (massField.equalsIgnoreCase("Price Protection Start Date")) {
                    propertyId = Constants.PP_S_DATE;
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
                        AbstractNotificationUtils.getErrorNotification(Constants.NO_VALUE, Constants.NO_VALUE_ENTERED_FOR_MASS_POPULATE);
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
        newDiscountTabDto.setBulkUpdate(BooleanConstant.getFalseFlag());
        LOGGER.debug("Ending populate method");
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
        LOGGER.debug("Entering addItemsButtonClick method ");
        ContractsDetailsDto newDiscountDto = null;
        if (componentDetailsSearchTable.isSelectable()) {
            Object itemId = componentDetailsSearchTable.getValue();
            if (itemId != null) {
                newDiscountDto = (ContractsDetailsDto) itemId;
                newDiscountTabDto.setCategory(newDiscountDto.getCategory());
                DiscountLogic.insertToTempTable(newDiscountDto, session);
                if (!selectedTableLogic.loadSetData(newDiscountDto, session, false)) {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());
                    AbstractNotificationUtils.getErrorNotification("No Records Found",
                            "Please select other record.");
                } else {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, BooleanConstant.getFalseFlag());
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
            if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true, true) > 0) {
                new DiscountLogic().updateDataOperation(newDiscountTabDto.getCategory(), session, true);
                if (!selectedTableLogic.loadSetData(newDiscountTabDto, session, false)) {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, BooleanConstant.getTrueFlag());
                    AbstractNotificationUtils.getErrorNotification("No Records Found",
                            "Please select other record.");
                } else {
                    componentDetailsSelectedItem.setColumnCheckBoxDisable(Constants.CHECK_RECORD, BooleanConstant.getFalseFlag());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Add", "Please check a record to Add.");
            }
        }
        LOGGER.debug("addItemsButtonClick method Ended");

    }

    @UiHandler("componentTypeddlb")
    public void componentTypeDdlbLogic(Property.ValueChangeEvent event) {
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
            searchDdlb = CommonLogic.loadNewTabSearchDdlb(searchDdlb, selectedComponenttype);
        }
        if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString()) || selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
        session.setSearchSessionId(StringUtils.EMPTY);
        }
        selectedTableLogic.resetAndLoadData(true);
        tableLogic.resetAndLoadData(true);
        loadTableHeaders();
        loadcomponentSelectionGrid();
    }

    private void loadTableHeaders() {
        if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setSelectable(false);
            componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().adSearchResultsColumnsCfp);
            componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().adSearchResultsHeadersCfp);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adSelectedResultsColumnsCfp);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adSelectedResultsHeadersCfp);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            componentDetailsSearchTable.setEditable(true);
            componentDetailsSearchTable.setSelectable(false);
            componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().adSearchResultsColumnsIfp);
            componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().adSearchResultsHeadersIfp);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adSelectedResultsColumnsIfp);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adSelectedResultsHeadersIfp);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            componentDetailsSearchTable.setEditable(false);
            componentDetailsSearchTable.setSelectable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().adIfpResultsColumns);
            componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().adIfpResultsHeaders);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adNdPsComponentDetailsColumns);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adNdPsComponentDetailsHeaders);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            componentDetailsSearchTable.setEditable(false);
            componentDetailsSearchTable.setSelectable(true);
            componentDetailsSearchTable.setVisibleColumns(Constants.getInstance().adIfpResultsColumns);
            componentDetailsSearchTable.setColumnHeaders(Constants.getInstance().adIfpResultsHeaders);
            componentDetailsSelectedItem.setVisibleColumns(Constants.getInstance().adNdRsComponentDetailsColumns);
            componentDetailsSelectedItem.setColumnHeaders(Constants.getInstance().adNdRsComponentDetailsHeaders);
            componentDetailsSearchTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentDetailsSearchTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
        }
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
        LOGGER.debug(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateEnableDisable.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.debug("massUpdate ValueChangeEvent ends ");
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
        LOGGER.debug(" FieldDdlb ValueChangeEvent initiated ");
        datePeriod.setValue(null);
        if (Constants.STATUS_FIELD.equals(String.valueOf(fieldDdlb.getValue())) || Constants.PRICE_TYPE_LABEL.equals(String.valueOf(fieldDdlb.getValue()))) {
            datePeriod.setVisible(false);
            valueDdlb.setVisible(true);
            if (Constants.STATUS_FIELD.equals(String.valueOf(fieldDdlb.getValue()))) {
                try {
                    valueDdlb.removeAllItems();
                    valueDdlb = CommonLogic.getNativeSelect(valueDdlb, itemStatusList);
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }
            if (Constants.PRICE_TYPE_LABEL.equals(String.valueOf(fieldDdlb.getValue()))) {
                try {
                    valueDdlb.removeAllItems();
                    valueDdlb = CommonLogic.getNativeSelect(valueDdlb, priceTypeList);
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }

        } else {
            valueDdlb.setVisible(false);
            datePeriod.setVisible(true);
        }

        LOGGER.debug("FieldDdlb ValueChangeEvent ends ");
    }

    private void loadcomponentSelectionGrid() {
        componentSelectionLayout.removeAllComponents();
        if (componentTypeddlb.getValue() != null) {
            if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                componentSelectionLayout.addComponent(cfpComponent);
                cfpType = commonUtil.loadComboBox(cfpType, UiUtils.CFP_TYPE, false);
                cfpStatus = CommonLogic.getNativeSelect(cfpStatus, itemStatusList);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem(Constants.STATUS_FIELD);
                fieldDdlb.addItem(Constants.START_DATE_HEADER);
                fieldDdlb.addItem(Constants.END_DATE_HEADER);
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                componentSelectionLayout.addComponent(ifpComponent);
                ifpStatus = CommonLogic.getNativeSelect(ifpStatus, itemStatusList);
                ifpType = commonUtil.loadComboBox(ifpType, UiUtils.IFP_TYPE, false);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem(Constants.STATUS_FIELD);
                fieldDdlb.addItem(Constants.START_DATE_HEADER);
                fieldDdlb.addItem(Constants.END_DATE_HEADER);
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                componentSelectionLayout.addComponent(psComponent);
                priceTypeList = CommonLogic.getPriceTypeResults();
                psStatus = CommonLogic.getNativeSelect(psStatus, itemStatusList);
                psType = commonUtil.loadComboBox(psType, UiUtils.PS_TYPE, false);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem(Constants.STATUS_FIELD);
                fieldDdlb.addItem(Constants.START_DATE_HEADER);
                fieldDdlb.addItem(Constants.END_DATE_HEADER);
                fieldDdlb.addItem(Constants.PRICE_TYPE_LABEL);
                fieldDdlb.addItem("Price Protection Start Date");
            } else if (componentTypeddlb.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                componentSelectionLayout.addComponent(rsComponent);
                fieldDdlb.removeAllItems();
                fieldDdlb.addItem(Constants.STATUS_FIELD);
                fieldDdlb.addItem(Constants.START_DATE_HEADER);
                fieldDdlb.addItem(Constants.END_DATE_HEADER);
            }
        }
    }

    @UiHandler("addToTree")
    public void addToTreeLogic(Button.ClickEvent event) {
        try {
            Object id = componentTypeddlb.getValue();
            boolean addedFlag = false;
            if (id != null) {
                String compType = String.valueOf(id);
                Collection itemList = componentDetailsSelectedItem.getItemIds();
                for (Object obj : itemList) {
                    ContractsDetailsDto dto = (ContractsDetailsDto) obj;
                    if (dto.getCheckRecord()) {
                        if ((dto.getStatus() == null || dto.getStatus() == "" || dto.getsDate() == null)) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ADD_TO_TREE, Constants.PLEASE_ENSURE_ALL_MANDATORY_FIELDS);
                            return;
                        }
                    }
                }
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
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule ID is already exist. Please enter different Rebate Schedule ID");
                            return;
                        }
                        List listNo = DiscountLogic.duplicateCheck(compType, rebateNo, "RS_NO");
                        if (listNo != null && !listNo.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule No already exists. Please enter different Rebate Schedule No");
                            return;
                        }
                        List listName = DiscountLogic.duplicateCheck(compType, rebateName, "RS_NAME");
                        if (listName != null && !listName.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Rebate Schedule Name already exists. Please enter different Rebate Schedule Name");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        rsListforMap = new ArrayList<>();
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ADD_TO_TREE, Constants.PLEASE_ENSURE_ALL_MANDATORY_FIELDS);
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
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Price Schedule Name already exist. Please Enter Different Name");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        psListforMap = new ArrayList<>();
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ADD_TO_TREE, Constants.PLEASE_ENSURE_ALL_MANDATORY_FIELDS);
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                    String itemFamilyPlanId = this.ifpId.getValue();
                    String itemFamilyPlanNo = this.ifpNo.getValue();
                    String itemFamilyPlanName = this.ifpName.getValue();
                    String itemFamilyPlanStatus = String.valueOf(this.ifpStatus.getValue() != null ? this.ifpStatus.getValue() : StringUtils.EMPTY);
                    String ifpStartdate = ifpStartDate.getValue() != null ? ifpStartDate.getValue().toString() : StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(itemFamilyPlanId) && StringUtils.isNotBlank(itemFamilyPlanNo) && StringUtils.isNotBlank(itemFamilyPlanName)
                            && StringUtils.isNotBlank(itemFamilyPlanStatus) && StringUtils.isNotBlank(ifpStartdate)) {
                        List list = DiscountLogic.duplicateCheck(compType, itemFamilyPlanId, "IFP_ID");
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Item Family Plan ID already exist. Please enter different ID.");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        ifpListforMap = new ArrayList<>();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                        saveTreeDto.setId(itemFamilyPlanId);
                        saveTreeDto.setName(itemFamilyPlanName);
                        saveTreeDto.setNumber(itemFamilyPlanNo);
                        saveTreeDto.setStatus(itemFamilyPlanStatus);
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ADD_TO_TREE, Constants.PLEASE_ENSURE_ALL_MANDATORY_FIELDS);
                    }
                } else if (compType.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.getConstant())) {
                    String companyFamilyPlanId = this.cfpId.getValue();
                    String companyFamilyPlanNo = this.cfpNo.getValue();
                    String companyFamilyPlanName = this.cfpName.getValue();
                    String companyFamilyPlanStatus = String.valueOf(this.cfpStatus.getValue() != null ? this.cfpStatus.getValue() : StringUtils.EMPTY);
                    String cfpStartdate = cfpStartDate.getValue() != null ? cfpStartDate.getValue().toString() : StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(companyFamilyPlanId) && StringUtils.isNotBlank(companyFamilyPlanNo) && StringUtils.isNotBlank(companyFamilyPlanName)
                            && StringUtils.isNotBlank(companyFamilyPlanStatus) && StringUtils.isNotBlank(cfpStartdate)) {
                        List list = DiscountLogic.duplicateCheck(compType, companyFamilyPlanId, "CFP_ID");
                        if (list != null && !list.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Company Family Plan ID already exist. Please enter different ID.");
                            return;
                        }
                        List listNo = DiscountLogic.duplicateCheck(compType, companyFamilyPlanNo, "CFP_NO");
                        if (listNo != null && !listNo.isEmpty()) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Entered Company Family Plan No already exist. Please enter different No.");
                            return;
                        }
                        saveTreeDto = new ContractsDetailsDto();
                        saveTreeDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                        saveTreeDto.setId(companyFamilyPlanId);
                        saveTreeDto.setName(companyFamilyPlanName);
                        saveTreeDto.setNumber(companyFamilyPlanNo);
                        saveTreeDto.setStatus(companyFamilyPlanStatus);
                        saveTreeDto.setStartDate(cfpStartdate);
                        saveTreeDto.setLevel(ContractsDetailsDto.LEVEL2);
                        saveTreeDto.setSystemId(contractMasterId);
                        saveTreeDto.setAttachedList(new DiscountLogic().getSelectedTableData(saveTreeDto.getCategory(), session));
                        saveTreeDto.setSearchSessionId(session.getSearchSessionId());
                        if (addToTreeMethod(saveTreeDto)) {
                            cfpList.add(saveTreeDto);
                            addedFlag = true;
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ADD_TO_TREE, Constants.PLEASE_ENSURE_ALL_MANDATORY_FIELDS);
                    }
                }

                if (addedFlag) {
                    tableLogic.resetAndLoadData(true);
                    selectedTableLogic.resetAndLoadData(true);
                }
            }

        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }
    
    private boolean addToTreeMethod(ContractsDetailsDto srcTableBean) {

        ContractsDetailsDto treeBean = dashboardTreeTable.getValue() == null ? null : getBeanFromID(dashboardTreeTable.getValue());
        Object treeBeanId = dashboardTreeTable.getValue() == null ? null : dashboardTreeTable.getValue();
        boolean returnFlag = false;
        DiscountLogic discountLogic = new DiscountLogic();
        String userId = session.getUserId();
        String sessionId = session.getSearchSessionId();
        if (treeBean == null) {
            if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.getConstant())) {

                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, true);
                returnFlag = true;
            } else {
                final String message = "Cannot make a " + srcTableBean.getCategory() + " as contracts header";
                AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, message);
            }
        } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.CONTRACT.getConstant())) {
            final String message = "Cannot make a " + srcTableBean.getCategory() + " as child node";
            AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, message);
        } else if (srcTableBean.getCategory().equals(treeBean.getCategory())) {
            final String message = srcTableBean.getCategory() + " cannot be added to  " + treeBean.getCategory();
            AbstractNotificationUtils.getWarningNotification(Constants.CRITERIA_MISMATCH, message);
        } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
            final DynamicQuery rsDynamicQuery = IfpContractDetailsLocalServiceUtil.dynamicQuery();
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid", treeBean.getInternalId()));
            rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.ITEM_MASTER_SID_PROPERTY)));
            final List<Object> itemsList = IfpContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
            String query1 = "getItemForIFPInNewDiscount";
            String query2 = "getItemForPsInNewDiscount";
            
            if ((treeBean.getInternalId() == 0 && !discountLogic.checkSameItemInPs(query1, query2, userId, sessionId)) || (treeBean.getInternalId() != 0 && !discountLogic.checkSameItemInPs(query2, itemsList, userId, sessionId))) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_THE_SAME_ITEMS);
            } else {
                if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                    final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                    for (final Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
                        final Object childId = iterator.next();
                        final ContractsDetailsDto object = getBeanFromID(childId);
                        String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                        String dest = object.getId() + object.getName() + object.getNumber();
                        if (src.equals(dest)) {
                            final String messageStr = srcTableBean.getCategory() + "Already Added";
                            AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, messageStr);
                            return false;
                        }
                    }
                }
                saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), psListforMap);
                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, true);
                returnFlag = true;
            }
        } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.getConstant()) && treeBean.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
            LOGGER.debug("Inside Expected Code");
            final DynamicQuery rsDynamicQuery1 = IfpContractDetailsLocalServiceUtil.dynamicQuery();
            rsDynamicQuery1.add(RestrictionsFactoryUtil.eq("ifpContractSid", treeBean.getInternalId()));
            rsDynamicQuery1.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.ITEM_MASTER_SID_PROPERTY)));
            final List<Object> itemsList = IfpContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery1);
            final DynamicQuery rsDynamicQuery = RsDetailsLocalServiceUtil.dynamicQuery();
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", srcTableBean.getInternalId()));
            rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("ifpModelSid")));
            RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
            String query1 = "getItemForIFPInNewDiscount";
            String query2 = "getItemForRsInNewDiscount";
            if ((treeBean.getInternalId() == 0 && !discountLogic.checkSameItemInPs(query1, query2, userId, sessionId)) || (treeBean.getInternalId() != 0 && !discountLogic.checkSameItemInPs(query2, itemsList, userId, sessionId))) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_THE_SAME_ITEMS);
            } else {
                if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                    final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                    for (final Object id : collection) {
                        final ContractsDetailsDto object = getBeanFromID(id);
                        String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                        String dest = object.getId() + object.getName() + object.getNumber();
                        if (src.equals(dest)) {
                            final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                            AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                            return false;
                        }
                    }
                }
                saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBean, false);
                returnFlag = true;
            }
        } else if (srcTableBean.getCategory().equals(Constants.IndicatorConstants.RS_VALUE.getConstant()) && treeBean.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
            final DynamicQuery rsDynamicQuery = PsContractDetailsLocalServiceUtil.dynamicQuery();
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid", treeBean.getInternalId()));
            rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.ITEM_MASTER_SID_PROPERTY)));
            final List<Object> itemsList = PsContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
            String query1 = "getItemForPsInNewDiscount";
            String query2 = "getItemForRsInNewDiscount";
            if ((treeBean.getInternalId() == 0 && !discountLogic.checkSameItemInPs(query1, query2, userId, sessionId)) || (treeBean.getInternalId() != 0 && !discountLogic.checkSameItemInPs(query2, itemsList, userId, sessionId))) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, Constants.SELECT_THE_SAME_ITEMS);
            } else {
                final DynamicQuery psDynamicQuery = PsDetailsLocalServiceUtil.dynamicQuery();
                psDynamicQuery.add(RestrictionsFactoryUtil.eq("psModelSid", treeBean.getModelSysId()));
                psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("ifpModelSid")));
                PsDetailsLocalServiceUtil.dynamicQuery(psDynamicQuery);
                if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                    final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                    for (final Object id : collection) {
                        final ContractsDetailsDto object = getBeanFromID(id);
                        String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                        String dest = object.getId() + object.getName() + object.getNumber();
                        if (src.equals(dest)) {
                            final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                            AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                            return false;
                        }
                    }
                }
                saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), rsListforMap);
                setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId, false);
                returnFlag = true;
            }
        } else {
            if (dashBoardTreeContainer.hasChildren(treeBeanId)) {
                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(treeBeanId);
                for (final Object id : collection) {
                    final ContractsDetailsDto object = getBeanFromID(id);
                    String src = srcTableBean.getId() + srcTableBean.getName() + srcTableBean.getNumber();
                    String dest = object.getId() + object.getName() + object.getNumber();
                    if (src.equals(dest)) {
                        final String message = srcTableBean.getCategory() + Constants.ALREADY_ADDED;
                        AbstractNotificationUtils.getWarningNotification(Constants.DUPLICATE_CRITERIA, message);
                        return false;
                    }
                }
            }
            if ("IFP".equals(srcTableBean.getCategory())) {
                saveTreeDto.addStringProperties(treeBean.getId() + treeBean.getName() + treeBean.getNumber(), ifpListforMap);
            }
            setTreeNode(srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId, true);
            returnFlag = true;
        }
        return returnFlag;
    }

    private ContractsDetailsDto getBeanFromID(final Object tableID) {
        BeanItem<?> targetItem = new BeanItem<>(tableID);
        if (tableID instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableID;
        } else if (tableID instanceof ContractsDetailsDto) {
            targetItem = new BeanItem<>((ContractsDetailsDto) tableID);
        } 
        return (ContractsDetailsDto) targetItem.getBean();
    }

    private void setTreeNode(final ContractsDetailsDto bean, final VerticalDropLocation location, final Object targetItemId, boolean childrenAllowed) {

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
                bean.setInternalId(0);
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
                dashBoardTreeContainer.setChildrenAllowed(bean, childrenAllowed);
                dashBoardTreeContainer.setParent(bean, targetItemId);
            }

        } // Drop at the top of a subtree -> make it previous
        else if (location == VerticalDropLocation.TOP || location == VerticalDropLocation.BOTTOM) {
            AbstractNotificationUtils.getWarningNotification("Drop Criteria", "Drop the child node on the parent node");
            return;
        } 
        LOGGER.debug("End of setTreeNode method");
    }

    @UiHandler("levelPopulateBtn")
    public void levelPopulateBtnClick(Button.ClickEvent event) {
        if (dashboardTreeTable.getValue() != null) {
            ContractsDetailsDto temp = tableBean;
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
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(CFPComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForCFP(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromCfpCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getAdcomponentDetailsCompanyColumn());
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getAdcomponentDetailsCompanyHeader());
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadIfpFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForIFP(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromIfpCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getAdcomponentDetailsPsColumn());
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getAdcomponentDetailsPsHeader());
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadPsFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForPS_RS(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromPsCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getAdcomponentDetailsPsColumn());
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getAdcomponentDetailsPsHeader());
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    private void loadRsFromCD(final ContractsDetailsDto parent, int internalId, String userid, String sessionid) {
        levelDetailsResultsTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        if (internalId == 0) {
            levelDetailsResultsTable.addItems(new DiscountLogic().getDiscountItemsForPS_RS(userid, sessionid, parent.getAttachedList()));
        } else {
            levelDetailsResultsTable.addItems(new DiscountLogic().getFromRsCD(parent));
        }
        levelDetailsResultsTable.setVisibleColumns(HeaderUtil.getAdcomponentDetailsPsColumn());
        levelDetailsResultsTable.setColumnHeaders(HeaderUtil.getAdcomponentDetailsPsHeader());
        levelDetailsResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        levelDetailsResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
    }

    @UiHandler("levelRemoveBtn")
    public void levelRemoveBtnLogic(Button.ClickEvent event) {

        LOGGER.debug(" buttonClick ( ClickEvent event ) name= {} " , event.getButton().getCaption());
        if (dashBoardTreeContainer.getItemIds().size() > Constants.ZERO) {
            if (dashboardTreeTable.getValue() == null) {
                AbstractNotificationUtils.getWarningNotification(Constants.REMOVE_HEADER, "Please highlight a component to Remove.");
            } else {
                final Collection<Object> collection = (Collection<Object>) dashBoardTreeContainer.getChildren(tableBeanId);
                if (collection == null) {
                    ContractsDetailsDto ob = (ContractsDetailsDto) dashboardTreeTable.getValue();
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

                    } else {
                        AbstractNotificationUtils.getWarningNotification(Constants.REMOVE_HEADER, "You can remove only newly added Rebates.");
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
        String searchSessionId=  session.getSearchSessionId();
        boolean check = false;
        final Collection idList = dashBoardTreeContainer.getItemIds();
        String[] level = {Constants.IndicatorConstants.CONTRACT.toString(),
            Constants.IndicatorConstants.CFP.toString(),
            Constants.IndicatorConstants.IFP.toString(),
            Constants.IndicatorConstants.PS_VALUE.toString(),
            Constants.IndicatorConstants.RS_VALUE.toString()};
        session.setSearchSessionId(searchSessionId);
        check = checkForAllLevels(dashboardTreeTable.rootItemIds(), level, 0);
        if (check) {
            MessageBox.showPlain(Icon.QUESTION, "Create", "Are you sure you want to save the contract ?", new MessageBoxListener() {
                @Override
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals("YES")) {
                        try {
                            int rsModelSysId = 0;

                           if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true,false) > 0) {
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
                                notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                                notif.show(Page.getCurrent());
                            } else {
                                AbstractNotificationUtils.getErrorNotification("No Records Selected",
                                        "There were no records Selected from Results Table.  Please Select Record.");
                            }
                        } catch (Exception ex) {
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

    public void saveTree(final Collection list, int rsModelSysId, String category) {
        LOGGER.debug("Entering saveTree method");
        try {

            for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                final Object saveTreeIdValue = iterator.next();
                final ContractsDetailsDto tempBeanId = getBeanFromID(saveTreeIdValue);

                if (Constants.IndicatorConstants.CONTRACT.toString().equalsIgnoreCase(tempBeanId.getCategory())) {
                    contract = tempBeanId;
                    cfp = new ContractsDetailsDto();
                    ifp = new ContractsDetailsDto();
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                }
                if (Constants.IndicatorConstants.CFP.toString().equalsIgnoreCase(tempBeanId.getCategory())) {

                    cfp = tempBeanId;
                    ifp = new ContractsDetailsDto();
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.CFP.getConstant())) {
                        cfp.setModelSysId(rsModelSysId);
                        cfp.setCfpId(rsModelSysId);
                        DiscountLogic.saveCFp(contract.getInternalId(), cfp ,true);
                    }
                }
                if (Constants.IndicatorConstants.IFP.toString().equalsIgnoreCase(tempBeanId.getCategory())) {
                    ifp = tempBeanId;
                    priceSchedule = new ContractsDetailsDto();
                    rebateSchedule = new ContractsDetailsDto();
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.IFP.getConstant())) {
                        ifp.setModelSysId(rsModelSysId);
                        ifp.setIfpId(rsModelSysId);
                        DiscountLogic.saveIFP(contract.getInternalId(), cfp.getCfpContractId(), ifp ,true);
                    }
                }
                if (Constants.IndicatorConstants.PS_VALUE.toString().equalsIgnoreCase(tempBeanId.getCategory())) {
                    priceSchedule = tempBeanId;
                    rebateSchedule = new ContractsDetailsDto();
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
                        priceSchedule.setModelSysId(rsModelSysId);
                        priceSchedule.setPsSid(String.valueOf(rsModelSysId));
                        DiscountLogic.savePS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule ,true);
                    }
                }
                if (Constants.IndicatorConstants.RS_VALUE.toString().equalsIgnoreCase(tempBeanId.getCategory())) {
                    rebateSchedule = tempBeanId;
                    if (category.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.getConstant())) {
                        rebateSchedule.setRsSid(String.valueOf(rsModelSysId));
                        int internalId = tempBeanId.getInternalId();
                        if (internalId == 0) {
                            DiscountLogic.saveRS(contract.getInternalId(), cfp.getCfpContractId(), ifp.getIfpContractId(), priceSchedule.getPsContractId(), rebateSchedule ,true);
                        }
                    }
                }

                final Collection childlist = dashboardTreeTable.getChildren(saveTreeIdValue);
                if (childlist != null && !(childlist.isEmpty())) {
                    saveTree(childlist, rsModelSysId, category);
                } 
                    
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        LOGGER.debug("End of saveTree method");
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
        LOGGER.debug(" buttonClick ( ClickEvent event ) name={} " , event.getButton().getCaption());
        if (DiscountLogic.getCountForNewDiscountSelectedItems(newDiscountTabDto, session, true,false) > 0) {
            if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
            } else if (selectedComponenttype.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                newDiscountTabDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
            }
            DiscountLogic.removeSelectedItems(newDiscountTabDto, session);
            selectedContainer.removeAllItems();
            selectedContainer.addAll(contListafterRemove);
            contListafterRemove.clear();
            selectedTableLogic.loadSetData(newDiscountTabDto, session, false);
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.REMOVE_HEADER, "Please check a record to Remove.");
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
    public void searchFieldDdlb(Property.ValueChangeEvent event) {
        searchValue.setValue(StringUtils.EMPTY);
        if (event != null) {

            String value = String.valueOf(event.getProperty().getValue());

            if (Constants.COMPANY_ID.equals(value) || Constants.COMPANYNAME.equals(value) || Constants.COMPANYNO.equals(value)
                    || Constants.ITEM_ID.equals(value) || Constants.ITEM_NAME.equals(value) || Constants.ITEM_NO.equals(value)
                    || Constants.IFP_ID.equals(value) || Constants.IFP_NAME_LABEL.equals(value) || Constants.IFP_NO.equals(value)) {
                searchValue.setValue(StringUtils.EMPTY);
                searchValue.setVisible(true);
                searchValueStatusDdlb.setVisible(false);
                searchDatePeriod.setVisible(false);

            } else if (Constants.COMPANYSTATUS.equals(value) || Constants.ITEM_STATUS.equals(value) || Constants.IFP_STATUS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                searchValueStatusDdlb = CommonLogic.getNativeSelect(searchValueStatusDdlb, itemStatusList);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.COMPANYTYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.COMPANY_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.ITEM_TYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.ITEM_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.IFPTYPE.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.IFP_TYPE, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.COMPANYCATEGORY.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.COMPANY_CATEGORY, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.TRADECLASS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.COMPANY_TRADE_CLASS, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.BRAND.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                List<HelperDTO> temp;
                temp = CommonLogic.getBrand("ad.getBrand");
                searchValueStatusDdlb = CommonLogic.getNativeSelect(searchValueStatusDdlb, temp);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.FORM.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.FORM, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.STRENGTH.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.STRENGTH, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.THERAPY_CLASS.equals(value)) {
                searchValueStatusDdlb.removeAllItems();
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(false);
                commonUtil.loadComboBox(searchValueStatusDdlb, UiUtils.THERAPEUTIC_CLASS, false);
                searchValueStatusDdlb.setVisible(true);
            } else if (Constants.ITEM_START_DATE.equals(value) || Constants.ITEM_END_DATE.equals(value)) {
                searchValue.setVisible(false);
                searchDatePeriod.setVisible(true);
                searchValueStatusDdlb.setVisible(false);
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

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHMPermission = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Add Discount", "NewDiscount Screen");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHMPermission));
            addBtn.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", functionHMPermission));
            levelRemoveBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelRemoveBtn", functionHMPermission));
            levelPopulateBtn.setVisible(CommonLogic.isButtonVisibleAccess("levelPopulateBtn", functionHMPermission));
            removeBtn.setVisible(CommonLogic.isButtonVisibleAccess("removeBtn", functionHMPermission));
            addToTree.setVisible(CommonLogic.isButtonVisibleAccess("addToTree", functionHMPermission));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
