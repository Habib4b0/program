package com.stpl.app.parttwo.service.persistence;

import com.stpl.portal.kernel.util.DateUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Date;


public class StAdjustmentGtnDetailPK implements Comparable<StAdjustmentGtnDetailPK>,
    Serializable {
    public Date redemptionPeriod;
    public String deductionId;
    public String account;
    public String adjustmentType;
    public String sessionId;
    public String glCompanyId;
    public String itemId;
    public String accountType;
    public String workflowId;
    public String contractId;
    public String businessUnitId;
    public String userId;
    public String companyId;
    public String accountCategory;

    public StAdjustmentGtnDetailPK() {
    }

    public StAdjustmentGtnDetailPK(Date redemptionPeriod, String deductionId,
        String account, String adjustmentType, String sessionId,
        String glCompanyId, String itemId, String accountType,
        String workflowId, String contractId, String businessUnitId,
        String userId, String companyId, String accountCategory) {
        this.redemptionPeriod = redemptionPeriod;
        this.deductionId = deductionId;
        this.account = account;
        this.adjustmentType = adjustmentType;
        this.sessionId = sessionId;
        this.glCompanyId = glCompanyId;
        this.itemId = itemId;
        this.accountType = accountType;
        this.workflowId = workflowId;
        this.contractId = contractId;
        this.businessUnitId = businessUnitId;
        this.userId = userId;
        this.companyId = companyId;
        this.accountCategory = accountCategory;
    }

    public Date getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(Date redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getGlCompanyId() {
        return glCompanyId;
    }

    public void setGlCompanyId(String glCompanyId) {
        this.glCompanyId = glCompanyId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    @Override
    public int compareTo(StAdjustmentGtnDetailPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = DateUtil.compareTo(redemptionPeriod, pk.redemptionPeriod);

        if (value != 0) {
            return value;
        }

        value = deductionId.compareTo(pk.deductionId);

        if (value != 0) {
            return value;
        }

        value = account.compareTo(pk.account);

        if (value != 0) {
            return value;
        }

        value = adjustmentType.compareTo(pk.adjustmentType);

        if (value != 0) {
            return value;
        }

        value = sessionId.compareTo(pk.sessionId);

        if (value != 0) {
            return value;
        }

        value = glCompanyId.compareTo(pk.glCompanyId);

        if (value != 0) {
            return value;
        }

        value = itemId.compareTo(pk.itemId);

        if (value != 0) {
            return value;
        }

        value = accountType.compareTo(pk.accountType);

        if (value != 0) {
            return value;
        }

        value = workflowId.compareTo(pk.workflowId);

        if (value != 0) {
            return value;
        }

        value = contractId.compareTo(pk.contractId);

        if (value != 0) {
            return value;
        }

        value = businessUnitId.compareTo(pk.businessUnitId);

        if (value != 0) {
            return value;
        }

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = companyId.compareTo(pk.companyId);

        if (value != 0) {
            return value;
        }

        value = accountCategory.compareTo(pk.accountCategory);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StAdjustmentGtnDetailPK)) {
            return false;
        }

        StAdjustmentGtnDetailPK pk = (StAdjustmentGtnDetailPK) obj;

        if ((redemptionPeriod.equals(pk.redemptionPeriod)) &&
                (deductionId.equals(pk.deductionId)) &&
                (account.equals(pk.account)) &&
                (adjustmentType.equals(pk.adjustmentType)) &&
                (sessionId.equals(pk.sessionId)) &&
                (glCompanyId.equals(pk.glCompanyId)) &&
                (itemId.equals(pk.itemId)) &&
                (accountType.equals(pk.accountType)) &&
                (workflowId.equals(pk.workflowId)) &&
                (contractId.equals(pk.contractId)) &&
                (businessUnitId.equals(pk.businessUnitId)) &&
                (userId.equals(pk.userId)) && (companyId.equals(pk.companyId)) &&
                (accountCategory.equals(pk.accountCategory))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (redemptionPeriod.toString() + String.valueOf(deductionId) +
        String.valueOf(account) + String.valueOf(adjustmentType) +
        String.valueOf(sessionId) + String.valueOf(glCompanyId) +
        String.valueOf(itemId) + String.valueOf(accountType) +
        String.valueOf(workflowId) + String.valueOf(contractId) +
        String.valueOf(businessUnitId) + String.valueOf(userId) +
        String.valueOf(companyId) + String.valueOf(accountCategory)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(70);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("redemptionPeriod");
        sb.append(StringPool.EQUAL);
        sb.append(redemptionPeriod);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("deductionId");
        sb.append(StringPool.EQUAL);
        sb.append(deductionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("account");
        sb.append(StringPool.EQUAL);
        sb.append(account);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("adjustmentType");
        sb.append(StringPool.EQUAL);
        sb.append(adjustmentType);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("glCompanyId");
        sb.append(StringPool.EQUAL);
        sb.append(glCompanyId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("itemId");
        sb.append(StringPool.EQUAL);
        sb.append(itemId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("accountType");
        sb.append(StringPool.EQUAL);
        sb.append(accountType);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("workflowId");
        sb.append(StringPool.EQUAL);
        sb.append(workflowId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("contractId");
        sb.append(StringPool.EQUAL);
        sb.append(contractId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("businessUnitId");
        sb.append(StringPool.EQUAL);
        sb.append(businessUnitId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("companyId");
        sb.append(StringPool.EQUAL);
        sb.append(companyId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("accountCategory");
        sb.append(StringPool.EQUAL);
        sb.append(accountCategory);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
