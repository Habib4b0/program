/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.logic;

import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.itemgroup.logic.ItemGroupLogic;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class AbstractSearchLogic {
    
private static final Logger LOGGER = Logger.getLogger(AbstractSearchLogic.class);

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
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible()) {

                if (!ConstantsUtils.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
                }
            }
        }
        return isvalid;
    }
    
    /**
     * Gets the Total Number of records present based on the search criteria and
     * module.
     *
     * @param binder
     * @param obj
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @param moduleName
     * @return
     * @throws Exception
     */
    public int getCountBasedOnModules(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet, final String moduleName, final String searchCriteria, final int versionNo) throws Exception {
        int count;
        switch (moduleName) {
            case ConstantsUtils.CUSTOMER_GROUP_MASTER:
                if(searchCriteria.equals(ConstantsUtils.SEARCH)){
                count = (Integer) loadCustomerGroupMasterLogic(binder,ConstantsUtils.VERSION_CURRENT, start, offset, isCount, columns, 
                        filterSet, ConstantsUtils.CUSTOMER_GROUP_MASTER);
                }else {
                   count = (Integer) loadCustomerGroupMasterLogic(binder,ConstantsUtils.VERSION_HIST, start, offset, isCount, columns, 
                        filterSet, ConstantsUtils.CUSTOMER_GROUP_MASTER);
                }
                break;
                case ConstantsUtils.ITEM_GROUP_MASTER:
                    if (searchCriteria.equals(ConstantsUtils.SEARCH)) {
                        count = (Integer) loadItemGroupMasterLogic(binder, ConstantsUtils.VERSION_CURRENT, start, offset, isCount, columns,
                                filterSet, ConstantsUtils.ITEM_GROUP_MASTER);
                    } else {
                        count = (Integer) loadItemGroupMasterLogic(binder, ConstantsUtils.VERSION_HIST, start, offset, isCount, columns,
                                filterSet, ConstantsUtils.ITEM_GROUP_MASTER);
                    }
                break;
            case ConstantsUtils.HIERARCHY_DEF:
                count = (Integer) loadHierarchyDefLogic(binder, searchCriteria,start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantsUtils.RELATIONSHIP_BUILDER:
                count = (Integer) loadRelationshipBuilderLogic(binder,searchCriteria,versionNo, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantsUtils.DEDUCTION_GROUPING:
                count = (Integer) loadDeductionGroupingLogic(binder, start, offset, isCount, columns, filterSet, moduleName);
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
     * @throws Exception
     */
    public List getSearchResultsBasedOnModules(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet, final String moduleName, final String searchCriteria, final int versionNo) throws Exception {
        List list;
        switch (moduleName) {
            case ConstantsUtils.CUSTOMER_GROUP_MASTER:
                if(searchCriteria.equals(ConstantsUtils.SEARCH)){
                list = (List) loadCustomerGroupMasterLogic(binder,ConstantsUtils.VERSION_CURRENT, start, offset, isCount, columns, 
                        filterSet, ConstantsUtils.CUSTOMER_GROUP_MASTER);
                } else {
                    list = (List) loadCustomerGroupMasterLogic(binder,ConstantsUtils.VERSION_HIST, start, offset, isCount, columns, 
                        filterSet, ConstantsUtils.CUSTOMER_GROUP_MASTER);
                }
                break;
            case ConstantsUtils.ITEM_GROUP_MASTER:
                if(searchCriteria.equals(ConstantsUtils.SEARCH)){
                    list = (List) loadItemGroupMasterLogic(binder,ConstantsUtils.VERSION_CURRENT,start, offset, isCount, columns, 
                            filterSet, ConstantsUtils.ITEM_GROUP_MASTER);
                } else {
                    list = (List) loadItemGroupMasterLogic(binder,ConstantsUtils.VERSION_HIST,start, offset, isCount, columns, 
                            filterSet, ConstantsUtils.ITEM_GROUP_MASTER);
                }
                
                
                break;
            case ConstantsUtils.HIERARCHY_DEF:
                list = (List) loadHierarchyDefLogic(binder,searchCriteria,start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantsUtils.RELATIONSHIP_BUILDER:
                list = (List) loadRelationshipBuilderLogic(binder,searchCriteria,versionNo, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantsUtils.DEDUCTION_GROUPING:
                list = (List) loadDeductionGroupingLogic(binder, start, offset, isCount, columns, filterSet, moduleName);
                break; 
            default:
                list = new ArrayList();
                break;
        }
        return list;
    }    
   
    private Object loadCustomerGroupMasterLogic(ErrorfulFieldGroup binder, String version, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        CustomerGroupLogic customerGroupLogic = new CustomerGroupLogic();
        if (isCount) {
            object = customerGroupLogic.getSearchResults(binder, version,start, start + offset, columns, filterSet,true);
        } else {
            object = customerGroupLogic.getSearchResults(binder, version,start, start + offset, columns, filterSet,false);
        }
        return object;
    }
    
    private Object loadItemGroupMasterLogic(ErrorfulFieldGroup binder, String version, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        ItemGroupLogic itemGroupLogic = new ItemGroupLogic();
        if (isCount) {
            object = itemGroupLogic.getSearchResults(binder, version,start, start + offset, columns, filterSet,true);
        } else {
            object = itemGroupLogic.getSearchResults(binder, version,start, start + offset, columns, filterSet,false);
        }
        return object;
    }
    private Object loadHierarchyDefLogic(ErrorfulFieldGroup binder,String searchCriteria, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        HierarchyBuilderLogic hierarchyBuilderLogic = new HierarchyBuilderLogic();
        if(searchCriteria.equals(ConstantsUtils.SEARCH)){
            List list = hierarchyBuilderLogic.getSearchResults(binder,start, start + offset, columns, filterSet,true);
            if (isCount) {            
                return list.size();

            } else {
                return list;
            }
        } else {
            List auditList = hierarchyBuilderLogic.getHistorySearchResults(binder,start, start + offset, columns, filterSet,true);
            if (isCount) {            
                return auditList.size();

            } else {
                return auditList;
            }
        }
    }
    
    private Object loadRelationshipBuilderLogic(ErrorfulFieldGroup binder,final String searchCriteria, final int versionNo, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        RelationBuilderLogic relationBuilderLogic = new RelationBuilderLogic();
        
        List list = relationBuilderLogic.getSearchResults(binder,searchCriteria,versionNo,start, start + offset, columns, filterSet,isCount);
        if (isCount) {            
            return list.size();
           
        } else {
            return list;
        }
    }
    private Object loadDeductionGroupingLogic(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        DiscountLogic discountLogic = new DiscountLogic();
        List list = discountLogic.getDeductionGroup(binder,start, start + offset, columns, filterSet,true);
        if (isCount) {            
            return list.size();
           
        } else {
            return list;
        }
    }
  
}
  
