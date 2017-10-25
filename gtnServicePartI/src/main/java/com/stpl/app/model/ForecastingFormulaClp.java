package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;

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


public class ForecastingFormulaClp extends BaseModelImpl<ForecastingFormula>
    implements ForecastingFormula {
    private Date _createdDate;
    private int _formulaType;
    private int _forecastingFormulaSid;
    private String _formula;
    private String _formulaNo;
    private Date _modifiedDate;
    private boolean _isActive;
    private String _formulaName;
    private BaseModel<?> _forecastingFormulaRemoteModel;

    public ForecastingFormulaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingFormula.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingFormula.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _forecastingFormulaSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setForecastingFormulaSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _forecastingFormulaSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("formulaType", getFormulaType());
        attributes.put("forecastingFormulaSid", getForecastingFormulaSid());
        attributes.put("formula", getFormula());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("formulaName", getFormulaName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer formulaType = (Integer) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Integer forecastingFormulaSid = (Integer) attributes.get(
                "forecastingFormulaSid");

        if (forecastingFormulaSid != null) {
            setForecastingFormulaSid(forecastingFormulaSid);
        }

        String formula = (String) attributes.get("formula");

        if (formula != null) {
            setFormula(formula);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean isActive = (Boolean) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_forecastingFormulaRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(int formulaType) {
        _formulaType = formulaType;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", int.class);

                method.invoke(_forecastingFormulaRemoteModel, formulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getForecastingFormulaSid() {
        return _forecastingFormulaSid;
    }

    @Override
    public void setForecastingFormulaSid(int forecastingFormulaSid) {
        _forecastingFormulaSid = forecastingFormulaSid;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastingFormulaSid",
                        int.class);

                method.invoke(_forecastingFormulaRemoteModel,
                    forecastingFormulaSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormula() {
        return _formula;
    }

    @Override
    public void setFormula(String formula) {
        _formula = formula;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setFormula", String.class);

                method.invoke(_forecastingFormulaRemoteModel, formula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaNo() {
        return _formulaNo;
    }

    @Override
    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaNo", String.class);

                method.invoke(_forecastingFormulaRemoteModel, formulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_forecastingFormulaRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsActive() {
        return _isActive;
    }

    @Override
    public boolean isIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(boolean isActive) {
        _isActive = isActive;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", boolean.class);

                method.invoke(_forecastingFormulaRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaName() {
        return _formulaName;
    }

    @Override
    public void setFormulaName(String formulaName) {
        _formulaName = formulaName;

        if (_forecastingFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaName", String.class);

                method.invoke(_forecastingFormulaRemoteModel, formulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getForecastingFormulaRemoteModel() {
        return _forecastingFormulaRemoteModel;
    }

    public void setForecastingFormulaRemoteModel(
        BaseModel<?> forecastingFormulaRemoteModel) {
        _forecastingFormulaRemoteModel = forecastingFormulaRemoteModel;
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

        Class<?> remoteModelClass = _forecastingFormulaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_forecastingFormulaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ForecastingFormulaLocalServiceUtil.addForecastingFormula(this);
        } else {
            ForecastingFormulaLocalServiceUtil.updateForecastingFormula(this);
        }
    }

    @Override
    public ForecastingFormula toEscapedModel() {
        return (ForecastingFormula) ProxyUtil.newProxyInstance(ForecastingFormula.class.getClassLoader(),
            new Class[] { ForecastingFormula.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ForecastingFormulaClp clone = new ForecastingFormulaClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setFormulaType(getFormulaType());
        clone.setForecastingFormulaSid(getForecastingFormulaSid());
        clone.setFormula(getFormula());
        clone.setFormulaNo(getFormulaNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setIsActive(getIsActive());
        clone.setFormulaName(getFormulaName());

        return clone;
    }

    @Override
    public int compareTo(ForecastingFormula forecastingFormula) {
        int primaryKey = forecastingFormula.getPrimaryKey();

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

        if (!(obj instanceof ForecastingFormulaClp)) {
            return false;
        }

        ForecastingFormulaClp forecastingFormula = (ForecastingFormulaClp) obj;

        int primaryKey = forecastingFormula.getPrimaryKey();

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

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", forecastingFormulaSid=");
        sb.append(getForecastingFormulaSid());
        sb.append(", formula=");
        sb.append(getFormula());
        sb.append(", formulaNo=");
        sb.append(getFormulaNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", formulaName=");
        sb.append(getFormulaName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ForecastingFormula");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastingFormulaSid</column-name><column-value><![CDATA[");
        sb.append(getForecastingFormulaSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formula</column-name><column-value><![CDATA[");
        sb.append(getFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaNo</column-name><column-value><![CDATA[");
        sb.append(getFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaName</column-name><column-value><![CDATA[");
        sb.append(getFormulaName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
