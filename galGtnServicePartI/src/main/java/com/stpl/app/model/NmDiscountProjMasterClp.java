package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;

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


public class NmDiscountProjMasterClp extends BaseModelImpl<NmDiscountProjMaster>
    implements NmDiscountProjMaster {
    private boolean _checkRecord;
    private String _discountId;
    private String _userGroup;
    private String _priceGroupType;
    private int _projectionDetailsSid;
    private String _netFlag;
    private String _methodology;
    private String _discountName;
    private BaseModel<?> _nmDiscountProjMasterRemoteModel;

    public NmDiscountProjMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmDiscountProjMaster.class.getName();
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
        attributes.put("discountId", getDiscountId());
        attributes.put("userGroup", getUserGroup());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netFlag", getNetFlag());
        attributes.put("methodology", getMethodology());
        attributes.put("discountName", getDiscountName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String discountId = (String) attributes.get("discountId");

        if (discountId != null) {
            setDiscountId(discountId);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
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

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String discountName = (String) attributes.get("discountName");

        if (discountName != null) {
            setDiscountName(discountName);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDiscountId() {
        return _discountId;
    }

    @Override
    public void setDiscountId(String discountId) {
        _discountId = discountId;

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountId", String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, discountId);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroup", String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, userGroup);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceGroupType",
                        String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, priceGroupType);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmDiscountProjMasterRemoteModel,
                    projectionDetailsSid);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetFlag", String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, netFlag);
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

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, methodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDiscountName() {
        return _discountName;
    }

    @Override
    public void setDiscountName(String discountName) {
        _discountName = discountName;

        if (_nmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _nmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountName", String.class);

                method.invoke(_nmDiscountProjMasterRemoteModel, discountName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmDiscountProjMasterRemoteModel() {
        return _nmDiscountProjMasterRemoteModel;
    }

    public void setNmDiscountProjMasterRemoteModel(
        BaseModel<?> nmDiscountProjMasterRemoteModel) {
        _nmDiscountProjMasterRemoteModel = nmDiscountProjMasterRemoteModel;
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

        Class<?> remoteModelClass = _nmDiscountProjMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmDiscountProjMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmDiscountProjMasterLocalServiceUtil.addNmDiscountProjMaster(this);
        } else {
            NmDiscountProjMasterLocalServiceUtil.updateNmDiscountProjMaster(this);
        }
    }

    @Override
    public NmDiscountProjMaster toEscapedModel() {
        return (NmDiscountProjMaster) ProxyUtil.newProxyInstance(NmDiscountProjMaster.class.getClassLoader(),
            new Class[] { NmDiscountProjMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmDiscountProjMasterClp clone = new NmDiscountProjMasterClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setDiscountId(getDiscountId());
        clone.setUserGroup(getUserGroup());
        clone.setPriceGroupType(getPriceGroupType());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setNetFlag(getNetFlag());
        clone.setMethodology(getMethodology());
        clone.setDiscountName(getDiscountName());

        return clone;
    }

    @Override
    public int compareTo(NmDiscountProjMaster nmDiscountProjMaster) {
        int primaryKey = nmDiscountProjMaster.getPrimaryKey();

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

        if (!(obj instanceof NmDiscountProjMasterClp)) {
            return false;
        }

        NmDiscountProjMasterClp nmDiscountProjMaster = (NmDiscountProjMasterClp) obj;

        int primaryKey = nmDiscountProjMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", discountId=");
        sb.append(getDiscountId());
        sb.append(", userGroup=");
        sb.append(getUserGroup());
        sb.append(", priceGroupType=");
        sb.append(getPriceGroupType());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", netFlag=");
        sb.append(getNetFlag());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", discountName=");
        sb.append(getDiscountName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmDiscountProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountId</column-name><column-value><![CDATA[");
        sb.append(getDiscountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userGroup</column-name><column-value><![CDATA[");
        sb.append(getUserGroup());
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
            "<column><column-name>netFlag</column-name><column-value><![CDATA[");
        sb.append(getNetFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountName</column-name><column-value><![CDATA[");
        sb.append(getDiscountName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
