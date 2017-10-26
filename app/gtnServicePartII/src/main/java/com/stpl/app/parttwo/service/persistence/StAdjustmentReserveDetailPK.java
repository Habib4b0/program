package com.stpl.app.parttwo.service.persistence;

import com.stpl.portal.kernel.util.DateUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Date;


public class StAdjustmentReserveDetailPK implements Comparable<StAdjustmentReserveDetailPK>,
    Serializable {
    public String account;
    public String adjustmentType;
    public String glCompanyName;
    public String sessionId;
    public String accountType;
    public String businessUnitId;
    public String workflowId;
    public String userId;
    public String accountCategory;
    public String brand;
    public Date redemptionPeriod;

    public StAdjustmentReserveDetailPK() {
    }

    public StAdjustmentReserveDetailPK(String account, String adjustmentType,
        String glCompanyName, String sessionId, String accountType,
        String businessUnitId, String workflowId, String userId,
        String accountCategory, String brand, Date redemptionPeriod) {
        this.account = account;
        this.adjustmentType = adjustmentType;
        this.glCompanyName = glCompanyName;
        this.sessionId = sessionId;
        this.accountType = accountType;
        this.businessUnitId = businessUnitId;
        this.workflowId = workflowId;
        this.userId = userId;
        this.accountCategory = accountCategory;
        this.brand = brand;
        this.redemptionPeriod = redemptionPeriod;
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

    public String getGlCompanyName() {
        return glCompanyName;
    }

    public void setGlCompanyName(String glCompanyName) {
        this.glCompanyName = glCompanyName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(Date redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    @Override
    public int compareTo(StAdjustmentReserveDetailPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = account.compareTo(pk.account);

        if (value != 0) {
            return value;
        }

        value = adjustmentType.compareTo(pk.adjustmentType);

        if (value != 0) {
            return value;
        }

        value = glCompanyName.compareTo(pk.glCompanyName);

        if (value != 0) {
            return value;
        }

        value = sessionId.compareTo(pk.sessionId);

        if (value != 0) {
            return value;
        }

        value = accountType.compareTo(pk.accountType);

        if (value != 0) {
            return value;
        }

        value = businessUnitId.compareTo(pk.businessUnitId);

        if (value != 0) {
            return value;
        }

        value = workflowId.compareTo(pk.workflowId);

        if (value != 0) {
            return value;
        }

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = accountCategory.compareTo(pk.accountCategory);

        if (value != 0) {
            return value;
        }

        value = brand.compareTo(pk.brand);

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(redemptionPeriod, pk.redemptionPeriod);

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

        if (!(obj instanceof StAdjustmentReserveDetailPK)) {
            return false;
        }

        StAdjustmentReserveDetailPK pk = (StAdjustmentReserveDetailPK) obj;

        if ((account.equals(pk.account)) &&
                (adjustmentType.equals(pk.adjustmentType)) &&
                (glCompanyName.equals(pk.glCompanyName)) &&
                (sessionId.equals(pk.sessionId)) &&
                (accountType.equals(pk.accountType)) &&
                (businessUnitId.equals(pk.businessUnitId)) &&
                (workflowId.equals(pk.workflowId)) &&
                (userId.equals(pk.userId)) &&
                (accountCategory.equals(pk.accountCategory)) &&
                (brand.equals(pk.brand)) &&
                (redemptionPeriod.equals(pk.redemptionPeriod))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(account) + String.valueOf(adjustmentType) +
        String.valueOf(glCompanyName) + String.valueOf(sessionId) +
        String.valueOf(accountType) + String.valueOf(businessUnitId) +
        String.valueOf(workflowId) + String.valueOf(userId) +
        String.valueOf(accountCategory) + String.valueOf(brand) +
        redemptionPeriod.toString()).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(55);

        sb.append(StringPool.OPEN_CURLY_BRACE);

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
        sb.append("glCompanyName");
        sb.append(StringPool.EQUAL);
        sb.append(glCompanyName);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("accountType");
        sb.append(StringPool.EQUAL);
        sb.append(accountType);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("businessUnitId");
        sb.append(StringPool.EQUAL);
        sb.append(businessUnitId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("workflowId");
        sb.append(StringPool.EQUAL);
        sb.append(workflowId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("accountCategory");
        sb.append(StringPool.EQUAL);
        sb.append(accountCategory);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("brand");
        sb.append(StringPool.EQUAL);
        sb.append(brand);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("redemptionPeriod");
        sb.append(StringPool.EQUAL);
        sb.append(redemptionPeriod);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
