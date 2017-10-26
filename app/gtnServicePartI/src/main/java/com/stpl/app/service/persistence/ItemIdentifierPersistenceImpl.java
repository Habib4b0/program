package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemIdentifierException;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.impl.ItemIdentifierImpl;
import com.stpl.app.model.impl.ItemIdentifierModelImpl;
import com.stpl.app.service.persistence.ItemIdentifierPersistence;

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
import com.stpl.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemIdentifierPersistence
 * @see ItemIdentifierUtil
 * @generated
 */
public class ItemIdentifierPersistenceImpl extends BasePersistenceImpl<ItemIdentifier>
    implements ItemIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemIdentifierUtil} to access the item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER =
        new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemIrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER =
        new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemIrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName()
            },
            ItemIdentifierModelImpl.ITEMIDENTIFIERVALUE_COLUMN_BITMASK |
            ItemIdentifierModelImpl.ITEMQUALIFIERSID_COLUMN_BITMASK |
            ItemIdentifierModelImpl.IDENTIFIERSTATUS_COLUMN_BITMASK |
            ItemIdentifierModelImpl.STARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemIrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName()
            });
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1 =
        "itemIdentifier.itemIdentifierValue IS NULL AND ";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2 =
        "itemIdentifier.itemIdentifierValue = ? AND ";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3 =
        "(itemIdentifier.itemIdentifierValue IS NULL OR itemIdentifier.itemIdentifierValue = '') AND ";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2 =
        "itemIdentifier.itemQualifierSid = ? AND ";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2 =
        "itemIdentifier.identifierStatus = ? AND ";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1 = "itemIdentifier.startDate IS NULL";
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2 = "itemIdentifier.startDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS =
        new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemIrtIdentifierDetails",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS =
        new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            ItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemIrtIdentifierDetails",
            new String[] { Integer.class.getName() },
            ItemIdentifierModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS =
        new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemIrtIdentifierDetails",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2 =
        "itemIdentifier.itemMasterSid = ?";
    private static final String _SQL_SELECT_ITEMIDENTIFIER = "SELECT itemIdentifier FROM ItemIdentifier itemIdentifier";
    private static final String _SQL_SELECT_ITEMIDENTIFIER_WHERE = "SELECT itemIdentifier FROM ItemIdentifier itemIdentifier WHERE ";
    private static final String _SQL_COUNT_ITEMIDENTIFIER = "SELECT COUNT(itemIdentifier) FROM ItemIdentifier itemIdentifier";
    private static final String _SQL_COUNT_ITEMIDENTIFIER_WHERE = "SELECT COUNT(itemIdentifier) FROM ItemIdentifier itemIdentifier WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemIdentifier exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemIdentifier exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemIdentifierSid", "itemMasterSid", "endDate", "modifiedDate",
                "identifierStatus", "entityCode", "itemIdentifierValue",
                "recordLockStatus", "itemQualifierSid", "startDate",
                "createdDate", "source", "createdBy", "batchId", "modifiedBy",
                "inboundStatus"
            });
    private static ItemIdentifier _nullItemIdentifier = new ItemIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemIdentifier> toCacheModel() {
                return _nullItemIdentifierCacheModel;
            }
        };

    private static CacheModel<ItemIdentifier> _nullItemIdentifierCacheModel = new CacheModel<ItemIdentifier>() {
            @Override
            public ItemIdentifier toEntityModel() {
                return _nullItemIdentifier;
            }
        };

    public ItemIdentifierPersistenceImpl() {
        setModelClass(ItemIdentifier.class);
    }

    /**
     * Returns all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @return the matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifier(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate) throws SystemException {
        return findByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
            identifierStatus, startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @return the range of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifier(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, int start, int end) throws SystemException {
        return findByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
            identifierStatus, startDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifier(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER;
            finderArgs = new Object[] {
                    itemIdentifierValue, itemQualifierSid, identifierStatus,
                    startDate
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER;
            finderArgs = new Object[] {
                    itemIdentifierValue, itemQualifierSid, identifierStatus,
                    startDate,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemIdentifier> list = (List<ItemIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemIdentifier itemIdentifier : list) {
                if (!Validator.equals(itemIdentifierValue,
                            itemIdentifier.getItemIdentifierValue()) ||
                        (itemQualifierSid != itemIdentifier.getItemQualifierSid()) ||
                        (identifierStatus != itemIdentifier.getIdentifierStatus()) ||
                        !Validator.equals(startDate,
                            itemIdentifier.getStartDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(6 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(6);
            }

            query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

            boolean bindItemIdentifierValue = false;

            if (itemIdentifierValue == null) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
            } else if (itemIdentifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
            } else {
                bindItemIdentifierValue = true;

                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
            }

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdentifierValue) {
                    qPos.add(itemIdentifierValue);
                }

                qPos.add(itemQualifierSid);

                qPos.add(identifierStatus);

                if (bindStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(startDate));
                }

                if (!pagination) {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemIdentifier>(list);
                } else {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
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
     * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByItemIrtIdentifier_First(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = fetchByItemIrtIdentifier_First(itemIdentifierValue,
                itemQualifierSid, identifierStatus, startDate, orderByComparator);

        if (itemIdentifier != null) {
            return itemIdentifier;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdentifierValue=");
        msg.append(itemIdentifierValue);

        msg.append(", itemQualifierSid=");
        msg.append(itemQualifierSid);

        msg.append(", identifierStatus=");
        msg.append(identifierStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemIdentifierException(msg.toString());
    }

    /**
     * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByItemIrtIdentifier_First(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, OrderByComparator orderByComparator)
        throws SystemException {
        List<ItemIdentifier> list = findByItemIrtIdentifier(itemIdentifierValue,
                itemQualifierSid, identifierStatus, startDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByItemIrtIdentifier_Last(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = fetchByItemIrtIdentifier_Last(itemIdentifierValue,
                itemQualifierSid, identifierStatus, startDate, orderByComparator);

        if (itemIdentifier != null) {
            return itemIdentifier;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdentifierValue=");
        msg.append(itemIdentifierValue);

        msg.append(", itemQualifierSid=");
        msg.append(itemQualifierSid);

        msg.append(", identifierStatus=");
        msg.append(identifierStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemIdentifierException(msg.toString());
    }

    /**
     * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByItemIrtIdentifier_Last(
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByItemIrtIdentifier(itemIdentifierValue,
                itemQualifierSid, identifierStatus, startDate);

        if (count == 0) {
            return null;
        }

        List<ItemIdentifier> list = findByItemIrtIdentifier(itemIdentifierValue,
                itemQualifierSid, identifierStatus, startDate, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item identifiers before and after the current item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierSid the primary key of the current item identifier
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
        int itemIdentifierSid, String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = findByPrimaryKey(itemIdentifierSid);

        Session session = null;

        try {
            session = openSession();

            ItemIdentifier[] array = new ItemIdentifierImpl[3];

            array[0] = getByItemIrtIdentifier_PrevAndNext(session,
                    itemIdentifier, itemIdentifierValue, itemQualifierSid,
                    identifierStatus, startDate, orderByComparator, true);

            array[1] = itemIdentifier;

            array[2] = getByItemIrtIdentifier_PrevAndNext(session,
                    itemIdentifier, itemIdentifierValue, itemQualifierSid,
                    identifierStatus, startDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemIdentifier getByItemIrtIdentifier_PrevAndNext(
        Session session, ItemIdentifier itemIdentifier,
        String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
        Date startDate, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

        boolean bindItemIdentifierValue = false;

        if (itemIdentifierValue == null) {
            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
        } else if (itemIdentifierValue.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
        } else {
            bindItemIdentifierValue = true;

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
        }

        query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

        query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

        boolean bindStartDate = false;

        if (startDate == null) {
            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
        } else {
            bindStartDate = true;

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
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
            query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemIdentifierValue) {
            qPos.add(itemIdentifierValue);
        }

        qPos.add(itemQualifierSid);

        qPos.add(identifierStatus);

        if (bindStartDate) {
            qPos.add(CalendarUtil.getTimestamp(startDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemIdentifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemIdentifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemIrtIdentifier(String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, Date startDate)
        throws SystemException {
        for (ItemIdentifier itemIdentifier : findByItemIrtIdentifier(
                itemIdentifierValue, itemQualifierSid, identifierStatus,
                startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemIdentifier);
        }
    }

    /**
     * Returns the number of item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
     *
     * @param itemIdentifierValue the item identifier value
     * @param itemQualifierSid the item qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @return the number of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemIrtIdentifier(String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, Date startDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER;

        Object[] finderArgs = new Object[] {
                itemIdentifierValue, itemQualifierSid, identifierStatus,
                startDate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_ITEMIDENTIFIER_WHERE);

            boolean bindItemIdentifierValue = false;

            if (itemIdentifierValue == null) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
            } else if (itemIdentifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
            } else {
                bindItemIdentifierValue = true;

                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
            }

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdentifierValue) {
                    qPos.add(itemIdentifierValue);
                }

                qPos.add(itemQualifierSid);

                qPos.add(identifierStatus);

                if (bindStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(startDate));
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
     * Returns all the item identifiers where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @return the matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid) throws SystemException {
        return findByItemIrtIdentifierDetails(itemMasterSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item identifiers where itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @return the range of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end) throws SystemException {
        return findByItemIrtIdentifierDetails(itemMasterSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS;
            finderArgs = new Object[] { itemMasterSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS;
            finderArgs = new Object[] {
                    itemMasterSid,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemIdentifier> list = (List<ItemIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemIdentifier itemIdentifier : list) {
                if ((itemMasterSid != itemIdentifier.getItemMasterSid())) {
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

            query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(itemMasterSid);

                if (!pagination) {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemIdentifier>(list);
                } else {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
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
     * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByItemIrtIdentifierDetails_First(
        int itemMasterSid, OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = fetchByItemIrtIdentifierDetails_First(itemMasterSid,
                orderByComparator);

        if (itemIdentifier != null) {
            return itemIdentifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemIdentifierException(msg.toString());
    }

    /**
     * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByItemIrtIdentifierDetails_First(
        int itemMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        List<ItemIdentifier> list = findByItemIrtIdentifierDetails(itemMasterSid,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByItemIrtIdentifierDetails_Last(
        int itemMasterSid, OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = fetchByItemIrtIdentifierDetails_Last(itemMasterSid,
                orderByComparator);

        if (itemIdentifier != null) {
            return itemIdentifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemIdentifierException(msg.toString());
    }

    /**
     * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
        int itemMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByItemIrtIdentifierDetails(itemMasterSid);

        if (count == 0) {
            return null;
        }

        List<ItemIdentifier> list = findByItemIrtIdentifierDetails(itemMasterSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item identifiers before and after the current item identifier in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemIdentifierSid the primary key of the current item identifier
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
        int itemIdentifierSid, int itemMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = findByPrimaryKey(itemIdentifierSid);

        Session session = null;

        try {
            session = openSession();

            ItemIdentifier[] array = new ItemIdentifierImpl[3];

            array[0] = getByItemIrtIdentifierDetails_PrevAndNext(session,
                    itemIdentifier, itemMasterSid, orderByComparator, true);

            array[1] = itemIdentifier;

            array[2] = getByItemIrtIdentifierDetails_PrevAndNext(session,
                    itemIdentifier, itemMasterSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemIdentifier getByItemIrtIdentifierDetails_PrevAndNext(
        Session session, ItemIdentifier itemIdentifier, int itemMasterSid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

        query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

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
            query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(itemMasterSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemIdentifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemIdentifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item identifiers where itemMasterSid = &#63; from the database.
     *
     * @param itemMasterSid the item master sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemIrtIdentifierDetails(int itemMasterSid)
        throws SystemException {
        for (ItemIdentifier itemIdentifier : findByItemIrtIdentifierDetails(
                itemMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemIdentifier);
        }
    }

    /**
     * Returns the number of item identifiers where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @return the number of matching item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemIrtIdentifierDetails(int itemMasterSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS;

        Object[] finderArgs = new Object[] { itemMasterSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMIDENTIFIER_WHERE);

            query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(itemMasterSid);

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
     * Caches the item identifier in the entity cache if it is enabled.
     *
     * @param itemIdentifier the item identifier
     */
    @Override
    public void cacheResult(ItemIdentifier itemIdentifier) {
        EntityCacheUtil.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey(),
            itemIdentifier);

        itemIdentifier.resetOriginalValues();
    }

    /**
     * Caches the item identifiers in the entity cache if it is enabled.
     *
     * @param itemIdentifiers the item identifiers
     */
    @Override
    public void cacheResult(List<ItemIdentifier> itemIdentifiers) {
        for (ItemIdentifier itemIdentifier : itemIdentifiers) {
            if (EntityCacheUtil.getResult(
                        ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey()) == null) {
                cacheResult(itemIdentifier);
            } else {
                itemIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemIdentifier itemIdentifier) {
        EntityCacheUtil.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ItemIdentifier> itemIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemIdentifier itemIdentifier : itemIdentifiers) {
            EntityCacheUtil.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new item identifier with the primary key. Does not add the item identifier to the database.
     *
     * @param itemIdentifierSid the primary key for the new item identifier
     * @return the new item identifier
     */
    @Override
    public ItemIdentifier create(int itemIdentifierSid) {
        ItemIdentifier itemIdentifier = new ItemIdentifierImpl();

        itemIdentifier.setNew(true);
        itemIdentifier.setPrimaryKey(itemIdentifierSid);

        return itemIdentifier;
    }

    /**
     * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemIdentifierSid the primary key of the item identifier
     * @return the item identifier that was removed
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier remove(int itemIdentifierSid)
        throws NoSuchItemIdentifierException, SystemException {
        return remove((Serializable) itemIdentifierSid);
    }

    /**
     * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item identifier
     * @return the item identifier that was removed
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier remove(Serializable primaryKey)
        throws NoSuchItemIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemIdentifier itemIdentifier = (ItemIdentifier) session.get(ItemIdentifierImpl.class,
                    primaryKey);

            if (itemIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemIdentifier);
        } catch (NoSuchItemIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemIdentifier removeImpl(ItemIdentifier itemIdentifier)
        throws SystemException {
        itemIdentifier = toUnwrappedModel(itemIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemIdentifier)) {
                itemIdentifier = (ItemIdentifier) session.get(ItemIdentifierImpl.class,
                        itemIdentifier.getPrimaryKeyObj());
            }

            if (itemIdentifier != null) {
                session.delete(itemIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemIdentifier != null) {
            clearCache(itemIdentifier);
        }

        return itemIdentifier;
    }

    @Override
    public ItemIdentifier updateImpl(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws SystemException {
        itemIdentifier = toUnwrappedModel(itemIdentifier);

        boolean isNew = itemIdentifier.isNew();

        ItemIdentifierModelImpl itemIdentifierModelImpl = (ItemIdentifierModelImpl) itemIdentifier;

        Session session = null;

        try {
            session = openSession();

            if (itemIdentifier.isNew()) {
                session.save(itemIdentifier);

                itemIdentifier.setNew(false);
            } else {
                session.merge(itemIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemIdentifierModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((itemIdentifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemIdentifierModelImpl.getOriginalItemIdentifierValue(),
                        itemIdentifierModelImpl.getOriginalItemQualifierSid(),
                        itemIdentifierModelImpl.getOriginalIdentifierStatus(),
                        itemIdentifierModelImpl.getOriginalStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER,
                    args);

                args = new Object[] {
                        itemIdentifierModelImpl.getItemIdentifierValue(),
                        itemIdentifierModelImpl.getItemQualifierSid(),
                        itemIdentifierModelImpl.getIdentifierStatus(),
                        itemIdentifierModelImpl.getStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER,
                    args);
            }

            if ((itemIdentifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemIdentifierModelImpl.getOriginalItemMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS,
                    args);

                args = new Object[] { itemIdentifierModelImpl.getItemMasterSid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey(),
            itemIdentifier);

        return itemIdentifier;
    }

    protected ItemIdentifier toUnwrappedModel(ItemIdentifier itemIdentifier) {
        if (itemIdentifier instanceof ItemIdentifierImpl) {
            return itemIdentifier;
        }

        ItemIdentifierImpl itemIdentifierImpl = new ItemIdentifierImpl();

        itemIdentifierImpl.setNew(itemIdentifier.isNew());
        itemIdentifierImpl.setPrimaryKey(itemIdentifier.getPrimaryKey());

        itemIdentifierImpl.setItemIdentifierSid(itemIdentifier.getItemIdentifierSid());
        itemIdentifierImpl.setItemMasterSid(itemIdentifier.getItemMasterSid());
        itemIdentifierImpl.setEndDate(itemIdentifier.getEndDate());
        itemIdentifierImpl.setModifiedDate(itemIdentifier.getModifiedDate());
        itemIdentifierImpl.setIdentifierStatus(itemIdentifier.getIdentifierStatus());
        itemIdentifierImpl.setEntityCode(itemIdentifier.getEntityCode());
        itemIdentifierImpl.setItemIdentifierValue(itemIdentifier.getItemIdentifierValue());
        itemIdentifierImpl.setRecordLockStatus(itemIdentifier.isRecordLockStatus());
        itemIdentifierImpl.setItemQualifierSid(itemIdentifier.getItemQualifierSid());
        itemIdentifierImpl.setStartDate(itemIdentifier.getStartDate());
        itemIdentifierImpl.setCreatedDate(itemIdentifier.getCreatedDate());
        itemIdentifierImpl.setSource(itemIdentifier.getSource());
        itemIdentifierImpl.setCreatedBy(itemIdentifier.getCreatedBy());
        itemIdentifierImpl.setBatchId(itemIdentifier.getBatchId());
        itemIdentifierImpl.setModifiedBy(itemIdentifier.getModifiedBy());
        itemIdentifierImpl.setInboundStatus(itemIdentifier.getInboundStatus());

        return itemIdentifierImpl;
    }

    /**
     * Returns the item identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item identifier
     * @return the item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemIdentifierException, SystemException {
        ItemIdentifier itemIdentifier = fetchByPrimaryKey(primaryKey);

        if (itemIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemIdentifier;
    }

    /**
     * Returns the item identifier with the primary key or throws a {@link com.stpl.app.NoSuchItemIdentifierException} if it could not be found.
     *
     * @param itemIdentifierSid the primary key of the item identifier
     * @return the item identifier
     * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier findByPrimaryKey(int itemIdentifierSid)
        throws NoSuchItemIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) itemIdentifierSid);
    }

    /**
     * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item identifier
     * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemIdentifier itemIdentifier = (ItemIdentifier) EntityCacheUtil.getResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                ItemIdentifierImpl.class, primaryKey);

        if (itemIdentifier == _nullItemIdentifier) {
            return null;
        }

        if (itemIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                itemIdentifier = (ItemIdentifier) session.get(ItemIdentifierImpl.class,
                        primaryKey);

                if (itemIdentifier != null) {
                    cacheResult(itemIdentifier);
                } else {
                    EntityCacheUtil.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        ItemIdentifierImpl.class, primaryKey,
                        _nullItemIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    ItemIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemIdentifier;
    }

    /**
     * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemIdentifierSid the primary key of the item identifier
     * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemIdentifier fetchByPrimaryKey(int itemIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemIdentifierSid);
    }

    /**
     * Returns all the item identifiers.
     *
     * @return the item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @return the range of item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item identifiers
     * @param end the upper bound of the range of item identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemIdentifier> findAll(int start, int end,
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

        List<ItemIdentifier> list = (List<ItemIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(ItemIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemIdentifier>(list);
                } else {
                    list = (List<ItemIdentifier>) QueryUtil.list(q,
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
     * Removes all the item identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemIdentifier itemIdentifier : findAll()) {
            remove(itemIdentifier);
        }
    }

    /**
     * Returns the number of item identifiers.
     *
     * @return the number of item identifiers
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

                Query q = session.createQuery(_SQL_COUNT_ITEMIDENTIFIER);

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
     * Initializes the item identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemIdentifier>> listenersList = new ArrayList<ModelListener<ItemIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
