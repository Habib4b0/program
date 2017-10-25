package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchPsDetailsException;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.impl.PsDetailsImpl;
import com.stpl.app.model.impl.PsDetailsModelImpl;
import com.stpl.app.service.persistence.PsDetailsPersistence;

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
 * The persistence implementation for the ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsDetailsPersistence
 * @see PsDetailsUtil
 * @generated
 */
public class PsDetailsPersistenceImpl extends BasePersistenceImpl<PsDetails>
    implements PsDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PsDetailsUtil} to access the ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PsDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
        new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPriceScheduleMasterDetails",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
        new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByPriceScheduleMasterDetails",
            new String[] { Integer.class.getName() },
            PsDetailsModelImpl.PSMODELSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS =
        new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPriceScheduleMasterDetails",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2 =
        "psDetails.psModelSid = ?";
    private static final String _SQL_SELECT_PSDETAILS = "SELECT psDetails FROM PsDetails psDetails";
    private static final String _SQL_SELECT_PSDETAILS_WHERE = "SELECT psDetails FROM PsDetails psDetails WHERE ";
    private static final String _SQL_COUNT_PSDETAILS = "SELECT COUNT(psDetails) FROM PsDetails psDetails";
    private static final String _SQL_COUNT_PSDETAILS_WHERE = "SELECT COUNT(psDetails) FROM PsDetails psDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "psDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PsDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PsDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "nepFormula", "price", "itemMasterSid", "resetType",
                "priceProtectionStartDate", "resetDate", "basePrice",
                "itemPsAttachedDate", "brandMasterSid", "status", "modifiedDate",
                "itemPsAttachedStatus", "revisionDate", "priceTolerance",
                "createdDate", "createdBy", "source", "psDetailsSid",
                "psModelSid", "suggestedPrice", "netPriceTypeFormula",
                "priceProtectionPriceType", "modifiedBy", "inboundStatus",
                "contractPrice", "ifpModelSid", "priceToleranceType",
                "maxIncrementalChange", "itemPricingQualifierSid",
                "contractPriceEndDate", "nep", "contractPriceStartDate",
                "priceToleranceFrequency", "priceProtectionEndDate",
                "priceProtectionStatus", "recordLockStatus", "resetEligible",
                "batchId", "priceToleranceInterval", "netPriceType",
                "priceRevision", "resetFrequency", "resetInterval",
                "basePriceType", "basePriceEntry", "basePriceDate",
                "netBasePrice", "basePriceDdlb", "subsequentPeriodPriceType",
                "netSubsequentPeriodPrice", "netSubsequentPriceFormulaId",
                "resetPriceType", "netResetPriceType", "netResetPriceFormulaId",
                "netBasePriceFormulaId"
            });
    private static PsDetails _nullPsDetails = new PsDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PsDetails> toCacheModel() {
                return _nullPsDetailsCacheModel;
            }
        };

    private static CacheModel<PsDetails> _nullPsDetailsCacheModel = new CacheModel<PsDetails>() {
            @Override
            public PsDetails toEntityModel() {
                return _nullPsDetails;
            }
        };

    public PsDetailsPersistenceImpl() {
        setModelClass(PsDetails.class);
    }

    /**
     * Returns all the ps detailses where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @return the matching ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid)
        throws SystemException {
        return findByPriceScheduleMasterDetails(psModelSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ps detailses where psModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param psModelSid the ps model sid
     * @param start the lower bound of the range of ps detailses
     * @param end the upper bound of the range of ps detailses (not inclusive)
     * @return the range of matching ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid,
        int start, int end) throws SystemException {
        return findByPriceScheduleMasterDetails(psModelSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the ps detailses where psModelSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param psModelSid the ps model sid
     * @param start the lower bound of the range of ps detailses
     * @param end the upper bound of the range of ps detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
            finderArgs = new Object[] { psModelSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
            finderArgs = new Object[] { psModelSid, start, end, orderByComparator };
        }

        List<PsDetails> list = (List<PsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PsDetails psDetails : list) {
                if ((psModelSid != psDetails.getPsModelSid())) {
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

            query.append(_SQL_SELECT_PSDETAILS_WHERE);

            query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PsDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(psModelSid);

                if (!pagination) {
                    list = (List<PsDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PsDetails>(list);
                } else {
                    list = (List<PsDetails>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ps details in the ordered set where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ps details
     * @throws com.stpl.app.NoSuchPsDetailsException if a matching ps details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails findByPriceScheduleMasterDetails_First(int psModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchPsDetailsException, SystemException {
        PsDetails psDetails = fetchByPriceScheduleMasterDetails_First(psModelSid,
                orderByComparator);

        if (psDetails != null) {
            return psDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("psModelSid=");
        msg.append(psModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPsDetailsException(msg.toString());
    }

    /**
     * Returns the first ps details in the ordered set where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ps details, or <code>null</code> if a matching ps details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails fetchByPriceScheduleMasterDetails_First(int psModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        List<PsDetails> list = findByPriceScheduleMasterDetails(psModelSid, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ps details in the ordered set where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ps details
     * @throws com.stpl.app.NoSuchPsDetailsException if a matching ps details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails findByPriceScheduleMasterDetails_Last(int psModelSid,
        OrderByComparator orderByComparator)
        throws NoSuchPsDetailsException, SystemException {
        PsDetails psDetails = fetchByPriceScheduleMasterDetails_Last(psModelSid,
                orderByComparator);

        if (psDetails != null) {
            return psDetails;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("psModelSid=");
        msg.append(psModelSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPsDetailsException(msg.toString());
    }

    /**
     * Returns the last ps details in the ordered set where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ps details, or <code>null</code> if a matching ps details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails fetchByPriceScheduleMasterDetails_Last(int psModelSid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPriceScheduleMasterDetails(psModelSid);

        if (count == 0) {
            return null;
        }

        List<PsDetails> list = findByPriceScheduleMasterDetails(psModelSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ps detailses before and after the current ps details in the ordered set where psModelSid = &#63;.
     *
     * @param psDetailsSid the primary key of the current ps details
     * @param psModelSid the ps model sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ps details
     * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
        int psDetailsSid, int psModelSid, OrderByComparator orderByComparator)
        throws NoSuchPsDetailsException, SystemException {
        PsDetails psDetails = findByPrimaryKey(psDetailsSid);

        Session session = null;

        try {
            session = openSession();

            PsDetails[] array = new PsDetailsImpl[3];

            array[0] = getByPriceScheduleMasterDetails_PrevAndNext(session,
                    psDetails, psModelSid, orderByComparator, true);

            array[1] = psDetails;

            array[2] = getByPriceScheduleMasterDetails_PrevAndNext(session,
                    psDetails, psModelSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PsDetails getByPriceScheduleMasterDetails_PrevAndNext(
        Session session, PsDetails psDetails, int psModelSid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PSDETAILS_WHERE);

        query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

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
            query.append(PsDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(psModelSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(psDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PsDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ps detailses where psModelSid = &#63; from the database.
     *
     * @param psModelSid the ps model sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPriceScheduleMasterDetails(int psModelSid)
        throws SystemException {
        for (PsDetails psDetails : findByPriceScheduleMasterDetails(
                psModelSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(psDetails);
        }
    }

    /**
     * Returns the number of ps detailses where psModelSid = &#63;.
     *
     * @param psModelSid the ps model sid
     * @return the number of matching ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPriceScheduleMasterDetails(int psModelSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS;

        Object[] finderArgs = new Object[] { psModelSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PSDETAILS_WHERE);

            query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(psModelSid);

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
     * Caches the ps details in the entity cache if it is enabled.
     *
     * @param psDetails the ps details
     */
    @Override
    public void cacheResult(PsDetails psDetails) {
        EntityCacheUtil.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsImpl.class, psDetails.getPrimaryKey(), psDetails);

        psDetails.resetOriginalValues();
    }

    /**
     * Caches the ps detailses in the entity cache if it is enabled.
     *
     * @param psDetailses the ps detailses
     */
    @Override
    public void cacheResult(List<PsDetails> psDetailses) {
        for (PsDetails psDetails : psDetailses) {
            if (EntityCacheUtil.getResult(
                        PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        PsDetailsImpl.class, psDetails.getPrimaryKey()) == null) {
                cacheResult(psDetails);
            } else {
                psDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ps detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PsDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PsDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ps details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PsDetails psDetails) {
        EntityCacheUtil.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsImpl.class, psDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PsDetails> psDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PsDetails psDetails : psDetailses) {
            EntityCacheUtil.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                PsDetailsImpl.class, psDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ps details with the primary key. Does not add the ps details to the database.
     *
     * @param psDetailsSid the primary key for the new ps details
     * @return the new ps details
     */
    @Override
    public PsDetails create(int psDetailsSid) {
        PsDetails psDetails = new PsDetailsImpl();

        psDetails.setNew(true);
        psDetails.setPrimaryKey(psDetailsSid);

        return psDetails;
    }

    /**
     * Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param psDetailsSid the primary key of the ps details
     * @return the ps details that was removed
     * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails remove(int psDetailsSid)
        throws NoSuchPsDetailsException, SystemException {
        return remove((Serializable) psDetailsSid);
    }

    /**
     * Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ps details
     * @return the ps details that was removed
     * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails remove(Serializable primaryKey)
        throws NoSuchPsDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PsDetails psDetails = (PsDetails) session.get(PsDetailsImpl.class,
                    primaryKey);

            if (psDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(psDetails);
        } catch (NoSuchPsDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PsDetails removeImpl(PsDetails psDetails)
        throws SystemException {
        psDetails = toUnwrappedModel(psDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(psDetails)) {
                psDetails = (PsDetails) session.get(PsDetailsImpl.class,
                        psDetails.getPrimaryKeyObj());
            }

            if (psDetails != null) {
                session.delete(psDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (psDetails != null) {
            clearCache(psDetails);
        }

        return psDetails;
    }

    @Override
    public PsDetails updateImpl(com.stpl.app.model.PsDetails psDetails)
        throws SystemException {
        psDetails = toUnwrappedModel(psDetails);

        boolean isNew = psDetails.isNew();

        PsDetailsModelImpl psDetailsModelImpl = (PsDetailsModelImpl) psDetails;

        Session session = null;

        try {
            session = openSession();

            if (psDetails.isNew()) {
                session.save(psDetails);

                psDetails.setNew(false);
            } else {
                session.merge(psDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PsDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((psDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        psDetailsModelImpl.getOriginalPsModelSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
                    args);

                args = new Object[] { psDetailsModelImpl.getPsModelSid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsDetailsImpl.class, psDetails.getPrimaryKey(), psDetails);

        return psDetails;
    }

    protected PsDetails toUnwrappedModel(PsDetails psDetails) {
        if (psDetails instanceof PsDetailsImpl) {
            return psDetails;
        }

        PsDetailsImpl psDetailsImpl = new PsDetailsImpl();

        psDetailsImpl.setNew(psDetails.isNew());
        psDetailsImpl.setPrimaryKey(psDetails.getPrimaryKey());

        psDetailsImpl.setNepFormula(psDetails.getNepFormula());
        psDetailsImpl.setPrice(psDetails.getPrice());
        psDetailsImpl.setItemMasterSid(psDetails.getItemMasterSid());
        psDetailsImpl.setResetType(psDetails.getResetType());
        psDetailsImpl.setPriceProtectionStartDate(psDetails.getPriceProtectionStartDate());
        psDetailsImpl.setResetDate(psDetails.getResetDate());
        psDetailsImpl.setBasePrice(psDetails.getBasePrice());
        psDetailsImpl.setItemPsAttachedDate(psDetails.getItemPsAttachedDate());
        psDetailsImpl.setBrandMasterSid(psDetails.getBrandMasterSid());
        psDetailsImpl.setStatus(psDetails.getStatus());
        psDetailsImpl.setModifiedDate(psDetails.getModifiedDate());
        psDetailsImpl.setItemPsAttachedStatus(psDetails.getItemPsAttachedStatus());
        psDetailsImpl.setRevisionDate(psDetails.getRevisionDate());
        psDetailsImpl.setPriceTolerance(psDetails.getPriceTolerance());
        psDetailsImpl.setCreatedDate(psDetails.getCreatedDate());
        psDetailsImpl.setCreatedBy(psDetails.getCreatedBy());
        psDetailsImpl.setSource(psDetails.getSource());
        psDetailsImpl.setPsDetailsSid(psDetails.getPsDetailsSid());
        psDetailsImpl.setPsModelSid(psDetails.getPsModelSid());
        psDetailsImpl.setSuggestedPrice(psDetails.getSuggestedPrice());
        psDetailsImpl.setNetPriceTypeFormula(psDetails.getNetPriceTypeFormula());
        psDetailsImpl.setPriceProtectionPriceType(psDetails.getPriceProtectionPriceType());
        psDetailsImpl.setModifiedBy(psDetails.getModifiedBy());
        psDetailsImpl.setInboundStatus(psDetails.getInboundStatus());
        psDetailsImpl.setContractPrice(psDetails.getContractPrice());
        psDetailsImpl.setIfpModelSid(psDetails.getIfpModelSid());
        psDetailsImpl.setPriceToleranceType(psDetails.getPriceToleranceType());
        psDetailsImpl.setMaxIncrementalChange(psDetails.getMaxIncrementalChange());
        psDetailsImpl.setItemPricingQualifierSid(psDetails.getItemPricingQualifierSid());
        psDetailsImpl.setContractPriceEndDate(psDetails.getContractPriceEndDate());
        psDetailsImpl.setNep(psDetails.getNep());
        psDetailsImpl.setContractPriceStartDate(psDetails.getContractPriceStartDate());
        psDetailsImpl.setPriceToleranceFrequency(psDetails.getPriceToleranceFrequency());
        psDetailsImpl.setPriceProtectionEndDate(psDetails.getPriceProtectionEndDate());
        psDetailsImpl.setPriceProtectionStatus(psDetails.getPriceProtectionStatus());
        psDetailsImpl.setRecordLockStatus(psDetails.isRecordLockStatus());
        psDetailsImpl.setResetEligible(psDetails.getResetEligible());
        psDetailsImpl.setBatchId(psDetails.getBatchId());
        psDetailsImpl.setPriceToleranceInterval(psDetails.getPriceToleranceInterval());
        psDetailsImpl.setNetPriceType(psDetails.getNetPriceType());
        psDetailsImpl.setPriceRevision(psDetails.getPriceRevision());
        psDetailsImpl.setResetFrequency(psDetails.getResetFrequency());
        psDetailsImpl.setResetInterval(psDetails.getResetInterval());
        psDetailsImpl.setBasePriceType(psDetails.getBasePriceType());
        psDetailsImpl.setBasePriceEntry(psDetails.getBasePriceEntry());
        psDetailsImpl.setBasePriceDate(psDetails.getBasePriceDate());
        psDetailsImpl.setNetBasePrice(psDetails.getNetBasePrice());
        psDetailsImpl.setBasePriceDdlb(psDetails.getBasePriceDdlb());
        psDetailsImpl.setSubsequentPeriodPriceType(psDetails.getSubsequentPeriodPriceType());
        psDetailsImpl.setNetSubsequentPeriodPrice(psDetails.getNetSubsequentPeriodPrice());
        psDetailsImpl.setNetSubsequentPriceFormulaId(psDetails.getNetSubsequentPriceFormulaId());
        psDetailsImpl.setResetPriceType(psDetails.getResetPriceType());
        psDetailsImpl.setNetResetPriceType(psDetails.getNetResetPriceType());
        psDetailsImpl.setNetResetPriceFormulaId(psDetails.getNetResetPriceFormulaId());
        psDetailsImpl.setNetBasePriceFormulaId(psDetails.getNetBasePriceFormulaId());

        return psDetailsImpl;
    }

    /**
     * Returns the ps details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ps details
     * @return the ps details
     * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPsDetailsException, SystemException {
        PsDetails psDetails = fetchByPrimaryKey(primaryKey);

        if (psDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return psDetails;
    }

    /**
     * Returns the ps details with the primary key or throws a {@link com.stpl.app.NoSuchPsDetailsException} if it could not be found.
     *
     * @param psDetailsSid the primary key of the ps details
     * @return the ps details
     * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails findByPrimaryKey(int psDetailsSid)
        throws NoSuchPsDetailsException, SystemException {
        return findByPrimaryKey((Serializable) psDetailsSid);
    }

    /**
     * Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ps details
     * @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PsDetails psDetails = (PsDetails) EntityCacheUtil.getResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                PsDetailsImpl.class, primaryKey);

        if (psDetails == _nullPsDetails) {
            return null;
        }

        if (psDetails == null) {
            Session session = null;

            try {
                session = openSession();

                psDetails = (PsDetails) session.get(PsDetailsImpl.class,
                        primaryKey);

                if (psDetails != null) {
                    cacheResult(psDetails);
                } else {
                    EntityCacheUtil.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        PsDetailsImpl.class, primaryKey, _nullPsDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    PsDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return psDetails;
    }

    /**
     * Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param psDetailsSid the primary key of the ps details
     * @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsDetails fetchByPrimaryKey(int psDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) psDetailsSid);
    }

    /**
     * Returns all the ps detailses.
     *
     * @return the ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ps detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps detailses
     * @param end the upper bound of the range of ps detailses (not inclusive)
     * @return the range of ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ps detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps detailses
     * @param end the upper bound of the range of ps detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsDetails> findAll(int start, int end,
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

        List<PsDetails> list = (List<PsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PSDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PSDETAILS;

                if (pagination) {
                    sql = sql.concat(PsDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PsDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PsDetails>(list);
                } else {
                    list = (List<PsDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ps detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PsDetails psDetails : findAll()) {
            remove(psDetails);
        }
    }

    /**
     * Returns the number of ps detailses.
     *
     * @return the number of ps detailses
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

                Query q = session.createQuery(_SQL_COUNT_PSDETAILS);

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
     * Initializes the ps details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.PsDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PsDetails>> listenersList = new ArrayList<ModelListener<PsDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PsDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PsDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
