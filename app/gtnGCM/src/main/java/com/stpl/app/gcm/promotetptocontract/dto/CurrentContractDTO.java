/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class CurrentContractDTO implements Serializable {

    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private String rarCategory = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private boolean checkRecord = false;
    private String contractId = StringUtils.EMPTY;
    private String cfpContSid = StringUtils.EMPTY;
    private String ifpContSid = StringUtils.EMPTY;
    private String psContSid = StringUtils.EMPTY;
    private String rsContSid = StringUtils.EMPTY;
    private String rebateScheduleId = StringUtils.EMPTY;
    private String rebateScheduleCategory = StringUtils.EMPTY;
    private String rebateScheduleType = StringUtils.EMPTY;
    private String rebateProgramType = StringUtils.EMPTY;
    private String rebateScheduleAlias = StringUtils.EMPTY;
    private String companySystemId = StringUtils.EMPTY;
    private Date companyEndDate;
    private String levelNo = StringUtils.EMPTY;
    private String cfpStartDate = StringUtils.EMPTY;
    private String cfpEndDate = StringUtils.EMPTY;
    private String aliasStartDate = StringUtils.EMPTY;
    private String aliasEndDate = StringUtils.EMPTY;
    private Integer hiddenId = 0;
    private String screenName = StringUtils.EMPTY;
    private Integer cfpId = 0;
    private Integer IFPId = 0;
    private Integer psId = 0;
    private Integer rsId = 0;
    private boolean search = false;
    private boolean reset = false;
    private boolean searchInverse = false;
    private boolean transfer = false;
    private Date contStartDate;
    private Date contEndDate;
    private String projectionId = StringUtils.EMPTY;
    private boolean isCustomerDetailsTab = false;
    
    public Integer getCfpId() {
        return cfpId;
    }

    public void setCfpId(Integer cfpId) {
        this.cfpId = cfpId;
    }

    public Integer getIFPId() {
        return IFPId;
    }

    public void setIFPId(Integer IFPId) {
        this.IFPId = IFPId;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    public Date getContStartDate() {
        return contStartDate == null ? null : (Date) contStartDate.clone();
    }

    public void setContStartDate(Date contStartDate) {
        this.contStartDate = contStartDate == null ? null : (Date) contStartDate.clone();
    }

    public Date getContEndDate() {
        return contEndDate == null ? null : (Date) contEndDate.clone();
    }

    public void setContEndDate(Date contEndDate) {
        this.contEndDate = contEndDate == null ? null : (Date) contEndDate.clone();
    }

    public Boolean getSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public Boolean getSearchInverse() {
        return searchInverse;
    }

    public void setSearchInverse(Boolean searchInverse) {
        this.searchInverse = searchInverse;
    }

    public Boolean getTransfer() {
        return transfer;
    }

    public void setTransfer(Boolean transfer) {
        this.transfer = transfer;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(Integer hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAliasStartDate() {
        return aliasStartDate;
    }

    public void setAliasStartDate(String aliasStartDate) {
        this.aliasStartDate = aliasStartDate;
    }

    public String getAliasEndDate() {
        return aliasEndDate;
    }

    public void setAliasEndDate(String aliasEndDate) {
        this.aliasEndDate = aliasEndDate;
    }

    public String getCfpStartDate() {
        return cfpStartDate;
    }

    public void setCfpStartDate(String cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }

    public String getCfpEndDate() {
        return cfpEndDate;
    }

    public void setCfpEndDate(String cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public Date getCompanyEndDate() {
        return companyEndDate == null ? null : (Date) companyEndDate.clone();
    }

    public void setCompanyEndDate(Date companyEndDate) {
        this.companyEndDate = companyEndDate == null ? null : (Date) companyEndDate.clone();
    }

    public String getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(String companySystemId) {
        this.companySystemId = companySystemId;
    }

    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    public void setRebateScheduleAlias(String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public String getRebateScheduleType() {
        return rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    public String getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    public void setRebateScheduleCategory(String rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getCfpContSid() {
        return cfpContSid;
    }

    public void setCfpContSid(String cfpContSid) {
        this.cfpContSid = cfpContSid;
    }

    public String getIfpContSid() {
        return ifpContSid;
    }

    public void setIfpContSid(String ifpContSid) {
        this.ifpContSid = ifpContSid;
    }

    public String getPsContSid() {
        return psContSid;
    }

    public void setPsContSid(String psContSid) {
        this.psContSid = psContSid;
    }

    public String getRsContSid() {
        return rsContSid;
    }

    public void setRsContSid(String rsContSid) {
        this.rsContSid = rsContSid;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * Default Constractor.
     */
    public CurrentContractDTO() {
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
    public CurrentContractDTO(final Integer internalId, final Integer modelSysId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed) {
        this.internalId = internalId;
        this.modelSysId = modelSysId;
        this.contractName = name;
        this.category = category;
        this.contractId = memberId;
        this.contractNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = new ArrayList<>();
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
    public CurrentContractDTO(final Integer systemId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed, final Integer level) {
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
    public CurrentContractDTO(final Integer systemId, final String name, final String memberId, final String memberNo, final String category, final Boolean childrenAllowed, final List<CurrentContractDTO> subList) {
        this.contractName = name;
        this.category = category;
        this.systemId = systemId;
        this.contractId = memberId;
        this.contractNo = memberNo;
        this.childrenAllowed = childrenAllowed;
        this.sub = subList == null ? subList : new ArrayList<>(subList);
    }
    private Integer systemId = 0;
    /**
     * Variable used for Contract id.
     */
    private Integer contractSid = 0;
    
    /**
     * Variable used for CFPID.
     */
    private Integer cfpContractId = 0;
    
    /**
     * Variable used for IFPID.
     */
    private Integer ifpContractId = 0;
    
    /**
     * Variable used for PSID.
     */
    private Integer psContractId = 0;
    /**
     * Variable used for INTERNAL ID.
     */
    private Integer internalId = 0;
    /**
     * FLAG used for ChildrenAllowed value.
     */
    private Boolean childrenAllowed;
    /**
     * Variable used for LEVEL VALUE.
     */
    private Integer level;
    /**
     * Variable used for PARENT1.
     */
    private CurrentContractDTO parent1;
    /**
     * Variable used for PARENT2.
     */
    private CurrentContractDTO parent2;
    /**
     * Variable used for PARENT3.
     */
    private CurrentContractDTO parent3;
    /**
     * Variable used for PARENT4.
     */
    private CurrentContractDTO parent4;
    /**
     * List value for ContractMember named as sub.
     */
    private List<CurrentContractDTO> sub;
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

    public Integer getCfpContractId() {
        return cfpContractId;
    }

    public void setCfpContractId(Integer cfpContractId) {
        this.cfpContractId = cfpContractId;
    }

    public Integer getIfpContractId() {
        return ifpContractId;
    }

    public void setIfpContractId(Integer ifpContractId) {
        this.ifpContractId = ifpContractId;
    }

    public Integer getPsContractId() {
        return psContractId;
    }

    public void setPsContractId(Integer psContractId) {
        this.psContractId = psContractId;
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

    public CurrentContractDTO getParent1() {
        return parent1;
    }

    public void setParent1(CurrentContractDTO parent1) {
        this.parent1 = parent1;
    }

    public CurrentContractDTO getParent2() {
        return parent2;
    }

    public void setParent2(CurrentContractDTO parent2) {
        this.parent2 = parent2;
    }

    public CurrentContractDTO getParent3() {
        return parent3;
    }

    public void setParent3(CurrentContractDTO parent3) {
        this.parent3 = parent3;
    }

    public CurrentContractDTO getParent4() {
        return parent4;
    }

    public void setParent4(CurrentContractDTO parent4) {
        this.parent4 = parent4;
    }

    public List<CurrentContractDTO> getSub() {
        return sub == null ? sub : new ArrayList<>(sub);
    }

    public void setSub(List<CurrentContractDTO> sub) {
        this.sub = sub == null ? sub : new ArrayList<>(sub);
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

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
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

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public Boolean getIsCustomerDetailsTab() {
        return isCustomerDetailsTab;
    }

    public void setIsCustomerDetailsTab(Boolean isCustomerDetailsTab) {
        this.isCustomerDetailsTab = isCustomerDetailsTab;
    }
    
}
