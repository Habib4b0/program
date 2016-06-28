package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
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


public class CffCustomViewDetailsClp extends BaseModelImpl<CffCustomViewDetails>
    implements CffCustomViewDetails {
    private int _hierarchyId;
    private String _hierarchyIndicator;
    private int _cffCustomViewDetailsSid;
    private int _levelNo;
    private int _cffCustomViewMasterSid;
    private BaseModel<?> _cffCustomViewDetailsRemoteModel;

    public CffCustomViewDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustomViewDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustomViewDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffCustomViewDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffCustomViewDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffCustomViewDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("hierarchyId", getHierarchyId());
        attributes.put("hierarchyIndicator", getHierarchyIndicator());
        attributes.put("cffCustomViewDetailsSid", getCffCustomViewDetailsSid());
        attributes.put("levelNo", getLevelNo());
        attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer hierarchyId = (Integer) attributes.get("hierarchyId");

        if (hierarchyId != null) {
            setHierarchyId(hierarchyId);
        }

        String hierarchyIndicator = (String) attributes.get(
                "hierarchyIndicator");

        if (hierarchyIndicator != null) {
            setHierarchyIndicator(hierarchyIndicator);
        }

        Integer cffCustomViewDetailsSid = (Integer) attributes.get(
                "cffCustomViewDetailsSid");

        if (cffCustomViewDetailsSid != null) {
            setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer cffCustomViewMasterSid = (Integer) attributes.get(
                "cffCustomViewMasterSid");

        if (cffCustomViewMasterSid != null) {
            setCffCustomViewMasterSid(cffCustomViewMasterSid);
        }
    }

    @Override
    public int getHierarchyId() {
        return _hierarchyId;
    }

    @Override
    public void setHierarchyId(int hierarchyId) {
        _hierarchyId = hierarchyId;

        if (_cffCustomViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyId", int.class);

                method.invoke(_cffCustomViewDetailsRemoteModel, hierarchyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHierarchyIndicator() {
        return _hierarchyIndicator;
    }

    @Override
    public void setHierarchyIndicator(String hierarchyIndicator) {
        _hierarchyIndicator = hierarchyIndicator;

        if (_cffCustomViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyIndicator",
                        String.class);

                method.invoke(_cffCustomViewDetailsRemoteModel,
                    hierarchyIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffCustomViewDetailsSid() {
        return _cffCustomViewDetailsSid;
    }

    @Override
    public void setCffCustomViewDetailsSid(int cffCustomViewDetailsSid) {
        _cffCustomViewDetailsSid = cffCustomViewDetailsSid;

        if (_cffCustomViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffCustomViewDetailsSid",
                        int.class);

                method.invoke(_cffCustomViewDetailsRemoteModel,
                    cffCustomViewDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getLevelNo() {
        return _levelNo;
    }

    @Override
    public void setLevelNo(int levelNo) {
        _levelNo = levelNo;

        if (_cffCustomViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", int.class);

                method.invoke(_cffCustomViewDetailsRemoteModel, levelNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffCustomViewMasterSid() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
        _cffCustomViewMasterSid = cffCustomViewMasterSid;

        if (_cffCustomViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffCustomViewMasterSid",
                        int.class);

                method.invoke(_cffCustomViewDetailsRemoteModel,
                    cffCustomViewMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffCustomViewDetailsRemoteModel() {
        return _cffCustomViewDetailsRemoteModel;
    }

    public void setCffCustomViewDetailsRemoteModel(
        BaseModel<?> cffCustomViewDetailsRemoteModel) {
        _cffCustomViewDetailsRemoteModel = cffCustomViewDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cffCustomViewDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffCustomViewDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffCustomViewDetailsLocalServiceUtil.addCffCustomViewDetails(this);
        } else {
            CffCustomViewDetailsLocalServiceUtil.updateCffCustomViewDetails(this);
        }
    }

    @Override
    public CffCustomViewDetails toEscapedModel() {
        return (CffCustomViewDetails) ProxyUtil.newProxyInstance(CffCustomViewDetails.class.getClassLoader(),
            new Class[] { CffCustomViewDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffCustomViewDetailsClp clone = new CffCustomViewDetailsClp();

        clone.setHierarchyId(getHierarchyId());
        clone.setHierarchyIndicator(getHierarchyIndicator());
        clone.setCffCustomViewDetailsSid(getCffCustomViewDetailsSid());
        clone.setLevelNo(getLevelNo());
        clone.setCffCustomViewMasterSid(getCffCustomViewMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CffCustomViewDetails cffCustomViewDetails) {
        int primaryKey = cffCustomViewDetails.getPrimaryKey();

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

        if (!(obj instanceof CffCustomViewDetailsClp)) {
            return false;
        }

        CffCustomViewDetailsClp cffCustomViewDetails = (CffCustomViewDetailsClp) obj;

        int primaryKey = cffCustomViewDetails.getPrimaryKey();

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

        sb.append("{hierarchyId=");
        sb.append(getHierarchyId());
        sb.append(", hierarchyIndicator=");
        sb.append(getHierarchyIndicator());
        sb.append(", cffCustomViewDetailsSid=");
        sb.append(getCffCustomViewDetailsSid());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
        sb.append(", cffCustomViewMasterSid=");
        sb.append(getCffCustomViewMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffCustomViewDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>hierarchyId</column-name><column-value><![CDATA[");
        sb.append(getHierarchyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyIndicator</column-name><column-value><![CDATA[");
        sb.append(getHierarchyIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffCustomViewDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCffCustomViewDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffCustomViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffCustomViewMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
