package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StNmAssumptionsPK implements Comparable<StNmAssumptionsPK>,
    Serializable {
    public int projectionPeriod;
    public int nmAssumptionsSid;
    public int projectionDetailsSid;
    public int userId;
    public String stNmAssumptionsSid;
    public int sessionId;

    public StNmAssumptionsPK() {
    }

    public StNmAssumptionsPK(int projectionPeriod, int nmAssumptionsSid,
        int projectionDetailsSid, int userId, String stNmAssumptionsSid,
        int sessionId) {
        this.projectionPeriod = projectionPeriod;
        this.nmAssumptionsSid = nmAssumptionsSid;
        this.projectionDetailsSid = projectionDetailsSid;
        this.userId = userId;
        this.stNmAssumptionsSid = stNmAssumptionsSid;
        this.sessionId = sessionId;
    }

    public int getProjectionPeriod() {
        return projectionPeriod;
    }

    public void setProjectionPeriod(int projectionPeriod) {
        this.projectionPeriod = projectionPeriod;
    }

    public int getNmAssumptionsSid() {
        return nmAssumptionsSid;
    }

    public void setNmAssumptionsSid(int nmAssumptionsSid) {
        this.nmAssumptionsSid = nmAssumptionsSid;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStNmAssumptionsSid() {
        return stNmAssumptionsSid;
    }

    public void setStNmAssumptionsSid(String stNmAssumptionsSid) {
        this.stNmAssumptionsSid = stNmAssumptionsSid;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int compareTo(StNmAssumptionsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

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

        if (nmAssumptionsSid < pk.nmAssumptionsSid) {
            value = -1;
        } else if (nmAssumptionsSid > pk.nmAssumptionsSid) {
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

        value = stNmAssumptionsSid.compareTo(pk.stNmAssumptionsSid);

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

        if (!(obj instanceof StNmAssumptionsPK)) {
            return false;
        }

        StNmAssumptionsPK pk = (StNmAssumptionsPK) obj;

        if ((projectionPeriod == pk.projectionPeriod) &&
                (nmAssumptionsSid == pk.nmAssumptionsSid) &&
                (projectionDetailsSid == pk.projectionDetailsSid) &&
                (userId == pk.userId) &&
                (stNmAssumptionsSid.equals(pk.stNmAssumptionsSid)) &&
                (sessionId == pk.sessionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(projectionPeriod) +
        String.valueOf(nmAssumptionsSid) +
        String.valueOf(projectionDetailsSid) + String.valueOf(userId) +
        String.valueOf(stNmAssumptionsSid) + String.valueOf(sessionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(30);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("projectionPeriod");
        sb.append(StringPool.EQUAL);
        sb.append(projectionPeriod);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("nmAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(nmAssumptionsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("projectionDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(projectionDetailsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("stNmAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(stNmAssumptionsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
