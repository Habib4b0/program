package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistHierarchyDefinitionPK implements Comparable<HistHierarchyDefinitionPK>,
    Serializable {
    public String actionFlag;
    public int hierarchyDefinitionSid;
    public int versionNo;

    public HistHierarchyDefinitionPK() {
    }

    public HistHierarchyDefinitionPK(String actionFlag,
        int hierarchyDefinitionSid, int versionNo) {
        this.actionFlag = actionFlag;
        this.hierarchyDefinitionSid = hierarchyDefinitionSid;
        this.versionNo = versionNo;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public int getHierarchyDefinitionSid() {
        return hierarchyDefinitionSid;
    }

    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        this.hierarchyDefinitionSid = hierarchyDefinitionSid;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public int compareTo(HistHierarchyDefinitionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = actionFlag.compareTo(pk.actionFlag);

        if (value != 0) {
            return value;
        }

        if (hierarchyDefinitionSid < pk.hierarchyDefinitionSid) {
            value = -1;
        } else if (hierarchyDefinitionSid > pk.hierarchyDefinitionSid) {
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

        if (!(obj instanceof HistHierarchyDefinitionPK)) {
            return false;
        }

        HistHierarchyDefinitionPK pk = (HistHierarchyDefinitionPK) obj;

        if ((actionFlag.equals(pk.actionFlag)) &&
                (hierarchyDefinitionSid == pk.hierarchyDefinitionSid) &&
                (versionNo == pk.versionNo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(actionFlag) +
        String.valueOf(hierarchyDefinitionSid) + String.valueOf(versionNo)).hashCode();
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
        sb.append("hierarchyDefinitionSid");
        sb.append(StringPool.EQUAL);
        sb.append(hierarchyDefinitionSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
