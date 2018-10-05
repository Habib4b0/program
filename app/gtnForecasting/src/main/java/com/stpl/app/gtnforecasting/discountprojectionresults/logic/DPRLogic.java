/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pvinoth
 */
public class DPRLogic {

    private List<DiscountProjectionResultsDTO> projectionTotalList = new ArrayList<>();

    public List<DiscountProjectionResultsDTO> getProjectionTotalList() {
        return projectionTotalList;
    }

    public void setProjectionTotalList(List<DiscountProjectionResultsDTO> projectionTotalList) {
        this.projectionTotalList = projectionTotalList;
    }

}
