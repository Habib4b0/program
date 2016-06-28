package com.stpl.app.global.rebateschedule.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;


/**
 * The Class for ItemDetailsDTO.
 */
public class ItemDetailsDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5268991086029971185L;

	/** The ifp system id. */
	private String ifpSystemId;
	
	/** The item no. */
	private String itemNo;
	
	/** The item name. */
	private String itemName;
	
	/** The item type. */
	private String itemType;
        
        private String itemId;
	
	/** The rebate amount. */
	private String rebateAmount = "0";
	
	/** The revision date. */
	private Date revisionDate;
	
	/** The item system id. */
	private String itemSystemId;
	
	/** The formula id. */
	private String formulaId   = StringUtils.EMPTY;
	
	/** The formula name. */
	private String formulaName = StringUtils.EMPTY;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The check box. */
	private Boolean checkbox = false;
	
	/** The attached status. */
	private HelperDTO attachedStatus;
        
        /** attachedStatusValue. */
	private String attachedStatusValue = StringUtils.EMPTY;
	
	/** The formula type. */
	private String formulaType;
	
	/** The formula method id. */
	private String formulaMethodId = StringUtils.EMPTY;
	
	/** The formula no. */
	private String formulaNo = StringUtils.EMPTY;
	
	/** The rebate plan name. */
	private String rebatePlanName ;
	
        /** The rebate plan name. */
	private String rebatePlanSystemId ;
        
	/** The attached date. */
	private Date attachedDate;        
        
        private String rebatePlanNo = StringUtils.EMPTY;
        
        private String deductionCalendarNo = StringUtils.EMPTY;;        
        
        private String deductionCalendarName = StringUtils.EMPTY;
        
        private String deductionSystemId = StringUtils.EMPTY;
        
        /** The formula no. */
	private String formulaSystemId = StringUtils.EMPTY;
        
        private int docDetailsId;
	private String documentName;
	private String dateAdded;
	private String userName;
	private String documentFullPath;
        private int userId;
        
        private String netSalesFormulaNo = StringUtils.EMPTY;;        
        
        private String netSalesFormulaName = StringUtils.EMPTY;
        
        private String systemID = StringUtils.EMPTY;
        private String netSalesSystemId=StringUtils.EMPTY;
        private String calculationSystemId=StringUtils.EMPTY;
        private String evaluationSystemId=StringUtils.EMPTY;
    
        
        private String netSalesRule = StringUtils.EMPTY;
        private String evaluationRule = StringUtils.EMPTY;
        private String evaluationRuleBundle = StringUtils.EMPTY;
        private String calculationRule = StringUtils.EMPTY;
        private String calculationRuleBundle = StringUtils.EMPTY;
        
        


    public int getDocDetailsId() {
        return docDetailsId;
    }

    public void setDocDetailsId(int docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   
	/** The unique date. */
	private Date uniqueDate;
        
        /** The unique date. */
	private String bundleNo = StringUtils.EMPTY;
        
        private int tempRSDetailsSystemId;
         /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;

	/**
	 * Instantiates a new item details dto.	 
	 * @param ifpSystemId the ifp system id
	 * @param itemNo the item no
	 * @param itemName the item name
	 * @param itemType the item type
	 * @param itemSystemId the item system id
	 * @param uniqueDate the unique date
	 */
	public ItemDetailsDTO(final String ifpSystemId,final  String itemNo,final  String itemName,
			final String itemType, final String itemSystemId,final  Date uniqueDate) {
		super();
		this.ifpSystemId = ifpSystemId;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemSystemId = itemSystemId;
		this.uniqueDate = uniqueDate;
	}

	/**
	 * Instantiates a new item details dto.
	 */
	public ItemDetailsDTO() {
		/**
		 * Empty Constructor.
		 */
		
	}

	/**
	 * Gets the ifp system id.
	 *
	 * @return the ifpSystemId
	 */
	public String getIfpSystemId() {
		return ifpSystemId;
	}

	/**
	 * Sets the ifp system id.
	 *
	 * @param ifpSystemId the ifpSystemId to set
	 */
	public void setIfpSystemId(final String ifpSystemId) {
		this.ifpSystemId = ifpSystemId;
	}

	/**
	 * Gets the item no.
	 *
	 * @return the itemNo
	 */
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * Sets the item no.
	 *
	 * @param itemNo the itemNo to set
	 */
	public void setItemNo(final String itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * Gets the item name.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the item name.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the item type.
	 *
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * Sets the item type.
	 *
	 * @param itemType the itemType to set
	 */
	public void setItemType(final String itemType) {
		this.itemType = itemType;
	}

	/**
	 * Gets the rebate amount.
	 *
	 * @return the rebateAmount
	 */
	public String getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * Sets the rebate amount.
	 *
	 * @param rebateAmount the rebateAmount to set
	 */
	public void setRebateAmount(final String rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	/**
	 * Gets the revision date.
	 *
	 * @return the revisionDate
	 */
	public Date getRevisionDate() {
		return revisionDate;
	}

	/**
	 * Sets the revision date.
	 *
	 * @param revisionDate the revisionDate to set
	 */
	public void setRevisionDate(final Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	/**
	 * Gets the item system id.
	 *
	 * @return the itemSystemId
	 */
	public String getItemSystemId() {
		return itemSystemId;
	}

	/**
	 * Sets the item system id.
	 *
	 * @param itemSystemId the itemSystemId to set
	 */
	public void setItemSystemId(final String itemSystemId) {
		this.itemSystemId = itemSystemId;
	}

	/**
	 * Gets the formula id.
	 *
	 * @return the formulaId
	 */
	public String getFormulaId() {
		return formulaId;
	}

	/**
	 * Sets the formula id.
	 *
	 * @param formulaId the formulaId to set
	 */
	public void setFormulaId(final String formulaId) {
		this.formulaId = formulaId;
	}

	/**
	 * Gets the formula name.
	 *
	 * @return the formulaName
	 */
	public String getFormulaName() {
		return formulaName;
	}

	/**
	 * Sets the formula name.
	 *
	 * @param formulaName the formulaName to set
	 */
	public void setFormulaName(final String formulaName) {
		this.formulaName = formulaName;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the checkbox.
	 *
	 * @return the checkbox
	 */
	public Boolean getCheckbox() {
		return checkbox;
	}

	/**
	 * Sets the checkbox.
	 *
	 * @param checkbox the checkbox to set
	 */
	public void setCheckbox(final Boolean checkbox) {
		this.checkbox = checkbox;
	}

	/**
	 * Gets the attached status.
	 *
	 * @return the attachedStatus
	 */
	public HelperDTO getAttachedStatus() {
		return attachedStatus;
	}

	/**
	 * Sets the attached status.
	 *
	 * @param attachedStatus the attachedStatus to set
	 */
	public void setAttachedStatus(final HelperDTO attachedStatus) {
		this.attachedStatus = attachedStatus;
	}

	/**
	 * Gets the formula type.
	 *
	 * @return the formulaType
	 */
	public String getFormulaType() {
		return formulaType;
	}

	/**
	 * Sets the formula type.
	 *
	 * @param formulaType the formulaType to set
	 */
	public void setFormulaType(final String formulaType) {
		this.formulaType = formulaType;
	}

	/**
	 * Gets the formula method id.
	 *
	 * @return the formulaMethodId
	 */
	public String getFormulaMethodId() {
		return formulaMethodId;
	}

	/**
	 * Sets the formula method id.
	 *
	 * @param formulaMethodId the formulaMethodId to set
	 */
	public void setFormulaMethodId(final String formulaMethodId) {
		this.formulaMethodId = formulaMethodId;
	}

	/**
	 * Gets the formula no.
	 *
	 * @return the formulaNo
	 */
	public String getFormulaNo() {
		return formulaNo;
	}

	/**
	 * Sets the formula no.
	 *
	 * @param formulaNo the formulaNo to set
	 */
	public void setFormulaNo(final String formulaNo) {
		this.formulaNo = formulaNo;
	}

	/**
	 * Gets the rebate plan name.
	 *
	 * @return the rebatePlanName
	 */
	public String getRebatePlanName() {
		return rebatePlanName;
	}

	/**
	 * Sets the rebate plan name.
	 *
	 * @param rebatePlanName the rebatePlanName to set
	 */
	public void setRebatePlanName(final String rebatePlanName) {
		this.rebatePlanName = rebatePlanName;
	}

	/**
	 * Gets the attached date.
	 *
	 * @return the attachedDate
	 */
	public Date getAttachedDate() {
		return attachedDate;
	}

	/**
	 * Sets the attached date.
	 *
	 * @param attachedDate the attachedDate to set
	 */
	public void setAttachedDate(final Date attachedDate) {
		this.attachedDate = attachedDate;
	}

	/**
	 * Gets the unique date.
	 *
	 * @return the uniqueDate
	 */
	public Date getUniqueDate() {
		return uniqueDate;
	}

	/**
	 * Sets the unique date.
	 *
	 * @param uniqueDate the uniqueDate to set
	 */
	public void setUniqueDate(final Date uniqueDate) {
		this.uniqueDate = uniqueDate;
	}

    public int getTempRSDetailsSystemId() {
        return tempRSDetailsSystemId;
    }

    public void setTempRSDetailsSystemId(int tempRSDetailsSystemId) {
        this.tempRSDetailsSystemId = tempRSDetailsSystemId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }
    public String getDocumentFullPath() {
        return documentFullPath;
    }
    
    public void setDocumentFullPath(String documentFullPath) {
        this.documentFullPath = documentFullPath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }	

    public String getAttachedStatusValue() {
        return attachedStatusValue;
    }

    public void setAttachedStatusValue(String attachedStatusValue) {
        this.attachedStatusValue = attachedStatusValue;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getDeductionCalendarNo() {
        return deductionCalendarNo;
    }

    public void setDeductionCalendarNo(String deductionCalendarNo) {
        this.deductionCalendarNo = deductionCalendarNo;
    }

    public String getDeductionCalendarName() {
        return deductionCalendarName;
    }

    public void setDeductionCalendarName(String deductionCalendarName) {
        this.deductionCalendarName = deductionCalendarName;
    }

    public String getRebatePlanSystemId() {
        return rebatePlanSystemId;
    }

    public void setRebatePlanSystemId(String rebatePlanSystemId) {
        this.rebatePlanSystemId = rebatePlanSystemId;
    }

    public String getFormulaSystemId() {
        return formulaSystemId;
    }

    public void setFormulaSystemId(String formulaSystemId) {
        this.formulaSystemId = formulaSystemId;
    }

    public String getDeductionSystemId() {
        return deductionSystemId;
    }

    public void setDeductionSystemId(String deductionSystemId) {
        this.deductionSystemId = deductionSystemId;
    }

    public String getNetSalesFormulaNo() {
        return netSalesFormulaNo;
    }

    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        this.netSalesFormulaNo = netSalesFormulaNo;
    }

    public String getNetSalesFormulaName() {
        return netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        this.netSalesFormulaName = netSalesFormulaName;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getNetSalesRule() {
        return netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        this.netSalesRule = netSalesRule;
    }

    public String getEvaluationRule() {
        return evaluationRule;
    }

    public void setEvaluationRule(String evaluationRule) {
        this.evaluationRule = evaluationRule;
    }

    public String getEvaluationRuleBundle() {
        return evaluationRuleBundle;
    }

    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        this.evaluationRuleBundle = evaluationRuleBundle;
    }

    public String getCalculationRule() {
        return calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        this.calculationRule = calculationRule;
    }

    public String getCalculationRuleBundle() {
        return calculationRuleBundle;
    }

    public void setCalculationRuleBundle(String calculationRuleBundle) {
        this.calculationRuleBundle = calculationRuleBundle;
    }

    public String getNetSalesSystemId() {
        return netSalesSystemId;
    }

    public void setNetSalesSystemId(String netSalesSystemId) {
        this.netSalesSystemId = netSalesSystemId;
    }

    public String getCalculationSystemId() {
        return calculationSystemId;
    }

    public void setCalculationSystemId(String calculationSystemId) {
        this.calculationSystemId = calculationSystemId;
    }

    public String getEvaluationSystemId() {
        return evaluationSystemId;
    }

    public void setEvaluationSystemId(String evaluationSystemId) {
        this.evaluationSystemId = evaluationSystemId;
    }
    
}
