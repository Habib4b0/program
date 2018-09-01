/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.cff.util.Constants.CommonConstants;
import com.stpl.app.cff.util.Constants.DateFormatConstants;
import com.stpl.app.model.CompanyGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class Converters {
   /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Converters.class);

    /**
     * Gets the customized views.
     *
     * @param list the list
     * @return the customized views
     * @throws java.text.ParseException
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static List<ViewDTO> getCustomizedViews(final List list) throws ParseException, SystemException {
        final List<ViewDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCustomizedViews method with list size= {}",list.size());
        try{
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setViewName(convertNullToEmpty(String.valueOf(obj[0])));
            result.setDescription(convertNullToEmpty(String.valueOf(obj[1])));
            result.setProjectionName(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
            result.setBrandType("Contracted");
            result.setModifiedDate(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR]))));
            result.setModifiedDateSearch(parseDate(String.valueOf(obj[NumericConstants.FOUR])));
            result.setCreatedUserid(String.valueOf(obj[NumericConstants.FIVE]));
            result.setCreatedBy(getUserFLName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE]))));
            result.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.SIX]))));
            result.setCreatedDateSearch(parseDate(String.valueOf(obj[NumericConstants.SIX])));
            result.setProductLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVEN])));
            result.setCustomerLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT])));
            result.setCustomerHierarchy(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE])));
            result.setProductHierarchy(convertNullToEmpty(String.valueOf(obj[NumericConstants.TEN])));
            result.setCompany(convertNullToEmpty(String.valueOf(obj[NumericConstants.ELEVEN])));
            result.setCustomerGroup(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWELVE])));
            result.setProductGroup(convertNullToEmpty(String.valueOf(obj[NumericConstants.THIRTEEN])));
            result.setProjectionId(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOURTEEN])));
            result.setViewId(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIFTEEN])));
            result.setCustomerHierarchySid(convertNullToEmpty(String.valueOf(obj[NumericConstants.SIXTEEN])));
            result.setProductHierarchySid(convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVENTEEN])));
            result.setCompanyGroupSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHTEEN])));
            result.setProductGroupSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINETEEN])));
            result.setCompanyMasterSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY])));
            result.setFrom(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_ONE]))));
            result.setTo(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_TWO]))));
            result.setFromDate(parseDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_ONE]))));
            result.setToDate(parseDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_TWO]))));
            result.setFromPeriod(DataSelectionUtil.getTimePeriod(parseDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_ONE])))));
            result.setToPeriod(DataSelectionUtil.getTimePeriod(parseDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_TWO])))));
            result.setCustomerInnerLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_THREE])));
            result.setProductInnerLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_FOUR])));
            result.setCustRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_FIVE])));
            result.setProdRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_SIX])));
            if (String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) != null && !String.valueOf(obj[NumericConstants.TWENTY_SEVEN]).isEmpty() && !"null".equals(String.valueOf(obj[NumericConstants.TWENTY_SEVEN]))) {
                result.setBusinessUnitSystemId(Integer.valueOf(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])));
            }
            result.setBusinessUnitSystemName(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_EIGHT])));
            result.setCustHierarchyVersion(obj[NumericConstants.TWENTY_NINE] == null ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_NINE])));
            result.setProdHierarchyVersion(obj[NumericConstants.THIRTY] == null ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.THIRTY])));
            results.add(result);
        }
        }catch(PortalException | SystemException | NumberFormatException | ParseException e){
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("End of getCustomizedViews method");
        return results;
    }

    /**
     * Converts list of CompanyGroup to list of GroupDTO
     *
     * @param resultList list of CompanyGroup
     * @return list of GroupDTO
     */
    public static List<GroupDTO> convertCustomerGroupList(final List resultList) {
        List<GroupDTO> returnList = new ArrayList<>();
        GroupDTO groupDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            groupDTO = new GroupDTO();
            Object[] objects = (Object[]) resultList.get(loop);
            groupDTO.setCustomerGroupSid(String.valueOf(objects[0]));
            groupDTO.setCustomerGroupNo(String.valueOf(objects[1]));
            groupDTO.setCustomerGroupName(String.valueOf(objects[NumericConstants.TWO]));
            groupDTO.setCustomerGroupVersionNo(String.valueOf(objects[NumericConstants.THREE]));
            groupDTO.setCustomergroupDescription(String.valueOf(objects[NumericConstants.FOUR]));
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
        List<GroupDTO> returnList = new ArrayList<>();
        GroupDTO groupDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            groupDTO = new GroupDTO();
            Object[] objects = (Object[]) resultList.get(loop);
            groupDTO.setProductGroupSid(String.valueOf(objects[0]));
            groupDTO.setProductGroupNo(String.valueOf(objects[1]));
            groupDTO.setProductGroupName(String.valueOf(objects[NumericConstants.TWO]));
            groupDTO.setCompany(String.valueOf(objects[NumericConstants.THREE]));
            groupDTO.setProductGroupVersionNo(String.valueOf(objects[NumericConstants.FOUR]));
            groupDTO.setProductgroupDescription(String.valueOf(objects[NumericConstants.FIVE]));
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
        List<GroupDTO> returnList = new ArrayList<>();
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
    
    public static List<DataSelectionDTO> searchDsProjection(List resultList, boolean channelsFlag) throws ParseException {
        
        List<DataSelectionDTO> dataSelectionDTOs = new ArrayList<>();
        DataSelectionDTO dataSelectionDTO;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            dataSelectionDTO = new DataSelectionDTO();
            Object[] objects = (Object[]) resultList.get(loop);
            dataSelectionDTO.setProjectionId(Integer.parseInt(String.valueOf(objects[0])));
            dataSelectionDTO.setProjectionName(String.valueOf(objects[1]));
            dataSelectionDTO.setDescription(String.valueOf(objects[NumericConstants.TWO]));
            dataSelectionDTO.setCustomerHierSid(String.valueOf(objects[NumericConstants.THREE]));
            dataSelectionDTO.setCustomerHierarchy(String.valueOf(objects[NumericConstants.FOUR]));
            dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(objects[NumericConstants.FIVE]));
            dataSelectionDTO.setProdHierSid(String.valueOf(objects[NumericConstants.SIX]));
            dataSelectionDTO.setProductHierarchy(String.valueOf(objects[NumericConstants.SEVEN]));
            dataSelectionDTO.setProductHierarchyLevel(String.valueOf(objects[NumericConstants.EIGHT]));
            dataSelectionDTO.setCreatedBy(DataSelectionUtil.getUserName(convertNullToEmpty(String.valueOf(objects[NumericConstants.NINE]))));
            dataSelectionDTO.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TEN]))));
            dataSelectionDTO.setLastModifiedDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.ELEVEN]))));
            dataSelectionDTO.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TEN]))));
            dataSelectionDTO.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.ELEVEN]))));
            dataSelectionDTO.setCompanySid(String.valueOf(objects[NumericConstants.TWELVE]));
            dataSelectionDTO.setCustomerGrpSid(String.valueOf(objects[NumericConstants.THIRTEEN]));
            dataSelectionDTO.setCustomerGroup(String.valueOf(objects[NumericConstants.FOURTEEN]));
            dataSelectionDTO.setProdGrpSid(String.valueOf(objects[NumericConstants.FIFTEEN]));
            dataSelectionDTO.setProductGroup(String.valueOf(objects[NumericConstants.SIXTEEN]));
            dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(objects[NumericConstants.SEVENTEEN]));
            dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(objects[NumericConstants.EIGHTEEN]));
            dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(objects[NumericConstants.NINETEEN]));
            dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(objects[NumericConstants.TWENTY]));
            if (channelsFlag) {
                dataSelectionDTO.setDiscountSid(Integer.parseInt(objects[NumericConstants.TWENTY_ONE] == null ? "0" : objects[NumericConstants.TWENTY_ONE].toString()));
                dataSelectionDTO.setDiscount(DataSelectionUtil.getDiscountName(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_ONE]))));
            }
            dataSelectionDTOs.add(dataSelectionDTO);
        }
        
        return dataSelectionDTOs;
    }
    
    public static DataSelectionDTO getProjection(List resultList) throws ParseException {
        DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
        Object[] objects = (Object[]) resultList.get(0);
        dataSelectionDTO.setProjectionId(Integer.parseInt(String.valueOf(objects[0])));
        dataSelectionDTO.setProjectionName(String.valueOf(objects[1]));
        dataSelectionDTO.setDescription(String.valueOf(objects[NumericConstants.TWO]));
        dataSelectionDTO.setCustomerHierSid(String.valueOf(objects[NumericConstants.THREE]));
        dataSelectionDTO.setCustomerHierarchy(String.valueOf(objects[NumericConstants.FOUR]));
        dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(objects[NumericConstants.FIVE]));
        dataSelectionDTO.setProdHierSid(String.valueOf(objects[NumericConstants.SIX]));
        dataSelectionDTO.setProductHierarchy(String.valueOf(objects[NumericConstants.SEVEN]));
        dataSelectionDTO.setProductHierarchyLevel(String.valueOf(objects[NumericConstants.EIGHT]));
        dataSelectionDTO.setCreatedBy(String.valueOf(objects[NumericConstants.NINE]));
        dataSelectionDTO.setCreatedDate(String.valueOf(objects[NumericConstants.TEN]));
        dataSelectionDTO.setLastModifiedDate(!CommonConstants.NULL.getConstant().equals(String.valueOf(objects[NumericConstants.ELEVEN])) ? String.valueOf(objects[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
        dataSelectionDTO.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TEN]))));
        dataSelectionDTO.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.ELEVEN]))));
        dataSelectionDTO.setCompanySid(String.valueOf(objects[NumericConstants.TWELVE]));
        dataSelectionDTO.setCustomerGrpSid(String.valueOf(objects[NumericConstants.THIRTEEN]));
        dataSelectionDTO.setCustomerGroup(String.valueOf(objects[NumericConstants.FOURTEEN]).equals(CommonConstants.NULL.getConstant()) ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FOURTEEN]));
        dataSelectionDTO.setProdGrpSid(String.valueOf(objects[NumericConstants.FIFTEEN]));
        dataSelectionDTO.setProductGroup(String.valueOf(objects[NumericConstants.SIXTEEN]).equals(CommonConstants.NULL.getConstant()) ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SIXTEEN]));
        dataSelectionDTO.setCustomerHierVersionNo(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVENTEEN])));
        dataSelectionDTO.setProductHierVersionNo(Integer.parseInt(String.valueOf(objects[NumericConstants.EIGHTEEN])));
        dataSelectionDTO.setBrand(Boolean.parseBoolean(String.valueOf(objects[NumericConstants.NINETEEN])));
        dataSelectionDTO.setSelectedCompanyName(String.valueOf(objects[NumericConstants.TWENTY]));
        try {
            dataSelectionDTO.setFromPeriod(String.valueOf(objects[NumericConstants.TWENTY_ONE]));
            dataSelectionDTO.setToPeriod(String.valueOf(objects[NumericConstants.TWENTY_FOUR]));
            
            dataSelectionDTO.setFromDate(parseDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_ONE]))));
            dataSelectionDTO.setToDate(parseDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_FOUR]))));
            
            dataSelectionDTO.setFromPeriod(String.valueOf(objects[NumericConstants.TWENTY_ONE]));
            dataSelectionDTO.setToPeriod(String.valueOf(objects[NumericConstants.TWENTY_FOUR]));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        
        dataSelectionDTO.setModifiedBy(String.valueOf(objects[NumericConstants.TWENTY_TWO]));
        dataSelectionDTO.setSaveFlag(CommonConstants.NULL.getConstant().equals(String.valueOf(objects[NumericConstants.TWENTY_THREE])) ? false : Boolean.parseBoolean(String.valueOf(objects[NumericConstants.TWENTY_THREE])));
        
        dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(objects[NumericConstants.TWENTY_FIVE]));
        dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(objects[NumericConstants.TWENTY_SIX]));
        dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(objects[NumericConstants.TWENTY_SEVEN]));
        dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(objects[NumericConstants.TWENTY_EIGHT]));
        dataSelectionDTO.setDiscountSid(Integer.parseInt(String.valueOf(objects[NumericConstants.TWENTY_NINE] == null ? 0 : objects[NumericConstants.TWENTY_NINE])));
        dataSelectionDTO.setDiscount(String.valueOf(objects[NumericConstants.THIRTY]));
        if ("true".equalsIgnoreCase(String.valueOf(objects[NumericConstants.NINETEEN]))) {
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
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.YYYYMMDDHHMMSSSSS.getConstant());
        SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMDDYYYYHHMMSS.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }
    
    private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.YYYYMMDDHHMMSSSSS.getConstant());
        SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMDDYYYYHHMMSS.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }
        return date;
    }
    
    public static String getUserFLName(String userId) throws PortalException{
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
        SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.YYYYMMDDHHMMSSSSS.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
            date = parse.parse(value);
        }
        return date;
    } 
}