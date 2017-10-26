package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistCompanyGroupDetailsPK implements Comparable<HistCompanyGroupDetailsPK>,
    Serializable {
    public String actionFlag;
    public int versionNo;
    public int companyGroupDetailsSid;

    public HistCompanyGroupDetailsPK() {
    }

    public HistCompanyGroupDetailsPK(String actionFlag, int versionNo,
        int companyGroupDetailsSid) {
        this.actionFlag = actionFlag;
        this.versionNo = versionNo;
        this.companyGroupDetailsSid = companyGroupDetailsSid;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public int getCompanyGroupDetailsSid() {
        return companyGroupDetailsSid;
    }

    public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
        this.companyGroupDetailsSid = companyGroupDetailsSid;
    }

    @Override
    public int compareTo(HistCompanyGroupDetailsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = actionFlag.compareTo(pk.actionFlag);

        if (value != 0) {
            return value;
        }

        if (versionNo < pk.versionNo) {
            value = -1;
        } else if (versionNo > pk.versionNo) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (companyGroupDetailsSid < pk.companyGroupDetailsSid) {
            value = -1;
        } else if (companyGroupDetailsSid > pk.companyGroupDetailsSid) {
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

        if (!(obj instanceof HistCompanyGroupDetailsPK)) {
            return false;
        }

        HistCompanyGroupDetailsPK pk = (HistCompanyGroupDetailsPK) obj;

        if ((actionFlag.equals(pk.actionFlag)) && (versionNo == pk.versionNo) &&
                (companyGroupDetailsSid == pk.companyGroupDetailsSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(actionFlag) + String.valueOf(versionNo) +
        String.valueOf(companyGroupDetailsSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("actionFlag");
        sb.append(StringPool.EQUAL);
        sb.append(actionFlag);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("companyGroupDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(companyGroupDetailsSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
