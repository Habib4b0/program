/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.logic;

import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Manasa
 */
public class AbstractSearchLogic {
    
    String moduleName;
    ErrorfulFieldGroup binder;
    String searchCriteria;
    int versionNo;
    
    
    /**
     * Check search criteria.
     *
     * @param binder the binder
     * @return true, if successful
     */
    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
            if (object != null && object instanceof TextField && ((TextField) object).isVisible()) {
                if (StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible() && !ConstantsUtils.NULL.equals(String.valueOf(((ComboBox) object).getValue())) && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(((ComboBox) object).getValue()))) {
                    isvalid = true;
                    break;
                }
            else if (object != null && object instanceof PopupDateField && ((PopupDateField) object).isVisible() && !ConstantsUtils.NULL.equals(String.valueOf(((PopupDateField) object).getValue()))) {
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
     * @param searchCriteria     
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.text.ParseException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * 
     * @return
     */
    public int getCountBasedOnModules(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet, final String moduleName, final String searchCriteria) throws SystemException, ParseException, PortalException {
        int count;
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
    public List getSearchResultsBasedOnModules(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet, final String moduleName, final String searchCriteria) throws SystemException, ParseException, PortalException {
        List list;
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
  
