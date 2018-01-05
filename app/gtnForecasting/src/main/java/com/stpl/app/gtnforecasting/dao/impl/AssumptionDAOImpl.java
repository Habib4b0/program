/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.AssumptionDAO;
import com.stpl.app.gtnforecasting.dto.AssumptionPVDTO;
import com.stpl.app.gtnforecasting.service.finderImpl.NMSalesProjectionMasterImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harlin
 */
public class AssumptionDAOImpl implements AssumptionDAO{

	/**
	 * To get the result from Customer ID Transfer Table
	 */
    @Override
    public List<?> getCIDTResults() {
        return new ArrayList<>();
    }

    /**
     * To get the Trading Partner Maintenance-Sales Results.
     */
    @Override
    public List<?>  getAssumptionResults(List input, String queryName) {
        return new NMSalesProjectionMasterImpl()
                .getAssumptionResult(input, queryName);
    }

    /**
     * To get the PPA Results.
     */
    @Override
    public List<?>  getPVResults() {
        return new ArrayList<>();
    }

    /**
     * To save the line to DB
     * 
     * @param parent
     */
    @Override
    public void saveNewLinePVS(AssumptionPVDTO parent) {
        return;
    }

    /**
     * To remove the line from DB
     * 
     * @param child
     */
    @Override
    public void deleteLinePVS(AssumptionPVDTO child) {
        return;
    }
    
}
