package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistRelationshipLevelDefnPK implements Comparable<HistRelationshipLevelDefnPK>,
    Serializable {
    public int versionNo;
    public String actionFlag;
    public int relationshipLevelSid;

    public HistRelationshipLevelDefnPK() {
    }

    public HistRelationshipLevelDefnPK(int versionNo, String actionFlag,
        int relationshipLevelSid) {
        this.versionNo = versionNo;
        this.actionFlag = actionFlag;
        this.relationshipLevelSid = relationshipLevelSid;
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

    public int getRelationshipLevelSid() {
        return relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        this.relationshipLevelSid = relationshipLevelSid;
    }

    @Override
    public int compareTo(HistRelationshipLevelDefnPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

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

        if (relationshipLevelSid < pk.relationshipLevelSid) {
            value = -1;
        } else if (relationshipLevelSid > pk.relationshipLevelSid) {
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

        if (!(obj instanceof HistRelationshipLevelDefnPK)) {
            return false;
        }

        HistRelationshipLevelDefnPK pk = (HistRelationshipLevelDefnPK) obj;

        if ((versionNo == pk.versionNo) && (actionFlag.equals(pk.actionFlag)) &&
                (relationshipLevelSid == pk.relationshipLevelSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(versionNo) + String.valueOf(actionFlag) +
        String.valueOf(relationshipLevelSid)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("versionNo");
        sb.append(StringPool.EQUAL);
        sb.append(versionNo);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("actionFlag");
        sb.append(StringPool.EQUAL);
        sb.append(actionFlag);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("relationshipLevelSid");
        sb.append(StringPool.EQUAL);
        sb.append(relationshipLevelSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
