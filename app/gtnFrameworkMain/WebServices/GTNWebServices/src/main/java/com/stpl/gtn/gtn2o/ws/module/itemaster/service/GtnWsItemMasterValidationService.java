package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemPricing;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.ItemPricingQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.itemmaster.GtnWsItemMasterResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
@Service()
public class GtnWsItemMasterValidationService {
    public GtnWsItemMasterValidationService(){
        /**
         * empty constructor
         */
    }

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public void checkIdentifierExist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		List<GtnWsItemIdentifierBean> indentifierList = gtnWsRequest.getGtnWsItemMasterRequest()
				.getGtnWsValidationBean().getGtnWsItemMasterIdentifierBeanList();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		boolean isIdentifierExist = false;
		try {
			for (GtnWsItemIdentifierBean indentifier : indentifierList) {

				Criterion itemIdenfierValueCriterion = Restrictions.eq("itemIdentifierValue",
						indentifier.getItemIdentifierValue());
				Criterion itemIdenfierStartDateCriterion = Restrictions.eq("startDate", indentifier.getStartDate());
				Criterion itemIdentifierQualifierCriterion = Restrictions.eq("itemQualifier",
						session.load(ItemQualifier.class, indentifier.getItemQualifierMasterSid()));
				Criterion itemIdentifierInboundStatus = Restrictions.ne("inboundStatus", 'D');
				List<Criterion> itemIdentifierCriterionList = new ArrayList<>();
				itemIdentifierCriterionList.addAll(
						Arrays.asList(new Criterion[] { itemIdenfierValueCriterion, itemIdenfierStartDateCriterion,
								itemIdentifierQualifierCriterion, itemIdentifierInboundStatus }));

				if (indentifier.getItemIdentifierSid() > 0) {
					Criterion itemIdentifierSID = Restrictions.ne("itemIdentifierSid",
							indentifier.getItemIdentifierSid());
					itemIdentifierCriterionList.add(itemIdentifierSID);
				}
				ProjectionList proj = Projections.projectionList();
				proj.add(Projections.countDistinct("itemIdentifierSid"));
				//
				List<Long> results = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemIdentifier.class,
						itemIdentifierCriterionList, proj);
				if (results != null) {
					isIdentifierExist = (long) results.get(0) > 0;
				}
				if (isIdentifierExist) {
					break;
				}
			}
			tx.commit();
			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			imResponse.setIndentifierExist(isIdentifierExist);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in checkIdentifierExist ", e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void checkItemIdentifierQualfierValueExist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsItemQualifierBean qualfier = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsValidationBean()
				.getGtnWsItemQualifierBean();
		Session session = getSessionFactory().openSession();
		boolean isQualfierNameExist = false;
		boolean isQualfierValueExist = false;
		try {
			Criterion itemQualifierCriterion = Restrictions.eq("itemQualifierValue", qualfier.getItemQualifierValue());

			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("itemQualifierValue"));

