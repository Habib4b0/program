/**
 * 
 */
package com.stpl.app.global.rebateschedule.dto;

import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RebateScheduleMasterDTO.
 *
 * @author manikanta
 */
public class RebateScheduleSearchDTO {
	
	
	/** The rebate schedule trans ref no. */
	public String rebateScheduleTransRefNo=StringUtils.EMPTY;
	
	/** The company familyplan system id. */
	public int companyFamilyplanSystemId;

    public int getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }
	
	/** The formula method id. */
	public String formulaMethodId;
	
	/** The payment frequency. */
	public String paymentFrequency;
        
        /** The formula itemNo */
	private String itemId=StringUtils.EMPTY;
	
        private String systemId =StringUtils.EMPTY;
        
         private String rsCategory =StringUtils.EMPTY;
         
          private String rsDesignation =StringUtils.EMPTY;
          
          private String parentId=StringUtils.EMPTY;
          private String parentName =StringUtils.EMPTY;
          
          
         private Date startDate;
         private Date endDate;
        /** The formula itemNo */
	private String itemNo=StringUtils.EMPTY;
	
	/** The payment itemName */
	private String itemName=StringUtils.EMPTY;
	
	/** The rebate schedule trans ref name. */
	public String rebateScheduleTransRefName=StringUtils.EMPTY;
	
	/** The modified date. */
	public Date modifiedDate;
	
	/** The parent rebate schedule name. */
	public String parentRebateScheduleName=StringUtils.EMPTY;
	
	/** The item familyplan system id. */
	public int itemFamilyplanSystemId;
	
	/** The parent rebate schedule id. */
	public String parentRebateScheduleId=StringUtils.EMPTY;
	
	/** The rebate plan level. */
	public int rebatePlanLevel;
	
	/** The rebate rule type. */
	public int rebateRuleType;
	
	/** The inbound status. */
	public String inboundStatus;
	
	/** The rebate schedule status. */
	public String rebateScheduleStatus=StringUtils.EMPTY;
	
	/** The modified by. */
	public String modifiedBy;
	
	/** The udc6. */
	public int udc6;
	
	/** The udc5. */
	public int udc5;
	
	/** The udc4. */
	public int udc4;
	
	/** The rebate schedule no. */
	public String rebateScheduleNo=StringUtils.EMPTY;
	
	/** The payment method. */
	public int paymentMethod;
	
	/** The udc1. */
	public int udc1;
	
	/** The zip code. */
	public String zipCode;
	
	/** The udc2. */
	public int udc2;
	
	/** The member familyplan system id. */
	public int memberFamilyplanSystemId;
	
	/** The udc3. */
	public int udc3;
	
	/** The rebate rule association. */
	public String rebateRuleAssociation;
	
	/** The internal notes. */
	public String internalNotes;
	
	/** The record lock status. */
	public boolean recordLockStatus;
	
	/** The rebate schedule designation. */
	public String rebateScheduleDesignation;
	
	/** The rebate schedule trans ref id. */
	public String rebateScheduleTransRefId;
	
	/** The rebate schedule name. */
	public String rebateScheduleName=StringUtils.EMPTY;
	
	/** The rebate program type. */
	public String rebateProgramType=StringUtils.EMPTY;
	
	/** The interest bearing basis. */
	public String interestBearingBasis;
	
	/** The city. */
	public String city;
	
	/** The rebate processing type. */
	public String rebateProcessingType;
	
	/** The state. */
	public String state;
        
         public int manufacturer;
	
	/** The rebate frequency. */
	public int rebateFrequency;
	
	/** The rebate schedule id. */
	public String rebateScheduleId=StringUtils.EMPTY;
	
	/** The make payable to. */
	public String makePayableTo;
	
	/** The price schedule system id. */
	public int priceScheduleSystemId;
	
	/** The created by. */
	public String createdBy;
	
	/** The created date. */
	public Date createdDate;
	
	/** The rebate schedule system id. */
	public int rebateScheduleSystemId;
	
	/** The trade class. */
	public int tradeClass;
	
	/** The interest bearing indicator. */
	public String interestBearingIndicator;
	
	/** The payment terms. */
	public int paymentTerms;
	
	/** The address1. */
	public String address1;
	
	/** The rebate schedule type. */
	public String rebateScheduleType=StringUtils.EMPTY;
	
	/** The address2. */
	public String address2;
	
	/** The validation profile. */
	public int validationProfile;
	
	/** The rebate schedule category. */
	public int rebateScheduleCategory;
	
	/** The calendar. */
	public String calendar;
	
	/** The item rebate end date. */
	public Date itemRebateEndDate;
	
	/** The payment grace period. */
	public String paymentGracePeriod;
	
	/** The batch id. */
	public String batchId;
	
	/** The item rebate start date. */
	public Date itemRebateStartDate;
	
	//Added 
	/** The rebate schedule alias. */
	public String rebateScheduleAlias;
        
         /** The rebate schedule no. */
	private String rsNo=StringUtils.EMPTY;
        
         /** The rebate schedule rsId. */
	private String rsId=StringUtils.EMPTY;
        
        /** The rebate schedule rsName. */
	private String rsName=StringUtils.EMPTY;
	
        /** The rebate schedule rsStatus. */
	private String rsStatus=StringUtils.EMPTY;
        
         /** The rebate schedule rsStatus. */
	private String rsType=StringUtils.EMPTY;
        
        /** The rebate schedule system id. */
	private int rsModelSid;
        
        
	/**
	 * Gets the rebate schedule trans ref no.
	 *
	 * @return the rebate schedule trans ref no
	 */
	public String getRebateScheduleTransRefNo() {
		return rebateScheduleTransRefNo;
	}
	
	/**
	 * Sets the rebate schedule trans ref no.
	 *
	 * @param rebateScheduleTransRefNo
	 *            the rebate schedule trans ref no
	 */
	public void setRebateScheduleTransRefNo(final String rebateScheduleTransRefNo) {
		this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
	}
	
	/**
	 * Gets the company familyplan system id.
	 *
	 * @return the company familyplan system id
	 */
	public int getCompanyFamilyplanSystemId() {
		return companyFamilyplanSystemId;
	}
	
	/**
	 * Sets the company familyplan system id.
	 *
	 * @param companyFamilyplanSystemId
	 *            the company familyplan system id
	 */
	public void setCompanyFamilyplanSystemId(final int companyFamilyplanSystemId) {
		this.companyFamilyplanSystemId = companyFamilyplanSystemId;
	}
	
	/**
	 * Gets the formula method id.
	 *
	 * @return the formula method id
	 */
	public String getFormulaMethodId() {
		return formulaMethodId;
	}
	
	/**
	 * Sets the formula method id.
	 *
	 * @param formulaMethodId
	 *            the formula method id
	 */
	public void setFormulaMethodId(final String formulaMethodId) {
		this.formulaMethodId = formulaMethodId;
	}
	
	/**
	 * Gets the payment frequency.
	 *
	 * @return the payment frequency
	 */
	public String getPaymentFrequency() {
		return paymentFrequency;
	}
	
	/**
	 * Sets the payment frequency.
	 *
	 * @param paymentFrequency
	 *            the payment frequency
	 */
	public void setPaymentFrequency(final String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}
	
	/**
	 * Gets the rebate schedule trans ref name.
	 *
	 * @return the rebate schedule trans ref name
	 */
	public String getRebateScheduleTransRefName() {
		return rebateScheduleTransRefName;
	}
	
	/**
	 * Sets the rebate schedule trans ref name.
	 *
	 * @param rebateScheduleTransRefName
	 *            the rebate schedule trans ref name
	 */
	public void setRebateScheduleTransRefName(final String rebateScheduleTransRefName) {
		this.rebateScheduleTransRefName = rebateScheduleTransRefName;
	}
	
	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate
	 *            the modified date
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * Gets the parent rebate schedule name.
	 *
	 * @return the parent rebate schedule name
	 */
	public String getParentRebateScheduleName() {
		return parentRebateScheduleName;
	}
	
	/**
	 * Sets the parent rebate schedule name.
	 *
	 * @param parentRebateScheduleName
	 *            the parent rebate schedule name
	 */
	public void setParentRebateScheduleName(final String parentRebateScheduleName) {
		this.parentRebateScheduleName = parentRebateScheduleName;
	}
	
	/**
	 * Gets the item familyplan system id.
	 *
	 * @return the item familyplan system id
	 */
	public int getItemFamilyplanSystemId() {
		return itemFamilyplanSystemId;
	}
	
	/**
	 * Sets the item familyplan system id.
	 *
	 * @param itemFamilyplanSystemId
	 *            the item familyplan system id
	 */
	public void setItemFamilyplanSystemId(final int itemFamilyplanSystemId) {
		this.itemFamilyplanSystemId = itemFamilyplanSystemId;
	}
	
	/**
	 * Gets the parent rebate schedule id.
	 *
	 * @return the parent rebate schedule id
	 */
	public String getParentRebateScheduleId() {
		return parentRebateScheduleId;
	}
	
	/**
	 * Sets the parent rebate schedule id.
	 *
	 * @param parentRebateScheduleId
	 *            the parent rebate schedule id
	 */
	public void setParentRebateScheduleId(final String parentRebateScheduleId) {
		this.parentRebateScheduleId = parentRebateScheduleId;
	}
	
	/**
	 * Gets the rebate plan level.
	 *
	 * @return the rebate plan level
	 */
	public int getRebatePlanLevel() {
		return rebatePlanLevel;
	}
	
	/**
	 * Sets the rebate plan level.
	 *
	 * @param rebatePlanLevel
	 *            the rebate plan level
	 */
	public void setRebatePlanLevel(final int rebatePlanLevel) {
		this.rebatePlanLevel = rebatePlanLevel;
	}
	
	/**
	 * Gets the rebate rule type.
	 *
	 * @return the rebate rule type
	 */
	public int getRebateRuleType() {
		return rebateRuleType;
	}
	
	/**
	 * Sets the rebate rule type.
	 *
	 * @param rebateRuleType
	 *            the rebate rule type
	 */
	public void setRebateRuleType(final int rebateRuleType) {
		this.rebateRuleType = rebateRuleType;
	}
	
	/**
	 * Gets the inbound status.
	 *
	 * @return the inbound status
	 */
	public String getInboundStatus() {
		return inboundStatus;
	}
	
	/**
	 * Sets the inbound status.
	 *
	 * @param inboundStatus
	 *            the inbound status
	 */
	public void setInboundStatus(final String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}
	
	/**
	 * Gets the rebate schedule status.
	 *
	 * @return the rebate schedule status
	 */
	public String getRebateScheduleStatus() {
		return rebateScheduleStatus;
	}
	
	/**
	 * Sets the rebate schedule status.
	 *
	 * @param rebateScheduleStatus
	 *            the rebate schedule status
	 */
	public void setRebateScheduleStatus(final String rebateScheduleStatus) {
		this.rebateScheduleStatus = rebateScheduleStatus;
	}
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy
	 *            the modified by
	 */
	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the udc6.
	 *
	 * @return the udc6
	 */
	public int getUdc6() {
		return udc6;
	}
	
	/**
	 * Sets the udc6.
	 *
	 * @param udc6
	 *            the udc6
	 */
	public void setUdc6(final int udc6) {
		this.udc6 = udc6;
	}
	
	/**
	 * Gets the udc5.
	 *
	 * @return the udc5
	 */
	public int getUdc5() {
		return udc5;
	}
	
	/**
	 * Sets the udc5.
	 *
	 * @param udc5
	 *            the udc5
	 */
	public void setUdc5(final int udc5) {
		this.udc5 = udc5;
	}
	
	/**
	 * Gets the udc4.
	 *
	 * @return the udc4
	 */
	public int getUdc4() {
		return udc4;
	}
	
	/**
	 * Sets the udc4.
	 *
	 * @param udc4
	 *            the udc4
	 */
	public void setUdc4(final int udc4) {
		this.udc4 = udc4;
	}

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getRsCategory() {
            return rsCategory;
        }

        public void setRsCategory(String rsCategory) {
            this.rsCategory = rsCategory;
        }

        public String getRsDesignation() {
            return rsDesignation;
        }

        public void setRsDesignation(String rsDesignation) {
            this.rsDesignation = rsDesignation;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }
	
        
        
	/**
	 * Gets the rebate schedule no.
	 *
	 * @return the rebate schedule no
	 */
	public String getRebateScheduleNo() {
		return rebateScheduleNo;
	}
	
	/**
	 * Sets the rebate schedule no.
	 *
	 * @param rebateScheduleNo
	 *            the rebate schedule no
	 */
	public void setRebateScheduleNo(final String rebateScheduleNo) {
		this.rebateScheduleNo = rebateScheduleNo;
	}
	
	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public int getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod
	 *            the payment method
	 */
	public void setPaymentMethod(final int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * Gets the udc1.
	 *
	 * @return the udc1
	 */
	public int getUdc1() {
		return udc1;
	}
	
	/**
	 * Sets the udc1.
	 *
	 * @param udc1
	 *            the udc1
	 */
	public void setUdc1(final int udc1) {
		this.udc1 = udc1;
	}
	
	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * Sets the zip code.
	 *
	 * @param zipCode
	 *            the zip code
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * Gets the udc2.
	 *
	 * @return the udc2
	 */
	public int getUdc2() {
		return udc2;
	}
	
	/**
	 * Sets the udc2.
	 *
	 * @param udc2
	 *            the udc2
	 */
	public void setUdc2(final int udc2) {
		this.udc2 = udc2;
	}
	
	/**
	 * Gets the member familyplan system id.
	 *
	 * @return the member familyplan system id
	 */
	public int getMemberFamilyplanSystemId() {
		return memberFamilyplanSystemId;
	}
	
	/**
	 * Sets the member familyplan system id.
	 *
	 * @param memberFamilyplanSystemId
	 *            the member familyplan system id
	 */
	public void setMemberFamilyplanSystemId(final int memberFamilyplanSystemId) {
		this.memberFamilyplanSystemId = memberFamilyplanSystemId;
	}
	
	/**
	 * Gets the udc3.
	 *
	 * @return the udc3
	 */
	public int getUdc3() {
		return udc3;
	}
	
	/**
	 * Sets the udc3.
	 *
	 * @param udc3
	 *            the udc3
	 */
	public void setUdc3(final int udc3) {
		this.udc3 = udc3;
	}
	
	/**
	 * Gets the rebate rule association.
	 *
	 * @return the rebate rule association
	 */
	public String getRebateRuleAssociation() {
		return rebateRuleAssociation;
	}
	
	/**
	 * Sets the rebate rule association.
	 *
	 * @param rebateRuleAssociation
	 *            the rebate rule association
	 */
	public void setRebateRuleAssociation(final String rebateRuleAssociation) {
		this.rebateRuleAssociation = rebateRuleAssociation;
	}
	
	/**
	 * Gets the internal notes.
	 *
	 * @return the internal notes
	 */
	public String getInternalNotes() {
		return internalNotes;
	}
	
	/**
	 * Sets the internal notes.
	 *
	 * @param internalNotes
	 *            the internal notes
	 */
	public void setInternalNotes(final String internalNotes) {
		this.internalNotes = internalNotes;
	}
	
	/**
	 * Gets the record lock status.
	 *
	 * @return the record lock status
	 */
	public boolean getRecordLockStatus() {
		return recordLockStatus;
	}
	
	/**
	 * Sets the record lock status.
	 *
	 * @param recordLockStatus
	 *            the record lock status
	 */
	public void setRecordLockStatus(final boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}
	
	/**
	 * Gets the rebate schedule designation.
	 *
	 * @return the rebate schedule designation
	 */
	public String getRebateScheduleDesignation() {
		return rebateScheduleDesignation;
	}
	
	/**
	 * Sets the rebate schedule designation.
	 *
	 * @param rebateScheduleDesignation
	 *            the rebate schedule designation
	 */
	public void setRebateScheduleDesignation(final String rebateScheduleDesignation) {
		this.rebateScheduleDesignation = rebateScheduleDesignation;
	}
	
	/**
	 * Gets the rebate schedule trans ref id.
	 *
	 * @return the rebate schedule trans ref id
	 */
	public String getRebateScheduleTransRefId() {
		return rebateScheduleTransRefId;
	}
	
	/**
	 * Sets the rebate schedule trans ref id.
	 *
	 * @param rebateScheduleTransRefId
	 *            the rebate schedule trans ref id
	 */
	public void setRebateScheduleTransRefId(final String rebateScheduleTransRefId) {
		this.rebateScheduleTransRefId = rebateScheduleTransRefId;
	}
	
	/**
	 * Gets the rebate schedule name.
	 *
	 * @return the rebate schedule name
	 */
	public String getRebateScheduleName() {
		return rebateScheduleName;
	}
	
	/**
	 * Sets the rebate schedule name.
	 *
	 * @param rebateScheduleName
	 *            the rebate schedule name
	 */
	public void setRebateScheduleName(final String rebateScheduleName) {
		this.rebateScheduleName = rebateScheduleName;
	}
	
	/**
	 * Gets the rebate program type.
	 *
	 * @return the rebate program type
	 */
	public String getRebateProgramType() {
		return rebateProgramType;
	}
	
	/**
	 * Sets the rebate program type.
	 *
	 * @param rebateProgramType
	 *            the rebate program type
	 */
	public void setRebateProgramType(final String rebateProgramType) {
		this.rebateProgramType = rebateProgramType;
	}
	
	/**
	 * Gets the interest bearing basis.
	 *
	 * @return the interest bearing basis
	 */
	public String getInterestBearingBasis() {
		return interestBearingBasis;
	}
	
	/**
	 * Sets the interest bearing basis.
	 *
	 * @param interestBearingBasis
	 *            the interest bearing basis
	 */
	public void setInterestBearingBasis(final String interestBearingBasis) {
		this.interestBearingBasis = interestBearingBasis;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the city
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	
	/**
	 * Gets the rebate processing type.
	 *
	 * @return the rebate processing type
	 */
	public String getRebateProcessingType() {
		return rebateProcessingType;
	}
	
	/**
	 * Sets the rebate processing type.
	 *
	 * @param rebateProcessingType
	 *            the rebate processing type
	 */
	public void setRebateProcessingType(final String rebateProcessingType) {
		this.rebateProcessingType = rebateProcessingType;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the state
	 */
	public void setState(final String state) {
		this.state = state;
	}
	
	/**
	 * Gets the rebate frequency.
	 *
	 * @return the rebate frequency
	 */
	public int getRebateFrequency() {
		return rebateFrequency;
	}
	
	/**
	 * Sets the rebate frequency.
	 *
	 * @param rebateFrequency
	 *            the rebate frequency
	 */
	public void setRebateFrequency(final int rebateFrequency) {
		this.rebateFrequency = rebateFrequency;
	}
	
	/**
	 * Gets the rebate schedule id.
	 *
	 * @return the rebate schedule id
	 */
	public String getRebateScheduleId() {
		return rebateScheduleId;
	}
	
	/**
	 * Sets the rebate schedule id.
	 *
	 * @param rebateScheduleId
	 *            the rebate schedule id
	 */
	public void setRebateScheduleId(final String rebateScheduleId) {
		this.rebateScheduleId = rebateScheduleId;
	}
	
	/**
	 * Gets the make payable to.
	 *
	 * @return the make payable to
	 */
	public String getMakePayableTo() {
		return makePayableTo;
	}
	
	/**
	 * Sets the make payable to.
	 *
	 * @param makePayableTo
	 *            the make payable to
	 */
	public void setMakePayableTo(final String makePayableTo) {
		this.makePayableTo = makePayableTo;
	}
	
	/**
	 * Gets the price schedule system id.
	 *
	 * @return the price schedule system id
	 */
	public int getPriceScheduleSystemId() {
		return priceScheduleSystemId;
	}
	
	/**
	 * Sets the price schedule system id.
	 *
	 * @param priceScheduleSystemId
	 *            the price schedule system id
	 */
	public void setPriceScheduleSystemId(final int priceScheduleSystemId) {
		this.priceScheduleSystemId = priceScheduleSystemId;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the created by
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the rebate schedule system id.
	 *
	 * @return the rebate schedule system id
	 */
	public int getRebateScheduleSystemId() {
		return rebateScheduleSystemId;
	}
	
	/**
	 * Sets the rebate schedule system id.
	 *
	 * @param rebateScheduleSystemId
	 *            the rebate schedule system id
	 */
	public void setRebateScheduleSystemId(final int rebateScheduleSystemId) {
		this.rebateScheduleSystemId = rebateScheduleSystemId;
	}
	
	/**
	 * Gets the trade class.
	 *
	 * @return the trade class
	 */
	public int getTradeClass() {
		return tradeClass;
	}
	
	/**
	 * Sets the trade class.
	 *
	 * @param tradeClass
	 *            the trade class
	 */
	public void setTradeClass(final int tradeClass) {
		this.tradeClass = tradeClass;
	}
	
	/**
	 * Gets the interest bearing indicator.
	 *
	 * @return the interest bearing indicator
	 */
	public String getInterestBearingIndicator() {
		return interestBearingIndicator;
	}
	
	/**
	 * Sets the interest bearing indicator.
	 *
	 * @param interestBearingIndicator
	 *            the interest bearing indicator
	 */
	public void setInterestBearingIndicator(final String interestBearingIndicator) {
		this.interestBearingIndicator = interestBearingIndicator;
	}
	
	/**
	 * Gets the payment terms.
	 *
	 * @return the payment terms
	 */
	public int getPaymentTerms() {
		return paymentTerms;
	}
	
	/**
	 * Sets the payment terms.
	 *
	 * @param paymentTerms
	 *            the payment terms
	 */
	public void setPaymentTerms(final int paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	
	/**
	 * Gets the address1.
	 *
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	
	/**
	 * Sets the address1.
	 *
	 * @param address1
	 *            the address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}
	
	/**
	 * Gets the rebate schedule type.
	 *
	 * @return the rebate schedule type
	 */
	public String getRebateScheduleType() {
		return rebateScheduleType;
	}
	
	/**
	 * Sets the rebate schedule type.
	 *
	 * @param rebateScheduleType
	 *            the rebate schedule type
	 */
	public void setRebateScheduleType(final String rebateScheduleType) {
		this.rebateScheduleType = rebateScheduleType;
	}
	
	/**
	 * Gets the address2.
	 *
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * Sets the address2.
	 *
	 * @param address2
	 *            the address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}
	
	/**
	 * Gets the validation profile.
	 *
	 * @return the validation profile
	 */
	public int getValidationProfile() {
		return validationProfile;
	}
	
	/**
	 * Sets the validation profile.
	 *
	 * @param validationProfile
	 *            the validation profile
	 */
	public void setValidationProfile(final int validationProfile) {
		this.validationProfile = validationProfile;
	}
	
	/**
	 * Gets the rebate schedule category.
	 *
	 * @return the rebate schedule category
	 */
	public int getRebateScheduleCategory() {
		return rebateScheduleCategory;
	}
	
	/**
	 * Sets the rebate schedule category.
	 *
	 * @param rebateScheduleCategory
	 *            the rebate schedule category
	 */
	public void setRebateScheduleCategory(final int rebateScheduleCategory) {
		this.rebateScheduleCategory = rebateScheduleCategory;
	}
	
	/**
	 * Gets the calendar.
	 *
	 * @return the calendar
	 */
	public String getCalendar() {
		return calendar;
	}
	
	/**
	 * Sets the calendar.
	 *
	 * @param calendar
	 *            the calendar
	 */
	public void setCalendar(final String calendar) {
		this.calendar = calendar;
	}
	
	/**
	 * Gets the item rebate end date.
	 *
	 * @return the item rebate end date
	 */
	public Date getItemRebateEndDate() {
		return itemRebateEndDate;
	}
	
	/**
	 * Sets the item rebate end date.
	 *
	 * @param itemRebateEndDate
	 *            the item rebate end date
	 */
	public void setItemRebateEndDate(final Date itemRebateEndDate) {
		this.itemRebateEndDate = itemRebateEndDate;
	}
	
	/**
	 * Gets the payment grace period.
	 *
	 * @return the payment grace period
	 */
	public String getPaymentGracePeriod() {
		return paymentGracePeriod;
	}
	
	/**
	 * Sets the payment grace period.
	 *
	 * @param paymentGracePeriod
	 *            the payment grace period
	 */
	public void setPaymentGracePeriod(final String paymentGracePeriod) {
		this.paymentGracePeriod = paymentGracePeriod;
	}
	
	/**
	 * Gets the batch id.
	 *
	 * @return the batch id
	 */
	public String getBatchId() {
		return batchId;
	}
	
	/**
	 * Sets the batch id.
	 *
	 * @param batchId
	 *            the batch id
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * Gets the item rebate start date.
	 *
	 * @return the item rebate start date
	 */
	public Date getItemRebateStartDate() {
		return itemRebateStartDate;
	}
	
	/**
	 * Sets the item rebate start date.
	 *
	 * @param itemRebateStartDate
	 *            the item rebate start date
	 */
	public void setItemRebateStartDate(final Date itemRebateStartDate) {
		this.itemRebateStartDate = itemRebateStartDate;
	}
	
	/**
	 * Gets the rebate schedule alias.
	 *
	 * @return the rebate schedule alias
	 */
	public String getRebateScheduleAlias() {
		return rebateScheduleAlias;
	}
	
	/**
	 * Sets the rebate schedule alias.
	 *
	 * @param rebateScheduleAlias
	 *            the rebate schedule alias
	 */
	public void setRebateScheduleAlias(final String rebateScheduleAlias) {
		this.rebateScheduleAlias = rebateScheduleAlias;
	}

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsStatus() {
        return rsStatus;
    }

    public void setRsStatus(String rsStatus) {
        this.rsStatus = rsStatus;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public int getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        this.rsModelSid = rsModelSid;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    
    
   public static Comparator<RebateScheduleSearchDTO> rsIdAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleId()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleId()).toUpperCase();
	   return value1.compareTo(value2);
    }};      
   
   public static Comparator<RebateScheduleSearchDTO> rsIdDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleId()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleId()).toUpperCase();
	   return value2.compareTo(value1);
    }};     
   
   public static Comparator<RebateScheduleSearchDTO> rsNoAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleNo()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleNo()).toUpperCase();
	   return value1.compareTo(value2);
    }};     
   
   public static Comparator<RebateScheduleSearchDTO> rsNoDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleNo()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleNo()).toUpperCase();
	   return value2.compareTo(value1);
    }};       
   
   public static Comparator<RebateScheduleSearchDTO> rsNameAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleName()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleName()).toUpperCase();
	   return value1.compareTo(value2);
    }};      
   
   public static Comparator<RebateScheduleSearchDTO> rsNameDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleName()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleName()).toUpperCase();
	   return value2.compareTo(value1);
    }};        
   
   public static Comparator<RebateScheduleSearchDTO> rsStatusAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleStatus()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleStatus()).toUpperCase();
	   return value1.compareTo(value2);
    }};    
   
   
   public static Comparator<RebateScheduleSearchDTO> rsStatusDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleStatus()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleStatus()).toUpperCase();
	   return value2.compareTo(value1);
    }};      
   
   public static Comparator<RebateScheduleSearchDTO> rsTypeAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleType()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleType()).toUpperCase();
	   return value1.compareTo(value2);
    }};       
   
   public static Comparator<RebateScheduleSearchDTO> rsTypeDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateScheduleType()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateScheduleType()).toUpperCase();
	   return value2.compareTo(value1);
    }};     
   
   public static Comparator<RebateScheduleSearchDTO> rebatePgmTypeAsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateProgramType()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateProgramType()).toUpperCase();
	   return value1.compareTo(value2);
    }};    
   
  public static Comparator<RebateScheduleSearchDTO> rebatePgmTypeDsc = new Comparator<RebateScheduleSearchDTO>() {
	public int compare(RebateScheduleSearchDTO s1, RebateScheduleSearchDTO s2) {
	   String value1 = String.valueOf(s1.getRebateProgramType()).toUpperCase();
	   String value2 = String.valueOf(s2.getRebateProgramType()).toUpperCase();
	   return value2.compareTo(value1);
    }};      
    
}
