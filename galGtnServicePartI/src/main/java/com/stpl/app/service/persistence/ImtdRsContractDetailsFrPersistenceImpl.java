package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdRsContractDetailsFrException;
import com.stpl.app.model.ImtdRsContractDetailsFr;
import com.stpl.app.model.impl.ImtdRsContractDetailsFrImpl;
import com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl;
import com.stpl.app.service.persistence.ImtdRsContractDetailsFrPersistence;

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
 * The persistence implementation for the imtd rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFrPersistence
 * @see ImtdRsContractDetailsFrUtil
 * @generated
 */
public class ImtdRsContractDetailsFrPersistenceImpl extends BasePersistenceImpl<ImtdRsContractDetailsFr>
    implements ImtdRsContractDetailsFrPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdRsContractDetailsFrUtil} to access the imtd rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsContractDetailsFrImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsContractDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsContractDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDRSCONTRACTDETAILSFR = "SELECT imtdRsContractDetailsFr FROM ImtdRsContractDetailsFr imtdRsContractDetailsFr";
    private static final String _SQL_COUNT_IMTDRSCONTRACTDETAILSFR = "SELECT COUNT(imtdRsContractDetailsFr) FROM ImtdRsContractDetailsFr imtdRsContractDetailsFr";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsContractDetailsFr.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsContractDetailsFr exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdRsContractDetailsFrPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "imtdRsContractDetailsFrSid", "formulaMethodId", "itemMasterSid",
                "rsContractDetailsFrSid", "modifiedDate", "rsContractDetailsSid",
                "imtdItemPriceRebateDetailsSid", "recordLockStatus",
                "createdDate", "createdBy", "source", "batchId",
                "imtdCreatedDate", "sessionId", "usersId", "operation",
                "modifiedBy", "formulaId", "inboundStatus"
            });
    private static ImtdRsContractDetailsFr _nullImtdRsContractDetailsFr = new ImtdRsContractDetailsFrImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdRsContractDetailsFr> toCacheModel() {
                return _nullImtdRsContractDetailsFrCacheModel;
            }
        };

    private static CacheModel<ImtdRsContractDetailsFr> _nullImtdRsContractDetailsFrCacheModel =
        new CacheModel<ImtdRsContractDetailsFr>() {
            @Override
            public ImtdRsContractDetailsFr toEntityModel() {
                return _nullImtdRsContractDetailsFr;
            }
        };

    public ImtdRsContractDetailsFrPersistenceImpl() {
        setModelClass(ImtdRsContractDetailsFr.class);
    }

    /**
     * Caches the imtd rs contract details fr in the entity cache if it is enabled.
     *
     * @param imtdRsContractDetailsFr the imtd rs contract details fr
     */
    @Override
    public void cacheResult(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        EntityCacheUtil.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrImpl.class,
            imtdRsContractDetailsFr.getPrimaryKey(), imtdRsContractDetailsFr);

        imtdRsContractDetailsFr.resetOriginalValues();
    }

    /**
     * Caches the imtd rs contract details frs in the entity cache if it is enabled.
     *
     * @param imtdRsContractDetailsFrs the imtd rs contract details frs
     */
    @Override
    public void cacheResult(
        List<ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
        for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : imtdRsContractDetailsFrs) {
            if (EntityCacheUtil.getResult(
                        ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsContractDetailsFrImpl.class,
                        imtdRsContractDetailsFr.getPrimaryKey()) == null) {
                cacheResult(imtdRsContractDetailsFr);
            } else {
                imtdRsContractDetailsFr.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd rs contract details frs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdRsContractDetailsFrImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdRsContractDetailsFrImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd rs contract details fr.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        EntityCacheUtil.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrImpl.class,
            imtdRsContractDetailsFr.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : imtdRsContractDetailsFrs) {
            EntityCacheUtil.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsContractDetailsFrImpl.class,
                imtdRsContractDetailsFr.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
     *
     * @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
     * @return the new imtd rs contract details fr
     */
    @Override
    public ImtdRsContractDetailsFr create(int imtdRsContractDetailsFrSid) {
        ImtdRsContractDetailsFr imtdRsContractDetailsFr = new ImtdRsContractDetailsFrImpl();

        imtdRsContractDetailsFr.setNew(true);
        imtdRsContractDetailsFr.setPrimaryKey(imtdRsContractDetailsFrSid);

        return imtdRsContractDetailsFr;
    }

    /**
     * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr that was removed
     * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr remove(int imtdRsContractDetailsFrSid)
        throws NoSuchImtdRsContractDetailsFrException, SystemException {
        return remove((Serializable) imtdRsContractDetailsFrSid);
    }

    /**
     * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr that was removed
     * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr remove(Serializable primaryKey)
        throws NoSuchImtdRsContractDetailsFrException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdRsContractDetailsFr imtdRsContractDetailsFr = (ImtdRsContractDetailsFr) session.get(ImtdRsContractDetailsFrImpl.class,
                    primaryKey);

            if (imtdRsContractDetailsFr == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdRsContractDetailsFr);
        } catch (NoSuchImtdRsContractDetailsFrException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdRsContractDetailsFr removeImpl(
        ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws SystemException {
        imtdRsContractDetailsFr = toUnwrappedModel(imtdRsContractDetailsFr);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdRsContractDetailsFr)) {
                imtdRsContractDetailsFr = (ImtdRsContractDetailsFr) session.get(ImtdRsContractDetailsFrImpl.class,
                        imtdRsContractDetailsFr.getPrimaryKeyObj());
            }

            if (imtdRsContractDetailsFr != null) {
                session.delete(imtdRsContractDetailsFr);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdRsContractDetailsFr != null) {
            clearCache(imtdRsContractDetailsFr);
        }

        return imtdRsContractDetailsFr;
    }

    @Override
    public ImtdRsContractDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws SystemException {
        imtdRsContractDetailsFr = toUnwrappedModel(imtdRsContractDetailsFr);

        boolean isNew = imtdRsContractDetailsFr.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdRsContractDetailsFr.isNew()) {
                session.save(imtdRsContractDetailsFr);

                imtdRsContractDetailsFr.setNew(false);
            } else {
                session.merge(imtdRsContractDetailsFr);
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

        EntityCacheUtil.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsContractDetailsFrImpl.class,
            imtdRsContractDetailsFr.getPrimaryKey(), imtdRsContractDetailsFr);

        return imtdRsContractDetailsFr;
    }

    protected ImtdRsContractDetailsFr toUnwrappedModel(
        ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        if (imtdRsContractDetailsFr instanceof ImtdRsContractDetailsFrImpl) {
            return imtdRsContractDetailsFr;
        }

        ImtdRsContractDetailsFrImpl imtdRsContractDetailsFrImpl = new ImtdRsContractDetailsFrImpl();

        imtdRsContractDetailsFrImpl.setNew(imtdRsContractDetailsFr.isNew());
        imtdRsContractDetailsFrImpl.setPrimaryKey(imtdRsContractDetailsFr.getPrimaryKey());

        imtdRsContractDetailsFrImpl.setImtdRsContractDetailsFrSid(imtdRsContractDetailsFr.getImtdRsContractDetailsFrSid());
        imtdRsContractDetailsFrImpl.setFormulaMethodId(imtdRsContractDetailsFr.getFormulaMethodId());
        imtdRsContractDetailsFrImpl.setItemMasterSid(imtdRsContractDetailsFr.getItemMasterSid());
        imtdRsContractDetailsFrImpl.setRsContractDetailsFrSid(imtdRsContractDetailsFr.getRsContractDetailsFrSid());
        imtdRsContractDetailsFrImpl.setModifiedDate(imtdRsContractDetailsFr.getModifiedDate());
        imtdRsContractDetailsFrImpl.setRsContractDetailsSid(imtdRsContractDetailsFr.getRsContractDetailsSid());
        imtdRsContractDetailsFrImpl.setImtdItemPriceRebateDetailsSid(imtdRsContractDetailsFr.getImtdItemPriceRebateDetailsSid());
        imtdRsContractDetailsFrImpl.setRecordLockStatus(imtdRsContractDetailsFr.isRecordLockStatus());
        imtdRsContractDetailsFrImpl.setCreatedDate(imtdRsContractDetailsFr.getCreatedDate());
        imtdRsContractDetailsFrImpl.setCreatedBy(imtdRsContractDetailsFr.getCreatedBy());
        imtdRsContractDetailsFrImpl.setSource(imtdRsContractDetailsFr.getSource());
        imtdRsContractDetailsFrImpl.setBatchId(imtdRsContractDetailsFr.getBatchId());
        imtdRsContractDetailsFrImpl.setImtdCreatedDate(imtdRsContractDetailsFr.getImtdCreatedDate());
        imtdRsContractDetailsFrImpl.setSessionId(imtdRsContractDetailsFr.getSessionId());
        imtdRsContractDetailsFrImpl.setUsersId(imtdRsContractDetailsFr.getUsersId());
        imtdRsContractDetailsFrImpl.setOperation(imtdRsContractDetailsFr.getOperation());
        imtdRsContractDetailsFrImpl.setModifiedBy(imtdRsContractDetailsFr.getModifiedBy());
        imtdRsContractDetailsFrImpl.setFormulaId(imtdRsContractDetailsFr.getFormulaId());
        imtdRsContractDetailsFrImpl.setInboundStatus(imtdRsContractDetailsFr.getInboundStatus());

        return imtdRsContractDetailsFrImpl;
    }

    /**
     * Returns the imtd rs contract details fr with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr
     * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdRsContractDetailsFrException, SystemException {
        ImtdRsContractDetailsFr imtdRsContractDetailsFr = fetchByPrimaryKey(primaryKey);

        if (imtdRsContractDetailsFr == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdRsContractDetailsFr;
    }

    /**
     * Returns the imtd rs contract details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsContractDetailsFrException} if it could not be found.
     *
     * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr
     * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr findByPrimaryKey(
        int imtdRsContractDetailsFrSid)
        throws NoSuchImtdRsContractDetailsFrException, SystemException {
        return findByPrimaryKey((Serializable) imtdRsContractDetailsFrSid);
    }

    /**
     * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdRsContractDetailsFr imtdRsContractDetailsFr = (ImtdRsContractDetailsFr) EntityCacheUtil.getResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsContractDetailsFrImpl.class, primaryKey);

        if (imtdRsContractDetailsFr == _nullImtdRsContractDetailsFr) {
            return null;
        }

        if (imtdRsContractDetailsFr == null) {
            Session session = null;

            try {
                session = openSession();

                imtdRsContractDetailsFr = (ImtdRsContractDetailsFr) session.get(ImtdRsContractDetailsFrImpl.class,
                        primaryKey);

                if (imtdRsContractDetailsFr != null) {
                    cacheResult(imtdRsContractDetailsFr);
                } else {
                    EntityCacheUtil.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsContractDetailsFrImpl.class, primaryKey,
                        _nullImtdRsContractDetailsFr);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdRsContractDetailsFrImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdRsContractDetailsFr;
    }

    /**
     * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
     * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsContractDetailsFr fetchByPrimaryKey(
        int imtdRsContractDetailsFrSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdRsContractDetailsFrSid);
    }

    /**
     * Returns all the imtd rs contract details frs.
     *
     * @return the imtd rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsContractDetailsFr> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd rs contract details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs contract details frs
     * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
     * @return the range of imtd rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsContractDetailsFr> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd rs contract details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs contract details frs
     * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsContractDetailsFr> findAll(int start, int end,
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

        List<ImtdRsContractDetailsFr> list = (List<ImtdRsContractDetailsFr>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDRSCONTRACTDETAILSFR);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDRSCONTRACTDETAILSFR;

                if (pagination) {
                    sql = sql.concat(ImtdRsContractDetailsFrModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdRsContractDetailsFr>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdRsContractDetailsFr>(list);
                } else {
                    list = (List<ImtdRsContractDetailsFr>) QueryUtil.list(q,
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
     * Removes all the imtd rs contract details frs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : findAll()) {
            remove(imtdRsContractDetailsFr);
        }
    }

    /**
     * Returns the number of imtd rs contract details frs.
     *
     * @return the number of imtd rs contract details frs
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

                Query q = session.createQuery(_SQL_COUNT_IMTDRSCONTRACTDETAILSFR);

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
     * Initializes the imtd rs contract details fr persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdRsContractDetailsFr")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdRsContractDetailsFr>> listenersList = new ArrayList<ModelListener<ImtdRsContractDetailsFr>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdRsContractDetailsFr>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdRsContractDetailsFrImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
