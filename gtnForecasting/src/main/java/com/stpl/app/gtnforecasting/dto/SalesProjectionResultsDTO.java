/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesProjectionResultsDTO.
 *
 * @author lokeshwari
 */
public class SalesProjectionResultsDTO extends ExtMapDTO implements Comparator<SalesProjectionResultsDTO> {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 8127118762859743943L;
    private String level = StringUtils.EMPTY;
    private String group = StringUtils.EMPTY;
    private String customer = StringUtils.EMPTY;
    private String contract = StringUtils.EMPTY;
    private String product = StringUtils.EMPTY;
    private Integer levelNo = new Integer(Constant.DASH);
    private String levelValue = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String history = StringUtils.EMPTY;
    private String salesOrUnits = StringUtils.EMPTY;
    private String actuasOrProj = StringUtils.EMPTY;
    private String projectionPeriodOrder = StringUtils.EMPTY;
    private String pivotView = StringUtils.EMPTY;
    private String screenName = StringUtils.EMPTY;
    private String hierarchyNo = StringUtils.EMPTY;
    private Integer parent=0;
    private String parentNode = StringUtils.EMPTY;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private Integer projectionTotal = 0;
    private Integer treeLevelNo = 0;
    private Integer onExpandTotalRow=0;
    private String productHierarchyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;
    private String relationshipLevelName = StringUtils.EMPTY;
    private Integer customLevelNo =0;

    public Integer getCustomLevelNo() {
        return customLevelNo;
    }

    public void setCustomLevelNo(Integer customLevelNo) {
        this.customLevelNo = customLevelNo;
    }

    public String getLevel() {
        return level;
    }

    public String getRelationshipLevelName() {
        return relationshipLevelName;
    }

    public void setRelationshipLevelName(String relationshipLevelName) {
        this.relationshipLevelName = relationshipLevelName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSalesOrUnits() {
        return salesOrUnits;
    }

    public void setSalesOrUnits(String salesOrUnits) {
        this.salesOrUnits = salesOrUnits;
    }

    public String getActuasOrProj() {
        return actuasOrProj;
    }

    public void setActuasOrProj(String actuasOrProj) {
        this.actuasOrProj = actuasOrProj;
    }

    public String getProjectionPeriodOrder() {
        return projectionPeriodOrder;
    }

    public void setProjectionPeriodOrder(String projectionPeriodOrder) {
        this.projectionPeriodOrder = projectionPeriodOrder;
    }

    public String getPivotView() {
        return pivotView;
    }

    public void setPivotView(String pivotView) {
        this.pivotView = pivotView;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public Integer getProjectionTotal() {
        return projectionTotal;
    }

    public void setProjectionTotal(Integer projectionTotal) {
        this.projectionTotal = projectionTotal;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

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

    @Override
    public int compare(SalesProjectionResultsDTO obj1, SalesProjectionResultsDTO obj2) {
        int value=0;
        try {
            if (obj1 != null && obj2 != null && obj1.getLevelValue() != null && obj2.getLevelValue() != null) {

                 if (obj1.getLevelValue().length() == NumericConstants.FOUR) {
                    Integer year1 = Integer.valueOf(obj1.getLevelValue());
                    Integer year2 = Integer.valueOf(obj2.getLevelValue());
                    value = year1.compareTo(year2);
                } else if (obj1.getLevelValue().length() != NumericConstants.FOUR && Character.isDigit(obj1.getLevelValue().charAt(1)) && Character.isDigit(obj2.getLevelValue().charAt(1))) {
                    String str1 = obj1.getLevelValue().substring(NumericConstants.TWO);
                    String str2 = obj2.getLevelValue().substring(NumericConstants.TWO);
                    value = str1.compareTo(str2);
                }else {
                    Integer year1 = Integer.valueOf(obj1.getLevelValue().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    Integer year2 = Integer.valueOf(obj2.getLevelValue().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    value=year1.compareTo(year2);

                }
            }
        } catch (Exception e) {
            LOGGER.error(e);

        }
        
        return value;
    }
    
}
