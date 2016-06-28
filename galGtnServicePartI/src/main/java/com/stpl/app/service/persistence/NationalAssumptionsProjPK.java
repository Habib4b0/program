package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class NationalAssumptionsProjPK implements Comparable<NationalAssumptionsProjPK>,
    Serializable {
    public int periodSid;
    public int itemMasterSid;
    public String priceType;

    public NationalAssumptionsProjPK() {
    }

    public NationalAssumptionsProjPK(int periodSid, int itemMasterSid,
        String priceType) {
        this.periodSid = periodSid;
        this.itemMasterSid = itemMasterSid;
        this.priceType = priceType;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    @Override
    public int compareTo(NationalAssumptionsProjPK pk) {
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

        value = priceType.compareTo(pk.priceType);

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

        if (!(obj instanceof NationalAssumptionsProjPK)) {
            return false;
        }

        NationalAssumptionsProjPK pk = (NationalAssumptionsProjPK) obj;

        if ((periodSid == pk.periodSid) && (itemMasterSid == pk.itemMasterSid) &&
                (priceType.equals(pk.priceType))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(periodSid) + String.valueOf(itemMasterSid) +
        String.valueOf(priceType)).hashCode();
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
        sb.append("itemMasterSid");
        sb.append(StringPool.EQUAL);
        sb.append(itemMasterSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("priceType");
        sb.append(StringPool.EQUAL);
        sb.append(priceType);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
