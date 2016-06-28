package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCdrModelException;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.impl.CdrModelImpl;
import com.stpl.app.model.impl.CdrModelModelImpl;
import com.stpl.app.service.persistence.CdrModelPersistence;

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
 * The persistence implementation for the cdr model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrModelPersistence
 * @see CdrModelUtil
 * @generated
 */
public class CdrModelPersistenceImpl extends BasePersistenceImpl<CdrModel>
    implements CdrModelPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CdrModelUtil} to access the cdr model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CdrModelImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelModelImpl.FINDER_CACHE_ENABLED, CdrModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelModelImpl.FINDER_CACHE_ENABLED, CdrModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CDRMODEL = "SELECT cdrModel FROM CdrModel cdrModel";
    private static final String _SQL_COUNT_CDRMODEL = "SELECT COUNT(cdrModel) FROM CdrModel cdrModel";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cdrModel.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CdrModel exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CdrModelPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "ruleCategory", "ruleType", "modifiedBy",
                "internalNotes", "createdDate", "ruleName", "cdrModelSid",
                "ruleNo", "modifiedDate"
            });
    private static CdrModel _nullCdrModel = new CdrModelImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CdrModel> toCacheModel() {
                return _nullCdrModelCacheModel;
            }
        };

    private static CacheModel<CdrModel> _nullCdrModelCacheModel = new CacheModel<CdrModel>() {
            @Override
            public CdrModel toEntityModel() {
                return _nullCdrModel;
            }
        };

    public CdrModelPersistenceImpl() {
        setModelClass(CdrModel.class);
    }

    /**
     * Caches the cdr model in the entity cache if it is enabled.
     *
     * @param cdrModel the cdr model
     */
    @Override
    public void cacheResult(CdrModel cdrModel) {
        EntityCacheUtil.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelImpl.class, cdrModel.getPrimaryKey(), cdrModel);

        cdrModel.resetOriginalValues();
    }

    /**
     * Caches the cdr models in the entity cache if it is enabled.
     *
     * @param cdrModels the cdr models
     */
    @Override
    public void cacheResult(List<CdrModel> cdrModels) {
        for (CdrModel cdrModel : cdrModels) {
            if (EntityCacheUtil.getResult(
                        CdrModelModelImpl.ENTITY_CACHE_ENABLED,
                        CdrModelImpl.class, cdrModel.getPrimaryKey()) == null) {
                cacheResult(cdrModel);
            } else {
                cdrModel.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cdr models.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CdrModelImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CdrModelImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cdr model.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CdrModel cdrModel) {
        EntityCacheUtil.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelImpl.class, cdrModel.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CdrModel> cdrModels) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CdrModel cdrModel : cdrModels) {
            EntityCacheUtil.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
                CdrModelImpl.class, cdrModel.getPrimaryKey());
        }
    }

    /**
     * Creates a new cdr model with the primary key. Does not add the cdr model to the database.
     *
     * @param cdrModelSid the primary key for the new cdr model
     * @return the new cdr model
     */
    @Override
    public CdrModel create(int cdrModelSid) {
        CdrModel cdrModel = new CdrModelImpl();

        cdrModel.setNew(true);
        cdrModel.setPrimaryKey(cdrModelSid);

        return cdrModel;
    }

    /**
     * Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cdrModelSid the primary key of the cdr model
     * @return the cdr model that was removed
     * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel remove(int cdrModelSid)
        throws NoSuchCdrModelException, SystemException {
        return remove((Serializable) cdrModelSid);
    }

    /**
     * Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cdr model
     * @return the cdr model that was removed
     * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel remove(Serializable primaryKey)
        throws NoSuchCdrModelException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CdrModel cdrModel = (CdrModel) session.get(CdrModelImpl.class,
                    primaryKey);

            if (cdrModel == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCdrModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cdrModel);
        } catch (NoSuchCdrModelException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CdrModel removeImpl(CdrModel cdrModel) throws SystemException {
        cdrModel = toUnwrappedModel(cdrModel);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cdrModel)) {
                cdrModel = (CdrModel) session.get(CdrModelImpl.class,
                        cdrModel.getPrimaryKeyObj());
            }

            if (cdrModel != null) {
                session.delete(cdrModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cdrModel != null) {
            clearCache(cdrModel);
        }

        return cdrModel;
    }

    @Override
    public CdrModel updateImpl(com.stpl.app.model.CdrModel cdrModel)
        throws SystemException {
        cdrModel = toUnwrappedModel(cdrModel);

        boolean isNew = cdrModel.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cdrModel.isNew()) {
                session.save(cdrModel);

                cdrModel.setNew(false);
            } else {
                session.merge(cdrModel);
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

        EntityCacheUtil.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
            CdrModelImpl.class, cdrModel.getPrimaryKey(), cdrModel);

        return cdrModel;
    }

    protected CdrModel toUnwrappedModel(CdrModel cdrModel) {
        if (cdrModel instanceof CdrModelImpl) {
            return cdrModel;
        }

        CdrModelImpl cdrModelImpl = new CdrModelImpl();

        cdrModelImpl.setNew(cdrModel.isNew());
        cdrModelImpl.setPrimaryKey(cdrModel.getPrimaryKey());

        cdrModelImpl.setCreatedBy(cdrModel.getCreatedBy());
        cdrModelImpl.setRuleCategory(cdrModel.getRuleCategory());
        cdrModelImpl.setRuleType(cdrModel.getRuleType());
        cdrModelImpl.setModifiedBy(cdrModel.getModifiedBy());
        cdrModelImpl.setInternalNotes(cdrModel.getInternalNotes());
        cdrModelImpl.setCreatedDate(cdrModel.getCreatedDate());
        cdrModelImpl.setRuleName(cdrModel.getRuleName());
        cdrModelImpl.setCdrModelSid(cdrModel.getCdrModelSid());
        cdrModelImpl.setRuleNo(cdrModel.getRuleNo());
        cdrModelImpl.setModifiedDate(cdrModel.getModifiedDate());

        return cdrModelImpl;
    }

    /**
     * Returns the cdr model with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cdr model
     * @return the cdr model
     * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCdrModelException, SystemException {
        CdrModel cdrModel = fetchByPrimaryKey(primaryKey);

        if (cdrModel == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCdrModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cdrModel;
    }

    /**
     * Returns the cdr model with the primary key or throws a {@link com.stpl.app.NoSuchCdrModelException} if it could not be found.
     *
     * @param cdrModelSid the primary key of the cdr model
     * @return the cdr model
     * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel findByPrimaryKey(int cdrModelSid)
        throws NoSuchCdrModelException, SystemException {
        return findByPrimaryKey((Serializable) cdrModelSid);
    }

    /**
     * Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cdr model
     * @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CdrModel cdrModel = (CdrModel) EntityCacheUtil.getResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
                CdrModelImpl.class, primaryKey);

        if (cdrModel == _nullCdrModel) {
            return null;
        }

        if (cdrModel == null) {
            Session session = null;

            try {
                session = openSession();

                cdrModel = (CdrModel) session.get(CdrModelImpl.class, primaryKey);

                if (cdrModel != null) {
                    cacheResult(cdrModel);
                } else {
                    EntityCacheUtil.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
                        CdrModelImpl.class, primaryKey, _nullCdrModel);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
                    CdrModelImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cdrModel;
    }

    /**
     * Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cdrModelSid the primary key of the cdr model
     * @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrModel fetchByPrimaryKey(int cdrModelSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cdrModelSid);
    }

    /**
     * Returns all the cdr models.
     *
     * @return the cdr models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrModel> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cdr models.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cdr models
     * @param end the upper bound of the range of cdr models (not inclusive)
     * @return the range of cdr models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrModel> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cdr models.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cdr models
     * @param end the upper bound of the range of cdr models (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cdr models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrModel> findAll(int start, int end,
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

        List<CdrModel> list = (List<CdrModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CDRMODEL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CDRMODEL;

                if (pagination) {
                    sql = sql.concat(CdrModelModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CdrModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CdrModel>(list);
                } else {
                    list = (List<CdrModel>) QueryUtil.list(q, getDialect(),
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
     * Removes all the cdr models from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CdrModel cdrModel : findAll()) {
            remove(cdrModel);
        }
    }

    /**
     * Returns the number of cdr models.
     *
     * @return the number of cdr models
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

                Query q = session.createQuery(_SQL_COUNT_CDRMODEL);

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
     * Initializes the cdr model persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CdrModel")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CdrModel>> listenersList = new ArrayList<ModelListener<CdrModel>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CdrModel>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CdrModelImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
