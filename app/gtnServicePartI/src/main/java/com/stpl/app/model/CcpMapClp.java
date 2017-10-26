package com.stpl.app.model;

import com.stpl.app.service.CcpMapLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class CcpMapClp extends BaseModelImpl<CcpMap> implements CcpMap {
    private int _ccpDetailsSid;
    private int _relationshipLevelSid;
    private int _ccpMapSid;
    private BaseModel<?> _ccpMapRemoteModel;

    public CcpMapClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CcpMap.class;
    }

    @Override
    public String getModelClassName() {
        return CcpMap.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ccpMapSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCcpMapSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ccpMapSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("ccpMapSid", getCcpMapSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        Integer ccpMapSid = (Integer) attributes.get("ccpMapSid");

        if (ccpMapSid != null) {
            setCcpMapSid(ccpMapSid);
        }
    }

    @Override
    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;

        if (_ccpMapRemoteModel != null) {
            try {
                Class<?> clazz = _ccpMapRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_ccpMapRemoteModel, ccpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;

        if (_ccpMapRemoteModel != null) {
            try {
                Class<?> clazz = _ccpMapRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_ccpMapRemoteModel, relationshipLevelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCcpMapSid() {
        return _ccpMapSid;
    }

    @Override
    public void setCcpMapSid(int ccpMapSid) {
        _ccpMapSid = ccpMapSid;

        if (_ccpMapRemoteModel != null) {
            try {
                Class<?> clazz = _ccpMapRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpMapSid", int.class);

                method.invoke(_ccpMapRemoteModel, ccpMapSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCcpMapRemoteModel() {
        return _ccpMapRemoteModel;
    }

    public void setCcpMapRemoteModel(BaseModel<?> ccpMapRemoteModel) {
        _ccpMapRemoteModel = ccpMapRemoteModel;
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

        Class<?> remoteModelClass = _ccpMapRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ccpMapRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CcpMapLocalServiceUtil.addCcpMap(this);
        } else {
            CcpMapLocalServiceUtil.updateCcpMap(this);
        }
    }

    @Override
    public CcpMap toEscapedModel() {
        return (CcpMap) ProxyUtil.newProxyInstance(CcpMap.class.getClassLoader(),
            new Class[] { CcpMap.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CcpMapClp clone = new CcpMapClp();

        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());
        clone.setCcpMapSid(getCcpMapSid());

        return clone;
    }

    @Override
    public int compareTo(CcpMap ccpMap) {
        int primaryKey = ccpMap.getPrimaryKey();

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

        if (!(obj instanceof CcpMapClp)) {
            return false;
        }

        CcpMapClp ccpMap = (CcpMapClp) obj;

        int primaryKey = ccpMap.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append(", ccpMapSid=");
        sb.append(getCcpMapSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CcpMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccpMapSid</column-name><column-value><![CDATA[");
        sb.append(getCcpMapSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
