package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class DeductionCalendarDetailsPK implements Comparable<DeductionCalendarDetailsPK>,
    Serializable {
    public int deductionCalendarMasterSid;
    public int periodSid;
    public int companyMasterSid;
    public int itemMasterSid;

    public DeductionCalendarDetailsPK() {
    }

    public DeductionCalendarDetailsPK(int deductionCalendarMasterSid,
        int periodSid, int companyMasterSid, int itemMasterSid) {
        this.deductionCalendarMasterSid = deductionCalendarMasterSid;
        this.periodSid = periodSid;
        this.companyMasterSid = companyMasterSid;
        this.itemMasterSid = itemMasterSid;
    }

    public int getDeductionCalendarMasterSid() {
        return deductionCalendarMasterSid;
    }

    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        this.deductionCalendarMasterSid = deductionCalendarMasterSid;
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

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    @Override
    public int compareTo(DeductionCalendarDetailsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (deductionCalendarMasterSid < pk.deductionCalendarMasterSid) {
            value = -1;
        } else if (deductionCalendarMasterSid > pk.deductionCalendarMasterSid) {
            value = 1;
        } else {
            value = 0;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionCalendarDetailsPK)) {
            return false;
        }

        DeductionCalendarDetailsPK pk = (DeductionCalendarDetailsPK) obj;

        if ((deductionCalendarMasterSid == pk.deductionCalendarMasterSid) &&
                (periodSid == pk.periodSid) &&
                (companyMasterSid == pk.companyMasterSid) &&
                (itemMasterSid == pk.itemMasterSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(deductionCalendarMasterSid) +
        String.valueOf(periodSid) + String.valueOf(companyMasterSid) +
        String.valueOf(itemMasterSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(20);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("deductionCalendarMasterSid");
        sb.append(StringPool.EQUAL);
        sb.append(deductionCalendarMasterSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
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
        sb.append("itemMasterSid");
        sb.append(StringPool.EQUAL);
        sb.append(itemMasterSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
