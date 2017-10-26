package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MedicaidUraProjLocalServiceUtil;
import com.stpl.app.service.persistence.MedicaidUraProjPK;

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


public class MedicaidUraProjClp extends BaseModelImpl<MedicaidUraProj>
    implements MedicaidUraProj {
    private double _adjustment;
    private int _periodSid;
    private String _priceType;
    private double _projectionPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private BaseModel<?> _medicaidUraProjRemoteModel;

    public MedicaidUraProjClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidUraProj.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidUraProj.class.getName();
    }

    @Override
    public MedicaidUraProjPK getPrimaryKey() {
        return new MedicaidUraProjPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(MedicaidUraProjPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new MedicaidUraProjPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((MedicaidUraProjPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustment", getAdjustment());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("projectionPrice", getProjectionPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double adjustment = (Double) attributes.get("adjustment");

        if (adjustment != null) {
            setAdjustment(adjustment);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double projectionPrice = (Double) attributes.get("projectionPrice");

        if (projectionPrice != null) {
            setProjectionPrice(projectionPrice);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
    }

    @Override
    public double getAdjustment() {
        return _adjustment;
    }

    @Override
    public void setAdjustment(double adjustment) {
        _adjustment = adjustment;

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustment", double.class);

                method.invoke(_medicaidUraProjRemoteModel, adjustment);
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

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_medicaidUraProjRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceType() {
        return _priceType;
    }

    @Override
    public void setPriceType(String priceType) {
        _priceType = priceType;

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_medicaidUraProjRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionPrice() {
        return _projectionPrice;
    }

    @Override
    public void setProjectionPrice(double projectionPrice) {
        _projectionPrice = projectionPrice;

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPrice",
                        double.class);

                method.invoke(_medicaidUraProjRemoteModel, projectionPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_medicaidUraProjRemoteModel, notes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjDetailsSid() {
        return _naProjDetailsSid;
    }

    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetailsSid = naProjDetailsSid;

        if (_medicaidUraProjRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraProjRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_medicaidUraProjRemoteModel, naProjDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMedicaidUraProjRemoteModel() {
        return _medicaidUraProjRemoteModel;
    }

    public void setMedicaidUraProjRemoteModel(
        BaseModel<?> medicaidUraProjRemoteModel) {
        _medicaidUraProjRemoteModel = medicaidUraProjRemoteModel;
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

        Class<?> remoteModelClass = _medicaidUraProjRemoteModel.getClass();

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

        Object returnValue = method.invoke(_medicaidUraProjRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MedicaidUraProjLocalServiceUtil.addMedicaidUraProj(this);
        } else {
            MedicaidUraProjLocalServiceUtil.updateMedicaidUraProj(this);
        }
    }

    @Override
    public MedicaidUraProj toEscapedModel() {
        return (MedicaidUraProj) ProxyUtil.newProxyInstance(MedicaidUraProj.class.getClassLoader(),
            new Class[] { MedicaidUraProj.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MedicaidUraProjClp clone = new MedicaidUraProjClp();

        clone.setAdjustment(getAdjustment());
        clone.setPeriodSid(getPeriodSid());
        clone.setPriceType(getPriceType());
        clone.setProjectionPrice(getProjectionPrice());
        clone.setNotes(getNotes());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(MedicaidUraProj medicaidUraProj) {
        MedicaidUraProjPK primaryKey = medicaidUraProj.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidUraProjClp)) {
            return false;
        }

        MedicaidUraProjClp medicaidUraProj = (MedicaidUraProjClp) obj;

        MedicaidUraProjPK primaryKey = medicaidUraProj.getPrimaryKey();

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

        sb.append("{adjustment=");
        sb.append(getAdjustment());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", projectionPrice=");
        sb.append(getProjectionPrice());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MedicaidUraProj");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustment</column-name><column-value><![CDATA[");
        sb.append(getAdjustment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionPrice</column-name><column-value><![CDATA[");
        sb.append(getProjectionPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
