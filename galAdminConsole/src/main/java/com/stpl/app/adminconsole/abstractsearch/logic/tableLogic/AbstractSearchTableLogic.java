/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.logic.tableLogic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class AbstractSearchTableLogic extends PageTableLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchTableLogic.class);
    
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    private AbstractSearchForm abstractSearchForm;    
    private String moduleName;
    private String searchType;
    private String flag;
    private int versionNo;
    private ErrorfulFieldGroup binder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    
    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = searchLogic.getCountBasedOnModules(binder,0, 0, true, this.getSortByColumns(), this.getFilters(), moduleName,searchType,versionNo);
            }
            isResultsEmpty = (count == 0);            
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = searchLogic.getSearchResultsBasedOnModules(binder,  start, offset, false, this.getSortByColumns(), this.getFilters(), moduleName,searchType,versionNo);
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

    public void configureSearchData(final ErrorfulFieldGroup binder, String moduleName, AbstractSearchForm obj, String searchType, int versionNo) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.moduleName = moduleName;
        this.abstractSearchForm = obj;
        this.binder = binder;
        this.searchType = searchType;
        this.versionNo = versionNo;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }    
    
}
