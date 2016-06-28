package com.stpl.app.model;

import com.stpl.app.service.ChActualSalesLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.persistence.ChActualSalesPK;

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


public class ChActualSalesClp extends BaseModelImpl<ChActualSales>
    implements ChActualSales {
    private double _contractUnits;
    private double _perOfBusiness;
    private int _periodSid;
    private double _contractSales;
    private int _projectionDetailsSid;
    private double _gtsSales;
    private BaseModel<?> _chActualSalesRemoteModel;

    public ChActualSalesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChActualSales.class;
    }

    @Override
    public String getModelClassName() {
        return ChActualSales.class.getName();
    }

    @Override
    public ChActualSalesPK getPrimaryKey() {
        return new ChActualSalesPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(ChActualSalesPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ChActualSalesPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ChActualSalesPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractUnits", getContractUnits());
        attributes.put("perOfBusiness", getPerOfBusiness());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("contractSales", getContractSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("gtsSales", getGtsSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double contractUnits = (Double) attributes.get("contractUnits");

        if (contractUnits != null) {
            setContractUnits(contractUnits);
        }

        Double perOfBusiness = (Double) attributes.get("perOfBusiness");

        if (perOfBusiness != null) {
            setPerOfBusiness(perOfBusiness);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double contractSales = (Double) attributes.get("contractSales");

        if (contractSales != null) {
            setContractSales(contractSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double gtsSales = (Double) attributes.get("gtsSales");

        if (gtsSales != null) {
            setGtsSales(gtsSales);
        }
    }

    @Override
    public double getContractUnits() {
        return _contractUnits;
    }

    @Override
    public void setContractUnits(double contractUnits) {
        _contractUnits = contractUnits;

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setContractUnits", double.class);

                method.invoke(_chActualSalesRemoteModel, contractUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPerOfBusiness() {
        return _perOfBusiness;
    }

    @Override
    public void setPerOfBusiness(double perOfBusiness) {
        _perOfBusiness = perOfBusiness;

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setPerOfBusiness", double.class);

                method.invoke(_chActualSalesRemoteModel, perOfBusiness);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_chActualSalesRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractSales() {
        return _contractSales;
    }

    @Override
    public void setContractSales(double contractSales) {
        _contractSales = contractSales;

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setContractSales", double.class);

                method.invoke(_chActualSalesRemoteModel, contractSales);
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

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_chActualSalesRemoteModel, projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGtsSales() {
        return _gtsSales;
    }

    @Override
    public void setGtsSales(double gtsSales) {
        _gtsSales = gtsSales;

        if (_chActualSalesRemoteModel != null) {
            try {
                Class<?> clazz = _chActualSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setGtsSales", double.class);

                method.invoke(_chActualSalesRemoteModel, gtsSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChActualSalesRemoteModel() {
        return _chActualSalesRemoteModel;
    }

    public void setChActualSalesRemoteModel(
        BaseModel<?> chActualSalesRemoteModel) {
        _chActualSalesRemoteModel = chActualSalesRemoteModel;
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

        Class<?> remoteModelClass = _chActualSalesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chActualSalesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChActualSalesLocalServiceUtil.addChActualSales(this);
        } else {
            ChActualSalesLocalServiceUtil.updateChActualSales(this);
        }
    }

    @Override
    public ChActualSales toEscapedModel() {
        return (ChActualSales) ProxyUtil.newProxyInstance(ChActualSales.class.getClassLoader(),
            new Class[] { ChActualSales.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChActualSalesClp clone = new ChActualSalesClp();

        clone.setContractUnits(getContractUnits());
        clone.setPerOfBusiness(getPerOfBusiness());
        clone.setPeriodSid(getPeriodSid());
        clone.setContractSales(getContractSales());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setGtsSales(getGtsSales());

        return clone;
    }

    @Override
    public int compareTo(ChActualSales chActualSales) {
        ChActualSalesPK primaryKey = chActualSales.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChActualSalesClp)) {
            return false;
        }

        ChActualSalesClp chActualSales = (ChActualSalesClp) obj;

        ChActualSalesPK primaryKey = chActualSales.getPrimaryKey();

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

        sb.append("{contractUnits=");
        sb.append(getContractUnits());
        sb.append(", perOfBusiness=");
        sb.append(getPerOfBusiness());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", contractSales=");
        sb.append(getContractSales());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", gtsSales=");
        sb.append(getGtsSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ChActualSales");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>contractUnits</column-name><column-value><![CDATA[");
        sb.append(getContractUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>perOfBusiness</column-name><column-value><![CDATA[");
        sb.append(getPerOfBusiness());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractSales</column-name><column-value><![CDATA[");
        sb.append(getContractSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gtsSales</column-name><column-value><![CDATA[");
        sb.append(getGtsSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
