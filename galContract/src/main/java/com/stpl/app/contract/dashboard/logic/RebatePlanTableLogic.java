/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.dashboard.dto.RebatePlanDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Sibi
 */
public class RebatePlanTableLogic extends PageTableLogic{

    private RebatePlanDTO rebatePlanDTO;
    private boolean isGenerate;
    private final DashBoardLogic dashBoardLogic = new DashBoardLogic();
    private final static Logger LOGGER = Logger.getLogger(RebatePlanTableLogic.class);
    CustomFieldGroup binder;
    private boolean isReset = false;
    
    @Override
    public int getCount() {
        int count = 0;        
        if (isGenerate) {
            try {                
                count = (Integer) dashBoardLogic.loadRebatePlan(rebatePlanDTO,0,0,true,this.getFilters(),null);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RebatePlanDTO> resultList = (List<RebatePlanDTO>) dashBoardLogic.loadRebatePlan(rebatePlanDTO,start,offset,false,this.getFilters(),this.getSortByColumns());        
        return resultList;
    }

    /**
     * 
     * @param object
     * @param container
     * @return 
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        RebatePlanDTO dto = (RebatePlanDTO) object;
        ((BeanItemContainer<RebatePlanDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param rebatePlanDTO 
    */
    public void setSearchData(final RebatePlanDTO rebatePlanDTO,final CustomFieldGroup binder){   
        isGenerate = true;
        isReset = false;
        clearAll();
        this.rebatePlanDTO = rebatePlanDTO;
        this.binder = binder;
        setRequiredCount(true);
        this.setCurrentPage(1);
                  
    }
    
}
