package com.stpl.app.model.impl;

import com.stpl.app.model.FcpActuals;
import com.stpl.app.model.FcpActualsModel;
import com.stpl.app.service.persistence.FcpActualsPK;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the FcpActuals service. Represents a row in the &quot;FCP_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.FcpActualsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FcpActualsImpl}.
 * </p>
 *
 * @author
 * @see FcpActualsImpl
 * @see com.stpl.app.model.FcpActuals
 * @see com.stpl.app.model.FcpActualsModel
 * @generated
 */
public class FcpActualsModelImpl extends BaseModelImpl<FcpActuals>
    implements FcpActualsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a fcp actuals model instance should use the {@link com.stpl.app.model.FcpActuals} interface instead.
     */
    public static final String TABLE_NAME = "FCP_ACTUALS";
    public static final Object[][] TABLE_COLUMNS = {
            { "PERIOD_SID", Types.INTEGER },
            { "PRICE_TYPE", Types.VARCHAR },
            { "ACTUAL_PRICE", Types.DOUBLE },
            { "NOTES", Types.VARCHAR },
            { "NA_PROJ_DETAILS_SID", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table FCP_ACTUALS (PERIOD_SID INTEGER not null IDENTITY,PRICE_TYPE VARCHAR(75) not null IDENTITY,ACTUAL_PRICE DOUBLE,NOTES VARCHAR(75) null,NA_PROJ_DETAILS_SID INTEGER not null IDENTITY,primary key (PERIOD_SID, PRICE_TYPE, NA_PROJ_DETAILS_SID))";
    public static final String TABLE_SQL_DROP = "drop table FCP_ACTUALS";
    public static final String ORDER_BY_JPQL = " ORDER BY fcpActuals.id.periodSid ASC, fcpActuals.id.priceType ASC, fcpActuals.id.naProjDetailsSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY FCP_ACTUALS.PERIOD_SID ASC, FCP_ACTUALS.PRICE_TYPE ASC, FCP_ACTUALS.NA_PROJ_DETAILS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.FcpActuals"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.FcpActuals"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.FcpActuals"));
    private static ClassLoader _classLoader = FcpActuals.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            FcpActuals.class
        };
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private FcpActuals _escapedModel;

    public FcpActualsModelImpl() {
    }

    @Override
    public FcpActualsPK getPrimaryKey() {
        return new FcpActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(FcpActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new FcpActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((FcpActualsPK) primaryKeyObj);
    }

    @Override
    public Class<?> getModelClass() {
        return FcpActuals.class;
    }

    @Override
    public String getModelClassName() {
        return FcpActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
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
    public String getPriceType() {
        if (_priceType == null) {
            return StringPool.BLANK;
        } else {
            return _priceType;
        }
    }

    @Override
    public void setPriceType(String priceType) {
        _priceType = priceType;
    }

    @Override
    public double getActualPrice() {
        return _actualPrice;
    }

    @Override
    public void setActualPrice(double actualPrice) {
        _actualPrice = actualPrice;
    }

    @Override
    public String getNotes() {
        if (_notes == null) {
            return StringPool.BLANK;
        } else {
            return _notes;
        }
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;
    }

    @Override
    public int getNaProjDetailsSid() {
        return _naProjDetailsSid;
    }

    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetailsSid = naProjDetailsSid;
    }

    @Override
    public FcpActuals toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (FcpActuals) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        FcpActualsImpl fcpActualsImpl = new FcpActualsImpl();

        fcpActualsImpl.setPeriodSid(getPeriodSid());
        fcpActualsImpl.setPriceType(getPriceType());
        fcpActualsImpl.setActualPrice(getActualPrice());
        fcpActualsImpl.setNotes(getNotes());
        fcpActualsImpl.setNaProjDetailsSid(getNaProjDetailsSid());

        fcpActualsImpl.resetOriginalValues();

        return fcpActualsImpl;
    }

    @Override
    public int compareTo(FcpActuals fcpActuals) {
        FcpActualsPK primaryKey = fcpActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FcpActuals)) {
            return false;
        }

        FcpActuals fcpActuals = (FcpActuals) obj;

        FcpActualsPK primaryKey = fcpActuals.getPrimaryKey();

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
    public CacheModel<FcpActuals> toCacheModel() {
        FcpActualsCacheModel fcpActualsCacheModel = new FcpActualsCacheModel();

        fcpActualsCacheModel.periodSid = getPeriodSid();

        fcpActualsCacheModel.priceType = getPriceType();

        String priceType = fcpActualsCacheModel.priceType;

        if ((priceType != null) && (priceType.length() == 0)) {
            fcpActualsCacheModel.priceType = null;
        }

        fcpActualsCacheModel.actualPrice = getActualPrice();

        fcpActualsCacheModel.notes = getNotes();

        String notes = fcpActualsCacheModel.notes;

        if ((notes != null) && (notes.length() == 0)) {
            fcpActualsCacheModel.notes = null;
        }

        fcpActualsCacheModel.naProjDetailsSid = getNaProjDetailsSid();

        return fcpActualsCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", actualPrice=");
        sb.append(getActualPrice());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FcpActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPrice</column-name><column-value><![CDATA[");
        sb.append(getActualPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
