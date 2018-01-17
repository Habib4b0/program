/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.stpl.app.gtnforecasting.dto.AssumptionPVDTO;
import java.util.List;

/**
 *
 * @author harlin
 */
public interface AssumptionDAO {
    /**
     * To get the customer ID transfer details.
     * 
     * @return 
     */
    public List getCIDTResults();
    
    /**
     * To get the Trading Partner Maintenance-Sales 
     * @return 
     */
    public List getAssumptionResults(List input, String queryName);
    
    /**
     * 
     * @return 
     */
    public List getPVResults();
    
    /**
     * 
     * @param parent 
     */
    public void saveNewLinePVS(AssumptionPVDTO parent);
    
    /**
     * 
     * @param child 
     */
    public void deleteLinePVS(AssumptionPVDTO child);
   
}
