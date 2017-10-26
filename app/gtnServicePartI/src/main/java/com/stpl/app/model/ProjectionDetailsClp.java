package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;

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


public class ProjectionDetailsClp extends BaseModelImpl<ProjectionDetails>
    implements ProjectionDetails {
    private int _projectionDetailsSid;
    private int _ccpDetailsSid;
    private int _projectionMasterSid;
    private BaseModel<?> _projectionDetailsRemoteModel;

    public ProjectionDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }
    }

    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;

        if (_projectionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_projectionDetailsRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;

        if (_projectionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_projectionDetailsRemoteModel, ccpDetailsSid);
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

        if (_projectionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_projectionDetailsRemoteModel, projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionDetailsRemoteModel() {
        return _projectionDetailsRemoteModel;
    }

    public void setProjectionDetailsRemoteModel(
        BaseModel<?> projectionDetailsRemoteModel) {
        _projectionDetailsRemoteModel = projectionDetailsRemoteModel;
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

        Class<?> remoteModelClass = _projectionDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionDetailsLocalServiceUtil.addProjectionDetails(this);
        } else {
            ProjectionDetailsLocalServiceUtil.updateProjectionDetails(this);
        }
    }

    @Override
    public ProjectionDetails toEscapedModel() {
        return (ProjectionDetails) ProxyUtil.newProxyInstance(ProjectionDetails.class.getClassLoader(),
            new Class[] { ProjectionDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionDetailsClp clone = new ProjectionDetailsClp();

        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setProjectionMasterSid(getProjectionMasterSid());

        return clone;
    }

    @Override
    public int compareTo(ProjectionDetails projectionDetails) {
        int primaryKey = projectionDetails.getPrimaryKey();

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

        if (!(obj instanceof ProjectionDetailsClp)) {
            return false;
        }

        ProjectionDetailsClp projectionDetails = (ProjectionDetailsClp) obj;

        int primaryKey = projectionDetails.getPrimaryKey();

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

        sb.append("{projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
