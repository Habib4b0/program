package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.FileManagementLocalServiceUtil;

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


public class FileManagementClp extends BaseModelImpl<FileManagement>
    implements FileManagement {
    private int _country;
    private Date _fromPeriod;
    private int _versionNo;
    private String _forecastSource;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _version;
    private String _fileSource;
    private Date _toPeriod;
    private int _modifiedBy;
    private int _fileManagementSid;
    private String _forecastName;
    private int _fileType;
    private BaseModel<?> _fileManagementRemoteModel;

    public FileManagementClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FileManagement.class;
    }

    @Override
    public String getModelClassName() {
        return FileManagement.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _fileManagementSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setFileManagementSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _fileManagementSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("country", getCountry());
        attributes.put("fromPeriod", getFromPeriod());
        attributes.put("versionNo", getVersionNo());
        attributes.put("forecastSource", getForecastSource());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("version", getVersion());
        attributes.put("fileSource", getFileSource());
        attributes.put("toPeriod", getToPeriod());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("fileManagementSid", getFileManagementSid());
        attributes.put("forecastName", getForecastName());
        attributes.put("fileType", getFileType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer country = (Integer) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Date fromPeriod = (Date) attributes.get("fromPeriod");

        if (fromPeriod != null) {
            setFromPeriod(fromPeriod);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String forecastSource = (String) attributes.get("forecastSource");

        if (forecastSource != null) {
            setForecastSource(forecastSource);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String version = (String) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        String fileSource = (String) attributes.get("fileSource");

        if (fileSource != null) {
            setFileSource(fileSource);
        }

        Date toPeriod = (Date) attributes.get("toPeriod");

        if (toPeriod != null) {
            setToPeriod(toPeriod);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer fileManagementSid = (Integer) attributes.get(
                "fileManagementSid");

        if (fileManagementSid != null) {
            setFileManagementSid(fileManagementSid);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Integer fileType = (Integer) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }
    }

    @Override
    public int getCountry() {
        return _country;
    }

    @Override
    public void setCountry(int country) {
        _country = country;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", int.class);

                method.invoke(_fileManagementRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFromPeriod() {
        return _fromPeriod;
    }

    @Override
    public void setFromPeriod(Date fromPeriod) {
        _fromPeriod = fromPeriod;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setFromPeriod", Date.class);

                method.invoke(_fileManagementRemoteModel, fromPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_fileManagementRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastSource() {
        return _forecastSource;
    }

    @Override
    public void setForecastSource(String forecastSource) {
        _forecastSource = forecastSource;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastSource",
                        String.class);

                method.invoke(_fileManagementRemoteModel, forecastSource);
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

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_fileManagementRemoteModel, modifiedDate);
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

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_fileManagementRemoteModel, createdBy);
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

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_fileManagementRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getVersion() {
        return _version;
    }

    @Override
    public void setVersion(String version) {
        _version = version;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", String.class);

                method.invoke(_fileManagementRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileSource() {
        return _fileSource;
    }

    @Override
    public void setFileSource(String fileSource) {
        _fileSource = fileSource;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setFileSource", String.class);

                method.invoke(_fileManagementRemoteModel, fileSource);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getToPeriod() {
        return _toPeriod;
    }

    @Override
    public void setToPeriod(Date toPeriod) {
        _toPeriod = toPeriod;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setToPeriod", Date.class);

                method.invoke(_fileManagementRemoteModel, toPeriod);
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

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_fileManagementRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFileManagementSid() {
        return _fileManagementSid;
    }

    @Override
    public void setFileManagementSid(int fileManagementSid) {
        _fileManagementSid = fileManagementSid;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setFileManagementSid",
                        int.class);

                method.invoke(_fileManagementRemoteModel, fileManagementSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastName() {
        return _forecastName;
    }

    @Override
    public void setForecastName(String forecastName) {
        _forecastName = forecastName;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_fileManagementRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFileType() {
        return _fileType;
    }

    @Override
    public void setFileType(int fileType) {
        _fileType = fileType;

        if (_fileManagementRemoteModel != null) {
            try {
                Class<?> clazz = _fileManagementRemoteModel.getClass();

                Method method = clazz.getMethod("setFileType", int.class);

                method.invoke(_fileManagementRemoteModel, fileType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFileManagementRemoteModel() {
        return _fileManagementRemoteModel;
    }

    public void setFileManagementRemoteModel(
        BaseModel<?> fileManagementRemoteModel) {
        _fileManagementRemoteModel = fileManagementRemoteModel;
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

        Class<?> remoteModelClass = _fileManagementRemoteModel.getClass();

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

        Object returnValue = method.invoke(_fileManagementRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FileManagementLocalServiceUtil.addFileManagement(this);
        } else {
            FileManagementLocalServiceUtil.updateFileManagement(this);
        }
    }

    @Override
    public FileManagement toEscapedModel() {
        return (FileManagement) ProxyUtil.newProxyInstance(FileManagement.class.getClassLoader(),
            new Class[] { FileManagement.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FileManagementClp clone = new FileManagementClp();

        clone.setCountry(getCountry());
        clone.setFromPeriod(getFromPeriod());
        clone.setVersionNo(getVersionNo());
        clone.setForecastSource(getForecastSource());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setVersion(getVersion());
        clone.setFileSource(getFileSource());
        clone.setToPeriod(getToPeriod());
        clone.setModifiedBy(getModifiedBy());
        clone.setFileManagementSid(getFileManagementSid());
        clone.setForecastName(getForecastName());
        clone.setFileType(getFileType());

        return clone;
    }

    @Override
    public int compareTo(FileManagement fileManagement) {
        int primaryKey = fileManagement.getPrimaryKey();

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

        if (!(obj instanceof FileManagementClp)) {
            return false;
        }

        FileManagementClp fileManagement = (FileManagementClp) obj;

        int primaryKey = fileManagement.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{country=");
        sb.append(getCountry());
        sb.append(", fromPeriod=");
        sb.append(getFromPeriod());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", forecastSource=");
        sb.append(getForecastSource());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", fileSource=");
        sb.append(getFileSource());
        sb.append(", toPeriod=");
        sb.append(getToPeriod());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", fileManagementSid=");
        sb.append(getFileManagementSid());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", fileType=");
        sb.append(getFileType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FileManagement");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromPeriod</column-name><column-value><![CDATA[");
        sb.append(getFromPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastSource</column-name><column-value><![CDATA[");
        sb.append(getForecastSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileSource</column-name><column-value><![CDATA[");
        sb.append(getFileSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>toPeriod</column-name><column-value><![CDATA[");
        sb.append(getToPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileManagementSid</column-name><column-value><![CDATA[");
        sb.append(getFileManagementSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileType</column-name><column-value><![CDATA[");
        sb.append(getFileType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
