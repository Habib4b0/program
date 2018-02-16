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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchNmPpaProjectionMasterException;
import com.stpl.app.model.NmPpaProjectionMaster;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.NmPpaProjectionMasterPersistence;
import com.stpl.app.service.persistence.NmPpaProjectionMasterUtil;

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
public class NmPpaProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmPpaProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmPpaProjectionMaster> iterator = _nmPpaProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmPpaProjectionMaster nmPpaProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(nmPpaProjectionMaster);

		Assert.assertEquals(nmPpaProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		_persistence.remove(newNmPpaProjectionMaster);

		NmPpaProjectionMaster existingNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(newNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingNmPpaProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmPpaProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmPpaProjectionMaster newNmPpaProjectionMaster = _persistence.create(pk);

		newNmPpaProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newNmPpaProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		newNmPpaProjectionMaster.setPriceBasis(RandomTestUtil.randomString());

		newNmPpaProjectionMaster.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newNmPpaProjectionMaster.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newNmPpaProjectionMaster.setActualPriceCap(RandomTestUtil.nextDouble());

		_nmPpaProjectionMasters.add(_persistence.update(
				newNmPpaProjectionMaster));

		NmPpaProjectionMaster existingNmPpaProjectionMaster = _persistence.findByPrimaryKey(newNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjectionMaster.getCheckRecord(),
			newNmPpaProjectionMaster.getCheckRecord());
		Assert.assertEquals(existingNmPpaProjectionMaster.getUserGroup(),
			newNmPpaProjectionMaster.getUserGroup());
		Assert.assertEquals(existingNmPpaProjectionMaster.getProjectionDetailsSid(),
			newNmPpaProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingNmPpaProjectionMaster.getPriceBasis(),
			newNmPpaProjectionMaster.getPriceBasis());
		Assert.assertEquals(Time.getShortTimestamp(
				existingNmPpaProjectionMaster.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newNmPpaProjectionMaster.getPriceProtectionEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingNmPpaProjectionMaster.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newNmPpaProjectionMaster.getPriceProtectionStartDate()));
		AssertUtils.assertEquals(existingNmPpaProjectionMaster.getActualPriceCap(),
			newNmPpaProjectionMaster.getActualPriceCap());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		NmPpaProjectionMaster existingNmPpaProjectionMaster = _persistence.findByPrimaryKey(newNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjectionMaster,
			newNmPpaProjectionMaster);
	}

	@Test(expected = NoSuchNmPpaProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NmPpaProjectionMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NM_PPA_PROJECTION_MASTER",
			"checkRecord", true, "userGroup", true, "projectionDetailsSid",
			true, "priceBasis", true, "priceProtectionEndDate", true,
			"priceProtectionStartDate", true, "actualPriceCap", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		NmPpaProjectionMaster existingNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(newNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjectionMaster,
			newNmPpaProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmPpaProjectionMaster missingNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmPpaProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster1 = addNmPpaProjectionMaster();
		NmPpaProjectionMaster newNmPpaProjectionMaster2 = addNmPpaProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newNmPpaProjectionMaster2.getPrimaryKey());

		Map<Serializable, NmPpaProjectionMaster> nmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmPpaProjectionMasters.size());
		Assert.assertEquals(newNmPpaProjectionMaster1,
			nmPpaProjectionMasters.get(
				newNmPpaProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newNmPpaProjectionMaster2,
			nmPpaProjectionMasters.get(
				newNmPpaProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmPpaProjectionMaster> nmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmPpaProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmPpaProjectionMaster> nmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmPpaProjectionMasters.size());
		Assert.assertEquals(newNmPpaProjectionMaster,
			nmPpaProjectionMasters.get(newNmPpaProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmPpaProjectionMaster> nmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmPpaProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjectionMaster.getPrimaryKey());

		Map<Serializable, NmPpaProjectionMaster> nmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmPpaProjectionMasters.size());
		Assert.assertEquals(newNmPpaProjectionMaster,
			nmPpaProjectionMasters.get(newNmPpaProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmPpaProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmPpaProjectionMaster>() {
				@Override
				public void performAction(
					NmPpaProjectionMaster nmPpaProjectionMaster) {
					Assert.assertNotNull(nmPpaProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newNmPpaProjectionMaster.getProjectionDetailsSid()));

		List<NmPpaProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmPpaProjectionMaster existingNmPpaProjectionMaster = result.get(0);

		Assert.assertEquals(existingNmPpaProjectionMaster,
			newNmPpaProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmPpaProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmPpaProjectionMaster newNmPpaProjectionMaster = addNmPpaProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newNmPpaProjectionMaster.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmPpaProjectionMaster addNmPpaProjectionMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmPpaProjectionMaster nmPpaProjectionMaster = _persistence.create(pk);

		nmPpaProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		nmPpaProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		nmPpaProjectionMaster.setPriceBasis(RandomTestUtil.randomString());

		nmPpaProjectionMaster.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		nmPpaProjectionMaster.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		nmPpaProjectionMaster.setActualPriceCap(RandomTestUtil.nextDouble());

		_nmPpaProjectionMasters.add(_persistence.update(nmPpaProjectionMaster));

		return nmPpaProjectionMaster;
	}

	private List<NmPpaProjectionMaster> _nmPpaProjectionMasters = new ArrayList<NmPpaProjectionMaster>();
	private NmPpaProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}