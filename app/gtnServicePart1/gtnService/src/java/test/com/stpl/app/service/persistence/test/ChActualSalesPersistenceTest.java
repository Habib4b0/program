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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchChActualSalesException;
import com.stpl.app.model.ChActualSales;
import com.stpl.app.service.ChActualSalesLocalServiceUtil;
import com.stpl.app.service.persistence.ChActualSalesPK;
import com.stpl.app.service.persistence.ChActualSalesPersistence;
import com.stpl.app.service.persistence.ChActualSalesUtil;

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
public class ChActualSalesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChActualSalesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChActualSales> iterator = _chActualSaleses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChActualSales chActualSales = _persistence.create(pk);

		Assert.assertNotNull(chActualSales);

		Assert.assertEquals(chActualSales.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		_persistence.remove(newChActualSales);

		ChActualSales existingChActualSales = _persistence.fetchByPrimaryKey(newChActualSales.getPrimaryKey());

		Assert.assertNull(existingChActualSales);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChActualSales();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChActualSales newChActualSales = _persistence.create(pk);

		newChActualSales.setContractUnits(RandomTestUtil.nextDouble());

		newChActualSales.setPerOfBusiness(RandomTestUtil.nextDouble());

		newChActualSales.setContractSales(RandomTestUtil.nextDouble());

		newChActualSales.setGtsSales(RandomTestUtil.nextDouble());

		_chActualSaleses.add(_persistence.update(newChActualSales));

		ChActualSales existingChActualSales = _persistence.findByPrimaryKey(newChActualSales.getPrimaryKey());

		AssertUtils.assertEquals(existingChActualSales.getContractUnits(),
			newChActualSales.getContractUnits());
		AssertUtils.assertEquals(existingChActualSales.getPerOfBusiness(),
			newChActualSales.getPerOfBusiness());
		Assert.assertEquals(existingChActualSales.getPeriodSid(),
			newChActualSales.getPeriodSid());
		AssertUtils.assertEquals(existingChActualSales.getContractSales(),
			newChActualSales.getContractSales());
		Assert.assertEquals(existingChActualSales.getProjectionDetailsSid(),
			newChActualSales.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingChActualSales.getGtsSales(),
			newChActualSales.getGtsSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		ChActualSales existingChActualSales = _persistence.findByPrimaryKey(newChActualSales.getPrimaryKey());

		Assert.assertEquals(existingChActualSales, newChActualSales);
	}

	@Test(expected = NoSuchChActualSalesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		ChActualSales existingChActualSales = _persistence.fetchByPrimaryKey(newChActualSales.getPrimaryKey());

		Assert.assertEquals(existingChActualSales, newChActualSales);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChActualSales missingChActualSales = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChActualSales);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChActualSales newChActualSales1 = addChActualSales();
		ChActualSales newChActualSales2 = addChActualSales();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualSales1.getPrimaryKey());
		primaryKeys.add(newChActualSales2.getPrimaryKey());

		Map<Serializable, ChActualSales> chActualSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chActualSaleses.size());
		Assert.assertEquals(newChActualSales1,
			chActualSaleses.get(newChActualSales1.getPrimaryKey()));
		Assert.assertEquals(newChActualSales2,
			chActualSaleses.get(newChActualSales2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ChActualSalesPK pk1 = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChActualSalesPK pk2 = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChActualSales> chActualSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chActualSaleses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualSales.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChActualSales> chActualSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chActualSaleses.size());
		Assert.assertEquals(newChActualSales,
			chActualSaleses.get(newChActualSales.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChActualSales> chActualSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chActualSaleses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualSales.getPrimaryKey());

		Map<Serializable, ChActualSales> chActualSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chActualSaleses.size());
		Assert.assertEquals(newChActualSales,
			chActualSaleses.get(newChActualSales.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChActualSalesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChActualSales>() {
				@Override
				public void performAction(ChActualSales chActualSales) {
					Assert.assertNotNull(chActualSales);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newChActualSales.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newChActualSales.getProjectionDetailsSid()));

		List<ChActualSales> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChActualSales existingChActualSales = result.get(0);

		Assert.assertEquals(existingChActualSales, newChActualSales);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<ChActualSales> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChActualSales newChActualSales = addChActualSales();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newChActualSales.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChActualSales addChActualSales() throws Exception {
		ChActualSalesPK pk = new ChActualSalesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChActualSales chActualSales = _persistence.create(pk);

		chActualSales.setContractUnits(RandomTestUtil.nextDouble());

		chActualSales.setPerOfBusiness(RandomTestUtil.nextDouble());

		chActualSales.setContractSales(RandomTestUtil.nextDouble());

		chActualSales.setGtsSales(RandomTestUtil.nextDouble());

		_chActualSaleses.add(_persistence.update(chActualSales));

		return chActualSales;
	}

	private List<ChActualSales> _chActualSaleses = new ArrayList<ChActualSales>();
	private ChActualSalesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}