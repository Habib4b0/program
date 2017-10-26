package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StChAssumptionsPK implements Comparable<StChAssumptionsPK>,
    Serializable {
    public int projectionDetailsSid;
    public int userId;
    public int quarter;
    public int year;
    public String stChAssumptionsSid;
    public int chAssumptionsSid;
    public int sessionId;

    public StChAssumptionsPK() {
    }

    public StChAssumptionsPK(int projectionDetailsSid, int userId, int quarter,
        int year, String stChAssumptionsSid, int chAssumptionsSid, int sessionId) {
        this.projectionDetailsSid = projectionDetailsSid;
        this.userId = userId;
        this.quarter = quarter;
        this.year = year;
        this.stChAssumptionsSid = stChAssumptionsSid;
        this.chAssumptionsSid = chAssumptionsSid;
        this.sessionId = sessionId;
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

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStChAssumptionsSid() {
        return stChAssumptionsSid;
    }

    public void setStChAssumptionsSid(String stChAssumptionsSid) {
        this.stChAssumptionsSid = stChAssumptionsSid;
    }

    public int getChAssumptionsSid() {
        return chAssumptionsSid;
    }

    public void setChAssumptionsSid(int chAssumptionsSid) {
        this.chAssumptionsSid = chAssumptionsSid;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int compareTo(StChAssumptionsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

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

        if (quarter < pk.quarter) {
            value = -1;
        } else if (quarter > pk.quarter) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (year < pk.year) {
            value = -1;
        } else if (year > pk.year) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = stChAssumptionsSid.compareTo(pk.stChAssumptionsSid);

        if (value != 0) {
            return value;
        }

        if (chAssumptionsSid < pk.chAssumptionsSid) {
            value = -1;
        } else if (chAssumptionsSid > pk.chAssumptionsSid) {
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

        if (!(obj instanceof StChAssumptionsPK)) {
            return false;
        }

        StChAssumptionsPK pk = (StChAssumptionsPK) obj;

        if ((projectionDetailsSid == pk.projectionDetailsSid) &&
                (userId == pk.userId) && (quarter == pk.quarter) &&
                (year == pk.year) &&
                (stChAssumptionsSid.equals(pk.stChAssumptionsSid)) &&
                (chAssumptionsSid == pk.chAssumptionsSid) &&
                (sessionId == pk.sessionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(projectionDetailsSid) + String.valueOf(userId) +
        String.valueOf(quarter) + String.valueOf(year) +
        String.valueOf(stChAssumptionsSid) + String.valueOf(chAssumptionsSid) +
        String.valueOf(sessionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append(StringPool.OPEN_CURLY_BRACE);

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
        sb.append("quarter");
        sb.append(StringPool.EQUAL);
        sb.append(quarter);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("year");
        sb.append(StringPool.EQUAL);
        sb.append(year);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("stChAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(stChAssumptionsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("chAssumptionsSid");
        sb.append(StringPool.EQUAL);
        sb.append(chAssumptionsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
