package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldItemHierarchyException;
import com.stpl.app.model.IvldItemHierarchy;
import com.stpl.app.model.impl.IvldItemHierarchyImpl;
import com.stpl.app.model.impl.IvldItemHierarchyModelImpl;
import com.stpl.app.service.persistence.IvldItemHierarchyPersistence;

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
 * The persistence implementation for the ivld item hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyPersistence
 * @see IvldItemHierarchyUtil
 * @generated
 */
public class IvldItemHierarchyPersistenceImpl extends BasePersistenceImpl<IvldItemHierarchy>
    implements IvldItemHierarchyPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldItemHierarchyUtil} to access the ivld item hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldItemHierarchyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED,
            IvldItemHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED,
            IvldItemHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDITEMHIERARCHY = "SELECT ivldItemHierarchy FROM IvldItemHierarchy ivldItemHierarchy";
    private static final String _SQL_COUNT_IVLDITEMHIERARCHY = "SELECT COUNT(ivldItemHierarchy) FROM IvldItemHierarchy ivldItemHierarchy";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemHierarchy.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemHierarchy exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldItemHierarchyPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "level1Alias", "level9Alias", "level3Alias", "modifiedDate",
                "level13Alias", "gtnCompany", "source", "level15Alias",
                "addChgDelIndicator", "ivldItemHierarchySid", "modifiedBy",
                "level6Alias", "reasonForFailure", "level10Alias",
                "itemHierarchyIntfid", "level5Alias", "level18Alias",
                "errorField", "gtnTherapeuticClass", "level8", "level9",
                "level11Alias", "level20", "level4", "level5", "level6",
                "level7", "level16Alias", "createdDate", "createdBy", "gtnBrand",
                "level2Alias", "level1", "level0", "errorCode", "level3",
                "level17Alias", "level20Alias", "intfInsertedDate",
                "level7Alias", "level2", "reprocessedFlag", "level8Alias",
                "level10", "level4Alias", "level12", "level11", "level14",
                "level0Tag", "level13", "level16", "level15", "level18",
                "level17", "level19", "level12Alias", "level19Alias", "batchId",
                "level0Alias", "level14Alias", "checkRecord"
            });
    private static IvldItemHierarchy _nullIvldItemHierarchy = new IvldItemHierarchyImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldItemHierarchy> toCacheModel() {
                return _nullIvldItemHierarchyCacheModel;
            }
        };

    private static CacheModel<IvldItemHierarchy> _nullIvldItemHierarchyCacheModel =
        new CacheModel<IvldItemHierarchy>() {
            @Override
            public IvldItemHierarchy toEntityModel() {
                return _nullIvldItemHierarchy;
            }
        };

    public IvldItemHierarchyPersistenceImpl() {
        setModelClass(IvldItemHierarchy.class);
    }

    /**
     * Caches the ivld item hierarchy in the entity cache if it is enabled.
     *
     * @param ivldItemHierarchy the ivld item hierarchy
     */
    @Override
    public void cacheResult(IvldItemHierarchy ivldItemHierarchy) {
        EntityCacheUtil.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey(),
            ivldItemHierarchy);

        ivldItemHierarchy.resetOriginalValues();
    }

    /**
     * Caches the ivld item hierarchies in the entity cache if it is enabled.
     *
     * @param ivldItemHierarchies the ivld item hierarchies
     */
    @Override
    public void cacheResult(List<IvldItemHierarchy> ivldItemHierarchies) {
        for (IvldItemHierarchy ivldItemHierarchy : ivldItemHierarchies) {
            if (EntityCacheUtil.getResult(
                        IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemHierarchyImpl.class,
                        ivldItemHierarchy.getPrimaryKey()) == null) {
                cacheResult(ivldItemHierarchy);
            } else {
                ivldItemHierarchy.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld item hierarchies.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldItemHierarchyImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldItemHierarchyImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld item hierarchy.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldItemHierarchy ivldItemHierarchy) {
        EntityCacheUtil.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldItemHierarchy> ivldItemHierarchies) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldItemHierarchy ivldItemHierarchy : ivldItemHierarchies) {
            EntityCacheUtil.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld item hierarchy with the primary key. Does not add the ivld item hierarchy to the database.
     *
     * @param ivldItemHierarchySid the primary key for the new ivld item hierarchy
     * @return the new ivld item hierarchy
     */
    @Override
    public IvldItemHierarchy create(int ivldItemHierarchySid) {
        IvldItemHierarchy ivldItemHierarchy = new IvldItemHierarchyImpl();

        ivldItemHierarchy.setNew(true);
        ivldItemHierarchy.setPrimaryKey(ivldItemHierarchySid);

        return ivldItemHierarchy;
    }

    /**
     * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy that was removed
     * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy remove(int ivldItemHierarchySid)
        throws NoSuchIvldItemHierarchyException, SystemException {
        return remove((Serializable) ivldItemHierarchySid);
    }

    /**
     * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy that was removed
     * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy remove(Serializable primaryKey)
        throws NoSuchIvldItemHierarchyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldItemHierarchy ivldItemHierarchy = (IvldItemHierarchy) session.get(IvldItemHierarchyImpl.class,
                    primaryKey);

            if (ivldItemHierarchy == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldItemHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldItemHierarchy);
        } catch (NoSuchIvldItemHierarchyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldItemHierarchy removeImpl(IvldItemHierarchy ivldItemHierarchy)
        throws SystemException {
        ivldItemHierarchy = toUnwrappedModel(ivldItemHierarchy);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldItemHierarchy)) {
                ivldItemHierarchy = (IvldItemHierarchy) session.get(IvldItemHierarchyImpl.class,
                        ivldItemHierarchy.getPrimaryKeyObj());
            }

            if (ivldItemHierarchy != null) {
                session.delete(ivldItemHierarchy);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldItemHierarchy != null) {
            clearCache(ivldItemHierarchy);
        }

        return ivldItemHierarchy;
    }

    @Override
    public IvldItemHierarchy updateImpl(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws SystemException {
        ivldItemHierarchy = toUnwrappedModel(ivldItemHierarchy);

        boolean isNew = ivldItemHierarchy.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldItemHierarchy.isNew()) {
                session.save(ivldItemHierarchy);

                ivldItemHierarchy.setNew(false);
            } else {
                session.merge(ivldItemHierarchy);
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

        EntityCacheUtil.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey(),
            ivldItemHierarchy);

        return ivldItemHierarchy;
    }

    protected IvldItemHierarchy toUnwrappedModel(
        IvldItemHierarchy ivldItemHierarchy) {
        if (ivldItemHierarchy instanceof IvldItemHierarchyImpl) {
            return ivldItemHierarchy;
        }

        IvldItemHierarchyImpl ivldItemHierarchyImpl = new IvldItemHierarchyImpl();

        ivldItemHierarchyImpl.setNew(ivldItemHierarchy.isNew());
        ivldItemHierarchyImpl.setPrimaryKey(ivldItemHierarchy.getPrimaryKey());

        ivldItemHierarchyImpl.setLevel1Alias(ivldItemHierarchy.getLevel1Alias());
        ivldItemHierarchyImpl.setLevel9Alias(ivldItemHierarchy.getLevel9Alias());
        ivldItemHierarchyImpl.setLevel3Alias(ivldItemHierarchy.getLevel3Alias());
        ivldItemHierarchyImpl.setModifiedDate(ivldItemHierarchy.getModifiedDate());
        ivldItemHierarchyImpl.setLevel13Alias(ivldItemHierarchy.getLevel13Alias());
        ivldItemHierarchyImpl.setGtnCompany(ivldItemHierarchy.getGtnCompany());
        ivldItemHierarchyImpl.setSource(ivldItemHierarchy.getSource());
        ivldItemHierarchyImpl.setLevel15Alias(ivldItemHierarchy.getLevel15Alias());
        ivldItemHierarchyImpl.setAddChgDelIndicator(ivldItemHierarchy.getAddChgDelIndicator());
        ivldItemHierarchyImpl.setIvldItemHierarchySid(ivldItemHierarchy.getIvldItemHierarchySid());
        ivldItemHierarchyImpl.setModifiedBy(ivldItemHierarchy.getModifiedBy());
        ivldItemHierarchyImpl.setLevel6Alias(ivldItemHierarchy.getLevel6Alias());
        ivldItemHierarchyImpl.setReasonForFailure(ivldItemHierarchy.getReasonForFailure());
        ivldItemHierarchyImpl.setLevel10Alias(ivldItemHierarchy.getLevel10Alias());
        ivldItemHierarchyImpl.setItemHierarchyIntfid(ivldItemHierarchy.getItemHierarchyIntfid());
        ivldItemHierarchyImpl.setLevel5Alias(ivldItemHierarchy.getLevel5Alias());
        ivldItemHierarchyImpl.setLevel18Alias(ivldItemHierarchy.getLevel18Alias());
        ivldItemHierarchyImpl.setErrorField(ivldItemHierarchy.getErrorField());
        ivldItemHierarchyImpl.setGtnTherapeuticClass(ivldItemHierarchy.getGtnTherapeuticClass());
        ivldItemHierarchyImpl.setLevel8(ivldItemHierarchy.getLevel8());
        ivldItemHierarchyImpl.setLevel9(ivldItemHierarchy.getLevel9());
        ivldItemHierarchyImpl.setLevel11Alias(ivldItemHierarchy.getLevel11Alias());
        ivldItemHierarchyImpl.setLevel20(ivldItemHierarchy.getLevel20());
        ivldItemHierarchyImpl.setLevel4(ivldItemHierarchy.getLevel4());
        ivldItemHierarchyImpl.setLevel5(ivldItemHierarchy.getLevel5());
        ivldItemHierarchyImpl.setLevel6(ivldItemHierarchy.getLevel6());
        ivldItemHierarchyImpl.setLevel7(ivldItemHierarchy.getLevel7());
        ivldItemHierarchyImpl.setLevel16Alias(ivldItemHierarchy.getLevel16Alias());
        ivldItemHierarchyImpl.setCreatedDate(ivldItemHierarchy.getCreatedDate());
        ivldItemHierarchyImpl.setCreatedBy(ivldItemHierarchy.getCreatedBy());
        ivldItemHierarchyImpl.setGtnBrand(ivldItemHierarchy.getGtnBrand());
        ivldItemHierarchyImpl.setLevel2Alias(ivldItemHierarchy.getLevel2Alias());
        ivldItemHierarchyImpl.setLevel1(ivldItemHierarchy.getLevel1());
        ivldItemHierarchyImpl.setLevel0(ivldItemHierarchy.getLevel0());
        ivldItemHierarchyImpl.setErrorCode(ivldItemHierarchy.getErrorCode());
        ivldItemHierarchyImpl.setLevel3(ivldItemHierarchy.getLevel3());
        ivldItemHierarchyImpl.setLevel17Alias(ivldItemHierarchy.getLevel17Alias());
        ivldItemHierarchyImpl.setLevel20Alias(ivldItemHierarchy.getLevel20Alias());
        ivldItemHierarchyImpl.setIntfInsertedDate(ivldItemHierarchy.getIntfInsertedDate());
        ivldItemHierarchyImpl.setLevel7Alias(ivldItemHierarchy.getLevel7Alias());
        ivldItemHierarchyImpl.setLevel2(ivldItemHierarchy.getLevel2());
        ivldItemHierarchyImpl.setReprocessedFlag(ivldItemHierarchy.getReprocessedFlag());
        ivldItemHierarchyImpl.setLevel8Alias(ivldItemHierarchy.getLevel8Alias());
        ivldItemHierarchyImpl.setLevel10(ivldItemHierarchy.getLevel10());
        ivldItemHierarchyImpl.setLevel4Alias(ivldItemHierarchy.getLevel4Alias());
        ivldItemHierarchyImpl.setLevel12(ivldItemHierarchy.getLevel12());
        ivldItemHierarchyImpl.setLevel11(ivldItemHierarchy.getLevel11());
        ivldItemHierarchyImpl.setLevel14(ivldItemHierarchy.getLevel14());
        ivldItemHierarchyImpl.setLevel0Tag(ivldItemHierarchy.getLevel0Tag());
        ivldItemHierarchyImpl.setLevel13(ivldItemHierarchy.getLevel13());
        ivldItemHierarchyImpl.setLevel16(ivldItemHierarchy.getLevel16());
        ivldItemHierarchyImpl.setLevel15(ivldItemHierarchy.getLevel15());
        ivldItemHierarchyImpl.setLevel18(ivldItemHierarchy.getLevel18());
        ivldItemHierarchyImpl.setLevel17(ivldItemHierarchy.getLevel17());
        ivldItemHierarchyImpl.setLevel19(ivldItemHierarchy.getLevel19());
        ivldItemHierarchyImpl.setLevel12Alias(ivldItemHierarchy.getLevel12Alias());
        ivldItemHierarchyImpl.setLevel19Alias(ivldItemHierarchy.getLevel19Alias());
        ivldItemHierarchyImpl.setBatchId(ivldItemHierarchy.getBatchId());
        ivldItemHierarchyImpl.setLevel0Alias(ivldItemHierarchy.getLevel0Alias());
        ivldItemHierarchyImpl.setLevel14Alias(ivldItemHierarchy.getLevel14Alias());
        ivldItemHierarchyImpl.setCheckRecord(ivldItemHierarchy.isCheckRecord());

        return ivldItemHierarchyImpl;
    }

    /**
     * Returns the ivld item hierarchy with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy
     * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldItemHierarchyException, SystemException {
        IvldItemHierarchy ivldItemHierarchy = fetchByPrimaryKey(primaryKey);

        if (ivldItemHierarchy == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldItemHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldItemHierarchy;
    }

    /**
     * Returns the ivld item hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyException} if it could not be found.
     *
     * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy
     * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy findByPrimaryKey(int ivldItemHierarchySid)
        throws NoSuchIvldItemHierarchyException, SystemException {
        return findByPrimaryKey((Serializable) ivldItemHierarchySid);
    }

    /**
     * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldItemHierarchy ivldItemHierarchy = (IvldItemHierarchy) EntityCacheUtil.getResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemHierarchyImpl.class, primaryKey);

        if (ivldItemHierarchy == _nullIvldItemHierarchy) {
            return null;
        }

        if (ivldItemHierarchy == null) {
            Session session = null;

            try {
                session = openSession();

                ivldItemHierarchy = (IvldItemHierarchy) session.get(IvldItemHierarchyImpl.class,
                        primaryKey);

                if (ivldItemHierarchy != null) {
                    cacheResult(ivldItemHierarchy);
                } else {
                    EntityCacheUtil.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemHierarchyImpl.class, primaryKey,
                        _nullIvldItemHierarchy);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                    IvldItemHierarchyImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldItemHierarchy;
    }

    /**
     * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
     * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchy fetchByPrimaryKey(int ivldItemHierarchySid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldItemHierarchySid);
    }

    /**
     * Returns all the ivld item hierarchies.
     *
     * @return the ivld item hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchy> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld item hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item hierarchies
     * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
     * @return the range of ivld item hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchy> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld item hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item hierarchies
     * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld item hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchy> findAll(int start, int end,
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

        List<IvldItemHierarchy> list = (List<IvldItemHierarchy>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDITEMHIERARCHY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDITEMHIERARCHY;

                if (pagination) {
                    sql = sql.concat(IvldItemHierarchyModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldItemHierarchy>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldItemHierarchy>(list);
                } else {
                    list = (List<IvldItemHierarchy>) QueryUtil.list(q,
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
     * Removes all the ivld item hierarchies from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldItemHierarchy ivldItemHierarchy : findAll()) {
            remove(ivldItemHierarchy);
        }
    }

    /**
     * Returns the number of ivld item hierarchies.
     *
     * @return the number of ivld item hierarchies
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

                Query q = session.createQuery(_SQL_COUNT_IVLDITEMHIERARCHY);

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
     * Initializes the ivld item hierarchy persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldItemHierarchy")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldItemHierarchy>> listenersList = new ArrayList<ModelListener<IvldItemHierarchy>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldItemHierarchy>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldItemHierarchyImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
