package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;

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


public class ProjectionProdDetailsClp extends BaseModelImpl<ProjectionProdDetails>
    implements ProjectionProdDetails {
    private String _productName;
    private String _costCenter;
    private String _productNo;
    private String _subLedgerCode;
    private int _productDetailsId;
    private String _brandName;
    private int _projectionId;
    private BaseModel<?> _projectionProdDetailsRemoteModel;

    public ProjectionProdDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionProdDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionProdDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _productDetailsId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProductDetailsId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _productDetailsId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productName", getProductName());
        attributes.put("costCenter", getCostCenter());
        attributes.put("productNo", getProductNo());
        attributes.put("subLedgerCode", getSubLedgerCode());
        attributes.put("productDetailsId", getProductDetailsId());
        attributes.put("brandName", getBrandName());
        attributes.put("projectionId", getProjectionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String productName = (String) attributes.get("productName");

        if (productName != null) {
            setProductName(productName);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String productNo = (String) attributes.get("productNo");

        if (productNo != null) {
            setProductNo(productNo);
        }

        String subLedgerCode = (String) attributes.get("subLedgerCode");

        if (subLedgerCode != null) {
            setSubLedgerCode(subLedgerCode);
        }

        Integer productDetailsId = (Integer) attributes.get("productDetailsId");

        if (productDetailsId != null) {
            setProductDetailsId(productDetailsId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }
    }

    @Override
    public String getProductName() {
        return _productName;
    }

    @Override
    public void setProductName(String productName) {
        _productName = productName;

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProductName", String.class);

                method.invoke(_projectionProdDetailsRemoteModel, productName);
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

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_projectionProdDetailsRemoteModel, costCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProductNo() {
        return _productNo;
    }

    @Override
    public void setProductNo(String productNo) {
        _productNo = productNo;

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProductNo", String.class);

                method.invoke(_projectionProdDetailsRemoteModel, productNo);
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

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerCode", String.class);

                method.invoke(_projectionProdDetailsRemoteModel, subLedgerCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProductDetailsId() {
        return _productDetailsId;
    }

    @Override
    public void setProductDetailsId(int productDetailsId) {
        _productDetailsId = productDetailsId;

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProductDetailsId", int.class);

                method.invoke(_projectionProdDetailsRemoteModel,
                    productDetailsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_projectionProdDetailsRemoteModel, brandName);
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

        if (_projectionProdDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _projectionProdDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionId", int.class);

                method.invoke(_projectionProdDetailsRemoteModel, projectionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionProdDetailsRemoteModel() {
        return _projectionProdDetailsRemoteModel;
    }

    public void setProjectionProdDetailsRemoteModel(
        BaseModel<?> projectionProdDetailsRemoteModel) {
        _projectionProdDetailsRemoteModel = projectionProdDetailsRemoteModel;
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

        Class<?> remoteModelClass = _projectionProdDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionProdDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionProdDetailsLocalServiceUtil.addProjectionProdDetails(this);
        } else {
            ProjectionProdDetailsLocalServiceUtil.updateProjectionProdDetails(this);
        }
    }

    @Override
    public ProjectionProdDetails toEscapedModel() {
        return (ProjectionProdDetails) ProxyUtil.newProxyInstance(ProjectionProdDetails.class.getClassLoader(),
            new Class[] { ProjectionProdDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionProdDetailsClp clone = new ProjectionProdDetailsClp();

        clone.setProductName(getProductName());
        clone.setCostCenter(getCostCenter());
        clone.setProductNo(getProductNo());
        clone.setSubLedgerCode(getSubLedgerCode());
        clone.setProductDetailsId(getProductDetailsId());
        clone.setBrandName(getBrandName());
        clone.setProjectionId(getProjectionId());

        return clone;
    }

    @Override
    public int compareTo(ProjectionProdDetails projectionProdDetails) {
        int primaryKey = projectionProdDetails.getPrimaryKey();

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

        if (!(obj instanceof ProjectionProdDetailsClp)) {
            return false;
        }

        ProjectionProdDetailsClp projectionProdDetails = (ProjectionProdDetailsClp) obj;

        int primaryKey = projectionProdDetails.getPrimaryKey();

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

        sb.append("{productName=");
        sb.append(getProductName());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", productNo=");
        sb.append(getProductNo());
        sb.append(", subLedgerCode=");
        sb.append(getSubLedgerCode());
        sb.append(", productDetailsId=");
        sb.append(getProductDetailsId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", projectionId=");
        sb.append(getProjectionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionProdDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>productName</column-name><column-value><![CDATA[");
        sb.append(getProductName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productNo</column-name><column-value><![CDATA[");
        sb.append(getProductNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedgerCode</column-name><column-value><![CDATA[");
        sb.append(getSubLedgerCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productDetailsId</column-name><column-value><![CDATA[");
        sb.append(getProductDetailsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionId</column-name><column-value><![CDATA[");
        sb.append(getProjectionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
