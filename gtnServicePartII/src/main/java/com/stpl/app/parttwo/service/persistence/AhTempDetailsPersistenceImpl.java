package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAhTempDetailsException;
import com.stpl.app.parttwo.model.AhTempDetails;
import com.stpl.app.parttwo.model.impl.AhTempDetailsImpl;
import com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AhTempDetailsPersistence;

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
 * The persistence implementation for the ah temp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AhTempDetailsPersistence
 * @see AhTempDetailsUtil
 * @generated
 */
public class AhTempDetailsPersistenceImpl extends BasePersistenceImpl<AhTempDetails>
    implements AhTempDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AhTempDetailsUtil} to access the ah temp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AhTempDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsModelImpl.FINDER_CACHE_ENABLED,
            AhTempDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsModelImpl.FINDER_CACHE_ENABLED,
            AhTempDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_AHTEMPDETAILS = "SELECT ahTempDetails FROM AhTempDetails ahTempDetails";
    private static final String _SQL_COUNT_AHTEMPDETAILS = "SELECT COUNT(ahTempDetails) FROM AhTempDetails ahTempDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ahTempDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AhTempDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AhTempDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "contractHolder", "userId", "itemMasterSid",
                "businessUnitNo", "companyName", "itemId", "brandName",
                "componentName", "createdDate", "createdBy", "screenName",
                "businessUnitName", "companyNo", "itemIdentifierType",
                "componentNo", "sessionId", "itemName", "itemIdentifier",
                "companySid", "itemNo", "componentType", "theraputicClass",
                "componentMasterSid"
            });
    private static AhTempDetails _nullAhTempDetails = new AhTempDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AhTempDetails> toCacheModel() {
                return _nullAhTempDetailsCacheModel;
            }
        };

    private static CacheModel<AhTempDetails> _nullAhTempDetailsCacheModel = new CacheModel<AhTempDetails>() {
            @Override
            public AhTempDetails toEntityModel() {
                return _nullAhTempDetails;
            }
        };

    public AhTempDetailsPersistenceImpl() {
        setModelClass(AhTempDetails.class);
    }

    /**
     * Caches the ah temp details in the entity cache if it is enabled.
     *
     * @param ahTempDetails the ah temp details
     */
    @Override
    public void cacheResult(AhTempDetails ahTempDetails) {
        EntityCacheUtil.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey(),
            ahTempDetails);

        ahTempDetails.resetOriginalValues();
    }

    /**
     * Caches the ah temp detailses in the entity cache if it is enabled.
     *
     * @param ahTempDetailses the ah temp detailses
     */
    @Override
    public void cacheResult(List<AhTempDetails> ahTempDetailses) {
        for (AhTempDetails ahTempDetails : ahTempDetailses) {
            if (EntityCacheUtil.getResult(
                        AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey()) == null) {
                cacheResult(ahTempDetails);
            } else {
                ahTempDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ah temp detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AhTempDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AhTempDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ah temp details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AhTempDetails ahTempDetails) {
        EntityCacheUtil.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AhTempDetails> ahTempDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AhTempDetails ahTempDetails : ahTempDetailses) {
            EntityCacheUtil.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
     *
     * @param componentMasterSid the primary key for the new ah temp details
     * @return the new ah temp details
     */
    @Override
    public AhTempDetails create(int componentMasterSid) {
        AhTempDetails ahTempDetails = new AhTempDetailsImpl();

        ahTempDetails.setNew(true);
        ahTempDetails.setPrimaryKey(componentMasterSid);

        return ahTempDetails;
    }

    /**
     * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param componentMasterSid the primary key of the ah temp details
     * @return the ah temp details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails remove(int componentMasterSid)
        throws NoSuchAhTempDetailsException, SystemException {
        return remove((Serializable) componentMasterSid);
    }

    /**
     * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ah temp details
     * @return the ah temp details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails remove(Serializable primaryKey)
        throws NoSuchAhTempDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AhTempDetails ahTempDetails = (AhTempDetails) session.get(AhTempDetailsImpl.class,
                    primaryKey);

            if (ahTempDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAhTempDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ahTempDetails);
        } catch (NoSuchAhTempDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AhTempDetails removeImpl(AhTempDetails ahTempDetails)
        throws SystemException {
        ahTempDetails = toUnwrappedModel(ahTempDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ahTempDetails)) {
                ahTempDetails = (AhTempDetails) session.get(AhTempDetailsImpl.class,
                        ahTempDetails.getPrimaryKeyObj());
            }

            if (ahTempDetails != null) {
                session.delete(ahTempDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ahTempDetails != null) {
            clearCache(ahTempDetails);
        }

        return ahTempDetails;
    }

    @Override
    public AhTempDetails updateImpl(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws SystemException {
        ahTempDetails = toUnwrappedModel(ahTempDetails);

        boolean isNew = ahTempDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ahTempDetails.isNew()) {
                session.save(ahTempDetails);

                ahTempDetails.setNew(false);
            } else {
                session.merge(ahTempDetails);
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

        EntityCacheUtil.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey(),
            ahTempDetails);

        return ahTempDetails;
    }

    protected AhTempDetails toUnwrappedModel(AhTempDetails ahTempDetails) {
        if (ahTempDetails instanceof AhTempDetailsImpl) {
            return ahTempDetails;
        }

        AhTempDetailsImpl ahTempDetailsImpl = new AhTempDetailsImpl();

        ahTempDetailsImpl.setNew(ahTempDetails.isNew());
        ahTempDetailsImpl.setPrimaryKey(ahTempDetails.getPrimaryKey());

        ahTempDetailsImpl.setCheckRecord(ahTempDetails.isCheckRecord());
        ahTempDetailsImpl.setContractHolder(ahTempDetails.getContractHolder());
        ahTempDetailsImpl.setUserId(ahTempDetails.getUserId());
        ahTempDetailsImpl.setItemMasterSid(ahTempDetails.getItemMasterSid());
        ahTempDetailsImpl.setBusinessUnitNo(ahTempDetails.getBusinessUnitNo());
        ahTempDetailsImpl.setCompanyName(ahTempDetails.getCompanyName());
        ahTempDetailsImpl.setItemId(ahTempDetails.getItemId());
        ahTempDetailsImpl.setBrandName(ahTempDetails.getBrandName());
        ahTempDetailsImpl.setComponentName(ahTempDetails.getComponentName());
        ahTempDetailsImpl.setCreatedDate(ahTempDetails.getCreatedDate());
        ahTempDetailsImpl.setCreatedBy(ahTempDetails.getCreatedBy());
        ahTempDetailsImpl.setScreenName(ahTempDetails.getScreenName());
        ahTempDetailsImpl.setBusinessUnitName(ahTempDetails.getBusinessUnitName());
        ahTempDetailsImpl.setCompanyNo(ahTempDetails.getCompanyNo());
        ahTempDetailsImpl.setItemIdentifierType(ahTempDetails.getItemIdentifierType());
        ahTempDetailsImpl.setComponentNo(ahTempDetails.getComponentNo());
        ahTempDetailsImpl.setSessionId(ahTempDetails.getSessionId());
        ahTempDetailsImpl.setItemName(ahTempDetails.getItemName());
        ahTempDetailsImpl.setItemIdentifier(ahTempDetails.getItemIdentifier());
        ahTempDetailsImpl.setCompanySid(ahTempDetails.getCompanySid());
        ahTempDetailsImpl.setItemNo(ahTempDetails.getItemNo());
        ahTempDetailsImpl.setComponentType(ahTempDetails.getComponentType());
        ahTempDetailsImpl.setTheraputicClass(ahTempDetails.getTheraputicClass());
        ahTempDetailsImpl.setComponentMasterSid(ahTempDetails.getComponentMasterSid());

        return ahTempDetailsImpl;
    }

    /**
     * Returns the ah temp details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ah temp details
     * @return the ah temp details
     * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAhTempDetailsException, SystemException {
        AhTempDetails ahTempDetails = fetchByPrimaryKey(primaryKey);

        if (ahTempDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAhTempDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ahTempDetails;
    }

    /**
     * Returns the ah temp details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAhTempDetailsException} if it could not be found.
     *
     * @param componentMasterSid the primary key of the ah temp details
     * @return the ah temp details
     * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails findByPrimaryKey(int componentMasterSid)
        throws NoSuchAhTempDetailsException, SystemException {
        return findByPrimaryKey((Serializable) componentMasterSid);
    }

    /**
     * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ah temp details
     * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AhTempDetails ahTempDetails = (AhTempDetails) EntityCacheUtil.getResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AhTempDetailsImpl.class, primaryKey);

        if (ahTempDetails == _nullAhTempDetails) {
            return null;
        }

        if (ahTempDetails == null) {
            Session session = null;

            try {
                session = openSession();

                ahTempDetails = (AhTempDetails) session.get(AhTempDetailsImpl.class,
                        primaryKey);

                if (ahTempDetails != null) {
                    cacheResult(ahTempDetails);
                } else {
                    EntityCacheUtil.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AhTempDetailsImpl.class, primaryKey, _nullAhTempDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    AhTempDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ahTempDetails;
    }

    /**
     * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param componentMasterSid the primary key of the ah temp details
     * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AhTempDetails fetchByPrimaryKey(int componentMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) componentMasterSid);
    }

    /**
     * Returns all the ah temp detailses.
     *
     * @return the ah temp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AhTempDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ah temp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ah temp detailses
     * @param end the upper bound of the range of ah temp detailses (not inclusive)
     * @return the range of ah temp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AhTempDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ah temp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ah temp detailses
     * @param end the upper bound of the range of ah temp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ah temp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AhTempDetails> findAll(int start, int end,
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

        List<AhTempDetails> list = (List<AhTempDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_AHTEMPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_AHTEMPDETAILS;

                if (pagination) {
                    sql = sql.concat(AhTempDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AhTempDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AhTempDetails>(list);
                } else {
                    list = (List<AhTempDetails>) QueryUtil.list(q,
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
     * Removes all the ah temp detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AhTempDetails ahTempDetails : findAll()) {
            remove(ahTempDetails);
        }
    }

    /**
     * Returns the number of ah temp detailses.
     *
     * @return the number of ah temp detailses
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

                Query q = session.createQuery(_SQL_COUNT_AHTEMPDETAILS);

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
     * Initializes the ah temp details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AhTempDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AhTempDetails>> listenersList = new ArrayList<ModelListener<AhTempDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AhTempDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AhTempDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
