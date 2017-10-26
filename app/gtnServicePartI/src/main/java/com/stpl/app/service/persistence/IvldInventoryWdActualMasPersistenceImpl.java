package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldInventoryWdActualMasException;
import com.stpl.app.model.IvldInventoryWdActualMas;
import com.stpl.app.model.impl.IvldInventoryWdActualMasImpl;
import com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl;
import com.stpl.app.service.persistence.IvldInventoryWdActualMasPersistence;

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
 * The persistence implementation for the ivld inventory wd actual mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdActualMasPersistence
 * @see IvldInventoryWdActualMasUtil
 * @generated
 */
public class IvldInventoryWdActualMasPersistenceImpl extends BasePersistenceImpl<IvldInventoryWdActualMas>
    implements IvldInventoryWdActualMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldInventoryWdActualMasUtil} to access the ivld inventory wd actual mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldInventoryWdActualMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
            IvldInventoryWdActualMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
            IvldInventoryWdActualMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDINVENTORYWDACTUALMAS = "SELECT ivldInventoryWdActualMas FROM IvldInventoryWdActualMas ivldInventoryWdActualMas";
    private static final String _SQL_COUNT_IVLDINVENTORYWDACTUALMAS = "SELECT COUNT(ivldInventoryWdActualMas) FROM IvldInventoryWdActualMas ivldInventoryWdActualMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldInventoryWdActualMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldInventoryWdActualMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldInventoryWdActualMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "quantityOnOrder", "week", "amountOnHand", "year", "itemId",
                "modifiedDate", "organizationKey", "createdBy", "createdDate",
                "source", "ivldInventoryWdActualMasSid", "day",
                "addChgDelIndicator", "unitsOnHand", "amountReceived",
                "itemIdentifier", "errorCode", "intfInsertedDate", "modifiedBy",
                "month", "reprocessedFlag", "amountWithdrawn",
                "inventoryWdActualMasIntfId", "quantityReceived",
                "unitsWithdrawn", "reasonForFailure", "country",
                "itemIdentifierCodeQualifier", "batchId", "errorField",
                "amountOnOrder", "checkRecord"
            });
    private static IvldInventoryWdActualMas _nullIvldInventoryWdActualMas = new IvldInventoryWdActualMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldInventoryWdActualMas> toCacheModel() {
                return _nullIvldInventoryWdActualMasCacheModel;
            }
        };

    private static CacheModel<IvldInventoryWdActualMas> _nullIvldInventoryWdActualMasCacheModel =
        new CacheModel<IvldInventoryWdActualMas>() {
            @Override
            public IvldInventoryWdActualMas toEntityModel() {
                return _nullIvldInventoryWdActualMas;
            }
        };

    public IvldInventoryWdActualMasPersistenceImpl() {
        setModelClass(IvldInventoryWdActualMas.class);
    }

    /**
     * Caches the ivld inventory wd actual mas in the entity cache if it is enabled.
     *
     * @param ivldInventoryWdActualMas the ivld inventory wd actual mas
     */
    @Override
    public void cacheResult(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
        EntityCacheUtil.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasImpl.class,
            ivldInventoryWdActualMas.getPrimaryKey(), ivldInventoryWdActualMas);

        ivldInventoryWdActualMas.resetOriginalValues();
    }

    /**
     * Caches the ivld inventory wd actual mases in the entity cache if it is enabled.
     *
     * @param ivldInventoryWdActualMases the ivld inventory wd actual mases
     */
    @Override
    public void cacheResult(
        List<IvldInventoryWdActualMas> ivldInventoryWdActualMases) {
        for (IvldInventoryWdActualMas ivldInventoryWdActualMas : ivldInventoryWdActualMases) {
            if (EntityCacheUtil.getResult(
                        IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                        IvldInventoryWdActualMasImpl.class,
                        ivldInventoryWdActualMas.getPrimaryKey()) == null) {
                cacheResult(ivldInventoryWdActualMas);
            } else {
                ivldInventoryWdActualMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld inventory wd actual mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldInventoryWdActualMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldInventoryWdActualMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld inventory wd actual mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
        EntityCacheUtil.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasImpl.class,
            ivldInventoryWdActualMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<IvldInventoryWdActualMas> ivldInventoryWdActualMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldInventoryWdActualMas ivldInventoryWdActualMas : ivldInventoryWdActualMases) {
            EntityCacheUtil.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                IvldInventoryWdActualMasImpl.class,
                ivldInventoryWdActualMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld inventory wd actual mas with the primary key. Does not add the ivld inventory wd actual mas to the database.
     *
     * @param ivldInventoryWdActualMasSid the primary key for the new ivld inventory wd actual mas
     * @return the new ivld inventory wd actual mas
     */
    @Override
    public IvldInventoryWdActualMas create(int ivldInventoryWdActualMasSid) {
        IvldInventoryWdActualMas ivldInventoryWdActualMas = new IvldInventoryWdActualMasImpl();

        ivldInventoryWdActualMas.setNew(true);
        ivldInventoryWdActualMas.setPrimaryKey(ivldInventoryWdActualMasSid);

        return ivldInventoryWdActualMas;
    }

    /**
     * Removes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas that was removed
     * @throws com.stpl.app.NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas remove(int ivldInventoryWdActualMasSid)
        throws NoSuchIvldInventoryWdActualMasException, SystemException {
        return remove((Serializable) ivldInventoryWdActualMasSid);
    }

    /**
     * Removes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas that was removed
     * @throws com.stpl.app.NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas remove(Serializable primaryKey)
        throws NoSuchIvldInventoryWdActualMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldInventoryWdActualMas ivldInventoryWdActualMas = (IvldInventoryWdActualMas) session.get(IvldInventoryWdActualMasImpl.class,
                    primaryKey);

            if (ivldInventoryWdActualMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldInventoryWdActualMas);
        } catch (NoSuchIvldInventoryWdActualMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldInventoryWdActualMas removeImpl(
        IvldInventoryWdActualMas ivldInventoryWdActualMas)
        throws SystemException {
        ivldInventoryWdActualMas = toUnwrappedModel(ivldInventoryWdActualMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldInventoryWdActualMas)) {
                ivldInventoryWdActualMas = (IvldInventoryWdActualMas) session.get(IvldInventoryWdActualMasImpl.class,
                        ivldInventoryWdActualMas.getPrimaryKeyObj());
            }

            if (ivldInventoryWdActualMas != null) {
                session.delete(ivldInventoryWdActualMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldInventoryWdActualMas != null) {
            clearCache(ivldInventoryWdActualMas);
        }

        return ivldInventoryWdActualMas;
    }

    @Override
    public IvldInventoryWdActualMas updateImpl(
        com.stpl.app.model.IvldInventoryWdActualMas ivldInventoryWdActualMas)
        throws SystemException {
        ivldInventoryWdActualMas = toUnwrappedModel(ivldInventoryWdActualMas);

        boolean isNew = ivldInventoryWdActualMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldInventoryWdActualMas.isNew()) {
                session.save(ivldInventoryWdActualMas);

                ivldInventoryWdActualMas.setNew(false);
            } else {
                session.merge(ivldInventoryWdActualMas);
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

        EntityCacheUtil.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdActualMasImpl.class,
            ivldInventoryWdActualMas.getPrimaryKey(), ivldInventoryWdActualMas);

        return ivldInventoryWdActualMas;
    }

    protected IvldInventoryWdActualMas toUnwrappedModel(
        IvldInventoryWdActualMas ivldInventoryWdActualMas) {
        if (ivldInventoryWdActualMas instanceof IvldInventoryWdActualMasImpl) {
            return ivldInventoryWdActualMas;
        }

        IvldInventoryWdActualMasImpl ivldInventoryWdActualMasImpl = new IvldInventoryWdActualMasImpl();

        ivldInventoryWdActualMasImpl.setNew(ivldInventoryWdActualMas.isNew());
        ivldInventoryWdActualMasImpl.setPrimaryKey(ivldInventoryWdActualMas.getPrimaryKey());

        ivldInventoryWdActualMasImpl.setQuantityOnOrder(ivldInventoryWdActualMas.getQuantityOnOrder());
        ivldInventoryWdActualMasImpl.setWeek(ivldInventoryWdActualMas.getWeek());
        ivldInventoryWdActualMasImpl.setAmountOnHand(ivldInventoryWdActualMas.getAmountOnHand());
        ivldInventoryWdActualMasImpl.setYear(ivldInventoryWdActualMas.getYear());
        ivldInventoryWdActualMasImpl.setItemId(ivldInventoryWdActualMas.getItemId());
        ivldInventoryWdActualMasImpl.setModifiedDate(ivldInventoryWdActualMas.getModifiedDate());
        ivldInventoryWdActualMasImpl.setOrganizationKey(ivldInventoryWdActualMas.getOrganizationKey());
        ivldInventoryWdActualMasImpl.setCreatedBy(ivldInventoryWdActualMas.getCreatedBy());
        ivldInventoryWdActualMasImpl.setCreatedDate(ivldInventoryWdActualMas.getCreatedDate());
        ivldInventoryWdActualMasImpl.setSource(ivldInventoryWdActualMas.getSource());
        ivldInventoryWdActualMasImpl.setIvldInventoryWdActualMasSid(ivldInventoryWdActualMas.getIvldInventoryWdActualMasSid());
        ivldInventoryWdActualMasImpl.setDay(ivldInventoryWdActualMas.getDay());
        ivldInventoryWdActualMasImpl.setAddChgDelIndicator(ivldInventoryWdActualMas.getAddChgDelIndicator());
        ivldInventoryWdActualMasImpl.setUnitsOnHand(ivldInventoryWdActualMas.getUnitsOnHand());
        ivldInventoryWdActualMasImpl.setAmountReceived(ivldInventoryWdActualMas.getAmountReceived());
        ivldInventoryWdActualMasImpl.setItemIdentifier(ivldInventoryWdActualMas.getItemIdentifier());
        ivldInventoryWdActualMasImpl.setErrorCode(ivldInventoryWdActualMas.getErrorCode());
        ivldInventoryWdActualMasImpl.setIntfInsertedDate(ivldInventoryWdActualMas.getIntfInsertedDate());
        ivldInventoryWdActualMasImpl.setModifiedBy(ivldInventoryWdActualMas.getModifiedBy());
        ivldInventoryWdActualMasImpl.setMonth(ivldInventoryWdActualMas.getMonth());
        ivldInventoryWdActualMasImpl.setReprocessedFlag(ivldInventoryWdActualMas.getReprocessedFlag());
        ivldInventoryWdActualMasImpl.setAmountWithdrawn(ivldInventoryWdActualMas.getAmountWithdrawn());
        ivldInventoryWdActualMasImpl.setInventoryWdActualMasIntfId(ivldInventoryWdActualMas.getInventoryWdActualMasIntfId());
        ivldInventoryWdActualMasImpl.setQuantityReceived(ivldInventoryWdActualMas.getQuantityReceived());
        ivldInventoryWdActualMasImpl.setUnitsWithdrawn(ivldInventoryWdActualMas.getUnitsWithdrawn());
        ivldInventoryWdActualMasImpl.setReasonForFailure(ivldInventoryWdActualMas.getReasonForFailure());
        ivldInventoryWdActualMasImpl.setCountry(ivldInventoryWdActualMas.getCountry());
        ivldInventoryWdActualMasImpl.setItemIdentifierCodeQualifier(ivldInventoryWdActualMas.getItemIdentifierCodeQualifier());
        ivldInventoryWdActualMasImpl.setBatchId(ivldInventoryWdActualMas.getBatchId());
        ivldInventoryWdActualMasImpl.setErrorField(ivldInventoryWdActualMas.getErrorField());
        ivldInventoryWdActualMasImpl.setAmountOnOrder(ivldInventoryWdActualMas.getAmountOnOrder());
        ivldInventoryWdActualMasImpl.setCheckRecord(ivldInventoryWdActualMas.isCheckRecord());

        return ivldInventoryWdActualMasImpl;
    }

    /**
     * Returns the ivld inventory wd actual mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas
     * @throws com.stpl.app.NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldInventoryWdActualMasException, SystemException {
        IvldInventoryWdActualMas ivldInventoryWdActualMas = fetchByPrimaryKey(primaryKey);

        if (ivldInventoryWdActualMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldInventoryWdActualMas;
    }

    /**
     * Returns the ivld inventory wd actual mas with the primary key or throws a {@link com.stpl.app.NoSuchIvldInventoryWdActualMasException} if it could not be found.
     *
     * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas
     * @throws com.stpl.app.NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas findByPrimaryKey(
        int ivldInventoryWdActualMasSid)
        throws NoSuchIvldInventoryWdActualMasException, SystemException {
        return findByPrimaryKey((Serializable) ivldInventoryWdActualMasSid);
    }

    /**
     * Returns the ivld inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas, or <code>null</code> if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldInventoryWdActualMas ivldInventoryWdActualMas = (IvldInventoryWdActualMas) EntityCacheUtil.getResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                IvldInventoryWdActualMasImpl.class, primaryKey);

        if (ivldInventoryWdActualMas == _nullIvldInventoryWdActualMas) {
            return null;
        }

        if (ivldInventoryWdActualMas == null) {
            Session session = null;

            try {
                session = openSession();

                ivldInventoryWdActualMas = (IvldInventoryWdActualMas) session.get(IvldInventoryWdActualMasImpl.class,
                        primaryKey);

                if (ivldInventoryWdActualMas != null) {
                    cacheResult(ivldInventoryWdActualMas);
                } else {
                    EntityCacheUtil.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                        IvldInventoryWdActualMasImpl.class, primaryKey,
                        _nullIvldInventoryWdActualMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                    IvldInventoryWdActualMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldInventoryWdActualMas;
    }

    /**
     * Returns the ivld inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
     * @return the ivld inventory wd actual mas, or <code>null</code> if a ivld inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdActualMas fetchByPrimaryKey(
        int ivldInventoryWdActualMasSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldInventoryWdActualMasSid);
    }

    /**
     * Returns all the ivld inventory wd actual mases.
     *
     * @return the ivld inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdActualMas> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld inventory wd actual mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld inventory wd actual mases
     * @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
     * @return the range of ivld inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdActualMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld inventory wd actual mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld inventory wd actual mases
     * @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdActualMas> findAll(int start, int end,
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

        List<IvldInventoryWdActualMas> list = (List<IvldInventoryWdActualMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDINVENTORYWDACTUALMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDINVENTORYWDACTUALMAS;

                if (pagination) {
                    sql = sql.concat(IvldInventoryWdActualMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldInventoryWdActualMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldInventoryWdActualMas>(list);
                } else {
                    list = (List<IvldInventoryWdActualMas>) QueryUtil.list(q,
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
     * Removes all the ivld inventory wd actual mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldInventoryWdActualMas ivldInventoryWdActualMas : findAll()) {
            remove(ivldInventoryWdActualMas);
        }
    }

    /**
     * Returns the number of ivld inventory wd actual mases.
     *
     * @return the number of ivld inventory wd actual mases
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

                Query q = session.createQuery(_SQL_COUNT_IVLDINVENTORYWDACTUALMAS);

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
     * Initializes the ivld inventory wd actual mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldInventoryWdActualMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldInventoryWdActualMas>> listenersList = new ArrayList<ModelListener<IvldInventoryWdActualMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldInventoryWdActualMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldInventoryWdActualMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
