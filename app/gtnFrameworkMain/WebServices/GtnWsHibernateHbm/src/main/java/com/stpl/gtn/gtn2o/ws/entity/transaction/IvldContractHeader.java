package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

@SuppressWarnings("serial")
public class IvldContractHeader  implements java.io.Serializable {


     private int ivldContractHeaderSid;
     private String contractHeaderIntfid;
     private String organizationKey;
     private String contractId;
     private String contractNo;
     private String contractName;
     private String contractType;
     private String contractStatus;
     private String docType;
     private String docClass;
     private String companyIdentCodeQualifier;
     private String companyNo;
     private String companyName;
     private String tpIdentifierCodeQualifier;
     private String tradingPartnerNo;
     private String tradingPartnerName;
     private String tpContractId;
     private String tpContractName;
     private String tradeClass;
     private String startDate;
     private String endDate;
     private String term;
     private String renegotiationStartDate;
     private String renegotiationEndDate;
     private String priceprotectionStartDate;
     private String priceprotectionEndDate;
     private String advanceNoticeDays;
     private String insideOwner;
     private String insidePhone;
     private String insideAuthor;
     private String insideAdditional;
     private String insideAdditionalName;
     private String insideAdditionalPhone;
     private String outsideOwner;
     private String outsidePhone;
     private String outsideAuthor;
     private String outsideAdditional;
     private String outsideAdditionalName;
     private String outsideAdditionalPhone;
     private String affiliatedContractInfo;
     private String shippingTerms;
     private String proposalStartDate;
     private String proposalEndDate;
     private String originalStartDate;
     private String originalEndDate;
     private String awardStatus;
     private String lastUpdatedDate;
     private String priceEscalationClause;
     private String exemptFromLowPrice;
     private String priceResetIndicator;
     private String cancellationClause;
     private String mostFavoredNation;
     private String category;
     private String currency;
     private String minimumOrder;
     private String paymentTerms;
     private String contractAliasNo;
     private String contractAliasName;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String batchId;
     private String source;
     private String addChgDelIndicator;
     private String reasonForFailure;
     private Date intfInsertedDate;
     private String errorCode;
     private String errorField;
     private boolean checkRecord;
     private String reprocessedFlag;

