package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdRsDetailsFrException;
import com.stpl.app.model.ImtdRsDetailsFr;
import com.stpl.app.model.impl.ImtdRsDetailsFrImpl;
import com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl;
import com.stpl.app.service.persistence.ImtdRsDetailsFrPersistence;

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
 * The persistence implementation for the imtd rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsFrPersistence
 * @see ImtdRsDetailsFrUtil
 * @generated
 */
public class ImtdRsDetailsFrPersistenceImpl extends BasePersistenceImpl<ImtdRsDetailsFr>
    implements ImtdRsDetailsFrPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdRsDetailsFrUtil} to access the imtd rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsDetailsFrImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsFrImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDRSDETAILSFR = "SELECT imtdRsDetailsFr FROM ImtdRsDetailsFr imtdRsDetailsFr";
    private static final String _SQL_COUNT_IMTDRSDETAILSFR = "SELECT COUNT(imtdRsDetailsFr) FROM ImtdRsDetailsFr imtdRsDetailsFr";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsDetailsFr.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsDetailsFr exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdRsDetailsFrPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "formulaMethodId", "itemMasterSid", "imtdRsDetailsSid",
                "modifiedDate", "rsDetailsFrSid", "recordLockStatus",
                "createdDate", "createdBy", "source", "imtdRsDetailsFrSid",
                "batchId", "imtdCreatedDate", "sessionId", "usersId",
                "operation", "modifiedBy", "rsDetailsSid", "formulaId",
                "inboundStatus"
            });
    private static ImtdRsDetailsFr _nullImtdRsDetailsFr = new ImtdRsDetailsFrImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdRsDetailsFr> toCacheModel() {
                return _nullImtdRsDetailsFrCacheModel;
            }
        };

    private static CacheModel<ImtdRsDetailsFr> _nullImtdRsDetailsFrCacheModel = new CacheModel<ImtdRsDetailsFr>() {
            @Override
            public ImtdRsDetailsFr toEntityModel() {
                return _nullImtdRsDetailsFr;
            }
        };

    public ImtdRsDetailsFrPersistenceImpl() {
        setModelClass(ImtdRsDetailsFr.class);
    }

    /**
     * Caches the imtd rs details fr in the entity cache if it is enabled.
     *
     * @param imtdRsDetailsFr the imtd rs details fr
     */
    @Override
    public void cacheResult(ImtdRsDetailsFr imtdRsDetailsFr) {
        EntityCacheUtil.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey(),
            imtdRsDetailsFr);

        imtdRsDetailsFr.resetOriginalValues();
    }

    /**
     * Caches the imtd rs details frs in the entity cache if it is enabled.
     *
     * @param imtdRsDetailsFrs the imtd rs details frs
     */
    @Override
    public void cacheResult(List<ImtdRsDetailsFr> imtdRsDetailsFrs) {
        for (ImtdRsDetailsFr imtdRsDetailsFr : imtdRsDetailsFrs) {
            if (EntityCacheUtil.getResult(
                        ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsDetailsFrImpl.class,
                        imtdRsDetailsFr.getPrimaryKey()) == null) {
                cacheResult(imtdRsDetailsFr);
            } else {
                imtdRsDetailsFr.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd rs details frs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdRsDetailsFrImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdRsDetailsFrImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd rs details fr.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdRsDetailsFr imtdRsDetailsFr) {
        EntityCacheUtil.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdRsDetailsFr> imtdRsDetailsFrs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdRsDetailsFr imtdRsDetailsFr : imtdRsDetailsFrs) {
            EntityCacheUtil.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd rs details fr with the primary key. Does not add the imtd rs details fr to the database.
     *
     * @param imtdRsDetailsFrSid the primary key for the new imtd rs details fr
     * @return the new imtd rs details fr
     */
    @Override
    public ImtdRsDetailsFr create(int imtdRsDetailsFrSid) {
        ImtdRsDetailsFr imtdRsDetailsFr = new ImtdRsDetailsFrImpl();

        imtdRsDetailsFr.setNew(true);
        imtdRsDetailsFr.setPrimaryKey(imtdRsDetailsFrSid);

        return imtdRsDetailsFr;
    }

    /**
     * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
     * @return the imtd rs details fr that was removed
     * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr remove(int imtdRsDetailsFrSid)
        throws NoSuchImtdRsDetailsFrException, SystemException {
        return remove((Serializable) imtdRsDetailsFrSid);
    }

    /**
     * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd rs details fr
     * @return the imtd rs details fr that was removed
     * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr remove(Serializable primaryKey)
        throws NoSuchImtdRsDetailsFrException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdRsDetailsFr imtdRsDetailsFr = (ImtdRsDetailsFr) session.get(ImtdRsDetailsFrImpl.class,
                    primaryKey);

            if (imtdRsDetailsFr == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdRsDetailsFr);
        } catch (NoSuchImtdRsDetailsFrException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdRsDetailsFr removeImpl(ImtdRsDetailsFr imtdRsDetailsFr)
        throws SystemException {
        imtdRsDetailsFr = toUnwrappedModel(imtdRsDetailsFr);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdRsDetailsFr)) {
                imtdRsDetailsFr = (ImtdRsDetailsFr) session.get(ImtdRsDetailsFrImpl.class,
                        imtdRsDetailsFr.getPrimaryKeyObj());
            }

            if (imtdRsDetailsFr != null) {
                session.delete(imtdRsDetailsFr);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdRsDetailsFr != null) {
            clearCache(imtdRsDetailsFr);
        }

        return imtdRsDetailsFr;
    }

    @Override
    public ImtdRsDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsDetailsFr imtdRsDetailsFr)
        throws SystemException {
        imtdRsDetailsFr = toUnwrappedModel(imtdRsDetailsFr);

        boolean isNew = imtdRsDetailsFr.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdRsDetailsFr.isNew()) {
                session.save(imtdRsDetailsFr);

                imtdRsDetailsFr.setNew(false);
            } else {
                session.merge(imtdRsDetailsFr);
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

        EntityCacheUtil.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey(),
            imtdRsDetailsFr);

        return imtdRsDetailsFr;
    }

    protected ImtdRsDetailsFr toUnwrappedModel(ImtdRsDetailsFr imtdRsDetailsFr) {
        if (imtdRsDetailsFr instanceof ImtdRsDetailsFrImpl) {
            return imtdRsDetailsFr;
        }

        ImtdRsDetailsFrImpl imtdRsDetailsFrImpl = new ImtdRsDetailsFrImpl();

        imtdRsDetailsFrImpl.setNew(imtdRsDetailsFr.isNew());
        imtdRsDetailsFrImpl.setPrimaryKey(imtdRsDetailsFr.getPrimaryKey());

        imtdRsDetailsFrImpl.setFormulaMethodId(imtdRsDetailsFr.getFormulaMethodId());
        imtdRsDetailsFrImpl.setItemMasterSid(imtdRsDetailsFr.getItemMasterSid());
        imtdRsDetailsFrImpl.setImtdRsDetailsSid(imtdRsDetailsFr.getImtdRsDetailsSid());
        imtdRsDetailsFrImpl.setModifiedDate(imtdRsDetailsFr.getModifiedDate());
        imtdRsDetailsFrImpl.setRsDetailsFrSid(imtdRsDetailsFr.getRsDetailsFrSid());
        imtdRsDetailsFrImpl.setRecordLockStatus(imtdRsDetailsFr.isRecordLockStatus());
        imtdRsDetailsFrImpl.setCreatedDate(imtdRsDetailsFr.getCreatedDate());
        imtdRsDetailsFrImpl.setCreatedBy(imtdRsDetailsFr.getCreatedBy());
        imtdRsDetailsFrImpl.setSource(imtdRsDetailsFr.getSource());
        imtdRsDetailsFrImpl.setImtdRsDetailsFrSid(imtdRsDetailsFr.getImtdRsDetailsFrSid());
        imtdRsDetailsFrImpl.setBatchId(imtdRsDetailsFr.getBatchId());
        imtdRsDetailsFrImpl.setImtdCreatedDate(imtdRsDetailsFr.getImtdCreatedDate());
        imtdRsDetailsFrImpl.setSessionId(imtdRsDetailsFr.getSessionId());
        imtdRsDetailsFrImpl.setUsersId(imtdRsDetailsFr.getUsersId());
        imtdRsDetailsFrImpl.setOperation(imtdRsDetailsFr.getOperation());
        imtdRsDetailsFrImpl.setModifiedBy(imtdRsDetailsFr.getModifiedBy());
        imtdRsDetailsFrImpl.setRsDetailsSid(imtdRsDetailsFr.getRsDetailsSid());
        imtdRsDetailsFrImpl.setFormulaId(imtdRsDetailsFr.getFormulaId());
        imtdRsDetailsFrImpl.setInboundStatus(imtdRsDetailsFr.getInboundStatus());

        return imtdRsDetailsFrImpl;
    }

    /**
     * Returns the imtd rs details fr with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs details fr
     * @return the imtd rs details fr
     * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdRsDetailsFrException, SystemException {
        ImtdRsDetailsFr imtdRsDetailsFr = fetchByPrimaryKey(primaryKey);

        if (imtdRsDetailsFr == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdRsDetailsFr;
    }

    /**
     * Returns the imtd rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsFrException} if it could not be found.
     *
     * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
     * @return the imtd rs details fr
     * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr findByPrimaryKey(int imtdRsDetailsFrSid)
        throws NoSuchImtdRsDetailsFrException, SystemException {
        return findByPrimaryKey((Serializable) imtdRsDetailsFrSid);
    }

    /**
     * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs details fr
     * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdRsDetailsFr imtdRsDetailsFr = (ImtdRsDetailsFr) EntityCacheUtil.getResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsDetailsFrImpl.class, primaryKey);

        if (imtdRsDetailsFr == _nullImtdRsDetailsFr) {
            return null;
        }

        if (imtdRsDetailsFr == null) {
            Session session = null;

            try {
                session = openSession();

                imtdRsDetailsFr = (ImtdRsDetailsFr) session.get(ImtdRsDetailsFrImpl.class,
                        primaryKey);

                if (imtdRsDetailsFr != null) {
                    cacheResult(imtdRsDetailsFr);
                } else {
                    EntityCacheUtil.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsDetailsFrImpl.class, primaryKey,
                        _nullImtdRsDetailsFr);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdRsDetailsFrImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdRsDetailsFr;
    }

    /**
     * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
     * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetailsFr fetchByPrimaryKey(int imtdRsDetailsFrSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdRsDetailsFrSid);
    }

    /**
     * Returns all the imtd rs details frs.
     *
     * @return the imtd rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetailsFr> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd rs details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs details frs
     * @param end the upper bound of the range of imtd rs details frs (not inclusive)
     * @return the range of imtd rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetailsFr> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd rs details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs details frs
     * @param end the upper bound of the range of imtd rs details frs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetailsFr> findAll(int start, int end,
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

        List<ImtdRsDetailsFr> list = (List<ImtdRsDetailsFr>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDRSDETAILSFR);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDRSDETAILSFR;

                if (pagination) {
                    sql = sql.concat(ImtdRsDetailsFrModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdRsDetailsFr>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdRsDetailsFr>(list);
                } else {
                    list = (List<ImtdRsDetailsFr>) QueryUtil.list(q,
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
     * Removes all the imtd rs details frs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdRsDetailsFr imtdRsDetailsFr : findAll()) {
            remove(imtdRsDetailsFr);
        }
    }

    /**
     * Returns the number of imtd rs details frs.
     *
     * @return the number of imtd rs details frs
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

                Query q = session.createQuery(_SQL_COUNT_IMTDRSDETAILSFR);

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
     * Initializes the imtd rs details fr persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdRsDetailsFr")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdRsDetailsFr>> listenersList = new ArrayList<ModelListener<ImtdRsDetailsFr>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdRsDetailsFr>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdRsDetailsFrImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
