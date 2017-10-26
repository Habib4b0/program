/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic;

import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.dto.CalculationProfileDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Vinodhini.Chandrasekar
 */
public class CalculationProfileLogic {

    public static final Logger LOGGER = Logger.getLogger(CalculationProfileLogic.class);

    public List<CalculationProfileDTO> loadCalculationTable() {
        String sqlQuery = SQlUtil.getQuery("getCProfileCalculationData");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        final List<CalculationProfileDTO> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final CalculationProfileDTO result = new CalculationProfileDTO();
            result.setAdjustmentType(obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY);
            result.setSystemId(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);

            results.add(result);

        }
        return results;

    }
    public List<CalculationProfileDTO> loadSelectedCalculation(String calculationProfileId) {
        String sqlQuery = SQlUtil.getQuery("retrievceCalculationProfileData");
        sqlQuery=sqlQuery.replace("@CALCULATION_PROFILE_ID", calculationProfileId);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        final List<CalculationProfileDTO> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final CalculationProfileDTO result = new CalculationProfileDTO();
            result.setAdjustmentType(obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY);
            result.setAccountType(obj[1] != null ? (Integer)obj[1] : 0);
            result.setInclude(((Integer)obj[2] != 0));
            if(result.isInclude()){
            result.setIndicator(obj[3] != null ? (Integer)obj[3]==0?-1:1 : 0);
            }
            result.setSystemId(obj[5] != null ? String.valueOf(obj[5]) : StringUtils.EMPTY);
            results.add(result);

        }
        return results;

    }

    public boolean checkAndSaveCalculationProfile(CalculationProfileDTO calculationProfileDTO, List<CalculationProfileDTO> cProfileList, boolean isView) {
        String detailsQuery =SQlUtil.getQuery("getCProfileCalculationDetailsInsert");
        StringBuilder sbQuery = new StringBuilder(StringUtils.EMPTY);
        int priorityCount = 1;
        boolean isNotIncluded = true;
        for (CalculationProfileDTO profileDTO : cProfileList) {
           
            if (profileDTO.isInclude()) {
                if (priorityCount != 1) {
                    sbQuery.append(" , ");
                }

                sbQuery.append("( @id, ");
                sbQuery.append(profileDTO.getSystemId()).append(", ");
                
                if (profileDTO.getAccountType() == 0) {
                    return true;
                } else {
                    sbQuery.append(profileDTO.getAccountType()).append(", "); // helper id
                }
                sbQuery.append("1, ");
                if (profileDTO.getIndicator() == 0) {
                    return true;
                } else {
                    sbQuery.append(profileDTO.getIndicator() == -1 ? "0" : "1").append(", ");// -1 for "-" and 1 for "+"
                }
                sbQuery.append(priorityCount++).append(") ");
                isNotIncluded = false;
            }
        }
        detailsQuery=detailsQuery.replace("@DETAILS_INPUT", sbQuery);
        calculationProfileDTO.setUpdateViewQuery(detailsQuery);
        
        if (isNotIncluded) {
            return isNotIncluded;
        }

        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

        String insertQuery = SQlUtil.getQuery("saveCProfileCalculationMaster");
        insertQuery = insertQuery.replace("@PROFILE_NAME", calculationProfileDTO.getProfileName());
        insertQuery = insertQuery.replace("@PROFILE_DESCRIPTION", calculationProfileDTO.getProfileDesc());
        insertQuery = insertQuery.replace("@SAVE_FLAG", isView ? "0" : "1"); 
        insertQuery = insertQuery.replace("@CREATED_BY", userId);
        insertQuery = insertQuery.replace("@MODIFIED_BY", userId);

        insertQuery += detailsQuery;

        calculationProfileDTO.setQuery(insertQuery);
        return false;
    }

    public void insert(String insertQuery) {
        HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
    }

    /**
     * Logic for Deleting the existing save view.
     *
     * @param projectionId
     * @return true - if deleted : false if fail
     */
    public boolean deleteViewLogic(int projectionId) {
        String query = SQlUtil.getQuery("deleteCalculationProfileView");
        query = query.replace("@PROJECTION_MASTER_SID", "" + projectionId);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }
    
    public String getViewQuery(String insertQuery, CalculationProfileDTO calculationProfileDTO) {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String viewQuery = SQlUtil.getQuery("saveCProfileCalculationView");
        viewQuery = viewQuery.replace("@VIEW_NAME", calculationProfileDTO.getViewName());
        viewQuery = viewQuery.replace("@VIEW_TYPE", calculationProfileDTO.getViewType());
        viewQuery = viewQuery.replace("@CREATED_BY", userId);
        viewQuery = viewQuery.replace("@MODIFIED_BY", userId);
        insertQuery += viewQuery;
        return insertQuery;
    }
    
    public int searhProfileCount(final String profileName, final String profileDesc, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns)
            throws SystemException, PortalException {
        LOGGER.debug("Entering searhProfileCount method");
        List list = null;
        String profileValue = StringUtils.EMPTY;
        String descValue = StringUtils.EMPTY;
        if (profileName.isEmpty()) {
            profileValue = ARMUtils.CHAR_PERCENT;
        }
        if (StringUtils.isNotBlank(profileName)) {
            profileValue = profileName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(profileDesc)) {
            descValue = profileDesc.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        list = findCalculationProfile(profileValue, descValue, isCount, filters, sortByColumns, 0, 0);
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0) + "");
        }
        LOGGER.debug("End of searhProfileCount method");
        return count;
    }

    public List searhProfile(final String profileName, final String profileDesc, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset)
            throws SystemException, PortalException {
        LOGGER.debug("Entering searhProfile method");
        List list;
       
        String profileValue = StringUtils.EMPTY;
        String descValue = StringUtils.EMPTY;
        if (profileName.isEmpty()) {
            profileValue = ARMUtils.CHAR_PERCENT;
        }
        if (StringUtils.isNotBlank(profileName)) {
            profileValue = profileName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(profileDesc)) {
            descValue = profileDesc.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        list = findCalculationProfile(profileValue, descValue, isCount, filters, sortByColumns, start, offset);
        LOGGER.debug("End of searhProfile method");
        return list;
    }

    public List<CalculationProfileDTO> getCustomizedProfiles(final List list) throws ParseException, PortalException, SystemException {
        final List<CalculationProfileDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCustomizedProfiles method with list size  " + list.size());
       
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final CalculationProfileDTO result = new CalculationProfileDTO();
            result.setCalculationProfileId(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
            result.setProfileName(CommonLogic.convertNullToEmpty(String.valueOf(obj[1])));
            result.setProfileDesc(CommonLogic.convertNullToEmpty(String.valueOf(obj[2])));
           
            results.add(result);

        }
        LOGGER.debug("End of getCustomizedProfiles method");
        return results;
    }
    
    
     public static List findCalculationProfile(String profileName, String profileDesc, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) {
        String customSql = StringUtils.EMPTY;
     
        LOGGER.debug("Entering findCalculationProfile method with profileName " + profileName + " profileDesc " + profileDesc);
      
            try {

                if (isCount) {
                    customSql = SQlUtil.getQuery("searchCalculationProfileCount");
                } else {
                    customSql = SQlUtil.getQuery("searchCalculationProfileData");
                }

                if (StringUtils.isNotEmpty(profileName)
                        && StringUtils.isNotBlank(profileName)) {
                    customSql += " AND PROFILE_NAME LIKE '" + profileName + "' ";
                }
                if (StringUtils.isNotEmpty(profileDesc)
                        && StringUtils.isNotBlank(profileDesc)) {
                    customSql += " AND PROFILE_DESCRIPTION LIKE '" + profileDesc + "' ";
                }
               

                Map<String, String> profileFilterMap = ARMUtils.loadProfileFilterMap();
                
                String filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, profileFilterMap).toString().replace("where", " AND");

                customSql += filterQuery;
                if (isCount) {
                    List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                    return list;
                }
                
                String orderBy = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, profileFilterMap, "CALCULATION_PROFILE_MASTER_SID").toString();
                customSql += orderBy;
                if (!isCount) {
                    customSql += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
                }
                List<Object[]> sqlList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                LOGGER.debug("End of findCalculationProfile method");
                return sqlList;
            } catch (Exception e) {
                LOGGER.error("Error :" + e + " While Executing " + customSql);
                return null;
            } 
        
    }
    public boolean isDuplicateView(String viewName) {
        try {
            String sqlQuery = SQlUtil.getQuery("calculationProfileDuplicateViewCheck");
            sqlQuery = sqlQuery.replace("$$$VN$$$", viewName);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            return !list.isEmpty();
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return true;
        }
    }
    public int isDuplicateProfile(String viewName) {
        try {
            String sqlQuery = SQlUtil.getQuery("calculationProfileDuplicateCheck");
            sqlQuery = sqlQuery.replace("$$$VN$$$", viewName);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            return list.isEmpty() ? 0 : (int) list.get(0);
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return 0;
        }
    }
