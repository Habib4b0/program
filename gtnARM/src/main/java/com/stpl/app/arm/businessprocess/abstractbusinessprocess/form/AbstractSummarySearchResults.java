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
import org.jboss.logging.Logger;

/**
 *
 * @author Abhiram.Giri
 * @param <T>
 */
public abstract class AbstractSummarySearchResults<T extends AbstractSelectionDTO> extends AbstractSearchResults<AbstractSelectionDTO>{
    /**
	 * The Constant LOGGER.
	 */
	public static final Logger LOGGER = Logger.getLogger(AbstractSummarySearchResults.class);
    public AbstractSummarySearchResults(LogicAble logic, AbstractSelectionDTO selection) {
        super(logic, selection);
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
            getLogic().updateOverrideColumn(selection.getSessionDTO());
            getLogic().updatecalculateOverride(selection.getSessionDTO(),selection.getDataSelectionDTO().getAdjustmentType());
            for (Object dto : resultBeanContainer.getItemIds()) {
                AdjustmentDTO adjusDTO = (AdjustmentDTO) dto;
                if (adjusDTO.getLevelNo() == NumericConstants.FIVE && adjusDTO.getCalculateFlag()) {
                    calculateFlag=true;
                    adjusDTO.setCalculateFlag(Boolean.FALSE);
                    break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return calculateFlag;
    }
    @Override
    public AbstractSummaryLogic getLogic() {
        return (AbstractSummaryLogic) super.getLogic();
    }
        @Override
     protected void cancelOverrideLogic(){
        getLogic().updateTempOverrideColumn(selection.getSessionDTO());
        super.cancelOverrideLogic();
    }
}
