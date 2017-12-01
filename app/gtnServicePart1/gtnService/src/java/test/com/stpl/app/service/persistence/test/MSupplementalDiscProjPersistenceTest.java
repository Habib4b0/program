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

import com.stpl.app.exception.NoSuchMSupplementalDiscProjException;
import com.stpl.app.model.MSupplementalDiscProj;
import com.stpl.app.service.MSupplementalDiscProjLocalServiceUtil;
import com.stpl.app.service.persistence.MSupplementalDiscProjPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscProjUtil;

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
public class MSupplementalDiscProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MSupplementalDiscProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MSupplementalDiscProj> iterator = _mSupplementalDiscProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscProj mSupplementalDiscProj = _persistence.create(pk);

		Assert.assertNotNull(mSupplementalDiscProj);

		Assert.assertEquals(mSupplementalDiscProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		_persistence.remove(newMSupplementalDiscProj);

		MSupplementalDiscProj existingMSupplementalDiscProj = _persistence.fetchByPrimaryKey(newMSupplementalDiscProj.getPrimaryKey());

		Assert.assertNull(existingMSupplementalDiscProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMSupplementalDiscProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscProj newMSupplementalDiscProj = _persistence.create(pk);

		newMSupplementalDiscProj.setMethodology(RandomTestUtil.randomString());

		newMSupplementalDiscProj.setProjectionRate(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setParity(RandomTestUtil.randomBoolean());

		newMSupplementalDiscProj.setPeriodSid(RandomTestUtil.nextInt());

		newMSupplementalDiscProj.setDiscountRate1(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setParityReference(RandomTestUtil.randomString());

		newMSupplementalDiscProj.setDiscountRate2(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setParityDiscount(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setProjectionSales(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setContractPrice(RandomTestUtil.nextDouble());

		newMSupplementalDiscProj.setAccess(RandomTestUtil.randomString());

		_mSupplementalDiscProjs.add(_persistence.update(
				newMSupplementalDiscProj));

		MSupplementalDiscProj existingMSupplementalDiscProj = _persistence.findByPrimaryKey(newMSupplementalDiscProj.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscProj.getMethodology(),
			newMSupplementalDiscProj.getMethodology());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getProjectionRate(),
			newMSupplementalDiscProj.getProjectionRate());
		Assert.assertEquals(existingMSupplementalDiscProj.getParity(),
			newMSupplementalDiscProj.getParity());
		Assert.assertEquals(existingMSupplementalDiscProj.getPeriodSid(),
			newMSupplementalDiscProj.getPeriodSid());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getDiscountRate1(),
			newMSupplementalDiscProj.getDiscountRate1());
		Assert.assertEquals(existingMSupplementalDiscProj.getParityReference(),
			newMSupplementalDiscProj.getParityReference());
		Assert.assertEquals(existingMSupplementalDiscProj.getProjectionDetailsSid(),
			newMSupplementalDiscProj.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getDiscountRate2(),
			newMSupplementalDiscProj.getDiscountRate2());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getParityDiscount(),
			newMSupplementalDiscProj.getParityDiscount());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getProjectionSales(),
			newMSupplementalDiscProj.getProjectionSales());
		AssertUtils.assertEquals(existingMSupplementalDiscProj.getContractPrice(),
			newMSupplementalDiscProj.getContractPrice());
		Assert.assertEquals(existingMSupplementalDiscProj.getAccess(),
			newMSupplementalDiscProj.getAccess());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		MSupplementalDiscProj existingMSupplementalDiscProj = _persistence.findByPrimaryKey(newMSupplementalDiscProj.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscProj,
			newMSupplementalDiscProj);
	}

	@Test(expected = NoSuchMSupplementalDiscProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MSupplementalDiscProj> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_SUPPLEMENTAL_DISC_PROJ",
			"methodology", true, "projectionRate", true, "parity", true,
			"periodSid", true, "discountRate1", true, "parityReference", true,
			"projectionDetailsSid", true, "discountRate2", true,
			"parityDiscount", true, "projectionSales", true, "contractPrice",
			true, "access", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		MSupplementalDiscProj existingMSupplementalDiscProj = _persistence.fetchByPrimaryKey(newMSupplementalDiscProj.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscProj,
			newMSupplementalDiscProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscProj missingMSupplementalDiscProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMSupplementalDiscProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj1 = addMSupplementalDiscProj();
		MSupplementalDiscProj newMSupplementalDiscProj2 = addMSupplementalDiscProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscProj1.getPrimaryKey());
		primaryKeys.add(newMSupplementalDiscProj2.getPrimaryKey());

		Map<Serializable, MSupplementalDiscProj> mSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mSupplementalDiscProjs.size());
		Assert.assertEquals(newMSupplementalDiscProj1,
			mSupplementalDiscProjs.get(
				newMSupplementalDiscProj1.getPrimaryKey()));
		Assert.assertEquals(newMSupplementalDiscProj2,
			mSupplementalDiscProjs.get(
				newMSupplementalDiscProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MSupplementalDiscProj> mSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MSupplementalDiscProj> mSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscProjs.size());
		Assert.assertEquals(newMSupplementalDiscProj,
			mSupplementalDiscProjs.get(newMSupplementalDiscProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MSupplementalDiscProj> mSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscProj.getPrimaryKey());

		Map<Serializable, MSupplementalDiscProj> mSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscProjs.size());
		Assert.assertEquals(newMSupplementalDiscProj,
			mSupplementalDiscProjs.get(newMSupplementalDiscProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MSupplementalDiscProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MSupplementalDiscProj>() {
				@Override
				public void performAction(
					MSupplementalDiscProj mSupplementalDiscProj) {
					Assert.assertNotNull(mSupplementalDiscProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newMSupplementalDiscProj.getProjectionDetailsSid()));

		List<MSupplementalDiscProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MSupplementalDiscProj existingMSupplementalDiscProj = result.get(0);

		Assert.assertEquals(existingMSupplementalDiscProj,
			newMSupplementalDiscProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<MSupplementalDiscProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MSupplementalDiscProj newMSupplementalDiscProj = addMSupplementalDiscProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newMSupplementalDiscProj.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MSupplementalDiscProj addMSupplementalDiscProj()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSupplementalDiscProj mSupplementalDiscProj = _persistence.create(pk);

		mSupplementalDiscProj.setMethodology(RandomTestUtil.randomString());

		mSupplementalDiscProj.setProjectionRate(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setParity(RandomTestUtil.randomBoolean());

		mSupplementalDiscProj.setPeriodSid(RandomTestUtil.nextInt());

		mSupplementalDiscProj.setDiscountRate1(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setParityReference(RandomTestUtil.randomString());

		mSupplementalDiscProj.setDiscountRate2(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setParityDiscount(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setProjectionSales(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setContractPrice(RandomTestUtil.nextDouble());

		mSupplementalDiscProj.setAccess(RandomTestUtil.randomString());

		_mSupplementalDiscProjs.add(_persistence.update(mSupplementalDiscProj));

		return mSupplementalDiscProj;
	}

	private List<MSupplementalDiscProj> _mSupplementalDiscProjs = new ArrayList<MSupplementalDiscProj>();
	private MSupplementalDiscProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}