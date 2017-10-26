package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
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


public class CffCustHierarchyClp extends BaseModelImpl<CffCustHierarchy>
    implements CffCustHierarchy {
    private int _cffCustHierarchySid;
    private int _cffMasterSid;
    private int _relationshipLevelSid;
    private BaseModel<?> _cffCustHierarchyRemoteModel;

    public CffCustHierarchyClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustHierarchy.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffCustHierarchySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffCustHierarchySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffCustHierarchySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cffCustHierarchySid", getCffCustHierarchySid());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer cffCustHierarchySid = (Integer) attributes.get(
                "cffCustHierarchySid");

        if (cffCustHierarchySid != null) {
            setCffCustHierarchySid(cffCustHierarchySid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }
    }

    @Override
    public int getCffCustHierarchySid() {
        return _cffCustHierarchySid;
    }

    @Override
    public void setCffCustHierarchySid(int cffCustHierarchySid) {
        _cffCustHierarchySid = cffCustHierarchySid;

        if (_cffCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setCffCustHierarchySid",
                        int.class);

                method.invoke(_cffCustHierarchyRemoteModel, cffCustHierarchySid);
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

        if (_cffCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffCustHierarchyRemoteModel, cffMasterSid);
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

        if (_cffCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_cffCustHierarchyRemoteModel, relationshipLevelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffCustHierarchyRemoteModel() {
        return _cffCustHierarchyRemoteModel;
    }

    public void setCffCustHierarchyRemoteModel(
        BaseModel<?> cffCustHierarchyRemoteModel) {
        _cffCustHierarchyRemoteModel = cffCustHierarchyRemoteModel;
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

        Class<?> remoteModelClass = _cffCustHierarchyRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffCustHierarchyRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffCustHierarchyLocalServiceUtil.addCffCustHierarchy(this);
        } else {
            CffCustHierarchyLocalServiceUtil.updateCffCustHierarchy(this);
        }
    }

    @Override
    public CffCustHierarchy toEscapedModel() {
        return (CffCustHierarchy) ProxyUtil.newProxyInstance(CffCustHierarchy.class.getClassLoader(),
            new Class[] { CffCustHierarchy.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffCustHierarchyClp clone = new CffCustHierarchyClp();

        clone.setCffCustHierarchySid(getCffCustHierarchySid());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());

        return clone;
    }

    @Override
    public int compareTo(CffCustHierarchy cffCustHierarchy) {
        int primaryKey = cffCustHierarchy.getPrimaryKey();

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

        if (!(obj instanceof CffCustHierarchyClp)) {
            return false;
        }

        CffCustHierarchyClp cffCustHierarchy = (CffCustHierarchyClp) obj;

        int primaryKey = cffCustHierarchy.getPrimaryKey();

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

        sb.append("{cffCustHierarchySid=");
        sb.append(getCffCustHierarchySid());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffCustHierarchy");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>cffCustHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getCffCustHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
