package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMedicaidUraProjException;
import com.stpl.app.model.MedicaidUraProj;
import com.stpl.app.model.impl.MedicaidUraProjImpl;
import com.stpl.app.model.impl.MedicaidUraProjModelImpl;
import com.stpl.app.service.persistence.MedicaidUraProjPersistence;

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
 * The persistence implementation for the medicaid ura proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraProjPersistence
 * @see MedicaidUraProjUtil
 * @generated
 */
public class MedicaidUraProjPersistenceImpl extends BasePersistenceImpl<MedicaidUraProj>
    implements MedicaidUraProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MedicaidUraProjUtil} to access the medicaid ura proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MedicaidUraProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED,
            MedicaidUraProjImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED,
            MedicaidUraProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MEDICAIDURAPROJ = "SELECT medicaidUraProj FROM MedicaidUraProj medicaidUraProj";
    private static final String _SQL_COUNT_MEDICAIDURAPROJ = "SELECT COUNT(medicaidUraProj) FROM MedicaidUraProj medicaidUraProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidUraProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidUraProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MedicaidUraProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustment", "periodSid", "priceType", "projectionPrice",
                "notes", "naProjDetailsSid"
            });
    private static MedicaidUraProj _nullMedicaidUraProj = new MedicaidUraProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MedicaidUraProj> toCacheModel() {
                return _nullMedicaidUraProjCacheModel;
            }
        };

    private static CacheModel<MedicaidUraProj> _nullMedicaidUraProjCacheModel = new CacheModel<MedicaidUraProj>() {
            @Override
            public MedicaidUraProj toEntityModel() {
                return _nullMedicaidUraProj;
            }
        };

    public MedicaidUraProjPersistenceImpl() {
        setModelClass(MedicaidUraProj.class);
    }

    /**
     * Caches the medicaid ura proj in the entity cache if it is enabled.
     *
     * @param medicaidUraProj the medicaid ura proj
     */
    @Override
    public void cacheResult(MedicaidUraProj medicaidUraProj) {
        EntityCacheUtil.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey(),
            medicaidUraProj);

        medicaidUraProj.resetOriginalValues();
    }

    /**
     * Caches the medicaid ura projs in the entity cache if it is enabled.
     *
     * @param medicaidUraProjs the medicaid ura projs
     */
    @Override
    public void cacheResult(List<MedicaidUraProj> medicaidUraProjs) {
        for (MedicaidUraProj medicaidUraProj : medicaidUraProjs) {
            if (EntityCacheUtil.getResult(
                        MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidUraProjImpl.class,
                        medicaidUraProj.getPrimaryKey()) == null) {
                cacheResult(medicaidUraProj);
            } else {
                medicaidUraProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all medicaid ura projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MedicaidUraProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MedicaidUraProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the medicaid ura proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MedicaidUraProj medicaidUraProj) {
        EntityCacheUtil.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MedicaidUraProj> medicaidUraProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MedicaidUraProj medicaidUraProj : medicaidUraProjs) {
            EntityCacheUtil.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new medicaid ura proj with the primary key. Does not add the medicaid ura proj to the database.
     *
     * @param medicaidUraProjPK the primary key for the new medicaid ura proj
     * @return the new medicaid ura proj
     */
    @Override
    public MedicaidUraProj create(MedicaidUraProjPK medicaidUraProjPK) {
        MedicaidUraProj medicaidUraProj = new MedicaidUraProjImpl();

        medicaidUraProj.setNew(true);
        medicaidUraProj.setPrimaryKey(medicaidUraProjPK);

        return medicaidUraProj;
    }

    /**
     * Removes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param medicaidUraProjPK the primary key of the medicaid ura proj
     * @return the medicaid ura proj that was removed
     * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj remove(MedicaidUraProjPK medicaidUraProjPK)
        throws NoSuchMedicaidUraProjException, SystemException {
        return remove((Serializable) medicaidUraProjPK);
    }

    /**
     * Removes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the medicaid ura proj
     * @return the medicaid ura proj that was removed
     * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj remove(Serializable primaryKey)
        throws NoSuchMedicaidUraProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MedicaidUraProj medicaidUraProj = (MedicaidUraProj) session.get(MedicaidUraProjImpl.class,
                    primaryKey);

            if (medicaidUraProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMedicaidUraProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(medicaidUraProj);
        } catch (NoSuchMedicaidUraProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MedicaidUraProj removeImpl(MedicaidUraProj medicaidUraProj)
        throws SystemException {
        medicaidUraProj = toUnwrappedModel(medicaidUraProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(medicaidUraProj)) {
                medicaidUraProj = (MedicaidUraProj) session.get(MedicaidUraProjImpl.class,
                        medicaidUraProj.getPrimaryKeyObj());
            }

            if (medicaidUraProj != null) {
                session.delete(medicaidUraProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (medicaidUraProj != null) {
            clearCache(medicaidUraProj);
        }

        return medicaidUraProj;
    }

    @Override
    public MedicaidUraProj updateImpl(
        com.stpl.app.model.MedicaidUraProj medicaidUraProj)
        throws SystemException {
        medicaidUraProj = toUnwrappedModel(medicaidUraProj);

        boolean isNew = medicaidUraProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (medicaidUraProj.isNew()) {
                session.save(medicaidUraProj);

                medicaidUraProj.setNew(false);
            } else {
                session.merge(medicaidUraProj);
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

        EntityCacheUtil.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey(),
            medicaidUraProj);

        return medicaidUraProj;
    }

    protected MedicaidUraProj toUnwrappedModel(MedicaidUraProj medicaidUraProj) {
        if (medicaidUraProj instanceof MedicaidUraProjImpl) {
            return medicaidUraProj;
        }

        MedicaidUraProjImpl medicaidUraProjImpl = new MedicaidUraProjImpl();

        medicaidUraProjImpl.setNew(medicaidUraProj.isNew());
        medicaidUraProjImpl.setPrimaryKey(medicaidUraProj.getPrimaryKey());

        medicaidUraProjImpl.setAdjustment(medicaidUraProj.getAdjustment());
        medicaidUraProjImpl.setPeriodSid(medicaidUraProj.getPeriodSid());
        medicaidUraProjImpl.setPriceType(medicaidUraProj.getPriceType());
        medicaidUraProjImpl.setProjectionPrice(medicaidUraProj.getProjectionPrice());
        medicaidUraProjImpl.setNotes(medicaidUraProj.getNotes());
        medicaidUraProjImpl.setNaProjDetailsSid(medicaidUraProj.getNaProjDetailsSid());

        return medicaidUraProjImpl;
    }

    /**
     * Returns the medicaid ura proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid ura proj
     * @return the medicaid ura proj
     * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMedicaidUraProjException, SystemException {
        MedicaidUraProj medicaidUraProj = fetchByPrimaryKey(primaryKey);

        if (medicaidUraProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMedicaidUraProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return medicaidUraProj;
    }

    /**
     * Returns the medicaid ura proj with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidUraProjException} if it could not be found.
     *
     * @param medicaidUraProjPK the primary key of the medicaid ura proj
     * @return the medicaid ura proj
     * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj findByPrimaryKey(MedicaidUraProjPK medicaidUraProjPK)
        throws NoSuchMedicaidUraProjException, SystemException {
        return findByPrimaryKey((Serializable) medicaidUraProjPK);
    }

    /**
     * Returns the medicaid ura proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid ura proj
     * @return the medicaid ura proj, or <code>null</code> if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MedicaidUraProj medicaidUraProj = (MedicaidUraProj) EntityCacheUtil.getResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidUraProjImpl.class, primaryKey);

        if (medicaidUraProj == _nullMedicaidUraProj) {
            return null;
        }

        if (medicaidUraProj == null) {
            Session session = null;

            try {
                session = openSession();

                medicaidUraProj = (MedicaidUraProj) session.get(MedicaidUraProjImpl.class,
                        primaryKey);

                if (medicaidUraProj != null) {
                    cacheResult(medicaidUraProj);
                } else {
                    EntityCacheUtil.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidUraProjImpl.class, primaryKey,
                        _nullMedicaidUraProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
                    MedicaidUraProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return medicaidUraProj;
    }

    /**
     * Returns the medicaid ura proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param medicaidUraProjPK the primary key of the medicaid ura proj
     * @return the medicaid ura proj, or <code>null</code> if a medicaid ura proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraProj fetchByPrimaryKey(
        MedicaidUraProjPK medicaidUraProjPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) medicaidUraProjPK);
    }

    /**
     * Returns all the medicaid ura projs.
     *
     * @return the medicaid ura projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the medicaid ura projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid ura projs
     * @param end the upper bound of the range of medicaid ura projs (not inclusive)
     * @return the range of medicaid ura projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraProj> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the medicaid ura projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid ura projs
     * @param end the upper bound of the range of medicaid ura projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of medicaid ura projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraProj> findAll(int start, int end,
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

        List<MedicaidUraProj> list = (List<MedicaidUraProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MEDICAIDURAPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MEDICAIDURAPROJ;

                if (pagination) {
                    sql = sql.concat(MedicaidUraProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MedicaidUraProj>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MedicaidUraProj>(list);
                } else {
                    list = (List<MedicaidUraProj>) QueryUtil.list(q,
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
     * Removes all the medicaid ura projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MedicaidUraProj medicaidUraProj : findAll()) {
            remove(medicaidUraProj);
        }
    }

    /**
     * Returns the number of medicaid ura projs.
     *
     * @return the number of medicaid ura projs
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

                Query q = session.createQuery(_SQL_COUNT_MEDICAIDURAPROJ);

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
     * Initializes the medicaid ura proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MedicaidUraProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MedicaidUraProj>> listenersList = new ArrayList<ModelListener<MedicaidUraProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MedicaidUraProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MedicaidUraProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
