package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMProjectionSelectionException;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.impl.MProjectionSelectionImpl;
import com.stpl.app.model.impl.MProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.MProjectionSelectionPersistence;

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
 * The persistence implementation for the m projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MProjectionSelectionPersistence
 * @see MProjectionSelectionUtil
 * @generated
 */
public class MProjectionSelectionPersistenceImpl extends BasePersistenceImpl<MProjectionSelection>
    implements MProjectionSelectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MProjectionSelectionUtil} to access the m projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MProjectionSelectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            MProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
            MProjectionSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MPROJECTIONSELECTION = "SELECT mProjectionSelection FROM MProjectionSelection mProjectionSelection";
    private static final String _SQL_COUNT_MPROJECTIONSELECTION = "SELECT COUNT(mProjectionSelection) FROM MProjectionSelection mProjectionSelection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mProjectionSelection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MProjectionSelection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MProjectionSelectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "mProjectionSelectionSid", "projectionMasterSid", "fieldValues",
                "fieldName", "screenName"
            });
    private static MProjectionSelection _nullMProjectionSelection = new MProjectionSelectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MProjectionSelection> toCacheModel() {
                return _nullMProjectionSelectionCacheModel;
            }
        };

    private static CacheModel<MProjectionSelection> _nullMProjectionSelectionCacheModel =
        new CacheModel<MProjectionSelection>() {
            @Override
            public MProjectionSelection toEntityModel() {
                return _nullMProjectionSelection;
            }
        };

    public MProjectionSelectionPersistenceImpl() {
        setModelClass(MProjectionSelection.class);
    }

    /**
     * Caches the m projection selection in the entity cache if it is enabled.
     *
     * @param mProjectionSelection the m projection selection
     */
    @Override
    public void cacheResult(MProjectionSelection mProjectionSelection) {
        EntityCacheUtil.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionImpl.class,
            mProjectionSelection.getPrimaryKey(), mProjectionSelection);

        mProjectionSelection.resetOriginalValues();
    }

    /**
     * Caches the m projection selections in the entity cache if it is enabled.
     *
     * @param mProjectionSelections the m projection selections
     */
    @Override
    public void cacheResult(List<MProjectionSelection> mProjectionSelections) {
        for (MProjectionSelection mProjectionSelection : mProjectionSelections) {
            if (EntityCacheUtil.getResult(
                        MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        MProjectionSelectionImpl.class,
                        mProjectionSelection.getPrimaryKey()) == null) {
                cacheResult(mProjectionSelection);
            } else {
                mProjectionSelection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m projection selections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MProjectionSelectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MProjectionSelectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m projection selection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MProjectionSelection mProjectionSelection) {
        EntityCacheUtil.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionImpl.class, mProjectionSelection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MProjectionSelection> mProjectionSelections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MProjectionSelection mProjectionSelection : mProjectionSelections) {
            EntityCacheUtil.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                MProjectionSelectionImpl.class,
                mProjectionSelection.getPrimaryKey());
        }
    }

    /**
     * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
     *
     * @param mProjectionSelectionSid the primary key for the new m projection selection
     * @return the new m projection selection
     */
    @Override
    public MProjectionSelection create(int mProjectionSelectionSid) {
        MProjectionSelection mProjectionSelection = new MProjectionSelectionImpl();

        mProjectionSelection.setNew(true);
        mProjectionSelection.setPrimaryKey(mProjectionSelectionSid);

        return mProjectionSelection;
    }

    /**
     * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param mProjectionSelectionSid the primary key of the m projection selection
     * @return the m projection selection that was removed
     * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection remove(int mProjectionSelectionSid)
        throws NoSuchMProjectionSelectionException, SystemException {
        return remove((Serializable) mProjectionSelectionSid);
    }

    /**
     * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m projection selection
     * @return the m projection selection that was removed
     * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection remove(Serializable primaryKey)
        throws NoSuchMProjectionSelectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MProjectionSelection mProjectionSelection = (MProjectionSelection) session.get(MProjectionSelectionImpl.class,
                    primaryKey);

            if (mProjectionSelection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mProjectionSelection);
        } catch (NoSuchMProjectionSelectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MProjectionSelection removeImpl(
        MProjectionSelection mProjectionSelection) throws SystemException {
        mProjectionSelection = toUnwrappedModel(mProjectionSelection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mProjectionSelection)) {
                mProjectionSelection = (MProjectionSelection) session.get(MProjectionSelectionImpl.class,
                        mProjectionSelection.getPrimaryKeyObj());
            }

            if (mProjectionSelection != null) {
                session.delete(mProjectionSelection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mProjectionSelection != null) {
            clearCache(mProjectionSelection);
        }

        return mProjectionSelection;
    }

    @Override
    public MProjectionSelection updateImpl(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws SystemException {
        mProjectionSelection = toUnwrappedModel(mProjectionSelection);

        boolean isNew = mProjectionSelection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mProjectionSelection.isNew()) {
                session.save(mProjectionSelection);

                mProjectionSelection.setNew(false);
            } else {
                session.merge(mProjectionSelection);
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

        EntityCacheUtil.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
            MProjectionSelectionImpl.class,
            mProjectionSelection.getPrimaryKey(), mProjectionSelection);

        return mProjectionSelection;
    }

    protected MProjectionSelection toUnwrappedModel(
        MProjectionSelection mProjectionSelection) {
        if (mProjectionSelection instanceof MProjectionSelectionImpl) {
            return mProjectionSelection;
        }

        MProjectionSelectionImpl mProjectionSelectionImpl = new MProjectionSelectionImpl();

        mProjectionSelectionImpl.setNew(mProjectionSelection.isNew());
        mProjectionSelectionImpl.setPrimaryKey(mProjectionSelection.getPrimaryKey());

        mProjectionSelectionImpl.setMProjectionSelectionSid(mProjectionSelection.getMProjectionSelectionSid());
        mProjectionSelectionImpl.setProjectionMasterSid(mProjectionSelection.getProjectionMasterSid());
        mProjectionSelectionImpl.setFieldValues(mProjectionSelection.getFieldValues());
        mProjectionSelectionImpl.setFieldName(mProjectionSelection.getFieldName());
        mProjectionSelectionImpl.setScreenName(mProjectionSelection.getScreenName());

        return mProjectionSelectionImpl;
    }

    /**
     * Returns the m projection selection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m projection selection
     * @return the m projection selection
     * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMProjectionSelectionException, SystemException {
        MProjectionSelection mProjectionSelection = fetchByPrimaryKey(primaryKey);

        if (mProjectionSelection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mProjectionSelection;
    }

    /**
     * Returns the m projection selection with the primary key or throws a {@link com.stpl.app.NoSuchMProjectionSelectionException} if it could not be found.
     *
     * @param mProjectionSelectionSid the primary key of the m projection selection
     * @return the m projection selection
     * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection findByPrimaryKey(int mProjectionSelectionSid)
        throws NoSuchMProjectionSelectionException, SystemException {
        return findByPrimaryKey((Serializable) mProjectionSelectionSid);
    }

    /**
     * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m projection selection
     * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MProjectionSelection mProjectionSelection = (MProjectionSelection) EntityCacheUtil.getResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                MProjectionSelectionImpl.class, primaryKey);

        if (mProjectionSelection == _nullMProjectionSelection) {
            return null;
        }

        if (mProjectionSelection == null) {
            Session session = null;

            try {
                session = openSession();

                mProjectionSelection = (MProjectionSelection) session.get(MProjectionSelectionImpl.class,
                        primaryKey);

                if (mProjectionSelection != null) {
                    cacheResult(mProjectionSelection);
                } else {
                    EntityCacheUtil.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        MProjectionSelectionImpl.class, primaryKey,
                        _nullMProjectionSelection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
                    MProjectionSelectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mProjectionSelection;
    }

    /**
     * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param mProjectionSelectionSid the primary key of the m projection selection
     * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MProjectionSelection fetchByPrimaryKey(int mProjectionSelectionSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) mProjectionSelectionSid);
    }

    /**
     * Returns all the m projection selections.
     *
     * @return the m projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MProjectionSelection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m projection selections
     * @param end the upper bound of the range of m projection selections (not inclusive)
     * @return the range of m projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MProjectionSelection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m projection selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m projection selections
     * @param end the upper bound of the range of m projection selections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m projection selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MProjectionSelection> findAll(int start, int end,
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

        List<MProjectionSelection> list = (List<MProjectionSelection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MPROJECTIONSELECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MPROJECTIONSELECTION;

                if (pagination) {
                    sql = sql.concat(MProjectionSelectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MProjectionSelection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MProjectionSelection>(list);
                } else {
                    list = (List<MProjectionSelection>) QueryUtil.list(q,
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
     * Removes all the m projection selections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MProjectionSelection mProjectionSelection : findAll()) {
            remove(mProjectionSelection);
        }
    }

    /**
     * Returns the number of m projection selections.
     *
     * @return the number of m projection selections
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

                Query q = session.createQuery(_SQL_COUNT_MPROJECTIONSELECTION);

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
     * Initializes the m projection selection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MProjectionSelection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MProjectionSelection>> listenersList = new ArrayList<ModelListener<MProjectionSelection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MProjectionSelection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MProjectionSelectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
