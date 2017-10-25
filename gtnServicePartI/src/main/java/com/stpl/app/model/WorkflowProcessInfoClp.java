package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.WorkflowProcessInfoLocalServiceUtil;

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


public class WorkflowProcessInfoClp extends BaseModelImpl<WorkflowProcessInfo>
    implements WorkflowProcessInfo {
    private int _processInstanceId;
    private int _projectionMasterSid;
    private int _workflowProcessInfoSid;
    private int _accClosureMasterSid;
    private int _contractMasterSid;
    private String _contractStructure;
    private BaseModel<?> _workflowProcessInfoRemoteModel;

    public WorkflowProcessInfoClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return WorkflowProcessInfo.class;
    }

    @Override
    public String getModelClassName() {
        return WorkflowProcessInfo.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _workflowProcessInfoSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setWorkflowProcessInfoSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _workflowProcessInfoSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("processInstanceId", getProcessInstanceId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("workflowProcessInfoSid", getWorkflowProcessInfoSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractStructure", getContractStructure());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer processInstanceId = (Integer) attributes.get(
                "processInstanceId");

        if (processInstanceId != null) {
            setProcessInstanceId(processInstanceId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer workflowProcessInfoSid = (Integer) attributes.get(
                "workflowProcessInfoSid");

        if (workflowProcessInfoSid != null) {
            setWorkflowProcessInfoSid(workflowProcessInfoSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String contractStructure = (String) attributes.get("contractStructure");

        if (contractStructure != null) {
            setContractStructure(contractStructure);
        }
    }

    @Override
    public int getProcessInstanceId() {
        return _processInstanceId;
    }

    @Override
    public void setProcessInstanceId(int processInstanceId) {
        _processInstanceId = processInstanceId;

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessInstanceId",
                        int.class);

                method.invoke(_workflowProcessInfoRemoteModel, processInstanceId);
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

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_workflowProcessInfoRemoteModel,
                    projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWorkflowProcessInfoSid() {
        return _workflowProcessInfoSid;
    }

    @Override
    public void setWorkflowProcessInfoSid(int workflowProcessInfoSid) {
        _workflowProcessInfoSid = workflowProcessInfoSid;

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowProcessInfoSid",
                        int.class);

                method.invoke(_workflowProcessInfoRemoteModel,
                    workflowProcessInfoSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_workflowProcessInfoRemoteModel,
                    accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_workflowProcessInfoRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractStructure() {
        return _contractStructure;
    }

    @Override
    public void setContractStructure(String contractStructure) {
        _contractStructure = contractStructure;

        if (_workflowProcessInfoRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProcessInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setContractStructure",
                        String.class);

                method.invoke(_workflowProcessInfoRemoteModel, contractStructure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getWorkflowProcessInfoRemoteModel() {
        return _workflowProcessInfoRemoteModel;
    }

    public void setWorkflowProcessInfoRemoteModel(
        BaseModel<?> workflowProcessInfoRemoteModel) {
        _workflowProcessInfoRemoteModel = workflowProcessInfoRemoteModel;
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

        Class<?> remoteModelClass = _workflowProcessInfoRemoteModel.getClass();

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

        Object returnValue = method.invoke(_workflowProcessInfoRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            WorkflowProcessInfoLocalServiceUtil.addWorkflowProcessInfo(this);
        } else {
            WorkflowProcessInfoLocalServiceUtil.updateWorkflowProcessInfo(this);
        }
    }

    @Override
    public WorkflowProcessInfo toEscapedModel() {
        return (WorkflowProcessInfo) ProxyUtil.newProxyInstance(WorkflowProcessInfo.class.getClassLoader(),
            new Class[] { WorkflowProcessInfo.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        WorkflowProcessInfoClp clone = new WorkflowProcessInfoClp();

        clone.setProcessInstanceId(getProcessInstanceId());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setWorkflowProcessInfoSid(getWorkflowProcessInfoSid());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setContractStructure(getContractStructure());

        return clone;
    }

    @Override
    public int compareTo(WorkflowProcessInfo workflowProcessInfo) {
        int primaryKey = workflowProcessInfo.getPrimaryKey();

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

        if (!(obj instanceof WorkflowProcessInfoClp)) {
            return false;
        }

        WorkflowProcessInfoClp workflowProcessInfo = (WorkflowProcessInfoClp) obj;

        int primaryKey = workflowProcessInfo.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{processInstanceId=");
        sb.append(getProcessInstanceId());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", workflowProcessInfoSid=");
        sb.append(getWorkflowProcessInfoSid());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", contractStructure=");
        sb.append(getContractStructure());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.WorkflowProcessInfo");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>processInstanceId</column-name><column-value><![CDATA[");
        sb.append(getProcessInstanceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowProcessInfoSid</column-name><column-value><![CDATA[");
        sb.append(getWorkflowProcessInfoSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractStructure</column-name><column-value><![CDATA[");
        sb.append(getContractStructure());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
