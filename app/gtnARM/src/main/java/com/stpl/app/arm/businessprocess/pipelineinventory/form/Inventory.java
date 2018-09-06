
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.lookups.InventoryCustomerGroupLookup;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.lookups.InventoryCustomerLookup;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.InventoryLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryLookupLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.ui.themes.Reindeer;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import org.asi.ui.customcombobox.CustomComboBox;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import java.util.Arrays;
import java.util.Calendar;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.custommenubar.MenuItemDTO;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.supercode.LeaveConfirmMessageAble;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.ifs.ui.util.NumericConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class Inventory extends VerticalLayout implements View, GenerateAble, DefaultFocusable, LeaveConfirmMessageAble, LeaveCheckAble {

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    private String[] variableValues;
    private CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("inventoryLevel")
    protected OptionGroup inventoryLevel;
    @UiField("inventoryCalculation")
    protected Button inventoryCalculation;
    @UiField("reset")
    private Button reset;
    @UiField("generate")
    private Button generate;
    @UiField("inventoryDetailsDdlb")
    private CustomComboBox inventoryDetailsDdlb;
    @UiField("price")
    private CustomComboBox price;
    @UiField("reserveDate")
    private CustomComboBox reserveDate;

    private InventoryLogic logic = new InventoryLogic();
    private DataSelectionDTO dataselection;
    private String[] variableVisibleColumns;
    private AbstractSelectionDTO selectionDto;
    private InventorySearchResults inventoryResults;
    private InventoryCustomerLookup inventoryCustomerLookup;
    private int projectionId = 0;
    private InventoryCustomerGroupLookup inventoryCustomerGroupLookup;
    private int cgViewSid = 0;
    private boolean arcCgInitialView = false;
    private int custViewSid = 0;
    private boolean arcCustInitialView = false;
    private boolean isReset = false;
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(Inventory.class);

    public Inventory(DataSelectionDTO dataselection, AbstractSelectionDTO selectionDto, int projectionId) {
        this.dataselection = dataselection;
        this.selectionDto = selectionDto;
        this.projectionId = projectionId;
        init();
        configureWorkFlow();

    }

    private void init() {
        inventoryResults = new InventorySearchResults(logic, selectionDto);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/Inventory/inventory.xml"), this));
        inventoryCalculation.addStyleName(Reindeer.BUTTON_LINK);
        inventoryLevel.addItem(VariableConstants.CUSTOMER);
        inventoryLevel.addItem(VariableConstants.CUSTOMER_GROUP);
        inventoryLevel.select(VariableConstants.CUSTOMER_GROUP);
        inventoryLevel.addValueChangeListener(frequencyListener);
        inventoryResults.getResults();
        addComponent(inventoryResults);

        CommonUtils.loadRatePeriodComboBox(price, 1, CommonConstant.ARM_PERIOD_BASIS);
        configureFields();
        configureDblbLoading();
        setDefaultValue();
        generate();
        reset();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method Of Inventory Class");

    }

    @UiHandler("inventoryCalculation")
    public void inventoryCalculationLookup(Button.ClickEvent event) {

        if (inventoryLevel.getValue().equals(VariableConstants.CUSTOMER)) {
            if (inventoryCustomerLookup == null) {
                inventoryCustomerLookup = new InventoryCustomerLookup(projectionId, selectionDto);
            } else {
                inventoryCustomerLookup.reloadScreen(projectionId);
            }

            if (arcCustInitialView) {
                inventoryCustomerLookup.loadInitialArc(String.valueOf(custViewSid), VariableConstants.CUSTOMER);
            }

            UI.getCurrent().addWindow(inventoryCustomerLookup);
            inventoryCustomerLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {

                    if (inventoryCustomerLookup.isSubmitted()) {
                        loadCustomMenuBar(inventoryCustomerLookup.getCustomerGroupList());
                        inventoryCustomerLookup.setSubmitted(false);
                        arcCustInitialView = false;
                    }
                }
            });
        } else if (inventoryLevel.getValue().equals(VariableConstants.CUSTOMER_GROUP)) {
            inventoryCustomerGroupLookup = new InventoryCustomerGroupLookup(projectionId, selectionDto);

            if (arcCgInitialView) {
                inventoryCustomerGroupLookup.loadInitialArc(String.valueOf(cgViewSid), VariableConstants.CUSTOMER_GROUP);
            }

            UI.getCurrent().addWindow(inventoryCustomerGroupLookup);
            inventoryCustomerGroupLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (inventoryCustomerGroupLookup.isSubmitted()) {
                        loadCustomMenuBar(inventoryCustomerGroupLookup.getCustomerListGroup());
                        inventoryCustomerGroupLookup.setSubmitted(false);
                        arcCgInitialView = false;
                    }
                }
            });
        }

    }

    private Property.ValueChangeListener frequencyListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                if (event.getProperty().getValue().equals(VariableConstants.CUSTOMER)) {
                    loadCustomMenuBar(selectionDto.getCustomerList());
                } else if (event.getProperty().getValue().equals(VariableConstants.CUSTOMER_GROUP) && (!selectionDto.getCustomerGroupList().isEmpty())) {
                    loadCustomMenuBar(selectionDto.getCustomerGroupList());
                }
            } catch (Exception e) {
                LOGGER.error("Error in frequencyListener :", e);
            }
        }
    };

    public void loadCustomMenuBar(List<String> listCustomValue) {
        String[] customerSelectionList = new String[listCustomValue.size()];
        customerSelectionList = listCustomValue.toArray(customerSelectionList);
        variableValues = VariableConstants.PipelineInventoryVariables.names();
        variableVisibleColumns = VariableConstants.getVariableInventoryVisibleColumn();
        customMenuBar.setScrollable(true);
        if (listCustomValue.size() > NumericConstants.TWENTY) {
            customMenuBar.setPageLength(NumericConstants.TWENTY);
        } else {
            customMenuBar.setPageLength(listCustomValue.size() + variableValues.length);
        }

        String[] selectionHeader = (String[]) ArrayUtils.addAll(customerSelectionList, variableValues);
        String[] selectionColumn = (String[]) ArrayUtils.addAll(customerSelectionList, variableVisibleColumns);
        variableVisibleColumns = selectionColumn;
        variableValues = selectionHeader;
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableVisibleColumns.length];
        customMenuItem.removeChildren();
        if (selectionHeader.length != 0 && selectionColumn.length != 0) {
            for (int i = 0; i < selectionHeader.length; i++) {
                MenuItemDTO dto;
                if (selectionColumn[i].contains("~")) {
                    dto = new MenuItemDTO(selectionColumn[i].trim(), selectionColumn[i].split("~")[0].trim());
                } else {
                    dto = new MenuItemDTO(selectionColumn[i].trim(), selectionHeader[i].trim());
                }
                customItem[i] = customMenuItem.addItem(dto, null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }

        }
    }

    private final CustomNotification notifier = new CustomNotification();

    private void configureDblbLoading() {
        List<String> inventoryDetailsList = getPeriodsByFrequencyForMonth();
        List<Object> defaultValues = logic.getMonthYear();
        Integer vvalue = Integer.valueOf(String.valueOf(defaultValues.get(1)));
        String month = InventoryLogic.getMonthName(vvalue);
        String str = month + " " + defaultValues.get(NumericConstants.TWO);
        priceddlb = CommonUtils.getPeriodsByFrequency("M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), str);
        price.removeAllItems();
        price.setContainerDataSource(new IndexedContainer(priceddlb));
        List<String> reserveDatelist = getPeriodsByFrequencyForMonthPrice();
        reserveDate.removeAllItems();
        reserveDate.setContainerDataSource(new IndexedContainer(reserveDatelist));
        inventoryDetailsDdlb.removeAllItems();
        inventoryDetailsDdlb.setContainerDataSource(new IndexedContainer(inventoryDetailsList));
    }

    private void configureFields() {
        customMenuBar.setScrollable(true);
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        loadFixedCustomMenuBar();
        inventoryDetailsDdlb.setNullSelectionAllowed(false);
        reserveDate.setNullSelectionAllowed(false);
    }

    public void loadMenu(List<CustomerGroupDTO> listCustomValue) {
        List<String> selectionList = new ArrayList<>();
        if (inventoryLevel.getValue().equals(VariableConstants.CUSTOMER)) {
            selectionList.add("ESI-RETAIL");
        }
        String[] stockArr = new String[selectionList.size()];
        stockArr = selectionList.toArray(stockArr);
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.FOUR);
        variableValues = VariableConstants.PipelineInventoryVariables.names();
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        variableVisibleColumns = VariableConstants.getVariableInventoryVisibleColumn();
        String[] both = (String[]) ArrayUtils.addAll(stockArr, variableValues);
        String[] both1 = (String[]) ArrayUtils.addAll(stockArr, variableVisibleColumns);
        variableVisibleColumns = both1;
        variableValues = both;
        loadCustomMenu(customMenuItem, both, both1, listCustomValue);
    }

    public static void loadCustomMenu(CustomMenuBar.CustomMenuItem customMenuItem, String[] variableHeader, String[] variableProperty, List<CustomerGroupDTO> listCustomValue) {
        if (variableHeader.length != 0 && variableProperty.length != 0) {
            customMenuItem.removeChildren();
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableHeader.length + listCustomValue.size()];
            for (int i = 0; i < listCustomValue.size(); i++) {
                CustomerGroupDTO dtoValue = listCustomValue.get(i);
                MenuItemDTO dto = new MenuItemDTO(dtoValue.getCustomerGroupSid(), dtoValue.getCustomerGroupName());
                customItem[i] = customMenuItem.addItem(dto, null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }
            for (int i = 0; i < variableHeader.length; i++) {
                MenuItemDTO dto = new MenuItemDTO(variableProperty[i].trim(), variableHeader[i].trim());
                customItem[i] = customMenuItem.addItem(dto, null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }
        }
    }

    class CustomNotification extends AbstractNotificationUtils {

        public CustomNotification() {
            LOGGER.debug("Empty Constructor");
        }

        private String buttonName;

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case CommonConstant.RESET:
                        inventoryLevel.select(VariableConstants.CUSTOMER_GROUP);
                        setDefaultValue();
                        if (isReset) {
                            loadFixedCustomMenuBar();
                            logic.resetCustomerGroupValue(projectionId, selectionDto);
                            if (inventoryCustomerLookup != null) {
                                inventoryCustomerLookup.resetFields();
                            }
                            if (inventoryCustomerGroupLookup != null) {
                                inventoryCustomerGroupLookup.resetFields();
                            }
                            isReset = false;
                        }

                        CommonUtils.unCheckMenuBarItem(customMenuItem);
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    public void reset() {
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    notifier.setButtonName(CommonConstant.RESET);
                    notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
                } catch (Exception e) {
                    LOGGER.error("Error in reset :", e);
                }
            }
        });
    }

    public void generate() {
        generate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                LOGGER.debug("Inside generate ButtonClick Btn");
                try {

                    inventoryResults.setValueChangeAllowed(false);
                    selectionDto.setVariableVisibleColumns(variableVisibleColumns);
                    selectionDto.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
                    selectionDto.setInventoryDetails((String) inventoryDetailsDdlb.getValue());
                    selectionDto.setInventoryreserveDate(reserveDate.getValue().toString());
                    selectionDto.setPrice(CommonUtils.getPeriodValue('M', price.getValue().toString()));
                    selectionDto.setInventoryOptionGroup(String.valueOf(inventoryLevel.getValue()));
                    if (validateGenearteButton(selectionDto)) {
                        AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_Demand());
                    } else {
                        inventoryResults.generateButtonLogic(selectionDto);
                    }
                    inventoryResults.setValueChangeAllowed(true);
                } catch (Exception e) {
                    LOGGER.error("Error in generate :", e);
                }
            }
        });
    }

    private boolean validateGenearteButton(AbstractSelectionDTO selectionDto) {
        if (GlobalConstants.getSelectOne().equals(selectionDto.getInventoryDetails())
                || GlobalConstants.getSelectOne().equals(selectionDto.getPrice())
                || GlobalConstants.getSelectOne().equals(selectionDto.getInventoryreserveDate())
                || selectionDto.getSalesVariables() == null
                || selectionDto.getSalesVariables().isEmpty()) {
            return true;
        } else if (VariableConstants.CUSTOMER.equals(selectionDto.getInventoryOptionGroup())) {
            if (null == selectionDto.getCustomerList() || selectionDto.getCustomerList().isEmpty()) {
                return true;
            }
        } else if (selectionDto.getCustomerGroupList() == null || selectionDto.getCustomerGroupList().isEmpty()) {
            return true;
        }
        return false;
    }

    private void setDefaultValue() {
        List<String> defaultValues = logic.getRateConfigSettings(new ArrayList<>(Arrays.asList(dataselection.getCompanyMasterSid(), dataselection.getBucompanyMasterSid(), dataselection.getAdjustmentId(), StringUtils.isNotBlank(dataselection.getFromPeriodMonth()) ? CommonUtils.getMonthNo(dataselection.getFromPeriodMonth().trim().split(" ")[0]) : 1)));// Changed for GAL-6120
        if (!defaultValues.isEmpty()) {
            getDefaultValues(defaultValues);
            if (!"0".equals(defaultValues.get(NumericConstants.TEN))) {
                boolean isCust = VariableConstants.CUSTOMER.equalsIgnoreCase(HelperListUtil.getInstance().getIdDescMap().get(defaultValues.get(NumericConstants.TEN)));
                if (isCust) {
                    inventoryLevel.select(VariableConstants.CUSTOMER);
                } else {
                    inventoryLevel.select(VariableConstants.CUSTOMER_GROUP);
                }

                if (!isCust && !"0".equals(defaultValues.get(NumericConstants.NINE))) {
                    cgViewSid = Integer.valueOf(defaultValues.get(NumericConstants.NINE));
                    List<CustomerGroupDTO> cgDtoList = new ArrayList<>();
                    List<String> cgList = logic.getCustomerGroupView(String.valueOf(defaultValues.get(NumericConstants.NINE)), cgDtoList);
                    selectionDto.setCustomerGroupList(cgList);
                    loadCustomMenuBar(cgList);
                    if (!cgDtoList.isEmpty()) {
                        initializeArcLoad(cgDtoList, projectionId, selectionDto);
                        arcCgInitialView = true;
                    }
                }
                if (isCust && !"0".equals(defaultValues.get(NumericConstants.NINE))) {
                    custViewSid = Integer.valueOf(defaultValues.get(NumericConstants.NINE));
                    List<CustomerGroupDTO> cgDtoList = new ArrayList<>();
                    List<String> customerList = logic.getCustomerView(String.valueOf(defaultValues.get(NumericConstants.NINE)), cgDtoList);
                    selectionDto.setCustomerList(customerList);
                    loadCustomMenuBar(customerList);
                    if (!cgDtoList.isEmpty()) {
                        initializeArcLoad(cgDtoList, projectionId, selectionDto);
                        arcCustInitialView = true;
                    }
                }
            } else {
                isReset = true;
            }
        }
    }

    public static List<String> getPeriodsByFrequency(String frequency) {
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        if (StringUtils.isNotBlank(frequency)) {
            String freq = frequency.trim().toUpperCase(Locale.ENGLISH);
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -NumericConstants.SIX);
            String year;
            int month;
            String quarter;
            String semi;
            int iterationCountWithS = freq.startsWith("S") ? NumericConstants.EIGHT : NumericConstants.FOUR;
            int iterationCountWithQ = freq.startsWith("Q") ? NumericConstants.SIXTEEN
                    : iterationCountWithS;
            int iterationCount = freq.startsWith("M") ? NumericConstants.TWELVE : iterationCountWithQ;
            int incrementWithS = freq.startsWith("S") ? NumericConstants.SIX : NumericConstants.TWELVE;
            int incrementWithQ = freq.startsWith("Q") ? NumericConstants.THREE
                    : incrementWithS;
            int increment = freq.startsWith("M") ? 1 : incrementWithQ;
            for (int i = 0; i <= iterationCount; i++) {
                year = String.valueOf(calendar.get(Calendar.YEAR));
                month = calendar.get(Calendar.MONTH);
                quarter = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.THREE + 1);
                semi = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.SIX + 1);
                String periodWithS = freq.startsWith("S") ? "S" + semi + " " + year : year;
                String periodWithQ = freq.startsWith("Q") ? "Q" + quarter + " " + year
                        : periodWithS;
                String period = freq.startsWith("M") ? months[month] + " " + year : periodWithQ;
                calendar.add(Calendar.MONTH, increment);
                periodList.add(period);
            }
        }

        return periodList;
    }

    public static List<String> getPeriodsByFrequencyForMonth() {
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -NumericConstants.SIX);
        String year;
        int month;
        for (int i = 0; i <= NumericConstants.NINE; i++) {
            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = calendar.get(Calendar.MONTH);
            String period = months[month] + " " + year;
            calendar.add(Calendar.MONTH, NumericConstants.ONE);
            periodList.add(period);
        }
        return periodList;
    }

    private void configureWorkFlow() {
        if (selectionDto.getSessionDTO().isWorkFlow()) {
            inventoryResults.setValueChangeAllowed(false);
            List<Object[]> list = CommonLogic.loadPipelineAccrual(projectionId);
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                    try {
                        BeanUtils.setProperty(selectionDto, String.valueOf(obj[0]), obj[1]);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        LOGGER.error("Error in configureWorkFlow :", ex);
                    }
                }
            }
            List<Object[]> customerList = CommonLogic.loadCustomerOptionGroup(projectionId);
            List<String> customerListValue = new ArrayList();
            for (int i = 0; i < customerList.size(); i++) {
                Object[] obj = customerList.get(i);
                customerListValue.add(obj[0] + "~" + obj[1]);
            }
            if (VariableConstants.CUSTOMER_GROUP.equals(selectionDto.getInventoryOptionGroup())) {
                selectionDto.setCustomerGroupList(customerListValue);
            } else {
                selectionDto.setCustomerList(customerListValue);
            }
            loadCustomMenuBar(customerListValue);
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if ("salesvariables".equals(String.valueOf(obj[0]))) {
                    String str1 = (String) obj[1];
                    String[] str2 = str1.split(",");
                    String str3 = null;
                    for (String strings : str2) {
                        str3 = strings;
                        CommonUtils.checkMenuBarItem(customMenuItem, str3);
                    }
                }
            }
            loadDetails();
            selectionDto.setVariableVisibleColumns(variableVisibleColumns);
            selectionDto.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
            inventoryResults.generateButtonLogic(selectionDto);
            if (ARMUtils.VIEW_SMALL.equals(selectionDto.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            inventoryResults.setValueChangeAllowed(true);
        }
    }

    private void loadDetails() {
        HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        CommonUtils.loadRatePeriodComboBox(price, 1, CommonConstant.ARM_PERIOD_BASIS);
        CommonUtils.loadRatePeriodComboBox(reserveDate, 1, CommonConstant.ARM_PERIOD_BASIS);
        List<Object> defaultValues = logic.getMonthYear();
        Integer vvalue = Integer.valueOf(String.valueOf(defaultValues.get(1)));
        String month = InventoryLogic.getMonthName(vvalue);
        String str = month + " " + defaultValues.get(NumericConstants.TWO);
        List<String> priceddlbList = CommonUtils.getPeriodsByFrequency("M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), str);
        price.removeAllItems();
        price.setContainerDataSource(new IndexedContainer(priceddlbList));
        price.setValue(selectionDto.getPrice());
        List<String> reserveDatelist = getPeriodsByFrequencyForMonthPrice();
        List<String> inventoryDetailsDdlbList = getPeriodsByFrequencyForMonth();
        reserveDate.removeAllItems();
        reserveDate.setContainerDataSource(new IndexedContainer(reserveDatelist));
        reserveDate.setValue(selectionDto.getInventoryreserveDate());
        reserveDate.setNullSelectionAllowed(false);
        inventoryDetailsDdlb.removeAllItems();
        inventoryDetailsDdlb.setContainerDataSource(new IndexedContainer(inventoryDetailsDdlbList));
        inventoryDetailsDdlb.setValue(selectionDto.getInventoryDetails());
        inventoryDetailsDdlb.setNullSelectionAllowed(false);
        inventoryLevel.removeValueChangeListener(frequencyListener);
        inventoryLevel.setValue(selectionDto.getInventoryOptionGroup());
        inventoryLevel.addValueChangeListener(frequencyListener);
    }

    private void configureFieldsOnViewMode() {
        inventoryDetailsDdlb.setEnabled(false);
        price.setEnabled(false);
        inventoryCalculation.setEnabled(false);
        inventoryLevel.setEnabled(false);
        reset.setEnabled(false);
        reserveDate.setEnabled(false);
    }

    @Override
    public boolean isGenerated() {
        return inventoryResults.isGenerated();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return customMenuBar;
    }

    public InventorySearchResults getInventoryResults() {
        return inventoryResults;
    }

    @Override
    public String leaveConfirmationHeader() {
        return ARMMessages.getInventoryLeaveConfirmHeaderTransaction3();
    }

    @Override
    public String leaveConfirmationMessage() {
        return ARMMessages.getInventoryLeaveConfirmMessageTransaction3();
    }

    @Override
    public boolean checkLeave() {
        return isGenerated();
    }

    @Override
    public boolean isRestrict() {
        return false;
    }

    @Override
    public String leaveRestrictionHeader() {
        return ARMMessages.getInventoryLeaveConfirmHeaderTransaction3();
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getInventoryLeaveConfirmMessageTransaction3();
    }

    public void initializeArcLoad(List<CustomerGroupDTO> cgList, int projectionId, AbstractSelectionDTO selectionDto) {
        PipelineInventoryLookupLogic pipelineLogic = new PipelineInventoryLookupLogic();
        pipelineLogic.saveCustomerGroupValue(cgList, projectionId, selectionDto);
    }

    public void loadFixedCustomMenuBar() {

        variableValues = VariableConstants.PipelineInventoryVariables.names();
        variableVisibleColumns = VariableConstants.getVariableInventoryVisibleColumn();
        String[] both = (String[]) ArrayUtils.addAll(null, variableValues);
        String[] both1 = (String[]) ArrayUtils.addAll(null, variableVisibleColumns);
        variableVisibleColumns = both1;
        variableValues = both;
        CommonUtils.loadCustomMenu(customMenuItem, both, both1);
        customMenuBar.setPageLength(both.length);
    }

    public void getDefaultValues(List<String> defaultValues) {
        if ("0".equals(defaultValues.get(NumericConstants.THREE))) {
            price.setValue(GlobalConstants.getSelectOne());
        } else {
            price.setValue(logic.getRatePeriod(defaultValues.get(NumericConstants.THREE), "M", dataselection.getFromPeriodMonth(), priceddlb));
        }
        if ("0".equals(defaultValues.get(NumericConstants.FIVE))) {
            reserveDate.setValue(GlobalConstants.getSelectOne());
        } else {
            /**
             * Explicitly passed StringUtils.EMPTY because it is not specified
             * in any CR If these DDLB needed to be loaded based on Data
             * selection from period then we can follow like above If we pass
             * from period then values will be loaded based on Data selection
             * From.
             */
            reserveDate.setValue(logic.getRatePeriodFromDS(defaultValues.get(NumericConstants.FIVE), "M", StringUtils.EMPTY, null, dataselection.getFromDate()));
        }
        if ("0".equals(defaultValues.get(NumericConstants.SIX))) {
            inventoryDetailsDdlb.setValue(GlobalConstants.getSelectOne());
        } else {
            inventoryDetailsDdlb.setValue(logic.getRatePeriodFromDS(defaultValues.get(NumericConstants.SIX), "M", StringUtils.EMPTY, null, dataselection.getFromDate()));
        }
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction3", "Inventory");
        reset.setVisible(CommonLogic.isButtonVisibleAccess(CommonConstant.RESET, functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        inventoryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        inventoryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        inventoryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static List<String> getPeriodsByFrequencyForMonthPrice() {
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -NumericConstants.SIX);
        String year;
        int month;
        for (int i = 0; i <= NumericConstants.TWELVE; i++) {
            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = calendar.get(Calendar.MONTH);
            String period = months[month] + " " + year;
            calendar.add(Calendar.MONTH, NumericConstants.ONE);
            periodList.add(period);
        }
        return periodList;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
