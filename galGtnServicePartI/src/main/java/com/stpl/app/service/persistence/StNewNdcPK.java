package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StNewNdcPK implements Comparable<StNewNdcPK>, Serializable {
    public int naProjDetailsSid;
    public int userId;
    public int itemMasterSid;
    public int sessionId;

    public StNewNdcPK() {
    }

    public StNewNdcPK(int naProjDetailsSid, int userId, int itemMasterSid,
        int sessionId) {
        this.naProjDetailsSid = naProjDetailsSid;
        this.userId = userId;
        this.itemMasterSid = itemMasterSid;
        this.sessionId = sessionId;
    }

    public int getNaProjDetailsSid() {
        return naProjDetailsSid;
    }

    public void setNaProjDetailsSid(int naProjDetailsSid) {
        this.naProjDetailsSid = naProjDetailsSid;
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

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int compareTo(StNewNdcPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (naProjDetailsSid < pk.naProjDetailsSid) {
            value = -1;
        } else if (naProjDetailsSid > pk.naProjDetailsSid) {
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

        if (sessionId < pk.sessionId) {
            value = -1;
        } else if (sessionId > pk.sessionId) {
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

        if (!(obj instanceof StNewNdcPK)) {
            return false;
        }

        StNewNdcPK pk = (StNewNdcPK) obj;

        if ((naProjDetailsSid == pk.naProjDetailsSid) && (userId == pk.userId) &&
                (itemMasterSid == pk.itemMasterSid) &&
                (sessionId == pk.sessionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(naProjDetailsSid) + String.valueOf(userId) +
        String.valueOf(itemMasterSid) + String.valueOf(sessionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(20);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("naProjDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(naProjDetailsSid);

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
