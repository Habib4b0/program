package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MAssumptionsLocalServiceUtil;

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


public class MAssumptionsClp extends BaseModelImpl<MAssumptions>
    implements MAssumptions {
    private double _grossSalesPercentChange;
    private double _grossSalesPrior;
    private int _projYear;
    private double _totalDiscountPercentProjected;
    private int _camId;
    private String _commentary;
    private double _grossSalesProjected;
    private double _totalDiscountPercentChange;
    private double _totalDiscountPercentPrior;
    private double _netSalesPercentChange;
    private boolean _parent;
    private int _projectionPeriod;
    private int _projectionDetailsSid;
    private double _netSalesPrior;
    private double _netSalesProjected;
    private String _reasonCodes;
    private int _mAssumptionsSid;
    private BaseModel<?> _mAssumptionsRemoteModel;

    public MAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return MAssumptions.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _mAssumptionsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setMAssumptionsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _mAssumptionsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
        attributes.put("grossSalesPrior", getGrossSalesPrior());
        attributes.put("projYear", getProjYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("camId", getCamId());
        attributes.put("commentary", getCommentary());
        attributes.put("grossSalesProjected", getGrossSalesProjected());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("netSalesPercentChange", getNetSalesPercentChange());
        attributes.put("parent", getParent());
        attributes.put("projectionPeriod", getProjectionPeriod());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netSalesPrior", getNetSalesPrior());
        attributes.put("netSalesProjected", getNetSalesProjected());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("mAssumptionsSid", getMAssumptionsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double grossSalesPercentChange = (Double) attributes.get(
                "grossSalesPercentChange");

        if (grossSalesPercentChange != null) {
            setGrossSalesPercentChange(grossSalesPercentChange);
        }

        Double grossSalesPrior = (Double) attributes.get("grossSalesPrior");

        if (grossSalesPrior != null) {
            setGrossSalesPrior(grossSalesPrior);
        }

        Integer projYear = (Integer) attributes.get("projYear");

        if (projYear != null) {
            setProjYear(projYear);
        }

        Double totalDiscountPercentProjected = (Double) attributes.get(
                "totalDiscountPercentProjected");

        if (totalDiscountPercentProjected != null) {
            setTotalDiscountPercentProjected(totalDiscountPercentProjected);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Double grossSalesProjected = (Double) attributes.get(
                "grossSalesProjected");

        if (grossSalesProjected != null) {
            setGrossSalesProjected(grossSalesProjected);
        }

        Double totalDiscountPercentChange = (Double) attributes.get(
                "totalDiscountPercentChange");

        if (totalDiscountPercentChange != null) {
            setTotalDiscountPercentChange(totalDiscountPercentChange);
        }

        Double totalDiscountPercentPrior = (Double) attributes.get(
                "totalDiscountPercentPrior");

        if (totalDiscountPercentPrior != null) {
            setTotalDiscountPercentPrior(totalDiscountPercentPrior);
        }

        Double netSalesPercentChange = (Double) attributes.get(
                "netSalesPercentChange");

        if (netSalesPercentChange != null) {
            setNetSalesPercentChange(netSalesPercentChange);
        }

        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        Integer projectionPeriod = (Integer) attributes.get("projectionPeriod");

        if (projectionPeriod != null) {
            setProjectionPeriod(projectionPeriod);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double netSalesPrior = (Double) attributes.get("netSalesPrior");

        if (netSalesPrior != null) {
            setNetSalesPrior(netSalesPrior);
        }

        Double netSalesProjected = (Double) attributes.get("netSalesProjected");

        if (netSalesProjected != null) {
            setNetSalesProjected(netSalesProjected);
        }

        String reasonCodes = (String) attributes.get("reasonCodes");

        if (reasonCodes != null) {
            setReasonCodes(reasonCodes);
        }

        Integer mAssumptionsSid = (Integer) attributes.get("mAssumptionsSid");

        if (mAssumptionsSid != null) {
            setMAssumptionsSid(mAssumptionsSid);
        }
    }

    @Override
    public double getGrossSalesPercentChange() {
        return _grossSalesPercentChange;
    }

    @Override
    public void setGrossSalesPercentChange(double grossSalesPercentChange) {
        _grossSalesPercentChange = grossSalesPercentChange;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPercentChange",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel, grossSalesPercentChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossSalesPrior() {
        return _grossSalesPrior;
    }

    @Override
    public void setGrossSalesPrior(double grossSalesPrior) {
        _grossSalesPrior = grossSalesPrior;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPrior",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel, grossSalesPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjYear() {
        return _projYear;
    }

    @Override
    public void setProjYear(int projYear) {
        _projYear = projYear;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjYear", int.class);

                method.invoke(_mAssumptionsRemoteModel, projYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountPercentProjected() {
        return _totalDiscountPercentProjected;
    }

    @Override
    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _totalDiscountPercentProjected = totalDiscountPercentProjected;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentProjected",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel,
                    totalDiscountPercentProjected);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCamId() {
        return _camId;
    }

    @Override
    public void setCamId(int camId) {
        _camId = camId;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCamId", int.class);

                method.invoke(_mAssumptionsRemoteModel, camId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCommentary() {
        return _commentary;
    }

    @Override
    public void setCommentary(String commentary) {
        _commentary = commentary;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentary", String.class);

                method.invoke(_mAssumptionsRemoteModel, commentary);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossSalesProjected() {
        return _grossSalesProjected;
    }

    @Override
    public void setGrossSalesProjected(double grossSalesProjected) {
        _grossSalesProjected = grossSalesProjected;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesProjected",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel, grossSalesProjected);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountPercentChange() {
        return _totalDiscountPercentChange;
    }

    @Override
    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _totalDiscountPercentChange = totalDiscountPercentChange;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentChange",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel,
                    totalDiscountPercentChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountPercentPrior() {
        return _totalDiscountPercentPrior;
    }

    @Override
    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _totalDiscountPercentPrior = totalDiscountPercentPrior;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentPrior",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel,
                    totalDiscountPercentPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesPercentChange() {
        return _netSalesPercentChange;
    }

    @Override
    public void setNetSalesPercentChange(double netSalesPercentChange) {
        _netSalesPercentChange = netSalesPercentChange;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPercentChange",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel, netSalesPercentChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getParent() {
        return _parent;
    }

    @Override
    public boolean isParent() {
        return _parent;
    }

    @Override
    public void setParent(boolean parent) {
        _parent = parent;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", boolean.class);

                method.invoke(_mAssumptionsRemoteModel, parent);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionPeriod() {
        return _projectionPeriod;
    }

    @Override
    public void setProjectionPeriod(int projectionPeriod) {
        _projectionPeriod = projectionPeriod;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPeriod", int.class);

                method.invoke(_mAssumptionsRemoteModel, projectionPeriod);
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

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mAssumptionsRemoteModel, projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesPrior() {
        return _netSalesPrior;
    }

    @Override
    public void setNetSalesPrior(double netSalesPrior) {
        _netSalesPrior = netSalesPrior;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrior", double.class);

                method.invoke(_mAssumptionsRemoteModel, netSalesPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesProjected() {
        return _netSalesProjected;
    }

    @Override
    public void setNetSalesProjected(double netSalesProjected) {
        _netSalesProjected = netSalesProjected;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesProjected",
                        double.class);

                method.invoke(_mAssumptionsRemoteModel, netSalesProjected);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonCodes() {
        return _reasonCodes;
    }

    @Override
    public void setReasonCodes(String reasonCodes) {
        _reasonCodes = reasonCodes;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonCodes", String.class);

                method.invoke(_mAssumptionsRemoteModel, reasonCodes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMAssumptionsSid() {
        return _mAssumptionsSid;
    }

    @Override
    public void setMAssumptionsSid(int mAssumptionsSid) {
        _mAssumptionsSid = mAssumptionsSid;

        if (_mAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _mAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setMAssumptionsSid", int.class);

                method.invoke(_mAssumptionsRemoteModel, mAssumptionsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMAssumptionsRemoteModel() {
        return _mAssumptionsRemoteModel;
    }

    public void setMAssumptionsRemoteModel(BaseModel<?> mAssumptionsRemoteModel) {
        _mAssumptionsRemoteModel = mAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _mAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MAssumptionsLocalServiceUtil.addMAssumptions(this);
        } else {
            MAssumptionsLocalServiceUtil.updateMAssumptions(this);
        }
    }

    @Override
    public MAssumptions toEscapedModel() {
        return (MAssumptions) ProxyUtil.newProxyInstance(MAssumptions.class.getClassLoader(),
            new Class[] { MAssumptions.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MAssumptionsClp clone = new MAssumptionsClp();

        clone.setGrossSalesPercentChange(getGrossSalesPercentChange());
        clone.setGrossSalesPrior(getGrossSalesPrior());
        clone.setProjYear(getProjYear());
        clone.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
        clone.setCamId(getCamId());
        clone.setCommentary(getCommentary());
        clone.setGrossSalesProjected(getGrossSalesProjected());
        clone.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
        clone.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
        clone.setNetSalesPercentChange(getNetSalesPercentChange());
        clone.setParent(getParent());
        clone.setProjectionPeriod(getProjectionPeriod());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setNetSalesPrior(getNetSalesPrior());
        clone.setNetSalesProjected(getNetSalesProjected());
        clone.setReasonCodes(getReasonCodes());
        clone.setMAssumptionsSid(getMAssumptionsSid());

        return clone;
    }

    @Override
    public int compareTo(MAssumptions mAssumptions) {
        int primaryKey = mAssumptions.getPrimaryKey();

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

        if (!(obj instanceof MAssumptionsClp)) {
            return false;
        }

        MAssumptionsClp mAssumptions = (MAssumptionsClp) obj;

        int primaryKey = mAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(35);

        sb.append("{grossSalesPercentChange=");
        sb.append(getGrossSalesPercentChange());
        sb.append(", grossSalesPrior=");
        sb.append(getGrossSalesPrior());
        sb.append(", projYear=");
        sb.append(getProjYear());
        sb.append(", totalDiscountPercentProjected=");
        sb.append(getTotalDiscountPercentProjected());
        sb.append(", camId=");
        sb.append(getCamId());
        sb.append(", commentary=");
        sb.append(getCommentary());
        sb.append(", grossSalesProjected=");
        sb.append(getGrossSalesProjected());
        sb.append(", totalDiscountPercentChange=");
        sb.append(getTotalDiscountPercentChange());
        sb.append(", totalDiscountPercentPrior=");
        sb.append(getTotalDiscountPercentPrior());
        sb.append(", netSalesPercentChange=");
        sb.append(getNetSalesPercentChange());
        sb.append(", parent=");
        sb.append(getParent());
        sb.append(", projectionPeriod=");
        sb.append(getProjectionPeriod());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", netSalesPrior=");
        sb.append(getNetSalesPrior());
        sb.append(", netSalesProjected=");
        sb.append(getNetSalesProjected());
        sb.append(", reasonCodes=");
        sb.append(getReasonCodes());
        sb.append(", mAssumptionsSid=");
        sb.append(getMAssumptionsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MAssumptions");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>grossSalesPercentChange</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesPercentChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossSalesPrior</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesPrior());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projYear</column-name><column-value><![CDATA[");
        sb.append(getProjYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentProjected</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>camId</column-name><column-value><![CDATA[");
        sb.append(getCamId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commentary</column-name><column-value><![CDATA[");
        sb.append(getCommentary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossSalesProjected</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentChange</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentPrior</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentPrior());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesPercentChange</column-name><column-value><![CDATA[");
        sb.append(getNetSalesPercentChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parent</column-name><column-value><![CDATA[");
        sb.append(getParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionPeriod</column-name><column-value><![CDATA[");
        sb.append(getProjectionPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesPrior</column-name><column-value><![CDATA[");
        sb.append(getNetSalesPrior());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesProjected</column-name><column-value><![CDATA[");
        sb.append(getNetSalesProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonCodes</column-name><column-value><![CDATA[");
        sb.append(getReasonCodes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getMAssumptionsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
