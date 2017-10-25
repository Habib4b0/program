package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchForecastingMasterException;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.impl.ForecastingMasterImpl;
import com.stpl.app.model.impl.ForecastingMasterModelImpl;
import com.stpl.app.service.persistence.ForecastingMasterPersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.QueryPos;
import com.stpl.portal.kernel.dao.orm.QueryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.log.Log;
import com.stpl.portal.kernel.log.LogFactoryUtil;
import com.stpl.portal.kernel.util.CalendarUtil;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.InstanceFactory;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.PropsKeys;
import com.stpl.portal.kernel.util.PropsUtil;
import com.stpl.portal.kernel.util.SetUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.kernel.util.StringUtil;
import com.stpl.portal.kernel.util.UnmodifiableList;
import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the forecasting master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingMasterPersistence
 * @see ForecastingMasterUtil
 * @generated
 */
public class ForecastingMasterPersistenceImpl extends BasePersistenceImpl<ForecastingMaster>
    implements ForecastingMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ForecastingMasterUtil} to access the forecasting master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ForecastingMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTYEAR =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByForecastYear",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTYEAR =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByForecastYear",
            new String[] { String.class.getName() },
            ForecastingMasterModelImpl.FORECASTYEAR_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FORECASTYEAR = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByForecastYear",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_1 = "forecastingMaster.forecastYear IS NULL";
    private static final String _FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_2 = "forecastingMaster.forecastYear = ?";
    private static final String _FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_3 = "(forecastingMaster.forecastYear IS NULL OR forecastingMaster.forecastYear = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTMONTH =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByForecastMonth",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTMONTH =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByForecastMonth",
            new String[] { String.class.getName() },
            ForecastingMasterModelImpl.FORECASTMONTH_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FORECASTMONTH = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByForecastMonth",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_1 = "forecastingMaster.forecastMonth IS NULL";
    private static final String _FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_2 = "forecastingMaster.forecastMonth = ?";
    private static final String _FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_3 = "(forecastingMaster.forecastMonth IS NULL OR forecastingMaster.forecastMonth = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRY = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCountry",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRY =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCountry",
            new String[] { String.class.getName() },
            ForecastingMasterModelImpl.COUNTRY_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COUNTRY = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCountry",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COUNTRY_COUNTRY_1 = "forecastingMaster.country IS NULL";
    private static final String _FINDER_COLUMN_COUNTRY_COUNTRY_2 = "forecastingMaster.country = ?";
    private static final String _FINDER_COLUMN_COUNTRY_COUNTRY_3 = "(forecastingMaster.country IS NULL OR forecastingMaster.country = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NDC = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNdc",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNdc",
            new String[] { String.class.getName() },
            ForecastingMasterModelImpl.NDC_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NDC = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNdc",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_NDC_NDC_1 = "forecastingMaster.ndc IS NULL";
    private static final String _FINDER_COLUMN_NDC_NDC_2 = "forecastingMaster.ndc = ?";
    private static final String _FINDER_COLUMN_NDC_NDC_3 = "(forecastingMaster.ndc IS NULL OR forecastingMaster.ndc = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTSTARTDATE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByForecastStartDate",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTSTARTDATE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByForecastStartDate", new String[] { Date.class.getName() },
            ForecastingMasterModelImpl.FORECASTSTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FORECASTSTARTDATE = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByForecastStartDate", new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_1 =
        "forecastingMaster.forecastStartDate IS NULL";
    private static final String _FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_2 =
        "forecastingMaster.forecastStartDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDDATE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreatedDate",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDDATE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreatedDate",
            new String[] { Date.class.getName() },
            ForecastingMasterModelImpl.CREATEDDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CREATEDDATE = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreatedDate",
            new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_CREATEDDATE_CREATEDDATE_1 = "forecastingMaster.createdDate IS NULL";
    private static final String _FINDER_COLUMN_CREATEDDATE_CREATEDDATE_2 = "forecastingMaster.createdDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByForecastingSalesUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), Date.class.getName(),
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE =
        new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByForecastingSalesUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), Date.class.getName(),
                Date.class.getName()
            },
            ForecastingMasterModelImpl.FORECASTYEAR_COLUMN_BITMASK |
            ForecastingMasterModelImpl.FORECASTMONTH_COLUMN_BITMASK |
            ForecastingMasterModelImpl.COUNTRY_COLUMN_BITMASK |
            ForecastingMasterModelImpl.FORECASTSTARTDATE_COLUMN_BITMASK |
            ForecastingMasterModelImpl.CREATEDDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FORECASTINGSALESUNIQUE = new FinderPath(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByForecastingSalesUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), Date.class.getName(),
                Date.class.getName()
            });
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_1 =
        "forecastingMaster.forecastYear IS NULL AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_2 =
        "forecastingMaster.forecastYear = ? AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_3 =
        "(forecastingMaster.forecastYear IS NULL OR forecastingMaster.forecastYear = '') AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_1 =
        "forecastingMaster.forecastMonth IS NULL AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_2 =
        "forecastingMaster.forecastMonth = ? AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_3 =
        "(forecastingMaster.forecastMonth IS NULL OR forecastingMaster.forecastMonth = '') AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_1 = "forecastingMaster.country IS NULL AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_2 = "forecastingMaster.country = ? AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_3 = "(forecastingMaster.country IS NULL OR forecastingMaster.country = '') AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_1 =
        "forecastingMaster.forecastStartDate IS NULL AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_2 =
        "forecastingMaster.forecastStartDate = ? AND ";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_1 =
        "forecastingMaster.createdDate IS NULL";
    private static final String _FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_2 =
        "forecastingMaster.createdDate = ?";
    private static final String _SQL_SELECT_FORECASTINGMASTER = "SELECT forecastingMaster FROM ForecastingMaster forecastingMaster";
    private static final String _SQL_SELECT_FORECASTINGMASTER_WHERE = "SELECT forecastingMaster FROM ForecastingMaster forecastingMaster WHERE ";
    private static final String _SQL_COUNT_FORECASTINGMASTER = "SELECT COUNT(forecastingMaster) FROM ForecastingMaster forecastingMaster";
    private static final String _SQL_COUNT_FORECASTINGMASTER_WHERE = "SELECT COUNT(forecastingMaster) FROM ForecastingMaster forecastingMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "forecastingMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastingMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ForecastingMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ForecastingMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastValueType", "modifiedBy", "createdDate",
                "percentageEstimate", "actualSalesPercentageMonth", "ndc",
                "batchId", "forecastVer", "country", "product",
                "forecastStartDate", "forecastedSalesPercentage", "units",
                "dollars", "forecastMonth", "createdBy", "segment", "price",
                "actualSalesPercentage", "forecastYear", "forecastName",
                "forecastDate", "modifiedDate", "brand", "forecastValue",
                "percentageEstimateYear", "recordLockStatus", "source",
                "forecastMasterSid", "forecastedSalesPercentMonth",
                "inboundStatus"
            });
    private static ForecastingMaster _nullForecastingMaster = new ForecastingMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ForecastingMaster> toCacheModel() {
                return _nullForecastingMasterCacheModel;
            }
        };

    private static CacheModel<ForecastingMaster> _nullForecastingMasterCacheModel =
        new CacheModel<ForecastingMaster>() {
            @Override
            public ForecastingMaster toEntityModel() {
                return _nullForecastingMaster;
            }
        };

    public ForecastingMasterPersistenceImpl() {
        setModelClass(ForecastingMaster.class);
    }

    /**
     * Returns all the forecasting masters where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastYear(String forecastYear)
        throws SystemException {
        return findByForecastYear(forecastYear, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where forecastYear = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastYear the forecast year
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastYear(String forecastYear,
        int start, int end) throws SystemException {
        return findByForecastYear(forecastYear, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where forecastYear = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastYear the forecast year
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastYear(String forecastYear,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTYEAR;
            finderArgs = new Object[] { forecastYear };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTYEAR;
            finderArgs = new Object[] {
                    forecastYear,
                    
                    start, end, orderByComparator
                };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(forecastYear,
                            forecastingMaster.getForecastYear())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindForecastYear = false;

            if (forecastYear == null) {
                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_1);
            } else if (forecastYear.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_3);
            } else {
                bindForecastYear = true;

                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastYear) {
                    qPos.add(forecastYear);
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastYear_First(String forecastYear,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastYear_First(forecastYear,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastYear=");
        msg.append(forecastYear);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastYear_First(String forecastYear,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByForecastYear(forecastYear, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastYear_Last(String forecastYear,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastYear_Last(forecastYear,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastYear=");
        msg.append(forecastYear);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastYear_Last(String forecastYear,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByForecastYear(forecastYear);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByForecastYear(forecastYear,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param forecastYear the forecast year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByForecastYear_PrevAndNext(
        int forecastMasterSid, String forecastYear,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByForecastYear_PrevAndNext(session,
                    forecastingMaster, forecastYear, orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByForecastYear_PrevAndNext(session,
                    forecastingMaster, forecastYear, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByForecastYear_PrevAndNext(Session session,
        ForecastingMaster forecastingMaster, String forecastYear,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindForecastYear = false;

        if (forecastYear == null) {
            query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_1);
        } else if (forecastYear.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_3);
        } else {
            bindForecastYear = true;

            query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindForecastYear) {
            qPos.add(forecastYear);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where forecastYear = &#63; from the database.
     *
     * @param forecastYear the forecast year
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByForecastYear(String forecastYear)
        throws SystemException {
        for (ForecastingMaster forecastingMaster : findByForecastYear(
                forecastYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where forecastYear = &#63;.
     *
     * @param forecastYear the forecast year
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByForecastYear(String forecastYear)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FORECASTYEAR;

        Object[] finderArgs = new Object[] { forecastYear };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindForecastYear = false;

            if (forecastYear == null) {
                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_1);
            } else if (forecastYear.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_3);
            } else {
                bindForecastYear = true;

                query.append(_FINDER_COLUMN_FORECASTYEAR_FORECASTYEAR_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastYear) {
                    qPos.add(forecastYear);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastMonth(String forecastMonth)
        throws SystemException {
        return findByForecastMonth(forecastMonth, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where forecastMonth = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastMonth the forecast month
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastMonth(String forecastMonth,
        int start, int end) throws SystemException {
        return findByForecastMonth(forecastMonth, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where forecastMonth = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastMonth the forecast month
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastMonth(String forecastMonth,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTMONTH;
            finderArgs = new Object[] { forecastMonth };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTMONTH;
            finderArgs = new Object[] {
                    forecastMonth,
                    
                    start, end, orderByComparator
                };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(forecastMonth,
                            forecastingMaster.getForecastMonth())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindForecastMonth = false;

            if (forecastMonth == null) {
                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_1);
            } else if (forecastMonth.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_3);
            } else {
                bindForecastMonth = true;

                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastMonth) {
                    qPos.add(forecastMonth);
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastMonth_First(String forecastMonth,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastMonth_First(forecastMonth,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastMonth=");
        msg.append(forecastMonth);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastMonth_First(String forecastMonth,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByForecastMonth(forecastMonth, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastMonth_Last(String forecastMonth,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastMonth_Last(forecastMonth,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastMonth=");
        msg.append(forecastMonth);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastMonth_Last(String forecastMonth,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByForecastMonth(forecastMonth);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByForecastMonth(forecastMonth,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastMonth = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param forecastMonth the forecast month
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByForecastMonth_PrevAndNext(
        int forecastMasterSid, String forecastMonth,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByForecastMonth_PrevAndNext(session,
                    forecastingMaster, forecastMonth, orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByForecastMonth_PrevAndNext(session,
                    forecastingMaster, forecastMonth, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByForecastMonth_PrevAndNext(
        Session session, ForecastingMaster forecastingMaster,
        String forecastMonth, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindForecastMonth = false;

        if (forecastMonth == null) {
            query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_1);
        } else if (forecastMonth.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_3);
        } else {
            bindForecastMonth = true;

            query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindForecastMonth) {
            qPos.add(forecastMonth);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where forecastMonth = &#63; from the database.
     *
     * @param forecastMonth the forecast month
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByForecastMonth(String forecastMonth)
        throws SystemException {
        for (ForecastingMaster forecastingMaster : findByForecastMonth(
                forecastMonth, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where forecastMonth = &#63;.
     *
     * @param forecastMonth the forecast month
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByForecastMonth(String forecastMonth)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FORECASTMONTH;

        Object[] finderArgs = new Object[] { forecastMonth };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindForecastMonth = false;

            if (forecastMonth == null) {
                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_1);
            } else if (forecastMonth.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_3);
            } else {
                bindForecastMonth = true;

                query.append(_FINDER_COLUMN_FORECASTMONTH_FORECASTMONTH_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastMonth) {
                    qPos.add(forecastMonth);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where country = &#63;.
     *
     * @param country the country
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCountry(String country)
        throws SystemException {
        return findByCountry(country, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where country = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param country the country
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCountry(String country, int start,
        int end) throws SystemException {
        return findByCountry(country, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where country = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param country the country
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCountry(String country, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRY;
            finderArgs = new Object[] { country };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRY;
            finderArgs = new Object[] { country, start, end, orderByComparator };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(country, forecastingMaster.getCountry())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindCountry = false;

            if (country == null) {
                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_1);
            } else if (country.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
            } else {
                bindCountry = true;

                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCountry) {
                    qPos.add(country);
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where country = &#63;.
     *
     * @param country the country
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByCountry_First(String country,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByCountry_First(country,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("country=");
        msg.append(country);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where country = &#63;.
     *
     * @param country the country
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByCountry_First(String country,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByCountry(country, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where country = &#63;.
     *
     * @param country the country
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByCountry_Last(String country,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByCountry_Last(country,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("country=");
        msg.append(country);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where country = &#63;.
     *
     * @param country the country
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByCountry_Last(String country,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCountry(country);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByCountry(country, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where country = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param country the country
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByCountry_PrevAndNext(
        int forecastMasterSid, String country,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByCountry_PrevAndNext(session, forecastingMaster,
                    country, orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByCountry_PrevAndNext(session, forecastingMaster,
                    country, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByCountry_PrevAndNext(Session session,
        ForecastingMaster forecastingMaster, String country,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindCountry = false;

        if (country == null) {
            query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_1);
        } else if (country.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
        } else {
            bindCountry = true;

            query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCountry) {
            qPos.add(country);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where country = &#63; from the database.
     *
     * @param country the country
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCountry(String country) throws SystemException {
        for (ForecastingMaster forecastingMaster : findByCountry(country,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where country = &#63;.
     *
     * @param country the country
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCountry(String country) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COUNTRY;

        Object[] finderArgs = new Object[] { country };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindCountry = false;

            if (country == null) {
                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_1);
            } else if (country.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
            } else {
                bindCountry = true;

                query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCountry) {
                    qPos.add(country);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where ndc = &#63;.
     *
     * @param ndc the ndc
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByNdc(String ndc)
        throws SystemException {
        return findByNdc(ndc, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where ndc = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ndc the ndc
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByNdc(String ndc, int start, int end)
        throws SystemException {
        return findByNdc(ndc, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where ndc = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ndc the ndc
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByNdc(String ndc, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC;
            finderArgs = new Object[] { ndc };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NDC;
            finderArgs = new Object[] { ndc, start, end, orderByComparator };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(ndc, forecastingMaster.getNdc())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindNdc = false;

            if (ndc == null) {
                query.append(_FINDER_COLUMN_NDC_NDC_1);
            } else if (ndc.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NDC_NDC_3);
            } else {
                bindNdc = true;

                query.append(_FINDER_COLUMN_NDC_NDC_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindNdc) {
                    qPos.add(ndc);
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where ndc = &#63;.
     *
     * @param ndc the ndc
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByNdc_First(String ndc,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByNdc_First(ndc,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ndc=");
        msg.append(ndc);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where ndc = &#63;.
     *
     * @param ndc the ndc
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByNdc_First(String ndc,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByNdc(ndc, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where ndc = &#63;.
     *
     * @param ndc the ndc
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByNdc_Last(String ndc,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByNdc_Last(ndc,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ndc=");
        msg.append(ndc);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where ndc = &#63;.
     *
     * @param ndc the ndc
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByNdc_Last(String ndc,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByNdc(ndc);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByNdc(ndc, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where ndc = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param ndc the ndc
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByNdc_PrevAndNext(int forecastMasterSid,
        String ndc, OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByNdc_PrevAndNext(session, forecastingMaster, ndc,
                    orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByNdc_PrevAndNext(session, forecastingMaster, ndc,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByNdc_PrevAndNext(Session session,
        ForecastingMaster forecastingMaster, String ndc,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindNdc = false;

        if (ndc == null) {
            query.append(_FINDER_COLUMN_NDC_NDC_1);
        } else if (ndc.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_NDC_NDC_3);
        } else {
            bindNdc = true;

            query.append(_FINDER_COLUMN_NDC_NDC_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindNdc) {
            qPos.add(ndc);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where ndc = &#63; from the database.
     *
     * @param ndc the ndc
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByNdc(String ndc) throws SystemException {
        for (ForecastingMaster forecastingMaster : findByNdc(ndc,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where ndc = &#63;.
     *
     * @param ndc the ndc
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByNdc(String ndc) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_NDC;

        Object[] finderArgs = new Object[] { ndc };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindNdc = false;

            if (ndc == null) {
                query.append(_FINDER_COLUMN_NDC_NDC_1);
            } else if (ndc.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NDC_NDC_3);
            } else {
                bindNdc = true;

                query.append(_FINDER_COLUMN_NDC_NDC_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindNdc) {
                    qPos.add(ndc);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastStartDate(
        Date forecastStartDate) throws SystemException {
        return findByForecastStartDate(forecastStartDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where forecastStartDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastStartDate the forecast start date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastStartDate(
        Date forecastStartDate, int start, int end) throws SystemException {
        return findByForecastStartDate(forecastStartDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where forecastStartDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastStartDate the forecast start date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastStartDate(
        Date forecastStartDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTSTARTDATE;
            finderArgs = new Object[] { forecastStartDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTSTARTDATE;
            finderArgs = new Object[] {
                    forecastStartDate,
                    
                    start, end, orderByComparator
                };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(forecastStartDate,
                            forecastingMaster.getForecastStartDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindForecastStartDate = false;

            if (forecastStartDate == null) {
                query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_1);
            } else {
                bindForecastStartDate = true;

                query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastStartDate_First(
        Date forecastStartDate, OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastStartDate_First(forecastStartDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastStartDate=");
        msg.append(forecastStartDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastStartDate_First(
        Date forecastStartDate, OrderByComparator orderByComparator)
        throws SystemException {
        List<ForecastingMaster> list = findByForecastStartDate(forecastStartDate,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastStartDate_Last(
        Date forecastStartDate, OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastStartDate_Last(forecastStartDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastStartDate=");
        msg.append(forecastStartDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastStartDate_Last(
        Date forecastStartDate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByForecastStartDate(forecastStartDate);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByForecastStartDate(forecastStartDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastStartDate = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param forecastStartDate the forecast start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByForecastStartDate_PrevAndNext(
        int forecastMasterSid, Date forecastStartDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByForecastStartDate_PrevAndNext(session,
                    forecastingMaster, forecastStartDate, orderByComparator,
                    true);

            array[1] = forecastingMaster;

            array[2] = getByForecastStartDate_PrevAndNext(session,
                    forecastingMaster, forecastStartDate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByForecastStartDate_PrevAndNext(
        Session session, ForecastingMaster forecastingMaster,
        Date forecastStartDate, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindForecastStartDate = false;

        if (forecastStartDate == null) {
            query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_1);
        } else {
            bindForecastStartDate = true;

            query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindForecastStartDate) {
            qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where forecastStartDate = &#63; from the database.
     *
     * @param forecastStartDate the forecast start date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByForecastStartDate(Date forecastStartDate)
        throws SystemException {
        for (ForecastingMaster forecastingMaster : findByForecastStartDate(
                forecastStartDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where forecastStartDate = &#63;.
     *
     * @param forecastStartDate the forecast start date
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByForecastStartDate(Date forecastStartDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FORECASTSTARTDATE;

        Object[] finderArgs = new Object[] { forecastStartDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindForecastStartDate = false;

            if (forecastStartDate == null) {
                query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_1);
            } else {
                bindForecastStartDate = true;

                query.append(_FINDER_COLUMN_FORECASTSTARTDATE_FORECASTSTARTDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCreatedDate(Date createdDate)
        throws SystemException {
        return findByCreatedDate(createdDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where createdDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param createdDate the created date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCreatedDate(Date createdDate,
        int start, int end) throws SystemException {
        return findByCreatedDate(createdDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where createdDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param createdDate the created date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByCreatedDate(Date createdDate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDDATE;
            finderArgs = new Object[] { createdDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDDATE;
            finderArgs = new Object[] { createdDate, start, end, orderByComparator };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(createdDate,
                            forecastingMaster.getCreatedDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindCreatedDate = false;

            if (createdDate == null) {
                query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_1);
            } else {
                bindCreatedDate = true;

                query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(createdDate));
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByCreatedDate_First(Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByCreatedDate_First(createdDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("createdDate=");
        msg.append(createdDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByCreatedDate_First(Date createdDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByCreatedDate(createdDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByCreatedDate_Last(Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByCreatedDate_Last(createdDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("createdDate=");
        msg.append(createdDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByCreatedDate_Last(Date createdDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCreatedDate(createdDate);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByCreatedDate(createdDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where createdDate = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByCreatedDate_PrevAndNext(
        int forecastMasterSid, Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByCreatedDate_PrevAndNext(session, forecastingMaster,
                    createdDate, orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByCreatedDate_PrevAndNext(session, forecastingMaster,
                    createdDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByCreatedDate_PrevAndNext(Session session,
        ForecastingMaster forecastingMaster, Date createdDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindCreatedDate = false;

        if (createdDate == null) {
            query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_1);
        } else {
            bindCreatedDate = true;

            query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCreatedDate) {
            qPos.add(CalendarUtil.getTimestamp(createdDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where createdDate = &#63; from the database.
     *
     * @param createdDate the created date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCreatedDate(Date createdDate) throws SystemException {
        for (ForecastingMaster forecastingMaster : findByCreatedDate(
                createdDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where createdDate = &#63;.
     *
     * @param createdDate the created date
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCreatedDate(Date createdDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CREATEDDATE;

        Object[] finderArgs = new Object[] { createdDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindCreatedDate = false;

            if (createdDate == null) {
                query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_1);
            } else {
                bindCreatedDate = true;

                query.append(_FINDER_COLUMN_CREATEDDATE_CREATEDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(createdDate));
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @return the matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastingSalesUnique(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate) throws SystemException {
        return findByForecastingSalesUnique(forecastYear, forecastMonth,
            country, forecastStartDate, createdDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastingSalesUnique(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate, int start, int end)
        throws SystemException {
        return findByForecastingSalesUnique(forecastYear, forecastMonth,
            country, forecastStartDate, createdDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findByForecastingSalesUnique(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE;
            finderArgs = new Object[] {
                    forecastYear, forecastMonth, country, forecastStartDate,
                    createdDate
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE;
            finderArgs = new Object[] {
                    forecastYear, forecastMonth, country, forecastStartDate,
                    createdDate,
                    
                    start, end, orderByComparator
                };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ForecastingMaster forecastingMaster : list) {
                if (!Validator.equals(forecastYear,
                            forecastingMaster.getForecastYear()) ||
                        !Validator.equals(forecastMonth,
                            forecastingMaster.getForecastMonth()) ||
                        !Validator.equals(country,
                            forecastingMaster.getCountry()) ||
                        !Validator.equals(forecastStartDate,
                            forecastingMaster.getForecastStartDate()) ||
                        !Validator.equals(createdDate,
                            forecastingMaster.getCreatedDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(7 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(7);
            }

            query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

            boolean bindForecastYear = false;

            if (forecastYear == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_1);
            } else if (forecastYear.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_3);
            } else {
                bindForecastYear = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_2);
            }

            boolean bindForecastMonth = false;

            if (forecastMonth == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_1);
            } else if (forecastMonth.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_3);
            } else {
                bindForecastMonth = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_2);
            }

            boolean bindCountry = false;

            if (country == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_1);
            } else if (country.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_3);
            } else {
                bindCountry = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_2);
            }

            boolean bindForecastStartDate = false;

            if (forecastStartDate == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_1);
            } else {
                bindForecastStartDate = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_2);
            }

            boolean bindCreatedDate = false;

            if (createdDate == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_1);
            } else {
                bindCreatedDate = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastYear) {
                    qPos.add(forecastYear);
                }

                if (bindForecastMonth) {
                    qPos.add(forecastMonth);
                }

                if (bindCountry) {
                    qPos.add(country);
                }

                if (bindForecastStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
                }

                if (bindCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(createdDate));
                }

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastingSalesUnique_First(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastingSalesUnique_First(forecastYear,
                forecastMonth, country, forecastStartDate, createdDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(12);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastYear=");
        msg.append(forecastYear);

        msg.append(", forecastMonth=");
        msg.append(forecastMonth);

        msg.append(", country=");
        msg.append(country);

        msg.append(", forecastStartDate=");
        msg.append(forecastStartDate);

        msg.append(", createdDate=");
        msg.append(createdDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastingSalesUnique_First(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<ForecastingMaster> list = findByForecastingSalesUnique(forecastYear,
                forecastMonth, country, forecastStartDate, createdDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByForecastingSalesUnique_Last(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByForecastingSalesUnique_Last(forecastYear,
                forecastMonth, country, forecastStartDate, createdDate,
                orderByComparator);

        if (forecastingMaster != null) {
            return forecastingMaster;
        }

        StringBundler msg = new StringBundler(12);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("forecastYear=");
        msg.append(forecastYear);

        msg.append(", forecastMonth=");
        msg.append(forecastMonth);

        msg.append(", country=");
        msg.append(country);

        msg.append(", forecastStartDate=");
        msg.append(forecastStartDate);

        msg.append(", createdDate=");
        msg.append(createdDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchForecastingMasterException(msg.toString());
    }

    /**
     * Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByForecastingSalesUnique_Last(
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByForecastingSalesUnique(forecastYear, forecastMonth,
                country, forecastStartDate, createdDate);

        if (count == 0) {
            return null;
        }

        List<ForecastingMaster> list = findByForecastingSalesUnique(forecastYear,
                forecastMonth, country, forecastStartDate, createdDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastMasterSid the primary key of the current forecasting master
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster[] findByForecastingSalesUnique_PrevAndNext(
        int forecastMasterSid, String forecastYear, String forecastMonth,
        String country, Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = findByPrimaryKey(forecastMasterSid);

        Session session = null;

        try {
            session = openSession();

            ForecastingMaster[] array = new ForecastingMasterImpl[3];

            array[0] = getByForecastingSalesUnique_PrevAndNext(session,
                    forecastingMaster, forecastYear, forecastMonth, country,
                    forecastStartDate, createdDate, orderByComparator, true);

            array[1] = forecastingMaster;

            array[2] = getByForecastingSalesUnique_PrevAndNext(session,
                    forecastingMaster, forecastYear, forecastMonth, country,
                    forecastStartDate, createdDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ForecastingMaster getByForecastingSalesUnique_PrevAndNext(
        Session session, ForecastingMaster forecastingMaster,
        String forecastYear, String forecastMonth, String country,
        Date forecastStartDate, Date createdDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FORECASTINGMASTER_WHERE);

        boolean bindForecastYear = false;

        if (forecastYear == null) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_1);
        } else if (forecastYear.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_3);
        } else {
            bindForecastYear = true;

            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_2);
        }

        boolean bindForecastMonth = false;

        if (forecastMonth == null) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_1);
        } else if (forecastMonth.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_3);
        } else {
            bindForecastMonth = true;

            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_2);
        }

        boolean bindCountry = false;

        if (country == null) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_1);
        } else if (country.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_3);
        } else {
            bindCountry = true;

            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_2);
        }

        boolean bindForecastStartDate = false;

        if (forecastStartDate == null) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_1);
        } else {
            bindForecastStartDate = true;

            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_2);
        }

        boolean bindCreatedDate = false;

        if (createdDate == null) {
            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_1);
        } else {
            bindCreatedDate = true;

            query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ForecastingMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindForecastYear) {
            qPos.add(forecastYear);
        }

        if (bindForecastMonth) {
            qPos.add(forecastMonth);
        }

        if (bindCountry) {
            qPos.add(country);
        }

        if (bindForecastStartDate) {
            qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
        }

        if (bindCreatedDate) {
            qPos.add(CalendarUtil.getTimestamp(createdDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(forecastingMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ForecastingMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63; from the database.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByForecastingSalesUnique(String forecastYear,
        String forecastMonth, String country, Date forecastStartDate,
        Date createdDate) throws SystemException {
        for (ForecastingMaster forecastingMaster : findByForecastingSalesUnique(
                forecastYear, forecastMonth, country, forecastStartDate,
                createdDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
     *
     * @param forecastYear the forecast year
     * @param forecastMonth the forecast month
     * @param country the country
     * @param forecastStartDate the forecast start date
     * @param createdDate the created date
     * @return the number of matching forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByForecastingSalesUnique(String forecastYear,
        String forecastMonth, String country, Date forecastStartDate,
        Date createdDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FORECASTINGSALESUNIQUE;

        Object[] finderArgs = new Object[] {
                forecastYear, forecastMonth, country, forecastStartDate,
                createdDate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(6);

            query.append(_SQL_COUNT_FORECASTINGMASTER_WHERE);

            boolean bindForecastYear = false;

            if (forecastYear == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_1);
            } else if (forecastYear.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_3);
            } else {
                bindForecastYear = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTYEAR_2);
            }

            boolean bindForecastMonth = false;

            if (forecastMonth == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_1);
            } else if (forecastMonth.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_3);
            } else {
                bindForecastMonth = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTMONTH_2);
            }

            boolean bindCountry = false;

            if (country == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_1);
            } else if (country.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_3);
            } else {
                bindCountry = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_COUNTRY_2);
            }

            boolean bindForecastStartDate = false;

            if (forecastStartDate == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_1);
            } else {
                bindForecastStartDate = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_FORECASTSTARTDATE_2);
            }

            boolean bindCreatedDate = false;

            if (createdDate == null) {
                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_1);
            } else {
                bindCreatedDate = true;

                query.append(_FINDER_COLUMN_FORECASTINGSALESUNIQUE_CREATEDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindForecastYear) {
                    qPos.add(forecastYear);
                }

                if (bindForecastMonth) {
                    qPos.add(forecastMonth);
                }

                if (bindCountry) {
                    qPos.add(country);
                }

                if (bindForecastStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(forecastStartDate));
                }

                if (bindCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(createdDate));
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the forecasting master in the entity cache if it is enabled.
     *
     * @param forecastingMaster the forecasting master
     */
    @Override
    public void cacheResult(ForecastingMaster forecastingMaster) {
        EntityCacheUtil.putResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterImpl.class, forecastingMaster.getPrimaryKey(),
            forecastingMaster);

        forecastingMaster.resetOriginalValues();
    }

    /**
     * Caches the forecasting masters in the entity cache if it is enabled.
     *
     * @param forecastingMasters the forecasting masters
     */
    @Override
    public void cacheResult(List<ForecastingMaster> forecastingMasters) {
        for (ForecastingMaster forecastingMaster : forecastingMasters) {
            if (EntityCacheUtil.getResult(
                        ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingMasterImpl.class,
                        forecastingMaster.getPrimaryKey()) == null) {
                cacheResult(forecastingMaster);
            } else {
                forecastingMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all forecasting masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ForecastingMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ForecastingMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the forecasting master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ForecastingMaster forecastingMaster) {
        EntityCacheUtil.removeResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterImpl.class, forecastingMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ForecastingMaster> forecastingMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ForecastingMaster forecastingMaster : forecastingMasters) {
            EntityCacheUtil.removeResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingMasterImpl.class, forecastingMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
     *
     * @param forecastMasterSid the primary key for the new forecasting master
     * @return the new forecasting master
     */
    @Override
    public ForecastingMaster create(int forecastMasterSid) {
        ForecastingMaster forecastingMaster = new ForecastingMasterImpl();

        forecastingMaster.setNew(true);
        forecastingMaster.setPrimaryKey(forecastMasterSid);

        return forecastingMaster;
    }

    /**
     * Removes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param forecastMasterSid the primary key of the forecasting master
     * @return the forecasting master that was removed
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster remove(int forecastMasterSid)
        throws NoSuchForecastingMasterException, SystemException {
        return remove((Serializable) forecastMasterSid);
    }

    /**
     * Removes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the forecasting master
     * @return the forecasting master that was removed
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster remove(Serializable primaryKey)
        throws NoSuchForecastingMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ForecastingMaster forecastingMaster = (ForecastingMaster) session.get(ForecastingMasterImpl.class,
                    primaryKey);

            if (forecastingMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchForecastingMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(forecastingMaster);
        } catch (NoSuchForecastingMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ForecastingMaster removeImpl(ForecastingMaster forecastingMaster)
        throws SystemException {
        forecastingMaster = toUnwrappedModel(forecastingMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(forecastingMaster)) {
                forecastingMaster = (ForecastingMaster) session.get(ForecastingMasterImpl.class,
                        forecastingMaster.getPrimaryKeyObj());
            }

            if (forecastingMaster != null) {
                session.delete(forecastingMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (forecastingMaster != null) {
            clearCache(forecastingMaster);
        }

        return forecastingMaster;
    }

    @Override
    public ForecastingMaster updateImpl(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws SystemException {
        forecastingMaster = toUnwrappedModel(forecastingMaster);

        boolean isNew = forecastingMaster.isNew();

        ForecastingMasterModelImpl forecastingMasterModelImpl = (ForecastingMasterModelImpl) forecastingMaster;

        Session session = null;

        try {
            session = openSession();

            if (forecastingMaster.isNew()) {
                session.save(forecastingMaster);

                forecastingMaster.setNew(false);
            } else {
                session.merge(forecastingMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ForecastingMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTYEAR.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalForecastYear()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTYEAR,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTYEAR,
                    args);

                args = new Object[] { forecastingMasterModelImpl.getForecastYear() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTYEAR,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTYEAR,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTMONTH.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalForecastMonth()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTMONTH,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTMONTH,
                    args);

                args = new Object[] {
                        forecastingMasterModelImpl.getForecastMonth()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTMONTH,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTMONTH,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRY.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalCountry()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRY, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRY,
                    args);

                args = new Object[] { forecastingMasterModelImpl.getCountry() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRY, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRY,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalNdc()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NDC, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC,
                    args);

                args = new Object[] { forecastingMasterModelImpl.getNdc() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NDC, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTSTARTDATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalForecastStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTSTARTDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTSTARTDATE,
                    args);

                args = new Object[] {
                        forecastingMasterModelImpl.getForecastStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTSTARTDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTSTARTDATE,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDDATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalCreatedDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDDATE,
                    args);

                args = new Object[] { forecastingMasterModelImpl.getCreatedDate() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDDATE,
                    args);
            }

            if ((forecastingMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        forecastingMasterModelImpl.getOriginalForecastYear(),
                        forecastingMasterModelImpl.getOriginalForecastMonth(),
                        forecastingMasterModelImpl.getOriginalCountry(),
                        forecastingMasterModelImpl.getOriginalForecastStartDate(),
                        forecastingMasterModelImpl.getOriginalCreatedDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTINGSALESUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE,
                    args);

                args = new Object[] {
                        forecastingMasterModelImpl.getForecastYear(),
                        forecastingMasterModelImpl.getForecastMonth(),
                        forecastingMasterModelImpl.getCountry(),
                        forecastingMasterModelImpl.getForecastStartDate(),
                        forecastingMasterModelImpl.getCreatedDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FORECASTINGSALESUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORECASTINGSALESUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingMasterImpl.class, forecastingMaster.getPrimaryKey(),
            forecastingMaster);

        return forecastingMaster;
    }

    protected ForecastingMaster toUnwrappedModel(
        ForecastingMaster forecastingMaster) {
        if (forecastingMaster instanceof ForecastingMasterImpl) {
            return forecastingMaster;
        }

        ForecastingMasterImpl forecastingMasterImpl = new ForecastingMasterImpl();

        forecastingMasterImpl.setNew(forecastingMaster.isNew());
        forecastingMasterImpl.setPrimaryKey(forecastingMaster.getPrimaryKey());

        forecastingMasterImpl.setForecastValueType(forecastingMaster.getForecastValueType());
        forecastingMasterImpl.setModifiedBy(forecastingMaster.getModifiedBy());
        forecastingMasterImpl.setCreatedDate(forecastingMaster.getCreatedDate());
        forecastingMasterImpl.setPercentageEstimate(forecastingMaster.getPercentageEstimate());
        forecastingMasterImpl.setActualSalesPercentageMonth(forecastingMaster.getActualSalesPercentageMonth());
        forecastingMasterImpl.setNdc(forecastingMaster.getNdc());
        forecastingMasterImpl.setBatchId(forecastingMaster.getBatchId());
        forecastingMasterImpl.setForecastVer(forecastingMaster.getForecastVer());
        forecastingMasterImpl.setCountry(forecastingMaster.getCountry());
        forecastingMasterImpl.setProduct(forecastingMaster.getProduct());
        forecastingMasterImpl.setForecastStartDate(forecastingMaster.getForecastStartDate());
        forecastingMasterImpl.setForecastedSalesPercentage(forecastingMaster.getForecastedSalesPercentage());
        forecastingMasterImpl.setUnits(forecastingMaster.getUnits());
        forecastingMasterImpl.setDollars(forecastingMaster.getDollars());
        forecastingMasterImpl.setForecastMonth(forecastingMaster.getForecastMonth());
        forecastingMasterImpl.setCreatedBy(forecastingMaster.getCreatedBy());
        forecastingMasterImpl.setSegment(forecastingMaster.getSegment());
        forecastingMasterImpl.setPrice(forecastingMaster.getPrice());
        forecastingMasterImpl.setActualSalesPercentage(forecastingMaster.getActualSalesPercentage());
        forecastingMasterImpl.setForecastYear(forecastingMaster.getForecastYear());
        forecastingMasterImpl.setForecastName(forecastingMaster.getForecastName());
        forecastingMasterImpl.setForecastDate(forecastingMaster.getForecastDate());
        forecastingMasterImpl.setModifiedDate(forecastingMaster.getModifiedDate());
        forecastingMasterImpl.setBrand(forecastingMaster.getBrand());
        forecastingMasterImpl.setForecastValue(forecastingMaster.getForecastValue());
        forecastingMasterImpl.setPercentageEstimateYear(forecastingMaster.getPercentageEstimateYear());
        forecastingMasterImpl.setRecordLockStatus(forecastingMaster.isRecordLockStatus());
        forecastingMasterImpl.setSource(forecastingMaster.getSource());
        forecastingMasterImpl.setForecastMasterSid(forecastingMaster.getForecastMasterSid());
        forecastingMasterImpl.setForecastedSalesPercentMonth(forecastingMaster.getForecastedSalesPercentMonth());
        forecastingMasterImpl.setInboundStatus(forecastingMaster.getInboundStatus());

        return forecastingMasterImpl;
    }

    /**
     * Returns the forecasting master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting master
     * @return the forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchForecastingMasterException, SystemException {
        ForecastingMaster forecastingMaster = fetchByPrimaryKey(primaryKey);

        if (forecastingMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchForecastingMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return forecastingMaster;
    }

    /**
     * Returns the forecasting master with the primary key or throws a {@link com.stpl.app.NoSuchForecastingMasterException} if it could not be found.
     *
     * @param forecastMasterSid the primary key of the forecasting master
     * @return the forecasting master
     * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster findByPrimaryKey(int forecastMasterSid)
        throws NoSuchForecastingMasterException, SystemException {
        return findByPrimaryKey((Serializable) forecastMasterSid);
    }

    /**
     * Returns the forecasting master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting master
     * @return the forecasting master, or <code>null</code> if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ForecastingMaster forecastingMaster = (ForecastingMaster) EntityCacheUtil.getResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingMasterImpl.class, primaryKey);

        if (forecastingMaster == _nullForecastingMaster) {
            return null;
        }

        if (forecastingMaster == null) {
            Session session = null;

            try {
                session = openSession();

                forecastingMaster = (ForecastingMaster) session.get(ForecastingMasterImpl.class,
                        primaryKey);

                if (forecastingMaster != null) {
                    cacheResult(forecastingMaster);
                } else {
                    EntityCacheUtil.putResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingMasterImpl.class, primaryKey,
                        _nullForecastingMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ForecastingMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ForecastingMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return forecastingMaster;
    }

    /**
     * Returns the forecasting master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param forecastMasterSid the primary key of the forecasting master
     * @return the forecasting master, or <code>null</code> if a forecasting master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingMaster fetchByPrimaryKey(int forecastMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) forecastMasterSid);
    }

    /**
     * Returns all the forecasting masters.
     *
     * @return the forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @return the range of forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting masters
     * @param end the upper bound of the range of forecasting masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingMaster> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<ForecastingMaster> list = (List<ForecastingMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FORECASTINGMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FORECASTINGMASTER;

                if (pagination) {
                    sql = sql.concat(ForecastingMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingMaster>(list);
                } else {
                    list = (List<ForecastingMaster>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the forecasting masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ForecastingMaster forecastingMaster : findAll()) {
            remove(forecastingMaster);
        }
    }

    /**
     * Returns the number of forecasting masters.
     *
     * @return the number of forecasting masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FORECASTINGMASTER);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the forecasting master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ForecastingMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ForecastingMaster>> listenersList = new ArrayList<ModelListener<ForecastingMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ForecastingMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ForecastingMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
