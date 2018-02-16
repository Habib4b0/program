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

import com.stpl.app.exception.NoSuchNmDiscountProjMasterException;
import com.stpl.app.model.NmDiscountProjMaster;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.NmDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.NmDiscountProjMasterUtil;

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
public class NmDiscountProjMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmDiscountProjMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmDiscountProjMaster> iterator = _nmDiscountProjMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmDiscountProjMaster nmDiscountProjMaster = _persistence.create(pk);

		Assert.assertNotNull(nmDiscountProjMaster);

		Assert.assertEquals(nmDiscountProjMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		_persistence.remove(newNmDiscountProjMaster);

		NmDiscountProjMaster existingNmDiscountProjMaster = _persistence.fetchByPrimaryKey(newNmDiscountProjMaster.getPrimaryKey());

		Assert.assertNull(existingNmDiscountProjMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmDiscountProjMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmDiscountProjMaster newNmDiscountProjMaster = _persistence.create(pk);

		newNmDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newNmDiscountProjMaster.setDiscountId(RandomTestUtil.randomString());

		newNmDiscountProjMaster.setUserGroup(RandomTestUtil.randomString());

		newNmDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		newNmDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		newNmDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		newNmDiscountProjMaster.setDiscountName(RandomTestUtil.randomString());

		_nmDiscountProjMasters.add(_persistence.update(newNmDiscountProjMaster));

		NmDiscountProjMaster existingNmDiscountProjMaster = _persistence.findByPrimaryKey(newNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjMaster.getCheckRecord(),
			newNmDiscountProjMaster.getCheckRecord());
		Assert.assertEquals(existingNmDiscountProjMaster.getDiscountId(),
			newNmDiscountProjMaster.getDiscountId());
		Assert.assertEquals(existingNmDiscountProjMaster.getUserGroup(),
			newNmDiscountProjMaster.getUserGroup());
		Assert.assertEquals(existingNmDiscountProjMaster.getPriceGroupType(),
			newNmDiscountProjMaster.getPriceGroupType());
		Assert.assertEquals(existingNmDiscountProjMaster.getProjectionDetailsSid(),
			newNmDiscountProjMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingNmDiscountProjMaster.getNetFlag(),
			newNmDiscountProjMaster.getNetFlag());
		Assert.assertEquals(existingNmDiscountProjMaster.getMethodology(),
			newNmDiscountProjMaster.getMethodology());
		Assert.assertEquals(existingNmDiscountProjMaster.getDiscountName(),
			newNmDiscountProjMaster.getDiscountName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		NmDiscountProjMaster existingNmDiscountProjMaster = _persistence.findByPrimaryKey(newNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjMaster,
			newNmDiscountProjMaster);
	}

	@Test(expected = NoSuchNmDiscountProjMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NmDiscountProjMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NM_DISCOUNT_PROJ_MASTER",
			"checkRecord", true, "discountId", true, "userGroup", true,
			"priceGroupType", true, "projectionDetailsSid", true, "netFlag",
			true, "methodology", true, "discountName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		NmDiscountProjMaster existingNmDiscountProjMaster = _persistence.fetchByPrimaryKey(newNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjMaster,
			newNmDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmDiscountProjMaster missingNmDiscountProjMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster1 = addNmDiscountProjMaster();
		NmDiscountProjMaster newNmDiscountProjMaster2 = addNmDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjMaster1.getPrimaryKey());
		primaryKeys.add(newNmDiscountProjMaster2.getPrimaryKey());

		Map<Serializable, NmDiscountProjMaster> nmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmDiscountProjMasters.size());
		Assert.assertEquals(newNmDiscountProjMaster1,
			nmDiscountProjMasters.get(newNmDiscountProjMaster1.getPrimaryKey()));
		Assert.assertEquals(newNmDiscountProjMaster2,
			nmDiscountProjMasters.get(newNmDiscountProjMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmDiscountProjMaster> nmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmDiscountProjMaster> nmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmDiscountProjMasters.size());
		Assert.assertEquals(newNmDiscountProjMaster,
			nmDiscountProjMasters.get(newNmDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmDiscountProjMaster> nmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjMaster.getPrimaryKey());

		Map<Serializable, NmDiscountProjMaster> nmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmDiscountProjMasters.size());
		Assert.assertEquals(newNmDiscountProjMaster,
			nmDiscountProjMasters.get(newNmDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmDiscountProjMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmDiscountProjMaster>() {
				@Override
				public void performAction(
					NmDiscountProjMaster nmDiscountProjMaster) {
					Assert.assertNotNull(nmDiscountProjMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newNmDiscountProjMaster.getProjectionDetailsSid()));

		List<NmDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmDiscountProjMaster existingNmDiscountProjMaster = result.get(0);

		Assert.assertEquals(existingNmDiscountProjMaster,
			newNmDiscountProjMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmDiscountProjMaster newNmDiscountProjMaster = addNmDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newNmDiscountProjMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmDiscountProjMaster addNmDiscountProjMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmDiscountProjMaster nmDiscountProjMaster = _persistence.create(pk);

		nmDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		nmDiscountProjMaster.setDiscountId(RandomTestUtil.randomString());

		nmDiscountProjMaster.setUserGroup(RandomTestUtil.randomString());

		nmDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		nmDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		nmDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		nmDiscountProjMaster.setDiscountName(RandomTestUtil.randomString());

		_nmDiscountProjMasters.add(_persistence.update(nmDiscountProjMaster));

		return nmDiscountProjMaster;
	}

	private List<NmDiscountProjMaster> _nmDiscountProjMasters = new ArrayList<NmDiscountProjMaster>();
	private NmDiscountProjMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}