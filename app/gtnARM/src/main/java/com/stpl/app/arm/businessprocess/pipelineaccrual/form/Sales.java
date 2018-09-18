
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.PipelineAccrualSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.SalesLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.lookups.ExclusionDetailsLookup;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.supercode.LeaveConfirmMessageAble;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author srithar
 */
public class Sales extends VerticalLayout implements View, DefaultFocusable, GenerateAble, LeaveConfirmMessageAble, LeaveCheckAble {

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    private CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("dateType")
    protected CustomComboBox salesDateType;
    @UiField("price")
    protected CustomComboBox salesPrice;
    @UiField("exclusionDetails")
    protected Button salesExclusionDetails;
    @UiField("reset")
    private Button reset;
    @UiField("generate")
    private Button generate;
    private DataSelectionDTO dataselection;
    public final Map<String, String> listNameMapper = new HashMap<>(ARMUtils.listNameMapper());
    protected Object[] variablesValue;
    private SalesSearchResults salesResults;
    private SalesLogic salesLogic = new SalesLogic();
    private PipelineAccrualSelectionDTO salesSelection;
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    public static final Logger LOGGER = LoggerFactory.getLogger(Sales.class);

    public Sales(DataSelectionDTO dataselection, PipelineAccrualSelectionDTO selection) {
        this.dataselection = dataselection;
        this.salesSelection = selection;
        init();
        configureFields();
        configureWorkFlow();
        generate();
        reset();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method Of Sales Class");
    }

