package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;

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


public class ProjectionCustDetailsClp extends BaseModelImpl<ProjectionCustDetails>
    implements ProjectionCustDetails {
    private String _contractName;
    private String _customerName;
    private int _customerDetailsId;
    private String _costCenter;
    private String _customerAlias;
    private String _subLedgerCode;
    private int _projectionId;
    private String _marketType;
    private String _contractNo;
    private BaseModel<?> _projectionCustDetailsRemoteModel;

    public ProjectionCustDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionCustDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionCustDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _customerDetailsId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCustomerDetailsId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _customerDetailsId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractName", getContractName());
        attributes.put("customerName", getCustomerName());
        attributes.put("customerDetailsId", getCustomerDetailsId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("customerAlias", getCustomerAlias());
        attributes.put("subLedgerCode", getSubLedgerCode());
        attributes.put("projectionId", getProjectionId());
        attributes.put("marketType", getMarketType());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        Integer customerDetailsId = (Integer) attributes.get(
                "customerDetailsId");

        if (customerDetailsId != null) {
            setCustomerDetailsId(customerDetailsId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String customerAlias = (String) attributes.get("customerAlias");

        if (customerAlias != null) {
            setCustomerAlias(customerAlias);
        }

        String subLedgerCode = (String) attributes.get("subLedgerCode");

        if (subLedgerCode != null) {
            setSubLedgerCode(subLedgerCode);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    @Override
    public String getContractName() {
        return _contractName;
    }

    @Override
    public void setContractName(String contractName) {
        _contractName = contractName;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerName() {
        return _customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        _customerName = customerName;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerName", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, customerName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerDetailsId() {
        return _customerDetailsId;
    }

    @Override
    public void setCustomerDetailsId(int customerDetailsId) {
        _customerDetailsId = customerDetailsId;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerDetailsId",
                        int.class);

                method.invoke(_projectionCustDetailsRemoteModel,
                    customerDetailsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCostCenter() {
        return _costCenter;
    }

    @Override
    public void setCostCenter(String costCenter) {
        _costCenter = costCenter;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, costCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerAlias() {
        return _customerAlias;
    }

    @Override
    public void setCustomerAlias(String customerAlias) {
        _customerAlias = customerAlias;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerAlias", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, customerAlias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubLedgerCode() {
        return _subLedgerCode;
    }

    @Override
    public void setSubLedgerCode(String subLedgerCode) {
        _subLedgerCode = subLedgerCode;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerCode", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, subLedgerCode);
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

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionId", int.class);

                method.invoke(_projectionCustDetailsRemoteModel, projectionId);
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

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketType", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, marketType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractNo() {
        return _contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        _contractNo = contractNo;

        if (_projectionCustDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionCustDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_projectionCustDetailsRemoteModel, contractNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionCustDetailsRemoteModel() {
        return _projectionCustDetailsRemoteModel;
    }

    public void setProjectionCustDetailsRemoteModel(
        BaseModel<?> projectionCustDetailsRemoteModel) {
        _projectionCustDetailsRemoteModel = projectionCustDetailsRemoteModel;
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

        Class<?> remoteModelClass = _projectionCustDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionCustDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionCustDetailsLocalServiceUtil.addProjectionCustDetails(this);
        } else {
            ProjectionCustDetailsLocalServiceUtil.updateProjectionCustDetails(this);
        }
    }

    @Override
    public ProjectionCustDetails toEscapedModel() {
        return (ProjectionCustDetails) ProxyUtil.newProxyInstance(ProjectionCustDetails.class.getClassLoader(),
            new Class[] { ProjectionCustDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionCustDetailsClp clone = new ProjectionCustDetailsClp();

        clone.setContractName(getContractName());
        clone.setCustomerName(getCustomerName());
        clone.setCustomerDetailsId(getCustomerDetailsId());
        clone.setCostCenter(getCostCenter());
        clone.setCustomerAlias(getCustomerAlias());
        clone.setSubLedgerCode(getSubLedgerCode());
        clone.setProjectionId(getProjectionId());
        clone.setMarketType(getMarketType());
        clone.setContractNo(getContractNo());

        return clone;
    }

    @Override
    public int compareTo(ProjectionCustDetails projectionCustDetails) {
        int primaryKey = projectionCustDetails.getPrimaryKey();

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

        if (!(obj instanceof ProjectionCustDetailsClp)) {
            return false;
        }

        ProjectionCustDetailsClp projectionCustDetails = (ProjectionCustDetailsClp) obj;

        int primaryKey = projectionCustDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{contractName=");
        sb.append(getContractName());
        sb.append(", customerName=");
        sb.append(getCustomerName());
        sb.append(", customerDetailsId=");
        sb.append(getCustomerDetailsId());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", customerAlias=");
        sb.append(getCustomerAlias());
        sb.append(", subLedgerCode=");
        sb.append(getSubLedgerCode());
        sb.append(", projectionId=");
        sb.append(getProjectionId());
        sb.append(", marketType=");
        sb.append(getMarketType());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionCustDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerName</column-name><column-value><![CDATA[");
        sb.append(getCustomerName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerDetailsId</column-name><column-value><![CDATA[");
        sb.append(getCustomerDetailsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerAlias</column-name><column-value><![CDATA[");
        sb.append(getCustomerAlias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedgerCode</column-name><column-value><![CDATA[");
        sb.append(getSubLedgerCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionId</column-name><column-value><![CDATA[");
        sb.append(getProjectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketType</column-name><column-value><![CDATA[");
        sb.append(getMarketType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
