package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIfpDetailsException;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.impl.IfpDetailsImpl;
import com.stpl.app.model.impl.IfpDetailsModelImpl;
import com.stpl.app.service.persistence.IfpDetailsPersistence;

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
 * The persistence implementation for the ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpDetailsPersistence
 * @see IfpDetailsUtil
 * @generated
 */
public class IfpDetailsPersistenceImpl extends BasePersistenceImpl<IfpDetails>
    implements IfpDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IfpDetailsUtil} to access the ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IfpDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS =
        new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemFamilyPlanDetails",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS =
        new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanDetails",
            new String[] { Integer.class.getName() },
            IfpDetailsModelImpl.IFPMODELSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanDetails",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2 =
        "ifpDetails.ifpModelSid = ?";
    private static final String _SQL_SELECT_IFPDETAILS = "SELECT ifpDetails FROM IfpDetails ifpDetails";
    private static final String _SQL_SELECT_IFPDETAILS_WHERE = "SELECT ifpDetails FROM IfpDetails ifpDetails WHERE ";
    private static final String _SQL_COUNT_IFPDETAILS = "SELECT COUNT(ifpDetails) FROM IfpDetails ifpDetails";
    private static final String _SQL_COUNT_IFPDETAILS_WHERE = "SELECT COUNT(ifpDetails) FROM IfpDetails ifpDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ifpDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IfpDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IfpDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "endDate", "modifiedDate", "recordLockStatus",
                "startDate", "createdDate", "source", "createdBy",
                "itemIfpAttachedDate", "batchId", "modifiedBy", "inboundStatus",
                "ifpDetailsSid", "ifpModelSid", "itemIfpAttachedStatus"
            });
    private static IfpDetails _nullIfpDetails = new IfpDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IfpDetails> toCacheModel() {
                return _nullIfpDetailsCacheModel;
            }
        };

    private static CacheModel<IfpDetails> _nullIfpDetailsCacheModel = new CacheModel<IfpDetails>() {
            @Override
            public IfpDetails toEntityModel() {
                return _nullIfpDetails;
            }
        };

    public IfpDetailsPersistenceImpl() {
        setModelClass(IfpDetails.class);
    }

    /**
     * Returns all the ifp detailses where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @return the matching ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid)
        throws SystemException {
        return findByItemFamilyPlanDetails(ifpModelSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ifp detailses where ifpModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ifpModelSid the ifp model sid
     * @param start the lower bound of the range of ifp detailses
     * @param end the upper bound of the range of ifp detailses (not inclusive)
     * @return the range of matching ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid,
        int start, int end) throws SystemException {
        return findByItemFamilyPlanDetails(ifpModelSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ifpModelSid the ifp model sid
     * @param start the lower bound of the range of ifp detailses
     * @param end the upper bound of the range of ifp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS;
            finderArgs = new Object[] { ifpModelSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS;
            finderArgs = new Object[] { ifpModelSid, start, end, orderByComparator };
        }

        List<IfpDetails> list = (List<IfpDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpDetails ifpDetails : list) {
                if ((ifpModelSid != ifpDetails.getIfpModelSid())) {
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

            query.append(_SQL_SELECT_IFPDETAILS_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpModelSid);

                if (!pagination) {
                    list = (List<IfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpDetails>(list);
                } else {
                    list = (List<IfpDetails>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp details
     * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails findByItemFamilyPlanDetails_First(int ifpModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchIfpDetailsException, SystemException {
        IfpDetails ifpDetails = fetchByItemFamilyPlanDetails_First(ifpModelSid,
                orderByComparator);

        if (ifpDetails != null) {
            return ifpDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpModelSid=");
        msg.append(ifpModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpDetailsException(msg.toString());
    }

    /**
     * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails fetchByItemFamilyPlanDetails_First(int ifpModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpDetails> list = findByItemFamilyPlanDetails(ifpModelSid, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp details
     * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails findByItemFamilyPlanDetails_Last(int ifpModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchIfpDetailsException, SystemException {
        IfpDetails ifpDetails = fetchByItemFamilyPlanDetails_Last(ifpModelSid,
                orderByComparator);

        if (ifpDetails != null) {
            return ifpDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpModelSid=");
        msg.append(ifpModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpDetailsException(msg.toString());
    }

    /**
     * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails fetchByItemFamilyPlanDetails_Last(int ifpModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanDetails(ifpModelSid);

        if (count == 0) {
            return null;
        }

        List<IfpDetails> list = findByItemFamilyPlanDetails(ifpModelSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
     *
     * @param ifpDetailsSid the primary key of the current ifp details
     * @param ifpModelSid the ifp model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ifp details
     * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
        int ifpDetailsSid, int ifpModelSid, OrderByComparator orderByComparator)
        throws NoSuchIfpDetailsException, SystemException {
        IfpDetails ifpDetails = findByPrimaryKey(ifpDetailsSid);

        Session session = null;

        try {
            session = openSession();

            IfpDetails[] array = new IfpDetailsImpl[3];

            array[0] = getByItemFamilyPlanDetails_PrevAndNext(session,
                    ifpDetails, ifpModelSid, orderByComparator, true);

            array[1] = ifpDetails;

            array[2] = getByItemFamilyPlanDetails_PrevAndNext(session,
                    ifpDetails, ifpModelSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpDetails getByItemFamilyPlanDetails_PrevAndNext(
        Session session, IfpDetails ifpDetails, int ifpModelSid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPDETAILS_WHERE);

        query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

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
            query.append(IfpDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ifpModelSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp detailses where ifpModelSid = &#63; from the database.
     *
     * @param ifpModelSid the ifp model sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanDetails(int ifpModelSid)
        throws SystemException {
        for (IfpDetails ifpDetails : findByItemFamilyPlanDetails(ifpModelSid,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpDetails);
        }
    }

    /**
     * Returns the number of ifp detailses where ifpModelSid = &#63;.
     *
     * @param ifpModelSid the ifp model sid
     * @return the number of matching ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanDetails(int ifpModelSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS;

        Object[] finderArgs = new Object[] { ifpModelSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPDETAILS_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpModelSid);

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
     * Caches the ifp details in the entity cache if it is enabled.
     *
     * @param ifpDetails the ifp details
     */
    @Override
    public void cacheResult(IfpDetails ifpDetails) {
        EntityCacheUtil.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsImpl.class, ifpDetails.getPrimaryKey(), ifpDetails);

        ifpDetails.resetOriginalValues();
    }

    /**
     * Caches the ifp detailses in the entity cache if it is enabled.
     *
     * @param ifpDetailses the ifp detailses
     */
    @Override
    public void cacheResult(List<IfpDetails> ifpDetailses) {
        for (IfpDetails ifpDetails : ifpDetailses) {
            if (EntityCacheUtil.getResult(
                        IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IfpDetailsImpl.class, ifpDetails.getPrimaryKey()) == null) {
                cacheResult(ifpDetails);
            } else {
                ifpDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ifp detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IfpDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IfpDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ifp details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IfpDetails ifpDetails) {
        EntityCacheUtil.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsImpl.class, ifpDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IfpDetails> ifpDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IfpDetails ifpDetails : ifpDetailses) {
            EntityCacheUtil.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IfpDetailsImpl.class, ifpDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
     *
     * @param ifpDetailsSid the primary key for the new ifp details
     * @return the new ifp details
     */
    @Override
    public IfpDetails create(int ifpDetailsSid) {
        IfpDetails ifpDetails = new IfpDetailsImpl();

        ifpDetails.setNew(true);
        ifpDetails.setPrimaryKey(ifpDetailsSid);

        return ifpDetails;
    }

    /**
     * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ifpDetailsSid the primary key of the ifp details
     * @return the ifp details that was removed
     * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails remove(int ifpDetailsSid)
        throws NoSuchIfpDetailsException, SystemException {
        return remove((Serializable) ifpDetailsSid);
    }

    /**
     * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ifp details
     * @return the ifp details that was removed
     * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails remove(Serializable primaryKey)
        throws NoSuchIfpDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IfpDetails ifpDetails = (IfpDetails) session.get(IfpDetailsImpl.class,
                    primaryKey);

            if (ifpDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ifpDetails);
        } catch (NoSuchIfpDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IfpDetails removeImpl(IfpDetails ifpDetails)
        throws SystemException {
        ifpDetails = toUnwrappedModel(ifpDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ifpDetails)) {
                ifpDetails = (IfpDetails) session.get(IfpDetailsImpl.class,
                        ifpDetails.getPrimaryKeyObj());
            }

            if (ifpDetails != null) {
                session.delete(ifpDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ifpDetails != null) {
            clearCache(ifpDetails);
        }

        return ifpDetails;
    }

    @Override
    public IfpDetails updateImpl(com.stpl.app.model.IfpDetails ifpDetails)
        throws SystemException {
        ifpDetails = toUnwrappedModel(ifpDetails);

        boolean isNew = ifpDetails.isNew();

        IfpDetailsModelImpl ifpDetailsModelImpl = (IfpDetailsModelImpl) ifpDetails;

        Session session = null;

        try {
            session = openSession();

            if (ifpDetails.isNew()) {
                session.save(ifpDetails);

                ifpDetails.setNew(false);
            } else {
                session.merge(ifpDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ifpDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpDetailsModelImpl.getOriginalIfpModelSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS,
                    args);

                args = new Object[] { ifpDetailsModelImpl.getIfpModelSid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpDetailsImpl.class, ifpDetails.getPrimaryKey(), ifpDetails);

        return ifpDetails;
    }

    protected IfpDetails toUnwrappedModel(IfpDetails ifpDetails) {
        if (ifpDetails instanceof IfpDetailsImpl) {
            return ifpDetails;
        }

        IfpDetailsImpl ifpDetailsImpl = new IfpDetailsImpl();

        ifpDetailsImpl.setNew(ifpDetails.isNew());
        ifpDetailsImpl.setPrimaryKey(ifpDetails.getPrimaryKey());

        ifpDetailsImpl.setItemMasterSid(ifpDetails.getItemMasterSid());
        ifpDetailsImpl.setEndDate(ifpDetails.getEndDate());
        ifpDetailsImpl.setModifiedDate(ifpDetails.getModifiedDate());
        ifpDetailsImpl.setRecordLockStatus(ifpDetails.isRecordLockStatus());
        ifpDetailsImpl.setStartDate(ifpDetails.getStartDate());
        ifpDetailsImpl.setCreatedDate(ifpDetails.getCreatedDate());
        ifpDetailsImpl.setSource(ifpDetails.getSource());
        ifpDetailsImpl.setCreatedBy(ifpDetails.getCreatedBy());
        ifpDetailsImpl.setItemIfpAttachedDate(ifpDetails.getItemIfpAttachedDate());
        ifpDetailsImpl.setBatchId(ifpDetails.getBatchId());
        ifpDetailsImpl.setModifiedBy(ifpDetails.getModifiedBy());
        ifpDetailsImpl.setInboundStatus(ifpDetails.getInboundStatus());
        ifpDetailsImpl.setIfpDetailsSid(ifpDetails.getIfpDetailsSid());
        ifpDetailsImpl.setIfpModelSid(ifpDetails.getIfpModelSid());
        ifpDetailsImpl.setItemIfpAttachedStatus(ifpDetails.getItemIfpAttachedStatus());

        return ifpDetailsImpl;
    }

    /**
     * Returns the ifp details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ifp details
     * @return the ifp details
     * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIfpDetailsException, SystemException {
        IfpDetails ifpDetails = fetchByPrimaryKey(primaryKey);

        if (ifpDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ifpDetails;
    }

    /**
     * Returns the ifp details with the primary key or throws a {@link com.stpl.app.NoSuchIfpDetailsException} if it could not be found.
     *
     * @param ifpDetailsSid the primary key of the ifp details
     * @return the ifp details
     * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails findByPrimaryKey(int ifpDetailsSid)
        throws NoSuchIfpDetailsException, SystemException {
        return findByPrimaryKey((Serializable) ifpDetailsSid);
    }

    /**
     * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ifp details
     * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IfpDetails ifpDetails = (IfpDetails) EntityCacheUtil.getResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IfpDetailsImpl.class, primaryKey);

        if (ifpDetails == _nullIfpDetails) {
            return null;
        }

        if (ifpDetails == null) {
            Session session = null;

            try {
                session = openSession();

                ifpDetails = (IfpDetails) session.get(IfpDetailsImpl.class,
                        primaryKey);

                if (ifpDetails != null) {
                    cacheResult(ifpDetails);
                } else {
                    EntityCacheUtil.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IfpDetailsImpl.class, primaryKey, _nullIfpDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    IfpDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ifpDetails;
    }

    /**
     * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ifpDetailsSid the primary key of the ifp details
     * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpDetails fetchByPrimaryKey(int ifpDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ifpDetailsSid);
    }

    /**
     * Returns all the ifp detailses.
     *
     * @return the ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ifp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp detailses
     * @param end the upper bound of the range of ifp detailses (not inclusive)
     * @return the range of ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ifp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp detailses
     * @param end the upper bound of the range of ifp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ifp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpDetails> findAll(int start, int end,
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

        List<IfpDetails> list = (List<IfpDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IFPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IFPDETAILS;

                if (pagination) {
                    sql = sql.concat(IfpDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IfpDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpDetails>(list);
                } else {
                    list = (List<IfpDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ifp detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IfpDetails ifpDetails : findAll()) {
            remove(ifpDetails);
        }
    }

    /**
     * Returns the number of ifp detailses.
     *
     * @return the number of ifp detailses
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

                Query q = session.createQuery(_SQL_COUNT_IFPDETAILS);

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
     * Initializes the ifp details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IfpDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IfpDetails>> listenersList = new ArrayList<ModelListener<IfpDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IfpDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IfpDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
