package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ChDiscountProjMasterPK implements Comparable<ChDiscountProjMasterPK>,
    Serializable {
    public int projectionDetailsSid;
    public int rsModelSid;

    public ChDiscountProjMasterPK() {
    }

    public ChDiscountProjMasterPK(int projectionDetailsSid, int rsModelSid) {
        this.projectionDetailsSid = projectionDetailsSid;
        this.rsModelSid = rsModelSid;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public int getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        this.rsModelSid = rsModelSid;
    }

    @Override
    public int compareTo(ChDiscountProjMasterPK pk) {
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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChDiscountProjMasterPK)) {
            return false;
        }

        ChDiscountProjMasterPK pk = (ChDiscountProjMasterPK) obj;

        if ((projectionDetailsSid == pk.projectionDetailsSid) &&
                (rsModelSid == pk.rsModelSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(projectionDetailsSid) +
        String.valueOf(rsModelSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("projectionDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(projectionDetailsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("rsModelSid");
        sb.append(StringPool.EQUAL);
        sb.append(rsModelSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
