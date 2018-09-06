/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PipelineAccrualRateLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.RateLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.HasExcel;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import java.util.Calendar;
import java.util.Collections;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinoth.parthasarathy
 */
public abstract class AbstractPipelineRates extends VerticalLayout implements Rates, HasExcel {

    /**
     * The Deduction Level Combo box links to the Rebate Schedule Category data
     * attribute. Will show all distinct values of the Rebate Schedule Category
     * data attribute.
     */
    @UiField("deductionLevelDdlb")
    protected ComboBox deductionLevelDdlb;

    /**
     * Deduction Value ddlb values will depend on the value selected in
     * Deduction Level. and display all of the unique values under the selected
     * Deduction Level
     */
    @UiField("deductionValueDdlb")
    protected CustomMenuBar deductionValueDdlb;

    /**
     * This ComboBox provides frequency selections
     */
    @UiField("rateFrequencyDdlb")
    protected ComboBox rateFrequencyDdlb;

    /**
     * This ComboBox is used to calculate an Accrual Rate for each Item in the
     * list view
     */
    @UiField("rateBasisDdlb")
    protected ComboBox rateBasisDdlb;

    /**
     * Rate Period will display values based on the selection from the Rate
     * Frequency ddlb,
     */
    @UiField("ratePeriodDdlb")
    protected ComboBox ratePeriodDdlb;

    @UiField("reset")
    protected Button reset;
    @UiField("generate")
    protected Button generate;

    @UiField("rateFrequencyDdlbLabel")
    protected Label rateFrequencyDdlbLabel;
    @UiField("ratePeriodDdlbLabel")
    protected Label ratePeriodDdlbLabel;

    protected CustomMenuBar.CustomMenuItem customMenuItem;

    protected RateLogic rateLogic = new PipelineAccrualRateLogic();
    protected AbstractSelectionDTO selection;
    protected AbstractRatesSearchResults ratesResults;
    private final CustomNotification notifier = new CustomNotification();
    protected HelperListUtil helperId = HelperListUtil.getInstance();
    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    public AbstractPipelineRates(AbstractSelectionDTO selection) {
        this.selection = selection;
        init();
    }

