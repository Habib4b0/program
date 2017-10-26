
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form;

import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.arm.businessprocess.transaction_7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction_7.logic.Trx7SalesLogic;
import com.stpl.app.arm.businessprocess.transaction_7.lookups.Trx7ExclusionDetailsLookup;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.supercode.LeaveConfirmMessageAble;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.VariableConstants;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
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

/**
 *
 * @author srithar
 */
public class Trx7SalesTab extends VerticalLayout implements View, DefaultFocusable, GenerateAble, LeaveConfirmMessageAble, LeaveCheckAble {

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    private static String[] variableValues;
    private static CustomMenuBar.CustomMenuItem customMenuItem;
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
    private Trx7SalesSearchResult salesResults;
    Trx7SalesLogic logic = new Trx7SalesLogic();
    private String[] variableVisibleColumns;
    Trx7SelectionDTO selection;
    List<Object> defaultValues;
    List<String> propertyList = new ArrayList<>();
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    public Trx7SalesTab(DataSelectionDTO dataselection, Trx7SelectionDTO selection) {
        this.dataselection = dataselection;
        this.selection = selection;
        init();
        configureFields();
        configureWorkFlow();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void init() {
        salesResults = new Trx7SalesSearchResult(logic, selection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/sale_dataselection.xml"), this));
        salesResults.getResults();
        exclusionDetails.setImmediate(true);
        exclusionDetails.addStyleName(Reindeer.BUTTON_LINK);
        addComponent(salesResults);
        CommonUtils.loadComboBoxWithInteger(dateType, "ARM_DATE_TYPE", false);
        generate();
        reset();
    }

    @UiHandler("exclusionDetails")
    public void exclusionDetailsLookup(Button.ClickEvent event) {
        LOGGER.debug("exclusionDetails starts");
        Trx7ExclusionDetailsLookup exclusionDetailsLookup = new Trx7ExclusionDetailsLookup(dataselection.getProjectionId(), selection.getSessionDTO(), dataselection);
        UI.getCurrent().addWindow(exclusionDetailsLookup);
        exclusionDetailsLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {

            }
        });
    }
    private final CustomNotification notifier = new CustomNotification();

    private void configureFields() {
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.FOUR);
        variableValues = VariableConstants.SalesVariables.names();
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        variableVisibleColumns = VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN;
        CommonUtils.loadCustomMenu(customMenuItem, variableValues, variableVisibleColumns);
        List<Object> defaultValues = logic.getMonthYear();
        Integer vvalue = Integer.valueOf(String.valueOf(defaultValues.get(1)));
        String month = logic.getMonthName(vvalue);
        String str = month + " " + defaultValues.get(NumericConstants.TWO);
        priceddlb = CommonUtils.getPeriodsByFrequency("M", selection.getDataSelectionDTO().getFromPeriodMonth(), str);
        price.removeAllItems();
        price.setContainerDataSource(new IndexedContainer(priceddlb));
        price.setNullSelectionAllowed(false);
        setDefaultValue();
    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            salesResults.setValueChangeAllowed(false);
            loadDetails();
            selection.setSales_variables(CommonUtils.getCheckedValues(customMenuItem));
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
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        setDefaultValue();
                        CommonUtils.unCheckMenuBarItem(customMenuItem);
                        break;
                    case "save":
                        break;
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
                    notifier.setButtonName("reset");
                    notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());

                } catch (Exception e) {
                    LOGGER.error(e);
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
                    if (String.valueOf(price.getValue()).equals("null") || String.valueOf(price.getValue()).equals(ConstantsUtils.SELECT_ONE)
                            || String.valueOf(dateType.getValue()).equals("0")
                            || CommonUtils.getCheckedValues(customMenuItem).size() == 0) {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please make sure at least one value is selected from the Variables,Date Type and Price drop downs.");
                    } else {
                        salesResults.setValueChangeAllowed(false);
                        String description = HelperListUtil.getInstance().getDescriptionByID((int) dateType.getValue()).trim();
                        selection.setSales_variables(CommonUtils.getCheckedValues(customMenuItem));
                        selection.setPrice(CommonUtils.getPeriodValue('M', price.getValue().toString()));
                        selection.setDateType(description);
                        selection.setProjectionMasterSid(dataselection.getProjectionId());

                        salesResults.generateButtonLogic(selection, Boolean.FALSE);
                        salesResults.setValueChangeAllowed(true);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
    }

    private void setDefaultValue() {
        try {
            List<String> defaultValues = logic.getRateConfigSettings(new ArrayList<>(Arrays.asList(dataselection.getCompanyMasterSid(), dataselection.getBu_companyMasterSid(), dataselection.getAdjustmentId(),
                    StringUtils.isNotBlank(selection.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(selection.getDataSelectionDTO().getFromPeriodMonth().trim().split(" ")[0]) : 1)));
            if (!defaultValues.isEmpty()) {
                if (!defaultValues.get(NumericConstants.THREE).equals("0") || defaultValues.get(NumericConstants.THREE).contains("CURRENT")) {
                    price.setValue(logic.getRatePeriod(defaultValues.get(NumericConstants.THREE), "M", selection.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
                } else {
                    price.setValue(ConstantsUtils.SELECT_ONE);
                }
                if (!defaultValues.get(NumericConstants.FOUR).equals("0")) {
                    dateType.setValue(Integer.valueOf(defaultValues.get(NumericConstants.FOUR)));
                } else {
                    dateType.setValue(ConstantsUtils.SELECT_ONE);
                }

            } else {
                price.setValue(ConstantsUtils.SELECT_ONE);
                dateType.select(0);
            }
        } catch (Exception e) {
            LOGGER.error(e);
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
                Object[] obj = (Object[]) list.get(i);
                if ("sales_variables".equals(String.valueOf(obj[0]))) {
                    String str1 = (String) obj[1];
                    String[] str2 = str1.split(",");
                    String str3 = null;
                    for (String strings : str2) {
                        str3 = strings;
                        CommonUtils.checkMenuBarItem(customMenuItem, str3);
                    }
                } else if (!"detail_variables".equals(String.valueOf(obj[0])) && !"detail_reserveAcount".equals(String.valueOf(obj[0]))
                        && !"summary_deductionValues".equals(String.valueOf(obj[0])) && !"summary_variables".equals(String.valueOf(obj[0]))
                        && !VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);

                }
            }
            HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
            CommonUtils.loadComboBoxWithInteger(dateType, "ARM_DATE_TYPE", false);
            dateType.setValue(HelperListUtil.getInstance().getIdByDesc("ARM_DATE_TYPE", selection.getDateType()));
            List<Object> defaultValues = logic.getMonthYear();
            Integer vvalue = Integer.valueOf(String.valueOf(defaultValues.get(1)));
            String month = logic.getMonthName(vvalue);
            String str = month + " " + defaultValues.get(NumericConstants.TWO);
            List<String> priceddlb = CommonUtils.getPeriodsByFrequency("M", selection.getDataSelectionDTO().getFromPeriodMonth(), str);
            price.removeAllItems();
            price.setContainerDataSource(new IndexedContainer(priceddlb));
            price.setValue(selection.getPrice());
            price.setNullSelectionAllowed(false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public Trx7SalesSearchResult getSalesResults() {
        return salesResults;
    }

    public boolean isSalesGenerated() {
        return salesResults.getResultBeanContainer().size() > 0;
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return customMenuBar;
    }

    @Override
    public boolean isGenerated() {
        return salesResults.getResultBeanContainer().size() > 0;
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
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction7", "Sales");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        salesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        salesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        salesResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));
    }
}
