package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;

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


public class MasterDataFilesClp extends BaseModelImpl<MasterDataFiles>
    implements MasterDataFiles {
    private int _masterTableSid;
    private int _masterDataFilesSid;
    private String _masterTableName;
    private String _filePath;
    private Date _createdDate;
    private int _createdBy;
    private BaseModel<?> _masterDataFilesRemoteModel;

    public MasterDataFilesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MasterDataFiles.class;
    }

    @Override
    public String getModelClassName() {
        return MasterDataFiles.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _masterDataFilesSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setMasterDataFilesSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _masterDataFilesSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("masterTableSid", getMasterTableSid());
        attributes.put("masterDataFilesSid", getMasterDataFilesSid());
        attributes.put("masterTableName", getMasterTableName());
        attributes.put("filePath", getFilePath());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer masterTableSid = (Integer) attributes.get("masterTableSid");

        if (masterTableSid != null) {
            setMasterTableSid(masterTableSid);
        }

        Integer masterDataFilesSid = (Integer) attributes.get(
                "masterDataFilesSid");

        if (masterDataFilesSid != null) {
            setMasterDataFilesSid(masterDataFilesSid);
        }

        String masterTableName = (String) attributes.get("masterTableName");

        if (masterTableName != null) {
            setMasterTableName(masterTableName);
        }

        String filePath = (String) attributes.get("filePath");

        if (filePath != null) {
            setFilePath(filePath);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }
    }

    @Override
    public int getMasterTableSid() {
        return _masterTableSid;
    }

    @Override
    public void setMasterTableSid(int masterTableSid) {
        _masterTableSid = masterTableSid;

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterTableSid", int.class);

                method.invoke(_masterDataFilesRemoteModel, masterTableSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMasterDataFilesSid() {
        return _masterDataFilesSid;
    }

    @Override
    public void setMasterDataFilesSid(int masterDataFilesSid) {
        _masterDataFilesSid = masterDataFilesSid;

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterDataFilesSid",
                        int.class);

                method.invoke(_masterDataFilesRemoteModel, masterDataFilesSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMasterTableName() {
        return _masterTableName;
    }

    @Override
    public void setMasterTableName(String masterTableName) {
        _masterTableName = masterTableName;

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterTableName",
                        String.class);

                method.invoke(_masterDataFilesRemoteModel, masterTableName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFilePath() {
        return _filePath;
    }

    @Override
    public void setFilePath(String filePath) {
        _filePath = filePath;

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setFilePath", String.class);

                method.invoke(_masterDataFilesRemoteModel, filePath);
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

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_masterDataFilesRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_masterDataFilesRemoteModel != null) {
            try {
                Class<?> clazz = _masterDataFilesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_masterDataFilesRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMasterDataFilesRemoteModel() {
        return _masterDataFilesRemoteModel;
    }

    public void setMasterDataFilesRemoteModel(
        BaseModel<?> masterDataFilesRemoteModel) {
        _masterDataFilesRemoteModel = masterDataFilesRemoteModel;
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

        Class<?> remoteModelClass = _masterDataFilesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_masterDataFilesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MasterDataFilesLocalServiceUtil.addMasterDataFiles(this);
        } else {
            MasterDataFilesLocalServiceUtil.updateMasterDataFiles(this);
        }
    }

    @Override
    public MasterDataFiles toEscapedModel() {
        return (MasterDataFiles) ProxyUtil.newProxyInstance(MasterDataFiles.class.getClassLoader(),
            new Class[] { MasterDataFiles.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MasterDataFilesClp clone = new MasterDataFilesClp();

        clone.setMasterTableSid(getMasterTableSid());
        clone.setMasterDataFilesSid(getMasterDataFilesSid());
        clone.setMasterTableName(getMasterTableName());
        clone.setFilePath(getFilePath());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());

        return clone;
    }

    @Override
    public int compareTo(MasterDataFiles masterDataFiles) {
        int primaryKey = masterDataFiles.getPrimaryKey();

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

        if (!(obj instanceof MasterDataFilesClp)) {
            return false;
        }

        MasterDataFilesClp masterDataFiles = (MasterDataFilesClp) obj;

        int primaryKey = masterDataFiles.getPrimaryKey();

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

        sb.append("{masterTableSid=");
        sb.append(getMasterTableSid());
        sb.append(", masterDataFilesSid=");
        sb.append(getMasterDataFilesSid());
        sb.append(", masterTableName=");
        sb.append(getMasterTableName());
        sb.append(", filePath=");
        sb.append(getFilePath());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MasterDataFiles");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>masterTableSid</column-name><column-value><![CDATA[");
        sb.append(getMasterTableSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterDataFilesSid</column-name><column-value><![CDATA[");
        sb.append(getMasterDataFilesSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterTableName</column-name><column-value><![CDATA[");
        sb.append(getMasterTableName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filePath</column-name><column-value><![CDATA[");
        sb.append(getFilePath());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
