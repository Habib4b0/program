package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualImpl;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualPersistence;

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
 * The persistence implementation for the ivld customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActualPersistence
 * @see IvldCustomerGtsActualUtil
 * @generated
 */
public class IvldCustomerGtsActualPersistenceImpl extends BasePersistenceImpl<IvldCustomerGtsActual>
    implements IvldCustomerGtsActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCustomerGtsActualUtil} to access the ivld customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCustomerGtsActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
            IvldCustomerGtsActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
            IvldCustomerGtsActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCUSTOMERGTSACTUAL = "SELECT ivldCustomerGtsActual FROM IvldCustomerGtsActual ivldCustomerGtsActual";
    private static final String _SQL_COUNT_IVLDCUSTOMERGTSACTUAL = "SELECT COUNT(ivldCustomerGtsActual) FROM IvldCustomerGtsActual ivldCustomerGtsActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCustomerGtsActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCustomerGtsActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCustomerGtsActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "parentAccountId", "ivldCustomerGtsActualSid", "accountId",
                "itemId", "orderReceivedDate", "modifiedDate", "orderNumber",
                "organizationKey", "source", "createdBy", "createdDate",
                "addChgDelIndicator", "errorCode", "itemUom", "invoiceNumber",
                "modifiedBy", "intfInsertedDate", "lotNo", "reprocessedFlag",
                "quantity", "invoiceLineNumber", "contractId",
                "reasonForFailure", "amount", "invoiceDate",
                "customerGtsActualIntfId", "batchId", "salesId", "errorField"
            });
    private static IvldCustomerGtsActual _nullIvldCustomerGtsActual = new IvldCustomerGtsActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCustomerGtsActual> toCacheModel() {
                return _nullIvldCustomerGtsActualCacheModel;
            }
        };

    private static CacheModel<IvldCustomerGtsActual> _nullIvldCustomerGtsActualCacheModel =
        new CacheModel<IvldCustomerGtsActual>() {
            @Override
            public IvldCustomerGtsActual toEntityModel() {
                return _nullIvldCustomerGtsActual;
            }
        };

    public IvldCustomerGtsActualPersistenceImpl() {
        setModelClass(IvldCustomerGtsActual.class);
    }

    /**
     * Caches the ivld customer gts actual in the entity cache if it is enabled.
     *
     * @param ivldCustomerGtsActual the ivld customer gts actual
     */
    @Override
    public void cacheResult(IvldCustomerGtsActual ivldCustomerGtsActual) {
        EntityCacheUtil.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualImpl.class,
            ivldCustomerGtsActual.getPrimaryKey(), ivldCustomerGtsActual);

        ivldCustomerGtsActual.resetOriginalValues();
    }

    /**
     * Caches the ivld customer gts actuals in the entity cache if it is enabled.
     *
     * @param ivldCustomerGtsActuals the ivld customer gts actuals
     */
    @Override
    public void cacheResult(List<IvldCustomerGtsActual> ivldCustomerGtsActuals) {
        for (IvldCustomerGtsActual ivldCustomerGtsActual : ivldCustomerGtsActuals) {
            if (EntityCacheUtil.getResult(
                        IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCustomerGtsActualImpl.class,
                        ivldCustomerGtsActual.getPrimaryKey()) == null) {
                cacheResult(ivldCustomerGtsActual);
            } else {
                ivldCustomerGtsActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld customer gts actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCustomerGtsActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCustomerGtsActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld customer gts actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCustomerGtsActual ivldCustomerGtsActual) {
        EntityCacheUtil.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualImpl.class,
            ivldCustomerGtsActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCustomerGtsActual> ivldCustomerGtsActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCustomerGtsActual ivldCustomerGtsActual : ivldCustomerGtsActuals) {
            EntityCacheUtil.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                IvldCustomerGtsActualImpl.class,
                ivldCustomerGtsActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld customer gts actual with the primary key. Does not add the ivld customer gts actual to the database.
     *
     * @param ivldCustomerGtsActualSid the primary key for the new ivld customer gts actual
     * @return the new ivld customer gts actual
     */
    @Override
    public IvldCustomerGtsActual create(int ivldCustomerGtsActualSid) {
        IvldCustomerGtsActual ivldCustomerGtsActual = new IvldCustomerGtsActualImpl();

        ivldCustomerGtsActual.setNew(true);
        ivldCustomerGtsActual.setPrimaryKey(ivldCustomerGtsActualSid);

        return ivldCustomerGtsActual;
    }

    /**
     * Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual remove(int ivldCustomerGtsActualSid)
        throws NoSuchIvldCustomerGtsActualException, SystemException {
        return remove((Serializable) ivldCustomerGtsActualSid);
    }

    /**
     * Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual remove(Serializable primaryKey)
        throws NoSuchIvldCustomerGtsActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCustomerGtsActual ivldCustomerGtsActual = (IvldCustomerGtsActual) session.get(IvldCustomerGtsActualImpl.class,
                    primaryKey);

            if (ivldCustomerGtsActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCustomerGtsActual);
        } catch (NoSuchIvldCustomerGtsActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCustomerGtsActual removeImpl(
        IvldCustomerGtsActual ivldCustomerGtsActual) throws SystemException {
        ivldCustomerGtsActual = toUnwrappedModel(ivldCustomerGtsActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCustomerGtsActual)) {
                ivldCustomerGtsActual = (IvldCustomerGtsActual) session.get(IvldCustomerGtsActualImpl.class,
                        ivldCustomerGtsActual.getPrimaryKeyObj());
            }

            if (ivldCustomerGtsActual != null) {
                session.delete(ivldCustomerGtsActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCustomerGtsActual != null) {
            clearCache(ivldCustomerGtsActual);
        }

        return ivldCustomerGtsActual;
    }

    @Override
    public IvldCustomerGtsActual updateImpl(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual)
        throws SystemException {
        ivldCustomerGtsActual = toUnwrappedModel(ivldCustomerGtsActual);

        boolean isNew = ivldCustomerGtsActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCustomerGtsActual.isNew()) {
                session.save(ivldCustomerGtsActual);

                ivldCustomerGtsActual.setNew(false);
            } else {
                session.merge(ivldCustomerGtsActual);
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

        EntityCacheUtil.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsActualImpl.class,
            ivldCustomerGtsActual.getPrimaryKey(), ivldCustomerGtsActual);

        return ivldCustomerGtsActual;
    }

    protected IvldCustomerGtsActual toUnwrappedModel(
        IvldCustomerGtsActual ivldCustomerGtsActual) {
        if (ivldCustomerGtsActual instanceof IvldCustomerGtsActualImpl) {
            return ivldCustomerGtsActual;
        }

        IvldCustomerGtsActualImpl ivldCustomerGtsActualImpl = new IvldCustomerGtsActualImpl();

        ivldCustomerGtsActualImpl.setNew(ivldCustomerGtsActual.isNew());
        ivldCustomerGtsActualImpl.setPrimaryKey(ivldCustomerGtsActual.getPrimaryKey());

        ivldCustomerGtsActualImpl.setParentAccountId(ivldCustomerGtsActual.getParentAccountId());
        ivldCustomerGtsActualImpl.setIvldCustomerGtsActualSid(ivldCustomerGtsActual.getIvldCustomerGtsActualSid());
        ivldCustomerGtsActualImpl.setAccountId(ivldCustomerGtsActual.getAccountId());
        ivldCustomerGtsActualImpl.setItemId(ivldCustomerGtsActual.getItemId());
        ivldCustomerGtsActualImpl.setOrderReceivedDate(ivldCustomerGtsActual.getOrderReceivedDate());
        ivldCustomerGtsActualImpl.setModifiedDate(ivldCustomerGtsActual.getModifiedDate());
        ivldCustomerGtsActualImpl.setOrderNumber(ivldCustomerGtsActual.getOrderNumber());
        ivldCustomerGtsActualImpl.setOrganizationKey(ivldCustomerGtsActual.getOrganizationKey());
        ivldCustomerGtsActualImpl.setSource(ivldCustomerGtsActual.getSource());
        ivldCustomerGtsActualImpl.setCreatedBy(ivldCustomerGtsActual.getCreatedBy());
        ivldCustomerGtsActualImpl.setCreatedDate(ivldCustomerGtsActual.getCreatedDate());
        ivldCustomerGtsActualImpl.setAddChgDelIndicator(ivldCustomerGtsActual.getAddChgDelIndicator());
        ivldCustomerGtsActualImpl.setErrorCode(ivldCustomerGtsActual.getErrorCode());
        ivldCustomerGtsActualImpl.setItemUom(ivldCustomerGtsActual.getItemUom());
        ivldCustomerGtsActualImpl.setInvoiceNumber(ivldCustomerGtsActual.getInvoiceNumber());
        ivldCustomerGtsActualImpl.setModifiedBy(ivldCustomerGtsActual.getModifiedBy());
        ivldCustomerGtsActualImpl.setIntfInsertedDate(ivldCustomerGtsActual.getIntfInsertedDate());
        ivldCustomerGtsActualImpl.setLotNo(ivldCustomerGtsActual.getLotNo());
        ivldCustomerGtsActualImpl.setReprocessedFlag(ivldCustomerGtsActual.getReprocessedFlag());
        ivldCustomerGtsActualImpl.setQuantity(ivldCustomerGtsActual.getQuantity());
        ivldCustomerGtsActualImpl.setInvoiceLineNumber(ivldCustomerGtsActual.getInvoiceLineNumber());
        ivldCustomerGtsActualImpl.setContractId(ivldCustomerGtsActual.getContractId());
        ivldCustomerGtsActualImpl.setReasonForFailure(ivldCustomerGtsActual.getReasonForFailure());
        ivldCustomerGtsActualImpl.setAmount(ivldCustomerGtsActual.getAmount());
        ivldCustomerGtsActualImpl.setInvoiceDate(ivldCustomerGtsActual.getInvoiceDate());
        ivldCustomerGtsActualImpl.setCustomerGtsActualIntfId(ivldCustomerGtsActual.getCustomerGtsActualIntfId());
        ivldCustomerGtsActualImpl.setBatchId(ivldCustomerGtsActual.getBatchId());
        ivldCustomerGtsActualImpl.setSalesId(ivldCustomerGtsActual.getSalesId());
        ivldCustomerGtsActualImpl.setErrorField(ivldCustomerGtsActual.getErrorField());

        return ivldCustomerGtsActualImpl;
    }

    /**
     * Returns the ivld customer gts actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCustomerGtsActualException, SystemException {
        IvldCustomerGtsActual ivldCustomerGtsActual = fetchByPrimaryKey(primaryKey);

        if (ivldCustomerGtsActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCustomerGtsActual;
    }

    /**
     * Returns the ivld customer gts actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException} if it could not be found.
     *
     * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual findByPrimaryKey(int ivldCustomerGtsActualSid)
        throws NoSuchIvldCustomerGtsActualException, SystemException {
        return findByPrimaryKey((Serializable) ivldCustomerGtsActualSid);
    }

    /**
     * Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCustomerGtsActual ivldCustomerGtsActual = (IvldCustomerGtsActual) EntityCacheUtil.getResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                IvldCustomerGtsActualImpl.class, primaryKey);

        if (ivldCustomerGtsActual == _nullIvldCustomerGtsActual) {
            return null;
        }

        if (ivldCustomerGtsActual == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCustomerGtsActual = (IvldCustomerGtsActual) session.get(IvldCustomerGtsActualImpl.class,
                        primaryKey);

                if (ivldCustomerGtsActual != null) {
                    cacheResult(ivldCustomerGtsActual);
                } else {
                    EntityCacheUtil.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCustomerGtsActualImpl.class, primaryKey,
                        _nullIvldCustomerGtsActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCustomerGtsActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCustomerGtsActual;
    }

    /**
     * Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
     * @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsActual fetchByPrimaryKey(int ivldCustomerGtsActualSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCustomerGtsActualSid);
    }

    /**
     * Returns all the ivld customer gts actuals.
     *
     * @return the ivld customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld customer gts actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld customer gts actuals
     * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
     * @return the range of ivld customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld customer gts actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld customer gts actuals
     * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsActual> findAll(int start, int end,
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

        List<IvldCustomerGtsActual> list = (List<IvldCustomerGtsActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCUSTOMERGTSACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCUSTOMERGTSACTUAL;

                if (pagination) {
                    sql = sql.concat(IvldCustomerGtsActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCustomerGtsActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCustomerGtsActual>(list);
                } else {
                    list = (List<IvldCustomerGtsActual>) QueryUtil.list(q,
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
     * Removes all the ivld customer gts actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCustomerGtsActual ivldCustomerGtsActual : findAll()) {
            remove(ivldCustomerGtsActual);
        }
    }

    /**
     * Returns the number of ivld customer gts actuals.
     *
     * @return the number of ivld customer gts actuals
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCUSTOMERGTSACTUAL);

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
     * Initializes the ivld customer gts actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCustomerGtsActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCustomerGtsActual>> listenersList = new ArrayList<ModelListener<IvldCustomerGtsActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCustomerGtsActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCustomerGtsActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
