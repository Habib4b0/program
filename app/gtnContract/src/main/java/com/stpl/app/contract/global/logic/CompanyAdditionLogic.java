/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.global.logic;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import static com.stpl.app.contract.dashboard.ui.form.CompanyAdditionTab.LOGGER;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import static com.stpl.app.contract.global.logic.CFPSearchLogic.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.contract.global.logic.CFPSearchLogic.DEFAULT_SQL_DATE_FORMAT;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author nimisha.rakesh
 */
public class CompanyAdditionLogic {
    SessionDTO sessionDTO;
    public CompanyAdditionLogic(SessionDTO sessionDTO){
        this.sessionDTO=sessionDTO;
    }
    /**
     * Returns the List of Company Master DTO with the given search field and
     * Value.
     *
     * @param searchField - String contains Company Details
     * @param value - Value for wild card
     * @return List of CompanyMasterDTO.
     * @throws SystemException
     */
    
    public List<CompanyMasterDTO> getCompaniesForCFP(final String searchField, final String val,int start, int end, List<SortByColumn> columns,final Set<Container.Filter> searchCriteria) {
        LOGGER.debug("Entering CFPSearchLogic getCompaniesForCFP");
        final Map<String, String> map = new HashMap<>();
        map.put("Company ID", Constants.COMPANY_ID_CAPS);
        map.put("Company No", Constants.COMPANY_NO_CAPS);
        map.put("Company Name", Constants.COMPANY_NAME_CAPS);
        map.put("Company Type", Constants.COMP_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS_CAPS);

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;
        
        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            
            if (Constants.COMPANY_ID.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_ID_CAPS;
            } else if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NO_CAPS;
            } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NAME_CAPS;
            } else if (ConstantUtil.DISPLAY_COMPANY_STATUS.equals(orderByColumn.getName())) {
                column = ConstantUtil.CSTATUS;
            } else if (ConstantUtil.DISPLAY_COMPANY_TYPE.equals(orderByColumn.getName())) {
                column = ConstantUtil.CTYPE;
            } else if (Constants.TRADE_CLASS.equals(orderByColumn.getName())) {
                column = "COMPANY_TRADE_CLASS";
            } else if (ConstantUtil.COMPANY_CATEGORY.equals(orderByColumn.getName())) {
                column = "ccategory";
            } else if (ConstantUtil.COMPANY_GROUP.equals(orderByColumn.getName())) {
                column = "cgroup";
            }
            
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }
        String value=Constants.PERCENT;
        if (StringUtils.isNotBlank(val)) {
            value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        List<String> returnList = null;
     Map<String, Object> filterMap = new HashMap<>();
        filterMap.put(Constants.COMPANY_ID,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NO,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE,StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP,StringUtils.EMPTY);
     
       if (searchCriteria != null) {
              for (Container.Filter filter : searchCriteria) {
                 if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                    
                    if(stringFilter.getPropertyId().equals(Constants.COMPANY_NO)){
                            
                            filterMap.put(Constants.COMPANY_NO, filterText);
                            
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_ID)){
                            
                            filterMap.put(Constants.COMPANY_ID, filterText);
                            
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)){
                                        
                            filterMap.put(Constants.COMPANY_NAME,filterText );
                   
                    }else if(stringFilter.getPropertyId().equals(ConstantUtil.DISPLAY_COMPANY_TYPE)){
                    
                            filterMap.put(Constants.COMPANY_TYPE, stringFilter.getFilterString());
                        
                    }else if(stringFilter.getPropertyId().equals(ConstantUtil.DISPLAY_COMPANY_STATUS)){
                            
                            filterMap.put(Constants.COMPANY_STATUS, stringFilter.getFilterString());
                        
                    } else if(stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)){
                            filterMap.put(Constants.TRADE_CLASS, filterText);
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_CATEGORY)){
                            filterMap.put(ConstantUtil.COMPANY_CATEGORY, stringFilter.getFilterString());
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_GROUP)){
                            filterMap.put(ConstantUtil.COMPANY_GROUP, stringFilter.getFilterString());
                    }
                }
                   
                   
               }
          }
        
        List list = CfpContractDetailsLocalServiceUtil.getCompaniesList(map.get(searchField), value,filterMap, start, end, column, orderBy, returnList, null);
         CFPSearchLogic lgc=new CFPSearchLogic();
        final List<CompanyMasterDTO> companyMasterDTOList = lgc.getCustomizedCompanyData(list);
        LOGGER.debug("End of companyMasterList()");
        return companyMasterDTOList;
    }
   
    public int getCompanyAddtionCount(final String searchField, final String val, final  Set<Container.Filter> searchCriteria) {
        LOGGER.debug("Entering CFPSearchLogic getCompanyAddtionCount");
        final Map<String, String> map = new HashMap<>();
        map.put("Company ID", Constants.COMPANY_ID_CAPS);
        map.put("Company No", Constants.COMPANY_NO_CAPS);
        map.put("Company Name", Constants.COMPANY_NAME_CAPS);
        map.put("Company Type", Constants.COMP_TYPE);
        map.put("Company Status", Constants.COMPANY_STATUS_CAPS);
        Map<String, Object> filterMap = new HashMap<>();
        String value = Constants.PERCENT;
        if (StringUtils.isNotBlank(val)) {
            value = val.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);        
        }
        List<Integer> returnList = new ArrayList<>();
        filterMap.put(Constants.COMPANY_ID, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP,StringUtils.EMPTY);
        if (searchCriteria != null) {
            for (Container.Filter filter : searchCriteria) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;

                    if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {

                        filterMap.put(Constants.COMPANY_ID, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(ConstantUtil.DISPLAY_COMPANY_TYPE)) {

                        filterMap.put(Constants.COMPANY_TYPE, stringFilter.getFilterString());

                    } else if (stringFilter.getPropertyId().equals(ConstantUtil.DISPLAY_COMPANY_STATUS)) {
                        filterMap.put(Constants.COMPANY_STATUS, stringFilter.getFilterString());
                    } else if(stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)){
                            filterMap.put(Constants.TRADE_CLASS, filterText);
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_CATEGORY)){
                            filterMap.put(ConstantUtil.COMPANY_CATEGORY, stringFilter.getFilterString());
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_GROUP)){
                            filterMap.put(ConstantUtil.COMPANY_GROUP, stringFilter.getFilterString());
                    }
                }
            }
        }
        List list = CfpContractDetailsLocalServiceUtil.getCompaniesList(map.get(searchField), value, filterMap, 0, 0, null, null, returnList, null);
        final int count = list != null ? list.size() : 0;
        LOGGER.debug("End of getCompaniesForCFP()");
        return count;
    }
    public int getLazySelectedCompaniesDetailsCount(int start, int end, Boolean flag, final List<SortByColumn> list,final Set<Container.Filter> searchCriteria,boolean isCount) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;

        if (list != null) {
            for (final Iterator<SortByColumn> iterator = list.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NO_CAPS;
                } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                    column = Constants.COMPANY_NAME_CAPS;
                } else if (Constants.COMPANY_STATUS.equals(orderByColumn.getName())) {
                    column = ConstantUtil.CSTATUS;
                } else if (Constants.COMPANY_TYPE.equals(orderByColumn.getName())) {
                    column = ConstantUtil.CTYPE;
                }

                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = Constants.ASC;
                } else {
                    orderBy = "DESC";
                }
            }

        }

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put(Constants.COMPANY_NO, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE, StringUtils.EMPTY);
        filterMap.put(COMPANY_FAMILY_PLAN_STATUS, StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP,StringUtils.EMPTY);
        if (searchCriteria != null) {
            for (Container.Filter filter : searchCriteria) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                     if (stringFilter.getPropertyId().equals(Constants.COMPANY_ID)) {

                        filterMap.put(Constants.COMPANY_ID, filterText);

                    }else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NO)) {

                        filterMap.put(Constants.COMPANY_NO, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)) {

                        filterMap.put(Constants.COMPANY_NAME, filterText);

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)) {
                        filterMap.put(Constants.COMPANY_TYPE, stringFilter.getFilterString());

                    } else if (stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)) {
                        filterMap.put(Constants.COMPANY_STATUS, stringFilter.getFilterString());

                    } else if (stringFilter.getPropertyId().equals(COMPANY_FAMILY_PLAN_STATUS)) {
                        filterMap.put(COMPANY_FAMILY_PLAN_STATUS, filterText);

                    } else if(stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)){
                            filterMap.put(Constants.TRADE_CLASS, filterText);
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_CATEGORY)){
                            filterMap.put(ConstantUtil.COMPANY_CATEGORY, stringFilter.getFilterString());
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_GROUP)){
                            filterMap.put(ConstantUtil.COMPANY_GROUP, stringFilter.getFilterString());
                    }
                } else if (filter instanceof Between) {

                    Between betweenFilter = (Between) filter;
                    Date fromDate = (Date) betweenFilter.getStartValue();
                    Date toDate = (Date) betweenFilter.getEndValue();
                    if (betweenFilter.getPropertyId().equals("companyStartDate")) {
                        filterMap.put("companyStartDate-from", fromDate);
                        filterMap.put("companyStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyEndDate")) {

                        filterMap.put("companyEndDate-from", fromDate);
                        filterMap.put("companyEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanStartDate")) {

                        filterMap.put("companyFamilyPlanStartDate-from", fromDate);
                        filterMap.put("companyFamilyPlanStartDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("companyFamilyPlanEndDate")) {

                        filterMap.put("companyFamilyPlanEndDate-from", fromDate);
                        filterMap.put("companyFamilyPlanEndDate-to", toDate);

                    } else if (betweenFilter.getPropertyId().equals("attachedDate")) {

                        filterMap.put("attachedDate-from", fromDate);
                        filterMap.put("attachedDate-to", toDate);

                    }
                }

            }
        }
        
        
        final List<Object[]> returnList = ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(userId, sessionId, start, end, column, orderBy, flag, null,filterMap,isCount);
        Object obj= returnList.get(0);
        LOGGER.debug("selected count :" + returnList.size());
        return Integer.valueOf(String.valueOf(obj));
    }
    
    public List<CFPCompanyDTO> getLazySelectedCompaniesDeatils(int start, int end, Boolean flag, final List<SortByColumn> list,final Set<Container.Filter> searchCriteria,boolean isCount,String record) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        String column = Constants.COMPANY_NO_CAPS;
        String orderBy = Constants.ASC;

        if (list!=null) {
            for (final Iterator<SortByColumn> iterator = list.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            
            if (Constants.COMPANY_NO.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NO_CAPS;
            } else if (Constants.COMPANY_NAME.equals(orderByColumn.getName())) {
                column = Constants.COMPANY_NAME_CAPS;
            } else if (Constants.COMPANY_STATUS.equals(orderByColumn.getName())) {
                column = ConstantUtil.CSTATUS;
            } else if (Constants.COMPANY_TYPE.equals(orderByColumn.getName())) {
                column = ConstantUtil.CTYPE;
            } else if (Constants.TRADE_CLASS.equals(orderByColumn.getName())) {
                column = "ctrade";
            } else if (ConstantUtil.COMPANY_CATEGORY.equals(orderByColumn.getName())) {
                column = "ccategory";
            } else if (ConstantUtil.COMPANY_GROUP.equals(orderByColumn.getName())) {
                column = "cgroup";
            }
            
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }

     }

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put(Constants.COMPANY_NO,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_NAME,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_STATUS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_TYPE,StringUtils.EMPTY);
        filterMap.put(COMPANY_FAMILY_PLAN_STATUS,StringUtils.EMPTY);
        filterMap.put(Constants.TRADE_CLASS,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_CATEGORY,StringUtils.EMPTY);
        filterMap.put(Constants.COMPANY_GROUP,StringUtils.EMPTY);
       if (searchCriteria != null) {
              for (Container.Filter filter : searchCriteria) {
                 if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterText = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                    if(stringFilter.getPropertyId().equals(Constants.COMPANY_ID)){
                            
                            filterMap.put(Constants.COMPANY_ID, filterText);
                            
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_NO)){
                            
                            filterMap.put(Constants.COMPANY_NO, filterText);
                            
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_NAME)){
                                        
                            filterMap.put(Constants.COMPANY_NAME,filterText );
                   
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_TYPE)){
                            filterMap.put(Constants.COMPANY_TYPE, stringFilter.getFilterString());
                        
                    }else if(stringFilter.getPropertyId().equals(Constants.COMPANY_STATUS)){
                            filterMap.put(Constants.COMPANY_STATUS, stringFilter.getFilterString());
                        
                    }else if(stringFilter.getPropertyId().equals(COMPANY_FAMILY_PLAN_STATUS)){
                            filterMap.put(COMPANY_FAMILY_PLAN_STATUS, filterText);
                        
                    } else if(stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)){
                            filterMap.put(Constants.TRADE_CLASS, filterText);
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_CATEGORY)){
                            filterMap.put(ConstantUtil.COMPANY_CATEGORY, stringFilter.getFilterString());
                        
                    } else if(stringFilter.getPropertyId().equals(ConstantUtil.COMPANY_GROUP)){
                            filterMap.put(ConstantUtil.COMPANY_GROUP, stringFilter.getFilterString());
                    }
                }else if(filter instanceof Between){
                    
                    Between betweenFilter = (Between) filter;
                            Date fromDate = (Date)betweenFilter.getStartValue();
                            Date toDate = (Date)betweenFilter.getEndValue();
                    if(betweenFilter.getPropertyId().equals("companyStartDate")){
                       filterMap.put("companyStartDate-from", fromDate);
                       filterMap.put("companyStartDate-to", toDate);
                        
                    }else if(betweenFilter.getPropertyId().equals("companyEndDate")){
                            
                        filterMap.put("companyEndDate-from", fromDate);
                        filterMap.put("companyEndDate-to", toDate);
                        
                    }else if(betweenFilter.getPropertyId().equals("companyFamilyPlanStartDate")){
                            
                        filterMap.put("companyFamilyPlanStartDate-from", fromDate);
                        filterMap.put("companyFamilyPlanStartDate-to", toDate);
                        
                    }else if(betweenFilter.getPropertyId().equals("companyFamilyPlanEndDate")){
                            
                        filterMap.put("companyFamilyPlanEndDate-from", fromDate);
                        filterMap.put("companyFamilyPlanEndDate-to", toDate);
                        
                    }else if(betweenFilter.getPropertyId().equals("attachedDate")){
                            
                        filterMap.put("attachedDate-from", fromDate);
                        filterMap.put("attachedDate-to", toDate);
                        
                    }
                }
                   
                   
               }
          }
       if(!StringUtils.isBlank(record)){
            if(record.contains("Current")){
                    filterMap.put("Current", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains("History")){
                    filterMap.put("History", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains("Future")){
                    filterMap.put("Future", ContractUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        
        final List<Object[]> returnList = ImtdCfpDetailsLocalServiceUtil.getSelectedCompanies(userId, sessionId, start, end, column, orderBy, flag, null,filterMap,isCount);
        
      
        List<CFPCompanyDTO> companyList = new ArrayList<>();
        LOGGER.debug("selected results :" + returnList.size());
        return CFPSearchLogic.getCustomizedCompanyMasterDTO(returnList, companyList, flag, StringUtils.EMPTY);
    }
    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }
    public static final String COMPANY_FAMILY_PLAN_STATUS = "companyFamilyPlanStatus";
}
