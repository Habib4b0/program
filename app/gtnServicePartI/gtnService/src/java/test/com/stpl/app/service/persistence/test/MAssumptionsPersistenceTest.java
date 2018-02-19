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

import com.stpl.app.exception.NoSuchMAssumptionsException;
import com.stpl.app.model.MAssumptions;
import com.stpl.app.service.MAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.MAssumptionsPersistence;
import com.stpl.app.service.persistence.MAssumptionsUtil;

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
public class MAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MAssumptions> iterator = _mAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MAssumptions mAssumptions = _persistence.create(pk);

		Assert.assertNotNull(mAssumptions);

		Assert.assertEquals(mAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		_persistence.remove(newMAssumptions);

		MAssumptions existingMAssumptions = _persistence.fetchByPrimaryKey(newMAssumptions.getPrimaryKey());

		Assert.assertNull(existingMAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MAssumptions newMAssumptions = _persistence.create(pk);

		newMAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		newMAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		newMAssumptions.setProjYear(RandomTestUtil.nextInt());

		newMAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		newMAssumptions.setCamId(RandomTestUtil.nextInt());

		newMAssumptions.setCommentary(RandomTestUtil.randomString());

		newMAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		newMAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		newMAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		newMAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		newMAssumptions.setParent(RandomTestUtil.randomBoolean());

		newMAssumptions.setProjectionPeriod(RandomTestUtil.nextInt());

		newMAssumptions.setProjectionDetailsSid(RandomTestUtil.nextInt());

		newMAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		newMAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		newMAssumptions.setReasonCodes(RandomTestUtil.randomString());

		_mAssumptionses.add(_persistence.update(newMAssumptions));

		MAssumptions existingMAssumptions = _persistence.findByPrimaryKey(newMAssumptions.getPrimaryKey());

		AssertUtils.assertEquals(existingMAssumptions.getGrossSalesPercentChange(),
			newMAssumptions.getGrossSalesPercentChange());
		AssertUtils.assertEquals(existingMAssumptions.getGrossSalesPrior(),
			newMAssumptions.getGrossSalesPrior());
		Assert.assertEquals(existingMAssumptions.getProjYear(),
			newMAssumptions.getProjYear());
		AssertUtils.assertEquals(existingMAssumptions.getTotalDiscountPercentProjected(),
			newMAssumptions.getTotalDiscountPercentProjected());
		Assert.assertEquals(existingMAssumptions.getCamId(),
			newMAssumptions.getCamId());
		Assert.assertEquals(existingMAssumptions.getCommentary(),
			newMAssumptions.getCommentary());
		AssertUtils.assertEquals(existingMAssumptions.getGrossSalesProjected(),
			newMAssumptions.getGrossSalesProjected());
		AssertUtils.assertEquals(existingMAssumptions.getTotalDiscountPercentChange(),
			newMAssumptions.getTotalDiscountPercentChange());
		AssertUtils.assertEquals(existingMAssumptions.getTotalDiscountPercentPrior(),
			newMAssumptions.getTotalDiscountPercentPrior());
		AssertUtils.assertEquals(existingMAssumptions.getNetSalesPercentChange(),
			newMAssumptions.getNetSalesPercentChange());
		Assert.assertEquals(existingMAssumptions.getParent(),
			newMAssumptions.getParent());
		Assert.assertEquals(existingMAssumptions.getProjectionPeriod(),
			newMAssumptions.getProjectionPeriod());
		Assert.assertEquals(existingMAssumptions.getProjectionDetailsSid(),
			newMAssumptions.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingMAssumptions.getNetSalesPrior(),
			newMAssumptions.getNetSalesPrior());
		AssertUtils.assertEquals(existingMAssumptions.getNetSalesProjected(),
			newMAssumptions.getNetSalesProjected());
		Assert.assertEquals(existingMAssumptions.getReasonCodes(),
			newMAssumptions.getReasonCodes());
		Assert.assertEquals(existingMAssumptions.getMAssumptionsSid(),
			newMAssumptions.getMAssumptionsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		MAssumptions existingMAssumptions = _persistence.findByPrimaryKey(newMAssumptions.getPrimaryKey());

		Assert.assertEquals(existingMAssumptions, newMAssumptions);
	}

	@Test(expected = NoSuchMAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MAssumptions> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_ASSUMPTIONS",
			"grossSalesPercentChange", true, "grossSalesPrior", true,
			"projYear", true, "totalDiscountPercentProjected", true, "camId",
			true, "commentary", true, "grossSalesProjected", true,
			"totalDiscountPercentChange", true, "totalDiscountPercentPrior",
			true, "netSalesPercentChange", true, "parent", true,
			"projectionPeriod", true, "projectionDetailsSid", true,
			"netSalesPrior", true, "netSalesProjected", true, "reasonCodes",
			true, "mAssumptionsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		MAssumptions existingMAssumptions = _persistence.fetchByPrimaryKey(newMAssumptions.getPrimaryKey());

		Assert.assertEquals(existingMAssumptions, newMAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MAssumptions missingMAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MAssumptions newMAssumptions1 = addMAssumptions();
		MAssumptions newMAssumptions2 = addMAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMAssumptions1.getPrimaryKey());
		primaryKeys.add(newMAssumptions2.getPrimaryKey());

		Map<Serializable, MAssumptions> mAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mAssumptionses.size());
		Assert.assertEquals(newMAssumptions1,
			mAssumptionses.get(newMAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newMAssumptions2,
			mAssumptionses.get(newMAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MAssumptions> mAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MAssumptions> mAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mAssumptionses.size());
		Assert.assertEquals(newMAssumptions,
			mAssumptionses.get(newMAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MAssumptions> mAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMAssumptions.getPrimaryKey());

		Map<Serializable, MAssumptions> mAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mAssumptionses.size());
		Assert.assertEquals(newMAssumptions,
			mAssumptionses.get(newMAssumptions.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MAssumptionsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MAssumptions>() {
				@Override
				public void performAction(MAssumptions mAssumptions) {
					Assert.assertNotNull(mAssumptions);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mAssumptionsSid",
				newMAssumptions.getMAssumptionsSid()));

		List<MAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MAssumptions existingMAssumptions = result.get(0);

		Assert.assertEquals(existingMAssumptions, newMAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mAssumptionsSid",
				RandomTestUtil.nextInt()));

		List<MAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MAssumptions newMAssumptions = addMAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mAssumptionsSid"));

		Object newMAssumptionsSid = newMAssumptions.getMAssumptionsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("mAssumptionsSid",
				new Object[] { newMAssumptionsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMAssumptionsSid = result.get(0);

		Assert.assertEquals(existingMAssumptionsSid, newMAssumptionsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mAssumptionsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("mAssumptionsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MAssumptions addMAssumptions() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MAssumptions mAssumptions = _persistence.create(pk);

		mAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		mAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		mAssumptions.setProjYear(RandomTestUtil.nextInt());

		mAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		mAssumptions.setCamId(RandomTestUtil.nextInt());

		mAssumptions.setCommentary(RandomTestUtil.randomString());

		mAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		mAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		mAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		mAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		mAssumptions.setParent(RandomTestUtil.randomBoolean());

		mAssumptions.setProjectionPeriod(RandomTestUtil.nextInt());

		mAssumptions.setProjectionDetailsSid(RandomTestUtil.nextInt());

		mAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		mAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		mAssumptions.setReasonCodes(RandomTestUtil.randomString());

		_mAssumptionses.add(_persistence.update(mAssumptions));

		return mAssumptions;
	}

	private List<MAssumptions> _mAssumptionses = new ArrayList<MAssumptions>();
	private MAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}