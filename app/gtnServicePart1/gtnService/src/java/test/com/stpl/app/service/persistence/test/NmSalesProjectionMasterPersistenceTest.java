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

import com.stpl.app.exception.NoSuchNmSalesProjectionMasterException;
import com.stpl.app.model.NmSalesProjectionMaster;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.NmSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.NmSalesProjectionMasterUtil;

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
public class NmSalesProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmSalesProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmSalesProjectionMaster> iterator = _nmSalesProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmSalesProjectionMaster nmSalesProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(nmSalesProjectionMaster);

		Assert.assertEquals(nmSalesProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		_persistence.remove(newNmSalesProjectionMaster);

		NmSalesProjectionMaster existingNmSalesProjectionMaster = _persistence.fetchByPrimaryKey(newNmSalesProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingNmSalesProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmSalesProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmSalesProjectionMaster newNmSalesProjectionMaster = _persistence.create(pk);

		newNmSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newNmSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		newNmSalesProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		newNmSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_nmSalesProjectionMasters.add(_persistence.update(
				newNmSalesProjectionMaster));

		NmSalesProjectionMaster existingNmSalesProjectionMaster = _persistence.findByPrimaryKey(newNmSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjectionMaster.getCheckRecord(),
			newNmSalesProjectionMaster.getCheckRecord());
		Assert.assertEquals(existingNmSalesProjectionMaster.getCalculationPeriods(),
			newNmSalesProjectionMaster.getCalculationPeriods());
		Assert.assertEquals(existingNmSalesProjectionMaster.getUserGroup(),
			newNmSalesProjectionMaster.getUserGroup());
		Assert.assertEquals(existingNmSalesProjectionMaster.getProjectionDetailsSid(),
			newNmSalesProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingNmSalesProjectionMaster.getMethodology(),
			newNmSalesProjectionMaster.getMethodology());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		NmSalesProjectionMaster existingNmSalesProjectionMaster = _persistence.findByPrimaryKey(newNmSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjectionMaster,
			newNmSalesProjectionMaster);
	}

	@Test(expected = NoSuchNmSalesProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NmSalesProjectionMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NM_SALES_PROJECTION_MASTER",
			"checkRecord", true, "calculationPeriods", true, "userGroup", true,
			"projectionDetailsSid", true, "methodology", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		NmSalesProjectionMaster existingNmSalesProjectionMaster = _persistence.fetchByPrimaryKey(newNmSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjectionMaster,
			newNmSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmSalesProjectionMaster missingNmSalesProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster1 = addNmSalesProjectionMaster();
		NmSalesProjectionMaster newNmSalesProjectionMaster2 = addNmSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newNmSalesProjectionMaster2.getPrimaryKey());

		Map<Serializable, NmSalesProjectionMaster> nmSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmSalesProjectionMasters.size());
		Assert.assertEquals(newNmSalesProjectionMaster1,
			nmSalesProjectionMasters.get(
				newNmSalesProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newNmSalesProjectionMaster2,
			nmSalesProjectionMasters.get(
				newNmSalesProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmSalesProjectionMaster> nmSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmSalesProjectionMaster> nmSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmSalesProjectionMasters.size());
		Assert.assertEquals(newNmSalesProjectionMaster,
			nmSalesProjectionMasters.get(
				newNmSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmSalesProjectionMaster> nmSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjectionMaster.getPrimaryKey());

		Map<Serializable, NmSalesProjectionMaster> nmSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmSalesProjectionMasters.size());
		Assert.assertEquals(newNmSalesProjectionMaster,
			nmSalesProjectionMasters.get(
				newNmSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmSalesProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmSalesProjectionMaster>() {
				@Override
				public void performAction(
					NmSalesProjectionMaster nmSalesProjectionMaster) {
					Assert.assertNotNull(nmSalesProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newNmSalesProjectionMaster.getProjectionDetailsSid()));

		List<NmSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmSalesProjectionMaster existingNmSalesProjectionMaster = result.get(0);

		Assert.assertEquals(existingNmSalesProjectionMaster,
			newNmSalesProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmSalesProjectionMaster newNmSalesProjectionMaster = addNmSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newNmSalesProjectionMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmSalesProjectionMaster addNmSalesProjectionMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmSalesProjectionMaster nmSalesProjectionMaster = _persistence.create(pk);

		nmSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		nmSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		nmSalesProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		nmSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_nmSalesProjectionMasters.add(_persistence.update(
				nmSalesProjectionMaster));

		return nmSalesProjectionMaster;
	}

	private List<NmSalesProjectionMaster> _nmSalesProjectionMasters = new ArrayList<NmSalesProjectionMaster>();
	private NmSalesProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}