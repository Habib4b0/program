package com.stpl.app.contract.dashboard.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * This class used for Contract Memeber .
 *
 * @author gopinath
 */
public class ContractMember implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -792290752533943296L;
    /**
     * Variable used for name field.
     */
    private String name = StringUtils.EMPTY;
    /**
     * Variable used for categorize contract member.
     */
    private String category = StringUtils.EMPTY;
    /**
     * Variable used for member unique id.
     */
    private String memberId = StringUtils.EMPTY;
    /**
     * Variable used for member no.
     */
    private String memberNo = StringUtils.EMPTY;
    
    /**
     * Variable used for member Type
     */
    private String memberType=StringUtils.EMPTY;
    /**
     * Variable used for unique System id.
     */
    private int systemId;
    /**
     * Variable used for Contract id.
     */
    private int contractId;
    /**
     * Variable used for CFPID.
     */
    private int cfpId;
    /**
     * Variable used for CFPID.
     */
    private int cfpContractId;
    /**
     * Variable used for IFPID.
     */
    private int ifpId;
    /**
     * Variable used for IFPID.
     */
    private int ifpContractId;
    /**
     * Variable used for PSID.
     */
    private int psId;
    /**
     * Variable used for PSID.
     */
    private int psContractId;
    /**
     * Variable used for RSID.
     */
    private int rsId;
    /**
     * Variable used for INTERNAL ID.
     */
    private int internalId;
    /**
     * FLAG used for ChildrenAllowed value.
     */
    private boolean childrenAllowed;
    /**
     * Variable used for LEVEL VALUE.
     */
    private int level;
    /**
     * Variable used for PARENT1.
     */
    private ContractMember parent1;
    /**
     * Variable used for PARENT2.
     */
    private ContractMember parent2;
    /**
     * Variable used for PARENT3.
     */
    private ContractMember parent3;
    /**
     * Variable used for PARENT4.
     */
    private ContractMember parent4;
    /**
     * List value for ContractMember named as sub.
     */
    private List<ContractMember> sub;
    /**
     * Static Final Variable LEVEL1.
     */
    public static final int LEVEL1 = 1;
    /**
     * Static Final Variable LEVEL2.
     */
    public static final int LEVEL2 = NumericConstants.TWO;
    /**
     * Static Final Variable LEVEL3.
     */
    public static final int LEVEL3 = NumericConstants.THREE;
    /**
     * Static Final Variable LEVEL4.
     */
    public static final int LEVEL4 = NumericConstants.FOUR;
    /**
     * Static Final Variable LEVEL5.
     */
    public static final int LEVEL5 = 5;

    private int modelSysId;
    
    private boolean contractChild=false;
    /**
     * Start date
     */
    private Date startDate;
    /**
     * End date
     */
    private Date endDate;
    
	/**
     * Default Constractor.
     */
    public ContractMember() {
        // default constractor
    }

    /**
     * Constructor for ContractMember specify following final fields .
     *
     * @param internalId the internal id
     * @param name the name
     * @param memberId the member id
     * @param memberNo the member no
     * @param category the category
     * @param childrenAllowed the children allowed
     */
    public ContractMember(final int internalId,final int modelSysId, final String name, final String memberId, final String memberNo, final String category, final boolean childrenAllowed) {
        this.internalId = internalId;
        this.modelSysId=modelSysId;
        this.name = name;
        this.category = category;
        this.memberId = memberId;
        this.memberNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = new ArrayList<>();
    }

    
    /**
     * Constructor for ContractMember specify following final fields .
     *
     * @param internalId the internal id
     * @param name the name
     * @param memberId the member id
     * @param memberNo the member no
     * @param category the category
     * @param childrenAllowed the children allowed
     */
    public ContractMember(final int internalId,final int modelSysId, final String name, final String memberId, final String memberNo,final String memberType, final String category, final boolean childrenAllowed,final Date startDate,final Date endDate) {
        this.internalId = internalId;
        this.modelSysId=modelSysId;
        this.name = name;
        this.category = category;
        this.memberId = memberId;
        this.memberNo = memberNo;
        this.memberType=memberType;
        this.childrenAllowed = childrenAllowed;
        this.sub = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    /**
     * Constructor for ContractMember specify following final fields .
     *
     * @param systemId the system id,
     * @param name the name
     * @param memberId the member id
     * @param memberNo the member no
     * @param category the category
     * @param childrenAllowed the children allowed
     * @param level the level
     */
    public ContractMember(final int systemId, final String name, final String memberId, final String memberNo, final String category, final boolean childrenAllowed, final int level) {
        this.name = name;
        this.category = category;
        this.systemId = systemId;
        this.memberId = memberId;
        this.memberNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.level = level;
    }

    /**
     * Constructor for ContractMember specify following final fields .
     *
     * @param systemId the system id
     * @param name the name
     * @param memberId the member id
     * @param memberNo the member no
     * @param category the category
     * @param childrenAllowed the children allowed
     * @param subList the sub list
     */
    public ContractMember(final int systemId, final String name, final String memberId, final String memberNo, final String category, final boolean childrenAllowed, final List<ContractMember> subList) {
        this.name = name;
        this.category = category;
        this.systemId = systemId;
        this.memberId = memberId;
        this.memberNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = subList;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return systemId + level;
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if equals
     */
    @Override
    public boolean equals(final Object obj) {

        return obj == null ? false : toString().equals(obj.toString());

    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name + category + systemId + memberId + memberNo + level;
    }

    
    
    public int getModelSysId() {
		return modelSysId;
	}

	public void setModelSysId(int modelSysId) {
		this.modelSysId = modelSysId;
	}

    
    
    public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**
     * Gets the contract id.
     *
     * @return the contract id
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * Sets the contract id.
     *
     * @param contractId the contract id
     */
    public void setContractId(final int contractId) {
        this.contractId = contractId;
    }

    /**
     * Gets the cfp id.
     *
     * @return the cfp id
     */
    public int getCfpId() {
        return cfpId;
    }

    /**
     * Sets the cfp id.
     *
     * @param cfpId the cfp id
     */
    public void setCfpId(final int cfpId) {
        this.cfpId = cfpId;
    }

    /**
     * Gets the ifp id.
     *
     * @return the ifp id
     */
    public int getIfpId() {
        return ifpId;
    }

    /**
     * Sets the ifp id.
     *
     * @param ifpId the ifp id
     */
    public void setIfpId(final int ifpId) {
        this.ifpId = ifpId;
    }

    /**
     * Gets the ps id.
     *
     * @return the ps id
     */
    public int getPsId() {
        return psId;
    }

    /**
     * Sets the ps id.
     *
     * @param psId the ps id
     */
    public void setPsId(final int psId) {
        this.psId = psId;
    }

    /**
     * Gets the rs id.
     *
     * @return the rs id
     */
    public int getRsId() {
        return rsId;
    }

    /**
     * Sets the rs id.
     *
     * @param rsId the rs id
     */
    public void setRsId(final int rsId) {
        this.rsId = rsId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * Gets the member id.
     *
     * @return the member id
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Sets the member id.
     *
     * @param memberId the member id
     */
    public void setMemberId(final String memberId) {
        this.memberId = memberId;
    }

    /**
     * Gets the member no.
     *
     * @return the member no
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * Sets the member no.
     *
     * @param memberNo the member no
     */
    public void setMemberNo(final String memberNo) {
        this.memberNo = memberNo;
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
     * Checks if is children allowed.
     *
     * @return true, if checks if is children allowed
     */
    public boolean isChildrenAllowed() {
        return childrenAllowed;
    }

    /**
     * Sets the children allowed.
     *
     * @param childrenAllowed the children allowed
     */
    public void setChildrenAllowed(final boolean childrenAllowed) {
        this.childrenAllowed = childrenAllowed;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level.
     *
     * @param level the level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Gets the sub.
     *
     * @return the sub
     */
    public List<ContractMember> getSub() {
        return sub;
    }

    /**
     * Sets the sub.
     *
     * @param sub the sub
     */
    public void setSub(final List<ContractMember> sub) {
        this.sub = sub;
    }

    /**
     * Gets the parent1.
     *
     * @return the parent1
     */
    public ContractMember getParent1() {
        return parent1;
    }

    /**
     * Sets the parent1.
     *
     * @param parent1 the parent1
     */
    public void setParent1(final ContractMember parent1) {
        this.parent1 = parent1;
    }

    /**
     * Gets the parent2.
     *
     * @return the parent2
     */
    public ContractMember getParent2() {
        return parent2;
    }

    /**
     * Sets the parent2.
     *
     * @param parent2 the parent2
     */
    public void setParent2(final ContractMember parent2) {
        this.parent2 = parent2;
    }

    /**
     * Gets the parent3.
     *
     * @return the parent3
     */
    public ContractMember getParent3() {
        return parent3;
    }

    /**
     * Sets the parent3.
     *
     * @param parent3 the parent3
     */
    public void setParent3(final ContractMember parent3) {
        this.parent3 = parent3;
    }

    /**
     * Gets the parent4.
     *
     * @return the parent4
     */
    public ContractMember getParent4() {
        return parent4;
    }

    /**
     * Sets the parent4.
     *
     * @param parent4 the parent4
     */
    public void setParent4(final ContractMember parent4) {
        this.parent4 = parent4;
    }

    /**
     * Gets the internal id.
     *
     * @return the internal id
     */
    public int getInternalId() {
        return internalId;
    }

    /**
     * Sets the internal id.
     *
     * @param internalId the internal id
     */
    public void setInternalId(final int internalId) {
        this.internalId = internalId;
    }

    public int getCfpContractId() {
        return cfpContractId;
    }

    public void setCfpContractId(int cfpContractId) {
        this.cfpContractId = cfpContractId;
    }

    public int getIfpContractId() {
        return ifpContractId;
    }

    public void setIfpContractId(int ifpContractId) {
        this.ifpContractId = ifpContractId;
    }

    public int getPsContractId() {
        return psContractId;
    }

    public void setPsContractId(int psContractId) {
        this.psContractId = psContractId;
    }

    public boolean isContractChild() {
        return contractChild;
    }

    public void setContractChild(boolean contractChild) {
        this.contractChild = contractChild;
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

    }
