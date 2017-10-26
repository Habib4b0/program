package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGlBalanceMasterException;
import com.stpl.app.model.GlBalanceMaster;
import com.stpl.app.model.impl.GlBalanceMasterImpl;
import com.stpl.app.model.impl.GlBalanceMasterModelImpl;
import com.stpl.app.service.persistence.GlBalanceMasterPersistence;

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
 * The persistence implementation for the gl balance master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlBalanceMasterPersistence
 * @see GlBalanceMasterUtil
 * @generated
 */
public class GlBalanceMasterPersistenceImpl extends BasePersistenceImpl<GlBalanceMaster>
    implements GlBalanceMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GlBalanceMasterUtil} to access the gl balance master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GlBalanceMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAccountNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountNo",
            new String[] { String.class.getName() },
            GlBalanceMasterModelImpl.ACCOUNTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTNO = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1 = "glBalanceMaster.accountNo IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2 = "glBalanceMaster.accountNo = ?";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3 = "(glBalanceMaster.accountNo IS NULL OR glBalanceMaster.accountNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ISACTIVE = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByIsActive",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISACTIVE =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIsActive",
            new String[] { String.class.getName() },
            GlBalanceMasterModelImpl.ISACTIVE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ISACTIVE = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIsActive",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ISACTIVE_ISACTIVE_1 = "glBalanceMaster.isActive IS NULL";
    private static final String _FINDER_COLUMN_ISACTIVE_ISACTIVE_2 = "glBalanceMaster.isActive = ?";
    private static final String _FINDER_COLUMN_ISACTIVE_ISACTIVE_3 = "(glBalanceMaster.isActive IS NULL OR glBalanceMaster.isActive = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UPLOADDATE =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByUploadDate",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UPLOADDATE =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUploadDate",
            new String[] { Date.class.getName() },
            GlBalanceMasterModelImpl.UPLOADDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UPLOADDATE = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUploadDate",
            new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_UPLOADDATE_UPLOADDATE_1 = "glBalanceMaster.uploadDate IS NULL";
    private static final String _FINDER_COLUMN_UPLOADDATE_UPLOADDATE_2 = "glBalanceMaster.uploadDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAccountId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
            new String[] { String.class.getName() },
            GlBalanceMasterModelImpl.ACCOUNTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1 = "glBalanceMaster.accountId IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "glBalanceMaster.accountId = ?";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3 = "(glBalanceMaster.accountId IS NULL OR glBalanceMaster.accountId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_YEAR = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByYear",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_YEAR = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByYear",
            new String[] { String.class.getName() },
            GlBalanceMasterModelImpl.YEAR_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_YEAR = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByYear",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_YEAR_YEAR_1 = "glBalanceMaster.year IS NULL";
    private static final String _FINDER_COLUMN_YEAR_YEAR_2 = "glBalanceMaster.year = ?";
    private static final String _FINDER_COLUMN_YEAR_YEAR_3 = "(glBalanceMaster.year IS NULL OR glBalanceMaster.year = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIOD = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPeriod",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPeriod",
            new String[] { String.class.getName() },
            GlBalanceMasterModelImpl.PERIOD_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PERIOD = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPeriod",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_1 = "glBalanceMaster.period IS NULL";
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_2 = "glBalanceMaster.period = ?";
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_3 = "(glBalanceMaster.period IS NULL OR glBalanceMaster.period = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GLBALANCEUNIQUE =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByGlBalanceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLBALANCEUNIQUE =
        new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED,
            GlBalanceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGlBalanceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName()
            },
            GlBalanceMasterModelImpl.ACCOUNTNO_COLUMN_BITMASK |
            GlBalanceMasterModelImpl.PERIOD_COLUMN_BITMASK |
            GlBalanceMasterModelImpl.UPLOADDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GLBALANCEUNIQUE = new FinderPath(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByGlBalanceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName()
            });
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_1 = "glBalanceMaster.accountNo IS NULL AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_2 = "glBalanceMaster.accountNo = ? AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_3 = "(glBalanceMaster.accountNo IS NULL OR glBalanceMaster.accountNo = '') AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_1 = "glBalanceMaster.period IS NULL AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_2 = "glBalanceMaster.period = ? AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_3 = "(glBalanceMaster.period IS NULL OR glBalanceMaster.period = '') AND ";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_1 = "glBalanceMaster.uploadDate IS NULL";
    private static final String _FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_2 = "glBalanceMaster.uploadDate = ?";
    private static final String _SQL_SELECT_GLBALANCEMASTER = "SELECT glBalanceMaster FROM GlBalanceMaster glBalanceMaster";
    private static final String _SQL_SELECT_GLBALANCEMASTER_WHERE = "SELECT glBalanceMaster FROM GlBalanceMaster glBalanceMaster WHERE ";
    private static final String _SQL_COUNT_GLBALANCEMASTER = "SELECT COUNT(glBalanceMaster) FROM GlBalanceMaster glBalanceMaster";
    private static final String _SQL_COUNT_GLBALANCEMASTER_WHERE = "SELECT COUNT(glBalanceMaster) FROM GlBalanceMaster glBalanceMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "glBalanceMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GlBalanceMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GlBalanceMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GlBalanceMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "accountId", "uploadDate",
                "createdDate", "glBalanceMasterSid", "isActive", "batchId",
                "modifiedDate", "balance", "closeIndicator", "recordLockStatus",
                "year", "period", "source", "insertedDate", "accountNo",
                "inboundStatus"
            });
    private static GlBalanceMaster _nullGlBalanceMaster = new GlBalanceMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GlBalanceMaster> toCacheModel() {
                return _nullGlBalanceMasterCacheModel;
            }
        };

    private static CacheModel<GlBalanceMaster> _nullGlBalanceMasterCacheModel = new CacheModel<GlBalanceMaster>() {
            @Override
            public GlBalanceMaster toEntityModel() {
                return _nullGlBalanceMaster;
            }
        };

    public GlBalanceMasterPersistenceImpl() {
        setModelClass(GlBalanceMaster.class);
    }

    /**
     * Returns all the gl balance masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountNo(String accountNo)
        throws SystemException {
        return findByAccountNo(accountNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the gl balance masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountNo(String accountNo, int start,
        int end) throws SystemException {
        return findByAccountNo(accountNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountNo(String accountNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO;
            finderArgs = new Object[] { accountNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO;
            finderArgs = new Object[] { accountNo, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(accountNo, glBalanceMaster.getAccountNo())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByAccountNo_First(accountNo,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByAccountNo(accountNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByAccountNo_Last(accountNo,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountNo(accountNo);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByAccountNo(accountNo, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByAccountNo_PrevAndNext(
        int glBalanceMasterSid, String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByAccountNo_PrevAndNext(session, glBalanceMaster,
                    accountNo, orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByAccountNo_PrevAndNext(session, glBalanceMaster,
                    accountNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByAccountNo_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, String accountNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindAccountNo = false;

        if (accountNo == null) {
            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
        } else if (accountNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
        } else {
            bindAccountNo = true;

            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAccountNo) {
            qPos.add(accountNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where accountNo = &#63; from the database.
     *
     * @param accountNo the account no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountNo(String accountNo) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByAccountNo(accountNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAccountNo(String accountNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTNO;

        Object[] finderArgs = new Object[] { accountNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
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
     * Returns all the gl balance masters where isActive = &#63;.
     *
     * @param isActive the is active
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByIsActive(String isActive)
        throws SystemException {
        return findByIsActive(isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the gl balance masters where isActive = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param isActive the is active
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByIsActive(String isActive, int start,
        int end) throws SystemException {
        return findByIsActive(isActive, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where isActive = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param isActive the is active
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByIsActive(String isActive, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISACTIVE;
            finderArgs = new Object[] { isActive };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ISACTIVE;
            finderArgs = new Object[] { isActive, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(isActive, glBalanceMaster.getIsActive())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindIsActive = false;

            if (isActive == null) {
                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_1);
            } else if (isActive.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_3);
            } else {
                bindIsActive = true;

                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIsActive) {
                    qPos.add(isActive);
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where isActive = &#63;.
     *
     * @param isActive the is active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByIsActive_First(String isActive,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByIsActive_First(isActive,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("isActive=");
        msg.append(isActive);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where isActive = &#63;.
     *
     * @param isActive the is active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByIsActive_First(String isActive,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByIsActive(isActive, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where isActive = &#63;.
     *
     * @param isActive the is active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByIsActive_Last(String isActive,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByIsActive_Last(isActive,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("isActive=");
        msg.append(isActive);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where isActive = &#63;.
     *
     * @param isActive the is active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByIsActive_Last(String isActive,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByIsActive(isActive);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByIsActive(isActive, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where isActive = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param isActive the is active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByIsActive_PrevAndNext(
        int glBalanceMasterSid, String isActive,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByIsActive_PrevAndNext(session, glBalanceMaster,
                    isActive, orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByIsActive_PrevAndNext(session, glBalanceMaster,
                    isActive, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByIsActive_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, String isActive,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindIsActive = false;

        if (isActive == null) {
            query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_1);
        } else if (isActive.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_3);
        } else {
            bindIsActive = true;

            query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindIsActive) {
            qPos.add(isActive);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where isActive = &#63; from the database.
     *
     * @param isActive the is active
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByIsActive(String isActive) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByIsActive(isActive,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where isActive = &#63;.
     *
     * @param isActive the is active
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByIsActive(String isActive) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ISACTIVE;

        Object[] finderArgs = new Object[] { isActive };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindIsActive = false;

            if (isActive == null) {
                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_1);
            } else if (isActive.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_3);
            } else {
                bindIsActive = true;

                query.append(_FINDER_COLUMN_ISACTIVE_ISACTIVE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIsActive) {
                    qPos.add(isActive);
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
     * Returns all the gl balance masters where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByUploadDate(Date uploadDate)
        throws SystemException {
        return findByUploadDate(uploadDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl balance masters where uploadDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param uploadDate the upload date
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByUploadDate(Date uploadDate, int start,
        int end) throws SystemException {
        return findByUploadDate(uploadDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param uploadDate the upload date
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByUploadDate(Date uploadDate, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UPLOADDATE;
            finderArgs = new Object[] { uploadDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UPLOADDATE;
            finderArgs = new Object[] { uploadDate, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(uploadDate,
                            glBalanceMaster.getUploadDate())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindUploadDate = false;

            if (uploadDate == null) {
                query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_1);
            } else {
                bindUploadDate = true;

                query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUploadDate) {
                    qPos.add(CalendarUtil.getTimestamp(uploadDate));
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByUploadDate_First(Date uploadDate,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByUploadDate_First(uploadDate,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uploadDate=");
        msg.append(uploadDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByUploadDate_First(Date uploadDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByUploadDate(uploadDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByUploadDate_Last(Date uploadDate,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByUploadDate_Last(uploadDate,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uploadDate=");
        msg.append(uploadDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByUploadDate_Last(Date uploadDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUploadDate(uploadDate);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByUploadDate(uploadDate, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where uploadDate = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByUploadDate_PrevAndNext(
        int glBalanceMasterSid, Date uploadDate,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByUploadDate_PrevAndNext(session, glBalanceMaster,
                    uploadDate, orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByUploadDate_PrevAndNext(session, glBalanceMaster,
                    uploadDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByUploadDate_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, Date uploadDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindUploadDate = false;

        if (uploadDate == null) {
            query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_1);
        } else {
            bindUploadDate = true;

            query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUploadDate) {
            qPos.add(CalendarUtil.getTimestamp(uploadDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where uploadDate = &#63; from the database.
     *
     * @param uploadDate the upload date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUploadDate(Date uploadDate) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByUploadDate(uploadDate,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where uploadDate = &#63;.
     *
     * @param uploadDate the upload date
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUploadDate(Date uploadDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UPLOADDATE;

        Object[] finderArgs = new Object[] { uploadDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindUploadDate = false;

            if (uploadDate == null) {
                query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_1);
            } else {
                bindUploadDate = true;

                query.append(_FINDER_COLUMN_UPLOADDATE_UPLOADDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUploadDate) {
                    qPos.add(CalendarUtil.getTimestamp(uploadDate));
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
     * Returns all the gl balance masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountId(String accountId)
        throws SystemException {
        return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the gl balance masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountId(String accountId, int start,
        int end) throws SystemException {
        return findByAccountId(accountId, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByAccountId(String accountId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID;
            finderArgs = new Object[] { accountId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID;
            finderArgs = new Object[] { accountId, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(accountId, glBalanceMaster.getAccountId())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountId) {
                    qPos.add(accountId);
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByAccountId_First(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByAccountId_First(accountId,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByAccountId_First(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByAccountId(accountId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByAccountId_Last(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByAccountId_Last(accountId,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByAccountId_Last(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountId(accountId);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByAccountId(accountId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountId = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByAccountId_PrevAndNext(
        int glBalanceMasterSid, String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByAccountId_PrevAndNext(session, glBalanceMaster,
                    accountId, orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByAccountId_PrevAndNext(session, glBalanceMaster,
                    accountId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByAccountId_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, String accountId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindAccountId = false;

        if (accountId == null) {
            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
        } else if (accountId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
        } else {
            bindAccountId = true;

            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAccountId) {
            qPos.add(accountId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where accountId = &#63; from the database.
     *
     * @param accountId the account ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountId(String accountId) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByAccountId(accountId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAccountId(String accountId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTID;

        Object[] finderArgs = new Object[] { accountId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountId) {
                    qPos.add(accountId);
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
     * Returns all the gl balance masters where year = &#63;.
     *
     * @param year the year
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByYear(String year)
        throws SystemException {
        return findByYear(year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl balance masters where year = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param year the year
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByYear(String year, int start, int end)
        throws SystemException {
        return findByYear(year, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where year = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param year the year
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByYear(String year, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_YEAR;
            finderArgs = new Object[] { year };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_YEAR;
            finderArgs = new Object[] { year, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(year, glBalanceMaster.getYear())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindYear = false;

            if (year == null) {
                query.append(_FINDER_COLUMN_YEAR_YEAR_1);
            } else if (year.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_YEAR_YEAR_3);
            } else {
                bindYear = true;

                query.append(_FINDER_COLUMN_YEAR_YEAR_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindYear) {
                    qPos.add(year);
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where year = &#63;.
     *
     * @param year the year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByYear_First(String year,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByYear_First(year,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("year=");
        msg.append(year);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where year = &#63;.
     *
     * @param year the year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByYear_First(String year,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByYear(year, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where year = &#63;.
     *
     * @param year the year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByYear_Last(String year,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByYear_Last(year,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("year=");
        msg.append(year);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where year = &#63;.
     *
     * @param year the year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByYear_Last(String year,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByYear(year);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByYear(year, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where year = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param year the year
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByYear_PrevAndNext(int glBalanceMasterSid,
        String year, OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByYear_PrevAndNext(session, glBalanceMaster, year,
                    orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByYear_PrevAndNext(session, glBalanceMaster, year,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByYear_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, String year,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindYear = false;

        if (year == null) {
            query.append(_FINDER_COLUMN_YEAR_YEAR_1);
        } else if (year.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_YEAR_YEAR_3);
        } else {
            bindYear = true;

            query.append(_FINDER_COLUMN_YEAR_YEAR_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindYear) {
            qPos.add(year);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where year = &#63; from the database.
     *
     * @param year the year
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByYear(String year) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByYear(year,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where year = &#63;.
     *
     * @param year the year
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByYear(String year) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_YEAR;

        Object[] finderArgs = new Object[] { year };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindYear = false;

            if (year == null) {
                query.append(_FINDER_COLUMN_YEAR_YEAR_1);
            } else if (year.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_YEAR_YEAR_3);
            } else {
                bindYear = true;

                query.append(_FINDER_COLUMN_YEAR_YEAR_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindYear) {
                    qPos.add(year);
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
     * Returns all the gl balance masters where period = &#63;.
     *
     * @param period the period
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByPeriod(String period)
        throws SystemException {
        return findByPeriod(period, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl balance masters where period = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param period the period
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByPeriod(String period, int start, int end)
        throws SystemException {
        return findByPeriod(period, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where period = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param period the period
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByPeriod(String period, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD;
            finderArgs = new Object[] { period };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIOD;
            finderArgs = new Object[] { period, start, end, orderByComparator };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(period, glBalanceMaster.getPeriod())) {
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

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByPeriod_First(String period,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByPeriod_First(period,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("period=");
        msg.append(period);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByPeriod_First(String period,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlBalanceMaster> list = findByPeriod(period, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByPeriod_Last(String period,
        OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByPeriod_Last(period,
                orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("period=");
        msg.append(period);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByPeriod_Last(String period,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPeriod(period);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByPeriod(period, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where period = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByPeriod_PrevAndNext(int glBalanceMasterSid,
        String period, OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByPeriod_PrevAndNext(session, glBalanceMaster,
                    period, orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByPeriod_PrevAndNext(session, glBalanceMaster,
                    period, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByPeriod_PrevAndNext(Session session,
        GlBalanceMaster glBalanceMaster, String period,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindPeriod = false;

        if (period == null) {
            query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
        } else if (period.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
        } else {
            bindPeriod = true;

            query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindPeriod) {
            qPos.add(period);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where period = &#63; from the database.
     *
     * @param period the period
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPeriod(String period) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByPeriod(period,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where period = &#63;.
     *
     * @param period the period
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPeriod(String period) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PERIOD;

        Object[] finderArgs = new Object[] { period };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindPeriod) {
                    qPos.add(period);
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
     * Returns all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @return the matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByGlBalanceUnique(String accountNo,
        String period, Date uploadDate) throws SystemException {
        return findByGlBalanceUnique(accountNo, period, uploadDate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByGlBalanceUnique(String accountNo,
        String period, Date uploadDate, int start, int end)
        throws SystemException {
        return findByGlBalanceUnique(accountNo, period, uploadDate, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findByGlBalanceUnique(String accountNo,
        String period, Date uploadDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLBALANCEUNIQUE;
            finderArgs = new Object[] { accountNo, period, uploadDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GLBALANCEUNIQUE;
            finderArgs = new Object[] {
                    accountNo, period, uploadDate,
                    
                    start, end, orderByComparator
                };
        }

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlBalanceMaster glBalanceMaster : list) {
                if (!Validator.equals(accountNo, glBalanceMaster.getAccountNo()) ||
                        !Validator.equals(period, glBalanceMaster.getPeriod()) ||
                        !Validator.equals(uploadDate,
                            glBalanceMaster.getUploadDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(5);
            }

            query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_2);
            }

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_2);
            }

            boolean bindUploadDate = false;

            if (uploadDate == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_1);
            } else {
                bindUploadDate = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
                }

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (bindUploadDate) {
                    qPos.add(CalendarUtil.getTimestamp(uploadDate));
                }

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByGlBalanceUnique_First(String accountNo,
        String period, Date uploadDate, OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByGlBalanceUnique_First(accountNo,
                period, uploadDate, orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(", period=");
        msg.append(period);

        msg.append(", uploadDate=");
        msg.append(uploadDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByGlBalanceUnique_First(String accountNo,
        String period, Date uploadDate, OrderByComparator orderByComparator)
        throws SystemException {
        List<GlBalanceMaster> list = findByGlBalanceUnique(accountNo, period,
                uploadDate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByGlBalanceUnique_Last(String accountNo,
        String period, Date uploadDate, OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByGlBalanceUnique_Last(accountNo,
                period, uploadDate, orderByComparator);

        if (glBalanceMaster != null) {
            return glBalanceMaster;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(", period=");
        msg.append(period);

        msg.append(", uploadDate=");
        msg.append(uploadDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlBalanceMasterException(msg.toString());
    }

    /**
     * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByGlBalanceUnique_Last(String accountNo,
        String period, Date uploadDate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByGlBalanceUnique(accountNo, period, uploadDate);

        if (count == 0) {
            return null;
        }

        List<GlBalanceMaster> list = findByGlBalanceUnique(accountNo, period,
                uploadDate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param glBalanceMasterSid the primary key of the current gl balance master
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster[] findByGlBalanceUnique_PrevAndNext(
        int glBalanceMasterSid, String accountNo, String period,
        Date uploadDate, OrderByComparator orderByComparator)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = findByPrimaryKey(glBalanceMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster[] array = new GlBalanceMasterImpl[3];

            array[0] = getByGlBalanceUnique_PrevAndNext(session,
                    glBalanceMaster, accountNo, period, uploadDate,
                    orderByComparator, true);

            array[1] = glBalanceMaster;

            array[2] = getByGlBalanceUnique_PrevAndNext(session,
                    glBalanceMaster, accountNo, period, uploadDate,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlBalanceMaster getByGlBalanceUnique_PrevAndNext(
        Session session, GlBalanceMaster glBalanceMaster, String accountNo,
        String period, Date uploadDate, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLBALANCEMASTER_WHERE);

        boolean bindAccountNo = false;

        if (accountNo == null) {
            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_1);
        } else if (accountNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_3);
        } else {
            bindAccountNo = true;

            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_2);
        }

        boolean bindPeriod = false;

        if (period == null) {
            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_1);
        } else if (period.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_3);
        } else {
            bindPeriod = true;

            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_2);
        }

        boolean bindUploadDate = false;

        if (uploadDate == null) {
            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_1);
        } else {
            bindUploadDate = true;

            query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_2);
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
            query.append(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAccountNo) {
            qPos.add(accountNo);
        }

        if (bindPeriod) {
            qPos.add(period);
        }

        if (bindUploadDate) {
            qPos.add(CalendarUtil.getTimestamp(uploadDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glBalanceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlBalanceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63; from the database.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByGlBalanceUnique(String accountNo, String period,
        Date uploadDate) throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findByGlBalanceUnique(
                accountNo, period, uploadDate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
     *
     * @param accountNo the account no
     * @param period the period
     * @param uploadDate the upload date
     * @return the number of matching gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByGlBalanceUnique(String accountNo, String period,
        Date uploadDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GLBALANCEUNIQUE;

        Object[] finderArgs = new Object[] { accountNo, period, uploadDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_GLBALANCEMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_ACCOUNTNO_2);
            }

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_PERIOD_2);
            }

            boolean bindUploadDate = false;

            if (uploadDate == null) {
                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_1);
            } else {
                bindUploadDate = true;

                query.append(_FINDER_COLUMN_GLBALANCEUNIQUE_UPLOADDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
                }

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (bindUploadDate) {
                    qPos.add(CalendarUtil.getTimestamp(uploadDate));
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
     * Caches the gl balance master in the entity cache if it is enabled.
     *
     * @param glBalanceMaster the gl balance master
     */
    @Override
    public void cacheResult(GlBalanceMaster glBalanceMaster) {
        EntityCacheUtil.putResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterImpl.class, glBalanceMaster.getPrimaryKey(),
            glBalanceMaster);

        glBalanceMaster.resetOriginalValues();
    }

    /**
     * Caches the gl balance masters in the entity cache if it is enabled.
     *
     * @param glBalanceMasters the gl balance masters
     */
    @Override
    public void cacheResult(List<GlBalanceMaster> glBalanceMasters) {
        for (GlBalanceMaster glBalanceMaster : glBalanceMasters) {
            if (EntityCacheUtil.getResult(
                        GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
                        GlBalanceMasterImpl.class,
                        glBalanceMaster.getPrimaryKey()) == null) {
                cacheResult(glBalanceMaster);
            } else {
                glBalanceMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gl balance masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GlBalanceMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GlBalanceMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gl balance master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GlBalanceMaster glBalanceMaster) {
        EntityCacheUtil.removeResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterImpl.class, glBalanceMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GlBalanceMaster> glBalanceMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GlBalanceMaster glBalanceMaster : glBalanceMasters) {
            EntityCacheUtil.removeResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
                GlBalanceMasterImpl.class, glBalanceMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
     *
     * @param glBalanceMasterSid the primary key for the new gl balance master
     * @return the new gl balance master
     */
    @Override
    public GlBalanceMaster create(int glBalanceMasterSid) {
        GlBalanceMaster glBalanceMaster = new GlBalanceMasterImpl();

        glBalanceMaster.setNew(true);
        glBalanceMaster.setPrimaryKey(glBalanceMasterSid);

        return glBalanceMaster;
    }

    /**
     * Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param glBalanceMasterSid the primary key of the gl balance master
     * @return the gl balance master that was removed
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster remove(int glBalanceMasterSid)
        throws NoSuchGlBalanceMasterException, SystemException {
        return remove((Serializable) glBalanceMasterSid);
    }

    /**
     * Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gl balance master
     * @return the gl balance master that was removed
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster remove(Serializable primaryKey)
        throws NoSuchGlBalanceMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GlBalanceMaster glBalanceMaster = (GlBalanceMaster) session.get(GlBalanceMasterImpl.class,
                    primaryKey);

            if (glBalanceMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGlBalanceMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(glBalanceMaster);
        } catch (NoSuchGlBalanceMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GlBalanceMaster removeImpl(GlBalanceMaster glBalanceMaster)
        throws SystemException {
        glBalanceMaster = toUnwrappedModel(glBalanceMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(glBalanceMaster)) {
                glBalanceMaster = (GlBalanceMaster) session.get(GlBalanceMasterImpl.class,
                        glBalanceMaster.getPrimaryKeyObj());
            }

            if (glBalanceMaster != null) {
                session.delete(glBalanceMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (glBalanceMaster != null) {
            clearCache(glBalanceMaster);
        }

        return glBalanceMaster;
    }

    @Override
    public GlBalanceMaster updateImpl(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws SystemException {
        glBalanceMaster = toUnwrappedModel(glBalanceMaster);

        boolean isNew = glBalanceMaster.isNew();

        GlBalanceMasterModelImpl glBalanceMasterModelImpl = (GlBalanceMasterModelImpl) glBalanceMaster;

        Session session = null;

        try {
            session = openSession();

            if (glBalanceMaster.isNew()) {
                session.save(glBalanceMaster);

                glBalanceMaster.setNew(false);
            } else {
                session.merge(glBalanceMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !GlBalanceMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalAccountNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getAccountNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISACTIVE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalIsActive()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ISACTIVE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISACTIVE,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getIsActive() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ISACTIVE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISACTIVE,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UPLOADDATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalUploadDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UPLOADDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UPLOADDATE,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getUploadDate() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UPLOADDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UPLOADDATE,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalAccountId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getAccountId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_YEAR.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalYear()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_YEAR, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_YEAR,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getYear() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_YEAR, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_YEAR,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalPeriod()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PERIOD, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD,
                    args);

                args = new Object[] { glBalanceMasterModelImpl.getPeriod() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PERIOD, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD,
                    args);
            }

            if ((glBalanceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLBALANCEUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glBalanceMasterModelImpl.getOriginalAccountNo(),
                        glBalanceMasterModelImpl.getOriginalPeriod(),
                        glBalanceMasterModelImpl.getOriginalUploadDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLBALANCEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLBALANCEUNIQUE,
                    args);

                args = new Object[] {
                        glBalanceMasterModelImpl.getAccountNo(),
                        glBalanceMasterModelImpl.getPeriod(),
                        glBalanceMasterModelImpl.getUploadDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLBALANCEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLBALANCEUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlBalanceMasterImpl.class, glBalanceMaster.getPrimaryKey(),
            glBalanceMaster);

        return glBalanceMaster;
    }

    protected GlBalanceMaster toUnwrappedModel(GlBalanceMaster glBalanceMaster) {
        if (glBalanceMaster instanceof GlBalanceMasterImpl) {
            return glBalanceMaster;
        }

        GlBalanceMasterImpl glBalanceMasterImpl = new GlBalanceMasterImpl();

        glBalanceMasterImpl.setNew(glBalanceMaster.isNew());
        glBalanceMasterImpl.setPrimaryKey(glBalanceMaster.getPrimaryKey());

        glBalanceMasterImpl.setCreatedBy(glBalanceMaster.getCreatedBy());
        glBalanceMasterImpl.setModifiedBy(glBalanceMaster.getModifiedBy());
        glBalanceMasterImpl.setAccountId(glBalanceMaster.getAccountId());
        glBalanceMasterImpl.setUploadDate(glBalanceMaster.getUploadDate());
        glBalanceMasterImpl.setCreatedDate(glBalanceMaster.getCreatedDate());
        glBalanceMasterImpl.setGlBalanceMasterSid(glBalanceMaster.getGlBalanceMasterSid());
        glBalanceMasterImpl.setIsActive(glBalanceMaster.getIsActive());
        glBalanceMasterImpl.setBatchId(glBalanceMaster.getBatchId());
        glBalanceMasterImpl.setModifiedDate(glBalanceMaster.getModifiedDate());
        glBalanceMasterImpl.setBalance(glBalanceMaster.getBalance());
        glBalanceMasterImpl.setCloseIndicator(glBalanceMaster.getCloseIndicator());
        glBalanceMasterImpl.setRecordLockStatus(glBalanceMaster.isRecordLockStatus());
        glBalanceMasterImpl.setYear(glBalanceMaster.getYear());
        glBalanceMasterImpl.setPeriod(glBalanceMaster.getPeriod());
        glBalanceMasterImpl.setSource(glBalanceMaster.getSource());
        glBalanceMasterImpl.setInsertedDate(glBalanceMaster.getInsertedDate());
        glBalanceMasterImpl.setAccountNo(glBalanceMaster.getAccountNo());
        glBalanceMasterImpl.setInboundStatus(glBalanceMaster.getInboundStatus());

        return glBalanceMasterImpl;
    }

    /**
     * Returns the gl balance master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gl balance master
     * @return the gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGlBalanceMasterException, SystemException {
        GlBalanceMaster glBalanceMaster = fetchByPrimaryKey(primaryKey);

        if (glBalanceMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGlBalanceMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return glBalanceMaster;
    }

    /**
     * Returns the gl balance master with the primary key or throws a {@link com.stpl.app.NoSuchGlBalanceMasterException} if it could not be found.
     *
     * @param glBalanceMasterSid the primary key of the gl balance master
     * @return the gl balance master
     * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster findByPrimaryKey(int glBalanceMasterSid)
        throws NoSuchGlBalanceMasterException, SystemException {
        return findByPrimaryKey((Serializable) glBalanceMasterSid);
    }

    /**
     * Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gl balance master
     * @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GlBalanceMaster glBalanceMaster = (GlBalanceMaster) EntityCacheUtil.getResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
                GlBalanceMasterImpl.class, primaryKey);

        if (glBalanceMaster == _nullGlBalanceMaster) {
            return null;
        }

        if (glBalanceMaster == null) {
            Session session = null;

            try {
                session = openSession();

                glBalanceMaster = (GlBalanceMaster) session.get(GlBalanceMasterImpl.class,
                        primaryKey);

                if (glBalanceMaster != null) {
                    cacheResult(glBalanceMaster);
                } else {
                    EntityCacheUtil.putResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
                        GlBalanceMasterImpl.class, primaryKey,
                        _nullGlBalanceMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GlBalanceMasterModelImpl.ENTITY_CACHE_ENABLED,
                    GlBalanceMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return glBalanceMaster;
    }

    /**
     * Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param glBalanceMasterSid the primary key of the gl balance master
     * @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlBalanceMaster fetchByPrimaryKey(int glBalanceMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) glBalanceMasterSid);
    }

    /**
     * Returns all the gl balance masters.
     *
     * @return the gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl balance masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @return the range of gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gl balance masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gl balance masters
     * @param end the upper bound of the range of gl balance masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gl balance masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlBalanceMaster> findAll(int start, int end,
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

        List<GlBalanceMaster> list = (List<GlBalanceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GLBALANCEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GLBALANCEMASTER;

                if (pagination) {
                    sql = sql.concat(GlBalanceMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlBalanceMaster>(list);
                } else {
                    list = (List<GlBalanceMaster>) QueryUtil.list(q,
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
     * Removes all the gl balance masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GlBalanceMaster glBalanceMaster : findAll()) {
            remove(glBalanceMaster);
        }
    }

    /**
     * Returns the number of gl balance masters.
     *
     * @return the number of gl balance masters
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

                Query q = session.createQuery(_SQL_COUNT_GLBALANCEMASTER);

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
     * Initializes the gl balance master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GlBalanceMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GlBalanceMaster>> listenersList = new ArrayList<ModelListener<GlBalanceMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GlBalanceMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GlBalanceMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
