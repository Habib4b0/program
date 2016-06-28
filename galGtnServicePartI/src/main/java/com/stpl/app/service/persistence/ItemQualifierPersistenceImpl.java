package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemQualifierException;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.model.impl.ItemQualifierImpl;
import com.stpl.app.model.impl.ItemQualifierModelImpl;
import com.stpl.app.service.persistence.ItemQualifierPersistence;

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
 * The persistence implementation for the item qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemQualifierPersistence
 * @see ItemQualifierUtil
 * @generated
 */
public class ItemQualifierPersistenceImpl extends BasePersistenceImpl<ItemQualifier>
    implements ItemQualifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemQualifierUtil} to access the item qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemQualifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME =
        new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemIrtQualifierName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME =
        new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemIrtQualifierName",
            new String[] { String.class.getName() },
            ItemQualifierModelImpl.ITEMQUALIFIERVALUE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemIrtQualifierName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1 =
        "itemQualifier.itemQualifierValue IS NULL";
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2 =
        "itemQualifier.itemQualifierValue = ?";
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3 =
        "(itemQualifier.itemQualifierValue IS NULL OR itemQualifier.itemQualifierValue = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
            ItemQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByItemIrtQualifierByName",
            new String[] { String.class.getName() },
            ItemQualifierModelImpl.ITEMQUALIFIERNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemIrtQualifierByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1 =
        "itemQualifier.itemQualifierName IS NULL";
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2 =
        "itemQualifier.itemQualifierName = ?";
    private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3 =
        "(itemQualifier.itemQualifierName IS NULL OR itemQualifier.itemQualifierName = '')";
    private static final String _SQL_SELECT_ITEMQUALIFIER = "SELECT itemQualifier FROM ItemQualifier itemQualifier";
    private static final String _SQL_SELECT_ITEMQUALIFIER_WHERE = "SELECT itemQualifier FROM ItemQualifier itemQualifier WHERE ";
    private static final String _SQL_COUNT_ITEMQUALIFIER = "SELECT COUNT(itemQualifier) FROM ItemQualifier itemQualifier";
    private static final String _SQL_COUNT_ITEMQUALIFIER_WHERE = "SELECT COUNT(itemQualifier) FROM ItemQualifier itemQualifier WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemQualifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemQualifier exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemQualifier exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemQualifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "itemQualifierSid", "specificEntityCode",
                "itemQualifierName", "modifiedBy", "createdDate", "batchId",
                "modifiedDate", "effectiveDates", "notes", "itemQualifierValue",
                "recordLockStatus", "source", "inboundStatus"
            });
    private static ItemQualifier _nullItemQualifier = new ItemQualifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemQualifier> toCacheModel() {
                return _nullItemQualifierCacheModel;
            }
        };

    private static CacheModel<ItemQualifier> _nullItemQualifierCacheModel = new CacheModel<ItemQualifier>() {
            @Override
            public ItemQualifier toEntityModel() {
                return _nullItemQualifier;
            }
        };

    public ItemQualifierPersistenceImpl() {
        setModelClass(ItemQualifier.class);
    }

    /**
     * Returns all the item qualifiers where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @return the matching item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findByItemIrtQualifierName(
        String itemQualifierValue) throws SystemException {
        return findByItemIrtQualifierName(itemQualifierValue,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item qualifiers where itemQualifierValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemQualifierValue the item qualifier value
     * @param start the lower bound of the range of item qualifiers
     * @param end the upper bound of the range of item qualifiers (not inclusive)
     * @return the range of matching item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findByItemIrtQualifierName(
        String itemQualifierValue, int start, int end)
        throws SystemException {
        return findByItemIrtQualifierName(itemQualifierValue, start, end, null);
    }

    /**
     * Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemQualifierValue the item qualifier value
     * @param start the lower bound of the range of item qualifiers
     * @param end the upper bound of the range of item qualifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findByItemIrtQualifierName(
        String itemQualifierValue, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME;
            finderArgs = new Object[] { itemQualifierValue };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME;
            finderArgs = new Object[] {
                    itemQualifierValue,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemQualifier> list = (List<ItemQualifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemQualifier itemQualifier : list) {
                if (!Validator.equals(itemQualifierValue,
                            itemQualifier.getItemQualifierValue())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

            boolean bindItemQualifierValue = false;

            if (itemQualifierValue == null) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
            } else if (itemQualifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
            } else {
                bindItemQualifierValue = true;

                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemQualifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemQualifierValue) {
                    qPos.add(itemQualifierValue);
                }

                if (!pagination) {
                    list = (List<ItemQualifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemQualifier>(list);
                } else {
                    list = (List<ItemQualifier>) QueryUtil.list(q,
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
     * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier findByItemIrtQualifierName_First(
        String itemQualifierValue, OrderByComparator orderByComparator)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = fetchByItemIrtQualifierName_First(itemQualifierValue,
                orderByComparator);

        if (itemQualifier != null) {
            return itemQualifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemQualifierValue=");
        msg.append(itemQualifierValue);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemQualifierException(msg.toString());
    }

    /**
     * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByItemIrtQualifierName_First(
        String itemQualifierValue, OrderByComparator orderByComparator)
        throws SystemException {
        List<ItemQualifier> list = findByItemIrtQualifierName(itemQualifierValue,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier findByItemIrtQualifierName_Last(
        String itemQualifierValue, OrderByComparator orderByComparator)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = fetchByItemIrtQualifierName_Last(itemQualifierValue,
                orderByComparator);

        if (itemQualifier != null) {
            return itemQualifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemQualifierValue=");
        msg.append(itemQualifierValue);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemQualifierException(msg.toString());
    }

    /**
     * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByItemIrtQualifierName_Last(
        String itemQualifierValue, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByItemIrtQualifierName(itemQualifierValue);

        if (count == 0) {
            return null;
        }

        List<ItemQualifier> list = findByItemIrtQualifierName(itemQualifierValue,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item qualifiers before and after the current item qualifier in the ordered set where itemQualifierValue = &#63;.
     *
     * @param itemQualifierSid the primary key of the current item qualifier
     * @param itemQualifierValue the item qualifier value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
        int itemQualifierSid, String itemQualifierValue,
        OrderByComparator orderByComparator)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = findByPrimaryKey(itemQualifierSid);

        Session session = null;

        try {
            session = openSession();

            ItemQualifier[] array = new ItemQualifierImpl[3];

            array[0] = getByItemIrtQualifierName_PrevAndNext(session,
                    itemQualifier, itemQualifierValue, orderByComparator, true);

            array[1] = itemQualifier;

            array[2] = getByItemIrtQualifierName_PrevAndNext(session,
                    itemQualifier, itemQualifierValue, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemQualifier getByItemIrtQualifierName_PrevAndNext(
        Session session, ItemQualifier itemQualifier,
        String itemQualifierValue, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

        boolean bindItemQualifierValue = false;

        if (itemQualifierValue == null) {
            query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
        } else if (itemQualifierValue.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
        } else {
            bindItemQualifierValue = true;

            query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ItemQualifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemQualifierValue) {
            qPos.add(itemQualifierValue);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemQualifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemQualifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
     *
     * @param itemQualifierValue the item qualifier value
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemIrtQualifierName(String itemQualifierValue)
        throws SystemException {
        for (ItemQualifier itemQualifier : findByItemIrtQualifierName(
                itemQualifierValue, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemQualifier);
        }
    }

    /**
     * Returns the number of item qualifiers where itemQualifierValue = &#63;.
     *
     * @param itemQualifierValue the item qualifier value
     * @return the number of matching item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemIrtQualifierName(String itemQualifierValue)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME;

        Object[] finderArgs = new Object[] { itemQualifierValue };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMQUALIFIER_WHERE);

            boolean bindItemQualifierValue = false;

            if (itemQualifierValue == null) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
            } else if (itemQualifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
            } else {
                bindItemQualifierValue = true;

                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemQualifierValue) {
                    qPos.add(itemQualifierValue);
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
     * Returns the item qualifier where itemQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
     *
     * @param itemQualifierName the item qualifier name
     * @return the matching item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier findByItemIrtQualifierByName(String itemQualifierName)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = fetchByItemIrtQualifierByName(itemQualifierName);

        if (itemQualifier == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("itemQualifierName=");
            msg.append(itemQualifierName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchItemQualifierException(msg.toString());
        }

        return itemQualifier;
    }

    /**
     * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param itemQualifierName the item qualifier name
     * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByItemIrtQualifierByName(String itemQualifierName)
        throws SystemException {
        return fetchByItemIrtQualifierByName(itemQualifierName, true);
    }

    /**
     * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param itemQualifierName the item qualifier name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByItemIrtQualifierByName(
        String itemQualifierName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { itemQualifierName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                    finderArgs, this);
        }

        if (result instanceof ItemQualifier) {
            ItemQualifier itemQualifier = (ItemQualifier) result;

            if (!Validator.equals(itemQualifierName,
                        itemQualifier.getItemQualifierName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

            boolean bindItemQualifierName = false;

            if (itemQualifierName == null) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1);
            } else if (itemQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3);
            } else {
                bindItemQualifierName = true;

                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemQualifierName) {
                    qPos.add(itemQualifierName);
                }

                List<ItemQualifier> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ItemQualifierPersistenceImpl.fetchByItemIrtQualifierByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ItemQualifier itemQualifier = list.get(0);

                    result = itemQualifier;

                    cacheResult(itemQualifier);

                    if ((itemQualifier.getItemQualifierName() == null) ||
                            !itemQualifier.getItemQualifierName()
                                              .equals(itemQualifierName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                            finderArgs, itemQualifier);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ItemQualifier) result;
        }
    }

    /**
     * Removes the item qualifier where itemQualifierName = &#63; from the database.
     *
     * @param itemQualifierName the item qualifier name
     * @return the item qualifier that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier removeByItemIrtQualifierByName(
        String itemQualifierName)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = findByItemIrtQualifierByName(itemQualifierName);

        return remove(itemQualifier);
    }

    /**
     * Returns the number of item qualifiers where itemQualifierName = &#63;.
     *
     * @param itemQualifierName the item qualifier name
     * @return the number of matching item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemIrtQualifierByName(String itemQualifierName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME;

        Object[] finderArgs = new Object[] { itemQualifierName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMQUALIFIER_WHERE);

            boolean bindItemQualifierName = false;

            if (itemQualifierName == null) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1);
            } else if (itemQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3);
            } else {
                bindItemQualifierName = true;

                query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemQualifierName) {
                    qPos.add(itemQualifierName);
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
     * Caches the item qualifier in the entity cache if it is enabled.
     *
     * @param itemQualifier the item qualifier
     */
    @Override
    public void cacheResult(ItemQualifier itemQualifier) {
        EntityCacheUtil.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierImpl.class, itemQualifier.getPrimaryKey(),
            itemQualifier);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
            new Object[] { itemQualifier.getItemQualifierName() }, itemQualifier);

        itemQualifier.resetOriginalValues();
    }

    /**
     * Caches the item qualifiers in the entity cache if it is enabled.
     *
     * @param itemQualifiers the item qualifiers
     */
    @Override
    public void cacheResult(List<ItemQualifier> itemQualifiers) {
        for (ItemQualifier itemQualifier : itemQualifiers) {
            if (EntityCacheUtil.getResult(
                        ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemQualifierImpl.class, itemQualifier.getPrimaryKey()) == null) {
                cacheResult(itemQualifier);
            } else {
                itemQualifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item qualifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemQualifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemQualifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item qualifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemQualifier itemQualifier) {
        EntityCacheUtil.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierImpl.class, itemQualifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(itemQualifier);
    }

    @Override
    public void clearCache(List<ItemQualifier> itemQualifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemQualifier itemQualifier : itemQualifiers) {
            EntityCacheUtil.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemQualifierImpl.class, itemQualifier.getPrimaryKey());

            clearUniqueFindersCache(itemQualifier);
        }
    }

    protected void cacheUniqueFindersCache(ItemQualifier itemQualifier) {
        if (itemQualifier.isNew()) {
            Object[] args = new Object[] { itemQualifier.getItemQualifierName() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                args, itemQualifier);
        } else {
            ItemQualifierModelImpl itemQualifierModelImpl = (ItemQualifierModelImpl) itemQualifier;

            if ((itemQualifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemQualifier.getItemQualifierName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                    args, itemQualifier);
            }
        }
    }

    protected void clearUniqueFindersCache(ItemQualifier itemQualifier) {
        ItemQualifierModelImpl itemQualifierModelImpl = (ItemQualifierModelImpl) itemQualifier;

        Object[] args = new Object[] { itemQualifier.getItemQualifierName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
            args);

        if ((itemQualifierModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    itemQualifierModelImpl.getOriginalItemQualifierName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
                args);
        }
    }

    /**
     * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
     *
     * @param itemQualifierSid the primary key for the new item qualifier
     * @return the new item qualifier
     */
    @Override
    public ItemQualifier create(int itemQualifierSid) {
        ItemQualifier itemQualifier = new ItemQualifierImpl();

        itemQualifier.setNew(true);
        itemQualifier.setPrimaryKey(itemQualifierSid);

        return itemQualifier;
    }

    /**
     * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemQualifierSid the primary key of the item qualifier
     * @return the item qualifier that was removed
     * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier remove(int itemQualifierSid)
        throws NoSuchItemQualifierException, SystemException {
        return remove((Serializable) itemQualifierSid);
    }

    /**
     * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item qualifier
     * @return the item qualifier that was removed
     * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier remove(Serializable primaryKey)
        throws NoSuchItemQualifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemQualifier itemQualifier = (ItemQualifier) session.get(ItemQualifierImpl.class,
                    primaryKey);

            if (itemQualifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemQualifier);
        } catch (NoSuchItemQualifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemQualifier removeImpl(ItemQualifier itemQualifier)
        throws SystemException {
        itemQualifier = toUnwrappedModel(itemQualifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemQualifier)) {
                itemQualifier = (ItemQualifier) session.get(ItemQualifierImpl.class,
                        itemQualifier.getPrimaryKeyObj());
            }

            if (itemQualifier != null) {
                session.delete(itemQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemQualifier != null) {
            clearCache(itemQualifier);
        }

        return itemQualifier;
    }

    @Override
    public ItemQualifier updateImpl(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws SystemException {
        itemQualifier = toUnwrappedModel(itemQualifier);

        boolean isNew = itemQualifier.isNew();

        ItemQualifierModelImpl itemQualifierModelImpl = (ItemQualifierModelImpl) itemQualifier;

        Session session = null;

        try {
            session = openSession();

            if (itemQualifier.isNew()) {
                session.save(itemQualifier);

                itemQualifier.setNew(false);
            } else {
                session.merge(itemQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((itemQualifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemQualifierModelImpl.getOriginalItemQualifierValue()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME,
                    args);

                args = new Object[] {
                        itemQualifierModelImpl.getItemQualifierValue()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemQualifierImpl.class, itemQualifier.getPrimaryKey(),
            itemQualifier);

        clearUniqueFindersCache(itemQualifier);
        cacheUniqueFindersCache(itemQualifier);

        return itemQualifier;
    }

    protected ItemQualifier toUnwrappedModel(ItemQualifier itemQualifier) {
        if (itemQualifier instanceof ItemQualifierImpl) {
            return itemQualifier;
        }

        ItemQualifierImpl itemQualifierImpl = new ItemQualifierImpl();

        itemQualifierImpl.setNew(itemQualifier.isNew());
        itemQualifierImpl.setPrimaryKey(itemQualifier.getPrimaryKey());

        itemQualifierImpl.setCreatedBy(itemQualifier.getCreatedBy());
        itemQualifierImpl.setItemQualifierSid(itemQualifier.getItemQualifierSid());
        itemQualifierImpl.setSpecificEntityCode(itemQualifier.getSpecificEntityCode());
        itemQualifierImpl.setItemQualifierName(itemQualifier.getItemQualifierName());
        itemQualifierImpl.setModifiedBy(itemQualifier.getModifiedBy());
        itemQualifierImpl.setCreatedDate(itemQualifier.getCreatedDate());
        itemQualifierImpl.setBatchId(itemQualifier.getBatchId());
        itemQualifierImpl.setModifiedDate(itemQualifier.getModifiedDate());
        itemQualifierImpl.setEffectiveDates(itemQualifier.getEffectiveDates());
        itemQualifierImpl.setNotes(itemQualifier.getNotes());
        itemQualifierImpl.setItemQualifierValue(itemQualifier.getItemQualifierValue());
        itemQualifierImpl.setRecordLockStatus(itemQualifier.isRecordLockStatus());
        itemQualifierImpl.setSource(itemQualifier.getSource());
        itemQualifierImpl.setInboundStatus(itemQualifier.getInboundStatus());

        return itemQualifierImpl;
    }

    /**
     * Returns the item qualifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item qualifier
     * @return the item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemQualifierException, SystemException {
        ItemQualifier itemQualifier = fetchByPrimaryKey(primaryKey);

        if (itemQualifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemQualifier;
    }

    /**
     * Returns the item qualifier with the primary key or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
     *
     * @param itemQualifierSid the primary key of the item qualifier
     * @return the item qualifier
     * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier findByPrimaryKey(int itemQualifierSid)
        throws NoSuchItemQualifierException, SystemException {
        return findByPrimaryKey((Serializable) itemQualifierSid);
    }

    /**
     * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item qualifier
     * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemQualifier itemQualifier = (ItemQualifier) EntityCacheUtil.getResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemQualifierImpl.class, primaryKey);

        if (itemQualifier == _nullItemQualifier) {
            return null;
        }

        if (itemQualifier == null) {
            Session session = null;

            try {
                session = openSession();

                itemQualifier = (ItemQualifier) session.get(ItemQualifierImpl.class,
                        primaryKey);

                if (itemQualifier != null) {
                    cacheResult(itemQualifier);
                } else {
                    EntityCacheUtil.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemQualifierImpl.class, primaryKey, _nullItemQualifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
                    ItemQualifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemQualifier;
    }

    /**
     * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemQualifierSid the primary key of the item qualifier
     * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemQualifier fetchByPrimaryKey(int itemQualifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemQualifierSid);
    }

    /**
     * Returns all the item qualifiers.
     *
     * @return the item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item qualifiers
     * @param end the upper bound of the range of item qualifiers (not inclusive)
     * @return the range of item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item qualifiers
     * @param end the upper bound of the range of item qualifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemQualifier> findAll(int start, int end,
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

        List<ItemQualifier> list = (List<ItemQualifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMQUALIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMQUALIFIER;

                if (pagination) {
                    sql = sql.concat(ItemQualifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemQualifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemQualifier>(list);
                } else {
                    list = (List<ItemQualifier>) QueryUtil.list(q,
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
     * Removes all the item qualifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemQualifier itemQualifier : findAll()) {
            remove(itemQualifier);
        }
    }

    /**
     * Returns the number of item qualifiers.
     *
     * @return the number of item qualifiers
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

                Query q = session.createQuery(_SQL_COUNT_ITEMQUALIFIER);

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
     * Initializes the item qualifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemQualifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemQualifier>> listenersList = new ArrayList<ModelListener<ItemQualifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemQualifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemQualifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
