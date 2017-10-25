package com.stpl.app.model.impl;

import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.model.MedicaidUraActualsModel;
import com.stpl.app.service.persistence.MedicaidUraActualsPK;

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
 * The base model implementation for the MedicaidUraActuals service. Represents a row in the &quot;MEDICAID_URA_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.MedicaidUraActualsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MedicaidUraActualsImpl}.
 * </p>
 *
 * @author
 * @see MedicaidUraActualsImpl
 * @see com.stpl.app.model.MedicaidUraActuals
 * @see com.stpl.app.model.MedicaidUraActualsModel
 * @generated
 */
public class MedicaidUraActualsModelImpl extends BaseModelImpl<MedicaidUraActuals>
    implements MedicaidUraActualsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a medicaid ura actuals model instance should use the {@link com.stpl.app.model.MedicaidUraActuals} interface instead.
     */
    public static final String TABLE_NAME = "MEDICAID_URA_ACTUALS";
    public static final Object[][] TABLE_COLUMNS = {
            { "PERIOD_SID", Types.INTEGER },
            { "PRICE_TYPE", Types.VARCHAR },
            { "ACTUAL_PRICE", Types.DOUBLE },
            { "NOTES", Types.VARCHAR },
            { "NA_PROJ_DETAILS_SID", Types.INTEGER },
            { "BASE_YEAR", Types.DOUBLE }
        };
    public static final String TABLE_SQL_CREATE = "create table MEDICAID_URA_ACTUALS (PERIOD_SID INTEGER not null IDENTITY,PRICE_TYPE VARCHAR(75) not null IDENTITY,ACTUAL_PRICE DOUBLE,NOTES VARCHAR(75) null,NA_PROJ_DETAILS_SID INTEGER not null IDENTITY,BASE_YEAR DOUBLE,primary key (PERIOD_SID, PRICE_TYPE, NA_PROJ_DETAILS_SID))";
    public static final String TABLE_SQL_DROP = "drop table MEDICAID_URA_ACTUALS";
    public static final String ORDER_BY_JPQL = " ORDER BY medicaidUraActuals.id.periodSid ASC, medicaidUraActuals.id.priceType ASC, medicaidUraActuals.id.naProjDetailsSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY MEDICAID_URA_ACTUALS.PERIOD_SID ASC, MEDICAID_URA_ACTUALS.PRICE_TYPE ASC, MEDICAID_URA_ACTUALS.NA_PROJ_DETAILS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.MedicaidUraActuals"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.MedicaidUraActuals"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.MedicaidUraActuals"));
    private static ClassLoader _classLoader = MedicaidUraActuals.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            MedicaidUraActuals.class
        };
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private double _baseYear;
    private MedicaidUraActuals _escapedModel;

    public MedicaidUraActualsModelImpl() {
    }

    @Override
    public MedicaidUraActualsPK getPrimaryKey() {
        return new MedicaidUraActualsPK(_periodSid, _priceType,
            _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(MedicaidUraActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new MedicaidUraActualsPK(_periodSid, _priceType,
            _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((MedicaidUraActualsPK) primaryKeyObj);
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidUraActuals.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidUraActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());
        attributes.put("baseYear", getBaseYear());

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

        Double baseYear = (Double) attributes.get("baseYear");

        if (baseYear != null) {
            setBaseYear(baseYear);
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
    public double getBaseYear() {
        return _baseYear;
    }

    @Override
    public void setBaseYear(double baseYear) {
        _baseYear = baseYear;
    }

    @Override
    public MedicaidUraActuals toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (MedicaidUraActuals) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        MedicaidUraActualsImpl medicaidUraActualsImpl = new MedicaidUraActualsImpl();

        medicaidUraActualsImpl.setPeriodSid(getPeriodSid());
        medicaidUraActualsImpl.setPriceType(getPriceType());
        medicaidUraActualsImpl.setActualPrice(getActualPrice());
        medicaidUraActualsImpl.setNotes(getNotes());
        medicaidUraActualsImpl.setNaProjDetailsSid(getNaProjDetailsSid());
        medicaidUraActualsImpl.setBaseYear(getBaseYear());

        medicaidUraActualsImpl.resetOriginalValues();

        return medicaidUraActualsImpl;
    }

    @Override
    public int compareTo(MedicaidUraActuals medicaidUraActuals) {
        MedicaidUraActualsPK primaryKey = medicaidUraActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidUraActuals)) {
            return false;
        }

        MedicaidUraActuals medicaidUraActuals = (MedicaidUraActuals) obj;

        MedicaidUraActualsPK primaryKey = medicaidUraActuals.getPrimaryKey();

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
    public CacheModel<MedicaidUraActuals> toCacheModel() {
        MedicaidUraActualsCacheModel medicaidUraActualsCacheModel = new MedicaidUraActualsCacheModel();

        medicaidUraActualsCacheModel.periodSid = getPeriodSid();

        medicaidUraActualsCacheModel.priceType = getPriceType();

        String priceType = medicaidUraActualsCacheModel.priceType;

        if ((priceType != null) && (priceType.length() == 0)) {
            medicaidUraActualsCacheModel.priceType = null;
        }

        medicaidUraActualsCacheModel.actualPrice = getActualPrice();

        medicaidUraActualsCacheModel.notes = getNotes();

        String notes = medicaidUraActualsCacheModel.notes;

        if ((notes != null) && (notes.length() == 0)) {
            medicaidUraActualsCacheModel.notes = null;
        }

        medicaidUraActualsCacheModel.naProjDetailsSid = getNaProjDetailsSid();

        medicaidUraActualsCacheModel.baseYear = getBaseYear();

        return medicaidUraActualsCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

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
        sb.append(", baseYear=");
        sb.append(getBaseYear());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MedicaidUraActuals");
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
        sb.append(
            "<column><column-name>baseYear</column-name><column-value><![CDATA[");
        sb.append(getBaseYear());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
