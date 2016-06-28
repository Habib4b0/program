package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIfpContractDetailsException;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.impl.IfpContractDetailsImpl;
import com.stpl.app.model.impl.IfpContractDetailsModelImpl;
import com.stpl.app.service.persistence.IfpContractDetailsPersistence;

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
 * The persistence implementation for the ifp contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractDetailsPersistence
 * @see IfpContractDetailsUtil
 * @generated
 */
public class IfpContractDetailsPersistenceImpl extends BasePersistenceImpl<IfpContractDetails>
    implements IfpContractDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IfpContractDetailsUtil} to access the ifp contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IfpContractDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            IfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            IfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_IFPDETAILS =
        new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            IfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByIFPDetails",
            new String[] {
                Integer.class.getName(), Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IFPDETAILS =
        new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            IfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIFPDetails",
            new String[] { Integer.class.getName(), Integer.class.getName() },
            IfpContractDetailsModelImpl.IFPCONTRACTSID_COLUMN_BITMASK |
            IfpContractDetailsModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_IFPDETAILS = new FinderPath(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIFPDetails",
            new String[] { Integer.class.getName(), Integer.class.getName() });
    private static final String _FINDER_COLUMN_IFPDETAILS_IFPCONTRACTSID_2 = "ifpContractDetails.ifpContractSid = ? AND ";
    private static final String _FINDER_COLUMN_IFPDETAILS_ITEMMASTERSID_2 = "ifpContractDetails.itemMasterSid = ?";
    private static final String _SQL_SELECT_IFPCONTRACTDETAILS = "SELECT ifpContractDetails FROM IfpContractDetails ifpContractDetails";
    private static final String _SQL_SELECT_IFPCONTRACTDETAILS_WHERE = "SELECT ifpContractDetails FROM IfpContractDetails ifpContractDetails WHERE ";
    private static final String _SQL_COUNT_IFPCONTRACTDETAILS = "SELECT COUNT(ifpContractDetails) FROM IfpContractDetails ifpContractDetails";
    private static final String _SQL_COUNT_IFPCONTRACTDETAILS_WHERE = "SELECT COUNT(ifpContractDetails) FROM IfpContractDetails ifpContractDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ifpContractDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpContractDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IfpContractDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IfpContractDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemStatus", "itemMasterSid", "ifpContractAttachedDate",
                "itemEndDate", "totalVolumeCommitment", "totalDollarCommitment",
                "ifpContractAttachedStatus", "modifiedDate",
                "totalMarketshareCommitment", "recordLockStatus", "createdDate",
                "createdBy", "source", "itemStartDate", "batchId",
                "ifpContractDetailsSid", "modifiedBy", "inboundStatus",
                "ifpContractSid", "commitmentPeriod"
            });
    private static IfpContractDetails _nullIfpContractDetails = new IfpContractDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IfpContractDetails> toCacheModel() {
                return _nullIfpContractDetailsCacheModel;
            }
        };

    private static CacheModel<IfpContractDetails> _nullIfpContractDetailsCacheModel =
        new CacheModel<IfpContractDetails>() {
            @Override
            public IfpContractDetails toEntityModel() {
                return _nullIfpContractDetails;
            }
        };

    public IfpContractDetailsPersistenceImpl() {
        setModelClass(IfpContractDetails.class);
    }

    /**
     * Returns all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @return the matching ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findByIFPDetails(int ifpContractSid,
        int itemMasterSid) throws SystemException {
        return findByIFPDetails(ifpContractSid, itemMasterSid,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of ifp contract detailses
     * @param end the upper bound of the range of ifp contract detailses (not inclusive)
     * @return the range of matching ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findByIFPDetails(int ifpContractSid,
        int itemMasterSid, int start, int end) throws SystemException {
        return findByIFPDetails(ifpContractSid, itemMasterSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of ifp contract detailses
     * @param end the upper bound of the range of ifp contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findByIFPDetails(int ifpContractSid,
        int itemMasterSid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IFPDETAILS;
            finderArgs = new Object[] { ifpContractSid, itemMasterSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_IFPDETAILS;
            finderArgs = new Object[] {
                    ifpContractSid, itemMasterSid,
                    
                    start, end, orderByComparator
                };
        }

        List<IfpContractDetails> list = (List<IfpContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpContractDetails ifpContractDetails : list) {
                if ((ifpContractSid != ifpContractDetails.getIfpContractSid()) ||
                        (itemMasterSid != ifpContractDetails.getItemMasterSid())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_IFPCONTRACTDETAILS_WHERE);

            query.append(_FINDER_COLUMN_IFPDETAILS_IFPCONTRACTSID_2);

            query.append(_FINDER_COLUMN_IFPDETAILS_ITEMMASTERSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpContractDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpContractSid);

                qPos.add(itemMasterSid);

                if (!pagination) {
                    list = (List<IfpContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpContractDetails>(list);
                } else {
                    list = (List<IfpContractDetails>) QueryUtil.list(q,
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
     * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp contract details
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails findByIFPDetails_First(int ifpContractSid,
        int itemMasterSid, OrderByComparator orderByComparator)
        throws NoSuchIfpContractDetailsException, SystemException {
        IfpContractDetails ifpContractDetails = fetchByIFPDetails_First(ifpContractSid,
                itemMasterSid, orderByComparator);

        if (ifpContractDetails != null) {
            return ifpContractDetails;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpContractSid=");
        msg.append(ifpContractSid);

        msg.append(", itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpContractDetailsException(msg.toString());
    }

    /**
     * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails fetchByIFPDetails_First(int ifpContractSid,
        int itemMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        List<IfpContractDetails> list = findByIFPDetails(ifpContractSid,
                itemMasterSid, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp contract details
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails findByIFPDetails_Last(int ifpContractSid,
        int itemMasterSid, OrderByComparator orderByComparator)
        throws NoSuchIfpContractDetailsException, SystemException {
        IfpContractDetails ifpContractDetails = fetchByIFPDetails_Last(ifpContractSid,
                itemMasterSid, orderByComparator);

        if (ifpContractDetails != null) {
            return ifpContractDetails;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpContractSid=");
        msg.append(ifpContractSid);

        msg.append(", itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpContractDetailsException(msg.toString());
    }

    /**
     * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails fetchByIFPDetails_Last(int ifpContractSid,
        int itemMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByIFPDetails(ifpContractSid, itemMasterSid);

        if (count == 0) {
            return null;
        }

        List<IfpContractDetails> list = findByIFPDetails(ifpContractSid,
                itemMasterSid, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ifp contract detailses before and after the current ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractDetailsSid the primary key of the current ifp contract details
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ifp contract details
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails[] findByIFPDetails_PrevAndNext(
        int ifpContractDetailsSid, int ifpContractSid, int itemMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchIfpContractDetailsException, SystemException {
        IfpContractDetails ifpContractDetails = findByPrimaryKey(ifpContractDetailsSid);

        Session session = null;

        try {
            session = openSession();

            IfpContractDetails[] array = new IfpContractDetailsImpl[3];

            array[0] = getByIFPDetails_PrevAndNext(session, ifpContractDetails,
                    ifpContractSid, itemMasterSid, orderByComparator, true);

            array[1] = ifpContractDetails;

            array[2] = getByIFPDetails_PrevAndNext(session, ifpContractDetails,
                    ifpContractSid, itemMasterSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpContractDetails getByIFPDetails_PrevAndNext(Session session,
        IfpContractDetails ifpContractDetails, int ifpContractSid,
        int itemMasterSid, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPCONTRACTDETAILS_WHERE);

        query.append(_FINDER_COLUMN_IFPDETAILS_IFPCONTRACTSID_2);

        query.append(_FINDER_COLUMN_IFPDETAILS_ITEMMASTERSID_2);

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
            query.append(IfpContractDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ifpContractSid);

        qPos.add(itemMasterSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpContractDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpContractDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63; from the database.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws SystemException {
        for (IfpContractDetails ifpContractDetails : findByIFPDetails(
                ifpContractSid, itemMasterSid, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(ifpContractDetails);
        }
    }

    /**
     * Returns the number of ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
     *
     * @param ifpContractSid the ifp contract sid
     * @param itemMasterSid the item master sid
     * @return the number of matching ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_IFPDETAILS;

        Object[] finderArgs = new Object[] { ifpContractSid, itemMasterSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IFPCONTRACTDETAILS_WHERE);

            query.append(_FINDER_COLUMN_IFPDETAILS_IFPCONTRACTSID_2);

            query.append(_FINDER_COLUMN_IFPDETAILS_ITEMMASTERSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpContractSid);

                qPos.add(itemMasterSid);

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
     * Caches the ifp contract details in the entity cache if it is enabled.
     *
     * @param ifpContractDetails the ifp contract details
     */
    @Override
    public void cacheResult(IfpContractDetails ifpContractDetails) {
        EntityCacheUtil.putResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsImpl.class, ifpContractDetails.getPrimaryKey(),
            ifpContractDetails);

        ifpContractDetails.resetOriginalValues();
    }

    /**
     * Caches the ifp contract detailses in the entity cache if it is enabled.
     *
     * @param ifpContractDetailses the ifp contract detailses
     */
    @Override
    public void cacheResult(List<IfpContractDetails> ifpContractDetailses) {
        for (IfpContractDetails ifpContractDetails : ifpContractDetailses) {
            if (EntityCacheUtil.getResult(
                        IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IfpContractDetailsImpl.class,
                        ifpContractDetails.getPrimaryKey()) == null) {
                cacheResult(ifpContractDetails);
            } else {
                ifpContractDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ifp contract detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IfpContractDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IfpContractDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ifp contract details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IfpContractDetails ifpContractDetails) {
        EntityCacheUtil.removeResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsImpl.class, ifpContractDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IfpContractDetails> ifpContractDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IfpContractDetails ifpContractDetails : ifpContractDetailses) {
            EntityCacheUtil.removeResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IfpContractDetailsImpl.class, ifpContractDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
     *
     * @param ifpContractDetailsSid the primary key for the new ifp contract details
     * @return the new ifp contract details
     */
    @Override
    public IfpContractDetails create(int ifpContractDetailsSid) {
        IfpContractDetails ifpContractDetails = new IfpContractDetailsImpl();

        ifpContractDetails.setNew(true);
        ifpContractDetails.setPrimaryKey(ifpContractDetailsSid);

        return ifpContractDetails;
    }

    /**
     * Removes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ifpContractDetailsSid the primary key of the ifp contract details
     * @return the ifp contract details that was removed
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails remove(int ifpContractDetailsSid)
        throws NoSuchIfpContractDetailsException, SystemException {
        return remove((Serializable) ifpContractDetailsSid);
    }

    /**
     * Removes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ifp contract details
     * @return the ifp contract details that was removed
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails remove(Serializable primaryKey)
        throws NoSuchIfpContractDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IfpContractDetails ifpContractDetails = (IfpContractDetails) session.get(IfpContractDetailsImpl.class,
                    primaryKey);

            if (ifpContractDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIfpContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ifpContractDetails);
        } catch (NoSuchIfpContractDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IfpContractDetails removeImpl(
        IfpContractDetails ifpContractDetails) throws SystemException {
        ifpContractDetails = toUnwrappedModel(ifpContractDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ifpContractDetails)) {
                ifpContractDetails = (IfpContractDetails) session.get(IfpContractDetailsImpl.class,
                        ifpContractDetails.getPrimaryKeyObj());
            }

            if (ifpContractDetails != null) {
                session.delete(ifpContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ifpContractDetails != null) {
            clearCache(ifpContractDetails);
        }

        return ifpContractDetails;
    }

    @Override
    public IfpContractDetails updateImpl(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws SystemException {
        ifpContractDetails = toUnwrappedModel(ifpContractDetails);

        boolean isNew = ifpContractDetails.isNew();

        IfpContractDetailsModelImpl ifpContractDetailsModelImpl = (IfpContractDetailsModelImpl) ifpContractDetails;

        Session session = null;

        try {
            session = openSession();

            if (ifpContractDetails.isNew()) {
                session.save(ifpContractDetails);

                ifpContractDetails.setNew(false);
            } else {
                session.merge(ifpContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IfpContractDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ifpContractDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IFPDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpContractDetailsModelImpl.getOriginalIfpContractSid(),
                        ifpContractDetailsModelImpl.getOriginalItemMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_IFPDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IFPDETAILS,
                    args);

                args = new Object[] {
                        ifpContractDetailsModelImpl.getIfpContractSid(),
                        ifpContractDetailsModelImpl.getItemMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_IFPDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IFPDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractDetailsImpl.class, ifpContractDetails.getPrimaryKey(),
            ifpContractDetails);

        return ifpContractDetails;
    }

    protected IfpContractDetails toUnwrappedModel(
        IfpContractDetails ifpContractDetails) {
        if (ifpContractDetails instanceof IfpContractDetailsImpl) {
            return ifpContractDetails;
        }

        IfpContractDetailsImpl ifpContractDetailsImpl = new IfpContractDetailsImpl();

        ifpContractDetailsImpl.setNew(ifpContractDetails.isNew());
        ifpContractDetailsImpl.setPrimaryKey(ifpContractDetails.getPrimaryKey());

        ifpContractDetailsImpl.setItemStatus(ifpContractDetails.getItemStatus());
        ifpContractDetailsImpl.setItemMasterSid(ifpContractDetails.getItemMasterSid());
        ifpContractDetailsImpl.setIfpContractAttachedDate(ifpContractDetails.getIfpContractAttachedDate());
        ifpContractDetailsImpl.setItemEndDate(ifpContractDetails.getItemEndDate());
        ifpContractDetailsImpl.setTotalVolumeCommitment(ifpContractDetails.getTotalVolumeCommitment());
        ifpContractDetailsImpl.setTotalDollarCommitment(ifpContractDetails.getTotalDollarCommitment());
        ifpContractDetailsImpl.setIfpContractAttachedStatus(ifpContractDetails.getIfpContractAttachedStatus());
        ifpContractDetailsImpl.setModifiedDate(ifpContractDetails.getModifiedDate());
        ifpContractDetailsImpl.setTotalMarketshareCommitment(ifpContractDetails.getTotalMarketshareCommitment());
        ifpContractDetailsImpl.setRecordLockStatus(ifpContractDetails.isRecordLockStatus());
        ifpContractDetailsImpl.setCreatedDate(ifpContractDetails.getCreatedDate());
        ifpContractDetailsImpl.setCreatedBy(ifpContractDetails.getCreatedBy());
        ifpContractDetailsImpl.setSource(ifpContractDetails.getSource());
        ifpContractDetailsImpl.setItemStartDate(ifpContractDetails.getItemStartDate());
        ifpContractDetailsImpl.setBatchId(ifpContractDetails.getBatchId());
        ifpContractDetailsImpl.setIfpContractDetailsSid(ifpContractDetails.getIfpContractDetailsSid());
        ifpContractDetailsImpl.setModifiedBy(ifpContractDetails.getModifiedBy());
        ifpContractDetailsImpl.setInboundStatus(ifpContractDetails.getInboundStatus());
        ifpContractDetailsImpl.setIfpContractSid(ifpContractDetails.getIfpContractSid());
        ifpContractDetailsImpl.setCommitmentPeriod(ifpContractDetails.getCommitmentPeriod());

        return ifpContractDetailsImpl;
    }

    /**
     * Returns the ifp contract details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ifp contract details
     * @return the ifp contract details
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIfpContractDetailsException, SystemException {
        IfpContractDetails ifpContractDetails = fetchByPrimaryKey(primaryKey);

        if (ifpContractDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIfpContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ifpContractDetails;
    }

    /**
     * Returns the ifp contract details with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractDetailsException} if it could not be found.
     *
     * @param ifpContractDetailsSid the primary key of the ifp contract details
     * @return the ifp contract details
     * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails findByPrimaryKey(int ifpContractDetailsSid)
        throws NoSuchIfpContractDetailsException, SystemException {
        return findByPrimaryKey((Serializable) ifpContractDetailsSid);
    }

    /**
     * Returns the ifp contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ifp contract details
     * @return the ifp contract details, or <code>null</code> if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IfpContractDetails ifpContractDetails = (IfpContractDetails) EntityCacheUtil.getResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IfpContractDetailsImpl.class, primaryKey);

        if (ifpContractDetails == _nullIfpContractDetails) {
            return null;
        }

        if (ifpContractDetails == null) {
            Session session = null;

            try {
                session = openSession();

                ifpContractDetails = (IfpContractDetails) session.get(IfpContractDetailsImpl.class,
                        primaryKey);

                if (ifpContractDetails != null) {
                    cacheResult(ifpContractDetails);
                } else {
                    EntityCacheUtil.putResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IfpContractDetailsImpl.class, primaryKey,
                        _nullIfpContractDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    IfpContractDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ifpContractDetails;
    }

    /**
     * Returns the ifp contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ifpContractDetailsSid the primary key of the ifp contract details
     * @return the ifp contract details, or <code>null</code> if a ifp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContractDetails fetchByPrimaryKey(int ifpContractDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ifpContractDetailsSid);
    }

    /**
     * Returns all the ifp contract detailses.
     *
     * @return the ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ifp contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp contract detailses
     * @param end the upper bound of the range of ifp contract detailses (not inclusive)
     * @return the range of ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ifp contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp contract detailses
     * @param end the upper bound of the range of ifp contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ifp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContractDetails> findAll(int start, int end,
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

        List<IfpContractDetails> list = (List<IfpContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IFPCONTRACTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IFPCONTRACTDETAILS;

                if (pagination) {
                    sql = sql.concat(IfpContractDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IfpContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpContractDetails>(list);
                } else {
                    list = (List<IfpContractDetails>) QueryUtil.list(q,
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
     * Removes all the ifp contract detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IfpContractDetails ifpContractDetails : findAll()) {
            remove(ifpContractDetails);
        }
    }

    /**
     * Returns the number of ifp contract detailses.
     *
     * @return the number of ifp contract detailses
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

                Query q = session.createQuery(_SQL_COUNT_IFPCONTRACTDETAILS);

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
     * Initializes the ifp contract details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IfpContractDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IfpContractDetails>> listenersList = new ArrayList<ModelListener<IfpContractDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IfpContractDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IfpContractDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
