/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abhiram.Giri
 * @param <T>
 */
public abstract class AbstractSummarySearchResults extends AbstractSearchResults<AbstractSelectionDTO> {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractSummarySearchResults.class);

    public AbstractSummarySearchResults(LogicAble logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    public AbstractSummarySearchResults() {
    }

    /**
     * Field ddlb.
     *
     * @return
     */
    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("CalculateBtn value change listener starts");
        boolean calculateFlag = false;
        try {
            getSummaryLogic().updateOverrideColumn(selection.getSessionDTO());
            getSummaryLogic().updatecalculateOverride(selection.getSessionDTO(), selection.getDataSelectionDTO().getAdjustmentType());
            for (Object dto : resultBeanContainer.getItemIds()) {
                AdjustmentDTO adjusDTO = (AdjustmentDTO) dto;
                if (adjusDTO.getLevelNo() == NumericConstants.FIVE && adjusDTO.getCalculateFlag()) {
                    calculateFlag = true;
                    adjusDTO.setCalculateFlag(Boolean.FALSE);
                    break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in calculateLogic :", ex);
        }
        return calculateFlag;
    }

    @Override
    public AbstractSummaryLogic getSummaryLogic() {
        return (AbstractSummaryLogic) super.getSummaryLogic();
    }

    @Override
    protected void cancelOverrideLogic() {
        getSummaryLogic().updateTempOverrideColumn(selection.getSessionDTO());
        super.cancelOverrideLogic();
    }

    @Override
    protected void loadLevelValues() {
        super.loadLevelValues();
        customerProductView.addItem(ARMConstants.getCustomerDedection());
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }
}
