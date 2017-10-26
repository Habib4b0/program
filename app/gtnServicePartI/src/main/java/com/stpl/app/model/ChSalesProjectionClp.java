package com.stpl.app.model;

import com.stpl.app.service.ChSalesProjectionLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.persistence.ChSalesProjectionPK;

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


public class ChSalesProjectionClp extends BaseModelImpl<ChSalesProjection>
    implements ChSalesProjection {
    private double _contractUnits;
    private double _perOfBusiness;
    private int _periodSid;
    private double _contractSales;
    private int _projectionDetailsSid;
    private double _gtsSales;
    private BaseModel<?> _chSalesProjectionRemoteModel;

    public ChSalesProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChSalesProjection.class;
    }

    @Override
    public String getModelClassName() {
        return ChSalesProjection.class.getName();
    }

    @Override
    public ChSalesProjectionPK getPrimaryKey() {
        return new ChSalesProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(ChSalesProjectionPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ChSalesProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ChSalesProjectionPK) primaryKeyObj);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setContractUnits", double.class);

                method.invoke(_chSalesProjectionRemoteModel, contractUnits);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPerOfBusiness", double.class);

                method.invoke(_chSalesProjectionRemoteModel, perOfBusiness);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_chSalesProjectionRemoteModel, periodSid);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setContractSales", double.class);

                method.invoke(_chSalesProjectionRemoteModel, contractSales);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_chSalesProjectionRemoteModel,
                    projectionDetailsSid);
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

        if (_chSalesProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _chSalesProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setGtsSales", double.class);

                method.invoke(_chSalesProjectionRemoteModel, gtsSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChSalesProjectionRemoteModel() {
        return _chSalesProjectionRemoteModel;
    }

    public void setChSalesProjectionRemoteModel(
        BaseModel<?> chSalesProjectionRemoteModel) {
        _chSalesProjectionRemoteModel = chSalesProjectionRemoteModel;
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

        Class<?> remoteModelClass = _chSalesProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chSalesProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChSalesProjectionLocalServiceUtil.addChSalesProjection(this);
        } else {
            ChSalesProjectionLocalServiceUtil.updateChSalesProjection(this);
        }
    }

    @Override
    public ChSalesProjection toEscapedModel() {
        return (ChSalesProjection) ProxyUtil.newProxyInstance(ChSalesProjection.class.getClassLoader(),
            new Class[] { ChSalesProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChSalesProjectionClp clone = new ChSalesProjectionClp();

        clone.setContractUnits(getContractUnits());
        clone.setPerOfBusiness(getPerOfBusiness());
        clone.setPeriodSid(getPeriodSid());
        clone.setContractSales(getContractSales());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setGtsSales(getGtsSales());

        return clone;
    }

    @Override
    public int compareTo(ChSalesProjection chSalesProjection) {
        ChSalesProjectionPK primaryKey = chSalesProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChSalesProjectionClp)) {
            return false;
        }

        ChSalesProjectionClp chSalesProjection = (ChSalesProjectionClp) obj;

        ChSalesProjectionPK primaryKey = chSalesProjection.getPrimaryKey();

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
        sb.append("com.stpl.app.model.ChSalesProjection");
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
