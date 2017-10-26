/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.app.accountclose.dto.FixedDollarGroupDTO;
import com.stpl.app.accountclose.dto.GroupDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class Converters {

    /**
     * Converts list of CompanyGroup to list of GroupDTO
     *
     * @param resultList list of CompanyGroup
     * @return list of GroupDTO
     */
    public static List<GroupDTO> convertCustomerGroupList(final List resultList) {
        List<GroupDTO> returnList = new ArrayList<GroupDTO>();
        GroupDTO groupDTO;
        if (resultList != null && resultList.size() != 0) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                groupDTO = new GroupDTO();
                Object[] objects = (Object[]) resultList.get(loop);
                groupDTO.setCustomerGroupSid(String.valueOf(objects[0]));
                groupDTO.setCustomerGroupNo(String.valueOf(objects[1]));
                groupDTO.setCustomerGroupName(String.valueOf(objects[2]));
                groupDTO.setCustomerGroupVersionNo(String.valueOf(objects[3]));
                returnList.add(groupDTO);
            }
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
        if (resultList != null && resultList.size() != 0) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                groupDTO = new GroupDTO();
                Object[] objects = (Object[]) resultList.get(loop);
                groupDTO.setProductGroupSid(String.valueOf(objects[0]));
                groupDTO.setProductGroupNo(String.valueOf(objects[1]));
                groupDTO.setProductGroupName(String.valueOf(objects[2]));
                groupDTO.setCompany(String.valueOf(objects[3]));
                groupDTO.setProductGroupVersionNo(String.valueOf(objects[4]));
                returnList.add(groupDTO);
            }
        }
        return returnList;
    }
    
     /**
     * Converts list of CompanyGroup to list of GroupDTO
     *
     * @param resultList list of CompanyGroup
     * @return list of GroupDTO
     */
    public static List<FixedDollarGroupDTO> convertFDCustomerGroupList(final List resultList) {
        List<FixedDollarGroupDTO> returnList = new ArrayList<FixedDollarGroupDTO>();
        FixedDollarGroupDTO groupDTO;
        if (resultList != null && resultList.size() != 0) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                groupDTO = new FixedDollarGroupDTO();
                Object[] objects = (Object[]) resultList.get(loop);
                groupDTO.setCustomerGroupSid(String.valueOf(objects[0]));
                groupDTO.setCustomerGroupNo(String.valueOf(objects[1]));
                groupDTO.setCustomerGroupName(String.valueOf(objects[2]));
                groupDTO.setCustomerGroupVersionNo(String.valueOf(objects[3]));
                returnList.add(groupDTO);
            }
        }
        return returnList;
    }
    
    /**
     * Converts list of ItemGroup to list of GroupDTO
     *
     * @param resultList list of ItemGroup
     * @return list of GroupDTO
     */
    public static List<FixedDollarGroupDTO> convertFDItemGroupList(final List resultList) {
        List<FixedDollarGroupDTO> returnList = new ArrayList<FixedDollarGroupDTO>();
        FixedDollarGroupDTO groupDTO;
        if (resultList != null && resultList.size() != 0) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                groupDTO = new FixedDollarGroupDTO();
                Object[] objects = (Object[]) resultList.get(loop);
                groupDTO.setProductGroupSid(String.valueOf(objects[0]));
                groupDTO.setProductGroupNo(String.valueOf(objects[1]));
                groupDTO.setProductGroupName(String.valueOf(objects[2]));
                groupDTO.setCompany(String.valueOf(objects[3]));
                groupDTO.setProductGroupVersionNo(String.valueOf(objects[4]));
                returnList.add(groupDTO);
            }
        }
        return returnList;
    }
    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || Constants.NULL.equals(String.valueOf(value)) || Constants.SELECT_ONE.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
}
