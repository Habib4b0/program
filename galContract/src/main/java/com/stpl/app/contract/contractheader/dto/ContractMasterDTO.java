package com.stpl.app.contract.contractheader.dto;

import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * DTO holds the properties of ContractMaster.
 *
 * @author sibi
 */
public class ContractMasterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5143090213123072104L;
    /**
     * Contract System Id - System generated Id.
     */
    private int contractSystemId;
    /**
     * Contract Id field.
     *
     */
    private String contractId = StringUtils.EMPTY;
    /**
     * Organization Key field.
     *
     */
    private String organizationKey = StringUtils.EMPTY;
    /**
     * Contract Number field.
     */
    private String contractNo = StringUtils.EMPTY;
    /**
     * Contract Name field.
     */
    private String contractName = StringUtils.EMPTY;
    /**
     * Contract Type field.
     *
     */
    private HelperDTO contractType = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Contract Status field.
     *
     */
    private HelperDTO contractStatus = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Document Type field.
     *
     */
    private HelperDTO docType = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Document Class field.
     */
    private HelperDTO docClass = new HelperDTO(0, Constants.SELECT_ONE);

    /**
     * Company Name Field.
     */
    private int companyName;
    /**
     * Company Label field.
     */
    private String companyLabel = StringUtils.EMPTY;
    /**
     * company System Id.
     */
    private HelperDTO companySystemId;
    /**
     * Trade class field.
     */
    private HelperDTO tradeClass = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Start Date field.
     */
    private Date startDate;
    /**
     * End Date Field.
     */
    private Date endDate;
    /**
     * Term field.
     */
    private String term = StringUtils.EMPTY;
    /**
     * Trading Partner Name field.
     */
    private String tradingPartnerName = StringUtils.EMPTY;
    /**
     * Trading Partner System Id field - System generated Id.
     */
    private int tradingPartnerSystemId;
    /**
     * Re-Negotiation Start Date field.
     */
    private Date renegotiationStartDate;
    /**
     * Re-Negotiation End Date field.
     */
    private Date renegotiationEndDate;
    /**
     * Price protection Start Date field.
     */
    private Date priceprotectionStartDate;
    /**
     * Price Protection End Date field.
     */
    private Date priceprotectionEndDate;
    /**
     * Advance Notice Days fields.
     */
    private String advanceNoticeDays = StringUtils.EMPTY;
    /**
     * Inside owner fields.
     */
    private String insideOwner = StringUtils.EMPTY;
    /**
     * Inside Phone fields.
     */
    private String insidePhone = StringUtils.EMPTY;
    /**
     * Inside Author fields.
     */
    private String insideAuthor = StringUtils.EMPTY;
    /**
     * Inside Additional fields.
     */
    private String insideAdditional = StringUtils.EMPTY;
    /**
     * Inside Additional Name field.
     */
    private String insideAdditionalName = StringUtils.EMPTY;
    /**
     * Inside Additional Phone field.
     */
    private String insideAdditionalPhone = StringUtils.EMPTY;
    /**
     * Outside owner field.
     */
    private String outsideOwner = StringUtils.EMPTY;
    /**
     * Outside phone field.
     */
    private String outsidePhone = StringUtils.EMPTY;
    /**
     * Outside Author field.
     */
    private String outsideAuthor = StringUtils.EMPTY;
    /**
     * Outside Additional field.
     */
    private String outsideAdditional = StringUtils.EMPTY;
    /**
     * Outside additional Name field.
     */
    private String outsideAdditionalName = StringUtils.EMPTY;
    /**
     * Outside Additional Phone field.
     */
    private String outsideAdditionalPhone = StringUtils.EMPTY;
    /**
     * Affiliated Contract info field.
     */
    private String affiliatedContractInfo = StringUtils.EMPTY;
    /**
     * Shipping Terms field.
     */
    private String shippingTerms = StringUtils.EMPTY;
    /**
     * Proposed start date field.
     */
    private Date proposedStartDate;
    /**
     * Proposed End Date field.
     */
    private Date proposedEndDate;
    /**
     * Original Start Date field.
     */
    private Date originalStartDate;
    /**
     * Original End Date field.
     */
    private Date originalEndDate;
    /**
     * Award Status field.
     */
    private HelperDTO awardStatus = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Last Update Date field.
     */
    private Date lastUpdatedDate;
    /**
     * Price Escalation cause field.
     */
    private String priceEscalationClause = StringUtils.EMPTY;
    /**
     * Exempt from low price field.
     */
    private String exemptFromLowPrice;
    /**
     * Price reset indicator field.
     */
    private HelperDTO priceResetIndicator = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Cancellation clause field.
     */
    private String cancellationClause = StringUtils.EMPTY;
    /**
     * Most Favored Nation field.
     */
    private String mostFavoredNation = StringUtils.EMPTY;
    /**
     * Category field.
     */
    private String category = StringUtils.EMPTY;
    /**
     * Currency field.
     */
    private String currency = StringUtils.EMPTY;
    /**
     * Minimum order field.
     */
    private String minimumOrder = StringUtils.EMPTY;
    /**
     * Payment Terms field.
     */
    private HelperDTO paymentTerms = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Created By field.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * Created Date Field.
     */
    private Date createdDate;
    /**
     * Modified By field.
     */
    private String modifiedBy = StringUtils.EMPTY;
    /**
     * Modified Date field.
     */
    private Date modifiedDate;
    /**
     * Batch Id field.
     */
    private String batchId = StringUtils.EMPTY;
    /**
     * In bound Status field.
     */
    private String inboundStatus = StringUtils.EMPTY;
    /**
     * Record Lock Status field.
     */
    private String recordLockStatus = StringUtils.EMPTY;

    /**
     * The internal notes.
     */
    private String internalNotes = StringUtils.EMPTY;

    /**
     * Contract Alias Master List Object.
     */
    private List<ContractAliasMasterDTO> contracAliasMasterList = new ArrayList<ContractAliasMasterDTO>();

    /**
     * The companyGroup.
     */
    private String companyGroup = StringUtils.EMPTY;
    /**
     * The companyCategory.
     */
    private String companyCategory = StringUtils.EMPTY;

    /**
     * Gets the contract system id.
     *
     * @return the contract system id
     */
    public final int getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the contract system id
     */
    public final void setContractSystemId(final int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the contract id.
     *
     * @return the contract id
     */
    public final String getContractId() {
        return contractId;
    }

    /**
     * Sets the contract id.
     *
     * @param contractId the contract id
     */
    public final void setContractId(final String contractId) {
        this.contractId = contractId;
    }

    /**
     * Gets the organization key.
     *
     * @return the organization key
     */
    public final String getOrganizationKey() {
        return organizationKey;
    }

    /**
     * Sets the organization key.
     *
     * @param organizationKey the organization key
     */
    public final void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    /**
     * Gets the contract no.
     *
     * @return the contract no
     */
    public final String getContractNo() {
        return contractNo;
    }

    /**
     * Sets the contract no.
     *
     * @param contractNo the contract no
     */
    public final void setContractNo(final String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * Gets the contract name.
     *
     * @return the contract name
     */
    public final String getContractName() {
        return contractName;
    }

    /**
     * Sets the contract name.
     *
     * @param contractName the contract name
     */
    public final void setContractName(final String contractName) {
        this.contractName = contractName;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public final int getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the company name
     */
    public final void setCompanyName(final int companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the company label.
     *
     * @return the company label
     */
    public final String getCompanyLabel() {
        return companyLabel;
    }

    /**
     * Sets the company label.
     *
     * @param companyLabel the company label
     */
    public final void setCompanyLabel(final String companyLabel) {
        this.companyLabel = companyLabel;
    }

    /**
     * Gets the company system id.
     *
     * @return the company system id
     */
    public final HelperDTO getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Sets the company system id.
     *
     * @param companySystemId the company system id
     */
    public final void setCompanySystemId(final HelperDTO companySystemId) {
        this.companySystemId = companySystemId;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public final Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public final void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public final Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public final void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    public final String getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the term
     */
    public final void setTerm(final String term) {
        this.term = term;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the trading partner name
     */
    public final String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Sets the trading partner name.
     *
     * @param tradingPartnerName the trading partner name
     */
    public final void setTradingPartnerName(final String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /**
     * Gets the trading partner system id.
     *
     * @return the trading partner system id
     */
    public final int getTradingPartnerSystemId() {
        return tradingPartnerSystemId;
    }

    /**
     * Sets the trading partner system id.
     *
     * @param tradingPartnerSystemId the trading partner system id
     */
    public final void setTradingPartnerSystemId(final int tradingPartnerSystemId) {
        this.tradingPartnerSystemId = tradingPartnerSystemId;
    }

    /**
     * Gets the renegotiation start date.
     *
     * @return the renegotiation start date
     */
    public final Date getRenegotiationStartDate() {
        return renegotiationStartDate;
    }

    /**
     * Sets the renegotiation start date.
     *
     * @param renegotiationStartDate the renegotiation start date
     */
    public final void setRenegotiationStartDate(final Date renegotiationStartDate) {
        this.renegotiationStartDate = renegotiationStartDate;
    }

    /**
     * Gets the renegotiation end date.
     *
     * @return the renegotiation end date
     */
    public final Date getRenegotiationEndDate() {
        return renegotiationEndDate;
    }

    /**
     * Sets the renegotiation end date.
     *
     * @param renegotiationEndDate the renegotiation end date
     */
    public final void setRenegotiationEndDate(final Date renegotiationEndDate) {
        this.renegotiationEndDate = renegotiationEndDate;
    }

    /**
     * Gets the priceprotection start date.
     *
     * @return the priceprotection start date
     */
    public final Date getPriceprotectionStartDate() {
        return priceprotectionStartDate;
    }

    /**
     * Sets the priceprotection start date.
     *
     * @param priceprotectionStartDate the priceprotection start date
     */
    public final void setPriceprotectionStartDate(final Date priceprotectionStartDate) {
        this.priceprotectionStartDate = priceprotectionStartDate;
    }

    /**
     * Gets the priceprotection end date.
     *
     * @return the priceprotection end date
     */
    public final Date getPriceprotectionEndDate() {
        return priceprotectionEndDate;
    }

    /**
     * Sets the priceprotection end date.
     *
     * @param priceprotectionEndDate the priceprotection end date
     */
    public final void setPriceprotectionEndDate(final Date priceprotectionEndDate) {
        this.priceprotectionEndDate = priceprotectionEndDate;
    }

    public String getAdvanceNoticeDays() {
        return advanceNoticeDays;
    }

    public void setAdvanceNoticeDays(String advanceNoticeDays) {
        this.advanceNoticeDays = advanceNoticeDays;
    }

    /**
     * Gets the advance notice days.
     *
     * @return the advance notice days
     */
    /**
     * Gets the inside owner.
     *
     * @return the inside owner
     */
    public final String getInsideOwner() {
        return insideOwner;
    }

    /**
     * Sets the inside owner.
     *
     * @param insideOwner the inside owner
     */
    public final void setInsideOwner(final String insideOwner) {
        this.insideOwner = insideOwner;
    }

    /**
     * Gets the inside phone.
     *
     * @return the inside phone
     */
    public final String getInsidePhone() {
        return insidePhone;
    }

    /**
     * Sets the inside phone.
     *
     * @param insidePhone the inside phone
     */
    public final void setInsidePhone(final String insidePhone) {
        this.insidePhone = insidePhone;
    }

    /**
     * Gets the inside author.
     *
     * @return the inside author
     */
    public final String getInsideAuthor() {
        return insideAuthor;
    }

    /**
     * Sets the inside author.
     *
     * @param insideAuthor the inside author
     */
    public final void setInsideAuthor(final String insideAuthor) {
        this.insideAuthor = insideAuthor;
    }

    /**
     * Gets the inside additional.
     *
     * @return the inside additional
     */
    public final String getInsideAdditional() {
        return insideAdditional;
    }

    /**
     * Sets the inside additional.
     *
     * @param insideAdditional the inside additional
     */
    public final void setInsideAdditional(final String insideAdditional) {
        this.insideAdditional = insideAdditional;
    }

    /**
     * Gets the inside additional name.
     *
     * @return the inside additional name
     */
    public final String getInsideAdditionalName() {
        return insideAdditionalName;
    }

    /**
     * Sets the inside additional name.
     *
     * @param insideAdditionalName the inside additional name
     */
    public final void setInsideAdditionalName(final String insideAdditionalName) {
        this.insideAdditionalName = insideAdditionalName;
    }

    /**
     * Gets the inside additional phone.
     *
     * @return the inside additional phone
     */
    public final String getInsideAdditionalPhone() {
        return insideAdditionalPhone;
    }

    /**
     * Sets the inside additional phone.
     *
     * @param insideAdditionalPhone the inside additional phone
     */
    public final void setInsideAdditionalPhone(final String insideAdditionalPhone) {
        this.insideAdditionalPhone = insideAdditionalPhone;
    }

    /**
     * Gets the outside owner.
     *
     * @return the outside owner
     */
    public final String getOutsideOwner() {
        return outsideOwner;
    }

    /**
     * Sets the outside owner.
     *
     * @param outsideOwner the outside owner
     */
    public final void setOutsideOwner(final String outsideOwner) {
        this.outsideOwner = outsideOwner;
    }

    /**
     * Gets the outside phone.
     *
     * @return the outside phone
     */
    public final String getOutsidePhone() {
        return outsidePhone;
    }

    /**
     * Sets the outside phone.
     *
     * @param outsidePhone the outside phone
     */
    public final void setOutsidePhone(final String outsidePhone) {
        this.outsidePhone = outsidePhone;
    }

    /**
     * Gets the outside author.
     *
     * @return the outside author
     */
    public final String getOutsideAuthor() {
        return outsideAuthor;
    }

    /**
     * Sets the outside author.
     *
     * @param outsideAuthor the outside author
     */
    public final void setOutsideAuthor(final String outsideAuthor) {
        this.outsideAuthor = outsideAuthor;
    }

    /**
     * Gets the outside additional.
     *
     * @return the outside additional
     */
    public final String getOutsideAdditional() {
        return outsideAdditional;
    }

    /**
     * Sets the outside additional.
     *
     * @param outsideAdditional the outside additional
     */
    public final void setOutsideAdditional(final String outsideAdditional) {
        this.outsideAdditional = outsideAdditional;
    }

    /**
     * Gets the outside additional name.
     *
     * @return the outside additional name
     */
    public final String getOutsideAdditionalName() {
        return outsideAdditionalName;
    }

    /**
     * Sets the outside additional name.
     *
     * @param outsideAdditionalName the outside additional name
     */
    public final void setOutsideAdditionalName(final String outsideAdditionalName) {
        this.outsideAdditionalName = outsideAdditionalName;
    }

    /**
     * Gets the outside additional phone.
     *
     * @return the outside additional phone
     */
    public final String getOutsideAdditionalPhone() {
        return outsideAdditionalPhone;
    }

    /**
     * Sets the outside additional phone.
     *
     * @param outsideAdditionalPhone the outside additional phone
     */
    public final void setOutsideAdditionalPhone(final String outsideAdditionalPhone) {
        this.outsideAdditionalPhone = outsideAdditionalPhone;
    }

    /**
     * Gets the affiliated contract info.
     *
     * @return the affiliated contract info
     */
    public final String getAffiliatedContractInfo() {
        return affiliatedContractInfo;
    }

    /**
     * Sets the affiliated contract info.
     *
     * @param affiliatedContractInfo the affiliated contract info
     */
    public final void setAffiliatedContractInfo(final String affiliatedContractInfo) {
        this.affiliatedContractInfo = affiliatedContractInfo;
    }

    /**
     * Gets the shipping terms.
     *
     * @return the shipping terms
     */
    public final String getShippingTerms() {
        return shippingTerms;
    }

    /**
     * Sets the shipping terms.
     *
     * @param shippingTerms the shipping terms
     */
    public final void setShippingTerms(final String shippingTerms) {
        this.shippingTerms = shippingTerms;
    }

    /**
     * Gets the proposed start date.
     *
     * @return the proposed start date
     */
    public final Date getProposedStartDate() {
        return proposedStartDate;
    }

    /**
     * Sets the proposed start date.
     *
     * @param proposedStartDate the proposed start date
     */
    public final void setProposedStartDate(final Date proposedStartDate) {
        this.proposedStartDate = proposedStartDate;
    }

    /**
     * Gets the proposed end date.
     *
     * @return the proposed end date
     */
    public final Date getProposedEndDate() {
        return proposedEndDate;
    }

    /**
     * Sets the proposed end date.
     *
     * @param proposedEndDate the proposed end date
     */
    public final void setProposedEndDate(final Date proposedEndDate) {
        this.proposedEndDate = proposedEndDate;
    }

    /**
     * Gets the original start date.
     *
     * @return the original start date
     */
    public final Date getOriginalStartDate() {
        return originalStartDate;
    }

    /**
     * Sets the original start date.
     *
     * @param originalStartDate the original start date
     */
    public final void setOriginalStartDate(final Date originalStartDate) {
        this.originalStartDate = originalStartDate;
    }

    /**
     * Gets the original end date.
     *
     * @return the original end date
     */
    public final Date getOriginalEndDate() {
        return originalEndDate;
    }

    /**
     * Sets the original end date.
     *
     * @param originalEndDate the original end date
     */
    public final void setOriginalEndDate(final Date originalEndDate) {
        this.originalEndDate = originalEndDate;
    }

    /**
     * Gets the last updated date.
     *
     * @return the last updated date
     */
    public final Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the last updated date.
     *
     * @param lastUpdatedDate the last updated date
     */
    public final void setLastUpdatedDate(final Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * Gets the price escalation clause.
     *
     * @return the price escalation clause
     */
    public final String getPriceEscalationClause() {
        return priceEscalationClause;
    }

    /**
     * Sets the price escalation clause.
     *
     * @param priceEscalationClause the price escalation clause
     */
    public final void setPriceEscalationClause(final String priceEscalationClause) {
        this.priceEscalationClause = priceEscalationClause;
    }

    /**
     * Gets the exempt from low price.
     *
     * @return the exempt from low price
     */
    public final String getExemptFromLowPrice() {
        return exemptFromLowPrice;
    }

    /**
     * Sets the exempt from low price.
     *
     * @param exemptFromLowPrice the exempt from low price
     */
    public final void setExemptFromLowPrice(final String exemptFromLowPrice) {
        this.exemptFromLowPrice = exemptFromLowPrice;
    }

    /**
     * Gets the cancellation clause.
     *
     * @return the cancellation clause
     */
    public final String getCancellationClause() {
        return cancellationClause;
    }

    /**
     * Sets the cancellation clause.
     *
     * @param cancellationClause the cancellation clause
     */
    public final void setCancellationClause(final String cancellationClause) {
        this.cancellationClause = cancellationClause;
    }

    /**
     * Gets the most favored nation.
     *
     * @return the most favored nation
     */
    public final String getMostFavoredNation() {
        return mostFavoredNation;
    }

    /**
     * Sets the most favored nation.
     *
     * @param mostFavoredNation the most favored nation
     */
    public final void setMostFavoredNation(final String mostFavoredNation) {
        this.mostFavoredNation = mostFavoredNation;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public final String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category
     */
    public final void setCategory(final String category) {
        this.category = category;
    }

    /**
     * Gets the currency.
     *
     * @return the currency
     */
    public final String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency.
     *
     * @param currency the currency
     */
    public final void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * Gets the minimum order.
     *
     * @return the minimum order
     */
    public final String getMinimumOrder() {
        return minimumOrder;
    }

    /**
     * Sets the minimum order.
     *
     * @param minimumOrder the minimum order
     */
    public final void setMinimumOrder(final String minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public final String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the created by
     */
    public final void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public final Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public final void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public final String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modified by
     */
    public final void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public final Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public final void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the batch id.
     *
     * @return the batch id
     */
    public final String getBatchId() {
        return batchId;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the batch id
     */
    public final void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Gets the inbound status.
     *
     * @return the inbound status
     */
    public final String getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inbound status
     */
    public final void setInboundStatus(final String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the record lock status.
     *
     * @return the record lock status
     */
    public final String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the record lock status
     */
    public final void setRecordLockStatus(final String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * Gets the contract alias master list.
     *
     * @return the contract alias master list
     */
    public final List<ContractAliasMasterDTO> getContracAliasMasterList() {
        return contracAliasMasterList;
    }

    /**
     * Sets the contract alias master list.
     *
     * @param contracAliasMasterList the contract alias master list
     */
    public final void setContracAliasMasterList(final List<ContractAliasMasterDTO> contracAliasMasterList) {
        this.contracAliasMasterList = contracAliasMasterList;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public HelperDTO getContractType() {
        return contractType;
    }

    public void setContractType(HelperDTO contractType) {
        this.contractType = contractType;
    }

    public HelperDTO getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(HelperDTO contractStatus) {
        this.contractStatus = contractStatus;
    }

    public HelperDTO getDocType() {
        return docType;
    }

    public void setDocType(HelperDTO docType) {
        this.docType = docType;
    }

    public HelperDTO getDocClass() {
        return docClass;
    }

    public void setDocClass(HelperDTO docClass) {
        this.docClass = docClass;
    }

    public HelperDTO getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(HelperDTO tradeClass) {
        this.tradeClass = tradeClass;
    }

    public HelperDTO getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(HelperDTO awardStatus) {
        this.awardStatus = awardStatus;
    }

    public HelperDTO getPriceResetIndicator() {
        return priceResetIndicator;
    }

    public void setPriceResetIndicator(HelperDTO priceResetIndicator) {
        this.priceResetIndicator = priceResetIndicator;
    }

    public HelperDTO getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(HelperDTO paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(String companyGroup) {
        this.companyGroup = companyGroup;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

}
