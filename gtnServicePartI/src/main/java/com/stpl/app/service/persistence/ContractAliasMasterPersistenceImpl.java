package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchContractAliasMasterException;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.impl.ContractAliasMasterImpl;
import com.stpl.app.model.impl.ContractAliasMasterModelImpl;
import com.stpl.app.service.persistence.ContractAliasMasterPersistence;

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
 * The persistence implementation for the contract alias master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractAliasMasterPersistence
 * @see ContractAliasMasterUtil
 * @generated
 */
public class ContractAliasMasterPersistenceImpl extends BasePersistenceImpl<ContractAliasMaster>
    implements ContractAliasMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContractAliasMasterUtil} to access the contract alias master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContractAliasMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractAliasMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractAliasMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTSYSTEMID =
        new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractAliasMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContractSystemId",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID =
        new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractAliasMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContractSystemId", new String[] { Integer.class.getName() },
            ContractAliasMasterModelImpl.CONTRACTMASTERSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContractSystemId", new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2 =
        "contractAliasMaster.contractMasterSid = ?";
    private static final String _SQL_SELECT_CONTRACTALIASMASTER = "SELECT contractAliasMaster FROM ContractAliasMaster contractAliasMaster";
    private static final String _SQL_SELECT_CONTRACTALIASMASTER_WHERE = "SELECT contractAliasMaster FROM ContractAliasMaster contractAliasMaster WHERE ";
    private static final String _SQL_COUNT_CONTRACTALIASMASTER = "SELECT COUNT(contractAliasMaster) FROM ContractAliasMaster contractAliasMaster";
    private static final String _SQL_COUNT_CONTRACTALIASMASTER_WHERE = "SELECT COUNT(contractAliasMaster) FROM ContractAliasMaster contractAliasMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contractAliasMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractAliasMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractAliasMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContractAliasMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "contractAliasType", "tpCompanyMasterSid", "endDate",
                "contractAliasMasterSid", "modifiedDate", "contractAliasNo",
                "recordLockStatus", "startDate", "createdDate", "source",
                "createdBy", "contractMasterSid", "batchId", "contractAliasName",
                "modifiedBy", "inboundStatus"
            });
    private static ContractAliasMaster _nullContractAliasMaster = new ContractAliasMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContractAliasMaster> toCacheModel() {
                return _nullContractAliasMasterCacheModel;
            }
        };

    private static CacheModel<ContractAliasMaster> _nullContractAliasMasterCacheModel =
        new CacheModel<ContractAliasMaster>() {
            @Override
            public ContractAliasMaster toEntityModel() {
                return _nullContractAliasMaster;
            }
        };

    public ContractAliasMasterPersistenceImpl() {
        setModelClass(ContractAliasMaster.class);
    }

    /**
     * Returns all the contract alias masters where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @return the matching contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findByContractSystemId(
        int contractMasterSid) throws SystemException {
        return findByContractSystemId(contractMasterSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contract alias masters where contractMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractMasterSid the contract master sid
     * @param start the lower bound of the range of contract alias masters
     * @param end the upper bound of the range of contract alias masters (not inclusive)
     * @return the range of matching contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end) throws SystemException {
        return findByContractSystemId(contractMasterSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractMasterSid the contract master sid
     * @param start the lower bound of the range of contract alias masters
     * @param end the upper bound of the range of contract alias masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID;
            finderArgs = new Object[] { contractMasterSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTSYSTEMID;
            finderArgs = new Object[] {
                    contractMasterSid,
                    
                    start, end, orderByComparator
                };
        }

        List<ContractAliasMaster> list = (List<ContractAliasMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContractAliasMaster contractAliasMaster : list) {
                if ((contractMasterSid != contractAliasMaster.getContractMasterSid())) {
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

            query.append(_SQL_SELECT_CONTRACTALIASMASTER_WHERE);

            query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contractMasterSid);

                if (!pagination) {
                    list = (List<ContractAliasMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContractAliasMaster>(list);
                } else {
                    list = (List<ContractAliasMaster>) QueryUtil.list(q,
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
     * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contract alias master
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster findByContractSystemId_First(
        int contractMasterSid, OrderByComparator orderByComparator)
        throws NoSuchContractAliasMasterException, SystemException {
        ContractAliasMaster contractAliasMaster = fetchByContractSystemId_First(contractMasterSid,
                orderByComparator);

        if (contractAliasMaster != null) {
            return contractAliasMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractMasterSid=");
        msg.append(contractMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContractAliasMasterException(msg.toString());
    }

    /**
     * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster fetchByContractSystemId_First(
        int contractMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        List<ContractAliasMaster> list = findByContractSystemId(contractMasterSid,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contract alias master
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster findByContractSystemId_Last(
        int contractMasterSid, OrderByComparator orderByComparator)
        throws NoSuchContractAliasMasterException, SystemException {
        ContractAliasMaster contractAliasMaster = fetchByContractSystemId_Last(contractMasterSid,
                orderByComparator);

        if (contractAliasMaster != null) {
            return contractAliasMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractMasterSid=");
        msg.append(contractMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContractAliasMasterException(msg.toString());
    }

    /**
     * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster fetchByContractSystemId_Last(
        int contractMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByContractSystemId(contractMasterSid);

        if (count == 0) {
            return null;
        }

        List<ContractAliasMaster> list = findByContractSystemId(contractMasterSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
     *
     * @param contractAliasMasterSid the primary key of the current contract alias master
     * @param contractMasterSid the contract master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contract alias master
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster[] findByContractSystemId_PrevAndNext(
        int contractAliasMasterSid, int contractMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchContractAliasMasterException, SystemException {
        ContractAliasMaster contractAliasMaster = findByPrimaryKey(contractAliasMasterSid);

        Session session = null;

        try {
            session = openSession();

            ContractAliasMaster[] array = new ContractAliasMasterImpl[3];

            array[0] = getByContractSystemId_PrevAndNext(session,
                    contractAliasMaster, contractMasterSid, orderByComparator,
                    true);

            array[1] = contractAliasMaster;

            array[2] = getByContractSystemId_PrevAndNext(session,
                    contractAliasMaster, contractMasterSid, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContractAliasMaster getByContractSystemId_PrevAndNext(
        Session session, ContractAliasMaster contractAliasMaster,
        int contractMasterSid, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTRACTALIASMASTER_WHERE);

        query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

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
            query.append(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contractMasterSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contractAliasMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContractAliasMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contract alias masters where contractMasterSid = &#63; from the database.
     *
     * @param contractMasterSid the contract master sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractSystemId(int contractMasterSid)
        throws SystemException {
        for (ContractAliasMaster contractAliasMaster : findByContractSystemId(
                contractMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contractAliasMaster);
        }
    }

    /**
     * Returns the number of contract alias masters where contractMasterSid = &#63;.
     *
     * @param contractMasterSid the contract master sid
     * @return the number of matching contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContractSystemId(int contractMasterSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID;

        Object[] finderArgs = new Object[] { contractMasterSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTRACTALIASMASTER_WHERE);

            query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contractMasterSid);

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
     * Caches the contract alias master in the entity cache if it is enabled.
     *
     * @param contractAliasMaster the contract alias master
     */
    @Override
    public void cacheResult(ContractAliasMaster contractAliasMaster) {
        EntityCacheUtil.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey(),
            contractAliasMaster);

        contractAliasMaster.resetOriginalValues();
    }

    /**
     * Caches the contract alias masters in the entity cache if it is enabled.
     *
     * @param contractAliasMasters the contract alias masters
     */
    @Override
    public void cacheResult(List<ContractAliasMaster> contractAliasMasters) {
        for (ContractAliasMaster contractAliasMaster : contractAliasMasters) {
            if (EntityCacheUtil.getResult(
                        ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ContractAliasMasterImpl.class,
                        contractAliasMaster.getPrimaryKey()) == null) {
                cacheResult(contractAliasMaster);
            } else {
                contractAliasMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contract alias masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContractAliasMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContractAliasMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contract alias master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContractAliasMaster contractAliasMaster) {
        EntityCacheUtil.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContractAliasMaster> contractAliasMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContractAliasMaster contractAliasMaster : contractAliasMasters) {
            EntityCacheUtil.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
                ContractAliasMasterImpl.class,
                contractAliasMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
     *
     * @param contractAliasMasterSid the primary key for the new contract alias master
     * @return the new contract alias master
     */
    @Override
    public ContractAliasMaster create(int contractAliasMasterSid) {
        ContractAliasMaster contractAliasMaster = new ContractAliasMasterImpl();

        contractAliasMaster.setNew(true);
        contractAliasMaster.setPrimaryKey(contractAliasMasterSid);

        return contractAliasMaster;
    }

    /**
     * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param contractAliasMasterSid the primary key of the contract alias master
     * @return the contract alias master that was removed
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster remove(int contractAliasMasterSid)
        throws NoSuchContractAliasMasterException, SystemException {
        return remove((Serializable) contractAliasMasterSid);
    }

    /**
     * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contract alias master
     * @return the contract alias master that was removed
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster remove(Serializable primaryKey)
        throws NoSuchContractAliasMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContractAliasMaster contractAliasMaster = (ContractAliasMaster) session.get(ContractAliasMasterImpl.class,
                    primaryKey);

            if (contractAliasMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContractAliasMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contractAliasMaster);
        } catch (NoSuchContractAliasMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContractAliasMaster removeImpl(
        ContractAliasMaster contractAliasMaster) throws SystemException {
        contractAliasMaster = toUnwrappedModel(contractAliasMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contractAliasMaster)) {
                contractAliasMaster = (ContractAliasMaster) session.get(ContractAliasMasterImpl.class,
                        contractAliasMaster.getPrimaryKeyObj());
            }

            if (contractAliasMaster != null) {
                session.delete(contractAliasMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contractAliasMaster != null) {
            clearCache(contractAliasMaster);
        }

        return contractAliasMaster;
    }

    @Override
    public ContractAliasMaster updateImpl(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws SystemException {
        contractAliasMaster = toUnwrappedModel(contractAliasMaster);

        boolean isNew = contractAliasMaster.isNew();

        ContractAliasMasterModelImpl contractAliasMasterModelImpl = (ContractAliasMasterModelImpl) contractAliasMaster;

        Session session = null;

        try {
            session = openSession();

            if (contractAliasMaster.isNew()) {
                session.save(contractAliasMaster);

                contractAliasMaster.setNew(false);
            } else {
                session.merge(contractAliasMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContractAliasMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contractAliasMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contractAliasMasterModelImpl.getOriginalContractMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID,
                    args);

                args = new Object[] {
                        contractAliasMasterModelImpl.getContractMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey(),
            contractAliasMaster);

        return contractAliasMaster;
    }

    protected ContractAliasMaster toUnwrappedModel(
        ContractAliasMaster contractAliasMaster) {
        if (contractAliasMaster instanceof ContractAliasMasterImpl) {
            return contractAliasMaster;
        }

        ContractAliasMasterImpl contractAliasMasterImpl = new ContractAliasMasterImpl();

        contractAliasMasterImpl.setNew(contractAliasMaster.isNew());
        contractAliasMasterImpl.setPrimaryKey(contractAliasMaster.getPrimaryKey());

        contractAliasMasterImpl.setContractAliasType(contractAliasMaster.getContractAliasType());
        contractAliasMasterImpl.setTpCompanyMasterSid(contractAliasMaster.getTpCompanyMasterSid());
        contractAliasMasterImpl.setEndDate(contractAliasMaster.getEndDate());
        contractAliasMasterImpl.setContractAliasMasterSid(contractAliasMaster.getContractAliasMasterSid());
        contractAliasMasterImpl.setModifiedDate(contractAliasMaster.getModifiedDate());
        contractAliasMasterImpl.setContractAliasNo(contractAliasMaster.getContractAliasNo());
        contractAliasMasterImpl.setRecordLockStatus(contractAliasMaster.isRecordLockStatus());
        contractAliasMasterImpl.setStartDate(contractAliasMaster.getStartDate());
        contractAliasMasterImpl.setCreatedDate(contractAliasMaster.getCreatedDate());
        contractAliasMasterImpl.setSource(contractAliasMaster.getSource());
        contractAliasMasterImpl.setCreatedBy(contractAliasMaster.getCreatedBy());
        contractAliasMasterImpl.setContractMasterSid(contractAliasMaster.getContractMasterSid());
        contractAliasMasterImpl.setBatchId(contractAliasMaster.getBatchId());
        contractAliasMasterImpl.setContractAliasName(contractAliasMaster.getContractAliasName());
        contractAliasMasterImpl.setModifiedBy(contractAliasMaster.getModifiedBy());
        contractAliasMasterImpl.setInboundStatus(contractAliasMaster.getInboundStatus());

        return contractAliasMasterImpl;
    }

    /**
     * Returns the contract alias master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contract alias master
     * @return the contract alias master
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContractAliasMasterException, SystemException {
        ContractAliasMaster contractAliasMaster = fetchByPrimaryKey(primaryKey);

        if (contractAliasMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContractAliasMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contractAliasMaster;
    }

    /**
     * Returns the contract alias master with the primary key or throws a {@link com.stpl.app.NoSuchContractAliasMasterException} if it could not be found.
     *
     * @param contractAliasMasterSid the primary key of the contract alias master
     * @return the contract alias master
     * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster findByPrimaryKey(int contractAliasMasterSid)
        throws NoSuchContractAliasMasterException, SystemException {
        return findByPrimaryKey((Serializable) contractAliasMasterSid);
    }

    /**
     * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contract alias master
     * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContractAliasMaster contractAliasMaster = (ContractAliasMaster) EntityCacheUtil.getResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
                ContractAliasMasterImpl.class, primaryKey);

        if (contractAliasMaster == _nullContractAliasMaster) {
            return null;
        }

        if (contractAliasMaster == null) {
            Session session = null;

            try {
                session = openSession();

                contractAliasMaster = (ContractAliasMaster) session.get(ContractAliasMasterImpl.class,
                        primaryKey);

                if (contractAliasMaster != null) {
                    cacheResult(contractAliasMaster);
                } else {
                    EntityCacheUtil.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ContractAliasMasterImpl.class, primaryKey,
                        _nullContractAliasMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ContractAliasMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contractAliasMaster;
    }

    /**
     * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param contractAliasMasterSid the primary key of the contract alias master
     * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractAliasMaster fetchByPrimaryKey(int contractAliasMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) contractAliasMasterSid);
    }

    /**
     * Returns all the contract alias masters.
     *
     * @return the contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contract alias masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contract alias masters
     * @param end the upper bound of the range of contract alias masters (not inclusive)
     * @return the range of contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contract alias masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contract alias masters
     * @param end the upper bound of the range of contract alias masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contract alias masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractAliasMaster> findAll(int start, int end,
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

        List<ContractAliasMaster> list = (List<ContractAliasMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTRACTALIASMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTRACTALIASMASTER;

                if (pagination) {
                    sql = sql.concat(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContractAliasMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContractAliasMaster>(list);
                } else {
                    list = (List<ContractAliasMaster>) QueryUtil.list(q,
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
     * Removes all the contract alias masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContractAliasMaster contractAliasMaster : findAll()) {
            remove(contractAliasMaster);
        }
    }

    /**
     * Returns the number of contract alias masters.
     *
     * @return the number of contract alias masters
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

                Query q = session.createQuery(_SQL_COUNT_CONTRACTALIASMASTER);

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
     * Initializes the contract alias master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ContractAliasMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContractAliasMaster>> listenersList = new ArrayList<ModelListener<ContractAliasMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContractAliasMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContractAliasMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
