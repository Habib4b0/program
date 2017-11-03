/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.abstractsearch.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.util.ConstantsUtils.QUOTE;
import static com.stpl.app.util.ConstantsUtils.TAB;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
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
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible() && !ConstantUtil.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
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
    public void excelExportLogic(String moduleName, ExtFilterTable resultTable, AbstractSearchForm obj,ErrorfulFieldGroup binder) throws PortalException, ParseException, NoSuchMethodException, SystemException, IllegalAccessException,  InvocationTargetException {
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
    private void createWorkSheet(String moduleName,ExtFilterTable resultTable, AbstractSearchForm obj, ErrorfulFieldGroup binder) throws SystemException, PortalException, ParseException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {

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
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
        final List<SortByColumn> columns = new ArrayList<>();

        try {
            if (end != 0) {
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
                        ||"CompanyFamilyPlanStartDate".equals(methodName)||ConstantsUtils.CFP_END_DATE.equals(methodName)||"CfpmodifiedDate".equals(methodName)
                        ||"CfpcreatedDate".equals(methodName)||"IfpcreatedDate".equals(methodName)||"PriceScheduleStartDate".equals(methodName)
                        ||"PriceScheduleEndDate".equals(methodName)||"CdrCreatedDate".equals(methodName)||"CdrModifiedDate".equals(methodName))){
                    methodStringValue = dateFormat.format(methodValue);
                } else if(methodValue!=null && StringUtils.isNotBlank(String.valueOf(methodValue)) && !ConstantUtil.SELECT_ONE.equals(String.valueOf(methodValue)) ) {
                    methodStringValue = String.valueOf(methodValue);
                } else{
                    methodStringValue = StringUtils.EMPTY;
                }
                
                if(lastIndex==i){
                    builder.append(QUOTE + TAB + methodStringValue + QUOTE);
                }else{
                    builder.append(QUOTE + TAB + methodStringValue + QUOTE + ExcelExportUtil.COMMA);
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
    public int getCountBasedOnModules(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws SystemException, ParseException, PortalException {
        int count;
        switch (moduleName) {
            case ConstantUtil.COMPLIANCE_DEDUCTION_RULES:   
                count = (Integer) loadCDRLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                 break;
            case ConstantUtil.DEDUCTION_CALENDAR:   
                count = (Integer) loadDeductionCalendarLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);  
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
    public List getSearchResultsBasedOnModules(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws PortalException, SystemException, ParseException {
        List list;
        switch (moduleName) {
            case ConstantUtil.COMPLIANCE_DEDUCTION_RULES:   
                list = (List) loadCDRLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);    
                break;
            case ConstantUtil.DEDUCTION_CALENDAR:   
                list = (List) loadDeductionCalendarLogic(binder, obj, start, offset, isCount, columns, filterSet, moduleName);
                 break;            
            default:
                list = new ArrayList();
                break;
        }
        return list;
    }

    private Object loadCDRLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) {
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
    
    private Object loadDeductionCalendarLogic(ErrorfulFieldGroup binder, AbstractSearchForm obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String moduleName) throws SystemException  {
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
 }
