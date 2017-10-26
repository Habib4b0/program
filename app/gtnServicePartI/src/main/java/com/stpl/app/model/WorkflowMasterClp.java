package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WorkflowMasterClp extends BaseModelImpl<WorkflowMaster>
    implements WorkflowMaster {
    private int _createdBy;
    private String _fileSize;
    private int _workflowStatusId;
    private int _modifiedBy;
    private Date _createdDate;
    private int _approvalLevel;
    private int _noOfApproval;
    private String _fileName;
    private String _uploadedBy;
    private Date _modifiedDate;
    private int _accClosureMasterSid;
    private String _notes;
    private int _workflowMasterSid;
    private String _workflowId;
    private int _projectionMasterSid;
    private Date _uploadedDate;
    private String _fileType;
    private int _approvedBy;
    private String _workflowDescrption;
    private Date _approvedDate;
    private int _contractMasterSid;
    private String _contractStructure;
    private BaseModel<?> _workflowMasterRemoteModel;

    public WorkflowMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return WorkflowMaster.class;
    }

    @Override
    public String getModelClassName() {
        return WorkflowMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _workflowMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setWorkflowMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _workflowMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("fileSize", getFileSize());
        attributes.put("workflowStatusId", getWorkflowStatusId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("approvalLevel", getApprovalLevel());
        attributes.put("noOfApproval", getNoOfApproval());
        attributes.put("fileName", getFileName());
        attributes.put("uploadedBy", getUploadedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("notes", getNotes());
        attributes.put("workflowMasterSid", getWorkflowMasterSid());
        attributes.put("workflowId", getWorkflowId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("uploadedDate", getUploadedDate());
        attributes.put("fileType", getFileType());
        attributes.put("approvedBy", getApprovedBy());
        attributes.put("workflowDescrption", getWorkflowDescrption());
        attributes.put("approvedDate", getApprovedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractStructure", getContractStructure());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String fileSize = (String) attributes.get("fileSize");

        if (fileSize != null) {
            setFileSize(fileSize);
        }

        Integer workflowStatusId = (Integer) attributes.get("workflowStatusId");

        if (workflowStatusId != null) {
            setWorkflowStatusId(workflowStatusId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer approvalLevel = (Integer) attributes.get("approvalLevel");

        if (approvalLevel != null) {
            setApprovalLevel(approvalLevel);
        }

        Integer noOfApproval = (Integer) attributes.get("noOfApproval");

        if (noOfApproval != null) {
            setNoOfApproval(noOfApproval);
        }

        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        String uploadedBy = (String) attributes.get("uploadedBy");

        if (uploadedBy != null) {
            setUploadedBy(uploadedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer workflowMasterSid = (Integer) attributes.get(
                "workflowMasterSid");

        if (workflowMasterSid != null) {
            setWorkflowMasterSid(workflowMasterSid);
        }

        String workflowId = (String) attributes.get("workflowId");

        if (workflowId != null) {
            setWorkflowId(workflowId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Date uploadedDate = (Date) attributes.get("uploadedDate");

        if (uploadedDate != null) {
            setUploadedDate(uploadedDate);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        Integer approvedBy = (Integer) attributes.get("approvedBy");

        if (approvedBy != null) {
            setApprovedBy(approvedBy);
        }

        String workflowDescrption = (String) attributes.get(
                "workflowDescrption");

        if (workflowDescrption != null) {
            setWorkflowDescrption(workflowDescrption);
        }

        Date approvedDate = (Date) attributes.get("approvedDate");

        if (approvedDate != null) {
            setApprovedDate(approvedDate);
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
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_workflowMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileSize() {
        return _fileSize;
    }

    @Override
    public void setFileSize(String fileSize) {
        _fileSize = fileSize;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFileSize", String.class);

                method.invoke(_workflowMasterRemoteModel, fileSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWorkflowStatusId() {
        return _workflowStatusId;
    }

    @Override
    public void setWorkflowStatusId(int workflowStatusId) {
        _workflowStatusId = workflowStatusId;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowStatusId", int.class);

                method.invoke(_workflowMasterRemoteModel, workflowStatusId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_workflowMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_workflowMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getApprovalLevel() {
        return _approvalLevel;
    }

    @Override
    public void setApprovalLevel(int approvalLevel) {
        _approvalLevel = approvalLevel;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovalLevel", int.class);

                method.invoke(_workflowMasterRemoteModel, approvalLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNoOfApproval() {
        return _noOfApproval;
    }

    @Override
    public void setNoOfApproval(int noOfApproval) {
        _noOfApproval = noOfApproval;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNoOfApproval", int.class);

                method.invoke(_workflowMasterRemoteModel, noOfApproval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileName() {
        return _fileName;
    }

    @Override
    public void setFileName(String fileName) {
        _fileName = fileName;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFileName", String.class);

                method.invoke(_workflowMasterRemoteModel, fileName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUploadedBy() {
        return _uploadedBy;
    }

    @Override
    public void setUploadedBy(String uploadedBy) {
        _uploadedBy = uploadedBy;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadedBy", String.class);

                method.invoke(_workflowMasterRemoteModel, uploadedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_workflowMasterRemoteModel, modifiedDate);
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

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_workflowMasterRemoteModel, accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_workflowMasterRemoteModel, notes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWorkflowMasterSid() {
        return _workflowMasterSid;
    }

    @Override
    public void setWorkflowMasterSid(int workflowMasterSid) {
        _workflowMasterSid = workflowMasterSid;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowMasterSid",
                        int.class);

                method.invoke(_workflowMasterRemoteModel, workflowMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowId() {
        return _workflowId;
    }

    @Override
    public void setWorkflowId(String workflowId) {
        _workflowId = workflowId;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowId", String.class);

                method.invoke(_workflowMasterRemoteModel, workflowId);
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

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_workflowMasterRemoteModel, projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUploadedDate() {
        return _uploadedDate;
    }

    @Override
    public void setUploadedDate(Date uploadedDate) {
        _uploadedDate = uploadedDate;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadedDate", Date.class);

                method.invoke(_workflowMasterRemoteModel, uploadedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileType() {
        return _fileType;
    }

    @Override
    public void setFileType(String fileType) {
        _fileType = fileType;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFileType", String.class);

                method.invoke(_workflowMasterRemoteModel, fileType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getApprovedBy() {
        return _approvedBy;
    }

    @Override
    public void setApprovedBy(int approvedBy) {
        _approvedBy = approvedBy;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovedBy", int.class);

                method.invoke(_workflowMasterRemoteModel, approvedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowDescrption() {
        return _workflowDescrption;
    }

    @Override
    public void setWorkflowDescrption(String workflowDescrption) {
        _workflowDescrption = workflowDescrption;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowDescrption",
                        String.class);

                method.invoke(_workflowMasterRemoteModel, workflowDescrption);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getApprovedDate() {
        return _approvedDate;
    }

    @Override
    public void setApprovedDate(Date approvedDate) {
        _approvedDate = approvedDate;

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setApprovedDate", Date.class);

                method.invoke(_workflowMasterRemoteModel, approvedDate);
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

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_workflowMasterRemoteModel, contractMasterSid);
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

        if (_workflowMasterRemoteModel != null) {
            try {
                Class<?> clazz = _workflowMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractStructure",
                        String.class);

                method.invoke(_workflowMasterRemoteModel, contractStructure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getWorkflowMasterRemoteModel() {
        return _workflowMasterRemoteModel;
    }

    public void setWorkflowMasterRemoteModel(
        BaseModel<?> workflowMasterRemoteModel) {
        _workflowMasterRemoteModel = workflowMasterRemoteModel;
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

        Class<?> remoteModelClass = _workflowMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_workflowMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            WorkflowMasterLocalServiceUtil.addWorkflowMaster(this);
        } else {
            WorkflowMasterLocalServiceUtil.updateWorkflowMaster(this);
        }
    }

    @Override
    public WorkflowMaster toEscapedModel() {
        return (WorkflowMaster) ProxyUtil.newProxyInstance(WorkflowMaster.class.getClassLoader(),
            new Class[] { WorkflowMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        WorkflowMasterClp clone = new WorkflowMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setFileSize(getFileSize());
        clone.setWorkflowStatusId(getWorkflowStatusId());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setApprovalLevel(getApprovalLevel());
        clone.setNoOfApproval(getNoOfApproval());
        clone.setFileName(getFileName());
        clone.setUploadedBy(getUploadedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setNotes(getNotes());
        clone.setWorkflowMasterSid(getWorkflowMasterSid());
        clone.setWorkflowId(getWorkflowId());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setUploadedDate(getUploadedDate());
        clone.setFileType(getFileType());
        clone.setApprovedBy(getApprovedBy());
        clone.setWorkflowDescrption(getWorkflowDescrption());
        clone.setApprovedDate(getApprovedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setContractStructure(getContractStructure());

        return clone;
    }

    @Override
    public int compareTo(WorkflowMaster workflowMaster) {
        int primaryKey = workflowMaster.getPrimaryKey();

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

        if (!(obj instanceof WorkflowMasterClp)) {
            return false;
        }

        WorkflowMasterClp workflowMaster = (WorkflowMasterClp) obj;

        int primaryKey = workflowMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", fileSize=");
        sb.append(getFileSize());
        sb.append(", workflowStatusId=");
        sb.append(getWorkflowStatusId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", approvalLevel=");
        sb.append(getApprovalLevel());
        sb.append(", noOfApproval=");
        sb.append(getNoOfApproval());
        sb.append(", fileName=");
        sb.append(getFileName());
        sb.append(", uploadedBy=");
        sb.append(getUploadedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", workflowMasterSid=");
        sb.append(getWorkflowMasterSid());
        sb.append(", workflowId=");
        sb.append(getWorkflowId());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", uploadedDate=");
        sb.append(getUploadedDate());
        sb.append(", fileType=");
        sb.append(getFileType());
        sb.append(", approvedBy=");
        sb.append(getApprovedBy());
        sb.append(", workflowDescrption=");
        sb.append(getWorkflowDescrption());
        sb.append(", approvedDate=");
        sb.append(getApprovedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", contractStructure=");
        sb.append(getContractStructure());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.WorkflowMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileSize</column-name><column-value><![CDATA[");
        sb.append(getFileSize());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowStatusId</column-name><column-value><![CDATA[");
        sb.append(getWorkflowStatusId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvalLevel</column-name><column-value><![CDATA[");
        sb.append(getApprovalLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>noOfApproval</column-name><column-value><![CDATA[");
        sb.append(getNoOfApproval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileName</column-name><column-value><![CDATA[");
        sb.append(getFileName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadedBy</column-name><column-value><![CDATA[");
        sb.append(getUploadedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowMasterSid</column-name><column-value><![CDATA[");
        sb.append(getWorkflowMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowId</column-name><column-value><![CDATA[");
        sb.append(getWorkflowId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadedDate</column-name><column-value><![CDATA[");
        sb.append(getUploadedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileType</column-name><column-value><![CDATA[");
        sb.append(getFileType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvedBy</column-name><column-value><![CDATA[");
        sb.append(getApprovedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowDescrption</column-name><column-value><![CDATA[");
        sb.append(getWorkflowDescrption());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>approvedDate</column-name><column-value><![CDATA[");
        sb.append(getApprovedDate());
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
