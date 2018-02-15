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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchCcpMapException;
import com.stpl.app.model.CcpMap;
import com.stpl.app.service.CcpMapLocalServiceUtil;
import com.stpl.app.service.persistence.CcpMapPersistence;
import com.stpl.app.service.persistence.CcpMapUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CcpMapPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CcpMapUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CcpMap> iterator = _ccpMaps.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpMap ccpMap = _persistence.create(pk);

		Assert.assertNotNull(ccpMap);

		Assert.assertEquals(ccpMap.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CcpMap newCcpMap = addCcpMap();

		_persistence.remove(newCcpMap);

		CcpMap existingCcpMap = _persistence.fetchByPrimaryKey(newCcpMap.getPrimaryKey());

		Assert.assertNull(existingCcpMap);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCcpMap();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpMap newCcpMap = _persistence.create(pk);

		newCcpMap.setCcpDetailsSid(RandomTestUtil.nextInt());

		newCcpMap.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_ccpMaps.add(_persistence.update(newCcpMap));

		CcpMap existingCcpMap = _persistence.findByPrimaryKey(newCcpMap.getPrimaryKey());

		Assert.assertEquals(existingCcpMap.getCcpDetailsSid(),
			newCcpMap.getCcpDetailsSid());
		Assert.assertEquals(existingCcpMap.getRelationshipLevelSid(),
			newCcpMap.getRelationshipLevelSid());
		Assert.assertEquals(existingCcpMap.getCcpMapSid(),
			newCcpMap.getCcpMapSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CcpMap newCcpMap = addCcpMap();

		CcpMap existingCcpMap = _persistence.findByPrimaryKey(newCcpMap.getPrimaryKey());

		Assert.assertEquals(existingCcpMap, newCcpMap);
	}

	@Test(expected = NoSuchCcpMapException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CcpMap> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CCP_MAP", "ccpDetailsSid",
			true, "relationshipLevelSid", true, "ccpMapSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CcpMap newCcpMap = addCcpMap();

		CcpMap existingCcpMap = _persistence.fetchByPrimaryKey(newCcpMap.getPrimaryKey());

		Assert.assertEquals(existingCcpMap, newCcpMap);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpMap missingCcpMap = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCcpMap);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CcpMap newCcpMap1 = addCcpMap();
		CcpMap newCcpMap2 = addCcpMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpMap1.getPrimaryKey());
		primaryKeys.add(newCcpMap2.getPrimaryKey());

		Map<Serializable, CcpMap> ccpMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ccpMaps.size());
		Assert.assertEquals(newCcpMap1, ccpMaps.get(newCcpMap1.getPrimaryKey()));
		Assert.assertEquals(newCcpMap2, ccpMaps.get(newCcpMap2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CcpMap> ccpMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ccpMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CcpMap newCcpMap = addCcpMap();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpMap.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CcpMap> ccpMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ccpMaps.size());
		Assert.assertEquals(newCcpMap, ccpMaps.get(newCcpMap.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CcpMap> ccpMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ccpMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CcpMap newCcpMap = addCcpMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpMap.getPrimaryKey());

		Map<Serializable, CcpMap> ccpMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ccpMaps.size());
		Assert.assertEquals(newCcpMap, ccpMaps.get(newCcpMap.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CcpMapLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CcpMap>() {
				@Override
				public void performAction(CcpMap ccpMap) {
					Assert.assertNotNull(ccpMap);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CcpMap newCcpMap = addCcpMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ccpMapSid",
				newCcpMap.getCcpMapSid()));

		List<CcpMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CcpMap existingCcpMap = result.get(0);

		Assert.assertEquals(existingCcpMap, newCcpMap);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ccpMapSid",
				RandomTestUtil.nextInt()));

		List<CcpMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CcpMap newCcpMap = addCcpMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ccpMapSid"));

		Object newCcpMapSid = newCcpMap.getCcpMapSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ccpMapSid",
				new Object[] { newCcpMapSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCcpMapSid = result.get(0);

		Assert.assertEquals(existingCcpMapSid, newCcpMapSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ccpMapSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ccpMapSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CcpMap addCcpMap() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpMap ccpMap = _persistence.create(pk);

		ccpMap.setCcpDetailsSid(RandomTestUtil.nextInt());

		ccpMap.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_ccpMaps.add(_persistence.update(ccpMap));

		return ccpMap;
	}

	private List<CcpMap> _ccpMaps = new ArrayList<CcpMap>();
	private CcpMapPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}