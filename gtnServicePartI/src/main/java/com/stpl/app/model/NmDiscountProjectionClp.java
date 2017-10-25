package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmDiscountProjectionPK;

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


public class NmDiscountProjectionClp extends BaseModelImpl<NmDiscountProjection>
    implements NmDiscountProjection {
    private String _adjustmentMethodology;
    private String _adjustmentBasis;
    private int _periodSid;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private boolean _adjustmentVariable;
    private double _adjustmentValue;
    private String _adjustmentType;
    private double _projectionSales;
    private double _discountRate;
    private BaseModel<?> _nmDiscountProjectionRemoteModel;

    public NmDiscountProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmDiscountProjection.class;
    }

    @Override
    public String getModelClassName() {
        return NmDiscountProjection.class.getName();
    }

    @Override
    public NmDiscountProjectionPK getPrimaryKey() {
        return new NmDiscountProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(NmDiscountProjectionPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NmDiscountProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NmDiscountProjectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("discountRate", getDiscountRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String adjustmentMethodology = (String) attributes.get(
                "adjustmentMethodology");

        if (adjustmentMethodology != null) {
            setAdjustmentMethodology(adjustmentMethodology);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Boolean adjustmentVariable = (Boolean) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }
    }

    @Override
    public String getAdjustmentMethodology() {
        return _adjustmentMethodology;
    }

    @Override
    public void setAdjustmentMethodology(String adjustmentMethodology) {
        _adjustmentMethodology = adjustmentMethodology;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentMethodology",
                        String.class);

                method.invoke(_nmDiscountProjectionRemoteModel,
                    adjustmentMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentBasis() {
        return _adjustmentBasis;
    }

    @Override
    public void setAdjustmentBasis(String adjustmentBasis) {
        _adjustmentBasis = adjustmentBasis;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentBasis",
                        String.class);

                method.invoke(_nmDiscountProjectionRemoteModel, adjustmentBasis);
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

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nmDiscountProjectionRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionRate() {
        return _projectionRate;
    }

    @Override
    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_nmDiscountProjectionRemoteModel, projectionRate);
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

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmDiscountProjectionRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getAdjustmentVariable() {
        return _adjustmentVariable;
    }

    @Override
    public boolean isAdjustmentVariable() {
        return _adjustmentVariable;
    }

    @Override
    public void setAdjustmentVariable(boolean adjustmentVariable) {
        _adjustmentVariable = adjustmentVariable;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentVariable",
                        boolean.class);

                method.invoke(_nmDiscountProjectionRemoteModel,
                    adjustmentVariable);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAdjustmentValue() {
        return _adjustmentValue;
    }

    @Override
    public void setAdjustmentValue(double adjustmentValue) {
        _adjustmentValue = adjustmentValue;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentValue",
                        double.class);

                method.invoke(_nmDiscountProjectionRemoteModel, adjustmentValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_nmDiscountProjectionRemoteModel, adjustmentType);
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

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_nmDiscountProjectionRemoteModel, projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscountRate() {
        return _discountRate;
    }

    @Override
    public void setDiscountRate(double discountRate) {
        _discountRate = discountRate;

        if (_nmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate", double.class);

                method.invoke(_nmDiscountProjectionRemoteModel, discountRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmDiscountProjectionRemoteModel() {
        return _nmDiscountProjectionRemoteModel;
    }

    public void setNmDiscountProjectionRemoteModel(
        BaseModel<?> nmDiscountProjectionRemoteModel) {
        _nmDiscountProjectionRemoteModel = nmDiscountProjectionRemoteModel;
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

        Class<?> remoteModelClass = _nmDiscountProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmDiscountProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmDiscountProjectionLocalServiceUtil.addNmDiscountProjection(this);
        } else {
            NmDiscountProjectionLocalServiceUtil.updateNmDiscountProjection(this);
        }
    }

    @Override
    public NmDiscountProjection toEscapedModel() {
        return (NmDiscountProjection) ProxyUtil.newProxyInstance(NmDiscountProjection.class.getClassLoader(),
            new Class[] { NmDiscountProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmDiscountProjectionClp clone = new NmDiscountProjectionClp();

        clone.setAdjustmentMethodology(getAdjustmentMethodology());
        clone.setAdjustmentBasis(getAdjustmentBasis());
        clone.setPeriodSid(getPeriodSid());
        clone.setProjectionRate(getProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setAdjustmentVariable(getAdjustmentVariable());
        clone.setAdjustmentValue(getAdjustmentValue());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setProjectionSales(getProjectionSales());
        clone.setDiscountRate(getDiscountRate());

        return clone;
    }

    @Override
    public int compareTo(NmDiscountProjection nmDiscountProjection) {
        NmDiscountProjectionPK primaryKey = nmDiscountProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmDiscountProjectionClp)) {
            return false;
        }

        NmDiscountProjectionClp nmDiscountProjection = (NmDiscountProjectionClp) obj;

        NmDiscountProjectionPK primaryKey = nmDiscountProjection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{adjustmentMethodology=");
        sb.append(getAdjustmentMethodology());
        sb.append(", adjustmentBasis=");
        sb.append(getAdjustmentBasis());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", adjustmentVariable=");
        sb.append(getAdjustmentVariable());
        sb.append(", adjustmentValue=");
        sb.append(getAdjustmentValue());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", discountRate=");
        sb.append(getDiscountRate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmDiscountProjection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustmentMethodology</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentBasis</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentVariable</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentVariable());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
