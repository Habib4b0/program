/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.rates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineRates;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRRatesLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @aut
 */
public class RatesReturnsReserve extends AbstractPipelineRates {

    private final RatesReturnsReserve.ReturnReserveCustomNotification notifier = new RatesReturnsReserve.ReturnReserveCustomNotification();

    private static final Logger RATES_RR_LOGGER = LoggerFactory.getLogger(RatesReturnsReserve.class);

    public RatesReturnsReserve(AbstractSelectionDTO selection) {
        super(selection);
    }

    @Override
    public void setDefaultValue() {
        try {
            rateFrequencyDdlb.setVisible(false);
            ratePeriodDdlb.setVisible(false);
            rateFrequencyDdlbLabel.setVisible(false);
            ratePeriodDdlbLabel.setVisible(false);
            deductionLevelDdlb.setValue(0);
            rateBasisDdlb.select(HelperListUtil.getInstance().getIdByDesc("ARM_RATE_BASIS", "Calculated"));
            rateBasisDdlb.setReadOnly(true);
        } catch (Exception e) {
            RATES_RR_LOGGER.error("Error in setDefaultValue :", e);
        }
    }

    @UiHandler("reset")
    public void resetBtnLogic(Button.ClickEvent event) {
        notifier.setButtonName("reset");
        notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
    }

    @Override
    public AbstractRatesSearchResults getResultsObject(AbstractBPLogic logic, AbstractSelectionDTO selectionDto) {
        selectionDto.setModuleName(ARMConstants.getTransaction8());
        boolean isView = selectionDto.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);

        if (isView) {
            selectionDto.setTableName("ARM_RETURN_RATE");
        } else {
            selectionDto.setTableName(selectionDto.getSessionDTO().getCurrentTableNames().get("ST_ARM_RETURN_RATE"));
        }

        return new RatesReturnReserveSearchResults(logic, selectionDto);
    }

    @Override
    public AbstractBPLogic getRatelogicObject() {
        return new RRRatesLogic();
    }

    @Override
    public boolean saveAssets() {
        return true;
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getRatelogicObject();
    }

    public class ReturnReserveCustomNotification extends AbstractNotificationUtils {

        private String ratesButtonName;

        public ReturnReserveCustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", ratesButtonName);
            if (null != ratesButtonName && "reset".equals(ratesButtonName)) {
                deductionLevelDdlb.select(0);
                CommonUtils.unCheckMenuBarItem(customMenuItem);

            }
        }

        public void setButtonName(String buttonName) {
            this.ratesButtonName = buttonName;
        }

    }

    @Override
    public void setSelection() {
        selection.setRateDeductionLevel((Integer) deductionLevelDdlb.getValue());
        selection.setRateDeductionLevelName(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()));
        selection.setRateRateColumnList(CommonUtils.getSelectedVariables(customMenuItem, Boolean.TRUE));
        StringBuilder deductionValues = new StringBuilder(StringUtils.EMPTY);
        if (!selection.getRateColumnList().isEmpty()) {
            List<String> listSize = new ArrayList(selection.getRateColumnList().get(0));
            if (!listSize.isEmpty()) {
                for (int i = 0; i < listSize.size(); i++) {
                    String value = listSize.get(i);
                    if (value.contains(ARMUtils.DOT)) {
                        value = value.substring(0, value.lastIndexOf('.'));
                    }
                    listSize.set(i, value.replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).trim());
                    if (i != listSize.size() - 1) {
                        deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append("',");
                    } else {
                        deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append(ARMUtils.SINGLE_QUOTES);
                    }
                }
            }
        }
        selection.setRateDeductionValue(deductionValues.toString());
        selection.setRateBasisValue((Integer) rateBasisDdlb.getValue());
        selection.setRateBasisName(rateBasisDdlb.getItemCaption(rateBasisDdlb.getValue()));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void loadDetails() {
        try {
            StringBuilder variablesBuilder = new StringBuilder();
            variablesBuilder.append(VariableConstants.RATE_DEDUCTION_LEVEL_FIELD).append(ARMUtils.COMMA_CHAR)
                    .append(VariableConstants.RATE_DEDUCTION_VALUE_FIELD).append(ARMUtils.COMMA_CHAR)
                    .append(VariableConstants.RATE_BASIS_FIELD);
            List<Object[]> list = CommonLogic.loadReturnReserve(selection.getDataSelectionDTO().getProjectionId(), variablesBuilder.toString());
            for (Object[] obj : list) {
                if (VariableConstants.RATE_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                    deductionLevelDdlb.setValue(selection.getRateDeductionLevel());
                    String[] values = String.valueOf(obj[1]).split(",");
                    for (String value : values) {
                        CommonUtils.checkMenuBarItem(customMenuItem, value);
                    }
                } else {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException ex) {
            RATES_RR_LOGGER.error(ex.getMessage());
        }

    }

    @Override
    public void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            ratesResults.setValueChangeAllowed(false);
            loadDetails();
            rateBasisDdlb.setValue(selection.getRateBasis());
            generateButtonClick(null);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
        }

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return deductionLevelDdlb;
    }

}
