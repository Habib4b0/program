/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.logic.tablelogic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 *
 * @author maheshj
 */
public class CDLookupTableLogic extends PageTableLogic{
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    
    private boolean isGenerate;
    private final static Logger LOGGER = Logger.getLogger(CDLookupTableLogic.class);
    ErrorfulFieldGroup binder;
    CDRLogic cdrLogic = new CDRLogic();
    SearchResultsDTO searchResultDto;
    
    @Override
    public int getCount() {
        int count = 0;        
        if (isGenerate) {
            try {                
                count = (Integer) cdrLogic.getCDRCount(binder, this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<SearchResultsDTO> resultList = null;        
        try {
            resultList = (List<SearchResultsDTO>)cdrLogic.loadCDRResults(binder, start, start + offset, this.getSortByColumns(), this.getFilters());
        } catch (Exception ex) {
              LOGGER.error(ex);
        }
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
        SearchResultsDTO dto = (SearchResultsDTO) object;
        ((BeanItemContainer<SearchResultsDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
     * @param netSalesDTO 
     * @param binder 
    */
    public void setSearchData(final SearchResultsDTO searchResultDto, final ErrorfulFieldGroup binder){        
        clearAll();
        this.searchResultDto = searchResultDto;
        this.binder = binder;
        setRequiredCount(true);
        isGenerate = true;          
    }
    
}
