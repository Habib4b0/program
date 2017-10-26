package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class CffDocDetailsClp extends BaseModelImpl<CffDocDetails>
    implements CffDocDetails {
    private String _fileName;
    private Date _uploadDate;
    private String _fileType;
    private int _uploadBy;
    private int _cffMasterSid;
    private int _cffDocDetailsSid;
    private String _fileSize;
    private BaseModel<?> _cffDocDetailsRemoteModel;

    public CffDocDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffDocDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffDocDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffDocDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffDocDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffDocDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fileName", getFileName());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("fileType", getFileType());
        attributes.put("uploadBy", getUploadBy());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("cffDocDetailsSid", getCffDocDetailsSid());
        attributes.put("fileSize", getFileSize());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        Integer uploadBy = (Integer) attributes.get("uploadBy");

        if (uploadBy != null) {
            setUploadBy(uploadBy);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer cffDocDetailsSid = (Integer) attributes.get("cffDocDetailsSid");

        if (cffDocDetailsSid != null) {
            setCffDocDetailsSid(cffDocDetailsSid);
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

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileName", String.class);

                method.invoke(_cffDocDetailsRemoteModel, fileName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(Date uploadDate) {
        _uploadDate = uploadDate;

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", Date.class);

                method.invoke(_cffDocDetailsRemoteModel, uploadDate);
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

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileType", String.class);

                method.invoke(_cffDocDetailsRemoteModel, fileType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUploadBy() {
        return _uploadBy;
    }

    @Override
    public void setUploadBy(int uploadBy) {
        _uploadBy = uploadBy;

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadBy", int.class);

                method.invoke(_cffDocDetailsRemoteModel, uploadBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffDocDetailsRemoteModel, cffMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffDocDetailsSid() {
        return _cffDocDetailsSid;
    }

    @Override
    public void setCffDocDetailsSid(int cffDocDetailsSid) {
        _cffDocDetailsSid = cffDocDetailsSid;

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCffDocDetailsSid", int.class);

                method.invoke(_cffDocDetailsRemoteModel, cffDocDetailsSid);
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

        if (_cffDocDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cffDocDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileSize", String.class);

                method.invoke(_cffDocDetailsRemoteModel, fileSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffDocDetailsRemoteModel() {
        return _cffDocDetailsRemoteModel;
    }

    public void setCffDocDetailsRemoteModel(
        BaseModel<?> cffDocDetailsRemoteModel) {
        _cffDocDetailsRemoteModel = cffDocDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cffDocDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffDocDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffDocDetailsLocalServiceUtil.addCffDocDetails(this);
        } else {
            CffDocDetailsLocalServiceUtil.updateCffDocDetails(this);
        }
    }

    @Override
    public CffDocDetails toEscapedModel() {
        return (CffDocDetails) ProxyUtil.newProxyInstance(CffDocDetails.class.getClassLoader(),
            new Class[] { CffDocDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffDocDetailsClp clone = new CffDocDetailsClp();

        clone.setFileName(getFileName());
        clone.setUploadDate(getUploadDate());
        clone.setFileType(getFileType());
        clone.setUploadBy(getUploadBy());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setCffDocDetailsSid(getCffDocDetailsSid());
        clone.setFileSize(getFileSize());

        return clone;
    }

    @Override
    public int compareTo(CffDocDetails cffDocDetails) {
        int primaryKey = cffDocDetails.getPrimaryKey();

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

        if (!(obj instanceof CffDocDetailsClp)) {
            return false;
        }

        CffDocDetailsClp cffDocDetails = (CffDocDetailsClp) obj;

        int primaryKey = cffDocDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{fileName=");
        sb.append(getFileName());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", fileType=");
        sb.append(getFileType());
        sb.append(", uploadBy=");
        sb.append(getUploadBy());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", cffDocDetailsSid=");
        sb.append(getCffDocDetailsSid());
        sb.append(", fileSize=");
        sb.append(getFileSize());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffDocDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>fileName</column-name><column-value><![CDATA[");
        sb.append(getFileName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileType</column-name><column-value><![CDATA[");
        sb.append(getFileType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadBy</column-name><column-value><![CDATA[");
        sb.append(getUploadBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffDocDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCffDocDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileSize</column-name><column-value><![CDATA[");
        sb.append(getFileSize());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
