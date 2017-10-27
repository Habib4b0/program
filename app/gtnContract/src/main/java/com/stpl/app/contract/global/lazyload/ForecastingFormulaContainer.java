/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.global.lazyload;

import com.stpl.app.contract.dashboard.dto.RSFormulaDTO;
import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Shrihariharan
 */
public class ForecastingFormulaContainer implements BeanDAO<RSFormulaDTO>{
    
    private static final Logger LOGGER = Logger.getLogger(ForecastingFormulaContainer.class);
    private RebateScheduleLogic logic = new RebateScheduleLogic();
    @Override
    public int count(BeanSearchCriteria bsc) {
        LOGGER.debug("Entering inside Formula count");
            try{
               return logic.getForecastingFormulaCount();
            }catch(Exception e){
            LOGGER.error(e);
            }
        
        return 0;
    }

    @Override
    public List<RSFormulaDTO> find(BeanSearchCriteria bsc, int start, int offset, List<OrderByColumn> list) {
        List<RSFormulaDTO> resultList = new ArrayList<>();
            try{
                
                return logic.getForecastingFormula(start, start+offset);
            }catch(Exception e){
            LOGGER.error(e);
            }
        return resultList;
    }
    
}
