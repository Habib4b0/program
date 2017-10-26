/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CDRDto implements Serializable {
    public String ruleType = StringUtils.EMPTY;
    public String ruleNo = StringUtils.EMPTY;
    public String ruleName = StringUtils.EMPTY;
    public String ruleCategory = StringUtils.EMPTY;
    public HelperDTO lineTypeDdlb;
    public HelperDTO itemGroupDdlb ;
    public HelperDTO keywordDdlb ;
    public HelperDTO operatorDdlb;
    public HelperDTO comparisonDdlb ;
    public String valueText = StringUtils.EMPTY;
    public int cdrDetailsSid = 0;
    private HelperDTO logicalOperatorDdlb;
    public HelperDTO ruleCategory_DTO;
    public HelperDTO lineTypeDdlb_DTO;
    public HelperDTO keywordDdlb_DTO;
    public HelperDTO operatorDdlb_DTO;
    public HelperDTO comparisonDdlb_DTO;
    private HelperDTO logicalOperatorDdlb_DTO;
    public HelperDTO ruleType_DTO;

    public int getCdrDetailsSid() {
        return cdrDetailsSid;
    }

    public void setCdrDetailsSid(int cdrDetailsSid) {
        this.cdrDetailsSid = cdrDetailsSid;
    }

   

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
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

    public String getRuleCategory() {
        return ruleCategory;
    }

    public void setRuleCategory(String ruleCategory) {
        this.ruleCategory = ruleCategory;
    }
  
    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public HelperDTO getRuleCategory_DTO() {
        return ruleCategory_DTO;
    }

    public void setRuleCategory_DTO(HelperDTO ruleCategory_DTO) {
        this.ruleCategory_DTO = ruleCategory_DTO;
    }

    public HelperDTO getLineTypeDdlb_DTO() {
        return lineTypeDdlb_DTO;
    }

    public void setLineTypeDdlb_DTO(HelperDTO lineTypeDdlb_DTO) {
        this.lineTypeDdlb_DTO = lineTypeDdlb_DTO;
    }

    public HelperDTO getKeywordDdlb_DTO() {
        return keywordDdlb_DTO;
    }

    public void setKeywordDdlb_DTO(HelperDTO keywordDdlb_DTO) {
        this.keywordDdlb_DTO = keywordDdlb_DTO;
    }

    public HelperDTO getOperatorDdlb_DTO() {
        return operatorDdlb_DTO;
    }

    public void setOperatorDdlb_DTO(HelperDTO operatorDdlb_DTO) {
        this.operatorDdlb_DTO = operatorDdlb_DTO;
    }

    public HelperDTO getComparisonDdlb_DTO() {
        return comparisonDdlb_DTO;
    }

    public void setComparisonDdlb_DTO(HelperDTO comparisonDdlb_DTO) {
        this.comparisonDdlb_DTO = comparisonDdlb_DTO;
    }

    public HelperDTO getLogicalOperatorDdlb_DTO() {
        return logicalOperatorDdlb_DTO;
    }

    public void setLogicalOperatorDdlb_DTO(HelperDTO logicalOperatorDdlb_DTO) {
        this.logicalOperatorDdlb_DTO = logicalOperatorDdlb_DTO;
    }

    public HelperDTO getRuleType_DTO() {
        return ruleType_DTO;
    }

    public void setRuleType_DTO(HelperDTO ruleType_DTO) {
        this.ruleType_DTO = ruleType_DTO;
    }

    public HelperDTO getLineTypeDdlb() {
        return lineTypeDdlb;
    }

    public void setLineTypeDdlb(HelperDTO lineTypeDdlb) {
        this.lineTypeDdlb = lineTypeDdlb;
    }

    public HelperDTO getItemGroupDdlb() {
        return itemGroupDdlb;
    }

    public void setItemGroupDdlb(HelperDTO itemGroupDdlb) {
        this.itemGroupDdlb = itemGroupDdlb;
    }

    public HelperDTO getKeywordDdlb() {
        return keywordDdlb;
    }

    public void setKeywordDdlb(HelperDTO keywordDdlb) {
        this.keywordDdlb = keywordDdlb;
    }

    public HelperDTO getOperatorDdlb() {
        return operatorDdlb;
    }

    public void setOperatorDdlb(HelperDTO operatorDdlb) {
        this.operatorDdlb = operatorDdlb;
    }

    public HelperDTO getComparisonDdlb() {
        return comparisonDdlb;
    }

    public void setComparisonDdlb(HelperDTO comparisonDdlb) {
        this.comparisonDdlb = comparisonDdlb;
    }

    public HelperDTO getLogicalOperatorDdlb() {
        return logicalOperatorDdlb;
    }

    public void setLogicalOperatorDdlb(HelperDTO logicalOperatorDdlb) {
        this.logicalOperatorDdlb = logicalOperatorDdlb;
    }
    
    
}
