/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import com.stpl.app.util.CommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Rohit.Vignesh
 */
public class AdjustmentDetailsDTO {

   private static final Logger LOGGER = Logger.getLogger(AdjustmentDetailsDTO.class);
    private boolean check_Record;
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private Date createdDate;
    private String modifiedBy = StringUtils.EMPTY;
    private Date modifiedDate;
    private HelperDTO transactionLevel = new HelperDTO();
    private String viewCreatedBy = StringUtils.EMPTY;
    private boolean addUpdateFlag = false;
    private List<SortByColumn> sortByColumn;
    private Set<Container.Filter> filters;
    private int start;
    private int offset;
    private boolean count;
    private int viewMasterSid;
    private boolean generate = false;
    private boolean mode = false;

    private Date glDate, creationDate;

    private String creationDateString,
            glDateString,
            redemptionPeriod,
            redemptionPeriodEndDate;

    private int adjustmentType = 0,
            adjustmentLevel = 0,
            companyMasterSid = 0,
            buCompanyMasterSid = 0,
            deductionLevel = 0,
            postingIndicator,
            transactionLevelId,
            accountCategory,
            accountType;
    private String customerNo = StringUtils.EMPTY,
            itemNo = StringUtils.EMPTY,
            customerName = StringUtils.EMPTY,
            itemName = StringUtils.EMPTY,
            brandName = StringUtils.EMPTY,
            contractName = StringUtils.EMPTY,
            accountName = StringUtils.EMPTY,
            workflowId = StringUtils.EMPTY,
            workflowName = StringUtils.EMPTY,
            deductionValue = StringUtils.EMPTY,
            batchId = StringUtils.EMPTY,
            account = StringUtils.EMPTY;
    Map<Integer, String> propertyMap = new HashMap<>();
    Map<String, Object> valueMap = new HashMap<>();
    Map<String, String> dbMap = new HashMap<>();
    private String userId = StringUtils.EMPTY;

    public boolean isCheck_Record() {
        return check_Record;
    }

    public void setCheck_Record(boolean check_Record) {
        this.check_Record = check_Record;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HelperDTO getTransactionLevel() {
        return transactionLevel;
    }

    public void setTransactionLevel(HelperDTO transactionLevel) {
        this.transactionLevel = transactionLevel;
    }

    public String getViewCreatedBy() {
        return viewCreatedBy;
    }

    public void setViewCreatedBy(String viewCreatedBy) {
        this.viewCreatedBy = viewCreatedBy;
    }

    public boolean isAddUpdateFlag() {
        return addUpdateFlag;
    }

    public void setAddUpdateFlag(boolean addUpdateFlag) {
        this.addUpdateFlag = addUpdateFlag;
    }

    public List<SortByColumn> getSortByColumn() {
        return sortByColumn;
    }

    public void setSortByColumn(List<SortByColumn> sortByColumn) {
        this.sortByColumn = sortByColumn;
    }

    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public int getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(int viewMasterSid) {
        this.viewMasterSid = viewMasterSid;
    }

    public boolean isGenerate() {
        return generate;
    }

    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public Map<Integer, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<Integer, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap.putAll(valueMap);
    }

    public Map<String, String> getDbMap() {
        return dbMap;
    }

    public void setDbMap(Map<String, String> dbMap) {
        this.dbMap = dbMap;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreationDateString() {
        return creationDateString;
    }

    public void setCreationDateString(String creationDateString) {
        this.creationDateString = creationDateString;
        setCreationDate(getDateFromString(creationDateString));
    }

    public String getGlDateString() {
        return glDateString;
    }

    public void setGlDateString(String glDateString) {
        this.glDateString = glDateString;
        setGlDate(getDateFromString(glDateString));
    }

    public String getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(String redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    public int getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(int adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getAdjustmentLevel() {
        return adjustmentLevel;
    }

    public void setAdjustmentLevel(int adjustmentLevel) {
        this.adjustmentLevel = adjustmentLevel;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public int getBuCompanyMasterSid() {
        return buCompanyMasterSid;
    }

    public void setBuCompanyMasterSid(int buCompanyMasterSid) {
        this.buCompanyMasterSid = buCompanyMasterSid;
    }

    public int getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(int deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public int getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(int postingIndicator) {
        this.postingIndicator = postingIndicator;
    }

    public int getTransactionLevelId() {
        return transactionLevelId;
    }

    public void setTransactionLevelId(int transactionLevelId) {
        this.transactionLevelId = transactionLevelId;
    }

    public int getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(int accountCategory) {
        this.accountCategory = accountCategory;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        
    }

    public String getRedemptionPeriodEndDate() {
        return redemptionPeriodEndDate;
    }

    public void setRedemptionPeriodEndDate(String redemptionPeriodEndDate) {

        if (redemptionPeriodEndDate != null && !StringUtils.EMPTY.equals(redemptionPeriodEndDate)) {
            Date date = null;
            try {
                SimpleDateFormat parser = new SimpleDateFormat(CommonUtils.YYYYMMDDHHMMSSS);
                SimpleDateFormat formatter = new SimpleDateFormat(CommonUtils.MMDDYYYY);
                date = parser.parse(redemptionPeriodEndDate);
                redemptionPeriodEndDate = formatter.format(date);
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        this.redemptionPeriodEndDate = redemptionPeriodEndDate;
    }

    private Date getDateFromString(String dateString) {
        Date date = null;
        try {
            if (!dateString.isEmpty()) {
                SimpleDateFormat inputFormat = new SimpleDateFormat(CommonUtils.YYYYMMDDHHMMSSS);
                date = inputFormat.parse(dateString);
            }
        } catch (ParseException ex) {
        LOGGER.error(ex.getMessage());

        }
        return date;
    }
    
}
