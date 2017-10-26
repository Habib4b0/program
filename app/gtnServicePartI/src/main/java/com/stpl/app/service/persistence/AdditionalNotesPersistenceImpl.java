package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchAdditionalNotesException;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.impl.AdditionalNotesImpl;
import com.stpl.app.model.impl.AdditionalNotesModelImpl;
import com.stpl.app.service.persistence.AdditionalNotesPersistence;

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
 * The persistence implementation for the additional notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdditionalNotesPersistence
 * @see AdditionalNotesUtil
 * @generated
 */
public class AdditionalNotesPersistenceImpl extends BasePersistenceImpl<AdditionalNotes>
    implements AdditionalNotesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AdditionalNotesUtil} to access the additional notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AdditionalNotesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesModelImpl.FINDER_CACHE_ENABLED,
            AdditionalNotesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesModelImpl.FINDER_CACHE_ENABLED,
            AdditionalNotesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ADDITIONALNOTES = "SELECT additionalNotes FROM AdditionalNotes additionalNotes";
    private static final String _SQL_COUNT_ADDITIONALNOTES = "SELECT COUNT(additionalNotes) FROM AdditionalNotes additionalNotes";
    private static final String _ORDER_BY_ENTITY_ALIAS = "additionalNotes.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AdditionalNotes exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AdditionalNotesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "forecastType", "additionalNotesId",
                "projectionId", "notes"
            });
    private static AdditionalNotes _nullAdditionalNotes = new AdditionalNotesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AdditionalNotes> toCacheModel() {
                return _nullAdditionalNotesCacheModel;
            }
        };

    private static CacheModel<AdditionalNotes> _nullAdditionalNotesCacheModel = new CacheModel<AdditionalNotes>() {
            @Override
            public AdditionalNotes toEntityModel() {
                return _nullAdditionalNotes;
            }
        };

    public AdditionalNotesPersistenceImpl() {
        setModelClass(AdditionalNotes.class);
    }

    /**
     * Caches the additional notes in the entity cache if it is enabled.
     *
     * @param additionalNotes the additional notes
     */
    @Override
    public void cacheResult(AdditionalNotes additionalNotes) {
        EntityCacheUtil.putResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesImpl.class, additionalNotes.getPrimaryKey(),
            additionalNotes);

        additionalNotes.resetOriginalValues();
    }

    /**
     * Caches the additional noteses in the entity cache if it is enabled.
     *
     * @param additionalNoteses the additional noteses
     */
    @Override
    public void cacheResult(List<AdditionalNotes> additionalNoteses) {
        for (AdditionalNotes additionalNotes : additionalNoteses) {
            if (EntityCacheUtil.getResult(
                        AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
                        AdditionalNotesImpl.class,
                        additionalNotes.getPrimaryKey()) == null) {
                cacheResult(additionalNotes);
            } else {
                additionalNotes.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all additional noteses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AdditionalNotesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AdditionalNotesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the additional notes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AdditionalNotes additionalNotes) {
        EntityCacheUtil.removeResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesImpl.class, additionalNotes.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AdditionalNotes> additionalNoteses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AdditionalNotes additionalNotes : additionalNoteses) {
            EntityCacheUtil.removeResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
                AdditionalNotesImpl.class, additionalNotes.getPrimaryKey());
        }
    }

    /**
     * Creates a new additional notes with the primary key. Does not add the additional notes to the database.
     *
     * @param additionalNotesId the primary key for the new additional notes
     * @return the new additional notes
     */
    @Override
    public AdditionalNotes create(int additionalNotesId) {
        AdditionalNotes additionalNotes = new AdditionalNotesImpl();

        additionalNotes.setNew(true);
        additionalNotes.setPrimaryKey(additionalNotesId);

        return additionalNotes;
    }

    /**
     * Removes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param additionalNotesId the primary key of the additional notes
     * @return the additional notes that was removed
     * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes remove(int additionalNotesId)
        throws NoSuchAdditionalNotesException, SystemException {
        return remove((Serializable) additionalNotesId);
    }

    /**
     * Removes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the additional notes
     * @return the additional notes that was removed
     * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes remove(Serializable primaryKey)
        throws NoSuchAdditionalNotesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AdditionalNotes additionalNotes = (AdditionalNotes) session.get(AdditionalNotesImpl.class,
                    primaryKey);

            if (additionalNotes == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAdditionalNotesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(additionalNotes);
        } catch (NoSuchAdditionalNotesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AdditionalNotes removeImpl(AdditionalNotes additionalNotes)
        throws SystemException {
        additionalNotes = toUnwrappedModel(additionalNotes);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(additionalNotes)) {
                additionalNotes = (AdditionalNotes) session.get(AdditionalNotesImpl.class,
                        additionalNotes.getPrimaryKeyObj());
            }

            if (additionalNotes != null) {
                session.delete(additionalNotes);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (additionalNotes != null) {
            clearCache(additionalNotes);
        }

        return additionalNotes;
    }

    @Override
    public AdditionalNotes updateImpl(
        com.stpl.app.model.AdditionalNotes additionalNotes)
        throws SystemException {
        additionalNotes = toUnwrappedModel(additionalNotes);

        boolean isNew = additionalNotes.isNew();

        Session session = null;

        try {
            session = openSession();

            if (additionalNotes.isNew()) {
                session.save(additionalNotes);

                additionalNotes.setNew(false);
            } else {
                session.merge(additionalNotes);
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

        EntityCacheUtil.putResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
            AdditionalNotesImpl.class, additionalNotes.getPrimaryKey(),
            additionalNotes);

        return additionalNotes;
    }

    protected AdditionalNotes toUnwrappedModel(AdditionalNotes additionalNotes) {
        if (additionalNotes instanceof AdditionalNotesImpl) {
            return additionalNotes;
        }

        AdditionalNotesImpl additionalNotesImpl = new AdditionalNotesImpl();

        additionalNotesImpl.setNew(additionalNotes.isNew());
        additionalNotesImpl.setPrimaryKey(additionalNotes.getPrimaryKey());

        additionalNotesImpl.setCreatedDate(additionalNotes.getCreatedDate());
        additionalNotesImpl.setCreatedBy(additionalNotes.getCreatedBy());
        additionalNotesImpl.setForecastType(additionalNotes.getForecastType());
        additionalNotesImpl.setAdditionalNotesId(additionalNotes.getAdditionalNotesId());
        additionalNotesImpl.setProjectionId(additionalNotes.getProjectionId());
        additionalNotesImpl.setNotes(additionalNotes.getNotes());

        return additionalNotesImpl;
    }

    /**
     * Returns the additional notes with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the additional notes
     * @return the additional notes
     * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAdditionalNotesException, SystemException {
        AdditionalNotes additionalNotes = fetchByPrimaryKey(primaryKey);

        if (additionalNotes == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAdditionalNotesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return additionalNotes;
    }

    /**
     * Returns the additional notes with the primary key or throws a {@link com.stpl.app.NoSuchAdditionalNotesException} if it could not be found.
     *
     * @param additionalNotesId the primary key of the additional notes
     * @return the additional notes
     * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes findByPrimaryKey(int additionalNotesId)
        throws NoSuchAdditionalNotesException, SystemException {
        return findByPrimaryKey((Serializable) additionalNotesId);
    }

    /**
     * Returns the additional notes with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the additional notes
     * @return the additional notes, or <code>null</code> if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AdditionalNotes additionalNotes = (AdditionalNotes) EntityCacheUtil.getResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
                AdditionalNotesImpl.class, primaryKey);

        if (additionalNotes == _nullAdditionalNotes) {
            return null;
        }

        if (additionalNotes == null) {
            Session session = null;

            try {
                session = openSession();

                additionalNotes = (AdditionalNotes) session.get(AdditionalNotesImpl.class,
                        primaryKey);

                if (additionalNotes != null) {
                    cacheResult(additionalNotes);
                } else {
                    EntityCacheUtil.putResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
                        AdditionalNotesImpl.class, primaryKey,
                        _nullAdditionalNotes);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AdditionalNotesModelImpl.ENTITY_CACHE_ENABLED,
                    AdditionalNotesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return additionalNotes;
    }

    /**
     * Returns the additional notes with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param additionalNotesId the primary key of the additional notes
     * @return the additional notes, or <code>null</code> if a additional notes with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdditionalNotes fetchByPrimaryKey(int additionalNotesId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) additionalNotesId);
    }

    /**
     * Returns all the additional noteses.
     *
     * @return the additional noteses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdditionalNotes> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the additional noteses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of additional noteses
     * @param end the upper bound of the range of additional noteses (not inclusive)
     * @return the range of additional noteses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdditionalNotes> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the additional noteses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of additional noteses
     * @param end the upper bound of the range of additional noteses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of additional noteses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdditionalNotes> findAll(int start, int end,
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

        List<AdditionalNotes> list = (List<AdditionalNotes>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ADDITIONALNOTES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ADDITIONALNOTES;

                if (pagination) {
                    sql = sql.concat(AdditionalNotesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AdditionalNotes>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AdditionalNotes>(list);
                } else {
                    list = (List<AdditionalNotes>) QueryUtil.list(q,
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
     * Removes all the additional noteses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AdditionalNotes additionalNotes : findAll()) {
            remove(additionalNotes);
        }
    }

    /**
     * Returns the number of additional noteses.
     *
     * @return the number of additional noteses
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

                Query q = session.createQuery(_SQL_COUNT_ADDITIONALNOTES);

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
     * Initializes the additional notes persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.AdditionalNotes")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AdditionalNotes>> listenersList = new ArrayList<ModelListener<AdditionalNotes>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AdditionalNotes>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AdditionalNotesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
