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
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.HasExcel;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import java.util.Calendar;
import java.util.Map;

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

    List<String> propertyList = new ArrayList<>();
    AbstractBPLogic logic;
    protected RateLogic rateLogic = new PipelineAccrualRateLogic();
    public AbstractSelectionDTO selection;
    public AbstractRatesSearchResults ratesResults;
    private final CustomNotification notifier = new CustomNotification();
    final static CommonDao ITEMDAO = CommonImpl.getInstance();
    protected HelperListUtil helperId = HelperListUtil.getInstance();
    /**
     * priceddlb holds the list of periods
     */
    protected List<String> priceddlb = null;

    public AbstractPipelineRates(AbstractSelectionDTO selection) {
        this.selection = selection;
        init();
    }

    private void init() {
        logic = getRatelogicObject();
        ratesResults = getResultsObject(logic, selection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/rate.xml"), this));
        ratesResults.getResults();
        addComponent(ratesResults);
        configureFields();
        configureWorkFlow();
        generate();
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
            List<String> defaultValues = rateLogic.getRateConfigSettings(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getCompanyMasterSid(), selection.getDataSelectionDTO().getBu_companyMasterSid(),
                    selection.getDataSelectionDTO().getAdjustmentId(), StringUtils.isNotBlank(selection.getDataSelectionDTO().getFromPeriodMonth()) ? CommonUtils.getMonthNo(selection.getDataSelectionDTO().getFromPeriodMonth().trim().split(" ")[0]) : 1)));// Changed for GAL-6120
            if (!defaultValues.isEmpty()) {
                rateBasisDdlb.setValue(Integer.valueOf(defaultValues.get(0)));
                rateFrequencyDdlb.setValue(Integer.valueOf(defaultValues.get(1)));
                ratePeriodDdlb.setValue(rateLogic.getRatePeriod(defaultValues.get(NumericConstants.TWO), rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromPeriodMonth(), priceddlb));
            }

        } catch (Exception e) {
            LOGGER.error(e);
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
                    setSelection();
                    if (selection.isRateGenerateAllowed()) {
                        ratesResults.setValueChangeAllowed(false);
                        ratesResults.generateLogic(selection);
                        ratesResults.setValueChangeAllowed(true);
                    } else {
                        notifier.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
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
            String month = getRatelogicObject().getMonthName(cal.get(Calendar.MONTH) + 1);
            String year = String.valueOf(cal.get(Calendar.YEAR));

            String str = month + " " + year;
            priceddlb = CommonUtils.getPeriodsByFrequency(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromPeriodMonth(), str);
            ratePeriodDdlb.setContainerDataSource(new IndexedContainer(priceddlb));
        } else {
            ratePeriodDdlb.removeAllItems();
            ratePeriodDdlb.addItem(ConstantsUtils.SELECT_ONE);
        }
        ratePeriodDdlb.setValue(ConstantsUtils.SELECT_ONE);
    }

    /**
     * Sets the value from data selection section
     */
    protected void setSelection() {
        selection.setRate_DeductionLevel((Integer) deductionLevelDdlb.getValue());
        selection.setRate_DeductionLevelName(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()));
        selection.setRate_RateColumnList(CommonUtils.getSelectedVariables(customMenuItem, true));
        String deductionValues = StringUtils.EMPTY;
        if (!selection.getRate_ColumnList().isEmpty()) {
            List<String> listSize = new ArrayList(selection.getRate_ColumnList().get(0));
            if (!listSize.isEmpty()) {
                for (int i = 0; i < listSize.size(); i++) {
                    String value = listSize.get(i);
                    if (value.contains(".")) {
                        value = value.substring(0, value.lastIndexOf("."));
                    }
                    listSize.set(i, value.replace(" ", StringUtils.EMPTY).trim());
                    if (i != listSize.size() - 1) {
                        deductionValues += "'" + value + "',";
                    } else {
                        deductionValues += "'" + value + "'";
                    }
                }
            }
        }
        selection.setRate_DeductionValue(deductionValues);
        selection.setRate_Basis((Integer) rateBasisDdlb.getValue());
        selection.setRate_BasisName(rateBasisDdlb.getItemCaption(rateBasisDdlb.getValue()));
        selection.setRate_Frequency((Integer) rateFrequencyDdlb.getValue());
        selection.setRate_FrequencyName(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()));
        String ratePeriod = String.valueOf(ratePeriodDdlb.getValue());
        selection.setRate_Period(ratePeriod != null && !"-Select One-".equals(ratePeriod) ? CommonUtils.getPeriodValue(rateFrequencyDdlb.getItemCaption(rateFrequencyDdlb.getValue()).charAt(0), ratePeriod) : StringUtils.EMPTY);
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
                        break;
                    case "save":
                        // save logic
                        break;
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

    private void configureFieldsOnViewMode() {
        rateBasisDdlb.setEnabled(false);
        rateFrequencyDdlb.setEnabled(false);
        ratePeriodDdlb.setEnabled(false);
        reset.setEnabled(false);
    }

    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getDataSelectionDTO().getProjectionId());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if ("rate_DeductionLevel".equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                    deductionLevelDdlb.setValue(selection.getRate_DeductionLevel());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AbstractPipelineRates.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AbstractPipelineRates.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if ("rate_DeductionValue".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
            } else if (!"detail_variables".equals(String.valueOf(obj[0])) && !"detail_reserveAcount".equals(String.valueOf(obj[0]))
                    && !"sales_variables".equals(String.valueOf(obj[0])) && !"summary_deductionValues".equals(String.valueOf(obj[0]))
                    && !"summary_variables".equals(String.valueOf(obj[0])) && !VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AbstractPipelineRates.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AbstractPipelineRates.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    private void configureWorkFlow() {
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
            selection.setRate_BasisName(rateBasis);
            selection.setRate_Period(selection.getRatePeriod());
            if (!StringUtils.isBlank(selection.getRateFrequency())) {
                selection.setRate_FrequencyName(helperId.getDescriptionByID(Integer.valueOf(selection.getRateFrequency())));
            }
            selection.setRate_RateColumnList(CommonUtils.getSelectedVariables(customMenuItem, true));
            List<String> listSize = new ArrayList(selection.getRate_ColumnList().get(0));
            String deductionValues = StringUtils.EMPTY;
            if (!listSize.isEmpty()) {
                for (int i = 0; i < listSize.size(); i++) {
                    String value = listSize.get(i);
                    if (value.contains(".")) {
                        value = value.substring(0, value.lastIndexOf("."));
                    }
                    listSize.set(i, value.replace(" ", StringUtils.EMPTY).trim());
                    if (i != listSize.size() - 1) {
                        deductionValues += "'" + value + "',";
                    } else {
                        deductionValues += "'" + value + "'";
                    }
                }
            }
            selection.setRate_DeductionValue(deductionValues);
            String dedLevel = helperId.getDescriptionByID(selection.getRate_DeductionLevel());
            selection.setRate_DeductionLevelName(dedLevel);
            ratesResults.generateLogic(selection);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            ratesResults.setValueChangeAllowed(true);
        }

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

}
