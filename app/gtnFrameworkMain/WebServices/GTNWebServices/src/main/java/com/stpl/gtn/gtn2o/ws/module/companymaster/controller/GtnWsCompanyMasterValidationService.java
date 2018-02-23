package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterValidationBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER)
public class GtnWsCompanyMasterValidationService {
	public GtnWsCompanyMasterValidationService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCompanyMasterValidationService.class);

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_VALIDATION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkIdentifierExist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		List<GtnCMasterIdentifierInfoBean> indentifierList = gtnWsRequest.getGtnCMasterRequest()
				.getGtnCMasterValidationBean().getGtnWsCompanyMasterIdentifierBeanList();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		boolean isIdentifierExist = false;
		try {
			for (GtnCMasterIdentifierInfoBean indentifier : indentifierList) {

				Criterion compIdenfierValueCriterion = Restrictions.eq("companyIdentifierValue",
						indentifier.getCompanyIdentifierValue());
				Criterion compIdenfierStartDateCriterion = Restrictions.eq("startDate",
						indentifier.getCompanyIdentifierStartDate());
				Criterion compIdentifierQualifierCriterion = Restrictions.eq("companyQualifier",
						session.load(CompanyQualifier.class, indentifier.getCompanyQualifierSid()));
				Criterion compIdentifierInboundStatus = Restrictions.ne("inboundStatus", 'D');
				List<Criterion> compIdentifierCriterionList = new ArrayList<>();
				compIdentifierCriterionList.addAll(
						Arrays.asList(new Criterion[] { compIdenfierValueCriterion, compIdenfierStartDateCriterion,
								compIdentifierQualifierCriterion, compIdentifierInboundStatus }));

				if (!indentifier.getCompanyIdentifierValue().isEmpty()) {
					Criterion compIdentifierSID = Restrictions.ne("companyIdentifierValue",
							indentifier.getCompanyIdentifierValue());
					compIdentifierCriterionList.add(compIdentifierSID);
				}
				ProjectionList proj = Projections.projectionList();
				proj.add(Projections.countDistinct("companyIdentifierSid"));
				//
				@SuppressWarnings("unchecked")
				List<Long> results = (List<Long>) gtnSqlQueryEngine.executeSelectQuery(CompanyIdentifier.class,
						compIdentifierCriterionList, proj);
				if (results != null) {
					isIdentifierExist = (long) results.get(0) > 0;
				}
				if (isIdentifierExist) {
					break;
				}
			}
			tx.commit();
			GtnCompanyMasterResponse imResponse = new GtnCompanyMasterResponse();
			imResponse.setIndentifierExist(isIdentifierExist);
			gtnResponse.setGtnCompanyMasterResponse(imResponse);
			return gtnResponse;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in checkIdentifierExist ", e);
		} finally {
			session.close();
		}

	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_VALIDATION_EXISTS, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkCompMasterIdNameExist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {

		GtnCMasterBean companyMasterBean = gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean();
		Session session = getSessionFactory().openSession();
		boolean iscompanyIdExist = false;
		boolean iscompanyNoExist = false;
		int systemId = companyMasterBean.getGtnCMasterInformationBean().getCompanyMasterSystemId();
		try {

			List<String> compIdCriteria = new ArrayList<>();
			compIdCriteria.add(companyMasterBean.getGtnCMasterInformationBean().getCompanyId());
			List<Integer> resultsDb = null;
			resultsDb = checkConditionForCompanyId(systemId, compIdCriteria);

			if (resultsDb != null) {
				iscompanyIdExist = exceptionHandledCompanyId(iscompanyIdExist, resultsDb);
			}
			List<String> compNoCriteria = new ArrayList<>();
			compNoCriteria.add(companyMasterBean.getGtnCMasterInformationBean().getCompanyNo());
			List<Integer> resultsDb2 = null;
			resultsDb2 = checkConditionForCompanyNo(systemId, compNoCriteria);

			if (resultsDb2 != null) {
				iscompanyNoExist = exceptionHandledCompanyNo(iscompanyNoExist, resultsDb2);
			}
			GtnCompanyMasterResponse imResponse = new GtnCompanyMasterResponse();
			GtnCMasterValidationBean bean = new GtnCMasterValidationBean();
			bean.setCompanyIdExist(iscompanyIdExist);
			bean.setCompanyNoExist(iscompanyNoExist);
			imResponse.setGtnWsCMValidationBean(bean);
			gtnResponse.setGtnCompanyMasterResponse(imResponse);
			return gtnResponse;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in CompanyNo and Company Id Check ", e);

		} finally {
			session.close();
		}

	}

	private boolean exceptionHandledCompanyId(boolean isCompExist, List<Integer> resultsDb) {
		boolean iscompanyIdExist = isCompExist;
		try {
			iscompanyIdExist = checkCompanyIdExist(iscompanyIdExist, resultsDb);
		} catch (Exception e) {
			LOGGER.error("Exception:" + e);
		}
		return iscompanyIdExist;
	}

	private boolean exceptionHandledCompanyNo(boolean iscompNoExist, List<Integer> resultsDb2) {
		boolean iscompanyNoExist = iscompNoExist;
		try {
			iscompanyNoExist = checkCompanyNoExist(iscompanyNoExist, resultsDb2);
		} catch (Exception e) {
			LOGGER.error("Exception:" + e);
		}
		return iscompanyNoExist;
	}

	private boolean checkCompanyNoExist(boolean iscompNoExist, List<Integer> resultsDb2) {
		boolean iscompNumberExist = iscompNoExist;
		if (!resultsDb2.isEmpty()) {
			iscompNumberExist = resultsDb2.get(0) > 0;
		}
		return iscompNumberExist;
	}

	private boolean checkCompanyIdExist(boolean iscompIdExist, List<Integer> resultsDb) {
		boolean iscompanyIdExist = iscompIdExist;

		if (!resultsDb.isEmpty()) {
			iscompanyIdExist = resultsDb.get(0) > 0;
		}
		return iscompanyIdExist;
	}

	@SuppressWarnings("unchecked")
	private List<Integer> checkConditionForCompanyNo(int systemId, List<String> compNoCriteria)
			throws GtnFrameworkGeneralException {

		List<Integer> resultsDb2;
		if (systemId == 0) {
			resultsDb2 = (List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(compNoCriteria, "checkCompanyNoStatusNotD"));
		} else {
			compNoCriteria.add(String.valueOf(systemId));
			resultsDb2 = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(
					gtnWsSqlService.getQuery(compNoCriteria, "checkCompanyNoStatusNotDNotSameSystemId"));
		}
		return resultsDb2;
	}

	@SuppressWarnings("unchecked")
	private List<Integer> checkConditionForCompanyId(int systemId, List<String> compIdCriteria)
			throws GtnFrameworkGeneralException {
		List<Integer> resultsDb;
		if (systemId == 0) {
			resultsDb = (List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(compIdCriteria, "checkCompanyIDStatusNotD"));
		} else {
			compIdCriteria.add(String.valueOf(systemId));
			resultsDb = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(
					gtnWsSqlService.getQuery(compIdCriteria, "checkCompanyIDStatusNotDNotSameSystemId"));
		}
		return resultsDb;
	}

}
