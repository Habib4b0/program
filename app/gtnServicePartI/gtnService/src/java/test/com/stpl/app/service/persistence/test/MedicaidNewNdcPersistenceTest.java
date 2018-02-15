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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchMedicaidNewNdcException;
import com.stpl.app.model.MedicaidNewNdc;
import com.stpl.app.service.persistence.MedicaidNewNdcPersistence;
import com.stpl.app.service.persistence.MedicaidNewNdcUtil;

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
public class MedicaidNewNdcPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MedicaidNewNdcUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MedicaidNewNdc> iterator = _medicaidNewNdcs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		String pk = RandomTestUtil.randomString();

		MedicaidNewNdc medicaidNewNdc = _persistence.create(pk);

		Assert.assertNotNull(medicaidNewNdc);

		Assert.assertEquals(medicaidNewNdc.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		_persistence.remove(newMedicaidNewNdc);

		MedicaidNewNdc existingMedicaidNewNdc = _persistence.fetchByPrimaryKey(newMedicaidNewNdc.getPrimaryKey());

		Assert.assertNull(existingMedicaidNewNdc);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMedicaidNewNdc();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		String pk = RandomTestUtil.randomString();

		MedicaidNewNdc newMedicaidNewNdc = _persistence.create(pk);

		newMedicaidNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		newMedicaidNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		newMedicaidNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		newMedicaidNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		newMedicaidNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_medicaidNewNdcs.add(_persistence.update(newMedicaidNewNdc));

		MedicaidNewNdc existingMedicaidNewNdc = _persistence.findByPrimaryKey(newMedicaidNewNdc.getPrimaryKey());

		AssertUtils.assertEquals(existingMedicaidNewNdc.getForecastAmp(),
			newMedicaidNewNdc.getForecastAmp());
		AssertUtils.assertEquals(existingMedicaidNewNdc.getForecastBestprice(),
			newMedicaidNewNdc.getForecastBestprice());
		AssertUtils.assertEquals(existingMedicaidNewNdc.getBaseYearCpi(),
			newMedicaidNewNdc.getBaseYearCpi());
		Assert.assertEquals(existingMedicaidNewNdc.getNdc9(),
			newMedicaidNewNdc.getNdc9());
		AssertUtils.assertEquals(existingMedicaidNewNdc.getWacPrice(),
			newMedicaidNewNdc.getWacPrice());
		AssertUtils.assertEquals(existingMedicaidNewNdc.getBaseYearAmp(),
			newMedicaidNewNdc.getBaseYearAmp());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		MedicaidNewNdc existingMedicaidNewNdc = _persistence.findByPrimaryKey(newMedicaidNewNdc.getPrimaryKey());

		Assert.assertEquals(existingMedicaidNewNdc, newMedicaidNewNdc);
	}

	@Test(expected = NoSuchMedicaidNewNdcException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MedicaidNewNdc> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MEDICAID_NEW_NDC",
			"forecastAmp", true, "forecastBestprice", true, "baseYearCpi",
			true, "ndc9", true, "wacPrice", true, "baseYearAmp", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		MedicaidNewNdc existingMedicaidNewNdc = _persistence.fetchByPrimaryKey(newMedicaidNewNdc.getPrimaryKey());

		Assert.assertEquals(existingMedicaidNewNdc, newMedicaidNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		MedicaidNewNdc missingMedicaidNewNdc = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMedicaidNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MedicaidNewNdc newMedicaidNewNdc1 = addMedicaidNewNdc();
		MedicaidNewNdc newMedicaidNewNdc2 = addMedicaidNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidNewNdc1.getPrimaryKey());
		primaryKeys.add(newMedicaidNewNdc2.getPrimaryKey());

		Map<Serializable, MedicaidNewNdc> medicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, medicaidNewNdcs.size());
		Assert.assertEquals(newMedicaidNewNdc1,
			medicaidNewNdcs.get(newMedicaidNewNdc1.getPrimaryKey()));
		Assert.assertEquals(newMedicaidNewNdc2,
			medicaidNewNdcs.get(newMedicaidNewNdc2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		String pk1 = RandomTestUtil.randomString();

		String pk2 = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MedicaidNewNdc> medicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		String pk = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidNewNdc.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MedicaidNewNdc> medicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidNewNdcs.size());
		Assert.assertEquals(newMedicaidNewNdc,
			medicaidNewNdcs.get(newMedicaidNewNdc.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MedicaidNewNdc> medicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidNewNdc.getPrimaryKey());

		Map<Serializable, MedicaidNewNdc> medicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidNewNdcs.size());
		Assert.assertEquals(newMedicaidNewNdc,
			medicaidNewNdcs.get(newMedicaidNewNdc.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ndc9",
				newMedicaidNewNdc.getNdc9()));

		List<MedicaidNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MedicaidNewNdc existingMedicaidNewNdc = result.get(0);

		Assert.assertEquals(existingMedicaidNewNdc, newMedicaidNewNdc);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ndc9",
				RandomTestUtil.randomString()));

		List<MedicaidNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MedicaidNewNdc newMedicaidNewNdc = addMedicaidNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ndc9"));

		Object newNdc9 = newMedicaidNewNdc.getNdc9();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ndc9",
				new Object[] { newNdc9 }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNdc9 = result.get(0);

		Assert.assertEquals(existingNdc9, newNdc9);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ndc9"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ndc9",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MedicaidNewNdc addMedicaidNewNdc() throws Exception {
		String pk = RandomTestUtil.randomString();

		MedicaidNewNdc medicaidNewNdc = _persistence.create(pk);

		medicaidNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		medicaidNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		medicaidNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		medicaidNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		medicaidNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_medicaidNewNdcs.add(_persistence.update(medicaidNewNdc));

		return medicaidNewNdc;
	}

	private List<MedicaidNewNdc> _medicaidNewNdcs = new ArrayList<MedicaidNewNdc>();
	private MedicaidNewNdcPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}