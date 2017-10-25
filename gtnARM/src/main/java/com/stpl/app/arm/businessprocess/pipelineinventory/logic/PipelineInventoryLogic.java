/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

import com.stpl.app.arm.AbstractForms.AbstractFilter;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class PipelineInventoryLogic {
    
    
   public int getCustomerProductGroupCount(CustomerGroupDTO groupDTO, Set<Container.Filter> filters) throws PortalException {
       List input = new ArrayList();
        String query = StringUtils.EMPTY;
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
                input.add(astToPerConverter(groupDTO.getCustomerGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
                input.add(astToPerConverter(groupDTO.getCustomerGroupName()));
            } else {
                input.add("%");
            }
            input.add(AbstractFilter.getInstance().getFilterCustomerLookUp(filters).toString());
            query = "customerGroupSearchCount";
        
        List resultList = (List<Object[]>) QueryUtils.getItemData(input, query,null);
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    
    
   public List<CustomerGroupDTO> getCustomerProductGroup(CustomerGroupDTO groupDTO, Set<Container.Filter> filters) throws PortalException {
       List<CustomerGroupDTO> searchList = new ArrayList<CustomerGroupDTO>();
        List input = new ArrayList();
        String query = StringUtils.EMPTY;
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
                input.add(astToPerConverter(groupDTO.getCustomerGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
                input.add(astToPerConverter(groupDTO.getCustomerGroupName()));
            } else {
                input.add("%");
            }
            input.add(AbstractFilter.getInstance().getFilterCustomerLookUp(filters).toString());
            query = "customerGroupSearch";
            input.add(" ORDER BY CG.company_Group_Name OFFSET " + groupDTO.getStartIndex() + "  ROWS FETCH NEXT " + groupDTO.getEndIndex() + " ROWS ONLY");
        
        List resultList = (List<Object[]>) QueryUtils.getItemData(input, query,null);
        searchList = getCustomisedGroupDto(resultList);
        return searchList;
    }
    
    private List<CustomerGroupDTO> getCustomisedGroupDto(List results) {
        List<CustomerGroupDTO> searchList = new ArrayList<CustomerGroupDTO>();
        int size = results.size();
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                CustomerGroupDTO dTO = new CustomerGroupDTO();
                dTO.setCustomerGroupSid(convertNullToEmpty(arr[0]));
                dTO.setCustomerGroupNo(convertNullToEmpty(arr[1]));
                dTO.setCustomerGroupName(convertNullToEmpty(arr[NumericConstants.TWO]));
                dTO.setCustomerGroupDesc(convertNullToEmpty(arr[NumericConstants.THREE]));
                searchList.add(dTO);
            }
        return searchList;
    }
    
    public static String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || "null".equals(inputString) ? "%" : inputString.replace("*", "%");
    }
    
    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value)) || ConstantsUtils.SELECT_ONE.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
    
}
