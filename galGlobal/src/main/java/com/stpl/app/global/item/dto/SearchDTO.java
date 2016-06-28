package com.stpl.app.global.item.dto;

import com.stpl.app.util.ConstantsUtils;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;


/**
 * The Class SearchDTO.
 */
public class SearchDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3584109928269363711L;

	/** The search item. */
	private SearchItem searchItem = new SearchItem();

	/** The item no. */
	@NotBlank(message = "The item No must not be empty")
	private String itemNo = HelperUtils.EMPTY;

	/** The item id. */
	private String itemId = HelperUtils.EMPTY;

	/** The item name. */
	private String itemName = HelperUtils.EMPTY;

	/** The item status. */
	private String itemStatus;

	/** The item start date. */
	private Date itemStartDate;

	/** The item end date. */
	private Date itemEndDate;

	/** The item qualifier. */
	private HelperDTO itemQualifier;

	/** The item identifier. */
	private String itemIdentifier = HelperUtils.EMPTY;

	/** The system id. */
	private String systemId = HelperUtils.EMPTY;

	/** The error messages. */
	private String errorMessages = HelperUtils.EMPTY;

	/** The manufacturer no. */
	private String manufacturerNo = HelperUtils.EMPTY;

	/** The brand. */
	private String brand;

	/** The item type. */
	private Integer itemType;

	/** The item desc. */
	private String itemDesc = HelperUtils.EMPTY;

	/** The identifier. */
	private String identifier = HelperUtils.EMPTY;

	/** The item type desc. */
	private String itemTypeDesc = HelperUtils.EMPTY;

	/** The item status desc. */
	private String itemStatusDesc = HelperUtils.EMPTY;

	/** The item irt qualifier name. */
	private HelperDTO itemIrtQualifierName;

	/** The qualifier id. */
	private String qualifierId = HelperUtils.EMPTY;;

	/** The manufacturer id. */
	private String manufacturerId = HelperUtils.EMPTY;

	/** The search flag. */
	private Boolean searchFlag;

	/** The qualifier flag. */
	private Boolean qualifierFlag;

	/** The identifier flag. */
	private Boolean identifierFlag;

	/** The success message. */
	private String successMessage;

	/** The record lock status. */
	private String recordLockStatus;
        
        /** The item irt qualifier name. */
	private HelperDTO itemIrtQualifierNameDDLB = new HelperDTO(ConstantsUtils.SELECT_ONE);
        
        /** The brand. */
	private HelperDTO brandDdlb = new HelperDTO(ConstantsUtils.SELECT_ONE);
        
        /** Therapeutic class. */
	private String therapeuticClass  = HelperUtils.EMPTY;
        
        /** The form. */
	private Integer form;
	
	/** The strength. */
	private Integer strength;
        
        /** The ndc9. */
	private HelperDTO ndc9 = new HelperDTO(ConstantsUtils.SELECT_ONE);
        
         /** The ndc8. */
	private HelperDTO ndc8 = new HelperDTO(ConstantsUtils.SELECT_ONE);
       

    public HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(final HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public HelperDTO getItemIrtQualifierNameDDLB() {
        return itemIrtQualifierNameDDLB;
    }

    public void setItemIrtQualifierNameDDLB(final HelperDTO itemIrtQualifierNameDDLB) {
        this.itemIrtQualifierNameDDLB = itemIrtQualifierNameDDLB;
    }

	/**
	 * Gets the search item.
	 *
	 * @return the search item
	 */
	public SearchItem getSearchItem() {
		return searchItem;
	}

	/**
	 * Sets the search item.
	 *
	 * @param searchItem
	 *            the search item
	 */
	public void setSearchItem(final SearchItem searchItem) {
		this.searchItem = searchItem;
	}

	/**
	 * Gets the item no.
	 *
	 * @return the item no
	 */
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * Sets the item no.
	 *
	 * @param itemNo
	 *            the item no
	 */
	public void setItemNo(final String itemNo) {
		this.itemNo = itemNo;
	}

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
	 * @param itemId
	 *            the item id
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the item name.
	 *
	 * @param itemName
	 *            the item name
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the item status.
	 *
	 * @return the item status
	 */
	public String getItemStatus() {
		return itemStatus;
	}

	/**
	 * Sets the item status.
	 *
	 * @param itemStatus
	 *            the item status
	 */
	public void setItemStatus(final String itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * Gets the item start date.
	 *
	 * @return the item start date
	 */
	public Date getItemStartDate() {
		return itemStartDate;
	}

	/**
	 * Sets the item start date.
	 *
	 * @param itemStartDate
	 *            the item start date
	 */
	public void setItemStartDate(final Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}

	/**
	 * Gets the item end date.
	 *
	 * @return the item end date
	 */
	public Date getItemEndDate() {
		return itemEndDate;
	}

	/**
	 * Sets the item end date.
	 *
	 * @param itemEndDate
	 *            the item end date
	 */
	public void setItemEndDate(final Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}

	/**
	 * Gets the item qualifier.
	 *
	 * @return the item qualifier
	 */
	public HelperDTO getItemQualifier() {
		return itemQualifier;
	}

	/**
	 * Sets the item qualifier.
	 *
	 * @param itemQualifier
	 *            the item qualifier
	 */
	public void setItemQualifier(final HelperDTO itemQualifier) {
		this.itemQualifier = itemQualifier;
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
	 * @param itemIdentifier
	 *            the item identifier
	 */
	public void setItemIdentifier(final String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	/**
	 * Gets the system id.
	 *
	 * @return the system id
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * Sets the system id.
	 *
	 * @param systemId
	 *            the system id
	 */
	public void setSystemId(final String systemId) {
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
	 * @param errorMessages
	 *            the error messages
	 */
	public void setErrorMessages(final String errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * Gets the manufacturer no.
	 *
	 * @return the manufacturer no
	 */
	public String getManufacturerNo() {
		return manufacturerNo;
	}

	/**
	 * Sets the manufacturer no.
	 *
	 * @param manufacturerNo
	 *            the manufacturer no
	 */
	public void setManufacturerNo(final String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
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
	 * @param brand
	 *            the brand
	 */
	public void setBrand(final String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the item type.
	 *
	 * @return the item type
	 */
	public Integer getItemType() {
		return itemType;
	}

	/**
	 * Sets the item type.
	 *
	 * @param itemType
	 *            the item type
	 */
	public void setItemType(final Integer itemType) {
		this.itemType = itemType;
	}

	/**
	 * Gets the item desc.
	 *
	 * @return the item desc
	 */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
	 * Sets the item desc.
	 *
	 * @param itemDesc
	 *            the item desc
	 */
	public void setItemDesc(final String itemDesc) {
		this.itemDesc = itemDesc;
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
	 * @param identifier
	 *            the identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the item type desc.
	 *
	 * @return the item type desc
	 */
	public String getItemTypeDesc() {
		return itemTypeDesc;
	}

	/**
	 * Sets the item type desc.
	 *
	 * @param itemTypeDesc
	 *            the item type desc
	 */
	public void setItemTypeDesc(final String itemTypeDesc) {
		this.itemTypeDesc = itemTypeDesc;
	}

	/**
	 * Gets the item status desc.
	 *
	 * @return the item status desc
	 */
	public String getItemStatusDesc() {
		return itemStatusDesc;
	}

	/**
	 * Sets the item status desc.
	 *
	 * @param itemStatusDesc
	 *            the item status desc
	 */
	public void setItemStatusDesc(final String itemStatusDesc) {
		this.itemStatusDesc = itemStatusDesc;
	}

	/**
	 * Gets the item irt qualifier name.
	 *
	 * @return the item irt qualifier name
	 */
	public HelperDTO getItemIrtQualifierName() {
		return itemIrtQualifierName;
	}

	/**
	 * Sets the item irt qualifier name.
	 *
	 * @param itemIrtQualifierName
	 *            the item irt qualifier name
	 */
	public void setItemIrtQualifierName(final HelperDTO itemIrtQualifierName) {
		this.itemIrtQualifierName = itemIrtQualifierName;
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
	 * @param qualifierId
	 *            the qualifier id
	 */
	public void setQualifierId(final String qualifierId) {
		this.qualifierId = qualifierId;
	}

	/**
	 * Gets the manufacturer id.
	 *
	 * @return the manufacturer id
	 */
	public String getManufacturerId() {
		return manufacturerId;
	}

	/**
	 * Sets the manufacturer id.
	 *
	 * @param manufacturerId
	 *            the manufacturer id
	 */
	public void setManufacturerId(final String manufacturerId) {
		this.manufacturerId = manufacturerId;
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
	 * @param searchFlag
	 *            the search flag
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
	 * @param qualifierFlag
	 *            the qualifier flag
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
	 * @param identifierFlag
	 *            the identifier flag
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
	 * @param successMessage
	 *            the success message
	 */
	public void setSuccessMessage(final String successMessage) {
		this.successMessage = successMessage;
	}

	/**
	 * Gets the record lock status.
	 *
	 * @return the record lock status
	 */
	public String getRecordLockStatus() {
		return recordLockStatus;
	}

	/**
	 * Sets the record lock status.
	 *
	 * @param recordLockStatus
	 *            the record lock status
	 */
	public void setRecordLockStatus(final String recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	/**
	 * Gets the search flag.
	 *
	 * @return the search flag
	 */
	public Boolean getSearchFlag() {
		return searchFlag;
	}

	/**
	 * Gets the qualifier flag.
	 *
	 * @return the qualifier flag
	 */
	public Boolean getQualifierFlag() {
		return qualifierFlag;
	}

	/**
	 * Gets the identifier flag.
	 *
	 * @return the identifier flag
	 */
	public Boolean getIdentifierFlag() {
		return identifierFlag;
	}

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public HelperDTO getNdc9() {
        return ndc9;
    }

    public void setNdc9(HelperDTO ndc9) {
        this.ndc9 = ndc9;
    }

    public HelperDTO getNdc8() {
        return ndc8;
    }

    public void setNdc8(HelperDTO ndc8) {
        this.ndc8 = ndc8;
    }
        

}
