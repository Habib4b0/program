package com.stpl.app.model;

import com.stpl.app.service.ChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.persistence.ChDiscountProjMasterPK;

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


public class ChDiscountProjMasterClp extends BaseModelImpl<ChDiscountProjMaster>
    implements ChDiscountProjMaster {
    private String _selectedPeriods;
    private boolean _checkRecord;
    private String _priceGroupType;
    private int _projectionDetailsSid;
    private String _baselinePeriods;
    private String _netFlag;
    private String _methodology;
    private int _rsModelSid;
    private String _discountType;
    private String _projectedType;
    private BaseModel<?> _chDiscountProjMasterRemoteModel;

    public ChDiscountProjMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ChDiscountProjMaster.class.getName();
    }

    @Override
    public ChDiscountProjMasterPK getPrimaryKey() {
        return new ChDiscountProjMasterPK(_projectionDetailsSid, _rsModelSid);
    }

    @Override
    public void setPrimaryKey(ChDiscountProjMasterPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setRsModelSid(primaryKey.rsModelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ChDiscountProjMasterPK(_projectionDetailsSid, _rsModelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ChDiscountProjMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("baselinePeriods", getBaselinePeriods());
        attributes.put("netFlag", getNetFlag());
        attributes.put("methodology", getMethodology());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("discountType", getDiscountType());
        attributes.put("projectedType", getProjectedType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String discountType = (String) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
        }

        String projectedType = (String) attributes.get("projectedType");

        if (projectedType != null) {
            setProjectedType(projectedType);
        }
    }

    @Override
    public String getSelectedPeriods() {
        return _selectedPeriods;
    }

    @Override
    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelectedPeriods",
                        String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, selectedPeriods);
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

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_chDiscountProjMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceGroupType() {
        return _priceGroupType;
    }

    @Override
    public void setPriceGroupType(String priceGroupType) {
        _priceGroupType = priceGroupType;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceGroupType",
                        String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, priceGroupType);
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

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_chDiscountProjMasterRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaselinePeriods() {
        return _baselinePeriods;
    }

    @Override
    public void setBaselinePeriods(String baselinePeriods) {
        _baselinePeriods = baselinePeriods;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselinePeriods",
                        String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, baselinePeriods);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetFlag() {
        return _netFlag;
    }

    @Override
    public void setNetFlag(String netFlag) {
        _netFlag = netFlag;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetFlag", String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, netFlag);
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

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, methodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_chDiscountProjMasterRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDiscountType() {
        return _discountType;
    }

    @Override
    public void setDiscountType(String discountType) {
        _discountType = discountType;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountType", String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, discountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectedType() {
        return _projectedType;
    }

    @Override
    public void setProjectedType(String projectedType) {
        _projectedType = projectedType;

        if (_chDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _chDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectedType", String.class);

                method.invoke(_chDiscountProjMasterRemoteModel, projectedType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChDiscountProjMasterRemoteModel() {
        return _chDiscountProjMasterRemoteModel;
    }

    public void setChDiscountProjMasterRemoteModel(
        BaseModel<?> chDiscountProjMasterRemoteModel) {
        _chDiscountProjMasterRemoteModel = chDiscountProjMasterRemoteModel;
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

        Class<?> remoteModelClass = _chDiscountProjMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chDiscountProjMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChDiscountProjMasterLocalServiceUtil.addChDiscountProjMaster(this);
        } else {
            ChDiscountProjMasterLocalServiceUtil.updateChDiscountProjMaster(this);
        }
    }

    @Override
    public ChDiscountProjMaster toEscapedModel() {
        return (ChDiscountProjMaster) ProxyUtil.newProxyInstance(ChDiscountProjMaster.class.getClassLoader(),
            new Class[] { ChDiscountProjMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChDiscountProjMasterClp clone = new ChDiscountProjMasterClp();

        clone.setSelectedPeriods(getSelectedPeriods());
        clone.setCheckRecord(getCheckRecord());
        clone.setPriceGroupType(getPriceGroupType());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setBaselinePeriods(getBaselinePeriods());
        clone.setNetFlag(getNetFlag());
        clone.setMethodology(getMethodology());
        clone.setRsModelSid(getRsModelSid());
        clone.setDiscountType(getDiscountType());
        clone.setProjectedType(getProjectedType());

        return clone;
    }

    @Override
    public int compareTo(ChDiscountProjMaster chDiscountProjMaster) {
        ChDiscountProjMasterPK primaryKey = chDiscountProjMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChDiscountProjMasterClp)) {
            return false;
        }

        ChDiscountProjMasterClp chDiscountProjMaster = (ChDiscountProjMasterClp) obj;

        ChDiscountProjMasterPK primaryKey = chDiscountProjMaster.getPrimaryKey();

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

        sb.append("{selectedPeriods=");
        sb.append(getSelectedPeriods());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", priceGroupType=");
        sb.append(getPriceGroupType());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", baselinePeriods=");
        sb.append(getBaselinePeriods());
        sb.append(", netFlag=");
        sb.append(getNetFlag());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", discountType=");
        sb.append(getDiscountType());
        sb.append(", projectedType=");
        sb.append(getProjectedType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ChDiscountProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>selectedPeriods</column-name><column-value><![CDATA[");
        sb.append(getSelectedPeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceGroupType</column-name><column-value><![CDATA[");
        sb.append(getPriceGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselinePeriods</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netFlag</column-name><column-value><![CDATA[");
        sb.append(getNetFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountType</column-name><column-value><![CDATA[");
        sb.append(getDiscountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectedType</column-name><column-value><![CDATA[");
        sb.append(getProjectedType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
