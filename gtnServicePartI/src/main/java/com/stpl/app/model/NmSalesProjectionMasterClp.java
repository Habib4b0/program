package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;

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


public class NmSalesProjectionMasterClp extends BaseModelImpl<NmSalesProjectionMaster>
    implements NmSalesProjectionMaster {
    private boolean _checkRecord;
    private String _calculationPeriods;
    private String _userGroup;
    private int _projectionDetailsSid;
    private String _methodology;
    private String _calculationBased;
    private BaseModel<?> _nmSalesProjectionMasterRemoteModel;

    public NmSalesProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmSalesProjectionMaster.class.getName();
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
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("methodology", getMethodology());
        attributes.put("calculationBased", getCalculationBased());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String calculationPeriods = (String) attributes.get(
                "calculationPeriods");

        if (calculationPeriods != null) {
            setCalculationPeriods(calculationPeriods);
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

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String calculationBased = (String) attributes.get("calculationBased");

        if (calculationBased != null) {
            setCalculationBased(calculationBased);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel, checkRecord);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationPeriods",
                        String.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel,
                    calculationPeriods);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroup", String.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel, userGroup);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel,
                    projectionDetailsSid);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel, methodology);
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

        if (_nmSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationBased",
                        String.class);

                method.invoke(_nmSalesProjectionMasterRemoteModel,
                    calculationBased);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmSalesProjectionMasterRemoteModel() {
        return _nmSalesProjectionMasterRemoteModel;
    }

    public void setNmSalesProjectionMasterRemoteModel(
        BaseModel<?> nmSalesProjectionMasterRemoteModel) {
        _nmSalesProjectionMasterRemoteModel = nmSalesProjectionMasterRemoteModel;
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

        Class<?> remoteModelClass = _nmSalesProjectionMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmSalesProjectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmSalesProjectionMasterLocalServiceUtil.addNmSalesProjectionMaster(this);
        } else {
            NmSalesProjectionMasterLocalServiceUtil.updateNmSalesProjectionMaster(this);
        }
    }

    @Override
    public NmSalesProjectionMaster toEscapedModel() {
        return (NmSalesProjectionMaster) ProxyUtil.newProxyInstance(NmSalesProjectionMaster.class.getClassLoader(),
            new Class[] { NmSalesProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmSalesProjectionMasterClp clone = new NmSalesProjectionMasterClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setCalculationPeriods(getCalculationPeriods());
        clone.setUserGroup(getUserGroup());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setMethodology(getMethodology());
        clone.setCalculationBased(getCalculationBased());

        return clone;
    }

    @Override
    public int compareTo(NmSalesProjectionMaster nmSalesProjectionMaster) {
        int primaryKey = nmSalesProjectionMaster.getPrimaryKey();

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

        if (!(obj instanceof NmSalesProjectionMasterClp)) {
            return false;
        }

        NmSalesProjectionMasterClp nmSalesProjectionMaster = (NmSalesProjectionMasterClp) obj;

        int primaryKey = nmSalesProjectionMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", calculationPeriods=");
        sb.append(getCalculationPeriods());
        sb.append(", userGroup=");
        sb.append(getUserGroup());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", calculationBased=");
        sb.append(getCalculationBased());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmSalesProjectionMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationPeriods</column-name><column-value><![CDATA[");
        sb.append(getCalculationPeriods());
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
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationBased</column-name><column-value><![CDATA[");
        sb.append(getCalculationBased());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
