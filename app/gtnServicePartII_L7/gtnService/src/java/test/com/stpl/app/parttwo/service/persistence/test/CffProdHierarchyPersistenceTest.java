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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchCffProdHierarchyException;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffProdHierarchyPersistence;
import com.stpl.app.parttwo.service.persistence.CffProdHierarchyUtil;

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
public class CffProdHierarchyPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffProdHierarchyUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffProdHierarchy> iterator = _cffProdHierarchies.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffProdHierarchy cffProdHierarchy = _persistence.create(pk);

		Assert.assertNotNull(cffProdHierarchy);

		Assert.assertEquals(cffProdHierarchy.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		_persistence.remove(newCffProdHierarchy);

		CffProdHierarchy existingCffProdHierarchy = _persistence.fetchByPrimaryKey(newCffProdHierarchy.getPrimaryKey());

		Assert.assertNull(existingCffProdHierarchy);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffProdHierarchy();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffProdHierarchy newCffProdHierarchy = _persistence.create(pk);

		newCffProdHierarchy.setCffMasterSid(RandomTestUtil.nextInt());

		newCffProdHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_cffProdHierarchies.add(_persistence.update(newCffProdHierarchy));

		CffProdHierarchy existingCffProdHierarchy = _persistence.findByPrimaryKey(newCffProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffProdHierarchy.getCffMasterSid(),
			newCffProdHierarchy.getCffMasterSid());
		Assert.assertEquals(existingCffProdHierarchy.getRelationshipLevelSid(),
			newCffProdHierarchy.getRelationshipLevelSid());
		Assert.assertEquals(existingCffProdHierarchy.getCffProdHierarchySid(),
			newCffProdHierarchy.getCffProdHierarchySid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		CffProdHierarchy existingCffProdHierarchy = _persistence.findByPrimaryKey(newCffProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffProdHierarchy, newCffProdHierarchy);
	}

	@Test(expected = NoSuchCffProdHierarchyException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffProdHierarchy> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_PROD_HIERARCHY",
			"cffMasterSid", true, "relationshipLevelSid", true,
			"cffProdHierarchySid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		CffProdHierarchy existingCffProdHierarchy = _persistence.fetchByPrimaryKey(newCffProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffProdHierarchy, newCffProdHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffProdHierarchy missingCffProdHierarchy = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffProdHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffProdHierarchy newCffProdHierarchy1 = addCffProdHierarchy();
		CffProdHierarchy newCffProdHierarchy2 = addCffProdHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffProdHierarchy1.getPrimaryKey());
		primaryKeys.add(newCffProdHierarchy2.getPrimaryKey());

		Map<Serializable, CffProdHierarchy> cffProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffProdHierarchies.size());
		Assert.assertEquals(newCffProdHierarchy1,
			cffProdHierarchies.get(newCffProdHierarchy1.getPrimaryKey()));
		Assert.assertEquals(newCffProdHierarchy2,
			cffProdHierarchies.get(newCffProdHierarchy2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffProdHierarchy> cffProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffProdHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffProdHierarchy.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffProdHierarchy> cffProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffProdHierarchies.size());
		Assert.assertEquals(newCffProdHierarchy,
			cffProdHierarchies.get(newCffProdHierarchy.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffProdHierarchy> cffProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffProdHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffProdHierarchy.getPrimaryKey());

		Map<Serializable, CffProdHierarchy> cffProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffProdHierarchies.size());
		Assert.assertEquals(newCffProdHierarchy,
			cffProdHierarchies.get(newCffProdHierarchy.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffProdHierarchyLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffProdHierarchy>() {
				@Override
				public void performAction(CffProdHierarchy cffProdHierarchy) {
					Assert.assertNotNull(cffProdHierarchy);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffProdHierarchySid",
				newCffProdHierarchy.getCffProdHierarchySid()));

		List<CffProdHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffProdHierarchy existingCffProdHierarchy = result.get(0);

		Assert.assertEquals(existingCffProdHierarchy, newCffProdHierarchy);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffProdHierarchySid",
				RandomTestUtil.nextInt()));

		List<CffProdHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffProdHierarchy newCffProdHierarchy = addCffProdHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffProdHierarchySid"));

		Object newCffProdHierarchySid = newCffProdHierarchy.getCffProdHierarchySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffProdHierarchySid",
				new Object[] { newCffProdHierarchySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffProdHierarchySid = result.get(0);

		Assert.assertEquals(existingCffProdHierarchySid, newCffProdHierarchySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffProdHierarchySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffProdHierarchySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffProdHierarchy addCffProdHierarchy() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffProdHierarchy cffProdHierarchy = _persistence.create(pk);

		cffProdHierarchy.setCffMasterSid(RandomTestUtil.nextInt());

		cffProdHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_cffProdHierarchies.add(_persistence.update(cffProdHierarchy));

		return cffProdHierarchy;
	}

	private List<CffProdHierarchy> _cffProdHierarchies = new ArrayList<CffProdHierarchy>();
	private CffProdHierarchyPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}