    public IvldContractHeader() {
    }

	
    public IvldContractHeader(int ivldContractHeaderSid, String contractHeaderIntfid) {
        this.ivldContractHeaderSid = ivldContractHeaderSid;
        this.contractHeaderIntfid = contractHeaderIntfid;
    }
    public IvldContractHeader(int ivldContractHeaderSid, String contractHeaderIntfid, String organizationKey, String contractId, String contractNo, String contractName, String contractType, String contractStatus, String docType, String docClass, String companyIdentCodeQualifier, String companyNo, String companyName, String tpIdentifierCodeQualifier, String tradingPartnerNo, String tradingPartnerName, String tpContractId, String tpContractName, String tradeClass, String startDate, String endDate, String term, String renegotiationStartDate, String renegotiationEndDate, String priceprotectionStartDate, String priceprotectionEndDate, String advanceNoticeDays, String insideOwner, String insidePhone, String insideAuthor, String insideAdditional, String insideAdditionalName, String insideAdditionalPhone, String outsideOwner, String outsidePhone, String outsideAuthor, String outsideAdditional, String outsideAdditionalName, String outsideAdditionalPhone, String affiliatedContractInfo, String shippingTerms, String proposalStartDate, String proposalEndDate, String originalStartDate, String originalEndDate, String awardStatus, String lastUpdatedDate, String priceEscalationClause, String exemptFromLowPrice, String priceResetIndicator, String cancellationClause, String mostFavoredNation, String category, String currency, String minimumOrder, String paymentTerms, String contractAliasNo, String contractAliasName, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, boolean checkRecord, String reprocessedFlag) {
       this.ivldContractHeaderSid = ivldContractHeaderSid;
       this.contractHeaderIntfid = contractHeaderIntfid;
       this.organizationKey = organizationKey;
       this.contractId = contractId;
       this.contractNo = contractNo;
       this.contractName = contractName;
       this.contractType = contractType;
       this.contractStatus = contractStatus;
       this.docType = docType;
       this.docClass = docClass;
       this.companyIdentCodeQualifier = companyIdentCodeQualifier;
       this.companyNo = companyNo;
       this.companyName = companyName;
       this.tpIdentifierCodeQualifier = tpIdentifierCodeQualifier;
       this.tradingPartnerNo = tradingPartnerNo;
       this.tradingPartnerName = tradingPartnerName;
       this.tpContractId = tpContractId;
       this.tpContractName = tpContractName;
       this.tradeClass = tradeClass;
       this.startDate = startDate;
       this.endDate = endDate;
       this.term = term;
       this.renegotiationStartDate = renegotiationStartDate;
       this.renegotiationEndDate = renegotiationEndDate;
       this.priceprotectionStartDate = priceprotectionStartDate;
       this.priceprotectionEndDate = priceprotectionEndDate;
       this.advanceNoticeDays = advanceNoticeDays;
       this.insideOwner = insideOwner;
       this.insidePhone = insidePhone;
       this.insideAuthor = insideAuthor;
       this.insideAdditional = insideAdditional;
       this.insideAdditionalName = insideAdditionalName;
       this.insideAdditionalPhone = insideAdditionalPhone;
       this.outsideOwner = outsideOwner;
       this.outsidePhone = outsidePhone;
       this.outsideAuthor = outsideAuthor;
       this.outsideAdditional = outsideAdditional;
       this.outsideAdditionalName = outsideAdditionalName;
       this.outsideAdditionalPhone = outsideAdditionalPhone;
       this.affiliatedContractInfo = affiliatedContractInfo;
       this.shippingTerms = shippingTerms;
       this.proposalStartDate = proposalStartDate;
       this.proposalEndDate = proposalEndDate;
       this.originalStartDate = originalStartDate;
       this.originalEndDate = originalEndDate;
       this.awardStatus = awardStatus;
       this.lastUpdatedDate = lastUpdatedDate;
       this.priceEscalationClause = priceEscalationClause;
       this.exemptFromLowPrice = exemptFromLowPrice;
       this.priceResetIndicator = priceResetIndicator;
       this.cancellationClause = cancellationClause;
       this.mostFavoredNation = mostFavoredNation;
       this.category = category;
       this.currency = currency;
       this.minimumOrder = minimumOrder;
       this.paymentTerms = paymentTerms;
       this.contractAliasNo = contractAliasNo;
       this.contractAliasName = contractAliasName;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.batchId = batchId;
       this.source = source;
       this.addChgDelIndicator = addChgDelIndicator;
       this.reasonForFailure = reasonForFailure;
       this.intfInsertedDate = intfInsertedDate;
       this.errorCode = errorCode;
       this.errorField = errorField;
       this.checkRecord = checkRecord;
       this.reprocessedFlag = reprocessedFlag;
    }
   
    public int getIvldContractHeaderSid() {
        return this.ivldContractHeaderSid;
    }
    
    public void setIvldContractHeaderSid(int ivldContractHeaderSid) {
        this.ivldContractHeaderSid = ivldContractHeaderSid;
    }
    public String getContractHeaderIntfid() {
        return this.contractHeaderIntfid;
    }
    
    public void setContractHeaderIntfid(String contractHeaderIntfid) {
        this.contractHeaderIntfid = contractHeaderIntfid;
    }
    public String getOrganizationKey() {
        return this.organizationKey;
    }
    
    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getContractNo() {
        return this.contractNo;
    }
    
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public String getContractName() {
        return this.contractName;
    }
    
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    public String getContractType() {
        return this.contractType;
    }
    
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    public String getContractStatus() {
        return this.contractStatus;
    }
    
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
    public String getDocType() {
        return this.docType;
    }
    
    public void setDocType(String docType) {
        this.docType = docType;
    }
    public String getDocClass() {
        return this.docClass;
    }
    
    public void setDocClass(String docClass) {
        this.docClass = docClass;
    }
    public String getCompanyIdentCodeQualifier() {
        return this.companyIdentCodeQualifier;
    }
    
    public void setCompanyIdentCodeQualifier(String companyIdentCodeQualifier) {
        this.companyIdentCodeQualifier = companyIdentCodeQualifier;
    }
    public String getCompanyNo() {
        return this.companyNo;
    }
    
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getTpIdentifierCodeQualifier() {
        return this.tpIdentifierCodeQualifier;
    }
    
