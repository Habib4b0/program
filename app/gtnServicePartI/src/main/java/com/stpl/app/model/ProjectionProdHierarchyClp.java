package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;

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


public class ProjectionProdHierarchyClp extends BaseModelImpl<ProjectionProdHierarchy>
    implements ProjectionProdHierarchy {
    private int _projectionMasterSid;
    private int _projectionProdHierarchySid;
    private int _relationshipLevelSid;
    private BaseModel<?> _projectionProdHierarchyRemoteModel;

    public ProjectionProdHierarchyClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionProdHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionProdHierarchy.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionProdHierarchySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionProdHierarchySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionProdHierarchySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("projectionProdHierarchySid",
            getProjectionProdHierarchySid());
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

        Integer projectionProdHierarchySid = (Integer) attributes.get(
                "projectionProdHierarchySid");

        if (projectionProdHierarchySid != null) {
            setProjectionProdHierarchySid(projectionProdHierarchySid);
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

        if (_projectionProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_projectionProdHierarchyRemoteModel,
                    projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionProdHierarchySid() {
        return _projectionProdHierarchySid;
    }

    @Override
    public void setProjectionProdHierarchySid(int projectionProdHierarchySid) {
        _projectionProdHierarchySid = projectionProdHierarchySid;

        if (_projectionProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionProdHierarchySid",
                        int.class);

                method.invoke(_projectionProdHierarchyRemoteModel,
                    projectionProdHierarchySid);
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

        if (_projectionProdHierarchyRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdHierarchyRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_projectionProdHierarchyRemoteModel,
                    relationshipLevelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionProdHierarchyRemoteModel() {
        return _projectionProdHierarchyRemoteModel;
    }

    public void setProjectionProdHierarchyRemoteModel(
        BaseModel<?> projectionProdHierarchyRemoteModel) {
        _projectionProdHierarchyRemoteModel = projectionProdHierarchyRemoteModel;
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

        Class<?> remoteModelClass = _projectionProdHierarchyRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionProdHierarchyRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionProdHierarchyLocalServiceUtil.addProjectionProdHierarchy(this);
        } else {
            ProjectionProdHierarchyLocalServiceUtil.updateProjectionProdHierarchy(this);
        }
    }

    @Override
    public ProjectionProdHierarchy toEscapedModel() {
        return (ProjectionProdHierarchy) ProxyUtil.newProxyInstance(ProjectionProdHierarchy.class.getClassLoader(),
            new Class[] { ProjectionProdHierarchy.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionProdHierarchyClp clone = new ProjectionProdHierarchyClp();

        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setProjectionProdHierarchySid(getProjectionProdHierarchySid());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());

        return clone;
    }

    @Override
    public int compareTo(ProjectionProdHierarchy projectionProdHierarchy) {
        int primaryKey = projectionProdHierarchy.getPrimaryKey();

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

        if (!(obj instanceof ProjectionProdHierarchyClp)) {
            return false;
        }

        ProjectionProdHierarchyClp projectionProdHierarchy = (ProjectionProdHierarchyClp) obj;

        int primaryKey = projectionProdHierarchy.getPrimaryKey();

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
        sb.append(", projectionProdHierarchySid=");
        sb.append(getProjectionProdHierarchySid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionProdHierarchy");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionProdHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getProjectionProdHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
