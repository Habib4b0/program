package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;

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


public class ProjectionCustHierarchyClp extends BaseModelImpl<ProjectionCustHierarchy>
    implements ProjectionCustHierarchy {
    private int _projectionMasterSid;
    private int _projectionCustHierarchySid;
    private int _relationshipLevelSid;
    private BaseModel<?> _projectionCustHierarchyRemoteModel;

    public ProjectionCustHierarchyClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionCustHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionCustHierarchy.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionCustHierarchySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionCustHierarchySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionCustHierarchySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("projectionCustHierarchySid",
            getProjectionCustHierarchySid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer projectionCustHierarchySid = (Integer) attributes.get(
                "projectionCustHierarchySid");

        if (projectionCustHierarchySid != null) {
            setProjectionCustHierarchySid(projectionCustHierarchySid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }
    }

    @Override
    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;

        if (_projectionCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_projectionCustHierarchyRemoteModel,
                    projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionCustHierarchySid() {
        return _projectionCustHierarchySid;
    }

    @Override
    public void setProjectionCustHierarchySid(int projectionCustHierarchySid) {
        _projectionCustHierarchySid = projectionCustHierarchySid;

        if (_projectionCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionCustHierarchySid",
                        int.class);

                method.invoke(_projectionCustHierarchyRemoteModel,
                    projectionCustHierarchySid);
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

        if (_projectionCustHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_projectionCustHierarchyRemoteModel,
                    relationshipLevelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionCustHierarchyRemoteModel() {
        return _projectionCustHierarchyRemoteModel;
    }

    public void setProjectionCustHierarchyRemoteModel(
        BaseModel<?> projectionCustHierarchyRemoteModel) {
        _projectionCustHierarchyRemoteModel = projectionCustHierarchyRemoteModel;
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

        Class<?> remoteModelClass = _projectionCustHierarchyRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionCustHierarchyRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionCustHierarchyLocalServiceUtil.addProjectionCustHierarchy(this);
        } else {
            ProjectionCustHierarchyLocalServiceUtil.updateProjectionCustHierarchy(this);
        }
    }

    @Override
    public ProjectionCustHierarchy toEscapedModel() {
        return (ProjectionCustHierarchy) ProxyUtil.newProxyInstance(ProjectionCustHierarchy.class.getClassLoader(),
            new Class[] { ProjectionCustHierarchy.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionCustHierarchyClp clone = new ProjectionCustHierarchyClp();

        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setProjectionCustHierarchySid(getProjectionCustHierarchySid());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());

        return clone;
    }

    @Override
    public int compareTo(ProjectionCustHierarchy projectionCustHierarchy) {
        int primaryKey = projectionCustHierarchy.getPrimaryKey();

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

        if (!(obj instanceof ProjectionCustHierarchyClp)) {
            return false;
        }

        ProjectionCustHierarchyClp projectionCustHierarchy = (ProjectionCustHierarchyClp) obj;

        int primaryKey = projectionCustHierarchy.getPrimaryKey();

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

        sb.append("{projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", projectionCustHierarchySid=");
        sb.append(getProjectionCustHierarchySid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionCustHierarchy");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionCustHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getProjectionCustHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