    public void setTpIdentifierCodeQualifier(String tpIdentifierCodeQualifier) {
        this.tpIdentifierCodeQualifier = tpIdentifierCodeQualifier;
    }
    public String getTradingPartnerNo() {
        return this.tradingPartnerNo;
    }
    
    public void setTradingPartnerNo(String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }
    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }
    
    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }
    public String getTpContractId() {
        return this.tpContractId;
    }
    
    public void setTpContractId(String tpContractId) {
        this.tpContractId = tpContractId;
    }
    public String getTpContractName() {
        return this.tpContractName;
    }
    
    public void setTpContractName(String tpContractName) {
        this.tpContractName = tpContractName;
    }
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getTerm() {
        return this.term;
    }
    
    public void setTerm(String term) {
        this.term = term;
    }
    public String getRenegotiationStartDate() {
        return this.renegotiationStartDate;
    }
    
    public void setRenegotiationStartDate(String renegotiationStartDate) {
        this.renegotiationStartDate = renegotiationStartDate;
    }
    public String getRenegotiationEndDate() {
        return this.renegotiationEndDate;
    }
    
    public void setRenegotiationEndDate(String renegotiationEndDate) {
        this.renegotiationEndDate = renegotiationEndDate;
    }
    public String getPriceprotectionStartDate() {
        return this.priceprotectionStartDate;
    }
    
    public void setPriceprotectionStartDate(String priceprotectionStartDate) {
        this.priceprotectionStartDate = priceprotectionStartDate;
    }
    public String getPriceprotectionEndDate() {
        return this.priceprotectionEndDate;
    }
    
    public void setPriceprotectionEndDate(String priceprotectionEndDate) {
        this.priceprotectionEndDate = priceprotectionEndDate;
    }
    public String getAdvanceNoticeDays() {
        return this.advanceNoticeDays;
    }
    
    public void setAdvanceNoticeDays(String advanceNoticeDays) {
        this.advanceNoticeDays = advanceNoticeDays;
    }
    public String getInsideOwner() {
        return this.insideOwner;
    }
    
    public void setInsideOwner(String insideOwner) {
        this.insideOwner = insideOwner;
    }
    public String getInsidePhone() {
        return this.insidePhone;
    }
    
    public void setInsidePhone(String insidePhone) {
        this.insidePhone = insidePhone;
    }
    public String getInsideAuthor() {
        return this.insideAuthor;
    }
    
    public void setInsideAuthor(String insideAuthor) {
        this.insideAuthor = insideAuthor;
    }
    public String getInsideAdditional() {
        return this.insideAdditional;
    }
    
    public void setInsideAdditional(String insideAdditional) {
        this.insideAdditional = insideAdditional;
    }
    public String getInsideAdditionalName() {
        return this.insideAdditionalName;
    }
    
    public void setInsideAdditionalName(String insideAdditionalName) {
        this.insideAdditionalName = insideAdditionalName;
    }
    public String getInsideAdditionalPhone() {
        return this.insideAdditionalPhone;
    }
    
    public void setInsideAdditionalPhone(String insideAdditionalPhone) {
        this.insideAdditionalPhone = insideAdditionalPhone;
    }
    public String getOutsideOwner() {
        return this.outsideOwner;
    }
    
    public void setOutsideOwner(String outsideOwner) {
        this.outsideOwner = outsideOwner;
    }
    public String getOutsidePhone() {
        return this.outsidePhone;
    }
    
    public void setOutsidePhone(String outsidePhone) {
        this.outsidePhone = outsidePhone;
    }
    public String getOutsideAuthor() {
        return this.outsideAuthor;
    }
    
    public void setOutsideAuthor(String outsideAuthor) {
        this.outsideAuthor = outsideAuthor;
    }
    public String getOutsideAdditional() {
        return this.outsideAdditional;
    }
    
    public void setOutsideAdditional(String outsideAdditional) {
        this.outsideAdditional = outsideAdditional;
    }
    public String getOutsideAdditionalName() {
        return this.outsideAdditionalName;
    }
    
    public void setOutsideAdditionalName(String outsideAdditionalName) {
        this.outsideAdditionalName = outsideAdditionalName;
    }
    public String getOutsideAdditionalPhone() {
        return this.outsideAdditionalPhone;
    }
    
    public void setOutsideAdditionalPhone(String outsideAdditionalPhone) {
        this.outsideAdditionalPhone = outsideAdditionalPhone;
    }
    public String getAffiliatedContractInfo() {
        return this.affiliatedContractInfo;
    }
    
    public void setAffiliatedContractInfo(String affiliatedContractInfo) {
        this.affiliatedContractInfo = affiliatedContractInfo;
    }
    public String getShippingTerms() {
        return this.shippingTerms;
    }
    
    public void setShippingTerms(String shippingTerms) {
        this.shippingTerms = shippingTerms;
    }
    public String getProposalStartDate() {
        return this.proposalStartDate;
    }
    
    public void setProposalStartDate(String proposalStartDate) {
        this.proposalStartDate = proposalStartDate;
    }
    public String getProposalEndDate() {
        return this.proposalEndDate;
    }
    
    public void setProposalEndDate(String proposalEndDate) {
        this.proposalEndDate = proposalEndDate;
    }
    public String getOriginalStartDate() {
        return this.originalStartDate;
    }
    
    public void setOriginalStartDate(String originalStartDate) {
        this.originalStartDate = originalStartDate;
    }
    public String getOriginalEndDate() {
        return this.originalEndDate;
    }
    
    public void setOriginalEndDate(String originalEndDate) {
        this.originalEndDate = originalEndDate;
    }
    public String getAwardStatus() {
        return this.awardStatus;
    }
    
    public void setAwardStatus(String awardStatus) {
        this.awardStatus = awardStatus;
    }
    public String getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    public String getPriceEscalationClause() {
        return this.priceEscalationClause;
    }
    
    public void setPriceEscalationClause(String priceEscalationClause) {
        this.priceEscalationClause = priceEscalationClause;
    }
    public String getExemptFromLowPrice() {
        return this.exemptFromLowPrice;
    }
    
    public void setExemptFromLowPrice(String exemptFromLowPrice) {
        this.exemptFromLowPrice = exemptFromLowPrice;
    }
    public String getPriceResetIndicator() {
        return this.priceResetIndicator;
    }
    
    public void setPriceResetIndicator(String priceResetIndicator) {
        this.priceResetIndicator = priceResetIndicator;
    }
    public String getCancellationClause() {
        return this.cancellationClause;
    }
    
    public void setCancellationClause(String cancellationClause) {
        this.cancellationClause = cancellationClause;
    }
    public String getMostFavoredNation() {
        return this.mostFavoredNation;
    }
    
    public void setMostFavoredNation(String mostFavoredNation) {
        this.mostFavoredNation = mostFavoredNation;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getMinimumOrder() {
        return this.minimumOrder;
    }
    
    public void setMinimumOrder(String minimumOrder) {
        this.minimumOrder = minimumOrder;
    }
    public String getPaymentTerms() {
        return this.paymentTerms;
    }
    
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    public String getContractAliasNo() {
        return this.contractAliasNo;
    }
    
    public void setContractAliasNo(String contractAliasNo) {
        this.contractAliasNo = contractAliasNo;
    }
    public String getContractAliasName() {
        return this.contractAliasName;
    }
    
    public void setContractAliasName(String contractAliasName) {
        this.contractAliasName = contractAliasName;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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
    public String getAddChgDelIndicator() {
        return this.addChgDelIndicator;
    }
    
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        this.addChgDelIndicator = addChgDelIndicator;
    }
    public String getReasonForFailure() {
        return this.reasonForFailure;
    }
    
    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }
    public Date getIntfInsertedDate() {
        return this.intfInsertedDate;
    }
    
    public void setIntfInsertedDate(Date intfInsertedDate) {
        this.intfInsertedDate = intfInsertedDate;
    }
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorField() {
        return this.errorField;
    }
    
    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }
    public boolean getCheckRecord() {
        return this.checkRecord;
    }
    
    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }
    public String getReprocessedFlag() {
        return this.reprocessedFlag;
    }
    
    public void setReprocessedFlag(String reprocessedFlag) {
        this.reprocessedFlag = reprocessedFlag;
    }




}


