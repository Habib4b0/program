/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.logic;

import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class AbstractSearchLogic {
    
    /**
     * Check search criteria.
     *
     * @param binder the binder
     * @return true, if successful
     */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AbstractSearchLogic.class.getName());

    
    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
                if ((object instanceof TextField && ((TextField) object).isVisible())&&(StringUtils.isNotBlank(((TextField) object).getValue())) || 
                        (object instanceof ComboBox && ((ComboBox) object).isVisible() && !ConstantsUtils.NULL.equals(String.valueOf(((ComboBox) object).getValue())) && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(((ComboBox) object).getValue()))) ||
                        (object instanceof PopupDateField && ((PopupDateField) object).isVisible() && !ConstantsUtils.NULL.equals(String.valueOf(((PopupDateField) object).getValue())))) {
                    isvalid = true;
                    break;
                }
            }
        return isvalid;
    }
    
    /**
     * Gets the Total Number of records present based on the search criteria and
     * module.
     *
     * @param binder
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @param moduleName
     * @return
     */
    public int getCountBasedOnModules(ErrorfulFieldGroup binder, int start, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws ParseException, PortalException {
        int count;
        LOGGER.debug("start ={}",start);
        switch (moduleName) {
            case ConstantsUtils.DEDUCTION_GROUPING:
                count = (Integer) loadDeductionGroupingLogic(binder, isCount, columns, filterSet);
                break;            
            default:
                count = 0;
                break;
        }
        return count;
    }
    
    /**
     * Gets the records based on the search criteria ,module,start and offset
     * values.
     *
     * @param binder
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @param moduleName
     * @return
     */
    public List getSearchResultsBasedOnModules(ErrorfulFieldGroup binder, int start, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws ParseException, PortalException {
        List list;
        LOGGER.debug("start ={}",start);
        switch (moduleName) {
            case ConstantsUtils.DEDUCTION_GROUPING:
                list = (List) loadDeductionGroupingLogic(binder, isCount, columns, filterSet);
                break; 
            default:
                list = new ArrayList();
                break;
        }
        return list;
    } 
    private Object loadDeductionGroupingLogic(ErrorfulFieldGroup binder, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException {

        DiscountLogic discountLogic = new DiscountLogic();
        List list = discountLogic.getDeductionGroup(binder, columns, filterSet);
        if (isCount) {            
            return list.size();
           
        } else {
            return list;
        }
    }
  
}
  
