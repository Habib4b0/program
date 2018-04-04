package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnWsCMasterQualifierBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
public class GtnWsCMasterAddQualifier {
	public GtnWsCMasterAddQualifier() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterAddQualifier.class);

	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
			+ GtnWebServiceUrlConstants.GTN_CM_GET_COMPANY_QUALIFIER, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCompanyQualifier(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering getCompanyQualifier");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			boolean isCount = gtnWsRequest.getGtnWsSearchRequest().isCount();
			List<Object[]> result;
			if (isCount) {
				result = gtnGeneralServiceController
						.executeQuery(gtnWsSqlService.getQuery("getCompanyQualifierCountQuery"));
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));

			} else {
				result = gtnGeneralServiceController
						.executeQuery(gtnWsSqlService.getQuery("getCompanyQualifierSelectQuery"));
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Exception in executig query-", ex);
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} finally {
			logger.info("Exit getCompanyIdentifier");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
			+ GtnWebServiceUrlConstants.GTN_CM_QUALIFIER_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCmpnyQualifier(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		gtnResponse.getGtnWsGeneralResponse().setSucess(true);
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsCMasterQualifierBean bean = gtnCMasterRequest.getGtnCMasterQualifierBean();
			CompanyQualifier masterData;
			if (bean.getCompanyQualifierSid() == 0) {
				masterData = new CompanyQualifier();
			} else {
				masterData = session.get(CompanyQualifier.class, bean.getCompanyQualifierSid());
			}
			masterData.setCompanyQualifierSid(bean.getCompanyQualifierSid());
			masterData.setCompanyQualifierName(bean.getCompanyQualifierName());
			masterData.setCompanyQualifierValue(bean.getCompanyQualifierValue());
			masterData.setEffectiveDates(bean.getEffectiveDates());
			masterData.setNotes(bean.getNotes());
			masterData.setInboundStatus(bean.getCompanyQualifierSid() != 0 ? 'C' : 'A');
			masterData.setRecordLockStatus(false);
			masterData.setSource("GTN");
			if (bean.getCompanyQualifierSid() == 0) {
				masterData.setCreatedBy(bean.getUserId());
				masterData.setCreatedDate(new Date());
			} else {
				masterData.setCreatedBy(masterData.getCreatedBy());
				masterData.setCreatedDate(masterData.getCreatedDate());
			}
			masterData.setModifiedBy(bean.getUserId());
			masterData.setModifiedDate(new Date());
			masterData.setBatchId("");
			session.saveOrUpdate(masterData);
			tx.commit();

		} catch (Exception e) {
			logger.error("Exception", e);
			tx.rollback();
		} finally {
			session.close();
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
			+ GtnWebServiceUrlConstants.GTN_CM_QUALIFIER_DELETE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteCmpnyQualifier(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse) {
		logger.info("Entering DeleteCmpnyQualifier");
		int countUpdate = 0;
		List<Object> qualifierIdCriteria = new ArrayList<>();
		qualifierIdCriteria.add(gtnWsRequest.getGtnCMasterRequest().getGtnCMasterQualifierBean().getUserId());
		qualifierIdCriteria.add(gtnWsRequest.getGtnCMasterRequest().getGtnCMasterQualifierBean().getCompanyQualifierSid());
		try {
			countUpdate=gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery(qualifierIdCriteria,"getCompanyQualifierDeleteQuery"));
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Exception in executig query-", ex);
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} finally {
			logger.info("Exit DeleteCmpnyQualifier");
		}
		if(countUpdate>0)
		{
		gtnResponse.getGtnWsGeneralResponse().setSucess(true);
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
			+ GtnWebServiceUrlConstants.GTN_CM_IDENTIFIER_QUALIFIER_VALIDATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkCompanyIdentifierQualfierValueUsed(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		GtnWsCMasterQualifierBean qualifier = gtnWsRequest.getGtnCMasterRequest().getGtnCMasterQualifierBean();

		Session session = getSessionFactory().openSession();
		boolean isQualfierUsed = false;
		try {
			Criteria companyIdentifierCriteria = session.createCriteria(CompanyIdentifier.class, "ID");
			Criteria companyQualifierCriteria = companyIdentifierCriteria.createCriteria("companyQualifier", "CQ");
			companyQualifierCriteria.add(Restrictions.eq("companyQualifierSid", qualifier.getCompanyQualifierSid()));
			companyIdentifierCriteria.setProjection(Projections.rowCount());
			@SuppressWarnings("unchecked")
			List<Long> results = companyIdentifierCriteria.list();

			if (results != null) {
				isQualfierUsed = results.get(0) > 0;
			}

			GtnCompanyMasterResponse cmResponse = new GtnCompanyMasterResponse();
			cmResponse.setIndentifierQualfierUsed(isQualfierUsed);
			gtnResponse.setGtnCompanyMasterResponse(cmResponse);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkItemIdentifierQualfierValueExist ", e);
		} finally {
			session.close();
		}
		gtnResponse.getGtnWsGeneralResponse().setSucess(true);
		return gtnResponse;

	}

}
