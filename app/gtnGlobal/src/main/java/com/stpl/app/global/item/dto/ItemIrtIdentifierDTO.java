package com.stpl.app.global.item.dto;
import com.stpl.app.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;


/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 * @author arulmurugan
 */

public class ItemIrtIdentifierDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -849365522985110991L;
	
	/** The item system id. */
	private int itemSystemId;
	
          /** The item irt qualifier name. */
	private com.stpl.app.util.HelperDTO itemIrtQualifierNameDDLB=new com.stpl.app.util.HelperDTO(0,Constants.SELECT_ONE);
        
	/** The item identifier. */
	private String itemIdentifier=HelperUtils.EMPTY;
	
	/** The item irt qualifier id. */
	private int itemIrtQualifierId;
	
	/** The item irt qualifier name. */
	private String itemIrtQualifierName=HelperUtils.EMPTY;
	
	/** The identifier status. */
	private HelperDTO identifierStatus= new HelperDTO(0, Constants.SELECT_ONE);
        
        /** The identifier status. */
	private String identifierStatusView;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The entity code. */
	private String entityCode=HelperUtils.EMPTY;
        
        	/** The entity code. */
	private String entityCodeName=HelperUtils.EMPTY;
        
        
        private String entityCodeSid = HelperUtils.EMPTY;
	
	/** The created by. */
	private String createdBy;
	
	/** The created date. */
	private Date createdDate;
	
	/** The modified by. */
	private String modifiedBy=HelperUtils.EMPTY;
	
	/** The modified date. */
	private Date modifiedDate;
	
	/** The batch id. */
	private String batchId=HelperUtils.EMPTY;
	
	/** The inbound status. */
	private String inboundStatus;
	
	/** The item irt identifier indicator. */
	private boolean itemIrtIdentifierIndicator;
	
	/** The item id. */
	private String itemId=HelperUtils.EMPTY;
	
	/** The view start date. */
	private String viewStartDate=HelperUtils.EMPTY;
	
	/** The view end date. */
	private String viewEndDate=HelperUtils.EMPTY;
	
	/** The irt identifier system id. */
	private int irtIdentifierSystemId;
	
	/** The checkbox. */
	private Boolean checkbox=false;
        
        private boolean recordLockStatus;
        	
        private String identifierStatusDdlb;
        
        private int createdByValue;
        
         private String identifierCodeQualifier = StringUtils.EMPTY;
         private String identifierCodeQualifierName = StringUtils.EMPTY;
         private String effectiveDates;
         private String notes = StringUtils.EMPTY;
        

    public int getCreatedByValue() {
        return createdByValue;
    }

    public void setCreatedByValue(int createdByValue) {
        this.createdByValue = createdByValue;
    }

    public int getModifiedByValue() {
        return modifiedByValue;
    }

    public void setModifiedByValue(int modifiedByValue) {
        this.modifiedByValue = modifiedByValue;
    }
        private int modifiedByValue;
       
	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public String getItemId() {
		return itemId;
	}
	
	/**
	 * Sets the item id.
	 *
	 * @param itemId the item id
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
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
	 * @param checkbox the checkbox
	 */
	public void setCheckbox(final Boolean checkbox) {
		this.checkbox = checkbox;
	}
	
	/**
	 * Gets the item system id.
	 *
	 * @return the item system id
	 */
	public int getItemSystemId() {
		return itemSystemId;
	}
	
	/**
	 * Sets the item system id.
	 *
	 * @param itemSystemId the item system id
	 */
	public void setItemSystemId(final int itemSystemId) {
		this.itemSystemId = itemSystemId;
	}
	
	/**
	 * Gets the item identifier.
	 *
	 * @return the item identifier
	 */
	public String getItemIdentifier() {
		return itemIdentifier;
	}
	
	/**
	 * Sets the item identifier.
	 *
	 * @param itemIdentifier the item identifier
	 */
	public void setItemIdentifier(final String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}
			
	/**
	 * Gets the item irt qualifier name.
	 *
	 * @return the item irt qualifier name
	 */
	public String getItemIrtQualifierName() {
		return itemIrtQualifierName;
	}
	
	/**
	 * Sets the item irt qualifier name.
	 *
	 * @param itemIrtQualifierName the item irt qualifier name
	 */
	public void setItemIrtQualifierName(final String itemIrtQualifierName) {
		this.itemIrtQualifierName = itemIrtQualifierName;
	}
	
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the entity code.
	 *
	 * @return the entity code
	 */
	public String getEntityCode() {
		return entityCode;
	}
	
	/**
	 * Sets the entity code.
	 *
	 * @param entityCode the entity code
	 */
	public void setEntityCode(final String entityCode) {
		this.entityCode = entityCode;
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
	 * @param createdBy the created by
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
	 * @param createdDate the created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
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
	 * @param modifiedBy the modified by
	 */
	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	 * @param modifiedDate the modified date
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	 * @param batchId the batch id
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
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
	 * @param inboundStatus the inbound status
	 */
	public void setInboundStatus(final String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}
	
	/**
	 * Checks if is item irt identifier indicator.
	 *
	 * @return true, if checks if is item irt identifier indicator
	 */
	public boolean isItemIrtIdentifierIndicator() {
		return itemIrtIdentifierIndicator;
	}
	
	/**
	 * Sets the item irt identifier indicator.
	 *
	 * @param itemIrtIdentifierIndicator the item irt identifier indicator
	 */
	public void setItemIrtIdentifierIndicator(final boolean itemIrtIdentifierIndicator) {
		this.itemIrtIdentifierIndicator = itemIrtIdentifierIndicator;
	}
	
	/**
	 * Gets the view start date.
	 *
	 * @return the view start date
	 */
	public String getViewStartDate() {
		return viewStartDate;
	}
	
	/**
	 * Sets the view start date.
	 *
	 * @param viewStartDate the view start date
	 */
	public void setViewStartDate(final String viewStartDate) {
		this.viewStartDate = viewStartDate;
	}
	
	/**
	 * Gets the view end date.
	 *
	 * @return the view end date
	 */
	public String getViewEndDate() {
		return viewEndDate;
	}
	
	/**
	 * Sets the view end date.
	 *
	 * @param viewEndDate the view end date
	 */
	public void setViewEndDate(final String viewEndDate) {
		this.viewEndDate = viewEndDate;
	}
	
	/**
	 * Gets the irt identifier system id.
	 *
	 * @return the irt identifier system id
	 */
	public int getIrtIdentifierSystemId() {
		return irtIdentifierSystemId;
	}
	
	/**
	 * Sets the irt identifier system id.
	 *
	 * @param irtIdentifierSystemId the irt identifier system id
	 */
	public void setIrtIdentifierSystemId(final int irtIdentifierSystemId) {
		this.irtIdentifierSystemId = irtIdentifierSystemId;
	}

    public String getIdentifierStatusView() {
        return identifierStatusView;
    }

    public void setIdentifierStatusView(String identifierStatusView) {
        this.identifierStatusView = identifierStatusView;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getEntityCodeSid() {
        return entityCodeSid;
    }

    public void setEntityCodeSid(String entityCodeSid) {
        this.entityCodeSid = entityCodeSid;
    }

    public String getIdentifierStatusDdlb() {
        return identifierStatusDdlb;
    }

    public void setIdentifierStatusDdlb(String identifierStatusDdlb) {
        this.identifierStatusDdlb = identifierStatusDdlb;
    }

    public String getEntityCodeName() {
        return entityCodeName;
    }

    public void setEntityCodeName(String entityCodeName) {
        this.entityCodeName = entityCodeName;
    }

    public String getIdentifierCodeQualifier() {
        return identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }

    public String getIdentifierCodeQualifierName() {
        return identifierCodeQualifierName;
    }

    public void setIdentifierCodeQualifierName(String identifierCodeQualifierName) {
        this.identifierCodeQualifierName = identifierCodeQualifierName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEffectiveDates() {
        return effectiveDates;
    }

    public void setEffectiveDates(String effectiveDates) {
        this.effectiveDates = effectiveDates;
    }

    public com.stpl.app.util.HelperDTO getItemIrtQualifierNameDDLB() {
        return itemIrtQualifierNameDDLB;
    }

    public void setItemIrtQualifierNameDDLB(com.stpl.app.util.HelperDTO itemIrtQualifierNameDDLB) {
        this.itemIrtQualifierNameDDLB = itemIrtQualifierNameDDLB;
    }

    public int getItemIrtQualifierId() {
        return itemIrtQualifierId;
    }

    public void setItemIrtQualifierId(int itemIrtQualifierId) {
        this.itemIrtQualifierId = itemIrtQualifierId;
    }

    public HelperDTO getIdentifierStatus() {
        return identifierStatus;
    }

    public void setIdentifierStatus(HelperDTO identifierStatus) {
        this.identifierStatus = identifierStatus;
    }

           
}
