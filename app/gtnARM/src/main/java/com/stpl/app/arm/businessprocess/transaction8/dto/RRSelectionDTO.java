/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.dto;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;

/**
 *
 * @author
 */
public class RRSelectionDTO extends AbstractSelectionDTO {

    @Override
    public boolean isRateGenerateAllowed() {
        return this.getRateDeductionLevel() != 0 && StringUtils.isNotBlank(this.getRateDeductionValue());
    }
}
