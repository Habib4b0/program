package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldInventoryWdProjMasException;
import com.stpl.app.model.IvldInventoryWdProjMas;
import com.stpl.app.model.impl.IvldInventoryWdProjMasImpl;
import com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl;
import com.stpl.app.service.persistence.IvldInventoryWdProjMasPersistence;

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
 * The persistence implementation for the ivld inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMasPersistence
 * @see IvldInventoryWdProjMasUtil
 * @generated
 */
public class IvldInventoryWdProjMasPersistenceImpl extends BasePersistenceImpl<IvldInventoryWdProjMas>
    implements IvldInventoryWdProjMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldInventoryWdProjMasUtil} to access the ivld inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldInventoryWdProjMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
            IvldInventoryWdProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
            IvldInventoryWdProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDINVENTORYWDPROJMAS = "SELECT ivldInventoryWdProjMas FROM IvldInventoryWdProjMas ivldInventoryWdProjMas";
    private static final String _SQL_COUNT_IVLDINVENTORYWDPROJMAS = "SELECT COUNT(ivldInventoryWdProjMas) FROM IvldInventoryWdProjMas ivldInventoryWdProjMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldInventoryWdProjMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldInventoryWdProjMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldInventoryWdProjMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "inventoryWdProjMasIntfId", "week", "unitsWithdrawn",
                "reasonForFailure", "country", "year", "itemId", "modifiedDate",
                "organizationKey", "itemIdentifierCodeQualifier", "source",
                "createdDate", "createdBy", "day", "forecastVer", "batchId",
                "addChgDelIndicator", "itemIdentifier", "errorField",
                "errorCode", "intfInsertedDate", "modifiedBy",
                "ivldInventoryWdProjMasSid", "month", "reprocessedFlag",
                "forecastName", "amountWithdrawn", "checkRecord"
            });
    private static IvldInventoryWdProjMas _nullIvldInventoryWdProjMas = new IvldInventoryWdProjMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldInventoryWdProjMas> toCacheModel() {
                return _nullIvldInventoryWdProjMasCacheModel;
            }
        };

    private static CacheModel<IvldInventoryWdProjMas> _nullIvldInventoryWdProjMasCacheModel =
        new CacheModel<IvldInventoryWdProjMas>() {
            @Override
            public IvldInventoryWdProjMas toEntityModel() {
                return _nullIvldInventoryWdProjMas;
            }
        };

    public IvldInventoryWdProjMasPersistenceImpl() {
        setModelClass(IvldInventoryWdProjMas.class);
    }

    /**
     * Caches the ivld inventory wd proj mas in the entity cache if it is enabled.
     *
     * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
     */
    @Override
    public void cacheResult(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        EntityCacheUtil.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasImpl.class,
            ivldInventoryWdProjMas.getPrimaryKey(), ivldInventoryWdProjMas);

        ivldInventoryWdProjMas.resetOriginalValues();
    }

    /**
     * Caches the ivld inventory wd proj mases in the entity cache if it is enabled.
     *
     * @param ivldInventoryWdProjMases the ivld inventory wd proj mases
     */
    @Override
    public void cacheResult(
        List<IvldInventoryWdProjMas> ivldInventoryWdProjMases) {
        for (IvldInventoryWdProjMas ivldInventoryWdProjMas : ivldInventoryWdProjMases) {
            if (EntityCacheUtil.getResult(
                        IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        IvldInventoryWdProjMasImpl.class,
                        ivldInventoryWdProjMas.getPrimaryKey()) == null) {
                cacheResult(ivldInventoryWdProjMas);
            } else {
                ivldInventoryWdProjMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld inventory wd proj mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldInventoryWdProjMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldInventoryWdProjMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld inventory wd proj mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        EntityCacheUtil.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasImpl.class,
            ivldInventoryWdProjMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<IvldInventoryWdProjMas> ivldInventoryWdProjMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldInventoryWdProjMas ivldInventoryWdProjMas : ivldInventoryWdProjMases) {
            EntityCacheUtil.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                IvldInventoryWdProjMasImpl.class,
                ivldInventoryWdProjMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
     *
     * @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
     * @return the new ivld inventory wd proj mas
     */
    @Override
    public IvldInventoryWdProjMas create(int ivldInventoryWdProjMasSid) {
        IvldInventoryWdProjMas ivldInventoryWdProjMas = new IvldInventoryWdProjMasImpl();

        ivldInventoryWdProjMas.setNew(true);
        ivldInventoryWdProjMas.setPrimaryKey(ivldInventoryWdProjMasSid);

        return ivldInventoryWdProjMas;
    }

    /**
     * Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas that was removed
     * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas remove(int ivldInventoryWdProjMasSid)
        throws NoSuchIvldInventoryWdProjMasException, SystemException {
        return remove((Serializable) ivldInventoryWdProjMasSid);
    }

    /**
     * Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas that was removed
     * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas remove(Serializable primaryKey)
        throws NoSuchIvldInventoryWdProjMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldInventoryWdProjMas ivldInventoryWdProjMas = (IvldInventoryWdProjMas) session.get(IvldInventoryWdProjMasImpl.class,
                    primaryKey);

            if (ivldInventoryWdProjMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldInventoryWdProjMas);
        } catch (NoSuchIvldInventoryWdProjMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldInventoryWdProjMas removeImpl(
        IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws SystemException {
        ivldInventoryWdProjMas = toUnwrappedModel(ivldInventoryWdProjMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldInventoryWdProjMas)) {
                ivldInventoryWdProjMas = (IvldInventoryWdProjMas) session.get(IvldInventoryWdProjMasImpl.class,
                        ivldInventoryWdProjMas.getPrimaryKeyObj());
            }

            if (ivldInventoryWdProjMas != null) {
                session.delete(ivldInventoryWdProjMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldInventoryWdProjMas != null) {
            clearCache(ivldInventoryWdProjMas);
        }

        return ivldInventoryWdProjMas;
    }

    @Override
    public IvldInventoryWdProjMas updateImpl(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws SystemException {
        ivldInventoryWdProjMas = toUnwrappedModel(ivldInventoryWdProjMas);

        boolean isNew = ivldInventoryWdProjMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldInventoryWdProjMas.isNew()) {
                session.save(ivldInventoryWdProjMas);

                ivldInventoryWdProjMas.setNew(false);
            } else {
                session.merge(ivldInventoryWdProjMas);
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

        EntityCacheUtil.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            IvldInventoryWdProjMasImpl.class,
            ivldInventoryWdProjMas.getPrimaryKey(), ivldInventoryWdProjMas);

        return ivldInventoryWdProjMas;
    }

    protected IvldInventoryWdProjMas toUnwrappedModel(
        IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        if (ivldInventoryWdProjMas instanceof IvldInventoryWdProjMasImpl) {
            return ivldInventoryWdProjMas;
        }

        IvldInventoryWdProjMasImpl ivldInventoryWdProjMasImpl = new IvldInventoryWdProjMasImpl();

        ivldInventoryWdProjMasImpl.setNew(ivldInventoryWdProjMas.isNew());
        ivldInventoryWdProjMasImpl.setPrimaryKey(ivldInventoryWdProjMas.getPrimaryKey());

        ivldInventoryWdProjMasImpl.setInventoryWdProjMasIntfId(ivldInventoryWdProjMas.getInventoryWdProjMasIntfId());
        ivldInventoryWdProjMasImpl.setWeek(ivldInventoryWdProjMas.getWeek());
        ivldInventoryWdProjMasImpl.setUnitsWithdrawn(ivldInventoryWdProjMas.getUnitsWithdrawn());
        ivldInventoryWdProjMasImpl.setReasonForFailure(ivldInventoryWdProjMas.getReasonForFailure());
        ivldInventoryWdProjMasImpl.setCountry(ivldInventoryWdProjMas.getCountry());
        ivldInventoryWdProjMasImpl.setYear(ivldInventoryWdProjMas.getYear());
        ivldInventoryWdProjMasImpl.setItemId(ivldInventoryWdProjMas.getItemId());
        ivldInventoryWdProjMasImpl.setModifiedDate(ivldInventoryWdProjMas.getModifiedDate());
        ivldInventoryWdProjMasImpl.setOrganizationKey(ivldInventoryWdProjMas.getOrganizationKey());
        ivldInventoryWdProjMasImpl.setItemIdentifierCodeQualifier(ivldInventoryWdProjMas.getItemIdentifierCodeQualifier());
        ivldInventoryWdProjMasImpl.setSource(ivldInventoryWdProjMas.getSource());
        ivldInventoryWdProjMasImpl.setCreatedDate(ivldInventoryWdProjMas.getCreatedDate());
        ivldInventoryWdProjMasImpl.setCreatedBy(ivldInventoryWdProjMas.getCreatedBy());
        ivldInventoryWdProjMasImpl.setDay(ivldInventoryWdProjMas.getDay());
        ivldInventoryWdProjMasImpl.setForecastVer(ivldInventoryWdProjMas.getForecastVer());
        ivldInventoryWdProjMasImpl.setBatchId(ivldInventoryWdProjMas.getBatchId());
        ivldInventoryWdProjMasImpl.setAddChgDelIndicator(ivldInventoryWdProjMas.getAddChgDelIndicator());
        ivldInventoryWdProjMasImpl.setItemIdentifier(ivldInventoryWdProjMas.getItemIdentifier());
        ivldInventoryWdProjMasImpl.setErrorField(ivldInventoryWdProjMas.getErrorField());
        ivldInventoryWdProjMasImpl.setErrorCode(ivldInventoryWdProjMas.getErrorCode());
        ivldInventoryWdProjMasImpl.setIntfInsertedDate(ivldInventoryWdProjMas.getIntfInsertedDate());
        ivldInventoryWdProjMasImpl.setModifiedBy(ivldInventoryWdProjMas.getModifiedBy());
        ivldInventoryWdProjMasImpl.setIvldInventoryWdProjMasSid(ivldInventoryWdProjMas.getIvldInventoryWdProjMasSid());
        ivldInventoryWdProjMasImpl.setMonth(ivldInventoryWdProjMas.getMonth());
        ivldInventoryWdProjMasImpl.setReprocessedFlag(ivldInventoryWdProjMas.getReprocessedFlag());
        ivldInventoryWdProjMasImpl.setForecastName(ivldInventoryWdProjMas.getForecastName());
        ivldInventoryWdProjMasImpl.setAmountWithdrawn(ivldInventoryWdProjMas.getAmountWithdrawn());
        ivldInventoryWdProjMasImpl.setCheckRecord(ivldInventoryWdProjMas.isCheckRecord());

        return ivldInventoryWdProjMasImpl;
    }

    /**
     * Returns the ivld inventory wd proj mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas
     * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldInventoryWdProjMasException, SystemException {
        IvldInventoryWdProjMas ivldInventoryWdProjMas = fetchByPrimaryKey(primaryKey);

        if (ivldInventoryWdProjMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldInventoryWdProjMas;
    }

    /**
     * Returns the ivld inventory wd proj mas with the primary key or throws a {@link com.stpl.app.NoSuchIvldInventoryWdProjMasException} if it could not be found.
     *
     * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas
     * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas findByPrimaryKey(
        int ivldInventoryWdProjMasSid)
        throws NoSuchIvldInventoryWdProjMasException, SystemException {
        return findByPrimaryKey((Serializable) ivldInventoryWdProjMasSid);
    }

    /**
     * Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldInventoryWdProjMas ivldInventoryWdProjMas = (IvldInventoryWdProjMas) EntityCacheUtil.getResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                IvldInventoryWdProjMasImpl.class, primaryKey);

        if (ivldInventoryWdProjMas == _nullIvldInventoryWdProjMas) {
            return null;
        }

        if (ivldInventoryWdProjMas == null) {
            Session session = null;

            try {
                session = openSession();

                ivldInventoryWdProjMas = (IvldInventoryWdProjMas) session.get(IvldInventoryWdProjMasImpl.class,
                        primaryKey);

                if (ivldInventoryWdProjMas != null) {
                    cacheResult(ivldInventoryWdProjMas);
                } else {
                    EntityCacheUtil.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        IvldInventoryWdProjMasImpl.class, primaryKey,
                        _nullIvldInventoryWdProjMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                    IvldInventoryWdProjMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldInventoryWdProjMas;
    }

    /**
     * Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
     * @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldInventoryWdProjMas fetchByPrimaryKey(
        int ivldInventoryWdProjMasSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldInventoryWdProjMasSid);
    }

    /**
     * Returns all the ivld inventory wd proj mases.
     *
     * @return the ivld inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdProjMas> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld inventory wd proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld inventory wd proj mases
     * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
     * @return the range of ivld inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdProjMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld inventory wd proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld inventory wd proj mases
     * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldInventoryWdProjMas> findAll(int start, int end,
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

        List<IvldInventoryWdProjMas> list = (List<IvldInventoryWdProjMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDINVENTORYWDPROJMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDINVENTORYWDPROJMAS;

                if (pagination) {
                    sql = sql.concat(IvldInventoryWdProjMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldInventoryWdProjMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldInventoryWdProjMas>(list);
                } else {
                    list = (List<IvldInventoryWdProjMas>) QueryUtil.list(q,
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
     * Removes all the ivld inventory wd proj mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldInventoryWdProjMas ivldInventoryWdProjMas : findAll()) {
            remove(ivldInventoryWdProjMas);
        }
    }

    /**
     * Returns the number of ivld inventory wd proj mases.
     *
     * @return the number of ivld inventory wd proj mases
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

                Query q = session.createQuery(_SQL_COUNT_IVLDINVENTORYWDPROJMAS);

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
     * Initializes the ivld inventory wd proj mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldInventoryWdProjMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldInventoryWdProjMas>> listenersList = new ArrayList<ModelListener<IvldInventoryWdProjMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldInventoryWdProjMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldInventoryWdProjMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
