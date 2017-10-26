/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vinodhini
 */
public class NetSalesRuleLookupDto implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9084529327026132883L;
    private String ruleSystemId =StringUtils.EMPTY;
    private HelperDTO ruleType;
    private String ruleNo = StringUtils.EMPTY;
    private String ruleName = StringUtils.EMPTY;
    private HelperDTO ruleCategory;
    private String lineType = StringUtils.EMPTY;
    private String association = StringUtils.EMPTY;
    private String keyword = StringUtils.EMPTY;
    private String operator = StringUtils.EMPTY;
    private String value = StringUtils.EMPTY;
    private String comparison = StringUtils.EMPTY;
    private String logicalOperator = StringUtils.EMPTY;
    private String ruleCategoryString= StringUtils.EMPTY;

    public String getRuleSystemId() {
        return ruleSystemId;
    }

    public void setRuleSystemId(String ruleSystemId) {
        this.ruleSystemId = ruleSystemId;
    }

    public HelperDTO getRuleType() {
        return ruleType;
    }

    public void setRuleType(HelperDTO ruleType) {
        this.ruleType = ruleType;
    }
  
    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

   
    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public HelperDTO getRuleCategory() {
        return ruleCategory;
    }

    public void setRuleCategory(HelperDTO ruleCategory) {
        this.ruleCategory = ruleCategory;
    }

    public String getRuleCategoryString() {
        return ruleCategoryString;
    }

    public void setRuleCategoryString(String ruleCategoryString) {
        this.ruleCategoryString = ruleCategoryString;
    }
   
}
