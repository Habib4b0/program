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
    private String ruleType = StringUtils.EMPTY;
    private String ruleNo = StringUtils.EMPTY;
    private String ruleName = StringUtils.EMPTY;
    private String ruleCategory = StringUtils.EMPTY;
    private HelperDTO lineTypeDdlb;
    private HelperDTO itemGroupDdlb ;
    private HelperDTO keywordDdlb ;
    private HelperDTO operatorDdlb;
    private HelperDTO comparisonDdlb ;
    private String valueText = StringUtils.EMPTY;
    private int cdrDetailsSid = 0;
    private HelperDTO logicalOperatorDdlb;
    private HelperDTO ruleCategoryDto;
    private HelperDTO lineTypeDdlbDto;
    private HelperDTO keywordDdlbDto;
    private HelperDTO operatorDdlbDto;
    private HelperDTO comparisonDdlbDto;
    private HelperDTO logicalOperatorDdlbDto;
    private HelperDTO ruleTypeDto;

    public CDRDto(){
    	super();
    }
    
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

    public HelperDTO getRuleCategoryDto() {
        return ruleCategoryDto;
    }

    public void setRuleCategoryDto(HelperDTO ruleCategoryDto) {
        this.ruleCategoryDto = ruleCategoryDto;
    }

    public HelperDTO getLineTypeDdlbDto() {
        return lineTypeDdlbDto;
    }

    public void setLineTypeDdlbDto(HelperDTO lineTypeDdlbDto) {
        this.lineTypeDdlbDto = lineTypeDdlbDto;
    }

    public HelperDTO getKeywordDdlbDto() {
        return keywordDdlbDto;
    }

    public void setKeywordDdlbDto(HelperDTO keywordDdlbDto) {
        this.keywordDdlbDto = keywordDdlbDto;
    }

    public HelperDTO getOperatorDdlbDto() {
        return operatorDdlbDto;
    }

    public void setOperatorDdlbDto(HelperDTO operatorDdlbDto) {
        this.operatorDdlbDto = operatorDdlbDto;
    }

    public HelperDTO getComparisonDdlbDto() {
        return comparisonDdlbDto;
    }

    public void setComparisonDdlbDto(HelperDTO comparisonDdlbDto) {
        this.comparisonDdlbDto = comparisonDdlbDto;
    }

    public HelperDTO getLogicalOperatorDdlbDto() {
        return logicalOperatorDdlbDto;
    }

    public void setLogicalOperatorDdlbDto(HelperDTO logicalOperatorDdlbDto) {
        this.logicalOperatorDdlbDto = logicalOperatorDdlbDto;
    }

    public HelperDTO getRuleTypeDto() {
        return ruleTypeDto;
    }

    public void setRuleTypeDto(HelperDTO ruleTypeDto) {
        this.ruleTypeDto = ruleTypeDto;
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
