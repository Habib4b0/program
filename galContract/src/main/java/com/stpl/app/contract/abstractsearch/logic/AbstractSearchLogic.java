/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.abstractsearch.logic;

import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.contract.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class AbstractSearchLogic.
 *
 * @author pvinoth
 */
public class AbstractSearchLogic {

    /** The binder. */
    private CustomFieldGroup binder;
    
    /** The module name. */
    private String moduleName;

   /**
     * Check search criteria.
     *
     * @param binder the binder
     * @return true, if successful
     */
    public boolean checkSearchCriteria(final CustomFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
           if (object != null && object instanceof CustomTextField && ((CustomTextField) object).isVisible()) {
                if (((CustomTextField) object).getValue() != null && StringUtils.isNotBlank(((CustomTextField) object).getValue())) {
                    isvalid = true;
                    break; 
                }
            }else if (object != null && object instanceof TextField && ((TextField) object).isVisible()) {
                
                if("tradingPartnerSystemId".equals(String.valueOf(binder.getPropertyId((Field<?>)object)))){
                        if(!"0".equals(((TextField) object).getValue())){
                            isvalid = true;
                            break;
                         }
                  }else if (((TextField) object).getValue() != null && StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible()) {
                if (((ComboBox) object).getValue() != null && !StringUtils.isBlank(String.valueOf(((ComboBox) object).getValue()))&&( !ConstantUtil.SELECT_ONE.equals(String.valueOf(((ComboBox) object).getValue())) && !"0".equals(String.valueOf(((ComboBox) object).getValue())) )) {
                    isvalid = true;
                    break;
                }
            }
        }
        return isvalid;
    }

    /**
     * Implementation of excel export logic.
     *
     * @param moduleName the module name
     * @param resultTable the result table
     * @param obj the obj
     * @param binder the binder
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void excelExportLogic(String moduleName, ExtFilterTable resultTable, AbstractSearchForm obj, CustomFieldGroup binder) throws PortalException, Exception {
        createWorkSheet(moduleName, resultTable, obj, binder);
    }

    /**
     * Logic to create the worksheet.
     *
     * @param moduleName the module name
     * @param resultTable the result table
     * @param obj the obj
     * @param binder the binder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void createWorkSheet(String moduleName, ExtFilterTable resultTable, AbstractSearchForm obj, CustomFieldGroup binder) throws SystemException, PortalException, Exception {
        long recordCount = 0;
        if (resultTable.size() != 0) {
            recordCount = getCountBasedOnModules(binder, obj, 0, 0, true, null, null, moduleName);
        }
        this.binder = binder;
        this.moduleName = moduleName;
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());

    }

    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
     public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
         
         SearchResultsDTO searchItemForm;
         final List<SortByColumn> columns = new ArrayList<SortByColumn>();

         if (end != 0) {
             if (ConstantUtil.CONTRACT_HEADER.equals(moduleName)) {
                 final List<SearchResultsDTO> reultList = (List<SearchResultsDTO>) new ContractHeaderLogic().searchContractResults(binder, start, end, columns, null);
                 for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                     searchItemForm = (SearchResultsDTO) reultList.get(rowCount);
                     printWriter.print(UIUtils.QUOTE + searchItemForm.getContractId() + UIUtils.QUOTE + UIUtils.COMMA);
                     printWriter.print(UIUtils.QUOTE + searchItemForm.getContractNo() + UIUtils.QUOTE + UIUtils.COMMA);
                     printWriter.print(UIUtils.QUOTE + searchItemForm.getContractName() + UIUtils.QUOTE + UIUtils.COMMA);
                     if (searchItemForm.getContractType() != null && !searchItemForm.getContractStatus().equals(Constants.SELECT_ONE)) {
                         printWriter.print(UIUtils.QUOTE + searchItemForm.getContractStatus() + UIUtils.QUOTE + UIUtils.COMMA);
                     }
                     if (searchItemForm.getContractType() != null && !searchItemForm.getContractType().equals(Constants.SELECT_ONE)) {
                         printWriter.print(UIUtils.QUOTE + searchItemForm.getContractType() + UIUtils.QUOTE + UIUtils.COMMA);
                     }
                     printWriter.print(UIUtils.QUOTE + searchItemForm.getTradeClass() + UIUtils.QUOTE + UIUtils.COMMA);
                     printWriter.println(UIUtils.QUOTE + searchItemForm.getTradingPartnerName() + UIUtils.QUOTE);
                 }
             }
    }
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
    public int getCountBasedOnModules(CustomFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        int count;
        switch (moduleName) {
            case ConstantUtil.CONTRACT_HEADER:
                count = (Integer) loadContractHeaderLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            default:
                count = 0;
                break;
        }
        return count;
    }

     /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCountForSearch(...) of ContractHeaderLogic to get the count
     * and getResultsForCompany(...) to get the results.
     *
     * @param binder
     * @param obj
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @return
     * @throws Exception
     */
    private Object loadContractHeaderLogic(CustomFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        ContractHeaderLogic chLogic = new ContractHeaderLogic();
        if (isCount) {
            object = (Integer) chLogic.getContractCount(binder,filterSet);
        } else {
            object = chLogic.searchContractResults(binder, start, offset, columns, filterSet);
            obj.setTableDefaultConfig(moduleName);
        }

        return object;
    }
    /**
     * Gets the records based on the search criteria ,module,start and offset
     * values.
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
    public List getSearchResultsBasedOnModules(CustomFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        List list;
        switch (moduleName) {
            case ConstantUtil.CONTRACT_HEADER:
                list = (List) loadContractHeaderLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
           
            default:
                list = new ArrayList();
                break;
        }
        return list;
    }
}
