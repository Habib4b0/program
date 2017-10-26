package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;

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


public class CustomViewDetailsClp extends BaseModelImpl<CustomViewDetails>
    implements CustomViewDetails {
    private int _hierarchyId;
    private String _hierarchyIndicator;
    private int _customViewMasterSid;
    private int _customViewDetailsSid;
    private int _levelNo;
    private BaseModel<?> _customViewDetailsRemoteModel;

    public CustomViewDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CustomViewDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CustomViewDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _customViewDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCustomViewDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _customViewDetailsSid;
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
        attributes.put("customViewMasterSid", getCustomViewMasterSid());
        attributes.put("customViewDetailsSid", getCustomViewDetailsSid());
        attributes.put("levelNo", getLevelNo());

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

        Integer customViewMasterSid = (Integer) attributes.get(
                "customViewMasterSid");

        if (customViewMasterSid != null) {
            setCustomViewMasterSid(customViewMasterSid);
        }

        Integer customViewDetailsSid = (Integer) attributes.get(
                "customViewDetailsSid");

        if (customViewDetailsSid != null) {
            setCustomViewDetailsSid(customViewDetailsSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }
    }

    @Override
    public int getHierarchyId() {
        return _hierarchyId;
    }

    @Override
    public void setHierarchyId(int hierarchyId) {
        _hierarchyId = hierarchyId;

        if (_customViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _customViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyId", int.class);

                method.invoke(_customViewDetailsRemoteModel, hierarchyId);
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

        if (_customViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _customViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyIndicator",
                        String.class);

                method.invoke(_customViewDetailsRemoteModel, hierarchyIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomViewMasterSid() {
        return _customViewMasterSid;
    }

    @Override
    public void setCustomViewMasterSid(int customViewMasterSid) {
        _customViewMasterSid = customViewMasterSid;

        if (_customViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _customViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomViewMasterSid",
                        int.class);

                method.invoke(_customViewDetailsRemoteModel, customViewMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomViewDetailsSid() {
        return _customViewDetailsSid;
    }

    @Override
    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        _customViewDetailsSid = customViewDetailsSid;

        if (_customViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _customViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomViewDetailsSid",
                        int.class);

                method.invoke(_customViewDetailsRemoteModel,
                    customViewDetailsSid);
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

        if (_customViewDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _customViewDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", int.class);

                method.invoke(_customViewDetailsRemoteModel, levelNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCustomViewDetailsRemoteModel() {
        return _customViewDetailsRemoteModel;
    }

    public void setCustomViewDetailsRemoteModel(
        BaseModel<?> customViewDetailsRemoteModel) {
        _customViewDetailsRemoteModel = customViewDetailsRemoteModel;
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

        Class<?> remoteModelClass = _customViewDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_customViewDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CustomViewDetailsLocalServiceUtil.addCustomViewDetails(this);
        } else {
            CustomViewDetailsLocalServiceUtil.updateCustomViewDetails(this);
        }
    }

    @Override
    public CustomViewDetails toEscapedModel() {
        return (CustomViewDetails) ProxyUtil.newProxyInstance(CustomViewDetails.class.getClassLoader(),
            new Class[] { CustomViewDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CustomViewDetailsClp clone = new CustomViewDetailsClp();

        clone.setHierarchyId(getHierarchyId());
        clone.setHierarchyIndicator(getHierarchyIndicator());
        clone.setCustomViewMasterSid(getCustomViewMasterSid());
        clone.setCustomViewDetailsSid(getCustomViewDetailsSid());
        clone.setLevelNo(getLevelNo());

        return clone;
    }

    @Override
    public int compareTo(CustomViewDetails customViewDetails) {
        int primaryKey = customViewDetails.getPrimaryKey();

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

        if (!(obj instanceof CustomViewDetailsClp)) {
            return false;
        }

        CustomViewDetailsClp customViewDetails = (CustomViewDetailsClp) obj;

        int primaryKey = customViewDetails.getPrimaryKey();

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
        sb.append(", customViewMasterSid=");
        sb.append(getCustomViewMasterSid());
        sb.append(", customViewDetailsSid=");
        sb.append(getCustomViewDetailsSid());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CustomViewDetails");
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
            "<column><column-name>customViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCustomViewMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customViewDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCustomViewDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
