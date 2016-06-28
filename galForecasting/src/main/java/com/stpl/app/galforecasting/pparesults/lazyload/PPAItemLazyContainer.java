/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.lazyload;

import com.stpl.app.galforecasting.pparesults.dto.*;
import com.stpl.app.galforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.galforecasting.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author pvinoth
 */
public class PPAItemLazyContainer implements DAO<PPAHelperDTO>{
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PPAItemLazyContainer.class);
    
    PPAProjectionResultsLogic logic= new PPAProjectionResultsLogic();
    
     String ddlbType= StringUtils.EMPTY;
    PPADetailsDTO ppaDetailsDTO;
    
    private PPAHelperDTO defaultDTO = new PPAHelperDTO(0, Constant.SELECT_ONE,Constant.SELECT_ONE);
    
    
    int count;
    
    public PPAItemLazyContainer(final String ddlbType,final PPADetailsDTO ppaDetailsDTO) {
        this.ddlbType=ddlbType;
        this.ppaDetailsDTO=ppaDetailsDTO;
    }


    @Override
    public int count(SearchCriteria sc) {
        count = 0;
        try {
                count = logic.getPPAItemCount(ppaDetailsDTO,sc.getFilter(),ddlbType);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List<PPAHelperDTO> find(SearchCriteria sc, final int startIndex, final int offset, List<OrderByColumn> list) {
        List<PPAHelperDTO> returnList= new ArrayList<>();
        try {
        if (count!=0) {
            returnList= logic.getPPAItemDDLBResult(startIndex, startIndex + offset,ppaDetailsDTO,ddlbType,sc.getFilter());
        
        }
        if (returnList.isEmpty()) {
            returnList.add(defaultDTO);
        }
        if (startIndex == 0 && ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId() != 0) {
                returnList.add(1,ppaDetailsDTO.getPpaHelperDTO());
            }
        } catch (Exception ex) {
              LOGGER.error(ex);
        }
        return returnList;
    }

    }
