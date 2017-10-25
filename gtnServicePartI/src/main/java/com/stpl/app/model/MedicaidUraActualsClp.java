package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MedicaidUraActualsLocalServiceUtil;
import com.stpl.app.service.persistence.MedicaidUraActualsPK;

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


public class MedicaidUraActualsClp extends BaseModelImpl<MedicaidUraActuals>
    implements MedicaidUraActuals {
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private double _baseYear;
    private BaseModel<?> _medicaidUraActualsRemoteModel;

    public MedicaidUraActualsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidUraActuals.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidUraActuals.class.getName();
    }

    @Override
    public MedicaidUraActualsPK getPrimaryKey() {
        return new MedicaidUraActualsPK(_periodSid, _priceType,
            _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(MedicaidUraActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new MedicaidUraActualsPK(_periodSid, _priceType,
            _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((MedicaidUraActualsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());
        attributes.put("baseYear", getBaseYear());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }

        Double baseYear = (Double) attributes.get("baseYear");

        if (baseYear != null) {
            setBaseYear(baseYear);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_medicaidUraActualsRemoteModel, periodSid);
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

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_medicaidUraActualsRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualPrice() {
        return _actualPrice;
    }

    @Override
    public void setActualPrice(double actualPrice) {
        _actualPrice = actualPrice;

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualPrice", double.class);

                method.invoke(_medicaidUraActualsRemoteModel, actualPrice);
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

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_medicaidUraActualsRemoteModel, notes);
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

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_medicaidUraActualsRemoteModel, naProjDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBaseYear() {
        return _baseYear;
    }

    @Override
    public void setBaseYear(double baseYear) {
        _baseYear = baseYear;

        if (_medicaidUraActualsRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidUraActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYear", double.class);

                method.invoke(_medicaidUraActualsRemoteModel, baseYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMedicaidUraActualsRemoteModel() {
        return _medicaidUraActualsRemoteModel;
    }

    public void setMedicaidUraActualsRemoteModel(
        BaseModel<?> medicaidUraActualsRemoteModel) {
        _medicaidUraActualsRemoteModel = medicaidUraActualsRemoteModel;
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

        Class<?> remoteModelClass = _medicaidUraActualsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_medicaidUraActualsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MedicaidUraActualsLocalServiceUtil.addMedicaidUraActuals(this);
        } else {
            MedicaidUraActualsLocalServiceUtil.updateMedicaidUraActuals(this);
        }
    }

    @Override
    public MedicaidUraActuals toEscapedModel() {
        return (MedicaidUraActuals) ProxyUtil.newProxyInstance(MedicaidUraActuals.class.getClassLoader(),
            new Class[] { MedicaidUraActuals.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MedicaidUraActualsClp clone = new MedicaidUraActualsClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setPriceType(getPriceType());
        clone.setActualPrice(getActualPrice());
        clone.setNotes(getNotes());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());
        clone.setBaseYear(getBaseYear());

        return clone;
    }

    @Override
    public int compareTo(MedicaidUraActuals medicaidUraActuals) {
        MedicaidUraActualsPK primaryKey = medicaidUraActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidUraActualsClp)) {
            return false;
        }

        MedicaidUraActualsClp medicaidUraActuals = (MedicaidUraActualsClp) obj;

        MedicaidUraActualsPK primaryKey = medicaidUraActuals.getPrimaryKey();

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
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", actualPrice=");
        sb.append(getActualPrice());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append(", baseYear=");
        sb.append(getBaseYear());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MedicaidUraActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPrice</column-name><column-value><![CDATA[");
        sb.append(getActualPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseYear</column-name><column-value><![CDATA[");
        sb.append(getBaseYear());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
