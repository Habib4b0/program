package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ChActualSalesPK implements Comparable<ChActualSalesPK>,
    Serializable {
    public int periodSid;
    public int projectionDetailsSid;

    public ChActualSalesPK() {
    }

    public ChActualSalesPK(int periodSid, int projectionDetailsSid) {
        this.periodSid = periodSid;
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    @Override
    public int compareTo(ChActualSalesPK pk) {
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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChActualSalesPK)) {
            return false;
        }

        ChActualSalesPK pk = (ChActualSalesPK) obj;

        if ((periodSid == pk.periodSid) &&
                (projectionDetailsSid == pk.projectionDetailsSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(periodSid) +
        String.valueOf(projectionDetailsSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("periodSid");
        sb.append(StringPool.EQUAL);
        sb.append(periodSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("projectionDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(projectionDetailsSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
