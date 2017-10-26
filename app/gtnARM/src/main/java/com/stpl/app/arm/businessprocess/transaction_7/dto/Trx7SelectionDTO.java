/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class Trx7SelectionDTO extends AbstractSelectionDTO {

    List<String[]> sales_variables;

    public List<String[]> getSales_variables() {
        return sales_variables;
    }

    public void setSales_variables(List<String[]> sales_variables) {
        this.sales_variables = sales_variables;
    }
    
    @Override
      public boolean isRateGenerateAllowed() {
        return this.getRate_DeductionLevel() != 0 && StringUtils.isNotBlank(this.getRate_DeductionValue());
    }


}
