package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MSupplementalDiscMasterLocalServiceUtil;

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


public class MSupplementalDiscMasterClp extends BaseModelImpl<MSupplementalDiscMaster>
    implements MSupplementalDiscMaster {
    private double _actualDiscountRate2;
    private double _actualDiscountRate1;
    private String _marketType;
    private String _actualMethodology;
    private double _actualContractPrice;
    private int _projectionDetailsSid;
    private double _actualDiscount;
    private int _checkRecord;
    private Date _contractEndDate;
    private BaseModel<?> _mSupplementalDiscMasterRemoteModel;

    public MSupplementalDiscMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MSupplementalDiscMaster.class;
    }

    @Override
    public String getModelClassName() {
        return MSupplementalDiscMaster.class.getName();
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

        attributes.put("actualDiscountRate2", getActualDiscountRate2());
        attributes.put("actualDiscountRate1", getActualDiscountRate1());
        attributes.put("marketType", getMarketType());
        attributes.put("actualMethodology", getActualMethodology());
        attributes.put("actualContractPrice", getActualContractPrice());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualDiscount", getActualDiscount());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractEndDate", getContractEndDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualDiscountRate2 = (Double) attributes.get(
                "actualDiscountRate2");

        if (actualDiscountRate2 != null) {
            setActualDiscountRate2(actualDiscountRate2);
        }

        Double actualDiscountRate1 = (Double) attributes.get(
                "actualDiscountRate1");

        if (actualDiscountRate1 != null) {
            setActualDiscountRate1(actualDiscountRate1);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        String actualMethodology = (String) attributes.get("actualMethodology");

        if (actualMethodology != null) {
            setActualMethodology(actualMethodology);
        }

        Double actualContractPrice = (Double) attributes.get(
                "actualContractPrice");

        if (actualContractPrice != null) {
            setActualContractPrice(actualContractPrice);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double actualDiscount = (Double) attributes.get("actualDiscount");

        if (actualDiscount != null) {
            setActualDiscount(actualDiscount);
        }

        Integer checkRecord = (Integer) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date contractEndDate = (Date) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }
    }

    @Override
    public double getActualDiscountRate2() {
        return _actualDiscountRate2;
    }

    @Override
    public void setActualDiscountRate2(double actualDiscountRate2) {
        _actualDiscountRate2 = actualDiscountRate2;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountRate2",
                        double.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    actualDiscountRate2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscountRate1() {
        return _actualDiscountRate1;
    }

    @Override
    public void setActualDiscountRate1(double actualDiscountRate1) {
        _actualDiscountRate1 = actualDiscountRate1;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountRate1",
                        double.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    actualDiscountRate1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketType() {
        return _marketType;
    }

    @Override
    public void setMarketType(String marketType) {
        _marketType = marketType;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketType", String.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel, marketType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActualMethodology() {
        return _actualMethodology;
    }

    @Override
    public void setActualMethodology(String actualMethodology) {
        _actualMethodology = actualMethodology;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualMethodology",
                        String.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    actualMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualContractPrice() {
        return _actualContractPrice;
    }

    @Override
    public void setActualContractPrice(double actualContractPrice) {
        _actualContractPrice = actualContractPrice;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualContractPrice",
                        double.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    actualContractPrice);
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

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscount() {
        return _actualDiscount;
    }

    @Override
    public void setActualDiscount(double actualDiscount) {
        _actualDiscount = actualDiscount;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscount",
                        double.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    actualDiscount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(int checkRecord) {
        _checkRecord = checkRecord;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", int.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractEndDate() {
        return _contractEndDate;
    }

    @Override
    public void setContractEndDate(Date contractEndDate) {
        _contractEndDate = contractEndDate;

        if (_mSupplementalDiscMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractEndDate", Date.class);

                method.invoke(_mSupplementalDiscMasterRemoteModel,
                    contractEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMSupplementalDiscMasterRemoteModel() {
        return _mSupplementalDiscMasterRemoteModel;
    }

    public void setMSupplementalDiscMasterRemoteModel(
        BaseModel<?> mSupplementalDiscMasterRemoteModel) {
        _mSupplementalDiscMasterRemoteModel = mSupplementalDiscMasterRemoteModel;
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

        Class<?> remoteModelClass = _mSupplementalDiscMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mSupplementalDiscMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSupplementalDiscMasterLocalServiceUtil.addMSupplementalDiscMaster(this);
        } else {
            MSupplementalDiscMasterLocalServiceUtil.updateMSupplementalDiscMaster(this);
        }
    }

    @Override
    public MSupplementalDiscMaster toEscapedModel() {
        return (MSupplementalDiscMaster) ProxyUtil.newProxyInstance(MSupplementalDiscMaster.class.getClassLoader(),
            new Class[] { MSupplementalDiscMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MSupplementalDiscMasterClp clone = new MSupplementalDiscMasterClp();

        clone.setActualDiscountRate2(getActualDiscountRate2());
        clone.setActualDiscountRate1(getActualDiscountRate1());
        clone.setMarketType(getMarketType());
        clone.setActualMethodology(getActualMethodology());
        clone.setActualContractPrice(getActualContractPrice());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setActualDiscount(getActualDiscount());
        clone.setCheckRecord(getCheckRecord());
        clone.setContractEndDate(getContractEndDate());

        return clone;
    }

    @Override
    public int compareTo(MSupplementalDiscMaster mSupplementalDiscMaster) {
        int primaryKey = mSupplementalDiscMaster.getPrimaryKey();

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

        if (!(obj instanceof MSupplementalDiscMasterClp)) {
            return false;
        }

        MSupplementalDiscMasterClp mSupplementalDiscMaster = (MSupplementalDiscMasterClp) obj;

        int primaryKey = mSupplementalDiscMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{actualDiscountRate2=");
        sb.append(getActualDiscountRate2());
        sb.append(", actualDiscountRate1=");
        sb.append(getActualDiscountRate1());
        sb.append(", marketType=");
        sb.append(getMarketType());
        sb.append(", actualMethodology=");
        sb.append(getActualMethodology());
        sb.append(", actualContractPrice=");
        sb.append(getActualContractPrice());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", actualDiscount=");
        sb.append(getActualDiscount());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", contractEndDate=");
        sb.append(getContractEndDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MSupplementalDiscMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualDiscountRate2</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountRate2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountRate1</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountRate1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketType</column-name><column-value><![CDATA[");
        sb.append(getMarketType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualMethodology</column-name><column-value><![CDATA[");
        sb.append(getActualMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualContractPrice</column-name><column-value><![CDATA[");
        sb.append(getActualContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscount</column-name><column-value><![CDATA[");
        sb.append(getActualDiscount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractEndDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
