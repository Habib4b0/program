/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;

/**
 *
 * @author Karthikeyan.Subraman
 */
public interface ARMBusinessProcessTemplate {

    /**
     * Method gets the count from data base based on the implementation.
     *
     * @param dto
     * @param selection
     * @return
     */
    int getCount(Object dto, AbstractSelectionDTO selection);

    /**
     * Method loads the data from data base based on the implementation.
     *
     * @param dto
     * @param selection
     * @param start
     * @param offset
     * @return
     */
    List<AdjustmentDTO> loadData(Object dto, AbstractSelectionDTO selection, int start, int offset);

 
}
