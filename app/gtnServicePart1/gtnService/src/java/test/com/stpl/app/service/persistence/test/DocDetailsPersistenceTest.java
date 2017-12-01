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

import com.stpl.app.exception.NoSuchDocDetailsException;
import com.stpl.app.model.DocDetails;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.DocDetailsPersistence;
import com.stpl.app.service.persistence.DocDetailsUtil;

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
public class DocDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DocDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DocDetails> iterator = _docDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DocDetails docDetails = _persistence.create(pk);

		Assert.assertNotNull(docDetails);

		Assert.assertEquals(docDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DocDetails newDocDetails = addDocDetails();

		_persistence.remove(newDocDetails);

		DocDetails existingDocDetails = _persistence.fetchByPrimaryKey(newDocDetails.getPrimaryKey());

		Assert.assertNull(existingDocDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDocDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DocDetails newDocDetails = _persistence.create(pk);

		newDocDetails.setFileName(RandomTestUtil.randomString());

		newDocDetails.setFileType(RandomTestUtil.randomString());

		newDocDetails.setUploadedBy(RandomTestUtil.randomString());

		newDocDetails.setForecastType(RandomTestUtil.randomString());

		newDocDetails.setProjectionId(RandomTestUtil.nextInt());

		newDocDetails.setUploadedDate(RandomTestUtil.nextDate());

		newDocDetails.setFileSize(RandomTestUtil.randomString());

		_docDetailses.add(_persistence.update(newDocDetails));

		DocDetails existingDocDetails = _persistence.findByPrimaryKey(newDocDetails.getPrimaryKey());

		Assert.assertEquals(existingDocDetails.getFileName(),
			newDocDetails.getFileName());
		Assert.assertEquals(existingDocDetails.getFileType(),
			newDocDetails.getFileType());
		Assert.assertEquals(existingDocDetails.getUploadedBy(),
			newDocDetails.getUploadedBy());
		Assert.assertEquals(existingDocDetails.getForecastType(),
			newDocDetails.getForecastType());
		Assert.assertEquals(existingDocDetails.getProjectionId(),
			newDocDetails.getProjectionId());
		Assert.assertEquals(existingDocDetails.getDocDetailsId(),
			newDocDetails.getDocDetailsId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDocDetails.getUploadedDate()),
			Time.getShortTimestamp(newDocDetails.getUploadedDate()));
		Assert.assertEquals(existingDocDetails.getFileSize(),
			newDocDetails.getFileSize());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DocDetails newDocDetails = addDocDetails();

		DocDetails existingDocDetails = _persistence.findByPrimaryKey(newDocDetails.getPrimaryKey());

		Assert.assertEquals(existingDocDetails, newDocDetails);
	}

	@Test(expected = NoSuchDocDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DocDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DOC_DETAILS", "fileName",
			true, "fileType", true, "uploadedBy", true, "forecastType", true,
			"projectionId", true, "docDetailsId", true, "uploadedDate", true,
			"fileSize", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DocDetails newDocDetails = addDocDetails();

		DocDetails existingDocDetails = _persistence.fetchByPrimaryKey(newDocDetails.getPrimaryKey());

		Assert.assertEquals(existingDocDetails, newDocDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DocDetails missingDocDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDocDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DocDetails newDocDetails1 = addDocDetails();
		DocDetails newDocDetails2 = addDocDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocDetails1.getPrimaryKey());
		primaryKeys.add(newDocDetails2.getPrimaryKey());

		Map<Serializable, DocDetails> docDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, docDetailses.size());
		Assert.assertEquals(newDocDetails1,
			docDetailses.get(newDocDetails1.getPrimaryKey()));
		Assert.assertEquals(newDocDetails2,
			docDetailses.get(newDocDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DocDetails> docDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(docDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DocDetails newDocDetails = addDocDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DocDetails> docDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, docDetailses.size());
		Assert.assertEquals(newDocDetails,
			docDetailses.get(newDocDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DocDetails> docDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(docDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DocDetails newDocDetails = addDocDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocDetails.getPrimaryKey());

		Map<Serializable, DocDetails> docDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, docDetailses.size());
		Assert.assertEquals(newDocDetails,
			docDetailses.get(newDocDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DocDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DocDetails>() {
				@Override
				public void performAction(DocDetails docDetails) {
					Assert.assertNotNull(docDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DocDetails newDocDetails = addDocDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("docDetailsId",
				newDocDetails.getDocDetailsId()));

		List<DocDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DocDetails existingDocDetails = result.get(0);

		Assert.assertEquals(existingDocDetails, newDocDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("docDetailsId",
				RandomTestUtil.nextInt()));

		List<DocDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DocDetails newDocDetails = addDocDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"docDetailsId"));

		Object newDocDetailsId = newDocDetails.getDocDetailsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("docDetailsId",
				new Object[] { newDocDetailsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDocDetailsId = result.get(0);

		Assert.assertEquals(existingDocDetailsId, newDocDetailsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"docDetailsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("docDetailsId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DocDetails addDocDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DocDetails docDetails = _persistence.create(pk);

		docDetails.setFileName(RandomTestUtil.randomString());

		docDetails.setFileType(RandomTestUtil.randomString());

		docDetails.setUploadedBy(RandomTestUtil.randomString());

		docDetails.setForecastType(RandomTestUtil.randomString());

		docDetails.setProjectionId(RandomTestUtil.nextInt());

		docDetails.setUploadedDate(RandomTestUtil.nextDate());

		docDetails.setFileSize(RandomTestUtil.randomString());

		_docDetailses.add(_persistence.update(docDetails));

		return docDetails;
	}

	private List<DocDetails> _docDetailses = new ArrayList<DocDetails>();
	private DocDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}