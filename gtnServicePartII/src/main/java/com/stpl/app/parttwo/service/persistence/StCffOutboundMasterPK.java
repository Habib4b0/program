package com.stpl.app.parttwo.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StCffOutboundMasterPK implements Comparable<StCffOutboundMasterPK>,
    Serializable {
    public int cffDetailsSid;
    public String sessionId;
    public int rsModelSid;
    public String userId;
    public int periodSid;

    public StCffOutboundMasterPK() {
    }

    public StCffOutboundMasterPK(int cffDetailsSid, String sessionId,
        int rsModelSid, String userId, int periodSid) {
        this.cffDetailsSid = cffDetailsSid;
        this.sessionId = sessionId;
        this.rsModelSid = rsModelSid;
        this.userId = userId;
        this.periodSid = periodSid;
    }

    public int getCffDetailsSid() {
        return cffDetailsSid;
    }

    public void setCffDetailsSid(int cffDetailsSid) {
        this.cffDetailsSid = cffDetailsSid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        this.rsModelSid = rsModelSid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    @Override
    public int compareTo(StCffOutboundMasterPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (cffDetailsSid < pk.cffDetailsSid) {
            value = -1;
        } else if (cffDetailsSid > pk.cffDetailsSid) {
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

        if (rsModelSid < pk.rsModelSid) {
            value = -1;
        } else if (rsModelSid > pk.rsModelSid) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StCffOutboundMasterPK)) {
            return false;
        }

        StCffOutboundMasterPK pk = (StCffOutboundMasterPK) obj;

        if ((cffDetailsSid == pk.cffDetailsSid) &&
                (sessionId.equals(pk.sessionId)) &&
                (rsModelSid == pk.rsModelSid) && (userId.equals(pk.userId)) &&
                (periodSid == pk.periodSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(cffDetailsSid) + String.valueOf(sessionId) +
        String.valueOf(rsModelSid) + String.valueOf(userId) +
        String.valueOf(periodSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("cffDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(cffDetailsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("rsModelSid");
        sb.append(StringPool.EQUAL);
        sb.append(rsModelSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("periodSid");
        sb.append(StringPool.EQUAL);
        sb.append(periodSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
