
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
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
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

/**
 *
 * @author srithar
 */
public class Sales extends VerticalLayout implements View, DefaultFocusable, GenerateAble, LeaveConfirmMessageAble, LeaveCheckAble {

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    private CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("dateType")
    protected CustomComboBox dateType;
    @UiField("price")
    protected CustomComboBox price;
    @UiField("exclusionDetails")
    protected Button exclusionDetails;
    @UiField("reset")
    private Button reset;
    @UiField("generate")
    private Button generate;
    DataSelectionDTO dataselection;
    public final Map<String, String> listNameMapper = new HashMap<>(ARMUtils.listNameMapper());
    protected Object[] variablesValue;
    private SalesSearchResults salesResults;
    SalesLogic logic = new SalesLogic();
    PipelineAccrualSelectionDTO selection;
    List<Object> defaultValues;
    List<String> propertyList = new ArrayList<>();
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    public Sales(DataSelectionDTO dataselection, PipelineAccrualSelectionDTO selection) {
        this.dataselection = dataselection;
        this.selection = selection;
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
        salesResults = new SalesSearchResults(logic, selection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/sale_dataselection.xml"), this));
        salesResults.getResults();
        exclusionDetails.setImmediate(true);
        exclusionDetails.addStyleName(Reindeer.BUTTON_LINK);
        addComponent(salesResults);
        CommonUtils.loadComboBoxWithInteger(dateType, CommonConstant.ARM_DATE_TYPE, false);
    }

    @UiHandler("exclusionDetails")
    public void exclusionDetailsLookup(Button.ClickEvent event) {
        LOGGER.debug("exclusionDetails starts");
        ExclusionDetailsLookup exclusionDetailsLookup = new ExclusionDetailsLookup(dataselection, selection.getSessionDTO()); // Changed for GAL-7235
        UI.getCurrent().addWindow(exclusionDetailsLookup);
        exclusionDetailsLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                LOGGER.debug("Inside Window Close Method Of exclusionDetailsLookup");

            }
        });
    }
    private final CustomNotification notifier = new CustomNotification();

    private void configureFields() {
        String[] variableValues;
        String[] variableVisibleColumns;
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.FOUR);
        variableValues = VariableConstants.SalesVariables.names();
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        variableVisibleColumns = VariableConstants.getVariableSalesVisibleColumn();
        CommonUtils.loadCustomMenu(customMenuItem, variableValues, variableVisibleColumns);
        List<Object> defaultValue = logic.getMonthYear();
        Integer vvalue = Integer.valueOf(String.valueOf(defaultValue.get(1)));
        String month = logic.getMonthName(vvalue);
        String str = month + " " + defaultValue.get(NumericConstants.TWO);
        priceddlb = CommonUtils.getPeriodsByFrequency("M", selection.getDataSelectionDTO().getFromPeriodMonth(), str);
        price.removeAllItems();
        price.setContainerDataSource(new IndexedContainer(priceddlb));
        setDefaultValue();
    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            salesResults.setValueChangeAllowed(false);
            loadDetails();
            selection.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
            salesResults.generateButtonLogic(selection, Boolean.TRUE);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            salesResults.setValueChangeAllowed(true);
        }
    }

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
            LOGGER.debug("inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
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
                    LOGGER.error("Error in reset :"+e);
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
                    if ("null".equals(String.valueOf(price.getValue())) || String.valueOf(price.getValue()).equals(ConstantsUtils.SELECT_ONE) || CommonUtils.getCheckedValues(customMenuItem).isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please make sure at least one value is selected from the Variables and Price drop downs.");
                    } else {
                        salesResults.setValueChangeAllowed(false);
                        String description = HelperListUtil.getInstance().getDescriptionByID((int) dateType.getValue()).trim();
                        selection.setSalesVariables(CommonUtils.getCheckedValues(customMenuItem));
                        selection.setPrice(CommonUtils.getPeriodValue('M', price.getValue().toString()));
                        selection.setDateType(description);
                        selection.setProjectionMasterSid(dataselection.getProjectionId());

                        salesResults.generateButtonLogic(selection, Boolean.FALSE);
                        salesResults.setValueChangeAllowed(true);
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in generate :"+e);
                }
            }
        });
    }

    private void setDefaultValue() {
        try {
            List<String> defaultValue = logic.getRateConfigSettings(new ArrayList<>(Arrays.asList(dataselection.getCompanyMasterSid(), dataselection.getBucompanyMasterSid(), dataselection.getAdjustmentId(),
                    StringUtils.isNotBlank(selection.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(selection.getDataSelectionDTO().getFromPeriodMonth().trim().split(" ")[0]) : 1)));
            if (!defaultValue.isEmpty()) {
                if (!"0".equals(defaultValue.get(NumericConstants.FOUR))) {
                    dateType.setValue(Integer.valueOf(defaultValue.get(NumericConstants.FOUR)));
                } else {
                    dateType.setValue(0);
                }
                if (!"0".equals(defaultValue.get(NumericConstants.THREE))) {
                    price.setValue(logic.getRatePeriod(defaultValue.get(NumericConstants.THREE), "M", selection.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
                } else {
                    price.setValue(ConstantsUtils.SELECT_ONE);
                }
            } else {
                price.setValue(ConstantsUtils.SELECT_ONE);
            }

        } catch (Exception e) {
            LOGGER.error("Error in setDefaultValue :"+e);
        }
    }

    private void configureFieldsOnViewMode() {
        dateType.setEnabled(false);
        price.setEnabled(false);
        exclusionDetails.setEnabled(false);
        reset.setEnabled(false);
    }

    private void loadDetails() {
        try {
            List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getProjectionMasterSid());
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if (VariableConstants.SALES_VARIABLE.equals(String.valueOf(obj[0]))) {
                    String str1 = (String) obj[1];
                    String[] str2 = str1.split(",");
                    String str3 = null;
                    for (String strings : str2) {
                        str3 = strings;
                        CommonUtils.checkMenuBarItem(customMenuItem, str3);
                    }
                } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                    System.out.println("obj[0]----------" + obj[0]);
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                }
            }
            HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
            CommonUtils.loadComboBoxWithInteger(dateType, CommonConstant.ARM_DATE_TYPE, false);
            dateType.setValue(HelperListUtil.getInstance().getIdByDesc(CommonConstant.ARM_DATE_TYPE, selection.getDateType()));
            List<Object> defaultValue = logic.getMonthYear();
            Integer vvalue = Integer.valueOf(String.valueOf(defaultValue.get(1)));
            String month = logic.getMonthName(vvalue);
            String str = month + " " + defaultValue.get(NumericConstants.TWO);
            priceddlb = CommonUtils.getPeriodsByFrequency("M", selection.getDataSelectionDTO().getFromPeriodMonth(), str);
            price.removeAllItems();
            price.setContainerDataSource(new IndexedContainer(priceddlb));
            price.setValue(selection.getPrice());
        } catch (Exception e) {
            LOGGER.error("Error in loadDetails :"+e);
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
    public String leaveConfirmationMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
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
        return ARMMessages.getSalesLeaveConfirmHeaderTransaction1();
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Sales");
        reset.setVisible(CommonLogic.isButtonVisibleAccess(CommonConstant.RESET, functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        salesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        salesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        salesResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
