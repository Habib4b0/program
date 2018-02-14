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

import com.stpl.app.exception.NoSuchMSupplementalDiscMasterException;
import com.stpl.app.model.MSupplementalDiscMaster;
import com.stpl.app.service.MSupplementalDiscMasterLocalServiceUtil;
import com.stpl.app.service.persistence.MSupplementalDiscMasterPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscMasterUtil;

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
public class MSupplementalDiscMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MSupplementalDiscMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MSupplementalDiscMaster> iterator = _mSupplementalDiscMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscMaster mSupplementalDiscMaster = _persistence.create(pk);

		Assert.assertNotNull(mSupplementalDiscMaster);

		Assert.assertEquals(mSupplementalDiscMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		_persistence.remove(newMSupplementalDiscMaster);

		MSupplementalDiscMaster existingMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(newMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertNull(existingMSupplementalDiscMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMSupplementalDiscMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscMaster newMSupplementalDiscMaster = _persistence.create(pk);

		newMSupplementalDiscMaster.setActualDiscountRate2(RandomTestUtil.nextDouble());

		newMSupplementalDiscMaster.setActualDiscountRate1(RandomTestUtil.nextDouble());

		newMSupplementalDiscMaster.setMarketType(RandomTestUtil.randomString());

		newMSupplementalDiscMaster.setActualMethodology(RandomTestUtil.randomString());

		newMSupplementalDiscMaster.setActualContractPrice(RandomTestUtil.nextDouble());

		newMSupplementalDiscMaster.setActualDiscount(RandomTestUtil.nextDouble());

		newMSupplementalDiscMaster.setCheckRecord(RandomTestUtil.nextInt());

		newMSupplementalDiscMaster.setContractEndDate(RandomTestUtil.nextDate());

		_mSupplementalDiscMasters.add(_persistence.update(
				newMSupplementalDiscMaster));

		MSupplementalDiscMaster existingMSupplementalDiscMaster = _persistence.findByPrimaryKey(newMSupplementalDiscMaster.getPrimaryKey());

		AssertUtils.assertEquals(existingMSupplementalDiscMaster.getActualDiscountRate2(),
			newMSupplementalDiscMaster.getActualDiscountRate2());
		AssertUtils.assertEquals(existingMSupplementalDiscMaster.getActualDiscountRate1(),
			newMSupplementalDiscMaster.getActualDiscountRate1());
		Assert.assertEquals(existingMSupplementalDiscMaster.getMarketType(),
			newMSupplementalDiscMaster.getMarketType());
		Assert.assertEquals(existingMSupplementalDiscMaster.getActualMethodology(),
			newMSupplementalDiscMaster.getActualMethodology());
		AssertUtils.assertEquals(existingMSupplementalDiscMaster.getActualContractPrice(),
			newMSupplementalDiscMaster.getActualContractPrice());
		Assert.assertEquals(existingMSupplementalDiscMaster.getProjectionDetailsSid(),
			newMSupplementalDiscMaster.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingMSupplementalDiscMaster.getActualDiscount(),
			newMSupplementalDiscMaster.getActualDiscount());
		Assert.assertEquals(existingMSupplementalDiscMaster.getCheckRecord(),
			newMSupplementalDiscMaster.getCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMSupplementalDiscMaster.getContractEndDate()),
			Time.getShortTimestamp(
				newMSupplementalDiscMaster.getContractEndDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		MSupplementalDiscMaster existingMSupplementalDiscMaster = _persistence.findByPrimaryKey(newMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscMaster,
			newMSupplementalDiscMaster);
	}

	@Test(expected = NoSuchMSupplementalDiscMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MSupplementalDiscMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_SUPPLEMENTAL_DISC_MASTER",
			"actualDiscountRate2", true, "actualDiscountRate1", true,
			"marketType", true, "actualMethodology", true,
			"actualContractPrice", true, "projectionDetailsSid", true,
			"actualDiscount", true, "checkRecord", true, "contractEndDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		MSupplementalDiscMaster existingMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(newMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscMaster,
			newMSupplementalDiscMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscMaster missingMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMSupplementalDiscMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster1 = addMSupplementalDiscMaster();
		MSupplementalDiscMaster newMSupplementalDiscMaster2 = addMSupplementalDiscMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscMaster1.getPrimaryKey());
		primaryKeys.add(newMSupplementalDiscMaster2.getPrimaryKey());

		Map<Serializable, MSupplementalDiscMaster> mSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mSupplementalDiscMasters.size());
		Assert.assertEquals(newMSupplementalDiscMaster1,
			mSupplementalDiscMasters.get(
				newMSupplementalDiscMaster1.getPrimaryKey()));
		Assert.assertEquals(newMSupplementalDiscMaster2,
			mSupplementalDiscMasters.get(
				newMSupplementalDiscMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MSupplementalDiscMaster> mSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MSupplementalDiscMaster> mSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscMasters.size());
		Assert.assertEquals(newMSupplementalDiscMaster,
			mSupplementalDiscMasters.get(
				newMSupplementalDiscMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MSupplementalDiscMaster> mSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscMaster.getPrimaryKey());

		Map<Serializable, MSupplementalDiscMaster> mSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscMasters.size());
		Assert.assertEquals(newMSupplementalDiscMaster,
			mSupplementalDiscMasters.get(
				newMSupplementalDiscMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MSupplementalDiscMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MSupplementalDiscMaster>() {
				@Override
				public void performAction(
					MSupplementalDiscMaster mSupplementalDiscMaster) {
					Assert.assertNotNull(mSupplementalDiscMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newMSupplementalDiscMaster.getProjectionDetailsSid()));

		List<MSupplementalDiscMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MSupplementalDiscMaster existingMSupplementalDiscMaster = result.get(0);

		Assert.assertEquals(existingMSupplementalDiscMaster,
			newMSupplementalDiscMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<MSupplementalDiscMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MSupplementalDiscMaster newMSupplementalDiscMaster = addMSupplementalDiscMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newMSupplementalDiscMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MSupplementalDiscMaster addMSupplementalDiscMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscMaster mSupplementalDiscMaster = _persistence.create(pk);

		mSupplementalDiscMaster.setActualDiscountRate2(RandomTestUtil.nextDouble());

		mSupplementalDiscMaster.setActualDiscountRate1(RandomTestUtil.nextDouble());

		mSupplementalDiscMaster.setMarketType(RandomTestUtil.randomString());

		mSupplementalDiscMaster.setActualMethodology(RandomTestUtil.randomString());

		mSupplementalDiscMaster.setActualContractPrice(RandomTestUtil.nextDouble());

		mSupplementalDiscMaster.setActualDiscount(RandomTestUtil.nextDouble());

		mSupplementalDiscMaster.setCheckRecord(RandomTestUtil.nextInt());

		mSupplementalDiscMaster.setContractEndDate(RandomTestUtil.nextDate());

		_mSupplementalDiscMasters.add(_persistence.update(
				mSupplementalDiscMaster));

		return mSupplementalDiscMaster;
	}

	private List<MSupplementalDiscMaster> _mSupplementalDiscMasters = new ArrayList<MSupplementalDiscMaster>();
	private MSupplementalDiscMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}