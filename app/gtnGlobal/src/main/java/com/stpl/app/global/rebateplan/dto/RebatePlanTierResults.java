package com.stpl.app.global.rebateplan.dto;

// TODO: Auto-generated Javadoc

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class RebatePlanTierResults.
 */
public class RebatePlanTierResults implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7601850543220094067L;

	/** The tier value. */
	private String tierValue;
	
	/** The rebate plan tier system id. */
	private int rebatePlanTierSystemId;
	
	/** The modified date. */
	private long modifiedDate;
	
	/** The rebate plan system id. */
	private int rebatePlanSystemId;
	
	/** The tier tolerance. */
	private double tierTolerance;
	
	/** The record lock status. */
	private String recordLockStatus;
	
	/** The tier from. */
	private BigDecimal tierFrom;
	
	/** The tier operator. */
	private String tierOperator;
        
        /** The tier operatorValue. */
	private String tierOperatorValue;
	
	/** The created by. */
	private String createdBy;
	
	/** The created date. */
	private long createdDate;
	
	/** The tier to. */
	private BigDecimal tierTo;
	
	/** The batch id. */
	private String batchId;
	
	/** The rebate plan tier id. */
	private String rebatePlanTierId;
	
	/** The free amount. */
	private double freeAmount;
	
	/** The modified by. */
	private String modifiedBy;
	
	/** The tier level. */
	private String tierLevel;
        
        private String formulaNo;
        
        private String formulaName;
        
        private String secondaryRebatePlanNo;
        
        private String secondaryRebatePlanName;        
        
        private int secondaryRebatePlanSid;
	
        private String formulaID;
        
        /** The tier value. */
	private String tierValueId;
        
	/**
	 * Gets the tier value.
	 *
	 * @return the tierValue
	 */
	public String getTierValue() {
		return tierValue;
	}
	
	/**
	 * Sets the tier value.
	 *
	 * @param tierValue the tierValue to set
	 */
	public void setTierValue(final String tierValue) {
		this.tierValue = tierValue;
	}
	
	/**
	 * Gets the rebate plan tier system id.
	 *
	 * @return the rebatePlanTierSystemId
	 */
	public int getRebatePlanTierSystemId() {
		return rebatePlanTierSystemId;
	}
	
	/**
	 * Sets the rebate plan tier system id.
	 *
	 * @param rebatePlanTierSystemId the rebatePlanTierSystemId to set
	 */
	public void setRebatePlanTierSystemId(final int rebatePlanTierSystemId) {
		this.rebatePlanTierSystemId = rebatePlanTierSystemId;
	}
	
	/**
	 * Gets the modified date.
	 *
	 * @return the modifiedDate
	 */
	public long getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(final long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * Gets the rebate plan system id.
	 *
	 * @return the rebatePlanSystemId
	 */
	public int getRebatePlanSystemId() {
		return rebatePlanSystemId;
	}
	
	/**
	 * Sets the rebate plan system id.
	 *
	 * @param rebatePlanSystemId the rebatePlanSystemId to set
	 */
	public void setRebatePlanSystemId(final int rebatePlanSystemId) {
		this.rebatePlanSystemId = rebatePlanSystemId;
	}
	
	/**
	 * Gets the tier tolerance.
	 *
	 * @return the tierTolerance
	 */
	public double getTierTolerance() {
		return tierTolerance;
	}
	
	/**
	 * Sets the tier tolerance.
	 *
	 * @param tierTolerance the tierTolerance to set
	 */
	public void setTierTolerance(final double tierTolerance) {
		this.tierTolerance = tierTolerance;
	}
	
	/**
	 * Gets the record lock status.
	 *
	 * @return the recordLockStatus
	 */
	public String getRecordLockStatus() {
		return recordLockStatus;
	}
	
	/**
	 * Sets the record lock status.
	 *
	 * @param recordLockStatus the recordLockStatus to set
	 */
	public void setRecordLockStatus(final String recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}
	
	/**
	 * Gets the tier from.
	 *
	 * @return the tierFrom
	 */
	public BigDecimal getTierFrom() {
		return tierFrom;
	}
	
	/**
	 * Sets the tier from.
	 *
	 * @param tierFrom the tierFrom to set
	 */
	public void setTierFrom(final BigDecimal tierFrom) {
		this.tierFrom = tierFrom;
	}
	
	/**
	 * Gets the tier operator.
	 *
	 * @return the tierOperator
	 */
	public String getTierOperator() {
		return tierOperator;
	}
	
	/**
	 * Sets the tier operator.
	 *
	 * @param tierOperator the tierOperator to set
	 */
	public void setTierOperator(final String tierOperator) {
		this.tierOperator = tierOperator;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the createdDate
	 */
	public long getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(final long createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the tier to.
	 *
	 * @return the tierTo
	 */
	public BigDecimal getTierTo() {
		return tierTo;
	}
	
	/**
	 * Sets the tier to.
	 *
	 * @param tierTo the tierTo to set
	 */
	public void setTierTo(final BigDecimal tierTo) {
		this.tierTo = tierTo;
	}
	
	/**
	 * Gets the batch id.
	 *
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	
	/**
	 * Sets the batch id.
	 *
	 * @param batchId the batchId to set
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * Gets the rebate plan tier id.
	 *
	 * @return the rebatePlanTierId
	 */
	public String getRebatePlanTierId() {
		return rebatePlanTierId;
	}
	
	/**
	 * Sets the rebate plan tier id.
	 *
	 * @param rebatePlanTierId the rebatePlanTierId to set
	 */
	public void setRebatePlanTierId(final String rebatePlanTierId) {
		this.rebatePlanTierId = rebatePlanTierId;
	}
	
	/**
	 * Gets the free amount.
	 *
	 * @return the freeAmount
	 */
	public double getFreeAmount() {
		return freeAmount;
	}
	
	/**
	 * Sets the free amount.
	 *
	 * @param freeAmount the freeAmount to set
	 */
	public void setFreeAmount(final double freeAmount) {
		this.freeAmount = freeAmount;
	}
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the tier level.
	 *
	 * @return the tierLevel
	 */
	public String getTierLevel() {
		return tierLevel;
	}
	
	/**
	 * Sets the tier level.
	 *
	 * @param tierLevel the tierLevel to set
	 */
	public void setTierLevel(final String tierLevel) {
		this.tierLevel = tierLevel;
	}

        /**
	 * Gets the tier operator value.
	 *
	 * @return the tierOperatorValue
	 */
        public String getTierOperatorValue() {
            return tierOperatorValue;
        }

        /**
	 * Sets the tier operator.
	 *
	 * @param tierOperatorValue the tierOperator to set
	 */
        public void setTierOperatorValue(String tierOperatorValue) {
            this.tierOperatorValue = tierOperatorValue;
        }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getSecondaryRebatePlanNo() {
        return secondaryRebatePlanNo;
    }

    public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
        this.secondaryRebatePlanNo = secondaryRebatePlanNo;
    }

    public String getSecondaryRebatePlanName() {
        return secondaryRebatePlanName;
    }

    public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
        this.secondaryRebatePlanName = secondaryRebatePlanName;
    }

    public int getSecondaryRebatePlanSid() {
        return secondaryRebatePlanSid;
    }

    public void setSecondaryRebatePlanSid(int secondaryRebatePlanSid) {
        this.secondaryRebatePlanSid = secondaryRebatePlanSid;
    }

    public String getFormulaID() {
        return formulaID;
    }

    public void setFormulaID(String formulaID) {
        this.formulaID = formulaID;
    }

    public String getTierValueId() {
        return tierValueId;
    }

    public void setTierValueId(String tierValueId) {
        this.tierValueId = tierValueId;
    }
    
}
