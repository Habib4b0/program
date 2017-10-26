package com.stpl.app.model;

import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class CcpDetailsClp extends BaseModelImpl<CcpDetails>
    implements CcpDetails {
    private int _itemMasterSid;
    private int _contractMasterSid;
    private int _ccpDetailsSid;
    private int _companyMasterSid;
    private BaseModel<?> _ccpDetailsRemoteModel;

    public CcpDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CcpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CcpDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ccpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCcpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ccpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_ccpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ccpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_ccpDetailsRemoteModel, itemMasterSid);
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

        if (_ccpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ccpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_ccpDetailsRemoteModel, contractMasterSid);
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

        if (_ccpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ccpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_ccpDetailsRemoteModel, ccpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_ccpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ccpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_ccpDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCcpDetailsRemoteModel() {
        return _ccpDetailsRemoteModel;
    }

    public void setCcpDetailsRemoteModel(BaseModel<?> ccpDetailsRemoteModel) {
        _ccpDetailsRemoteModel = ccpDetailsRemoteModel;
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

        Class<?> remoteModelClass = _ccpDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ccpDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CcpDetailsLocalServiceUtil.addCcpDetails(this);
        } else {
            CcpDetailsLocalServiceUtil.updateCcpDetails(this);
        }
    }

    @Override
    public CcpDetails toEscapedModel() {
        return (CcpDetails) ProxyUtil.newProxyInstance(CcpDetails.class.getClassLoader(),
            new Class[] { CcpDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CcpDetailsClp clone = new CcpDetailsClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CcpDetails ccpDetails) {
        int primaryKey = ccpDetails.getPrimaryKey();

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

        if (!(obj instanceof CcpDetailsClp)) {
            return false;
        }

        CcpDetailsClp ccpDetails = (CcpDetailsClp) obj;

        int primaryKey = ccpDetails.getPrimaryKey();

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

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CcpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
