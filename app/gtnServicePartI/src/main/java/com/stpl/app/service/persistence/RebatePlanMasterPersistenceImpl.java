package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRebatePlanMasterException;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.impl.RebatePlanMasterImpl;
import com.stpl.app.model.impl.RebatePlanMasterModelImpl;
import com.stpl.app.service.persistence.RebatePlanMasterPersistence;

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
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the rebate plan master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanMasterPersistence
 * @see RebatePlanMasterUtil
 * @generated
 */
public class RebatePlanMasterPersistenceImpl extends BasePersistenceImpl<RebatePlanMaster>
    implements RebatePlanMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RebatePlanMasterUtil} to access the rebate plan master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RebatePlanMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANID =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebatePlanId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANID =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRebatePlanId",
            new String[] { String.class.getName() },
            RebatePlanMasterModelImpl.REBATEPLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPLANID = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRebatePlanId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPLANID_REBATEPLANID_1 = "rebatePlanMaster.rebatePlanId IS NULL";
    private static final String _FINDER_COLUMN_REBATEPLANID_REBATEPLANID_2 = "rebatePlanMaster.rebatePlanId = ?";
    private static final String _FINDER_COLUMN_REBATEPLANID_REBATEPLANID_3 = "(rebatePlanMaster.rebatePlanId IS NULL OR rebatePlanMaster.rebatePlanId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANNO =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebatePlanNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNO =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRebatePlanNo",
            new String[] { String.class.getName() },
            RebatePlanMasterModelImpl.REBATEPLANNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPLANNO = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRebatePlanNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_1 = "rebatePlanMaster.rebatePlanNo IS NULL";
    private static final String _FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_2 = "rebatePlanMaster.rebatePlanNo = ?";
    private static final String _FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_3 = "(rebatePlanMaster.rebatePlanNo IS NULL OR rebatePlanMaster.rebatePlanNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANNAME =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebatePlanName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNAME =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRebatePlanName",
            new String[] { String.class.getName() },
            RebatePlanMasterModelImpl.REBATEPLANNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPLANNAME = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRebatePlanName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_1 = "rebatePlanMaster.rebatePlanName IS NULL";
    private static final String _FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_2 = "rebatePlanMaster.rebatePlanName = ?";
    private static final String _FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_3 = "(rebatePlanMaster.rebatePlanName IS NULL OR rebatePlanMaster.rebatePlanName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANSTATUS =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebatePlanStatus",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANSTATUS =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebatePlanStatus", new String[] { Integer.class.getName() },
            RebatePlanMasterModelImpl.REBATEPLANSTATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPLANSTATUS = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebatePlanStatus", new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPLANSTATUS_REBATEPLANSTATUS_2 =
        "rebatePlanMaster.rebatePlanStatus = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANTYPE =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebatePlanType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANTYPE =
        new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRebatePlanType",
            new String[] { Integer.class.getName() },
            RebatePlanMasterModelImpl.REBATEPLANTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPLANTYPE = new FinderPath(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRebatePlanType",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPLANTYPE_REBATEPLANTYPE_2 = "rebatePlanMaster.rebatePlanType = ?";
    private static final String _SQL_SELECT_REBATEPLANMASTER = "SELECT rebatePlanMaster FROM RebatePlanMaster rebatePlanMaster";
    private static final String _SQL_SELECT_REBATEPLANMASTER_WHERE = "SELECT rebatePlanMaster FROM RebatePlanMaster rebatePlanMaster WHERE ";
    private static final String _SQL_COUNT_REBATEPLANMASTER = "SELECT COUNT(rebatePlanMaster) FROM RebatePlanMaster rebatePlanMaster";
    private static final String _SQL_COUNT_REBATEPLANMASTER_WHERE = "SELECT COUNT(rebatePlanMaster) FROM RebatePlanMaster rebatePlanMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rebatePlanMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RebatePlanMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RebatePlanMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RebatePlanMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "selfGrowthIndicator", "rebateStructure", "marketShareFrom",
                "secondaryRebatePlanNo", "modifiedDate", "rebateRangeBasedOn",
                "cdrModelSid", "rebateRule", "createdDate", "createdBy",
                "source", "rebateBasedOn", "rebatePlanType", "rebatePlanId",
                "manfCompanyMasterSid", "modifiedBy", "inboundStatus",
                "secondaryRebatePlanId", "marketShareIndicator", "bogoEligible",
                "marketShareTo", "rebatePlanStatus", "rebatePlanMasterSid",
                "marketShareReference", "netSalesFormulaMasterSid",
                "selfGrowthFrom", "internalNotes", "secondaryRebatePlanName",
                "recordLockStatus", "rebatePlanName", "selfGrowthReference",
                "batchId", "formulaType", "selfGrowthTo", "rebatePlanNo"
            });
    private static RebatePlanMaster _nullRebatePlanMaster = new RebatePlanMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RebatePlanMaster> toCacheModel() {
                return _nullRebatePlanMasterCacheModel;
            }
        };

    private static CacheModel<RebatePlanMaster> _nullRebatePlanMasterCacheModel = new CacheModel<RebatePlanMaster>() {
            @Override
            public RebatePlanMaster toEntityModel() {
                return _nullRebatePlanMaster;
            }
        };

    public RebatePlanMasterPersistenceImpl() {
        setModelClass(RebatePlanMaster.class);
    }

    /**
     * Returns all the rebate plan masters where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @return the matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanId(String rebatePlanId)
        throws SystemException {
        return findByRebatePlanId(rebatePlanId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters where rebatePlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanId the rebate plan ID
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanId(String rebatePlanId,
        int start, int end) throws SystemException {
        return findByRebatePlanId(rebatePlanId, start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanId the rebate plan ID
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanId(String rebatePlanId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANID;
            finderArgs = new Object[] { rebatePlanId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANID;
            finderArgs = new Object[] {
                    rebatePlanId,
                    
                    start, end, orderByComparator
                };
        }

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RebatePlanMaster rebatePlanMaster : list) {
                if (!Validator.equals(rebatePlanId,
                            rebatePlanMaster.getRebatePlanId())) {
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

            query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanId = false;

            if (rebatePlanId == null) {
                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_1);
            } else if (rebatePlanId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_3);
            } else {
                bindRebatePlanId = true;

                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanId) {
                    qPos.add(rebatePlanId);
                }

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanId_First(String rebatePlanId,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanId_First(rebatePlanId,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanId=");
        msg.append(rebatePlanId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanId_First(String rebatePlanId,
        OrderByComparator orderByComparator) throws SystemException {
        List<RebatePlanMaster> list = findByRebatePlanId(rebatePlanId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanId_Last(String rebatePlanId,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanId_Last(rebatePlanId,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanId=");
        msg.append(rebatePlanId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanId_Last(String rebatePlanId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebatePlanId(rebatePlanId);

        if (count == 0) {
            return null;
        }

        List<RebatePlanMaster> list = findByRebatePlanId(rebatePlanId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanId = &#63;.
     *
     * @param rebatePlanMasterSid the primary key of the current rebate plan master
     * @param rebatePlanId the rebate plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster[] findByRebatePlanId_PrevAndNext(
        int rebatePlanMasterSid, String rebatePlanId,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = findByPrimaryKey(rebatePlanMasterSid);

        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster[] array = new RebatePlanMasterImpl[3];

            array[0] = getByRebatePlanId_PrevAndNext(session, rebatePlanMaster,
                    rebatePlanId, orderByComparator, true);

            array[1] = rebatePlanMaster;

            array[2] = getByRebatePlanId_PrevAndNext(session, rebatePlanMaster,
                    rebatePlanId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RebatePlanMaster getByRebatePlanId_PrevAndNext(Session session,
        RebatePlanMaster rebatePlanMaster, String rebatePlanId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

        boolean bindRebatePlanId = false;

        if (rebatePlanId == null) {
            query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_1);
        } else if (rebatePlanId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_3);
        } else {
            bindRebatePlanId = true;

            query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_2);
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
            query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRebatePlanId) {
            qPos.add(rebatePlanId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rebatePlanMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RebatePlanMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rebate plan masters where rebatePlanId = &#63; from the database.
     *
     * @param rebatePlanId the rebate plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebatePlanId(String rebatePlanId)
        throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findByRebatePlanId(
                rebatePlanId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters where rebatePlanId = &#63;.
     *
     * @param rebatePlanId the rebate plan ID
     * @return the number of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebatePlanId(String rebatePlanId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPLANID;

        Object[] finderArgs = new Object[] { rebatePlanId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanId = false;

            if (rebatePlanId == null) {
                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_1);
            } else if (rebatePlanId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_3);
            } else {
                bindRebatePlanId = true;

                query.append(_FINDER_COLUMN_REBATEPLANID_REBATEPLANID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanId) {
                    qPos.add(rebatePlanId);
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
     * Returns all the rebate plan masters where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @return the matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanNo(String rebatePlanNo)
        throws SystemException {
        return findByRebatePlanNo(rebatePlanNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters where rebatePlanNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanNo the rebate plan no
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanNo(String rebatePlanNo,
        int start, int end) throws SystemException {
        return findByRebatePlanNo(rebatePlanNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanNo the rebate plan no
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanNo(String rebatePlanNo,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNO;
            finderArgs = new Object[] { rebatePlanNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANNO;
            finderArgs = new Object[] {
                    rebatePlanNo,
                    
                    start, end, orderByComparator
                };
        }

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RebatePlanMaster rebatePlanMaster : list) {
                if (!Validator.equals(rebatePlanNo,
                            rebatePlanMaster.getRebatePlanNo())) {
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

            query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanNo = false;

            if (rebatePlanNo == null) {
                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_1);
            } else if (rebatePlanNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_3);
            } else {
                bindRebatePlanNo = true;

                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanNo) {
                    qPos.add(rebatePlanNo);
                }

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanNo_First(String rebatePlanNo,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanNo_First(rebatePlanNo,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanNo=");
        msg.append(rebatePlanNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanNo_First(String rebatePlanNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<RebatePlanMaster> list = findByRebatePlanNo(rebatePlanNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanNo_Last(String rebatePlanNo,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanNo_Last(rebatePlanNo,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanNo=");
        msg.append(rebatePlanNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanNo_Last(String rebatePlanNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebatePlanNo(rebatePlanNo);

        if (count == 0) {
            return null;
        }

        List<RebatePlanMaster> list = findByRebatePlanNo(rebatePlanNo,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanNo = &#63;.
     *
     * @param rebatePlanMasterSid the primary key of the current rebate plan master
     * @param rebatePlanNo the rebate plan no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster[] findByRebatePlanNo_PrevAndNext(
        int rebatePlanMasterSid, String rebatePlanNo,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = findByPrimaryKey(rebatePlanMasterSid);

        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster[] array = new RebatePlanMasterImpl[3];

            array[0] = getByRebatePlanNo_PrevAndNext(session, rebatePlanMaster,
                    rebatePlanNo, orderByComparator, true);

            array[1] = rebatePlanMaster;

            array[2] = getByRebatePlanNo_PrevAndNext(session, rebatePlanMaster,
                    rebatePlanNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RebatePlanMaster getByRebatePlanNo_PrevAndNext(Session session,
        RebatePlanMaster rebatePlanMaster, String rebatePlanNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

        boolean bindRebatePlanNo = false;

        if (rebatePlanNo == null) {
            query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_1);
        } else if (rebatePlanNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_3);
        } else {
            bindRebatePlanNo = true;

            query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_2);
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
            query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRebatePlanNo) {
            qPos.add(rebatePlanNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rebatePlanMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RebatePlanMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rebate plan masters where rebatePlanNo = &#63; from the database.
     *
     * @param rebatePlanNo the rebate plan no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebatePlanNo(String rebatePlanNo)
        throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findByRebatePlanNo(
                rebatePlanNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters where rebatePlanNo = &#63;.
     *
     * @param rebatePlanNo the rebate plan no
     * @return the number of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebatePlanNo(String rebatePlanNo)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPLANNO;

        Object[] finderArgs = new Object[] { rebatePlanNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanNo = false;

            if (rebatePlanNo == null) {
                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_1);
            } else if (rebatePlanNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_3);
            } else {
                bindRebatePlanNo = true;

                query.append(_FINDER_COLUMN_REBATEPLANNO_REBATEPLANNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanNo) {
                    qPos.add(rebatePlanNo);
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
     * Returns all the rebate plan masters where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @return the matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanName(String rebatePlanName)
        throws SystemException {
        return findByRebatePlanName(rebatePlanName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters where rebatePlanName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanName the rebate plan name
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanName(String rebatePlanName,
        int start, int end) throws SystemException {
        return findByRebatePlanName(rebatePlanName, start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanName the rebate plan name
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanName(String rebatePlanName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNAME;
            finderArgs = new Object[] { rebatePlanName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANNAME;
            finderArgs = new Object[] {
                    rebatePlanName,
                    
                    start, end, orderByComparator
                };
        }

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RebatePlanMaster rebatePlanMaster : list) {
                if (!Validator.equals(rebatePlanName,
                            rebatePlanMaster.getRebatePlanName())) {
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

            query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanName = false;

            if (rebatePlanName == null) {
                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_1);
            } else if (rebatePlanName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_3);
            } else {
                bindRebatePlanName = true;

                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanName) {
                    qPos.add(rebatePlanName);
                }

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanName_First(String rebatePlanName,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanName_First(rebatePlanName,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanName=");
        msg.append(rebatePlanName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanName_First(String rebatePlanName,
        OrderByComparator orderByComparator) throws SystemException {
        List<RebatePlanMaster> list = findByRebatePlanName(rebatePlanName, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanName_Last(String rebatePlanName,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanName_Last(rebatePlanName,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanName=");
        msg.append(rebatePlanName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanName_Last(String rebatePlanName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebatePlanName(rebatePlanName);

        if (count == 0) {
            return null;
        }

        List<RebatePlanMaster> list = findByRebatePlanName(rebatePlanName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanName = &#63;.
     *
     * @param rebatePlanMasterSid the primary key of the current rebate plan master
     * @param rebatePlanName the rebate plan name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster[] findByRebatePlanName_PrevAndNext(
        int rebatePlanMasterSid, String rebatePlanName,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = findByPrimaryKey(rebatePlanMasterSid);

        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster[] array = new RebatePlanMasterImpl[3];

            array[0] = getByRebatePlanName_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanName, orderByComparator, true);

            array[1] = rebatePlanMaster;

            array[2] = getByRebatePlanName_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RebatePlanMaster getByRebatePlanName_PrevAndNext(
        Session session, RebatePlanMaster rebatePlanMaster,
        String rebatePlanName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

        boolean bindRebatePlanName = false;

        if (rebatePlanName == null) {
            query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_1);
        } else if (rebatePlanName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_3);
        } else {
            bindRebatePlanName = true;

            query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_2);
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
            query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRebatePlanName) {
            qPos.add(rebatePlanName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rebatePlanMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RebatePlanMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rebate plan masters where rebatePlanName = &#63; from the database.
     *
     * @param rebatePlanName the rebate plan name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebatePlanName(String rebatePlanName)
        throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findByRebatePlanName(
                rebatePlanName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters where rebatePlanName = &#63;.
     *
     * @param rebatePlanName the rebate plan name
     * @return the number of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebatePlanName(String rebatePlanName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPLANNAME;

        Object[] finderArgs = new Object[] { rebatePlanName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REBATEPLANMASTER_WHERE);

            boolean bindRebatePlanName = false;

            if (rebatePlanName == null) {
                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_1);
            } else if (rebatePlanName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_3);
            } else {
                bindRebatePlanName = true;

                query.append(_FINDER_COLUMN_REBATEPLANNAME_REBATEPLANNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRebatePlanName) {
                    qPos.add(rebatePlanName);
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
     * Returns all the rebate plan masters where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @return the matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanStatus(int rebatePlanStatus)
        throws SystemException {
        return findByRebatePlanStatus(rebatePlanStatus, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters where rebatePlanStatus = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanStatus the rebate plan status
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanStatus(int rebatePlanStatus,
        int start, int end) throws SystemException {
        return findByRebatePlanStatus(rebatePlanStatus, start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanStatus the rebate plan status
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanStatus(int rebatePlanStatus,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANSTATUS;
            finderArgs = new Object[] { rebatePlanStatus };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANSTATUS;
            finderArgs = new Object[] {
                    rebatePlanStatus,
                    
                    start, end, orderByComparator
                };
        }

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RebatePlanMaster rebatePlanMaster : list) {
                if ((rebatePlanStatus != rebatePlanMaster.getRebatePlanStatus())) {
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

            query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

            query.append(_FINDER_COLUMN_REBATEPLANSTATUS_REBATEPLANSTATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebatePlanStatus);

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanStatus_First(int rebatePlanStatus,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanStatus_First(rebatePlanStatus,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanStatus=");
        msg.append(rebatePlanStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanStatus_First(
        int rebatePlanStatus, OrderByComparator orderByComparator)
        throws SystemException {
        List<RebatePlanMaster> list = findByRebatePlanStatus(rebatePlanStatus,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanStatus_Last(int rebatePlanStatus,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanStatus_Last(rebatePlanStatus,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanStatus=");
        msg.append(rebatePlanStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanStatus_Last(int rebatePlanStatus,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebatePlanStatus(rebatePlanStatus);

        if (count == 0) {
            return null;
        }

        List<RebatePlanMaster> list = findByRebatePlanStatus(rebatePlanStatus,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanMasterSid the primary key of the current rebate plan master
     * @param rebatePlanStatus the rebate plan status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster[] findByRebatePlanStatus_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanStatus,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = findByPrimaryKey(rebatePlanMasterSid);

        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster[] array = new RebatePlanMasterImpl[3];

            array[0] = getByRebatePlanStatus_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanStatus, orderByComparator, true);

            array[1] = rebatePlanMaster;

            array[2] = getByRebatePlanStatus_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanStatus, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RebatePlanMaster getByRebatePlanStatus_PrevAndNext(
        Session session, RebatePlanMaster rebatePlanMaster,
        int rebatePlanStatus, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

        query.append(_FINDER_COLUMN_REBATEPLANSTATUS_REBATEPLANSTATUS_2);

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
            query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(rebatePlanStatus);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rebatePlanMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RebatePlanMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rebate plan masters where rebatePlanStatus = &#63; from the database.
     *
     * @param rebatePlanStatus the rebate plan status
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebatePlanStatus(int rebatePlanStatus)
        throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findByRebatePlanStatus(
                rebatePlanStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters where rebatePlanStatus = &#63;.
     *
     * @param rebatePlanStatus the rebate plan status
     * @return the number of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebatePlanStatus(int rebatePlanStatus)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPLANSTATUS;

        Object[] finderArgs = new Object[] { rebatePlanStatus };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REBATEPLANMASTER_WHERE);

            query.append(_FINDER_COLUMN_REBATEPLANSTATUS_REBATEPLANSTATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebatePlanStatus);

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
     * Returns all the rebate plan masters where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @return the matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanType(int rebatePlanType)
        throws SystemException {
        return findByRebatePlanType(rebatePlanType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters where rebatePlanType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanType the rebate plan type
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanType(int rebatePlanType,
        int start, int end) throws SystemException {
        return findByRebatePlanType(rebatePlanType, start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param rebatePlanType the rebate plan type
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findByRebatePlanType(int rebatePlanType,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANTYPE;
            finderArgs = new Object[] { rebatePlanType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPLANTYPE;
            finderArgs = new Object[] {
                    rebatePlanType,
                    
                    start, end, orderByComparator
                };
        }

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RebatePlanMaster rebatePlanMaster : list) {
                if ((rebatePlanType != rebatePlanMaster.getRebatePlanType())) {
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

            query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

            query.append(_FINDER_COLUMN_REBATEPLANTYPE_REBATEPLANTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebatePlanType);

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanType_First(int rebatePlanType,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanType_First(rebatePlanType,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanType=");
        msg.append(rebatePlanType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanType_First(int rebatePlanType,
        OrderByComparator orderByComparator) throws SystemException {
        List<RebatePlanMaster> list = findByRebatePlanType(rebatePlanType, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByRebatePlanType_Last(int rebatePlanType,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByRebatePlanType_Last(rebatePlanType,
                orderByComparator);

        if (rebatePlanMaster != null) {
            return rebatePlanMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebatePlanType=");
        msg.append(rebatePlanType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRebatePlanMasterException(msg.toString());
    }

    /**
     * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByRebatePlanType_Last(int rebatePlanType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebatePlanType(rebatePlanType);

        if (count == 0) {
            return null;
        }

        List<RebatePlanMaster> list = findByRebatePlanType(rebatePlanType,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanType = &#63;.
     *
     * @param rebatePlanMasterSid the primary key of the current rebate plan master
     * @param rebatePlanType the rebate plan type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster[] findByRebatePlanType_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanType,
        OrderByComparator orderByComparator)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = findByPrimaryKey(rebatePlanMasterSid);

        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster[] array = new RebatePlanMasterImpl[3];

            array[0] = getByRebatePlanType_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanType, orderByComparator, true);

            array[1] = rebatePlanMaster;

            array[2] = getByRebatePlanType_PrevAndNext(session,
                    rebatePlanMaster, rebatePlanType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RebatePlanMaster getByRebatePlanType_PrevAndNext(
        Session session, RebatePlanMaster rebatePlanMaster, int rebatePlanType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REBATEPLANMASTER_WHERE);

        query.append(_FINDER_COLUMN_REBATEPLANTYPE_REBATEPLANTYPE_2);

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
            query.append(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(rebatePlanType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rebatePlanMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RebatePlanMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rebate plan masters where rebatePlanType = &#63; from the database.
     *
     * @param rebatePlanType the rebate plan type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebatePlanType(int rebatePlanType)
        throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findByRebatePlanType(
                rebatePlanType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters where rebatePlanType = &#63;.
     *
     * @param rebatePlanType the rebate plan type
     * @return the number of matching rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebatePlanType(int rebatePlanType)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPLANTYPE;

        Object[] finderArgs = new Object[] { rebatePlanType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REBATEPLANMASTER_WHERE);

            query.append(_FINDER_COLUMN_REBATEPLANTYPE_REBATEPLANTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebatePlanType);

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
     * Caches the rebate plan master in the entity cache if it is enabled.
     *
     * @param rebatePlanMaster the rebate plan master
     */
    @Override
    public void cacheResult(RebatePlanMaster rebatePlanMaster) {
        EntityCacheUtil.putResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterImpl.class, rebatePlanMaster.getPrimaryKey(),
            rebatePlanMaster);

        rebatePlanMaster.resetOriginalValues();
    }

    /**
     * Caches the rebate plan masters in the entity cache if it is enabled.
     *
     * @param rebatePlanMasters the rebate plan masters
     */
    @Override
    public void cacheResult(List<RebatePlanMaster> rebatePlanMasters) {
        for (RebatePlanMaster rebatePlanMaster : rebatePlanMasters) {
            if (EntityCacheUtil.getResult(
                        RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
                        RebatePlanMasterImpl.class,
                        rebatePlanMaster.getPrimaryKey()) == null) {
                cacheResult(rebatePlanMaster);
            } else {
                rebatePlanMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rebate plan masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RebatePlanMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RebatePlanMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rebate plan master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RebatePlanMaster rebatePlanMaster) {
        EntityCacheUtil.removeResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterImpl.class, rebatePlanMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RebatePlanMaster> rebatePlanMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RebatePlanMaster rebatePlanMaster : rebatePlanMasters) {
            EntityCacheUtil.removeResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
                RebatePlanMasterImpl.class, rebatePlanMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
     *
     * @param rebatePlanMasterSid the primary key for the new rebate plan master
     * @return the new rebate plan master
     */
    @Override
    public RebatePlanMaster create(int rebatePlanMasterSid) {
        RebatePlanMaster rebatePlanMaster = new RebatePlanMasterImpl();

        rebatePlanMaster.setNew(true);
        rebatePlanMaster.setPrimaryKey(rebatePlanMasterSid);

        return rebatePlanMaster;
    }

    /**
     * Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rebatePlanMasterSid the primary key of the rebate plan master
     * @return the rebate plan master that was removed
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster remove(int rebatePlanMasterSid)
        throws NoSuchRebatePlanMasterException, SystemException {
        return remove((Serializable) rebatePlanMasterSid);
    }

    /**
     * Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rebate plan master
     * @return the rebate plan master that was removed
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster remove(Serializable primaryKey)
        throws NoSuchRebatePlanMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RebatePlanMaster rebatePlanMaster = (RebatePlanMaster) session.get(RebatePlanMasterImpl.class,
                    primaryKey);

            if (rebatePlanMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRebatePlanMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rebatePlanMaster);
        } catch (NoSuchRebatePlanMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RebatePlanMaster removeImpl(RebatePlanMaster rebatePlanMaster)
        throws SystemException {
        rebatePlanMaster = toUnwrappedModel(rebatePlanMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rebatePlanMaster)) {
                rebatePlanMaster = (RebatePlanMaster) session.get(RebatePlanMasterImpl.class,
                        rebatePlanMaster.getPrimaryKeyObj());
            }

            if (rebatePlanMaster != null) {
                session.delete(rebatePlanMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rebatePlanMaster != null) {
            clearCache(rebatePlanMaster);
        }

        return rebatePlanMaster;
    }

    @Override
    public RebatePlanMaster updateImpl(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws SystemException {
        rebatePlanMaster = toUnwrappedModel(rebatePlanMaster);

        boolean isNew = rebatePlanMaster.isNew();

        RebatePlanMasterModelImpl rebatePlanMasterModelImpl = (RebatePlanMasterModelImpl) rebatePlanMaster;

        Session session = null;

        try {
            session = openSession();

            if (rebatePlanMaster.isNew()) {
                session.save(rebatePlanMaster);

                rebatePlanMaster.setNew(false);
            } else {
                session.merge(rebatePlanMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !RebatePlanMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((rebatePlanMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rebatePlanMasterModelImpl.getOriginalRebatePlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANID,
                    args);

                args = new Object[] { rebatePlanMasterModelImpl.getRebatePlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANID,
                    args);
            }

            if ((rebatePlanMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rebatePlanMasterModelImpl.getOriginalRebatePlanNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNO,
                    args);

                args = new Object[] { rebatePlanMasterModelImpl.getRebatePlanNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNO,
                    args);
            }

            if ((rebatePlanMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rebatePlanMasterModelImpl.getOriginalRebatePlanName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNAME,
                    args);

                args = new Object[] {
                        rebatePlanMasterModelImpl.getRebatePlanName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANNAME,
                    args);
            }

            if ((rebatePlanMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANSTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rebatePlanMasterModelImpl.getOriginalRebatePlanStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANSTATUS,
                    args);

                args = new Object[] {
                        rebatePlanMasterModelImpl.getRebatePlanStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANSTATUS,
                    args);
            }

            if ((rebatePlanMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rebatePlanMasterModelImpl.getOriginalRebatePlanType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANTYPE,
                    args);

                args = new Object[] {
                        rebatePlanMasterModelImpl.getRebatePlanType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPLANTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPLANTYPE,
                    args);
            }
        }

        EntityCacheUtil.putResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanMasterImpl.class, rebatePlanMaster.getPrimaryKey(),
            rebatePlanMaster);

        return rebatePlanMaster;
    }

    protected RebatePlanMaster toUnwrappedModel(
        RebatePlanMaster rebatePlanMaster) {
        if (rebatePlanMaster instanceof RebatePlanMasterImpl) {
            return rebatePlanMaster;
        }

        RebatePlanMasterImpl rebatePlanMasterImpl = new RebatePlanMasterImpl();

        rebatePlanMasterImpl.setNew(rebatePlanMaster.isNew());
        rebatePlanMasterImpl.setPrimaryKey(rebatePlanMaster.getPrimaryKey());

        rebatePlanMasterImpl.setSelfGrowthIndicator(rebatePlanMaster.getSelfGrowthIndicator());
        rebatePlanMasterImpl.setRebateStructure(rebatePlanMaster.getRebateStructure());
        rebatePlanMasterImpl.setMarketShareFrom(rebatePlanMaster.getMarketShareFrom());
        rebatePlanMasterImpl.setSecondaryRebatePlanNo(rebatePlanMaster.getSecondaryRebatePlanNo());
        rebatePlanMasterImpl.setModifiedDate(rebatePlanMaster.getModifiedDate());
        rebatePlanMasterImpl.setRebateRangeBasedOn(rebatePlanMaster.getRebateRangeBasedOn());
        rebatePlanMasterImpl.setCdrModelSid(rebatePlanMaster.getCdrModelSid());
        rebatePlanMasterImpl.setRebateRule(rebatePlanMaster.getRebateRule());
        rebatePlanMasterImpl.setCreatedDate(rebatePlanMaster.getCreatedDate());
        rebatePlanMasterImpl.setCreatedBy(rebatePlanMaster.getCreatedBy());
        rebatePlanMasterImpl.setSource(rebatePlanMaster.getSource());
        rebatePlanMasterImpl.setRebateBasedOn(rebatePlanMaster.getRebateBasedOn());
        rebatePlanMasterImpl.setRebatePlanType(rebatePlanMaster.getRebatePlanType());
        rebatePlanMasterImpl.setRebatePlanId(rebatePlanMaster.getRebatePlanId());
        rebatePlanMasterImpl.setManfCompanyMasterSid(rebatePlanMaster.getManfCompanyMasterSid());
        rebatePlanMasterImpl.setModifiedBy(rebatePlanMaster.getModifiedBy());
        rebatePlanMasterImpl.setInboundStatus(rebatePlanMaster.getInboundStatus());
        rebatePlanMasterImpl.setSecondaryRebatePlanId(rebatePlanMaster.getSecondaryRebatePlanId());
        rebatePlanMasterImpl.setMarketShareIndicator(rebatePlanMaster.getMarketShareIndicator());
        rebatePlanMasterImpl.setBogoEligible(rebatePlanMaster.getBogoEligible());
        rebatePlanMasterImpl.setMarketShareTo(rebatePlanMaster.getMarketShareTo());
        rebatePlanMasterImpl.setRebatePlanStatus(rebatePlanMaster.getRebatePlanStatus());
        rebatePlanMasterImpl.setRebatePlanMasterSid(rebatePlanMaster.getRebatePlanMasterSid());
        rebatePlanMasterImpl.setMarketShareReference(rebatePlanMaster.getMarketShareReference());
        rebatePlanMasterImpl.setNetSalesFormulaMasterSid(rebatePlanMaster.getNetSalesFormulaMasterSid());
        rebatePlanMasterImpl.setSelfGrowthFrom(rebatePlanMaster.getSelfGrowthFrom());
        rebatePlanMasterImpl.setInternalNotes(rebatePlanMaster.getInternalNotes());
        rebatePlanMasterImpl.setSecondaryRebatePlanName(rebatePlanMaster.getSecondaryRebatePlanName());
        rebatePlanMasterImpl.setRecordLockStatus(rebatePlanMaster.isRecordLockStatus());
        rebatePlanMasterImpl.setRebatePlanName(rebatePlanMaster.getRebatePlanName());
        rebatePlanMasterImpl.setSelfGrowthReference(rebatePlanMaster.getSelfGrowthReference());
        rebatePlanMasterImpl.setBatchId(rebatePlanMaster.getBatchId());
        rebatePlanMasterImpl.setFormulaType(rebatePlanMaster.getFormulaType());
        rebatePlanMasterImpl.setSelfGrowthTo(rebatePlanMaster.getSelfGrowthTo());
        rebatePlanMasterImpl.setRebatePlanNo(rebatePlanMaster.getRebatePlanNo());

        return rebatePlanMasterImpl;
    }

    /**
     * Returns the rebate plan master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rebate plan master
     * @return the rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRebatePlanMasterException, SystemException {
        RebatePlanMaster rebatePlanMaster = fetchByPrimaryKey(primaryKey);

        if (rebatePlanMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRebatePlanMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rebatePlanMaster;
    }

    /**
     * Returns the rebate plan master with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanMasterException} if it could not be found.
     *
     * @param rebatePlanMasterSid the primary key of the rebate plan master
     * @return the rebate plan master
     * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster findByPrimaryKey(int rebatePlanMasterSid)
        throws NoSuchRebatePlanMasterException, SystemException {
        return findByPrimaryKey((Serializable) rebatePlanMasterSid);
    }

    /**
     * Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rebate plan master
     * @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RebatePlanMaster rebatePlanMaster = (RebatePlanMaster) EntityCacheUtil.getResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
                RebatePlanMasterImpl.class, primaryKey);

        if (rebatePlanMaster == _nullRebatePlanMaster) {
            return null;
        }

        if (rebatePlanMaster == null) {
            Session session = null;

            try {
                session = openSession();

                rebatePlanMaster = (RebatePlanMaster) session.get(RebatePlanMasterImpl.class,
                        primaryKey);

                if (rebatePlanMaster != null) {
                    cacheResult(rebatePlanMaster);
                } else {
                    EntityCacheUtil.putResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
                        RebatePlanMasterImpl.class, primaryKey,
                        _nullRebatePlanMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RebatePlanMasterModelImpl.ENTITY_CACHE_ENABLED,
                    RebatePlanMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rebatePlanMaster;
    }

    /**
     * Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rebatePlanMasterSid the primary key of the rebate plan master
     * @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanMaster fetchByPrimaryKey(int rebatePlanMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rebatePlanMasterSid);
    }

    /**
     * Returns all the rebate plan masters.
     *
     * @return the rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @return the range of rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate plan masters
     * @param end the upper bound of the range of rebate plan masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rebate plan masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanMaster> findAll(int start, int end,
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

        List<RebatePlanMaster> list = (List<RebatePlanMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_REBATEPLANMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_REBATEPLANMASTER;

                if (pagination) {
                    sql = sql.concat(RebatePlanMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanMaster>(list);
                } else {
                    list = (List<RebatePlanMaster>) QueryUtil.list(q,
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
     * Removes all the rebate plan masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RebatePlanMaster rebatePlanMaster : findAll()) {
            remove(rebatePlanMaster);
        }
    }

    /**
     * Returns the number of rebate plan masters.
     *
     * @return the number of rebate plan masters
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

                Query q = session.createQuery(_SQL_COUNT_REBATEPLANMASTER);

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
     * Initializes the rebate plan master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RebatePlanMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RebatePlanMaster>> listenersList = new ArrayList<ModelListener<RebatePlanMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RebatePlanMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RebatePlanMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
