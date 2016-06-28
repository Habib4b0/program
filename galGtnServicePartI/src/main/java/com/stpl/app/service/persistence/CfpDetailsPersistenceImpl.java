package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCfpDetailsException;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.impl.CfpDetailsImpl;
import com.stpl.app.model.impl.CfpDetailsModelImpl;
import com.stpl.app.service.persistence.CfpDetailsPersistence;

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
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpDetailsPersistence
 * @see CfpDetailsUtil
 * @generated
 */
public class CfpDetailsPersistenceImpl extends BasePersistenceImpl<CfpDetails>
    implements CfpDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CfpDetailsUtil} to access the cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CfpDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPMODELSID =
        new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpModelSid",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID =
        new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpModelSid",
            new String[] { Integer.class.getName() },
            CfpDetailsModelImpl.CFPMODELSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPMODELSID = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpModelSid",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2 = "cfpDetails.cfpModelSid = ?";
    private static final String _SQL_SELECT_CFPDETAILS = "SELECT cfpDetails FROM CfpDetails cfpDetails";
    private static final String _SQL_SELECT_CFPDETAILS_WHERE = "SELECT cfpDetails FROM CfpDetails cfpDetails WHERE ";
    private static final String _SQL_COUNT_CFPDETAILS = "SELECT COUNT(cfpDetails) FROM CfpDetails cfpDetails";
    private static final String _SQL_COUNT_CFPDETAILS_WHERE = "SELECT COUNT(cfpDetails) FROM CfpDetails cfpDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cfpDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CfpDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CfpDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "companyCfpAttachedStatus", "tradeClass",
                "tradeClassEndDate", "modifiedBy", "companyStartDate",
                "tradeClassStartDate", "createdDate", "companyEndDate",
                "companyMasterSid", "companyCfpAttachedDate", "cfpModelSid",
                "batchId", "cfpDetailsSid", "modifiedDate", "recordLockStatus",
                "source", "inboundStatus"
            });
    private static CfpDetails _nullCfpDetails = new CfpDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CfpDetails> toCacheModel() {
                return _nullCfpDetailsCacheModel;
            }
        };

    private static CacheModel<CfpDetails> _nullCfpDetailsCacheModel = new CacheModel<CfpDetails>() {
            @Override
            public CfpDetails toEntityModel() {
                return _nullCfpDetails;
            }
        };

    public CfpDetailsPersistenceImpl() {
        setModelClass(CfpDetails.class);
    }

    /**
     * Returns all the cfp detailses where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @return the matching cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findByCfpModelSid(int cfpModelSid)
        throws SystemException {
        return findByCfpModelSid(cfpModelSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cfp detailses where cfpModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param cfpModelSid the cfp model sid
     * @param start the lower bound of the range of cfp detailses
     * @param end the upper bound of the range of cfp detailses (not inclusive)
     * @return the range of matching cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findByCfpModelSid(int cfpModelSid, int start,
        int end) throws SystemException {
        return findByCfpModelSid(cfpModelSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param cfpModelSid the cfp model sid
     * @param start the lower bound of the range of cfp detailses
     * @param end the upper bound of the range of cfp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findByCfpModelSid(int cfpModelSid, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID;
            finderArgs = new Object[] { cfpModelSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPMODELSID;
            finderArgs = new Object[] { cfpModelSid, start, end, orderByComparator };
        }

        List<CfpDetails> list = (List<CfpDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpDetails cfpDetails : list) {
                if ((cfpModelSid != cfpDetails.getCfpModelSid())) {
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

            query.append(_SQL_SELECT_CFPDETAILS_WHERE);

            query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpModelSid);

                if (!pagination) {
                    list = (List<CfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpDetails>(list);
                } else {
                    list = (List<CfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp details
     * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails findByCfpModelSid_First(int cfpModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchCfpDetailsException, SystemException {
        CfpDetails cfpDetails = fetchByCfpModelSid_First(cfpModelSid,
                orderByComparator);

        if (cfpDetails != null) {
            return cfpDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpModelSid=");
        msg.append(cfpModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpDetailsException(msg.toString());
    }

    /**
     * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails fetchByCfpModelSid_First(int cfpModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpDetails> list = findByCfpModelSid(cfpModelSid, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp details
     * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails findByCfpModelSid_Last(int cfpModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchCfpDetailsException, SystemException {
        CfpDetails cfpDetails = fetchByCfpModelSid_Last(cfpModelSid,
                orderByComparator);

        if (cfpDetails != null) {
            return cfpDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpModelSid=");
        msg.append(cfpModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpDetailsException(msg.toString());
    }

    /**
     * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails fetchByCfpModelSid_Last(int cfpModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpModelSid(cfpModelSid);

        if (count == 0) {
            return null;
        }

        List<CfpDetails> list = findByCfpModelSid(cfpModelSid, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
     *
     * @param cfpDetailsSid the primary key of the current cfp details
     * @param cfpModelSid the cfp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next cfp details
     * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails[] findByCfpModelSid_PrevAndNext(int cfpDetailsSid,
        int cfpModelSid, OrderByComparator orderByComparator)
        throws NoSuchCfpDetailsException, SystemException {
        CfpDetails cfpDetails = findByPrimaryKey(cfpDetailsSid);

        Session session = null;

        try {
            session = openSession();

            CfpDetails[] array = new CfpDetailsImpl[3];

            array[0] = getByCfpModelSid_PrevAndNext(session, cfpDetails,
                    cfpModelSid, orderByComparator, true);

            array[1] = cfpDetails;

            array[2] = getByCfpModelSid_PrevAndNext(session, cfpDetails,
                    cfpModelSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpDetails getByCfpModelSid_PrevAndNext(Session session,
        CfpDetails cfpDetails, int cfpModelSid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPDETAILS_WHERE);

        query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

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
            query.append(CfpDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(cfpModelSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp detailses where cfpModelSid = &#63; from the database.
     *
     * @param cfpModelSid the cfp model sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpModelSid(int cfpModelSid) throws SystemException {
        for (CfpDetails cfpDetails : findByCfpModelSid(cfpModelSid,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(cfpDetails);
        }
    }

    /**
     * Returns the number of cfp detailses where cfpModelSid = &#63;.
     *
     * @param cfpModelSid the cfp model sid
     * @return the number of matching cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpModelSid(int cfpModelSid) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPMODELSID;

        Object[] finderArgs = new Object[] { cfpModelSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPDETAILS_WHERE);

            query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpModelSid);

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
     * Caches the cfp details in the entity cache if it is enabled.
     *
     * @param cfpDetails the cfp details
     */
    @Override
    public void cacheResult(CfpDetails cfpDetails) {
        EntityCacheUtil.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsImpl.class, cfpDetails.getPrimaryKey(), cfpDetails);

        cfpDetails.resetOriginalValues();
    }

    /**
     * Caches the cfp detailses in the entity cache if it is enabled.
     *
     * @param cfpDetailses the cfp detailses
     */
    @Override
    public void cacheResult(List<CfpDetails> cfpDetailses) {
        for (CfpDetails cfpDetails : cfpDetailses) {
            if (EntityCacheUtil.getResult(
                        CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CfpDetailsImpl.class, cfpDetails.getPrimaryKey()) == null) {
                cacheResult(cfpDetails);
            } else {
                cfpDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cfp detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CfpDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CfpDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cfp details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CfpDetails cfpDetails) {
        EntityCacheUtil.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsImpl.class, cfpDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CfpDetails> cfpDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CfpDetails cfpDetails : cfpDetailses) {
            EntityCacheUtil.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CfpDetailsImpl.class, cfpDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cfp details with the primary key. Does not add the cfp details to the database.
     *
     * @param cfpDetailsSid the primary key for the new cfp details
     * @return the new cfp details
     */
    @Override
    public CfpDetails create(int cfpDetailsSid) {
        CfpDetails cfpDetails = new CfpDetailsImpl();

        cfpDetails.setNew(true);
        cfpDetails.setPrimaryKey(cfpDetailsSid);

        return cfpDetails;
    }

    /**
     * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cfpDetailsSid the primary key of the cfp details
     * @return the cfp details that was removed
     * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails remove(int cfpDetailsSid)
        throws NoSuchCfpDetailsException, SystemException {
        return remove((Serializable) cfpDetailsSid);
    }

    /**
     * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cfp details
     * @return the cfp details that was removed
     * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails remove(Serializable primaryKey)
        throws NoSuchCfpDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CfpDetails cfpDetails = (CfpDetails) session.get(CfpDetailsImpl.class,
                    primaryKey);

            if (cfpDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cfpDetails);
        } catch (NoSuchCfpDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CfpDetails removeImpl(CfpDetails cfpDetails)
        throws SystemException {
        cfpDetails = toUnwrappedModel(cfpDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cfpDetails)) {
                cfpDetails = (CfpDetails) session.get(CfpDetailsImpl.class,
                        cfpDetails.getPrimaryKeyObj());
            }

            if (cfpDetails != null) {
                session.delete(cfpDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cfpDetails != null) {
            clearCache(cfpDetails);
        }

        return cfpDetails;
    }

    @Override
    public CfpDetails updateImpl(com.stpl.app.model.CfpDetails cfpDetails)
        throws SystemException {
        cfpDetails = toUnwrappedModel(cfpDetails);

        boolean isNew = cfpDetails.isNew();

        CfpDetailsModelImpl cfpDetailsModelImpl = (CfpDetailsModelImpl) cfpDetails;

        Session session = null;

        try {
            session = openSession();

            if (cfpDetails.isNew()) {
                session.save(cfpDetails);

                cfpDetails.setNew(false);
            } else {
                session.merge(cfpDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((cfpDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpDetailsModelImpl.getOriginalCfpModelSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPMODELSID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID,
                    args);

                args = new Object[] { cfpDetailsModelImpl.getCfpModelSid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPMODELSID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID,
                    args);
            }
        }

        EntityCacheUtil.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpDetailsImpl.class, cfpDetails.getPrimaryKey(), cfpDetails);

        return cfpDetails;
    }

    protected CfpDetails toUnwrappedModel(CfpDetails cfpDetails) {
        if (cfpDetails instanceof CfpDetailsImpl) {
            return cfpDetails;
        }

        CfpDetailsImpl cfpDetailsImpl = new CfpDetailsImpl();

        cfpDetailsImpl.setNew(cfpDetails.isNew());
        cfpDetailsImpl.setPrimaryKey(cfpDetails.getPrimaryKey());

        cfpDetailsImpl.setCreatedBy(cfpDetails.getCreatedBy());
        cfpDetailsImpl.setCompanyCfpAttachedStatus(cfpDetails.getCompanyCfpAttachedStatus());
        cfpDetailsImpl.setTradeClass(cfpDetails.getTradeClass());
        cfpDetailsImpl.setTradeClassEndDate(cfpDetails.getTradeClassEndDate());
        cfpDetailsImpl.setModifiedBy(cfpDetails.getModifiedBy());
        cfpDetailsImpl.setCompanyStartDate(cfpDetails.getCompanyStartDate());
        cfpDetailsImpl.setTradeClassStartDate(cfpDetails.getTradeClassStartDate());
        cfpDetailsImpl.setCreatedDate(cfpDetails.getCreatedDate());
        cfpDetailsImpl.setCompanyEndDate(cfpDetails.getCompanyEndDate());
        cfpDetailsImpl.setCompanyMasterSid(cfpDetails.getCompanyMasterSid());
        cfpDetailsImpl.setCompanyCfpAttachedDate(cfpDetails.getCompanyCfpAttachedDate());
        cfpDetailsImpl.setCfpModelSid(cfpDetails.getCfpModelSid());
        cfpDetailsImpl.setBatchId(cfpDetails.getBatchId());
        cfpDetailsImpl.setCfpDetailsSid(cfpDetails.getCfpDetailsSid());
        cfpDetailsImpl.setModifiedDate(cfpDetails.getModifiedDate());
        cfpDetailsImpl.setRecordLockStatus(cfpDetails.isRecordLockStatus());
        cfpDetailsImpl.setSource(cfpDetails.getSource());
        cfpDetailsImpl.setInboundStatus(cfpDetails.getInboundStatus());

        return cfpDetailsImpl;
    }

    /**
     * Returns the cfp details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cfp details
     * @return the cfp details
     * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCfpDetailsException, SystemException {
        CfpDetails cfpDetails = fetchByPrimaryKey(primaryKey);

        if (cfpDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cfpDetails;
    }

    /**
     * Returns the cfp details with the primary key or throws a {@link com.stpl.app.NoSuchCfpDetailsException} if it could not be found.
     *
     * @param cfpDetailsSid the primary key of the cfp details
     * @return the cfp details
     * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails findByPrimaryKey(int cfpDetailsSid)
        throws NoSuchCfpDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cfpDetailsSid);
    }

    /**
     * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cfp details
     * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CfpDetails cfpDetails = (CfpDetails) EntityCacheUtil.getResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CfpDetailsImpl.class, primaryKey);

        if (cfpDetails == _nullCfpDetails) {
            return null;
        }

        if (cfpDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cfpDetails = (CfpDetails) session.get(CfpDetailsImpl.class,
                        primaryKey);

                if (cfpDetails != null) {
                    cacheResult(cfpDetails);
                } else {
                    EntityCacheUtil.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CfpDetailsImpl.class, primaryKey, _nullCfpDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CfpDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cfpDetails;
    }

    /**
     * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cfpDetailsSid the primary key of the cfp details
     * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpDetails fetchByPrimaryKey(int cfpDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cfpDetailsSid);
    }

    /**
     * Returns all the cfp detailses.
     *
     * @return the cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cfp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp detailses
     * @param end the upper bound of the range of cfp detailses (not inclusive)
     * @return the range of cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cfp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp detailses
     * @param end the upper bound of the range of cfp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cfp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpDetails> findAll(int start, int end,
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

        List<CfpDetails> list = (List<CfpDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFPDETAILS;

                if (pagination) {
                    sql = sql.concat(CfpDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpDetails>(list);
                } else {
                    list = (List<CfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the cfp detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CfpDetails cfpDetails : findAll()) {
            remove(cfpDetails);
        }
    }

    /**
     * Returns the number of cfp detailses.
     *
     * @return the number of cfp detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFPDETAILS);

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
     * Initializes the cfp details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CfpDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CfpDetails>> listenersList = new ArrayList<ModelListener<CfpDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CfpDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CfpDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
