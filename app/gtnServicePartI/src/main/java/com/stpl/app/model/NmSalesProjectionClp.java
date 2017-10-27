package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmSalesProjectionPK;

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


public class NmSalesProjectionClp extends BaseModelImpl<NmSalesProjection>
    implements NmSalesProjection {
    private int _periodSid;
    private double _productGrowth;
    private int _projectionDetailsSid;
    private double _accountGrowth;
    private double _projectionUnits;
    private double _projectionSales;
    private BaseModel<?> _nmSalesProjectionRemoteModel;

    public NmSalesProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmSalesProjection.class;
    }

    @Override
    public String getModelClassName() {
        return NmSalesProjection.class.getName();
    }

    @Override
    public NmSalesProjectionPK getPrimaryKey() {
        return new NmSalesProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(NmSalesProjectionPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NmSalesProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NmSalesProjectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("productGrowth", getProductGrowth());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("accountGrowth", getAccountGrowth());
        attributes.put("projectionUnits", getProjectionUnits());
        attributes.put("projectionSales", getProjectionSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double productGrowth = (Double) attributes.get("productGrowth");

        if (productGrowth != null) {
            setProductGrowth(productGrowth);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double accountGrowth = (Double) attributes.get("accountGrowth");

        if (accountGrowth != null) {
            setAccountGrowth(accountGrowth);
        }

        Double projectionUnits = (Double) attributes.get("projectionUnits");

        if (projectionUnits != null) {
            setProjectionUnits(projectionUnits);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nmSalesProjectionRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProductGrowth() {
        return _productGrowth;
    }

    @Override
    public void setProductGrowth(double productGrowth) {
        _productGrowth = productGrowth;

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProductGrowth", double.class);

                method.invoke(_nmSalesProjectionRemoteModel, productGrowth);
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

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmSalesProjectionRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAccountGrowth() {
        return _accountGrowth;
    }

    @Override
    public void setAccountGrowth(double accountGrowth) {
        _accountGrowth = accountGrowth;

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountGrowth", double.class);

                method.invoke(_nmSalesProjectionRemoteModel, accountGrowth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionUnits() {
        return _projectionUnits;
    }

    @Override
    public void setProjectionUnits(double projectionUnits) {
        _projectionUnits = projectionUnits;

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionUnits",
                        double.class);

                method.invoke(_nmSalesProjectionRemoteModel, projectionUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionSales() {
        return _projectionSales;
    }

    @Override
    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;

        if (_nmSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_nmSalesProjectionRemoteModel, projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmSalesProjectionRemoteModel() {
        return _nmSalesProjectionRemoteModel;
    }

    public void setNmSalesProjectionRemoteModel(
        BaseModel<?> nmSalesProjectionRemoteModel) {
        _nmSalesProjectionRemoteModel = nmSalesProjectionRemoteModel;
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

        Class<?> remoteModelClass = _nmSalesProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmSalesProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmSalesProjectionLocalServiceUtil.addNmSalesProjection(this);
        } else {
            NmSalesProjectionLocalServiceUtil.updateNmSalesProjection(this);
        }
    }

    @Override
    public NmSalesProjection toEscapedModel() {
        return (NmSalesProjection) ProxyUtil.newProxyInstance(NmSalesProjection.class.getClassLoader(),
            new Class[] { NmSalesProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmSalesProjectionClp clone = new NmSalesProjectionClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setProductGrowth(getProductGrowth());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setAccountGrowth(getAccountGrowth());
        clone.setProjectionUnits(getProjectionUnits());
        clone.setProjectionSales(getProjectionSales());

        return clone;
    }

    @Override
    public int compareTo(NmSalesProjection nmSalesProjection) {
        NmSalesProjectionPK primaryKey = nmSalesProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmSalesProjectionClp)) {
            return false;
        }

        NmSalesProjectionClp nmSalesProjection = (NmSalesProjectionClp) obj;

        NmSalesProjectionPK primaryKey = nmSalesProjection.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", productGrowth=");
        sb.append(getProductGrowth());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", accountGrowth=");
        sb.append(getAccountGrowth());
        sb.append(", projectionUnits=");
        sb.append(getProjectionUnits());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmSalesProjection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productGrowth</column-name><column-value><![CDATA[");
        sb.append(getProductGrowth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountGrowth</column-name><column-value><![CDATA[");
        sb.append(getAccountGrowth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionUnits</column-name><column-value><![CDATA[");
        sb.append(getProjectionUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
