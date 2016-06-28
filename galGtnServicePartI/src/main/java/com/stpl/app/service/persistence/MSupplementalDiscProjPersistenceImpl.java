package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMSupplementalDiscProjException;
import com.stpl.app.model.MSupplementalDiscProj;
import com.stpl.app.model.impl.MSupplementalDiscProjImpl;
import com.stpl.app.model.impl.MSupplementalDiscProjModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscProjPersistence;

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
 * The persistence implementation for the m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscProjPersistence
 * @see MSupplementalDiscProjUtil
 * @generated
 */
public class MSupplementalDiscProjPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscProj>
    implements MSupplementalDiscProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MSupplementalDiscProjUtil} to access the m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MSUPPLEMENTALDISCPROJ = "SELECT mSupplementalDiscProj FROM MSupplementalDiscProj mSupplementalDiscProj";
    private static final String _SQL_COUNT_MSUPPLEMENTALDISCPROJ = "SELECT COUNT(mSupplementalDiscProj) FROM MSupplementalDiscProj mSupplementalDiscProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MSupplementalDiscProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "methodology", "projectionRate", "parity", "periodSid",
                "discountRate1", "parityReference", "projectionDetailsSid",
                "discountRate2", "parityDiscount", "projectionSales",
                "contractPrice", "access"
            });
    private static MSupplementalDiscProj _nullMSupplementalDiscProj = new MSupplementalDiscProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MSupplementalDiscProj> toCacheModel() {
                return _nullMSupplementalDiscProjCacheModel;
            }
        };

    private static CacheModel<MSupplementalDiscProj> _nullMSupplementalDiscProjCacheModel =
        new CacheModel<MSupplementalDiscProj>() {
            @Override
            public MSupplementalDiscProj toEntityModel() {
                return _nullMSupplementalDiscProj;
            }
        };

    public MSupplementalDiscProjPersistenceImpl() {
        setModelClass(MSupplementalDiscProj.class);
    }

    /**
     * Caches the m supplemental disc proj in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscProj the m supplemental disc proj
     */
    @Override
    public void cacheResult(MSupplementalDiscProj mSupplementalDiscProj) {
        EntityCacheUtil.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjImpl.class,
            mSupplementalDiscProj.getPrimaryKey(), mSupplementalDiscProj);

        mSupplementalDiscProj.resetOriginalValues();
    }

    /**
     * Caches the m supplemental disc projs in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscProjs the m supplemental disc projs
     */
    @Override
    public void cacheResult(List<MSupplementalDiscProj> mSupplementalDiscProjs) {
        for (MSupplementalDiscProj mSupplementalDiscProj : mSupplementalDiscProjs) {
            if (EntityCacheUtil.getResult(
                        MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscProjImpl.class,
                        mSupplementalDiscProj.getPrimaryKey()) == null) {
                cacheResult(mSupplementalDiscProj);
            } else {
                mSupplementalDiscProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m supplemental disc projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MSupplementalDiscProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MSupplementalDiscProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m supplemental disc proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MSupplementalDiscProj mSupplementalDiscProj) {
        EntityCacheUtil.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjImpl.class,
            mSupplementalDiscProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MSupplementalDiscProj> mSupplementalDiscProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MSupplementalDiscProj mSupplementalDiscProj : mSupplementalDiscProjs) {
            EntityCacheUtil.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscProjImpl.class,
                mSupplementalDiscProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
     *
     * @param projectionDetailsSid the primary key for the new m supplemental disc proj
     * @return the new m supplemental disc proj
     */
    @Override
    public MSupplementalDiscProj create(int projectionDetailsSid) {
        MSupplementalDiscProj mSupplementalDiscProj = new MSupplementalDiscProjImpl();

        mSupplementalDiscProj.setNew(true);
        mSupplementalDiscProj.setPrimaryKey(projectionDetailsSid);

        return mSupplementalDiscProj;
    }

    /**
     * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj remove(int projectionDetailsSid)
        throws NoSuchMSupplementalDiscProjException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj remove(Serializable primaryKey)
        throws NoSuchMSupplementalDiscProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MSupplementalDiscProj mSupplementalDiscProj = (MSupplementalDiscProj) session.get(MSupplementalDiscProjImpl.class,
                    primaryKey);

            if (mSupplementalDiscProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mSupplementalDiscProj);
        } catch (NoSuchMSupplementalDiscProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MSupplementalDiscProj removeImpl(
        MSupplementalDiscProj mSupplementalDiscProj) throws SystemException {
        mSupplementalDiscProj = toUnwrappedModel(mSupplementalDiscProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mSupplementalDiscProj)) {
                mSupplementalDiscProj = (MSupplementalDiscProj) session.get(MSupplementalDiscProjImpl.class,
                        mSupplementalDiscProj.getPrimaryKeyObj());
            }

            if (mSupplementalDiscProj != null) {
                session.delete(mSupplementalDiscProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mSupplementalDiscProj != null) {
            clearCache(mSupplementalDiscProj);
        }

        return mSupplementalDiscProj;
    }

    @Override
    public MSupplementalDiscProj updateImpl(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws SystemException {
        mSupplementalDiscProj = toUnwrappedModel(mSupplementalDiscProj);

        boolean isNew = mSupplementalDiscProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mSupplementalDiscProj.isNew()) {
                session.save(mSupplementalDiscProj);

                mSupplementalDiscProj.setNew(false);
            } else {
                session.merge(mSupplementalDiscProj);
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

        EntityCacheUtil.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscProjImpl.class,
            mSupplementalDiscProj.getPrimaryKey(), mSupplementalDiscProj);

        return mSupplementalDiscProj;
    }

    protected MSupplementalDiscProj toUnwrappedModel(
        MSupplementalDiscProj mSupplementalDiscProj) {
        if (mSupplementalDiscProj instanceof MSupplementalDiscProjImpl) {
            return mSupplementalDiscProj;
        }

        MSupplementalDiscProjImpl mSupplementalDiscProjImpl = new MSupplementalDiscProjImpl();

        mSupplementalDiscProjImpl.setNew(mSupplementalDiscProj.isNew());
        mSupplementalDiscProjImpl.setPrimaryKey(mSupplementalDiscProj.getPrimaryKey());

        mSupplementalDiscProjImpl.setMethodology(mSupplementalDiscProj.getMethodology());
        mSupplementalDiscProjImpl.setProjectionRate(mSupplementalDiscProj.getProjectionRate());
        mSupplementalDiscProjImpl.setParity(mSupplementalDiscProj.isParity());
        mSupplementalDiscProjImpl.setPeriodSid(mSupplementalDiscProj.getPeriodSid());
        mSupplementalDiscProjImpl.setDiscountRate1(mSupplementalDiscProj.getDiscountRate1());
        mSupplementalDiscProjImpl.setParityReference(mSupplementalDiscProj.getParityReference());
        mSupplementalDiscProjImpl.setProjectionDetailsSid(mSupplementalDiscProj.getProjectionDetailsSid());
        mSupplementalDiscProjImpl.setDiscountRate2(mSupplementalDiscProj.getDiscountRate2());
        mSupplementalDiscProjImpl.setParityDiscount(mSupplementalDiscProj.getParityDiscount());
        mSupplementalDiscProjImpl.setProjectionSales(mSupplementalDiscProj.getProjectionSales());
        mSupplementalDiscProjImpl.setContractPrice(mSupplementalDiscProj.getContractPrice());
        mSupplementalDiscProjImpl.setAccess(mSupplementalDiscProj.getAccess());

        return mSupplementalDiscProjImpl;
    }

    /**
     * Returns the m supplemental disc proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj
     * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMSupplementalDiscProjException, SystemException {
        MSupplementalDiscProj mSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

        if (mSupplementalDiscProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mSupplementalDiscProj;
    }

    /**
     * Returns the m supplemental disc proj with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscProjException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj
     * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchMSupplementalDiscProjException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MSupplementalDiscProj mSupplementalDiscProj = (MSupplementalDiscProj) EntityCacheUtil.getResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscProjImpl.class, primaryKey);

        if (mSupplementalDiscProj == _nullMSupplementalDiscProj) {
            return null;
        }

        if (mSupplementalDiscProj == null) {
            Session session = null;

            try {
                session = openSession();

                mSupplementalDiscProj = (MSupplementalDiscProj) session.get(MSupplementalDiscProjImpl.class,
                        primaryKey);

                if (mSupplementalDiscProj != null) {
                    cacheResult(mSupplementalDiscProj);
                } else {
                    EntityCacheUtil.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscProjImpl.class, primaryKey,
                        _nullMSupplementalDiscProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
                    MSupplementalDiscProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mSupplementalDiscProj;
    }

    /**
     * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc proj
     * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscProj fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the m supplemental disc projs.
     *
     * @return the m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m supplemental disc projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc projs
     * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
     * @return the range of m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscProj> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m supplemental disc projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc projs
     * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m supplemental disc projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscProj> findAll(int start, int end,
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

        List<MSupplementalDiscProj> list = (List<MSupplementalDiscProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MSUPPLEMENTALDISCPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MSUPPLEMENTALDISCPROJ;

                if (pagination) {
                    sql = sql.concat(MSupplementalDiscProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MSupplementalDiscProj>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MSupplementalDiscProj>(list);
                } else {
                    list = (List<MSupplementalDiscProj>) QueryUtil.list(q,
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
     * Removes all the m supplemental disc projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MSupplementalDiscProj mSupplementalDiscProj : findAll()) {
            remove(mSupplementalDiscProj);
        }
    }

    /**
     * Returns the number of m supplemental disc projs.
     *
     * @return the number of m supplemental disc projs
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

                Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCPROJ);

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
     * Initializes the m supplemental disc proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MSupplementalDiscProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MSupplementalDiscProj>> listenersList = new ArrayList<ModelListener<MSupplementalDiscProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MSupplementalDiscProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MSupplementalDiscProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
