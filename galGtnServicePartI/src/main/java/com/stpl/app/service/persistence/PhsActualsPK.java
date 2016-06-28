package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PhsActualsPK implements Comparable<PhsActualsPK>, Serializable {
    public int periodSid;
    public String priceType;
    public int naProjDetailsSid;

    public PhsActualsPK() {
    }

    public PhsActualsPK(int periodSid, String priceType, int naProjDetailsSid) {
        this.periodSid = periodSid;
        this.priceType = priceType;
        this.naProjDetailsSid = naProjDetailsSid;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getNaProjDetailsSid() {
        return naProjDetailsSid;
    }

    public void setNaProjDetailsSid(int naProjDetailsSid) {
        this.naProjDetailsSid = naProjDetailsSid;
    }

    @Override
    public int compareTo(PhsActualsPK pk) {
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

        value = priceType.compareTo(pk.priceType);

        if (value != 0) {
            return value;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PhsActualsPK)) {
            return false;
        }

        PhsActualsPK pk = (PhsActualsPK) obj;

        if ((periodSid == pk.periodSid) && (priceType.equals(pk.priceType)) &&
                (naProjDetailsSid == pk.naProjDetailsSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(periodSid) + String.valueOf(priceType) +
        String.valueOf(naProjDetailsSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("periodSid");
        sb.append(StringPool.EQUAL);
        sb.append(periodSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("priceType");
        sb.append(StringPool.EQUAL);
        sb.append(priceType);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("naProjDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(naProjDetailsSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
