package com.stpl.app.global.ifp.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


// TODO: Auto-generated Javadoc
/**
 * The Class ItemFamilyplanMasterDTO.
 */
public class ItemFamilyplanSearchDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The company familyplan system id. */
	private int companyFamilyplanSystemId;
	
	/** The total dollar commitment. */
	private String totalDollarCommitment= StringUtils.EMPTY;
	
	/** The item familyplan id. */
	private String itemFamilyplanId = StringUtils.EMPTY;
        
	
	/** The modified date. */
	private Date modifiedDate;
	
	/** The item familyplan system id. */
	private int itemFamilyplanSystemId;
	
	/** The item familyplan name. */
	private String itemFamilyplanName = StringUtils.EMPTY;
        
	
	/** The created by. */
	private String createdBy = StringUtils.EMPTY;
	
	/** The created date. */
	private Date createdDate ;
	
	/** The item familyplan end date. */
	private Date itemFamilyplanEndDate;
	
	/** The parent item familyplan name. */
	private String parentItemFamilyplanName = StringUtils.EMPTY;
	
	/** The parent item familyplan id. */
	private String parentItemFamilyplanId = StringUtils.EMPTY;
	
	/** The modified by. */
	private String modifiedBy;
	
	/** The inbound status. */
	private String inboundStatus;
	
	/** The commitment period. */
	private String commitmentPeriod= StringUtils.EMPTY;
	
	/** The item familyplan no. */
	private String itemFamilyplanNo = StringUtils.EMPTY;
        
	
	/** The item familyplan status. */
	private String itemFamilyplanStatus = StringUtils.EMPTY;
        
	
	/** The item familyplan category. */
	private String itemFamilyplanCategory = StringUtils.EMPTY;
	
	/** The member familyplan system id. */
	private int memberFamilyplanSystemId;
	
	/** The total volume commitment. */
	private String totalVolumeCommitment= StringUtils.EMPTY;
	
	/** The item familyplan start date. */
	private Date itemFamilyplanStartDate;
	
	/** The total marketshare commitment. */
	private String totalMarketshareCommitment = StringUtils.EMPTY;
	
	/** The record lock status. */
	private Boolean recordLockStatus;
	
	/** The batch id. */
	private String batchId;
	
	/** The item familyplan type. */
	private String itemFamilyplanType = StringUtils.EMPTY;

    public String getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    public String getIfpStartDate() {
        return ifpStartDate;
    }

    public void setIfpStartDate(String ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    public String getIfpEndDate() {
        return ifpEndDate;
    }

    public void setIfpEndDate(String ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public String getIfpDesignation() {
        return ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        this.ifpDesignation = ifpDesignation;
    }
        private String ifpId = StringUtils.EMPTY;
        
        private String ifpNo = StringUtils.EMPTY;
        
        private String ifpName = StringUtils.EMPTY;
        private String ifpStatus = StringUtils.EMPTY;
        private String ifpType = StringUtils.EMPTY;
        private String ifpCategory = StringUtils.EMPTY;
        private String ifpStartDate = StringUtils.EMPTY;
        private String ifpEndDate = StringUtils.EMPTY;
        private String ifpDesignation = StringUtils.EMPTY;

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }
       
	
	/** The item uom. */
	private String itemUom;
	
	/** The primary uom. */
	private String primaryUom = StringUtils.EMPTY;
	
	/** The therapeutic class. */
	private String therapeuticClass = StringUtils.EMPTY;
	
	/** The brand. */
	private String brand = StringUtils.EMPTY;
	
	/** The form. */
	private String form = StringUtils.EMPTY;
	
	/** The strength. */
	private String strength = StringUtils.EMPTY;
	
	/** The attached date. */
	private Date attachedDate;
	
	/** The item familyplan designation. */
	private String itemFamilyplanDesignation = StringUtils.EMPTY;
	
	
	/**
	 * Gets the primary uom.
	 *
	 * @return the primary uom
	 */
	public String getPrimaryUom() {
		return primaryUom;
	}

	/**
	 * Sets the primary uom.
	 *
	 * @param primaryUom the new primary uom
	 */
	public void setPrimaryUom(final String primaryUom) {
		this.primaryUom = primaryUom;
	}

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(final String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	public String getForm() {
		return form;
	}

	/**
	 * Sets the form.
	 *
	 * @param form the new form
	 */
	public void setForm(final String form) {
		this.form = form;
	}

	/**
	 * Gets the strength.
	 *
	 * @return the strength
	 */
	public String getStrength() {
		return strength;
	}

	/**
	 * Sets the strength.
	 *
	 * @param strength the new strength
	 */
	public void setStrength(final String strength) {
		this.strength = strength;
	}

	/**
	 * Gets the therapeutic class.
	 *
	 * @return the therapeutic class
	 */
	public String getTherapeuticClass() {
		return therapeuticClass;
	}

	/**
	 * Sets the therapeutic class.
	 *
	 * @param therapeuticClass the new therapeutic class
	 */
	public void setTherapeuticClass(final String therapeuticClass) {
		this.therapeuticClass = therapeuticClass;
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
	 * @param companyFamilyplanSystemId the new company familyplan system id
	 */
	public void setCompanyFamilyplanSystemId(final int companyFamilyplanSystemId) {
		this.companyFamilyplanSystemId = companyFamilyplanSystemId;
	}
	
	/**
	 * Gets the total dollar commitment.
	 *
	 * @return the total dollar commitment
	 */
	public String getTotalDollarCommitment() {
		return totalDollarCommitment;
	}
	
	/**
	 * Sets the total dollar commitment.
	 *
	 * @param totalDollarCommitment the new total dollar commitment
	 */
	public void setTotalDollarCommitment(final String totalDollarCommitment) {
		this.totalDollarCommitment = totalDollarCommitment;
	}
	
	/**
	 * Gets the item familyplan id.
	 *
	 * @return the item familyplan id
	 */
	public String getItemFamilyplanId() {
		return itemFamilyplanId;
	}
	
	/**
	 * Sets the item familyplan id.
	 *
	 * @param itemFamilyplanId the new item familyplan id
	 */
	public void setItemFamilyplanId(final String itemFamilyplanId) {
		this.itemFamilyplanId = itemFamilyplanId;
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
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	 * @param itemFamilyplanSystemId the new item familyplan system id
	 */
	public void setItemFamilyplanSystemId(final int itemFamilyplanSystemId) {
		this.itemFamilyplanSystemId = itemFamilyplanSystemId;
	}
	
	/**
	 * Gets the item familyplan name.
	 *
	 * @return the item familyplan name
	 */
	public String getItemFamilyplanName() {
		return itemFamilyplanName;
	}
	
	/**
	 * Sets the item familyplan name.
	 *
	 * @param itemFamilyplanName the new item familyplan name
	 */
	public void setItemFamilyplanName(final String itemFamilyplanName) {
		this.itemFamilyplanName = itemFamilyplanName;
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
	 * @param createdBy the new created by
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
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the item familyplan end date.
	 *
	 * @return the item familyplan end date
	 */
	public Date getItemFamilyplanEndDate() {
		return itemFamilyplanEndDate;
	}
	
	/**
	 * Sets the item familyplan end date.
	 *
	 * @param itemFamilyplanEndDate the new item familyplan end date
	 */
	public void setItemFamilyplanEndDate(final Date itemFamilyplanEndDate) {
		this.itemFamilyplanEndDate = itemFamilyplanEndDate;
	}
	
	/**
	 * Gets the parent item familyplan name.
	 *
	 * @return the parent item familyplan name
	 */
	public String getParentItemFamilyplanName() {
		return parentItemFamilyplanName;
	}
	
	/**
	 * Sets the parent item familyplan name.
	 *
	 * @param parentItemFamilyplanName the new parent item familyplan name
	 */
	public void setParentItemFamilyplanName(final String parentItemFamilyplanName) {
		this.parentItemFamilyplanName = parentItemFamilyplanName;
	}
	
	/**
	 * Gets the parent item familyplan id.
	 *
	 * @return the parent item familyplan id
	 */
	public String getParentItemFamilyplanId() {
		return parentItemFamilyplanId;
	}
	
	/**
	 * Sets the parent item familyplan id.
	 *
	 * @param parentItemFamilyplanId the new parent item familyplan id
	 */
	public void setParentItemFamilyplanId(final String parentItemFamilyplanId) {
		this.parentItemFamilyplanId = parentItemFamilyplanId;
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
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	 * @param inboundStatus the new inbound status
	 */
	public void setInboundStatus(final String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}
	
	/**
	 * Gets the commitment period.
	 *
	 * @return the commitment period
	 */
	public String getCommitmentPeriod() {
		return commitmentPeriod;
	}
	
	/**
	 * Sets the commitment period.
	 *
	 * @param commitmentPeriod the new commitment period
	 */
	public void setCommitmentPeriod(final String commitmentPeriod) {
		this.commitmentPeriod = commitmentPeriod;
	}
	
	/**
	 * Gets the item familyplan no.
	 *
	 * @return the item familyplan no
	 */
	public String getItemFamilyplanNo() {
		return itemFamilyplanNo;
	}
	
	/**
	 * Sets the item familyplan no.
	 *
	 * @param itemFamilyplanNo the new item familyplan no
	 */
	public void setItemFamilyplanNo(final String itemFamilyplanNo) {
		this.itemFamilyplanNo = itemFamilyplanNo;
	}
	
	/**
	 * Gets the item familyplan status.
	 *
	 * @return the item familyplan status
	 */
	public String getItemFamilyplanStatus() {
		return itemFamilyplanStatus;
	}
	
	/**
	 * Sets the item familyplan status.
	 *
	 * @param itemFamilyplanStatus the new item familyplan status
	 */
	public void setItemFamilyplanStatus(final String itemFamilyplanStatus) {
		this.itemFamilyplanStatus = itemFamilyplanStatus;
	}
	
	/**
	 * Gets the item familyplan category.
	 *
	 * @return the item familyplan category
	 */
	public String getItemFamilyplanCategory() {
		return itemFamilyplanCategory;
	}
	
	/**
	 * Sets the item familyplan category.
	 *
	 * @param itemFamilyplanCategory the new item familyplan category
	 */
	public void setItemFamilyplanCategory(final String itemFamilyplanCategory) {
		this.itemFamilyplanCategory = itemFamilyplanCategory;
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
	 * @param memberFamilyplanSystemId the new member familyplan system id
	 */
	public void setMemberFamilyplanSystemId(final int memberFamilyplanSystemId) {
		this.memberFamilyplanSystemId = memberFamilyplanSystemId;
	}
	
	/**
	 * Gets the total volume commitment.
	 *
	 * @return the total volume commitment
	 */
	public String getTotalVolumeCommitment() {
		return totalVolumeCommitment;
	}
	
	/**
	 * Sets the total volume commitment.
	 *
	 * @param totalVolumeCommitment the new total volume commitment
	 */
	public void setTotalVolumeCommitment(final String totalVolumeCommitment) {
		this.totalVolumeCommitment = totalVolumeCommitment;
	}
	
	/**
	 * Gets the item familyplan start date.
	 *
	 * @return the item familyplan start date
	 */
	public Date getItemFamilyplanStartDate() {
		return itemFamilyplanStartDate;
	}
	
	/**
	 * Sets the item familyplan start date.
	 *
	 * @param itemFamilyplanStartDate the new item familyplan start date
	 */
	public void setItemFamilyplanStartDate(final Date itemFamilyplanStartDate) {
		this.itemFamilyplanStartDate = itemFamilyplanStartDate;
	}
	
	/**
	 * Gets the total marketshare commitment.
	 *
	 * @return the total marketshare commitment
	 */
	public String getTotalMarketshareCommitment() {
		return totalMarketshareCommitment;
	}
	
	/**
	 * Sets the total marketshare commitment.
	 *
	 * @param totalMarketshareCommitment the new total marketshare commitment
	 */
	public void setTotalMarketshareCommitment(final String totalMarketshareCommitment) {
		this.totalMarketshareCommitment = totalMarketshareCommitment;
	}
	
	/**
	 * Gets the record lock status.
	 *
	 * @return the record lock status
	 */
	public Boolean getRecordLockStatus() {
		return recordLockStatus;
	}

    public void setRecordLockStatus(Boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
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
	 * @param batchId the new batch id
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * Gets the item familyplan type.
	 *
	 * @return the item familyplan type
	 */
	public String getItemFamilyplanType() {
		return itemFamilyplanType;
	}
	
	/**
	 * Sets the item familyplan type.
	 *
	 * @param itemFamilyplanType the new item familyplan type
	 */
	public void setItemFamilyplanType(final String itemFamilyplanType) {
		this.itemFamilyplanType = itemFamilyplanType;
	}
	
	/**
	 * Gets the item familyplan designation.
	 *
	 * @return the item familyplan designation
	 */
	public String getItemFamilyplanDesignation() {
		return itemFamilyplanDesignation;
	}
	
	/**
	 * Sets the item familyplan designation.
	 *
	 * @param itemFamilyplanDesignation the new item familyplan designation
	 */
	public void setItemFamilyplanDesignation(final String itemFamilyplanDesignation) {
		this.itemFamilyplanDesignation = itemFamilyplanDesignation;
	}

	/**
	 * Gets the item uom.
	 *
	 * @return the item uom
	 */
	public String getItemUom() {
		return itemUom;
	}

	/**
	 * Sets the item uom.
	 *
	 * @param itemUom the new item uom
	 */
	public void setItemUom(final String itemUom) {
		this.itemUom = itemUom;
	}

	/**
	 * Gets the attached date.
	 *
	 * @return the attached date
	 */
	public Date getAttachedDate() {
		return attachedDate;
	}

	/**
	 * Sets the attached date.
	 *
	 * @param attachedDate the new attached date
	 */
	public void setAttachedDate(final Date attachedDate) {
		this.attachedDate = attachedDate;
	}
}