    private void init() {
        salesResults = new SalesSearchResults(salesLogic, salesSelection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/sale_dataselection.xml"), this));
        salesResults.getResults();
        salesExclusionDetails.addStyleName(Reindeer.BUTTON_LINK);
        addComponent(salesResults);
        CommonUtils.loadComboBoxWithInteger(salesDateType, CommonConstant.ARM_DATE_TYPE, false);
    }

    @UiHandler("exclusionDetails")
    public void exclusionDetailsLookup(Button.ClickEvent event) {
        LOGGER.debug("exclusionDetails starts");
        ExclusionDetailsLookup exclusionDetailsLookup = new ExclusionDetailsLookup(dataselection, salesSelection.getSessionDTO()); // Changed for GAL-7235
        UI.getCurrent().addWindow(exclusionDetailsLookup);
        exclusionDetailsLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                LOGGER.debug("Inside Window Close Method Of exclusionDetailsLookup");

            }
        });
    }
    private final SalesCustomNotification salesNotifier = new SalesCustomNotification();

    private void configureFields() {
        String[] variableValues;
        String[] variableVisibleColumns;
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.FOUR);
        variableValues = VariableConstants.SalesVariables.names();
        customMenuItem = customMenuBar.addItem("-Select One-", null);
        variableVisibleColumns = VariableConstants.getVariableSalesVisibleColumn();
        CommonUtils.loadCustomMenu(customMenuItem, variableValues, variableVisibleColumns);
        List<Object> defaultValue = salesLogic.getMonthYear();
        Integer vvalue = ARMUtils.getIntegerValue(String.valueOf(defaultValue.get(1)));
        String month = SalesLogic.getMonthName(vvalue);
        String str = month + ARMUtils.SPACE + defaultValue.get(NumericConstants.TWO);
        priceddlb = CommonUtils.getPeriodsByFrequency("M", salesSelection.getDataSelectionDTO().getFromPeriodMonth(), str);
        salesPrice.removeAllItems();
        salesPrice.setContainerDataSource(new IndexedContainer(priceddlb));
        setDefaultValue();
    }

    private void configureWorkFlow() {
        if (salesSelection.getSessionDTO().isWorkFlow()) {
            salesResults.setValueChangeAllowed(false);
            loadDetails();
            salesSelection.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
            salesResults.generateButtonLogic(salesSelection, true);
            if (ARMUtils.VIEW_SMALL.equals(salesSelection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            salesResults.setValueChangeAllowed(true);
        }
    }

    class SalesCustomNotification extends AbstractNotificationUtils {

        private String salesButtonName;

        public SalesCustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", salesButtonName);
            if (null != salesButtonName) {
                switch (salesButtonName) {
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
            this.salesButtonName = buttonName;
        }

    }

    public void reset() {
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    salesNotifier.setButtonName(CommonConstant.RESET);
                    salesNotifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
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
                    if ("null".equals(String.valueOf(salesPrice.getValue())) || String.valueOf(salesPrice.getValue()).equals(GlobalConstants.getSelectOne()) || CommonUtils.getCheckedValues(customMenuItem).isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please make sure at least one value is selected from the Variables and Price drop downs.");
                    } else {
                        salesResults.setValueChangeAllowed(false);
                        String description = HelperListUtil.getInstance().getDescriptionByID((int) salesDateType.getValue()).trim();
                        salesSelection.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
                        salesSelection.setPrice(CommonUtils.getPeriodValue('M', salesPrice.getValue().toString()));
                        salesSelection.setDateType(description);
                        salesSelection.setProjectionMasterSid(dataselection.getProjectionId());

                        salesResults.generateButtonLogic(salesSelection, false);
                        salesResults.setValueChangeAllowed(true);
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in generate :", e);
                }
            }
        });
    }

    private void setDefaultValue() {
        try {
            List<String> defaultValue = salesLogic.getRateConfigSettings(new ArrayList<>(Arrays.asList(dataselection.getCompanyMasterSid(), dataselection.getBucompanyMasterSid(), dataselection.getAdjustmentId(),
                    StringUtils.isNotBlank(salesSelection.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(salesSelection.getDataSelectionDTO().getFromPeriodMonth().trim().split(ARMUtils.SPACE.toString())[0]) : 1)));
            if (!defaultValue.isEmpty()) {
                if (!"0".equals(defaultValue.get(NumericConstants.FOUR))) {
                    salesDateType.setValue(Integer.valueOf(defaultValue.get(NumericConstants.FOUR)));
                } else {
                    salesDateType.setValue(0);
                }
                if (!"0".equals(defaultValue.get(NumericConstants.THREE))) {
                    salesPrice.setValue(salesLogic.getRatePeriod(defaultValue.get(NumericConstants.THREE), "M", salesSelection.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
                } else {
                    salesPrice.setValue(GlobalConstants.getSelectOne());
                }
            } else {
                salesPrice.setValue(GlobalConstants.getSelectOne());
            }

        } catch (Exception e) {
            LOGGER.error("Error in setDefaultValue :", e);
        }
    }

    private void configureFieldsOnViewMode() {
        salesDateType.setEnabled(false);
        salesPrice.setEnabled(false);
        salesExclusionDetails.setEnabled(false);
        reset.setEnabled(false);
    }

    private void loadDetails() {
        try {
            List<Object[]> list = CommonLogic.loadPipelineAccrual(salesSelection.getProjectionMasterSid());
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if (VariableConstants.SALES_VARIABLE.equals(String.valueOf(obj[0]))) {
                    String salesstr1 = (String) obj[1];
                    String[] salesstr2 = salesstr1.split(",");
                    String str3 = null;
                    for (String strings : salesstr2) {
                        str3 = strings;
                        CommonUtils.checkMenuBarItem(customMenuItem, str3);
                    }
                } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                    BeanUtils.setProperty(salesSelection, String.valueOf(obj[0]), obj[1]);
                }
            }
            HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
            CommonUtils.loadComboBoxWithInteger(salesDateType, CommonConstant.ARM_DATE_TYPE, false);
            salesDateType.setValue(HelperListUtil.getInstance().getIdByDesc(CommonConstant.ARM_DATE_TYPE, salesSelection.getDateType()));
            List<Object> defaultValue = salesLogic.getMonthYear();
            Integer vvalue = ARMUtils.getIntegerValue(String.valueOf(defaultValue.get(1)));
            String month = SalesLogic.getMonthName(vvalue);
            String str = month + ARMUtils.SPACE + defaultValue.get(NumericConstants.TWO);
            priceddlb = CommonUtils.getPeriodsByFrequency("M", salesSelection.getDataSelectionDTO().getFromPeriodMonth(), str);
            salesPrice.removeAllItems();
            salesPrice.setContainerDataSource(new IndexedContainer(priceddlb));
            salesPrice.setValue(salesSelection.getPrice());
        } catch (Exception e) {
            LOGGER.error("Error in loadDetails :", e);
        }
    }

    public SalesSearchResults getSalesResults() {
        return salesResults;
    }

    @Override
    public boolean isGenerated() {
        return salesResults.isGenerated();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return customMenuBar;
    }

    @Override
    public String leaveConfirmationHeader() {
        return ARMMessages.getSalesLeaveConfirmHeaderTransaction1();
    }

    @Override
    public boolean checkLeave() {
        return isGenerated();
    }

    @Override
    public String leaveConfirmationMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
    }

    @Override
    public String leaveRestrictionHeader() {
        return ARMMessages.getSalesLeaveConfirmHeaderTransaction1();
    }

    @Override
    public boolean isRestrict() {
        return false;
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> salesFunctionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Sales");
        reset.setVisible(CommonLogic.isButtonVisibleAccess(CommonConstant.RESET, salesFunctionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", salesFunctionHM));
        salesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", salesFunctionHM));
        salesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", salesFunctionHM));
        salesResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", salesFunctionHM));

    }

    @Override
    public boolean equals(Object salesobj) {
        return super.equals(salesobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream salesobj) throws IOException {
        salesobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream salesobj) throws IOException, ClassNotFoundException {
        salesobj.defaultReadObject();
    }
}
