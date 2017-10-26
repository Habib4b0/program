package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCfpContractDetailsException;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.impl.CfpContractDetailsImpl;
import com.stpl.app.model.impl.CfpContractDetailsModelImpl;
import com.stpl.app.service.persistence.CfpContractDetailsPersistence;

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
 * The persistence implementation for the cfp contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractDetailsPersistence
 * @see CfpContractDetailsUtil
 * @generated
 */
public class CfpContractDetailsPersistenceImpl extends BasePersistenceImpl<CfpContractDetails>
    implements CfpContractDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CfpContractDetailsUtil} to access the cfp contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CfpContractDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            CfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            CfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPDETAILS =
        new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            CfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCFPDetails",
            new String[] {
                Integer.class.getName(), Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPDETAILS =
        new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            CfpContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCFPDetails",
            new String[] { Integer.class.getName(), Integer.class.getName() },
            CfpContractDetailsModelImpl.COMPANYMASTERSID_COLUMN_BITMASK |
            CfpContractDetailsModelImpl.CFPCONTRACTSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPDETAILS = new FinderPath(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCFPDetails",
            new String[] { Integer.class.getName(), Integer.class.getName() });
    private static final String _FINDER_COLUMN_CFPDETAILS_COMPANYMASTERSID_2 = "cfpContractDetails.companyMasterSid = ? AND ";
    private static final String _FINDER_COLUMN_CFPDETAILS_CFPCONTRACTSID_2 = "cfpContractDetails.cfpContractSid = ?";
    private static final String _SQL_SELECT_CFPCONTRACTDETAILS = "SELECT cfpContractDetails FROM CfpContractDetails cfpContractDetails";
    private static final String _SQL_SELECT_CFPCONTRACTDETAILS_WHERE = "SELECT cfpContractDetails FROM CfpContractDetails cfpContractDetails WHERE ";
    private static final String _SQL_COUNT_CFPCONTRACTDETAILS = "SELECT COUNT(cfpContractDetails) FROM CfpContractDetails cfpContractDetails";
    private static final String _SQL_COUNT_CFPCONTRACTDETAILS_WHERE = "SELECT COUNT(cfpContractDetails) FROM CfpContractDetails cfpContractDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cfpContractDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpContractDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CfpContractDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CfpContractDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "tradeClass", "tradeClassEndDate", "cfpContractSid",
                "modifiedBy", "companyStartDate", "tradeClassStartDate",
                "createdDate", "cfpContractAttachedDate", "companyEndDate",
                "companyMasterSid", "batchId", "modifiedDate",
                "recordLockStatus", "source", "cfpContractDetailsSid",
                "cfpContractAttachedStatus", "inboundStatus"
            });
    private static CfpContractDetails _nullCfpContractDetails = new CfpContractDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CfpContractDetails> toCacheModel() {
                return _nullCfpContractDetailsCacheModel;
            }
        };

    private static CacheModel<CfpContractDetails> _nullCfpContractDetailsCacheModel =
        new CacheModel<CfpContractDetails>() {
            @Override
            public CfpContractDetails toEntityModel() {
                return _nullCfpContractDetails;
            }
        };

    public CfpContractDetailsPersistenceImpl() {
        setModelClass(CfpContractDetails.class);
    }

    /**
     * Returns all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @return the matching cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findByCFPDetails(int companyMasterSid,
        int cfpContractSid) throws SystemException {
        return findByCFPDetails(companyMasterSid, cfpContractSid,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param start the lower bound of the range of cfp contract detailses
     * @param end the upper bound of the range of cfp contract detailses (not inclusive)
     * @return the range of matching cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findByCFPDetails(int companyMasterSid,
        int cfpContractSid, int start, int end) throws SystemException {
        return findByCFPDetails(companyMasterSid, cfpContractSid, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param start the lower bound of the range of cfp contract detailses
     * @param end the upper bound of the range of cfp contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findByCFPDetails(int companyMasterSid,
        int cfpContractSid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPDETAILS;
            finderArgs = new Object[] { companyMasterSid, cfpContractSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPDETAILS;
            finderArgs = new Object[] {
                    companyMasterSid, cfpContractSid,
                    
                    start, end, orderByComparator
                };
        }

        List<CfpContractDetails> list = (List<CfpContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpContractDetails cfpContractDetails : list) {
                if ((companyMasterSid != cfpContractDetails.getCompanyMasterSid()) ||
                        (cfpContractSid != cfpContractDetails.getCfpContractSid())) {
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

            query.append(_SQL_SELECT_CFPCONTRACTDETAILS_WHERE);

            query.append(_FINDER_COLUMN_CFPDETAILS_COMPANYMASTERSID_2);

            query.append(_FINDER_COLUMN_CFPDETAILS_CFPCONTRACTSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpContractDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyMasterSid);

                qPos.add(cfpContractSid);

                if (!pagination) {
                    list = (List<CfpContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpContractDetails>(list);
                } else {
                    list = (List<CfpContractDetails>) QueryUtil.list(q,
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
     * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp contract details
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails findByCFPDetails_First(int companyMasterSid,
        int cfpContractSid, OrderByComparator orderByComparator)
        throws NoSuchCfpContractDetailsException, SystemException {
        CfpContractDetails cfpContractDetails = fetchByCFPDetails_First(companyMasterSid,
                cfpContractSid, orderByComparator);

        if (cfpContractDetails != null) {
            return cfpContractDetails;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyMasterSid=");
        msg.append(companyMasterSid);

        msg.append(", cfpContractSid=");
        msg.append(cfpContractSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpContractDetailsException(msg.toString());
    }

    /**
     * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails fetchByCFPDetails_First(int companyMasterSid,
        int cfpContractSid, OrderByComparator orderByComparator)
        throws SystemException {
        List<CfpContractDetails> list = findByCFPDetails(companyMasterSid,
                cfpContractSid, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp contract details
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails findByCFPDetails_Last(int companyMasterSid,
        int cfpContractSid, OrderByComparator orderByComparator)
        throws NoSuchCfpContractDetailsException, SystemException {
        CfpContractDetails cfpContractDetails = fetchByCFPDetails_Last(companyMasterSid,
                cfpContractSid, orderByComparator);

        if (cfpContractDetails != null) {
            return cfpContractDetails;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyMasterSid=");
        msg.append(companyMasterSid);

        msg.append(", cfpContractSid=");
        msg.append(cfpContractSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpContractDetailsException(msg.toString());
    }

    /**
     * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails fetchByCFPDetails_Last(int companyMasterSid,
        int cfpContractSid, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByCFPDetails(companyMasterSid, cfpContractSid);

        if (count == 0) {
            return null;
        }

        List<CfpContractDetails> list = findByCFPDetails(companyMasterSid,
                cfpContractSid, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the cfp contract detailses before and after the current cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param cfpContractDetailsSid the primary key of the current cfp contract details
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next cfp contract details
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails[] findByCFPDetails_PrevAndNext(
        int cfpContractDetailsSid, int companyMasterSid, int cfpContractSid,
        OrderByComparator orderByComparator)
        throws NoSuchCfpContractDetailsException, SystemException {
        CfpContractDetails cfpContractDetails = findByPrimaryKey(cfpContractDetailsSid);

        Session session = null;

        try {
            session = openSession();

            CfpContractDetails[] array = new CfpContractDetailsImpl[3];

            array[0] = getByCFPDetails_PrevAndNext(session, cfpContractDetails,
                    companyMasterSid, cfpContractSid, orderByComparator, true);

            array[1] = cfpContractDetails;

            array[2] = getByCFPDetails_PrevAndNext(session, cfpContractDetails,
                    companyMasterSid, cfpContractSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpContractDetails getByCFPDetails_PrevAndNext(Session session,
        CfpContractDetails cfpContractDetails, int companyMasterSid,
        int cfpContractSid, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPCONTRACTDETAILS_WHERE);

        query.append(_FINDER_COLUMN_CFPDETAILS_COMPANYMASTERSID_2);

        query.append(_FINDER_COLUMN_CFPDETAILS_CFPCONTRACTSID_2);

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
            query.append(CfpContractDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyMasterSid);

        qPos.add(cfpContractSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpContractDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpContractDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63; from the database.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCFPDetails(int companyMasterSid, int cfpContractSid)
        throws SystemException {
        for (CfpContractDetails cfpContractDetails : findByCFPDetails(
                companyMasterSid, cfpContractSid, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpContractDetails);
        }
    }

    /**
     * Returns the number of cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param cfpContractSid the cfp contract sid
     * @return the number of matching cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCFPDetails(int companyMasterSid, int cfpContractSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPDETAILS;

        Object[] finderArgs = new Object[] { companyMasterSid, cfpContractSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CFPCONTRACTDETAILS_WHERE);

            query.append(_FINDER_COLUMN_CFPDETAILS_COMPANYMASTERSID_2);

            query.append(_FINDER_COLUMN_CFPDETAILS_CFPCONTRACTSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyMasterSid);

                qPos.add(cfpContractSid);

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
     * Caches the cfp contract details in the entity cache if it is enabled.
     *
     * @param cfpContractDetails the cfp contract details
     */
    @Override
    public void cacheResult(CfpContractDetails cfpContractDetails) {
        EntityCacheUtil.putResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsImpl.class, cfpContractDetails.getPrimaryKey(),
            cfpContractDetails);

        cfpContractDetails.resetOriginalValues();
    }

    /**
     * Caches the cfp contract detailses in the entity cache if it is enabled.
     *
     * @param cfpContractDetailses the cfp contract detailses
     */
    @Override
    public void cacheResult(List<CfpContractDetails> cfpContractDetailses) {
        for (CfpContractDetails cfpContractDetails : cfpContractDetailses) {
            if (EntityCacheUtil.getResult(
                        CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CfpContractDetailsImpl.class,
                        cfpContractDetails.getPrimaryKey()) == null) {
                cacheResult(cfpContractDetails);
            } else {
                cfpContractDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cfp contract detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CfpContractDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CfpContractDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cfp contract details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CfpContractDetails cfpContractDetails) {
        EntityCacheUtil.removeResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsImpl.class, cfpContractDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CfpContractDetails> cfpContractDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CfpContractDetails cfpContractDetails : cfpContractDetailses) {
            EntityCacheUtil.removeResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CfpContractDetailsImpl.class, cfpContractDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
     *
     * @param cfpContractDetailsSid the primary key for the new cfp contract details
     * @return the new cfp contract details
     */
    @Override
    public CfpContractDetails create(int cfpContractDetailsSid) {
        CfpContractDetails cfpContractDetails = new CfpContractDetailsImpl();

        cfpContractDetails.setNew(true);
        cfpContractDetails.setPrimaryKey(cfpContractDetailsSid);

        return cfpContractDetails;
    }

    /**
     * Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cfpContractDetailsSid the primary key of the cfp contract details
     * @return the cfp contract details that was removed
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails remove(int cfpContractDetailsSid)
        throws NoSuchCfpContractDetailsException, SystemException {
        return remove((Serializable) cfpContractDetailsSid);
    }

    /**
     * Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cfp contract details
     * @return the cfp contract details that was removed
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails remove(Serializable primaryKey)
        throws NoSuchCfpContractDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CfpContractDetails cfpContractDetails = (CfpContractDetails) session.get(CfpContractDetailsImpl.class,
                    primaryKey);

            if (cfpContractDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCfpContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cfpContractDetails);
        } catch (NoSuchCfpContractDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CfpContractDetails removeImpl(
        CfpContractDetails cfpContractDetails) throws SystemException {
        cfpContractDetails = toUnwrappedModel(cfpContractDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cfpContractDetails)) {
                cfpContractDetails = (CfpContractDetails) session.get(CfpContractDetailsImpl.class,
                        cfpContractDetails.getPrimaryKeyObj());
            }

            if (cfpContractDetails != null) {
                session.delete(cfpContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cfpContractDetails != null) {
            clearCache(cfpContractDetails);
        }

        return cfpContractDetails;
    }

    @Override
    public CfpContractDetails updateImpl(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws SystemException {
        cfpContractDetails = toUnwrappedModel(cfpContractDetails);

        boolean isNew = cfpContractDetails.isNew();

        CfpContractDetailsModelImpl cfpContractDetailsModelImpl = (CfpContractDetailsModelImpl) cfpContractDetails;

        Session session = null;

        try {
            session = openSession();

            if (cfpContractDetails.isNew()) {
                session.save(cfpContractDetails);

                cfpContractDetails.setNew(false);
            } else {
                session.merge(cfpContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CfpContractDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((cfpContractDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpContractDetailsModelImpl.getOriginalCompanyMasterSid(),
                        cfpContractDetailsModelImpl.getOriginalCfpContractSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPDETAILS,
                    args);

                args = new Object[] {
                        cfpContractDetailsModelImpl.getCompanyMasterSid(),
                        cfpContractDetailsModelImpl.getCfpContractSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractDetailsImpl.class, cfpContractDetails.getPrimaryKey(),
            cfpContractDetails);

        return cfpContractDetails;
    }

    protected CfpContractDetails toUnwrappedModel(
        CfpContractDetails cfpContractDetails) {
        if (cfpContractDetails instanceof CfpContractDetailsImpl) {
            return cfpContractDetails;
        }

        CfpContractDetailsImpl cfpContractDetailsImpl = new CfpContractDetailsImpl();

        cfpContractDetailsImpl.setNew(cfpContractDetails.isNew());
        cfpContractDetailsImpl.setPrimaryKey(cfpContractDetails.getPrimaryKey());

        cfpContractDetailsImpl.setCreatedBy(cfpContractDetails.getCreatedBy());
        cfpContractDetailsImpl.setTradeClass(cfpContractDetails.getTradeClass());
        cfpContractDetailsImpl.setTradeClassEndDate(cfpContractDetails.getTradeClassEndDate());
        cfpContractDetailsImpl.setCfpContractSid(cfpContractDetails.getCfpContractSid());
        cfpContractDetailsImpl.setModifiedBy(cfpContractDetails.getModifiedBy());
        cfpContractDetailsImpl.setCompanyStartDate(cfpContractDetails.getCompanyStartDate());
        cfpContractDetailsImpl.setTradeClassStartDate(cfpContractDetails.getTradeClassStartDate());
        cfpContractDetailsImpl.setCreatedDate(cfpContractDetails.getCreatedDate());
        cfpContractDetailsImpl.setCfpContractAttachedDate(cfpContractDetails.getCfpContractAttachedDate());
        cfpContractDetailsImpl.setCompanyEndDate(cfpContractDetails.getCompanyEndDate());
        cfpContractDetailsImpl.setCompanyMasterSid(cfpContractDetails.getCompanyMasterSid());
        cfpContractDetailsImpl.setBatchId(cfpContractDetails.getBatchId());
        cfpContractDetailsImpl.setModifiedDate(cfpContractDetails.getModifiedDate());
        cfpContractDetailsImpl.setRecordLockStatus(cfpContractDetails.isRecordLockStatus());
        cfpContractDetailsImpl.setSource(cfpContractDetails.getSource());
        cfpContractDetailsImpl.setCfpContractDetailsSid(cfpContractDetails.getCfpContractDetailsSid());
        cfpContractDetailsImpl.setCfpContractAttachedStatus(cfpContractDetails.getCfpContractAttachedStatus());
        cfpContractDetailsImpl.setInboundStatus(cfpContractDetails.getInboundStatus());

        return cfpContractDetailsImpl;
    }

    /**
     * Returns the cfp contract details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cfp contract details
     * @return the cfp contract details
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCfpContractDetailsException, SystemException {
        CfpContractDetails cfpContractDetails = fetchByPrimaryKey(primaryKey);

        if (cfpContractDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCfpContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cfpContractDetails;
    }

    /**
     * Returns the cfp contract details with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractDetailsException} if it could not be found.
     *
     * @param cfpContractDetailsSid the primary key of the cfp contract details
     * @return the cfp contract details
     * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails findByPrimaryKey(int cfpContractDetailsSid)
        throws NoSuchCfpContractDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cfpContractDetailsSid);
    }

    /**
     * Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cfp contract details
     * @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CfpContractDetails cfpContractDetails = (CfpContractDetails) EntityCacheUtil.getResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CfpContractDetailsImpl.class, primaryKey);

        if (cfpContractDetails == _nullCfpContractDetails) {
            return null;
        }

        if (cfpContractDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cfpContractDetails = (CfpContractDetails) session.get(CfpContractDetailsImpl.class,
                        primaryKey);

                if (cfpContractDetails != null) {
                    cacheResult(cfpContractDetails);
                } else {
                    EntityCacheUtil.putResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CfpContractDetailsImpl.class, primaryKey,
                        _nullCfpContractDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CfpContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CfpContractDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cfpContractDetails;
    }

    /**
     * Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cfpContractDetailsSid the primary key of the cfp contract details
     * @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContractDetails fetchByPrimaryKey(int cfpContractDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cfpContractDetailsSid);
    }

    /**
     * Returns all the cfp contract detailses.
     *
     * @return the cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cfp contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp contract detailses
     * @param end the upper bound of the range of cfp contract detailses (not inclusive)
     * @return the range of cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cfp contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp contract detailses
     * @param end the upper bound of the range of cfp contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cfp contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContractDetails> findAll(int start, int end,
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

        List<CfpContractDetails> list = (List<CfpContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFPCONTRACTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFPCONTRACTDETAILS;

                if (pagination) {
                    sql = sql.concat(CfpContractDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CfpContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpContractDetails>(list);
                } else {
                    list = (List<CfpContractDetails>) QueryUtil.list(q,
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
     * Removes all the cfp contract detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CfpContractDetails cfpContractDetails : findAll()) {
            remove(cfpContractDetails);
        }
    }

    /**
     * Returns the number of cfp contract detailses.
     *
     * @return the number of cfp contract detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFPCONTRACTDETAILS);

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
     * Initializes the cfp contract details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CfpContractDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CfpContractDetails>> listenersList = new ArrayList<ModelListener<CfpContractDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CfpContractDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CfpContractDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
