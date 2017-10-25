package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MParityLookupLocalServiceUtil;

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


public class MParityLookupClp extends BaseModelImpl<MParityLookup>
    implements MParityLookup {
    private int _contractMasterSid;
    private String _marketType;
    private int _itemMasterSid;
    private int _mParityLookupSid;
    private int _projectionDetailsSid;
    private BaseModel<?> _mParityLookupRemoteModel;

    public MParityLookupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MParityLookup.class;
    }

    @Override
    public String getModelClassName() {
        return MParityLookup.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _mParityLookupSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setMParityLookupSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _mParityLookupSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("marketType", getMarketType());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("mParityLookupSid", getMParityLookupSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer mParityLookupSid = (Integer) attributes.get("mParityLookupSid");

        if (mParityLookupSid != null) {
            setMParityLookupSid(mParityLookupSid);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_mParityLookupRemoteModel != null) {
            try {
                Class<?> clazz = _mParityLookupRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_mParityLookupRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketType() {
        return _marketType;
    }

    @Override
    public void setMarketType(String marketType) {
        _marketType = marketType;

        if (_mParityLookupRemoteModel != null) {
            try {
                Class<?> clazz = _mParityLookupRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketType", String.class);

                method.invoke(_mParityLookupRemoteModel, marketType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_mParityLookupRemoteModel != null) {
            try {
                Class<?> clazz = _mParityLookupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_mParityLookupRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMParityLookupSid() {
        return _mParityLookupSid;
    }

    @Override
    public void setMParityLookupSid(int mParityLookupSid) {
        _mParityLookupSid = mParityLookupSid;

        if (_mParityLookupRemoteModel != null) {
            try {
                Class<?> clazz = _mParityLookupRemoteModel.getClass();

                Method method = clazz.getMethod("setMParityLookupSid", int.class);

                method.invoke(_mParityLookupRemoteModel, mParityLookupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;

        if (_mParityLookupRemoteModel != null) {
            try {
                Class<?> clazz = _mParityLookupRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mParityLookupRemoteModel, projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMParityLookupRemoteModel() {
        return _mParityLookupRemoteModel;
    }

    public void setMParityLookupRemoteModel(
        BaseModel<?> mParityLookupRemoteModel) {
        _mParityLookupRemoteModel = mParityLookupRemoteModel;
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

        Class<?> remoteModelClass = _mParityLookupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mParityLookupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MParityLookupLocalServiceUtil.addMParityLookup(this);
        } else {
            MParityLookupLocalServiceUtil.updateMParityLookup(this);
        }
    }

    @Override
    public MParityLookup toEscapedModel() {
        return (MParityLookup) ProxyUtil.newProxyInstance(MParityLookup.class.getClassLoader(),
            new Class[] { MParityLookup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MParityLookupClp clone = new MParityLookupClp();

        clone.setContractMasterSid(getContractMasterSid());
        clone.setMarketType(getMarketType());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setMParityLookupSid(getMParityLookupSid());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(MParityLookup mParityLookup) {
        int primaryKey = mParityLookup.getPrimaryKey();

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

        if (!(obj instanceof MParityLookupClp)) {
            return false;
        }

        MParityLookupClp mParityLookup = (MParityLookupClp) obj;

        int primaryKey = mParityLookup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", marketType=");
        sb.append(getMarketType());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", mParityLookupSid=");
        sb.append(getMParityLookupSid());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MParityLookup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketType</column-name><column-value><![CDATA[");
        sb.append(getMarketType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mParityLookupSid</column-name><column-value><![CDATA[");
        sb.append(getMParityLookupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
