/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

import com.stpl.app.arm.abstractforms.AbstractFilter;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;

import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class PipelineInventoryLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(PipelineInventoryLogic.class.getName());

    public int getCustomerProductGroupCount(CustomerGroupDTO groupDTO, Set<Container.Filter> filters) {
        List invInput = new ArrayList();
        Object obj = null;
        LOGGER.debug("Inside getCustomerProductGroupCount Method");
        String query;
        if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
            invInput.add(astToPerConverter(groupDTO.getCustomerGroupNo()));
        } else {
            invInput.add("%");
        }
        if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
            invInput.add(astToPerConverter(groupDTO.getCustomerGroupName()));
        } else {
            invInput.add("%");
        }
        invInput.add(AbstractFilter.getInstance().getFilterCustomerLookUp(filters).toString());
        query = "customerGroupSearchCount";

        List resultList = QueryUtils.getItemData(invInput, query, null);

        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        LOGGER.debug("Exit getCustomerProductGroupCount Method");
        return obj == null ? 0 : (Integer) obj;
    }

    public List<CustomerGroupDTO> getCustomerProductGroup(CustomerGroupDTO groupDTO, Set<Container.Filter> filters) {
        LOGGER.debug("Inside getCustomerProductGroup Method");
        List<CustomerGroupDTO> searchList = new ArrayList<>();
        try {
            List input = new ArrayList();
            String query;
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
            LOGGER.debug(query);
            List resultList = QueryUtils.getItemData(input, query, null);
            searchList = getCustomisedGroupDto(resultList);
        } catch (Exception e) {
            LOGGER.error("Error in getCustomerProductGroup :", e);
        }
        LOGGER.debug("Exit getCustomerProductGroup Method");
        return searchList;
    }

    private List<CustomerGroupDTO> getCustomisedGroupDto(List results) {
        LOGGER.debug("Inside getCustomisedGroupDto Method");
        List<CustomerGroupDTO> searchList = new ArrayList<>();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            Object[] arr = (Object[]) results.get(i);
            CustomerGroupDTO dTO = new CustomerGroupDTO();
            dTO.setCustomerGroupSid(convertNullToEmpty(arr[0]));
            dTO.setCustomerGroupNo(convertNullToEmpty(arr[1]));
            dTO.setCustomerGroupName(convertNullToEmpty(arr[NumericConstants.TWO]));
            dTO.setCustomerGroupDesc(convertNullToEmpty(arr[NumericConstants.THREE]));
            searchList.add(dTO);
        }
        LOGGER.debug("Exit getCustomisedGroupDto Method");
        return searchList;
    }

    public static String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || "null".equals(inputString) ? "%" : inputString.replace(ARMUtils.CHAR_ASTERISK, "%");
    }

    public static String convertNullToEmpty(Object value) {
        LOGGER.debug("Inside convertNullToEmpty Method");
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value)) || GlobalConstants.getSelectOne().equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        LOGGER.debug("Exit convertNullToEmpty Method{}", returnValue);
        return returnValue;
    }

}
