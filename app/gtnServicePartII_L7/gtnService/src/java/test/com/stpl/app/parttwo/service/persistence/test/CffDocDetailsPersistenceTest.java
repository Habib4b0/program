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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchCffDocDetailsException;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffDocDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffDocDetailsUtil;

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
public class CffDocDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffDocDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffDocDetails> iterator = _cffDocDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDocDetails cffDocDetails = _persistence.create(pk);

		Assert.assertNotNull(cffDocDetails);

		Assert.assertEquals(cffDocDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		_persistence.remove(newCffDocDetails);

		CffDocDetails existingCffDocDetails = _persistence.fetchByPrimaryKey(newCffDocDetails.getPrimaryKey());

		Assert.assertNull(existingCffDocDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffDocDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDocDetails newCffDocDetails = _persistence.create(pk);

		newCffDocDetails.setFileName(RandomTestUtil.randomString());

		newCffDocDetails.setUploadDate(RandomTestUtil.nextDate());

		newCffDocDetails.setFileType(RandomTestUtil.randomString());

		newCffDocDetails.setUploadBy(RandomTestUtil.nextInt());

		newCffDocDetails.setCffMasterSid(RandomTestUtil.nextInt());

		newCffDocDetails.setFileSize(RandomTestUtil.randomString());

		_cffDocDetailses.add(_persistence.update(newCffDocDetails));

		CffDocDetails existingCffDocDetails = _persistence.findByPrimaryKey(newCffDocDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDocDetails.getFileName(),
			newCffDocDetails.getFileName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffDocDetails.getUploadDate()),
			Time.getShortTimestamp(newCffDocDetails.getUploadDate()));
		Assert.assertEquals(existingCffDocDetails.getFileType(),
			newCffDocDetails.getFileType());
		Assert.assertEquals(existingCffDocDetails.getUploadBy(),
			newCffDocDetails.getUploadBy());
		Assert.assertEquals(existingCffDocDetails.getCffMasterSid(),
			newCffDocDetails.getCffMasterSid());
		Assert.assertEquals(existingCffDocDetails.getCffDocDetailsSid(),
			newCffDocDetails.getCffDocDetailsSid());
		Assert.assertEquals(existingCffDocDetails.getFileSize(),
			newCffDocDetails.getFileSize());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		CffDocDetails existingCffDocDetails = _persistence.findByPrimaryKey(newCffDocDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDocDetails, newCffDocDetails);
	}

	@Test(expected = NoSuchCffDocDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffDocDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_DOC_DETAILS",
			"fileName", true, "uploadDate", true, "fileType", true, "uploadBy",
			true, "cffMasterSid", true, "cffDocDetailsSid", true, "fileSize",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		CffDocDetails existingCffDocDetails = _persistence.fetchByPrimaryKey(newCffDocDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDocDetails, newCffDocDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDocDetails missingCffDocDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffDocDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffDocDetails newCffDocDetails1 = addCffDocDetails();
		CffDocDetails newCffDocDetails2 = addCffDocDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDocDetails1.getPrimaryKey());
		primaryKeys.add(newCffDocDetails2.getPrimaryKey());

		Map<Serializable, CffDocDetails> cffDocDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffDocDetailses.size());
		Assert.assertEquals(newCffDocDetails1,
			cffDocDetailses.get(newCffDocDetails1.getPrimaryKey()));
		Assert.assertEquals(newCffDocDetails2,
			cffDocDetailses.get(newCffDocDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffDocDetails> cffDocDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffDocDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDocDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffDocDetails> cffDocDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffDocDetailses.size());
		Assert.assertEquals(newCffDocDetails,
			cffDocDetailses.get(newCffDocDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffDocDetails> cffDocDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffDocDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDocDetails.getPrimaryKey());

		Map<Serializable, CffDocDetails> cffDocDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffDocDetailses.size());
		Assert.assertEquals(newCffDocDetails,
			cffDocDetailses.get(newCffDocDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffDocDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffDocDetails>() {
				@Override
				public void performAction(CffDocDetails cffDocDetails) {
					Assert.assertNotNull(cffDocDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffDocDetailsSid",
				newCffDocDetails.getCffDocDetailsSid()));

		List<CffDocDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffDocDetails existingCffDocDetails = result.get(0);

		Assert.assertEquals(existingCffDocDetails, newCffDocDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffDocDetailsSid",
				RandomTestUtil.nextInt()));

		List<CffDocDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffDocDetails newCffDocDetails = addCffDocDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffDocDetailsSid"));

		Object newCffDocDetailsSid = newCffDocDetails.getCffDocDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffDocDetailsSid",
				new Object[] { newCffDocDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffDocDetailsSid = result.get(0);

		Assert.assertEquals(existingCffDocDetailsSid, newCffDocDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDocDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffDocDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffDocDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffDocDetails addCffDocDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDocDetails cffDocDetails = _persistence.create(pk);

		cffDocDetails.setFileName(RandomTestUtil.randomString());

		cffDocDetails.setUploadDate(RandomTestUtil.nextDate());

		cffDocDetails.setFileType(RandomTestUtil.randomString());

		cffDocDetails.setUploadBy(RandomTestUtil.nextInt());

		cffDocDetails.setCffMasterSid(RandomTestUtil.nextInt());

		cffDocDetails.setFileSize(RandomTestUtil.randomString());

		_cffDocDetailses.add(_persistence.update(cffDocDetails));

		return cffDocDetails;
	}

	private List<CffDocDetails> _cffDocDetailses = new ArrayList<CffDocDetails>();
	private CffDocDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}