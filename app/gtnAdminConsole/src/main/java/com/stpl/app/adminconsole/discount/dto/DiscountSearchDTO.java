package com.stpl.app.adminconsole.discount.dto;

import java.io.Serializable;
import java.util.Date;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;

 
/**
 * The Class DiscountSearchDTO.
 */
public class DiscountSearchDTO implements Serializable{
	
	public DiscountSearchDTO(){
		super();
	}
	/**
	 * The Serial version id
	 */
	private static final long serialVersionUID = 6226322364977961187L;

	/** The discount name. */
	private String discountName=ConstantsUtils.EMPTY;
	
	/** The discount no. */
	private String discountNo=ConstantsUtils.EMPTY;
	
	/** The discount desc. */
	private String discountDesc=ConstantsUtils.EMPTY;
	
	/** The available list. */
	private HelperDTO availableList=new HelperDTO();
	
	/** The created by. */
	private String createdBy=ConstantsUtils.EMPTY;
	
	/** The created date. */
	private Date createdDate;
        
        /** The rebateName. */
        private String rebateName=ConstantsUtils.EMPTY;
        
        /** The rebateNo. */
        private String rebateNo=ConstantsUtils.EMPTY;
        
        /** The RebateScheduleType. */
        private String rebateScheduleType=ConstantsUtils.EMPTY;
        
        /** The RebateProgramType. */
        private String rebateProgramType=ConstantsUtils.EMPTY;
        
        /** The RebateScheduleCategory. */
        private String rebateScheduleCategory=ConstantsUtils.EMPTY;
        
        /** The RebatePlanLevel. */
        private String rebatePlanLevel=ConstantsUtils.EMPTY;

	
        /** The RebateScheduleSid. */
        private int rebateScheduleSid;
        
          /** The deductionGroupSid. */
        private int deductionGroupSid;
        
           /** The version. */
        private int version;
        
             /** The udc2. */
        private String udc2=ConstantsUtils.EMPTY;
        
              /** The contractNo. */
        private String contractNo=ConstantsUtils.EMPTY;
        
          /** The contractName. */
        private String contractName=ConstantsUtils.EMPTY;
        
          /** The contractName. */
        private String rebateContractType=ConstantsUtils.EMPTY;
        
          /** The contractName. */
        private String tradingPartner=ConstantsUtils.EMPTY;
          /**
     * The item no.
     */
    private  String itemNo =ConstantsUtils.EMPTY;
     /**
     * The item Name .
     */
    private  String itemName  =ConstantsUtils.EMPTY;
     /**
     * The customer no.
     */
    private  String customerNo=ConstantsUtils.EMPTY;
     /**
     * The customer Name
     */
    private  String customerName =ConstantsUtils.EMPTY;
        
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
		return createdDate == null ? null : (Date) createdDate.clone();
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}
	
	/**
	 * Gets the available list.
	 *
	 * @return the available list
	 */
	public HelperDTO getAvailableList() {
		return availableList;
	}
	
	/**
	 * Sets the available list.
	 *
	 * @param availableList the new available list
	 */
	public void setAvailableList(final HelperDTO availableList) {
		this.availableList = availableList;
	}
	
	/**
	 * Gets the discount name.
	 *
	 * @return the discount name
	 */
	public String getDiscountName() {
		return discountName;
	}
	
	/**
	 * Sets the discount name.
	 *
	 * @param discountName the new discount name
	 */
	public void setDiscountName(final String discountName) {
		this.discountName = discountName;
	}
	
	/**
	 * Gets the discount no.
	 *
	 * @return the discount no
	 */
	public String getDiscountNo() {
		return discountNo;
	}
	
	/**
	 * Sets the discount no.
	 *
	 * @param discountNo the new discount no
	 */
	public void setDiscountNo(final String discountNo) {
		this.discountNo = discountNo;
	}
	
	/**
	 * Gets the discount desc.
	 *
	 * @return the discount desc
	 */
	public String getDiscountDesc() {
		return discountDesc;
	}
	
	/**
	 * Sets the discount desc.
	 *
	 * @param discountDesc the new discount desc
	 */
	public void setDiscountDesc(final String discountDesc) {
		this.discountDesc = discountDesc;
	}


    public String getRebateName() {
        return rebateName;
    }

    public void setRebateName(String rebateName) {
        this.rebateName = rebateName;
    }

    public String getRebateNo() {
        return rebateNo;
    }

    public void setRebateNo(String rebateNo) {
        this.rebateNo = rebateNo;
    }

      public String getRebatePlanLevel() {
        return rebatePlanLevel;
    }

    public void setRebatePlanLevel(String rebatePlanLevel) {
        this.rebatePlanLevel = rebatePlanLevel;
    }

    public String getRebateScheduleType() {
        return rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public String getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    public void setRebateScheduleCategory(String rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    public int getRebateScheduleSid() {
        return rebateScheduleSid;
    }

    public void setRebateScheduleSid(int rebateScheduleSid) {
        this.rebateScheduleSid = rebateScheduleSid;
    }

    public int getDeductionGroupSid() {
        return deductionGroupSid;
    }

    public void setDeductionGroupSid(int deductionGroupSid) {
        this.deductionGroupSid = deductionGroupSid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getRebateContractType() {
        return rebateContractType;
    }

    public void setRebateContractType(String contractType) {
        this.rebateContractType = contractType;
    }

    public String getTradingPartner() {
        return tradingPartner;
    }

    public void setTradingPartner(String tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(final String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }
  
    
}
