package com.stpl.app.gcm.discount.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class ContractsDetailsDto implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -792290752533943296L;
    /**
     * Variable used for name field.
     */
    private String contractName = StringUtils.EMPTY;
    /**
     * Variable used for categorize contract member.
     */
    private String category = StringUtils.EMPTY;
    /**
     * Variable used for member unique id.
     */
    private String contractId = StringUtils.EMPTY;
    /**
     * Variable used for member no.
     */
    private String contractNo = StringUtils.EMPTY;
    /**
     * Variable used for unique System id.
     */
    private Integer systemId = 0;
    /**
     * Variable used for Contract id.
     */
    private Integer contractSid = 0;
    /**
     * Variable used for CFPID.
     */
    private Integer cfpId = 0;
    /**
     * Variable used for CFPID.
     */
    private Integer cfpContractId = 0;
    /**
     * Variable used for IFPID.
     */
    private Integer ifpId = 0;
    /**
     * Variable used for IFPID.
     */
    private Integer ifpContractId = 0;
    /**
     * Variable used for PSID.
     */
    private Integer psId = 0;
    /**
     * Variable used for PSID.
     */
    private Integer psContractId = 0;
    /**
     * Variable used for RSID.
     */
    private Integer rsId = 0;
    /**
     * Variable used for INTERNAL ID.
     */
    private Integer internalId = 0;
    /**
     * FLAG used for ChildrenAllowed value.
     */
    private Boolean childrenAllowed=Boolean.FALSE;
    /**
     * Variable used for LEVEL VALUE.
     */
    private Integer level;
    /**
     * Variable used for PARENT1.
     */
    private ContractsDetailsDto parent1;
    /**
     * Variable used for PARENT2.
     */
    private ContractsDetailsDto parent2;
    /**
     * Variable used for PARENT3.
     */
    private ContractsDetailsDto parent3;
    /**
     * Variable used for PARENT4.
     */
    private ContractsDetailsDto parent4;
    /**
     * List value for ContractMember named as sub.
     */
    private List<ContractsDetailsDto> sub;
    /**
     * Static Final Variable LEVEL1.
     */
    public static final int LEVEL1 = 1;
    /**
     * Static Final Variable LEVEL2.
     */
    public static final int LEVEL2 = 2;
    /**
     * Static Final Variable LEVEL3.
     */
    public static final int LEVEL3 = 3;
    /**
     * Static Final Variable LEVEL4.
     */
    public static final int LEVEL4 = 4;
    /**
     * Static Final Variable LEVEL5.
     */
    public static final int LEVEL5 = 5;

    private Integer modelSysId = 0;
    private Boolean checkRecord=Boolean.FALSE;
    private String contractHolder = StringUtils.EMPTY;

    private String marketType = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;

    private String contractStatus = StringUtils.EMPTY;
    private String contractendDate = StringUtils.EMPTY;
    private String contractstartDate = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String rarType = StringUtils.EMPTY;
    private String basis = StringUtils.EMPTY;

    //For Selected item table
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemStartDate = StringUtils.EMPTY;
    private String itemEndDate = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private String tempStartDate = StringUtils.EMPTY;
    private String tempEndDate = StringUtils.EMPTY;
    private String tempRebatePlan = StringUtils.EMPTY;
    private String tempFormulaId = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;

    private String searchField = StringUtils.EMPTY;
    private String searchFieldValue = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private Boolean isCount =Boolean.FALSE;

    private String rsSid;
    private String ifpSystemId;

    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String tempStatus = StringUtils.EMPTY;
    private String designation = StringUtils.EMPTY;
    private String type = StringUtils.EMPTY;
    private String psCategory = StringUtils.EMPTY;
    private String psSid = StringUtils.EMPTY;
    private String attachedDate = StringUtils.EMPTY;

    private Integer rsContractSId = 0;

    private String rebatePlanNo = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    private String priceType = StringUtils.EMPTY;
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private String priceProtectionStartDate = StringUtils.EMPTY;
    private String priceProtectionEndDate = StringUtils.EMPTY;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;

    private String resetEligibility = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private String resetDate = StringUtils.EMPTY;
    private String resetInterval = StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;

    private String tpNo = StringUtils.EMPTY;
    private String tpName = StringUtils.EMPTY;
    private String tpContractNo = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String formulaType = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String helperTableSid = StringUtils.EMPTY;

    private List<Integer> rebateList;
    private String formulaSysId = StringUtils.EMPTY;
    private String rebatePlanSysId = StringUtils.EMPTY;
    private Boolean checkAll =Boolean.FALSE;
    private Date sDate = null;
    private Date eDate = null;
    private Date tempSDate = null;
    private Date tempEDate = null;
    private String paymentFrequency = StringUtils.EMPTY;
    private String paymentMethod = StringUtils.EMPTY;
    private String rebateType = StringUtils.EMPTY;
    private String rebateProgramType = StringUtils.EMPTY;
    private Boolean bulkUpdate =Boolean.FALSE;
    private String tempPriceType = StringUtils.EMPTY;
    private String rsSystemId = StringUtils.EMPTY;
    private Date ppSDate = null;
    private Date tempPpSDate = null;

    private String tradingPartnerNo = StringUtils.EMPTY;
    private String tradingPartnerName = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private Map<String, List<ContractsDetailsDto>> relation = new HashMap();
    private List<String> attachedList = new ArrayList<String>();
    private List<Object> removedRsList = new ArrayList<>();
    private String searchSessionId = StringUtils.EMPTY;

    /**
     *
     * /**
     *
     * Default Constractor.
     */
    public ContractsDetailsDto() {
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
    public ContractsDetailsDto(final Integer internalId, final Integer modelSysId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed) {
        this.internalId = internalId;
        this.modelSysId = modelSysId;
        this.contractName = name;
        this.category = category;
        this.contractId = memberId;
        this.contractNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = new ArrayList<ContractsDetailsDto>();
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
    public ContractsDetailsDto(final Integer systemId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed, final Integer level) {
        this.contractName = name;
        this.category = category;
        this.systemId = systemId;
        this.contractId = memberId;
        this.contractNo = memberNo;
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
    public ContractsDetailsDto(final Integer systemId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed, final List<ContractsDetailsDto> subList) {
        this.contractName = name;
        this.category = category;
        this.systemId = systemId;
        this.contractId = memberId;
        this.contractNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = subList;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getContractSid() {
        return contractSid;
    }

    public void setContractSid(Integer contractSid) {
        this.contractSid = contractSid;
    }

    public Integer getCfpId() {
        return cfpId;
    }

    public void setCfpId(Integer cfpId) {
        this.cfpId = cfpId;
    }

    public Integer getCfpContractId() {
        return cfpContractId;
    }

    public void setCfpContractId(Integer cfpContractId) {
        this.cfpContractId = cfpContractId;
    }

    public Integer getIfpId() {
        return ifpId;
    }

    public void setIfpId(Integer ifpId) {
        this.ifpId = ifpId;
    }

    public Integer getIfpContractId() {
        return ifpContractId;
    }

    public void setIfpContractId(Integer ifpContractId) {
        this.ifpContractId = ifpContractId;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getPsContractId() {
        return psContractId;
    }

    public void setPsContractId(Integer psContractId) {
        this.psContractId = psContractId;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public Boolean getChildrenAllowed() {
        return childrenAllowed;
    }

    public void setChildrenAllowed(Boolean childrenAllowed) {
        this.childrenAllowed = childrenAllowed;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public ContractsDetailsDto getParent1() {
        return parent1;
    }

    public void setParent1(ContractsDetailsDto parent1) {
        this.parent1 = parent1;
    }

    public ContractsDetailsDto getParent2() {
        return parent2;
    }

    public void setParent2(ContractsDetailsDto parent2) {
        this.parent2 = parent2;
    }

    public ContractsDetailsDto getParent3() {
        return parent3;
    }

    public void setParent3(ContractsDetailsDto parent3) {
        this.parent3 = parent3;
    }

    public ContractsDetailsDto getParent4() {
        return parent4;
    }

    public void setParent4(ContractsDetailsDto parent4) {
        this.parent4 = parent4;
    }

    public List<ContractsDetailsDto> getSub() {
        return sub;
    }

    public void setSub(List<ContractsDetailsDto> sub) {
        this.sub = sub;
    }

    public Integer getModelSysId() {
        return modelSysId;
    }

    public void setModelSysId(Integer modelSysId) {
        this.modelSysId = modelSysId;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractendDate() {
        return contractendDate;
    }

    public void setContractendDate(String contractendDate) {
        this.contractendDate = contractendDate;
    }

    public String getContractstartDate() {
        return contractstartDate;
    }

    public void setContractstartDate(String contractstartDate) {
        this.contractstartDate = contractstartDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRarType() {
        return rarType;
    }

    public void setRarType(String rarType) {
        this.rarType = rarType;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchFieldValue() {
        return searchFieldValue;
    }

    public void setSearchFieldValue(String searchFieldValue) {
        this.searchFieldValue = searchFieldValue;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Boolean getIsCount() {
        return isCount;
    }

    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
    }

    public String getRsSid() {
        return rsSid;
    }

    public void setRsSid(String rsSid) {
        this.rsSid = rsSid;
    }

    public String getIfpSystemId() {
        return ifpSystemId;
    }

    public void setIfpSystemId(String ifpSystemId) {
        this.ifpSystemId = ifpSystemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPsCategory() {
        return psCategory;
    }

    public void setPsCategory(String psCategory) {
        this.psCategory = psCategory;
    }

    public String getPsSid() {
        return psSid;
    }

    public void setPsSid(String psSid) {
        this.psSid = psSid;
    }

    public Integer getRsContractSId() {
        return rsContractSId;
    }

    public String getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }

    public void setRsContractSId(Integer rsContractSId) {
        this.rsContractSId = rsContractSId;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPricePlanNo() {
        return pricePlanNo;
    }

    public void setPricePlanNo(String pricePlanNo) {
        this.pricePlanNo = pricePlanNo;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    public String getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(String priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }

    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public String getTpNo() {
        return tpNo;
    }

    public void setTpNo(String tpNo) {
        this.tpNo = tpNo;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    public String getTpContractNo() {
        return tpContractNo;
    }

    public void setTpContractNo(String tpContractNo) {
        this.tpContractNo = tpContractNo;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getHelperTableSid() {
        return helperTableSid;
    }

    public void setHelperTableSid(String helperTableSid) {
        this.helperTableSid = helperTableSid;
    }

    public List<Integer> getRebateList() {
        return rebateList;
    }

    public void setRebateList(List<Integer> rebateList) {
        this.rebateList = rebateList;
    }

    public String getFormulaSysId() {
        return formulaSysId;
    }

    public void setFormulaSysId(String formulaSysId) {
        this.formulaSysId = formulaSysId;
    }

    public String getRebatePlanSysId() {
        return rebatePlanSysId;
    }

    public void setRebatePlanSysId(String rebatePlanSysId) {
        this.rebatePlanSysId = rebatePlanSysId;
    }

    public String getTempStartDate() {
        return tempStartDate;
    }

    public void setTempStartDate(String tempStartDate) {
        this.tempStartDate = tempStartDate;
    }

    public String getTempEndDate() {
        return tempEndDate;
    }

    public void setTempEndDate(String tempEndDate) {
        this.tempEndDate = tempEndDate;
    }

    public String getTempRebatePlan() {
        return tempRebatePlan;
    }

    public void setTempRebatePlan(String tempRebatePlan) {
        this.tempRebatePlan = tempRebatePlan;
    }

    public String getTempFormulaId() {
        return tempFormulaId;
    }

    public void setTempFormulaId(String tempFormulaId) {
        this.tempFormulaId = tempFormulaId;
    }

    public String getTempStatus() {
        return tempStatus;
    }

    public void setTempStatus(String tempStatus) {
        this.tempStatus = tempStatus;
    }

    public Boolean getCheckAll() {
        return checkAll;
    }

    public void setCheckAll(Boolean checkAll) {
        this.checkAll = checkAll;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Date getTempSDate() {
        return tempSDate;
    }

    public void setTempSDate(Date tempSDate) {
        this.tempSDate = tempSDate;
    }

    public Date getTempEDate() {
        return tempEDate;
    }

    public void setTempEDate(Date tempEDate) {
        this.tempEDate = tempEDate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getRebateType() {
        return rebateType;
    }

    public void setRebateType(String rebateType) {
        this.rebateType = rebateType;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public Boolean getBulkUpdate() {
        return bulkUpdate;
    }

    public void setBulkUpdate(Boolean bulkUpdate) {
        this.bulkUpdate = bulkUpdate;
    }

    public String getTempPriceType() {
        return tempPriceType;
    }

    public void setTempPriceType(String tempPriceType) {
        this.tempPriceType = tempPriceType;
    }

    public String getRsSystemId() {
        return rsSystemId;
    }

    public void setRsSystemId(String rsSystemId) {
        this.rsSystemId = rsSystemId;
    }

    public Date getPpSDate() {
        return ppSDate;
    }

    public void setPpSDate(Date ppSDate) {
        this.ppSDate = ppSDate;
    }

    public Date getTempPpSDate() {
        return tempPpSDate;
    }

    public void setTempPpSDate(Date tempPpSDate) {
        this.tempPpSDate = tempPpSDate;
    }

    public String getTradingPartnerNo() {
        return tradingPartnerNo;
    }

    public void setTradingPartnerNo(String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }

    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(String priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public String getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(String priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getResetEligibility() {
        return resetEligibility;
    }

    public void setResetEligibility(String resetEligibility) {
        this.resetEligibility = resetEligibility;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public String getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(String resetInterval) {
        this.resetInterval = resetInterval;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public Map<String, List<ContractsDetailsDto>> getRelation() {
        return relation;
    }

    public void setRelation(Map<String, List<ContractsDetailsDto>> relation) {
        this.relation = relation;
    }

    public void addStringProperties(String propertyId, List<ContractsDetailsDto> value) {
        this.relation.put(propertyId, value);
    }

    public List<ContractsDetailsDto> getPropertyValue(String propertyId) {
        return relation.get(propertyId);
    }

    public List<String> getAttachedList() {
        return attachedList;
    }

    public void setAttachedList(List<String> attachedList) {
        this.attachedList = attachedList;
    }

    public String getSearchSessionId() {
        return searchSessionId;
    }

    public void setSearchSessionId(String searchSessionId) {
        this.searchSessionId = searchSessionId;
    }

    public List<Object> getRemovedRsList() {
        return removedRsList;
    }

    public void setRemovedRsList(List<Object> removedRsList) {
        this.removedRsList = removedRsList;
    }
}
