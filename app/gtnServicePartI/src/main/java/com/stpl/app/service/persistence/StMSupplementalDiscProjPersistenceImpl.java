package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStMSupplementalDiscProjException;
import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.model.impl.StMSupplementalDiscProjImpl;
import com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPersistence;

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
 * The persistence implementation for the st m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProjPersistence
 * @see StMSupplementalDiscProjUtil
 * @generated
 */
public class StMSupplementalDiscProjPersistenceImpl extends BasePersistenceImpl<StMSupplementalDiscProj>
    implements StMSupplementalDiscProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StMSupplementalDiscProjUtil} to access the st m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StMSupplementalDiscProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
            StMSupplementalDiscProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
            StMSupplementalDiscProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STMSUPPLEMENTALDISCPROJ = "SELECT stMSupplementalDiscProj FROM StMSupplementalDiscProj stMSupplementalDiscProj";
    private static final String _SQL_COUNT_STMSUPPLEMENTALDISCPROJ = "SELECT COUNT(stMSupplementalDiscProj) FROM StMSupplementalDiscProj stMSupplementalDiscProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stMSupplementalDiscProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMSupplementalDiscProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StMSupplementalDiscProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "projectionRate", "userId", "lastModifiedDate",
                "parityReference", "projectionSales", "contractPrice",
                "methodology", "parity", "periodSid", "discountRate1",
                "projectionDetailsSid", "discountRate2", "parityDiscount",
                "sessionId", "access"
            });
    private static StMSupplementalDiscProj _nullStMSupplementalDiscProj = new StMSupplementalDiscProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StMSupplementalDiscProj> toCacheModel() {
                return _nullStMSupplementalDiscProjCacheModel;
            }
        };

    private static CacheModel<StMSupplementalDiscProj> _nullStMSupplementalDiscProjCacheModel =
        new CacheModel<StMSupplementalDiscProj>() {
            @Override
            public StMSupplementalDiscProj toEntityModel() {
                return _nullStMSupplementalDiscProj;
            }
        };

    public StMSupplementalDiscProjPersistenceImpl() {
        setModelClass(StMSupplementalDiscProj.class);
    }

    /**
     * Caches the st m supplemental disc proj in the entity cache if it is enabled.
     *
     * @param stMSupplementalDiscProj the st m supplemental disc proj
     */
    @Override
    public void cacheResult(StMSupplementalDiscProj stMSupplementalDiscProj) {
        EntityCacheUtil.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjImpl.class,
            stMSupplementalDiscProj.getPrimaryKey(), stMSupplementalDiscProj);

        stMSupplementalDiscProj.resetOriginalValues();
    }

    /**
     * Caches the st m supplemental disc projs in the entity cache if it is enabled.
     *
     * @param stMSupplementalDiscProjs the st m supplemental disc projs
     */
    @Override
    public void cacheResult(
        List<StMSupplementalDiscProj> stMSupplementalDiscProjs) {
        for (StMSupplementalDiscProj stMSupplementalDiscProj : stMSupplementalDiscProjs) {
            if (EntityCacheUtil.getResult(
                        StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                        StMSupplementalDiscProjImpl.class,
                        stMSupplementalDiscProj.getPrimaryKey()) == null) {
                cacheResult(stMSupplementalDiscProj);
            } else {
                stMSupplementalDiscProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st m supplemental disc projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StMSupplementalDiscProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StMSupplementalDiscProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st m supplemental disc proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StMSupplementalDiscProj stMSupplementalDiscProj) {
        EntityCacheUtil.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjImpl.class,
            stMSupplementalDiscProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StMSupplementalDiscProj> stMSupplementalDiscProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StMSupplementalDiscProj stMSupplementalDiscProj : stMSupplementalDiscProjs) {
            EntityCacheUtil.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                StMSupplementalDiscProjImpl.class,
                stMSupplementalDiscProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new st m supplemental disc proj with the primary key. Does not add the st m supplemental disc proj to the database.
     *
     * @param stMSupplementalDiscProjPK the primary key for the new st m supplemental disc proj
     * @return the new st m supplemental disc proj
     */
    @Override
    public StMSupplementalDiscProj create(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK) {
        StMSupplementalDiscProj stMSupplementalDiscProj = new StMSupplementalDiscProjImpl();

        stMSupplementalDiscProj.setNew(true);
        stMSupplementalDiscProj.setPrimaryKey(stMSupplementalDiscProjPK);

        return stMSupplementalDiscProj;
    }

    /**
     * Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj that was removed
     * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj remove(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws NoSuchStMSupplementalDiscProjException, SystemException {
        return remove((Serializable) stMSupplementalDiscProjPK);
    }

    /**
     * Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj that was removed
     * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj remove(Serializable primaryKey)
        throws NoSuchStMSupplementalDiscProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StMSupplementalDiscProj stMSupplementalDiscProj = (StMSupplementalDiscProj) session.get(StMSupplementalDiscProjImpl.class,
                    primaryKey);

            if (stMSupplementalDiscProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stMSupplementalDiscProj);
        } catch (NoSuchStMSupplementalDiscProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StMSupplementalDiscProj removeImpl(
        StMSupplementalDiscProj stMSupplementalDiscProj)
        throws SystemException {
        stMSupplementalDiscProj = toUnwrappedModel(stMSupplementalDiscProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stMSupplementalDiscProj)) {
                stMSupplementalDiscProj = (StMSupplementalDiscProj) session.get(StMSupplementalDiscProjImpl.class,
                        stMSupplementalDiscProj.getPrimaryKeyObj());
            }

            if (stMSupplementalDiscProj != null) {
                session.delete(stMSupplementalDiscProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stMSupplementalDiscProj != null) {
            clearCache(stMSupplementalDiscProj);
        }

        return stMSupplementalDiscProj;
    }

    @Override
    public StMSupplementalDiscProj updateImpl(
        com.stpl.app.model.StMSupplementalDiscProj stMSupplementalDiscProj)
        throws SystemException {
        stMSupplementalDiscProj = toUnwrappedModel(stMSupplementalDiscProj);

        boolean isNew = stMSupplementalDiscProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stMSupplementalDiscProj.isNew()) {
                session.save(stMSupplementalDiscProj);

                stMSupplementalDiscProj.setNew(false);
            } else {
                session.merge(stMSupplementalDiscProj);
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

        EntityCacheUtil.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscProjImpl.class,
            stMSupplementalDiscProj.getPrimaryKey(), stMSupplementalDiscProj);

        return stMSupplementalDiscProj;
    }

    protected StMSupplementalDiscProj toUnwrappedModel(
        StMSupplementalDiscProj stMSupplementalDiscProj) {
        if (stMSupplementalDiscProj instanceof StMSupplementalDiscProjImpl) {
            return stMSupplementalDiscProj;
        }

        StMSupplementalDiscProjImpl stMSupplementalDiscProjImpl = new StMSupplementalDiscProjImpl();

        stMSupplementalDiscProjImpl.setNew(stMSupplementalDiscProj.isNew());
        stMSupplementalDiscProjImpl.setPrimaryKey(stMSupplementalDiscProj.getPrimaryKey());

        stMSupplementalDiscProjImpl.setProjectionRate(stMSupplementalDiscProj.getProjectionRate());
        stMSupplementalDiscProjImpl.setUserId(stMSupplementalDiscProj.getUserId());
        stMSupplementalDiscProjImpl.setLastModifiedDate(stMSupplementalDiscProj.getLastModifiedDate());
        stMSupplementalDiscProjImpl.setParityReference(stMSupplementalDiscProj.getParityReference());
        stMSupplementalDiscProjImpl.setProjectionSales(stMSupplementalDiscProj.getProjectionSales());
        stMSupplementalDiscProjImpl.setContractPrice(stMSupplementalDiscProj.getContractPrice());
        stMSupplementalDiscProjImpl.setMethodology(stMSupplementalDiscProj.getMethodology());
        stMSupplementalDiscProjImpl.setParity(stMSupplementalDiscProj.isParity());
        stMSupplementalDiscProjImpl.setPeriodSid(stMSupplementalDiscProj.getPeriodSid());
        stMSupplementalDiscProjImpl.setDiscountRate1(stMSupplementalDiscProj.getDiscountRate1());
        stMSupplementalDiscProjImpl.setProjectionDetailsSid(stMSupplementalDiscProj.getProjectionDetailsSid());
        stMSupplementalDiscProjImpl.setDiscountRate2(stMSupplementalDiscProj.getDiscountRate2());
        stMSupplementalDiscProjImpl.setParityDiscount(stMSupplementalDiscProj.getParityDiscount());
        stMSupplementalDiscProjImpl.setSessionId(stMSupplementalDiscProj.getSessionId());
        stMSupplementalDiscProjImpl.setAccess(stMSupplementalDiscProj.getAccess());

        return stMSupplementalDiscProjImpl;
    }

    /**
     * Returns the st m supplemental disc proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj
     * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStMSupplementalDiscProjException, SystemException {
        StMSupplementalDiscProj stMSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

        if (stMSupplementalDiscProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stMSupplementalDiscProj;
    }

    /**
     * Returns the st m supplemental disc proj with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscProjException} if it could not be found.
     *
     * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj
     * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj findByPrimaryKey(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws NoSuchStMSupplementalDiscProjException, SystemException {
        return findByPrimaryKey((Serializable) stMSupplementalDiscProjPK);
    }

    /**
     * Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StMSupplementalDiscProj stMSupplementalDiscProj = (StMSupplementalDiscProj) EntityCacheUtil.getResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                StMSupplementalDiscProjImpl.class, primaryKey);

        if (stMSupplementalDiscProj == _nullStMSupplementalDiscProj) {
            return null;
        }

        if (stMSupplementalDiscProj == null) {
            Session session = null;

            try {
                session = openSession();

                stMSupplementalDiscProj = (StMSupplementalDiscProj) session.get(StMSupplementalDiscProjImpl.class,
                        primaryKey);

                if (stMSupplementalDiscProj != null) {
                    cacheResult(stMSupplementalDiscProj);
                } else {
                    EntityCacheUtil.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                        StMSupplementalDiscProjImpl.class, primaryKey,
                        _nullStMSupplementalDiscProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                    StMSupplementalDiscProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stMSupplementalDiscProj;
    }

    /**
     * Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
     * @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscProj fetchByPrimaryKey(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stMSupplementalDiscProjPK);
    }

    /**
     * Returns all the st m supplemental disc projs.
     *
     * @return the st m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st m supplemental disc projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st m supplemental disc projs
     * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
     * @return the range of st m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscProj> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st m supplemental disc projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st m supplemental disc projs
     * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscProj> findAll(int start, int end,
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

        List<StMSupplementalDiscProj> list = (List<StMSupplementalDiscProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STMSUPPLEMENTALDISCPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STMSUPPLEMENTALDISCPROJ;

                if (pagination) {
                    sql = sql.concat(StMSupplementalDiscProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StMSupplementalDiscProj>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StMSupplementalDiscProj>(list);
                } else {
                    list = (List<StMSupplementalDiscProj>) QueryUtil.list(q,
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
     * Removes all the st m supplemental disc projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StMSupplementalDiscProj stMSupplementalDiscProj : findAll()) {
            remove(stMSupplementalDiscProj);
        }
    }

    /**
     * Returns the number of st m supplemental disc projs.
     *
     * @return the number of st m supplemental disc projs
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

                Query q = session.createQuery(_SQL_COUNT_STMSUPPLEMENTALDISCPROJ);

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
     * Initializes the st m supplemental disc proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StMSupplementalDiscProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StMSupplementalDiscProj>> listenersList = new ArrayList<ModelListener<StMSupplementalDiscProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StMSupplementalDiscProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StMSupplementalDiscProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
