package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemPricingException;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.impl.ItemPricingImpl;
import com.stpl.app.model.impl.ItemPricingModelImpl;
import com.stpl.app.service.persistence.ItemPricingPersistence;

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
 * The persistence implementation for the item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingPersistence
 * @see ItemPricingUtil
 * @generated
 */
public class ItemPricingPersistenceImpl extends BasePersistenceImpl<ItemPricing>
    implements ItemPricingPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemPricingUtil} to access the item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemPricingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICING =
        new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemPricing",
            new String[] {
                Integer.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Integer.class.getName(),
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING =
        new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemPricing",
            new String[] {
                Integer.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Integer.class.getName(),
                Date.class.getName()
            },
            ItemPricingModelImpl.ITEMMASTERSID_COLUMN_BITMASK |
            ItemPricingModelImpl.ITEMUOM_COLUMN_BITMASK |
            ItemPricingModelImpl.ITEMPRICINGQUALIFIERSID_COLUMN_BITMASK |
            ItemPricingModelImpl.PRICINGCODESTATUS_COLUMN_BITMASK |
            ItemPricingModelImpl.STARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICING = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemPricing",
            new String[] {
                Integer.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Integer.class.getName(),
                Date.class.getName()
            });
    private static final String _FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2 = "itemPricing.itemMasterSid = ? AND ";
    private static final String _FINDER_COLUMN_ITEMPRICING_ITEMUOM_2 = "itemPricing.itemUom = ? AND ";
    private static final String _FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2 =
        "itemPricing.itemPricingQualifierSid = ? AND ";
    private static final String _FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2 = "itemPricing.pricingCodeStatus = ? AND ";
    private static final String _FINDER_COLUMN_ITEMPRICING_STARTDATE_1 = "itemPricing.startDate IS NULL";
    private static final String _FINDER_COLUMN_ITEMPRICING_STARTDATE_2 = "itemPricing.startDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICINGDETAILS =
        new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemPricingDetails",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS =
        new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemPricingDetails",
            new String[] { Integer.class.getName() },
            ItemPricingModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemPricingDetails",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2 =
        "itemPricing.itemMasterSid = ?";
    private static final String _SQL_SELECT_ITEMPRICING = "SELECT itemPricing FROM ItemPricing itemPricing";
    private static final String _SQL_SELECT_ITEMPRICING_WHERE = "SELECT itemPricing FROM ItemPricing itemPricing WHERE ";
    private static final String _SQL_COUNT_ITEMPRICING = "SELECT COUNT(itemPricing) FROM ItemPricing itemPricing";
    private static final String _SQL_COUNT_ITEMPRICING_WHERE = "SELECT COUNT(itemPricing) FROM ItemPricing itemPricing WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemPricing.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemPricing exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemPricing exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemPricingPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "itemPricingQualifierSid", "itemPrice",
                "endDate", "modifiedDate", "entityCode", "recordLockStatus",
                "startDate", "createdDate", "createdBy", "source", "batchId",
                "itemUom", "modifiedBy", "inboundStatus", "itemPricingSid",
                "pricingCodeStatus"
            });
    private static ItemPricing _nullItemPricing = new ItemPricingImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemPricing> toCacheModel() {
                return _nullItemPricingCacheModel;
            }
        };

    private static CacheModel<ItemPricing> _nullItemPricingCacheModel = new CacheModel<ItemPricing>() {
            @Override
            public ItemPricing toEntityModel() {
                return _nullItemPricing;
            }
        };

    public ItemPricingPersistenceImpl() {
        setModelClass(ItemPricing.class);
    }

    /**
     * Returns all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @return the matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate)
        throws SystemException {
        return findByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @return the range of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        int start, int end) throws SystemException {
        return findByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING;
            finderArgs = new Object[] {
                    itemMasterSid, itemUom, itemPricingQualifierSid,
                    pricingCodeStatus, startDate
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICING;
            finderArgs = new Object[] {
                    itemMasterSid, itemUom, itemPricingQualifierSid,
                    pricingCodeStatus, startDate,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemPricing> list = (List<ItemPricing>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemPricing itemPricing : list) {
                if ((itemMasterSid != itemPricing.getItemMasterSid()) ||
                        (itemUom != itemPricing.getItemUom()) ||
                        (itemPricingQualifierSid != itemPricing.getItemPricingQualifierSid()) ||
                        (pricingCodeStatus != itemPricing.getPricingCodeStatus()) ||
                        !Validator.equals(startDate, itemPricing.getStartDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(7 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(7);
            }

            query.append(_SQL_SELECT_ITEMPRICING_WHERE);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(itemMasterSid);

                qPos.add(itemUom);

                qPos.add(itemPricingQualifierSid);

                qPos.add(pricingCodeStatus);

                if (bindStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(startDate));
                }

                if (!pagination) {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemPricing>(list);
                } else {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
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
     * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByItemPricing_First(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = fetchByItemPricing_First(itemMasterSid,
                itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
                orderByComparator);

        if (itemPricing != null) {
            return itemPricing;
        }

        StringBundler msg = new StringBundler(12);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(", itemUom=");
        msg.append(itemUom);

        msg.append(", itemPricingQualifierSid=");
        msg.append(itemPricingQualifierSid);

        msg.append(", pricingCodeStatus=");
        msg.append(pricingCodeStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemPricingException(msg.toString());
    }

    /**
     * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByItemPricing_First(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemPricing> list = findByItemPricing(itemMasterSid, itemUom,
                itemPricingQualifierSid, pricingCodeStatus, startDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByItemPricing_Last(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = fetchByItemPricing_Last(itemMasterSid,
                itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
                orderByComparator);

        if (itemPricing != null) {
            return itemPricing;
        }

        StringBundler msg = new StringBundler(12);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(", itemUom=");
        msg.append(itemUom);

        msg.append(", itemPricingQualifierSid=");
        msg.append(itemPricingQualifierSid);

        msg.append(", pricingCodeStatus=");
        msg.append(pricingCodeStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemPricingException(msg.toString());
    }

    /**
     * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByItemPricing_Last(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemPricing(itemMasterSid, itemUom,
                itemPricingQualifierSid, pricingCodeStatus, startDate);

        if (count == 0) {
            return null;
        }

        List<ItemPricing> list = findByItemPricing(itemMasterSid, itemUom,
                itemPricingQualifierSid, pricingCodeStatus, startDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemPricingSid the primary key of the current item pricing
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing[] findByItemPricing_PrevAndNext(int itemPricingSid,
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = findByPrimaryKey(itemPricingSid);

        Session session = null;

        try {
            session = openSession();

            ItemPricing[] array = new ItemPricingImpl[3];

            array[0] = getByItemPricing_PrevAndNext(session, itemPricing,
                    itemMasterSid, itemUom, itemPricingQualifierSid,
                    pricingCodeStatus, startDate, orderByComparator, true);

            array[1] = itemPricing;

            array[2] = getByItemPricing_PrevAndNext(session, itemPricing,
                    itemMasterSid, itemUom, itemPricingQualifierSid,
                    pricingCodeStatus, startDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemPricing getByItemPricing_PrevAndNext(Session session,
        ItemPricing itemPricing, int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMPRICING_WHERE);

        query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

        query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

        query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

        query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

        boolean bindStartDate = false;

        if (startDate == null) {
            query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
        } else {
            bindStartDate = true;

            query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
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
            query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(itemMasterSid);

        qPos.add(itemUom);

        qPos.add(itemPricingQualifierSid);

        qPos.add(pricingCodeStatus);

        if (bindStartDate) {
            qPos.add(CalendarUtil.getTimestamp(startDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemPricing);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemPricing> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63; from the database.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate)
        throws SystemException {
        for (ItemPricing itemPricing : findByItemPricing(itemMasterSid,
                itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemPricing);
        }
    }

    /**
     * Returns the number of item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param itemUom the item uom
     * @param itemPricingQualifierSid the item pricing qualifier sid
     * @param pricingCodeStatus the pricing code status
     * @param startDate the start date
     * @return the number of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus, Date startDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICING;

        Object[] finderArgs = new Object[] {
                itemMasterSid, itemUom, itemPricingQualifierSid,
                pricingCodeStatus, startDate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(6);

            query.append(_SQL_COUNT_ITEMPRICING_WHERE);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(itemMasterSid);

                qPos.add(itemUom);

                qPos.add(itemPricingQualifierSid);

                qPos.add(pricingCodeStatus);

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
     * Returns all the item pricings where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @return the matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricingDetails(int itemMasterSid)
        throws SystemException {
        return findByItemPricingDetails(itemMasterSid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item pricings where itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @return the range of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricingDetails(int itemMasterSid,
        int start, int end) throws SystemException {
        return findByItemPricingDetails(itemMasterSid, start, end, null);
    }

    /**
     * Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemMasterSid the item master sid
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findByItemPricingDetails(int itemMasterSid,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS;
            finderArgs = new Object[] { itemMasterSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICINGDETAILS;
            finderArgs = new Object[] {
                    itemMasterSid,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemPricing> list = (List<ItemPricing>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemPricing itemPricing : list) {
                if ((itemMasterSid != itemPricing.getItemMasterSid())) {
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

            query.append(_SQL_SELECT_ITEMPRICING_WHERE);

            query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(itemMasterSid);

                if (!pagination) {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemPricing>(list);
                } else {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
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
     * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByItemPricingDetails_First(int itemMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = fetchByItemPricingDetails_First(itemMasterSid,
                orderByComparator);

        if (itemPricing != null) {
            return itemPricing;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemPricingException(msg.toString());
    }

    /**
     * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByItemPricingDetails_First(int itemMasterSid,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemPricing> list = findByItemPricingDetails(itemMasterSid, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByItemPricingDetails_Last(int itemMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = fetchByItemPricingDetails_Last(itemMasterSid,
                orderByComparator);

        if (itemPricing != null) {
            return itemPricing;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemMasterSid=");
        msg.append(itemMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemPricingException(msg.toString());
    }

    /**
     * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByItemPricingDetails_Last(int itemMasterSid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemPricingDetails(itemMasterSid);

        if (count == 0) {
            return null;
        }

        List<ItemPricing> list = findByItemPricingDetails(itemMasterSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63;.
     *
     * @param itemPricingSid the primary key of the current item pricing
     * @param itemMasterSid the item master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing[] findByItemPricingDetails_PrevAndNext(
        int itemPricingSid, int itemMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = findByPrimaryKey(itemPricingSid);

        Session session = null;

        try {
            session = openSession();

            ItemPricing[] array = new ItemPricingImpl[3];

            array[0] = getByItemPricingDetails_PrevAndNext(session,
                    itemPricing, itemMasterSid, orderByComparator, true);

            array[1] = itemPricing;

            array[2] = getByItemPricingDetails_PrevAndNext(session,
                    itemPricing, itemMasterSid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemPricing getByItemPricingDetails_PrevAndNext(Session session,
        ItemPricing itemPricing, int itemMasterSid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMPRICING_WHERE);

        query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

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
            query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(itemMasterSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemPricing);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemPricing> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item pricings where itemMasterSid = &#63; from the database.
     *
     * @param itemMasterSid the item master sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemPricingDetails(int itemMasterSid)
        throws SystemException {
        for (ItemPricing itemPricing : findByItemPricingDetails(itemMasterSid,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemPricing);
        }
    }

    /**
     * Returns the number of item pricings where itemMasterSid = &#63;.
     *
     * @param itemMasterSid the item master sid
     * @return the number of matching item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemPricingDetails(int itemMasterSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS;

        Object[] finderArgs = new Object[] { itemMasterSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMPRICING_WHERE);

            query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

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
     * Caches the item pricing in the entity cache if it is enabled.
     *
     * @param itemPricing the item pricing
     */
    @Override
    public void cacheResult(ItemPricing itemPricing) {
        EntityCacheUtil.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingImpl.class, itemPricing.getPrimaryKey(), itemPricing);

        itemPricing.resetOriginalValues();
    }

    /**
     * Caches the item pricings in the entity cache if it is enabled.
     *
     * @param itemPricings the item pricings
     */
    @Override
    public void cacheResult(List<ItemPricing> itemPricings) {
        for (ItemPricing itemPricing : itemPricings) {
            if (EntityCacheUtil.getResult(
                        ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        ItemPricingImpl.class, itemPricing.getPrimaryKey()) == null) {
                cacheResult(itemPricing);
            } else {
                itemPricing.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item pricings.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemPricingImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemPricingImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item pricing.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemPricing itemPricing) {
        EntityCacheUtil.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingImpl.class, itemPricing.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ItemPricing> itemPricings) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemPricing itemPricing : itemPricings) {
            EntityCacheUtil.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                ItemPricingImpl.class, itemPricing.getPrimaryKey());
        }
    }

    /**
     * Creates a new item pricing with the primary key. Does not add the item pricing to the database.
     *
     * @param itemPricingSid the primary key for the new item pricing
     * @return the new item pricing
     */
    @Override
    public ItemPricing create(int itemPricingSid) {
        ItemPricing itemPricing = new ItemPricingImpl();

        itemPricing.setNew(true);
        itemPricing.setPrimaryKey(itemPricingSid);

        return itemPricing;
    }

    /**
     * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemPricingSid the primary key of the item pricing
     * @return the item pricing that was removed
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing remove(int itemPricingSid)
        throws NoSuchItemPricingException, SystemException {
        return remove((Serializable) itemPricingSid);
    }

    /**
     * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item pricing
     * @return the item pricing that was removed
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing remove(Serializable primaryKey)
        throws NoSuchItemPricingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemPricing itemPricing = (ItemPricing) session.get(ItemPricingImpl.class,
                    primaryKey);

            if (itemPricing == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemPricing);
        } catch (NoSuchItemPricingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemPricing removeImpl(ItemPricing itemPricing)
        throws SystemException {
        itemPricing = toUnwrappedModel(itemPricing);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemPricing)) {
                itemPricing = (ItemPricing) session.get(ItemPricingImpl.class,
                        itemPricing.getPrimaryKeyObj());
            }

            if (itemPricing != null) {
                session.delete(itemPricing);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemPricing != null) {
            clearCache(itemPricing);
        }

        return itemPricing;
    }

    @Override
    public ItemPricing updateImpl(com.stpl.app.model.ItemPricing itemPricing)
        throws SystemException {
        itemPricing = toUnwrappedModel(itemPricing);

        boolean isNew = itemPricing.isNew();

        ItemPricingModelImpl itemPricingModelImpl = (ItemPricingModelImpl) itemPricing;

        Session session = null;

        try {
            session = openSession();

            if (itemPricing.isNew()) {
                session.save(itemPricing);

                itemPricing.setNew(false);
            } else {
                session.merge(itemPricing);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemPricingModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((itemPricingModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemPricingModelImpl.getOriginalItemMasterSid(),
                        itemPricingModelImpl.getOriginalItemUom(),
                        itemPricingModelImpl.getOriginalItemPricingQualifierSid(),
                        itemPricingModelImpl.getOriginalPricingCodeStatus(),
                        itemPricingModelImpl.getOriginalStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICING,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING,
                    args);

                args = new Object[] {
                        itemPricingModelImpl.getItemMasterSid(),
                        itemPricingModelImpl.getItemUom(),
                        itemPricingModelImpl.getItemPricingQualifierSid(),
                        itemPricingModelImpl.getPricingCodeStatus(),
                        itemPricingModelImpl.getStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICING,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING,
                    args);
            }

            if ((itemPricingModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemPricingModelImpl.getOriginalItemMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS,
                    args);

                args = new Object[] { itemPricingModelImpl.getItemMasterSid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            ItemPricingImpl.class, itemPricing.getPrimaryKey(), itemPricing);

        return itemPricing;
    }

    protected ItemPricing toUnwrappedModel(ItemPricing itemPricing) {
        if (itemPricing instanceof ItemPricingImpl) {
            return itemPricing;
        }

        ItemPricingImpl itemPricingImpl = new ItemPricingImpl();

        itemPricingImpl.setNew(itemPricing.isNew());
        itemPricingImpl.setPrimaryKey(itemPricing.getPrimaryKey());

        itemPricingImpl.setItemMasterSid(itemPricing.getItemMasterSid());
        itemPricingImpl.setItemPricingQualifierSid(itemPricing.getItemPricingQualifierSid());
        itemPricingImpl.setItemPrice(itemPricing.getItemPrice());
        itemPricingImpl.setEndDate(itemPricing.getEndDate());
        itemPricingImpl.setModifiedDate(itemPricing.getModifiedDate());
        itemPricingImpl.setEntityCode(itemPricing.getEntityCode());
        itemPricingImpl.setRecordLockStatus(itemPricing.isRecordLockStatus());
        itemPricingImpl.setStartDate(itemPricing.getStartDate());
        itemPricingImpl.setCreatedDate(itemPricing.getCreatedDate());
        itemPricingImpl.setCreatedBy(itemPricing.getCreatedBy());
        itemPricingImpl.setSource(itemPricing.getSource());
        itemPricingImpl.setBatchId(itemPricing.getBatchId());
        itemPricingImpl.setItemUom(itemPricing.getItemUom());
        itemPricingImpl.setModifiedBy(itemPricing.getModifiedBy());
        itemPricingImpl.setInboundStatus(itemPricing.getInboundStatus());
        itemPricingImpl.setItemPricingSid(itemPricing.getItemPricingSid());
        itemPricingImpl.setPricingCodeStatus(itemPricing.getPricingCodeStatus());

        return itemPricingImpl;
    }

    /**
     * Returns the item pricing with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item pricing
     * @return the item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemPricingException, SystemException {
        ItemPricing itemPricing = fetchByPrimaryKey(primaryKey);

        if (itemPricing == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemPricing;
    }

    /**
     * Returns the item pricing with the primary key or throws a {@link com.stpl.app.NoSuchItemPricingException} if it could not be found.
     *
     * @param itemPricingSid the primary key of the item pricing
     * @return the item pricing
     * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing findByPrimaryKey(int itemPricingSid)
        throws NoSuchItemPricingException, SystemException {
        return findByPrimaryKey((Serializable) itemPricingSid);
    }

    /**
     * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item pricing
     * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemPricing itemPricing = (ItemPricing) EntityCacheUtil.getResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                ItemPricingImpl.class, primaryKey);

        if (itemPricing == _nullItemPricing) {
            return null;
        }

        if (itemPricing == null) {
            Session session = null;

            try {
                session = openSession();

                itemPricing = (ItemPricing) session.get(ItemPricingImpl.class,
                        primaryKey);

                if (itemPricing != null) {
                    cacheResult(itemPricing);
                } else {
                    EntityCacheUtil.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        ItemPricingImpl.class, primaryKey, _nullItemPricing);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                    ItemPricingImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemPricing;
    }

    /**
     * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemPricingSid the primary key of the item pricing
     * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemPricing fetchByPrimaryKey(int itemPricingSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemPricingSid);
    }

    /**
     * Returns all the item pricings.
     *
     * @return the item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @return the range of item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item pricings
     * @param end the upper bound of the range of item pricings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemPricing> findAll(int start, int end,
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

        List<ItemPricing> list = (List<ItemPricing>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMPRICING);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMPRICING;

                if (pagination) {
                    sql = sql.concat(ItemPricingModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemPricing>(list);
                } else {
                    list = (List<ItemPricing>) QueryUtil.list(q, getDialect(),
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
     * Removes all the item pricings from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemPricing itemPricing : findAll()) {
            remove(itemPricing);
        }
    }

    /**
     * Returns the number of item pricings.
     *
     * @return the number of item pricings
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

                Query q = session.createQuery(_SQL_COUNT_ITEMPRICING);

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
     * Initializes the item pricing persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemPricing")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemPricing>> listenersList = new ArrayList<ModelListener<ItemPricing>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemPricing>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemPricingImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
