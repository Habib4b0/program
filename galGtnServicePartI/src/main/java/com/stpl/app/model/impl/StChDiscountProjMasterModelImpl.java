package com.stpl.app.model.impl;

import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.model.StChDiscountProjMasterModel;
import com.stpl.app.service.persistence.StChDiscountProjMasterPK;

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
 * The base model implementation for the StChDiscountProjMaster service. Represents a row in the &quot;ST_CH_DISCOUNT_PROJ_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.StChDiscountProjMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StChDiscountProjMasterImpl}.
 * </p>
 *
 * @author
 * @see StChDiscountProjMasterImpl
 * @see com.stpl.app.model.StChDiscountProjMaster
 * @see com.stpl.app.model.StChDiscountProjMasterModel
 * @generated
 */
public class StChDiscountProjMasterModelImpl extends BaseModelImpl<StChDiscountProjMaster>
    implements StChDiscountProjMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a st ch discount proj master model instance should use the {@link com.stpl.app.model.StChDiscountProjMaster} interface instead.
     */
    public static final String TABLE_NAME = "ST_CH_DISCOUNT_PROJ_MASTER";
    public static final Object[][] TABLE_COLUMNS = {
            { "CHECK_RECORD", Types.BOOLEAN },
            { "SELECTED_PERIODS", Types.VARCHAR },
            { "LAST_MODIFIED_DATE", Types.TIMESTAMP },
            { "PROJECTION_DETAILS_SID", Types.INTEGER },
            { "PRICE_GROUP_TYPE", Types.VARCHAR },
            { "USER_ID", Types.INTEGER },
            { "NET_FLAG", Types.VARCHAR },
            { "PROJECTED_TYPE", Types.VARCHAR },
            { "BASELINE_PERIODS", Types.VARCHAR },
            { "SESSION_ID", Types.INTEGER },
            { "METHODOLOGY", Types.VARCHAR },
            { "RS_MODEL_SID", Types.INTEGER },
            { "DISCOUNT_TYPE", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table ST_CH_DISCOUNT_PROJ_MASTER (CHECK_RECORD BOOLEAN,SELECTED_PERIODS VARCHAR(75) null,LAST_MODIFIED_DATE DATE null,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,PRICE_GROUP_TYPE VARCHAR(75) null,USER_ID INTEGER not null IDENTITY,NET_FLAG VARCHAR(75) null,PROJECTED_TYPE VARCHAR(75) null,BASELINE_PERIODS VARCHAR(75) null,SESSION_ID INTEGER not null IDENTITY,METHODOLOGY VARCHAR(75) null,RS_MODEL_SID INTEGER not null IDENTITY,DISCOUNT_TYPE VARCHAR(75) null,primary key (PROJECTION_DETAILS_SID, USER_ID, SESSION_ID, RS_MODEL_SID))";
    public static final String TABLE_SQL_DROP = "drop table ST_CH_DISCOUNT_PROJ_MASTER";
    public static final String ORDER_BY_JPQL = " ORDER BY stChDiscountProjMaster.id.projectionDetailsSid ASC, stChDiscountProjMaster.id.userId ASC, stChDiscountProjMaster.id.sessionId ASC, stChDiscountProjMaster.id.rsModelSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY ST_CH_DISCOUNT_PROJ_MASTER.PROJECTION_DETAILS_SID ASC, ST_CH_DISCOUNT_PROJ_MASTER.USER_ID ASC, ST_CH_DISCOUNT_PROJ_MASTER.SESSION_ID ASC, ST_CH_DISCOUNT_PROJ_MASTER.RS_MODEL_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.StChDiscountProjMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.StChDiscountProjMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.StChDiscountProjMaster"));
    private static ClassLoader _classLoader = StChDiscountProjMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            StChDiscountProjMaster.class
        };
    private boolean _checkRecord;
    private String _selectedPeriods;
    private Date _lastModifiedDate;
    private int _projectionDetailsSid;
    private String _priceGroupType;
    private int _userId;
    private String _netFlag;
    private String _projectedType;
    private String _baselinePeriods;
    private int _sessionId;
    private String _methodology;
    private int _rsModelSid;
    private String _discountType;
    private StChDiscountProjMaster _escapedModel;

    public StChDiscountProjMasterModelImpl() {
    }

    @Override
    public StChDiscountProjMasterPK getPrimaryKey() {
        return new StChDiscountProjMasterPK(_projectionDetailsSid, _userId,
            _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKey(StChDiscountProjMasterPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
        setRsModelSid(primaryKey.rsModelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StChDiscountProjMasterPK(_projectionDetailsSid, _userId,
            _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StChDiscountProjMasterPK) primaryKeyObj);
    }

    @Override
    public Class<?> getModelClass() {
        return StChDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StChDiscountProjMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("userId", getUserId());
        attributes.put("netFlag", getNetFlag());
        attributes.put("projectedType", getProjectedType());
        attributes.put("baselinePeriods", getBaselinePeriods());
        attributes.put("sessionId", getSessionId());
        attributes.put("methodology", getMethodology());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("discountType", getDiscountType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String projectedType = (String) attributes.get("projectedType");

        if (projectedType != null) {
            setProjectedType(projectedType);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String discountType = (String) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    @Override
    public String getSelectedPeriods() {
        if (_selectedPeriods == null) {
            return StringPool.BLANK;
        } else {
            return _selectedPeriods;
        }
    }

    @Override
    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;
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
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    @Override
    public String getPriceGroupType() {
        if (_priceGroupType == null) {
            return StringPool.BLANK;
        } else {
            return _priceGroupType;
        }
    }

    @Override
    public void setPriceGroupType(String priceGroupType) {
        _priceGroupType = priceGroupType;
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
    public String getNetFlag() {
        if (_netFlag == null) {
            return StringPool.BLANK;
        } else {
            return _netFlag;
        }
    }

    @Override
    public void setNetFlag(String netFlag) {
        _netFlag = netFlag;
    }

    @Override
    public String getProjectedType() {
        if (_projectedType == null) {
            return StringPool.BLANK;
        } else {
            return _projectedType;
        }
    }

    @Override
    public void setProjectedType(String projectedType) {
        _projectedType = projectedType;
    }

    @Override
    public String getBaselinePeriods() {
        if (_baselinePeriods == null) {
            return StringPool.BLANK;
        } else {
            return _baselinePeriods;
        }
    }

    @Override
    public void setBaselinePeriods(String baselinePeriods) {
        _baselinePeriods = baselinePeriods;
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
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    @Override
    public String getDiscountType() {
        if (_discountType == null) {
            return StringPool.BLANK;
        } else {
            return _discountType;
        }
    }

    @Override
    public void setDiscountType(String discountType) {
        _discountType = discountType;
    }

    @Override
    public StChDiscountProjMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (StChDiscountProjMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        StChDiscountProjMasterImpl stChDiscountProjMasterImpl = new StChDiscountProjMasterImpl();

        stChDiscountProjMasterImpl.setCheckRecord(getCheckRecord());
        stChDiscountProjMasterImpl.setSelectedPeriods(getSelectedPeriods());
        stChDiscountProjMasterImpl.setLastModifiedDate(getLastModifiedDate());
        stChDiscountProjMasterImpl.setProjectionDetailsSid(getProjectionDetailsSid());
        stChDiscountProjMasterImpl.setPriceGroupType(getPriceGroupType());
        stChDiscountProjMasterImpl.setUserId(getUserId());
        stChDiscountProjMasterImpl.setNetFlag(getNetFlag());
        stChDiscountProjMasterImpl.setProjectedType(getProjectedType());
        stChDiscountProjMasterImpl.setBaselinePeriods(getBaselinePeriods());
        stChDiscountProjMasterImpl.setSessionId(getSessionId());
        stChDiscountProjMasterImpl.setMethodology(getMethodology());
        stChDiscountProjMasterImpl.setRsModelSid(getRsModelSid());
        stChDiscountProjMasterImpl.setDiscountType(getDiscountType());

        stChDiscountProjMasterImpl.resetOriginalValues();

        return stChDiscountProjMasterImpl;
    }

    @Override
    public int compareTo(StChDiscountProjMaster stChDiscountProjMaster) {
        StChDiscountProjMasterPK primaryKey = stChDiscountProjMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChDiscountProjMaster)) {
            return false;
        }

        StChDiscountProjMaster stChDiscountProjMaster = (StChDiscountProjMaster) obj;

        StChDiscountProjMasterPK primaryKey = stChDiscountProjMaster.getPrimaryKey();

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
    public CacheModel<StChDiscountProjMaster> toCacheModel() {
        StChDiscountProjMasterCacheModel stChDiscountProjMasterCacheModel = new StChDiscountProjMasterCacheModel();

        stChDiscountProjMasterCacheModel.checkRecord = getCheckRecord();

        stChDiscountProjMasterCacheModel.selectedPeriods = getSelectedPeriods();

        String selectedPeriods = stChDiscountProjMasterCacheModel.selectedPeriods;

        if ((selectedPeriods != null) && (selectedPeriods.length() == 0)) {
            stChDiscountProjMasterCacheModel.selectedPeriods = null;
        }

        Date lastModifiedDate = getLastModifiedDate();

        if (lastModifiedDate != null) {
            stChDiscountProjMasterCacheModel.lastModifiedDate = lastModifiedDate.getTime();
        } else {
            stChDiscountProjMasterCacheModel.lastModifiedDate = Long.MIN_VALUE;
        }

        stChDiscountProjMasterCacheModel.projectionDetailsSid = getProjectionDetailsSid();

        stChDiscountProjMasterCacheModel.priceGroupType = getPriceGroupType();

        String priceGroupType = stChDiscountProjMasterCacheModel.priceGroupType;

        if ((priceGroupType != null) && (priceGroupType.length() == 0)) {
            stChDiscountProjMasterCacheModel.priceGroupType = null;
        }

        stChDiscountProjMasterCacheModel.userId = getUserId();

        stChDiscountProjMasterCacheModel.netFlag = getNetFlag();

        String netFlag = stChDiscountProjMasterCacheModel.netFlag;

        if ((netFlag != null) && (netFlag.length() == 0)) {
            stChDiscountProjMasterCacheModel.netFlag = null;
        }

        stChDiscountProjMasterCacheModel.projectedType = getProjectedType();

        String projectedType = stChDiscountProjMasterCacheModel.projectedType;

        if ((projectedType != null) && (projectedType.length() == 0)) {
            stChDiscountProjMasterCacheModel.projectedType = null;
        }

        stChDiscountProjMasterCacheModel.baselinePeriods = getBaselinePeriods();

        String baselinePeriods = stChDiscountProjMasterCacheModel.baselinePeriods;

        if ((baselinePeriods != null) && (baselinePeriods.length() == 0)) {
            stChDiscountProjMasterCacheModel.baselinePeriods = null;
        }

        stChDiscountProjMasterCacheModel.sessionId = getSessionId();

        stChDiscountProjMasterCacheModel.methodology = getMethodology();

        String methodology = stChDiscountProjMasterCacheModel.methodology;

        if ((methodology != null) && (methodology.length() == 0)) {
            stChDiscountProjMasterCacheModel.methodology = null;
        }

        stChDiscountProjMasterCacheModel.rsModelSid = getRsModelSid();

        stChDiscountProjMasterCacheModel.discountType = getDiscountType();

        String discountType = stChDiscountProjMasterCacheModel.discountType;

        if ((discountType != null) && (discountType.length() == 0)) {
            stChDiscountProjMasterCacheModel.discountType = null;
        }

        return stChDiscountProjMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", selectedPeriods=");
        sb.append(getSelectedPeriods());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", priceGroupType=");
        sb.append(getPriceGroupType());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", netFlag=");
        sb.append(getNetFlag());
        sb.append(", projectedType=");
        sb.append(getProjectedType());
        sb.append(", baselinePeriods=");
        sb.append(getBaselinePeriods());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", discountType=");
        sb.append(getDiscountType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StChDiscountProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selectedPeriods</column-name><column-value><![CDATA[");
        sb.append(getSelectedPeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceGroupType</column-name><column-value><![CDATA[");
        sb.append(getPriceGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netFlag</column-name><column-value><![CDATA[");
        sb.append(getNetFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectedType</column-name><column-value><![CDATA[");
        sb.append(getProjectedType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselinePeriods</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountType</column-name><column-value><![CDATA[");
        sb.append(getDiscountType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
