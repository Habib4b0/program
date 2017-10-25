/*
 * 
 */
package com.stpl.app.gtnforecasting.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class AssumptionPVDTO.
 */
public class AssumptionPVDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1590223087588536173L;

    /**
     * The projection period pv.
     */
    private String projectionPeriodPV = StringUtils.EMPTY;

    /**
     * The customer pv.
     */
    private String contractHolder = StringUtils.EMPTY;

    /**
     * The contract pv.
     */
    private String costomer = StringUtils.EMPTY;

    /**
     * Therapeutic class pv.
     */
    private String therapeuticClassPV = StringUtils.EMPTY;

    /**
     * The brand pv.
     */
    private String brandPV = StringUtils.EMPTY;

    /**
     * The ndc pv.
     */
    private String ndcPV = StringUtils.EMPTY;

    /**
     * The gross sale prior pv.
     */
    private String grossSalePriorPV = StringUtils.EMPTY;

    /**
     * The gross sale projected pv.
     */
    private String grossSaleProjectedPV = StringUtils.EMPTY;

    /**
     * The gross sale change pv.
     */
    private String grossSaleChangePV = StringUtils.EMPTY;

    /**
     * The total discount prior pv.
     */
    private String totalDiscountPriorPV = StringUtils.EMPTY;

    /**
     * The total discount projected pv.
     */
    private String totalDiscountProjectedPV = StringUtils.EMPTY;

    /**
     * The total discount change pv.
     */
    private String totalDiscountChangePV = StringUtils.EMPTY;

    /**
     * The net sale prior pv.
     */
    private String netSalePriorPV = StringUtils.EMPTY;

    /**
     * The net sale projected pv.
     */
    private String netSaleProjectedPV = StringUtils.EMPTY;

    /**
     * The net sale change pv.
     */
    private String netSaleChangePV = StringUtils.EMPTY;

    /**
     * The reason code pv.
     */
    private String reasonCodePV = StringUtils.EMPTY;

    /**
     * The commentary pv.
     */
    private String commentaryPV = StringUtils.EMPTY;

    /**
     * The cam name pv.
     */
    private String camNamePV = StringUtils.EMPTY;

    /**
     * The pv check.
     */
    private Boolean pvCheck= Boolean.FALSE;

    /**
     * The parent.
     */
    private Boolean parent;

    /**
     * The segment.
     */
    private String segment = StringUtils.EMPTY;

    /**
     * The marketType.
     */
    private String marketType = StringUtils.EMPTY;

    /**
     * The userId.
     */
    private Integer userId;

    /**
     * The sessionId.
     */
    private Integer sessionId;

    private String childPeriod = StringUtils.EMPTY;

    private String deleteIndicator = StringUtils.EMPTY;

    private Integer contractHolderSid;

    private Integer companySid;

    private Integer brandSid;

    private Integer matketTypeSid;

    private String segmentId;
    
    private String oldReasonCode = StringUtils.EMPTY;
    
    private String oldCommentary = StringUtils.EMPTY;
    
    private String childCamId=StringUtils.EMPTY;

    public String getChildCamId() {
        return childCamId;
    }

    public void setChildCamId(String childCamId) {
        this.childCamId = childCamId;
    }
    
    public String getOldReasonCode() {
        return oldReasonCode;
    }

    public void setOldReasonCode(String oldReasonCode) {
        this.oldReasonCode = oldReasonCode;
    }

    public String getOldCommentary() {
        return oldCommentary;
    }

    public void setOldCommentary(String oldCommentary) {
        this.oldCommentary = oldCommentary;
    }
    
    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getCostomer() {
        return costomer;
    }

    public void setCostomer(String costomer) {
        this.costomer = costomer;
    }

    public Integer getContractHolderSid() {
        return contractHolderSid;
    }

    public void setContractHolderSid(Integer contractHolderSid) {
        this.contractHolderSid = contractHolderSid;
    }
    
    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public Integer getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(Integer brandSid) {
        this.brandSid = brandSid;
    }

    public Integer getMatketTypeSid() {
        return matketTypeSid;
    }

    public void setMatketTypeSid(Integer matketTypeSid) {
        this.matketTypeSid = matketTypeSid;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public String getDeleteIndicator() {
        return deleteIndicator;
    }

    public void setDeleteIndicator(String deleteIndicator) {
        this.deleteIndicator = deleteIndicator;
    }

    public String getChildPeriod() {
        return childPeriod;
    }

    public void setChildPeriod(String childPeriod) {
        this.childPeriod = childPeriod;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Boolean getPvCheck() {
        return pvCheck;
    }

    public void setPvCheck(Boolean pvCheck) {
        this.pvCheck = pvCheck;
    }

    /**
     * Gets the projection period pv.
     *
     * @return the projection period pv
     */
    public String getProjectionPeriodPV() {
        return projectionPeriodPV;
    }

    /**
     * Sets the projection period pv.
     *
     * @param projectionPeriodPV the new projection period pv
     */
    public void setProjectionPeriodPV(String projectionPeriodPV) {
        this.projectionPeriodPV = projectionPeriodPV;
    }

    /**
     * Gets the therapeutic class pv.
     *
     * @return the therapeutic class pv
     */
    public String getTherapeuticClassPV() {
        return therapeuticClassPV;
    }

    /**
     * Sets the therapeutic class pv.
     *
     * @param therapeuticClassPV the new therapeutic class pv
     */
    public void setTherapeuticClassPV(String therapeuticClassPV) {
        this.therapeuticClassPV = therapeuticClassPV;
    }

    /**
     * Gets the brand pv.
     *
     * @return the brand pv
     */
    public String getBrandPV() {
        return brandPV;
    }

    /**
     * Sets the brand pv.
     *
     * @param brandPV the new brand pv
     */
    public void setBrandPV(String brandPV) {
        this.brandPV = brandPV;
    }

    /**
     * Gets the ndc pv.
     *
     * @return the ndc pv
     */
    public String getNdcPV() {
        return ndcPV;
    }

    /**
     * Sets the ndc pv.
     *
     * @param ndcPV the new ndc pv
     */
    public void setNdcPV(String ndcPV) {
        this.ndcPV = ndcPV;
    }

    /**
     * Gets the gross sale prior pv.
     *
     * @return the gross sale prior pv
     */
    public String getGrossSalePriorPV() {
        return grossSalePriorPV;
    }

    /**
     * Sets the gross sale prior pv.
     *
     * @param grossSalePriorPV the new gross sale prior pv
     */
    public void setGrossSalePriorPV(String grossSalePriorPV) {
        this.grossSalePriorPV = grossSalePriorPV;
    }

    /**
     * Gets the gross sale projected pv.
     *
     * @return the gross sale projected pv
     */
    public String getGrossSaleProjectedPV() {
        return grossSaleProjectedPV;
    }

    /**
     * Sets the gross sale projected pv.
     *
     * @param grossSaleProjectedPV the new gross sale projected pv
     */
    public void setGrossSaleProjectedPV(String grossSaleProjectedPV) {
        this.grossSaleProjectedPV = grossSaleProjectedPV;
    }

    /**
     * Gets the gross sale change pv.
     *
     * @return the gross sale change pv
     */
    public String getGrossSaleChangePV() {
        return grossSaleChangePV;
    }

    /**
     * Sets the gross sale change pv.
     *
     * @param grossSaleChangePV the new gross sale change pv
     */
    public void setGrossSaleChangePV(String grossSaleChangePV) {
        this.grossSaleChangePV = grossSaleChangePV;
    }

    /**
     * Gets the total discount prior pv.
     *
     * @return the total discount prior pv
     */
    public String getTotalDiscountPriorPV() {
        return totalDiscountPriorPV;
    }

    /**
     * Sets the total discount prior pv.
     *
     * @param totalDiscountPriorPV the new total discount prior pv
     */
    public void setTotalDiscountPriorPV(String totalDiscountPriorPV) {
        this.totalDiscountPriorPV = totalDiscountPriorPV;
    }

    /**
     * Gets the total discount projected pv.
     *
     * @return the total discount projected pv
     */
    public String getTotalDiscountProjectedPV() {
        return totalDiscountProjectedPV;
    }

    /**
     * Sets the total discount projected pv.
     *
     * @param totalDiscountProjectedPV the new total discount projected pv
     */
    public void setTotalDiscountProjectedPV(String totalDiscountProjectedPV) {
        this.totalDiscountProjectedPV = totalDiscountProjectedPV;
    }

    /**
     * Gets the total discount change pv.
     *
     * @return the total discount change pv
     */
    public String getTotalDiscountChangePV() {
        return totalDiscountChangePV;
    }

    /**
     * Sets the total discount change pv.
     *
     * @param totalDiscountChangePV the new total discount change pv
     */
    public void setTotalDiscountChangePV(String totalDiscountChangePV) {
        this.totalDiscountChangePV = totalDiscountChangePV;
    }

    /**
     * Gets the net sale prior pv.
     *
     * @return the net sale prior pv
     */
    public String getNetSalePriorPV() {
        return netSalePriorPV;
    }

    /**
     * Sets the net sale prior pv.
     *
     * @param netSalePriorPV the new net sale prior pv
     */
    public void setNetSalePriorPV(String netSalePriorPV) {
        this.netSalePriorPV = netSalePriorPV;
    }

    /**
     * Gets the net sale projected pv.
     *
     * @return the net sale projected pv
     */
    public String getNetSaleProjectedPV() {
        return netSaleProjectedPV;
    }

    /**
     * Sets the net sale projected pv.
     *
     * @param netSaleProjectedPV the new net sale projected pv
     */
    public void setNetSaleProjectedPV(String netSaleProjectedPV) {
        this.netSaleProjectedPV = netSaleProjectedPV;
    }

    /**
     * Gets the net sale change pv.
     *
     * @return the net sale change pv
     */
    public String getNetSaleChangePV() {
        return netSaleChangePV;
    }

    /**
     * Sets the net sale change pv.
     *
     * @param netSaleChangePV the new net sale change pv
     */
    public void setNetSaleChangePV(String netSaleChangePV) {
        this.netSaleChangePV = netSaleChangePV;
    }

    /**
     * Gets the reason code pv.
     *
     * @return the reason code pv
     */
    public String getReasonCodePV() {
        return reasonCodePV;
    }

    /**
     * Sets the reason code pv.
     *
     * @param reasonCodePV the new reason code pv
     */
    public void setReasonCodePV(String reasonCodePV) {
        this.reasonCodePV = reasonCodePV;
    }

    /**
     * Gets the commentary pv.
     *
     * @return the commentary pv
     */
    public String getCommentaryPV() {
        return commentaryPV;
    }

    /**
     * Sets the commentary pv.
     *
     * @param commentaryPV the new commentary pv
     */
    public void setCommentaryPV(String commentaryPV) {
        this.commentaryPV = commentaryPV;
    }

    /**
     * Gets the cam name pv.
     *
     * @return the cam name pv
     */
    public String getCamNamePV() {
        return camNamePV;
    }

    /**
     * Sets the cam name pv.
     *
     * @param camNamePV the new cam name pv
     */
    public void setCamNamePV(String camNamePV) {
        this.camNamePV = camNamePV;
    }

}
