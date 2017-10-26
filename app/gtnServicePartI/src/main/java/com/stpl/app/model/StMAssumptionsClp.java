package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StMAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StMAssumptionsPK;

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


public class StMAssumptionsClp extends BaseModelImpl<StMAssumptions>
    implements StMAssumptions {
    private double _grossSalesPercentChange;
    private double _grossSalesPrior;
    private int _projYear;
    private double _totalDiscountPercentProjected;
    private int _camId;
    private String _commentary;
    private boolean _isChecked;
    private int _userId;
    private Date _lastModifiedDate;
    private double _grossSalesProjected;
    private double _totalDiscountPercentChange;
    private double _totalDiscountPercentPrior;
    private double _netSalesPercentChange;
    private boolean _parent;
    private String _stMAssumptionsSid;
    private int _projectionPeriod;
    private int _projectionDetailsSid;
    private double _netSalesPrior;
    private int _sessionId;
    private double _netSalesProjected;
    private String _reasonCodes;
    private int _mAssumptionsSid;
    private BaseModel<?> _stMAssumptionsRemoteModel;

    public StMAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StMAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StMAssumptions.class.getName();
    }

    @Override
    public StMAssumptionsPK getPrimaryKey() {
        return new StMAssumptionsPK(_projYear, _userId, _stMAssumptionsSid,
            _projectionPeriod, _projectionDetailsSid, _sessionId,
            _mAssumptionsSid);
    }

    @Override
    public void setPrimaryKey(StMAssumptionsPK primaryKey) {
        setProjYear(primaryKey.projYear);
        setUserId(primaryKey.userId);
        setStMAssumptionsSid(primaryKey.stMAssumptionsSid);
        setProjectionPeriod(primaryKey.projectionPeriod);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setSessionId(primaryKey.sessionId);
        setMAssumptionsSid(primaryKey.mAssumptionsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StMAssumptionsPK(_projYear, _userId, _stMAssumptionsSid,
            _projectionPeriod, _projectionDetailsSid, _sessionId,
            _mAssumptionsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StMAssumptionsPK) primaryKeyObj);
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
        attributes.put("isChecked", getIsChecked());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("grossSalesProjected", getGrossSalesProjected());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("netSalesPercentChange", getNetSalesPercentChange());
        attributes.put("parent", getParent());
        attributes.put("stMAssumptionsSid", getStMAssumptionsSid());
        attributes.put("projectionPeriod", getProjectionPeriod());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netSalesPrior", getNetSalesPrior());
        attributes.put("sessionId", getSessionId());
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

        Boolean isChecked = (Boolean) attributes.get("isChecked");

        if (isChecked != null) {
            setIsChecked(isChecked);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
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

        String stMAssumptionsSid = (String) attributes.get("stMAssumptionsSid");

        if (stMAssumptionsSid != null) {
            setStMAssumptionsSid(stMAssumptionsSid);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPercentChange",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel,
                    grossSalesPercentChange);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPrior",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel, grossSalesPrior);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjYear", int.class);

                method.invoke(_stMAssumptionsRemoteModel, projYear);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentProjected",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel,
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCamId", int.class);

                method.invoke(_stMAssumptionsRemoteModel, camId);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentary", String.class);

                method.invoke(_stMAssumptionsRemoteModel, commentary);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setIsChecked", boolean.class);

                method.invoke(_stMAssumptionsRemoteModel, isChecked);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stMAssumptionsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stMAssumptionsRemoteModel, lastModifiedDate);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesProjected",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel, grossSalesProjected);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentChange",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel,
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentPrior",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel,
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPercentChange",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel, netSalesPercentChange);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", boolean.class);

                method.invoke(_stMAssumptionsRemoteModel, parent);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStMAssumptionsSid() {
        return _stMAssumptionsSid;
    }

    @Override
    public void setStMAssumptionsSid(String stMAssumptionsSid) {
        _stMAssumptionsSid = stMAssumptionsSid;

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setStMAssumptionsSid",
                        String.class);

                method.invoke(_stMAssumptionsRemoteModel, stMAssumptionsSid);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPeriod", int.class);

                method.invoke(_stMAssumptionsRemoteModel, projectionPeriod);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stMAssumptionsRemoteModel, projectionDetailsSid);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrior", double.class);

                method.invoke(_stMAssumptionsRemoteModel, netSalesPrior);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stMAssumptionsRemoteModel, sessionId);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesProjected",
                        double.class);

                method.invoke(_stMAssumptionsRemoteModel, netSalesProjected);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonCodes", String.class);

                method.invoke(_stMAssumptionsRemoteModel, reasonCodes);
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

        if (_stMAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stMAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setMAssumptionsSid", int.class);

                method.invoke(_stMAssumptionsRemoteModel, mAssumptionsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStMAssumptionsRemoteModel() {
        return _stMAssumptionsRemoteModel;
    }

    public void setStMAssumptionsRemoteModel(
        BaseModel<?> stMAssumptionsRemoteModel) {
        _stMAssumptionsRemoteModel = stMAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _stMAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stMAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StMAssumptionsLocalServiceUtil.addStMAssumptions(this);
        } else {
            StMAssumptionsLocalServiceUtil.updateStMAssumptions(this);
        }
    }

    @Override
    public StMAssumptions toEscapedModel() {
        return (StMAssumptions) ProxyUtil.newProxyInstance(StMAssumptions.class.getClassLoader(),
            new Class[] { StMAssumptions.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StMAssumptionsClp clone = new StMAssumptionsClp();

        clone.setGrossSalesPercentChange(getGrossSalesPercentChange());
        clone.setGrossSalesPrior(getGrossSalesPrior());
        clone.setProjYear(getProjYear());
        clone.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
        clone.setCamId(getCamId());
        clone.setCommentary(getCommentary());
        clone.setIsChecked(getIsChecked());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setGrossSalesProjected(getGrossSalesProjected());
        clone.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
        clone.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
        clone.setNetSalesPercentChange(getNetSalesPercentChange());
        clone.setParent(getParent());
        clone.setStMAssumptionsSid(getStMAssumptionsSid());
        clone.setProjectionPeriod(getProjectionPeriod());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setNetSalesPrior(getNetSalesPrior());
        clone.setSessionId(getSessionId());
        clone.setNetSalesProjected(getNetSalesProjected());
        clone.setReasonCodes(getReasonCodes());
        clone.setMAssumptionsSid(getMAssumptionsSid());

        return clone;
    }

    @Override
    public int compareTo(StMAssumptions stMAssumptions) {
        StMAssumptionsPK primaryKey = stMAssumptions.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMAssumptionsClp)) {
            return false;
        }

        StMAssumptionsClp stMAssumptions = (StMAssumptionsClp) obj;

        StMAssumptionsPK primaryKey = stMAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

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
        sb.append(", isChecked=");
        sb.append(getIsChecked());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
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
        sb.append(", stMAssumptionsSid=");
        sb.append(getStMAssumptionsSid());
        sb.append(", projectionPeriod=");
        sb.append(getProjectionPeriod());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", netSalesPrior=");
        sb.append(getNetSalesPrior());
        sb.append(", sessionId=");
        sb.append(getSessionId());
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
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StMAssumptions");
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
            "<column><column-name>isChecked</column-name><column-value><![CDATA[");
        sb.append(getIsChecked());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
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
            "<column><column-name>stMAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getStMAssumptionsSid());
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
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
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
