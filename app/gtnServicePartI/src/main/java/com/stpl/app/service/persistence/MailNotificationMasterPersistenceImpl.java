package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMailNotificationMasterException;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.model.impl.MailNotificationMasterImpl;
import com.stpl.app.model.impl.MailNotificationMasterModelImpl;
import com.stpl.app.service.persistence.MailNotificationMasterPersistence;

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
 * The persistence implementation for the mail notification master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MailNotificationMasterPersistence
 * @see MailNotificationMasterUtil
 * @generated
 */
public class MailNotificationMasterPersistenceImpl extends BasePersistenceImpl<MailNotificationMaster>
    implements MailNotificationMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MailNotificationMasterUtil} to access the mail notification master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MailNotificationMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED,
            MailNotificationMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED,
            MailNotificationMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MAILNOTIFICATIONMASTER = "SELECT mailNotificationMaster FROM MailNotificationMaster mailNotificationMaster";
    private static final String _SQL_COUNT_MAILNOTIFICATIONMASTER = "SELECT COUNT(mailNotificationMaster) FROM MailNotificationMaster mailNotificationMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mailNotificationMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MailNotificationMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MailNotificationMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "subject", "createdDate", "createdBy", "toMailIds",
                "notificationCategoryId", "notificationModule", "body",
                "fromMailId", "ccMailIds", "versionNo", "modifiedBy",
                "modifiedDate", "mailNotificationSid"
            });
    private static MailNotificationMaster _nullMailNotificationMaster = new MailNotificationMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MailNotificationMaster> toCacheModel() {
                return _nullMailNotificationMasterCacheModel;
            }
        };

    private static CacheModel<MailNotificationMaster> _nullMailNotificationMasterCacheModel =
        new CacheModel<MailNotificationMaster>() {
            @Override
            public MailNotificationMaster toEntityModel() {
                return _nullMailNotificationMaster;
            }
        };

    public MailNotificationMasterPersistenceImpl() {
        setModelClass(MailNotificationMaster.class);
    }

    /**
     * Caches the mail notification master in the entity cache if it is enabled.
     *
     * @param mailNotificationMaster the mail notification master
     */
    @Override
    public void cacheResult(MailNotificationMaster mailNotificationMaster) {
        EntityCacheUtil.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterImpl.class,
            mailNotificationMaster.getPrimaryKey(), mailNotificationMaster);

        mailNotificationMaster.resetOriginalValues();
    }

    /**
     * Caches the mail notification masters in the entity cache if it is enabled.
     *
     * @param mailNotificationMasters the mail notification masters
     */
    @Override
    public void cacheResult(
        List<MailNotificationMaster> mailNotificationMasters) {
        for (MailNotificationMaster mailNotificationMaster : mailNotificationMasters) {
            if (EntityCacheUtil.getResult(
                        MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MailNotificationMasterImpl.class,
                        mailNotificationMaster.getPrimaryKey()) == null) {
                cacheResult(mailNotificationMaster);
            } else {
                mailNotificationMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all mail notification masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MailNotificationMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MailNotificationMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the mail notification master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MailNotificationMaster mailNotificationMaster) {
        EntityCacheUtil.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterImpl.class,
            mailNotificationMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MailNotificationMaster> mailNotificationMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MailNotificationMaster mailNotificationMaster : mailNotificationMasters) {
            EntityCacheUtil.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
                MailNotificationMasterImpl.class,
                mailNotificationMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
     *
     * @param mailNotificationSid the primary key for the new mail notification master
     * @return the new mail notification master
     */
    @Override
    public MailNotificationMaster create(int mailNotificationSid) {
        MailNotificationMaster mailNotificationMaster = new MailNotificationMasterImpl();

        mailNotificationMaster.setNew(true);
        mailNotificationMaster.setPrimaryKey(mailNotificationSid);

        return mailNotificationMaster;
    }

    /**
     * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param mailNotificationSid the primary key of the mail notification master
     * @return the mail notification master that was removed
     * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster remove(int mailNotificationSid)
        throws NoSuchMailNotificationMasterException, SystemException {
        return remove((Serializable) mailNotificationSid);
    }

    /**
     * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the mail notification master
     * @return the mail notification master that was removed
     * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster remove(Serializable primaryKey)
        throws NoSuchMailNotificationMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MailNotificationMaster mailNotificationMaster = (MailNotificationMaster) session.get(MailNotificationMasterImpl.class,
                    primaryKey);

            if (mailNotificationMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMailNotificationMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mailNotificationMaster);
        } catch (NoSuchMailNotificationMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MailNotificationMaster removeImpl(
        MailNotificationMaster mailNotificationMaster)
        throws SystemException {
        mailNotificationMaster = toUnwrappedModel(mailNotificationMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mailNotificationMaster)) {
                mailNotificationMaster = (MailNotificationMaster) session.get(MailNotificationMasterImpl.class,
                        mailNotificationMaster.getPrimaryKeyObj());
            }

            if (mailNotificationMaster != null) {
                session.delete(mailNotificationMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mailNotificationMaster != null) {
            clearCache(mailNotificationMaster);
        }

        return mailNotificationMaster;
    }

    @Override
    public MailNotificationMaster updateImpl(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws SystemException {
        mailNotificationMaster = toUnwrappedModel(mailNotificationMaster);

        boolean isNew = mailNotificationMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mailNotificationMaster.isNew()) {
                session.save(mailNotificationMaster);

                mailNotificationMaster.setNew(false);
            } else {
                session.merge(mailNotificationMaster);
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

        EntityCacheUtil.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
            MailNotificationMasterImpl.class,
            mailNotificationMaster.getPrimaryKey(), mailNotificationMaster);

        return mailNotificationMaster;
    }

    protected MailNotificationMaster toUnwrappedModel(
        MailNotificationMaster mailNotificationMaster) {
        if (mailNotificationMaster instanceof MailNotificationMasterImpl) {
            return mailNotificationMaster;
        }

        MailNotificationMasterImpl mailNotificationMasterImpl = new MailNotificationMasterImpl();

        mailNotificationMasterImpl.setNew(mailNotificationMaster.isNew());
        mailNotificationMasterImpl.setPrimaryKey(mailNotificationMaster.getPrimaryKey());

        mailNotificationMasterImpl.setSubject(mailNotificationMaster.getSubject());
        mailNotificationMasterImpl.setCreatedDate(mailNotificationMaster.getCreatedDate());
        mailNotificationMasterImpl.setCreatedBy(mailNotificationMaster.getCreatedBy());
        mailNotificationMasterImpl.setToMailIds(mailNotificationMaster.getToMailIds());
        mailNotificationMasterImpl.setNotificationCategoryId(mailNotificationMaster.getNotificationCategoryId());
        mailNotificationMasterImpl.setNotificationModule(mailNotificationMaster.getNotificationModule());
        mailNotificationMasterImpl.setBody(mailNotificationMaster.getBody());
        mailNotificationMasterImpl.setFromMailId(mailNotificationMaster.getFromMailId());
        mailNotificationMasterImpl.setCcMailIds(mailNotificationMaster.getCcMailIds());
        mailNotificationMasterImpl.setVersionNo(mailNotificationMaster.getVersionNo());
        mailNotificationMasterImpl.setModifiedBy(mailNotificationMaster.getModifiedBy());
        mailNotificationMasterImpl.setModifiedDate(mailNotificationMaster.getModifiedDate());
        mailNotificationMasterImpl.setMailNotificationSid(mailNotificationMaster.getMailNotificationSid());

        return mailNotificationMasterImpl;
    }

    /**
     * Returns the mail notification master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the mail notification master
     * @return the mail notification master
     * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMailNotificationMasterException, SystemException {
        MailNotificationMaster mailNotificationMaster = fetchByPrimaryKey(primaryKey);

        if (mailNotificationMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMailNotificationMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mailNotificationMaster;
    }

    /**
     * Returns the mail notification master with the primary key or throws a {@link com.stpl.app.NoSuchMailNotificationMasterException} if it could not be found.
     *
     * @param mailNotificationSid the primary key of the mail notification master
     * @return the mail notification master
     * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster findByPrimaryKey(int mailNotificationSid)
        throws NoSuchMailNotificationMasterException, SystemException {
        return findByPrimaryKey((Serializable) mailNotificationSid);
    }

    /**
     * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the mail notification master
     * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MailNotificationMaster mailNotificationMaster = (MailNotificationMaster) EntityCacheUtil.getResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
                MailNotificationMasterImpl.class, primaryKey);

        if (mailNotificationMaster == _nullMailNotificationMaster) {
            return null;
        }

        if (mailNotificationMaster == null) {
            Session session = null;

            try {
                session = openSession();

                mailNotificationMaster = (MailNotificationMaster) session.get(MailNotificationMasterImpl.class,
                        primaryKey);

                if (mailNotificationMaster != null) {
                    cacheResult(mailNotificationMaster);
                } else {
                    EntityCacheUtil.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MailNotificationMasterImpl.class, primaryKey,
                        _nullMailNotificationMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
                    MailNotificationMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mailNotificationMaster;
    }

    /**
     * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param mailNotificationSid the primary key of the mail notification master
     * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MailNotificationMaster fetchByPrimaryKey(int mailNotificationSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) mailNotificationSid);
    }

    /**
     * Returns all the mail notification masters.
     *
     * @return the mail notification masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MailNotificationMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the mail notification masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of mail notification masters
     * @param end the upper bound of the range of mail notification masters (not inclusive)
     * @return the range of mail notification masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MailNotificationMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the mail notification masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of mail notification masters
     * @param end the upper bound of the range of mail notification masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of mail notification masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MailNotificationMaster> findAll(int start, int end,
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

        List<MailNotificationMaster> list = (List<MailNotificationMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MAILNOTIFICATIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MAILNOTIFICATIONMASTER;

                if (pagination) {
                    sql = sql.concat(MailNotificationMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MailNotificationMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MailNotificationMaster>(list);
                } else {
                    list = (List<MailNotificationMaster>) QueryUtil.list(q,
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
     * Removes all the mail notification masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MailNotificationMaster mailNotificationMaster : findAll()) {
            remove(mailNotificationMaster);
        }
    }

    /**
     * Returns the number of mail notification masters.
     *
     * @return the number of mail notification masters
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

                Query q = session.createQuery(_SQL_COUNT_MAILNOTIFICATIONMASTER);

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
     * Initializes the mail notification master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MailNotificationMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MailNotificationMaster>> listenersList = new ArrayList<ModelListener<MailNotificationMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MailNotificationMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MailNotificationMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
