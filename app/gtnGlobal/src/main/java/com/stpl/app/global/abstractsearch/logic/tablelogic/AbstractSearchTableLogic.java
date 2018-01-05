/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.abstractsearch.logic.tablelogic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.global.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sibi
 */
public class AbstractSearchTableLogic extends PageTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSearchTableLogic.class);
    
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    private AbstractSearchForm abstractSearchForm;    
    private String moduleName;
    private ErrorfulFieldGroup binder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = searchLogic.getCountBasedOnModules(binder, abstractSearchForm, 0, 0, true, null, this.getFilters(), moduleName);
            }
            isResultsEmpty = count == 0;            
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = searchLogic.getSearchResultsBasedOnModules(binder, abstractSearchForm, start, offset, false, this.getSortByColumns(), this.getFilters(), moduleName);
            } catch (Exception ex) {   
                LOGGER.error("",ex);
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

    public void configureSearchData(final ErrorfulFieldGroup binder, String moduleName, AbstractSearchForm obj) {
        isFirstLoad = true;
        isReset = false;
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

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }    

    }
