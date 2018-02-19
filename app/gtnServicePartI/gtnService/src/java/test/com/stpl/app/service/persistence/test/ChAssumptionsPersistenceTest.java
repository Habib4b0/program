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

import com.stpl.app.exception.NoSuchChAssumptionsException;
import com.stpl.app.model.ChAssumptions;
import com.stpl.app.service.ChAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.ChAssumptionsPersistence;
import com.stpl.app.service.persistence.ChAssumptionsUtil;

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
public class ChAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChAssumptions> iterator = _chAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChAssumptions chAssumptions = _persistence.create(pk);

		Assert.assertNotNull(chAssumptions);

		Assert.assertEquals(chAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		_persistence.remove(newChAssumptions);

		ChAssumptions existingChAssumptions = _persistence.fetchByPrimaryKey(newChAssumptions.getPrimaryKey());

		Assert.assertNull(existingChAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChAssumptions newChAssumptions = _persistence.create(pk);

		newChAssumptions.setParent(RandomTestUtil.randomBoolean());

		newChAssumptions.setProjectionDetailsSid(RandomTestUtil.nextInt());

		newChAssumptions.setCommentary(RandomTestUtil.randomString());

		newChAssumptions.setQuarter(RandomTestUtil.nextInt());

		newChAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		newChAssumptions.setReasonCodes(RandomTestUtil.randomString());

		newChAssumptions.setYear(RandomTestUtil.nextInt());

		newChAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		newChAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		newChAssumptions.setTotalDiscountChange(RandomTestUtil.nextDouble());

		newChAssumptions.setTotalDiscountProjected(RandomTestUtil.nextDouble());

		newChAssumptions.setCamId(RandomTestUtil.nextInt());

		newChAssumptions.setGrossTradeSales(RandomTestUtil.nextDouble());

		newChAssumptions.setTotalDiscountPrior(RandomTestUtil.nextDouble());

		_chAssumptionses.add(_persistence.update(newChAssumptions));

		ChAssumptions existingChAssumptions = _persistence.findByPrimaryKey(newChAssumptions.getPrimaryKey());

		Assert.assertEquals(existingChAssumptions.getParent(),
			newChAssumptions.getParent());
		Assert.assertEquals(existingChAssumptions.getProjectionDetailsSid(),
			newChAssumptions.getProjectionDetailsSid());
		Assert.assertEquals(existingChAssumptions.getCommentary(),
			newChAssumptions.getCommentary());
		Assert.assertEquals(existingChAssumptions.getQuarter(),
			newChAssumptions.getQuarter());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountPercentChange(),
			newChAssumptions.getTotalDiscountPercentChange());
		Assert.assertEquals(existingChAssumptions.getReasonCodes(),
			newChAssumptions.getReasonCodes());
		Assert.assertEquals(existingChAssumptions.getYear(),
			newChAssumptions.getYear());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountPercentProjected(),
			newChAssumptions.getTotalDiscountPercentProjected());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountPercentPrior(),
			newChAssumptions.getTotalDiscountPercentPrior());
		Assert.assertEquals(existingChAssumptions.getChAssumptionsSid(),
			newChAssumptions.getChAssumptionsSid());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountChange(),
			newChAssumptions.getTotalDiscountChange());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountProjected(),
			newChAssumptions.getTotalDiscountProjected());
		Assert.assertEquals(existingChAssumptions.getCamId(),
			newChAssumptions.getCamId());
		AssertUtils.assertEquals(existingChAssumptions.getGrossTradeSales(),
			newChAssumptions.getGrossTradeSales());
		AssertUtils.assertEquals(existingChAssumptions.getTotalDiscountPrior(),
			newChAssumptions.getTotalDiscountPrior());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		ChAssumptions existingChAssumptions = _persistence.findByPrimaryKey(newChAssumptions.getPrimaryKey());

		Assert.assertEquals(existingChAssumptions, newChAssumptions);
	}

	@Test(expected = NoSuchChAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ChAssumptions> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CH_ASSUMPTIONS", "parent",
			true, "projectionDetailsSid", true, "commentary", true, "quarter",
			true, "totalDiscountPercentChange", true, "reasonCodes", true,
			"year", true, "totalDiscountPercentProjected", true,
			"totalDiscountPercentPrior", true, "chAssumptionsSid", true,
			"totalDiscountChange", true, "totalDiscountProjected", true,
			"camId", true, "grossTradeSales", true, "totalDiscountPrior", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		ChAssumptions existingChAssumptions = _persistence.fetchByPrimaryKey(newChAssumptions.getPrimaryKey());

		Assert.assertEquals(existingChAssumptions, newChAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChAssumptions missingChAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChAssumptions newChAssumptions1 = addChAssumptions();
		ChAssumptions newChAssumptions2 = addChAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChAssumptions1.getPrimaryKey());
		primaryKeys.add(newChAssumptions2.getPrimaryKey());

		Map<Serializable, ChAssumptions> chAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chAssumptionses.size());
		Assert.assertEquals(newChAssumptions1,
			chAssumptionses.get(newChAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newChAssumptions2,
			chAssumptionses.get(newChAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChAssumptions> chAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChAssumptions> chAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chAssumptionses.size());
		Assert.assertEquals(newChAssumptions,
			chAssumptionses.get(newChAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChAssumptions> chAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChAssumptions.getPrimaryKey());

		Map<Serializable, ChAssumptions> chAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chAssumptionses.size());
		Assert.assertEquals(newChAssumptions,
			chAssumptionses.get(newChAssumptions.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChAssumptionsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChAssumptions>() {
				@Override
				public void performAction(ChAssumptions chAssumptions) {
					Assert.assertNotNull(chAssumptions);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("chAssumptionsSid",
				newChAssumptions.getChAssumptionsSid()));

		List<ChAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChAssumptions existingChAssumptions = result.get(0);

		Assert.assertEquals(existingChAssumptions, newChAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("chAssumptionsSid",
				RandomTestUtil.nextInt()));

		List<ChAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChAssumptions newChAssumptions = addChAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"chAssumptionsSid"));

		Object newChAssumptionsSid = newChAssumptions.getChAssumptionsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("chAssumptionsSid",
				new Object[] { newChAssumptionsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingChAssumptionsSid = result.get(0);

		Assert.assertEquals(existingChAssumptionsSid, newChAssumptionsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"chAssumptionsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("chAssumptionsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChAssumptions addChAssumptions() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChAssumptions chAssumptions = _persistence.create(pk);

		chAssumptions.setParent(RandomTestUtil.randomBoolean());

		chAssumptions.setProjectionDetailsSid(RandomTestUtil.nextInt());

		chAssumptions.setCommentary(RandomTestUtil.randomString());

		chAssumptions.setQuarter(RandomTestUtil.nextInt());

		chAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		chAssumptions.setReasonCodes(RandomTestUtil.randomString());

		chAssumptions.setYear(RandomTestUtil.nextInt());

		chAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		chAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		chAssumptions.setTotalDiscountChange(RandomTestUtil.nextDouble());

		chAssumptions.setTotalDiscountProjected(RandomTestUtil.nextDouble());

		chAssumptions.setCamId(RandomTestUtil.nextInt());

		chAssumptions.setGrossTradeSales(RandomTestUtil.nextDouble());

		chAssumptions.setTotalDiscountPrior(RandomTestUtil.nextDouble());

		_chAssumptionses.add(_persistence.update(chAssumptions));

		return chAssumptions;
	}

	private List<ChAssumptions> _chAssumptionses = new ArrayList<ChAssumptions>();
	private ChAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}