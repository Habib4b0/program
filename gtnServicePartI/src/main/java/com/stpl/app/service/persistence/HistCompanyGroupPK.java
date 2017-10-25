package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistCompanyGroupPK implements Comparable<HistCompanyGroupPK>,
    Serializable {
    public String actionFlag;
    public int companyGroupSid;
    public int versionNo;

    public HistCompanyGroupPK() {
    }

    public HistCompanyGroupPK(String actionFlag, int companyGroupSid,
        int versionNo) {
        this.actionFlag = actionFlag;
        this.companyGroupSid = companyGroupSid;
        this.versionNo = versionNo;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public int getCompanyGroupSid() {
        return companyGroupSid;
    }

    public void setCompanyGroupSid(int companyGroupSid) {
        this.companyGroupSid = companyGroupSid;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public int compareTo(HistCompanyGroupPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = actionFlag.compareTo(pk.actionFlag);

        if (value != 0) {
            return value;
        }

        if (companyGroupSid < pk.companyGroupSid) {
            value = -1;
        } else if (companyGroupSid > pk.companyGroupSid) {
            value = 1;
        } else {
            value = 0;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistCompanyGroupPK)) {
            return false;
        }

        HistCompanyGroupPK pk = (HistCompanyGroupPK) obj;

        if ((actionFlag.equals(pk.actionFlag)) &&
                (companyGroupSid == pk.companyGroupSid) &&
                (versionNo == pk.versionNo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(actionFlag) + String.valueOf(companyGroupSid) +
        String.valueOf(versionNo)).hashCode();
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
        sb.append("companyGroupSid");
        sb.append(StringPool.EQUAL);
        sb.append(companyGroupSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
