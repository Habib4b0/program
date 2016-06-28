/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.dto;

import java.io.Serializable;

/**
 *
 * @author gopinath
 */
public class ProductGroupLookUpDTO implements Serializable {

    private static final long serialVersionUID = 8125650604655709093L;

    private String company;
    private String productGroupName;
    private String productGroup;
    private Integer itemGroupSid;
    private Integer companySid;
    private String productGroupDescription;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public Integer getItemGroupSid() {
        return itemGroupSid;
    }

    public void setItemGroupSid(Integer itemGroupSid) {
        this.itemGroupSid = itemGroupSid;
    }

    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public String getProductGroupDescription() {
        return productGroupDescription;
    }

    public void setProductGroupDescription(String productGroupDescription) {
        this.productGroupDescription = productGroupDescription;
    }

}