    private void init() {
        AbstractBPLogic logic = getRatelogicObject();
        ratesResults = getResultsObject(logic, selection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/rate.xml"), this));
        ratesResults.getResults();
        addComponent(ratesResults);
        configureFields();
        configureWorkFlow();
        reset();
    }

    /**
     * load the component with required values and set default selections for
     * the same if any
     */
    public void configureFields() {
        customMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValueDdlb, selection.getDataSelectionDTO().getProjectionId());
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateBasisDdlb, "ARM_RATE_BASIS", false);
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateFrequencyDdlb, "PAYMENT_FREQUENCY", false);
        setDefaultValue();

    }

    /**
     * Sets the default value for DDLBs On Initial Page load
     */
    public void setDefaultValue() {
        try {
            deductionLevelDdlb.setValue(0);
            List<String> defaultValues = rateLogic.getRateConfigSettings(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getCompanyMasterSid(), selection.getDataSelectionDTO().getBucompanyMasterSid(),
                    selection.getDataSelectionDTO().getAdjustmentId(), StringUtils.isNotBlank(selection.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(selection.getDataSelectionDTO().getFromPeriodMonth().trim().split(" ")[0]) : 1)));// Changed for GAL-6120
            if (!defaultValues.isEmpty()) {
                rateBasisDdlb.setValue(Integer.valueOf(defaultValues.get(0)));
                rateFrequencyDdlb.setValue(Integer.valueOf(defaultValues.get(1)));
                ratePeriodDdlb.setValue(rateLogic.getRatePeriod(defaultValues.get(NumericConstants.TWO), rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
            }

        } catch (Exception e) {
            logger.error("Error in setDefaultValue :", e);
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
                    logger.error("Error in reset :", e);
                }
            }
        });
    }

    @UiHandler("generate")
    public void generateButtonClick(Button.ClickEvent event) {
        logger.debug("Inside generate ButtonClick Btn");
        try {
            setSelection();
            if (selection.isRateGenerateAllowed()) {
                ratesResults.setValueChangeAllowed(false);
                ratesResults.generateLogic(selection);
                ratesResults.setValueChangeAllowed(true);
            } else {
                CustomNotification.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
            }
        } catch (Exception e) {
            logger.error("Error in generate :", e);
        }
    }

    @UiHandler("rateFrequencyDdlb")
    public void rateFrequencyValueChange(Property.ValueChangeEvent event) {
        if (rateFrequencyDdlb.getValue() != null && Integer.valueOf(rateFrequencyDdlb.getValue().toString()) != 0) {
            ratePeriodDdlb.removeAllItems();
            Calendar cal = Calendar.getInstance();
            String[] startArr = selection.getDataSelectionDTO().getFromPeriodMonth().split(" ");
            int start = CommonUtils.getMonthNo(startArr[0]) - 1;
            cal.set(Calendar.MONTH, start);
            cal.set(Calendar.YEAR, Integer.valueOf(startArr[1]));
            cal.add(Calendar.MONTH, NumericConstants.THIRTY_SIX);
            String month = AbstractBPLogic.getMonthName(cal.get(Calendar.MONTH) + 1);
            String year = String.valueOf(cal.get(Calendar.YEAR));

            String str = month + " " + year;
            priceddlb = CommonUtils.getPeriodsByFrequency(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromPeriodMonth(), str);
            ratePeriodDdlb.setContainerDataSource(new IndexedContainer(priceddlb));
        } else {
            ratePeriodDdlb.removeAllItems();
            ratePeriodDdlb.addItem(GlobalConstants.getSelectOne());
        }
        ratePeriodDdlb.setValue(GlobalConstants.getSelectOne());
    }

    /**
     * Sets the value from data selection section
     */
    protected void setSelection() {
        selection.setRateDeductionLevel((Integer) deductionLevelDdlb.getValue());
        selection.setRateDeductionLevelName(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()));
        selection.setRateRateColumnList(CommonUtils.getSelectedVariables(customMenuItem, true));
        StringBuilder deductionValues = new StringBuilder();
        if (!selection.getRateColumnList().isEmpty()) {
            List<String> listSize = new ArrayList(selection.getRateColumnList().get(0));
            deductionValues = getDeductionValues(listSize);
        }
        selection.setRateDeductionValue(deductionValues.toString());
        selection.setRateBasisValue((Integer) rateBasisDdlb.getValue());
        selection.setRateBasisName(rateBasisDdlb.getItemCaption(rateBasisDdlb.getValue()));
        selection.setRateFrequencyValue((Integer) rateFrequencyDdlb.getValue());
        selection.setRateFrequencyName(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()));
        String ratePeriod = String.valueOf(ratePeriodDdlb.getValue());
        selection.setRatePeriodValue(ratePeriod != null && !"-Select One-".equals(ratePeriod) ? CommonUtils.getPeriodValue(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()).charAt(0), ratePeriod) : StringUtils.EMPTY);
    }

    private StringBuilder getDeductionValues(List<String> listSize) {
        StringBuilder deductionValues = new StringBuilder();
        if (!listSize.isEmpty()) {
            for (int i = 0; i < listSize.size(); i++) {
                String value = listSize.get(i);
                if (value.contains(".")) {
                    value = value.substring(0, value.lastIndexOf('.'));
                }
                listSize.set(i, value.replace(" ", StringUtils.EMPTY).trim());
                if (i != listSize.size() - 1) {
                    deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append("',");
                } else {
                    deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append(ARMUtils.SINGLE_QUOTES);
                }
            }
        }
        return deductionValues;
    }

    class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        setDefaultValue();
                        break;
                    case "save":
                        // save logic
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param logic
     * @param selectionDto
     * @return
     */
    public abstract AbstractRatesSearchResults getResultsObject(AbstractBPLogic logic, AbstractSelectionDTO selectionDto);

    public abstract AbstractBPLogic getRatelogicObject();

    public void configureFieldsOnViewMode() {
        rateBasisDdlb.setEnabled(false);
        rateFrequencyDdlb.setEnabled(false);
        ratePeriodDdlb.setEnabled(false);
        reset.setEnabled(false);
    }

    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getDataSelectionDTO().getProjectionId());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            setBeanProperties(obj);
        }
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (VariableConstants.RATE_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                } catch (InvocationTargetException | IllegalAccessException ex) {
                    LoggerFactory.getLogger(AbstractPipelineRates.class.getName()).error("Error in Load Details", ex);
                }

            }
        }

    }

    private void setBeanProperties(Object[] obj) throws Property.ReadOnlyException {
        if ("rateDeductionLevel".equals(String.valueOf(obj[0]))) {
            try {
                BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                deductionLevelDdlb.setValue(selection.getRateDeductionLevel());
            } catch (InvocationTargetException | IllegalAccessException ex) {
                LoggerFactory.getLogger(AbstractPipelineRates.class.getName()).error("Error in Load Details", ex);
            }
        }
    }

    public void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            ratesResults.setValueChangeAllowed(false);
            loadDetails();
            String rateBasis = StringUtils.EMPTY;
            if (!"".equals(selection.getRateBasis())) {
                rateBasisDdlb.setValue(Integer.parseInt(selection.getRateBasis()));
                rateBasis = helperId.getDescriptionByID(Integer.parseInt(selection.getRateBasis()));
            }
            if (!"".equals(selection.getRateFrequency())) {
                rateFrequencyDdlb.setValue(Integer.parseInt(selection.getRateFrequency()));
            }
            if (!StringUtils.EMPTY.equals(selection.getRateFrequency())) {
                ratePeriodDdlb.setValue(selection.getRatePeriod());
            }
            selection.setRateBasisName(rateBasis);
            selection.setRatePeriodValue(selection.getRatePeriod());
            if (!StringUtils.isBlank(selection.getRateFrequency())) {
                selection.setRateFrequencyName(helperId.getDescriptionByID(Integer.valueOf(selection.getRateFrequency())));
            }
            selection.setRateRateColumnList(CommonUtils.getSelectedVariables(customMenuItem, true));
            List<String> listSize = getListSize();
            StringBuilder deductionValues = new StringBuilder();
            if (!listSize.isEmpty()) {
                deductionValues = getDeductionValues(listSize);
            }
            selection.setRateDeductionValue(deductionValues.toString());
            String dedLevel = helperId.getDescriptionByID(selection.getRateDeductionLevel());
            selection.setRateDeductionLevelName(dedLevel);
            ratesResults.generateLogic(selection);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            ratesResults.setValueChangeAllowed(true);
        }

    }

    private List<String> getListSize() {
        List<String> listSize;
        if (!selection.getRateColumnList().isEmpty()) {
            listSize = new ArrayList(selection.getRateColumnList().get(0));
        } else {
            listSize = Collections.emptyList();
        }
        return listSize;
    }

    @Override
    public boolean isGenerated() {
        return ratesResults.isGenerated();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return deductionValueDdlb;
    }

    @Override
    public String leaveConfirmationHeader() {
        return ARMMessages.getRatesLeaveConfirmHeaderTransaction7();
    }

    @Override
    public String leaveConfirmationMessage() {
        return ARMMessages.getRatesLeaveConfirmMessageTransaction7();
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
        return ARMMessages.getRatesLeaveConfirmHeaderTransaction7();
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getRatesLeaveConfirmMessageTransaction7();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
