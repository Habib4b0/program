package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchFederalNewNdcException;
import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.model.impl.FederalNewNdcImpl;
import com.stpl.app.model.impl.FederalNewNdcModelImpl;
import com.stpl.app.service.persistence.FederalNewNdcPersistence;

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
 * The persistence implementation for the federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FederalNewNdcPersistence
 * @see FederalNewNdcUtil
 * @generated
 */
public class FederalNewNdcPersistenceImpl extends BasePersistenceImpl<FederalNewNdc>
    implements FederalNewNdcPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FederalNewNdcUtil} to access the federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FederalNewNdcImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
            FederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
            FederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FEDERALNEWNDC = "SELECT federalNewNdc FROM FederalNewNdc federalNewNdc";
    private static final String _SQL_COUNT_FEDERALNEWNDC = "SELECT COUNT(federalNewNdc) FROM FederalNewNdc federalNewNdc";
    private static final String _ORDER_BY_ENTITY_ALIAS = "federalNewNdc.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FederalNewNdc exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FederalNewNdcPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "fss", "itemMasterSid", "wacPrice", "nonFamp"
            });
    private static FederalNewNdc _nullFederalNewNdc = new FederalNewNdcImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FederalNewNdc> toCacheModel() {
                return _nullFederalNewNdcCacheModel;
            }
        };

    private static CacheModel<FederalNewNdc> _nullFederalNewNdcCacheModel = new CacheModel<FederalNewNdc>() {
            @Override
            public FederalNewNdc toEntityModel() {
                return _nullFederalNewNdc;
            }
        };

    public FederalNewNdcPersistenceImpl() {
        setModelClass(FederalNewNdc.class);
    }

    /**
     * Caches the federal new ndc in the entity cache if it is enabled.
     *
     * @param federalNewNdc the federal new ndc
     */
    @Override
    public void cacheResult(FederalNewNdc federalNewNdc) {
        EntityCacheUtil.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey(),
            federalNewNdc);

        federalNewNdc.resetOriginalValues();
    }

    /**
     * Caches the federal new ndcs in the entity cache if it is enabled.
     *
     * @param federalNewNdcs the federal new ndcs
     */
    @Override
    public void cacheResult(List<FederalNewNdc> federalNewNdcs) {
        for (FederalNewNdc federalNewNdc : federalNewNdcs) {
            if (EntityCacheUtil.getResult(
                        FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey()) == null) {
                cacheResult(federalNewNdc);
            } else {
                federalNewNdc.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all federal new ndcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FederalNewNdcImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FederalNewNdcImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the federal new ndc.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FederalNewNdc federalNewNdc) {
        EntityCacheUtil.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FederalNewNdc> federalNewNdcs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FederalNewNdc federalNewNdc : federalNewNdcs) {
            EntityCacheUtil.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey());
        }
    }

    /**
     * Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
     *
     * @param itemMasterSid the primary key for the new federal new ndc
     * @return the new federal new ndc
     */
    @Override
    public FederalNewNdc create(int itemMasterSid) {
        FederalNewNdc federalNewNdc = new FederalNewNdcImpl();

        federalNewNdc.setNew(true);
        federalNewNdc.setPrimaryKey(itemMasterSid);

        return federalNewNdc;
    }

    /**
     * Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemMasterSid the primary key of the federal new ndc
     * @return the federal new ndc that was removed
     * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc remove(int itemMasterSid)
        throws NoSuchFederalNewNdcException, SystemException {
        return remove((Serializable) itemMasterSid);
    }

    /**
     * Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the federal new ndc
     * @return the federal new ndc that was removed
     * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc remove(Serializable primaryKey)
        throws NoSuchFederalNewNdcException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FederalNewNdc federalNewNdc = (FederalNewNdc) session.get(FederalNewNdcImpl.class,
                    primaryKey);

            if (federalNewNdc == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(federalNewNdc);
        } catch (NoSuchFederalNewNdcException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FederalNewNdc removeImpl(FederalNewNdc federalNewNdc)
        throws SystemException {
        federalNewNdc = toUnwrappedModel(federalNewNdc);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(federalNewNdc)) {
                federalNewNdc = (FederalNewNdc) session.get(FederalNewNdcImpl.class,
                        federalNewNdc.getPrimaryKeyObj());
            }

            if (federalNewNdc != null) {
                session.delete(federalNewNdc);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (federalNewNdc != null) {
            clearCache(federalNewNdc);
        }

        return federalNewNdc;
    }

    @Override
    public FederalNewNdc updateImpl(
        com.stpl.app.model.FederalNewNdc federalNewNdc)
        throws SystemException {
        federalNewNdc = toUnwrappedModel(federalNewNdc);

        boolean isNew = federalNewNdc.isNew();

        Session session = null;

        try {
            session = openSession();

            if (federalNewNdc.isNew()) {
                session.save(federalNewNdc);

                federalNewNdc.setNew(false);
            } else {
                session.merge(federalNewNdc);
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

        EntityCacheUtil.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey(),
            federalNewNdc);

        return federalNewNdc;
    }

    protected FederalNewNdc toUnwrappedModel(FederalNewNdc federalNewNdc) {
        if (federalNewNdc instanceof FederalNewNdcImpl) {
            return federalNewNdc;
        }

        FederalNewNdcImpl federalNewNdcImpl = new FederalNewNdcImpl();

        federalNewNdcImpl.setNew(federalNewNdc.isNew());
        federalNewNdcImpl.setPrimaryKey(federalNewNdc.getPrimaryKey());

        federalNewNdcImpl.setFss(federalNewNdc.getFss());
        federalNewNdcImpl.setItemMasterSid(federalNewNdc.getItemMasterSid());
        federalNewNdcImpl.setWacPrice(federalNewNdc.getWacPrice());
        federalNewNdcImpl.setNonFamp(federalNewNdc.getNonFamp());

        return federalNewNdcImpl;
    }

    /**
     * Returns the federal new ndc with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the federal new ndc
     * @return the federal new ndc
     * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc findByPrimaryKey(Serializable primaryKey)
        throws NoSuchFederalNewNdcException, SystemException {
        FederalNewNdc federalNewNdc = fetchByPrimaryKey(primaryKey);

        if (federalNewNdc == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return federalNewNdc;
    }

    /**
     * Returns the federal new ndc with the primary key or throws a {@link com.stpl.app.NoSuchFederalNewNdcException} if it could not be found.
     *
     * @param itemMasterSid the primary key of the federal new ndc
     * @return the federal new ndc
     * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc findByPrimaryKey(int itemMasterSid)
        throws NoSuchFederalNewNdcException, SystemException {
        return findByPrimaryKey((Serializable) itemMasterSid);
    }

    /**
     * Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the federal new ndc
     * @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        FederalNewNdc federalNewNdc = (FederalNewNdc) EntityCacheUtil.getResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                FederalNewNdcImpl.class, primaryKey);

        if (federalNewNdc == _nullFederalNewNdc) {
            return null;
        }

        if (federalNewNdc == null) {
            Session session = null;

            try {
                session = openSession();

                federalNewNdc = (FederalNewNdc) session.get(FederalNewNdcImpl.class,
                        primaryKey);

                if (federalNewNdc != null) {
                    cacheResult(federalNewNdc);
                } else {
                    EntityCacheUtil.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        FederalNewNdcImpl.class, primaryKey, _nullFederalNewNdc);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                    FederalNewNdcImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return federalNewNdc;
    }

    /**
     * Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemMasterSid the primary key of the federal new ndc
     * @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FederalNewNdc fetchByPrimaryKey(int itemMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemMasterSid);
    }

    /**
     * Returns all the federal new ndcs.
     *
     * @return the federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FederalNewNdc> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the federal new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of federal new ndcs
     * @param end the upper bound of the range of federal new ndcs (not inclusive)
     * @return the range of federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FederalNewNdc> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the federal new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of federal new ndcs
     * @param end the upper bound of the range of federal new ndcs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FederalNewNdc> findAll(int start, int end,
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

        List<FederalNewNdc> list = (List<FederalNewNdc>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FEDERALNEWNDC);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FEDERALNEWNDC;

                if (pagination) {
                    sql = sql.concat(FederalNewNdcModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<FederalNewNdc>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<FederalNewNdc>(list);
                } else {
                    list = (List<FederalNewNdc>) QueryUtil.list(q,
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
     * Removes all the federal new ndcs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (FederalNewNdc federalNewNdc : findAll()) {
            remove(federalNewNdc);
        }
    }

    /**
     * Returns the number of federal new ndcs.
     *
     * @return the number of federal new ndcs
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

                Query q = session.createQuery(_SQL_COUNT_FEDERALNEWNDC);

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
     * Initializes the federal new ndc persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.FederalNewNdc")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FederalNewNdc>> listenersList = new ArrayList<ModelListener<FederalNewNdc>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FederalNewNdc>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FederalNewNdcImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
