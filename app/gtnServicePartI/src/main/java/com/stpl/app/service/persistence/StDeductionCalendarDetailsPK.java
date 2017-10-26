package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StDeductionCalendarDetailsPK implements Comparable<StDeductionCalendarDetailsPK>,
    Serializable {
    public int periodSid;
    public int companyMasterSid;
    public int userId;
    public int itemMasterSid;
    public String sessionId;

    public StDeductionCalendarDetailsPK() {
    }

    public StDeductionCalendarDetailsPK(int periodSid, int companyMasterSid,
        int userId, int itemMasterSid, String sessionId) {
        this.periodSid = periodSid;
        this.companyMasterSid = companyMasterSid;
        this.userId = userId;
        this.itemMasterSid = itemMasterSid;
        this.sessionId = sessionId;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int compareTo(StDeductionCalendarDetailsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (periodSid < pk.periodSid) {
            value = -1;
        } else if (periodSid > pk.periodSid) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (companyMasterSid < pk.companyMasterSid) {
            value = -1;
        } else if (companyMasterSid > pk.companyMasterSid) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (userId < pk.userId) {
            value = -1;
        } else if (userId > pk.userId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (itemMasterSid < pk.itemMasterSid) {
            value = -1;
        } else if (itemMasterSid > pk.itemMasterSid) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = sessionId.compareTo(pk.sessionId);

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

        if (!(obj instanceof StDeductionCalendarDetailsPK)) {
            return false;
        }

        StDeductionCalendarDetailsPK pk = (StDeductionCalendarDetailsPK) obj;

        if ((periodSid == pk.periodSid) &&
                (companyMasterSid == pk.companyMasterSid) &&
                (userId == pk.userId) && (itemMasterSid == pk.itemMasterSid) &&
                (sessionId.equals(pk.sessionId))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(periodSid) + String.valueOf(companyMasterSid) +
        String.valueOf(userId) + String.valueOf(itemMasterSid) +
        String.valueOf(sessionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("periodSid");
        sb.append(StringPool.EQUAL);
        sb.append(periodSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("companyMasterSid");
        sb.append(StringPool.EQUAL);
        sb.append(companyMasterSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("itemMasterSid");
        sb.append(StringPool.EQUAL);
        sb.append(itemMasterSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
