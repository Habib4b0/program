/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.abstractsearch.logic.tablelogic;

import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.contract.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.contract.abstractsearch.ui.AbstractSearchForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 * The Abstract Search Table Logic class for Paged table
 * @author vinodhini
 */
public class AbstractSearchTableLogic extends PageTableLogic {

    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    private AbstractSearchForm abstractSearchForm;    
    private String moduleName;
    private CustomFieldGroup binder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
   /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchTableLogic.class);
    
    @Override
    public int getCount() {
        LOGGER.info("Entering getCount");
        int count = 0;
        if (isFirstLoad) {
            try {
                count = searchLogic.getCountBasedOnModules(binder, abstractSearchForm, 0, 0, true, null, this.getFilters(), moduleName);
            } catch (Exception ex) {
              LOGGER.error(ex);
            }
        }
        isResultsEmpty = (count==0);
        LOGGER.info("getCount="+count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = searchLogic.getSearchResultsBasedOnModules(binder, abstractSearchForm, start, offset, false, this.getSortByColumns(), this.getFilters(), moduleName);
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
      
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        SearchResultsDTO dto = (SearchResultsDTO) object;
        ((BeanItemContainer<SearchResultsDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final CustomFieldGroup binder, String moduleName, AbstractSearchForm obj) {
        isFirstLoad = true;
        this.clearAll();
        this.getFilters().clear();
        this.moduleName = moduleName;
        this.abstractSearchForm = obj;
        this.binder = binder;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setIsResultsEmpty(boolean isResults) {
        this.isResultsEmpty = isResults;
    }

}
