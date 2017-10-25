package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmProjectionSelectionException;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.model.impl.NmProjectionSelectionImpl;
import com.stpl.app.model.impl.NmProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.NmProjectionSelectionPersistence;

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
 * The persistence implementation for the nm projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmProjectionSelectionPersistence
 * @see NmProjectionSelectionUtil
 * @generated
 */
public class NmProjectionSelectionPersistenceImpl extends BasePersistenceImpl<NmProjectionSelection>
    implements NmProjectionSelectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmProjectionSelectionUtil} to access the nm projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmProjectionSelectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            NmProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            NmProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMPROJECTIONSELECTION = "SELECT nmProjectionSelection FROM NmProjectionSelection nmProjectionSelection";
    private static final String _SQL_COUNT_NMPROJECTIONSELECTION = "SELECT COUNT(nmProjectionSelection) FROM NmProjectionSelection nmProjectionSelection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmProjectionSelection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmProjectionSelection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmProjectionSelectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "screenName", "nmProjectionSelectionSid", "fieldName",
                "fieldValues", "projectionMasterSid"
            });
    private static NmProjectionSelection _nullNmProjectionSelection = new NmProjectionSelectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmProjectionSelection> toCacheModel() {
                return _nullNmProjectionSelectionCacheModel;
            }
        };

    private static CacheModel<NmProjectionSelection> _nullNmProjectionSelectionCacheModel =
        new CacheModel<NmProjectionSelection>() {
            @Override
            public NmProjectionSelection toEntityModel() {
                return _nullNmProjectionSelection;
            }
        };

    public NmProjectionSelectionPersistenceImpl() {
        setModelClass(NmProjectionSelection.class);
    }

    /**
     * Caches the nm projection selection in the entity cache if it is enabled.
     *
     * @param nmProjectionSelection the nm projection selection
     */
    @Override
    public void cacheResult(NmProjectionSelection nmProjectionSelection) {
        EntityCacheUtil.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionImpl.class,
            nmProjectionSelection.getPrimaryKey(), nmProjectionSelection);

        nmProjectionSelection.resetOriginalValues();
    }

    /**
     * Caches the nm projection selections in the entity cache if it is enabled.
     *
     * @param nmProjectionSelections the nm projection selections
     */
    @Override
    public void cacheResult(List<NmProjectionSelection> nmProjectionSelections) {
        for (NmProjectionSelection nmProjectionSelection : nmProjectionSelections) {
            if (EntityCacheUtil.getResult(
                        NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmProjectionSelectionImpl.class,
                        nmProjectionSelection.getPrimaryKey()) == null) {
                cacheResult(nmProjectionSelection);
            } else {
                nmProjectionSelection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm projection selections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmProjectionSelectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmProjectionSelectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm projection selection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmProjectionSelection nmProjectionSelection) {
        EntityCacheUtil.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionImpl.class,
            nmProjectionSelection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmProjectionSelection> nmProjectionSelections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmProjectionSelection nmProjectionSelection : nmProjectionSelections) {
            EntityCacheUtil.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                NmProjectionSelectionImpl.class,
                nmProjectionSelection.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
     *
     * @param nmProjectionSelectionSid the primary key for the new nm projection selection
     * @return the new nm projection selection
     */
    @Override
    public NmProjectionSelection create(int nmProjectionSelectionSid) {
        NmProjectionSelection nmProjectionSelection = new NmProjectionSelectionImpl();

        nmProjectionSelection.setNew(true);
        nmProjectionSelection.setPrimaryKey(nmProjectionSelectionSid);

        return nmProjectionSelection;
    }

    /**
     * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmProjectionSelectionSid the primary key of the nm projection selection
     * @return the nm projection selection that was removed
     * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection remove(int nmProjectionSelectionSid)
        throws NoSuchNmProjectionSelectionException, SystemException {
        return remove((Serializable) nmProjectionSelectionSid);
    }

    /**
     * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm projection selection
     * @return the nm projection selection that was removed
     * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection remove(Serializable primaryKey)
        throws NoSuchNmProjectionSelectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmProjectionSelection nmProjectionSelection = (NmProjectionSelection) session.get(NmProjectionSelectionImpl.class,
                    primaryKey);

            if (nmProjectionSelection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmProjectionSelection);
        } catch (NoSuchNmProjectionSelectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmProjectionSelection removeImpl(
        NmProjectionSelection nmProjectionSelection) throws SystemException {
        nmProjectionSelection = toUnwrappedModel(nmProjectionSelection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmProjectionSelection)) {
                nmProjectionSelection = (NmProjectionSelection) session.get(NmProjectionSelectionImpl.class,
                        nmProjectionSelection.getPrimaryKeyObj());
            }

            if (nmProjectionSelection != null) {
                session.delete(nmProjectionSelection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmProjectionSelection != null) {
            clearCache(nmProjectionSelection);
        }

        return nmProjectionSelection;
    }

    @Override
    public NmProjectionSelection updateImpl(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws SystemException {
        nmProjectionSelection = toUnwrappedModel(nmProjectionSelection);

        boolean isNew = nmProjectionSelection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmProjectionSelection.isNew()) {
                session.save(nmProjectionSelection);

                nmProjectionSelection.setNew(false);
            } else {
                session.merge(nmProjectionSelection);
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

        EntityCacheUtil.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NmProjectionSelectionImpl.class,
            nmProjectionSelection.getPrimaryKey(), nmProjectionSelection);

        return nmProjectionSelection;
    }

    protected NmProjectionSelection toUnwrappedModel(
        NmProjectionSelection nmProjectionSelection) {
        if (nmProjectionSelection instanceof NmProjectionSelectionImpl) {
            return nmProjectionSelection;
        }

        NmProjectionSelectionImpl nmProjectionSelectionImpl = new NmProjectionSelectionImpl();

        nmProjectionSelectionImpl.setNew(nmProjectionSelection.isNew());
        nmProjectionSelectionImpl.setPrimaryKey(nmProjectionSelection.getPrimaryKey());

        nmProjectionSelectionImpl.setScreenName(nmProjectionSelection.getScreenName());
        nmProjectionSelectionImpl.setNmProjectionSelectionSid(nmProjectionSelection.getNmProjectionSelectionSid());
        nmProjectionSelectionImpl.setFieldName(nmProjectionSelection.getFieldName());
        nmProjectionSelectionImpl.setFieldValues(nmProjectionSelection.getFieldValues());
        nmProjectionSelectionImpl.setProjectionMasterSid(nmProjectionSelection.getProjectionMasterSid());

        return nmProjectionSelectionImpl;
    }

    /**
     * Returns the nm projection selection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm projection selection
     * @return the nm projection selection
     * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmProjectionSelectionException, SystemException {
        NmProjectionSelection nmProjectionSelection = fetchByPrimaryKey(primaryKey);

        if (nmProjectionSelection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmProjectionSelection;
    }

    /**
     * Returns the nm projection selection with the primary key or throws a {@link com.stpl.app.NoSuchNmProjectionSelectionException} if it could not be found.
     *
     * @param nmProjectionSelectionSid the primary key of the nm projection selection
     * @return the nm projection selection
     * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection findByPrimaryKey(int nmProjectionSelectionSid)
        throws NoSuchNmProjectionSelectionException, SystemException {
        return findByPrimaryKey((Serializable) nmProjectionSelectionSid);
    }

    /**
     * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm projection selection
     * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmProjectionSelection nmProjectionSelection = (NmProjectionSelection) EntityCacheUtil.getResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                NmProjectionSelectionImpl.class, primaryKey);

        if (nmProjectionSelection == _nullNmProjectionSelection) {
            return null;
        }

        if (nmProjectionSelection == null) {
            Session session = null;

            try {
                session = openSession();

                nmProjectionSelection = (NmProjectionSelection) session.get(NmProjectionSelectionImpl.class,
                        primaryKey);

                if (nmProjectionSelection != null) {
                    cacheResult(nmProjectionSelection);
                } else {
                    EntityCacheUtil.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmProjectionSelectionImpl.class, primaryKey,
                        _nullNmProjectionSelection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                    NmProjectionSelectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmProjectionSelection;
    }

    /**
     * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmProjectionSelectionSid the primary key of the nm projection selection
     * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmProjectionSelection fetchByPrimaryKey(int nmProjectionSelectionSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) nmProjectionSelectionSid);
    }

    /**
     * Returns all the nm projection selections.
     *
     * @return the nm projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmProjectionSelection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm projection selections
     * @param end the upper bound of the range of nm projection selections (not inclusive)
     * @return the range of nm projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmProjectionSelection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm projection selections
     * @param end the upper bound of the range of nm projection selections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmProjectionSelection> findAll(int start, int end,
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

        List<NmProjectionSelection> list = (List<NmProjectionSelection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMPROJECTIONSELECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMPROJECTIONSELECTION;

                if (pagination) {
                    sql = sql.concat(NmProjectionSelectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmProjectionSelection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmProjectionSelection>(list);
                } else {
                    list = (List<NmProjectionSelection>) QueryUtil.list(q,
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
     * Removes all the nm projection selections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmProjectionSelection nmProjectionSelection : findAll()) {
            remove(nmProjectionSelection);
        }
    }

    /**
     * Returns the number of nm projection selections.
     *
     * @return the number of nm projection selections
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

                Query q = session.createQuery(_SQL_COUNT_NMPROJECTIONSELECTION);

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
     * Initializes the nm projection selection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmProjectionSelection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmProjectionSelection>> listenersList = new ArrayList<ModelListener<NmProjectionSelection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmProjectionSelection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmProjectionSelectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
