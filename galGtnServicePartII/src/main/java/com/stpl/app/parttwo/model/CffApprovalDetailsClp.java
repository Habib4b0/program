package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CffApprovalDetailsClp extends BaseModelImpl<CffApprovalDetails>
    implements CffApprovalDetails {
    private int _approvalSequence;
    private Date _approvedDate;
    private int _approvedBy;
    private int _approvalStatus;
    private int _cffMasterSid;
    private String _inboundStatus;
    private int _cffApprovalDetailsSid;
    private BaseModel<?> _cffApprovalDetailsRemoteModel;

    public CffApprovalDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffApprovalDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffApprovalDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffApprovalDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffApprovalDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffApprovalDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("approvalSequence", getApprovalSequence());
        attributes.put("approvedDate", getApprovedDate());
        attributes.put("approvedBy", getApprovedBy());
        attributes.put("approvalStatus", getApprovalStatus());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cffApprovalDetailsSid", getCffApprovalDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer approvalSequence = (Integer) attributes.get("approvalSequence");

        if (approvalSequence != null) {
            setApprovalSequence(approvalSequence);
        }

        Date approvedDate = (Date) attributes.get("approvedDate");

        if (approvedDate != null) {
            setApprovedDate(approvedDate);
        }

        Integer approvedBy = (Integer) attributes.get("approvedBy");

        if (approvedBy != null) {
            setApprovedBy(approvedBy);
        }

        Integer approvalStatus = (Integer) attributes.get("approvalStatus");

        if (approvalStatus != null) {
            setApprovalStatus(approvalStatus);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer cffApprovalDetailsSid = (Integer) attributes.get(
                "cffApprovalDetailsSid");

        if (cffApprovalDetailsSid != null) {
            setCffApprovalDetailsSid(cffApprovalDetailsSid);
        }
    }

    @Override
    public int getApprovalSequence() {
        return _approvalSequence;
    }

    @Override
    public void setApprovalSequence(int approvalSequence) {
        _approvalSequence = approvalSequence;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovalSequence", int.class);

                method.invoke(_cffApprovalDetailsRemoteModel, approvalSequence);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getApprovedDate() {
        return _approvedDate;
    }

    @Override
    public void setApprovedDate(Date approvedDate) {
        _approvedDate = approvedDate;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovedDate", Date.class);

                method.invoke(_cffApprovalDetailsRemoteModel, approvedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getApprovedBy() {
        return _approvedBy;
    }

    @Override
    public void setApprovedBy(int approvedBy) {
        _approvedBy = approvedBy;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovedBy", int.class);

                method.invoke(_cffApprovalDetailsRemoteModel, approvedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getApprovalStatus() {
        return _approvalStatus;
    }

    @Override
    public void setApprovalStatus(int approvalStatus) {
        _approvalStatus = approvalStatus;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovalStatus", int.class);

                method.invoke(_cffApprovalDetailsRemoteModel, approvalStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffApprovalDetailsRemoteModel, cffMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cffApprovalDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffApprovalDetailsSid() {
        return _cffApprovalDetailsSid;
    }

    @Override
    public void setCffApprovalDetailsSid(int cffApprovalDetailsSid) {
        _cffApprovalDetailsSid = cffApprovalDetailsSid;

        if (_cffApprovalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffApprovalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffApprovalDetailsSid",
                        int.class);

                method.invoke(_cffApprovalDetailsRemoteModel,
                    cffApprovalDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffApprovalDetailsRemoteModel() {
        return _cffApprovalDetailsRemoteModel;
    }

    public void setCffApprovalDetailsRemoteModel(
        BaseModel<?> cffApprovalDetailsRemoteModel) {
        _cffApprovalDetailsRemoteModel = cffApprovalDetailsRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _cffApprovalDetailsRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_cffApprovalDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffApprovalDetailsLocalServiceUtil.addCffApprovalDetails(this);
        } else {
            CffApprovalDetailsLocalServiceUtil.updateCffApprovalDetails(this);
        }
    }

    @Override
    public CffApprovalDetails toEscapedModel() {
        return (CffApprovalDetails) ProxyUtil.newProxyInstance(CffApprovalDetails.class.getClassLoader(),
            new Class[] { CffApprovalDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffApprovalDetailsClp clone = new CffApprovalDetailsClp();

        clone.setApprovalSequence(getApprovalSequence());
        clone.setApprovedDate(getApprovedDate());
        clone.setApprovedBy(getApprovedBy());
        clone.setApprovalStatus(getApprovalStatus());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setInboundStatus(getInboundStatus());
        clone.setCffApprovalDetailsSid(getCffApprovalDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(CffApprovalDetails cffApprovalDetails) {
        int primaryKey = cffApprovalDetails.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffApprovalDetailsClp)) {
            return false;
        }

        CffApprovalDetailsClp cffApprovalDetails = (CffApprovalDetailsClp) obj;

        int primaryKey = cffApprovalDetails.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{approvalSequence=");
        sb.append(getApprovalSequence());
        sb.append(", approvedDate=");
        sb.append(getApprovedDate());
        sb.append(", approvedBy=");
        sb.append(getApprovedBy());
        sb.append(", approvalStatus=");
        sb.append(getApprovalStatus());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", cffApprovalDetailsSid=");
        sb.append(getCffApprovalDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffApprovalDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>approvalSequence</column-name><column-value><![CDATA[");
        sb.append(getApprovalSequence());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvedDate</column-name><column-value><![CDATA[");
        sb.append(getApprovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvedBy</column-name><column-value><![CDATA[");
        sb.append(getApprovedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvalStatus</column-name><column-value><![CDATA[");
        sb.append(getApprovalStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffApprovalDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCffApprovalDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
