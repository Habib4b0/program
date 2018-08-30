/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.DataSelectionQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class ProductGroupLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductGroupLogic.class);

    /**
     * This method is used to get product groups search
     *
     * @param productGroupNo
     * @param productGroupName
     * @return productGroupList
     */
    public List<ProductGroupLookUpDTO> getProductGroups(
            String productGroupNo, String productGroupName, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        DataSelectionQueryUtils dsUtils = new DataSelectionQueryUtils();
        List<ProductGroupLookUpDTO> resultList;
        List obtainedResultList;
        String productGroupsNo = StringUtils.EMPTY;
        String productGroupsName = StringUtils.EMPTY;
        if (productGroupNo != null && !StringUtils.EMPTY.equals(productGroupNo)) {
            productGroupsNo = productGroupNo.replace(
                    CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (productGroupName != null && !StringUtils.EMPTY.equals(productGroupName)) {
            productGroupsName = productGroupName.replace(
                    CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        obtainedResultList = dsUtils.getProductGroups(productGroupsNo, productGroupsName, startIndex, offset, filters, sortByColumns);
        resultList = convertToProductGroupDTO(obtainedResultList);

        return resultList;
    }

    public int getProductGroupsCount(
            String productGroupNo, String productGroupName, Set<Container.Filter> filters) {

        int count = 0;
        String productGroupsNoCount = StringUtils.EMPTY;
        String productGroupsNameCount = StringUtils.EMPTY;
        try {
            if (productGroupNo != null && !StringUtils.EMPTY.equals(productGroupNo)) {
                productGroupsNoCount = productGroupNo.replace(
                        CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            if (productGroupName != null && !StringUtils.EMPTY.equals(productGroupName)) {
                productGroupsNameCount = productGroupName.replace(
                        CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            count = DataSelectionQueryUtils.loadProductGroupsCount(productGroupsNoCount, productGroupsNameCount, filters);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;

    }

    /**
     * Converts the list of Object[] obtained from the database to list of
     * ProductGroupLookUpDTO.
     *
     * @param obtainedList
     * @return
     */
    public List<ProductGroupLookUpDTO> convertToProductGroupDTO(List obtainedList) {
        List<ProductGroupLookUpDTO> resultList = new ArrayList<>();
        for (Object record : obtainedList) {
            Object[] obtainedObject = (Object[]) record;
            ProductGroupLookUpDTO productGroupDTO = new ProductGroupLookUpDTO();
            productGroupDTO.setProductGroup((obtainedObject[0] == null) ? StringUtils.EMPTY : String.valueOf(obtainedObject[0]));
            productGroupDTO.setProductGroupName((obtainedObject[1] == null) ? StringUtils.EMPTY : String.valueOf(obtainedObject[1]));
            productGroupDTO.setItemGroupSid((obtainedObject[NumericConstants.TWO] == null) ? 0 : Integer.parseInt(obtainedObject[NumericConstants.TWO].toString()));
            productGroupDTO.setCompany((obtainedObject[NumericConstants.THREE] == null) ? StringUtils.EMPTY : String.valueOf(obtainedObject[NumericConstants.THREE].toString()));
            productGroupDTO.setCompanySid((obtainedObject[NumericConstants.FOUR] == null) ? 0 : Integer.parseInt(obtainedObject[NumericConstants.FOUR].toString()));
            productGroupDTO.setProductGroupDescription((obtainedObject[NumericConstants.FIVE] == null) ? StringUtils.EMPTY : String.valueOf(obtainedObject[NumericConstants.FIVE]));
            productGroupDTO.setCompanyNo((obtainedObject[NumericConstants.SIX] == null) ? StringUtils.EMPTY : String.valueOf(obtainedObject[NumericConstants.SIX]));
            resultList.add(productGroupDTO);
        }
        return resultList;
    }

}
