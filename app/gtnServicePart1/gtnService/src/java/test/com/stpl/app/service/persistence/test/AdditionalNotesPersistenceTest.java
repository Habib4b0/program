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

import com.stpl.app.exception.NoSuchAdditionalNotesException;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.persistence.AdditionalNotesPersistence;
import com.stpl.app.service.persistence.AdditionalNotesUtil;

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
public class AdditionalNotesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = AdditionalNotesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AdditionalNotes> iterator = _additionalNoteses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdditionalNotes additionalNotes = _persistence.create(pk);

		Assert.assertNotNull(additionalNotes);

		Assert.assertEquals(additionalNotes.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		_persistence.remove(newAdditionalNotes);

		AdditionalNotes existingAdditionalNotes = _persistence.fetchByPrimaryKey(newAdditionalNotes.getPrimaryKey());

		Assert.assertNull(existingAdditionalNotes);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAdditionalNotes();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdditionalNotes newAdditionalNotes = _persistence.create(pk);

		newAdditionalNotes.setCreatedDate(RandomTestUtil.nextDate());

		newAdditionalNotes.setCreatedBy(RandomTestUtil.randomString());

		newAdditionalNotes.setForecastType(RandomTestUtil.randomString());

		newAdditionalNotes.setProjectionId(RandomTestUtil.nextInt());

		newAdditionalNotes.setNotes(RandomTestUtil.randomString());

		_additionalNoteses.add(_persistence.update(newAdditionalNotes));

		AdditionalNotes existingAdditionalNotes = _persistence.findByPrimaryKey(newAdditionalNotes.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingAdditionalNotes.getCreatedDate()),
			Time.getShortTimestamp(newAdditionalNotes.getCreatedDate()));
		Assert.assertEquals(existingAdditionalNotes.getCreatedBy(),
			newAdditionalNotes.getCreatedBy());
		Assert.assertEquals(existingAdditionalNotes.getForecastType(),
			newAdditionalNotes.getForecastType());
		Assert.assertEquals(existingAdditionalNotes.getAdditionalNotesId(),
			newAdditionalNotes.getAdditionalNotesId());
		Assert.assertEquals(existingAdditionalNotes.getProjectionId(),
			newAdditionalNotes.getProjectionId());
		Assert.assertEquals(existingAdditionalNotes.getNotes(),
			newAdditionalNotes.getNotes());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		AdditionalNotes existingAdditionalNotes = _persistence.findByPrimaryKey(newAdditionalNotes.getPrimaryKey());

		Assert.assertEquals(existingAdditionalNotes, newAdditionalNotes);
	}

	@Test(expected = NoSuchAdditionalNotesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AdditionalNotes> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ADDITIONAL_NOTES",
			"createdDate", true, "createdBy", true, "forecastType", true,
			"additionalNotesId", true, "projectionId", true, "notes", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		AdditionalNotes existingAdditionalNotes = _persistence.fetchByPrimaryKey(newAdditionalNotes.getPrimaryKey());

		Assert.assertEquals(existingAdditionalNotes, newAdditionalNotes);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdditionalNotes missingAdditionalNotes = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAdditionalNotes);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AdditionalNotes newAdditionalNotes1 = addAdditionalNotes();
		AdditionalNotes newAdditionalNotes2 = addAdditionalNotes();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdditionalNotes1.getPrimaryKey());
		primaryKeys.add(newAdditionalNotes2.getPrimaryKey());

		Map<Serializable, AdditionalNotes> additionalNoteses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, additionalNoteses.size());
		Assert.assertEquals(newAdditionalNotes1,
			additionalNoteses.get(newAdditionalNotes1.getPrimaryKey()));
		Assert.assertEquals(newAdditionalNotes2,
			additionalNoteses.get(newAdditionalNotes2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AdditionalNotes> additionalNoteses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(additionalNoteses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdditionalNotes.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AdditionalNotes> additionalNoteses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, additionalNoteses.size());
		Assert.assertEquals(newAdditionalNotes,
			additionalNoteses.get(newAdditionalNotes.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AdditionalNotes> additionalNoteses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(additionalNoteses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdditionalNotes.getPrimaryKey());

		Map<Serializable, AdditionalNotes> additionalNoteses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, additionalNoteses.size());
		Assert.assertEquals(newAdditionalNotes,
			additionalNoteses.get(newAdditionalNotes.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AdditionalNotesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AdditionalNotes>() {
				@Override
				public void performAction(AdditionalNotes additionalNotes) {
					Assert.assertNotNull(additionalNotes);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("additionalNotesId",
				newAdditionalNotes.getAdditionalNotesId()));

		List<AdditionalNotes> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AdditionalNotes existingAdditionalNotes = result.get(0);

		Assert.assertEquals(existingAdditionalNotes, newAdditionalNotes);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("additionalNotesId",
				RandomTestUtil.nextInt()));

		List<AdditionalNotes> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AdditionalNotes newAdditionalNotes = addAdditionalNotes();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"additionalNotesId"));

		Object newAdditionalNotesId = newAdditionalNotes.getAdditionalNotesId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("additionalNotesId",
				new Object[] { newAdditionalNotesId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAdditionalNotesId = result.get(0);

		Assert.assertEquals(existingAdditionalNotesId, newAdditionalNotesId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"additionalNotesId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("additionalNotesId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AdditionalNotes addAdditionalNotes() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdditionalNotes additionalNotes = _persistence.create(pk);

		additionalNotes.setCreatedDate(RandomTestUtil.nextDate());

		additionalNotes.setCreatedBy(RandomTestUtil.randomString());

		additionalNotes.setForecastType(RandomTestUtil.randomString());

		additionalNotes.setProjectionId(RandomTestUtil.nextInt());

		additionalNotes.setNotes(RandomTestUtil.randomString());

		_additionalNoteses.add(_persistence.update(additionalNotes));

		return additionalNotes;
	}

	private List<AdditionalNotes> _additionalNoteses = new ArrayList<AdditionalNotes>();
	private AdditionalNotesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}