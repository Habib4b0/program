/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.dto;

import com.stpl.app.customtreecontainer.CustomTreeDTO;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class PPAProjectionResultsDTO extends CustomTreeDTO implements Comparator<PPAProjectionResultsDTO> {

    private String group = StringUtils.EMPTY;

    private Integer levelNo = 0;

    private String levelName = StringUtils.EMPTY;
    private Integer treeLevelNo;
    private static final Logger LOGGER = Logger.getLogger(PPAProjectionResultsDTO.class);

    /**
     * for pivot
     */
    private String discountPerUnitActuals = StringUtils.EMPTY;
    private String discountPerUnitProjections = StringUtils.EMPTY;
    private String discountPercentActuals = StringUtils.EMPTY;
    private String discountPercentProjections = StringUtils.EMPTY;
    private String unitVolumeActuals = StringUtils.EMPTY;
    private String unitVolumeProjections = StringUtils.EMPTY;
    private String totalDiscountActuals = StringUtils.EMPTY;
    private String totalDiscountProjections = StringUtils.EMPTY;

    private Boolean isTotalColumn = Boolean.FALSE;
    private String hirarechyNo = StringUtils.EMPTY;
    private String hirarechyIndicater = StringUtils.EMPTY;
    private String productHierarchyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public String getHirarechyIndicater() {
        return hirarechyIndicater;
    }

    public void setHirarechyIndicater(String hirarechyIndicater) {
        this.hirarechyIndicater = hirarechyIndicater;
    }

    public String getHirarechyNo() {
        return hirarechyNo;
    }

    public void setHirarechyNo(String hirarechyNo) {
        this.hirarechyNo = hirarechyNo;
    }

    public Boolean getIsTotalColumn() {
        return isTotalColumn;
    }

    public void setIsTotalColumn(Boolean isTotalColumn) {
        this.isTotalColumn = isTotalColumn;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDiscountPerUnitActuals() {
        return discountPerUnitActuals;
    }

    public void setDiscountPerUnitActuals(String discountPerUnitActuals) {
        this.discountPerUnitActuals = discountPerUnitActuals;
    }

    public String getDiscountPerUnitProjections() {
        return discountPerUnitProjections;
    }

    public void setDiscountPerUnitProjections(String discountPerUnitProjections) {
        this.discountPerUnitProjections = discountPerUnitProjections;
    }

    public String getDiscountPercentActuals() {
        return discountPercentActuals;
    }

    public void setDiscountPercentActuals(String discountPercentActuals) {
        this.discountPercentActuals = discountPercentActuals;
    }

    public String getDiscountPercentProjections() {
        return discountPercentProjections;
    }

    public void setDiscountPercentProjections(String discountPercentProjection) {
        this.discountPercentProjections = discountPercentProjection;
    }

    public String getUnitVolumeActuals() {
        return unitVolumeActuals;
    }

    public void setUnitVolumeActuals(String unitVolumeActuals) {
        this.unitVolumeActuals = unitVolumeActuals;
    }

    public String getUnitVolumeProjections() {
        return unitVolumeProjections;
    }

    public void setUnitVolumeProjections(String unitVolumeProjection) {
        this.unitVolumeProjections = unitVolumeProjection;
    }

    public String getTotalDiscountActuals() {
        return totalDiscountActuals;
    }

    public void setTotalDiscountActuals(String totalDiscountActuals) {
        this.totalDiscountActuals = totalDiscountActuals;
    }

    public String getTotalDiscountProjections() {
        return totalDiscountProjections;
    }

    public void setTotalDiscountProjections(String totalDiscountProjection) {
        this.totalDiscountProjections = totalDiscountProjection;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int compare(PPAProjectionResultsDTO obj1, PPAProjectionResultsDTO obj2) {

        int result = 0;
        try {
            if (obj1 != null && obj2 != null && obj1.getGroup() != null && obj2.getGroup() != null) {

                if (obj1.getGroup().length() != 4 && Character.isDigit(obj1.getGroup().charAt(1)) && Character.isDigit(obj2.getGroup().charAt(1))) {
                    String str1 = obj1.getGroup().substring(2);
                    String str2 = obj2.getGroup().substring(2);
                    result = str1.compareTo(str2);
                } else if (obj1.getGroup().length() == 4) {
                    Integer year1 = Integer.valueOf(obj1.getGroup());
                    Integer year2 = Integer.valueOf(obj2.getGroup());
                    result = year1.compareTo(year2);
                } else {

                    Integer year1 = Integer.valueOf(obj1.getGroup().substring(4, 8));
                    Integer year2 = Integer.valueOf(obj2.getGroup().substring(4, 8));
                  
                    result=year1.compareTo(year2);

                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

}
