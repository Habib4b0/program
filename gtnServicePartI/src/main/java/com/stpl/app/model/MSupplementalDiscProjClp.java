package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MSupplementalDiscProjLocalServiceUtil;

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


public class MSupplementalDiscProjClp extends BaseModelImpl<MSupplementalDiscProj>
    implements MSupplementalDiscProj {
    private String _methodology;
    private double _projectionRate;
    private boolean _parity;
    private int _periodSid;
    private double _discountRate1;
    private String _parityReference;
    private int _projectionDetailsSid;
    private double _discountRate2;
    private double _parityDiscount;
    private double _projectionSales;
    private double _contractPrice;
    private String _access;
    private BaseModel<?> _mSupplementalDiscProjRemoteModel;

    public MSupplementalDiscProjClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MSupplementalDiscProj.class;
    }

    @Override
    public String getModelClassName() {
        return MSupplementalDiscProj.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("methodology", getMethodology());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("parity", getParity());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("discountRate1", getDiscountRate1());
        attributes.put("parityReference", getParityReference());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("discountRate2", getDiscountRate2());
        attributes.put("parityDiscount", getParityDiscount());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("access", getAccess());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Boolean parity = (Boolean) attributes.get("parity");

        if (parity != null) {
            setParity(parity);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double discountRate1 = (Double) attributes.get("discountRate1");

        if (discountRate1 != null) {
            setDiscountRate1(discountRate1);
        }

        String parityReference = (String) attributes.get("parityReference");

        if (parityReference != null) {
            setParityReference(parityReference);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double discountRate2 = (Double) attributes.get("discountRate2");

        if (discountRate2 != null) {
            setDiscountRate2(discountRate2);
        }

        Double parityDiscount = (Double) attributes.get("parityDiscount");

        if (parityDiscount != null) {
            setParityDiscount(parityDiscount);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        String access = (String) attributes.get("access");

        if (access != null) {
            setAccess(access);
        }
    }

    @Override
    public String getMethodology() {
        return _methodology;
    }

    @Override
    public void setMethodology(String methodology) {
        _methodology = methodology;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, methodology);
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

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, projectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getParity() {
        return _parity;
    }

    @Override
    public boolean isParity() {
        return _parity;
    }

    @Override
    public void setParity(boolean parity) {
        _parity = parity;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParity", boolean.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, parity);
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

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscountRate1() {
        return _discountRate1;
    }

    @Override
    public void setDiscountRate1(double discountRate1) {
        _discountRate1 = discountRate1;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate1", double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, discountRate1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParityReference() {
        return _parityReference;
    }

    @Override
    public void setParityReference(String parityReference) {
        _parityReference = parityReference;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParityReference",
                        String.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, parityReference);
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

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mSupplementalDiscProjRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscountRate2() {
        return _discountRate2;
    }

    @Override
    public void setDiscountRate2(double discountRate2) {
        _discountRate2 = discountRate2;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate2", double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, discountRate2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getParityDiscount() {
        return _parityDiscount;
    }

    @Override
    public void setParityDiscount(double parityDiscount) {
        _parityDiscount = parityDiscount;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParityDiscount",
                        double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, parityDiscount);
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

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice() {
        return _contractPrice;
    }

    @Override
    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, contractPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccess() {
        return _access;
    }

    @Override
    public void setAccess(String access) {
        _access = access;

        if (_mSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setAccess", String.class);

                method.invoke(_mSupplementalDiscProjRemoteModel, access);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMSupplementalDiscProjRemoteModel() {
        return _mSupplementalDiscProjRemoteModel;
    }

    public void setMSupplementalDiscProjRemoteModel(
        BaseModel<?> mSupplementalDiscProjRemoteModel) {
        _mSupplementalDiscProjRemoteModel = mSupplementalDiscProjRemoteModel;
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

        Class<?> remoteModelClass = _mSupplementalDiscProjRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mSupplementalDiscProjRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSupplementalDiscProjLocalServiceUtil.addMSupplementalDiscProj(this);
        } else {
            MSupplementalDiscProjLocalServiceUtil.updateMSupplementalDiscProj(this);
        }
    }

    @Override
    public MSupplementalDiscProj toEscapedModel() {
        return (MSupplementalDiscProj) ProxyUtil.newProxyInstance(MSupplementalDiscProj.class.getClassLoader(),
            new Class[] { MSupplementalDiscProj.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MSupplementalDiscProjClp clone = new MSupplementalDiscProjClp();

        clone.setMethodology(getMethodology());
        clone.setProjectionRate(getProjectionRate());
        clone.setParity(getParity());
        clone.setPeriodSid(getPeriodSid());
        clone.setDiscountRate1(getDiscountRate1());
        clone.setParityReference(getParityReference());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setDiscountRate2(getDiscountRate2());
        clone.setParityDiscount(getParityDiscount());
        clone.setProjectionSales(getProjectionSales());
        clone.setContractPrice(getContractPrice());
        clone.setAccess(getAccess());

        return clone;
    }

    @Override
    public int compareTo(MSupplementalDiscProj mSupplementalDiscProj) {
        int primaryKey = mSupplementalDiscProj.getPrimaryKey();

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

        if (!(obj instanceof MSupplementalDiscProjClp)) {
            return false;
        }

        MSupplementalDiscProjClp mSupplementalDiscProj = (MSupplementalDiscProjClp) obj;

        int primaryKey = mSupplementalDiscProj.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{methodology=");
        sb.append(getMethodology());
        sb.append(", projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", parity=");
        sb.append(getParity());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", discountRate1=");
        sb.append(getDiscountRate1());
        sb.append(", parityReference=");
        sb.append(getParityReference());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", discountRate2=");
        sb.append(getDiscountRate2());
        sb.append(", parityDiscount=");
        sb.append(getParityDiscount());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", access=");
        sb.append(getAccess());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MSupplementalDiscProj");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parity</column-name><column-value><![CDATA[");
        sb.append(getParity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate1</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parityReference</column-name><column-value><![CDATA[");
        sb.append(getParityReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate2</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parityDiscount</column-name><column-value><![CDATA[");
        sb.append(getParityDiscount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice</column-name><column-value><![CDATA[");
        sb.append(getContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>access</column-name><column-value><![CDATA[");
        sb.append(getAccess());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
