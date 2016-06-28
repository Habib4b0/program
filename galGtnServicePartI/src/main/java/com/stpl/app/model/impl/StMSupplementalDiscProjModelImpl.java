package com.stpl.app.model.impl;

import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.model.StMSupplementalDiscProjModel;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StMSupplementalDiscProj service. Represents a row in the &quot;ST_M_SUPPLEMENTAL_DISC_PROJ&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.StMSupplementalDiscProjModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StMSupplementalDiscProjImpl}.
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProjImpl
 * @see com.stpl.app.model.StMSupplementalDiscProj
 * @see com.stpl.app.model.StMSupplementalDiscProjModel
 * @generated
 */
public class StMSupplementalDiscProjModelImpl extends BaseModelImpl<StMSupplementalDiscProj>
    implements StMSupplementalDiscProjModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a st m supplemental disc proj model instance should use the {@link com.stpl.app.model.StMSupplementalDiscProj} interface instead.
     */
    public static final String TABLE_NAME = "ST_M_SUPPLEMENTAL_DISC_PROJ";
    public static final Object[][] TABLE_COLUMNS = {
            { "PROJECTION_RATE", Types.DOUBLE },
            { "USER_ID", Types.INTEGER },
            { "LAST_MODIFIED_DATE", Types.TIMESTAMP },
            { "PARITY_REFERENCE", Types.VARCHAR },
            { "PROJECTION_SALES", Types.DOUBLE },
            { "CONTRACT_PRICE", Types.DOUBLE },
            { "METHODOLOGY", Types.VARCHAR },
            { "PARITY", Types.BOOLEAN },
            { "PERIOD_SID", Types.INTEGER },
            { "DISCOUNT_RATE_1", Types.DOUBLE },
            { "PROJECTION_DETAILS_SID", Types.INTEGER },
            { "DISCOUNT_RATE_2", Types.DOUBLE },
            { "PARITY_DISCOUNT", Types.DOUBLE },
            { "SESSION_ID", Types.INTEGER },
            { "ACCESS", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table ST_M_SUPPLEMENTAL_DISC_PROJ (PROJECTION_RATE DOUBLE,USER_ID INTEGER not null IDENTITY,LAST_MODIFIED_DATE DATE null,PARITY_REFERENCE VARCHAR(75) null,PROJECTION_SALES DOUBLE,CONTRACT_PRICE DOUBLE,METHODOLOGY VARCHAR(75) null,PARITY BOOLEAN,PERIOD_SID INTEGER,DISCOUNT_RATE_1 DOUBLE,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,DISCOUNT_RATE_2 DOUBLE,PARITY_DISCOUNT DOUBLE,SESSION_ID INTEGER not null IDENTITY,ACCESS VARCHAR(75) null,primary key (USER_ID, PROJECTION_DETAILS_SID, SESSION_ID))";
    public static final String TABLE_SQL_DROP = "drop table ST_M_SUPPLEMENTAL_DISC_PROJ";
    public static final String ORDER_BY_JPQL = " ORDER BY stMSupplementalDiscProj.id.userId ASC, stMSupplementalDiscProj.id.projectionDetailsSid ASC, stMSupplementalDiscProj.id.sessionId ASC";
    public static final String ORDER_BY_SQL = " ORDER BY ST_M_SUPPLEMENTAL_DISC_PROJ.USER_ID ASC, ST_M_SUPPLEMENTAL_DISC_PROJ.PROJECTION_DETAILS_SID ASC, ST_M_SUPPLEMENTAL_DISC_PROJ.SESSION_ID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.StMSupplementalDiscProj"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.StMSupplementalDiscProj"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.StMSupplementalDiscProj"));
    private static ClassLoader _classLoader = StMSupplementalDiscProj.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            StMSupplementalDiscProj.class
        };
    private double _projectionRate;
    private int _userId;
    private Date _lastModifiedDate;
    private String _parityReference;
    private double _projectionSales;
    private double _contractPrice;
    private String _methodology;
    private boolean _parity;
    private int _periodSid;
    private double _discountRate1;
    private int _projectionDetailsSid;
    private double _discountRate2;
    private double _parityDiscount;
    private int _sessionId;
    private String _access;
    private StMSupplementalDiscProj _escapedModel;

    public StMSupplementalDiscProjModelImpl() {
    }

    @Override
    public StMSupplementalDiscProjPK getPrimaryKey() {
        return new StMSupplementalDiscProjPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StMSupplementalDiscProjPK primaryKey) {
        setUserId(primaryKey.userId);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StMSupplementalDiscProjPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StMSupplementalDiscProjPK) primaryKeyObj);
    }

    @Override
    public Class<?> getModelClass() {
        return StMSupplementalDiscProj.class;
    }

    @Override
    public String getModelClassName() {
        return StMSupplementalDiscProj.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionRate", getProjectionRate());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parityReference", getParityReference());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("methodology", getMethodology());
        attributes.put("parity", getParity());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("discountRate1", getDiscountRate1());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("discountRate2", getDiscountRate2());
        attributes.put("parityDiscount", getParityDiscount());
        attributes.put("sessionId", getSessionId());
        attributes.put("access", getAccess());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String parityReference = (String) attributes.get("parityReference");

        if (parityReference != null) {
            setParityReference(parityReference);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Boolean parity = (Boolean) attributes.get("parity");

        if (parity != null) {
            setParity(parity);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double discountRate1 = (Double) attributes.get("discountRate1");

        if (discountRate1 != null) {
            setDiscountRate1(discountRate1);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double discountRate2 = (Double) attributes.get("discountRate2");

        if (discountRate2 != null) {
            setDiscountRate2(discountRate2);
        }

        Double parityDiscount = (Double) attributes.get("parityDiscount");

        if (parityDiscount != null) {
            setParityDiscount(parityDiscount);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String access = (String) attributes.get("access");

        if (access != null) {
            setAccess(access);
        }
    }

    @Override
    public double getProjectionRate() {
        return _projectionRate;
    }

    @Override
    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String getParityReference() {
        if (_parityReference == null) {
            return StringPool.BLANK;
        } else {
            return _parityReference;
        }
    }

    @Override
    public void setParityReference(String parityReference) {
        _parityReference = parityReference;
    }

    @Override
    public double getProjectionSales() {
        return _projectionSales;
    }

    @Override
    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;
    }

    @Override
    public double getContractPrice() {
        return _contractPrice;
    }

    @Override
    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;
    }

    @Override
    public String getMethodology() {
        if (_methodology == null) {
            return StringPool.BLANK;
        } else {
            return _methodology;
        }
    }

    @Override
    public void setMethodology(String methodology) {
        _methodology = methodology;
    }

    @Override
    public boolean getParity() {
        return _parity;
    }

    @Override
    public boolean isParity() {
        return _parity;
    }

    @Override
    public void setParity(boolean parity) {
        _parity = parity;
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
    public double getDiscountRate1() {
        return _discountRate1;
    }

    @Override
    public void setDiscountRate1(double discountRate1) {
        _discountRate1 = discountRate1;
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
    public double getDiscountRate2() {
        return _discountRate2;
    }

    @Override
    public void setDiscountRate2(double discountRate2) {
        _discountRate2 = discountRate2;
    }

    @Override
    public double getParityDiscount() {
        return _parityDiscount;
    }

    @Override
    public void setParityDiscount(double parityDiscount) {
        _parityDiscount = parityDiscount;
    }

    @Override
    public int getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    @Override
    public String getAccess() {
        if (_access == null) {
            return StringPool.BLANK;
        } else {
            return _access;
        }
    }

    @Override
    public void setAccess(String access) {
        _access = access;
    }

    @Override
    public StMSupplementalDiscProj toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (StMSupplementalDiscProj) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        StMSupplementalDiscProjImpl stMSupplementalDiscProjImpl = new StMSupplementalDiscProjImpl();

        stMSupplementalDiscProjImpl.setProjectionRate(getProjectionRate());
        stMSupplementalDiscProjImpl.setUserId(getUserId());
        stMSupplementalDiscProjImpl.setLastModifiedDate(getLastModifiedDate());
        stMSupplementalDiscProjImpl.setParityReference(getParityReference());
        stMSupplementalDiscProjImpl.setProjectionSales(getProjectionSales());
        stMSupplementalDiscProjImpl.setContractPrice(getContractPrice());
        stMSupplementalDiscProjImpl.setMethodology(getMethodology());
        stMSupplementalDiscProjImpl.setParity(getParity());
        stMSupplementalDiscProjImpl.setPeriodSid(getPeriodSid());
        stMSupplementalDiscProjImpl.setDiscountRate1(getDiscountRate1());
        stMSupplementalDiscProjImpl.setProjectionDetailsSid(getProjectionDetailsSid());
        stMSupplementalDiscProjImpl.setDiscountRate2(getDiscountRate2());
        stMSupplementalDiscProjImpl.setParityDiscount(getParityDiscount());
        stMSupplementalDiscProjImpl.setSessionId(getSessionId());
        stMSupplementalDiscProjImpl.setAccess(getAccess());

        stMSupplementalDiscProjImpl.resetOriginalValues();

        return stMSupplementalDiscProjImpl;
    }

    @Override
    public int compareTo(StMSupplementalDiscProj stMSupplementalDiscProj) {
        StMSupplementalDiscProjPK primaryKey = stMSupplementalDiscProj.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMSupplementalDiscProj)) {
            return false;
        }

        StMSupplementalDiscProj stMSupplementalDiscProj = (StMSupplementalDiscProj) obj;

        StMSupplementalDiscProjPK primaryKey = stMSupplementalDiscProj.getPrimaryKey();

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
    public CacheModel<StMSupplementalDiscProj> toCacheModel() {
        StMSupplementalDiscProjCacheModel stMSupplementalDiscProjCacheModel = new StMSupplementalDiscProjCacheModel();

        stMSupplementalDiscProjCacheModel.projectionRate = getProjectionRate();

        stMSupplementalDiscProjCacheModel.userId = getUserId();

        Date lastModifiedDate = getLastModifiedDate();

        if (lastModifiedDate != null) {
            stMSupplementalDiscProjCacheModel.lastModifiedDate = lastModifiedDate.getTime();
        } else {
            stMSupplementalDiscProjCacheModel.lastModifiedDate = Long.MIN_VALUE;
        }

        stMSupplementalDiscProjCacheModel.parityReference = getParityReference();

        String parityReference = stMSupplementalDiscProjCacheModel.parityReference;

        if ((parityReference != null) && (parityReference.length() == 0)) {
            stMSupplementalDiscProjCacheModel.parityReference = null;
        }

        stMSupplementalDiscProjCacheModel.projectionSales = getProjectionSales();

        stMSupplementalDiscProjCacheModel.contractPrice = getContractPrice();

        stMSupplementalDiscProjCacheModel.methodology = getMethodology();

        String methodology = stMSupplementalDiscProjCacheModel.methodology;

        if ((methodology != null) && (methodology.length() == 0)) {
            stMSupplementalDiscProjCacheModel.methodology = null;
        }

        stMSupplementalDiscProjCacheModel.parity = getParity();

        stMSupplementalDiscProjCacheModel.periodSid = getPeriodSid();

        stMSupplementalDiscProjCacheModel.discountRate1 = getDiscountRate1();

        stMSupplementalDiscProjCacheModel.projectionDetailsSid = getProjectionDetailsSid();

        stMSupplementalDiscProjCacheModel.discountRate2 = getDiscountRate2();

        stMSupplementalDiscProjCacheModel.parityDiscount = getParityDiscount();

        stMSupplementalDiscProjCacheModel.sessionId = getSessionId();

        stMSupplementalDiscProjCacheModel.access = getAccess();

        String access = stMSupplementalDiscProjCacheModel.access;

        if ((access != null) && (access.length() == 0)) {
            stMSupplementalDiscProjCacheModel.access = null;
        }

        return stMSupplementalDiscProjCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", parityReference=");
        sb.append(getParityReference());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", parity=");
        sb.append(getParity());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", discountRate1=");
        sb.append(getDiscountRate1());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", discountRate2=");
        sb.append(getDiscountRate2());
        sb.append(", parityDiscount=");
        sb.append(getParityDiscount());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", access=");
        sb.append(getAccess());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StMSupplementalDiscProj");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
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
            "<column><column-name>parityReference</column-name><column-value><![CDATA[");
        sb.append(getParityReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice</column-name><column-value><![CDATA[");
        sb.append(getContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parity</column-name><column-value><![CDATA[");
        sb.append(getParity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate1</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate2</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parityDiscount</column-name><column-value><![CDATA[");
        sb.append(getParityDiscount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>access</column-name><column-value><![CDATA[");
        sb.append(getAccess());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
