package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StNmAssumptionsPK;

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


public class StNmAssumptionsClp extends BaseModelImpl<StNmAssumptions>
    implements StNmAssumptions {
    private Date _lastModifiedDate;
    private boolean _parent;
    private int _projectionPeriod;
    private String _commentary;
    private int _nmAssumptionsSid;
    private int _projectionDetailsSid;
    private double _netSalesPrior;
    private int _userId;
    private double _grossSalesPercentChange;
    private double _totalDiscountPercentChange;
    private String _reasonCodes;
    private double _totalDiscountPercentProjected;
    private double _totalDiscountPercentPrior;
    private double _netSalesProjected;
    private String _stNmAssumptionsSid;
    private double _grossSalesProjected;
    private int _sessionId;
    private double _grossSalesPrior;
    private boolean _isChecked;
    private int _camId;
    private double _netSalesPercentChange;
    private String _segment;
    private BaseModel<?> _stNmAssumptionsRemoteModel;

    public StNmAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StNmAssumptions.class.getName();
    }

    @Override
    public StNmAssumptionsPK getPrimaryKey() {
        return new StNmAssumptionsPK(_projectionPeriod, _nmAssumptionsSid,
            _projectionDetailsSid, _userId, _stNmAssumptionsSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmAssumptionsPK primaryKey) {
        setProjectionPeriod(primaryKey.projectionPeriod);
        setNmAssumptionsSid(primaryKey.nmAssumptionsSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setStNmAssumptionsSid(primaryKey.stNmAssumptionsSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmAssumptionsPK(_projectionPeriod, _nmAssumptionsSid,
            _projectionDetailsSid, _userId, _stNmAssumptionsSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmAssumptionsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parent", getParent());
        attributes.put("projectionPeriod", getProjectionPeriod());
        attributes.put("commentary", getCommentary());
        attributes.put("nmAssumptionsSid", getNmAssumptionsSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netSalesPrior", getNetSalesPrior());
        attributes.put("userId", getUserId());
        attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("netSalesProjected", getNetSalesProjected());
        attributes.put("stNmAssumptionsSid", getStNmAssumptionsSid());
        attributes.put("grossSalesProjected", getGrossSalesProjected());
        attributes.put("sessionId", getSessionId());
        attributes.put("grossSalesPrior", getGrossSalesPrior());
        attributes.put("isChecked", getIsChecked());
        attributes.put("camId", getCamId());
        attributes.put("netSalesPercentChange", getNetSalesPercentChange());
        attributes.put("segment", getSegment());

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

        Integer projectionPeriod = (Integer) attributes.get("projectionPeriod");

        if (projectionPeriod != null) {
            setProjectionPeriod(projectionPeriod);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer nmAssumptionsSid = (Integer) attributes.get("nmAssumptionsSid");

        if (nmAssumptionsSid != null) {
            setNmAssumptionsSid(nmAssumptionsSid);
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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double grossSalesPercentChange = (Double) attributes.get(
                "grossSalesPercentChange");

        if (grossSalesPercentChange != null) {
            setGrossSalesPercentChange(grossSalesPercentChange);
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

        Double netSalesProjected = (Double) attributes.get("netSalesProjected");

        if (netSalesProjected != null) {
            setNetSalesProjected(netSalesProjected);
        }

        String stNmAssumptionsSid = (String) attributes.get(
                "stNmAssumptionsSid");

        if (stNmAssumptionsSid != null) {
            setStNmAssumptionsSid(stNmAssumptionsSid);
        }

        Double grossSalesProjected = (Double) attributes.get(
                "grossSalesProjected");

        if (grossSalesProjected != null) {
            setGrossSalesProjected(grossSalesProjected);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double grossSalesPrior = (Double) attributes.get("grossSalesPrior");

        if (grossSalesPrior != null) {
            setGrossSalesPrior(grossSalesPrior);
        }

        Boolean isChecked = (Boolean) attributes.get("isChecked");

        if (isChecked != null) {
            setIsChecked(isChecked);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        Double netSalesPercentChange = (Double) attributes.get(
                "netSalesPercentChange");

        if (netSalesPercentChange != null) {
            setNetSalesPercentChange(netSalesPercentChange);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmAssumptionsRemoteModel, lastModifiedDate);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", boolean.class);

                method.invoke(_stNmAssumptionsRemoteModel, parent);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPeriod", int.class);

                method.invoke(_stNmAssumptionsRemoteModel, projectionPeriod);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentary", String.class);

                method.invoke(_stNmAssumptionsRemoteModel, commentary);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNmAssumptionsSid() {
        return _nmAssumptionsSid;
    }

    @Override
    public void setNmAssumptionsSid(int nmAssumptionsSid) {
        _nmAssumptionsSid = nmAssumptionsSid;

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNmAssumptionsSid", int.class);

                method.invoke(_stNmAssumptionsRemoteModel, nmAssumptionsSid);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmAssumptionsRemoteModel, projectionDetailsSid);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrior", double.class);

                method.invoke(_stNmAssumptionsRemoteModel, netSalesPrior);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmAssumptionsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossSalesPercentChange() {
        return _grossSalesPercentChange;
    }

    @Override
    public void setGrossSalesPercentChange(double grossSalesPercentChange) {
        _grossSalesPercentChange = grossSalesPercentChange;

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPercentChange",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel,
                    grossSalesPercentChange);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentChange",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel,
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonCodes", String.class);

                method.invoke(_stNmAssumptionsRemoteModel, reasonCodes);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentProjected",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel,
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDiscountPercentPrior",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel,
                    totalDiscountPercentPrior);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesProjected",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel, netSalesProjected);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStNmAssumptionsSid() {
        return _stNmAssumptionsSid;
    }

    @Override
    public void setStNmAssumptionsSid(String stNmAssumptionsSid) {
        _stNmAssumptionsSid = stNmAssumptionsSid;

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setStNmAssumptionsSid",
                        String.class);

                method.invoke(_stNmAssumptionsRemoteModel, stNmAssumptionsSid);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesProjected",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel, grossSalesProjected);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmAssumptionsRemoteModel, sessionId);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossSalesPrior",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel, grossSalesPrior);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setIsChecked", boolean.class);

                method.invoke(_stNmAssumptionsRemoteModel, isChecked);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCamId", int.class);

                method.invoke(_stNmAssumptionsRemoteModel, camId);
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

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPercentChange",
                        double.class);

                method.invoke(_stNmAssumptionsRemoteModel, netSalesPercentChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSegment() {
        return _segment;
    }

    @Override
    public void setSegment(String segment) {
        _segment = segment;

        if (_stNmAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNmAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_stNmAssumptionsRemoteModel, segment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmAssumptionsRemoteModel() {
        return _stNmAssumptionsRemoteModel;
    }

    public void setStNmAssumptionsRemoteModel(
        BaseModel<?> stNmAssumptionsRemoteModel) {
        _stNmAssumptionsRemoteModel = stNmAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _stNmAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmAssumptionsLocalServiceUtil.addStNmAssumptions(this);
        } else {
            StNmAssumptionsLocalServiceUtil.updateStNmAssumptions(this);
        }
    }

    @Override
    public StNmAssumptions toEscapedModel() {
        return (StNmAssumptions) ProxyUtil.newProxyInstance(StNmAssumptions.class.getClassLoader(),
            new Class[] { StNmAssumptions.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmAssumptionsClp clone = new StNmAssumptionsClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setParent(getParent());
        clone.setProjectionPeriod(getProjectionPeriod());
        clone.setCommentary(getCommentary());
        clone.setNmAssumptionsSid(getNmAssumptionsSid());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setNetSalesPrior(getNetSalesPrior());
        clone.setUserId(getUserId());
        clone.setGrossSalesPercentChange(getGrossSalesPercentChange());
        clone.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
        clone.setReasonCodes(getReasonCodes());
        clone.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
        clone.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
        clone.setNetSalesProjected(getNetSalesProjected());
        clone.setStNmAssumptionsSid(getStNmAssumptionsSid());
        clone.setGrossSalesProjected(getGrossSalesProjected());
        clone.setSessionId(getSessionId());
        clone.setGrossSalesPrior(getGrossSalesPrior());
        clone.setIsChecked(getIsChecked());
        clone.setCamId(getCamId());
        clone.setNetSalesPercentChange(getNetSalesPercentChange());
        clone.setSegment(getSegment());

        return clone;
    }

    @Override
    public int compareTo(StNmAssumptions stNmAssumptions) {
        StNmAssumptionsPK primaryKey = stNmAssumptions.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmAssumptionsClp)) {
            return false;
        }

        StNmAssumptionsClp stNmAssumptions = (StNmAssumptionsClp) obj;

        StNmAssumptionsPK primaryKey = stNmAssumptions.getPrimaryKey();

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

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", parent=");
        sb.append(getParent());
        sb.append(", projectionPeriod=");
        sb.append(getProjectionPeriod());
        sb.append(", commentary=");
        sb.append(getCommentary());
        sb.append(", nmAssumptionsSid=");
        sb.append(getNmAssumptionsSid());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", netSalesPrior=");
        sb.append(getNetSalesPrior());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", grossSalesPercentChange=");
        sb.append(getGrossSalesPercentChange());
        sb.append(", totalDiscountPercentChange=");
        sb.append(getTotalDiscountPercentChange());
        sb.append(", reasonCodes=");
        sb.append(getReasonCodes());
        sb.append(", totalDiscountPercentProjected=");
        sb.append(getTotalDiscountPercentProjected());
        sb.append(", totalDiscountPercentPrior=");
        sb.append(getTotalDiscountPercentPrior());
        sb.append(", netSalesProjected=");
        sb.append(getNetSalesProjected());
        sb.append(", stNmAssumptionsSid=");
        sb.append(getStNmAssumptionsSid());
        sb.append(", grossSalesProjected=");
        sb.append(getGrossSalesProjected());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", grossSalesPrior=");
        sb.append(getGrossSalesPrior());
        sb.append(", isChecked=");
        sb.append(getIsChecked());
        sb.append(", camId=");
        sb.append(getCamId());
        sb.append(", netSalesPercentChange=");
        sb.append(getNetSalesPercentChange());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmAssumptions");
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
            "<column><column-name>projectionPeriod</column-name><column-value><![CDATA[");
        sb.append(getProjectionPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commentary</column-name><column-value><![CDATA[");
        sb.append(getCommentary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nmAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getNmAssumptionsSid());
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossSalesPercentChange</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesPercentChange());
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
            "<column><column-name>totalDiscountPercentProjected</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDiscountPercentPrior</column-name><column-value><![CDATA[");
        sb.append(getTotalDiscountPercentPrior());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesProjected</column-name><column-value><![CDATA[");
        sb.append(getNetSalesProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stNmAssumptionsSid</column-name><column-value><![CDATA[");
        sb.append(getStNmAssumptionsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossSalesProjected</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesProjected());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossSalesPrior</column-name><column-value><![CDATA[");
        sb.append(getGrossSalesPrior());
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
            "<column><column-name>netSalesPercentChange</column-name><column-value><![CDATA[");
        sb.append(getNetSalesPercentChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>segment</column-name><column-value><![CDATA[");
        sb.append(getSegment());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
