package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class AccClosureDetailsClp extends BaseModelImpl<AccClosureDetails>
    implements AccClosureDetails {
    private int _accClosureDetailsSid;
    private int _ccpDetailsSid;
    private int _accClosureMasterSid;
    private int _rsModelSid;
    private BaseModel<?> _accClosureDetailsRemoteModel;

    public AccClosureDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accClosureDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccClosureDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accClosureDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accClosureDetailsSid", getAccClosureDetailsSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("rsModelSid", getRsModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer accClosureDetailsSid = (Integer) attributes.get(
                "accClosureDetailsSid");

        if (accClosureDetailsSid != null) {
            setAccClosureDetailsSid(accClosureDetailsSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }
    }

    @Override
    public int getAccClosureDetailsSid() {
        return _accClosureDetailsSid;
    }

    @Override
    public void setAccClosureDetailsSid(int accClosureDetailsSid) {
        _accClosureDetailsSid = accClosureDetailsSid;

        if (_accClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureDetailsSid",
                        int.class);

                method.invoke(_accClosureDetailsRemoteModel,
                    accClosureDetailsSid);
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

        if (_accClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_accClosureDetailsRemoteModel, ccpDetailsSid);
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

        if (_accClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_accClosureDetailsRemoteModel, accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_accClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_accClosureDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAccClosureDetailsRemoteModel() {
        return _accClosureDetailsRemoteModel;
    }

    public void setAccClosureDetailsRemoteModel(
        BaseModel<?> accClosureDetailsRemoteModel) {
        _accClosureDetailsRemoteModel = accClosureDetailsRemoteModel;
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

        Class<?> remoteModelClass = _accClosureDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_accClosureDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccClosureDetailsLocalServiceUtil.addAccClosureDetails(this);
        } else {
            AccClosureDetailsLocalServiceUtil.updateAccClosureDetails(this);
        }
    }

    @Override
    public AccClosureDetails toEscapedModel() {
        return (AccClosureDetails) ProxyUtil.newProxyInstance(AccClosureDetails.class.getClassLoader(),
            new Class[] { AccClosureDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AccClosureDetailsClp clone = new AccClosureDetailsClp();

        clone.setAccClosureDetailsSid(getAccClosureDetailsSid());
        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setRsModelSid(getRsModelSid());

        return clone;
    }

    @Override
    public int compareTo(AccClosureDetails accClosureDetails) {
        int primaryKey = accClosureDetails.getPrimaryKey();

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

        if (!(obj instanceof AccClosureDetailsClp)) {
            return false;
        }

        AccClosureDetailsClp accClosureDetails = (AccClosureDetailsClp) obj;

        int primaryKey = accClosureDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{accClosureDetailsSid=");
        sb.append(getAccClosureDetailsSid());
        sb.append(", ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AccClosureDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>accClosureDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
