/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import java.util.List;

/**
 *
 * @author srithar
 */
public abstract class Trx7RateLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T,E> {


    /**
     * 
     * @param result
     * @param selection
     * @param lastParent
     * @return 
     */
    protected abstract List<AdjustmentDTO> customizeResultSet(List result, AbstractSelectionDTO selection, AdjustmentDTO lastParent);


    /**
     * 
     * @param selection
     * @param isCount
     * @return 
     */
    protected abstract Object getRateQuery(AbstractSelectionDTO selection, Object lastParent, boolean isCount, int start, int offset, int currentPage, int lastPage);


    /**
     * returns input for rate query w.r.t. level
     * @param parentDTO
     * @param selection
     * @param input
     * @param queryName
     * @return 
     */
    protected abstract List getQueryInput(AdjustmentDTO parentDTO, AbstractSelectionDTO selection, List input, String queryName);

}

        