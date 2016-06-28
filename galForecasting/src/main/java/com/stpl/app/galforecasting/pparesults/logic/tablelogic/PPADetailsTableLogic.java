/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.logic.tablelogic;

import com.stpl.app.galforecasting.pparesults.dto.PPADetailsDTO;
import com.stpl.app.galforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author pvinoth
 */
public class PPADetailsTableLogic extends PageTableLogic{
    
    private PPADetailsDTO ppaDetailsDTO;
    private PPAProjectionResultsLogic logic =new PPAProjectionResultsLogic();
    private boolean isGenerate;
    SessionDTO sessionDTO;
    
    @Override
    public int getCount() {
        int count=0;
        if (isGenerate) {
              count=(Integer) logic.loadPPADetails(ppaDetailsDTO, sessionDTO, true, 0, 0,null);
        }
        
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<PPADetailsDTO> resultList = (List<PPADetailsDTO>) logic.loadPPADetails(ppaDetailsDTO, sessionDTO, false, start, offset, this.getSortByColumns());  
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        PPADetailsDTO dto= (PPADetailsDTO) object;
        ((BeanItemContainer<PPADetailsDTO>) container).addBean(dto);
        return dto;        
    }

    /***
     * 
     * @param ppaDetailsDTO
     * @param sessionDTO 
     */
    public void setSearchData(PPADetailsDTO ppaDetailsDTO, SessionDTO sessionDTO) {
        clearAll();
        this.ppaDetailsDTO=ppaDetailsDTO;
        this.sessionDTO=sessionDTO;
        setRequiredCount(true);
        isGenerate = true;     
    }
    
}
