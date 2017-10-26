package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistHierarchyLevelValuesPK implements Comparable<HistHierarchyLevelValuesPK>,
    Serializable {
    public int hierarchyLevelValuesSid;
    public String actionFlag;
    public int versionNo;

    public HistHierarchyLevelValuesPK() {
    }

    public HistHierarchyLevelValuesPK(int hierarchyLevelValuesSid,
        String actionFlag, int versionNo) {
        this.hierarchyLevelValuesSid = hierarchyLevelValuesSid;
        this.actionFlag = actionFlag;
        this.versionNo = versionNo;
    }

    public int getHierarchyLevelValuesSid() {
        return hierarchyLevelValuesSid;
    }

    public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
        this.hierarchyLevelValuesSid = hierarchyLevelValuesSid;
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
    public int compareTo(HistHierarchyLevelValuesPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (hierarchyLevelValuesSid < pk.hierarchyLevelValuesSid) {
            value = -1;
        } else if (hierarchyLevelValuesSid > pk.hierarchyLevelValuesSid) {
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

        if (!(obj instanceof HistHierarchyLevelValuesPK)) {
            return false;
        }

        HistHierarchyLevelValuesPK pk = (HistHierarchyLevelValuesPK) obj;

        if ((hierarchyLevelValuesSid == pk.hierarchyLevelValuesSid) &&
                (actionFlag.equals(pk.actionFlag)) &&
                (versionNo == pk.versionNo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(hierarchyLevelValuesSid) +
        String.valueOf(actionFlag) + String.valueOf(versionNo)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("hierarchyLevelValuesSid");
        sb.append(StringPool.EQUAL);
        sb.append(hierarchyLevelValuesSid);

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
