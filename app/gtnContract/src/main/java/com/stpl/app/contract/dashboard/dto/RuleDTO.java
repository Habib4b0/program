/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author jayaram
 */
public class RuleDTO {
    private String ruleNo =StringUtils.EMPTY;
    
    private String ruleName = StringUtils.EMPTY;
    
    private String ruleVersion = StringUtils.EMPTY;

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

    public String getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(String ruleVersion) {
        this.ruleVersion = ruleVersion;
    }
    
}
