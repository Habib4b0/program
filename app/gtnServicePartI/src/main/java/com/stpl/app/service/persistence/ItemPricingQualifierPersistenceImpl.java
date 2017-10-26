package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemPricingQualifierException;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.impl.ItemPricingQualifierImpl;
import com.stpl.app.model.impl.ItemPricingQualifierModelImpl;
import com.stpl.app.service.persistence.ItemPricingQualifierPersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.QueryPos;
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
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.kernel.util.StringUtil;
import com.stpl.portal.kernel.util.UnmodifiableList;
import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the item pricing qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingQualifierPersistence
 * @see ItemPricingQualifierUtil
 * @generated
 */
public class ItemPricingQualifierPersistenceImpl extends BasePersistenceImpl<ItemPricingQualifier>
    implements ItemPricingQualifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemPricingQualifierUtil} to access the item pricing qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemPricingQualifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemPricingQualifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemPricingQualifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME =
        new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemPricingQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByitemPricingCodeQualifierByName",
            new String[] { String.class.getName() },
            ItemPricingQualifierModelImpl.ITEMPRICINGQUALIFIERNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME =
        new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByitemPricingCodeQualifierByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1 =
        "itemPricingQualifier.itemPricingQualifierName IS NULL";
    private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2 =
        "itemPricingQualifier.itemPricingQualifierName = ?";
    private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3 =
        "(itemPricingQualifier.itemPricingQualifierName IS NULL OR itemPricingQualifier.itemPricingQualifierName = '')";
    private static final String _SQL_SELECT_ITEMPRICINGQUALIFIER = "SELECT itemPricingQualifier FROM ItemPricingQualifier itemPricingQualifier";
    private static final String _SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE = "SELECT itemPricingQualifier FROM ItemPricingQualifier itemPricingQualifier WHERE ";
    private static final String _SQL_COUNT_ITEMPRICINGQUALIFIER = "SELECT COUNT(itemPricingQualifier) FROM ItemPricingQualifier itemPricingQualifier";
    private static final String _SQL_COUNT_ITEMPRICINGQUALIFIER_WHERE = "SELECT COUNT(itemPricingQualifier) FROM ItemPricingQualifier itemPricingQualifier WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemPricingQualifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemPricingQualifier exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemPricingQualifier exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemPricingQualifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "itemPricingQualifierSid", "specificEntityCode",
                "modifiedBy", "createdDate", "batchId", "modifiedDate",
                "effectiveDates", "notes", "recordLockStatus", "source",
                "pricingQualifier", "itemPricingQualifierName", "inboundStatus"
            });
    private static ItemPricingQualifier _nullItemPricingQualifier = new ItemPricingQualifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemPricingQualifier> toCacheModel() {
                return _nullItemPricingQualifierCacheModel;
            }
        };

    private static CacheModel<ItemPricingQualifier> _nullItemPricingQualifierCacheModel =
        new CacheModel<ItemPricingQualifier>() {
            @Override
            public ItemPricingQualifier toEntityModel() {
                return _nullItemPricingQualifier;
            }
        };

    public ItemPricingQualifierPersistenceImpl() {
        setModelClass(ItemPricingQualifier.class);
    }

    /**
     * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchItemPricingQualifierException} if it could not be found.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     * @return the matching item pricing qualifier
     * @throws com.stpl.app.NoSuchItemPricingQualifierException if a matching item pricing qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier findByitemPricingCodeQualifierByName(
        String itemPricingQualifierName)
        throws NoSuchItemPricingQualifierException, SystemException {
        ItemPricingQualifier itemPricingQualifier = fetchByitemPricingCodeQualifierByName(itemPricingQualifierName);

        if (itemPricingQualifier == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("itemPricingQualifierName=");
            msg.append(itemPricingQualifierName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchItemPricingQualifierException(msg.toString());
        }

        return itemPricingQualifier;
    }

    /**
     * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
        String itemPricingQualifierName) throws SystemException {
        return fetchByitemPricingCodeQualifierByName(itemPricingQualifierName,
            true);
    }

    /**
     * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
        String itemPricingQualifierName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { itemPricingQualifierName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                    finderArgs, this);
        }

        if (result instanceof ItemPricingQualifier) {
            ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier) result;

            if (!Validator.equals(itemPricingQualifierName,
                        itemPricingQualifier.getItemPricingQualifierName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE);

            boolean bindItemPricingQualifierName = false;

            if (itemPricingQualifierName == null) {
                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1);
            } else if (itemPricingQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3);
            } else {
                bindItemPricingQualifierName = true;

                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemPricingQualifierName) {
                    qPos.add(itemPricingQualifierName);
                }

                List<ItemPricingQualifier> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ItemPricingQualifierPersistenceImpl.fetchByitemPricingCodeQualifierByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ItemPricingQualifier itemPricingQualifier = list.get(0);

                    result = itemPricingQualifier;

                    cacheResult(itemPricingQualifier);

                    if ((itemPricingQualifier.getItemPricingQualifierName() == null) ||
                            !itemPricingQualifier.getItemPricingQualifierName()
                                                     .equals(itemPricingQualifierName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                            finderArgs, itemPricingQualifier);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ItemPricingQualifier) result;
        }
    }

    /**
     * Removes the item pricing qualifier where itemPricingQualifierName = &#63; from the database.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     * @return the item pricing qualifier that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier removeByitemPricingCodeQualifierByName(
        String itemPricingQualifierName)
        throws NoSuchItemPricingQualifierException, SystemException {
        ItemPricingQualifier itemPricingQualifier = findByitemPricingCodeQualifierByName(itemPricingQualifierName);

        return remove(itemPricingQualifier);
    }

    /**
     * Returns the number of item pricing qualifiers where itemPricingQualifierName = &#63;.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     * @return the number of matching item pricing qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByitemPricingCodeQualifierByName(
        String itemPricingQualifierName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME;

        Object[] finderArgs = new Object[] { itemPricingQualifierName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMPRICINGQUALIFIER_WHERE);

            boolean bindItemPricingQualifierName = false;

            if (itemPricingQualifierName == null) {
                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1);
            } else if (itemPricingQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3);
            } else {
                bindItemPricingQualifierName = true;

                query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemPricingQualifierName) {
                    qPos.add(itemPricingQualifierName);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the item pricing qualifier in the entity cache if it is enabled.
     *
     * @param itemPricingQualifier the item pricing qualifier
     */
    @Override
    public void cacheResult(ItemPricingQualifier itemPricingQualifier) {
        EntityCacheUtil.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierImpl.class,
            itemPricingQualifier.getPrimaryKey(), itemPricingQualifier);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
            new Object[] { itemPricingQualifier.getItemPricingQualifierName() },
            itemPricingQualifier);

        itemPricingQualifier.resetOriginalValues();
    }

    /**
     * Caches the item pricing qualifiers in the entity cache if it is enabled.
     *
     * @param itemPricingQualifiers the item pricing qualifiers
     */
    @Override
    public void cacheResult(List<ItemPricingQualifier> itemPricingQualifiers) {
        for (ItemPricingQualifier itemPricingQualifier : itemPricingQualifiers) {
            if (EntityCacheUtil.getResult(
                        ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemPricingQualifierImpl.class,
                        itemPricingQualifier.getPrimaryKey()) == null) {
                cacheResult(itemPricingQualifier);
            } else {
                itemPricingQualifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item pricing qualifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemPricingQualifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemPricingQualifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item pricing qualifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemPricingQualifier itemPricingQualifier) {
        EntityCacheUtil.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierImpl.class, itemPricingQualifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(itemPricingQualifier);
    }

    @Override
    public void clearCache(List<ItemPricingQualifier> itemPricingQualifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemPricingQualifier itemPricingQualifier : itemPricingQualifiers) {
            EntityCacheUtil.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemPricingQualifierImpl.class,
                itemPricingQualifier.getPrimaryKey());

            clearUniqueFindersCache(itemPricingQualifier);
        }
    }

    protected void cacheUniqueFindersCache(
        ItemPricingQualifier itemPricingQualifier) {
        if (itemPricingQualifier.isNew()) {
            Object[] args = new Object[] {
                    itemPricingQualifier.getItemPricingQualifierName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                args, itemPricingQualifier);
        } else {
            ItemPricingQualifierModelImpl itemPricingQualifierModelImpl = (ItemPricingQualifierModelImpl) itemPricingQualifier;

            if ((itemPricingQualifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemPricingQualifier.getItemPricingQualifierName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                    args, itemPricingQualifier);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ItemPricingQualifier itemPricingQualifier) {
        ItemPricingQualifierModelImpl itemPricingQualifierModelImpl = (ItemPricingQualifierModelImpl) itemPricingQualifier;

        Object[] args = new Object[] {
                itemPricingQualifier.getItemPricingQualifierName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
            args);

        if ((itemPricingQualifierModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    itemPricingQualifierModelImpl.getOriginalItemPricingQualifierName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
                args);
        }
    }

    /**
     * Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
     *
     * @param itemPricingQualifierSid the primary key for the new item pricing qualifier
     * @return the new item pricing qualifier
     */
    @Override
    public ItemPricingQualifier create(int itemPricingQualifierSid) {
        ItemPricingQualifier itemPricingQualifier = new ItemPricingQualifierImpl();

        itemPricingQualifier.setNew(true);
        itemPricingQualifier.setPrimaryKey(itemPricingQualifierSid);

        return itemPricingQualifier;
    }

    /**
     * Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemPricingQualifierSid the primary key of the item pricing qualifier
     * @return the item pricing qualifier that was removed
     * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier remove(int itemPricingQualifierSid)
        throws NoSuchItemPricingQualifierException, SystemException {
        return remove((Serializable) itemPricingQualifierSid);
    }

    /**
     * Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item pricing qualifier
     * @return the item pricing qualifier that was removed
     * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier remove(Serializable primaryKey)
        throws NoSuchItemPricingQualifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier) session.get(ItemPricingQualifierImpl.class,
                    primaryKey);

            if (itemPricingQualifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemPricingQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemPricingQualifier);
        } catch (NoSuchItemPricingQualifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemPricingQualifier removeImpl(
        ItemPricingQualifier itemPricingQualifier) throws SystemException {
        itemPricingQualifier = toUnwrappedModel(itemPricingQualifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemPricingQualifier)) {
                itemPricingQualifier = (ItemPricingQualifier) session.get(ItemPricingQualifierImpl.class,
                        itemPricingQualifier.getPrimaryKeyObj());
            }

            if (itemPricingQualifier != null) {
                session.delete(itemPricingQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemPricingQualifier != null) {
            clearCache(itemPricingQualifier);
        }

        return itemPricingQualifier;
    }

    @Override
    public ItemPricingQualifier updateImpl(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier)
        throws SystemException {
        itemPricingQualifier = toUnwrappedModel(itemPricingQualifier);

        boolean isNew = itemPricingQualifier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (itemPricingQualifier.isNew()) {
                session.save(itemPricingQualifier);

                itemPricingQualifier.setNew(false);
            } else {
                session.merge(itemPricingQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemPricingQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingQualifierImpl.class,
            itemPricingQualifier.getPrimaryKey(), itemPricingQualifier);

        clearUniqueFindersCache(itemPricingQualifier);
        cacheUniqueFindersCache(itemPricingQualifier);

        return itemPricingQualifier;
    }

    protected ItemPricingQualifier toUnwrappedModel(
        ItemPricingQualifier itemPricingQualifier) {
        if (itemPricingQualifier instanceof ItemPricingQualifierImpl) {
            return itemPricingQualifier;
        }

        ItemPricingQualifierImpl itemPricingQualifierImpl = new ItemPricingQualifierImpl();

        itemPricingQualifierImpl.setNew(itemPricingQualifier.isNew());
        itemPricingQualifierImpl.setPrimaryKey(itemPricingQualifier.getPrimaryKey());

        itemPricingQualifierImpl.setCreatedBy(itemPricingQualifier.getCreatedBy());
        itemPricingQualifierImpl.setItemPricingQualifierSid(itemPricingQualifier.getItemPricingQualifierSid());
        itemPricingQualifierImpl.setSpecificEntityCode(itemPricingQualifier.getSpecificEntityCode());
        itemPricingQualifierImpl.setModifiedBy(itemPricingQualifier.getModifiedBy());
        itemPricingQualifierImpl.setCreatedDate(itemPricingQualifier.getCreatedDate());
        itemPricingQualifierImpl.setBatchId(itemPricingQualifier.getBatchId());
        itemPricingQualifierImpl.setModifiedDate(itemPricingQualifier.getModifiedDate());
        itemPricingQualifierImpl.setEffectiveDates(itemPricingQualifier.getEffectiveDates());
        itemPricingQualifierImpl.setNotes(itemPricingQualifier.getNotes());
        itemPricingQualifierImpl.setRecordLockStatus(itemPricingQualifier.isRecordLockStatus());
        itemPricingQualifierImpl.setSource(itemPricingQualifier.getSource());
        itemPricingQualifierImpl.setPricingQualifier(itemPricingQualifier.getPricingQualifier());
        itemPricingQualifierImpl.setItemPricingQualifierName(itemPricingQualifier.getItemPricingQualifierName());
        itemPricingQualifierImpl.setInboundStatus(itemPricingQualifier.getInboundStatus());

        return itemPricingQualifierImpl;
    }

    /**
     * Returns the item pricing qualifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item pricing qualifier
     * @return the item pricing qualifier
     * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemPricingQualifierException, SystemException {
        ItemPricingQualifier itemPricingQualifier = fetchByPrimaryKey(primaryKey);

        if (itemPricingQualifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemPricingQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemPricingQualifier;
    }

    /**
     * Returns the item pricing qualifier with the primary key or throws a {@link com.stpl.app.NoSuchItemPricingQualifierException} if it could not be found.
     *
     * @param itemPricingQualifierSid the primary key of the item pricing qualifier
     * @return the item pricing qualifier
     * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier findByPrimaryKey(int itemPricingQualifierSid)
        throws NoSuchItemPricingQualifierException, SystemException {
        return findByPrimaryKey((Serializable) itemPricingQualifierSid);
    }

    /**
     * Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item pricing qualifier
     * @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier) EntityCacheUtil.getResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemPricingQualifierImpl.class, primaryKey);

        if (itemPricingQualifier == _nullItemPricingQualifier) {
            return null;
        }

        if (itemPricingQualifier == null) {
            Session session = null;

            try {
                session = openSession();

                itemPricingQualifier = (ItemPricingQualifier) session.get(ItemPricingQualifierImpl.class,
                        primaryKey);

                if (itemPricingQualifier != null) {
                    cacheResult(itemPricingQualifier);
                } else {
                    EntityCacheUtil.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemPricingQualifierImpl.class, primaryKey,
                        _nullItemPricingQualifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
                    ItemPricingQualifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemPricingQualifier;
    }

    /**
     * Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemPricingQualifierSid the primary key of the item pricing qualifier
     * @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricingQualifier fetchByPrimaryKey(int itemPricingQualifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemPricingQualifierSid);
    }

    /**
     * Returns all the item pricing qualifiers.
     *
     * @return the item pricing qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricingQualifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item pricing qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item pricing qualifiers
     * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
     * @return the range of item pricing qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricingQualifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item pricing qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item pricing qualifiers
     * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item pricing qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricingQualifier> findAll(int start, int end,
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

        List<ItemPricingQualifier> list = (List<ItemPricingQualifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMPRICINGQUALIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMPRICINGQUALIFIER;

                if (pagination) {
                    sql = sql.concat(ItemPricingQualifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemPricingQualifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemPricingQualifier>(list);
                } else {
                    list = (List<ItemPricingQualifier>) QueryUtil.list(q,
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
     * Removes all the item pricing qualifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemPricingQualifier itemPricingQualifier : findAll()) {
            remove(itemPricingQualifier);
        }
    }

    /**
     * Returns the number of item pricing qualifiers.
     *
     * @return the number of item pricing qualifiers
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

                Query q = session.createQuery(_SQL_COUNT_ITEMPRICINGQUALIFIER);

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
     * Initializes the item pricing qualifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemPricingQualifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemPricingQualifier>> listenersList = new ArrayList<ModelListener<ItemPricingQualifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemPricingQualifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemPricingQualifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
