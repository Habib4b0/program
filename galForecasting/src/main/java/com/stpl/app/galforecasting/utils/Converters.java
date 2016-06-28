/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.utils.Constants.CommonConstants;
import com.stpl.app.utils.Constants.DateFormatConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Converters.
 *
 * @author lokeshwari
 */
public class Converters {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Converters.class);

    /**
     * Gets the customized views.
     *
     * @param list the list
     * @return the customized views
     * @throws java.text.ParseException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static List<ViewDTO> getCustomizedViews(final List list, boolean flagValue) throws ParseException, PortalException, SystemException {
        final List<ViewDTO> results = new ArrayList<ViewDTO>();
        LOGGER.info("Entering getCustomizedViews method with list size  " + list.size());
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setViewName(convertNullToEmpty(String.valueOf(obj[0])));
            result.setDescription(convertNullToEmpty(String.valueOf(obj[1])));
            result.setProjectionName(convertNullToEmpty(String.valueOf(obj[2])));
            if (Constant.TRUE.equalsIgnoreCase(String.valueOf(obj[3]))) {
                result.setBrandType("Contracted");
            } else {
                result.setBrandType("All Brands");
            }
            result.setModifiedDate(formatDate(convertNullToEmpty((String.valueOf(obj[4])))));
            result.setModifiedDateSearch(parseDate((String.valueOf(obj[4]))));
            result.setCreatedUserid(String.valueOf(obj[5]));
            result.setCreatedBy(getUserFLName(convertNullToEmpty(String.valueOf(obj[5]))));
            result.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(obj[6]))));
            result.setCreatedDateSearch(parseDate(String.valueOf(obj[6])));
            result.setProductLevel(convertNullToEmpty(String.valueOf(obj[7])));
            result.setCustomerLevel(convertNullToEmpty(String.valueOf(obj[8])));
            result.setCustomerHierarchy(convertNullToEmpty(String.valueOf(obj[9])));
            result.setProductHierarchy(convertNullToEmpty(String.valueOf(obj[10])));
            result.setCompany(convertNullToEmpty(String.valueOf(obj[11])));
            result.setCustomerGroup(convertNullToEmpty(String.valueOf(obj[12])));
            result.setProductGroup(convertNullToEmpty(String.valueOf(obj[13])));
            result.setProjectionId(convertNullToEmpty(String.valueOf(obj[14])));
            result.setViewId(convertNullToEmpty(String.valueOf(obj[15])));
            result.setCustomerHierarchySid(convertNullToEmpty(String.valueOf(obj[16])));
            result.setProductHierarchySid(convertNullToEmpty(String.valueOf(obj[17])));
            result.setCompanyGroupSid(convertNullToEmpty(String.valueOf(obj[18])));
            result.setProductGroupSid(convertNullToEmpty(String.valueOf(obj[19])));
            result.setCompanyMasterSid(convertNullToEmpty(String.valueOf(obj[20])));
            result.setFrom(formatDate(convertNullToEmpty(String.valueOf(obj[21]))));
            result.setTo(formatDate(convertNullToEmpty(String.valueOf(obj[22]))));
            result.setFromDate(parseDate(convertNullToEmpty(String.valueOf(obj[21]))));
            result.setToDate(parseDate(convertNullToEmpty(String.valueOf(obj[22]))));
            result.setFromPeriod(DataSelectionUtil.getTimePeriod(parseDate(convertNullToEmpty(String.valueOf(obj[21])))));
            result.setToPeriod(DataSelectionUtil.getTimePeriod(parseDate(convertNullToEmpty(String.valueOf(obj[22])))));
            result.setCustomerInnerLevel(convertNullToEmpty(String.valueOf(obj[23])));
            result.setProductInnerLevel(convertNullToEmpty(String.valueOf(obj[24])));
            result.setCustRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[25])));
            result.setProdRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[26])));
            if (flagValue) {
                result.setDeductionLevel(convertNullToEmpty(String.valueOf(obj[27])));
                result.setDeductionValue(obj[29]!=null && !StringUtils.isBlank(String.valueOf(obj[29])) && !"-Select One-".equals(String.valueOf(obj[29])) ? convertNullToEmpty(String.valueOf(obj[29])) : StringUtils.EMPTY);
                if(obj[28]!=null && !StringUtils.isBlank(String.valueOf(obj[28])) && StringUtils.isNumeric(String.valueOf(obj[28]))){
                    result.setDeductionValueId(Integer.valueOf(String.valueOf(obj[28])));
                }    
            }

            results.add(result);
        }
        LOGGER.info("End of getCustomizedViews method");
        return results;
    }

    /**
     * Converts list of CompanyGroup to list of GroupDTO
     *
     * @param resultList list of CompanyGroup
     * @return list of GroupDTO
     */
    public static List<GroupDTO> convertCustomerGroupList(final List resultList) {
        List<GroupDTO> returnList = new ArrayList<GroupDTO>();
        GroupDTO groupDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            groupDTO = new GroupDTO();
            Object[] objects = (Object[]) resultList.get(loop);
            groupDTO.setCustomerGroupSid(String.valueOf(objects[0]));
            groupDTO.setCustomerGroupNo(String.valueOf(objects[1]));
            groupDTO.setCustomerGroupName(String.valueOf(objects[2]));
            groupDTO.setCustomerGroupVersionNo(String.valueOf(objects[3]));
            groupDTO.setCustomergroupDescription(String.valueOf(objects[4]));
            returnList.add(groupDTO);
        }
        return returnList;
    }

    /**
     * Converts list of ItemGroup to list of GroupDTO
     *
     * @param resultList list of ItemGroup
     * @return list of GroupDTO
     */
    public static List<GroupDTO> convertItemGroupList(final List resultList) {
        List<GroupDTO> returnList = new ArrayList<GroupDTO>();
        GroupDTO groupDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            groupDTO = new GroupDTO();
            Object[] objects = (Object[]) resultList.get(loop);
            groupDTO.setProductGroupSid(String.valueOf(objects[0]));
            groupDTO.setProductGroupNo(String.valueOf(objects[1]));
            groupDTO.setProductGroupName(String.valueOf(objects[2]));
            groupDTO.setCompany(String.valueOf(objects[3]));
            groupDTO.setProductGroupVersionNo(String.valueOf(objects[4]));
            groupDTO.setProductgroupDescription(String.valueOf(objects[5]));
            returnList.add(groupDTO);
        }
        return returnList;
    }

    /**
     * Converts list of CompanyGroup to list of GroupDTO
     *
     * @param resultList list of CompanyGroup
     * @return list of GroupDTO
     */
    public static List<GroupDTO> convertAllCustomerGroupList(final List<CompanyGroup> resultList) {
        List<GroupDTO> returnList = new ArrayList<GroupDTO>();
        GroupDTO groupDTO;
        for (CompanyGroup companyGroup : resultList) {
            groupDTO = new GroupDTO();
            groupDTO.setCustomerGroupName(companyGroup.getCompanyGroupName());
            groupDTO.setCustomerGroupNo(companyGroup.getCompanyGroupNo());
            groupDTO.setCustomerGroupSid(String.valueOf(companyGroup.getCompanyGroupSid()));
            groupDTO.setCustomerGroupVersionNo(String.valueOf(companyGroup.getVersionNo()));
            returnList.add(groupDTO);
        }
        return returnList;
    }
    
    public static List<DataSelectionDTO> searchDsProjection(List resultList, boolean channelsFlag) throws ParseException, PortalException, SystemException {
        
        List<DataSelectionDTO> dataSelectionDTOs = new ArrayList<DataSelectionDTO>();
        DataSelectionDTO dataSelectionDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            dataSelectionDTO = new DataSelectionDTO();
            Object objects[] = (Object[]) resultList.get(loop);
            dataSelectionDTO.setProjectionId(Integer.parseInt(String.valueOf(objects[0])));
            dataSelectionDTO.setProjectionName(String.valueOf(objects[1]));
            dataSelectionDTO.setDescription(String.valueOf(objects[2]));
            dataSelectionDTO.setCustomerHierSid(String.valueOf(objects[3]));
            dataSelectionDTO.setCustomerHierarchy(String.valueOf(objects[4]));
            dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(objects[5]));
            dataSelectionDTO.setProdHierSid(String.valueOf(objects[6]));
            dataSelectionDTO.setProductHierarchy(String.valueOf(objects[7]));
            dataSelectionDTO.setProductHierarchyLevel(String.valueOf(objects[8]));

            dataSelectionDTO.setCreatedBy(DataSelectionUtil.getUserName(convertNullToEmpty(String.valueOf(objects[9]))));
            dataSelectionDTO.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(objects[10]))));
            dataSelectionDTO.setLastModifiedDate(formatDate(convertNullToEmpty(String.valueOf(objects[11]))));
            dataSelectionDTO.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[10]))));
            dataSelectionDTO.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[11]))));
            dataSelectionDTO.setCompanySid(String.valueOf(objects[12]));
            dataSelectionDTO.setCustomerGrpSid(String.valueOf(objects[13]));
            dataSelectionDTO.setCustomerGroup(String.valueOf(objects[14]));
            dataSelectionDTO.setProdGrpSid(String.valueOf(objects[15]));
            dataSelectionDTO.setProductGroup(String.valueOf(objects[16]));
            dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(objects[17]));
            dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(objects[18]));
            dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(objects[19]));
            dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(objects[20]));
            if (channelsFlag) {
                dataSelectionDTO.setDiscountSid(Integer.parseInt(objects[21] == null ? DASH : objects[21].toString()));
                dataSelectionDTO.setDiscount(DataSelectionUtil.getDiscountName(convertNullToEmpty(String.valueOf(objects[21]))));
                dataSelectionDTO.setDeductionLevel(convertNullToEmpty(String.valueOf(objects[22])));
                dataSelectionDTO.setDeductionValue(convertNullToEmpty(String.valueOf(objects[23])));
            }
         
            dataSelectionDTOs.add(dataSelectionDTO);
        }
        
        return dataSelectionDTOs;
    }
    
    public static DataSelectionDTO getProjection(List resultList) throws ParseException, PortalException, SystemException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
        Object objects[] = (Object[]) resultList.get(0);
        dataSelectionDTO.setProjectionId(Integer.parseInt(String.valueOf(objects[0])));
        dataSelectionDTO.setProjectionName(String.valueOf(objects[1]));
        dataSelectionDTO.setDescription(String.valueOf(objects[2]));
        dataSelectionDTO.setCustomerHierSid(String.valueOf(objects[3]));
        dataSelectionDTO.setCustomerHierarchy(String.valueOf(objects[4]));
        dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(objects[5]));
        dataSelectionDTO.setProdHierSid(String.valueOf(objects[6]));
        dataSelectionDTO.setProductHierarchy(String.valueOf(objects[7]));
        dataSelectionDTO.setProductHierarchyLevel(String.valueOf(objects[8]));
        dataSelectionDTO.setCreatedBy(String.valueOf(objects[9]));
        dataSelectionDTO.setCreatedDate(String.valueOf(objects[10]));
        dataSelectionDTO.setLastModifiedDate(!CommonConstants.NULL.getConstant().equals(String.valueOf(objects[11])) ? String.valueOf(objects[11]) : StringUtils.EMPTY);
        dataSelectionDTO.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[10]))));
        dataSelectionDTO.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[11]))));
        dataSelectionDTO.setCompanySid(String.valueOf(objects[12]));
        dataSelectionDTO.setCustomerGrpSid(String.valueOf(objects[13]));
        dataSelectionDTO.setCustomerGroup(String.valueOf(objects[14]).equals(CommonConstants.NULL.getConstant()) ? StringUtils.EMPTY : String.valueOf(objects[14]));
        dataSelectionDTO.setProdGrpSid(String.valueOf(objects[15]));
        dataSelectionDTO.setProductGroup(String.valueOf(objects[16]).equals(CommonConstants.NULL.getConstant()) ? StringUtils.EMPTY : String.valueOf(objects[16]));
        dataSelectionDTO.setCustomerHierVersionNo(Integer.parseInt(String.valueOf(objects[17])));
        dataSelectionDTO.setProductHierVersionNo(Integer.parseInt(String.valueOf(objects[18])));
        dataSelectionDTO.setBrand(Boolean.valueOf(String.valueOf(objects[19])));
        dataSelectionDTO.setSelectedCompanyName(String.valueOf(objects[20]));
        try {
            dataSelectionDTO.setFromPeriod((String.valueOf(objects[21])));
            dataSelectionDTO.setToPeriod((String.valueOf(objects[24])));
            
            dataSelectionDTO.setFromDate(parseDate(convertNullToEmpty((String.valueOf(objects[21])))));
            dataSelectionDTO.setToDate(parseDate(convertNullToEmpty(String.valueOf(objects[24]))));
            
            dataSelectionDTO.setFromPeriod((String.valueOf(objects[21])));
            dataSelectionDTO.setToPeriod((String.valueOf(objects[24])));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        
        dataSelectionDTO.setModifiedBy(String.valueOf(objects[22]));
        dataSelectionDTO.setSaveFlag(CommonConstants.NULL.getConstant().equals(String.valueOf(objects[23])) ? false : Boolean.valueOf(String.valueOf(objects[23])));
        
        dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(objects[25]));
        dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(objects[26]));
        dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(objects[27]));
        dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(objects[28]));
        dataSelectionDTO.setDiscountSid(Integer.parseInt(String.valueOf(objects[29] == null ? 0 : objects[29])));
        dataSelectionDTO.setDiscount(String.valueOf(objects[30]));
        if (Constant.TRUE.equalsIgnoreCase(String.valueOf(objects[19]))) {
            dataSelectionDTO.setBrand(true);
            dataSelectionDTO.setBrandType("Contracted");
        } else {
            dataSelectionDTO.setBrand(false);
            dataSelectionDTO.setBrandType("All Brands");
        }
        return dataSelectionDTO;
    }
    
    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || CommonConstants.NULL.getConstant().equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
    
    private static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.yyyyMMddhhmmssSSS.getConstant());
        SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMddyyyyhhmmss.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }
    
    private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.yyyyMMddhhmmssSSS.getConstant());

        SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMddyyyyhhmmss.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
        }
        return date;
    }
    
    public static String getUserFLName(String userId) throws PortalException, SystemException {
        String name = StringUtils.EMPTY;
        User userInfo = CommonUtils.getUser(userId);
        if (userInfo != null) {
            name = userInfo.getLastName() + ", " + userInfo.getFirstName();
        }
        return name;
    }
    
    public static Date parseDate(String value) throws ParseException {
        Date date = null;
        value = convertNullToEmpty(value);
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.yyyyMMddhhmmssSSS.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            date = parse.parse(value);
        }
        return date;
    }
    
}
