/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.dao.CustomerGroupLogicDAO;
import com.stpl.app.adminconsole.dao.impl.CustomerGroupLogicDAOImpl;
import com.stpl.app.adminconsole.itemgroup.logic.ItemGroupLogic;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * The Class CommonUtils.
 *
 * @author vishalakshi
 */
public final class CommonUtils {

    public static final char CHAR_PERCENT = '%';

    public static final char CHAR_ASTERISK = '*';

    public static final String MMDDYYYY = "MM/dd/yyyy";

    public static final String EMPTY = ConstantsUtils.EMPTY;

    public static final String MANUFACTURER = "Manufacturer";

    public static final String COMP_TYPE = "GLCOMP";

    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

    private static CustomerGroupLogicDAO dao = new CustomerGroupLogicDAOImpl();

    private static ItemGroupLogic logic = new ItemGroupLogic();

    private CommonUtils() {

    }

    /**
     * Gets the company with ids.
     *
     * @param start the start
     * @param end the end
     * @param marketType the market type
     * @param tempFilterText
     * @param filterText the filter text
     * @return the company with ids
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public static List<HelperDTO> getCompanyWithIds(final int start, final int end, final String marketType, final String tempFilterText) throws SystemException, PortalException, Exception {
        LOGGER.info("getCompanyWithIds started with P1:int start=" + start + " P2:int end=" + end + " P3:String marketType=" + marketType + " P4:String filterText" + tempFilterText);
        final String filterText = StringUtils.trimToEmpty(tempFilterText) + "%";
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq("description", "GLCOMP"));
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
        List<HelperTable> helperList = HelperTableLocalServiceUtil.dynamicQuery(helperDynamicQuery);
        if (helperList != null) {
            int id = helperList.get(0).getHelperTableSid();
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, id));
        }
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_SYS_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        companyDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_NO, filterText));
        companyDynamicQuery.setLimit(start, end);
        List<Object[]> resultList;
        final List<HelperDTO> results = new ArrayList<HelperDTO>();

        resultList = dao.getCompanyMasterList(companyDynamicQuery);
        HelperDTO dto;

        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
        LOGGER.info("getCompanyWithIds return List<HelperDTO> results");
        return results;
    }

    /**
     * Gets the company info.
     *
     * @return the company info
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public static Map getCompanyInfo() throws SystemException, PortalException, Exception {
        LOGGER.info("getCompanyInfo started");
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_SYS_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_NO));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_NAME));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_ID));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList;
        final Map results = new HashMap();
        resultList = dao.getCompanyMasterList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]) + "~" + String.valueOf(obj[2]) + "`" + String.valueOf(obj[3]));
        }
        LOGGER.info("getCompanyInfo return HashMap results");
        return results;
    }

    /*
     * @param aDate - The Date object to be converted to string
     * 
     * @return String - date in string format in the format of "MM/dd/yyyy"
     */
    /**
     * Convert date to string.
     *
     * @param aDate the a date
     * @return the string
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public static String convertDateToString(final Date aDate) throws SystemException, PortalException, Exception {
        return getDateTime(MMDDYYYY, aDate);
    }

    /*
     * @param aMask - Date format input
     * 
     * @param aDate - The Date object to be converted to string
     * 
     * @return String - date in string format
     */
    /**
     * Gets the date time.
     *
     * @param aMask the a mask
     * @param aDate the a date
     * @return the date time
     */
    public static String getDateTime(final String aMask, final Date aDate) {

        String returnValue = EMPTY;
        try {
            if (aDate == null) {
                returnValue = EMPTY;
            } else {
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat(aMask);
                returnValue = sdf.format(aDate);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
        }

        return returnValue;
    }

    /**
     * Gets the lazy customer names count.
     *
     * @param marketType the market type
     * @param tempFilterText
     * @return the lazy customer names count
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public static int getLazyCustomerNamesCount(final String marketType, final String tempFilterText) throws SystemException, PortalException, Exception {
        LOGGER.info("getLazyCustomerNamesCount started with P1:String marketType=" + marketType + " P2:String filterText=" + tempFilterText);
        List<String> resultList;

        final String filterText = StringUtils.trimToEmpty(tempFilterText) + "%";
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq("description", "GLCOMP"));
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
        List<HelperTable> helperList = HelperTableLocalServiceUtil.dynamicQuery(helperDynamicQuery);
        if (helperList != null) {
            int id = helperList.get(0).getHelperTableSid();
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, id));
        }
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_NAME)));
        companyDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.COMPANY_NO, filterText));
        resultList = logic.getCompanyMasterList(companyDynamicQuery);

        LOGGER.info("getLazyCustomerNamesCount method returns list size()" + resultList.size());
        return resultList.size();
    }

    /**
     * Gets the company info.
     *
     * @return the company info
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public static Map getCompanyInformation() throws SystemException, PortalException, Exception {
        LOGGER.info("getCompanyInfo started");
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_SYS_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList;
        final Map results = new HashMap();
        resultList = dao.getCompanyMasterList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);

            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.info("getCompanyInfo return HashMap results");
        return results;
    }

}
