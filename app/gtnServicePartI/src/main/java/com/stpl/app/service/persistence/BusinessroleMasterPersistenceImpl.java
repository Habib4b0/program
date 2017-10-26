package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchBusinessroleMasterException;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.impl.BusinessroleMasterImpl;
import com.stpl.app.model.impl.BusinessroleMasterModelImpl;
import com.stpl.app.service.persistence.BusinessroleMasterPersistence;

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
 * The persistence implementation for the businessrole master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleMasterPersistence
 * @see BusinessroleMasterUtil
 * @generated
 */
public class BusinessroleMasterPersistenceImpl extends BasePersistenceImpl<BusinessroleMaster>
    implements BusinessroleMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BusinessroleMasterUtil} to access the businessrole master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BusinessroleMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLENAME =
        new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBusinessroleName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME =
        new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByBusinessroleName", new String[] { String.class.getName() },
            BusinessroleMasterModelImpl.BUSINESSROLENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BUSINESSROLENAME = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByBusinessroleName", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1 =
        "businessroleMaster.businessroleName IS NULL";
    private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2 =
        "businessroleMaster.businessroleName = ?";
    private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3 =
        "(businessroleMaster.businessroleName IS NULL OR businessroleMaster.businessroleName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE =
        new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBusinessRoleUnique",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE =
        new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByBusinessRoleUnique",
            new String[] { String.class.getName() },
            BusinessroleMasterModelImpl.BUSINESSROLENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByBusinessRoleUnique", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1 =
        "businessroleMaster.businessroleName IS NULL";
    private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2 =
        "businessroleMaster.businessroleName = ?";
    private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3 =
        "(businessroleMaster.businessroleName IS NULL OR businessroleMaster.businessroleName = '')";
    private static final String _SQL_SELECT_BUSINESSROLEMASTER = "SELECT businessroleMaster FROM BusinessroleMaster businessroleMaster";
    private static final String _SQL_SELECT_BUSINESSROLEMASTER_WHERE = "SELECT businessroleMaster FROM BusinessroleMaster businessroleMaster WHERE ";
    private static final String _SQL_COUNT_BUSINESSROLEMASTER = "SELECT COUNT(businessroleMaster) FROM BusinessroleMaster businessroleMaster";
    private static final String _SQL_COUNT_BUSINESSROLEMASTER_WHERE = "SELECT COUNT(businessroleMaster) FROM BusinessroleMaster businessroleMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "businessroleMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BusinessroleMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BusinessroleMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BusinessroleMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "businessroleMasterSid", "createdDate", "createdBy", "usersSid",
                "hierarchyLevel", "processed", "businessroleName", "versionNo",
                "modifiedBy", "modifiedDate", "businessroleDesc", "isActive"
            });
    private static BusinessroleMaster _nullBusinessroleMaster = new BusinessroleMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BusinessroleMaster> toCacheModel() {
                return _nullBusinessroleMasterCacheModel;
            }
        };

    private static CacheModel<BusinessroleMaster> _nullBusinessroleMasterCacheModel =
        new CacheModel<BusinessroleMaster>() {
            @Override
            public BusinessroleMaster toEntityModel() {
                return _nullBusinessroleMaster;
            }
        };

    public BusinessroleMasterPersistenceImpl() {
        setModelClass(BusinessroleMaster.class);
    }

    /**
     * Returns all the businessrole masters where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @return the matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessroleName(
        String businessroleName) throws SystemException {
        return findByBusinessroleName(businessroleName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the businessrole masters where businessroleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param businessroleName the businessrole name
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @return the range of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessroleName(
        String businessroleName, int start, int end) throws SystemException {
        return findByBusinessroleName(businessroleName, start, end, null);
    }

    /**
     * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param businessroleName the businessrole name
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessroleName(
        String businessroleName, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME;
            finderArgs = new Object[] { businessroleName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLENAME;
            finderArgs = new Object[] {
                    businessroleName,
                    
                    start, end, orderByComparator
                };
        }

        List<BusinessroleMaster> list = (List<BusinessroleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BusinessroleMaster businessroleMaster : list) {
                if (!Validator.equals(businessroleName,
                            businessroleMaster.getBusinessroleName())) {
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

            query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

            boolean bindBusinessroleName = false;

            if (businessroleName == null) {
                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
            } else if (businessroleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
            } else {
                bindBusinessroleName = true;

                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBusinessroleName) {
                    qPos.add(businessroleName);
                }

                if (!pagination) {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BusinessroleMaster>(list);
                } else {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
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
     * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByBusinessroleName_First(
        String businessroleName, OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = fetchByBusinessroleName_First(businessroleName,
                orderByComparator);

        if (businessroleMaster != null) {
            return businessroleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("businessroleName=");
        msg.append(businessroleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBusinessroleMasterException(msg.toString());
    }

    /**
     * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByBusinessroleName_First(
        String businessroleName, OrderByComparator orderByComparator)
        throws SystemException {
        List<BusinessroleMaster> list = findByBusinessroleName(businessroleName,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByBusinessroleName_Last(
        String businessroleName, OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = fetchByBusinessroleName_Last(businessroleName,
                orderByComparator);

        if (businessroleMaster != null) {
            return businessroleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("businessroleName=");
        msg.append(businessroleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBusinessroleMasterException(msg.toString());
    }

    /**
     * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByBusinessroleName_Last(
        String businessroleName, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByBusinessroleName(businessroleName);

        if (count == 0) {
            return null;
        }

        List<BusinessroleMaster> list = findByBusinessroleName(businessroleName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleMasterSid the primary key of the current businessrole master
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
        int businessroleMasterSid, String businessroleName,
        OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = findByPrimaryKey(businessroleMasterSid);

        Session session = null;

        try {
            session = openSession();

            BusinessroleMaster[] array = new BusinessroleMasterImpl[3];

            array[0] = getByBusinessroleName_PrevAndNext(session,
                    businessroleMaster, businessroleName, orderByComparator,
                    true);

            array[1] = businessroleMaster;

            array[2] = getByBusinessroleName_PrevAndNext(session,
                    businessroleMaster, businessroleName, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BusinessroleMaster getByBusinessroleName_PrevAndNext(
        Session session, BusinessroleMaster businessroleMaster,
        String businessroleName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

        boolean bindBusinessroleName = false;

        if (businessroleName == null) {
            query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
        } else if (businessroleName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
        } else {
            bindBusinessroleName = true;

            query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
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
            query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindBusinessroleName) {
            qPos.add(businessroleName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(businessroleMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BusinessroleMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the businessrole masters where businessroleName = &#63; from the database.
     *
     * @param businessroleName the businessrole name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBusinessroleName(String businessroleName)
        throws SystemException {
        for (BusinessroleMaster businessroleMaster : findByBusinessroleName(
                businessroleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(businessroleMaster);
        }
    }

    /**
     * Returns the number of businessrole masters where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @return the number of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBusinessroleName(String businessroleName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BUSINESSROLENAME;

        Object[] finderArgs = new Object[] { businessroleName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BUSINESSROLEMASTER_WHERE);

            boolean bindBusinessroleName = false;

            if (businessroleName == null) {
                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
            } else if (businessroleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
            } else {
                bindBusinessroleName = true;

                query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBusinessroleName) {
                    qPos.add(businessroleName);
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
     * Returns all the businessrole masters where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @return the matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessRoleUnique(
        String businessroleName) throws SystemException {
        return findByBusinessRoleUnique(businessroleName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the businessrole masters where businessroleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param businessroleName the businessrole name
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @return the range of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessRoleUnique(
        String businessroleName, int start, int end) throws SystemException {
        return findByBusinessRoleUnique(businessroleName, start, end, null);
    }

    /**
     * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param businessroleName the businessrole name
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findByBusinessRoleUnique(
        String businessroleName, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE;
            finderArgs = new Object[] { businessroleName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE;
            finderArgs = new Object[] {
                    businessroleName,
                    
                    start, end, orderByComparator
                };
        }

        List<BusinessroleMaster> list = (List<BusinessroleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BusinessroleMaster businessroleMaster : list) {
                if (!Validator.equals(businessroleName,
                            businessroleMaster.getBusinessroleName())) {
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

            query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

            boolean bindBusinessroleName = false;

            if (businessroleName == null) {
                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
            } else if (businessroleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
            } else {
                bindBusinessroleName = true;

                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBusinessroleName) {
                    qPos.add(businessroleName);
                }

                if (!pagination) {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BusinessroleMaster>(list);
                } else {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
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
     * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByBusinessRoleUnique_First(
        String businessroleName, OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = fetchByBusinessRoleUnique_First(businessroleName,
                orderByComparator);

        if (businessroleMaster != null) {
            return businessroleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("businessroleName=");
        msg.append(businessroleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBusinessroleMasterException(msg.toString());
    }

    /**
     * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByBusinessRoleUnique_First(
        String businessroleName, OrderByComparator orderByComparator)
        throws SystemException {
        List<BusinessroleMaster> list = findByBusinessRoleUnique(businessroleName,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByBusinessRoleUnique_Last(
        String businessroleName, OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = fetchByBusinessRoleUnique_Last(businessroleName,
                orderByComparator);

        if (businessroleMaster != null) {
            return businessroleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("businessroleName=");
        msg.append(businessroleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBusinessroleMasterException(msg.toString());
    }

    /**
     * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByBusinessRoleUnique_Last(
        String businessroleName, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByBusinessRoleUnique(businessroleName);

        if (count == 0) {
            return null;
        }

        List<BusinessroleMaster> list = findByBusinessRoleUnique(businessroleName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
     *
     * @param businessroleMasterSid the primary key of the current businessrole master
     * @param businessroleName the businessrole name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
        int businessroleMasterSid, String businessroleName,
        OrderByComparator orderByComparator)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = findByPrimaryKey(businessroleMasterSid);

        Session session = null;

        try {
            session = openSession();

            BusinessroleMaster[] array = new BusinessroleMasterImpl[3];

            array[0] = getByBusinessRoleUnique_PrevAndNext(session,
                    businessroleMaster, businessroleName, orderByComparator,
                    true);

            array[1] = businessroleMaster;

            array[2] = getByBusinessRoleUnique_PrevAndNext(session,
                    businessroleMaster, businessroleName, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BusinessroleMaster getByBusinessRoleUnique_PrevAndNext(
        Session session, BusinessroleMaster businessroleMaster,
        String businessroleName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

        boolean bindBusinessroleName = false;

        if (businessroleName == null) {
            query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
        } else if (businessroleName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
        } else {
            bindBusinessroleName = true;

            query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
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
            query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindBusinessroleName) {
            qPos.add(businessroleName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(businessroleMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BusinessroleMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the businessrole masters where businessroleName = &#63; from the database.
     *
     * @param businessroleName the businessrole name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBusinessRoleUnique(String businessroleName)
        throws SystemException {
        for (BusinessroleMaster businessroleMaster : findByBusinessRoleUnique(
                businessroleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(businessroleMaster);
        }
    }

    /**
     * Returns the number of businessrole masters where businessroleName = &#63;.
     *
     * @param businessroleName the businessrole name
     * @return the number of matching businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBusinessRoleUnique(String businessroleName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE;

        Object[] finderArgs = new Object[] { businessroleName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BUSINESSROLEMASTER_WHERE);

            boolean bindBusinessroleName = false;

            if (businessroleName == null) {
                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
            } else if (businessroleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
            } else {
                bindBusinessroleName = true;

                query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBusinessroleName) {
                    qPos.add(businessroleName);
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
     * Caches the businessrole master in the entity cache if it is enabled.
     *
     * @param businessroleMaster the businessrole master
     */
    @Override
    public void cacheResult(BusinessroleMaster businessroleMaster) {
        EntityCacheUtil.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey(),
            businessroleMaster);

        businessroleMaster.resetOriginalValues();
    }

    /**
     * Caches the businessrole masters in the entity cache if it is enabled.
     *
     * @param businessroleMasters the businessrole masters
     */
    @Override
    public void cacheResult(List<BusinessroleMaster> businessroleMasters) {
        for (BusinessroleMaster businessroleMaster : businessroleMasters) {
            if (EntityCacheUtil.getResult(
                        BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BusinessroleMasterImpl.class,
                        businessroleMaster.getPrimaryKey()) == null) {
                cacheResult(businessroleMaster);
            } else {
                businessroleMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all businessrole masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BusinessroleMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BusinessroleMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the businessrole master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BusinessroleMaster businessroleMaster) {
        EntityCacheUtil.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BusinessroleMaster> businessroleMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BusinessroleMaster businessroleMaster : businessroleMasters) {
            EntityCacheUtil.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
                BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
     *
     * @param businessroleMasterSid the primary key for the new businessrole master
     * @return the new businessrole master
     */
    @Override
    public BusinessroleMaster create(int businessroleMasterSid) {
        BusinessroleMaster businessroleMaster = new BusinessroleMasterImpl();

        businessroleMaster.setNew(true);
        businessroleMaster.setPrimaryKey(businessroleMasterSid);

        return businessroleMaster;
    }

    /**
     * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param businessroleMasterSid the primary key of the businessrole master
     * @return the businessrole master that was removed
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster remove(int businessroleMasterSid)
        throws NoSuchBusinessroleMasterException, SystemException {
        return remove((Serializable) businessroleMasterSid);
    }

    /**
     * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the businessrole master
     * @return the businessrole master that was removed
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster remove(Serializable primaryKey)
        throws NoSuchBusinessroleMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BusinessroleMaster businessroleMaster = (BusinessroleMaster) session.get(BusinessroleMasterImpl.class,
                    primaryKey);

            if (businessroleMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBusinessroleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(businessroleMaster);
        } catch (NoSuchBusinessroleMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BusinessroleMaster removeImpl(
        BusinessroleMaster businessroleMaster) throws SystemException {
        businessroleMaster = toUnwrappedModel(businessroleMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(businessroleMaster)) {
                businessroleMaster = (BusinessroleMaster) session.get(BusinessroleMasterImpl.class,
                        businessroleMaster.getPrimaryKeyObj());
            }

            if (businessroleMaster != null) {
                session.delete(businessroleMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (businessroleMaster != null) {
            clearCache(businessroleMaster);
        }

        return businessroleMaster;
    }

    @Override
    public BusinessroleMaster updateImpl(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws SystemException {
        businessroleMaster = toUnwrappedModel(businessroleMaster);

        boolean isNew = businessroleMaster.isNew();

        BusinessroleMasterModelImpl businessroleMasterModelImpl = (BusinessroleMasterModelImpl) businessroleMaster;

        Session session = null;

        try {
            session = openSession();

            if (businessroleMaster.isNew()) {
                session.save(businessroleMaster);

                businessroleMaster.setNew(false);
            } else {
                session.merge(businessroleMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !BusinessroleMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((businessroleMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        businessroleMasterModelImpl.getOriginalBusinessroleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME,
                    args);

                args = new Object[] {
                        businessroleMasterModelImpl.getBusinessroleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME,
                    args);
            }

            if ((businessroleMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        businessroleMasterModelImpl.getOriginalBusinessroleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE,
                    args);

                args = new Object[] {
                        businessroleMasterModelImpl.getBusinessroleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey(),
            businessroleMaster);

        return businessroleMaster;
    }

    protected BusinessroleMaster toUnwrappedModel(
        BusinessroleMaster businessroleMaster) {
        if (businessroleMaster instanceof BusinessroleMasterImpl) {
            return businessroleMaster;
        }

        BusinessroleMasterImpl businessroleMasterImpl = new BusinessroleMasterImpl();

        businessroleMasterImpl.setNew(businessroleMaster.isNew());
        businessroleMasterImpl.setPrimaryKey(businessroleMaster.getPrimaryKey());

        businessroleMasterImpl.setBusinessroleMasterSid(businessroleMaster.getBusinessroleMasterSid());
        businessroleMasterImpl.setCreatedDate(businessroleMaster.getCreatedDate());
        businessroleMasterImpl.setCreatedBy(businessroleMaster.getCreatedBy());
        businessroleMasterImpl.setUsersSid(businessroleMaster.getUsersSid());
        businessroleMasterImpl.setHierarchyLevel(businessroleMaster.getHierarchyLevel());
        businessroleMasterImpl.setProcessed(businessroleMaster.getProcessed());
        businessroleMasterImpl.setBusinessroleName(businessroleMaster.getBusinessroleName());
        businessroleMasterImpl.setVersionNo(businessroleMaster.getVersionNo());
        businessroleMasterImpl.setModifiedBy(businessroleMaster.getModifiedBy());
        businessroleMasterImpl.setModifiedDate(businessroleMaster.getModifiedDate());
        businessroleMasterImpl.setBusinessroleDesc(businessroleMaster.getBusinessroleDesc());
        businessroleMasterImpl.setIsActive(businessroleMaster.getIsActive());

        return businessroleMasterImpl;
    }

    /**
     * Returns the businessrole master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the businessrole master
     * @return the businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBusinessroleMasterException, SystemException {
        BusinessroleMaster businessroleMaster = fetchByPrimaryKey(primaryKey);

        if (businessroleMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBusinessroleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return businessroleMaster;
    }

    /**
     * Returns the businessrole master with the primary key or throws a {@link com.stpl.app.NoSuchBusinessroleMasterException} if it could not be found.
     *
     * @param businessroleMasterSid the primary key of the businessrole master
     * @return the businessrole master
     * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster findByPrimaryKey(int businessroleMasterSid)
        throws NoSuchBusinessroleMasterException, SystemException {
        return findByPrimaryKey((Serializable) businessroleMasterSid);
    }

    /**
     * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the businessrole master
     * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BusinessroleMaster businessroleMaster = (BusinessroleMaster) EntityCacheUtil.getResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
                BusinessroleMasterImpl.class, primaryKey);

        if (businessroleMaster == _nullBusinessroleMaster) {
            return null;
        }

        if (businessroleMaster == null) {
            Session session = null;

            try {
                session = openSession();

                businessroleMaster = (BusinessroleMaster) session.get(BusinessroleMasterImpl.class,
                        primaryKey);

                if (businessroleMaster != null) {
                    cacheResult(businessroleMaster);
                } else {
                    EntityCacheUtil.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BusinessroleMasterImpl.class, primaryKey,
                        _nullBusinessroleMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
                    BusinessroleMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return businessroleMaster;
    }

    /**
     * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param businessroleMasterSid the primary key of the businessrole master
     * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleMaster fetchByPrimaryKey(int businessroleMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) businessroleMasterSid);
    }

    /**
     * Returns all the businessrole masters.
     *
     * @return the businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the businessrole masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @return the range of businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the businessrole masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of businessrole masters
     * @param end the upper bound of the range of businessrole masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of businessrole masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleMaster> findAll(int start, int end,
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

        List<BusinessroleMaster> list = (List<BusinessroleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BUSINESSROLEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BUSINESSROLEMASTER;

                if (pagination) {
                    sql = sql.concat(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BusinessroleMaster>(list);
                } else {
                    list = (List<BusinessroleMaster>) QueryUtil.list(q,
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
     * Removes all the businessrole masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BusinessroleMaster businessroleMaster : findAll()) {
            remove(businessroleMaster);
        }
    }

    /**
     * Returns the number of businessrole masters.
     *
     * @return the number of businessrole masters
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

                Query q = session.createQuery(_SQL_COUNT_BUSINESSROLEMASTER);

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
     * Initializes the businessrole master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.BusinessroleMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BusinessroleMaster>> listenersList = new ArrayList<ModelListener<BusinessroleMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BusinessroleMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BusinessroleMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
