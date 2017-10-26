package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistItemGroupDetailsPK implements Comparable<HistItemGroupDetailsPK>,
    Serializable {
    public int itemGroupDetailsSid;
    public String actionFlag;
    public int versionNo;

    public HistItemGroupDetailsPK() {
    }

    public HistItemGroupDetailsPK(int itemGroupDetailsSid, String actionFlag,
        int versionNo) {
        this.itemGroupDetailsSid = itemGroupDetailsSid;
        this.actionFlag = actionFlag;
        this.versionNo = versionNo;
    }

    public int getItemGroupDetailsSid() {
        return itemGroupDetailsSid;
    }

    public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
        this.itemGroupDetailsSid = itemGroupDetailsSid;
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

    @Override
    public int compareTo(HistItemGroupDetailsPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (itemGroupDetailsSid < pk.itemGroupDetailsSid) {
            value = -1;
        } else if (itemGroupDetailsSid > pk.itemGroupDetailsSid) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistItemGroupDetailsPK)) {
            return false;
        }

        HistItemGroupDetailsPK pk = (HistItemGroupDetailsPK) obj;

        if ((itemGroupDetailsSid == pk.itemGroupDetailsSid) &&
                (actionFlag.equals(pk.actionFlag)) &&
                (versionNo == pk.versionNo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(itemGroupDetailsSid) +
        String.valueOf(actionFlag) + String.valueOf(versionNo)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("itemGroupDetailsSid");
        sb.append(StringPool.EQUAL);
        sb.append(itemGroupDetailsSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("actionFlag");
        sb.append(StringPool.EQUAL);
        sb.append(actionFlag);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
