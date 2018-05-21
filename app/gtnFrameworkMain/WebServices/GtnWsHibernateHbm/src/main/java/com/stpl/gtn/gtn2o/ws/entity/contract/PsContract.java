package com.stpl.gtn.gtn2o.ws.entity.contract;
// Generated Mar 30, 2017 9:06:03 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.PsModel;

/**
 * PsContract generated by hbm2java
 */
@SuppressWarnings("rawtypes") 
public class PsContract implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1283397056778917142L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int psContractSid;
	private CfpContract cfpContract;
	private ContractMaster contractMaster;
	private HelperTable helperTableByPsCategory;
	private HelperTable helperTableByPsStatus;
	private HelperTable helperTableByPsDesignation;
        private HelperTable helperTableByPsTradeClass;    

	private HelperTable helperTableByPsType;
	private HelperTable helperTableByPsContractAttachedStatus;
	private IfpContract ifpContract;
	private PsModel psModel;
	private String psName;
	private Date psStartDate;
	private Date psEndDate;
	private Date psContractAttachedDate;
	private String parentPsId;
	private String parentPsName;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private Integer psTradeClass;
	private String psNo;
	private Set rsContracts = new HashSet(0);
	private Set psContractDetailses = new HashSet(0);

	public PsContract() {
	}

	public PsContract(int psContractSid, ContractMaster contractMaster, PsModel psModel, Date psStartDate,
			char inboundStatus, boolean recordLockStatus, int createdBy, Date createdDate, int modifiedBy,
			Date modifiedDate) {
		this.psContractSid = psContractSid;
		this.contractMaster = contractMaster;
		this.psModel = psModel;
		this.psStartDate = psStartDate;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public PsContract(int psContractSid, CfpContract cfpContract, ContractMaster contractMaster,
			HelperTable helperTableByPsCategory, HelperTable helperTableByPsStatus,
			HelperTable helperTableByPsDesignation, HelperTable helperTableByPsType,
			HelperTable helperTableByPsContractAttachedStatus, IfpContract ifpContract, PsModel psModel, String psName,
			Date psStartDate, Date psEndDate, Date psContractAttachedDate, String parentPsId, String parentPsName,
			char inboundStatus, boolean recordLockStatus, String batchId, String source, int createdBy,
			Date createdDate, int modifiedBy, Date modifiedDate, Integer psTradeClass, String psNo, Set rsContracts,
			Set psContractDetailses, HelperTable helperTableByPsTradeClass) {
		this.psContractSid = psContractSid;
		this.cfpContract = cfpContract;
		this.contractMaster = contractMaster;
		this.helperTableByPsCategory = helperTableByPsCategory;
		this.helperTableByPsStatus = helperTableByPsStatus;
		this.helperTableByPsDesignation = helperTableByPsDesignation;
		this.helperTableByPsType = helperTableByPsType;
		this.helperTableByPsContractAttachedStatus = helperTableByPsContractAttachedStatus;
		this.ifpContract = ifpContract;
		this.psModel = psModel;
		this.psName = psName;
		this.psStartDate = psStartDate;
		this.psEndDate = psEndDate;
		this.psContractAttachedDate = psContractAttachedDate;
		this.parentPsId = parentPsId;
		this.parentPsName = parentPsName;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.psTradeClass = psTradeClass;
		this.psNo = psNo;
		this.rsContracts = rsContracts;
		this.psContractDetailses = psContractDetailses;
                this.helperTableByPsTradeClass =helperTableByPsTradeClass;
	}

	public int getPsContractSid() {
		return this.psContractSid;
	}

	public void setPsContractSid(int psContractSid) {
		this.psContractSid = psContractSid;
	}

	public CfpContract getCfpContract() {
		return this.cfpContract;
	}

	public void setCfpContract(CfpContract cfpContract) {
		this.cfpContract = cfpContract;
	}

	public ContractMaster getContractMaster() {
		return this.contractMaster;
	}

	public void setContractMaster(ContractMaster contractMaster) {
		this.contractMaster = contractMaster;
	}

	public HelperTable getHelperTableByPsCategory() {
		return this.helperTableByPsCategory;
	}

	public void setHelperTableByPsCategory(HelperTable helperTableByPsCategory) {
		this.helperTableByPsCategory = helperTableByPsCategory;
	}

	public HelperTable getHelperTableByPsStatus() {
		return this.helperTableByPsStatus;
	}

	public void setHelperTableByPsStatus(HelperTable helperTableByPsStatus) {
		this.helperTableByPsStatus = helperTableByPsStatus;
	}

	public HelperTable getHelperTableByPsDesignation() {
		return this.helperTableByPsDesignation;
	}

	public void setHelperTableByPsDesignation(HelperTable helperTableByPsDesignation) {
		this.helperTableByPsDesignation = helperTableByPsDesignation;
	}

	public HelperTable getHelperTableByPsType() {
		return this.helperTableByPsType;
	}

	public void setHelperTableByPsType(HelperTable helperTableByPsType) {
		this.helperTableByPsType = helperTableByPsType;
	}

	public HelperTable getHelperTableByPsContractAttachedStatus() {
		return this.helperTableByPsContractAttachedStatus;
	}

	public void setHelperTableByPsContractAttachedStatus(HelperTable helperTableByPsContractAttachedStatus) {
		this.helperTableByPsContractAttachedStatus = helperTableByPsContractAttachedStatus;
	}

	public IfpContract getIfpContract() {
		return this.ifpContract;
	}

	public void setIfpContract(IfpContract ifpContract) {
		this.ifpContract = ifpContract;
	}

	public PsModel getPsModel() {
		return this.psModel;
	}

	public void setPsModel(PsModel psModel) {
		this.psModel = psModel;
	}

	public String getPsName() {
		return this.psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public Date getPsStartDate() {
		return this.psStartDate;
	}

	public void setPsStartDate(Date psStartDate) {
		this.psStartDate = psStartDate;
	}

	public Date getPsEndDate() {
		return this.psEndDate;
	}

	public void setPsEndDate(Date psEndDate) {
		this.psEndDate = psEndDate;
	}

	public Date getPsContractAttachedDate() {
		return this.psContractAttachedDate;
	}

	public void setPsContractAttachedDate(Date psContractAttachedDate) {
		this.psContractAttachedDate = psContractAttachedDate;
	}

	public String getParentPsId() {
		return this.parentPsId;
	}

	public void setParentPsId(String parentPsId) {
		this.parentPsId = parentPsId;
	}

	public String getParentPsName() {
		return this.parentPsName;
	}

	public void setParentPsName(String parentPsName) {
		this.parentPsName = parentPsName;
	}

	public char getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public boolean isRecordLockStatus() {
		return this.recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getPsTradeClass() {
		return this.psTradeClass;
	}

	public void setPsTradeClass(Integer psTradeClass) {
		this.psTradeClass = psTradeClass;
	}

	public String getPsNo() {
		return this.psNo;
	}

	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}

	public Set getRsContracts() {
		return this.rsContracts;
	}

	public void setRsContracts(Set rsContracts) {
		this.rsContracts = rsContracts;
	}

	public Set getPsContractDetailses() {
		return this.psContractDetailses;
	}

	public void setPsContractDetailses(Set psContractDetailses) {
		this.psContractDetailses = psContractDetailses;
	}
        public HelperTable getHelperTableByPsTradeClass() {
        return helperTableByPsTradeClass;
    }

    public void setHelperTableByPsTradeClass(HelperTable helperTableByPsTradeClass) {
        this.helperTableByPsTradeClass = helperTableByPsTradeClass;
    }
        
    }
