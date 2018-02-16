/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchModuleSubmoduleMasterException;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.model.impl.ModuleSubmoduleMasterImpl;
import com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl;
import com.stpl.app.service.persistence.ModuleSubmoduleMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
 * @see com.stpl.app.service.persistence.ModuleSubmoduleMasterUtil
 * @generated
 */
@ProviderType
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

	/**
	 * Returns all the module submodule masters where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findByModuleName(String moduleName) {
		return findByModuleName(moduleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @return the range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findByModuleName(String moduleName,
		int start, int end) {
		return findByModuleName(moduleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findByModuleName(String moduleName,
		int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return findByModuleName(moduleName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findByModuleName(String moduleName,
		int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME;
			finderArgs = new Object[] { moduleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULENAME;
			finderArgs = new Object[] { moduleName, start, end, orderByComparator };
		}

		List<ModuleSubmoduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleSubmoduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ModuleSubmoduleMaster moduleSubmoduleMaster : list) {
					if (!Objects.equals(moduleName,
								moduleSubmoduleMaster.getModuleName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
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
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findByModuleName_First(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
	 */
	@Override
	public ModuleSubmoduleMaster fetchByModuleName_First(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findByModuleName_Last(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
	 */
	@Override
	public ModuleSubmoduleMaster fetchByModuleName_Last(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster[] findByModuleName_PrevAndNext(
		int moduleSubmoduleSid, String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ModuleSubmoduleMaster getByModuleName_PrevAndNext(
		Session session, ModuleSubmoduleMaster moduleSubmoduleMaster,
		String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

		boolean bindModuleName = false;

		if (moduleName == null) {
			query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
		}
		else if (moduleName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
		}
		else {
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
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
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
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
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
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the module submodule masters where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	@Override
	public void removeByModuleName(String moduleName) {
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
	 */
	@Override
	public int countByModuleName(String moduleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODULENAME;

		Object[] finderArgs = new Object[] { moduleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULESUBMODULEMASTER_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
			}
			else {
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

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

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

	/**
	 * Returns all the module submodule masters where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName) {
		return findBySubmoduleName(moduleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @return the range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName,
		int start, int end) {
		return findBySubmoduleName(moduleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName,
		int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return findBySubmoduleName(moduleName, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findBySubmoduleName(String moduleName,
		int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME;
			finderArgs = new Object[] { moduleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBMODULENAME;
			finderArgs = new Object[] { moduleName, start, end, orderByComparator };
		}

		List<ModuleSubmoduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleSubmoduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ModuleSubmoduleMaster moduleSubmoduleMaster : list) {
					if (!Objects.equals(moduleName,
								moduleSubmoduleMaster.getModuleName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
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
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findBySubmoduleName_First(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
	 */
	@Override
	public ModuleSubmoduleMaster fetchBySubmoduleName_First(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findBySubmoduleName_Last(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
	 */
	@Override
	public ModuleSubmoduleMaster fetchBySubmoduleName_Last(String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
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
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster[] findBySubmoduleName_PrevAndNext(
		int moduleSubmoduleSid, String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException {
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
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ModuleSubmoduleMaster getBySubmoduleName_PrevAndNext(
		Session session, ModuleSubmoduleMaster moduleSubmoduleMaster,
		String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE);

		boolean bindModuleName = false;

		if (moduleName == null) {
			query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
		}
		else if (moduleName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
		}
		else {
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
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
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
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
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
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the module submodule masters where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	@Override
	public void removeBySubmoduleName(String moduleName) {
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
	 */
	@Override
	public int countBySubmoduleName(String moduleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBMODULENAME;

		Object[] finderArgs = new Object[] { moduleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULESUBMODULEMASTER_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SUBMODULENAME_MODULENAME_3);
			}
			else {
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

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_1 = "moduleSubmoduleMaster.moduleName IS NULL";
	private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_2 = "moduleSubmoduleMaster.moduleName = ?";
	private static final String _FINDER_COLUMN_SUBMODULENAME_MODULENAME_3 = "(moduleSubmoduleMaster.moduleName IS NULL OR moduleSubmoduleMaster.moduleName = '')";

	public ModuleSubmoduleMasterPersistenceImpl() {
		setModelClass(ModuleSubmoduleMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("category", "CATEGORY");
			dbColumnNames.put("moduleSubmoduleSid", "MODULE_SUBMODULE_SID");
			dbColumnNames.put("submoduleName", "SUBMODULE_NAME");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the module submodule master in the entity cache if it is enabled.
	 *
	 * @param moduleSubmoduleMaster the module submodule master
	 */
	@Override
	public void cacheResult(ModuleSubmoduleMaster moduleSubmoduleMaster) {
		entityCache.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
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
			if (entityCache.getResult(
						ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
						ModuleSubmoduleMasterImpl.class,
						moduleSubmoduleMaster.getPrimaryKey()) == null) {
				cacheResult(moduleSubmoduleMaster);
			}
			else {
				moduleSubmoduleMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all module submodule masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ModuleSubmoduleMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the module submodule master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ModuleSubmoduleMaster moduleSubmoduleMaster) {
		entityCache.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			ModuleSubmoduleMasterImpl.class,
			moduleSubmoduleMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ModuleSubmoduleMaster> moduleSubmoduleMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ModuleSubmoduleMaster moduleSubmoduleMaster : moduleSubmoduleMasters) {
			entityCache.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
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
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster remove(int moduleSubmoduleSid)
		throws NoSuchModuleSubmoduleMasterException {
		return remove((Serializable)moduleSubmoduleSid);
	}

	/**
	 * Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module submodule master
	 * @return the module submodule master that was removed
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster remove(Serializable primaryKey)
		throws NoSuchModuleSubmoduleMasterException {
		Session session = null;

		try {
			session = openSession();

			ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster)session.get(ModuleSubmoduleMasterImpl.class,
					primaryKey);

			if (moduleSubmoduleMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModuleSubmoduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(moduleSubmoduleMaster);
		}
		catch (NoSuchModuleSubmoduleMasterException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ModuleSubmoduleMaster removeImpl(
		ModuleSubmoduleMaster moduleSubmoduleMaster) {
		moduleSubmoduleMaster = toUnwrappedModel(moduleSubmoduleMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(moduleSubmoduleMaster)) {
				moduleSubmoduleMaster = (ModuleSubmoduleMaster)session.get(ModuleSubmoduleMasterImpl.class,
						moduleSubmoduleMaster.getPrimaryKeyObj());
			}

			if (moduleSubmoduleMaster != null) {
				session.delete(moduleSubmoduleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (moduleSubmoduleMaster != null) {
			clearCache(moduleSubmoduleMaster);
		}

		return moduleSubmoduleMaster;
	}

	@Override
	public ModuleSubmoduleMaster updateImpl(
		ModuleSubmoduleMaster moduleSubmoduleMaster) {
		moduleSubmoduleMaster = toUnwrappedModel(moduleSubmoduleMaster);

		boolean isNew = moduleSubmoduleMaster.isNew();

		ModuleSubmoduleMasterModelImpl moduleSubmoduleMasterModelImpl = (ModuleSubmoduleMasterModelImpl)moduleSubmoduleMaster;

		Session session = null;

		try {
			session = openSession();

			if (moduleSubmoduleMaster.isNew()) {
				session.save(moduleSubmoduleMaster);

				moduleSubmoduleMaster.setNew(false);
			}
			else {
				moduleSubmoduleMaster = (ModuleSubmoduleMaster)session.merge(moduleSubmoduleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ModuleSubmoduleMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					moduleSubmoduleMasterModelImpl.getModuleName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
				args);

			args = new Object[] { moduleSubmoduleMasterModelImpl.getModuleName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBMODULENAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((moduleSubmoduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleSubmoduleMasterModelImpl.getOriginalModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
					args);

				args = new Object[] {
						moduleSubmoduleMasterModelImpl.getModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
					args);
			}

			if ((moduleSubmoduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleSubmoduleMasterModelImpl.getOriginalModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBMODULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME,
					args);

				args = new Object[] {
						moduleSubmoduleMasterModelImpl.getModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBMODULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMODULENAME,
					args);
			}
		}

		entityCache.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			ModuleSubmoduleMasterImpl.class,
			moduleSubmoduleMaster.getPrimaryKey(), moduleSubmoduleMaster, false);

		moduleSubmoduleMaster.resetOriginalValues();

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
	 * Returns the module submodule master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module submodule master
	 * @return the module submodule master
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModuleSubmoduleMasterException {
		ModuleSubmoduleMaster moduleSubmoduleMaster = fetchByPrimaryKey(primaryKey);

		if (moduleSubmoduleMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModuleSubmoduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return moduleSubmoduleMaster;
	}

	/**
	 * Returns the module submodule master with the primary key or throws a {@link NoSuchModuleSubmoduleMasterException} if it could not be found.
	 *
	 * @param moduleSubmoduleSid the primary key of the module submodule master
	 * @return the module submodule master
	 * @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster findByPrimaryKey(int moduleSubmoduleSid)
		throws NoSuchModuleSubmoduleMasterException {
		return findByPrimaryKey((Serializable)moduleSubmoduleSid);
	}

	/**
	 * Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module submodule master
	 * @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
	 */
	@Override
	public ModuleSubmoduleMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
				ModuleSubmoduleMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster)serializable;

		if (moduleSubmoduleMaster == null) {
			Session session = null;

			try {
				session = openSession();

				moduleSubmoduleMaster = (ModuleSubmoduleMaster)session.get(ModuleSubmoduleMasterImpl.class,
						primaryKey);

				if (moduleSubmoduleMaster != null) {
					cacheResult(moduleSubmoduleMaster);
				}
				else {
					entityCache.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
						ModuleSubmoduleMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					ModuleSubmoduleMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
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
	 */
	@Override
	public ModuleSubmoduleMaster fetchByPrimaryKey(int moduleSubmoduleSid) {
		return fetchByPrimaryKey((Serializable)moduleSubmoduleSid);
	}

	@Override
	public Map<Serializable, ModuleSubmoduleMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ModuleSubmoduleMaster> map = new HashMap<Serializable, ModuleSubmoduleMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ModuleSubmoduleMaster moduleSubmoduleMaster = fetchByPrimaryKey(primaryKey);

			if (moduleSubmoduleMaster != null) {
				map.put(primaryKey, moduleSubmoduleMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					ModuleSubmoduleMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ModuleSubmoduleMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MODULESUBMODULEMASTER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ModuleSubmoduleMaster moduleSubmoduleMaster : (List<ModuleSubmoduleMaster>)q.list()) {
				map.put(moduleSubmoduleMaster.getPrimaryKeyObj(),
					moduleSubmoduleMaster);

				cacheResult(moduleSubmoduleMaster);

				uncachedPrimaryKeys.remove(moduleSubmoduleMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ModuleSubmoduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					ModuleSubmoduleMasterImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the module submodule masters.
	 *
	 * @return the module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module submodule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @return the range of module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the module submodule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findAll(int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module submodule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module submodule masters
	 * @param end the upper bound of the range of module submodule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module submodule masters
	 */
	@Override
	public List<ModuleSubmoduleMaster> findAll(int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ModuleSubmoduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleSubmoduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MODULESUBMODULEMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
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
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleSubmoduleMaster>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the module submodule masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ModuleSubmoduleMaster moduleSubmoduleMaster : findAll()) {
			remove(moduleSubmoduleMaster);
		}
	}

	/**
	 * Returns the number of module submodule masters.
	 *
	 * @return the number of module submodule masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULESUBMODULEMASTER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ModuleSubmoduleMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the module submodule master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ModuleSubmoduleMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MODULESUBMODULEMASTER = "SELECT moduleSubmoduleMaster FROM ModuleSubmoduleMaster moduleSubmoduleMaster";
	private static final String _SQL_SELECT_MODULESUBMODULEMASTER_WHERE_PKS_IN = "SELECT moduleSubmoduleMaster FROM ModuleSubmoduleMaster moduleSubmoduleMaster WHERE MODULE_SUBMODULE_SID IN (";
	private static final String _SQL_SELECT_MODULESUBMODULEMASTER_WHERE = "SELECT moduleSubmoduleMaster FROM ModuleSubmoduleMaster moduleSubmoduleMaster WHERE ";
	private static final String _SQL_COUNT_MODULESUBMODULEMASTER = "SELECT COUNT(moduleSubmoduleMaster) FROM ModuleSubmoduleMaster moduleSubmoduleMaster";
	private static final String _SQL_COUNT_MODULESUBMODULEMASTER_WHERE = "SELECT COUNT(moduleSubmoduleMaster) FROM ModuleSubmoduleMaster moduleSubmoduleMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "moduleSubmoduleMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModuleSubmoduleMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModuleSubmoduleMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ModuleSubmoduleMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "category", "moduleSubmoduleSid",
				"submoduleName", "moduleName", "versionNo", "modifiedBy",
				"modifiedDate"
			});
}