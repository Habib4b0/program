package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchAuditMasterInboundException;
import com.stpl.app.model.AuditMasterInbound;
import com.stpl.app.model.impl.AuditMasterInboundImpl;
import com.stpl.app.model.impl.AuditMasterInboundModelImpl;
import com.stpl.app.service.persistence.AuditMasterInboundPersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
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
 * The persistence implementation for the audit master inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AuditMasterInboundPersistence
 * @see AuditMasterInboundUtil
 * @generated
 */
public class AuditMasterInboundPersistenceImpl extends BasePersistenceImpl<AuditMasterInbound>
    implements AuditMasterInboundPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AuditMasterInboundUtil} to access the audit master inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AuditMasterInboundImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED,
            AuditMasterInboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED,
            AuditMasterInboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_AUDITMASTERINBOUND = "SELECT auditMasterInbound FROM AuditMasterInbound auditMasterInbound";
    private static final String _SQL_COUNT_AUDITMASTERINBOUND = "SELECT COUNT(auditMasterInbound) FROM AuditMasterInbound auditMasterInbound";
    private static final String _ORDER_BY_ENTITY_ALIAS = "auditMasterInbound.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditMasterInbound exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AuditMasterInboundPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "receivedRecordAmountAttr", "modifiedBy", "createdDate",
                "interfaceRunEndDate", "applicationProcess", "discrepancyAmount",
                "batchId", "fileName", "sentRecordAmountAttribute", "status",
                "receivedRecordAmount", "validRecordAmount",
                "invalidRecordCount", "receivedRecordCount", "createdBy",
                "changeCount", "unprocessedRecords", "deleteCount",
                "modifiedDate", "auditInboundSid", "sentRecordAmount",
                "sentRecordCount", "addCount", "source", "invalidRecordAmount",
                "interfaceRunStartDate"
            });
    private static AuditMasterInbound _nullAuditMasterInbound = new AuditMasterInboundImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AuditMasterInbound> toCacheModel() {
                return _nullAuditMasterInboundCacheModel;
            }
        };

    private static CacheModel<AuditMasterInbound> _nullAuditMasterInboundCacheModel =
        new CacheModel<AuditMasterInbound>() {
            @Override
            public AuditMasterInbound toEntityModel() {
                return _nullAuditMasterInbound;
            }
        };

    public AuditMasterInboundPersistenceImpl() {
        setModelClass(AuditMasterInbound.class);
    }

    /**
     * Caches the audit master inbound in the entity cache if it is enabled.
     *
     * @param auditMasterInbound the audit master inbound
     */
    @Override
    public void cacheResult(AuditMasterInbound auditMasterInbound) {
        EntityCacheUtil.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey(),
            auditMasterInbound);

        auditMasterInbound.resetOriginalValues();
    }

    /**
     * Caches the audit master inbounds in the entity cache if it is enabled.
     *
     * @param auditMasterInbounds the audit master inbounds
     */
    @Override
    public void cacheResult(List<AuditMasterInbound> auditMasterInbounds) {
        for (AuditMasterInbound auditMasterInbound : auditMasterInbounds) {
            if (EntityCacheUtil.getResult(
                        AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
                        AuditMasterInboundImpl.class,
                        auditMasterInbound.getPrimaryKey()) == null) {
                cacheResult(auditMasterInbound);
            } else {
                auditMasterInbound.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all audit master inbounds.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AuditMasterInboundImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AuditMasterInboundImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the audit master inbound.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AuditMasterInbound auditMasterInbound) {
        EntityCacheUtil.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AuditMasterInbound> auditMasterInbounds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AuditMasterInbound auditMasterInbound : auditMasterInbounds) {
            EntityCacheUtil.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
                AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey());
        }
    }

    /**
     * Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
     *
     * @param auditInboundSid the primary key for the new audit master inbound
     * @return the new audit master inbound
     */
    @Override
    public AuditMasterInbound create(int auditInboundSid) {
        AuditMasterInbound auditMasterInbound = new AuditMasterInboundImpl();

        auditMasterInbound.setNew(true);
        auditMasterInbound.setPrimaryKey(auditInboundSid);

        return auditMasterInbound;
    }

    /**
     * Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param auditInboundSid the primary key of the audit master inbound
     * @return the audit master inbound that was removed
     * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound remove(int auditInboundSid)
        throws NoSuchAuditMasterInboundException, SystemException {
        return remove((Serializable) auditInboundSid);
    }

    /**
     * Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the audit master inbound
     * @return the audit master inbound that was removed
     * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound remove(Serializable primaryKey)
        throws NoSuchAuditMasterInboundException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AuditMasterInbound auditMasterInbound = (AuditMasterInbound) session.get(AuditMasterInboundImpl.class,
                    primaryKey);

            if (auditMasterInbound == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAuditMasterInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(auditMasterInbound);
        } catch (NoSuchAuditMasterInboundException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AuditMasterInbound removeImpl(
        AuditMasterInbound auditMasterInbound) throws SystemException {
        auditMasterInbound = toUnwrappedModel(auditMasterInbound);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(auditMasterInbound)) {
                auditMasterInbound = (AuditMasterInbound) session.get(AuditMasterInboundImpl.class,
                        auditMasterInbound.getPrimaryKeyObj());
            }

            if (auditMasterInbound != null) {
                session.delete(auditMasterInbound);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (auditMasterInbound != null) {
            clearCache(auditMasterInbound);
        }

        return auditMasterInbound;
    }

    @Override
    public AuditMasterInbound updateImpl(
        com.stpl.app.model.AuditMasterInbound auditMasterInbound)
        throws SystemException {
        auditMasterInbound = toUnwrappedModel(auditMasterInbound);

        boolean isNew = auditMasterInbound.isNew();

        Session session = null;

        try {
            session = openSession();

            if (auditMasterInbound.isNew()) {
                session.save(auditMasterInbound);

                auditMasterInbound.setNew(false);
            } else {
                session.merge(auditMasterInbound);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
            AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey(),
            auditMasterInbound);

        return auditMasterInbound;
    }

    protected AuditMasterInbound toUnwrappedModel(
        AuditMasterInbound auditMasterInbound) {
        if (auditMasterInbound instanceof AuditMasterInboundImpl) {
            return auditMasterInbound;
        }

        AuditMasterInboundImpl auditMasterInboundImpl = new AuditMasterInboundImpl();

        auditMasterInboundImpl.setNew(auditMasterInbound.isNew());
        auditMasterInboundImpl.setPrimaryKey(auditMasterInbound.getPrimaryKey());

        auditMasterInboundImpl.setReceivedRecordAmountAttr(auditMasterInbound.getReceivedRecordAmountAttr());
        auditMasterInboundImpl.setModifiedBy(auditMasterInbound.getModifiedBy());
        auditMasterInboundImpl.setCreatedDate(auditMasterInbound.getCreatedDate());
        auditMasterInboundImpl.setInterfaceRunEndDate(auditMasterInbound.getInterfaceRunEndDate());
        auditMasterInboundImpl.setApplicationProcess(auditMasterInbound.getApplicationProcess());
        auditMasterInboundImpl.setDiscrepancyAmount(auditMasterInbound.getDiscrepancyAmount());
        auditMasterInboundImpl.setBatchId(auditMasterInbound.getBatchId());
        auditMasterInboundImpl.setFileName(auditMasterInbound.getFileName());
        auditMasterInboundImpl.setSentRecordAmountAttribute(auditMasterInbound.getSentRecordAmountAttribute());
        auditMasterInboundImpl.setStatus(auditMasterInbound.getStatus());
        auditMasterInboundImpl.setReceivedRecordAmount(auditMasterInbound.getReceivedRecordAmount());
        auditMasterInboundImpl.setValidRecordAmount(auditMasterInbound.getValidRecordAmount());
        auditMasterInboundImpl.setInvalidRecordCount(auditMasterInbound.getInvalidRecordCount());
        auditMasterInboundImpl.setReceivedRecordCount(auditMasterInbound.getReceivedRecordCount());
        auditMasterInboundImpl.setCreatedBy(auditMasterInbound.getCreatedBy());
        auditMasterInboundImpl.setChangeCount(auditMasterInbound.getChangeCount());
        auditMasterInboundImpl.setUnprocessedRecords(auditMasterInbound.getUnprocessedRecords());
        auditMasterInboundImpl.setDeleteCount(auditMasterInbound.getDeleteCount());
        auditMasterInboundImpl.setModifiedDate(auditMasterInbound.getModifiedDate());
        auditMasterInboundImpl.setAuditInboundSid(auditMasterInbound.getAuditInboundSid());
        auditMasterInboundImpl.setSentRecordAmount(auditMasterInbound.getSentRecordAmount());
        auditMasterInboundImpl.setSentRecordCount(auditMasterInbound.getSentRecordCount());
        auditMasterInboundImpl.setAddCount(auditMasterInbound.getAddCount());
        auditMasterInboundImpl.setSource(auditMasterInbound.getSource());
        auditMasterInboundImpl.setInvalidRecordAmount(auditMasterInbound.getInvalidRecordAmount());
        auditMasterInboundImpl.setInterfaceRunStartDate(auditMasterInbound.getInterfaceRunStartDate());

        return auditMasterInboundImpl;
    }

    /**
     * Returns the audit master inbound with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the audit master inbound
     * @return the audit master inbound
     * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAuditMasterInboundException, SystemException {
        AuditMasterInbound auditMasterInbound = fetchByPrimaryKey(primaryKey);

        if (auditMasterInbound == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAuditMasterInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return auditMasterInbound;
    }

    /**
     * Returns the audit master inbound with the primary key or throws a {@link com.stpl.app.NoSuchAuditMasterInboundException} if it could not be found.
     *
     * @param auditInboundSid the primary key of the audit master inbound
     * @return the audit master inbound
     * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound findByPrimaryKey(int auditInboundSid)
        throws NoSuchAuditMasterInboundException, SystemException {
        return findByPrimaryKey((Serializable) auditInboundSid);
    }

    /**
     * Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the audit master inbound
     * @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AuditMasterInbound auditMasterInbound = (AuditMasterInbound) EntityCacheUtil.getResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
                AuditMasterInboundImpl.class, primaryKey);

        if (auditMasterInbound == _nullAuditMasterInbound) {
            return null;
        }

        if (auditMasterInbound == null) {
            Session session = null;

            try {
                session = openSession();

                auditMasterInbound = (AuditMasterInbound) session.get(AuditMasterInboundImpl.class,
                        primaryKey);

                if (auditMasterInbound != null) {
                    cacheResult(auditMasterInbound);
                } else {
                    EntityCacheUtil.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
                        AuditMasterInboundImpl.class, primaryKey,
                        _nullAuditMasterInbound);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
                    AuditMasterInboundImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return auditMasterInbound;
    }

    /**
     * Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param auditInboundSid the primary key of the audit master inbound
     * @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AuditMasterInbound fetchByPrimaryKey(int auditInboundSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) auditInboundSid);
    }

    /**
     * Returns all the audit master inbounds.
     *
     * @return the audit master inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AuditMasterInbound> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the audit master inbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of audit master inbounds
     * @param end the upper bound of the range of audit master inbounds (not inclusive)
     * @return the range of audit master inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AuditMasterInbound> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the audit master inbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of audit master inbounds
     * @param end the upper bound of the range of audit master inbounds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of audit master inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AuditMasterInbound> findAll(int start, int end,
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

        List<AuditMasterInbound> list = (List<AuditMasterInbound>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_AUDITMASTERINBOUND);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_AUDITMASTERINBOUND;

                if (pagination) {
                    sql = sql.concat(AuditMasterInboundModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AuditMasterInbound>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AuditMasterInbound>(list);
                } else {
                    list = (List<AuditMasterInbound>) QueryUtil.list(q,
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
     * Removes all the audit master inbounds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AuditMasterInbound auditMasterInbound : findAll()) {
            remove(auditMasterInbound);
        }
    }

    /**
     * Returns the number of audit master inbounds.
     *
     * @return the number of audit master inbounds
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

                Query q = session.createQuery(_SQL_COUNT_AUDITMASTERINBOUND);

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
     * Initializes the audit master inbound persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.AuditMasterInbound")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AuditMasterInbound>> listenersList = new ArrayList<ModelListener<AuditMasterInbound>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AuditMasterInbound>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AuditMasterInboundImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
