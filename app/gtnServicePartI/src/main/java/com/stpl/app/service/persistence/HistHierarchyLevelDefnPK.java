package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistHierarchyLevelDefnPK implements Comparable<HistHierarchyLevelDefnPK>,
    Serializable {
    public int hierarchyLevelDefinitionSid;
    public int versionNo;
    public String actionFlag;

    public HistHierarchyLevelDefnPK() {
    }

    public HistHierarchyLevelDefnPK(int hierarchyLevelDefinitionSid,
        int versionNo, String actionFlag) {
        this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
        this.versionNo = versionNo;
        this.actionFlag = actionFlag;
    }

    public int getHierarchyLevelDefinitionSid() {
        return hierarchyLevelDefinitionSid;
    }

    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    @Override
    public int compareTo(HistHierarchyLevelDefnPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (hierarchyLevelDefinitionSid < pk.hierarchyLevelDefinitionSid) {
            value = -1;
        } else if (hierarchyLevelDefinitionSid > pk.hierarchyLevelDefinitionSid) {
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

        value = actionFlag.compareTo(pk.actionFlag);

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

        if (!(obj instanceof HistHierarchyLevelDefnPK)) {
            return false;
        }

        HistHierarchyLevelDefnPK pk = (HistHierarchyLevelDefnPK) obj;

        if ((hierarchyLevelDefinitionSid == pk.hierarchyLevelDefinitionSid) &&
                (versionNo == pk.versionNo) &&
                (actionFlag.equals(pk.actionFlag))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(hierarchyLevelDefinitionSid) +
        String.valueOf(versionNo) + String.valueOf(actionFlag)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("hierarchyLevelDefinitionSid");
        sb.append(StringPool.EQUAL);
        sb.append(hierarchyLevelDefinitionSid);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("actionFlag");
        sb.append(StringPool.EQUAL);
        sb.append(actionFlag);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
