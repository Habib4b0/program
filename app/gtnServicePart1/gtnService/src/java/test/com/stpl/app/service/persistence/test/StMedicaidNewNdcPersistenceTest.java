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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStMedicaidNewNdcException;
import com.stpl.app.model.StMedicaidNewNdc;
import com.stpl.app.service.persistence.StMedicaidNewNdcPK;
import com.stpl.app.service.persistence.StMedicaidNewNdcPersistence;
import com.stpl.app.service.persistence.StMedicaidNewNdcUtil;

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
public class StMedicaidNewNdcPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StMedicaidNewNdcUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StMedicaidNewNdc> iterator = _stMedicaidNewNdcs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMedicaidNewNdc stMedicaidNewNdc = _persistence.create(pk);

		Assert.assertNotNull(stMedicaidNewNdc);

		Assert.assertEquals(stMedicaidNewNdc.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		_persistence.remove(newStMedicaidNewNdc);

		StMedicaidNewNdc existingStMedicaidNewNdc = _persistence.fetchByPrimaryKey(newStMedicaidNewNdc.getPrimaryKey());

		Assert.assertNull(existingStMedicaidNewNdc);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStMedicaidNewNdc();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMedicaidNewNdc newStMedicaidNewNdc = _persistence.create(pk);

		newStMedicaidNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		newStMedicaidNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		newStMedicaidNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		newStMedicaidNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		newStMedicaidNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		newStMedicaidNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_stMedicaidNewNdcs.add(_persistence.update(newStMedicaidNewNdc));

		StMedicaidNewNdc existingStMedicaidNewNdc = _persistence.findByPrimaryKey(newStMedicaidNewNdc.getPrimaryKey());

		AssertUtils.assertEquals(existingStMedicaidNewNdc.getForecastAmp(),
			newStMedicaidNewNdc.getForecastAmp());
		AssertUtils.assertEquals(existingStMedicaidNewNdc.getForecastBestprice(),
			newStMedicaidNewNdc.getForecastBestprice());
		AssertUtils.assertEquals(existingStMedicaidNewNdc.getBaseYearCpi(),
			newStMedicaidNewNdc.getBaseYearCpi());
		Assert.assertEquals(existingStMedicaidNewNdc.getNdc9(),
			newStMedicaidNewNdc.getNdc9());
		Assert.assertEquals(existingStMedicaidNewNdc.getUserId(),
			newStMedicaidNewNdc.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMedicaidNewNdc.getLastModifiedDate()),
			Time.getShortTimestamp(newStMedicaidNewNdc.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStMedicaidNewNdc.getWacPrice(),
			newStMedicaidNewNdc.getWacPrice());
		AssertUtils.assertEquals(existingStMedicaidNewNdc.getBaseYearAmp(),
			newStMedicaidNewNdc.getBaseYearAmp());
		Assert.assertEquals(existingStMedicaidNewNdc.getSessionId(),
			newStMedicaidNewNdc.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		StMedicaidNewNdc existingStMedicaidNewNdc = _persistence.findByPrimaryKey(newStMedicaidNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStMedicaidNewNdc, newStMedicaidNewNdc);
	}

	@Test(expected = NoSuchStMedicaidNewNdcException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		StMedicaidNewNdc existingStMedicaidNewNdc = _persistence.fetchByPrimaryKey(newStMedicaidNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStMedicaidNewNdc, newStMedicaidNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMedicaidNewNdc missingStMedicaidNewNdc = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStMedicaidNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc1 = addStMedicaidNewNdc();
		StMedicaidNewNdc newStMedicaidNewNdc2 = addStMedicaidNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMedicaidNewNdc1.getPrimaryKey());
		primaryKeys.add(newStMedicaidNewNdc2.getPrimaryKey());

		Map<Serializable, StMedicaidNewNdc> stMedicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stMedicaidNewNdcs.size());
		Assert.assertEquals(newStMedicaidNewNdc1,
			stMedicaidNewNdcs.get(newStMedicaidNewNdc1.getPrimaryKey()));
		Assert.assertEquals(newStMedicaidNewNdc2,
			stMedicaidNewNdcs.get(newStMedicaidNewNdc2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StMedicaidNewNdcPK pk1 = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMedicaidNewNdcPK pk2 = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StMedicaidNewNdc> stMedicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMedicaidNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMedicaidNewNdc.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StMedicaidNewNdc> stMedicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMedicaidNewNdcs.size());
		Assert.assertEquals(newStMedicaidNewNdc,
			stMedicaidNewNdcs.get(newStMedicaidNewNdc.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StMedicaidNewNdc> stMedicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMedicaidNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMedicaidNewNdc.getPrimaryKey());

		Map<Serializable, StMedicaidNewNdc> stMedicaidNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMedicaidNewNdcs.size());
		Assert.assertEquals(newStMedicaidNewNdc,
			stMedicaidNewNdcs.get(newStMedicaidNewNdc.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.ndc9",
				newStMedicaidNewNdc.getNdc9()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStMedicaidNewNdc.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStMedicaidNewNdc.getSessionId()));

		List<StMedicaidNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StMedicaidNewNdc existingStMedicaidNewNdc = result.get(0);

		Assert.assertEquals(existingStMedicaidNewNdc, newStMedicaidNewNdc);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.ndc9",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StMedicaidNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StMedicaidNewNdc newStMedicaidNewNdc = addStMedicaidNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.ndc9"));

		Object newNdc9 = newStMedicaidNewNdc.getNdc9();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.ndc9",
				new Object[] { newNdc9 }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNdc9 = result.get(0);

		Assert.assertEquals(existingNdc9, newNdc9);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMedicaidNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.ndc9"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.ndc9",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StMedicaidNewNdc addStMedicaidNewNdc() throws Exception {
		StMedicaidNewNdcPK pk = new StMedicaidNewNdcPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMedicaidNewNdc stMedicaidNewNdc = _persistence.create(pk);

		stMedicaidNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		stMedicaidNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		stMedicaidNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		stMedicaidNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		stMedicaidNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		stMedicaidNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_stMedicaidNewNdcs.add(_persistence.update(stMedicaidNewNdc));

		return stMedicaidNewNdc;
	}

	private List<StMedicaidNewNdc> _stMedicaidNewNdcs = new ArrayList<StMedicaidNewNdc>();
	private StMedicaidNewNdcPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}