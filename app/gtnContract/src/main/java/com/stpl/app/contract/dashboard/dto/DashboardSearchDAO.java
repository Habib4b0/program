/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.dashboard.logic.DashboardComponentSearchLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;

/**
 * The Class DashboardSearchDAO.
 *
 * @author soundarrajan
 */
public class DashboardSearchDAO implements BeanDAO<ContractMember> {

    /**
     * Logger used for DashboardSearchDAO.
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardSearchDAO.class);
    
    DashboardComponentSearchLogic logic = new DashboardComponentSearchLogic();
    /**
     * DashBoardSearchDto dto class named as searchDTO.
     */
    public DashBoardSearchDto searchDTO;
    
    private String component;
    
      
    
    /**
     * Left Side Search Component Binder
     */
    CustomFieldGroup leftSearchBinder;
   

    /**
     * Gets the search dto.
     *
     * @return the search dto
     */
    public DashBoardSearchDto getSearchDTO() {
        return searchDTO;
    }

    /**
     * Sets the search dto.
     *
     * @param searchDTO the search dto
     */
    public void setSearchDTO(final DashBoardSearchDto searchDTO) {
        this.searchDTO = searchDTO;
    }

   

    /**
     * Constructor used DashBoardSearchDto named as searchDTO.
     *
     * @param searchDTO the search dto
     */
    public DashboardSearchDAO(final CustomFieldGroup leftSearchBinder,String component) {
        this.leftSearchBinder = leftSearchBinder;
        this.component=component;
       
    }

    /**
     * Method used for count SearchCriteria.
     *
     * @param searchCriteria the search criteria
     * @return the int
     */
    public int count(final BeanSearchCriteria searchCriteria) {

        try {
            LOGGER.debug("enters count()");
            int count=logic.getComponentSearchCount(leftSearchBinder,component,searchCriteria);
            LOGGER.debug("method count ends with count = " + count);
            return count;
        } catch (Exception ex) {
           final  String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return 0;
    }

    /**
     * Method used for find SearchCriteria.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list< contract member>
     */
    public List<ContractMember> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        List<ContractMember> contractList = new ArrayList<>();
        try {
            LOGGER.debug("Enters getAlSearchList method with parameters startIndex=" + startIndex + ", offset=" + offset + ", columns size=" + columns.size());
            
            	contractList=logic.getComponentSearch(leftSearchBinder,component,criteria, startIndex, offset);
                LOGGER.debug("End of  getAlSearchList method with contractList size=" + contractList.size());
           
        } catch (Exception ex) {
          final   String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } 

        return contractList;
    }
}
