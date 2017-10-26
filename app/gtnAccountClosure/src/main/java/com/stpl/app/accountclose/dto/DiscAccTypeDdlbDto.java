/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class DiscAccTypeDdlbDto {

    private String acctType = StringUtils.EMPTY;

    public DiscAccTypeDdlbDto() {
    }

    public DiscAccTypeDdlbDto(String acctType) {
        this.acctType = acctType;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}
