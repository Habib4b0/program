package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StChAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StChAssumptionsPK;

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


public class StChAssumptionsClp extends BaseModelImpl<StChAssumptions>
    implements StChAssumptions {
    private Date _lastModifiedDate;
    private boolean _parent;
    private String _commentary;
    private int _projectionDetailsSid;
    private int _userId;
    private int _quarter;
    private double _totalDiscountPercentChange;
    private String _reasonCodes;
    private int _year;
    private double _totalDiscountPercentProjected;
    private double _totalDiscountPercentPrior;
    private String _stChAssumptionsSid;
    private int _chAssumptionsSid;
    private double _totalDiscountChange;
    private int _sessionId;
    private double _totalDiscountProjected;
    private boolean _isChecked;
    private int _camId;
    private double _grossTradeSales;
    private double _totalDiscountPrior;
    private BaseModel<?> _stChAssumptionsRemoteModel;

    public StChAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StChAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StChAssumptions.class.getName();
    }

    @Override
    public StChAssumptionsPK getPrimaryKey() {
        return new StChAssumptionsPK(_projectionDetailsSid, _userId, _quarter,
            _year, _stChAssumptionsSid, _chAssumptionsSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StChAssumptionsPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setQuarter(primaryKey.quarter);
        setYear(primaryKey.year);
        setStChAssumptionsSid(primaryKey.stChAssumptionsSid);
        setChAssumptionsSid(primaryKey.chAssumptionsSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StChAssumptionsPK(_projectionDetailsSid, _userId, _quarter,
            _year, _stChAssumptionsSid, _chAssumptionsSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StChAssumptionsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parent", getParent());
        attributes.put("commentary", getCommentary());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("quarter", getQuarter());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("year", getYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("stChAssumptionsSid", getStChAssumptionsSid());
        attributes.put("chAssumptionsSid", getChAssumptionsSid());
        attributes.put("totalDiscountChange", getTotalDiscountChange());
        attributes.put("sessionId", getSessionId());
        attributes.put("totalDiscountProjected", getTotalDiscountProjected());
        attributes.put("isChecked", getIsChecked());
        attributes.put("camId", getCamId());
        attributes.put("grossTradeSales", getGrossTradeSales());
        attributes.put("totalDiscountPrior", getTotalDiscountPrior());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
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

        String stChAssumptionsSid = (String) attributes.get(
                "stChAssumptionsSid");

        if (stChAssumptionsSid != null) {
            setStChAssumptionsSid(stChAssumptionsSid);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double totalDiscountProjected = (Double) attributes.get(
                "totalDiscountProjected");

        if (totalDiscountProjected != null) {
            setTotalDiscountProjected(totalDiscountProjected);
        }

        Boolean isChecked = (Boolean) attributes.get("isChecked");

        if (isChecked != null) {
            setIsChecked(isChecked);
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
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stChAssumptionsRemoteModel, lastModifiedDate);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", boolean.class);

                method.invoke(_stChAssumptionsRemoteModel, parent);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentary", String.class);

                method.invoke(_stChAssumptionsRemoteModel, commentary);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stChAssumptionsRemoteModel, projectionDetailsSid);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stChAssumptionsRemoteModel, userId);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setQuarter", int.class);

                method.invoke(_stChAssumptionsRemoteModel, quarter);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentChange",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel,
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonCodes", String.class);

                method.invoke(_stChAssumptionsRemoteModel, reasonCodes);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", int.class);

                method.invoke(_stChAssumptionsRemoteModel, year);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentProjected",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel,
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentPrior",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel,
                    totalDiscountPercentPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStChAssumptionsSid() {
        return _stChAssumptionsSid;
    }

    @Override
    public void setStChAssumptionsSid(String stChAssumptionsSid) {
        _stChAssumptionsSid = stChAssumptionsSid;

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setStChAssumptionsSid",
                        String.class);

                method.invoke(_stChAssumptionsRemoteModel, stChAssumptionsSid);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setChAssumptionsSid", int.class);

                method.invoke(_stChAssumptionsRemoteModel, chAssumptionsSid);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountChange",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel, totalDiscountChange);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stChAssumptionsRemoteModel, sessionId);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountProjected",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel,
                    totalDiscountProjected);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsChecked() {
        return _isChecked;
    }

    @Override
    public boolean isIsChecked() {
        return _isChecked;
    }

    @Override
    public void setIsChecked(boolean isChecked) {
        _isChecked = isChecked;

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setIsChecked", boolean.class);

                method.invoke(_stChAssumptionsRemoteModel, isChecked);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCamId", int.class);

                method.invoke(_stChAssumptionsRemoteModel, camId);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossTradeSales",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel, grossTradeSales);
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

        if (_stChAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stChAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPrior",
                        double.class);

                method.invoke(_stChAssumptionsRemoteModel, totalDiscountPrior);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStChAssumptionsRemoteModel() {
        return _stChAssumptionsRemoteModel;
    }

    public void setStChAssumptionsRemoteModel(
        BaseModel<?> stChAssumptionsRemoteModel) {
        _stChAssumptionsRemoteModel = stChAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _stChAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stChAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StChAssumptionsLocalServiceUtil.addStChAssumptions(this);
        } else {
            StChAssumptionsLocalServiceUtil.updateStChAssumptions(this);
        }
    }

    @Override
    public StChAssumptions toEscapedModel() {
        return (StChAssumptions) ProxyUtil.newProxyInstance(StChAssumptions.class.getClassLoader(),
            new Class[] { StChAssumptions.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StChAssumptionsClp clone = new StChAssumptionsClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setParent(getParent());
        clone.setCommentary(getCommentary());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setQuarter(getQuarter());
        clone.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
        clone.setReasonCodes(getReasonCodes());
        clone.setYear(getYear());
        clone.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
        clone.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
        clone.setStChAssumptionsSid(getStChAssumptionsSid());
        clone.setChAssumptionsSid(getChAssumptionsSid());
        clone.setTotalDiscountChange(getTotalDiscountChange());
        clone.setSessionId(getSessionId());
        clone.setTotalDiscountProjected(getTotalDiscountProjected());
        clone.setIsChecked(getIsChecked());
        clone.setCamId(getCamId());
        clone.setGrossTradeSales(getGrossTradeSales());
        clone.setTotalDiscountPrior(getTotalDiscountPrior());

        return clone;
    }

    @Override
    public int compareTo(StChAssumptions stChAssumptions) {
        StChAssumptionsPK primaryKey = stChAssumptions.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChAssumptionsClp)) {
            return false;
        }

        StChAssumptionsClp stChAssumptions = (StChAssumptionsClp) obj;

        StChAssumptionsPK primaryKey = stChAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(41);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", parent=");
        sb.append(getParent());
        sb.append(", commentary=");
        sb.append(getCommentary());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
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
        sb.append(", stChAssumptionsSid=");
        sb.append(getStChAssumptionsSid());
        sb.append(", chAssumptionsSid=");
        sb.append(getChAssumptionsSid());
        sb.append(", totalDiscountChange=");
        sb.append(getTotalDiscountChange());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", totalDiscountProjected=");
        sb.append(getTotalDiscountProjected());
        sb.append(", isChecked=");
        sb.append(getIsChecked());
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
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StChAssumptions");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parent</column-name><column-value><![CDATA[");
        sb.append(getParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commentary</column-name><column-value><![CDATA[");
        sb.append(getCommentary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
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
            "<column><column-name>stChAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getStChAssumptionsSid());
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
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountProjected</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isChecked</column-name><column-value><![CDATA[");
        sb.append(getIsChecked());
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
