package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StMSupplementalDiscProjLocalServiceUtil;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StMSupplementalDiscProjClp extends BaseModelImpl<StMSupplementalDiscProj>
    implements StMSupplementalDiscProj {
    private double _projectionRate;
    private int _userId;
    private Date _lastModifiedDate;
    private String _parityReference;
    private double _projectionSales;
    private double _contractPrice;
    private String _methodology;
    private boolean _parity;
    private int _periodSid;
    private double _discountRate1;
    private int _projectionDetailsSid;
    private double _discountRate2;
    private double _parityDiscount;
    private int _sessionId;
    private String _access;
    private BaseModel<?> _stMSupplementalDiscProjRemoteModel;

    public StMSupplementalDiscProjClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StMSupplementalDiscProj.class;
    }

    @Override
    public String getModelClassName() {
        return StMSupplementalDiscProj.class.getName();
    }

    @Override
    public StMSupplementalDiscProjPK getPrimaryKey() {
        return new StMSupplementalDiscProjPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StMSupplementalDiscProjPK primaryKey) {
        setUserId(primaryKey.userId);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StMSupplementalDiscProjPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StMSupplementalDiscProjPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionRate", getProjectionRate());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parityReference", getParityReference());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("methodology", getMethodology());
        attributes.put("parity", getParity());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("discountRate1", getDiscountRate1());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("discountRate2", getDiscountRate2());
        attributes.put("parityDiscount", getParityDiscount());
        attributes.put("sessionId", getSessionId());
        attributes.put("access", getAccess());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String parityReference = (String) attributes.get("parityReference");

        if (parityReference != null) {
            setParityReference(parityReference);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String access = (String) attributes.get("access");

        if (access != null) {
            setAccess(access);
        }
    }

    @Override
    public double getProjectionRate() {
        return _projectionRate;
    }

    @Override
    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
                    projectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
                    lastModifiedDate);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParityReference",
                        String.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
                    parityReference);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
                    projectionSales);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, contractPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMethodology() {
        return _methodology;
    }

    @Override
    public void setMethodology(String methodology) {
        _methodology = methodology;

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, methodology);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParity", boolean.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, parity);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, periodSid);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate1", double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, discountRate1);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate2", double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, discountRate2);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setParityDiscount",
                        double.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel,
                    parityDiscount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        _sessionId = sessionId;

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, sessionId);
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

        if (_stMSupplementalDiscProjRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscProjRemoteModel.getClass();

                Method method = clazz.getMethod("setAccess", String.class);

                method.invoke(_stMSupplementalDiscProjRemoteModel, access);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStMSupplementalDiscProjRemoteModel() {
        return _stMSupplementalDiscProjRemoteModel;
    }

    public void setStMSupplementalDiscProjRemoteModel(
        BaseModel<?> stMSupplementalDiscProjRemoteModel) {
        _stMSupplementalDiscProjRemoteModel = stMSupplementalDiscProjRemoteModel;
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

        Class<?> remoteModelClass = _stMSupplementalDiscProjRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stMSupplementalDiscProjRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StMSupplementalDiscProjLocalServiceUtil.addStMSupplementalDiscProj(this);
        } else {
            StMSupplementalDiscProjLocalServiceUtil.updateStMSupplementalDiscProj(this);
        }
    }

    @Override
    public StMSupplementalDiscProj toEscapedModel() {
        return (StMSupplementalDiscProj) ProxyUtil.newProxyInstance(StMSupplementalDiscProj.class.getClassLoader(),
            new Class[] { StMSupplementalDiscProj.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StMSupplementalDiscProjClp clone = new StMSupplementalDiscProjClp();

        clone.setProjectionRate(getProjectionRate());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setParityReference(getParityReference());
        clone.setProjectionSales(getProjectionSales());
        clone.setContractPrice(getContractPrice());
        clone.setMethodology(getMethodology());
        clone.setParity(getParity());
        clone.setPeriodSid(getPeriodSid());
        clone.setDiscountRate1(getDiscountRate1());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setDiscountRate2(getDiscountRate2());
        clone.setParityDiscount(getParityDiscount());
        clone.setSessionId(getSessionId());
        clone.setAccess(getAccess());

        return clone;
    }

    @Override
    public int compareTo(StMSupplementalDiscProj stMSupplementalDiscProj) {
        StMSupplementalDiscProjPK primaryKey = stMSupplementalDiscProj.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMSupplementalDiscProjClp)) {
            return false;
        }

        StMSupplementalDiscProjClp stMSupplementalDiscProj = (StMSupplementalDiscProjClp) obj;

        StMSupplementalDiscProjPK primaryKey = stMSupplementalDiscProj.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", parityReference=");
        sb.append(getParityReference());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", parity=");
        sb.append(getParity());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", discountRate1=");
        sb.append(getDiscountRate1());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", discountRate2=");
        sb.append(getDiscountRate2());
        sb.append(", parityDiscount=");
        sb.append(getParityDiscount());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", access=");
        sb.append(getAccess());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StMSupplementalDiscProj");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parityReference</column-name><column-value><![CDATA[");
        sb.append(getParityReference());
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
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
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
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>access</column-name><column-value><![CDATA[");
        sb.append(getAccess());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
