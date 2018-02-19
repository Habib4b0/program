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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchForecastingFormulaException;
import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;
import com.stpl.app.service.persistence.ForecastingFormulaPersistence;
import com.stpl.app.service.persistence.ForecastingFormulaUtil;

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
public class ForecastingFormulaPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ForecastingFormulaUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ForecastingFormula> iterator = _forecastingFormulas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingFormula forecastingFormula = _persistence.create(pk);

		Assert.assertNotNull(forecastingFormula);

		Assert.assertEquals(forecastingFormula.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		_persistence.remove(newForecastingFormula);

		ForecastingFormula existingForecastingFormula = _persistence.fetchByPrimaryKey(newForecastingFormula.getPrimaryKey());

		Assert.assertNull(existingForecastingFormula);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addForecastingFormula();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingFormula newForecastingFormula = _persistence.create(pk);

		newForecastingFormula.setCreatedDate(RandomTestUtil.nextDate());

		newForecastingFormula.setFormulaType(RandomTestUtil.nextInt());

		newForecastingFormula.setFormula(RandomTestUtil.randomString());

		newForecastingFormula.setFormulaNo(RandomTestUtil.randomString());

		newForecastingFormula.setModifiedDate(RandomTestUtil.nextDate());

		newForecastingFormula.setIsActive(RandomTestUtil.randomBoolean());

		newForecastingFormula.setFormulaName(RandomTestUtil.randomString());

		_forecastingFormulas.add(_persistence.update(newForecastingFormula));

		ForecastingFormula existingForecastingFormula = _persistence.findByPrimaryKey(newForecastingFormula.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingFormula.getCreatedDate()),
			Time.getShortTimestamp(newForecastingFormula.getCreatedDate()));
		Assert.assertEquals(existingForecastingFormula.getFormulaType(),
			newForecastingFormula.getFormulaType());
		Assert.assertEquals(existingForecastingFormula.getForecastingFormulaSid(),
			newForecastingFormula.getForecastingFormulaSid());
		Assert.assertEquals(existingForecastingFormula.getFormula(),
			newForecastingFormula.getFormula());
		Assert.assertEquals(existingForecastingFormula.getFormulaNo(),
			newForecastingFormula.getFormulaNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingFormula.getModifiedDate()),
			Time.getShortTimestamp(newForecastingFormula.getModifiedDate()));
		Assert.assertEquals(existingForecastingFormula.getIsActive(),
			newForecastingFormula.getIsActive());
		Assert.assertEquals(existingForecastingFormula.getFormulaName(),
			newForecastingFormula.getFormulaName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		ForecastingFormula existingForecastingFormula = _persistence.findByPrimaryKey(newForecastingFormula.getPrimaryKey());

		Assert.assertEquals(existingForecastingFormula, newForecastingFormula);
	}

	@Test(expected = NoSuchForecastingFormulaException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ForecastingFormula> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FORECASTING_FORMULA",
			"createdDate", true, "formulaType", true, "forecastingFormulaSid",
			true, "formula", true, "formulaNo", true, "modifiedDate", true,
			"isActive", true, "formulaName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		ForecastingFormula existingForecastingFormula = _persistence.fetchByPrimaryKey(newForecastingFormula.getPrimaryKey());

		Assert.assertEquals(existingForecastingFormula, newForecastingFormula);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingFormula missingForecastingFormula = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingForecastingFormula);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ForecastingFormula newForecastingFormula1 = addForecastingFormula();
		ForecastingFormula newForecastingFormula2 = addForecastingFormula();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingFormula1.getPrimaryKey());
		primaryKeys.add(newForecastingFormula2.getPrimaryKey());

		Map<Serializable, ForecastingFormula> forecastingFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, forecastingFormulas.size());
		Assert.assertEquals(newForecastingFormula1,
			forecastingFormulas.get(newForecastingFormula1.getPrimaryKey()));
		Assert.assertEquals(newForecastingFormula2,
			forecastingFormulas.get(newForecastingFormula2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ForecastingFormula> forecastingFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingFormulas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingFormula.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ForecastingFormula> forecastingFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingFormulas.size());
		Assert.assertEquals(newForecastingFormula,
			forecastingFormulas.get(newForecastingFormula.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ForecastingFormula> forecastingFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingFormulas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingFormula.getPrimaryKey());

		Map<Serializable, ForecastingFormula> forecastingFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingFormulas.size());
		Assert.assertEquals(newForecastingFormula,
			forecastingFormulas.get(newForecastingFormula.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ForecastingFormulaLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ForecastingFormula>() {
				@Override
				public void performAction(ForecastingFormula forecastingFormula) {
					Assert.assertNotNull(forecastingFormula);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastingFormulaSid",
				newForecastingFormula.getForecastingFormulaSid()));

		List<ForecastingFormula> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ForecastingFormula existingForecastingFormula = result.get(0);

		Assert.assertEquals(existingForecastingFormula, newForecastingFormula);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastingFormulaSid",
				RandomTestUtil.nextInt()));

		List<ForecastingFormula> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ForecastingFormula newForecastingFormula = addForecastingFormula();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastingFormulaSid"));

		Object newForecastingFormulaSid = newForecastingFormula.getForecastingFormulaSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastingFormulaSid",
				new Object[] { newForecastingFormulaSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingForecastingFormulaSid = result.get(0);

		Assert.assertEquals(existingForecastingFormulaSid,
			newForecastingFormulaSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastingFormulaSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastingFormulaSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ForecastingFormula addForecastingFormula()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingFormula forecastingFormula = _persistence.create(pk);

		forecastingFormula.setCreatedDate(RandomTestUtil.nextDate());

		forecastingFormula.setFormulaType(RandomTestUtil.nextInt());

		forecastingFormula.setFormula(RandomTestUtil.randomString());

		forecastingFormula.setFormulaNo(RandomTestUtil.randomString());

		forecastingFormula.setModifiedDate(RandomTestUtil.nextDate());

		forecastingFormula.setIsActive(RandomTestUtil.randomBoolean());

		forecastingFormula.setFormulaName(RandomTestUtil.randomString());

		_forecastingFormulas.add(_persistence.update(forecastingFormula));

		return forecastingFormula;
	}

	private List<ForecastingFormula> _forecastingFormulas = new ArrayList<ForecastingFormula>();
	private ForecastingFormulaPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}