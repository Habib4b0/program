package com.stpl.app.model;

import com.stpl.app.service.ChAssumptionsLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class ChAssumptionsClp extends BaseModelImpl<ChAssumptions>
    implements ChAssumptions {
    private boolean _parent;
    private int _projectionDetailsSid;
    private String _commentary;
    private int _quarter;
    private double _totalDiscountPercentChange;
    private String _reasonCodes;
    private int _year;
    private double _totalDiscountPercentProjected;
    private double _totalDiscountPercentPrior;
    private int _chAssumptionsSid;
    private double _totalDiscountChange;
    private double _totalDiscountProjected;
    private int _camId;
    private double _grossTradeSales;
    private double _totalDiscountPrior;
    private BaseModel<?> _chAssumptionsRemoteModel;

    public ChAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return ChAssumptions.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _chAssumptionsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setChAssumptionsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _chAssumptionsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parent", getParent());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("commentary", getCommentary());
        attributes.put("quarter", getQuarter());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("year", getYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("chAssumptionsSid", getChAssumptionsSid());
        attributes.put("totalDiscountChange", getTotalDiscountChange());
        attributes.put("totalDiscountProjected", getTotalDiscountProjected());
        attributes.put("camId", getCamId());
        attributes.put("grossTradeSales", getGrossTradeSales());
        attributes.put("totalDiscountPrior", getTotalDiscountPrior());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer quarter = (Integer) attributes.get("quarter");

        if (quarter != null) {
            setQuarter(quarter);
        }

        Double totalDiscountPercentChange = (Double) attributes.get(
                "totalDiscountPercentChange");

        if (totalDiscountPercentChange != null) {
            setTotalDiscountPercentChange(totalDiscountPercentChange);
        }

        String reasonCodes = (String) attributes.get("reasonCodes");

        if (reasonCodes != null) {
            setReasonCodes(reasonCodes);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Double totalDiscountPercentProjected = (Double) attributes.get(
                "totalDiscountPercentProjected");

        if (totalDiscountPercentProjected != null) {
            setTotalDiscountPercentProjected(totalDiscountPercentProjected);
        }

        Double totalDiscountPercentPrior = (Double) attributes.get(
                "totalDiscountPercentPrior");

        if (totalDiscountPercentPrior != null) {
            setTotalDiscountPercentPrior(totalDiscountPercentPrior);
        }

        Integer chAssumptionsSid = (Integer) attributes.get("chAssumptionsSid");

        if (chAssumptionsSid != null) {
            setChAssumptionsSid(chAssumptionsSid);
        }

        Double totalDiscountChange = (Double) attributes.get(
                "totalDiscountChange");

        if (totalDiscountChange != null) {
            setTotalDiscountChange(totalDiscountChange);
        }

        Double totalDiscountProjected = (Double) attributes.get(
                "totalDiscountProjected");

        if (totalDiscountProjected != null) {
            setTotalDiscountProjected(totalDiscountProjected);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        Double grossTradeSales = (Double) attributes.get("grossTradeSales");

        if (grossTradeSales != null) {
            setGrossTradeSales(grossTradeSales);
        }

        Double totalDiscountPrior = (Double) attributes.get(
                "totalDiscountPrior");

        if (totalDiscountPrior != null) {
            setTotalDiscountPrior(totalDiscountPrior);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", boolean.class);

                method.invoke(_chAssumptionsRemoteModel, parent);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_chAssumptionsRemoteModel, projectionDetailsSid);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentary", String.class);

                method.invoke(_chAssumptionsRemoteModel, commentary);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getQuarter() {
        return _quarter;
    }

    @Override
    public void setQuarter(int quarter) {
        _quarter = quarter;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setQuarter", int.class);

                method.invoke(_chAssumptionsRemoteModel, quarter);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentChange",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel,
                    totalDiscountPercentChange);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonCodes", String.class);

                method.invoke(_chAssumptionsRemoteModel, reasonCodes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getYear() {
        return _year;
    }

    @Override
    public void setYear(int year) {
        _year = year;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", int.class);

                method.invoke(_chAssumptionsRemoteModel, year);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentProjected",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel,
                    totalDiscountPercentProjected);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentPrior",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel,
                    totalDiscountPercentPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getChAssumptionsSid() {
        return _chAssumptionsSid;
    }

    @Override
    public void setChAssumptionsSid(int chAssumptionsSid) {
        _chAssumptionsSid = chAssumptionsSid;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setChAssumptionsSid", int.class);

                method.invoke(_chAssumptionsRemoteModel, chAssumptionsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountChange() {
        return _totalDiscountChange;
    }

    @Override
    public void setTotalDiscountChange(double totalDiscountChange) {
        _totalDiscountChange = totalDiscountChange;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountChange",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel, totalDiscountChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountProjected() {
        return _totalDiscountProjected;
    }

    @Override
    public void setTotalDiscountProjected(double totalDiscountProjected) {
        _totalDiscountProjected = totalDiscountProjected;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountProjected",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel, totalDiscountProjected);
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

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCamId", int.class);

                method.invoke(_chAssumptionsRemoteModel, camId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossTradeSales() {
        return _grossTradeSales;
    }

    @Override
    public void setGrossTradeSales(double grossTradeSales) {
        _grossTradeSales = grossTradeSales;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossTradeSales",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel, grossTradeSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDiscountPrior() {
        return _totalDiscountPrior;
    }

    @Override
    public void setTotalDiscountPrior(double totalDiscountPrior) {
        _totalDiscountPrior = totalDiscountPrior;

        if (_chAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _chAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPrior",
                        double.class);

                method.invoke(_chAssumptionsRemoteModel, totalDiscountPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChAssumptionsRemoteModel() {
        return _chAssumptionsRemoteModel;
    }

    public void setChAssumptionsRemoteModel(
        BaseModel<?> chAssumptionsRemoteModel) {
        _chAssumptionsRemoteModel = chAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _chAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChAssumptionsLocalServiceUtil.addChAssumptions(this);
        } else {
            ChAssumptionsLocalServiceUtil.updateChAssumptions(this);
        }
    }

    @Override
    public ChAssumptions toEscapedModel() {
        return (ChAssumptions) ProxyUtil.newProxyInstance(ChAssumptions.class.getClassLoader(),
            new Class[] { ChAssumptions.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChAssumptionsClp clone = new ChAssumptionsClp();

        clone.setParent(getParent());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setCommentary(getCommentary());
        clone.setQuarter(getQuarter());
        clone.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
        clone.setReasonCodes(getReasonCodes());
        clone.setYear(getYear());
        clone.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
        clone.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
        clone.setChAssumptionsSid(getChAssumptionsSid());
        clone.setTotalDiscountChange(getTotalDiscountChange());
        clone.setTotalDiscountProjected(getTotalDiscountProjected());
        clone.setCamId(getCamId());
        clone.setGrossTradeSales(getGrossTradeSales());
        clone.setTotalDiscountPrior(getTotalDiscountPrior());

        return clone;
    }

    @Override
    public int compareTo(ChAssumptions chAssumptions) {
        int primaryKey = chAssumptions.getPrimaryKey();

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

        if (!(obj instanceof ChAssumptionsClp)) {
            return false;
        }

        ChAssumptionsClp chAssumptions = (ChAssumptionsClp) obj;

        int primaryKey = chAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{parent=");
        sb.append(getParent());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", commentary=");
        sb.append(getCommentary());
        sb.append(", quarter=");
        sb.append(getQuarter());
        sb.append(", totalDiscountPercentChange=");
        sb.append(getTotalDiscountPercentChange());
        sb.append(", reasonCodes=");
        sb.append(getReasonCodes());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", totalDiscountPercentProjected=");
        sb.append(getTotalDiscountPercentProjected());
        sb.append(", totalDiscountPercentPrior=");
        sb.append(getTotalDiscountPercentPrior());
        sb.append(", chAssumptionsSid=");
        sb.append(getChAssumptionsSid());
        sb.append(", totalDiscountChange=");
        sb.append(getTotalDiscountChange());
        sb.append(", totalDiscountProjected=");
        sb.append(getTotalDiscountProjected());
        sb.append(", camId=");
        sb.append(getCamId());
        sb.append(", grossTradeSales=");
        sb.append(getGrossTradeSales());
        sb.append(", totalDiscountPrior=");
        sb.append(getTotalDiscountPrior());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ChAssumptions");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>parent</column-name><column-value><![CDATA[");
        sb.append(getParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commentary</column-name><column-value><![CDATA[");
        sb.append(getCommentary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quarter</column-name><column-value><![CDATA[");
        sb.append(getQuarter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentChange</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonCodes</column-name><column-value><![CDATA[");
        sb.append(getReasonCodes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentProjected</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentPrior</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentPrior());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>chAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getChAssumptionsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountChange</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountProjected</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>camId</column-name><column-value><![CDATA[");
        sb.append(getCamId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossTradeSales</column-name><column-value><![CDATA[");
        sb.append(getGrossTradeSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPrior</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPrior());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
