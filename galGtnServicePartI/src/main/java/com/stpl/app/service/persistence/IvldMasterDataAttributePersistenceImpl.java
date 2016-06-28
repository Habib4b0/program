package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldMasterDataAttributeException;
import com.stpl.app.model.IvldMasterDataAttribute;
import com.stpl.app.model.impl.IvldMasterDataAttributeImpl;
import com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl;
import com.stpl.app.service.persistence.IvldMasterDataAttributePersistence;

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
 * The persistence implementation for the ivld master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldMasterDataAttributePersistence
 * @see IvldMasterDataAttributeUtil
 * @generated
 */
public class IvldMasterDataAttributePersistenceImpl extends BasePersistenceImpl<IvldMasterDataAttribute>
    implements IvldMasterDataAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldMasterDataAttributeUtil} to access the ivld master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldMasterDataAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            IvldMasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            IvldMasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDMASTERDATAATTRIBUTE = "SELECT ivldMasterDataAttribute FROM IvldMasterDataAttribute ivldMasterDataAttribute";
    private static final String _SQL_COUNT_IVLDMASTERDATAATTRIBUTE = "SELECT COUNT(ivldMasterDataAttribute) FROM IvldMasterDataAttribute ivldMasterDataAttribute";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldMasterDataAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldMasterDataAttribute exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldMasterDataAttributePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "column19", "column18", "masterAttribute", "masterType",
                "dataAttributeIntfid", "modifiedDate", "source", "createdDate",
                "createdBy", "addChgDelIndicator", "masterId", "column10",
                "column11", "errorCode", "column12", "intfInsertedDate",
                "modifiedBy", "column13", "column14", "reprocessedFlag",
                "column15", "column16", "column17", "column4", "column3",
                "column2", "column1", "column8", "reasonForFailure", "column7",
                "column6", "column5", "column20", "batchId", "errorField",
                "column9", "ivldMasterDataAtbteSid"
            });
    private static IvldMasterDataAttribute _nullIvldMasterDataAttribute = new IvldMasterDataAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldMasterDataAttribute> toCacheModel() {
                return _nullIvldMasterDataAttributeCacheModel;
            }
        };

    private static CacheModel<IvldMasterDataAttribute> _nullIvldMasterDataAttributeCacheModel =
        new CacheModel<IvldMasterDataAttribute>() {
            @Override
            public IvldMasterDataAttribute toEntityModel() {
                return _nullIvldMasterDataAttribute;
            }
        };

    public IvldMasterDataAttributePersistenceImpl() {
        setModelClass(IvldMasterDataAttribute.class);
    }

    /**
     * Caches the ivld master data attribute in the entity cache if it is enabled.
     *
     * @param ivldMasterDataAttribute the ivld master data attribute
     */
    @Override
    public void cacheResult(IvldMasterDataAttribute ivldMasterDataAttribute) {
        EntityCacheUtil.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeImpl.class,
            ivldMasterDataAttribute.getPrimaryKey(), ivldMasterDataAttribute);

        ivldMasterDataAttribute.resetOriginalValues();
    }

    /**
     * Caches the ivld master data attributes in the entity cache if it is enabled.
     *
     * @param ivldMasterDataAttributes the ivld master data attributes
     */
    @Override
    public void cacheResult(
        List<IvldMasterDataAttribute> ivldMasterDataAttributes) {
        for (IvldMasterDataAttribute ivldMasterDataAttribute : ivldMasterDataAttributes) {
            if (EntityCacheUtil.getResult(
                        IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        IvldMasterDataAttributeImpl.class,
                        ivldMasterDataAttribute.getPrimaryKey()) == null) {
                cacheResult(ivldMasterDataAttribute);
            } else {
                ivldMasterDataAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld master data attributes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldMasterDataAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldMasterDataAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld master data attribute.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldMasterDataAttribute ivldMasterDataAttribute) {
        EntityCacheUtil.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeImpl.class,
            ivldMasterDataAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<IvldMasterDataAttribute> ivldMasterDataAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldMasterDataAttribute ivldMasterDataAttribute : ivldMasterDataAttributes) {
            EntityCacheUtil.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                IvldMasterDataAttributeImpl.class,
                ivldMasterDataAttribute.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
     *
     * @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
     * @return the new ivld master data attribute
     */
    @Override
    public IvldMasterDataAttribute create(int ivldMasterDataAtbteSid) {
        IvldMasterDataAttribute ivldMasterDataAttribute = new IvldMasterDataAttributeImpl();

        ivldMasterDataAttribute.setNew(true);
        ivldMasterDataAttribute.setPrimaryKey(ivldMasterDataAtbteSid);

        return ivldMasterDataAttribute;
    }

    /**
     * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
     * @return the ivld master data attribute that was removed
     * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute remove(int ivldMasterDataAtbteSid)
        throws NoSuchIvldMasterDataAttributeException, SystemException {
        return remove((Serializable) ivldMasterDataAtbteSid);
    }

    /**
     * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld master data attribute
     * @return the ivld master data attribute that was removed
     * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute remove(Serializable primaryKey)
        throws NoSuchIvldMasterDataAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldMasterDataAttribute ivldMasterDataAttribute = (IvldMasterDataAttribute) session.get(IvldMasterDataAttributeImpl.class,
                    primaryKey);

            if (ivldMasterDataAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldMasterDataAttribute);
        } catch (NoSuchIvldMasterDataAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldMasterDataAttribute removeImpl(
        IvldMasterDataAttribute ivldMasterDataAttribute)
        throws SystemException {
        ivldMasterDataAttribute = toUnwrappedModel(ivldMasterDataAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldMasterDataAttribute)) {
                ivldMasterDataAttribute = (IvldMasterDataAttribute) session.get(IvldMasterDataAttributeImpl.class,
                        ivldMasterDataAttribute.getPrimaryKeyObj());
            }

            if (ivldMasterDataAttribute != null) {
                session.delete(ivldMasterDataAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldMasterDataAttribute != null) {
            clearCache(ivldMasterDataAttribute);
        }

        return ivldMasterDataAttribute;
    }

    @Override
    public IvldMasterDataAttribute updateImpl(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws SystemException {
        ivldMasterDataAttribute = toUnwrappedModel(ivldMasterDataAttribute);

        boolean isNew = ivldMasterDataAttribute.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldMasterDataAttribute.isNew()) {
                session.save(ivldMasterDataAttribute);

                ivldMasterDataAttribute.setNew(false);
            } else {
                session.merge(ivldMasterDataAttribute);
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

        EntityCacheUtil.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            IvldMasterDataAttributeImpl.class,
            ivldMasterDataAttribute.getPrimaryKey(), ivldMasterDataAttribute);

        return ivldMasterDataAttribute;
    }

    protected IvldMasterDataAttribute toUnwrappedModel(
        IvldMasterDataAttribute ivldMasterDataAttribute) {
        if (ivldMasterDataAttribute instanceof IvldMasterDataAttributeImpl) {
            return ivldMasterDataAttribute;
        }

        IvldMasterDataAttributeImpl ivldMasterDataAttributeImpl = new IvldMasterDataAttributeImpl();

        ivldMasterDataAttributeImpl.setNew(ivldMasterDataAttribute.isNew());
        ivldMasterDataAttributeImpl.setPrimaryKey(ivldMasterDataAttribute.getPrimaryKey());

        ivldMasterDataAttributeImpl.setColumn19(ivldMasterDataAttribute.getColumn19());
        ivldMasterDataAttributeImpl.setColumn18(ivldMasterDataAttribute.getColumn18());
        ivldMasterDataAttributeImpl.setMasterAttribute(ivldMasterDataAttribute.getMasterAttribute());
        ivldMasterDataAttributeImpl.setMasterType(ivldMasterDataAttribute.getMasterType());
        ivldMasterDataAttributeImpl.setDataAttributeIntfid(ivldMasterDataAttribute.getDataAttributeIntfid());
        ivldMasterDataAttributeImpl.setModifiedDate(ivldMasterDataAttribute.getModifiedDate());
        ivldMasterDataAttributeImpl.setSource(ivldMasterDataAttribute.getSource());
        ivldMasterDataAttributeImpl.setCreatedDate(ivldMasterDataAttribute.getCreatedDate());
        ivldMasterDataAttributeImpl.setCreatedBy(ivldMasterDataAttribute.getCreatedBy());
        ivldMasterDataAttributeImpl.setAddChgDelIndicator(ivldMasterDataAttribute.getAddChgDelIndicator());
        ivldMasterDataAttributeImpl.setMasterId(ivldMasterDataAttribute.getMasterId());
        ivldMasterDataAttributeImpl.setColumn10(ivldMasterDataAttribute.getColumn10());
        ivldMasterDataAttributeImpl.setColumn11(ivldMasterDataAttribute.getColumn11());
        ivldMasterDataAttributeImpl.setErrorCode(ivldMasterDataAttribute.getErrorCode());
        ivldMasterDataAttributeImpl.setColumn12(ivldMasterDataAttribute.getColumn12());
        ivldMasterDataAttributeImpl.setIntfInsertedDate(ivldMasterDataAttribute.getIntfInsertedDate());
        ivldMasterDataAttributeImpl.setModifiedBy(ivldMasterDataAttribute.getModifiedBy());
        ivldMasterDataAttributeImpl.setColumn13(ivldMasterDataAttribute.getColumn13());
        ivldMasterDataAttributeImpl.setColumn14(ivldMasterDataAttribute.getColumn14());
        ivldMasterDataAttributeImpl.setReprocessedFlag(ivldMasterDataAttribute.getReprocessedFlag());
        ivldMasterDataAttributeImpl.setColumn15(ivldMasterDataAttribute.getColumn15());
        ivldMasterDataAttributeImpl.setColumn16(ivldMasterDataAttribute.getColumn16());
        ivldMasterDataAttributeImpl.setColumn17(ivldMasterDataAttribute.getColumn17());
        ivldMasterDataAttributeImpl.setColumn4(ivldMasterDataAttribute.getColumn4());
        ivldMasterDataAttributeImpl.setColumn3(ivldMasterDataAttribute.getColumn3());
        ivldMasterDataAttributeImpl.setColumn2(ivldMasterDataAttribute.getColumn2());
        ivldMasterDataAttributeImpl.setColumn1(ivldMasterDataAttribute.getColumn1());
        ivldMasterDataAttributeImpl.setColumn8(ivldMasterDataAttribute.getColumn8());
        ivldMasterDataAttributeImpl.setReasonForFailure(ivldMasterDataAttribute.getReasonForFailure());
        ivldMasterDataAttributeImpl.setColumn7(ivldMasterDataAttribute.getColumn7());
        ivldMasterDataAttributeImpl.setColumn6(ivldMasterDataAttribute.getColumn6());
        ivldMasterDataAttributeImpl.setColumn5(ivldMasterDataAttribute.getColumn5());
        ivldMasterDataAttributeImpl.setColumn20(ivldMasterDataAttribute.getColumn20());
        ivldMasterDataAttributeImpl.setBatchId(ivldMasterDataAttribute.getBatchId());
        ivldMasterDataAttributeImpl.setErrorField(ivldMasterDataAttribute.getErrorField());
        ivldMasterDataAttributeImpl.setColumn9(ivldMasterDataAttribute.getColumn9());
        ivldMasterDataAttributeImpl.setIvldMasterDataAtbteSid(ivldMasterDataAttribute.getIvldMasterDataAtbteSid());

        return ivldMasterDataAttributeImpl;
    }

    /**
     * Returns the ivld master data attribute with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld master data attribute
     * @return the ivld master data attribute
     * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldMasterDataAttributeException, SystemException {
        IvldMasterDataAttribute ivldMasterDataAttribute = fetchByPrimaryKey(primaryKey);

        if (ivldMasterDataAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldMasterDataAttribute;
    }

    /**
     * Returns the ivld master data attribute with the primary key or throws a {@link com.stpl.app.NoSuchIvldMasterDataAttributeException} if it could not be found.
     *
     * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
     * @return the ivld master data attribute
     * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute findByPrimaryKey(int ivldMasterDataAtbteSid)
        throws NoSuchIvldMasterDataAttributeException, SystemException {
        return findByPrimaryKey((Serializable) ivldMasterDataAtbteSid);
    }

    /**
     * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld master data attribute
     * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldMasterDataAttribute ivldMasterDataAttribute = (IvldMasterDataAttribute) EntityCacheUtil.getResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                IvldMasterDataAttributeImpl.class, primaryKey);

        if (ivldMasterDataAttribute == _nullIvldMasterDataAttribute) {
            return null;
        }

        if (ivldMasterDataAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                ivldMasterDataAttribute = (IvldMasterDataAttribute) session.get(IvldMasterDataAttributeImpl.class,
                        primaryKey);

                if (ivldMasterDataAttribute != null) {
                    cacheResult(ivldMasterDataAttribute);
                } else {
                    EntityCacheUtil.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        IvldMasterDataAttributeImpl.class, primaryKey,
                        _nullIvldMasterDataAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    IvldMasterDataAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldMasterDataAttribute;
    }

    /**
     * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
     * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldMasterDataAttribute fetchByPrimaryKey(int ivldMasterDataAtbteSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldMasterDataAtbteSid);
    }

    /**
     * Returns all the ivld master data attributes.
     *
     * @return the ivld master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldMasterDataAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld master data attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld master data attributes
     * @param end the upper bound of the range of ivld master data attributes (not inclusive)
     * @return the range of ivld master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldMasterDataAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld master data attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld master data attributes
     * @param end the upper bound of the range of ivld master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldMasterDataAttribute> findAll(int start, int end,
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

        List<IvldMasterDataAttribute> list = (List<IvldMasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDMASTERDATAATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDMASTERDATAATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(IvldMasterDataAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldMasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldMasterDataAttribute>(list);
                } else {
                    list = (List<IvldMasterDataAttribute>) QueryUtil.list(q,
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
     * Removes all the ivld master data attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldMasterDataAttribute ivldMasterDataAttribute : findAll()) {
            remove(ivldMasterDataAttribute);
        }
    }

    /**
     * Returns the number of ivld master data attributes.
     *
     * @return the number of ivld master data attributes
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

                Query q = session.createQuery(_SQL_COUNT_IVLDMASTERDATAATTRIBUTE);

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
     * Initializes the ivld master data attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldMasterDataAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldMasterDataAttribute>> listenersList = new ArrayList<ModelListener<IvldMasterDataAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldMasterDataAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldMasterDataAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
