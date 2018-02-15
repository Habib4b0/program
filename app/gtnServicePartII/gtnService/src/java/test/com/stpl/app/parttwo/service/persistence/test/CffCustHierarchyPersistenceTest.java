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

import com.stpl.app.parttwo.exception.NoSuchCffCustHierarchyException;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffCustHierarchyPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustHierarchyUtil;

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
public class CffCustHierarchyPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffCustHierarchyUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffCustHierarchy> iterator = _cffCustHierarchies.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustHierarchy cffCustHierarchy = _persistence.create(pk);

		Assert.assertNotNull(cffCustHierarchy);

		Assert.assertEquals(cffCustHierarchy.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		_persistence.remove(newCffCustHierarchy);

		CffCustHierarchy existingCffCustHierarchy = _persistence.fetchByPrimaryKey(newCffCustHierarchy.getPrimaryKey());

		Assert.assertNull(existingCffCustHierarchy);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffCustHierarchy();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustHierarchy newCffCustHierarchy = _persistence.create(pk);

		newCffCustHierarchy.setCffMasterSid(RandomTestUtil.nextInt());

		newCffCustHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_cffCustHierarchies.add(_persistence.update(newCffCustHierarchy));

		CffCustHierarchy existingCffCustHierarchy = _persistence.findByPrimaryKey(newCffCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffCustHierarchy.getCffCustHierarchySid(),
			newCffCustHierarchy.getCffCustHierarchySid());
		Assert.assertEquals(existingCffCustHierarchy.getCffMasterSid(),
			newCffCustHierarchy.getCffMasterSid());
		Assert.assertEquals(existingCffCustHierarchy.getRelationshipLevelSid(),
			newCffCustHierarchy.getRelationshipLevelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		CffCustHierarchy existingCffCustHierarchy = _persistence.findByPrimaryKey(newCffCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffCustHierarchy, newCffCustHierarchy);
	}

	@Test(expected = NoSuchCffCustHierarchyException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffCustHierarchy> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_CUST_HIERARCHY",
			"cffCustHierarchySid", true, "cffMasterSid", true,
			"relationshipLevelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		CffCustHierarchy existingCffCustHierarchy = _persistence.fetchByPrimaryKey(newCffCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingCffCustHierarchy, newCffCustHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustHierarchy missingCffCustHierarchy = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffCustHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffCustHierarchy newCffCustHierarchy1 = addCffCustHierarchy();
		CffCustHierarchy newCffCustHierarchy2 = addCffCustHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustHierarchy1.getPrimaryKey());
		primaryKeys.add(newCffCustHierarchy2.getPrimaryKey());

		Map<Serializable, CffCustHierarchy> cffCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffCustHierarchies.size());
		Assert.assertEquals(newCffCustHierarchy1,
			cffCustHierarchies.get(newCffCustHierarchy1.getPrimaryKey()));
		Assert.assertEquals(newCffCustHierarchy2,
			cffCustHierarchies.get(newCffCustHierarchy2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffCustHierarchy> cffCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustHierarchy.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffCustHierarchy> cffCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustHierarchies.size());
		Assert.assertEquals(newCffCustHierarchy,
			cffCustHierarchies.get(newCffCustHierarchy.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffCustHierarchy> cffCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustHierarchy.getPrimaryKey());

		Map<Serializable, CffCustHierarchy> cffCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustHierarchies.size());
		Assert.assertEquals(newCffCustHierarchy,
			cffCustHierarchies.get(newCffCustHierarchy.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffCustHierarchyLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffCustHierarchy>() {
				@Override
				public void performAction(CffCustHierarchy cffCustHierarchy) {
					Assert.assertNotNull(cffCustHierarchy);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustHierarchySid",
				newCffCustHierarchy.getCffCustHierarchySid()));

		List<CffCustHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffCustHierarchy existingCffCustHierarchy = result.get(0);

		Assert.assertEquals(existingCffCustHierarchy, newCffCustHierarchy);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustHierarchySid",
				RandomTestUtil.nextInt()));

		List<CffCustHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffCustHierarchy newCffCustHierarchy = addCffCustHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustHierarchySid"));

		Object newCffCustHierarchySid = newCffCustHierarchy.getCffCustHierarchySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustHierarchySid",
				new Object[] { newCffCustHierarchySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffCustHierarchySid = result.get(0);

		Assert.assertEquals(existingCffCustHierarchySid, newCffCustHierarchySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustHierarchySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustHierarchySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffCustHierarchy addCffCustHierarchy() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustHierarchy cffCustHierarchy = _persistence.create(pk);

		cffCustHierarchy.setCffMasterSid(RandomTestUtil.nextInt());

		cffCustHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_cffCustHierarchies.add(_persistence.update(cffCustHierarchy));

		return cffCustHierarchy;
	}

	private List<CffCustHierarchy> _cffCustHierarchies = new ArrayList<CffCustHierarchy>();
	private CffCustHierarchyPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}