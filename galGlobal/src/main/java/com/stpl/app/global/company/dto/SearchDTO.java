package com.stpl.app.global.company.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;


/**
 * The Class SearchDTO.
 */
public class SearchDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 6298884937812241080L;
    
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);

    /**
     * The company no.
     */
    @NotBlank(message = "The Company No must not be empty")
    private String companyNo = HelperUtils.EMPTY;

    /**
     * The company id.
     */
    private String companyId = HelperUtils.EMPTY;

    /**
     * The company name.
     */
    private String companyName = HelperUtils.EMPTY;

    /**
     * The company status.
     */
    private int companyStatus=0;
    
    private int companyCategory=0;
    
    private int companyGroup=0;
    
    private int tradeClass=0; 

    /**
     * The company start date.
     */
    private Date companyStartDate;

    /**
     * The company end date.
     */
    private Date companyEndDate;

    /**
     * The company qualifier.
     */
    private HelperDTO companyQualifier = new HelperDTO(ConstantsUtils.SELECT_ONE);;

    /**
     * The company identifier.
     */
    private String companyIdentifier = HelperUtils.EMPTY; 

    /**
     * The system id.
     */
    private int systemId;

    /**
     * The error messages.
     */
    private String errorMessages = HelperUtils.EMPTY;

    /**
     * The company type.
     */
    private int companyType=0;

    /**
     * The identifier.
     */
    private String identifier = HelperUtils.EMPTY;

    /**
     * The company type desc.
     */
    private String companyTypeDesc = HelperUtils.EMPTY;

    /**
     * The company status desc.
     */
    private String companyStatusDesc = HelperUtils.EMPTY;

    /**
     * The identifier type desc.
     */
    private HelperDTO identifierTypeDesc = dto;

    /**
     * The qualifier id.
     */
    private String qualifierId = HelperUtils.EMPTY;

     /** The company system id. */
     private String companySystemId;   
       
    /**
     * The search flag.
     */
    private static Boolean searchFlag;

    /**
     * The qualifier flag.
     */
    private static Boolean qualifierFlag;

    /**
     * The identifier flag.
     */
    private static Boolean identifierFlag;

    /**
     * The success message.
     */
    private String successMessage;
    
      /**
     * The identifier type desc.
     */
    private HelperDTO identifierType = dto;

    public HelperDTO getDto() {
        return dto;
    }

    public void setDto(HelperDTO dto) {
        this.dto = dto;
    }

    public int getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(int companyCategory) {
        this.companyCategory = companyCategory;
    }

    public int getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(int companyGroup) {
        this.companyGroup = companyGroup;
    }

    public int getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(int tradeClass) {
        this.tradeClass = tradeClass;
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
     * Gets the company status.
     *
     * @return the company status
     */
    public int getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Sets the company status.
     *
     * @param companyStatus the company status
     */
    public void setCompanyStatus(final int companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * Gets the company start date.
     *
     * @return the company start date
     */
    public Date getCompanyStartDate() {
        return companyStartDate;
    }

    /**
     * Sets the company start date.
     *
     * @param companyStartDate the company start date
     */
    public void setCompanyStartDate(final Date companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    /**
     * Gets the company end date.
     *
     * @return the company end date
     */
    public Date getCompanyEndDate() {
        return companyEndDate;
    }

    /**
     * Sets the company end date.
     *
     * @param companyEndDate the company end date
     */
    public void setCompanyEndDate(final Date companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    /**
     * Gets the company qualifier.
     *
     * @return the company qualifier
     */
    public HelperDTO getCompanyQualifier() {
        return companyQualifier;
    }

    /**
     * Sets the company qualifier.
     *
     * @param companyQualifier the company qualifier
     */
    public void setCompanyQualifier(final HelperDTO companyQualifier) {
        this.companyQualifier = companyQualifier;
    }

    /**
     * Gets the company identifier.
     *
     * @return the company identifier
     */
    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

    /**
     * Sets the company identifier.
     *
     * @param companyIdentifier the company identifier
     */
    public void setCompanyIdentifier(final String companyIdentifier) {
        this.companyIdentifier = companyIdentifier;
    }

    /**
     * Gets the system id.
     *
     * @return the system id
     */
    public int getSystemId() {
        return systemId;
    }

    /**
     * Sets the system id.
     *
     * @param systemId the system id
     */
    public void setSystemId(final int systemId) {
        this.systemId = systemId;
    }

    /**
     * Gets the error messages.
     *
     * @return the error messages
     */
    public String getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets the error messages.
     *
     * @param errorMessages the error messages
     */
    public void setErrorMessages(final String errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Gets the company type.
     *
     * @return the company type
     */
    public int getCompanyType() {
        return companyType;
    }

    /**
     * Sets the company type.
     *
     * @param companyType the company type
     */
    public void setCompanyType(final int companyType) {
        this.companyType = companyType;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the company type desc.
     *
     * @return the company type desc
     */
    public String getCompanyTypeDesc() {
        return companyTypeDesc;
    }

    /**
     * Sets the company type desc.
     *
     * @param companyTypeDesc the company type desc
     */
    public void setCompanyTypeDesc(final String companyTypeDesc) {
        this.companyTypeDesc = companyTypeDesc;
    }

    /**
     * Gets the company status desc.
     *
     * @return the company status desc
     */
    public String getCompanyStatusDesc() {
        return companyStatusDesc;
    }

    /**
     * Sets the company status desc.
     *
     * @param companyStatusDesc the company status desc
     */
    public void setCompanyStatusDesc(final String companyStatusDesc) {
        this.companyStatusDesc = companyStatusDesc;
    }

    /**
     * Gets the identifier type desc.
     *
     * @return the identifier type desc
     */
    public HelperDTO getIdentifierTypeDesc() {
        return identifierTypeDesc;
    }

    /**
     * Sets the identifier type desc.
     *
     * @param identifierTypeDesc the identifier type desc
     */
    public void setIdentifierTypeDesc(final HelperDTO identifierTypeDesc) {
        this.identifierTypeDesc = identifierTypeDesc;
    }

    /**
     * Gets the qualifier id.
     *
     * @return the qualifier id
     */
    public String getQualifierId() {
        return qualifierId;
    }

    /**
     * Sets the qualifier id.
     *
     * @param qualifierId the qualifier id
     */
    public void setQualifierId(final String qualifierId) {
        this.qualifierId = qualifierId;
    }

    /**
     * Checks if is search flag.
     *
     * @return true, if checks if is search flag
     */
    public Boolean isSearchFlag() {
        return searchFlag;
    }

    /**
     * Sets the search flag.
     *
     * @param searchFlag the search flag
     */
    public void setSearchFlag(final Boolean searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * Checks if is qualifier flag.
     *
     * @return true, if checks if is qualifier flag
     */
    public Boolean isQualifierFlag() {
        return qualifierFlag;
    }

    /**
     * Sets the qualifier flag.
     *
     * @param qualifierFlag the qualifier flag
     */
    public void setQualifierFlag(final Boolean qualifierFlag) {
        this.qualifierFlag = qualifierFlag;
    }

    /**
     * Checks if is identifier flag.
     *
     * @return true, if checks if is identifier flag
     */
    public Boolean isIdentifierFlag() {
        return identifierFlag;
    }

    /**
     * Sets the identifier flag.
     *
     * @param identifierFlag the identifier flag
     */
    public void setIdentifierFlag(final Boolean identifierFlag) {
        this.identifierFlag = identifierFlag;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message.
     *
     * @return the company system id
     */
    public String getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Sets the company system id.
     *
     * @param companySystemId the new company system id
     */
    public void setCompanySystemId(final String companySystemId) {
        this.companySystemId = companySystemId;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage the new success message
     */
    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    public HelperDTO getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(HelperDTO identifierType) {
        this.identifierType = identifierType;
    }

}
