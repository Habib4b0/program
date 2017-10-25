package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchBrandMasterException;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.impl.BrandMasterImpl;
import com.stpl.app.model.impl.BrandMasterModelImpl;
import com.stpl.app.service.persistence.BrandMasterPersistence;

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
 * The persistence implementation for the brand master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BrandMasterPersistence
 * @see BrandMasterUtil
 * @generated
 */
public class BrandMasterPersistenceImpl extends BasePersistenceImpl<BrandMaster>
    implements BrandMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BrandMasterUtil} to access the brand master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BrandMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterModelImpl.FINDER_CACHE_ENABLED, BrandMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterModelImpl.FINDER_CACHE_ENABLED, BrandMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_BRANDMASTER = "SELECT brandMaster FROM BrandMaster brandMaster";
    private static final String _SQL_COUNT_BRANDMASTER = "SELECT COUNT(brandMaster) FROM BrandMaster brandMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "brandMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BrandMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BrandMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "createdDate", "brandMasterSid",
                "batchId", "modifiedDate", "brandId", "displayBrand",
                "recordLockStatus", "brandName", "brandDesc", "source",
                "inboundStatus"
            });
    private static BrandMaster _nullBrandMaster = new BrandMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BrandMaster> toCacheModel() {
                return _nullBrandMasterCacheModel;
            }
        };

    private static CacheModel<BrandMaster> _nullBrandMasterCacheModel = new CacheModel<BrandMaster>() {
            @Override
            public BrandMaster toEntityModel() {
                return _nullBrandMaster;
            }
        };

    public BrandMasterPersistenceImpl() {
        setModelClass(BrandMaster.class);
    }

    /**
     * Caches the brand master in the entity cache if it is enabled.
     *
     * @param brandMaster the brand master
     */
    @Override
    public void cacheResult(BrandMaster brandMaster) {
        EntityCacheUtil.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterImpl.class, brandMaster.getPrimaryKey(), brandMaster);

        brandMaster.resetOriginalValues();
    }

    /**
     * Caches the brand masters in the entity cache if it is enabled.
     *
     * @param brandMasters the brand masters
     */
    @Override
    public void cacheResult(List<BrandMaster> brandMasters) {
        for (BrandMaster brandMaster : brandMasters) {
            if (EntityCacheUtil.getResult(
                        BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BrandMasterImpl.class, brandMaster.getPrimaryKey()) == null) {
                cacheResult(brandMaster);
            } else {
                brandMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all brand masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BrandMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BrandMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the brand master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BrandMaster brandMaster) {
        EntityCacheUtil.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterImpl.class, brandMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BrandMaster> brandMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BrandMaster brandMaster : brandMasters) {
            EntityCacheUtil.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
                BrandMasterImpl.class, brandMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new brand master with the primary key. Does not add the brand master to the database.
     *
     * @param brandMasterSid the primary key for the new brand master
     * @return the new brand master
     */
    @Override
    public BrandMaster create(int brandMasterSid) {
        BrandMaster brandMaster = new BrandMasterImpl();

        brandMaster.setNew(true);
        brandMaster.setPrimaryKey(brandMasterSid);

        return brandMaster;
    }

    /**
     * Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param brandMasterSid the primary key of the brand master
     * @return the brand master that was removed
     * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster remove(int brandMasterSid)
        throws NoSuchBrandMasterException, SystemException {
        return remove((Serializable) brandMasterSid);
    }

    /**
     * Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the brand master
     * @return the brand master that was removed
     * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster remove(Serializable primaryKey)
        throws NoSuchBrandMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BrandMaster brandMaster = (BrandMaster) session.get(BrandMasterImpl.class,
                    primaryKey);

            if (brandMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBrandMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(brandMaster);
        } catch (NoSuchBrandMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BrandMaster removeImpl(BrandMaster brandMaster)
        throws SystemException {
        brandMaster = toUnwrappedModel(brandMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(brandMaster)) {
                brandMaster = (BrandMaster) session.get(BrandMasterImpl.class,
                        brandMaster.getPrimaryKeyObj());
            }

            if (brandMaster != null) {
                session.delete(brandMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (brandMaster != null) {
            clearCache(brandMaster);
        }

        return brandMaster;
    }

    @Override
    public BrandMaster updateImpl(com.stpl.app.model.BrandMaster brandMaster)
        throws SystemException {
        brandMaster = toUnwrappedModel(brandMaster);

        boolean isNew = brandMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (brandMaster.isNew()) {
                session.save(brandMaster);

                brandMaster.setNew(false);
            } else {
                session.merge(brandMaster);
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

        EntityCacheUtil.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
            BrandMasterImpl.class, brandMaster.getPrimaryKey(), brandMaster);

        return brandMaster;
    }

    protected BrandMaster toUnwrappedModel(BrandMaster brandMaster) {
        if (brandMaster instanceof BrandMasterImpl) {
            return brandMaster;
        }

        BrandMasterImpl brandMasterImpl = new BrandMasterImpl();

        brandMasterImpl.setNew(brandMaster.isNew());
        brandMasterImpl.setPrimaryKey(brandMaster.getPrimaryKey());

        brandMasterImpl.setCreatedBy(brandMaster.getCreatedBy());
        brandMasterImpl.setModifiedBy(brandMaster.getModifiedBy());
        brandMasterImpl.setCreatedDate(brandMaster.getCreatedDate());
        brandMasterImpl.setBrandMasterSid(brandMaster.getBrandMasterSid());
        brandMasterImpl.setBatchId(brandMaster.getBatchId());
        brandMasterImpl.setModifiedDate(brandMaster.getModifiedDate());
        brandMasterImpl.setBrandId(brandMaster.getBrandId());
        brandMasterImpl.setDisplayBrand(brandMaster.getDisplayBrand());
        brandMasterImpl.setRecordLockStatus(brandMaster.isRecordLockStatus());
        brandMasterImpl.setBrandName(brandMaster.getBrandName());
        brandMasterImpl.setBrandDesc(brandMaster.getBrandDesc());
        brandMasterImpl.setSource(brandMaster.getSource());
        brandMasterImpl.setInboundStatus(brandMaster.getInboundStatus());

        return brandMasterImpl;
    }

    /**
     * Returns the brand master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the brand master
     * @return the brand master
     * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBrandMasterException, SystemException {
        BrandMaster brandMaster = fetchByPrimaryKey(primaryKey);

        if (brandMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBrandMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return brandMaster;
    }

    /**
     * Returns the brand master with the primary key or throws a {@link com.stpl.app.NoSuchBrandMasterException} if it could not be found.
     *
     * @param brandMasterSid the primary key of the brand master
     * @return the brand master
     * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster findByPrimaryKey(int brandMasterSid)
        throws NoSuchBrandMasterException, SystemException {
        return findByPrimaryKey((Serializable) brandMasterSid);
    }

    /**
     * Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the brand master
     * @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BrandMaster brandMaster = (BrandMaster) EntityCacheUtil.getResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
                BrandMasterImpl.class, primaryKey);

        if (brandMaster == _nullBrandMaster) {
            return null;
        }

        if (brandMaster == null) {
            Session session = null;

            try {
                session = openSession();

                brandMaster = (BrandMaster) session.get(BrandMasterImpl.class,
                        primaryKey);

                if (brandMaster != null) {
                    cacheResult(brandMaster);
                } else {
                    EntityCacheUtil.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BrandMasterImpl.class, primaryKey, _nullBrandMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
                    BrandMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return brandMaster;
    }

    /**
     * Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param brandMasterSid the primary key of the brand master
     * @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BrandMaster fetchByPrimaryKey(int brandMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) brandMasterSid);
    }

    /**
     * Returns all the brand masters.
     *
     * @return the brand masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BrandMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the brand masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of brand masters
     * @param end the upper bound of the range of brand masters (not inclusive)
     * @return the range of brand masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BrandMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the brand masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of brand masters
     * @param end the upper bound of the range of brand masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of brand masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BrandMaster> findAll(int start, int end,
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

        List<BrandMaster> list = (List<BrandMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BRANDMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BRANDMASTER;

                if (pagination) {
                    sql = sql.concat(BrandMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BrandMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BrandMaster>(list);
                } else {
                    list = (List<BrandMaster>) QueryUtil.list(q, getDialect(),
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
     * Removes all the brand masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BrandMaster brandMaster : findAll()) {
            remove(brandMaster);
        }
    }

    /**
     * Returns the number of brand masters.
     *
     * @return the number of brand masters
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

                Query q = session.createQuery(_SQL_COUNT_BRANDMASTER);

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
     * Initializes the brand master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.BrandMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BrandMaster>> listenersList = new ArrayList<ModelListener<BrandMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BrandMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BrandMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
