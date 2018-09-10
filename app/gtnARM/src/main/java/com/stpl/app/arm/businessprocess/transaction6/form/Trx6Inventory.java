
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6InventoryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.supercode.LeaveConfirmMessageAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.security.permission.model.AppPermission;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Srithar
 */
public class Trx6Inventory extends VerticalLayout implements View, GenerateAble, DefaultFocusable, LeaveConfirmMessageAble, LeaveCheckAble {

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;
    private CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("reset")
    private Button reset;
    @UiField("generate")
    private Button generate;
    @UiField("inventoryDetailsDdlb")
    private CustomComboBox inventoryDetailsDdlb;
    @UiField("baseLinePrice")
    private CustomComboBox baseLinePrice;
    @UiField("adjustedPrice")
    private CustomComboBox adjustedPrice;
    private Trx6InventoryLogic<AdjustmentDTO, Trx6SelectionDTO> logic = new Trx6InventoryLogic();
    private String[] variableVisibleColumns;
    private Trx6SelectionDTO selectionDto;
    private Trx6InventorySearchResults inventoryResults;
    private int projectionId = 0;
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(Trx6Inventory.class);

    public Trx6Inventory(Trx6SelectionDTO selectionDto, int projectionId) {
        this.selectionDto = selectionDto;
        this.projectionId = projectionId;
        init();
        configureWorkFlow();
    }

    private void init() {
        inventoryResults = new Trx6InventorySearchResults(logic, selectionDto);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/trx6_Inventory.xml"), this));
        inventoryResults.getResults();
        addComponent(inventoryResults);
        configureFields();
        configureDblbLoading();
        setDefaultValue();
        generate();
        reset();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @UiHandler("inventoryDetailsDdlb")
    public void inventoryDetailsDdlbValueChange(Property.ValueChangeEvent event) {
        LOGGER.debug("inside inventoryDetailsDdlbValueChange Method");
    }

    private final InflcationInventoryCustomNotification notifier = new InflcationInventoryCustomNotification();

    private void configureDblbLoading() {
        List<Object> defaultValues = logic.getMonthYear();
        Integer vvalue = Integer.valueOf(String.valueOf(defaultValues.get(1)));
        String month = Trx6InventoryLogic.getMonthName(vvalue);
        String str = month + " " + defaultValues.get(NumericConstants.TWO);
        priceddlb = CommonUtils.getPeriodsByFrequency("M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), str);
        adjustedPrice.setNullSelectionAllowed(Boolean.FALSE);
        inventoryDetailsDdlb.setNullSelectionAllowed(Boolean.FALSE);
        baseLinePrice.setNullSelectionAllowed(Boolean.FALSE);
        adjustedPrice.setNullSelectionAllowed(Boolean.FALSE);
        adjustedPrice.addItems(priceddlb);
        List<String> reserveDatelist = getPeriodsByFrequency();
        inventoryDetailsDdlb.addItems(reserveDatelist);
        baseLinePrice.addItems(priceddlb);
        baseLinePrice.select(priceddlb.get(0));
        inventoryDetailsDdlb.select(reserveDatelist.get(0));
        adjustedPrice.select(reserveDatelist.get(0));
    }

    private void configureFields() {
        String[] variableValues;
        customMenuBar.setScrollable(true);
        variableValues = ARMUtils.Trx6_Variables.names();
        customMenuItem = customMenuBar.addItem("-Select One-", null);
        variableVisibleColumns = ARMUtils.getVariableInventoryVisibleColumn();
        String[] both = (String[]) ArrayUtils.addAll(null, variableValues);
        String[] both1 = (String[]) ArrayUtils.addAll(null, variableVisibleColumns);
        variableVisibleColumns = both1;
        CommonUtils.loadCustomMenu(customMenuItem, both, both1);
        customMenuBar.setPageLength(both.length);
    }

    private void loadSelection() {
        selectionDto.setVariableVisibleColumns(variableVisibleColumns);
        selectionDto.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
        Object inventoryDetails = inventoryDetailsDdlb.getValue();
        selectionDto.setInventoryDetails(StringUtils.EMPTY);
        if (inventoryDetails != null && !inventoryDetails.equals(GlobalConstants.getSelectOne())) {
            selectionDto.setInventoryDetails(inventoryDetails.toString());
        }
        String baseLineP = CommonUtils.getPeriodValue('M', baseLinePrice.getValue().toString());
        selectionDto.setPrice(StringUtils.EMPTY);
        if (baseLineP != null && !StringUtils.isBlank(baseLineP) && !baseLineP.equals(GlobalConstants.getSelectOne())) {
            selectionDto.setPrice(baseLineP);
        }
        String adjPrice = CommonUtils.getPeriodValue('M', adjustedPrice.getValue().toString());
        selectionDto.setAdjustedPrice(StringUtils.EMPTY);
        if (adjPrice != null && !StringUtils.isBlank(adjPrice) && !adjPrice.equals(GlobalConstants.getSelectOne())) {
            selectionDto.setAdjustedPrice(adjPrice);
        }
    }

    class InflcationInventoryCustomNotification extends AbstractNotificationUtils {

        private String inventoryButtonName;

        public InflcationInventoryCustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification No method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", inventoryButtonName);
            if (null != inventoryButtonName) {
                switch (inventoryButtonName) {
                    case CommonConstant.RESET:
                        setDefaultValue();
                        CommonUtils.unCheckMenuBarItem(customMenuItem);
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.inventoryButtonName = buttonName;
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
                try {
                    loadSelection();
                    if (validateGenearteButton()) {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGenerateMessage_Demand());
                    } else {
                        inventoryResults.setValueChangeAllowed(false);
                        inventoryResults.generateButtonLogic(selectionDto);
                        inventoryResults.setValueChangeAllowed(true);
                    }

                } catch (Exception e) {
                    LOGGER.error("Error in generate :", e);
                }
            }
        });
    }

