package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistItemGroupPK implements Comparable<HistItemGroupPK>,
    Serializable {
    public String actionFlag;
    public int versionNo;
    public int itemGroupSid;

    public HistItemGroupPK() {
    }

    public HistItemGroupPK(String actionFlag, int versionNo, int itemGroupSid) {
        this.actionFlag = actionFlag;
        this.versionNo = versionNo;
        this.itemGroupSid = itemGroupSid;
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

    public int getItemGroupSid() {
        return itemGroupSid;
    }

    public void setItemGroupSid(int itemGroupSid) {
        this.itemGroupSid = itemGroupSid;
    }

    @Override
    public int compareTo(HistItemGroupPK pk) {
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

        if (itemGroupSid < pk.itemGroupSid) {
            value = -1;
        } else if (itemGroupSid > pk.itemGroupSid) {
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

        if (!(obj instanceof HistItemGroupPK)) {
            return false;
        }

        HistItemGroupPK pk = (HistItemGroupPK) obj;

        if ((actionFlag.equals(pk.actionFlag)) && (versionNo == pk.versionNo) &&
                (itemGroupSid == pk.itemGroupSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(actionFlag) + String.valueOf(versionNo) +
        String.valueOf(itemGroupSid)).hashCode();
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
        sb.append("itemGroupSid");
        sb.append(StringPool.EQUAL);
        sb.append(itemGroupSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
