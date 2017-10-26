package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChProjectionSelectionException;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.impl.ChProjectionSelectionImpl;
import com.stpl.app.model.impl.ChProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.ChProjectionSelectionPersistence;

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
 * The persistence implementation for the ch projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionSelectionPersistence
 * @see ChProjectionSelectionUtil
 * @generated
 */
public class ChProjectionSelectionPersistenceImpl extends BasePersistenceImpl<ChProjectionSelection>
    implements ChProjectionSelectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChProjectionSelectionUtil} to access the ch projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChProjectionSelectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            ChProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            ChProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHPROJECTIONSELECTION = "SELECT chProjectionSelection FROM ChProjectionSelection chProjectionSelection";
    private static final String _SQL_COUNT_CHPROJECTIONSELECTION = "SELECT COUNT(chProjectionSelection) FROM ChProjectionSelection chProjectionSelection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chProjectionSelection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChProjectionSelection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChProjectionSelectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "screenName", "fieldName", "chProjectionSelectionSid",
                "fieldValues", "projectionMasterSid"
            });
    private static ChProjectionSelection _nullChProjectionSelection = new ChProjectionSelectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChProjectionSelection> toCacheModel() {
                return _nullChProjectionSelectionCacheModel;
            }
        };

    private static CacheModel<ChProjectionSelection> _nullChProjectionSelectionCacheModel =
        new CacheModel<ChProjectionSelection>() {
            @Override
            public ChProjectionSelection toEntityModel() {
                return _nullChProjectionSelection;
            }
        };

    public ChProjectionSelectionPersistenceImpl() {
        setModelClass(ChProjectionSelection.class);
    }

    /**
     * Caches the ch projection selection in the entity cache if it is enabled.
     *
     * @param chProjectionSelection the ch projection selection
     */
    @Override
    public void cacheResult(ChProjectionSelection chProjectionSelection) {
        EntityCacheUtil.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionImpl.class,
            chProjectionSelection.getPrimaryKey(), chProjectionSelection);

        chProjectionSelection.resetOriginalValues();
    }

    /**
     * Caches the ch projection selections in the entity cache if it is enabled.
     *
     * @param chProjectionSelections the ch projection selections
     */
    @Override
    public void cacheResult(List<ChProjectionSelection> chProjectionSelections) {
        for (ChProjectionSelection chProjectionSelection : chProjectionSelections) {
            if (EntityCacheUtil.getResult(
                        ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        ChProjectionSelectionImpl.class,
                        chProjectionSelection.getPrimaryKey()) == null) {
                cacheResult(chProjectionSelection);
            } else {
                chProjectionSelection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch projection selections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChProjectionSelectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChProjectionSelectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch projection selection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChProjectionSelection chProjectionSelection) {
        EntityCacheUtil.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionImpl.class,
            chProjectionSelection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChProjectionSelection> chProjectionSelections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChProjectionSelection chProjectionSelection : chProjectionSelections) {
            EntityCacheUtil.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                ChProjectionSelectionImpl.class,
                chProjectionSelection.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
     *
     * @param chProjectionSelectionSid the primary key for the new ch projection selection
     * @return the new ch projection selection
     */
    @Override
    public ChProjectionSelection create(int chProjectionSelectionSid) {
        ChProjectionSelection chProjectionSelection = new ChProjectionSelectionImpl();

        chProjectionSelection.setNew(true);
        chProjectionSelection.setPrimaryKey(chProjectionSelectionSid);

        return chProjectionSelection;
    }

    /**
     * Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chProjectionSelectionSid the primary key of the ch projection selection
     * @return the ch projection selection that was removed
     * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection remove(int chProjectionSelectionSid)
        throws NoSuchChProjectionSelectionException, SystemException {
        return remove((Serializable) chProjectionSelectionSid);
    }

    /**
     * Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch projection selection
     * @return the ch projection selection that was removed
     * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection remove(Serializable primaryKey)
        throws NoSuchChProjectionSelectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChProjectionSelection chProjectionSelection = (ChProjectionSelection) session.get(ChProjectionSelectionImpl.class,
                    primaryKey);

            if (chProjectionSelection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chProjectionSelection);
        } catch (NoSuchChProjectionSelectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChProjectionSelection removeImpl(
        ChProjectionSelection chProjectionSelection) throws SystemException {
        chProjectionSelection = toUnwrappedModel(chProjectionSelection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chProjectionSelection)) {
                chProjectionSelection = (ChProjectionSelection) session.get(ChProjectionSelectionImpl.class,
                        chProjectionSelection.getPrimaryKeyObj());
            }

            if (chProjectionSelection != null) {
                session.delete(chProjectionSelection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chProjectionSelection != null) {
            clearCache(chProjectionSelection);
        }

        return chProjectionSelection;
    }

    @Override
    public ChProjectionSelection updateImpl(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection)
        throws SystemException {
        chProjectionSelection = toUnwrappedModel(chProjectionSelection);

        boolean isNew = chProjectionSelection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chProjectionSelection.isNew()) {
                session.save(chProjectionSelection);

                chProjectionSelection.setNew(false);
            } else {
                session.merge(chProjectionSelection);
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

        EntityCacheUtil.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionSelectionImpl.class,
            chProjectionSelection.getPrimaryKey(), chProjectionSelection);

        return chProjectionSelection;
    }

    protected ChProjectionSelection toUnwrappedModel(
        ChProjectionSelection chProjectionSelection) {
        if (chProjectionSelection instanceof ChProjectionSelectionImpl) {
            return chProjectionSelection;
        }

        ChProjectionSelectionImpl chProjectionSelectionImpl = new ChProjectionSelectionImpl();

        chProjectionSelectionImpl.setNew(chProjectionSelection.isNew());
        chProjectionSelectionImpl.setPrimaryKey(chProjectionSelection.getPrimaryKey());

        chProjectionSelectionImpl.setScreenName(chProjectionSelection.getScreenName());
        chProjectionSelectionImpl.setFieldName(chProjectionSelection.getFieldName());
        chProjectionSelectionImpl.setChProjectionSelectionSid(chProjectionSelection.getChProjectionSelectionSid());
        chProjectionSelectionImpl.setFieldValues(chProjectionSelection.getFieldValues());
        chProjectionSelectionImpl.setProjectionMasterSid(chProjectionSelection.getProjectionMasterSid());

        return chProjectionSelectionImpl;
    }

    /**
     * Returns the ch projection selection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch projection selection
     * @return the ch projection selection
     * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChProjectionSelectionException, SystemException {
        ChProjectionSelection chProjectionSelection = fetchByPrimaryKey(primaryKey);

        if (chProjectionSelection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chProjectionSelection;
    }

    /**
     * Returns the ch projection selection with the primary key or throws a {@link com.stpl.app.NoSuchChProjectionSelectionException} if it could not be found.
     *
     * @param chProjectionSelectionSid the primary key of the ch projection selection
     * @return the ch projection selection
     * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection findByPrimaryKey(int chProjectionSelectionSid)
        throws NoSuchChProjectionSelectionException, SystemException {
        return findByPrimaryKey((Serializable) chProjectionSelectionSid);
    }

    /**
     * Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch projection selection
     * @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChProjectionSelection chProjectionSelection = (ChProjectionSelection) EntityCacheUtil.getResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                ChProjectionSelectionImpl.class, primaryKey);

        if (chProjectionSelection == _nullChProjectionSelection) {
            return null;
        }

        if (chProjectionSelection == null) {
            Session session = null;

            try {
                session = openSession();

                chProjectionSelection = (ChProjectionSelection) session.get(ChProjectionSelectionImpl.class,
                        primaryKey);

                if (chProjectionSelection != null) {
                    cacheResult(chProjectionSelection);
                } else {
                    EntityCacheUtil.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        ChProjectionSelectionImpl.class, primaryKey,
                        _nullChProjectionSelection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                    ChProjectionSelectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chProjectionSelection;
    }

    /**
     * Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chProjectionSelectionSid the primary key of the ch projection selection
     * @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionSelection fetchByPrimaryKey(int chProjectionSelectionSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) chProjectionSelectionSid);
    }

    /**
     * Returns all the ch projection selections.
     *
     * @return the ch projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionSelection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch projection selections
     * @param end the upper bound of the range of ch projection selections (not inclusive)
     * @return the range of ch projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionSelection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch projection selections
     * @param end the upper bound of the range of ch projection selections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionSelection> findAll(int start, int end,
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

        List<ChProjectionSelection> list = (List<ChProjectionSelection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHPROJECTIONSELECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHPROJECTIONSELECTION;

                if (pagination) {
                    sql = sql.concat(ChProjectionSelectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChProjectionSelection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChProjectionSelection>(list);
                } else {
                    list = (List<ChProjectionSelection>) QueryUtil.list(q,
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
     * Removes all the ch projection selections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChProjectionSelection chProjectionSelection : findAll()) {
            remove(chProjectionSelection);
        }
    }

    /**
     * Returns the number of ch projection selections.
     *
     * @return the number of ch projection selections
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

                Query q = session.createQuery(_SQL_COUNT_CHPROJECTIONSELECTION);

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
     * Initializes the ch projection selection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChProjectionSelection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChProjectionSelection>> listenersList = new ArrayList<ModelListener<ChProjectionSelection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChProjectionSelection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChProjectionSelectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
