package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;

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


public class NmPpaProjectionMasterClp extends BaseModelImpl<NmPpaProjectionMaster>
    implements NmPpaProjectionMaster {
    private boolean _checkRecord;
    private String _userGroup;
    private int _projectionDetailsSid;
    private String _priceBasis;
    private Date _priceProtectionEndDate;
    private Date _priceProtectionStartDate;
    private double _actualPriceCap;
    private BaseModel<?> _nmPpaProjectionMasterRemoteModel;

    public NmPpaProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmPpaProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmPpaProjectionMaster.class.getName();
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

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("actualPriceCap", getActualPriceCap());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Double actualPriceCap = (Double) attributes.get("actualPriceCap");

        if (actualPriceCap != null) {
            setActualPriceCap(actualPriceCap);
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserGroup() {
        return _userGroup;
    }

    @Override
    public void setUserGroup(String userGroup) {
        _userGroup = userGroup;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroup", String.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel, userGroup);
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

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceBasis() {
        return _priceBasis;
    }

    @Override
    public void setPriceBasis(String priceBasis) {
        _priceBasis = priceBasis;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceBasis", String.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel, priceBasis);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    @Override
    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel,
                    priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    @Override
    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel,
                    priceProtectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualPriceCap() {
        return _actualPriceCap;
    }

    @Override
    public void setActualPriceCap(double actualPriceCap) {
        _actualPriceCap = actualPriceCap;

        if (_nmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualPriceCap",
                        double.class);

                method.invoke(_nmPpaProjectionMasterRemoteModel, actualPriceCap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmPpaProjectionMasterRemoteModel() {
        return _nmPpaProjectionMasterRemoteModel;
    }

    public void setNmPpaProjectionMasterRemoteModel(
        BaseModel<?> nmPpaProjectionMasterRemoteModel) {
        _nmPpaProjectionMasterRemoteModel = nmPpaProjectionMasterRemoteModel;
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

        Class<?> remoteModelClass = _nmPpaProjectionMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmPpaProjectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmPpaProjectionMasterLocalServiceUtil.addNmPpaProjectionMaster(this);
        } else {
            NmPpaProjectionMasterLocalServiceUtil.updateNmPpaProjectionMaster(this);
        }
    }

    @Override
    public NmPpaProjectionMaster toEscapedModel() {
        return (NmPpaProjectionMaster) ProxyUtil.newProxyInstance(NmPpaProjectionMaster.class.getClassLoader(),
            new Class[] { NmPpaProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmPpaProjectionMasterClp clone = new NmPpaProjectionMasterClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setUserGroup(getUserGroup());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setPriceBasis(getPriceBasis());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setActualPriceCap(getActualPriceCap());

        return clone;
    }

    @Override
    public int compareTo(NmPpaProjectionMaster nmPpaProjectionMaster) {
        int primaryKey = nmPpaProjectionMaster.getPrimaryKey();

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

        if (!(obj instanceof NmPpaProjectionMasterClp)) {
            return false;
        }

        NmPpaProjectionMasterClp nmPpaProjectionMaster = (NmPpaProjectionMasterClp) obj;

        int primaryKey = nmPpaProjectionMaster.getPrimaryKey();

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

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", userGroup=");
        sb.append(getUserGroup());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", priceBasis=");
        sb.append(getPriceBasis());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", actualPriceCap=");
        sb.append(getActualPriceCap());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmPpaProjectionMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userGroup</column-name><column-value><![CDATA[");
        sb.append(getUserGroup());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceBasis</column-name><column-value><![CDATA[");
        sb.append(getPriceBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPriceCap</column-name><column-value><![CDATA[");
        sb.append(getActualPriceCap());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
