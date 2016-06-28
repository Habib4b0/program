package com.stpl.app.model.impl;

import com.stpl.app.model.NmActualPpa;
import com.stpl.app.model.NmActualPpaModel;
import com.stpl.app.service.persistence.NmActualPpaPK;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the NmActualPpa service. Represents a row in the &quot;NM_ACTUAL_PPA&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.NmActualPpaModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NmActualPpaImpl}.
 * </p>
 *
 * @author
 * @see NmActualPpaImpl
 * @see com.stpl.app.model.NmActualPpa
 * @see com.stpl.app.model.NmActualPpaModel
 * @generated
 */
public class NmActualPpaModelImpl extends BaseModelImpl<NmActualPpa>
    implements NmActualPpaModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a nm actual ppa model instance should use the {@link com.stpl.app.model.NmActualPpa} interface instead.
     */
    public static final String TABLE_NAME = "NM_ACTUAL_PPA";
    public static final Object[][] TABLE_COLUMNS = {
            { "ACTUAL_RATE", Types.DOUBLE },
            { "PERIOD_SID", Types.INTEGER },
            { "ACTUAL_PROJ_DISCOUNT_DOLLAR", Types.DOUBLE },
            { "ACTUAL_PROJECTION_SALES", Types.DOUBLE },
            { "PROJECTION_DETAILS_SID", Types.INTEGER },
            { "ACTUAL_PROJECTION_RATE", Types.DOUBLE },
            { "ACTUAL_PROJ_DISCOUNT_UNITS", Types.DOUBLE },
            { "ACTUAL_DISCOUNT_DOLLAR", Types.DOUBLE },
            { "ACTUAL_DISCOUNT_UNITS", Types.DOUBLE },
            { "ACTUAL_SALES", Types.DOUBLE }
        };
    public static final String TABLE_SQL_CREATE = "create table NM_ACTUAL_PPA (ACTUAL_RATE DOUBLE,PERIOD_SID INTEGER not null IDENTITY,ACTUAL_PROJ_DISCOUNT_DOLLAR DOUBLE,ACTUAL_PROJECTION_SALES DOUBLE,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,ACTUAL_PROJECTION_RATE DOUBLE,ACTUAL_PROJ_DISCOUNT_UNITS DOUBLE,ACTUAL_DISCOUNT_DOLLAR DOUBLE,ACTUAL_DISCOUNT_UNITS DOUBLE,ACTUAL_SALES DOUBLE,primary key (PERIOD_SID, PROJECTION_DETAILS_SID))";
    public static final String TABLE_SQL_DROP = "drop table NM_ACTUAL_PPA";
    public static final String ORDER_BY_JPQL = " ORDER BY nmActualPpa.id.periodSid ASC, nmActualPpa.id.projectionDetailsSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY NM_ACTUAL_PPA.PERIOD_SID ASC, NM_ACTUAL_PPA.PROJECTION_DETAILS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.NmActualPpa"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.NmActualPpa"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.NmActualPpa"));
    private static ClassLoader _classLoader = NmActualPpa.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            NmActualPpa.class
        };
    private double _actualRate;
    private int _periodSid;
    private double _actualProjDiscountDollar;
    private double _actualProjectionSales;
    private int _projectionDetailsSid;
    private double _actualProjectionRate;
    private double _actualProjDiscountUnits;
    private double _actualDiscountDollar;
    private double _actualDiscountUnits;
    private double _actualSales;
    private NmActualPpa _escapedModel;

    public NmActualPpaModelImpl() {
    }

    @Override
    public NmActualPpaPK getPrimaryKey() {
        return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(NmActualPpaPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NmActualPpaPK) primaryKeyObj);
    }

    @Override
    public Class<?> getModelClass() {
        return NmActualPpa.class;
    }

    @Override
    public String getModelClassName() {
        return NmActualPpa.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
        attributes.put("actualDiscountDollar", getActualDiscountDollar());
        attributes.put("actualDiscountUnits", getActualDiscountUnits());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualProjDiscountDollar = (Double) attributes.get(
                "actualProjDiscountDollar");

        if (actualProjDiscountDollar != null) {
            setActualProjDiscountDollar(actualProjDiscountDollar);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Double actualProjDiscountUnits = (Double) attributes.get(
                "actualProjDiscountUnits");

        if (actualProjDiscountUnits != null) {
            setActualProjDiscountUnits(actualProjDiscountUnits);
        }

        Double actualDiscountDollar = (Double) attributes.get(
                "actualDiscountDollar");

        if (actualDiscountDollar != null) {
            setActualDiscountDollar(actualDiscountDollar);
        }

        Double actualDiscountUnits = (Double) attributes.get(
                "actualDiscountUnits");

        if (actualDiscountUnits != null) {
            setActualDiscountUnits(actualDiscountUnits);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    @Override
    public double getActualRate() {
        return _actualRate;
    }

    @Override
    public void setActualRate(double actualRate) {
        _actualRate = actualRate;
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    @Override
    public double getActualProjDiscountDollar() {
        return _actualProjDiscountDollar;
    }

    @Override
    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _actualProjDiscountDollar = actualProjDiscountDollar;
    }

    @Override
    public double getActualProjectionSales() {
        return _actualProjectionSales;
    }

    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _actualProjectionSales = actualProjectionSales;
    }

    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    @Override
    public double getActualProjectionRate() {
        return _actualProjectionRate;
    }

    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _actualProjectionRate = actualProjectionRate;
    }

    @Override
    public double getActualProjDiscountUnits() {
        return _actualProjDiscountUnits;
    }

    @Override
    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _actualProjDiscountUnits = actualProjDiscountUnits;
    }

    @Override
    public double getActualDiscountDollar() {
        return _actualDiscountDollar;
    }

    @Override
    public void setActualDiscountDollar(double actualDiscountDollar) {
        _actualDiscountDollar = actualDiscountDollar;
    }

    @Override
    public double getActualDiscountUnits() {
        return _actualDiscountUnits;
    }

    @Override
    public void setActualDiscountUnits(double actualDiscountUnits) {
        _actualDiscountUnits = actualDiscountUnits;
    }

    @Override
    public double getActualSales() {
        return _actualSales;
    }

    @Override
    public void setActualSales(double actualSales) {
        _actualSales = actualSales;
    }

    @Override
    public NmActualPpa toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (NmActualPpa) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

        nmActualPpaImpl.setActualRate(getActualRate());
        nmActualPpaImpl.setPeriodSid(getPeriodSid());
        nmActualPpaImpl.setActualProjDiscountDollar(getActualProjDiscountDollar());
        nmActualPpaImpl.setActualProjectionSales(getActualProjectionSales());
        nmActualPpaImpl.setProjectionDetailsSid(getProjectionDetailsSid());
        nmActualPpaImpl.setActualProjectionRate(getActualProjectionRate());
        nmActualPpaImpl.setActualProjDiscountUnits(getActualProjDiscountUnits());
        nmActualPpaImpl.setActualDiscountDollar(getActualDiscountDollar());
        nmActualPpaImpl.setActualDiscountUnits(getActualDiscountUnits());
        nmActualPpaImpl.setActualSales(getActualSales());

        nmActualPpaImpl.resetOriginalValues();

        return nmActualPpaImpl;
    }

    @Override
    public int compareTo(NmActualPpa nmActualPpa) {
        NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmActualPpa)) {
            return false;
        }

        NmActualPpa nmActualPpa = (NmActualPpa) obj;

        NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

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
    public void resetOriginalValues() {
    }

    @Override
    public CacheModel<NmActualPpa> toCacheModel() {
        NmActualPpaCacheModel nmActualPpaCacheModel = new NmActualPpaCacheModel();

        nmActualPpaCacheModel.actualRate = getActualRate();

        nmActualPpaCacheModel.periodSid = getPeriodSid();

        nmActualPpaCacheModel.actualProjDiscountDollar = getActualProjDiscountDollar();

        nmActualPpaCacheModel.actualProjectionSales = getActualProjectionSales();

        nmActualPpaCacheModel.projectionDetailsSid = getProjectionDetailsSid();

        nmActualPpaCacheModel.actualProjectionRate = getActualProjectionRate();

        nmActualPpaCacheModel.actualProjDiscountUnits = getActualProjDiscountUnits();

        nmActualPpaCacheModel.actualDiscountDollar = getActualDiscountDollar();

        nmActualPpaCacheModel.actualDiscountUnits = getActualDiscountUnits();

        nmActualPpaCacheModel.actualSales = getActualSales();

        return nmActualPpaCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{actualRate=");
        sb.append(getActualRate());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", actualProjDiscountDollar=");
        sb.append(getActualProjDiscountDollar());
        sb.append(", actualProjectionSales=");
        sb.append(getActualProjectionSales());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", actualProjectionRate=");
        sb.append(getActualProjectionRate());
        sb.append(", actualProjDiscountUnits=");
        sb.append(getActualProjDiscountUnits());
        sb.append(", actualDiscountDollar=");
        sb.append(getActualDiscountDollar());
        sb.append(", actualDiscountUnits=");
        sb.append(getActualDiscountUnits());
        sb.append(", actualSales=");
        sb.append(getActualSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmActualPpa");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionSales</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionRate</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
