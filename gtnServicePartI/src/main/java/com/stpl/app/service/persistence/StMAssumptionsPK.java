package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StMAssumptionsPK implements Comparable<StMAssumptionsPK>,
    Serializable {
    public int projYear;
    public int userId;
    public String stMAssumptionsSid;
    public int projectionPeriod;
    public int projectionDetailsSid;
    public int sessionId;
    public int mAssumptionsSid;

    public StMAssumptionsPK() {
    }

    public StMAssumptionsPK(int projYear, int userId, String stMAssumptionsSid,
        int projectionPeriod, int projectionDetailsSid, int sessionId,
        int mAssumptionsSid) {
        this.projYear = projYear;
        this.userId = userId;
        this.stMAssumptionsSid = stMAssumptionsSid;
        this.projectionPeriod = projectionPeriod;
        this.projectionDetailsSid = projectionDetailsSid;
        this.sessionId = sessionId;
        this.mAssumptionsSid = mAssumptionsSid;
    }

    public int getProjYear() {
        return projYear;
    }

    public void setProjYear(int projYear) {
        this.projYear = projYear;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStMAssumptionsSid() {
        return stMAssumptionsSid;
    }

    public void setStMAssumptionsSid(String stMAssumptionsSid) {
        this.stMAssumptionsSid = stMAssumptionsSid;
    }

    public int getProjectionPeriod() {
        return projectionPeriod;
    }

    public void setProjectionPeriod(int projectionPeriod) {
        this.projectionPeriod = projectionPeriod;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getMAssumptionsSid() {
        return mAssumptionsSid;
    }

    public void setMAssumptionsSid(int mAssumptionsSid) {
        this.mAssumptionsSid = mAssumptionsSid;
    }

    @Override
    public int compareTo(StMAssumptionsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (projYear < pk.projYear) {
            value = -1;
        } else if (projYear > pk.projYear) {
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

        value = stMAssumptionsSid.compareTo(pk.stMAssumptionsSid);

        if (value != 0) {
            return value;
        }

        if (projectionPeriod < pk.projectionPeriod) {
            value = -1;
        } else if (projectionPeriod > pk.projectionPeriod) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (projectionDetailsSid < pk.projectionDetailsSid) {
            value = -1;
        } else if (projectionDetailsSid > pk.projectionDetailsSid) {
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

        if (mAssumptionsSid < pk.mAssumptionsSid) {
            value = -1;
        } else if (mAssumptionsSid > pk.mAssumptionsSid) {
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

        if (!(obj instanceof StMAssumptionsPK)) {
            return false;
        }

        StMAssumptionsPK pk = (StMAssumptionsPK) obj;

        if ((projYear == pk.projYear) && (userId == pk.userId) &&
                (stMAssumptionsSid.equals(pk.stMAssumptionsSid)) &&
                (projectionPeriod == pk.projectionPeriod) &&
                (projectionDetailsSid == pk.projectionDetailsSid) &&
                (sessionId == pk.sessionId) &&
                (mAssumptionsSid == pk.mAssumptionsSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(projYear) + String.valueOf(userId) +
        String.valueOf(stMAssumptionsSid) + String.valueOf(projectionPeriod) +
        String.valueOf(projectionDetailsSid) + String.valueOf(sessionId) +
        String.valueOf(mAssumptionsSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("projYear");
        sb.append(StringPool.EQUAL);
        sb.append(projYear);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("stMAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(stMAssumptionsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("projectionPeriod");
        sb.append(StringPool.EQUAL);
        sb.append(projectionPeriod);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("projectionDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(projectionDetailsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("mAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(mAssumptionsSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
