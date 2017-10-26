package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPK;

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


public class DeductionCalendarDetailsClp extends BaseModelImpl<DeductionCalendarDetails>
    implements DeductionCalendarDetails {
    private int _deductionCalendarMasterSid;
    private String _adjustmentBasis;
    private int _periodSid;
    private String _adjustmentValue;
    private String _adjustmentAllocationMethodology;
    private int _companyMasterSid;
    private int _discountAmount;
    private String _adjustmentVariable;
    private int _itemMasterSid;
    private String _adjustmentType;
    private boolean _checkRecord;
    private BaseModel<?> _deductionCalendarDetailsRemoteModel;

    public DeductionCalendarDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionCalendarDetails.class.getName();
    }

    @Override
    public DeductionCalendarDetailsPK getPrimaryKey() {
        return new DeductionCalendarDetailsPK(_deductionCalendarMasterSid,
            _periodSid, _companyMasterSid, _itemMasterSid);
    }

    @Override
    public void setPrimaryKey(DeductionCalendarDetailsPK primaryKey) {
        setDeductionCalendarMasterSid(primaryKey.deductionCalendarMasterSid);
        setPeriodSid(primaryKey.periodSid);
        setCompanyMasterSid(primaryKey.companyMasterSid);
        setItemMasterSid(primaryKey.itemMasterSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new DeductionCalendarDetailsPK(_deductionCalendarMasterSid,
            _periodSid, _companyMasterSid, _itemMasterSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((DeductionCalendarDetailsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentAllocationMethodology",
            getAdjustmentAllocationMethodology());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentValue = (String) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentAllocationMethodology = (String) attributes.get(
                "adjustmentAllocationMethodology");

        if (adjustmentAllocationMethodology != null) {
            setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer discountAmount = (Integer) attributes.get("discountAmount");

        if (discountAmount != null) {
            setDiscountAmount(discountAmount);
        }

        String adjustmentVariable = (String) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        int.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    deductionCalendarMasterSid);
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

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentBasis",
                        String.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    adjustmentBasis);
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

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_deductionCalendarDetailsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentValue() {
        return _adjustmentValue;
    }

    @Override
    public void setAdjustmentValue(String adjustmentValue) {
        _adjustmentValue = adjustmentValue;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentValue",
                        String.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    adjustmentValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentAllocationMethodology() {
        return _adjustmentAllocationMethodology;
    }

    @Override
    public void setAdjustmentAllocationMethodology(
        String adjustmentAllocationMethodology) {
        _adjustmentAllocationMethodology = adjustmentAllocationMethodology;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentAllocationMethodology",
                        String.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    adjustmentAllocationMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDiscountAmount() {
        return _discountAmount;
    }

    @Override
    public void setDiscountAmount(int discountAmount) {
        _discountAmount = discountAmount;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountAmount", int.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    discountAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentVariable() {
        return _adjustmentVariable;
    }

    @Override
    public void setAdjustmentVariable(String adjustmentVariable) {
        _adjustmentVariable = adjustmentVariable;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentVariable",
                        String.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    adjustmentVariable);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    itemMasterSid);
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

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_deductionCalendarDetailsRemoteModel,
                    adjustmentType);
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

        if (_deductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_deductionCalendarDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDeductionCalendarDetailsRemoteModel() {
        return _deductionCalendarDetailsRemoteModel;
    }

    public void setDeductionCalendarDetailsRemoteModel(
        BaseModel<?> deductionCalendarDetailsRemoteModel) {
        _deductionCalendarDetailsRemoteModel = deductionCalendarDetailsRemoteModel;
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

        Class<?> remoteModelClass = _deductionCalendarDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_deductionCalendarDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionCalendarDetailsLocalServiceUtil.addDeductionCalendarDetails(this);
        } else {
            DeductionCalendarDetailsLocalServiceUtil.updateDeductionCalendarDetails(this);
        }
    }

    @Override
    public DeductionCalendarDetails toEscapedModel() {
        return (DeductionCalendarDetails) ProxyUtil.newProxyInstance(DeductionCalendarDetails.class.getClassLoader(),
            new Class[] { DeductionCalendarDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DeductionCalendarDetailsClp clone = new DeductionCalendarDetailsClp();

        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setAdjustmentBasis(getAdjustmentBasis());
        clone.setPeriodSid(getPeriodSid());
        clone.setAdjustmentValue(getAdjustmentValue());
        clone.setAdjustmentAllocationMethodology(getAdjustmentAllocationMethodology());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setDiscountAmount(getDiscountAmount());
        clone.setAdjustmentVariable(getAdjustmentVariable());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(DeductionCalendarDetails deductionCalendarDetails) {
        DeductionCalendarDetailsPK primaryKey = deductionCalendarDetails.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionCalendarDetailsClp)) {
            return false;
        }

        DeductionCalendarDetailsClp deductionCalendarDetails = (DeductionCalendarDetailsClp) obj;

        DeductionCalendarDetailsPK primaryKey = deductionCalendarDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", adjustmentBasis=");
        sb.append(getAdjustmentBasis());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", adjustmentValue=");
        sb.append(getAdjustmentValue());
        sb.append(", adjustmentAllocationMethodology=");
        sb.append(getAdjustmentAllocationMethodology());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", discountAmount=");
        sb.append(getDiscountAmount());
        sb.append(", adjustmentVariable=");
        sb.append(getAdjustmentVariable());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionCalendarDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
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
            "<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentAllocationMethodology</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentAllocationMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountAmount</column-name><column-value><![CDATA[");
        sb.append(getDiscountAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentVariable</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentVariable());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
