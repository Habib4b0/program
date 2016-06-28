package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNaProjectionSelectionException;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.model.impl.NaProjectionSelectionImpl;
import com.stpl.app.model.impl.NaProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.NaProjectionSelectionPersistence;

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
 * The persistence implementation for the na projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjectionSelectionPersistence
 * @see NaProjectionSelectionUtil
 * @generated
 */
public class NaProjectionSelectionPersistenceImpl extends BasePersistenceImpl<NaProjectionSelection>
    implements NaProjectionSelectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NaProjectionSelectionUtil} to access the na projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NaProjectionSelectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            NaProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            NaProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NAPROJECTIONSELECTION = "SELECT naProjectionSelection FROM NaProjectionSelection naProjectionSelection";
    private static final String _SQL_COUNT_NAPROJECTIONSELECTION = "SELECT COUNT(naProjectionSelection) FROM NaProjectionSelection naProjectionSelection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "naProjectionSelection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjectionSelection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NaProjectionSelectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "screenName", "fieldName", "fieldValues",
                "naProjectionSelectionSid", "naProjMasterSid"
            });
    private static NaProjectionSelection _nullNaProjectionSelection = new NaProjectionSelectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NaProjectionSelection> toCacheModel() {
                return _nullNaProjectionSelectionCacheModel;
            }
        };

    private static CacheModel<NaProjectionSelection> _nullNaProjectionSelectionCacheModel =
        new CacheModel<NaProjectionSelection>() {
            @Override
            public NaProjectionSelection toEntityModel() {
                return _nullNaProjectionSelection;
            }
        };

    public NaProjectionSelectionPersistenceImpl() {
        setModelClass(NaProjectionSelection.class);
    }

    /**
     * Caches the na projection selection in the entity cache if it is enabled.
     *
     * @param naProjectionSelection the na projection selection
     */
    @Override
    public void cacheResult(NaProjectionSelection naProjectionSelection) {
        EntityCacheUtil.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionImpl.class,
            naProjectionSelection.getPrimaryKey(), naProjectionSelection);

        naProjectionSelection.resetOriginalValues();
    }

    /**
     * Caches the na projection selections in the entity cache if it is enabled.
     *
     * @param naProjectionSelections the na projection selections
     */
    @Override
    public void cacheResult(List<NaProjectionSelection> naProjectionSelections) {
        for (NaProjectionSelection naProjectionSelection : naProjectionSelections) {
            if (EntityCacheUtil.getResult(
                        NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjectionSelectionImpl.class,
                        naProjectionSelection.getPrimaryKey()) == null) {
                cacheResult(naProjectionSelection);
            } else {
                naProjectionSelection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all na projection selections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NaProjectionSelectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NaProjectionSelectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the na projection selection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NaProjectionSelection naProjectionSelection) {
        EntityCacheUtil.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionImpl.class,
            naProjectionSelection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NaProjectionSelection> naProjectionSelections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NaProjectionSelection naProjectionSelection : naProjectionSelections) {
            EntityCacheUtil.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                NaProjectionSelectionImpl.class,
                naProjectionSelection.getPrimaryKey());
        }
    }

    /**
     * Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
     *
     * @param naProjectionSelectionSid the primary key for the new na projection selection
     * @return the new na projection selection
     */
    @Override
    public NaProjectionSelection create(int naProjectionSelectionSid) {
        NaProjectionSelection naProjectionSelection = new NaProjectionSelectionImpl();

        naProjectionSelection.setNew(true);
        naProjectionSelection.setPrimaryKey(naProjectionSelectionSid);

        return naProjectionSelection;
    }

    /**
     * Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param naProjectionSelectionSid the primary key of the na projection selection
     * @return the na projection selection that was removed
     * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection remove(int naProjectionSelectionSid)
        throws NoSuchNaProjectionSelectionException, SystemException {
        return remove((Serializable) naProjectionSelectionSid);
    }

    /**
     * Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the na projection selection
     * @return the na projection selection that was removed
     * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection remove(Serializable primaryKey)
        throws NoSuchNaProjectionSelectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NaProjectionSelection naProjectionSelection = (NaProjectionSelection) session.get(NaProjectionSelectionImpl.class,
                    primaryKey);

            if (naProjectionSelection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNaProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(naProjectionSelection);
        } catch (NoSuchNaProjectionSelectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NaProjectionSelection removeImpl(
        NaProjectionSelection naProjectionSelection) throws SystemException {
        naProjectionSelection = toUnwrappedModel(naProjectionSelection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(naProjectionSelection)) {
                naProjectionSelection = (NaProjectionSelection) session.get(NaProjectionSelectionImpl.class,
                        naProjectionSelection.getPrimaryKeyObj());
            }

            if (naProjectionSelection != null) {
                session.delete(naProjectionSelection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (naProjectionSelection != null) {
            clearCache(naProjectionSelection);
        }

        return naProjectionSelection;
    }

    @Override
    public NaProjectionSelection updateImpl(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws SystemException {
        naProjectionSelection = toUnwrappedModel(naProjectionSelection);

        boolean isNew = naProjectionSelection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (naProjectionSelection.isNew()) {
                session.save(naProjectionSelection);

                naProjectionSelection.setNew(false);
            } else {
                session.merge(naProjectionSelection);
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

        EntityCacheUtil.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            NaProjectionSelectionImpl.class,
            naProjectionSelection.getPrimaryKey(), naProjectionSelection);

        return naProjectionSelection;
    }

    protected NaProjectionSelection toUnwrappedModel(
        NaProjectionSelection naProjectionSelection) {
        if (naProjectionSelection instanceof NaProjectionSelectionImpl) {
            return naProjectionSelection;
        }

        NaProjectionSelectionImpl naProjectionSelectionImpl = new NaProjectionSelectionImpl();

        naProjectionSelectionImpl.setNew(naProjectionSelection.isNew());
        naProjectionSelectionImpl.setPrimaryKey(naProjectionSelection.getPrimaryKey());

        naProjectionSelectionImpl.setScreenName(naProjectionSelection.getScreenName());
        naProjectionSelectionImpl.setFieldName(naProjectionSelection.getFieldName());
        naProjectionSelectionImpl.setFieldValues(naProjectionSelection.getFieldValues());
        naProjectionSelectionImpl.setNaProjectionSelectionSid(naProjectionSelection.getNaProjectionSelectionSid());
        naProjectionSelectionImpl.setNaProjMasterSid(naProjectionSelection.getNaProjMasterSid());

        return naProjectionSelectionImpl;
    }

    /**
     * Returns the na projection selection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the na projection selection
     * @return the na projection selection
     * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNaProjectionSelectionException, SystemException {
        NaProjectionSelection naProjectionSelection = fetchByPrimaryKey(primaryKey);

        if (naProjectionSelection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNaProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return naProjectionSelection;
    }

    /**
     * Returns the na projection selection with the primary key or throws a {@link com.stpl.app.NoSuchNaProjectionSelectionException} if it could not be found.
     *
     * @param naProjectionSelectionSid the primary key of the na projection selection
     * @return the na projection selection
     * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection findByPrimaryKey(int naProjectionSelectionSid)
        throws NoSuchNaProjectionSelectionException, SystemException {
        return findByPrimaryKey((Serializable) naProjectionSelectionSid);
    }

    /**
     * Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the na projection selection
     * @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NaProjectionSelection naProjectionSelection = (NaProjectionSelection) EntityCacheUtil.getResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                NaProjectionSelectionImpl.class, primaryKey);

        if (naProjectionSelection == _nullNaProjectionSelection) {
            return null;
        }

        if (naProjectionSelection == null) {
            Session session = null;

            try {
                session = openSession();

                naProjectionSelection = (NaProjectionSelection) session.get(NaProjectionSelectionImpl.class,
                        primaryKey);

                if (naProjectionSelection != null) {
                    cacheResult(naProjectionSelection);
                } else {
                    EntityCacheUtil.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjectionSelectionImpl.class, primaryKey,
                        _nullNaProjectionSelection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                    NaProjectionSelectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return naProjectionSelection;
    }

    /**
     * Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param naProjectionSelectionSid the primary key of the na projection selection
     * @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjectionSelection fetchByPrimaryKey(int naProjectionSelectionSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) naProjectionSelectionSid);
    }

    /**
     * Returns all the na projection selections.
     *
     * @return the na projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjectionSelection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the na projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na projection selections
     * @param end the upper bound of the range of na projection selections (not inclusive)
     * @return the range of na projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjectionSelection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the na projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na projection selections
     * @param end the upper bound of the range of na projection selections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of na projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjectionSelection> findAll(int start, int end,
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

        List<NaProjectionSelection> list = (List<NaProjectionSelection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NAPROJECTIONSELECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NAPROJECTIONSELECTION;

                if (pagination) {
                    sql = sql.concat(NaProjectionSelectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NaProjectionSelection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NaProjectionSelection>(list);
                } else {
                    list = (List<NaProjectionSelection>) QueryUtil.list(q,
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
     * Removes all the na projection selections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NaProjectionSelection naProjectionSelection : findAll()) {
            remove(naProjectionSelection);
        }
    }

    /**
     * Returns the number of na projection selections.
     *
     * @return the number of na projection selections
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

                Query q = session.createQuery(_SQL_COUNT_NAPROJECTIONSELECTION);

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
     * Initializes the na projection selection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NaProjectionSelection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NaProjectionSelection>> listenersList = new ArrayList<ModelListener<NaProjectionSelection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NaProjectionSelection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NaProjectionSelectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
