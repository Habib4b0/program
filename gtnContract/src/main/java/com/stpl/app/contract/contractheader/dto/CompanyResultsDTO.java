/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.contractheader.dto;

import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pvinoth
 */
public class CompanyResultsDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5176635994571171069L;
    /**
     * The company system id.
     */
    private int companySystemId;
    /**
     * The company id.
     */
    private String companyId = StringUtils.EMPTY;
    /**
     * The company no.
     */
    private String companyNo = StringUtils.EMPTY;
    /**
     * The company name.
     */
    private String companyName = StringUtils.EMPTY;
    /**
     * The company status.
     */
    private HelperDTO companyStatus = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * The company type.
     */
    private String companyType = StringUtils.EMPTY;

    /**
     * Gets the company system id.
     *
     * @return the company system id
     */
    public int getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Sets the company system id.
     *
     * @param companySystemId the company system id
     */
    public void setCompanySystemId(final int companySystemId) {
        this.companySystemId = companySystemId;
    }

    /**
     * Gets the company id.
     *
     * @return the company id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * Sets the company id.
     *
     * @param companyId the company id
     */
    public void setCompanyId(final String companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the company no.
     *
     * @return the company no
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * Sets the company no.
     *
     * @param companyNo the company no
     */
    public void setCompanyNo(final String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the company type.
     *
     * @return the company type
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * Sets the company type.
     *
     * @param companyType the company type
     */
    public void setCompanyType(final String companyType) {
        this.companyType = companyType;
    }

    public HelperDTO getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(HelperDTO companyStatus) {
        this.companyStatus = companyStatus;
    }

    
}
