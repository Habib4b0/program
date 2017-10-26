package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DocDetailsLocalServiceUtil;

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


public class DocDetailsClp extends BaseModelImpl<DocDetails>
    implements DocDetails {
    private String _fileName;
    private String _fileType;
    private String _uploadedBy;
    private String _forecastType;
    private int _projectionId;
    private int _docDetailsId;
    private Date _uploadedDate;
    private String _fileSize;
    private BaseModel<?> _docDetailsRemoteModel;

    public DocDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DocDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DocDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _docDetailsId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDocDetailsId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _docDetailsId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fileName", getFileName());
        attributes.put("fileType", getFileType());
        attributes.put("uploadedBy", getUploadedBy());
        attributes.put("forecastType", getForecastType());
        attributes.put("projectionId", getProjectionId());
        attributes.put("docDetailsId", getDocDetailsId());
        attributes.put("uploadedDate", getUploadedDate());
        attributes.put("fileSize", getFileSize());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        String uploadedBy = (String) attributes.get("uploadedBy");

        if (uploadedBy != null) {
            setUploadedBy(uploadedBy);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        Integer docDetailsId = (Integer) attributes.get("docDetailsId");

        if (docDetailsId != null) {
            setDocDetailsId(docDetailsId);
        }

        Date uploadedDate = (Date) attributes.get("uploadedDate");

        if (uploadedDate != null) {
            setUploadedDate(uploadedDate);
        }

        String fileSize = (String) attributes.get("fileSize");

        if (fileSize != null) {
            setFileSize(fileSize);
        }
    }

    @Override
    public String getFileName() {
        return _fileName;
    }

    @Override
    public void setFileName(String fileName) {
        _fileName = fileName;

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileName", String.class);

                method.invoke(_docDetailsRemoteModel, fileName);
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

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileType", String.class);

                method.invoke(_docDetailsRemoteModel, fileType);
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

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadedBy", String.class);

                method.invoke(_docDetailsRemoteModel, uploadedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastType() {
        return _forecastType;
    }

    @Override
    public void setForecastType(String forecastType) {
        _forecastType = forecastType;

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_docDetailsRemoteModel, forecastType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionId() {
        return _projectionId;
    }

    @Override
    public void setProjectionId(int projectionId) {
        _projectionId = projectionId;

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionId", int.class);

                method.invoke(_docDetailsRemoteModel, projectionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDocDetailsId() {
        return _docDetailsId;
    }

    @Override
    public void setDocDetailsId(int docDetailsId) {
        _docDetailsId = docDetailsId;

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDocDetailsId", int.class);

                method.invoke(_docDetailsRemoteModel, docDetailsId);
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

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadedDate", Date.class);

                method.invoke(_docDetailsRemoteModel, uploadedDate);
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

        if (_docDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _docDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileSize", String.class);

                method.invoke(_docDetailsRemoteModel, fileSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDocDetailsRemoteModel() {
        return _docDetailsRemoteModel;
    }

    public void setDocDetailsRemoteModel(BaseModel<?> docDetailsRemoteModel) {
        _docDetailsRemoteModel = docDetailsRemoteModel;
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

        Class<?> remoteModelClass = _docDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_docDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DocDetailsLocalServiceUtil.addDocDetails(this);
        } else {
            DocDetailsLocalServiceUtil.updateDocDetails(this);
        }
    }

    @Override
    public DocDetails toEscapedModel() {
        return (DocDetails) ProxyUtil.newProxyInstance(DocDetails.class.getClassLoader(),
            new Class[] { DocDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DocDetailsClp clone = new DocDetailsClp();

        clone.setFileName(getFileName());
        clone.setFileType(getFileType());
        clone.setUploadedBy(getUploadedBy());
        clone.setForecastType(getForecastType());
        clone.setProjectionId(getProjectionId());
        clone.setDocDetailsId(getDocDetailsId());
        clone.setUploadedDate(getUploadedDate());
        clone.setFileSize(getFileSize());

        return clone;
    }

    @Override
    public int compareTo(DocDetails docDetails) {
        int primaryKey = docDetails.getPrimaryKey();

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

        if (!(obj instanceof DocDetailsClp)) {
            return false;
        }

        DocDetailsClp docDetails = (DocDetailsClp) obj;

        int primaryKey = docDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{fileName=");
        sb.append(getFileName());
        sb.append(", fileType=");
        sb.append(getFileType());
        sb.append(", uploadedBy=");
        sb.append(getUploadedBy());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", projectionId=");
        sb.append(getProjectionId());
        sb.append(", docDetailsId=");
        sb.append(getDocDetailsId());
        sb.append(", uploadedDate=");
        sb.append(getUploadedDate());
        sb.append(", fileSize=");
        sb.append(getFileSize());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DocDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>fileName</column-name><column-value><![CDATA[");
        sb.append(getFileName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileType</column-name><column-value><![CDATA[");
        sb.append(getFileType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadedBy</column-name><column-value><![CDATA[");
        sb.append(getUploadedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastType</column-name><column-value><![CDATA[");
        sb.append(getForecastType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionId</column-name><column-value><![CDATA[");
        sb.append(getProjectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>docDetailsId</column-name><column-value><![CDATA[");
        sb.append(getDocDetailsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadedDate</column-name><column-value><![CDATA[");
        sb.append(getUploadedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileSize</column-name><column-value><![CDATA[");
        sb.append(getFileSize());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
