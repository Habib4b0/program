package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
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


public class CffProdHierarchyClp extends BaseModelImpl<CffProdHierarchy>
    implements CffProdHierarchy {
    private int _cffMasterSid;
    private int _relationshipLevelSid;
    private int _cffProdHierarchySid;
    private BaseModel<?> _cffProdHierarchyRemoteModel;

    public CffProdHierarchyClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffProdHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return CffProdHierarchy.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffProdHierarchySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("cffProdHierarchySid", getCffProdHierarchySid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        Integer cffProdHierarchySid = (Integer) attributes.get(
                "cffProdHierarchySid");

        if (cffProdHierarchySid != null) {
            setCffProdHierarchySid(cffProdHierarchySid);
        }
    }

    @Override
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;

        if (_cffProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffProdHierarchyRemoteModel, cffMasterSid);
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

        if (_cffProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_cffProdHierarchyRemoteModel, relationshipLevelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffProdHierarchySid() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setCffProdHierarchySid(int cffProdHierarchySid) {
        _cffProdHierarchySid = cffProdHierarchySid;

        if (_cffProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setCffProdHierarchySid",
                        int.class);

                method.invoke(_cffProdHierarchyRemoteModel, cffProdHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffProdHierarchyRemoteModel() {
        return _cffProdHierarchyRemoteModel;
    }

    public void setCffProdHierarchyRemoteModel(
        BaseModel<?> cffProdHierarchyRemoteModel) {
        _cffProdHierarchyRemoteModel = cffProdHierarchyRemoteModel;
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

        Class<?> remoteModelClass = _cffProdHierarchyRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffProdHierarchyRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffProdHierarchyLocalServiceUtil.addCffProdHierarchy(this);
        } else {
            CffProdHierarchyLocalServiceUtil.updateCffProdHierarchy(this);
        }
    }

    @Override
    public CffProdHierarchy toEscapedModel() {
        return (CffProdHierarchy) ProxyUtil.newProxyInstance(CffProdHierarchy.class.getClassLoader(),
            new Class[] { CffProdHierarchy.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffProdHierarchyClp clone = new CffProdHierarchyClp();

        clone.setCffMasterSid(getCffMasterSid());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());
        clone.setCffProdHierarchySid(getCffProdHierarchySid());

        return clone;
    }

    @Override
    public int compareTo(CffProdHierarchy cffProdHierarchy) {
        int primaryKey = cffProdHierarchy.getPrimaryKey();

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

        if (!(obj instanceof CffProdHierarchyClp)) {
            return false;
        }

        CffProdHierarchyClp cffProdHierarchy = (CffProdHierarchyClp) obj;

        int primaryKey = cffProdHierarchy.getPrimaryKey();

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

        sb.append("{cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append(", cffProdHierarchySid=");
        sb.append(getCffProdHierarchySid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffProdHierarchy");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffProdHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getCffProdHierarchySid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
