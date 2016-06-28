package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class CffDetailsClp extends BaseModelImpl<CffDetails>
    implements CffDetails {
    private int _ccpDetailsSid;
    private int _projectionMasterSid;
    private int _cffMasterSid;
    private String _inboundStatus;
    private int _cffDetailsSid;
    private BaseModel<?> _cffDetailsRemoteModel;

    public CffDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cffDetailsSid", getCffDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer cffDetailsSid = (Integer) attributes.get("cffDetailsSid");

        if (cffDetailsSid != null) {
            setCffDetailsSid(cffDetailsSid);
        }
    }

    @Override
    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;

        if (_cffDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_cffDetailsRemoteModel, ccpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;

        if (_cffDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_cffDetailsRemoteModel, projectionMasterSid);
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

        if (_cffDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffDetailsRemoteModel, cffMasterSid);
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

        if (_cffDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cffDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffDetailsSid() {
        return _cffDetailsSid;
    }

    @Override
    public void setCffDetailsSid(int cffDetailsSid) {
        _cffDetailsSid = cffDetailsSid;

        if (_cffDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffDetailsSid", int.class);

                method.invoke(_cffDetailsRemoteModel, cffDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffDetailsRemoteModel() {
        return _cffDetailsRemoteModel;
    }

    public void setCffDetailsRemoteModel(BaseModel<?> cffDetailsRemoteModel) {
        _cffDetailsRemoteModel = cffDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cffDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffDetailsLocalServiceUtil.addCffDetails(this);
        } else {
            CffDetailsLocalServiceUtil.updateCffDetails(this);
        }
    }

    @Override
    public CffDetails toEscapedModel() {
        return (CffDetails) ProxyUtil.newProxyInstance(CffDetails.class.getClassLoader(),
            new Class[] { CffDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffDetailsClp clone = new CffDetailsClp();

        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setInboundStatus(getInboundStatus());
        clone.setCffDetailsSid(getCffDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(CffDetails cffDetails) {
        int primaryKey = cffDetails.getPrimaryKey();

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

        if (!(obj instanceof CffDetailsClp)) {
            return false;
        }

        CffDetailsClp cffDetails = (CffDetailsClp) obj;

        int primaryKey = cffDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", cffDetailsSid=");
        sb.append(getCffDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
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
            "<column><column-name>cffDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCffDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
