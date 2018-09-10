/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.ifs.util.constants.ARMConstants;

/**
 *
 * @author vishal.chaudhary
 */
public class ARMCheckUtils {
    
    private ARMCheckUtils() {
        
    }

    public static boolean isSingleVisibleColumnPresentInDto(int singleVisibleColumn, AdjustmentDTO dto) {
        if(dto.getMasterIds().isEmpty()) {
        return false;
        }
        return singleVisibleColumn == (dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
    }

    public static boolean checkIsSummaryTypeDeductionCustomerContract(AbstractSelectionDTO selection) {
        if(selection.getSummaryviewType().isEmpty()) {
            return false;
        }
        return selection.getSummaryviewType().equalsIgnoreCase(ARMConstants.getDeductionCustomerContract());
    }

    public static boolean checkIsProductFilterLevel(AbstractSelectionDTO selection) {
        if(selection.getSummarylevelFilterValue().isEmpty()) {
            return false;
        }
        return selection.getSummarylevelFilterValue().equals("Product");
    }
}
