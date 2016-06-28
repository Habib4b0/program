/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import com.stpl.app.customtreecontainer.CustomTreeDTO;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class BaseRateSummaryDTO extends CustomTreeDTO implements Comparator<BaseRateSummaryDTO> {

    private String customerName = StringUtils.EMPTY;
    private String currentRate = StringUtils.EMPTY;
    private String suggestedRate = StringUtils.EMPTY;
    private String accrualRate = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate = StringUtils.EMPTY;
    private String alternateContract = StringUtils.EMPTY;
    private String alternateProduct = StringUtils.EMPTY;
    private String methodology = StringUtils.EMPTY;
    private String reasonCode = StringUtils.EMPTY;
    private String notes = StringUtils.EMPTY;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private Integer parent = 0;
    private String ccpSid = StringUtils.EMPTY;
    private Integer LevelNo = 0;
    private Integer acMasterSid = 0;
    private Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();

    private String customerNo = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String therpeutic = StringUtils.EMPTY;
    private String brandId = StringUtils.EMPTY;
    private String brandName = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    private Integer methodologySid = 0;
    private Object parentId;
    private Integer checkRecordCount = 0;
    private Integer ccpCount = 0;

    public Integer getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
    }

    
    
    
    public Integer getCheckRecordCount() {
        return checkRecordCount;
    }

    public void setCheckRecordCount(Integer checkRecordCount) {
        this.checkRecordCount = checkRecordCount;
    }

    public String getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(String currentRate) {
        this.currentRate = currentRate;
    }

    public String getSuggestedRate() {
        return suggestedRate;
    }

    public void setSuggestedRate(String suggestedRate) {
        this.suggestedRate = suggestedRate;
    }

    public String getAccrualRate() {
        return accrualRate;
    }

    public void setAccrualRate(String accrualRate) {
        this.accrualRate = accrualRate;
    }

    public String getAlternateContract() {
        return alternateContract;
    }

    public void setAlternateContract(String alternateContract) {
        this.alternateContract = alternateContract;
    }

    public String getAlternateProduct() {
        return alternateProduct;
    }

    public void setAlternateProduct(String alternateProduct) {
        this.alternateProduct = alternateProduct;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getCcpSid() {
        return ccpSid;
    }

    public void setCcpSid(String ccpSid) {
        this.ccpSid = ccpSid;
    }

    public Integer getLevelNo() {
        return LevelNo;
    }

    public void setLevelNo(Integer LevelNo) {
        this.LevelNo = LevelNo;
    }

    public int compare(BaseRateSummaryDTO o1, BaseRateSummaryDTO o2) {
        return 0;
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

    public Map<Integer, Map<String, String>> getIdMainMap() {
        return idMainMap;
    }

    public void setIdMainMap(Map<Integer, Map<String, String>> idMainMap) {
        this.idMainMap = idMainMap;
    }

    public Integer getAcMasterSid() {
        return acMasterSid;
    }

    public void setAcMasterSid(Integer acMasterSid) {
        this.acMasterSid = acMasterSid;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
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

    public String getTherpeutic() {
        return therpeutic;
    }

    public void setTherpeutic(String therpeutic) {
        this.therpeutic = therpeutic;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getMethodologySid() {
        return methodologySid;
    }

    public void setMethodologySid(Integer methodologySid) {
        this.methodologySid = methodologySid;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

}
