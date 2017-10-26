package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;

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


public class MSalesProjectionMasterClp extends BaseModelImpl<MSalesProjectionMaster>
    implements MSalesProjectionMaster {
    private String _methodology;
    private String _calculationPeriods;
    private String _calculationBased;
    private int _projectionDetailsSid;
    private boolean _checkRecord;
    private BaseModel<?> _mSalesProjectionMasterRemoteModel;

    public MSalesProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return MSalesProjectionMaster.class.getName();
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
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("calculationBased", getCalculationBased());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String calculationPeriods = (String) attributes.get(
                "calculationPeriods");

        if (calculationPeriods != null) {
            setCalculationPeriods(calculationPeriods);
        }

        String calculationBased = (String) attributes.get("calculationBased");

        if (calculationBased != null) {
            setCalculationBased(calculationBased);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getMethodology() {
        return _methodology;
    }

    @Override
    public void setMethodology(String methodology) {
        _methodology = methodology;

        if (_mSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_mSalesProjectionMasterRemoteModel, methodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationPeriods() {
        return _calculationPeriods;
    }

    @Override
    public void setCalculationPeriods(String calculationPeriods) {
        _calculationPeriods = calculationPeriods;

        if (_mSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationPeriods",
                        String.class);

                method.invoke(_mSalesProjectionMasterRemoteModel,
                    calculationPeriods);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationBased() {
        return _calculationBased;
    }

    @Override
    public void setCalculationBased(String calculationBased) {
        _calculationBased = calculationBased;

        if (_mSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationBased",
                        String.class);

                method.invoke(_mSalesProjectionMasterRemoteModel,
                    calculationBased);
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

        if (_mSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mSalesProjectionMasterRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
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

        if (_mSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_mSalesProjectionMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMSalesProjectionMasterRemoteModel() {
        return _mSalesProjectionMasterRemoteModel;
    }

    public void setMSalesProjectionMasterRemoteModel(
        BaseModel<?> mSalesProjectionMasterRemoteModel) {
        _mSalesProjectionMasterRemoteModel = mSalesProjectionMasterRemoteModel;
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

        Class<?> remoteModelClass = _mSalesProjectionMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mSalesProjectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSalesProjectionMasterLocalServiceUtil.addMSalesProjectionMaster(this);
        } else {
            MSalesProjectionMasterLocalServiceUtil.updateMSalesProjectionMaster(this);
        }
    }

    @Override
    public MSalesProjectionMaster toEscapedModel() {
        return (MSalesProjectionMaster) ProxyUtil.newProxyInstance(MSalesProjectionMaster.class.getClassLoader(),
            new Class[] { MSalesProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MSalesProjectionMasterClp clone = new MSalesProjectionMasterClp();

        clone.setMethodology(getMethodology());
        clone.setCalculationPeriods(getCalculationPeriods());
        clone.setCalculationBased(getCalculationBased());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(MSalesProjectionMaster mSalesProjectionMaster) {
        int primaryKey = mSalesProjectionMaster.getPrimaryKey();

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

        if (!(obj instanceof MSalesProjectionMasterClp)) {
            return false;
        }

        MSalesProjectionMasterClp mSalesProjectionMaster = (MSalesProjectionMasterClp) obj;

        int primaryKey = mSalesProjectionMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{methodology=");
        sb.append(getMethodology());
        sb.append(", calculationPeriods=");
        sb.append(getCalculationPeriods());
        sb.append(", calculationBased=");
        sb.append(getCalculationBased());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MSalesProjectionMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationPeriods</column-name><column-value><![CDATA[");
        sb.append(getCalculationPeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationBased</column-name><column-value><![CDATA[");
        sb.append(getCalculationBased());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
