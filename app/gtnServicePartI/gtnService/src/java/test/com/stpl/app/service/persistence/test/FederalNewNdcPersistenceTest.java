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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchFederalNewNdcException;
import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.service.FederalNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.FederalNewNdcPersistence;
import com.stpl.app.service.persistence.FederalNewNdcUtil;

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
public class FederalNewNdcPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = FederalNewNdcUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FederalNewNdc> iterator = _federalNewNdcs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FederalNewNdc federalNewNdc = _persistence.create(pk);

		Assert.assertNotNull(federalNewNdc);

		Assert.assertEquals(federalNewNdc.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		_persistence.remove(newFederalNewNdc);

		FederalNewNdc existingFederalNewNdc = _persistence.fetchByPrimaryKey(newFederalNewNdc.getPrimaryKey());

		Assert.assertNull(existingFederalNewNdc);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFederalNewNdc();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FederalNewNdc newFederalNewNdc = _persistence.create(pk);

		newFederalNewNdc.setFss(RandomTestUtil.nextDouble());

		newFederalNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		newFederalNewNdc.setNonFamp(RandomTestUtil.nextDouble());

		_federalNewNdcs.add(_persistence.update(newFederalNewNdc));

		FederalNewNdc existingFederalNewNdc = _persistence.findByPrimaryKey(newFederalNewNdc.getPrimaryKey());

		AssertUtils.assertEquals(existingFederalNewNdc.getFss(),
			newFederalNewNdc.getFss());
		Assert.assertEquals(existingFederalNewNdc.getItemMasterSid(),
			newFederalNewNdc.getItemMasterSid());
		AssertUtils.assertEquals(existingFederalNewNdc.getWacPrice(),
			newFederalNewNdc.getWacPrice());
		AssertUtils.assertEquals(existingFederalNewNdc.getNonFamp(),
			newFederalNewNdc.getNonFamp());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		FederalNewNdc existingFederalNewNdc = _persistence.findByPrimaryKey(newFederalNewNdc.getPrimaryKey());

		Assert.assertEquals(existingFederalNewNdc, newFederalNewNdc);
	}

	@Test(expected = NoSuchFederalNewNdcException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FederalNewNdc> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FEDERAL_NEW_NDC", "fss",
			true, "itemMasterSid", true, "wacPrice", true, "nonFamp", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		FederalNewNdc existingFederalNewNdc = _persistence.fetchByPrimaryKey(newFederalNewNdc.getPrimaryKey());

		Assert.assertEquals(existingFederalNewNdc, newFederalNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FederalNewNdc missingFederalNewNdc = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFederalNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FederalNewNdc newFederalNewNdc1 = addFederalNewNdc();
		FederalNewNdc newFederalNewNdc2 = addFederalNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFederalNewNdc1.getPrimaryKey());
		primaryKeys.add(newFederalNewNdc2.getPrimaryKey());

		Map<Serializable, FederalNewNdc> federalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, federalNewNdcs.size());
		Assert.assertEquals(newFederalNewNdc1,
			federalNewNdcs.get(newFederalNewNdc1.getPrimaryKey()));
		Assert.assertEquals(newFederalNewNdc2,
			federalNewNdcs.get(newFederalNewNdc2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FederalNewNdc> federalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(federalNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFederalNewNdc.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FederalNewNdc> federalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, federalNewNdcs.size());
		Assert.assertEquals(newFederalNewNdc,
			federalNewNdcs.get(newFederalNewNdc.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FederalNewNdc> federalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(federalNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFederalNewNdc.getPrimaryKey());

		Map<Serializable, FederalNewNdc> federalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, federalNewNdcs.size());
		Assert.assertEquals(newFederalNewNdc,
			federalNewNdcs.get(newFederalNewNdc.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FederalNewNdcLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FederalNewNdc>() {
				@Override
				public void performAction(FederalNewNdc federalNewNdc) {
					Assert.assertNotNull(federalNewNdc);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				newFederalNewNdc.getItemMasterSid()));

		List<FederalNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FederalNewNdc existingFederalNewNdc = result.get(0);

		Assert.assertEquals(existingFederalNewNdc, newFederalNewNdc);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				RandomTestUtil.nextInt()));

		List<FederalNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FederalNewNdc newFederalNewNdc = addFederalNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		Object newItemMasterSid = newFederalNewNdc.getItemMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { newItemMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemMasterSid = result.get(0);

		Assert.assertEquals(existingItemMasterSid, newItemMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FederalNewNdc addFederalNewNdc() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FederalNewNdc federalNewNdc = _persistence.create(pk);

		federalNewNdc.setFss(RandomTestUtil.nextDouble());

		federalNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		federalNewNdc.setNonFamp(RandomTestUtil.nextDouble());

		_federalNewNdcs.add(_persistence.update(federalNewNdc));

		return federalNewNdc;
	}

	private List<FederalNewNdc> _federalNewNdcs = new ArrayList<FederalNewNdc>();
	private FederalNewNdcPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}