			List<Long> results = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemQualifier.class,
					Arrays.asList(new Criterion[] { itemQualifierCriterion }), proj);
			if (results != null) {
				isQualfierValueExist = (long) results.get(0) > 0;
			}
			Criterion itemQualifierNameCriterion = Restrictions.eq("itemQualifierName",
					qualfier.getItemQualifierName());

			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("itemQualifierName"));

			List<Long> results2 = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemQualifier.class,
					Arrays.asList(new Criterion[] { itemQualifierNameCriterion }), proj2);
			if (results2 != null) {
				isQualfierNameExist = (long) results2.get(0) > 0;
			}
			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			imResponse.setIndentifierQualfierValueExist(isQualfierValueExist);
			imResponse.setQualfierNameExist(isQualfierNameExist);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_ITEM_IDENTIFIER_QUALFIER_V, e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void checkItemPricingQualifierValueExist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsItemPricingQualifierBean pricingqualfier = gtnWsRequest.getGtnWsItemMasterRequest()
				.getGtnWsValidationBean().getGtnWsItemPricingQualifierBean();
		Session session = getSessionFactory().openSession();
		boolean isQualfierNameExist = false;
		boolean isQualfierValueExist = false;
		try {

			Criterion pricingQualifierCriterion = Restrictions.eq("pricingQualifier",
					pricingqualfier.getPricingQualifier());

			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("pricingQualifier"));

			List<Long> results = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemPricingQualifier.class,
					Arrays.asList(new Criterion[] { pricingQualifierCriterion }), proj);
			if (results != null) {
				isQualfierValueExist = (long) results.get(0) > 0;
			}

			Criterion pricingQualifierNameCriterion = Restrictions.eq("itemPricingQualifierName",
					pricingqualfier.getItemPricingQualifierName());

			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("itemPricingQualifierName"));

			List<Long> results2 = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemPricingQualifier.class,
					Arrays.asList(new Criterion[] { pricingQualifierNameCriterion }), proj2);
			if (results2 != null) {
				isQualfierNameExist = (long) results2.get(0) > 0;
			}
			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			imResponse.setPricnigQualfierExist(isQualfierValueExist);
			imResponse.setPricnigQualfierNameExist(isQualfierNameExist);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkItemPricingQualifierValueExist ", e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void checkItemMasterIdNameNdc8Exist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsItemMasterBean itemMasterBean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();
		Session session = getSessionFactory().openSession();
		boolean isItemIdExist = false;
		boolean isItemNoExist = false;
		boolean isNdc8Exist = false;
		int systemId = itemMasterBean.getGtnWsItemMasterInfoBean().getItemMasterSid();
		try {
			Criterion itemIdCriterion = Restrictions.eq("itemId",
					itemMasterBean.getGtnWsItemMasterInfoBean().getItemId());
			List<Criterion> itemValidationCriterionList = new ArrayList<>();
			itemValidationCriterionList.add(itemIdCriterion);
			if (systemId > 0) {
				Criterion itemMasterSIDCriterion = Restrictions.ne(GtnFrameworkWebserviceConstant.ITEM_MASTER_SID,
						systemId);
				itemValidationCriterionList.add(itemMasterSIDCriterion);
			}
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("itemId"));

			List<Long> results = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemMaster.class,
					itemValidationCriterionList, proj);
			if (results != null) {
				isItemIdExist = (long) results.get(0) > 0;
			}

			Criterion itemNoUniqueValidationCriterion = Restrictions.eq("itemNo",
					itemMasterBean.getGtnWsItemMasterInfoBean().getItemNo());
			List<Criterion> itemNoValidationCriterionList = new ArrayList<>();
			itemNoValidationCriterionList.add(itemNoUniqueValidationCriterion);
			if (systemId > 0) {
				Criterion itemMasterSIDCriterion = Restrictions.ne(GtnFrameworkWebserviceConstant.ITEM_MASTER_SID,
						systemId);
				itemNoValidationCriterionList.add(itemMasterSIDCriterion);
			}
			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("itemNo"));

			List<Long> results2 = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemMaster.class,
					itemNoValidationCriterionList, proj2);

			if (results2 != null) {
				isItemNoExist = (long) results2.get(0) > 0;
			}

			Criterion ndc8Criterion = Restrictions.eq("ndc8", itemMasterBean.getGtnWsItemMasterInfoBean().getNdc8());
			// ndc8Criterion
			List<Criterion> ndcValidationCriterionList = new ArrayList<>();
			ndcValidationCriterionList.add(ndc8Criterion);

			if (systemId > 0) {
				Criterion itemMasterSIDCriterion = Restrictions.ne(GtnFrameworkWebserviceConstant.ITEM_MASTER_SID,
						systemId);
				ndcValidationCriterionList.add(itemMasterSIDCriterion);
			}

			ProjectionList proj3 = Projections.projectionList();
			proj3.add(Projections.countDistinct("ndc8"));

			List<Long> results3 = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(ItemMaster.class,
					ndcValidationCriterionList, proj3);
			if (results3 != null) {
				isNdc8Exist = (long) results3.get(0) > 0;
			}
			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			GtnWsValidationBean bean = new GtnWsValidationBean();
			bean.setItemIdExist(isItemIdExist);
			bean.setItemNoExist(isItemNoExist);
			bean.setNdc8Exist(isNdc8Exist);
			imResponse.setGtnWsValidationBean(bean);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkItemMasterIdNameNdc8Exist ", e);

		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void checkItemIdentifierQualfierValueUsed(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsItemQualifierBean qualfier = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsValidationBean()
				.getGtnWsItemQualifierBean();
		Session session = getSessionFactory().openSession();
		boolean isQualfierUsed = false;
		try {
			Criteria itemIdentifierCriteria = session.createCriteria(ItemIdentifier.class, "ID");
			Criteria itemQualifierCriteria = itemIdentifierCriteria.createCriteria("itemQualifier", "IQ");
			itemQualifierCriteria.add(Restrictions.eq("itemQualifierSid", qualfier.getItemQualifierSid()));
			itemIdentifierCriteria.setProjection(Projections.rowCount());
			List<Long> results = (List<Long>) itemIdentifierCriteria.list();

			if (results != null) {
				isQualfierUsed = (long) results.get(0) > 0;
			}

			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			imResponse.setIndentifierQualfierUsed(isQualfierUsed);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_ITEM_IDENTIFIER_QUALFIER_V, e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void checkPricingIdentifierQualifierValueUsed(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsItemPricingQualifierBean qualfier = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsValidationBean()
				.getGtnWsItemPricingQualifierBean();
		Session session = getSessionFactory().openSession();
		boolean isQualfierUsed = false;
		try {
			Criteria itemIdentifierCriteria = session.createCriteria(ItemPricing.class, "PD");
			Criteria itemQualifierCriteria = itemIdentifierCriteria.createCriteria("itemPricingQualifier", "PQ");
			itemQualifierCriteria
					.add(Restrictions.eq("itemPricingQualifierSid", qualfier.getItemPricingQualifierSid()));
			itemIdentifierCriteria.setProjection(Projections.rowCount());
			List<Long> results = (List<Long>) itemIdentifierCriteria.list();

			if (results != null) {
				isQualfierUsed = (long) results.get(0) > 0;
			}

			GtnWsItemMasterResponse imResponse = new GtnWsItemMasterResponse();
			imResponse.setPricingQualfierUsed(isQualfierUsed);
			gtnResponse.setGtnWsItemMasterResponse(imResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_ITEM_IDENTIFIER_QUALFIER_V, e);
		} finally {
			session.close();
		}

	}

}
