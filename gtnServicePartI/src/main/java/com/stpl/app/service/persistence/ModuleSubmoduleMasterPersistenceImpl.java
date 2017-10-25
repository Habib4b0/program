package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchModuleSubmoduleMasterException;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.model.impl.ModuleSubmoduleMasterImpl;
import com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl;
import com.stpl.app.service.persistence.ModuleSubmoduleMasterPersistence;

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
 * The persistence implementation for the module submodule master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMasterPersistence
 * @see ModuleSubmoduleMasterUtil
 * @generated
 */
public class ModuleSubmoduleMasterPersistenceImpl extends BasePersistenceImpl<ModuleSubmoduleMaster>
    implements ModuleSubmoduleMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModuleSubmoduleMasterUtil} to access the module submodule master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModuleSubmoduleMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULENAME =
        new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME =
        new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModuleName",
            new String[] { String.class.getName() },
            ModuleSubmoduleMasterModelImpl.MODULENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODULENAME = new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModuleName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_1 = "moduleSubmoduleMaster.moduleName IS NULL";
    private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_2 = "moduleSubmoduleMaster.moduleName = ?";
    private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_3 = "(moduleSubmoduleMaster.moduleName IS NULL OR moduleSubmoduleMaster.moduleName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBMODULENAME =
        new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubmoduleName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME =
        new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubmoduleName",
            new String[] { String.class.getName() },
            ModuleSubmoduleMasterModelImpl.MODULENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SUBMODULENAME = new FinderPath(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubmoduleName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_1 = "moduleSubmoduleMaster.moduleName IS NULL";
    private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_2 = "moduleSubmoduleMaster.moduleName = ?";
    private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_3 = "(moduleSubmoduleMaster.moduleName IS NULL OR moduleSubmoduleMaster.moduleName = '')";
    private static final String _SQL_SELECT_MODULESUBMODULEMASTER = "SELECT moduleSubmoduleMaster FROM ModuleSubmoduleMaster moduleSubmoduleMaster";
    private static final String _SQL_SELECT_MODULESUBMODULEMASTER_WHERE = "SELECT moduleSubmoduleMaster FROM ModuleSubmoduleMaster moduleSubmoduleMaster WHERE ";
    private static final String _SQL_COUNT_MODULESUBMODULEMASTER = "SELECT COUNT(moduleSubmoduleMaster) FROM ModuleSubmoduleMaster moduleSubmoduleMaster";
    private static final String _SQL_COUNT_MODULESUBMODULEMASTER_WHERE = "SELECT COUNT(moduleSubmoduleMaster) FROM ModuleSubmoduleMaster moduleSubmoduleMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "moduleSubmoduleMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModuleSubmoduleMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModuleSubmoduleMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModuleSubmoduleMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "category", "moduleSubmoduleSid",
                "submoduleName", "moduleName", "versionNo", "modifiedBy",
                "modifiedDate"
            });
    private static ModuleSubmoduleMaster _nullModuleSubmoduleMaster = new ModuleSubmoduleMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModuleSubmoduleMaster> toCacheModel() {
                return _nullModuleSubmoduleMasterCacheModel;
            }
        };

    private static CacheModel<ModuleSubmoduleMaster> _nullModuleSubmoduleMasterCacheModel =
        new CacheModel<ModuleSubmoduleMaster>() {
            @Override
            public ModuleSubmoduleMaster toEntityModel() {
                return _nullModuleSubmoduleMaster;
            }
        };

    public ModuleSubmoduleMasterPersistenceImpl() {
        setModelClass(ModuleSubmoduleMaster.class);
    }

    /**
     * Returns all the module submodule masters where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @return the matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findByModuleName(String moduleName)
        throws SystemException {
        return findByModuleName(moduleName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the module submodule masters where moduleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param moduleName the module name
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @return the range of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findByModuleName(String moduleName,
        int start, int end) throws SystemException {
        return findByModuleName(moduleName, start, end, null);
    }

    /**
     * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param moduleName the module name
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findByModuleName(String moduleName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME;
            finderArgs = new Object[] { moduleName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULENAME;
            finderArgs = new Object[] { moduleName, start, end, orderByComparator };
        }

        List<ModuleSubmoduleMaster> list = (List<ModuleSubmoduleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ModuleSubmoduleMaster moduleSubmoduleMaster : list) {
                if (!Validator.equals(moduleName,
                            moduleSubmoduleMaster.getModuleName())) {
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

            query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

            boolean bindModuleName = false;

            if (moduleName == null) {
                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
            } else if (moduleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
            } else {
                bindModuleName = true;

                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ModuleSubmoduleMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindModuleName) {
                    qPos.add(moduleName);
                }

                if (!pagination) {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModuleSubmoduleMaster>(list);
                } else {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
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
     * Returns the first module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findByModuleName_First(String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = fetchByModuleName_First(moduleName,
                orderByComparator);

        if (moduleSubmoduleMaster != null) {
            return moduleSubmoduleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("moduleName=");
        msg.append(moduleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModuleSubmoduleMasterException(msg.toString());
    }

    /**
     * Returns the first module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchByModuleName_First(String moduleName,
        OrderByComparator orderByComparator) throws SystemException {
        List<ModuleSubmoduleMaster> list = findByModuleName(moduleName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findByModuleName_Last(String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = fetchByModuleName_Last(moduleName,
                orderByComparator);

        if (moduleSubmoduleMaster != null) {
            return moduleSubmoduleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("moduleName=");
        msg.append(moduleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModuleSubmoduleMasterException(msg.toString());
    }

    /**
     * Returns the last module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchByModuleName_Last(String moduleName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByModuleName(moduleName);

        if (count == 0) {
            return null;
        }

        List<ModuleSubmoduleMaster> list = findByModuleName(moduleName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleSubmoduleSid the primary key of the current module submodule master
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster[] findByModuleName_PrevAndNext(
        int moduleSubmoduleSid, String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = findByPrimaryKey(moduleSubmoduleSid);

        Session session = null;

        try {
            session = openSession();

            ModuleSubmoduleMaster[] array = new ModuleSubmoduleMasterImpl[3];

            array[0] = getByModuleName_PrevAndNext(session,
                    moduleSubmoduleMaster, moduleName, orderByComparator, true);

            array[1] = moduleSubmoduleMaster;

            array[2] = getByModuleName_PrevAndNext(session,
                    moduleSubmoduleMaster, moduleName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModuleSubmoduleMaster getByModuleName_PrevAndNext(
        Session session, ModuleSubmoduleMaster moduleSubmoduleMaster,
        String moduleName, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

        boolean bindModuleName = false;

        if (moduleName == null) {
            query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
        } else if (moduleName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
        } else {
            bindModuleName = true;

            query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
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
            query.append(ModuleSubmoduleMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindModuleName) {
            qPos.add(moduleName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(moduleSubmoduleMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModuleSubmoduleMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the module submodule masters where moduleName = &#63; from the database.
     *
     * @param moduleName the module name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByModuleName(String moduleName) throws SystemException {
        for (ModuleSubmoduleMaster moduleSubmoduleMaster : findByModuleName(
                moduleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(moduleSubmoduleMaster);
        }
    }

    /**
     * Returns the number of module submodule masters where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @return the number of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModuleName(String moduleName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MODULENAME;

        Object[] finderArgs = new Object[] { moduleName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODULESUBMODULEMASTER_WHERE);

            boolean bindModuleName = false;

            if (moduleName == null) {
                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
            } else if (moduleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
            } else {
                bindModuleName = true;

                query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindModuleName) {
                    qPos.add(moduleName);
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
     * Returns all the module submodule masters where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @return the matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName)
        throws SystemException {
        return findBySubmoduleName(moduleName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the module submodule masters where moduleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param moduleName the module name
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @return the range of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName,
        int start, int end) throws SystemException {
        return findBySubmoduleName(moduleName, start, end, null);
    }

    /**
     * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param moduleName the module name
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME;
            finderArgs = new Object[] { moduleName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBMODULENAME;
            finderArgs = new Object[] { moduleName, start, end, orderByComparator };
        }

        List<ModuleSubmoduleMaster> list = (List<ModuleSubmoduleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ModuleSubmoduleMaster moduleSubmoduleMaster : list) {
                if (!Validator.equals(moduleName,
                            moduleSubmoduleMaster.getModuleName())) {
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

            query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

            boolean bindModuleName = false;

            if (moduleName == null) {
                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
            } else if (moduleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
            } else {
                bindModuleName = true;

                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ModuleSubmoduleMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindModuleName) {
                    qPos.add(moduleName);
                }

                if (!pagination) {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModuleSubmoduleMaster>(list);
                } else {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
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
     * Returns the first module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findBySubmoduleName_First(String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = fetchBySubmoduleName_First(moduleName,
                orderByComparator);

        if (moduleSubmoduleMaster != null) {
            return moduleSubmoduleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("moduleName=");
        msg.append(moduleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModuleSubmoduleMasterException(msg.toString());
    }

    /**
     * Returns the first module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchBySubmoduleName_First(String moduleName,
        OrderByComparator orderByComparator) throws SystemException {
        List<ModuleSubmoduleMaster> list = findBySubmoduleName(moduleName, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findBySubmoduleName_Last(String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = fetchBySubmoduleName_Last(moduleName,
                orderByComparator);

        if (moduleSubmoduleMaster != null) {
            return moduleSubmoduleMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("moduleName=");
        msg.append(moduleName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModuleSubmoduleMasterException(msg.toString());
    }

    /**
     * Returns the last module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchBySubmoduleName_Last(String moduleName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySubmoduleName(moduleName);

        if (count == 0) {
            return null;
        }

        List<ModuleSubmoduleMaster> list = findBySubmoduleName(moduleName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
     *
     * @param moduleSubmoduleSid the primary key of the current module submodule master
     * @param moduleName the module name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster[] findBySubmoduleName_PrevAndNext(
        int moduleSubmoduleSid, String moduleName,
        OrderByComparator orderByComparator)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = findByPrimaryKey(moduleSubmoduleSid);

        Session session = null;

        try {
            session = openSession();

            ModuleSubmoduleMaster[] array = new ModuleSubmoduleMasterImpl[3];

            array[0] = getBySubmoduleName_PrevAndNext(session,
                    moduleSubmoduleMaster, moduleName, orderByComparator, true);

            array[1] = moduleSubmoduleMaster;

            array[2] = getBySubmoduleName_PrevAndNext(session,
                    moduleSubmoduleMaster, moduleName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModuleSubmoduleMaster getBySubmoduleName_PrevAndNext(
        Session session, ModuleSubmoduleMaster moduleSubmoduleMaster,
        String moduleName, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

        boolean bindModuleName = false;

        if (moduleName == null) {
            query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
        } else if (moduleName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
        } else {
            bindModuleName = true;

            query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_2);
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
            query.append(ModuleSubmoduleMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindModuleName) {
            qPos.add(moduleName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(moduleSubmoduleMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModuleSubmoduleMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the module submodule masters where moduleName = &#63; from the database.
     *
     * @param moduleName the module name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySubmoduleName(String moduleName)
        throws SystemException {
        for (ModuleSubmoduleMaster moduleSubmoduleMaster : findBySubmoduleName(
                moduleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(moduleSubmoduleMaster);
        }
    }

    /**
     * Returns the number of module submodule masters where moduleName = &#63;.
     *
     * @param moduleName the module name
     * @return the number of matching module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySubmoduleName(String moduleName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBMODULENAME;

        Object[] finderArgs = new Object[] { moduleName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODULESUBMODULEMASTER_WHERE);

            boolean bindModuleName = false;

            if (moduleName == null) {
                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
            } else if (moduleName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
            } else {
                bindModuleName = true;

                query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindModuleName) {
                    qPos.add(moduleName);
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
     * Caches the module submodule master in the entity cache if it is enabled.
     *
     * @param moduleSubmoduleMaster the module submodule master
     */
    @Override
    public void cacheResult(ModuleSubmoduleMaster moduleSubmoduleMaster) {
        EntityCacheUtil.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            moduleSubmoduleMaster.getPrimaryKey(), moduleSubmoduleMaster);

        moduleSubmoduleMaster.resetOriginalValues();
    }

    /**
     * Caches the module submodule masters in the entity cache if it is enabled.
     *
     * @param moduleSubmoduleMasters the module submodule masters
     */
    @Override
    public void cacheResult(List<ModuleSubmoduleMaster> moduleSubmoduleMasters) {
        for (ModuleSubmoduleMaster moduleSubmoduleMaster : moduleSubmoduleMasters) {
            if (EntityCacheUtil.getResult(
                        ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ModuleSubmoduleMasterImpl.class,
                        moduleSubmoduleMaster.getPrimaryKey()) == null) {
                cacheResult(moduleSubmoduleMaster);
            } else {
                moduleSubmoduleMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all module submodule masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModuleSubmoduleMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModuleSubmoduleMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the module submodule master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModuleSubmoduleMaster moduleSubmoduleMaster) {
        EntityCacheUtil.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            moduleSubmoduleMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ModuleSubmoduleMaster> moduleSubmoduleMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModuleSubmoduleMaster moduleSubmoduleMaster : moduleSubmoduleMasters) {
            EntityCacheUtil.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
                ModuleSubmoduleMasterImpl.class,
                moduleSubmoduleMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
     *
     * @param moduleSubmoduleSid the primary key for the new module submodule master
     * @return the new module submodule master
     */
    @Override
    public ModuleSubmoduleMaster create(int moduleSubmoduleSid) {
        ModuleSubmoduleMaster moduleSubmoduleMaster = new ModuleSubmoduleMasterImpl();

        moduleSubmoduleMaster.setNew(true);
        moduleSubmoduleMaster.setPrimaryKey(moduleSubmoduleSid);

        return moduleSubmoduleMaster;
    }

    /**
     * Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param moduleSubmoduleSid the primary key of the module submodule master
     * @return the module submodule master that was removed
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster remove(int moduleSubmoduleSid)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        return remove((Serializable) moduleSubmoduleSid);
    }

    /**
     * Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the module submodule master
     * @return the module submodule master that was removed
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster remove(Serializable primaryKey)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster) session.get(ModuleSubmoduleMasterImpl.class,
                    primaryKey);

            if (moduleSubmoduleMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModuleSubmoduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(moduleSubmoduleMaster);
        } catch (NoSuchModuleSubmoduleMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModuleSubmoduleMaster removeImpl(
        ModuleSubmoduleMaster moduleSubmoduleMaster) throws SystemException {
        moduleSubmoduleMaster = toUnwrappedModel(moduleSubmoduleMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(moduleSubmoduleMaster)) {
                moduleSubmoduleMaster = (ModuleSubmoduleMaster) session.get(ModuleSubmoduleMasterImpl.class,
                        moduleSubmoduleMaster.getPrimaryKeyObj());
            }

            if (moduleSubmoduleMaster != null) {
                session.delete(moduleSubmoduleMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (moduleSubmoduleMaster != null) {
            clearCache(moduleSubmoduleMaster);
        }

        return moduleSubmoduleMaster;
    }

    @Override
    public ModuleSubmoduleMaster updateImpl(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster)
        throws SystemException {
        moduleSubmoduleMaster = toUnwrappedModel(moduleSubmoduleMaster);

        boolean isNew = moduleSubmoduleMaster.isNew();

        ModuleSubmoduleMasterModelImpl moduleSubmoduleMasterModelImpl = (ModuleSubmoduleMasterModelImpl) moduleSubmoduleMaster;

        Session session = null;

        try {
            session = openSession();

            if (moduleSubmoduleMaster.isNew()) {
                session.save(moduleSubmoduleMaster);

                moduleSubmoduleMaster.setNew(false);
            } else {
                session.merge(moduleSubmoduleMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModuleSubmoduleMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((moduleSubmoduleMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        moduleSubmoduleMasterModelImpl.getOriginalModuleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
                    args);

                args = new Object[] {
                        moduleSubmoduleMasterModelImpl.getModuleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
                    args);
            }

            if ((moduleSubmoduleMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        moduleSubmoduleMasterModelImpl.getOriginalModuleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBMODULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME,
                    args);

                args = new Object[] {
                        moduleSubmoduleMasterModelImpl.getModuleName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBMODULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
            ModuleSubmoduleMasterImpl.class,
            moduleSubmoduleMaster.getPrimaryKey(), moduleSubmoduleMaster);

        return moduleSubmoduleMaster;
    }

    protected ModuleSubmoduleMaster toUnwrappedModel(
        ModuleSubmoduleMaster moduleSubmoduleMaster) {
        if (moduleSubmoduleMaster instanceof ModuleSubmoduleMasterImpl) {
            return moduleSubmoduleMaster;
        }

        ModuleSubmoduleMasterImpl moduleSubmoduleMasterImpl = new ModuleSubmoduleMasterImpl();

        moduleSubmoduleMasterImpl.setNew(moduleSubmoduleMaster.isNew());
        moduleSubmoduleMasterImpl.setPrimaryKey(moduleSubmoduleMaster.getPrimaryKey());

        moduleSubmoduleMasterImpl.setCreatedDate(moduleSubmoduleMaster.getCreatedDate());
        moduleSubmoduleMasterImpl.setCreatedBy(moduleSubmoduleMaster.getCreatedBy());
        moduleSubmoduleMasterImpl.setCategory(moduleSubmoduleMaster.getCategory());
        moduleSubmoduleMasterImpl.setModuleSubmoduleSid(moduleSubmoduleMaster.getModuleSubmoduleSid());
        moduleSubmoduleMasterImpl.setSubmoduleName(moduleSubmoduleMaster.getSubmoduleName());
        moduleSubmoduleMasterImpl.setModuleName(moduleSubmoduleMaster.getModuleName());
        moduleSubmoduleMasterImpl.setVersionNo(moduleSubmoduleMaster.getVersionNo());
        moduleSubmoduleMasterImpl.setModifiedBy(moduleSubmoduleMaster.getModifiedBy());
        moduleSubmoduleMasterImpl.setModifiedDate(moduleSubmoduleMaster.getModifiedDate());

        return moduleSubmoduleMasterImpl;
    }

    /**
     * Returns the module submodule master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the module submodule master
     * @return the module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = fetchByPrimaryKey(primaryKey);

        if (moduleSubmoduleMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchModuleSubmoduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return moduleSubmoduleMaster;
    }

    /**
     * Returns the module submodule master with the primary key or throws a {@link com.stpl.app.NoSuchModuleSubmoduleMasterException} if it could not be found.
     *
     * @param moduleSubmoduleSid the primary key of the module submodule master
     * @return the module submodule master
     * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster findByPrimaryKey(int moduleSubmoduleSid)
        throws NoSuchModuleSubmoduleMasterException, SystemException {
        return findByPrimaryKey((Serializable) moduleSubmoduleSid);
    }

    /**
     * Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the module submodule master
     * @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster) EntityCacheUtil.getResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
                ModuleSubmoduleMasterImpl.class, primaryKey);

        if (moduleSubmoduleMaster == _nullModuleSubmoduleMaster) {
            return null;
        }

        if (moduleSubmoduleMaster == null) {
            Session session = null;

            try {
                session = openSession();

                moduleSubmoduleMaster = (ModuleSubmoduleMaster) session.get(ModuleSubmoduleMasterImpl.class,
                        primaryKey);

                if (moduleSubmoduleMaster != null) {
                    cacheResult(moduleSubmoduleMaster);
                } else {
                    EntityCacheUtil.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ModuleSubmoduleMasterImpl.class, primaryKey,
                        _nullModuleSubmoduleMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ModuleSubmoduleMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return moduleSubmoduleMaster;
    }

    /**
     * Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param moduleSubmoduleSid the primary key of the module submodule master
     * @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleSubmoduleMaster fetchByPrimaryKey(int moduleSubmoduleSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) moduleSubmoduleSid);
    }

    /**
     * Returns all the module submodule masters.
     *
     * @return the module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the module submodule masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @return the range of module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the module submodule masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of module submodule masters
     * @param end the upper bound of the range of module submodule masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of module submodule masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleSubmoduleMaster> findAll(int start, int end,
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

        List<ModuleSubmoduleMaster> list = (List<ModuleSubmoduleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODULESUBMODULEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODULESUBMODULEMASTER;

                if (pagination) {
                    sql = sql.concat(ModuleSubmoduleMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModuleSubmoduleMaster>(list);
                } else {
                    list = (List<ModuleSubmoduleMaster>) QueryUtil.list(q,
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
     * Removes all the module submodule masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ModuleSubmoduleMaster moduleSubmoduleMaster : findAll()) {
            remove(moduleSubmoduleMaster);
        }
    }

    /**
     * Returns the number of module submodule masters.
     *
     * @return the number of module submodule masters
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

                Query q = session.createQuery(_SQL_COUNT_MODULESUBMODULEMASTER);

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
     * Initializes the module submodule master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ModuleSubmoduleMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModuleSubmoduleMaster>> listenersList = new ArrayList<ModelListener<ModuleSubmoduleMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModuleSubmoduleMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModuleSubmoduleMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
