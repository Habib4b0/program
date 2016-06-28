/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.abstractsearch.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.rebateplan.logic.RebatePlanSearchLogic;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.util.ConstantsUtils.QUOTE;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 * The Class AbstractSearchLogic.
 *
 * @author pvinoth
 */
public class AbstractSearchLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchLogic.class);
    
    /** The binder. */
    ErrorfulFieldGroup binder;
    
    /** The module name. */
    String moduleName;
    private static HelperListUtil helperListUtil=HelperListUtil.getInstance();
    private static ResourceBundle excelProperties = ResourceBundle.getBundle("properties.excelheader");

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

                if (!ConstantUtil.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
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
    public void excelExportLogic(String moduleName, ExtFilterTable resultTable, AbstractSearchForm obj,ErrorfulFieldGroup binder) throws PortalException, Exception {
        createWorkSheet(moduleName,resultTable, obj, binder);
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
    private void createWorkSheet(String moduleName,ExtFilterTable resultTable, AbstractSearchForm obj, ErrorfulFieldGroup binder) throws SystemException, PortalException, Exception {

        long recordCount = 0;
        if (resultTable.size() != 0) {
            recordCount = getCountBasedOnModules(binder, obj, 0, 0, true, null, null, moduleName);
        }
        this.binder=binder;
        this.moduleName=moduleName;
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
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        String date;
        SearchResultsDTO searchCompanyForm;
        final List<SortByColumn> columns = new ArrayList<SortByColumn>();

        try {
            if (end != 0) {
                if (ConstantUtil.COMPANY_MAST.equals(moduleName)) {
                    
                        SearchResultsDTO searchResultsDTO;
                        final List<SearchResultsDTO> searchList = (List<SearchResultsDTO>) new CompanySearchLogic().getResultsForCompany(binder, start, end, columns, null,false);
                        List printableDto= new ArrayList<>();
                        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {
                            searchResultsDTO = (SearchResultsDTO) searchList.get(rowCount);
                            printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("companyExcelHeader").split(",")));
                         }                    
                        printCsv(printableDto,printWriter);          
                                        
                }
                if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
                        
                        SearchResultsDTO searchResultsDTO;
                        DeductionCalendarLogic deductionLogic = new DeductionCalendarLogic();
                        final List<SearchResultsDTO> reultList = (List) deductionLogic.getCountAndResultsForDeductionCalendar(binder, start, end, null, null, false);
                        List printableDto= new ArrayList<>();
                        for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                            searchResultsDTO = (SearchResultsDTO) reultList.get(rowCount);
                            printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("deductionExcelHeader").split(",")));
                         }                    
                        printCsv(printableDto,printWriter);                         
                    
                }
                if (ConstantUtil.ITEM_FAMILY_PLAN.equals(moduleName)) {
                    
                        SearchResultsDTO searchResultsDTO;
                        final List<SearchResultsDTO> reultList = new IFPLogic().getResultsForIFP(binder, start, end, columns, null);
                        List printableDto= new ArrayList<>();
                        for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                            searchResultsDTO = (SearchResultsDTO) reultList.get(rowCount);
                            printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("ifpExcelHeader").split(",")));
                         }                    
                        printCsv(printableDto,printWriter);  
                   
                }
                if (ConstantUtil.PRICE_SCHEDULE_MASTER.equals(moduleName)) {
                        SearchResultsDTO searchResultsDTO;
                        final List<SearchResultsDTO> resultList = new PSLogic().getResultsForPS(binder, start, end, columns, null);
                        List printableDto= new ArrayList<>();
                        for (int rowCount = 0; rowCount < resultList.size(); rowCount++) {
                            searchResultsDTO = (SearchResultsDTO) resultList.get(rowCount);
                            printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("psExcelHeader").split(",")));
                         }                    
                        printCsv(printableDto,printWriter);                   
                }
                if (ConstantUtil.REBATE_SCHEDULE_MASTER.equals(moduleName)) {

                        SearchResultsDTO searchResultsDTO;
                        final List list = (List) new RebateScheduleLogic().getCountAndResultsForRS(binder, start, end, columns, false, null, false);
                         List<SearchResultsDTO> resultList = new RebateScheduleLogic().getCustomizedSearchForHelper(list);
                        List printableDto= new ArrayList<>();
                        for (int rowCount = 0; rowCount < resultList.size(); rowCount++) {
                            searchResultsDTO = (SearchResultsDTO) resultList.get(rowCount);
                            printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("rsExcelHeader").split(",")));
                         }                    
                        printCsv(printableDto,printWriter);
                    
                }
                if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
                    
                    SearchResultsDTO searchResultsDTO;
                    final List<SearchResultsDTO> reultList = new RebatePlanSearchLogic().getRebatePlanResults(binder, start, end, columns, null);
                    List printableDto= new ArrayList<>();
                    for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                        searchResultsDTO = (SearchResultsDTO) reultList.get(rowCount);
                        printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("rpExcelHeader").split(",")));
                     }                    
                    printCsv(printableDto,printWriter);                     
                }              
                
                
                 if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                     
                    SearchResultsDTO searchResultsDTO;
                    final List<SearchResultsDTO> resultList = new CDRLogic().loadCDRResults(binder, start, end, columns, null);
                    List printableDto= new ArrayList<>();
                    for (int rowCount = 0; rowCount < resultList.size(); rowCount++) {
                        searchResultsDTO = (SearchResultsDTO) resultList.get(rowCount);
                        printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("cdrExcelHeader").split(",")));
                     }                    
                    printCsv(printableDto,printWriter);                     
                    
                }
                
                
                if (ConstantUtil.ITEM_MASTER.equals(moduleName)) {
                    
                    SearchResultsDTO searchResultsDTO;
                   final List<SearchResultsDTO> itemlist = new ItemSearchLogic().getResultsForSearch(binder, start, end, columns, null);
                    List printableDto= new ArrayList<>();
                    for (int rowCount = 0; rowCount < itemlist.size(); rowCount++) {
                        searchResultsDTO = (SearchResultsDTO) itemlist.get(rowCount);
                        printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("itemExcelHeader").split(",")));
                     }
                    
                    printCsv(printableDto,printWriter);
                    
                }
                if (ConstantUtil.COMPANY_FAMILY_PLAN.equals(moduleName)) {
                    
                    SearchResultsDTO searchResultsDTO;
                     final List<SearchResultsDTO> cfpList = new CFPSearchLogic().getResultsForCFP(binder, start, end, columns, null);
                    List printableDto= new ArrayList<>();
                    for (int rowCount = 0; rowCount < cfpList.size(); rowCount++) {
                        searchResultsDTO = (SearchResultsDTO) cfpList.get(rowCount);
                        printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("cfpExcelHeader").split(",")));
                     }
                    
                    printCsv(printableDto,printWriter);
                }
                 if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
                    SearchResultsDTO searchResultsDTO;
                    final List<SearchResultsDTO> nsfList = new NsfLogic().loadNsfResults(binder, start, end, columns, null);
                    List<String> printableDto= new ArrayList();
                    for (int rowCount = 0; rowCount < nsfList.size(); rowCount++) {
                        searchResultsDTO = (SearchResultsDTO) nsfList.get(rowCount);
                        printableDto.add(getStringRows(searchResultsDTO,excelProperties.getString("nsfExcelHeader").split(",")));
                     }
                    
                    printCsv(printableDto,printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * Framing a row to print in the CSV file
     * @param obj - Cell value from which object we are going to take
     * @param methodNameArray - list of Headers
     * @return - Row in a String format
     */
     public String getStringRows(SearchResultsDTO obj,String[] methodNameArray){
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
         try {
            int lastIndex=0;
            
            if(methodNameArray!=null && methodNameArray.length!=0){
                    lastIndex=methodNameArray.length-1;
             }
            
            for(int i =0; i<methodNameArray.length;i++){
                String methodName=methodNameArray[i];
                String methodStringValue;
                Object methodValue=obj.getClass().getMethod("get"+methodName,null).invoke(obj, null);
                
                if(methodValue!=null && ("TradeStartDate".equals(methodName)||"TradeEndDate".equals(methodName)
                        ||"CompanyFamilyPlanStartDate".equals(methodName)||"CompanyFamilyPlanEndDate".equals(methodName)||"CfpmodifiedDate".equals(methodName)
                        ||"CfpcreatedDate".equals(methodName)||"IfpcreatedDate".equals(methodName)||"PriceScheduleStartDate".equals(methodName)
                        ||"PriceScheduleEndDate".equals(methodName)||"CdrCreatedDate".equals(methodName)||"CdrModifiedDate".equals(methodName))){
                    methodStringValue = dateFormat.format(methodValue);
                } else if(methodValue!=null && StringUtils.isNotBlank(String.valueOf(methodValue)) && !ConstantUtil.SELECT_ONE.equals(String.valueOf(methodValue)) ) {
                    methodStringValue = String.valueOf(methodValue);
                } else{
                    methodStringValue = StringUtils.EMPTY;
                }
                
                if(lastIndex==i){
                    builder.append(QUOTE + methodStringValue + QUOTE);
                }else{
                    builder.append(QUOTE + methodStringValue + QUOTE + ExcelExportUtil.COMMA);
                }
            }
                                   
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
           LOGGER.error(ex);
        }
       return builder.toString();
    }
    
     
    /**
     * Prints each line in the CSV file
     * @param printableDto - List of rows we are going to print in the CSV file
     * @param printWriter - Writer object which writes in the CSV file
     */
    public void printCsv(List<String> printableDto,final PrintWriter printWriter){
        
        for(int i =0; i<printableDto.size();i++){
            printWriter.println(printableDto.get(i));           
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
    public int getCountBasedOnModules(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        int count;
        switch (moduleName) {
            case ConstantUtil.COMPANY_MAST:
                count = (Integer) loadCompanyMasterLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.ITEM_MASTER:
                count = (Integer) loadItemMasterLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.COMPANY_FAMILY_PLAN:
                count = (Integer) loadCompanyFamilyPlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.ITEM_FAMILY_PLAN:
                count = (Integer) loadItemFamilyPlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.PRICE_SCHEDULE_MASTER:
                count = (Integer) loadPriceScheduleLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.REBATE_SCHEDULE_MASTER:
                count = (Integer) loadRebateScheduleLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.REBATE_PLAN:
                count = (Integer) loadRebatePlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.COMPLIANCE_DEDUCTION_RULES:   
                count = (Integer) loadCDRLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                 break;
            case ConstantUtil.DEDUCTION_CALENDAR:   
                count = (Integer) loadDeductionCalendarLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);  
                break;
            case ConstantUtil.NET_SALES_FORMULA:   
                count = (Integer) loadNsfLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
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
    public List getSearchResultsBasedOnModules(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        List list;
        switch (moduleName) {
            case ConstantUtil.COMPANY_MAST:
                list = (List) loadCompanyMasterLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.ITEM_MASTER:
                list = (List) loadItemMasterLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.COMPANY_FAMILY_PLAN:
                list = (List) loadCompanyFamilyPlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.ITEM_FAMILY_PLAN:
                list = (List) loadItemFamilyPlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.PRICE_SCHEDULE_MASTER:
                list = (List) loadPriceScheduleLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.REBATE_SCHEDULE_MASTER:
                list = (List) loadRebateScheduleLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.REBATE_PLAN:
                list = (List) loadRebatePlanLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                break;
            case ConstantUtil.COMPLIANCE_DEDUCTION_RULES:   
                list = (List) loadCDRLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);    
                break;
            case ConstantUtil.DEDUCTION_CALENDAR:   
                list = (List) loadDeductionCalendarLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                 break;
            case ConstantUtil.NET_SALES_FORMULA:
                list = (List) loadNsfLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);    
                break;
            default:
                list = new ArrayList();
                break;
        }
        return list;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCountForSearch(...) of CompanySearchLogic to get the count
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
    private Object loadCompanyMasterLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        CompanySearchLogic companySearchLogic = new CompanySearchLogic();
        if (isCount) {
            object = companySearchLogic.getResultsForCompany(binder, start, start + offset, columns, filterSet,true);
        } else {
            object = companySearchLogic.getResultsForCompany(binder, start, start + offset, columns, filterSet,false);
            final HelperDTO helperDTO = (HelperDTO) (binder.getField("combo6").getValue());
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription()) && !ConstantUtil.SELECT_ONE.equals(helperDTO.getDescription())) {
                obj.setTableDefaultConfig1(ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
            } else {
                obj.setTableDefaultConfig1(ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "WO" : moduleName);
            }

        }
        return object;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCountForSearch(...) of ItemSearchLogic to get the count and
     * getResultsForSearch(...) to get the results.
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
    public Object loadItemMasterLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        ItemSearchLogic itemSearchLogic = new ItemSearchLogic();
        if (isCount) {
            object = (Integer) itemSearchLogic.getCountForSearch(binder, filterSet);
        } else {

            object = itemSearchLogic.getResultsForSearch(binder, start, start + offset, columns, filterSet);
            final HelperDTO helperDTO = (HelperDTO) (binder.getField(ConstantsUtils.COMBO5).getValue());
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription()) && !ConstantUtil.SELECT_ONE.equals(helperDTO.getDescription())) {
                obj.setTableDefaultConfig1(ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
            } else {
                obj.setTableDefaultConfig1(ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) ? moduleName + "WO" : moduleName);
            }

        }

        return object;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCFPResults(...) of CFPSearchLogic to get the count and
     * getResultsForCFP(...) to get the results.
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
    private Object loadCompanyFamilyPlanLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        CFPSearchLogic cfpSearchLogic = new CFPSearchLogic();
        if (isCount) {
            Object listSize = ((List) cfpSearchLogic.getCFPResults(binder, filterSet, null, false, 0, 0, "count")).get(0);
            object = Integer.valueOf(String.valueOf(listSize));
        } else {
            object = cfpSearchLogic.getResultsForCFP(binder, start, offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;

    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getIFPCountForSearch(...) of IFPLogic to get the count and
     * getResultsForIFP(...) to get the results.
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
    private Object loadItemFamilyPlanLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        IFPLogic ifpLogic = new IFPLogic();
        if (isCount) {
            object = (Integer) ifpLogic.getIFPCountForSearch(binder, filterSet);
        } else {
            object = ifpLogic.getResultsForIFP(binder, start, offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCountForPS(...) of PSLogic to get the count and
     * getResultsForPS(...) to get the results.
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
    private Object loadPriceScheduleLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        PSLogic psLogic = new PSLogic();
        if (isCount) {
            object = (Integer) psLogic.getCountForPS(binder, filterSet);

        } else {
            object = psLogic.getResultsForPS(binder, start, offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getCountAndResultsForRS(...) of RebateScheduleLogic to get the
     * results.
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
    private Object loadRebateScheduleLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {

        Object object;
        RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
        if (isCount) {
            object = (Integer) rebateScheduleLogic.getCountAndResultsForRS(binder, 0, 0, columns, false, filterSet, true);
        } else {
            List resultList = (List) rebateScheduleLogic.getCountAndResultsForRS(binder, start, offset, columns, false, filterSet, false);
            object = rebateScheduleLogic.getCustomizedSearchForHelper(resultList);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }

    /**
     * Method retrieves the total count based on search criteria and retrieves
     * the data based on the search criteria,total count,start index and offset.
     * Calls the getDynamicQueryForSearch(...) of RebatePlanSearchLogic to get
     * the count and getResultsForSearch(...) to get the results.
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
    private Object loadRebatePlanLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        RebatePlanSearchLogic rebatePlanSearchLogic = new RebatePlanSearchLogic();
        if (isCount) {
            object = rebatePlanSearchLogic.getRebatePlanResultsCount(binder, 0,0, columns, filterSet);

        } else {            
            object = rebatePlanSearchLogic.getRebatePlanResults(binder, start, start + offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }

    private Object loadCDRLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        CDRLogic cdrLogic = new CDRLogic();
        if (isCount) {
            object = (Integer) cdrLogic.getCDRCount(binder, filterSet);

        } else {
            object = cdrLogic.loadCDRResults(binder, start, start + offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }
    
    private Object loadDeductionCalendarLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        DeductionCalendarLogic deductionLogic = new DeductionCalendarLogic();
        if (isCount) {
            object = (Integer) deductionLogic.getCountAndResultsForDeductionCalendar(binder, 0,0,columns,filterSet,true);

        } else {
            object = deductionLogic.getCountAndResultsForDeductionCalendar(binder, start, start + offset, columns, filterSet,false);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }
    private Object loadNsfLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws Exception {
        Object object;
        NsfLogic nsfLogic = new NsfLogic();
        if (isCount) {
            object = (Integer) nsfLogic.getNsfCount(binder, filterSet);

        } else {
            object = nsfLogic.loadNsfResults(binder, start, start + offset, columns, filterSet);
            obj.setTableDefaultConfig1(moduleName);
        }

        return object;
    }
}
