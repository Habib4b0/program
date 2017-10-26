package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemHierarchyDefMasterException;
import com.stpl.app.model.ItemHierarchyDefMaster;
import com.stpl.app.model.impl.ItemHierarchyDefMasterImpl;
import com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl;
import com.stpl.app.service.persistence.ItemHierarchyDefMasterPersistence;

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
 * The persistence implementation for the item hierarchy def master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMasterPersistence
 * @see ItemHierarchyDefMasterUtil
 * @generated
 */
public class ItemHierarchyDefMasterPersistenceImpl extends BasePersistenceImpl<ItemHierarchyDefMaster>
    implements ItemHierarchyDefMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemHierarchyDefMasterUtil} to access the item hierarchy def master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemHierarchyDefMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MEMBER = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMember",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER =
        new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMember",
            new String[] { String.class.getName() },
            ItemHierarchyDefMasterModelImpl.MEMBER_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MEMBER = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMember",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MEMBER_MEMBER_1 = "itemHierarchyDefMaster.member IS NULL";
    private static final String _FINDER_COLUMN_MEMBER_MEMBER_2 = "itemHierarchyDefMaster.member = ?";
    private static final String _FINDER_COLUMN_MEMBER_MEMBER_3 = "(itemHierarchyDefMaster.member IS NULL OR itemHierarchyDefMaster.member = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAlias",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAlias",
            new String[] { String.class.getName() },
            ItemHierarchyDefMasterModelImpl.ALIAS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAlias",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ALIAS_ALIAS_1 = "itemHierarchyDefMaster.alias IS NULL";
    private static final String _FINDER_COLUMN_ALIAS_ALIAS_2 = "itemHierarchyDefMaster.alias = ?";
    private static final String _FINDER_COLUMN_ALIAS_ALIAS_3 = "(itemHierarchyDefMaster.alias IS NULL OR itemHierarchyDefMaster.alias = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BPILVL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBpiLvl",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL =
        new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBpiLvl",
            new String[] { String.class.getName() },
            ItemHierarchyDefMasterModelImpl.BPILVL_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BPILVL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBpiLvl",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_BPILVL_BPILVL_1 = "itemHierarchyDefMaster.bpiLvl IS NULL";
    private static final String _FINDER_COLUMN_BPILVL_BPILVL_2 = "itemHierarchyDefMaster.bpiLvl = ?";
    private static final String _FINDER_COLUMN_BPILVL_BPILVL_3 = "(itemHierarchyDefMaster.bpiLvl IS NULL OR itemHierarchyDefMaster.bpiLvl = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE =
        new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemHierarchyDefUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE =
        new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemHierarchyDefUnique",
            new String[] { String.class.getName(), String.class.getName() },
            ItemHierarchyDefMasterModelImpl.MEMBER_COLUMN_BITMASK |
            ItemHierarchyDefMasterModelImpl.ALIAS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemHierarchyDefUnique",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1 = "itemHierarchyDefMaster.member IS NULL AND ";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2 = "itemHierarchyDefMaster.member = ? AND ";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3 = "(itemHierarchyDefMaster.member IS NULL OR itemHierarchyDefMaster.member = '') AND ";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1 = "itemHierarchyDefMaster.alias IS NULL";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2 = "itemHierarchyDefMaster.alias = ?";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3 = "(itemHierarchyDefMaster.alias IS NULL OR itemHierarchyDefMaster.alias = '')";
    private static final String _SQL_SELECT_ITEMHIERARCHYDEFMASTER = "SELECT itemHierarchyDefMaster FROM ItemHierarchyDefMaster itemHierarchyDefMaster";
    private static final String _SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE = "SELECT itemHierarchyDefMaster FROM ItemHierarchyDefMaster itemHierarchyDefMaster WHERE ";
    private static final String _SQL_COUNT_ITEMHIERARCHYDEFMASTER = "SELECT COUNT(itemHierarchyDefMaster) FROM ItemHierarchyDefMaster itemHierarchyDefMaster";
    private static final String _SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE = "SELECT COUNT(itemHierarchyDefMaster) FROM ItemHierarchyDefMaster itemHierarchyDefMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemHierarchyDefMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemHierarchyDefMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemHierarchyDefMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemHierarchyDefMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemHierarchyDefMasterSid", "createdBy", "recordLockStatus",
                "modifiedBy", "createdDate", "alias", "source", "batchId",
                "bpiLvl", "modifiedDate", "member", "inboundStatus"
            });
    private static ItemHierarchyDefMaster _nullItemHierarchyDefMaster = new ItemHierarchyDefMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemHierarchyDefMaster> toCacheModel() {
                return _nullItemHierarchyDefMasterCacheModel;
            }
        };

    private static CacheModel<ItemHierarchyDefMaster> _nullItemHierarchyDefMasterCacheModel =
        new CacheModel<ItemHierarchyDefMaster>() {
            @Override
            public ItemHierarchyDefMaster toEntityModel() {
                return _nullItemHierarchyDefMaster;
            }
        };

    public ItemHierarchyDefMasterPersistenceImpl() {
        setModelClass(ItemHierarchyDefMaster.class);
    }

    /**
     * Returns all the item hierarchy def masters where member = &#63;.
     *
     * @param member the member
     * @return the matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByMember(String member)
        throws SystemException {
        return findByMember(member, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy def masters where member = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param member the member
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @return the range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByMember(String member, int start,
        int end) throws SystemException {
        return findByMember(member, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy def masters where member = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param member the member
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByMember(String member, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER;
            finderArgs = new Object[] { member };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MEMBER;
            finderArgs = new Object[] { member, start, end, orderByComparator };
        }

        List<ItemHierarchyDefMaster> list = (List<ItemHierarchyDefMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
                if (!Validator.equals(member, itemHierarchyDefMaster.getMember())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindMember = false;

            if (member == null) {
                query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
            } else if (member.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
            } else {
                bindMember = true;

                query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMember) {
                    qPos.add(member);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyDefMaster>(list);
                } else {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy def master in the ordered set where member = &#63;.
     *
     * @param member the member
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByMember_First(String member,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByMember_First(member,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("member=");
        msg.append(member);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy def master in the ordered set where member = &#63;.
     *
     * @param member the member
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByMember_First(String member,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyDefMaster> list = findByMember(member, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where member = &#63;.
     *
     * @param member the member
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByMember_Last(String member,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByMember_Last(member,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("member=");
        msg.append(member);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where member = &#63;.
     *
     * @param member the member
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByMember_Last(String member,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMember(member);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyDefMaster> list = findByMember(member, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63;.
     *
     * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
     * @param member the member
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster[] findByMember_PrevAndNext(
        int itemHierarchyDefMasterSid, String member,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

            array[0] = getByMember_PrevAndNext(session, itemHierarchyDefMaster,
                    member, orderByComparator, true);

            array[1] = itemHierarchyDefMaster;

            array[2] = getByMember_PrevAndNext(session, itemHierarchyDefMaster,
                    member, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyDefMaster getByMember_PrevAndNext(Session session,
        ItemHierarchyDefMaster itemHierarchyDefMaster, String member,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

        boolean bindMember = false;

        if (member == null) {
            query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
        } else if (member.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
        } else {
            bindMember = true;

            query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
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
            query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMember) {
            qPos.add(member);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyDefMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy def masters where member = &#63; from the database.
     *
     * @param member the member
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMember(String member) throws SystemException {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByMember(
                member, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyDefMaster);
        }
    }

    /**
     * Returns the number of item hierarchy def masters where member = &#63;.
     *
     * @param member the member
     * @return the number of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMember(String member) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MEMBER;

        Object[] finderArgs = new Object[] { member };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindMember = false;

            if (member == null) {
                query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
            } else if (member.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
            } else {
                bindMember = true;

                query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMember) {
                    qPos.add(member);
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
     * Returns all the item hierarchy def masters where alias = &#63;.
     *
     * @param alias the alias
     * @return the matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByAlias(String alias)
        throws SystemException {
        return findByAlias(alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy def masters where alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param alias the alias
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @return the range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByAlias(String alias, int start,
        int end) throws SystemException {
        return findByAlias(alias, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param alias the alias
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByAlias(String alias, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS;
            finderArgs = new Object[] { alias };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALIAS;
            finderArgs = new Object[] { alias, start, end, orderByComparator };
        }

        List<ItemHierarchyDefMaster> list = (List<ItemHierarchyDefMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
                if (!Validator.equals(alias, itemHierarchyDefMaster.getAlias())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindAlias = false;

            if (alias == null) {
                query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
            } else if (alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
            } else {
                bindAlias = true;

                query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAlias) {
                    qPos.add(alias);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyDefMaster>(list);
                } else {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
     *
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByAlias_First(String alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByAlias_First(alias,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("alias=");
        msg.append(alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
     *
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByAlias_First(String alias,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyDefMaster> list = findByAlias(alias, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
     *
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByAlias_Last(String alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByAlias_Last(alias,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("alias=");
        msg.append(alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
     *
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByAlias_Last(String alias,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAlias(alias);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyDefMaster> list = findByAlias(alias, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where alias = &#63;.
     *
     * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
        int itemHierarchyDefMasterSid, String alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

            array[0] = getByAlias_PrevAndNext(session, itemHierarchyDefMaster,
                    alias, orderByComparator, true);

            array[1] = itemHierarchyDefMaster;

            array[2] = getByAlias_PrevAndNext(session, itemHierarchyDefMaster,
                    alias, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyDefMaster getByAlias_PrevAndNext(Session session,
        ItemHierarchyDefMaster itemHierarchyDefMaster, String alias,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

        boolean bindAlias = false;

        if (alias == null) {
            query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
        } else if (alias.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
        } else {
            bindAlias = true;

            query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
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
            query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAlias) {
            qPos.add(alias);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyDefMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy def masters where alias = &#63; from the database.
     *
     * @param alias the alias
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAlias(String alias) throws SystemException {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByAlias(
                alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyDefMaster);
        }
    }

    /**
     * Returns the number of item hierarchy def masters where alias = &#63;.
     *
     * @param alias the alias
     * @return the number of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAlias(String alias) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ALIAS;

        Object[] finderArgs = new Object[] { alias };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindAlias = false;

            if (alias == null) {
                query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
            } else if (alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
            } else {
                bindAlias = true;

                query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAlias) {
                    qPos.add(alias);
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
     * Returns all the item hierarchy def masters where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @return the matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl)
        throws SystemException {
        return findByBpiLvl(bpiLvl, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy def masters where bpiLvl = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param bpiLvl the bpi lvl
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @return the range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl, int start,
        int end) throws SystemException {
        return findByBpiLvl(bpiLvl, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param bpiLvl the bpi lvl
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL;
            finderArgs = new Object[] { bpiLvl };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BPILVL;
            finderArgs = new Object[] { bpiLvl, start, end, orderByComparator };
        }

        List<ItemHierarchyDefMaster> list = (List<ItemHierarchyDefMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
                if (!Validator.equals(bpiLvl, itemHierarchyDefMaster.getBpiLvl())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindBpiLvl = false;

            if (bpiLvl == null) {
                query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
            } else if (bpiLvl.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
            } else {
                bindBpiLvl = true;

                query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBpiLvl) {
                    qPos.add(bpiLvl);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyDefMaster>(list);
                } else {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByBpiLvl_First(String bpiLvl,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByBpiLvl_First(bpiLvl,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("bpiLvl=");
        msg.append(bpiLvl);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByBpiLvl_First(String bpiLvl,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyDefMaster> list = findByBpiLvl(bpiLvl, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByBpiLvl_Last(String bpiLvl,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByBpiLvl_Last(bpiLvl,
                orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("bpiLvl=");
        msg.append(bpiLvl);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByBpiLvl_Last(String bpiLvl,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByBpiLvl(bpiLvl);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyDefMaster> list = findByBpiLvl(bpiLvl, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where bpiLvl = &#63;.
     *
     * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
     * @param bpiLvl the bpi lvl
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
        int itemHierarchyDefMasterSid, String bpiLvl,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

            array[0] = getByBpiLvl_PrevAndNext(session, itemHierarchyDefMaster,
                    bpiLvl, orderByComparator, true);

            array[1] = itemHierarchyDefMaster;

            array[2] = getByBpiLvl_PrevAndNext(session, itemHierarchyDefMaster,
                    bpiLvl, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyDefMaster getByBpiLvl_PrevAndNext(Session session,
        ItemHierarchyDefMaster itemHierarchyDefMaster, String bpiLvl,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

        boolean bindBpiLvl = false;

        if (bpiLvl == null) {
            query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
        } else if (bpiLvl.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
        } else {
            bindBpiLvl = true;

            query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
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
            query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindBpiLvl) {
            qPos.add(bpiLvl);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyDefMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
     *
     * @param bpiLvl the bpi lvl
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBpiLvl(String bpiLvl) throws SystemException {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByBpiLvl(
                bpiLvl, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyDefMaster);
        }
    }

    /**
     * Returns the number of item hierarchy def masters where bpiLvl = &#63;.
     *
     * @param bpiLvl the bpi lvl
     * @return the number of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBpiLvl(String bpiLvl) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BPILVL;

        Object[] finderArgs = new Object[] { bpiLvl };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindBpiLvl = false;

            if (bpiLvl == null) {
                query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
            } else if (bpiLvl.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
            } else {
                bindBpiLvl = true;

                query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBpiLvl) {
                    qPos.add(bpiLvl);
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
     * Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @return the matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        String member, String alias) throws SystemException {
        return findByItemHierarchyDefUnique(member, alias, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param member the member
     * @param alias the alias
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @return the range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        String member, String alias, int start, int end)
        throws SystemException {
        return findByItemHierarchyDefUnique(member, alias, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param member the member
     * @param alias the alias
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        String member, String alias, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE;
            finderArgs = new Object[] { member, alias };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE;
            finderArgs = new Object[] {
                    member, alias,
                    
                    start, end, orderByComparator
                };
        }

        List<ItemHierarchyDefMaster> list = (List<ItemHierarchyDefMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
                if (!Validator.equals(member, itemHierarchyDefMaster.getMember()) ||
                        !Validator.equals(alias,
                            itemHierarchyDefMaster.getAlias())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindMember = false;

            if (member == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
            } else if (member.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
            } else {
                bindMember = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
            }

            boolean bindAlias = false;

            if (alias == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
            } else if (alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
            } else {
                bindAlias = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMember) {
                    qPos.add(member);
                }

                if (bindAlias) {
                    qPos.add(alias);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyDefMaster>(list);
                } else {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
        String member, String alias, OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByItemHierarchyDefUnique_First(member,
                alias, orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("member=");
        msg.append(member);

        msg.append(", alias=");
        msg.append(alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
        String member, String alias, OrderByComparator orderByComparator)
        throws SystemException {
        List<ItemHierarchyDefMaster> list = findByItemHierarchyDefUnique(member,
                alias, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
        String member, String alias, OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByItemHierarchyDefUnique_Last(member,
                alias, orderByComparator);

        if (itemHierarchyDefMaster != null) {
            return itemHierarchyDefMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("member=");
        msg.append(member);

        msg.append(", alias=");
        msg.append(alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyDefMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
        String member, String alias, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByItemHierarchyDefUnique(member, alias);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyDefMaster> list = findByItemHierarchyDefUnique(member,
                alias, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
     *
     * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
     * @param member the member
     * @param alias the alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
        int itemHierarchyDefMasterSid, String member, String alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

            array[0] = getByItemHierarchyDefUnique_PrevAndNext(session,
                    itemHierarchyDefMaster, member, alias, orderByComparator,
                    true);

            array[1] = itemHierarchyDefMaster;

            array[2] = getByItemHierarchyDefUnique_PrevAndNext(session,
                    itemHierarchyDefMaster, member, alias, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyDefMaster getByItemHierarchyDefUnique_PrevAndNext(
        Session session, ItemHierarchyDefMaster itemHierarchyDefMaster,
        String member, String alias, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

        boolean bindMember = false;

        if (member == null) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
        } else if (member.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
        } else {
            bindMember = true;

            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
        }

        boolean bindAlias = false;

        if (alias == null) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
        } else if (alias.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
        } else {
            bindAlias = true;

            query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
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
            query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMember) {
            qPos.add(member);
        }

        if (bindAlias) {
            qPos.add(alias);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyDefMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
     *
     * @param member the member
     * @param alias the alias
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemHierarchyDefUnique(String member, String alias)
        throws SystemException {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByItemHierarchyDefUnique(
                member, alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyDefMaster);
        }
    }

    /**
     * Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
     *
     * @param member the member
     * @param alias the alias
     * @return the number of matching item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemHierarchyDefUnique(String member, String alias)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE;

        Object[] finderArgs = new Object[] { member, alias };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

            boolean bindMember = false;

            if (member == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
            } else if (member.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
            } else {
                bindMember = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
            }

            boolean bindAlias = false;

            if (alias == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
            } else if (alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
            } else {
                bindAlias = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMember) {
                    qPos.add(member);
                }

                if (bindAlias) {
                    qPos.add(alias);
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
     * Caches the item hierarchy def master in the entity cache if it is enabled.
     *
     * @param itemHierarchyDefMaster the item hierarchy def master
     */
    @Override
    public void cacheResult(ItemHierarchyDefMaster itemHierarchyDefMaster) {
        EntityCacheUtil.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            itemHierarchyDefMaster.getPrimaryKey(), itemHierarchyDefMaster);

        itemHierarchyDefMaster.resetOriginalValues();
    }

    /**
     * Caches the item hierarchy def masters in the entity cache if it is enabled.
     *
     * @param itemHierarchyDefMasters the item hierarchy def masters
     */
    @Override
    public void cacheResult(
        List<ItemHierarchyDefMaster> itemHierarchyDefMasters) {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : itemHierarchyDefMasters) {
            if (EntityCacheUtil.getResult(
                        ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ItemHierarchyDefMasterImpl.class,
                        itemHierarchyDefMaster.getPrimaryKey()) == null) {
                cacheResult(itemHierarchyDefMaster);
            } else {
                itemHierarchyDefMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item hierarchy def masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemHierarchyDefMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemHierarchyDefMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item hierarchy def master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemHierarchyDefMaster itemHierarchyDefMaster) {
        EntityCacheUtil.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            itemHierarchyDefMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ItemHierarchyDefMaster> itemHierarchyDefMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemHierarchyDefMaster itemHierarchyDefMaster : itemHierarchyDefMasters) {
            EntityCacheUtil.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
                ItemHierarchyDefMasterImpl.class,
                itemHierarchyDefMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
     *
     * @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
     * @return the new item hierarchy def master
     */
    @Override
    public ItemHierarchyDefMaster create(int itemHierarchyDefMasterSid) {
        ItemHierarchyDefMaster itemHierarchyDefMaster = new ItemHierarchyDefMasterImpl();

        itemHierarchyDefMaster.setNew(true);
        itemHierarchyDefMaster.setPrimaryKey(itemHierarchyDefMasterSid);

        return itemHierarchyDefMaster;
    }

    /**
     * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
     * @return the item hierarchy def master that was removed
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster remove(int itemHierarchyDefMasterSid)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        return remove((Serializable) itemHierarchyDefMasterSid);
    }

    /**
     * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item hierarchy def master
     * @return the item hierarchy def master that was removed
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster remove(Serializable primaryKey)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemHierarchyDefMaster itemHierarchyDefMaster = (ItemHierarchyDefMaster) session.get(ItemHierarchyDefMasterImpl.class,
                    primaryKey);

            if (itemHierarchyDefMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemHierarchyDefMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemHierarchyDefMaster);
        } catch (NoSuchItemHierarchyDefMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemHierarchyDefMaster removeImpl(
        ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws SystemException {
        itemHierarchyDefMaster = toUnwrappedModel(itemHierarchyDefMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemHierarchyDefMaster)) {
                itemHierarchyDefMaster = (ItemHierarchyDefMaster) session.get(ItemHierarchyDefMasterImpl.class,
                        itemHierarchyDefMaster.getPrimaryKeyObj());
            }

            if (itemHierarchyDefMaster != null) {
                session.delete(itemHierarchyDefMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemHierarchyDefMaster != null) {
            clearCache(itemHierarchyDefMaster);
        }

        return itemHierarchyDefMaster;
    }

    @Override
    public ItemHierarchyDefMaster updateImpl(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws SystemException {
        itemHierarchyDefMaster = toUnwrappedModel(itemHierarchyDefMaster);

        boolean isNew = itemHierarchyDefMaster.isNew();

        ItemHierarchyDefMasterModelImpl itemHierarchyDefMasterModelImpl = (ItemHierarchyDefMasterModelImpl) itemHierarchyDefMaster;

        Session session = null;

        try {
            session = openSession();

            if (itemHierarchyDefMaster.isNew()) {
                session.save(itemHierarchyDefMaster);

                itemHierarchyDefMaster.setNew(false);
            } else {
                session.merge(itemHierarchyDefMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemHierarchyDefMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyDefMasterModelImpl.getOriginalMember()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEMBER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER,
                    args);

                args = new Object[] { itemHierarchyDefMasterModelImpl.getMember() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEMBER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER,
                    args);
            }

            if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyDefMasterModelImpl.getOriginalAlias()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALIAS, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS,
                    args);

                args = new Object[] { itemHierarchyDefMasterModelImpl.getAlias() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALIAS, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS,
                    args);
            }

            if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyDefMasterModelImpl.getOriginalBpiLvl()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BPILVL, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL,
                    args);

                args = new Object[] { itemHierarchyDefMasterModelImpl.getBpiLvl() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BPILVL, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL,
                    args);
            }

            if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyDefMasterModelImpl.getOriginalMember(),
                        itemHierarchyDefMasterModelImpl.getOriginalAlias()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE,
                    args);

                args = new Object[] {
                        itemHierarchyDefMasterModelImpl.getMember(),
                        itemHierarchyDefMasterModelImpl.getAlias()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyDefMasterImpl.class,
            itemHierarchyDefMaster.getPrimaryKey(), itemHierarchyDefMaster);

        return itemHierarchyDefMaster;
    }

    protected ItemHierarchyDefMaster toUnwrappedModel(
        ItemHierarchyDefMaster itemHierarchyDefMaster) {
        if (itemHierarchyDefMaster instanceof ItemHierarchyDefMasterImpl) {
            return itemHierarchyDefMaster;
        }

        ItemHierarchyDefMasterImpl itemHierarchyDefMasterImpl = new ItemHierarchyDefMasterImpl();

        itemHierarchyDefMasterImpl.setNew(itemHierarchyDefMaster.isNew());
        itemHierarchyDefMasterImpl.setPrimaryKey(itemHierarchyDefMaster.getPrimaryKey());

        itemHierarchyDefMasterImpl.setItemHierarchyDefMasterSid(itemHierarchyDefMaster.getItemHierarchyDefMasterSid());
        itemHierarchyDefMasterImpl.setCreatedBy(itemHierarchyDefMaster.getCreatedBy());
        itemHierarchyDefMasterImpl.setRecordLockStatus(itemHierarchyDefMaster.isRecordLockStatus());
        itemHierarchyDefMasterImpl.setModifiedBy(itemHierarchyDefMaster.getModifiedBy());
        itemHierarchyDefMasterImpl.setCreatedDate(itemHierarchyDefMaster.getCreatedDate());
        itemHierarchyDefMasterImpl.setAlias(itemHierarchyDefMaster.getAlias());
        itemHierarchyDefMasterImpl.setSource(itemHierarchyDefMaster.getSource());
        itemHierarchyDefMasterImpl.setBatchId(itemHierarchyDefMaster.getBatchId());
        itemHierarchyDefMasterImpl.setBpiLvl(itemHierarchyDefMaster.getBpiLvl());
        itemHierarchyDefMasterImpl.setModifiedDate(itemHierarchyDefMaster.getModifiedDate());
        itemHierarchyDefMasterImpl.setMember(itemHierarchyDefMaster.getMember());
        itemHierarchyDefMasterImpl.setInboundStatus(itemHierarchyDefMaster.getInboundStatus());

        return itemHierarchyDefMasterImpl;
    }

    /**
     * Returns the item hierarchy def master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item hierarchy def master
     * @return the item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByPrimaryKey(primaryKey);

        if (itemHierarchyDefMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemHierarchyDefMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemHierarchyDefMaster;
    }

    /**
     * Returns the item hierarchy def master with the primary key or throws a {@link com.stpl.app.NoSuchItemHierarchyDefMasterException} if it could not be found.
     *
     * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
     * @return the item hierarchy def master
     * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster findByPrimaryKey(
        int itemHierarchyDefMasterSid)
        throws NoSuchItemHierarchyDefMasterException, SystemException {
        return findByPrimaryKey((Serializable) itemHierarchyDefMasterSid);
    }

    /**
     * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item hierarchy def master
     * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemHierarchyDefMaster itemHierarchyDefMaster = (ItemHierarchyDefMaster) EntityCacheUtil.getResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
                ItemHierarchyDefMasterImpl.class, primaryKey);

        if (itemHierarchyDefMaster == _nullItemHierarchyDefMaster) {
            return null;
        }

        if (itemHierarchyDefMaster == null) {
            Session session = null;

            try {
                session = openSession();

                itemHierarchyDefMaster = (ItemHierarchyDefMaster) session.get(ItemHierarchyDefMasterImpl.class,
                        primaryKey);

                if (itemHierarchyDefMaster != null) {
                    cacheResult(itemHierarchyDefMaster);
                } else {
                    EntityCacheUtil.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ItemHierarchyDefMasterImpl.class, primaryKey,
                        _nullItemHierarchyDefMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ItemHierarchyDefMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemHierarchyDefMaster;
    }

    /**
     * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
     * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyDefMaster fetchByPrimaryKey(
        int itemHierarchyDefMasterSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) itemHierarchyDefMasterSid);
    }

    /**
     * Returns all the item hierarchy def masters.
     *
     * @return the item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy def masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @return the range of item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy def masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item hierarchy def masters
     * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item hierarchy def masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyDefMaster> findAll(int start, int end,
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

        List<ItemHierarchyDefMaster> list = (List<ItemHierarchyDefMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMHIERARCHYDEFMASTER;

                if (pagination) {
                    sql = sql.concat(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyDefMaster>(list);
                } else {
                    list = (List<ItemHierarchyDefMaster>) QueryUtil.list(q,
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
     * Removes all the item hierarchy def masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemHierarchyDefMaster itemHierarchyDefMaster : findAll()) {
            remove(itemHierarchyDefMaster);
        }
    }

    /**
     * Returns the number of item hierarchy def masters.
     *
     * @return the number of item hierarchy def masters
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

                Query q = session.createQuery(_SQL_COUNT_ITEMHIERARCHYDEFMASTER);

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
     * Initializes the item hierarchy def master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemHierarchyDefMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemHierarchyDefMaster>> listenersList = new ArrayList<ModelListener<ItemHierarchyDefMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemHierarchyDefMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemHierarchyDefMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
