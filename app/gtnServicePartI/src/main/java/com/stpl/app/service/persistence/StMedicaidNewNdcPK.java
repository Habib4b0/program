package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;

import java.io.Serializable;


public class StMedicaidNewNdcPK implements Comparable<StMedicaidNewNdcPK>,
    Serializable {
    public String ndc9;
    public int userId;
    public int sessionId;

    public StMedicaidNewNdcPK() {
    }

    public StMedicaidNewNdcPK(String ndc9, int userId, int sessionId) {
        this.ndc9 = ndc9;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int compareTo(StMedicaidNewNdcPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = ndc9.compareTo(pk.ndc9);

        if (value != 0) {
            return value;
        }

        if (userId < pk.userId) {
            value = -1;
        } else if (userId > pk.userId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (sessionId < pk.sessionId) {
            value = -1;
        } else if (sessionId > pk.sessionId) {
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

        if (!(obj instanceof StMedicaidNewNdcPK)) {
            return false;
        }

        StMedicaidNewNdcPK pk = (StMedicaidNewNdcPK) obj;

        if ((ndc9.equals(pk.ndc9)) && (userId == pk.userId) &&
                (sessionId == pk.sessionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(ndc9) + String.valueOf(userId) +
        String.valueOf(sessionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("ndc9");
        sb.append(StringPool.EQUAL);
        sb.append(ndc9);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("sessionId");
        sb.append(StringPool.EQUAL);
        sb.append(sessionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
