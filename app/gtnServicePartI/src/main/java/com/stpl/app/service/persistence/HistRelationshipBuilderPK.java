package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class HistRelationshipBuilderPK implements Comparable<HistRelationshipBuilderPK>,
    Serializable {
    public String actionFlag;
    public int versionNo;
    public int relationshipBuilderSid;

    public HistRelationshipBuilderPK() {
    }

    public HistRelationshipBuilderPK(String actionFlag, int versionNo,
        int relationshipBuilderSid) {
        this.actionFlag = actionFlag;
        this.versionNo = versionNo;
        this.relationshipBuilderSid = relationshipBuilderSid;
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

    public int getRelationshipBuilderSid() {
        return relationshipBuilderSid;
    }

    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        this.relationshipBuilderSid = relationshipBuilderSid;
    }

    @Override
    public int compareTo(HistRelationshipBuilderPK pk) {
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

        if (relationshipBuilderSid < pk.relationshipBuilderSid) {
            value = -1;
        } else if (relationshipBuilderSid > pk.relationshipBuilderSid) {
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

        if (!(obj instanceof HistRelationshipBuilderPK)) {
            return false;
        }

        HistRelationshipBuilderPK pk = (HistRelationshipBuilderPK) obj;

        if ((actionFlag.equals(pk.actionFlag)) && (versionNo == pk.versionNo) &&
                (relationshipBuilderSid == pk.relationshipBuilderSid)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(actionFlag) + String.valueOf(versionNo) +
        String.valueOf(relationshipBuilderSid)).hashCode();
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
        sb.append("relationshipBuilderSid");
        sb.append(StringPool.EQUAL);
        sb.append(relationshipBuilderSid);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