public String getViewUpdateQuery(String insertQuery, CalculationProfileDTO calculationProfileDTO) {
       String updateQuery = SQlUtil.getQuery("deleteCalculationDetailsQuery");
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String viewQuery = SQlUtil.getQuery("updateCalculationViewQuery");
        viewQuery = viewQuery.replace("@VIEW_NAME", calculationProfileDTO.getViewName());
        viewQuery = viewQuery.replace("@VIEW_TYPE", calculationProfileDTO.getViewType());
        viewQuery = viewQuery.replace("@CREATED_BY", userId);
        viewQuery = viewQuery.replace("@MODIFIED_BY", userId);
        
        insertQuery += viewQuery;
        updateQuery += insertQuery;
        updateQuery = updateQuery.replace("@id", calculationProfileDTO.getCalculationProfileId());
       
        return updateQuery;
    }

    public String submitAndSelectCalcProfileMasterId(String insertQuery) {
       
        insertQuery += " SELECT @id ";

        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(insertQuery);
        if (rawList!=null && !rawList.isEmpty()) {
            return String.valueOf(rawList.get(0));
        }
        return "0";
    }
    
    
    public String getUpdateQuery(String insertQuery, CalculationProfileDTO calculationProfileDTO) {
        String updateQuery = SQlUtil.getQuery("deleteCalculationDetailsQuery");
        updateQuery += insertQuery;
        updateQuery = updateQuery.replace("@id", calculationProfileDTO.getCalculationProfileId());
        return updateQuery;
    }

    public Object[] getProfileNameAndDesc(String masterSid) {
        String query = SQlUtil.getQuery("getProfileNameandDesc");
        query = query.replace("?", masterSid);
        return ((List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query)).get(0);
    }

    public boolean isResultsTableIdentical(List<CalculationProfileDTO> calculationList, String masterSid) {
        String sqlQuery = SQlUtil.getQuery("getSummaryTableIdenticalRows");
        String parameterQuery = StringUtils.EMPTY;
        for (CalculationProfileDTO dto : calculationList) {
            if (dto.isInclude()) {
                parameterQuery = parameterQuery + "('" + dto.getAdjustmentType()
                        + "'," + dto.getAccountType() + "," + (dto.isInclude() ? "1" : "0") + ","
                        + (dto.getIndicator() == -1 ? 0 : 1) + "," + dto.getSystemId() + "),";
            }
        }
        parameterQuery = parameterQuery.substring(0, parameterQuery.length() - 1);
        sqlQuery = sqlQuery.replace("@PARAMETERS", parameterQuery);
        sqlQuery = sqlQuery.replace("$CALCULATION_PROFILE_MASTER_SID", masterSid);
        List<Object> count = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return (int) count.get(0) > 0;
    }
}