    private boolean validateGenearteButton() {

        return (selectionDto.getInventoryDetails().equals(StringUtils.EMPTY)
                || selectionDto.getPrice().equals(StringUtils.EMPTY)
                || selectionDto.getAdjustedPrice().equals(StringUtils.EMPTY)
                || selectionDto.getSalesVariables() == null
                || selectionDto.getSalesVariables().isEmpty());
    }

    private void setDefaultValue() {
        List<String> defaultValues = logic.getRateConfigSettings(new ArrayList<>(Arrays.asList(selectionDto.getDataSelectionDTO().getCompanyMasterSid(), selectionDto.getDataSelectionDTO().getBucompanyMasterSid(), selectionDto.getDataSelectionDTO().getAdjustmentId(), StringUtils.isNotBlank(selectionDto.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(selectionDto.getDataSelectionDTO().getFromPeriodMonth().trim().split(" ")[0]) : 1))); // Changed for GAL-6120
        if (!defaultValues.isEmpty()) {
            adjustedPrice.setValue(logic.getRatePeriod(defaultValues.get(NumericConstants.SEVEN), "M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
            baseLinePrice.setValue(logic.getRatePeriod(defaultValues.get(NumericConstants.EIGHT), "M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
            /**
             * Explicitly passed StringUtils.EMPTY because it is not specified
             * in any CR If these DDLB needed to be loaded based on Data
             * selection from period then we can follow like above If we pass
             * from period then values will be loaded based on Data selection
             * from.
             */
            inventoryDetailsDdlb.setValue(logic.getRatePeriod(defaultValues.get(NumericConstants.SIX), "M", selectionDto.getDataSelectionDTO().getFromPeriodMonth(), null));
        } else {
            adjustedPrice.setValue(GlobalConstants.getSelectOne());
            baseLinePrice.setValue(GlobalConstants.getSelectOne());
            inventoryDetailsDdlb.setValue(GlobalConstants.getSelectOne());
        }
    }

    public static List<String> getPeriodsByFrequency() {
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -NumericConstants.TWELVE);
        String year;
        int month;
        for (int i = 0; i <= NumericConstants.FIFTEEN; i++) {
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
        try {
            List<Object[]> list = CommonLogic.loadPipelineAccrual(projectionId);
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if (VariableConstants.SALES_VARIABLE.equals(String.valueOf(obj[0]))) {
                    String inventoryStr1 = (String) obj[1];
                    String[] str2 = inventoryStr1.split(",");
                    String inventoryStr3 = null;
                    for (String strings : str2) {
                        inventoryStr3 = strings;
                        CommonUtils.checkMenuBarItem(customMenuItem, inventoryStr3);
                    }
                } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                    BeanUtils.setProperty(selectionDto, String.valueOf(obj[0]), obj[1]);

                }
                if ("inventoryDetailsDdlb".equals(String.valueOf(obj[0]))) {
                    inventoryDetailsDdlb.select(String.valueOf(obj[1]));
                    selectionDto.setInventoryDetails(CommonUtils.getPeriodValue('M', inventoryDetailsDdlb.getValue().toString()));
                } else if ("baseLinePrice".equals(String.valueOf(obj[0]))) {
                    baseLinePrice.select(String.valueOf(obj[1]));
                    selectionDto.setPrice(CommonUtils.getPeriodValue('M', baseLinePrice.getValue().toString()));
                } else if ("adjustedPrice".equals(String.valueOf(obj[0]))) {
                    adjustedPrice.select(String.valueOf(obj[1]));
                    selectionDto.setAdjustedPrice(CommonUtils.getPeriodValue('M', adjustedPrice.getValue().toString()));
                }
            }
            HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        } catch (Exception e) {
            LOGGER.error("Error in loadDetails :", e);
        }
    }

    private void configureFieldsOnViewMode() {
        inventoryDetailsDdlb.setEnabled(Boolean.FALSE);
        adjustedPrice.setEnabled(Boolean.FALSE);
        baseLinePrice.setEnabled(Boolean.FALSE);
        reset.setEnabled(Boolean.FALSE);
    }

    @Override
    public boolean isGenerated() {
        return inventoryResults.isGenerated();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return customMenuBar;
    }

    @Override
    public String leaveConfirmationMessage() {
        return ARMMessages.getInventoryLeaveConfirmMessageTransaction6();
    }

    @Override
    public String leaveConfirmationHeader() {
        return ARMMessages.getInventoryLeaveConfirmHeaderTransaction3();
    }

    @Override
    public boolean isRestrict() {
        return false;
    }

    @Override
    public boolean checkLeave() {
        return isGenerated();
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getInventoryLeaveConfirmMessageTransaction6();
    }

    @Override
    public String leaveRestrictionHeader() {
        return ARMMessages.getInventoryLeaveConfirmHeaderTransaction3();
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> inventoryFunctionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction6", "Inventory");
        reset.setVisible(CommonLogic.isButtonVisibleAccess(CommonConstant.RESET, inventoryFunctionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", inventoryFunctionHM));
        inventoryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", inventoryFunctionHM));
        inventoryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", inventoryFunctionHM));
        inventoryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", inventoryFunctionHM));

    }

    @Override
    public boolean equals(Object inflationInventoryObj) {
        return super.equals(inflationInventoryObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream inflationInventoryObj) throws IOException {
        inflationInventoryObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream inflationInventoryObj) throws IOException, ClassNotFoundException {
        inflationInventoryObj.defaultReadObject();
    }
}
